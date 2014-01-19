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

import com.app.wms.engine.db.dao.FishTsDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dao.FishWdsDao;
import com.app.wms.engine.db.dto.FishTs;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.dto.FishWds;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishTsDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishTs>, FishTsDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishTs dto) {
		String query = "INSERT INTO " + getTableName() +
				" (wds_id, vessel_id, ts_no, ts_date, issued_by, noted_by," +
				" approved_by, received_by, created_date, created_by," +
				" is_active, is_delete) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DATE));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getWdsId(), dto.getVesselId(), dto.getTsNo(), dto.getTsDate(),
				dto.getIssuedBy(), dto.getNotedBy(), dto.getApprovedBy(), dto.getReceivedBy(),
				dto.getCreatedDate(), dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishTs dto) throws DaoException {
		String query = "UPDATE " + getTableName() +
				" SET wds_id=?, vessel_id=?, ts_no=?, ts_date=?, issued_by=?, noted_by=?," +
				" approved_by=?, received_by=?, updated_date=?, updated_by," +
				" is_active=?, is_delete=? WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DATE));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getWdsId(), dto.getVesselId(), dto.getTsNo(), dto.getTsDate(),
				dto.getIssuedBy(), dto.getNotedBy(), dto.getApprovedBy(), dto.getReceivedBy(),
				dto.getUpdatedDate(), dto.getUpdatedBy(), dto.getIsActive(), dto.getIsDelete(), id});
	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() +
				" SET is_active='N', is_delete='Y' WHERE id=?";
		
		jdbcTemplate.update(query, id);
	}

	@Override
	public FishTs findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishTs> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishTs> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishTs> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishTs> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishTs> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public List<FishTs> findAllAndPaging(int limit, int offset) {
		String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"	SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"	FROM "+getTableName()+" " +
				") AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishTs> resultList = jdbcTemplate.query(query, this, limit, offset);
		return resultList;
	}

	@Override
	public FishTs mapRow(ResultSet rs, int row) throws SQLException {
		FishTs dto = new FishTs();
		dto.setId(rs.getInt("id"));
		dto.setWdsId(rs.getInt("wds_id"));
		dto.setVesselId(rs.getInt("vessel_id"));
		dto.setTsNo(rs.getString("ts_no"));
		dto.setTsDate(rs.getDate("ts_date"));
		dto.setIssuedBy(rs.getString("issued_by"));
		dto.setNotedBy(rs.getString("noted_by"));
		dto.setApprovedBy(rs.getString("approved_by"));
		dto.setReceivedBy(rs.getString("received_by"));
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
		return "inventory..fish_ts";
	}

	@Override
	public Boolean checkIsTsNoExist(String tsNo) {
		String query = "SELECT * FROM inventory..fish_ts ts "
                + "WHERE ts.ts_no = ? "
                + "UNION ALL "
                + "SELECT 0, * FROM inventory..fish_bad_stock fbs "
                + "WHERE fbs.bs_no = ?";
        
		List<FishTs> resultList = jdbcTemplate.query(query, this, tsNo, tsNo);
		return resultList.size() == 0 ? false : true;
	}

	@Override
	public List<FishTs> searchAndPaging(String tsNo, Date tsDate, int limit, int offset) {
		String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"FROM inventory..fish_ts WHERE ts_no LIKE ? AND ts_date = ?) " +
				"AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishTs> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+tsNo+"%", tsDate);
		return resultList;
	}

    public List<FishTs> searchAndPagingWithoutDate(String tsNo, int limit, int offset) {
        String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"FROM inventory..fish_ts WHERE ts_no LIKE ?) " +
				"AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishTs> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+tsNo+"%");
		return resultList;
    }

}
