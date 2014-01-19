package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.app.wms.engine.db.dao.CanvasserassignmentDao;
import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dao.PrsDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.Canvasserassignment;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;

public class CanvassingAssignmentController extends MultiActionController {

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
                return new ModelAndView("1_setup/CanvasserAssignEdit", "model", m);
            } else {
                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/CanvasserAssignList", "model", m);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView( "Error", "th", e );
        }
    }
	
    private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
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

            Canvasserassignment c = new Canvasserassignment();
            c.setPrsnumber(request.getParameter("prsnumber"));
            c.setCanvassername(request.getParameter("canvassername"));

            CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
            List<Canvasserassignment> listSearchPage = dao.findCanvasserAssignPaging(c,page);
            
            UserDao userDao = DaoFactory.createUserDao();
            for (Canvasserassignment ca : listSearchPage) {
                User u = userDao.findByPrimaryKey(ca.getCanvassername());
                ca.setCanvassername(u == null ? "- user not found -" : u.getName());
            }
            
            int total = 2000; 
            m.put("canvasserassignment", listSearchPage);
            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);

            return m;
        } catch (Exception e){
            throw e;
        }		
    }
	
    private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
            CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();		
            Canvasserassignment dto = new Canvasserassignment();

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }
         
            if (dto.getPrsnumber() == null) {
                dto.setPrsnumber("");
                dto.setCanvassername("");
            }
         
            if (dto.getPrsnumber() != null || dto.getCanvassername() != null) {
                dto.setPrsnumber(dto.getPrsnumber());
                dto.setCanvassername(dto.getCanvassername());
            }
            //edit
            UserDao daoUser = DaoFactory.createUserDao();
            List <User> dropListUser = daoUser.findRoleCanvasser();

            PrsDao daoPrs = DaoFactory.createPrsDao();
            DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
            List<Prs> dropListPrs = daoPrs.findAllNotInCanvas();
            for(Prs x : dropListPrs) {
                List<Department> d = departmentDao.findWhereDepartmentCodeEquals(x.getDepartmentName());
                x.setDepartmentName(d.isEmpty() ? "- department not found -" : d.get(0).getDepartmentName());
            }

            HashMap m = new HashMap();
            m.put("dto", dto);
            m.put("dropListUser", dropListUser);
            m.put("dropListPrs", dropListPrs);

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
			CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();		
		    List <Canvasserassignment> dto = dao.findAll();
			return new ModelAndView( "1_setup/CanvasserAssignList", "model", dto);
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String createdBy = "";
        if (lu == null) {
			HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        }else{
        	createdBy = (String)lu.getUserId();
        }
        CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
        Integer id = Integer.parseInt(request.getParameter("id"));
        Canvasserassignment dto = dao.findByPrimaryKey(id);
        if (dto != null) {
            dto.setUpdatedBy(createdBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/CanvasserAssignList", "model", m);
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

//        CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();

        return new ModelAndView( "1_setup/CanvasserAssignAdd", "model", map);
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
        boolean isCreate = true;
        String strError = "";
        Date now = new Date();
        java.lang.String mode = request.getParameter("mode");
        Canvasserassignment dto = null;
        try {
            if (mode.equalsIgnoreCase("create")) {
                isCreate = true;
            } else {
                isCreate = false;
            }
            CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
            if (isCreate) {
                dto = new Canvasserassignment();
            } else {
                Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }

            String prsnumber = request.getParameter("prsnumber");
            String canvasserid = request.getParameter("canvasserid");
            List<Canvasserassignment> tmp = dao.findWherePrsnumberEquals(prsnumber);

            if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getPrsnumber().equals(prsnumber))) {
                strError += "PRS Number already exists. Please try a different values" + AppConstant.EOL;
            }

            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            String userId = "";
            if (lu == null) {
                HashMap m = new HashMap();
                String msg = "You haven't login or your session has been expired! Please do login again";
                m.put("msg", msg);
                return new ModelAndView("login", "model", m);
            } else{
                userId = (String)lu.getUserId();
            }

            if (isCreate) {
                dto.setCreatedBy(userId);
                dto.setCreatedDate(now);
            }
            dto.setPrsnumber(prsnumber);
            dto.setCanvassername(canvasserid);
            dto.setUpdatedBy(userId);
            dto.setUpdatedDate(new java.util.Date());

            if (strError.length() > 0) {
                throw new Exception(strError);
            }

            if (isCreate) {
                dao.insert(dto);
                
                UserDao userDao = DaoFactory.createUserDao();
                User u = userDao.findByPrimaryKey(canvasserid);
                dto.setCanvassername(u.getName());
            } 

            else {
                dto.setUpdatedBy(userId);
                dto.setUpdatedDate(new java.util.Date());
                dao.update(dto.createPk(), dto);
            }

            return new ModelAndView("1_setup/CanvasserAssignView", "dto", dto);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = e.getMessage();
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);

            if (isCreate) {
                return new ModelAndView("1_setup/CanvasserAssignAdd", "model", m);
            } else {
                return new ModelAndView("1_setup/CanvasserAssignEdit", "model", m);
            }
        }  
    }
	

}
