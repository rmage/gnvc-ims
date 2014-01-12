package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dao.FishWsDao;
import com.app.wms.engine.db.dao.FishWsTypeDao;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.dto.FishWs;
import com.app.wms.engine.db.dto.FishWSType;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishWsDaoImpl extends AbstractDAO 
	implements ParameterizedRowMapper<FishWs>, FishWsDao {
	
	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishWs dto) {
		String query = "INSERT INTO " + getTableName() + 
				" (ws_type_id, vessel_id, storage_id, ws_no, date_shift, time_shift, regu," +
				"created_date, created_by, is_active, is_delete)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		SqlUpdate su = new SqlUpdate(dataSource, query);
		
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DATE));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getWsTypeId(), dto.getVesselId(), dto.getStorageId(), dto.getWsNo(),
				dto.getDateShift(), dto.getTimeShift(), dto.getRegu(),
				dto.getCreatedDate(), dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		
		return primaryKey;
	}

	@Override
	public void update(int id, FishWs dto) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET ws_type_id=?, vessel_id=?, storage_id=?, date_shift=?, time_shift=?, regu=?," +
				" updated_date=?, updated_by=?, is_active=?, is_delete=?" +
				" WHERE id=?";
		SqlUpdate su = new SqlUpdate(dataSource, query);
		
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.DATE));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getWsTypeId(), dto.getVesselId(),
				dto.getDateShift(), dto.getTimeShift(), dto.getRegu(),
				dto.getUpdatedDate(), dto.getUpdatedBy(), dto.getIsActive(), 
				dto.getIsDelete(), id});
	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET is_active='N', is_delete='Y' WHERE id=?";
		jdbcTemplate.update(query, id);
	}

	@Override
	public FishWs findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishWs> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishWs> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishWs> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public List<FishWs> findByVesselIdAndDateShift(int vesselId, Date dateShift) {
		String query = "SELECT * FROM " + getTableName() + 
				" WHERE vessel_id=? AND date_shift=?";
		
		List<FishWs> resultList = jdbcTemplate.query(query, this, vesselId, dateShift);
		return resultList;
	}
	
	@Override
	public List<FishWs> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishWs> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishWs> findAllAndPaging(int limit, int offset) {
		String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"	SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"	FROM "+getTableName()+" " +
				") AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishWs> resultList = jdbcTemplate.query(query, this, limit, offset);
		return resultList;
	}
	
	@Override
	public FishWs mapRow(ResultSet rs, int row) throws SQLException {
		FishWs dto = new FishWs();
		dto.setId(rs.getInt("id"));
		dto.setWsTypeId(rs.getInt("ws_type_id"));
		dto.setVesselId(rs.getInt("vessel_id"));
		dto.setStorageId(rs.getInt("storage_id"));
		dto.setWsNo(rs.getString("ws_no"));
		dto.setDateShift(rs.getDate("date_shift"));
		dto.setTimeShift(rs.getString("time_shift"));
		dto.setRegu(rs.getString("regu"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		try {
			FishWsTypeDao wsTypeDao = DaoFactory.createFishWsTypeDao();
			FishWSType wsType = wsTypeDao.findByPrimaryKey(dto.getWsTypeId());
			dto.setWsType(wsType);
			
			FishVesselDao vesselDao = DaoFactory.createFishVesselDao();
			FishVessel vessel = vesselDao.findByPrimaryKey(dto.getVesselId());
			dto.setVessel(vessel);
			
			FishStorageDao fishStorageDao = DaoFactory.createFishStorageDao();
			FishStorage storage = fishStorageDao.findByPrimaryKey(dto.getStorageId());
			dto.setStorage(storage);
		} catch(DaoException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public String getTableName() {
		return "inventory..fish_ws";
	}

	@Override
	public Boolean checkIsWsNoExist(String wsNo) {
		String query = "SELECT * FROM " + getTableName() + " WHERE ws_no=?";
		List<FishWs> resultList = jdbcTemplate.query(query, this, wsNo);
		
		return resultList.size() == 0 ? false : true;
	}

}
