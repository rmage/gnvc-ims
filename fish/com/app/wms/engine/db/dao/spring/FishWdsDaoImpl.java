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

import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dao.FishWdsDao;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.dto.FishWds;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishWdsDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishWds>, FishWdsDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishWds dto) {
		String query = "INSERT INTO " + getTableName() +
				" (wds_no, wds_date, vessel_id, requested_by, approved_by," +
				" created_date, created_by, is_active, is_delete)" +
				" VALUES (?,?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DATE));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getWdsNo(), dto.getWdsDate(), dto.getVesselId(),
				dto.getRequestedBy(), dto.getApprovedBy(), dto.getCreatedDate(),
				dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishWds dto) throws DaoException {
		String query = "UPDATE " + getTableName() +
				" SET wds_no=?, wds_date=?, vessel_id=?, requested_by=?," +
				" approved_by=?, updated_date=?, updated_by=?, is_active=?, is_delete=?" +
				" WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DATE));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getWdsNo(), dto.getWdsDate(), dto.getVesselId(),
				dto.getRequestedBy(), dto.getApprovedBy(), dto.getUpdatedDate(),
				dto.getUpdatedBy(), dto.getIsActive(), dto.getIsDelete(), id});

	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET is_active='N', is_delete='Y' WHERE id=?";

		jdbcTemplate.update(query, id);
	}

	@Override
	public FishWds findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishWds> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishWds> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishWds> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishWds> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishWds> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public List<FishWds> findAllByWdsNo(String wdsNo) {
		String query = "SELECT * FROM " + getTableName() + " WHERE wds_no LIKE ?";
		List<FishWds> resultList = jdbcTemplate.query(query, this, "%"+wdsNo+"%");
		
		return resultList;
	}
	
	@Override
	public List<FishWds> findAllAndPaging(int limit, int offset) {
		String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"	SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"	FROM "+getTableName()+" " +
				") AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishWds> resultList = jdbcTemplate.query(query, this, limit, offset);
		return resultList;
	}

	@Override
	public FishWds mapRow(ResultSet rs, int row) throws SQLException {
		FishWds dto = new FishWds();
		dto.setId(rs.getInt("id"));
		dto.setWdsNo(rs.getString("wds_no"));
		dto.setWdsDate(rs.getDate("wds_date"));
		dto.setVesselId(rs.getInt("vessel_id"));
		dto.setRequestedBy(rs.getString("requested_by"));
		dto.setApprovedBy(rs.getString("approved_by"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("created_date"));
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
		return "inventory..fish_wds";
	}

	@Override
	public Boolean checkIsWdsNoExist(String wdsNo) {
		String query = "SELECT * FROM " + getTableName() + " WHERE wds_no=?";
		List<FishWds> resultList = jdbcTemplate.query(query, this, wdsNo);
		
		return resultList.size() == 0 ? false : true;
	}

	@Override
	public List<FishWds> searchAndPaging(String wdsNo, Date wdsDate, int limit, int offset) {
		String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"FROM inventory..fish_wds WHERE wds_no LIKE ? AND wds_date = ?) " +
				"AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishWds> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+wdsNo+"%", wdsDate);
		return resultList;
	}

    public List<FishWds> searchAndPagingWithoutDate(String wdsNo, int limit, int offset) {
        String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"FROM inventory..fish_wds WHERE wds_no LIKE ?) " +
				"AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishWds> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+wdsNo+"%");
		return resultList;
    }
}
