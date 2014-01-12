/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @gnv solution
 */
public class StockTake2Controller extends ReportManagerController {
    
    @Override
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("Error");
    }
    
    public void doPrint(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

           String salesOrderNo = request.getParameter("sonumber");
           System.out.println("salesOrderNo ="+salesOrderNo);

           templateName = request.getParameter("templateName");
           System.out.println("templateName ="+templateName);

           parametersKey = request.getParameter("parametersKey");
           System.out.println("parameterKey ="+parametersKey);

           ArrayList resultList = new ArrayList();
           resultList.add(salesOrderNo);
           setParameterValues(resultList);

           List paramKey = new ArrayList();
           paramKey.add(parametersKey);
           setParameterKeys((ArrayList<String>) paramKey);
           outputFormat = "pdf";
                   createOnlineReport();

                 try{
                           printToStream(response);

                }catch(FileNotFoundException ex){
   //			Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                       ex.printStackTrace();

                }catch(Exception ex){
   //			Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
           }

   }
}
