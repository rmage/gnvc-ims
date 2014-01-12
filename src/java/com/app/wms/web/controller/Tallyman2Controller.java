/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.web.engine.search.UserSearch;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dao.UserRoleDao;
import com.app.wms.engine.db.dao.spring.TallymanDao;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.UserRole;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Tallyman;
import com.app.wms.web.helper.StringHelper;
import com.app.wms.web.util.AppConstant;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @genevacons
 */
public class Tallyman2Controller extends MultiActionController {
    
    @Autowired private TallymanDao tallymanDao;
    
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
	    final String mode = request.getParameter("mode");
	    if (mode != null && mode.equals("edit")) {
		HashMap m = this.getModelByPrimaryKey(request);
		m.put("mode", "edit");
		return new ModelAndView("1_setup/tallyman/TallymanEdit", "model", m);
	    } else {
                
                LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
                
		HashMap m = this.searchAndPaging(request, response);
                m.put("users", tallymanDao.getTallymans(lu.getCorpId(), lu.getWhCode()));
		return new ModelAndView("1_setup/tallyman/TallymanList", "model", m);
	    }

	} catch (Throwable e) {
	    e.printStackTrace();
	    return new ModelAndView("Error", "th", e);
	}

    }

    private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
	try {
	    LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
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

	    // handle masalah search
	    UserSearch c = null;
	    if (request.getParameter("btnViewAll") != null) {
		request.getSession().setAttribute("UserSearch", null);
	    } else if (request.getParameter("btnSearch") != null) {
		System.out.println("create new search session");
		c = new UserSearch();
		c.setCode(request.getParameter("code"));
		c.setName(request.getParameter("name"));
		request.getSession().setAttribute("UserSearch", c);
	    } else if (request.getSession().getAttribute("UserSearch") != null) {
		System.out.println("use previous search session");
		c = (UserSearch) request.getSession().getAttribute("UserSearch");
	    } else {
		// no criteria
	    }

	    UserDao dao = DaoFactory.createUserDao();
//	    List<User> list = dao.findByCriteriaLimit(c, start, end);//dao.findLimit(start, end);// dao.findLimit(((page - 1) * paging) + 1, paging); //dao.findLimit(((page - 1) * paging) + 1, paging * page);

	    int total = 2000; //dao.countTotalRows(); 
//	    m.put("users", list);
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
	    LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	    //BigDecimal puserId = Utility.formatBigDecimal(request.getParameter("userId"));
	    String puserId = request.getParameter("code");
	    UserDao dao = DaoFactory.createUserDao();
	    Tallyman dto = null;

	    String mode = request.getParameter("mode");
	    if (mode != null && puserId != null && mode.equals("edit")) {
		dto = tallymanDao.get(Integer.parseInt(puserId));
	    }

	    if (dto == null) {
		dto = new Tallyman();
		dto.setCode("");
		dto.setName("");
		dto.setTallyname("");
		dto.setJobfunction("");
		dto.setWhCode("");		
	    }

//	    UserRoleDao daoRole = DaoFactory.createUserRoleDao();
//	    List<UserRole> list = daoRole.findAll();

	    HashMap m = new HashMap();
	    m.put("dto", dto);
//	    m.put("roles", list);

	    return m;

	} catch (Exception e) {
	    throw e;
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
	HashMap m = this.getModelByPrimaryKey(request);
	m.put("mode", "create");
	return new ModelAndView("1_setup/tallyman/TallymanAdd", "model", m);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
	boolean isCreate = true;
	String strError = "";
	Date now = new Date();
	String mode = request.getParameter("mode");
	Tallyman dto = null;
	try {
	    if (mode.equalsIgnoreCase("create")) {
		isCreate = true;
	    } else {
		isCreate = false;
	    }

	    //BigDecimal puserId = Utility.formatBigDecimal(request.getParameter("userId"));
	    String puserId = request.getParameter("userId");
	    String id = request.getParameter("id");
//	    UserDao dao = DaoFactory.createUserDao();
	    if (isCreate) {
		dto = new Tallyman();
	    } else {
		dto = tallymanDao.get(Integer.parseInt(id));
	    }

	    String pcode = request.getParameter("code");
	    if (pcode.trim().isEmpty()) {
		strError += "ID Cannot be Empty!" + AppConstant.EOL;
	    }
//	    List<User> tmpUser = dao.findWhereCodeEquals(pcode);
//	    if ((isCreate && tmpUser != null && tmpUser.size() > 0) || (!isCreate && tmpUser != null && tmpUser.size() > 0 && !tmpUser.get(0).getUserId().equals(puserId))) {
//		strError += "ID Already Existed!" + AppConstant.EOL;
//	    }

	    String pname = request.getParameter("name");
	    if (pname.trim().isEmpty()) {
		strError += "Name Cannot be Empty!" + AppConstant.EOL;
	    }

//	    String tallyname = request.getParameter("tallyname");
//	    if (tallyname.trim().isEmpty()) {
//		strError += "Tallyname Cannot be Empty!" + AppConstant.EOL;
//	    }

//	    String ppassword = request.getParameter("password");
//	    String ppasswordVer = request.getParameter("passwordVer");
//	    if (ppassword.trim().isEmpty()) {
//		strError += "Password Cannot be Empty!" + AppConstant.EOL;
//	    } else if (StringHelper.trim(ppassword).length() < 3) {
//		strError += "Password Must be 3 character or more!" + AppConstant.EOL;
//	    } else if (!ppasswordVer.equals(ppassword)) {
//		strError += "Password and Verification do not match!" + AppConstant.EOL;
//	    }

	    String jobfunction = request.getParameter("jobfunction");
	    if (jobfunction.trim().isEmpty()) {
		strError += "Jobfunction Cannot be Empty!" + AppConstant.EOL;
	    }
            
//	    String whcode = request.getParameter("whcode");
//	    if (whcode.trim().isEmpty()) {
//		strError += "WHcode Cannot be Empty!" + AppConstant.EOL;
//	    }

	    String proleCode = request.getParameter("roleCode");
	    String pisActive = request.getParameter("isActive");

	    LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	    String userId = new String(lu.getUserId());

	    if (isCreate) {
//		dto.setCreatedBy(userId);
		dto.setCreatedDate(now);
	    }

	    dto.setCode(pcode);
	    dto.setName(pname);
	    dto.setTallyname("");
	    dto.setJobfunction(jobfunction);
	    dto.setWhCode(lu.getWhCode());
	    dto.setUpdatedDate(new Date());
	    dto.setCreatedDate(new Date());
	    dto.setCorpId(lu.getCorpId());
//	    dto.setUsername(tallyname);
//	    dto.setPassword(ppassword);
//	    dto.setRoleCode(proleCode);
//	    dto.setEmail(whcode);
//	    dto.setIsActive(pisActive);

	    if (strError.length() > 0) {
		throw new Exception(strError);
	    }

	    if (isCreate) {
//	    BigDecimal nextUserId =	dao.getNextUserId();
//	    	
//		dto.setUserId(nextUserId.toString());
//		dao.insert(dto);
                
                tallymanDao.save(dto);
                
	    } else {
//		dto.setUpdatedBy(userId);
//		dto.setUpdatedDate(now);
//		dao.update(dto.createPk(), dto);
                tallymanDao.update(dto);
	    }

	    return new ModelAndView("1_setup/tallyman/TallymanView", "dto", dto);

	} catch (org.springframework.dao.DataIntegrityViolationException e) {
	    String errorMsg = "Unique Key Constraint [UserId]!" + AppConstant.EOL;
	    HashMap m = this.getModelByPrimaryKey(request);
	    m.put("dto", dto);
	    m.put("mode", mode);
	    m.put("msg", errorMsg);
	    System.out.println(errorMsg);
	    if (isCreate) {
		return new ModelAndView("1_setup/tallyman/TallymanAdd", "model", m);
	    } else {
		return new ModelAndView("1_setup/tallyman/TallyEdit", "model", m);
	    }
	} catch (Exception e) {
	    String errorMsg = e.getMessage();
	    HashMap m = this.getModelByPrimaryKey(request);
            e.printStackTrace();
	    m.put("dto", dto);
	    m.put("mode", mode);
	    m.put("msg", errorMsg);
	    System.out.println(errorMsg);
	    if (isCreate) {
		return new ModelAndView("1_setup/tallyman/TallymanAdd", "model", m);
	    } else {
		return new ModelAndView("1_setup/tallyman/TallyEdit", "model", m);
	    }
	}

    }

    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	String userId = new String(lu.getUserId());

	String puserId = request.getParameter("code");

	Tallyman dto = tallymanDao.get(Integer.parseInt(puserId));
	
	if (dto != null) {
	    dto.setIsActive('N');
//	    dto.setUpdatedBy(userId);
	    dto.setUpdatedDate(new Date());
	    
	}
	HashMap m = this.searchAndPaging(request, response);
	return new ModelAndView("1_setup/tallyman/UserList", "model", m);
    }
}
