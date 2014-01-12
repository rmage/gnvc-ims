package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishRrDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dto.FishRr;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishRrDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishRr>, FishRrDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishRr dto) {
		String query = "INSERT INTO " + getTableName() + 
				" (rr_no, ws_no, rr_date, vessel_id, received_by, approved_by, evaluated_by," +
				" created_date, created_by, is_active, is_delete) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DATE));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getRrNo(), dto.getWsNo(), dto.getRrDate(), dto.getVesselId(),
				dto.getReceivedBy(), dto.getApprovedBy(), dto.getEvaluatedBy(),
				dto.getCreatedDate(), dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishRr dto) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET rr_no=?, ws_no=?, rr_date=?, vessel_id=?, received_by=?, approved_by=?," +
				" evaluated_by=?, updated_date=?, updated_by=?, is_active=?, is_delete=?" +
				" WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DATE));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getRrNo(), dto.getRrNo(), dto.getRrDate(), dto.getVesselId(),
				dto.getReceivedBy(), dto.getApprovedBy(), dto.getEvaluatedBy(),
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
	public FishRr findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishRr> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishRr> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishRr> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishRr> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishRr> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public List<FishRr> findAllAndPaging(int limit, int offset) {
		String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"	SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"	FROM "+getTableName()+" " +
				") AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishRr> resultList = jdbcTemplate.query(query, this, limit, offset);
		return resultList;
	}

	@Override
	public FishRr mapRow(ResultSet rs, int row) throws SQLException {
		FishRr dto = new FishRr();
		dto.setId(rs.getInt("id"));
		dto.setRrNo(rs.getString("rr_no"));
		dto.setRrDate(rs.getDate("rr_date"));
		dto.setVesselId(rs.getInt("vessel_id"));
		dto.setReceivedBy(rs.getString("received_by"));
		dto.setApprovedBy(rs.getString("approved_by"));
		dto.setEvaluatedBy(rs.getString("evaluated_by"));
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
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public String getTableName() {
		return "inventory..fish_rr";
	}

	@Override
	public Boolean checkIsRrNoExist(String rrNo) {
		String query = "SELECT * FROM " + getTableName() + " WHERE rr_no=?";
		List<FishRr> resultList = jdbcTemplate.query(query, this, rrNo);
		
		return resultList.size() == 0 ? false : true;
	}
}
