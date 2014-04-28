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
import com.app.wms.engine.db.dao.FishRrDao;
import com.app.wms.engine.db.dao.FishRrDetailDao;
import com.app.wms.engine.db.dao.FishWsDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishRr;
import com.app.wms.engine.db.dto.FishRrDetail;
import com.app.wms.engine.db.dto.FishWs;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishRrDetailDaoImpl extends AbstractDAO implements 
		ParameterizedRowMapper<FishRrDetail>, FishRrDetailDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishRrDetail dto) {
		String query = "INSERT INTO " + getTableName() + 
				" (rr_id, ws_id, fish_id, good_weight, spoilage_weight, total_weight," +
				" storage_id, created_date, created_by, is_active, is_delete)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getRrId(), dto.getWsId(), dto.getFishId(),
				dto.getGoodWeight(), dto.getSpoilageWeight(), dto.getTotalWeight(),
				dto.getStorageId(), dto.getCreatedDate(), dto.getCreatedBy(),
				dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishRrDetail dto) throws DaoException {
		String query = "UPDATE " + getTableName() +
				" SET rr_id=?, ws_id=?, fish_id=?, good_weight=?, spoilage_weight=?," +
				" total_weight=?, storage_id=?, updated_date=?, updated_by=?," +
				" is_active=?, is_delete=? WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getRrId(), dto.getWsId(), dto.getFishId(),
				dto.getGoodWeight(), dto.getSpoilageWeight(), dto.getTotalWeight(),
				dto.getStorageId(), dto.getUpdatedDate(), dto.getUpdatedBy(),
				dto.getIsActive(), dto.getIsDelete(), id});
	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() +
				" SET is_active='N', is_delete='Y' WHERE id=?";
		
		jdbcTemplate.update(query, id);
	}
    
    public void deleteAllByRrId(int rrId) {
        String query = "UPDATE " + getTableName() +
				" SET is_active='N', is_delete='Y' WHERE rr_id=?";
		
		jdbcTemplate.update(query, rrId);
    }

	@Override
	public FishRrDetail findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishRrDetail> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishRrDetail> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishRrDetail> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishRrDetail> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishRrDetail> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public List<FishRrDetail> findAllByRrId(int rrId) {
		String query = "SELECT * FROM " + getTableName() + " WHERE rr_id=?";
		List<FishRrDetail> resultList = jdbcTemplate.query(query, this, rrId);
		
		return resultList;
	}
    
    @Override
    public List<FishRrDetail> findByRrIdGroupByFish(int rrId) {
        String query = "SELECT MAX(rrd.id) AS id, MAX(rrd.ws_id) AS ws_id, MAX(rrd.rr_id) AS rr_id, rrd.fish_id, " +
				"SUM(rrd.good_weight) AS good_weight, SUM(rrd.spoilage_weight) AS spoilage_weight, " +
				"SUM(total_weight) AS total_weight, MAX(rrd.storage_id) AS storage_id, MAX(rrd.created_date) AS created_date, " +
				"MAX(rrd.created_by) AS created_by, MAX(rrd.updated_date) AS updated_date, " +
				"MAX(rrd.updated_by) AS updated_by, MAX(rrd.is_active) AS is_active, MAX(rrd.is_delete) AS is_delete " +
				"FROM inventory..fish_rr_detail rrd " +
				"WHERE rrd.rr_id=? " +
				"GROUP BY rrd.fish_id";
        
        System.out.println("++++++ "+query);
        System.out.println("++++++ "+rrId);
		
		List<FishRrDetail> resultList = jdbcTemplate.query(query, this, rrId);
		return resultList;
    }
	
	@Override
	public List<FishRrDetail> findByRrIdGroupByFishAndStorage(int rrId) {
		String query = "SELECT MAX(rrd.id) AS id, MAX(rrd.ws_id) AS ws_id, MAX(rrd.rr_id) AS rr_id, rrd.fish_id, " +
				"SUM(rrd.good_weight) AS good_weight, SUM(rrd.spoilage_weight) AS spoilage_weight, " +
				"SUM(total_weight) AS total_weight, rrd.storage_id, MAX(rrd.created_date) AS created_date, " +
				"MAX(rrd.created_by) AS created_by, MAX(rrd.updated_date) AS updated_date, " +
				"MAX(rrd.updated_by) AS updated_by, MAX(rrd.is_active) AS is_active, MAX(rrd.is_delete) AS is_delete " +
				"FROM inventory..fish_rr_detail rrd " +
				"WHERE rrd.rr_id=? " +
				"GROUP BY rrd.fish_id, rrd.storage_id";
		
		List<FishRrDetail> resultList = jdbcTemplate.query(query, this, rrId);
		return resultList;
	}

	@Override
	public FishRrDetail mapRow(ResultSet rs, int row) throws SQLException {
		FishRrDetail dto = new FishRrDetail();
		dto.setId(rs.getInt("id"));
		dto.setRrId(rs.getInt("rr_id"));
		dto.setWsId(rs.getInt("ws_id"));
		dto.setFishId(rs.getInt("fish_id"));
		dto.setGoodWeight(rs.getDouble("good_weight"));
		dto.setSpoilageWeight(rs.getDouble("spoilage_weight"));
		dto.setTotalWeight(rs.getDouble("total_weight"));
		dto.setStorageId(rs.getInt("storage_id"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		try{
			FishRrDao rrDao = DaoFactory.createFishRrDao();
			FishRr receivingReport = rrDao.findByPrimaryKey(dto.getRrId());
			dto.setReceivingReport(receivingReport);
			
			FishWsDao wsDao = DaoFactory.createFishWsDao();
			FishWs weightSlip = wsDao.findByPrimaryKey(dto.getWsId());
			dto.setWeightSlip(weightSlip);
			
			FishDao fishDao = DaoFactory.createFishDao();
			Fish fish = fishDao.findByPrimaryKey(dto.getFishId());
			dto.setFish(fish);
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public String getTableName() {
		return "inventory..fish_rr_detail";
	}
}
