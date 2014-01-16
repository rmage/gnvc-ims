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

import com.app.wms.engine.db.dao.FishBadStockDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dto.FishBadStock;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishBadStockDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishBadStock>, FishBadStockDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishBadStock dto) {
		String query = "INSERT INTO " + getTableName() +
				" (vessel_id, bs_no, bs_date, issued_by, noted_by," +
				" approved_by, received_by, created_date, created_by," +
				" is_active, is_delete) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
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
		
		su.update(new Object[] {dto.getVesselId(), dto.getBsNo(), dto.getBsDate(),
				dto.getIssuedBy(), dto.getNotedBy(), dto.getApprovedBy(), dto.getReceivedBy(),
				dto.getCreatedDate(), dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishBadStock dto) throws DaoException {
		String query = "UPDATE " + getTableName() +
				" SET vessel_id=?, bs_no=?, bs_date=?, issued_by=?, noted_by=?," +
				" approved_by=?, received_by=?, updated_date=?, updated_by," +
				" is_active=?, is_delete=? WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
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
		
		su.update(new Object[] {dto.getVesselId(), dto.getBsNo(), dto.getBsDate(),
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
	public FishBadStock findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishBadStock> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishBadStock> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishBadStock> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishBadStock> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishBadStock> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public List<FishBadStock> findAllAndPaging(int limit, int offset) {
		String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"	SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"	FROM "+getTableName()+" " +
				") AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishBadStock> resultList = jdbcTemplate.query(query, this, limit, offset);
		return resultList;
	}

	@Override
	public FishBadStock mapRow(ResultSet rs, int row) throws SQLException {
		FishBadStock dto = new FishBadStock();
		dto.setId(rs.getInt("id"));
		dto.setVesselId(rs.getInt("vessel_id"));
		dto.setBsNo(rs.getString("bs_no"));
		dto.setBsDate(rs.getDate("bs_date"));
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
		return "inventory..fish_bad_stock";
	}

	@Override
	public List<FishBadStock> searchAndPaging(String bsNo, Date bsDate,
			int limit, int offset) {
		
		String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"FROM inventory..fish_bad_stock WHERE bs_no LIKE ? AND bs_date = ?) " +
				"AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishBadStock> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+bsNo+"%", bsDate);
		return resultList;
	}

}
