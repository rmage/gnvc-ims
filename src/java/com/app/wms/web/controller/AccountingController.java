package com.app.wms.web.controller;

//import java.io.FileNotFoundException;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.app.wms.engine.db.dao.AccountingDao;
//import com.app.wms.engine.db.dao.DepartmentDao;
//import com.app.wms.engine.db.dao.StockBalanceDao;
//import com.app.wms.engine.db.dao.StockInventoryDao;
//import com.app.wms.engine.db.dao.TsDao;
//import com.app.wms.engine.db.dao.TsDetailDao;
//import com.app.wms.engine.db.dto.Accounting;
//import com.app.wms.engine.db.dto.Department;
//import com.app.wms.engine.db.dto.StockBalance;
//import com.app.wms.engine.db.dto.StockInventory;
//import com.app.wms.engine.db.dto.Ts;
//import com.app.wms.engine.db.dto.TsDetail;
//import com.app.wms.engine.db.dto.map.LoginUser;
//import com.app.wms.engine.db.exceptions.TsDaoException;
//import com.app.wms.engine.db.factory.DaoFactory;
//import com.app.wms.hbm.bean.Vgrdetailproduct;

public class AccountingController extends ReportManagerController {
//	
//
//	
//	private Integer size = 0;
//	
//	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		try {
//	       
//	       HashMap m = null;
//	       final String mode = request.getParameter("mode");
//	       if (mode != null && mode.equals("edit")) {
//	           m = this.getModelByPrimaryKey(request);
//	           m.put("mode", "edit");
//	           return new ModelAndView("11_accounting/AccEdit", "model", m);
//	       } else {
//	           m = this.searchAndPaging(request, response);
//	           return new ModelAndView("11_accounting/AccList", "model", m);
//	       }
//
//	   	} catch (Throwable e) {
//		  e.printStackTrace();
//		  return new ModelAndView( "Error", "th", e );
//		}
//		   
//	}
//
//	private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		try {
//
//	    HashMap m = new HashMap();
//	    Integer page = null;
//	    Integer paging = null;
//        if (request.getParameter("page") != null) {
//           page = Integer.parseInt(request.getParameter("page"));
//        }
//        if (request.getParameter("paging") != null) {
//           paging = Integer.parseInt(request.getParameter("paging"));
//        }
//        if (page == null) {
//           page = 1;
//        }
//        if (paging == null) {
//           paging = 10;
//        }
//        
//	    int start = (page - 1) * paging + 1;
//	    int end = start + paging - 1;
//	
//	    Ts t = new Ts();
//	    String tsNo = request.getParameter("tsnumber");
//	    String tsDate = request.getParameter("tsdate");
//	    t.setTsnumber(tsNo);
//
//	    TsDao dao = DaoFactory.createTsDao();
//	    if(tsDate == null || tsDate.equalsIgnoreCase("")){
//	    	List<Ts> listSearchPage = dao.findAll();
//	    	m.put("transferSlip", listSearchPage);
//	    }else{
//	    	t.setTsdate(new SimpleDateFormat("dd/MM/yyyy").parse(tsDate));
//	    	List<Ts> listSearchPage = dao.findTsPaging(t,page);
//	    	m.put("transferSlip", listSearchPage);
//	    }
//	      
//	    int total = 2000; 
//	   
//	    m.put("totalRows", total);
//	    m.put("page", page);
//	    m.put("paging", paging);
//	    m.put("tsdate", tsDate);
//	    m.put("tsnumber", tsNo);
//
//	    return m;
//
//		} catch (Exception e){
//		  throw e;
//		}
//	}
//
//	private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
//	try {
//		 TsDao dao = DaoFactory.createTsDao();
//		 TsDetailDao daod = DaoFactory.createTsDetailDao();
//		 Ts dto = new Ts();
//		 TsDetail dtod = new TsDetail();
//		 
//         String mode = request.getParameter("mode");
//         if (mode != null && mode.equals("edit")) {
//        	 Integer id = Integer.parseInt(request.getParameter("id"));
//             dto = dao.findByPrimaryKey(id);
//            
//         }
//         if(dto.getTsnumber() == null){
//        	 dto.setTsnumber("");
//        	 dto.setTsdate(null);
//        	
//         }
//         else if(dto.getTsnumber() != null){
//        	 dto.setTsnumber(dto.getTsnumber());
//        	 dto.setTsdate(dto.getTsdate());
//         }
//         if(dtod.getTsnumber() == null){
//        	 dtod.setTsnumber("");
//        	 dtod.setProductcode("");
//         }
//         else if(dto.getTsnumber()!= null){
//        	 dtod.setTsnumber(dtod.getTsnumber());
//        	 dtod.setProductcode(dtod.getProductcode());
//         }
//         
//         //edit
//         HashMap m = new HashMap();
//         DepartmentDao daoDep = DaoFactory.createDepartmentDao();
//         List<Department> dropListDepartment = daoDep.findAll();
// 		 
//		 m.put("dropListDepartment", dropListDepartment);
//         m.put("dto", dto);
//         
//         return m;
//         
//		} catch (Exception e) {
//            throw e;
//        }
//	}
//	
//	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        HashMap m = this.getModelByPrimaryKey(request);
//        m = this.getModelByPrimaryKey(request);
//        m.put("mode", "create");
//        m.put("hash", new Date().getTime());
//        AccountingDao dao = DaoFactory.createAccountingDao();
//        List<Accounting> accounting = dao.findProductDetail();
//        m.put("accounting", accounting);
//        return new ModelAndView("11_accounting/AccAdd", "model", m);
//    }
//	
//	public ModelAndView save (HttpServletRequest request, HttpServletResponse response) throws Exception {
//    try {
//    		TsDao dao = DaoFactory.createTsDao();
//    		TsDetailDao daod = DaoFactory.createTsDetailDao();
//            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
//            String createdBy = "";
//            if (lu == null) {
//    			HashMap m = new HashMap();
//                String msg = "You haven't login or your session has been expired! Please do login again";
//                m.put("msg", msg);
//                return new ModelAndView("login", "model", m);
//            }else{
//            	createdBy = lu.getUserId();
//            }
//            
//            StockInventory dtoInv = new StockInventory();
//            StockInventoryDao daoInv = DaoFactory.createStockInventoryDao();
//            
//            StockBalance dtoBalance = new StockBalance();
//            StockBalanceDao daoBalance = DaoFactory.createStockBalanceDao();
//            
//            Ts ts = new Ts();
//            ts.setTsnumber(request.getParameter("tsNo"));
//            ts.setSwsnumber(request.getParameter("wsnumber"));
//            if(!"".equals(request.getParameter("tsdate"))){
//            	ts.setTsdate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("tsdate") + ""));
//            }else{
//            	ts.setTsdate(new Date());
//            }	
//            ts.setCreatedby(createdBy);
//            /*
//            if(!"".equals(request.getParameter("unitCost"))&&request.getParameter("unitCost") != null||
//               !"".equals(request.getParameter("amount"))&&request.getParameter("amount") != null){
//            	ts.setUnitCost(Integer.parseInt(request.getParameter("unitCost")));
//            	ts.setAmount(Integer.parseInt(request.getParameter("amount")));
//            }
//            */
//            ts.setIssuedby(request.getParameter("issuedBy"));
//            ts.setNotedby(request.getParameter("notedBy"));
//            ts.setApprovedby(request.getParameter("approvedBy"));
//            ts.setRemarks(request.getParameter("remarks"));
//            ts.setReceivedby(request.getParameter("receivedBy"));
//            ts.setDepartmentCode(request.getParameter("department"));
//            
//            List<TsDetail> tsDetails = new ArrayList<TsDetail>();
//            String[] productCode1s = request.getParameterValues("productCode1");
//            String[] productName1s = request.getParameterValues("productName1");
//            String[] productCategory1s = request.getParameterValues("productCategory1");
//            String[] uom1s = request.getParameterValues("uom1");
//            String[] whCode1s = request.getParameterValues("warehouse1");
//            String[] balance1s = request.getParameterValues("balance1");
//            String[] qty1s = request.getParameterValues("qty1");
//            for (int i = 0; i < productCode1s.length; i++) {
//            	TsDetail tsDetail = new TsDetail();
//            	
//            	tsDetail.setTsnumber(request.getParameter("tsNo"));
//            	tsDetail.setProductcode(productCode1s[i]);
//            	tsDetail.setProductName(productName1s[i]);
//            	tsDetail.setProductCategory(productCategory1s[i]);
//            	tsDetail.setUomName(uom1s[i]);
//            	tsDetail.setWhCode(whCode1s[i]);
//            	tsDetail.setBalance(new BigDecimal(balance1s[i]));
//            	tsDetail.setQtyrequest(new BigDecimal(qty1s[i]));
//            	
//            	daod.insert(tsDetail);
//            	
//            	//check balance
//                //Integer bInv = 0;
//                BigDecimal bInv = BigDecimal.ZERO;
//                dtoInv.setProductCode(productCode1s[i]);
//                dtoInv.setWhCode(whCode1s[i]);
//                List <StockInventory> b = daoInv.balance(dtoInv);
//        		if(b.size() > 0){
//        			for(StockInventory inv : b){
//        				bInv = (inv).getBalance();
//        			}
//        			
//        		}else{
//        			    bInv = BigDecimal.ZERO;
//        		}
//        		
//        		dtoInv.setBalance(bInv.subtract(new BigDecimal(qty1s[i])));
//                dtoInv.setStartBalance(BigDecimal.ZERO);
//                dtoInv.setIsActive("Y");
//                dtoInv.setIsDelete("N");
//                dtoInv.setCreatedBy(lu.getUserId());
//                dtoInv.setCreatedDate(new Date());
//                logger.debug(">>>> dtoInv="+dtoInv); 
//                
//                dtoBalance.setDocumentNo(generateTsNumber(size));
//                dtoBalance.setDocumentDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("tsdate") + ""));
//                dtoBalance.setProductCode(productCode1s[i]);
//                dtoBalance.setQtyOut(new BigDecimal(qty1s[i]));
//                dtoBalance.setBalance(bInv.subtract(new BigDecimal(qty1s[i])));
//                dtoBalance.setCreatedBy(lu.getUserId());
//                dtoBalance.setCreatedDate(new Date());
//                logger.debug(">>>> dtoBalance="+dtoBalance); 
//                
//                daoBalance.insert(dtoBalance);
//                
//                //check porduct dan warehouse
//                List <StockInventory> inv = daoInv.findProductandWarehouse(dtoInv);
//                if(inv.size()>0){
//                	
//                for(StockInventory stinv : inv){
//                	String productCode = (stinv).getProductCode();
//                	String warehouse  = (stinv).getWhCode();
//
//                	if(dtoInv.getWhCode().equalsIgnoreCase(warehouse) 
//                		&& dtoInv.getProductCode().equalsIgnoreCase(productCode)){
//                    	daoInv.updateBalance(dtoInv);
//                    }
//                }
//    	            
//                }else{
//            		daoInv.insert(dtoInv); 
//            	}
//            	
//            }
//
//            dao.insert(ts);
//
//            Map m = new HashMap(); 
//            //m = this.searchAndPaging(request, response);
//            List<Ts> listSearchPage = dao.findAll();
//	    	m.put("transferSlip", listSearchPage);
//            return new ModelAndView("11_accounting/AccList", "model", m);
//        } catch (Exception e) {
//        	e.printStackTrace();
//            logger.error(e, e);
//            return new ModelAndView("11_accounting/AccList");
//        }
//    
//	}
//	
//	private String generateTsNumber (Integer size) throws TsDaoException{
//		TsDao dao = DaoFactory.createTsDao();
//		List<Ts> s = dao.findAll();
//		size = s.size()+1;
//		String head = "TS";
//		String year = new SimpleDateFormat("yyyy").format(new Date());
//		String tail = ("0000000"+size).substring(("0000000"+size).length()-7);
//		
//		return head+year+tail;
//	}
//
//	@Transactional
//    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        /*
//         *  GET LOGIN USER
//         */
//        String poNo = request.getParameter("poNo");
//        HashMap model = new HashMap();
//        
//        //List<Vgrdetailproduct> listSearch	= poDao.getDetail(poNo);
//        List<Vgrdetailproduct> listSearch	= null;
//        System.out.println("poNo "+poNo);
//        
//        if(listSearch != null){
//            System.out.println("[poNo][Ajax Document] sales order no : " + poNo + " is valid");
//           
//            Map tableMap = new HashMap();
//            for (Vgrdetailproduct searchDetail : listSearch){
//            	
//            	Map returnMap = new HashMap();
//                  String po_number    = (searchDetail).getPonumber();
//                  String productCode  = (searchDetail).getProductCode();
//                  String productName  = (searchDetail).getProductName();
//                  BigDecimal quantity    	= (searchDetail).getQty();
//
//                  returnMap.put("po_number",po_number);
//                  returnMap.put("productCode",productCode);
//                  returnMap.put("productName",productName);
//                  returnMap.put("quantity",quantity);
//
//                  tableMap.put(returnMap, returnMap);
//              }
//            
//            model.put("master", listSearch);
//            model.put("tableMap", tableMap);
//            
//        } else{
//            System.out.println("[PurchaseOrderDetail][Ajax Document] purchase order no : " + poNo + " is not valid");
//        }
//
//        return new ModelAndView("2_receive/util/PurchaseOrderDetail", "model", model);
//    }
//
//  
//  public void doPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
//	
//	String salesOrderNo = request.getParameter("sonumber");
//	System.out.println("salesOrderNo ="+salesOrderNo);
//	
//	templateName = request.getParameter("templateName");
//	System.out.println("templateName ="+templateName);
//	
//	parametersKey = request.getParameter("parametersKey");
//	System.out.println("parameterKey ="+parametersKey);
//	
//	ArrayList resultList = new ArrayList();
//	resultList.add(salesOrderNo);
//	setParameterValues(resultList);
//	
//	List paramKey = new ArrayList();
//	paramKey.add(parametersKey);
//	setParameterKeys((ArrayList<String>) paramKey);
//	outputFormat = "pdf";
//	createOnlineReport();
//		
//	try{
//		printToStream(response);
//		
//	}catch(FileNotFoundException ex){
//     ex.printStackTrace();
//              
//	}catch(Exception ex){
//     ex.printStackTrace();
//	}
//		
//  }
//	
//
//
}
