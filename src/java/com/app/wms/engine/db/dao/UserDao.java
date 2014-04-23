package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.UserPk;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.web.engine.search.UserSearch;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface UserDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return UserPk
     */
    public UserPk insert(User dto);

    /**
     * Updates a single row in the USER table.
     */
    public void update(UserPk pk, User dto) throws UserDaoException;
    
    public void updateInactivate(UserPk pk, User dto) throws UserDaoException;

    /**
     * Deletes a single row in the USER table.
     */
    public void delete(UserPk pk) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'USER_ID = :userId'.
     */
    public User findByPrimaryKey(String userId) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria ''.
     */
    //public List<User> findAll() throws UserDaoException;
    /**
     * Returns all rows from the USER table that match the criteria ''.
     */
    public List<User> findAll(String isWIC, String isDSA) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'ROLE_CODE = :roleCode'.
     */
    public List<User> findByUserRole(String roleCode) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'USER_ID = :userId'.
     */
    public List<User> findWhereUserIdEquals(BigDecimal userId) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'CODE = :code'.
     */
    public List<User> findWhereCodeEquals(String code) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'NAME = :name'.
     */
    public List<User> findWhereNameEquals(String name) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'USERNAME = :username'.
     */
    public List<User> findWhereUsernameEquals(String username) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'PASSWORD = :password'.
     */
    public List<User> findWherePasswordEquals(String password) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'ROLE_CODE = :roleCode'.
     */
    public List<User> findWhereRoleCodeEquals(String roleCode) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'EMAIL = :email'.
     */
    public List<User> findWhereEmailEquals(String email) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'IS_ACTIVE = :isActive'.
     */
    public List<User> findWhereIsActiveEquals(String isActive) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'CREATED_BY = :createdBy'.
     */
    public List<User> findWhereCreatedByEquals(BigDecimal createdBy) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'CREATED_DATE = :createdDate'.
     */
    public List<User> findWhereCreatedDateEquals(Date createdDate) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'UPDATED_BY = :updatedBy'.
     */
    public List<User> findWhereUpdatedByEquals(BigDecimal updatedBy) throws UserDaoException;

    /**
     * Returns all rows from the USER table that match the criteria 'UPDATED_DATE = :updatedDate'.
     */
    public List<User> findWhereUpdatedDateEquals(Date updatedDate) throws UserDaoException;

    public LoginUser findWhereUsernamePasswordEquals(String username, String password) throws UserDaoException;

    /**
     * Returns the rows from the USER table that matches the specified primary-key value.
     */
    public User findByPrimaryKey(UserPk pk) throws UserDaoException;

    public List<User> findLimit(int page, int paging) throws UserDaoException;

    public List<User> findByCriteriaLimit(UserSearch criteria, int start, int end) throws UserDaoException;

    public BigDecimal getNextUserId() throws UserDaoException;

    public List<User> findNextLevel(String sourceCode) throws UserDaoException;

    public List<User> findBoss(String bgCode, int bossLevel) throws UserDaoException;

    public List<User> findByBg(String bgCode) throws UserDaoException;

    public List<User> findWhereNotInUserBg(String bgCode, String filter, String isWIC, String isDSA) throws UserDaoException;

    public List<User> findByBgWhereRoleScm(String bgCode) throws UserDaoException;
    
    public List<User> findByUserName(String bgCode) throws UserDaoException;

    public List<User> findOtherUserInSameBg(String bgCode, BigDecimal userID) throws UserDaoException;

    public List<User> findUserInSvcPic(String bgCode, int page, int paging) throws UserDaoException;

    public List<User> findUserNotInSvcPic(UserSearch criteria, String bgCode, int page, int paging) throws UserDaoException;
  
    public Integer countUserInSvcPic(String bgCode) throws UserDaoException;

    public Integer countUserNotInSvcPic(UserSearch criteria, String bgCode) throws UserDaoException;
    
    public LoginUser findWhereLogin(String username, String password, String wh, String corp, String loginas) throws UserDaoException;
    
    public LoginUser findWhereLoginIMS(String username, String password) throws UserDaoException;
    
    public List<User> findUserPaging(UserSearch c, int page) throws UserDaoException;
    
    public List<User> findRoleCanvasser() throws UserDaoException;
    
    public List<User> findUserRoleAppRange() throws UserDaoException;

    //Modified 21 April 2014
    public int ajaxMaxPage(String where, BigDecimal show);
    public List<User> ajaxSearch(String where, String order, int page, int show);
    public User findId(String id);
}
