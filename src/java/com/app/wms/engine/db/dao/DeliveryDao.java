package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.DeliveryDao;
import com.app.wms.engine.db.dto.Delivery;
import com.app.wms.engine.db.dto.DeliveryPk;
import com.app.wms.engine.db.exceptions.DeliveryDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface DeliveryDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return DeliveryPk
     */
    public DeliveryPk insert(Delivery dto);

    /**
     * Updates a single row in the deliveryorder table.
     */
    public void update(DeliveryPk pk, Delivery dto) throws DeliveryDaoException;

    /**
     * Deletes a single row in the deliveryorder table.
     */
    public void delete(DeliveryPk pk) throws DeliveryDaoException;

    /**
     * Returns all rows from the deliveryorder table that match the criteria
     * 'delivery_no = :deliveryNo'.
     */
    public Delivery findByPrimaryKey(String deliveryNo) throws DeliveryDaoException;

    /**
     * Returns all rows from the deliveryorder table that match the criteria ''.
     */
    public List<Delivery> findAll() throws DeliveryDaoException;

    /**
     * Returns all rows from the deliveryorder table that match the criteria
     * 'delivery_no = :deliveryNo'.
     */
    public List<Delivery> findWhereDeliveryNoEquals(String deliveryNo) throws DeliveryDaoException;

    /**
     * Returns all rows from the deliveryorder table that match the criteria
     * 'delivery_date = :deliveryDate'.
     */
    public List<Delivery> findWhereDeliveryDateEquals(Date deliveryDate) throws DeliveryDaoException;

    /**
     * Returns all rows from the deliveryorder table that match the criteria
     * 'delivery_name = :deliveryName'.
     */
    public List<Delivery> findWhereDeliveryNameEquals(String deliveryName) throws DeliveryDaoException;

    /**
     * Returns all rows from the deliveryorder table that match the criteria
     * 'delivery_address = :deliveryAddress'.
     */
    public List<Delivery> findWhereDeliveryAddressEquals(String deliveryAddress) throws DeliveryDaoException;

    /**
     * Returns all rows from the deliveryorder table that match the criteria
     * 'created_by = :createdBy'.
     */
    public List<Delivery> findWhereCreatedByEquals(String createdBy) throws DeliveryDaoException;

    /**
     * Returns all rows from the deliveryorder table that match the criteria
     * 'created_date = :createdDate'.
     */
    public List<Delivery> findWhereCreatedDateEquals(Date createdDate) throws DeliveryDaoException;

    /**
     * Returns all rows from the deliveryorder table that match the criteria
     * 'updated_by = :updatedBy'.
     */
    public List<Delivery> findWhereUpdatedByEquals(String updatedBy) throws DeliveryDaoException;

    /**
     * Returns all rows from the deliveryorder table that match the criteria
     * 'updated_date = :updatedDate'.
     */
    public List<Delivery> findWhereUpdatedDateEquals(Date updatedDate) throws DeliveryDaoException;

    /**
     * Returns the rows from the deliveryorder table that matches the specified
     * primary-key value.
     */
    public Delivery findByPrimaryKey(DeliveryPk pk) throws DeliveryDaoException;

    public List<Delivery> findDeliveryPaging(Delivery d, int page) throws DeliveryDaoException;

    public int ajaxMaxPage(BigDecimal show, String where);

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);

}
