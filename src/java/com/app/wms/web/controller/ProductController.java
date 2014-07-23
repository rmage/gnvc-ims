package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.UomDaoException;
import com.app.wms.engine.db.factory.*;
import com.app.wms.web.util.AppConstant;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController extends MultiActionController {

    /**
     * Method 'findByPrimaryKey'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            HashMap m = null;
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("1_setup/ProductEdit", "model", m);
            } else {
                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/ProductList", "model", m);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            HashMap m = new HashMap();

            Integer page = null;
            Integer paging = null;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            if (request.getParameter("paging") != null) {
                paging = Integer.parseInt(request.getParameter("paging"));
            }
            if (page == null) {
                page = 1;
            }
            if (paging == null) {
                paging = 10;
            }
            int start = (page - 1) * paging + 1;
            int end = start + paging - 1;

            Product p = new Product();
            p.setProductCode(request.getParameter("productCode"));
            p.setProductName(request.getParameter("productName"));

            ProductDao dao = DaoFactory.createProductDao();
            List<Product> listSearchPage = dao.findProductPaging(p, page);

            ProductCategoryDao productCategoryDao = DaoFactory.createProductCategoryDao();
            for (Product x : listSearchPage) {
                List<ProductCategory> pc = productCategoryDao.findWhereCategoryCodeEquals(x.getProductCategory());
                x.setProductCategory(pc.isEmpty() ? "- product category not found -" : pc.get(0).getCategoryName());
            }

            int total = 2000;
            m.put("product", listSearchPage);
            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);

            return m;
        } catch (Exception e) {
            throw e;
        }
    }

    private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
            //String productCode = request.getParameter("productCode");
            String productId = request.getParameter("productId");
            System.out.println("productId=" + productId);
            ProductDao dao = DaoFactory.createProductDao();
            Product dto = new Product();

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(productId);

            }
            if (dto.getProductCode() == null) {

                dto.setProductCode("");
                dto.setProductName("");
                dto.setProductAlias("");
                dto.setBarCode("");
                dto.setProductCategory("");
                dto.setBrandName("");
                dto.setProductType("");
                dto.setProductColor("");
                dto.setProductDescription("");
                dto.setVolumeWeight("");
                dto.setUnitWeight("");
                dto.setUnitPiece(1);
                dto.setVolumeMatrix("");
                dto.setUnitHeight("");
                dto.setUnitLength("");
                dto.setUnitWidth("");
                dto.setUnitBox(0);
                dto.setUnitCartoon(0);
                dto.setUnitPallete(0);
                dto.setIsActive("Y");
                dto.setIsDelete("N");
            }

            if (dto.getProductCategory() != null || dto.getProductCode() != null || dto.getProductName() != null) {
                System.out.println("dto =" + dto);
                dto.setProductCode(dto.getProductCode());
                dto.setProductName(dto.getProductName());
                dto.setProductAlias(dto.getProductAlias());
                dto.setBarCode(dto.getBarCode());
                dto.setProductCategory(dto.getProductCategory());
                dto.setBrandName(dto.getBrandName());
                dto.setProductType(dto.getProductType());
                dto.setProductColor(dto.getProductColor());
                dto.setProductDescription(dto.getProductDescription());
                dto.setVolumeWeight(dto.getVolumeWeight());
                dto.setUnitWeight(dto.getUnitWeight());
                dto.setUnitPiece(dto.getUnitPiece());
                dto.setVolumeMatrix(dto.getVolumeMatrix());
                dto.setUnitMatrix(dto.getUnitMatrix());
                dto.setUnitHeight(dto.getUnitHeight());
                dto.setUnitLength(dto.getUnitLength());
                dto.setUnitWidth(dto.getUnitWidth());
                dto.setUnitBox(dto.getUnitBox());
                dto.setUnitCartoon(dto.getUnitCartoon());
                dto.setUnitPallete(dto.getUnitPallete());
                dto.setIsActive(dto.getIsActive());
                dto.setIsDelete(dto.getIsDelete());
            }
            //edit
            HashMap m = new HashMap();
            ProductCategoryDao daoCategory = DaoFactory.createProductCategoryDao();
            UomDao daoUoM = DaoFactory.createUomDao();
            WarehouseDao daoWh = DaoFactory.createWarehouseDao();
            SupplierDao daoSupp = DaoFactory.createSupplierDao();

            List<ProductCategory> dropListCategory = daoCategory.findAll();
            List<Uom> dropListUOM = daoUoM.findAll();
//            List<Warehouse> dropListWh = daoWh.findAll();
//            List<Supplier> dropListSupplier = daoSupp.findAll();

            m.put("dropListCategory", dropListCategory);
            m.put("dropListUOM", dropListUOM);
//            m.put("dropListWarehouse", dropListWh);
//            m.put("dropListSupplier", dropListSupplier);
            m.put("dto", dto);

            return m;

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Method 'findAll'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {

            ProductDao dao = DaoFactory.createProductDao();
            List<Product> dto = dao.findAll();

            return new ModelAndView("1_setup/ProductList", "model", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap m = new HashMap();
        String productId = request.getParameter("key");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        String createdBy = "";
        if (lu == null) {
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        } else {
            createdBy = (String) lu.getUserId();
        }

        ProductDao dao = DaoFactory.createProductDao();
        Product dto = dao.findByPrimaryKey(productId);
        if (dto != null) {
            UserDao uDao = DaoFactory.createUserDao();
            dto.setIsActive("N");
            dto.setIsDelete("Y");
            dto.setUpdatedBy(uDao.findByPrimaryKey(createdBy).getName());
            dto.setUpdatedDate(new Date());
            dao.update(dto.createPk(), dto);
        }

        m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/ProductList", "model", m);
    }

    /**
     * Method 'findWhereWhCodeEquals'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereIdEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.lang.String id = request.getParameter("productId");

            // create the DAO class
            ProductDao dao = DaoFactory.createProductDao();

            // execute the finder
            List<Product> dto = dao.findWhereProductIdEquals(id);

            return new ModelAndView("1_setup/ProductList", "model", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'findWhereNameEquals'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereNameEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.lang.String name = request.getParameter("productName");

            // create the DAO class
            ProductDao dao = DaoFactory.createProductDao();

            // execute the finder
            List<Product> dto = dao.findWhereProductNameEquals(name);

            return new ModelAndView("1_setup/ProductList", "model", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'create'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map map = new HashMap();
        map = this.getModelByPrimaryKey(request);
        map.put("mode", "create");

//        ProductDao dao = DaoFactory.createProductDao();
        return new ModelAndView("1_setup/ProductAdd", "model", map);
    }

    /**
     * Method 'save'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String createdBy;
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            /* DAO | Define needed dao here */
            UserDao uDao = DaoFactory.createUserDao();
            ProductDao pDao = DaoFactory.createProductDao();
            StockInventoryDao siDao = DaoFactory.createStockInventoryDao();

            /* TRANSACTION | Something complex here */
            // user from user login
            createdBy = uDao.findByPrimaryKey(lu.getUserId()).getName();

            // insert product
            Product p = new Product();
            p.setProductCode(request.getParameter("itemCode"));
            p.setProductName(request.getParameter("itemname"));
            p.setProductDescription(request.getParameter("itemDescription"));
            p.setProductCategory(request.getParameter("category"));
            p.setUom(request.getParameter("uom"));
            p.setIsActive("Y");
            p.setIsDelete("N");
            p.setCreatedBy(createdBy);
            p.setCreatedDate(new Date());
            pDao.insert(p);

            // insert stock inventory
            StockInventory si = new StockInventory();
            si.setProductCode(p.getProductCode());
            si.setWhCode("");
            si.setBalance(BigDecimal.ZERO);
            si.setStartBalance(BigDecimal.ZERO);
            si.setIsActive("Y");
            si.setIsDelete("N");
            si.setCreatedBy(createdBy);
            si.setCreatedDate(p.getCreatedDate());
            siDao.insert(si);

            return new ModelAndView("redirect:Product.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:Product.htm?action=create");
        }
//        boolean isCreate = true;
//        String strError = "";
//        Date now = new Date();
//        java.lang.String mode = request.getParameter("mode");
//        Product dto = null;
//        StockInventory dtos = new StockInventory();
//        try {
//            if (mode.equalsIgnoreCase("create")) {
//                isCreate = true;
//            } else {
//                isCreate = false;
//            }
//
//            String productId = request.getParameter("productId");
//            ProductDao dao = DaoFactory.createProductDao();
//            StockInventoryDao daoinv = DaoFactory.createStockInventoryDao();
//            if (isCreate) {
//                    dto = new Product();
//            } else {
//                dto = dao.findByPrimaryKey(productId);
//            }
//
//            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
//            String userId = "";
//            if (lu == null) {
//                HashMap m = new HashMap();
//                String msg = "You haven't login or your session has been expired! Please do login again";
//                m.put("msg", msg);
//                return new ModelAndView("login", "model", m);
//            } else{
//                userId = (String)lu.getUserId();
//            }
//
//            //String whCode = request.getParameter("warehouse");
//            String productCode = request.getParameter("productCode");
//            List<Product> tmp = dao.findWhereProductCodeEquals(productCode);
//            if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getProductCode().equals(productCode))) {
//                strError += "Item code already exists. Please try a different values" + AppConstant.EOL;
//            }
//
//            String productName = request.getParameter("productName");
//            String barcode = request.getParameter("barcode");
//            String productCategory = request.getParameter("category");
//            String brand = request.getParameter("brand");
//            //String productColor = request.getParameter("color");
//            //String productDescription = request.getParameter("description");
//            String pisActive = "Y";
//            String uom = request.getParameter("uom");
//            //String supplier = request.getParameter("supplierName");
//            //String buyer = request.getParameter("buyer");
//            String packstyle = request.getParameter("packstyle");
//            String packsize = request.getParameter("packsize");
//            String lid = request.getParameter("lid");
//            String nwdwpw = request.getParameter("nwdwpw");
//            String cancode = request.getParameter("cancode");
//
//            if (isCreate) {
//                dto.setCreatedBy(userId);
//                dto.setCreatedDate(now);
//            }
//
//            dto.setProductCode(productCode);
//            dto.setWhCode("");
//
//            /* auto insert to table stock inventory */
//            dtos.setProductId(productId);
//            dtos.setProductCode(productCode);
//            dtos.setWhCode("");
//            dtos.setBalance(BigDecimal.ZERO);
//            /**************************************/
//
//            dto.setProductName(productName);
//            String productAlias = productCode.concat(" - ").concat(productName);
//            dto.setProductAlias(productAlias);
//            dto.setBarCode(barcode);
//            dto.setProductCategory(productCategory);
//            dto.setBrandName(brand);
//            dto.setProductColor("");
//            dto.setProductDescription("");
//            dto.setIsActive(pisActive);
//            dto.setIsDelete("N");
//            dto.setUom(uom);
//            dto.setSupplier("");
//            dto.setBuyer("");
//            dto.setPackstyle(packstyle);
//            dto.setPacksize(packsize);
//            dto.setLid(lid);
//            dto.setNwdwpw(nwdwpw);
//            dto.setCanCode(cancode);
//            dto.setUserId(userId);
//            dto.setUpdatedBy(userId);
//            dto.setUpdatedDate(new java.util.Date());
//
//            if (strError.length() > 0) {
//                throw new Exception(strError);
//            }
//
//            if (isCreate) {
//                dao.insert(dto);
//                daoinv.insert(dtos);
//            }  else {
//                dto.setIsActive(pisActive);
//                dto.setUpdatedBy(userId);
//                dto.setUpdatedDate(new java.util.Date());
//                dao.update(dto.createPk(), dto);
//
//                daoinv.updateFromProduct(dtos.createPk(), dtos);
//            }
////          return new ModelAndView("1_setup/ProductView", "dto", dto);
//            return new ModelAndView("redirect:Product.htm");
//      } catch (Exception e) {
//          e.printStackTrace();
//          String errorMsg = e.getMessage();
//          HashMap m = this.getModelByPrimaryKey(request);
//          m.put("mode", mode);
//          m.put("msg", errorMsg);
//          System.out.println("error2="+errorMsg);
//          if (isCreate) {
//              return new ModelAndView("1_setup/ProductAdd", "model", m);
//          } else {
//              return new ModelAndView("1_setup/ProductEdit", "model", m);
//          }
//      }
    }
    
    public ModelAndView saveUpdate(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productId = request.getParameter("productId");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            ProductDao pDao = DaoFactory.createProductDao();
            UserDao uDao = DaoFactory.createUserDao();
            
            Product p = pDao.findByPrimaryKey(productId);
            p.setProductName(request.getParameter("itemname"));
            p.setProductDescription(request.getParameter("itemDescription"));
            p.setProductCategory(request.getParameter("category"));
            p.setUom(request.getParameter("uom"));
            p.setUpdatedBy(uDao.findByPrimaryKey(lu.getUserId()).getName());
            p.setUpdatedDate(new Date());
            pDao.update(p.createPk(), p);
            
            return new ModelAndView("redirect:Product.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:Product.htm?action=update&key=PR048904");
        }
    }

    public void getUnique(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ProductDaoException {

        PrintWriter pw = response.getWriter();
        String uniCode = request.getParameter("term");
        ProductDao productDao = DaoFactory.createProductDao();
        List<Product> sp = productDao.findWhereProductCodeEquals(uniCode);
        if (sp.isEmpty()) {
            pw.print("{\"status\": false}");
        } else {
            pw.print("{\"status\": true}");
        }
    }

    //Modified 9 April 2014
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();

        ProductDao proDao = DaoFactory.createProductDao();

        pw.print("{\"maxpage\": " + proDao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show"))) + ",\"data\": [");
        List<Product> ps = proDao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (Product x : ps) {
            if (b) {
                pw.print(",");
            }
            pw.print("{\"1\": \"" + x.getProductId() + "\", ");
            pw.print("\"2\": \"" + x.getProductCode() + "\", ");
            pw.print("\"3\": \"" + x.getProductName() + "\", ");
            pw.print("\"4\": \"" + x.getProductCategory() + "\", ");
            pw.print("\"5\": \"" + x.getIsActive() + "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]}");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response)
            throws ProductCategoryDaoException, UomDaoException {
        /* DATA | get initial value */
        String id = request.getParameter("key");

        /* DAO | Define needed dao here */
        UomDao daoUoM = DaoFactory.createUomDao();
        ProductDao productDao = DaoFactory.createProductDao();
        ProductCategoryDao daoCategory = DaoFactory.createProductCategoryDao();

        /* TRANSACTION | Something complex here */
        Map map = new HashMap();

        // get product that will be updated
        Product dto = productDao.findId(id);
        map.put("p", dto);

        // get product category
        List<ProductCategory> dropListCategory = daoCategory.findAll();
        map.put("dropListCategory", dropListCategory);

        // get product unit of measurement
        List<Uom> dropListUOM = daoUoM.findAll();
        map.put("dropListUOM", dropListUOM);
        
        return new ModelAndView("1_setup/ProductEdit", "model", map);
    }
}
