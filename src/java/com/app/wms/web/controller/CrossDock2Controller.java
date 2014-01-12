/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

/**
 *
 * @gnv solution
 */
/*@Controller
@RequestMapping( {"/CrossDockPO.htm","/CrossDockSave.htm","/CrossDockPOSave.htm"})*/
public class CrossDock2Controller {
    
    /*@Autowired
    private CrossDockDao crossdockDao;
    
    private Logger logger = Logger.getLogger(CrossDock2Controller.class);
    
    /**
     * Halaman Save new CrossDock
     * 
     * @param request
     * @param response
     * @return 
     */
    /*@RequestMapping(method = RequestMethod.GET, value="/CrossDockPO.htm")
    public String po(HttpServletRequest request, HttpServletResponse response) {        
        return "4_crossdock/CrossdockPOAdd";
    }
    
    
    /**
     * Save untuk PO
     * 
     * @param request
     * @param response
     * @return 
     */
    /*@RequestMapping(method = RequestMethod.POST, value="/CrossDockPOSave.htm")
    public String save(HttpServletRequest request, HttpServletResponse response) {       
        try {
            com.app.wms.engine.db.dto.map.LoginUser lu =
                    (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
            
            String poNumber = request.getParameter("ponumber");
            
            Crossdock crossdock = new Crossdock();            
            crossdock.setReceivedate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("receivedDate") + ""));
            crossdock.setCorpId(lu.getCorpId());
            crossdock.setWhId(lu.getWhCode());
            crossdock.setCreatedby(lu.getUsername());
            crossdock.setPonumber(poNumber);
            
            String[] productcode1s = request.getParameterValues("productCode1");
            String[] qtys = request.getParameterValues("qty");
            
            List<CrossdockDetail> crossdockDetails = new ArrayList<CrossdockDetail>();
            
            for (int i = 0; i < productcode1s.length; i++) {
                CrossdockDetail crossdockDetail = new CrossdockDetail();
                crossdockDetail.setPonumber(poNumber);
                crossdockDetail.setProductcode(productcode1s[i]);
                crossdockDetail.setQty(Integer.parseInt(qtys[i]));
                crossdockDetails.add(crossdockDetail);
            }
            
            crossdockDao.save(crossdock, crossdockDetails);
            
        } catch (Exception e) {
            System.err.println(e);
        }
        return "redirect:/CrossDock.htm";
    }
    
    
    /**
     * Save Consignee
     * 
     * @param request
     * @param response
     * @return 
     */
    /*@RequestMapping(method = RequestMethod.POST, value="/CrossDockSave.htm")
    public String save1(HttpServletRequest request, HttpServletResponse response) {        

//        logger.debug("start on crossdock controller");
        try {
            com.app.wms.engine.db.dto.map.LoginUser lu =
                    (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
            
            String poNumber = request.getParameter("consignee");
            
            Crossdock crossdock = new Crossdock();
            crossdock.setConsigneenumber(poNumber);
            crossdock.setReceivedate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("receivedDate") + ""));
            crossdock.setCorpId(lu.getCorpId());
            crossdock.setWhId(lu.getWhCode());
            crossdock.setCreatedby(lu.getUsername());
            crossdock.setPonumber(poNumber);
            
            String[] productcode1s = request.getParameterValues("productcode1");
            String[] qtys = request.getParameterValues("qty");
            
            List<CrossdockDetail> crossdockDetails = new ArrayList<CrossdockDetail>();
            
            for (int i = 0; i < productcode1s.length; i++) {
                CrossdockDetail crossdockDetail = new CrossdockDetail();
                crossdockDetail.setPonumber(poNumber);
                crossdockDetail.setProductcode(productcode1s[i]);
                crossdockDetail.setQty(Integer.parseInt(qtys[i]));
                crossdockDetails.add(crossdockDetail);
            }
            
            crossdockDao.save(crossdock, crossdockDetails);
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return "redirect:/CrossDock.htm";
    }*/
    
}
