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

import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishSpoilageDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishSpoilage;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishSpoilageDaoImpl extends AbstractDAO implements
		ParameterizedRowMapper<FishSpoilage>, FishSpoilageDao {

	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(FishSpoilage dto) throws DaoException {
		String query = "INSERT INTO " + getTableName() + 
				" (catcher_no, date_shift, time_shift, vessel_id, fish_id," +
				" raw_weight, cooked_weight, total_processed," +
				" reason, created_date, created_by, is_active, is_delete)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DATE));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getCatcherNo(), dto.getDateShift(), dto.getTimeShift(),
				dto.getVesselId(), dto.getFishId(), dto.getRawWeight(), dto.getCookedWeight(),
				dto.getTotalProcessed(), dto.getReason(), dto.getCreatedDate(), 
				dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
		
		int primaryKey = jdbcTemplate.queryForInt("SELECT @@IDENTITY");
		
		return primaryKey;
	}

	@Override
	public void update(int id, FishSpoilage dto) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET catcher_no=?, date_shift=?, time_shift=?, vessel_id=?," +
				" fish_id=?, raw_weight=?, cooked_weight=?, total_processed=?," +
				" reason=?, updated_date=?, updated_by=?, is_active=?, is_delete=?" +
				" WHERE id=?";
		
		SqlUpdate su = new SqlUpdate(dataSource, query);
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.DATE));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
		su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
		su.compile();
		
		su.update(new Object[] {dto.getCatcherNo(), dto.getDateShift(), dto.getTimeShift(),
				dto.getVesselId(), dto.getFishId(), dto.getRawWeight(), dto.getCookedWeight(),
				dto.getTotalProcessed(), dto.getReason(), dto.getCreatedDate(), 
				dto.getCreatedBy(), dto.getIsActive(), dto.getIsDelete()});
	}

	@Override
	public void delete(int id) throws DaoException {
		String query = "UPDATE " + getTableName() + 
				" SET is_active='N', is_delete='Y' WHERE id=?";
		jdbcTemplate.update(query, id);
	}

	@Override
	public FishSpoilage findByPrimaryKey(int id) throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
		List<FishSpoilage> resultList = jdbcTemplate.query(query, this, id);
		
		return resultList.size() == 0 ? null : resultList.get(0);
	}

	@Override
	public List<FishSpoilage> findAll() throws DaoException {
		String query = "SELECT * FROM " + getTableName();
		List<FishSpoilage> resultList = jdbcTemplate.query(query, this);
		
		return resultList;
	}

	@Override
	public List<FishSpoilage> findByDateRange(Date dateFrom, Date dateTo)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FishSpoilage> findByBatchNumber(String batchNumber)
			throws DaoException {
		String query = "SELECT * FROM " + getTableName() + " WHERE batch_no=?";
		List<FishSpoilage> resultList = jdbcTemplate.query(query, this, batchNumber);
		
		return resultList;
	}
	
	@Override
	public List<FishSpoilage> findAllDistinct() {
		String query = "select MAX(sp.id) as id, MAX(sp.catcher_no) as catcher_no, " +
				"MAX(sp.fish_id) as fish_id, sp.date_shift, sp.time_shift, " +
				"sp.vessel_id, SUM(sp.cooked_weight) as cooked_weight, " +
				"SUM(sp.raw_weight) as raw_weight, SUM(sp.total_processed) as total_processed, " +
				"MAX(sp.reason) as reason, MAX(sp.created_date) as created_date, MAX(sp.created_by) as created_by, " +
				"NULL as updated_date, NULL as updated_by, NULL as is_active, NULL as is_delete " +
				"from inventory..fish_spoilage sp " +
				"GROUP BY sp.vessel_id, sp.date_shift, sp.time_shift";
		
		List<FishSpoilage> resultList = jdbcTemplate.query(query, this);
		return resultList;
	}
	
	@Override
	public List<FishSpoilage> findAllForReport(int vesselId, Date dateShift, String timeShift) {
		
		String query = "SELECT * FROM " + getTableName() + 
				" WHERE vessel_id=? AND date_shift=? AND time_shift=?";
		
		List<FishSpoilage> resultList = jdbcTemplate.query(query, this, vesselId, dateShift, timeShift);
		return resultList;
	}
	
	@Override
	public List<FishSpoilage> findAllDistinctAndPaging(int limit, int offset) {
		String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ?; " +
				"WITH Results_CTE AS " +
				"(" +
				"	select MAX(sp.id) as id, MAX(sp.catcher_no) as catcher_no, " +
				"	MAX(sp.fish_id) as fish_id, sp.date_shift, sp.time_shift, " +
				"	sp.vessel_id, SUM(sp.cooked_weight) as cooked_weight, " +
				"	SUM(sp.raw_weight) as raw_weight, SUM(sp.total_processed) as total_processed, " +
				"	MAX(sp.reason) as reason, MAX(sp.created_date) as created_date, MAX(sp.created_by) as created_by, " +
				"	NULL as updated_date, NULL as updated_by, NULL as is_active, NULL as is_delete, " +
				"	ROW_NUMBER() OVER (ORDER BY MAX(sp.id)) AS RowNum " +
				"	from inventory..fish_spoilage sp " +
				"	GROUP BY sp.vessel_id, sp.date_shift, sp.time_shift " +
				") " +
				"SELECT * FROM Results_CTE " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishSpoilage> resultList = jdbcTemplate.query(query, this, limit, offset);
		return resultList;
	}

	@Override
	public FishSpoilage mapRow(ResultSet rs, int row) throws SQLException {
		FishSpoilage dto = new FishSpoilage();
		dto.setId(rs.getInt("id"));
		dto.setCatcherNo(rs.getString("catcher_no"));
		dto.setDateShift(rs.getDate("date_shift"));
		dto.setTimeShift(rs.getString("time_shift"));
		dto.setVesselId(rs.getInt("vessel_id"));
		dto.setFishId(rs.getInt("fish_id"));
		dto.setRawWeight(rs.getDouble("raw_weight"));
		dto.setCookedWeight(rs.getDouble("cooked_weight"));
		dto.setTotalProcessed(rs.getDouble("total_processed"));
		dto.setReason(rs.getString("reason"));
		dto.setCreatedDate(rs.getDate("created_date"));
		dto.setCreatedBy(rs.getString("created_by"));
		dto.setUpdatedDate(rs.getDate("updated_date"));
		dto.setUpdatedBy(rs.getString("updated_by"));
		dto.setIsActive(rs.getString("is_active"));
		dto.setIsDelete(rs.getString("is_delete"));
		
		try{
			FishDao fishDao = DaoFactory.createFishDao();
			Fish fish = fishDao.findByPrimaryKey(dto.getFishId());
			dto.setFish(fish);
			
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
		return "inventory..fish_spoilage";
	}

	@Override
	public List<FishSpoilage> searchDistinctAndPaging(String batchNo, Date dateShift,
			int limit, int offset) {
		String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ?; " +
				"WITH Results_CTE AS " +
				"(" +
				"	select MAX(sp.id) as id, MAX(sp.catcher_no) as catcher_no, " +
				"	MAX(sp.fish_id) as fish_id, sp.date_shift, sp.time_shift, " +
				"	sp.vessel_id, SUM(sp.cooked_weight) as cooked_weight, " +
				"	SUM(sp.raw_weight) as raw_weight, SUM(sp.total_processed) as total_processed, " +
				"	MAX(sp.reason) as reason, MAX(sp.created_date) as created_date, MAX(sp.created_by) as created_by, " +
				"	NULL as updated_date, NULL as updated_by, NULL as is_active, NULL as is_delete, " +
				"	MAX(vs.batch_no) as batch_no, ROW_NUMBER() OVER (ORDER BY MAX(sp.id)) AS RowNum " +
				"	from inventory..fish_spoilage sp " +
                "   LEFT JOIN inventory..fish_vessel vs ON vs.id = sp.vessel_id " +
                "   WHERE vs.batch_no LIKE ? AND sp.date_shift = ? " +
				"	GROUP BY sp.vessel_id, sp.date_shift, sp.time_shift " +
				") " +
				"SELECT * FROM Results_CTE cte " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishSpoilage> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+batchNo+"%", dateShift);
		return resultList;
	}

    public List<FishSpoilage> searchDistinctAndPagingWithoutDate(String batchNo, int limit, int offset) {
        String query = "DECLARE @LIMIT int, @OFFSET int " +
				"SET @LIMIT = ? " +
				"SET @OFFSET = ?; " +
				"WITH Results_CTE AS " +
				"(" +
				"	select MAX(sp.id) as id, MAX(sp.catcher_no) as catcher_no, " +
				"	MAX(sp.fish_id) as fish_id, sp.date_shift, sp.time_shift, " +
				"	sp.vessel_id, SUM(sp.cooked_weight) as cooked_weight, " +
				"	SUM(sp.raw_weight) as raw_weight, SUM(sp.total_processed) as total_processed, " +
				"	MAX(sp.reason) as reason, MAX(sp.created_date) as created_date, MAX(sp.created_by) as created_by, " +
				"	NULL as updated_date, NULL as updated_by, NULL as is_active, NULL as is_delete, " +
				"	MAX(vs.batch_no) as batch_no, ROW_NUMBER() OVER (ORDER BY MAX(sp.id)) AS RowNum " +
				"	from inventory..fish_spoilage sp " +
                "   LEFT JOIN inventory..fish_vessel vs ON vs.id = sp.vessel_id " +
                "   WHERE vs.batch_no LIKE ? " +
				"	GROUP BY sp.vessel_id, sp.date_shift, sp.time_shift " +
				") " +
				"SELECT * FROM Results_CTE cte " +
				"WHERE RowNum >= @OFFSET AND RowNum < @OFFSET + @LIMIT " +
				"ORDER BY RowNum";
		
		List<FishSpoilage> resultList = jdbcTemplate.query(query, this, limit, offset, "%"+batchNo+"%");
		return resultList;
    }
}
