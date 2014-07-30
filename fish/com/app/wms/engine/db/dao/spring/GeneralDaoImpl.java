package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.GeneralDao;
import com.app.wms.engine.db.dto.FishWeightType;
import com.app.wms.engine.db.exceptions.DaoException;

public class GeneralDaoImpl extends AbstractDAO 
	implements ParameterizedRowMapper<FishWeightType>, GeneralDao<FishWeightType> {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishWeightType dto) {
		String query = "INSERT INTO " + getTableName() + 
				" (code, description," +
				" created_date, created_by, is_active, is_delete) VALUES (?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getCode(), dto.getDescription(), 
				dto.getCreatedDate(), dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("select @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishWeightType dto) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET code=?, description=?, " +
				" updated_date=?, updated_by=?, is_active=?, is_delete=?" +
				" WHERE id=?";
		SqlUpdate su = new SqlUpdate(dataSource, query);
		
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getCode(), dto.getDescription(),
				dto.getUpdatedDate(), dto.getUpdatedBy(), dto.getIsActive(), dto.getIsDelete(), id});
	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() +
				" SET is_active='N', is_delete='Y' WHERE id=?";
		jdbcTemplate.update(query, id);
	}

	@Override
	public FishWeightType findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
		List<FishWeightType> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishWeightType> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishWeightType> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishWeightType> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishWeightType> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public FishWeightType mapRow(ResultSet rs, int row) throws SQLException {
		FishWeightType dto = new FishWeightType();
		dto.setId(rs.getInt("id"));
		dto.setCode(rs.getString("code"));
		dto.setDescription(rs.getString("description"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		return dto;
	}
	
	public String getTableName() {
		return "fish_weight_type";
	}
}
