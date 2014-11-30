package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishBalanceDao;
import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishBalance;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishBalanceDaoImpl extends AbstractDAO implements
        ParameterizedRowMapper<FishBalance>, FishBalanceDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public int insert(FishBalance dto) {
        String query = "INSERT INTO " + getTableName()
                + " (vessel_id, storage_id, fish_id, balance,"
                + " created_date, created_by, is_active, is_delete)"
                + " VALUES(?,?,?,?,?,?,?,?)";

        SqlUpdate su = new SqlUpdate(dataSource, query);
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.compile();

        su.update(new Object[]{dto.getVesselId(), dto.getStorageId(), dto.getFishId(),
            dto.getBalance(), dto.getCreatedDate(), dto.getCreatedBy(),
            dto.getIsActive(), dto.getIsDelete()});

        int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
        return primaryKey;
    }

    @Override
    public void update(int id, FishBalance dto) throws DaoException {
        String query = "UPDATE " + getTableName()
                + " SET vessel_id=?, storage_id=?, fish_id=?, balance=?,"
                + " updated_date=?, updated_by=?, is_active=?, is_delete=?"
                + " WHERE id=?";

        SqlUpdate su = new SqlUpdate(dataSource, query);
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.compile();

        su.update(new Object[]{dto.getVesselId(), dto.getStorageId(), dto.getFishId(),
            dto.getBalance(), dto.getUpdatedDate(), dto.getUpdatedBy(),
            dto.getIsActive(), dto.getIsDelete(), id});
    }

    @Override
    public void delete(int id) throws DaoException {
        String query = "UPDATE " + getTableName()
                + " SET is_active='N', is_delete='Y' WHERE id=?";

        jdbcTemplate.update(query, id);
    }

    @Override
    public FishBalance findByPrimaryKey(int id) throws DaoException {
        String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
        List<FishBalance> resultList = jdbcTemplate.query(query, this, id);

        return resultList.size() == 0 ? null : resultList.get(0);
    }

    @Override
    public List<FishBalance> findAll() throws DaoException {
        String query = "SELECT * FROM " + getTableName();
        List<FishBalance> resultList = jdbcTemplate.query(query, this);

        return resultList;
    }

    @Override
    public List<FishBalance> findAllActive() throws DaoException {
        String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
        List<FishBalance> resultList = jdbcTemplate.query(query, this);

        return resultList;
    }

    @Override
    public FishBalance findUniqueFishBalance(int vesselId, int storageId, int fishId) {
        String query = "SELECT * FROM " + getTableName()
                + " WHERE vessel_id=? AND storage_id=? AND fish_id=?";

        List<FishBalance> resultList = jdbcTemplate.query(query, this,
                vesselId, storageId, fishId);

        return resultList.size() == 0 ? null : resultList.get(0);
    }

    @Override
    public List<FishBalance> findBalanceByVesselId(int vesselId) {
        String query = "SELECT * FROM " + getTableName()
                + " WHERE vessel_id=? AND balance > 0";
        List<FishBalance> resultList = jdbcTemplate.query(query, this, vesselId);

        return resultList;
    }

    @Override
    public FishBalance mapRow(ResultSet rs, int row) throws SQLException {
        FishBalance dto = new FishBalance();
        dto.setId(rs.getInt("id"));
        dto.setVesselId(rs.getInt("vessel_id"));
        dto.setStorageId(rs.getInt("storage_id"));
        dto.setFishId(rs.getInt("fish_id"));
        dto.setBalance(rs.getDouble("balance"));
        dto.setCreatedDate(rs.getDate("created_date"));
        dto.setCreatedBy(rs.getString("created_by"));
        dto.setUpdatedDate(rs.getDate("updated_date"));
        dto.setUpdatedBy(rs.getString("updated_by"));
        dto.setIsActive(rs.getString("is_active"));
        dto.setIsDelete(rs.getString("is_delete"));

        try {
            FishVesselDao vesselDao = DaoFactory.createFishVesselDao();
            FishVessel vessel = vesselDao.findByPrimaryKey(dto.getVesselId());
            dto.setVessel(vessel);

            FishStorageDao storageDao = DaoFactory.createFishStorageDao();
            FishStorage storage = storageDao.findByPrimaryKey(dto.getStorageId());
            dto.setStorage(storage);

            FishDao fishDao = DaoFactory.createFishDao();
            Fish fish = fishDao.findByPrimaryKey(dto.getFishId());
            dto.setFish(fish);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return dto;
    }

    public String getTableName() {
        return "fish_balance";
    }

    /* GNVS | Actual Balance */
    public int insertActual(FishBalance dto) {
        String query = "INSERT INTO fish_balance_actual"
                + " (vessel_id, storage_id, fish_id, balance,"
                + " created_date, created_by, is_active, is_delete)"
                + " VALUES(?,?,?,?,?,?,?,?); SELECT @@IDENTITY;";

        int primaryKey = jdbcTemplate.queryForInt(query,
                new Object[]{dto.getVesselId(), dto.getStorageId(), dto.getFishId(),
                    dto.getBalance(), dto.getCreatedDate(), dto.getCreatedBy(),
                    dto.getIsActive(), dto.getIsDelete()}
        );
        return primaryKey;
    }

    @Override
    public void updateActual(int id, FishBalance dto) throws DaoException {
        String query = "UPDATE fish_balance_actual"
                + " SET vessel_id=?, storage_id=?, fish_id=?, balance=?,"
                + " updated_date=?, updated_by=?, is_active=?, is_delete=?"
                + " WHERE id=?";

        SqlUpdate su = new SqlUpdate(dataSource, query);
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.compile();

        su.update(new Object[]{dto.getVesselId(), dto.getStorageId(), dto.getFishId(),
            dto.getBalance(), dto.getUpdatedDate(), dto.getUpdatedBy(),
            dto.getIsActive(), dto.getIsDelete(), id});
    }

    @Override
    public FishBalance findByPrimaryKeyActual(int id) throws DaoException {
        String query = "SELECT * FROM fish_balance_actual WHERE id=?";
        List<FishBalance> resultList = jdbcTemplate.query(query, this, id);

        return resultList.size() == 0 ? null : resultList.get(0);
    }

    @Override
    public FishBalance findUniqueFishBalanceActual(int vesselId, int storageId, int fishId) {
        String query = "SELECT * FROM fish_balance_actual WHERE vessel_id=? AND storage_id=? AND fish_id=?";

        List<FishBalance> resultList = jdbcTemplate.query(query, this,
                vesselId, storageId, fishId);

        return resultList.size() == 0 ? null : resultList.get(0);
    }

    public List<FishBalance> getWithdrawableFish(int vesselId) {
        String query = "SELECT fb.* FROM fish_balance fb "
                + "	LEFT JOIN fish_storage fs ON fs.id = storage_id "
                + "	LEFT JOIN fish f ON f.id = fb.fish_id "
                + "	LEFT JOIN fish_type ft ON ft.id = f.fish_type_id "
                + "	LEFT JOIN fish_weight_type fwt ON fwt.id = f.fish_weight_type_id "
//                + "WHERE fb.vessel_id = ? AND fb.balance IS NOT NULL AND fb.balance > 0 "
                + "WHERE fb.vessel_id = ? AND fb.balance IS NOT NULL "
                + "ORDER BY fs.description, ft.description, fwt.code";
//        String query = "SELECT * FROM fish_balance_actual WHERE vessel_id = ? AND balance IS NOT NULL";
        List<FishBalance> resultList = jdbcTemplate.query(query, this, vesselId);

        return resultList;
    }
    
    public List<FishBalance> getWithdrawableFish(int vesselId, int storageId) {
        String query = "SELECT fb.* FROM fish_balance fb "
                + "	LEFT JOIN fish_storage fs ON fs.id = storage_id "
                + "	LEFT JOIN fish f ON f.id = fb.fish_id "
                + "	LEFT JOIN fish_type ft ON ft.id = f.fish_type_id "
                + "	LEFT JOIN fish_weight_type fwt ON fwt.id = f.fish_weight_type_id "
                + "WHERE fb.vessel_id = ? AND fb.balance IS NOT NULL AND fb.storage_id = ? "
                + "ORDER BY fs.description, ft.description, fwt.code";
        List<FishBalance> resultList = jdbcTemplate.query(query, this, vesselId, storageId);

        return resultList;
    }

}
