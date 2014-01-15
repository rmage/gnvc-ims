package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishWsDao;
import com.app.wms.engine.db.dao.FishWsDetailDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishWs;
import com.app.wms.engine.db.dto.FishWsDetail;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishWsDetailDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishWsDetail>, FishWsDetailDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishWsDetail dto) {
		String query = "INSERT INTO " + getTableName() + 
				" (ws_id, fish_id, total_weight," +
				" created_date, created_by, is_active, is_delete)" +
				" VALUES (?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getWsId(), dto.getFishId(), dto.getTotalWeight(),
				dto.getCreatedDate(), dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		
		return primaryKey;
	}

	@Override
	public void update(int id, FishWsDetail dto) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET ws_id=?, fish_id=?, total_weight=?," +
				" updated_date=?, updated_by=?, is_active=?, is_delete=?" +
				" WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getWsId(), dto.getFishId(), dto.getTotalWeight(),
				dto.getUpdatedDate(), dto.getUpdatedBy(), dto.getIsActive(), dto.getIsDelete(), id});

	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET is_active='N', is_delete='Y' WHERE id=?";
		
		jdbcTemplate.update(query, id);

	}

	@Override
	public FishWsDetail findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishWsDetail> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishWsDetail> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishWsDetail> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishWsDetail> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishWsDetail> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public List<FishWsDetail> findByWsId(int wsId) {
		String query = "SELECT * FROM " + getTableName() + " WHERE ws_id=?";
		List<FishWsDetail> resultList = jdbcTemplate.query(query, this, wsId);
		
		return resultList;
	}
	
	@Override
	public List<FishWsDetail> findByWsDetailIds(String wsDetailIds) {
		String query = "SELECT * FROM " + getTableName() + " WHERE ws_id IN (:ids)";
		String[] idStrings = wsDetailIds.split(",");
		Set<Integer> ids = new HashSet<Integer>();
		
		for (String idString : idStrings) {
			ids.add(Integer.valueOf(idString));
		}
		
		NamedParameterJdbcTemplate namedJdbcTemplate =  
				new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ids", ids);
		List<FishWsDetail> resultList = namedJdbcTemplate.query(query, parameters, this);
		
		return resultList;
	}
	
	@Override
	public List<FishWsDetail> findByWsIdGroupByFish(int wsId) {
		String query = "SELECT MAX(wsd.id) AS id, MAX(wsd.ws_id) AS ws_id, wsd.fish_id, " +
				"SUM(wsd.total_weight) AS total_weight, MAX(wsd.created_date) AS created_date, " +
				"MAX(wsd.created_by) AS created_by, MAX(wsd.updated_date) AS updated_date, " +
				"MAX(wsd.updated_by) AS updated_by, MAX(wsd.is_active) AS is_active, MAX(wsd.is_delete) AS is_delete " +
				"FROM inventory..fish_ws_detail wsd " +
				"WHERE wsd.ws_id = ? GROUP BY wsd.fish_id";
		
		List<FishWsDetail> resultList = jdbcTemplate.query(query, this, wsId);
		return resultList;
	}

	@Override
	public FishWsDetail mapRow(ResultSet rs, int row) throws SQLException {
		FishWsDetail dto = new FishWsDetail();
		
		dto.setId(rs.getInt("id"));
		dto.setWsId(rs.getInt("ws_id"));
		dto.setFishId(rs.getInt("fish_id"));
		dto.setTotalWeight(rs.getDouble("total_weight"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		try {
			FishWsDao wsDao = DaoFactory.createFishWsDao();
			FishWs weightSlip = wsDao.findByPrimaryKey(dto.getWsId());
			dto.setWeightSlip(weightSlip);
			
			FishDao fishDao = DaoFactory.createFishDao();
			Fish fish = fishDao.findByPrimaryKey(dto.getFishId());
			dto.setFish(fish);
		}
		catch(DaoException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public String getTableName() {
		return "inventory..fish_ws_detail";
	}

}
