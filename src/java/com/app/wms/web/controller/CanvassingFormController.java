package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.AssignCanvassingDao;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class CanvassingFormController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        HashMap m = new HashMap();
        
        AssignCanvassingDao assignCanvassingDao = DaoFactory.createAssignCanvassingDao();
        List<Supplier> ss = assignCanvassingDao.findForCanvasingForm(lu.getUserId());
        m.put("supplier", ss);
        
        return new ModelAndView("non_fish/CFList", "model", m);
    }
    
}
