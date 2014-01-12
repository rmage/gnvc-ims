package com.app.wms.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gnv solution < gnv solutionkun at gmail dot com >
 */
public abstract class BaseJsonController {

    protected Integer rows = 10;
    protected Integer page = 1;
    protected Integer min = 1;
    protected Integer max = 10;
    
    protected String sord = "desc";
    
    public static final String PAGE = "page";
    public static final String TOTAL = "total";
    public static final String RECORDS = "records";
    public static final String ROWS = "rows";
    
    protected void process(HttpServletRequest request) {
        rows = Integer.parseInt(request.getParameter("rows"));
        page = Integer.parseInt(request.getParameter("page"));
        
        sord = request.getParameter("sord");
        
        min = page==1?0:(rows * (page-1));
        max = rows * page;
        
    }
    
    protected Integer getTotalPages(int rowNumber) {
        if(rowNumber == 0) {            
            return 0;
        }
        else {
            if (rowNumber % rows == 0) {
                return rowNumber / rows;
            } else {
                return (rowNumber / rows) + 1;
            }
        }
    }
    
    // paging
    protected List pagination(List objects) {
        if(objects.size() < max) {
            return objects.subList(min, objects.size());
        } else {
            return objects.subList(min, max);
        }
    }
            
}
