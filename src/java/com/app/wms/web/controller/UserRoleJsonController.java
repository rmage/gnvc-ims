package com.app.wms.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

@Controller
@RequestMapping({"/userrolejson.htm"})
public class UserRoleJsonController extends BaseJsonController {
	
	@RequestMapping(value="/userrolejson.htm", method = RequestMethod.GET)
    public ModelAndView user(HttpServletRequest request, HttpServletResponse response) throws UserDaoException {        

		User u = new User();
		process(request);
		UserDao dao = DaoFactory.createUserDao();
		List<User> user = dao.findUserRoleAppRange();
		
		String roleCode = "";
		String userName = "";
		
		List result = new ArrayList();
		for(User valueSearch : user){
			Map returnMap = new HashMap();
			roleCode = valueSearch.getRoleCode();
			userName = valueSearch.getUsername();
			returnMap.put("rolecode", roleCode);
			returnMap.put("username", userName);
			result.add(returnMap);
		}
		
		if(sord.equalsIgnoreCase("asc"))
            Collections.reverse(result);
        
        Map model = new HashMap();
        model.put(PAGE, page);
        model.put(TOTAL, getTotalPages(result.size()));
        model.put(RECORDS, result.size());                       
        model.put(ROWS, pagination(result));
		
        return new ModelAndView("jsonView", model);
    }

}