package com.app.wms.engine.db.factory;

import com.app.wms.engine.db.dao.*;
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
     * Method 'createCrossdockDao'
     *
     * @return CrossdockDao
     */
    public static CrossDockDao createCrossDockDao() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("dao-beans.xml"));
        return (CrossDockDao) bf.getBean("CrossDockDao");
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
	public static PackingDetailDao createPackingDetailDao()
	{
		BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
		return (PackingDetailDao) bf.getBean( "PackingDetailDao" );
	}
	
	/**
	 * Method 'createPutawayDetailDao'
	 * 
	 * @return PutawayDetailDao
	 */
	public static PutawayDetailDao createPutawayDetailDao()
	{
		BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
		return (PutawayDetailDao) bf.getBean( "PutawayDetailDao" );
	}
	
	/**
	 * Method 'createReplenishDetailDao'
	 * 
	 * @return ReplenishDetailDao
	 */
	public static ReplenishDetailDao createReplenishDetailDao()
	{
		BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
		return (ReplenishDetailDao) bf.getBean( "ReplenishDetailDao" );
	}

	/**
	 * Method 'createReplenishmentDao'
	 * 
	 * @return ReplenishmentDao
	 */
	public static ReplenishmentDao createReplenishmentDao()
	{
		BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
		return (ReplenishmentDao) bf.getBean( "ReplenishmentDao" );
	}
	
	/**
	 * Method 'createConsigneeDao'
	 * 
	 * @return ConsigneeDao
	 */
	public static ConsigneeDao createConsigneeDao()
	{
		BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
		return (ConsigneeDao) bf.getBean( "ConsigneeDao" );
	}
        
        public static POCrossDockDao createPOCrossDockDao(){
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (POCrossDockDao) bf.getBean( "POCrossDockDao" );
        }

        public static POCrossDockDtlDao createPOCrossDockDtlDao(){
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (POCrossDockDtlDao) bf.getBean( "POCrossDockDtlDao" );
        }
        
        public static GRCrossDockDao createGRCrossDockDao(){
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (GRCrossDockDao) bf.getBean( "GRCrossDockDao" );
        }

        public static GRCrossDockDtlDao createGRCrossDockDtlDao(){
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (GRCrossDockDtlDao) bf.getBean( "GRCrossDockDtlDao" );
        }
        
        public static PACrossDockDao createPACrossDockDao(){
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (PACrossDockDao) bf.getBean( "PACrossDockDao" );
        }
        
        public static SOCrossDockDao createSOCrossDockDao(){
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (SOCrossDockDao) bf.getBean( "SOCrossDockDao" );
        }
        
        public static PCrossDockDao createPCrossDockDao(){
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (PCrossDockDao) bf.getBean( "PCrossDockDao" );
        }
        
        public static DOCrossDockDao createDOCrossDockDao(){
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (DOCrossDockDao) bf.getBean( "DOCrossDockDao" );
        }
        
        public static WhLocatingAreaDao createWhLocatingAreaDao()
        {
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (WhLocatingAreaDao) bf.getBean( "WhLocatingAreaDao" );
        }
        
        public static QuarantineDao createQuarantineDao(){
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (QuarantineDao) bf.getBean( "QuarantineDao" );
        }
        
        public static QuarantineDtlDao createQuarantineDtlDao(){
            BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
            return (QuarantineDtlDao) bf.getBean( "QuarantineDtlDao" );
        }
	
		public static UomDao createUomDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (UomDao) bf.getBean( "UomDao" );
		}
		
		public static DepartmentDao createDepartmentDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (DepartmentDao) bf.getBean( "DepartmentDao" );
		}
		
		public static SupplierDao createSupplierDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (SupplierDao) bf.getBean( "SupplierDao" );
		}
		
		public static PoDao createPoDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (PoDao) bf.getBean( "PoDao" );
		}
		
		public static PoDetailDao createPoDetailDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (PoDetailDao) bf.getBean( "PoDetailDao" );
		}
		
		public static GoodreceiveDetailDao createGoodreceiveDetailDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (GoodreceiveDetailDao) bf.getBean( "GoodreceiveDetailDao" );
		}

		public static VgrdetailproductcategoryDao createVgrdetailproductcategoryDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (VgrdetailproductcategoryDao) bf.getBean( "VgrdetailproductcategoryDao" );
		}
		
		public static CurrencyDao createCurrencyDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (CurrencyDao) bf.getBean( "CurrencyDao" );
		}
	
		public static PriceCatalogDao createPriceCatalogDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (PriceCatalogDao) bf.getBean( "PriceCatalogDao" );
		}

		public static ProductPriceDao createProductPriceDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (ProductPriceDao) bf.getBean( "ProductPriceDao" );
		}
		
		public static PtsDao createPtsDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (PtsDao) bf.getBean( "PtsDao" );
		}
		
		public static StockBalanceDao createStockBalanceDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (StockBalanceDao) bf.getBean( "StockBalanceDao" );
		}
		
		public static StockInDao createStockInDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (StockInDao) bf.getBean( "StockInDao" );
		}
		
		public static StockInventoryDao createStockInventoryDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (StockInventoryDao) bf.getBean( "StockInventoryDao" );
		}
		
		public static StockOpnameDao createStockOpnameDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (StockOpnameDao) bf.getBean( "StockOpnameDao" );
		}
		
		public static StockOutDao createStockOutDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (StockOutDao) bf.getBean( "StockOutDao" );
		}
		
		public static WarehouseDao createWarehouseDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (WarehouseDao) bf.getBean( "WarehouseDao" );
		}
		
		public static WsDao createWsDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (WsDao) bf.getBean( "WsDao" );
		}
		
		public static SwsDao createSwsDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (SwsDao) bf.getBean( "SwsDao" );
		}

		public static SwsDetailDao createSwsDetailDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (SwsDetailDao) bf.getBean( "SwsDetailDao" );
		}
	
		public static BorDao createBorDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (BorDao) bf.getBean( "BorDao" );
		}

		public static BorDetailDao createBorDetailDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (BorDetailDao) bf.getBean( "BorDetailDao" );
		}

		public static DrDao createDrDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (DrDao) bf.getBean( "DrDao" );
		}

		public static DrDetailDao createDrDetailDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (DrDetailDao) bf.getBean( "DrDetailDao" );
		}

		public static EdsDao createEdsDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (EdsDao) bf.getBean( "EdsDao" );
		}

		public static EdsDetailDao createEdsDetailDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (EdsDetailDao) bf.getBean( "EdsDetailDao" );
		}

		public static TsDao createTsDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (TsDao) bf.getBean( "TsDao" );
		}

		public static TsDetailDao createTsDetailDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (TsDetailDao) bf.getBean( "TsDetailDao" );
		}
		
		public static PrsDao createPrsDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (PrsDao) bf.getBean( "PrsDao" );
		}

		public static PrsDetailDao createPrsDetailDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (PrsDetailDao) bf.getBean( "PrsDetailDao" );
		}
		
		public static CanvassingDao createCanvassingDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (CanvassingDao) bf.getBean( "CanvassingDao" );
		}

		public static CanvassingDetailDao createCanvassingDetailDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (CanvassingDetailDao) bf.getBean( "CanvassingDetailDao" );
		}
		
		public static AssignCanvasserDao createAssignCanvasserDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (AssignCanvasserDao) bf.getBean( "AssignCanvasserDao" );
		}
		
		public static ApprovalRangeDao createApprovalRangeDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (ApprovalRangeDao) bf.getBean( "ApprovalRangeDao" );
		}
		
		public static BeritaAcaraDao createBeritaAcaraDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (BeritaAcaraDao) bf.getBean( "BeritaAcaraDao" );
		}
	
		public static AccountingDao createAccountingDao()
		{
			BeanFactory bf = new XmlBeanFactory( new ClassPathResource("dao-beans.xml") );
			return (AccountingDao) bf.getBean( "AccountingDao" );
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
    
}
