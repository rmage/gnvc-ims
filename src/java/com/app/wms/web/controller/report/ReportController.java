/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller.report;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.web.controller.ReportManagerController;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @gnv solution
 */
public class ReportController extends ReportManagerController {

    private Logger logger1 = Logger.getLogger(this.getClass());

    public ReportController() {
        parametersKey = "P_DOC_NO";
    }

    @Override
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public void print(HttpServletRequest request, HttpServletResponse response) {

        templateName = request.getParameter("templateName");

        ArrayList resultList = new ArrayList();
        if(!templateName.equals("rptStockOnHand")) {
            resultList.add(((LoginUser) request.getSession().getAttribute("user")).getWhCode());
        } else {
            resultList.add(((LoginUser) request.getSession().getAttribute("user")).getWhCode()
                    +"-"+((LoginUser) request.getSession().getAttribute("user")).getCorpId());
        }                
        setParameterValues(resultList);

        List paramKey = new ArrayList();
        paramKey.add(parametersKey);
        setParameterKeys((ArrayList<String>) paramKey);
        
        String myOutputFormat = request.getParameter("outputFormat");
        
        if(myOutputFormat == null)
            outputFormat = "pdf";
        else if(myOutputFormat.equals( "xls" )) //excel output
            outputFormat = "xls";
        createOnlineReport();

        try {
            printToStream(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger1.error(ex, ex);
        }

    }
}
