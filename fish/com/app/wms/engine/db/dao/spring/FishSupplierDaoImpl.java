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
import com.app.wms.engine.db.dto.FishSupplier;
import com.app.wms.engine.db.exceptions.DaoException;

public class FishSupplierDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishSupplier>, FishSupplierDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishSupplier dto) {
		String query = "INSERT INTO " + getTableName() + 
				" (code, name, address, telephone, fax, email," +
				"contact_person, created_date, created_by, is_active, is_delete)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getCode(), dto.getName(), dto.getAddress(), dto.getTelephone(),
				dto.getFax(), dto.getEmail(), dto.getContactPerson(), dto.getCreatedDate(),
				dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishSupplier dto) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET code=?, name=?, address=?, telephone=?, fax=?, email=?," +
				" contact_person=?, updated_date=?, updated_by=?, is_active=?, is_delete=?" +
				" WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
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
		
		su.update(new Object[] {dto.getCode(), dto.getName(), dto.getAddress(), dto.getTelephone(),
				dto.getFax(), dto.getEmail(), dto.getContactPerson(), dto.getUpdatedDate(),
				dto.getUpdatedBy(), dto.getIsActive(), dto.getIsDelete(), id});
	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET is_active='N', is_delete='Y' WHERE id=?";
		jdbcTemplate.update(query, id);
	}

	@Override
	public FishSupplier findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishSupplier> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishSupplier> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishSupplier> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishSupplier> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishSupplier> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishSupplier> findBySupplierName(String supplierName) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE name LIKE ?";
		List<FishSupplier> resultList = jdbcTemplate.query(query, this, "%"+supplierName+"%");
		
		return resultList;
	}

	@Override
	public FishSupplier mapRow(ResultSet rs, int row) throws SQLException {
		FishSupplier dto = new FishSupplier();
		dto.setId(rs.getInt("id"));
		dto.setCode(rs.getString("code"));
		dto.setName(rs.getString("name"));
		dto.setAddress(rs.getString("address"));
		dto.setTelephone(rs.getString("telephone"));
		dto.setFax(rs.getString("fax"));
		dto.setEmail(rs.getString("email"));
		dto.setContactPerson(rs.getString("contact_person"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		return dto;
	}
	
	public String getTableName() {
		return "inventory..fish_supplier";
	}

}
