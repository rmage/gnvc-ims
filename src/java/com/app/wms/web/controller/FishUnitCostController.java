package com.app.wms.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishSupplierDao;
import com.app.wms.engine.db.dao.FishUnitCostDao;
import com.app.wms.engine.db.dao.PrsDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishSupplier;
import com.app.wms.engine.db.dto.FishUnitCost;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;
import static com.app.wms.web.util.Utility.isExist;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FishUnitCostController extends MultiActionController {

    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /*DEFINE DAO*/
    private final FishSupplierDao fishSupplierDao = DaoFactory.createFishSupplierDao();

    private final FishDao fishDao = DaoFactory.createFishDao();

    private final FishUnitCostDao fishUnitCostDao = DaoFactory.createFishUnitCostDao();

    private List<FishSupplier> suppliers = new ArrayList<FishSupplier>();

    private List<Fish> fishes = new ArrayList<Fish>();

    private List<FishUnitCost> fces = new ArrayList<FishUnitCost>();

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

            HashMap<String, Object> modelMap = new HashMap<String, Object>();
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                modelMap.put("mode", "edit");
                return new ModelAndView("1_setup/FishUnitCostList", "model", modelMap);
            } else {
                /*VIEW*/

                /*GET ALL FISH SUPPLIER*/
                suppliers = fishSupplierDao.findAll();

                /*GET ALL FISH*/
                fishes = fishDao.findAllActiveAndNotDelete();

                modelMap.put("suppliers", suppliers);
                modelMap.put("fishes", fishes);
                return new ModelAndView("1_setup/FishUnitCostList", "model", modelMap);
            }

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
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("mode", "create");

        modelMap.put("suppliers", suppliers);
        modelMap.put("fishes", fishes);

        return new ModelAndView("1_setup/FishUnitCostAdd", "model", modelMap);
    }

    /**
     * Method 'save'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        Date now = new Date();
        Date start = new Date();
        Date end = new Date();
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        String mode = request.getParameter("mode");

        System.out.println("SAVE");

        /*GET ALL FISH*/
        fishes = fishDao.findAllActiveAndNotDelete();

        BigDecimal unitCost;
        FishUnitCost fc = new FishUnitCost();
        Integer fcId = 0;
        boolean isExist = false;

        /*GET FORM SUBMITTED VALUE*/
        String contractNumber = request.getParameter("contractNumber");
        String supplierCode = request.getParameter("groupSupplierName");
        String startDate = request.getParameter("contractBeginDate");
        String endDate = request.getParameter("contractEndDate");

        if (mode.equalsIgnoreCase("create")) {

            boolean isNotUnique = fishUnitCostDao.isContractNumberAlreadyUsed(contractNumber);
            if (isNotUnique) {
                modelMap.put("mode", "create");

                modelMap.put("msg", "Contract Number not Unique");

                modelMap.put("suppliers", suppliers);
                modelMap.put("fishes", fishes);

                /*SET PREV VALUE*/
                modelMap.put("contractNumber", contractNumber);
                modelMap.put("startDate", startDate);
                modelMap.put("endDate", endDate);

                return new ModelAndView("1_setup/FishUnitCostAdd", "model", modelMap);
            }

            start = df.parse(startDate);
            end = df.parse(endDate);

            fc.setContractNumber(contractNumber);
            fc.setContractBeginDate(start);
            fc.setContractEndDate(end);
            fc.setSupplierCode(supplierCode);
            fc.setCreatedBy(user.getUserId());
            fc.setCreatedDate(now);

        }

        /*BIND PARAM TO MODEL*/
        int size = fishes.size();
        for (int i = 0; i < size; i++) {

            String idUC = "unitCost-" + i;
            unitCost = new BigDecimal(request.getParameter(idUC));
            fc.setFishId(fishes.get(i).getId());
            fc.setUnitCost(unitCost);
            /*CHECK IF EXIST*/
            isExist = fishUnitCostDao.isExist(fc);
            if (isExist) {
                /*UPDATE*/
                System.out.println("UPDATE");
                fcId = fishUnitCostDao.findBySupplierCodeandFishCode(fc);
                fc.setId(fcId);
                fishUnitCostDao.update(fc);
            } else {
                /*INSERT*/
                System.out.println("INSERT");
                fishUnitCostDao.insert(fc);
            }

        }

        return new ModelAndView("1_setup/FishUnitCostList", "model", modelMap);
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer id = Integer.parseInt(request.getParameter("key"));

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String pcreatedBy = "";
        if (lu == null) {
            HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        } else {
            pcreatedBy = lu.getUserId();
        }
        CurrencyDao dao = DaoFactory.createCurrencyDao();
        Currency dto = dao.findByPrimaryKey(id);

        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(pcreatedBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        List<Currency> list = dao.findAll();

        HashMap m = new HashMap();

        m.put("currency", list);
        m.put("totalRows", 0);

        return new ModelAndView("1_setup/CurrencyList", "model", m);
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();

        CurrencyDao distDao = DaoFactory.createCurrencyDao();

        pw.print("{\"maxpage\": " + distDao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show"))) + ",\"data\": [");
        fces = fishUnitCostDao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (FishUnitCost fc : fces) {
            if (b) {
                pw.print(",");
            }
            pw.print("{\"1\": \"" + fc.getId() + "\", ");
            pw.print("\"2\": \"" + fc.getContractNumber() + "\", ");
            pw.print("\"3\": \"" + fc.getSupplierCode() + "\", ");
            pw.print("\"4\": \"" + fc.getContractBeginDate() + "\", ");
            pw.print("\"5\": \"" + fc.getContractEndDate() + "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]}");
    }

    public void ajaxReadDetail(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();
        String prsNumber = request.getParameter("term");

        /* DAO | Define needed dao here */
        PrsDao prsDao = DaoFactory.createPrsDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = prsDao.ajaxReadDetail(prsNumber);
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("product_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("product_name")).append("\", ");
            sb.append("\"4\": \"").append(x.get("soh")).append("\", ");
            sb.append("\"5\": \"").append(x.get("qty")).append("\", ");
            sb.append("\"6\": \"").append(x.get("uom_name")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]");
        response.getWriter().print(sb.toString());

    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        System.out.println("UPDATE");
        Integer index = Integer.parseInt(request.getParameter("key"));

        List<FishUnitCost> fishUnistCostList = new ArrayList<FishUnitCost>();
        FishSupplier fs = new FishSupplier();

        fishUnistCostList = fishUnitCostDao.findByContractNumber(fces.get(index - 1).getContractNumber());

        String dateBegin = df.format(fishUnistCostList.get(0).getContractBeginDate());
        String dateEnd = df.format(fishUnistCostList.get(0).getContractEndDate());
        String supplierCode = fishUnistCostList.get(0).getSupplierCode();

        fs = fishSupplierDao.findBySupplierCode(supplierCode);

        Map map = new HashMap();
        map.put("fces", fishUnistCostList);
        map.put("contractNumber", fces.get(index - 1).getContractNumber());
        map.put("dateBegin", dateBegin);
        map.put("dateEnd", dateEnd);
        map.put("fs", fs);
        return new ModelAndView("1_setup/FishUnitCostEdit", "model", map);
    }

    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("SAVE EDIT");
        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        Date now = new Date();

        FishUnitCost fc = new FishUnitCost();

        String contractNumber = request.getParameter("contractNumber");
        String supplierCode = request.getParameter("groupSupplierName");

        Integer fcId = 0;
        /*GET ALL FISH*/
        fishes = fishDao.findAllActiveAndNotDelete();
        BigDecimal unitCost = BigDecimal.ZERO;

        fc.setContractNumber(contractNumber);
        fc.setSupplierCode(supplierCode);

        int size = fishes.size();
        for (int i = 0; i < size; i++) {

            String idUC = "unitCost-" + i;
            unitCost = new BigDecimal(request.getParameter(idUC));

            fc.setFishId(fishes.get(i).getId());

            /*UPDATE*/
            fcId = fishUnitCostDao.findBySupplierCodeandFishCode(fc);

            fishUnitCostDao.updateUnitCost(fcId, unitCost, user.getUserId(), now);

        }

        return findByPrimaryKey(request, response);
    }
}
