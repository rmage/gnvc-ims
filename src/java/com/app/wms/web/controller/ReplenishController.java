package com.app.wms.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.web.engine.search.ReplenishSearch;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.PutawayDetailDao;
import com.app.wms.engine.db.dao.ReplenishDetailDao;
import com.app.wms.engine.db.dao.ReplenishmentDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.ReplenishDetail;
import com.app.wms.engine.db.dto.Replenishment;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.engine.util.ctrlIDGenerator;
import com.app.wms.web.util.AppConstant;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReplenishController extends ReportManagerController {
	private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
            java.lang.String replenishCode = request.getParameter("ReplenishCode");
            //ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
            //Replenish dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
            //    dto = dao.findByPrimaryKey(replenishCode);
            }

//            if (dto == null) {
//                dto = new Replenish();
//                dto.setReplenishCode("");
//                dto.setName("");
//                dto.setIsActive("Y");
//            }

            HashMap m = new HashMap();
       //     m.put("dto", dto);

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
	public ModelAndView findAll(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			
			ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		
			List<Replenishment> dto = dao.findAll();
		
			return new ModelAndView( "5_replenish/ReplenishList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'findWhereReplenishCodeEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereReplenishCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String preplenishCode = request.getParameter("replenishCode");
		
			// create the DAO class
			ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		
			// execute the finder
			//List<Replenishment> dto = dao.findWhereReplenishCodeEquals(preplenishCode);
			List<Replenishment> dto = null;
			return new ModelAndView( "5_replenish/ReplenishList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
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
	public ModelAndView findWhereNameEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String pname = request.getParameter("name");
		
			// create the DAO class
			ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		
			// execute the finder
			//List<Replenishment> dto = dao.findWhereNameEquals(pname);
			List<Replenishment> dto = null;
		
			return new ModelAndView( "5_replenish/ReplenishList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
            try {
                HashMap m;
                m = this.searchAndPaging(request, response);
                
                if(request.getSession().getAttribute("msg") != null) {
                    m.put("msg", request.getSession().getAttribute("msg"));
                    request.getSession().removeAttribute("msg");
                }
                
                return new ModelAndView("5_replenish/ReplenishList", "model", m);
            } catch (Throwable e) {
                e.printStackTrace();
                return new ModelAndView( "Error", "th", e );
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

                ReplenishSearch c = null;
                if(request.getParameter("btnSearch") != null) {
                    String replenishNo = request.getParameter("replenishNo");
                    String replenishDate = request.getParameter("replenishDate");
                    if(!replenishNo.isEmpty() || !replenishDate.isEmpty()) {
                        c = new ReplenishSearch();
                        
                        if(!replenishNo.isEmpty())
                            c.setReplenishNo(replenishNo);
                        
                        if(!replenishDate.isEmpty())
                            c.setReplenishDate(new SimpleDateFormat("dd/MM/yyyy").parse(replenishDate));
                        
                        request.getSession().setAttribute("ReplenishSearch", c);
                    } else {
                        request.getSession().removeAttribute("ReplenishSearch");
                    }
                } else {
                    c = (ReplenishSearch) request.getSession().getAttribute("ReplenishSearch");
                }

                ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
                List<Replenishment> list = dao.findByCriteriaLimit(c, start, end);

                int total = 2000; 
                m.put("replenish", list);
                m.put("totalRows", total);
                m.put("page", page);
                m.put("paging", paging);

                return m;

            } catch (Exception e) {
                throw e;
            }
        }
        
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
            HashMap m = new HashMap();

            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            m.put("user", lu);

            return new ModelAndView( "5_replenish/ReplenishAdd", "model", m);
	}
        
        public ModelAndView cancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
            try {
                String replenishNo = request.getParameter("replenishNo");
                
                ReplenishmentDao replenishmentDao = DaoFactory.createReplenishmentDao();
                replenishmentDao.statusDocument(replenishNo, "0");
                
                request.getSession().setAttribute("msg", "Replenish No : " + replenishNo + " has been canceled!");
                
                return new ModelAndView("redirect:Replenish.htm");
            } catch(Exception e) {
                e.printStackTrace();
                return new ModelAndView("Error", "th", e);
            }
        }
        
        public ModelAndView approve(HttpServletRequest request, HttpServletResponse response) throws Exception {
            try {
                String replenishNo = request.getParameter("replenishNo");
                String type = request.getParameter("type");
                
                LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
                
                /* cut quantity and validate it */
                ReplenishDetailDao replenishDetailDao = DaoFactory.createReplenishDetailDao();
                List<ReplenishDetail> rds = replenishDetailDao.findWhereReplenishNoEquals(replenishNo);
                
                PutawayDetailDao putawayDetailDao = DaoFactory.createPutawayDetailDao();
                for(int i = 0; i < rds.size(); i++) {
                    ReplenishDetail rd = rds.get(i);
                    
                    /* (-) take quantity */
                    PutawayDetail pd = new PutawayDetail();
                    if(type.equals("-2"))
                        pd = putawayDetailDao.findExactLocationCodeC(rd.getFromLocation(), rd.getUserId(), rd.getCorpId());
                    else
                        pd = putawayDetailDao.findExactLocationCode(rd.getFromLocation(), rd.getWhCode(), rd.getCorpId());
                    pd.setBalance(pd.getBalance() - rd.getQtyReplenish());
                    putawayDetailDao.updateBalance1(pd);
                    
                    /* (+) add quantity */
                    pd = putawayDetailDao.findExactLocationCode(rd.getToLocation(), rd.getWhCode(), rd.getCorpId());
                    pd.setBalance(pd.getBalance() + rd.getQtyReplenish());
                    putawayDetailDao.updateBalance1(pd);
                    
                    rd.setConfirmedBy(lu.getUserId());
                }
                replenishDetailDao.confirmDocument(replenishNo, lu.getUserId());
                
                /* update status to positif value = approved */
                ReplenishmentDao replenishmentDao = DaoFactory.createReplenishmentDao();
                replenishmentDao.statusDocument(replenishNo, String.valueOf(Integer.parseInt(type) * -1));
                
                request.getSession().setAttribute("msg", "Replenish No : " + replenishNo + " has been approved!");
                
                return new ModelAndView("redirect:Replenish.htm");
            } catch(Exception e) {
                e.printStackTrace();
                return new ModelAndView("Error", "th", e);
            }
        }
        
        public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
            String replenishNo = request.getParameter("replenishNo");
            HashMap m = new HashMap();
            
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            m.put("login", lu);
            
            ReplenishmentDao replenishmentDao = DaoFactory.createReplenishmentDao();
            Replenishment r = replenishmentDao.findByPrimaryKey(replenishNo);
            m.put("header", r);
            
            UserDao userDao = DaoFactory.createUserDao();
            User u = userDao.findByPrimaryKey(r.getCreatedBy());
            m.put("user", u);
            
            ReplenishDetailDao replenishDetailDao = DaoFactory.createReplenishDetailDao();
            List<ReplenishDetail> rds = replenishDetailDao.findWhereReplenishNoEquals(replenishNo);
            m.put("detail", rds);

            ProductDao productDao = DaoFactory.createProductDao();
            List<Product> ps = new ArrayList<Product>();
            for(int i = 0; i < rds.size(); i++) {
                ps.add(productDao.findByPrimaryKey(rds.get(i).getProductId()));
            }
            m.put("product", ps);
            
            return new ModelAndView( "5_replenish/ReplenishView", "model", m);
	}
        
        /* added by FYA : 14 May 2013 */
        public ModelAndView json(HttpServletRequest request, HttpServletResponse response) throws Exception {
            try {
                String type = request.getParameter("type");
                Integer rows = Integer.parseInt(request.getParameter("rows"));
                Integer page = Integer.parseInt(request.getParameter("page"));
                LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
                
                HashMap model = new HashMap();
                
                List<PutawayDetail> list = new ArrayList<PutawayDetail>();
                PutawayDetailDao putawayDetailDao = DaoFactory.createPutawayDetailDao();
                if(type.equals("fromLocation")) {
                    list = putawayDetailDao.findForReplenishFrom(lu.getWhCode(), 
                        request.getParameter("sidx"), request.getParameter("sord"));
                } else if(type.equals("toLocation")) {
                    if(request.getParameter("status") == null)
                        list = putawayDetailDao.findForReplenishTo(lu.getWhCode(), request.getParameter("not"),
                            request.getParameter("product"), request.getParameter("sidx"), request.getParameter("sord"));
                    else
                        list = putawayDetailDao.findForReplenishToX(lu.getWhCode(), lu.getCorpId(),
                            request.getParameter("product"), request.getParameter("sidx"), request.getParameter("sord"));
                } 
                
                Integer size = list.size();
                model.put("records", size);
                model.put("total", Math.ceil((double)list.size()/rows));
                model.put("rows", list.subList((page-1)*rows, (page*rows) > size ? size : (page*rows)));
                model.put("page", page);
                
                return new ModelAndView("jsonView", model);
            } catch(Exception e) {
                e.printStackTrace();
                return new ModelAndView("Error", "th", e);
            }
        }
        
        /* added by FYA : 22 May 2013 */
        public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
            try {
                String[] from = request.getParameterValues("from");
                String[] to = request.getParameterValues("to");
                String[] qty = request.getParameterValues("qty");
                String[] wh = request.getParameterValues("wh");
                
                LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
                
                ctrlIDGenerator iDGenerator = new ctrlIDGenerator();
                ReplenishmentDao replenishmentDao = DaoFactory.createReplenishmentDao();
                
                /* insert master replenish */
                Replenishment r = new Replenishment();
                r.setReplenishNo(iDGenerator.getIDReplenish());
                r.setReplenishDate(new Date());
                r.setStatusApp(request.getParameter("type"));
                r.setAppDate(new Date());
                r.setCreatedBy(lu.getUserId());
                r.setCreatedDate(new Date());
                replenishmentDao.insert(r);
                    
                ReplenishDetailDao replenishDetailDao = DaoFactory.createReplenishDetailDao();
                for(int i = 0; i < from.length; i++) {
                    /* insert detail replenish */
                    ReplenishDetail rd = new ReplenishDetail();
                    rd.setReplenishNo(r.getReplenishNo());
                    rd.setFromLocation(from[i]);
                    rd.setToLocation(to[i]);
                    rd.setQtyReplenish(Integer.parseInt(qty[i]));
                    rd.setConfirmedBy("-1");
                    rd.setReceivedDate(new Date());
                    rd.setUserId(lu.getUserId());
                    rd.setCorpId(lu.getCorpId());
                    rd.setWhCode(wh == null ? lu.getWhCode() : wh[i]);
                    replenishDetailDao.insert(rd);
                }
                
                response.getWriter().print(r.getReplenishNo());
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public void doPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
            String replenishNo = request.getParameter("replenishNo");
            System.out.println("replenishNo =" + replenishNo);

            templateName = request.getParameter("templateName");
            System.out.println("templateName =" + templateName);

            parametersKey = request.getParameter("parametersKey");
            System.out.println("parameterKey =" + parametersKey);

            ArrayList resultList = new ArrayList();
            resultList.add(replenishNo);
            setParameterValues(resultList);

            List paramKey = new ArrayList();
            paramKey.add(parametersKey);
            setParameterKeys((ArrayList<String>) paramKey);
            outputFormat = "pdf";
            createOnlineReport();

            try {
                printToStream(response);
            } catch(FileNotFoundException ex){
                Logger.getLogger(ReplenishController.class.getName()).log(Level.SEVERE, null, ex);
            } catch(IOException ex){
                Logger.getLogger(ReplenishController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

	/**
	 * Method 'save'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
//	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//
//      boolean isCreate = true;
//      String strError = "";
//      java.lang.String mode = request.getParameter("mode");
//      try {
//          if (mode.equalsIgnoreCase("create")) {
//              isCreate = true;
//          } else {
//              isCreate = false;
//          }
//
//          java.lang.String replenishCode = request.getParameter("replenishCode");
//          if (replenishCode.trim().isEmpty()) {
//              strError += "Replenish Code Cannot be Empty!" + AppConstant.EOL;
//          }
//
//          ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
//          Replenishment dto = null;
//          if (isCreate) {
//              dto = new Replenishment();
//          } else {
//              dto = dao.findByPrimaryKey(replenishCode);
//          }
//
//          java.lang.String pname = request.getParameter("name");
//          if (pname.trim().isEmpty()) {
//              strError += "Name Cannot be Empty!" + AppConstant.EOL;
//          }
//
//          java.lang.String pisActive = request.getParameter("isActive");
//          LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
//          java.math.BigDecimal userId = new BigDecimal(lu.getUserId());
//
//          if (strError.length() > 0) {
//              throw new Exception(strError);
//          }
//
//          if (isCreate) {
// //             dto.setCreatedBy(userId);
//  //            dto.setCreatedDate(new java.util.Date());
//          }
//
////          dto.setReplenishCode(replenishCode);
////          dto.setName(pname);
////          dto.setIsActive(pisActive);
//
//          if (isCreate) {
//              dao.insert(dto);
//          } else {
////              dto.setUpdatedBy(userId);
//  //            dto.setUpdatedDate(new java.util.Date());
//              dao.update(dto.createPk(), dto);
//          }
//
//          return new ModelAndView("5_replenish/ReplenishView", "dto", dto);
//
//      } catch (org.springframework.dao.DataIntegrityViolationException e) {
//          String errorMsg = "Unique Key Constraint [Code, Name]!" + AppConstant.EOL;
//          HashMap m = this.getModelByPrimaryKey(request);
//          m.put("mode", mode);
//          m.put("msg", errorMsg);
//          System.out.println(errorMsg);
//          if (isCreate) {
//              return new ModelAndView("5_replenish/ReplenishAdd", "model", m);
//          } else {
//              return new ModelAndView("5_replenish/ReplenishEdit", "model", m);
//          }
//      } catch (Exception e) {
//          String errorMsg = e.getMessage();
//          HashMap m = this.getModelByPrimaryKey(request);
//          m.put("mode", mode);
//          m.put("msg", errorMsg);
//          System.out.println(errorMsg);
//          if (isCreate) {
//              return new ModelAndView("5_replenish/ReplenishAdd", "model", m);
//          } else {
//              return new ModelAndView("5_replenish/ReplenishEdit", "model", m);
//          }
//      }
//
//  }



}
