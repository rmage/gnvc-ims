package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dao.FishWdsDao;
import com.app.wms.engine.db.dao.FishWdsDetailDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.dto.FishWds;
import com.app.wms.engine.db.dto.FishWdsDetail;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishWdsDetailDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishWdsDetail>, FishWdsDetailDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishWdsDetail dto) {
		String query = "INSERT INTO " + getTableName() +
				" (wds_id, fish_id, description, storage_id, qty, uom_code," +
				" created_date, created_by, is_active, is_delete)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getWdsId(), dto.getFishId(), dto.getDescription(),
				dto.getStorageId(), dto.getQuantity(), dto.getUomCode(), dto.getCreatedDate(),
				dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishWdsDetail dto) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET wds_id=?, fish_id=?, description=?, storage_id=?, qty=?, uom_code=?," +
				" updated_date=?, updated_by=?, is_active=?, is_delete=?," +
				" WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getWdsId(), dto.getFishId(), dto.getDescription(),
				dto.getStorageId(), dto.getQuantity(), dto.getUomCode(), dto.getCreatedDate(),
				dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete(), id});

	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET is_active='N', is_delete='Y' WHERE id=?";
		
		jdbcTemplate.update(query, id);
	}
    
    public void deleteAllByWdsId(int wdsId) {
        String query = "UPDATE " + getTableName() + 
				" SET is_active='N', is_delete='Y' WHERE wds_id=?";
		
		jdbcTemplate.update(query, wdsId);
    }

	@Override
	public FishWdsDetail findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishWdsDetail> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishWdsDetail> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishWdsDetail> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishWdsDetail> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishWdsDetail> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public List<FishWdsDetail> findAllByWdsId(int wdsId) {
		String query = "SELECT * FROM " + getTableName() + " WHERE wds_id=?";
		List<FishWdsDetail> resultList = jdbcTemplate.query(query, this, wdsId);
		
		return resultList;
	}

	@Override
	public FishWdsDetail mapRow(ResultSet rs, int row) throws SQLException {
		FishWdsDetail dto = new FishWdsDetail();
		dto.setId(rs.getInt("id"));
		dto.setWdsId(rs.getInt("wds_id"));
		dto.setFishId(rs.getInt("fish_id"));
		dto.setDescription(rs.getString("description"));
		dto.setStorageId(rs.getInt("storage_id"));
		dto.setQuantity(rs.getDouble("qty"));
		dto.setUomCode(rs.getString("uom_code"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		try {
			FishWdsDao wdsDao = DaoFactory.createFishWdsDao();
			FishWds withdrawalSlip = wdsDao.findByPrimaryKey(dto.getWdsId());
			dto.setWithdrawalSlip(withdrawalSlip);
			
			FishDao fishDao = DaoFactory.createFishDao();
			Fish fish = fishDao.findByPrimaryKey(dto.getFishId());
			dto.setFish(fish);
			
			FishStorageDao storageDao = DaoFactory.createFishStorageDao();
			FishStorage storage = storageDao.findByPrimaryKey(dto.getStorageId());
			dto.setStorage(storage);
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public String getTableName() {
		return "fish_wds_detail";
	}
}
