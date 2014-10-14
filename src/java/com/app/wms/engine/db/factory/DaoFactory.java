package com.app.wms.engine.db.factory;

import com.app.wms.engine.db.dao.*;
import com.spfi.ims.dao.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class DaoFactory {

    public static BasicDataSource getReportDataSource() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));

        return (BasicDataSource) bf.getBean("dataSource");
    }

    /**
     * Method 'createNextSequenceDao'
     *
     * @return NextSequenceDao
     */
    public static NextSequenceDao createNextSequenceDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (NextSequenceDao) bf.getBean("NextSequenceDao");
    }

    /**
     * Method 'createAppMenuDao'
     *
     * @return AppMenuDao
     */
    public static AppMenuDao createAppMenuDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (AppMenuDao) bf.getBean("AppMenuDao");
    }

    /**
     *
     */
    public static AppMenuGroupDao createAppMenuGroupDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (AppMenuGroupDao) bf.getBean("AppMenuGroupDao");
    }

    /**
     * Method 'createAppMenuRoleDao'
     *
     * @return AppMenuRoleDao
     */
    public static AppMenuRoleDao createAppMenuRoleDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (AppMenuRoleDao) bf.getBean("AppMenuRoleDao");
    }

    /**
     * Method 'createBundleDao'
     *
     * @return BundleDao
     */
    public static BundleDao createBundleDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (BundleDao) bf.getBean("BundleDao");
    }

    /**
     * Method 'createBundleDetailDao'
     *
     * @return BundleDetailDao
     */
    public static BundleDetailDao createBundleDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (BundleDetailDao) bf.getBean("BundleDetailDao");
    }

    /**
     * Method 'createCityDao'
     *
     * @return CityDao
     */
    public static CityDao createCityDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CityDao) bf.getBean("CityDao");
    }

    /**
     * Method 'createRegionDao'
     *
     * @return RegionDao
     */
    public static RegionDao createRegionDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (RegionDao) bf.getBean("RegionDao");
    }

    /**
     * Method 'createStockDao'
     *
     * @return StockDao
     */
    public static StockDao createStockDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (StockDao) bf.getBean("StockDao");
    }

    /**
     * Method 'createProductDao'
     *
     * @return ProductDao
     */
    public static ProductDao createProductDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ProductDao) bf.getBean("ProductDao");
    }

    /**
     * Method 'createUserDao'
     *
     * @return UserDao
     */
    public static UserDao createUserDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (UserDao) bf.getBean("UserDao");
    }

    /**
     * Method 'createUserRoleDao'
     *
     * @return UserRoleDao
     */
    public static UserRoleDao createUserRoleDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (UserRoleDao) bf.getBean("UserRoleDao");
    }

    /**
     * Method 'createProductCategoryDao'
     *
     * @return ProductCategoryDao
     */
    public static ProductCategoryDao createProductCategoryDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ProductCategoryDao) bf.getBean("ProductCategoryDao");
    }

    /**
     * Method 'createWhDao'
     *
     * @return WhDao
     */
    public static WhDao createWhDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (WhDao) bf.getBean("WhDao");
    }

    /*Warehouse Management System 12:50 AM*/
    /**
     * Method 'createCorporateDao'
     *
     * @return CorporateDao
     */
    public static CorporateDao createCorporateDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CorporateDao) bf.getBean("CorporateDao");
    }

    /**
     * Method 'createCorpDao'
     *
     * @return CorpDao
     */
    public static CorpDao createCorpDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CorpDao) bf.getBean("CorpDao");
    }

    /**
     * Method 'createCorpUserDao'
     *
     * @return CorpUserDao
     */
    public static CorpUserDao createCorpUserDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CorpUserDao) bf.getBean("CorpUserDao");
    }

    /**
     * Method 'createCorpUserGroupDao'
     *
     * @return CorpUserGroupDao
     */
    public static CorpUserGroupDao createCorpUserGroupDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CorpUserGroupDao) bf.getBean("CorpUserGroupDao");
    }

    /**
     * Method 'createCorpWhDao'
     *
     * @return CorpWhDao
     */
    public static CorpWhDao createCorpWhDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CorpWhDao) bf.getBean("CorpWhDao");
    }

    /**
     * Method 'createTallymanDao'
     *
     * @return TallymanDao
     */
    public static TallymanDao createTallymanDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (TallymanDao) bf.getBean("TallymanDao");
    }

    /**
     * Method 'createWhLocationDao'
     *
     * @return WhLocationDao
     */
    public static WhLocationDao createWhLocationDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (WhLocationDao) bf.getBean("WhLocationDao");
    }

    /**
     * Method 'createWhLocationDetailDao'
     *
     * @return WhLocationDetailDao
     */
    public static WhLocationDetailDao createWhLocationDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (WhLocationDetailDao) bf.getBean("WhLocationDetailDao");
    }

    /**
     * Method 'createWarehouseLocationDao'
     *
     * @return WarehouseLocationDao
     */
    public static WarehouseLocationDao createWarehouseLocationDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (WarehouseLocationDao) bf.getBean("WarehouseLocationDao");
    }

    /**
     * Method 'createProvinceDao'
     *
     * @return ProvinceDao
     */
    public static ProvinceDao createProvinceDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ProvinceDao) bf.getBean("ProvinceDao");
    }

    /**
     * Method 'createPurchaseDao'
     *
     * @return PurchaseDao
     */
    public static PurchaseDao createPurchaseDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PurchaseDao) bf.getBean("PurchaseDao");
    }

    /**
     * Method 'createGoodReceiveDao'
     *
     * @return GoodreceiveDao
     */
    public static GoodreceiveDao createGoodreceiveDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (GoodreceiveDao) bf.getBean("GoodreceiveDao");
    }

    /**
     * Method 'createPutawayDao'
     *
     * @return PutawayDao
     */
    public static PutawayDao createPutawayDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PutawayDao) bf.getBean("PutawayDao");
    }

    /**
     * Method 'createSalesOrderDao'
     *
     * @return SalesOrderDao
     */
    public static SalesOrderDao createSalesOrderDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (SalesOrderDao) bf.getBean("SalesOrderDao");
    }

    /**
     * Method 'createSalesOrderDetailDao'
     *
     * @return SalesOrderDetailDao
     */
    public static SalesOrderDetailDao createSalesOrderDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (SalesOrderDetailDao) bf.getBean("SalesOrderDetailDao");
    }

    /**
     * Method 'createPickingDao'
     *
     * @return PickingDao
     */
    public static PickingDao createPickingDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PickingDao) bf.getBean("PickingDao");
    }

    /**
     * Method 'createPickingDetailDao'
     *
     * @return PickingDetailDao
     */
    public static PickingDetailDao createPickingDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PickingDetailDao) bf.getBean("PickingDetailDao");
    }

    /**
     * Method 'createPackingDao'
     *
     * @return PackingDao
     */
    public static PackingDao createPackingDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PackingDao) bf.getBean("PackingDao");
    }

    /**
     * Method 'createDeliveryDao'
     *
     * @return DeliveryDao
     */
    public static DeliveryDao createDeliveryDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (DeliveryDao) bf.getBean("DeliveryDao");
    }

    /**
     * Method 'createConsolidateDao'
     *
     * @return ConsolidateDao
     */
    public static ConsolidateDao createConsolidateDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ConsolidateDao) bf.getBean("ConsolidateDao");
    }

    /**
     * Method 'createConsolidateDetailDao'
     *
     * @return ConsolidatedetailDao
     */
    public static ConsolidateDetailDao createConsolidateDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ConsolidateDetailDao) bf.getBean("ConsolidateDetailDao");
    }

    /**
     * Method 'createKittingDao'
     *
     * @return KittingDao
     */
    public static KittingDao createKittingDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (KittingDao) bf.getBean("KittingDao");
    }

    /**
     * Method 'createKittingDetailDao'
     *
     * @return KittingDetailDao
     */
    public static KittingDetailDao createKittingDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (KittingDetailDao) bf.getBean("KittingDetailDao");
    }

    /**
     * Method 'createDoDetailDao'
     *
     * @return DoDetailDao
     */
    public static DoDetailDao createDoDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (DoDetailDao) bf.getBean("DoDetailDao");
    }

    /**
     * Method 'createPackingDetailDao'
     *
     * @return PackingDetailDao
     */
    public static PackingDetailDao createPackingDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PackingDetailDao) bf.getBean("PackingDetailDao");
    }

    /**
     * Method 'createPutawayDetailDao'
     *
     * @return PutawayDetailDao
     */
    public static PutawayDetailDao createPutawayDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PutawayDetailDao) bf.getBean("PutawayDetailDao");
    }

    /**
     * Method 'createReplenishDetailDao'
     *
     * @return ReplenishDetailDao
     */
    public static ReplenishDetailDao createReplenishDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ReplenishDetailDao) bf.getBean("ReplenishDetailDao");
    }

    /**
     * Method 'createReplenishmentDao'
     *
     * @return ReplenishmentDao
     */
    public static ReplenishmentDao createReplenishmentDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ReplenishmentDao) bf.getBean("ReplenishmentDao");
    }

    /**
     * Method 'createConsigneeDao'
     *
     * @return ConsigneeDao
     */
    public static ConsigneeDao createConsigneeDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ConsigneeDao) bf.getBean("ConsigneeDao");
    }

    public static WhLocatingAreaDao createWhLocatingAreaDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (WhLocatingAreaDao) bf.getBean("WhLocatingAreaDao");
    }

    public static UomDao createUomDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (UomDao) bf.getBean("UomDao");
    }

    public static DepartmentDao createDepartmentDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (DepartmentDao) bf.getBean("DepartmentDao");
    }

    public static SupplierDao createSupplierDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (SupplierDao) bf.getBean("SupplierDao");
    }

    public static PoDao createPoDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PoDao) bf.getBean("PoDao");
    }

    public static PoDetailDao createPoDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PoDetailDao) bf.getBean("PoDetailDao");
    }

    public static GoodreceiveDetailDao createGoodreceiveDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (GoodreceiveDetailDao) bf.getBean("GoodreceiveDetailDao");
    }

    public static VgrdetailproductcategoryDao createVgrdetailproductcategoryDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (VgrdetailproductcategoryDao) bf.getBean("VgrdetailproductcategoryDao");
    }

    public static CurrencyDao createCurrencyDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CurrencyDao) bf.getBean("CurrencyDao");
    }

    public static PriceCatalogDao createPriceCatalogDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PriceCatalogDao) bf.getBean("PriceCatalogDao");
    }

    public static ProductPriceDao createProductPriceDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ProductPriceDao) bf.getBean("ProductPriceDao");
    }

    public static PtsDao createPtsDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PtsDao) bf.getBean("PtsDao");
    }

    public static StockBalanceDao createStockBalanceDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (StockBalanceDao) bf.getBean("StockBalanceDao");
    }

    public static StockInDao createStockInDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (StockInDao) bf.getBean("StockInDao");
    }

    public static StockInventoryDao createStockInventoryDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (StockInventoryDao) bf.getBean("StockInventoryDao");
    }

    public static StockOpnameDao createStockOpnameDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (StockOpnameDao) bf.getBean("StockOpnameDao");
    }

    public static StockOutDao createStockOutDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (StockOutDao) bf.getBean("StockOutDao");
    }

    public static WarehouseDao createWarehouseDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (WarehouseDao) bf.getBean("WarehouseDao");
    }

    public static WsDao createWsDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (WsDao) bf.getBean("WsDao");
    }

    public static SwsDao createSwsDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (SwsDao) bf.getBean("SwsDao");
    }

    public static SwsDtlDao createSwsDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (SwsDtlDao) bf.getBean("SwsDtlDao");
    }

    public static BorDao createBorDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (BorDao) bf.getBean("BorDao");
    }

    public static BorDtlDao createBorDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (BorDtlDao) bf.getBean("BorDtlDao");
    }

    public static DrDao createDrDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (DrDao) bf.getBean("DrDao");
    }

    public static DrDtlDao createDrDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (DrDtlDao) bf.getBean("DrDtlDao");
    }

    public static EdsDao createEdsDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (EdsDao) bf.getBean("EdsDao");
    }

    public static TsDao createTsDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (TsDao) bf.getBean("TsDao");
    }

    public static PrsDao createPrsDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PrsDao) bf.getBean("PrsDao");
    }

    public static PrsDetailDao createPrsDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PrsDetailDao) bf.getBean("PrsDetailDao");
    }

    public static CanvassingDao createCanvassingDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CanvassingDao) bf.getBean("CanvassingDao");
    }

    public static CanvassingDetailDao createCanvassingDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CanvassingDetailDao) bf.getBean("CanvassingDetailDao");
    }

    public static AssignCanvasserDao createAssignCanvasserDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (AssignCanvasserDao) bf.getBean("AssignCanvasserDao");
    }

    public static ApprovalRangeDao createApprovalRangeDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ApprovalRangeDao) bf.getBean("ApprovalRangeDao");
    }

    public static BeritaAcaraDao createBeritaAcaraDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (BeritaAcaraDao) bf.getBean("BeritaAcaraDao");
    }

    public static FishDao createFishDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishDao) bf.getBean("FishDao");
    }

    public static FishTypeDao createFishTypeDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishTypeDao) bf.getBean("FishTypeDao");
    }

    public static FishWeightTypeDao createFishWeightTypeDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishWeightTypeDao) bf.getBean("FishWeightTypeDao");
    }

    public static FishWsTypeDao createFishWsTypeDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishWsTypeDao) bf.getBean("FishWsTypeDao");
    }

    public static FishStorageDao createFishStorageDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishStorageDao) bf.getBean("FishStorageDao");
    }

    public static FishWsDao createFishWsDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishWsDao) bf.getBean("FishWsDao");
    }

    public static FishWsDetailDao createFishWsDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishWsDetailDao) bf.getBean("FishWsDetailDao");
    }

    public static FishSpoilageDao createFishSpoilageDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishSpoilageDao) bf.getBean("FishSpoilageDao");
    }

    public static FishSupplierDao createFishSupplierDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishSupplierDao) bf.getBean("FishSupplierDao");
    }

    public static FishVesselDao createFishVesselDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishVesselDao) bf.getBean("FishVesselDao");
    }

    public static FishRrDao createFishRrDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishRrDao) bf.getBean("FishRrDao");
    }

    public static FishRrDetailDao createFishRrDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishRrDetailDao) bf.getBean("FishRrDetailDao");
    }

    public static FishBalanceDao createFishBalanceDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishBalanceDao) bf.getBean("FishBalanceDao");
    }

    public static FishWdsDao createFishWdsDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishWdsDao) bf.getBean("FishWdsDao");
    }

    public static FishWdsDetailDao createFishWdsDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishWdsDetailDao) bf.getBean("FishWdsDetailDao");
    }

    public static FishTsDao createFishTsDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishTsDao) bf.getBean("FishTsDao");
    }

    public static FishTsDetailDao createFishTsDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishTsDetailDao) bf.getBean("FishTsDetailDao");
    }

    public static FishBalanceHistoryDao createFishBalanceHistoryDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishBalanceHistoryDao) bf.getBean("FishBalanceHistoryDao");
    }

    public static FishBadStockDao createFishBadStockDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishBadStockDao) bf.getBean("FishBadStockDao");
    }

    public static FishBadStockDetailDao createFishBadStockDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishBadStockDetailDao) bf.getBean("FishBadStockDetailDao");
    }

    /* FYA : 07 January 2014 */
    public static AssignCanvasserDtlDao createAssignCanvasserDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (AssignCanvasserDtlDao) bf.getBean("AssignCanvasserDtlDao");
    }

    public static AssignCanvassingDao createAssignCanvassingDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (AssignCanvassingDao) bf.getBean("AssignCanvassingDao");
    }

    public static PurchaseDtlDao createPurchaseDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PurchaseDtlDao) bf.getBean("PurchaseDtlDao");
    }

    public static ReceiveReportDao createReceiveReportDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ReceiveReportDao) bf.getBean("ReceiveReportDao");
    }

    public static ReceiveReportDtlDao createReceiveReportDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (ReceiveReportDtlDao) bf.getBean("ReceiveReportDtlDao");
    }

    public static PtsDtlDao createPtsDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (PtsDtlDao) bf.getBean("PtsDtlDao");
    }

    public static OfalDao createOfalDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (OfalDao) bf.getBean("OfalDao");
    }

    public static OfalDtlDao createOfalDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (OfalDtlDao) bf.getBean("OfalDtlDao");
    }

    public static FishMealDao createFishMealDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishMealDao) bf.getBean("FishMealDao");
    }

    public static FishMealDtlDao createFishMealDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishMealDtlDao) bf.getBean("FishMealDtlDao");
    }

    public static TsDtlDao createTsDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (TsDtlDao) bf.getBean("TsDtlDao");
    }

    public static DistributorDao createDistributorDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (DistributorDao) bf.getBean("DistributorDao");
    }

    public static CurrencyRateDao createCurrencyRateDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CurrencyRateDao) bf.getBean("CurrencyRateDao");
    }

    public static FishOilDao createFishOilDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishOilDao) bf.getBean("FishOilDao");
    }

    public static FishOilDtlDao createFishOilDtlDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishOilDtlDao) bf.getBean("FishOilDtlDao");
    }

    /* GNVS | Fish Module */
    public static BrineFreezingDao createBrineFreezingDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (BrineFreezingDao) bf.getBean("BrineFreezingDao");
    }

    public static AirBlastFreezingDao createAirBlastFreezingDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (AirBlastFreezingDao) bf.getBean("AirBlastFreezingDao");
    }

    public static FishReclassificationDao createFishReclassificationDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishReclassificationDao) bf.getBean("FishReclassificationDao");
    }

    public static FishMovingDao createFishMovingDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishMovingDao) bf.getBean("FishMovingDao");
    }

    public static FishWsSummaryDao createFishWsSummaryDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishWsSummaryDao) bf.getBean("FishWsSummaryDao");
    }

    /* GNVS | New Concept of Finish Goods */
    public static FGLocationDao createFGLocationDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGLocationDao) bf.getBean("FGLocationDao");
    }

    public static FGPackStyleDao createFGPackStyleDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGPackStyleDao) bf.getBean("FGPackStyleDao");
    }

    public static FGItemDao createFGItemDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGItemDao) bf.getBean("FGItemDao");
    }

    public static FGBookedOrderDao createFGBookedOrderDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGBookedOrderDao) bf.getBean("FGBookedOrderDao");
    }

    public static FGPalletTransferDao createFGPalletTransferDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGPalletTransferDao) bf.getBean("FGPalletTransferDao");
    }

    public static FGOrderFillDao createFGOrderFillDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGOrderFillDao) bf.getBean("FGOrderFillDao");
    }

    public static FGLabelingMonitoringDao createFGLabelingMonitoringDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGLabelingMonitoringDao) bf.getBean("FGLabelingMonitoringDao");
    }

    public static FGExportDeliveryDao createFGExportDeliveryDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGExportDeliveryDao) bf.getBean("FGExportDeliveryDao");
    }

    public static FGTransferDao createFGTransferDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGTransferDao) bf.getBean("FGTransferDao");
    }

    public static FGReclassificationDao createFGReclassificationDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGReclassificationDao) bf.getBean("FGReclassificationDao");
    }

    public static FGDeliveryDao createFGDeliveryDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGDeliveryDao) bf.getBean("FGDeliveryDao");
    }

    public static FGReturnCargoDao createFGReturnCargoDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGReturnCargoDao) bf.getBean("FGReturnCargoDao");
    }

    public static FGPalletDispositionDao createFGPalletDispositionDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGPalletDispositionDao) bf.getBean("FGPalletDispositionDao");
    }

    public static FGPalletRejectionDao createFGPalletRejectionDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGPalletRejectionDao) bf.getBean("FGPalletRejectionDao");
    }

    /* GNVS | Rendering Fish Module */
    public static RenderingFishDao createRenderingFishDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (RenderingFishDao) bf.getBean("RenderingFishDao");
    }
    
    public static RenderingSalesDao createRenderingSalesDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (RenderingSalesDao) bf.getBean("RenderingSalesDao");
    }

    /* GNVS | D3 Data-Driven Documents */
    public static D3Dao createD3Dao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (D3Dao) bf.getBean("D3Dao");
    }

    /*ACCOUNTING AND STUFF DAO*/
    public static NonFishStockCardDao createNonFishStockCardDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (NonFishStockCardDao) bf.getBean("NonFishStockCardDao");
    }

    public static NonFishStockCardSummaryDao createNonFishStockCardSummaryDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (NonFishStockCardSummaryDao) bf.getBean("NonFishStockCardSummaryDao");
    }

    public static FishTransactionDao createFishTransactionDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishTransactionDao) bf.getBean("FishTransactionDao");
    }

    /*MASTER FISH UNIT COST*/
    public static FishUnitCostDao createFishUnitCostDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishUnitCostDao) bf.getBean("FishUnitCostDao");
    }

    /*FG UNIT COST DAO*/
    public static FgUnitCostDao createFgUnitCostDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FgUnitCostDao) bf.getBean("FgUnitCostDao");
    }

    /*FG STOCK CARD DAO*/
    public static FGStockCardDao createFGStockCardDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FGStockCardDao) bf.getBean("FGStockCardDao");
    }

    /*FISH RR ACCOUNTING FishRRAccountingDao*/
    public static FishRRAccountingDao createFishRRAccountingDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishRRAccountingDao) bf.getBean("FishRRAccountingDao");
    }

    public static FishRRAccountingDetailDao createFishRRAccountingDetailDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (FishRRAccountingDetailDao) bf.getBean("FishRRAccountingDetailDao");
    }

    public static CategoryItemCurrencyTypeDao createCategoryItemCurrencyTypeDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CategoryItemCurrencyTypeDao) bf.getBean("CategoryItemCurrencyTypeDao");
    }

}
