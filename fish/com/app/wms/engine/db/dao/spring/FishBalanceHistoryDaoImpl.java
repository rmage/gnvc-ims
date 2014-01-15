package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishBalanceHistoryDao;
import com.app.wms.engine.db.dto.FishBalanceHistory;
import com.app.wms.engine.db.exceptions.DaoException;

public class FishBalanceHistoryDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishBalanceHistory>, FishBalanceHistoryDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishBalanceHistory dto) {
		String query = "INSERT INTO " + getTableName() + 
				" (doc_no, batch_no, fish_type, storage, qty_in, qty_out, balance," +
				" created_date, created_by, is_active, is_delete) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getDocNo(), dto.getBatchNo(), dto.getFishType(),
				dto.getStorage(), dto.getQtyIn(), dto.getQtyOut(), dto.getBalance(),
				dto.getCreatedDate(), dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishBalanceHistory dto) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET doc_no=?, batch_no=?, fish_type=?, storage=?, qty_in=?, qty_out=?," +
				" balance=?, updated_date=?, updated_by=?, is_active=?, is_delete=?" +
				" WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getDocNo(), dto.getBatchNo(), dto.getFishType(),
				dto.getStorage(), dto.getQtyIn(), dto.getQtyOut(), dto.getBalance(),
				dto.getUpdatedDate(), dto.getUpdatedBy(), dto.getIsActive(), dto.getIsDelete(), id});
	}

	@Override
	public void delete(int id) throws DaoException {
		String query  = "UPDATE " + getTableName() + 
				" SET is_active='N', is_delete='Y' WHERE id=?";
		
		jdbcTemplate.update(query, id);
	}

	@Override
	public FishBalanceHistory findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		
		List<FishBalanceHistory> resultList = jdbcTemplate.query(query, this, id);
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishBalanceHistory> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		
		List<FishBalanceHistory> resultList = jdbcTemplate.query(query, this);
		return resultList;
	}

	@Override
	public List<FishBalanceHistory> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		
		List<FishBalanceHistory> resultList = jdbcTemplate.query(query, this);
		return resultList;
	}

	@Override
	public FishBalanceHistory mapRow(ResultSet rs, int row) throws SQLException {
		
		FishBalanceHistory dto = new FishBalanceHistory();
		dto.setId(rs.getInt("id"));
		dto.setDocNo(rs.getString("doc_no"));
		dto.setBatchNo(rs.getString("batch_no"));
		dto.setFishType(rs.getString("fish_type"));
		dto.setStorage(rs.getString("storage"));
		dto.setQtyIn(rs.getDouble("qty_in"));
		dto.setQtyOut(rs.getDouble("qty_out"));
		dto.setBalance(rs.getDouble("balance"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		return dto;
	}
	
	public String getTableName() {
		return "inventory..fish_balance_history";
	}

}
