package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishWsTypeDao;
import com.app.wms.engine.db.dao.GeneralDao;
import com.app.wms.engine.db.dto.FishWSType;
import com.app.wms.engine.db.exceptions.DaoException;

public class FishWsTypeDaoImpl extends AbstractDAO 
	implements ParameterizedRowMapper<FishWSType>, FishWsTypeDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishWSType dto) {
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
	public void update(int id, FishWSType dto) throws DaoException {
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
	public FishWSType findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
		List<FishWSType> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishWSType> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishWSType> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishWSType> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishWSType> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public FishWSType mapRow(ResultSet rs, int row) throws SQLException {
		FishWSType dto = new FishWSType();
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
		return "inventory..fish_ws_type";
	}

    public List<FishWSType> findAllAndPaging(int limit, int offset) {
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
		
		List<FishWSType> resultList = jdbcTemplate.query(query, this, limit, offset);
		return resultList;
    }

    public List<FishWSType> searchAndPaging(String code, int limit, int offset) {
        String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ? " +
				"SELECT * FROM ( " +
				"	SELECT ROW_NUMBER() OVER (ORDER BY id DESC) AS RowNum, * " +
				"	FROM "+getTableName()+" " +
                "   WHERE is_active = 'Y' AND code LIKE ? " +
				") AS RowConstrainedResult " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishWSType> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+code+"%");
		return resultList;
    }

    public boolean checkWsTypeIsExist(String wsType) {
        String query = "SELECT * FROM " + getTableName() + 
                " WHERE code = ? AND is_active = 'Y'";
        
        List<FishWSType> resultList = jdbcTemplate.query(query, this, wsType);
        return resultList.isEmpty() ? false : true;
    }

    public FishWSType findByTypeCode(String typeCode) {
        String query = "SELECT * FROM " + getTableName() + " WHERE code = ?";
        List<FishWSType> resultList = jdbcTemplate.query(query, this, typeCode);
        
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
