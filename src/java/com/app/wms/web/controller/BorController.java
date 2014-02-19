package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.BorDao;
import com.app.wms.engine.db.dao.BorDtlDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.Bor;
import com.app.wms.engine.db.dto.BorDtl;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class BorController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        BorDao borDao = DaoFactory.createBorDao();

        /* TRANSACTION | Something complex here */
        List<Bor> bs = borDao.findAll();
        m.put("b", bs);
        m.put("user", lu);
        
        return new ModelAndView("finish_goods/BORList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */

        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("finish_goods/BORAdd", "model", m);
        
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) 
        throws ParseException {
        
        /* DATA | get initial value */
        String[] master = request.getParameter("master").split(":", -1);
        String[] details = request.getParameterValues("detail");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        /* DAO | Define needed dao here */
        BorDao borDao = DaoFactory.createBorDao();
        BorDtlDao borDtlDao = DaoFactory.createBorDtlDao();

        /* TRANSACTION | Something complex here */
        // insert master bor
        Bor b = new Bor();
        b.setBorCode(master[0]);
        b.setBorDate(new SimpleDateFormat("dd/MM/yyyy").parse(master[1]));
        b.setCreatedBy(lu.getUserId());
        b.setCreatedDate(new Date());
        borDao.insert(b);
        
        // insert detail bor
        for(int i = 1; i < 5; i++) {

            int j = 0;
            String[] data = new String[24];
            String[] detail = details[0].split(":", -1); 
            if(detail[i].isEmpty())
                break;
            
            for(String x : details) {
                detail = x.split(":", -1);
                data[j] = detail[i]; j++;
            }
            
            BorDtl bd = new BorDtl();
            bd.setBorCode(b.getBorCode());
            bd.setBorPackStyle(data[0]);
            bd.setBorCanSize(data[1]);
            bd.setBorQty(new BigDecimal(data[2].isEmpty() ? "0" : data[2]));
            bd.setBorCs(new BigDecimal(data[3].isEmpty() ? "0" : data[3]));
            bd.setBorCnfPrice(new BigDecimal(data[4].isEmpty() ? "0" : data[4]));
            bd.setBorCommission(data[5]);
            bd.setBorBuyer(data[6]);
            bd.setBorBrand(data[7]);
            bd.setBorShipmentDate(data[8]);
            bd.setBorDestinationPort(data[9]);
            bd.setBorPoNumber(data[10]);
            bd.setBorODpw(new BigDecimal(data[11].isEmpty() ? "0" : data[11]));
            bd.setBorOFt(data[12]);
            bd.setBorOPt(data[13]);
            bd.setBorOGf(data[14]);
            bd.setBorOCcm(data[15]);
            bd.setBorOOm(data[16]);
            bd.setBorOGmo(data[17]);
            bd.setBorOEc(data[18]);
            bd.setBorOPf(data[19]);
            bd.setBorOOwr(data[20]);
            bd.setBorONol(data[21]);
            bd.setBorOInfo(data[22]);
            bd.setCreatedBy(lu.getUserId());
            bd.setCreatedDate(new Date());
            borDtlDao.insert(bd);
            
        }
        
        return new ModelAndView("redirect:Bor.htm");
        
    }
    
    public ModelAndView approval(HttpServletRequest request, HttpServletResponse response) 
        throws UserDaoException {
        
        /* DATA | get initial value */
        String mode = request.getParameter("mode");
        String borCode = request.getParameter("key");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        BorDao borDao = DaoFactory.createBorDao();
        UserDao userDao = DaoFactory.createUserDao();

        /* TRANSACTION | Something complex here */
        // get user
        User u = userDao.findByPrimaryKey(lu.getUserId());
        
        // set bor preparition to update
        Bor b = new Bor();
        b.setBorCode(borCode);
        if(mode.equals("p")) {
            b.setPreparedBy(u.getName());
            b.setPreparedDate(new Date());
        } else if(mode.equals("r")) {
            b.setReviewedBy(u.getName());
            b.setReviewedDate(new Date());
        }
        b.setUpdatedBy(lu.getUserId());
        b.setUpdatedDate(new Date());
        borDao.approval(b, mode);
        
        return new ModelAndView("redirect:Bor.htm");
        
    }

}
