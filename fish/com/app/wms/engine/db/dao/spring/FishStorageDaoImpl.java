package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.exceptions.DaoException;

public class FishStorageDaoImpl extends AbstractDAO 
	implements ParameterizedRowMapper<FishStorage>, FishStorageDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishStorage dto) {
		String query = "INSERT INTO " + getTableName() + 
				" (code, description," +
				" created_date, created_by, is_active, is_delete) VALUES (?,?,?,?,?,?)";
		
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
	public void update(int id, FishStorage dto) throws DaoException {
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
	public FishStorage findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
		List<FishStorage> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishStorage> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishStorage> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishStorage> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y' ORDER BY description";
		List<FishStorage> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public FishStorage mapRow(ResultSet rs, int row) throws SQLException {
		FishStorage dto = new FishStorage();
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
		return "fish_storage";
	}

    public List<FishStorage> findAllAndPaging(int limit, int offset) {
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
		
		List<FishStorage> resultList = jdbcTemplate.query(query, this, limit, offset);
		return resultList;
    }

    public List<FishStorage> searchAndPaging(String code, int limit, int offset) {
        String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"	SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"	FROM "+getTableName()+" " +
                "   WHERE is_active = 'Y' AND code LIKE ?" +
				") AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishStorage> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+code+"%");
		return resultList;
    }

    public boolean checkStorageCodeIsExist(String code) {
        String query = "SELECT * FROM " + getTableName() + 
                " WHERE code = ? AND is_active = 'Y'";
        
        List<FishStorage> resultList = jdbcTemplate.query(query, this, code);
        return resultList.isEmpty() ? false : true;
    }
}
