/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.CorporateDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.EncryptionUtility;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author programmer
 */
@Transactional
public class ChangePwdController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return changePwd(request, response);
    }

    public ModelAndView changePwd(HttpServletRequest request, HttpServletResponse response) throws Exception {

//        System.err.print("entry changePwd");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        HashMap m = new HashMap();
        if (lu == null) {
            m.put("msg", "You must login to change your password");
            return new ModelAndView("login", "model", m);
        }


        m.put("dto", lu);

        return new ModelAndView("changePwd", "model", m);


    }
    
    @Transactional
    public ModelAndView doChangePwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        HashMap m = new HashMap();
        if (lu == null) {
            m.put("msg", "You must login to change your password");
            return new ModelAndView("login", "model", m);
        }
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");

        if (!EncryptionUtility.getEncrypted(oldPwd).equals((lu.getPassword()))) {
            m.put("msg", "Old Password does not match");
            m.put("dto", lu);
            return new ModelAndView("changePwd", "model", m);
        }

        User u = null;
        UserDao dao = DaoFactory.createUserDao();

        u = dao.findByPrimaryKey(lu.getUserId());
        u.setPassword(newPwd);

        dao.update(u.createPk(), u);
        
        m.put("msg", "Change password success, please login again");
        return new ModelAndView("login", "model", m);

    }
}
