package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.UserPk;
import com.app.wms.engine.db.dto.map.CorporateListMap;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.dto.map.LoginUserMap;
import com.app.wms.engine.db.dto.map.UserListMap;
import com.app.wms.engine.db.exceptions.CorpDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.util.DBUtil;
import com.app.web.engine.search.UserSearch;
import com.app.wms.web.util.EncryptionUtility;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoImpl extends AbstractDAO implements ParameterizedRowMapper<User>, UserDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    /**
     * Method 'setDataSource'
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return UserPk
     */
    @Transactional
    public UserPk insert(User dto) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " (  CODE, NAME, USERNAME, PASSWORD, "
                + "ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, WH_CODE, CORP_ID ) "
                + "VALUES (  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )", dto.getCode(), dto.getName(), dto.getUsername(), EncryptionUtility.getEncrypted(dto.getPassword()), dto.getRoleCode(), dto.getEmail(), dto.getIsActive(),
                dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), dto.getWhCode(), dto.getCorpId());
        return dto.createPk();
    }

    /**
     * Updates a single row in the USER table.
     */
    @Transactional
    public void update(UserPk pk, User dto) throws UserDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET CODE = ?, NAME = ?, USERNAME = ?, PASSWORD = ?, "
                + "ROLE_CODE = ?, EMAIL = ?, IS_ACTIVE = ?, CREATED_BY = ?, CREATED_DATE = ?, UPDATED_BY = ?, UPDATED_DATE = ?,  WH_CODE= ?, CORP_ID = ? "
                + "WHERE USER_ID = ?", dto.getCode(), dto.getName(), dto.getUsername(), EncryptionUtility.getEncrypted(dto.getPassword()),
                dto.getRoleCode(), dto.getEmail(), dto.getIsActive(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(),
                dto.getWhCode(), dto.getCorpId(), pk.getUserId());
    }

    @Transactional
    public void updateInactivate(UserPk pk, User dto) throws UserDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET CODE = ?, NAME = ?, USERNAME = ?, PASSWORD = ?, "
                + "ROLE_CODE = ?, EMAIL = ?, IS_ACTIVE = ?, CREATED_BY = ?, CREATED_DATE = ?, UPDATED_BY = ?, UPDATED_DATE = ?,  WH_CODE= ?, CORP_ID = ? "
                + "WHERE USER_ID = ?", dto.getCode(), dto.getName(), dto.getUsername(), dto.getPassword(),
                dto.getRoleCode(), dto.getEmail(), dto.getIsActive(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(),
                dto.getWhCode(), dto.getCorpId(), pk.getUserId());
    }

    /**
     * Deletes a single row in the USER table.
     */
    @Transactional
    public void delete(UserPk pk) throws UserDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE USER_ID = ?", pk.getUserId());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return User
     */
    public User mapRow(ResultSet rs, int row) throws SQLException {
        User dto = new User();
        dto.setUserId(rs.getString(1));
        dto.setCode(rs.getString(2));
        dto.setName(rs.getString(3));
        dto.setUsername(rs.getString(4));
        dto.setPassword(rs.getString(5));
        dto.setRoleCode(rs.getString(6));
        dto.setEmail(rs.getString(7));
        dto.setIsActive(rs.getString(8));
        dto.setCreatedBy(rs.getString(9));
        dto.setCreatedDate(rs.getTimestamp(10));
        dto.setUpdatedBy(rs.getString(11));
        dto.setUpdatedDate(rs.getTimestamp(12));
        dto.setWhCode(rs.getString(13));
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "dbo.[user]";
    }

    /**
     * Returns all rows from the USER table that match the criteria 'USER_ID =
     * :userId'.
     */
    @Transactional
    public User findByPrimaryKey(String userId) throws UserDaoException {
        try {
            List<User> list = jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, "
                    + "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, WH_CODE, CORP_ID FROM " + getTableName() + " WHERE USER_ID = ?", this, userId);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria ''.
     */
    @Transactional
    public List<User> findAll() throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, "
                    + "CREATED_DATE, UPDATED_BY, UPDATED_DATE, CORP_ID  FROM " + getTableName() + " ORDER BY USER_ID", this);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    @Transactional
    public List<User> findAll(String isWIC, String isDSA) throws UserDaoException {
        try {
            if (isWIC != null && isDSA != null) {
                return jdbcTemplate.query("SELECT "
                        + "d.USER_ID, d.CODE, d.NAME, d.USERNAME, d.PASSWORD, d.ROLE_CODE, d.EMAIL, d.IS_ACTIVE"
                        + ", d.CREATED_BY, d.CREATED_DATE, d.UPDATED_BY, d.UPDATED_DATE FROM "
                        + getTableName() + " d inner join USER_ROLE r on d.ROLE_CODE=r.ROLE_CODE "
                        + " AND r.IS_WIC = ? and r.IS_DSA = ? ORDER BY USER_ID", this, isWIC, isDSA);
            }
            if (isWIC != null) {
                return jdbcTemplate.query("SELECT "
                        + "d.USER_ID, d.CODE, d.NAME, d.USERNAME, d.PASSWORD, d.ROLE_CODE, d.EMAIL, d.IS_ACTIVE"
                        + ", d.CREATED_BY, d.CREATED_DATE, d.UPDATED_BY, d.UPDATED_DATE FROM "
                        + getTableName() + " d inner join USER_ROLE r on d.ROLE_CODE=r.ROLE_CODE "
                        + " AND r.IS_WIC = ?  ORDER BY USER_ID", this, isWIC);
            }
            if (isDSA != null) {
                return jdbcTemplate.query("SELECT "
                        + "d.USER_ID, d.CODE, d.NAME, d.USERNAME, d.PASSWORD, d.ROLE_CODE, d.EMAIL, d.IS_ACTIVE"
                        + ", d.CREATED_BY, d.CREATED_DATE, d.UPDATED_BY, d.UPDATED_DATE FROM "
                        + getTableName() + " d inner join USER_ROLE r on d.ROLE_CODE=r.ROLE_CODE "
                        + " AND  r.IS_DSA = ? ORDER BY USER_ID", this, isDSA);
            }
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " ORDER BY USER_ID", this);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria ''.
     */
    @Transactional
    public List<User> findNextLevel(String sourceCode) throws UserDaoException {
        try {
//	    return jdbcTemplate.query("SELECT u.USER_ID, u.CODE, u.NAME, u.USERNAME, u.PASSWORD, u.ROLE_CODE, u.EMAIL, u.IS_ACTIVE, u.CREATED_BY, u.CREATED_DATE, u.UPDATED_BY, u.UPDATED_DATE FROM " + getTableName() + " u INNER JOIN REQ_DATA_SOURCE rds ON rds.SOURCE_CODE=u.ROLE_CODE where rds.\"LEVEL\" = ? ", this, level.add(new BigDecimal(1)));
            return jdbcTemplate.query("SELECT u.USER_ID, u.CODE, u.NAME, u.USERNAME, u.PASSWORD, u.ROLE_CODE, u.EMAIL, u.IS_ACTIVE, u.CREATED_BY, u.CREATED_DATE, u.UPDATED_BY, u.UPDATED_DATE FROM " + getTableName() + " u INNER JOIN REQ_DATA_SOURCE rds ON rds.SOURCE_CODE=u.ROLE_CODE where u.ROLE_CODE = ? ", this, sourceCode);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria 'ROLE_CODE =
     * :roleCode'.
     */
    @Transactional
    public List<User> findByUserRole(String roleCode) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE ROLE_CODE = ?", this, roleCode);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }
//    
//    dto.setUserId(rs.getString(1));
//        dto.setCode(rs.getString(2));
//        dto.setName(rs.getString(3));
//        dto.setUsername(rs.getString(4));
//        dto.setPassword(rs.getString(5));
//        dto.setRoleCode(rs.getString(6));
//        dto.setEmail(rs.getString(7));
//        dto.setIsActive(rs.getString(8));
//        dto.setCreatedBy(rs.getString(9));
//        dto.setCreatedDate(rs.getTimestamp(10));
//        dto.setUpdatedBy(rs.getString(11));
//        dto.setUpdatedDate(rs.getTimestamp(12));
//        dto.setCorpId(rs.getString(13));

    @Transactional
    public List<User> findByUserName(String userName) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, "
                    + "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, WH_CODE, CORP_ID FROM " + getTableName() + " WHERE USERNAME = ?", this, userName);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria 'USER_ID =
     * :userId'.
     */
    @Transactional
    public List<User> findWhereUserIdEquals(BigDecimal userId) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE USER_ID = ? ORDER BY USER_ID", this, userId);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria 'CODE =
     * :code'.
     */
    @Transactional
    public List<User> findWhereCodeEquals(String code) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, "
                    + "CREATED_DATE, UPDATED_BY, UPDATED_DATE, CORP_ID FROM " + getTableName() + " WHERE CODE = ? ORDER BY CODE", this, code);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria 'NAME =
     * :name'.
     */
    @Transactional
    public List<User> findWhereNameEquals(String name) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria 'USERNAME =
     * :username'.
     */
    @Transactional
    public List<User> findWhereUsernameEquals(String username) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE USERNAME = ? ORDER BY USERNAME", this, username);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    @Transactional
    public LoginUser findWhereUsernamePasswordEquals(String username, String password) throws UserDaoException {

        LoginUser u = null;

        try {
            List<LoginUser> userList = jdbcTemplate.query(""
                    + "select "
                    + "u.user_id as user_id, "
                    + "u.username as username, u.password as password, "
                    + "u.role_code as role_code, "
                    + "ub.corp_id as corp_id, "
                    + "uc.wh_code as wh_code, "
                    + "ur.name as role_name, ur.role_level as role_level "
                    + "from dbo.[user] u, dbo.[user_role] ur, dbo.[corp_user] ub, dbo.[corp] uc "
                    + "where u.role_code = ur.role_code and ub.user_id = u.user_id and ub.corp_id = uc.corp_id "
                    + "and u.is_active = 'Y' and u.username = ? and u.password=?", new LoginUserMap(), username, EncryptionUtility.getEncrypted(password));

            if (userList != null) {
                if (userList.size() == 1) {
                    u = userList.get(0);
                } else if (userList.size() > 1) {
                    u = userList.get(0);
                    u.setCorpId("CORP001");
                }
            }

        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
        return u;
    }

    /**
     * Returns all rows from the USER table that match the criteria 'PASSWORD =
     * :password'.
     */
    @Transactional
    public List<User> findWherePasswordEquals(String password) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE PASSWORD = ? ORDER BY PASSWORD", this, password);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria 'ROLE_CODE =
     * :roleCode'.
     */
    @Transactional
    public List<User> findWhereRoleCodeEquals(String roleCode) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE ROLE_CODE = ? ORDER BY ROLE_CODE", this, roleCode);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria 'EMAIL =
     * :email'.
     */
    @Transactional
    public List<User> findWhereEmailEquals(String email) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE EMAIL = ? ORDER BY EMAIL", this, email);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria 'IS_ACTIVE =
     * :isActive'.
     */
    @Transactional
    public List<User> findWhereIsActiveEquals(String isActive) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE IS_ACTIVE = ? ORDER BY IS_ACTIVE", this, isActive);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria 'CREATED_BY
     * = :createdBy'.
     */
    @Transactional
    public List<User> findWhereCreatedByEquals(BigDecimal createdBy) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE CREATED_BY = ? ORDER BY CREATED_BY", this, createdBy);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria
     * 'CREATED_DATE = :createdDate'.
     */
    @Transactional
    public List<User> findWhereCreatedDateEquals(Date createdDate) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE CREATED_DATE = ? ORDER BY CREATED_DATE", this, createdDate);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria 'UPDATED_BY
     * = :updatedBy'.
     */
    @Transactional
    public List<User> findWhereUpdatedByEquals(BigDecimal updatedBy) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE UPDATED_BY = ? ORDER BY UPDATED_BY", this, updatedBy);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the USER table that match the criteria
     * 'UPDATED_DATE = :updatedDate'.
     */
    @Transactional
    public List<User> findWhereUpdatedDateEquals(Date updatedDate) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE UPDATED_DATE = ? ORDER BY UPDATED_DATE", this, updatedDate);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the USER table that matches the specified
     * primary-key value.
     */
    @Transactional
    public User findByPrimaryKey(UserPk pk) throws UserDaoException {
        return findByPrimaryKey(pk.getUserId());
    }

    @Transactional
    public List<User> findLimit(int page, int paging) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName(), this);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    @Transactional
    public List<User> findByCriteriaLimit(UserSearch criteria, int start, int end) throws UserDaoException {

        if (criteria == null) {

            System.out.println("Search User with no criteria");
            criteria = new UserSearch();
        }
        try {

            criteria.setTableAlias("D");

            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");

            String strQuery = "SELECT D.USER_ID, D.CODE, D.NAME, D.USERNAME,"
                    + "D.PASSWORD, D.ROLE_CODE, D.EMAIL, D.IS_ACTIVE, D.CREATED_BY,"
                    + "D.CREATED_DATE, D.UPDATED_BY, D.UPDATED_DATE, D.CORP_ID "
                    + "FROM dbo.[user] D "
                    + "INNER JOIN USER_ROLE R ON D.ROLE_CODE=R.ROLE_CODE ";

            System.out.println("strQuery=" + strQuery);
            return jdbcTemplate.query(strQuery, this, param);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    @Transactional
    public BigDecimal getNextUserId() throws UserDaoException {
        try {
            int result = jdbcTemplate.queryForInt("SELECT SQ_USER.NEXTVAL from DUAL");
            return new BigDecimal(result);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    @Transactional
    public List<User> findByBg(String bgCode) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT "
                    + "u.USER_ID, u.CODE, u.NAME, u.USERNAME, u.PASSWORD, u.ROLE_CODE, u.EMAIL, u.IS_ACTIVE, u.CREATED_BY, u.CREATED_DATE, u.UPDATED_BY, u.UPDATED_DATE "
                    + "FROM " + getTableName()
                    + " u INNER JOIN USER_BG ubg on u.USER_ID=ubg.USER_ID"
                    + " WHERE ubg.BG_CODE = ?", this, bgCode);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    @Transactional
    public List<User> findWhereNotInUserBg(String bgCode, String filter) throws UserDaoException {
        try {
            StringBuffer strQuery = new StringBuffer();
            strQuery.append("SELECT \"USER\".* FROM " + getTableName() + " ");
            strQuery.append("WHERE NOT EXISTS(SELECT USER_ID FROM USER_BG WHERE USER_BG.USER_ID = \"USER\".USER_ID AND USER_BG.BG_CODE = ?)");
            if (filter != null && !filter.trim().equals("")) {
                strQuery.append(" AND ");
                strQuery.append(filter);
            }
            strQuery.append("ORDER BY \"USER\".NAME ");
            //return jdbcTemplate.query(DBUtil.createPagingSql(strQuery.toString(), 1, 10), this, bgCode);
            return jdbcTemplate.query(strQuery.toString(), this, bgCode);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    @Transactional
    public List<User> findWhereNotInUserBg(String bgCode, String filter, String isWIC, String isDSA) throws UserDaoException {
        HashMap param = new HashMap();
        param.put("bgcode", bgCode);
        StringBuffer strQuery = new StringBuffer();

        if (isWIC != null && isDSA != null) {
            strQuery.append("SELECT D.* FROM " + getTableName()
                    + " D INNER JOIN USER_ROLE R ON D.ROLE_CODE=R.ROLE_CODE WHERE R.IS_WIC = :iswic AND R.IS_DSA = :isdsa "
                    + " AND NOT EXISTS(SELECT USER_ID FROM USER_BG WHERE USER_BG.USER_ID = D.USER_ID AND USER_BG.BG_CODE = :bgcode)");
            param.put("iswic", isWIC);
            param.put("isdsa", isDSA);
        } else if (isWIC != null) {
            strQuery.append("SELECT D.* FROM " + getTableName()
                    + " D INNER JOIN USER_ROLE R ON D.ROLE_CODE=R.ROLE_CODE WHERE R.IS_WIC = :iswic "
                    + " AND NOT EXISTS(SELECT USER_ID FROM USER_BG WHERE USER_BG.USER_ID = D.USER_ID AND USER_BG.BG_CODE = :bgcode)");
            param.put("iswic", isWIC);

        } else if (isDSA != null) {
            strQuery.append("SELECT D.* FROM " + getTableName()
                    + " D INNER JOIN USER_ROLE R ON D.ROLE_CODE=R.ROLE_CODE WHERE R.IS_DSA = :isdsa "
                    + " AND NOT EXISTS(SELECT USER_ID FROM USER_BG WHERE USER_BG.USER_ID = D.USER_ID AND USER_BG.BG_CODE = :bgcode)");
            param.put("isdsa", isDSA);
        } else {
            strQuery.append("SELECT D.* FROM " + getTableName() + " D ");
            strQuery.append("WHERE NOT EXISTS(SELECT USER_ID FROM USER_BG WHERE USER_BG.USER_ID = D.USER_ID AND USER_BG.BG_CODE = :bgcode)");

        }

        try {

            if (filter != null && !filter.trim().equals("")) {
                strQuery.append(" AND ");
                strQuery.append(filter);
            }

            strQuery.append(" ORDER BY D.NAME ");
            System.out.println("query: " + strQuery);
            //return jdbcTemplate.query(DBUtil.createPagingSql(strQuery.toString(), 1, 10), this, bgCode);
            return jdbcTemplate.query(strQuery.toString(), this, param);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    @Transactional
    public List<User> findByBgWhereRoleScm(String bgCode) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT "
                    + "u.USER_ID, u.CODE, u.NAME, u.USERNAME, u.PASSWORD, u.ROLE_CODE, u.EMAIL, u.IS_ACTIVE, u.CREATED_BY, u.CREATED_DATE, u.UPDATED_BY, u.UPDATED_DATE "
                    + "FROM " + getTableName()
                    + " u INNER JOIN USER_BG ubg on u.USER_ID=ubg.USER_ID"
                    + " WHERE ubg.BG_CODE = ? AND u.ROLE_CODE='SCM' ", this, bgCode);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    @Transactional
    public List<User> findOtherUserInSameBg(String bgCode, BigDecimal userID) throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT "
                    + "u.USER_ID, u.CODE, u.NAME, u.USERNAME, u.PASSWORD, u.ROLE_CODE, u.EMAIL, u.IS_ACTIVE, "
                    + "u.CREATED_BY, u.CREATED_DATE, u.UPDATED_BY, u.UPDATED_DATE "
                    + "FROM " + getTableName()
                    + " u INNER JOIN USER_BG ubg on u.USER_ID = ubg.USER_ID"
                    + " WHERE ubg.BG_CODE = ? AND u.USER_ID != ?", this, bgCode, userID);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    @Transactional
    public List<User> findBoss(String bgCode, int bossLevel) throws UserDaoException {
        // cari seseorang yang ada di bg tertentu dan level nya adalah yg disebut
        try {
            return jdbcTemplate.query("SELECT "
                    + "u.USER_ID, u.CODE, u.NAME, u.USERNAME, u.PASSWORD, u.ROLE_CODE"
                    + ", u.EMAIL, u.IS_ACTIVE, u.CREATED_BY, u.CREATED_DATE, u.UPDATED_BY, u.UPDATED_DATE "
                    + "FROM " + getTableName()
                    + " u INNER JOIN USER_BG ubg on u.USER_ID=ubg.USER_ID INNER JOIN REQ_DATA_SOURCE r ON u.ROLE_CODE=r.SOURCE_CODE"
                    + " WHERE ubg.BG_CODE = ? AND R.\"LEVEL\" = ? ", this, bgCode, bossLevel);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }

    }

    @Transactional
    public List<User> findUserInSvcPic(String bgCode, int page, int paging) throws UserDaoException {
        try {
            String strQuery = "SELECT U.* FROM " + getTableName() + " U WHERE EXISTS (SELECT S.SVC_PIC_ID FROM SVC_PIC S WHERE U.USER_ID = S.SVC_PIC_ID AND S.BG_CODE =? ) ";
            return jdbcTemplate.query(DBUtil.createPagingSql(strQuery + " ORDER BY U.CODE", page, paging), this, bgCode);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    @Transactional
    public List<User> findUserNotInSvcPic(UserSearch criteria, String bgCode, int page, int paging) throws UserDaoException {
        if (criteria == null) {
            criteria = new UserSearch();
        }
        try {
            criteria.setTableAlias("U");
            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
            param.put("ppbgCode", bgCode);
            String search = (String) ret.get("search");

            String strQuery = "SELECT U.* FROM " + getTableName() + " U INNER JOIN USER_BG UB ON UB.USER_ID = U.USER_ID WHERE NOT EXISTS (SELECT S.SVC_PIC_ID FROM SVC_PIC S WHERE U.USER_ID = S.SVC_PIC_ID AND S.BG_CODE =:ppbgCode )";
            strQuery += " AND UB.BG_CODE = :ppbgCode AND " + search;
            strQuery += " ORDER BY U.CODE ";
            return jdbcTemplate.query(DBUtil.createPagingSql(strQuery, page, paging), this, param);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    @Transactional
    public Integer countUserInSvcPic(String bgCode) throws UserDaoException {
        try {
            String strQuery = "SELECT COUNT(*) FROM " + getTableName() + " U WHERE EXISTS (SELECT S.SVC_PIC_ID FROM SVC_PIC S WHERE U.USER_ID = S.SVC_PIC_ID AND S.BG_CODE =? )";
            return jdbcTemplate.queryForInt(strQuery, bgCode);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    @Transactional
    public Integer countUserNotInSvcPic(UserSearch criteria, String bgCode) throws UserDaoException {
        if (criteria == null) {
            criteria = new UserSearch();
        }
        try {
            criteria.setTableAlias("U");
            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
            param.put("ppbgCode", bgCode);
            String search = (String) ret.get("search");

            String strQuery = "SELECT COUNT(*) FROM " + getTableName() + " U INNER JOIN USER_BG UB ON UB.USER_ID = U.USER_ID WHERE NOT EXISTS (SELECT S.SVC_PIC_ID FROM SVC_PIC S WHERE U.USER_ID = S.SVC_PIC_ID AND S.BG_CODE =:ppbgCode )";
            strQuery += " AND UB.BG_CODE = :ppbgCode AND " + search;
            return jdbcTemplate.queryForInt(strQuery, param);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    @Transactional
    public LoginUser findWhereLogin(String username, String password, String wh, String corp, String loginas) throws UserDaoException {
        System.out.println("username =" + username);
        System.out.println("password =" + password);
        System.out.println("wh =" + wh);
        System.out.println("corp =" + corp);
        System.out.println("loginas =" + loginas);
        LoginUser u = null;

        try {

            if (loginas.equalsIgnoreCase("system")) {
                String query = ""
                        + "select "
                        + "u.user_id as user_id, "
                        + "u.username as username, u.password as password, "
                        + "u.role_code as role_code, null as corp_id, null as wh_code, null as role_name, null as role_level "
                        + "from dbo.[user] u "
                        + "where  u.is_active = 'Y' and u.username = ? and u.password=?";

                List<LoginUser> userList = jdbcTemplate.query(query, new LoginUserMap(), username, EncryptionUtility.getEncrypted(password));
                if (userList != null) {
                    if (userList.size() == 1) {
                        u = userList.get(0);
                    }
                }
                return u;
            }

            String query = ""
                    + "select "
                    + "u.user_id as user_id, "
                    + "u.username as username, u.password as password, "
                    + "u.role_code as role_code, "
                    + "u.corp_id as corp_id, "
                    + "u.wh_code as wh_code, "
                    + "ur.name as role_name, ur.role_level as role_level "
                    + "from dbo.[user] u, dbo.[user_role] ur, dbo.[corp] uc "
                    + "where u.role_code = ur.role_code "
                    + "and u.is_active = 'Y' and u.username = ? and u.password=? "
                    + "and u.wh_code=? and u.corp_id=? and uc.corp_id=?";

            System.out.println(query);
            System.err.println(query);

            List<LoginUser> userList = jdbcTemplate.query(query, new LoginUserMap(), username, EncryptionUtility.getEncrypted(password), wh, corp, corp);

            if (userList != null) {
                if (userList.size() == 1) {
                    u = userList.get(0);
                } else if (userList.size() > 1) {
                    u = userList.get(0);
//                    u.setCorpId("CY-0001");
                }
            }

        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
        return u;
    }

    public LoginUser findWhereLoginIMS(String username, String password) throws UserDaoException {
        LoginUser u = null;
        try {
            String query = ""
                    + "select "
                    + "u.user_id as user_id, "
                    + "u.username as username, "
                    + "u.password as password, "
                    + "ur.role_code as role_code, "
                    + "ur.role_name as role_name, "
                    + "ur.role_level as role_level, "
                    + "ur.department_code as department_code "
                    + "from dbo.[user] u, dbo.[user_role] ur "
                    + "where u.role_code = ur.role_code "
                    + "and u.is_active = 'Y' and u.username = ? and u.password=? ";

            System.out.println(query);
            System.err.println(query);

            List<LoginUser> userList = jdbcTemplate.query(query, new LoginUserMap(), username, EncryptionUtility.getEncrypted(password));

            if (userList != null) {
                if (userList.size() == 1) {
                    u = userList.get(0);
                } else if (userList.size() > 1) {
                    u = userList.get(0);
                }
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public List<User> findUserPaging(UserSearch c, int page) throws UserDaoException {
        try {
            StringBuffer sb = new StringBuffer();
            int i = page;
            Map map = new HashMap();
            map.put("i", i);

            if (c == null) {
                c = new UserSearch();
            }

            String userId = c.getCode();
            String name = c.getName();

            if (userId == null || name == null) {
                userId = "%";
                name = "%";

                sb.append("declare @Page int, @PageSize int "
                        + "set @Page = '" + i + "'; "
                        + "set @PageSize = 10; "
                        + "with PagedResult "
                        + "as (select ROW_NUMBER() over (order by user_id desc) as id, user_id, code, name, username, password, role_code, wh_code, corp_id, is_active from dbo.[user] where user_id like '%" + userId + "%' and name like '%" + name + "%'"
                        + "and role_code not in ('SYSADMIN') and is_active = 'Y') "
                        + "select * from PagedResult where id between "
                        + "case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                        + "else @Page end and @PageSize * @Page");

            } else {
                sb.append("declare @Page int, @PageSize int "
                        + "set @Page = '" + i + "'; "
                        + "set @PageSize = 10; "
                        + "with PagedResult "
                        + "as (select ROW_NUMBER() over (order by user_id desc) as id, user_id, code, name, username, password, role_code, wh_code, corp_id, is_active from dbo.[user] where user_id like '%" + userId + "%' and name like '%" + name + "%'"
                        + "and role_code not in ('SYS-ADMIN')) and is_active = 'Y' "
                        + "select * from PagedResult where id between "
                        + "case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                        + "else @Page end and @PageSize * @Page");

            }

            return jdbcTemplate.query(sb.toString(), new UserListMap(), map);

        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    public List<User> findRoleCanvasser() throws UserDaoException {
        try {
            return jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, "
                    + "CREATED_DATE, UPDATED_BY, UPDATED_DATE, CORP_ID  FROM " + getTableName() + " WHERE ROLE_CODE = 'PRC.ST' ORDER BY USER_ID", this);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    public List<User> findUserRoleAppRange() throws UserDaoException {
        try {
            return jdbcTemplate.query(" select user_id, code, name, username, password, role_code, email, is_active, created_by, created_date, updated_by, updated_date, corp_id "
                    + " from " + getTableName() + " where role_code not in (select role_code from user_role where role_code='SYS-ADMIN') ", this);
        } catch (Exception e) {
            throw new UserDaoException("Query failed", e);
        }
    }

    //Modified 21 April 2014
    public int ajaxMaxPage(String where, BigDecimal show) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE is_active='Y'" : where + " AND is_active='Y'"), show);
    }

    public List<User> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("declare @Page int, @PageSize int set @Page = ?; set @PageSize = ?; "
                + "with PagedResult as (select ROW_NUMBER() over (" + (order.isEmpty() ? "ORDER BY id" : order) + ") as id, user_id, "
                + "code, name, username, password, role_code, wh_code, corp_id, is_active, "
                + "ROW_NUMBER() OVER (" + (order.isEmpty() ? "ORDER BY id" : order) + ") row from dbo.[user] "
                + (where.isEmpty() ? "where is_active='Y' " : where + " AND is_active='Y'") 
                + "AND user_id like '%%%' and name like '%%%'and role_code not in ('SYSADMIN')) "
                + "select * from PagedResult where id between case when @Page > 1 "
                + "then (@PageSize * @Page) - @PageSize + 1 else @Page end and @PageSize * @Page", new UserListMap(), page, show);
    }

    public User findId(String id) {
        List<User> users = jdbcTemplate.query("SELECT USER_ID, CODE, NAME, USERNAME, PASSWORD, ROLE_CODE, EMAIL, IS_ACTIVE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, WH_CODE, CORP_ID FROM " + getTableName() + " WHERE USER_ID = ?", this, id);
        return users.isEmpty() ? null : users.get(0);
    }

}
