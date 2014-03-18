package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishTypeDao;
import com.app.wms.engine.db.dao.FishWeightTypeDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishType;
import com.app.wms.engine.db.dto.FishWeightType;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishDaoImpl extends AbstractDAO 
	implements ParameterizedRowMapper<Fish>, FishDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(Fish dto) {
		String query = "INSERT INTO " + getTableName() + 
				" (fish_type_id, fish_weight_type_id, code," +
				" created_date, created_by, is_active, is_delete) VALUES (?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getFishTypeId(), dto.getFishWeightTypeId(), dto.getCode(), 
				dto.getCreatedDate(), dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("select @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, Fish dto) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET fish_type_id=?, fish_weight_type_id=?, code=?," +
				" updated_date=?, updated_by=?, is_active=?, is_delete=?" +
				" WHERE id=?";
		SqlUpdate su = new SqlUpdate(dataSource, query);
		
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getFishTypeId(), dto.getFishWeightTypeId(), dto.getCode(), 
				dto.getUpdatedDate(), dto.getUpdatedBy(), dto.getIsActive(), dto.getIsDelete(), id});
	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() +
				" SET is_active='N', is_delete='Y' WHERE id=?";
		jdbcTemplate.update(query, id);
	}

	@Override
	public Fish findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
		List<Fish> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<Fish> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<Fish> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<Fish> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<Fish> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
    
    public List<Fish> searchAndPaging(String fishCode, int limit, int offset) {
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
		
		List<Fish> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+fishCode+"%");
		return resultList;
    }
    
    public List<Fish> findAllAndPaging(int limit, int offset) {
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
		
		List<Fish> resultList = jdbcTemplate.query(query, this, limit, offset);
		return resultList;
    }
	
	@Override
	public Fish mapRow(ResultSet rs, int row) throws SQLException {
		Fish dto = new Fish();
		dto.setId(rs.getInt("id"));
		dto.setCode(rs.getString("code"));
		dto.setFishTypeId(rs.getInt("fish_type_id"));
		dto.setFishWeightTypeId(rs.getInt("fish_weight_type_id"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		try {
			FishTypeDao fishTypeDao = DaoFactory.createFishTypeDao();
			FishType fishType = fishTypeDao.findByPrimaryKey(dto.getFishTypeId());
			dto.setFishType(fishType);
			
			FishWeightTypeDao weightTypeDao = DaoFactory.createFishWeightTypeDao();
			FishWeightType weightType = weightTypeDao.findByPrimaryKey(dto.getFishWeightTypeId());
			dto.setFishWeightType(weightType);	
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public String getTableName() {
		return "inventory..fish";
	}

    public boolean checkFishCodeIsExist(String code) {
        String query = "SELECT * FROM " + getTableName() + 
                " WHERE code = ? AND is_active = 'Y'";
        
        List<Fish> resultList = jdbcTemplate.query(query, this, code);
        return resultList.isEmpty() ? false : true;
    }
    
    public List<Fish> findFishByBatchNo(String batchNo) {
        String query = "select distinct f.id, f.code, 0 as fish_type_id, 0 as fish_weight_type_id, getdate() as created_date, ft.description as created_by, getdate() as updated_date, '' as updated_by, '' as is_active, '' as is_delete from inventory..fish_ws ws "
                + "inner join inventory..fish_vessel fv on fv.id = ws.vessel_id "
                + "inner join inventory..fish_ws_type fwt on fwt.id = ws.ws_type_id "
                + "inner join inventory..fish_ws_detail fwd on fwd.ws_id = ws.id "
                + "inner join inventory..fish f on f.id = fwd.fish_id "
                + "inner join inventory..fish_type ft on ft.id = f.fish_type_id "
                + "where fwt.code in ('WSHR','WSBR','WSL') and batch_no = ? ";
        
        List<Fish> resultList = jdbcTemplate.query(query, this, batchNo);
        return resultList;
    }
}
