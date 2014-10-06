/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller.json;

//import com.app.wms.engine.db.dao.spring.CrossDockDao;
import com.app.wms.web.bean.json.Alert;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @gnv solution
 */
@Controller
@RequestMapping( {"/Alert.htm"})
public class AlertJsonController {
    
//    @Autowired
//    private CrossDockDao crossdockDao;
        
    @RequestMapping(value = "/Alert.htm", method = RequestMethod.GET)
    public ModelAndView json(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
        com.app.wms.engine.db.dto.map.LoginUser lu =
                    (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
        
        List<Alert> alerts = new ArrayList<Alert>();
        List<Object[]> objectss = new ArrayList<Object[]>();
//        objectss.addAll(goodReceiveDao.getExpiredProducts(lu.getWhCode()));
        
        Alert alert = new Alert();
        StringBuilder stringBuilder = new StringBuilder("");
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        for (Object[] objects : objectss) {
//            dbo.goodreceive_detail.grnumber, 4
//            dbo.goodreceive_detail.productcode, 5
//            dbo.goodreceive_detail.expirydate, 6
//            dbo.goodreceive_detail.lotid 7
            stringBuilder.append("<br /> ").append(objects[5])
                    .append(" - ").append(objects[8]).append(", Tanggal Kadaluarsa : ").append(simpleDateFormat.format((Date)objects[6]));
//            System.out.println(objects[5]);
//            alert.setTitle("Product "+objects[5]+ " Mendekati Masa Kadaluarsa");               
//            alert.setText("GRNumber "+objects[4]+", Lotid "+objects[7]+" akan Kadaluarsa pada "
//                    +new SimpleDateFormat("dd MMMM yyyy").format((Date)objects[6]));             
            
        }
        alert.setTitle("Mendekati Masa Kadaluarsa");               
            alert.setText("Product dengan kode berikut : "+stringBuilder+ "");    
        if(!stringBuilder.toString().equals(""))
            alerts.add(alert);
        
//        List<Crossdock> crossDocks = new ArrayList<Crossdock>();        
//        crossDocks .addAll (null);
//        crossDocks .addAll (crossdockDao.getExpiredCrossdocks(lu.getWhCode()));
//                
//        for (Crossdock crossDock : crossDocks) {
//            
//            
//            String nomerCrossDock = crossDock.getConsigneenumber() != null?crossDock.getConsigneenumber():crossDock.getPonumber();
//            
//            alert.setTitle("CrossDock "+nomerCrossDock+ " Sudah 3 Hari");               
//            alert.setText("Barang mendarat tanggal "+new SimpleDateFormat("dd MMMM yyyy").format(crossDock.getReceivedate()));       
//            
//            
//        }
//        alerts.add(alert);
        
        Map map = new HashMap();
        map.put("alerts", alerts);
         

        return new ModelAndView("jsonView", map);
    }
    
}
