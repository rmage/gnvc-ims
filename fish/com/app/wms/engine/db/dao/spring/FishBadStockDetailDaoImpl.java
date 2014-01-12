package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.app.wms.engine.db.dao.FishBadStockDao;
import com.app.wms.engine.db.dao.FishBadStockDetailDao;
import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishBadStock;
import com.app.wms.engine.db.dto.FishBadStockDetail;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishBadStockDetailDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishBadStockDetail>, FishBadStockDetailDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishBadStockDetail dto) {
		String query = "INSERT INTO " + getTableName() +
				" (bs_id, fish_id, storage_id, description, qty, uom_code," +
				" created_date, created_by, is_active, is_delete)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getBsId(), dto.getFishId(), dto.getStorageId(), 
				dto.getDescription(), dto.getQuantity(), dto.getUomCode(), 
				dto.getCreatedDate(), dto.getCreatedBy(),dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		return primaryKey;
	}

	@Override
	public void update(int id, FishBadStockDetail dto) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET bs_id=?, fish_id=?, storage_id=?, description=?, qty, uom_code=?," +
				" updated_date=?, updated_by=?, is_active=?, is_delete=?" +
				" WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.compile();
		
		su.update(new Object[] {dto.getBsId(), dto.getFishId(), dto.getStorageId(),
				dto.getDescription(), dto.getQuantity(), dto.getUomCode(), dto.getCreatedDate(), 
				dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete(), id});
	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET is_active='N', is_active='Y' WHERE id=?";
		
		jdbcTemplate.update(query, id);
	}

	@Override
	public FishBadStockDetail findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishBadStockDetail> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishBadStockDetail> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishBadStockDetail> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishBadStockDetail> findAllActive() throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE is_active='Y'";
		List<FishBadStockDetail> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}
	
	@Override
	public List<FishBadStockDetail> findAllByBsId(int bsId) {
		String query = "SELECT * FROM " + getTableName() + " WHERE bs_id=?";
		List<FishBadStockDetail> resultList = jdbcTemplate.query(query, this, bsId);
		
		return resultList;
	}


	@Override
	public FishBadStockDetail mapRow(ResultSet rs, int row) throws SQLException {
		FishBadStockDetail dto = new FishBadStockDetail();
		dto.setId(rs.getInt("id"));
		dto.setBsId(rs.getInt("bs_id"));
		dto.setFishId(rs.getInt("fish_id"));
		dto.setStorageId(rs.getInt("storage_id"));
		dto.setDescription(rs.getString("description"));
		dto.setQuantity(rs.getDouble("qty"));
		dto.setUomCode(rs.getString("uom_code"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		try {
			FishBadStockDao bsDao = DaoFactory.createFishBadStockDao();
			FishBadStock badStockSlip = bsDao.findByPrimaryKey(dto.getBsId());
			dto.setBadstockSlip(badStockSlip);
			
			FishDao fishDao = DaoFactory.createFishDao();
			Fish fish = fishDao.findByPrimaryKey(dto.getFishId());
			dto.setFish(fish);
			
			FishStorageDao storageDao = DaoFactory.createFishStorageDao();
			FishStorage storage = storageDao.findByPrimaryKey(dto.getStorageId());
			dto.setStorage(storage);
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public String getTableName() {
		return "inventory..fish_bad_stock_detail";
	}
}
