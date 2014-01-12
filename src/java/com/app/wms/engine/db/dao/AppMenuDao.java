package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.AppMenuDao;
import com.app.wms.engine.db.dto.AppMenu;
import com.app.wms.engine.db.dto.AppMenuPk;
import com.app.wms.engine.db.exceptions.AppMenuDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AppMenuDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return AppMenuPk
     */
    public AppMenuPk insert(AppMenu dto);

    /**
     * Updates a single row in the APP_MENU table.
     */
    public void update(AppMenuPk pk, AppMenu dto) throws AppMenuDaoException;

    /**
     * Deletes a single row in the APP_MENU table.
     */
    public void delete(AppMenuPk pk) throws AppMenuDaoException;

    /**
     * Returns all rows from the APP_MENU table that match the criteria 'MENU_CODE = :menuCode'.
     */
    public AppMenu findByPrimaryKey(String menuCode) throws AppMenuDaoException;

    /**
     * Returns all rows from the APP_MENU table that match the criteria ''.
     */
    public List<AppMenu> findAll() throws AppMenuDaoException;

//    /**
//     * Returns all rows from the APP_MENU table that match the criteria 'MENU_CODE = :menuCode'.
//     */
//    public List<AppMenu> findWhereMenuCodeEquals(String menuCode) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU table that match the criteria 'NAME = :name'.
//     */
//    public List<AppMenu> findWhereNameEquals(String name) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU table that match the criteria 'URL = :url'.
//     */
//    public List<AppMenu> findWhereUrlEquals(String url) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU table that match the criteria 'CREATED_BY = :createdBy'.
//     */
//    public List<AppMenu> findWhereCreatedByEquals(BigDecimal createdBy) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU table that match the criteria 'CREATED_DATE = :createdDate'.
//     */
//    public List<AppMenu> findWhereCreatedDateEquals(Date createdDate) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU table that match the criteria 'UPDATED_BY = :updatedBy'.
//     */
//    public List<AppMenu> findWhereUpdatedByEquals(BigDecimal updatedBy) throws AppMenuDaoException;
//
//    /**
//     * Returns all rows from the APP_MENU table that match the criteria 'UPDATED_DATE = :updatedDate'.
//     */
//    public List<AppMenu> findWhereUpdatedDateEquals(Date updatedDate) throws AppMenuDaoException;
    /**
     * Returns the rows from the APP_MENU table that matches the specified primary-key value.
     */
    public AppMenu findByPrimaryKey(AppMenuPk pk) throws AppMenuDaoException;

    /**
     *
     * @param userID
     * @return List
     * @throws AppMenuDaoException
     */
    public List<AppMenu> findByAuthLogin(String userID) throws AppMenuDaoException;

    public List<AppMenu> findByMenuGroup(String groupCode) throws AppMenuDaoException;

    public List<AppMenu> findWhereNotInAppMenuRole(String roleCode, String filter) throws AppMenuDaoException;
}
