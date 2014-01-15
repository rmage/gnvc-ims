package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishSupplierDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dto.FishSupplier;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishVesselDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishVessel>, FishVesselDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishVessel dto) {
		String query = "INSERT INTO " + getTableName() + " (supplier_id, code, name, batch_no," +
				"created_date, created_by, is_active, is_delete) VALUES(?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getSupplierId(), dto.getCode(), dto.getName(), 
				dto.getBatchNo(), dto.getCreatedDate(), dto.getCreatedBy(),
				dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishVessel dto) throws DaoException {
		String query = "UPDATE " + getTableName() + " SET supplier_id=?, code=?, name=?, " +
				"batch_no=?, updated_date=?, updated_by=?, is_active=?, is_delete=? WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
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
		
		su.update(new Object[] {dto.getSupplierId(), dto.getCode(), dto.getName(),
				dto.getBatchNo(), dto.getUpdatedDate(), dto.getUpdatedBy(),
				dto.getIsActive(), dto.getIsDelete(), id});
	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET is_active='N', is_delete='Y' WHERE id=?";
		
		jdbcTemplate.update(query, id);
	}

	@Override
	public FishVessel findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishVessel> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishVessel> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishVessel> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishVessel> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishVessel> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishVessel> findByBatchNumber(String batchNumber) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE batch_no LIKE ?";
		List<FishVessel> resultList = jdbcTemplate.query(query, this, "%"+batchNumber+"%");
		
		return resultList;
	}

	@Override
	public List<FishVessel> findByVesselName(String vesselName) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE name LIKE ?";
		List<FishVessel> resultList = jdbcTemplate.query(query, this, "%"+vesselName+"%");
		
		return resultList;
	}

	@Override
	public FishVessel mapRow(ResultSet rs, int row) throws SQLException {
		FishVessel dto = new FishVessel();
		dto.setId(rs.getInt("id"));
		dto.setSupplierId(rs.getInt("supplier_id"));
		dto.setCode(rs.getString("code"));
		dto.setName(rs.getString("name"));
		dto.setBatchNo(rs.getString("batch_no"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		try{
			FishSupplierDao supplierDao = DaoFactory.createFishSupplierDao();
			FishSupplier supplier = supplierDao.findByPrimaryKey(dto.getSupplierId());
			dto.setSupplier(supplier);
		}catch (DaoException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public String getTableName() {
		return "inventory..fish_vessel";
	}

    public List<FishVessel> findAllAndPaging(int limit, int offset) {
        String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"	SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"	FROM "+getTableName()+" " +
                "   WHERE is_active = 'Y' " +
				") AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishVessel> resultList = jdbcTemplate.query(query, this, limit, offset);
		return resultList;
    }

    public List<FishVessel> searchAndPaging(String batchNo, int limit, int offset) {
        String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"	SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"	FROM "+getTableName()+" " +
                "   WHERE is_active = 'Y' AND batch_no LIKE ? " +
				") AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishVessel> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+batchNo+"%");
		return resultList;
    }

}
