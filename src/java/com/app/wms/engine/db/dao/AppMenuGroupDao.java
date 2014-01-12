package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.AppMenuGroup; // depend on other dto , gak papa lah, males register new dao.
import com.app.wms.engine.db.dto.AppMenuGroupPk;
import com.app.wms.engine.db.exceptions.AppMenuGroupDaoException;
import java.math.BigDecimal;
import java.util.List;

public interface AppMenuGroupDao {

//    /**
//     * Method 'insert'
//     *
//     * @param dto
//     * @return AppMenuPk
//     */
    public AppMenuGroupPk insert(AppMenuGroup dto);
//
//    /**
//     * Updates a single row in the APP_MENU_GROUP table.
//     */

    public void update(AppMenuGroupPk pk, AppMenuGroup dto) throws AppMenuGroupDaoException;
//
//    /**
//     * Deletes a single row in the APP_MENU_GROUP table.
//     */

    public void delete(AppMenuGroupPk pk) throws AppMenuGroupDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU_GROUP table that match the criteria 'GROUP_CODE = :groupCode'.
//     */

    public AppMenuGroup findByPrimaryKey(String groupCode) throws AppMenuGroupDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU_GROUP table that match the criteria ''.
//     */

    public List<AppMenuGroup> findAll() throws AppMenuGroupDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU_GROUP table that match the criteria 'MENU_CODE = :groupCode'.
//     */
//    public List<AppMenu> findWhereMenuCodeEquals(String groupCode) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU_GROUP table that match the criteria 'NAME = :name'.
//     */
//    public List<AppMenu> findWhereNameEquals(String name) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU_GROUP table that match the criteria 'URL = :url'.
//     */
//    public List<AppMenu> findWhereUrlEquals(String url) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU_GROUP table that match the criteria 'CREATED_BY = :createdBy'.
//     */
//    public List<AppMenu> findWhereCreatedByEquals(BigDecimal createdBy) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU_GROUP table that match the criteria 'CREATED_DATE = :createdDate'.
//     */
//    public List<AppMenu> findWhereCreatedDateEquals(Date createdDate) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU_GROUP table that match the criteria 'UPDATED_BY = :updatedBy'.
//     */
//    public List<AppMenu> findWhereUpdatedByEquals(BigDecimal updatedBy) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU_GROUP table that match the criteria 'UPDATED_DATE = :updatedDate'.
//     */
//    public List<AppMenu> findWhereUpdatedDateEquals(Date updatedDate) throws AppMenuDaoException;
//
//    /**
//     * Returns the rows from the APP_MENU_GROUP table that matches the specified primary-key value.
//     */
//    public AppMenu findByPrimaryKey(AppMenuPk pk) throws AppMenuDaoException;

    /**
     *
     * @param userID
     * @return List
     * @throws AppMenuGroupDaoException
     */
    public List<AppMenuGroup> findByAuthLogin(String userID) throws AppMenuGroupDaoException;
}
