package com.app.wms.web.controller;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jett.jdbc.JDBCExecutor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.util.ctrlKoneksiDB;
import com.report.test.ReportFactory;
import com.report.test.ReportFactory.Report;

@SuppressWarnings({"rawtypes","unchecked"})
public class GeneralReportController extends MultiActionController {
	public static final Map<Report, Object> ListMap = new HashMap<Report, Object>();
	public static final Map<Report, PostProcess> PostProcess = new HashMap<Report, PostProcess>();
	
	static {		
		ListMap.put(Report.RPDailyProduction, null);
		ListMap.put(Report.FGBOR, null);
		ListMap.put(Report.FGBadStockReport, null);
		ListMap.put(Report.FGTunaVayaReport, null);
		ListMap.put(Report.FGOFAL, null);
		
		ListMap.put(Report.FFrozenFishStock, new String[]{
			"DECLARE @sDate DATETIME " +
			"SET @sDate = ? " +
			"DECLARE @eDate DATETIME " +
			"SET @eDate = ? " +
			"SELECT f.code, SUM(fb.balance) rqty, SUM(fwd.total_weight) wqty, SUM(frd.good_weight) eqty " +
			"FROM inventory..fish f " +
			"LEFT JOIN inventory..fish_balance fb ON f.id = fb.fish_id " +
			"LEFT JOIN inventory..fish_ws_detail fwd ON f.id = fwd.fish_id " +
			"LEFT JOIN inventory..fish_rr_detail frd ON f.id = fwd.fish_id " +
			"WHERE  fb.created_date >= @sDate AND fb.created_date <= @eDate OR " +
			"fwd.created_date >= @sDate AND fwd.created_date <= @eDate OR " +
			"frd.created_date >= @sDate AND frd.created_date <= @eDate " +
			"GROUP BY f.code " +
			"ORDER BY f.code",
			
			"SELECT fv.name, SUM(fb.balance) qty " +
			"FROM inventory..fish_balance fb " +
			"LEFT JOIN inventory..fish_vessel fv ON fb.vessel_id = fv.id " +
			"WHERE fb.created_date >= ? AND fb.created_date <= ? " +
			"GROUP BY fv.name"
		});
		
		ListMap.put(Report.IMRR, 
			"select * " +
			"from goodreceive gr " +
			"LEFT JOIN goodreceive_detail grd ON gr.grnumber = grd.grnumber " +
			"LEFT JOIN product p ON p.product_code = grd.productcode " +
			"LEFT JOIN department d ON d.department_code = gr.department");
		
		ListMap.put(Report.FSpoilagereport, 
			"SELECT catcher_no, f.code, cooked_weight, raw_weight, total_processed, reason, batch_no, date_shift, time_shift, supplier_name " +
			"FROM inventory..fish_spoilage fs " +
			"LEFT JOIN inventory..fish f ON fs.fish_id = f.id " +
			"LEFT JOIN inventory..fish_vessel fv ON fv.id = fs.vessel_id " +
			"LEFT JOIN inventory..supplier s ON fv.supplier_id = s.id " +
			"WHERE fv.id = ? AND fs.date_shift = ?");
		ListMap.put(Report.FSummaryWSSlip, 
			"select ws.ws_no, s.name as supplier_name, v.name as boat_name, fs.raw_weight as spoilage, " +
			"v.batch_no, ws.date_shift, ws.time_shift, f.code as type, wsd.total_weight as data " +
			"from inventory..fish_ws ws " +
			"left join inventory..fish_ws_detail wsd on wsd.ws_id = ws.id " +
			"left join inventory..fish f on wsd.fish_id = f.id " +
			"left join inventory..fish_vessel v on ws.vessel_id = v.id " +
			"left join inventory..fish_supplier s on s.id = v.supplier_id " +
			"left join inventory..fish_spoilage fs on v.id = fs.vessel_id " +
			"where ws.vessel_id = ? AND ws.ws_type_id IN (?)");
		
		ListMap.put(Report.FGEDS, 
			"SELECT * " +
			"FROM dbo.eds eds, dbo.eds_detail edsd " +
			"WHERE eds.edsnumber = edsd.edsnumber");
		
		ListMap.put(Report.FMDR, "SELECT * " +
			"FROM dbo.dr dr, dbo.dr_detail drd " +
			"WHERE dr.drnumber = drd.drnumber");
		
		ListMap.put(Report.PPrsNotyetPO, 
			"SELECT * " +
			"FROM dbo.prs prs, dbo.prs_detail prsd, dbo.po po " +
			"WHERE prs.prsnumber = prsd.prsnumber AND po.prsnumber = prs.prsnumber"
		);
		
		ListMap.put(Report.PPoNotyetDeliveredDP, 
			"SELECT * " +
			"FROM inventory..po po " +
			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name"+
			"WHERE pod.payment = 'DP'"
		);
		
		ListMap.put(Report.PPoNotyetDeliveredCash, 
			"SELECT * " +
			"FROM inventory..po po " +
			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name"+
			"WHERE pod.payment = 'CASH'"
		);
		
		ListMap.put(Report.PPoNotyetDeliveredCredit, 
			"SELECT * " +
			"FROM inventory..po po " +
			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name "+
			"WHERE pod.payment = 'CREDIT'"
		);
		
		ListMap.put(Report.PPoRegisterPerPeriode, 
			"SELECT * " +
			"FROM inventory..po po " +
			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name " 
		);
		
		ListMap.put(Report.PPoIssuedPerSupplier, 
			"SELECT * " +
			"FROM inventory..po po " +
			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name"
		);
					
		ListMap.put(Report.PPoIssuedPerItem, 
			"SELECT * " +
			"FROM inventory..po po " +
			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
			"LEFT JOIN inventory..canvassing_detail cvd ON cv.prsnumber = cvd.prsnumber " +
			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name"
		);
		
//		ListMap.put(Report.PPoForm, 
//			"SELECT * " +
//			"FROM inventory..po po " +
//			"LEFT JOIN inventory..po_detail pod ON po.ponumber = pod.ponumber " +
//			"LEFT JOIN inventory..supplier su ON po.supplier_name = su.supplier_name " +
//			"LEFT JOIN inventory..product p ON p.product_code = pod.productcode " +
//			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name");
		
		ListMap.put(Report.IMStockCardperCategory, 
				"select * " +
				"from product p " +
				"inner join product_category pc " +
				"on p.product_category = pc.category_name " +
				"inner join stock_balance s " +
				"on p.product_code = s.product_code " +
				"inner join stock_inventory si " +
				"on p.product_code = si.product_code");
		ListMap.put(Report.PPrsForm, 
			"SELECT * " +
			"FROM inventory..prs " +
			"LEFT JOIN prs_detail prsd ON prs.prsnumber = prsd.prsnumber " +
			"WHERE prsd.prsnumber = ?"
		);
		ListMap.put(Report.PCanvassingForm, 
			"SELECT * " +
			"FROM inventory..canvassing cv " +
			"LEFT JOIN inventory..canvassing_detail cvd ON cv.prsnumber = cvd.prsnumber " +
			"LEFT JOIN inventory..supplier su ON su.supplier_code = cvd.supplier_code "+
			"WHERE su.supplier_code = ?"
		);
		ListMap.put(Report.PPoConfirmatory, 
			"SELECT * " +
			"FROM inventory..po po " +
			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
			"LEFT JOIN inventory..canvassing cnv ON cnv.prsnumber = prs.prsnumber " +
			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name");
		
		ListMap.put(Report.PPoPerDepartment,  
			"SELECT * "+
			"FROM inventory..po_detail pod " +
			"LEFT JOIN po ON po.ponumber = pod.ponumber " +
			"LEFT JOIN product p ON p.product_code = pod.productcode "+
			"WHERE MONTH(podate) = ? AND YEAR(podate) = ?");
		ListMap.put(Report.IMSWS, 
			"SELECT * " +
			"FROM dbo.sws sws, dbo.sws_detail swsd " +
			"WHERE sws.swsnumber = swsd.swsnumber"); 
		ListMap.put(Report.IMTS,
			"SELECT * " +
			"FROM dbo.ts ts, dbo.ts_detail tsd " +
			"WHERE ts.tsnumber = tsd.tsnumber");
		ListMap.put(Report.IMDR, "SELECT * " +
			"FROM dbo.dr dr, dbo.dr_detail drd " +
			"WHERE dr.drnumber = drd.drnumber");
		ListMap.put(Report.FGPTS, 
			"SELECT * "+
			"FROM inventory..pts");
		ListMap.put(Report.FGTS, 
			"SELECT * " +
			"FROM dbo.ts ts, dbo.ts_detail tsd " +
			"WHERE ts.tsnumber = tsd.tsnumber");
	}
	
	public ModelAndView getReportXLS(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> temp = new HashMap(request.getParameterMap());
		HashMap<String, Object>	map = new HashMap<String, Object>();
		for(Entry<String, String[]> e:temp.entrySet()){
			map.put(e.getKey(), e.getValue()[0]);
		}
		
		Report reportType = Report.valueOf(map.get("item").toString());
		
		List data = getDataForType(reportType, map);
		System.out.println("Data"+(data != null?(":"+data.size()):"")+":"+data);
		if(data != null){
			if(!data.isEmpty())	map.put("sdata", data.get(0));
			if(!data.isEmpty())	map.put("edata", data.get(data.size()-1));
			PostProcess process = PostProcess.get(reportType);
			if(process != null)	process.process(data, map);
		}
		byte[] item = ReportFactory.getReportXLS(reportType, data, map);
		response.setHeader("Content-disposition", "attachment; filename=" + reportType+".xls");
		response.setContentLength(item.length);
		response.getOutputStream().write(item);
		
		return null;
	}
	
	public ModelAndView getReportPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> temp = new HashMap(request.getParameterMap());
		HashMap<String, Object>	map = new HashMap<String, Object>();
		for(Entry<String, String[]> e:temp.entrySet()){
			map.put(e.getKey(), e.getValue()[0]);
		}

		Report reportType = Report.valueOf(map.get("item").toString());
		
		List data = getDataForType(reportType, map);
		System.out.println("Data:"+data);
		if(data != null){
			if(!data.isEmpty())	map.put("sdata", data.get(0));
			if(!data.isEmpty())	map.put("edata", data.get(data.size()-1));
			PostProcess process = PostProcess.get(reportType);
			if(process != null)	process.process(data, map);
		}
		byte[] item = ReportFactory.getReportPDF(reportType, data, map);
		response.setHeader("Content-disposition", "attachment; filename=" + reportType+".pdf");
		response.setContentLength(item.length);
		response.getOutputStream().write(item);
		
		return null;
	}
	
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> temp = new HashMap(request.getParameterMap());
		HashMap<String, Object>	map = new HashMap<String, Object>();
		for(Entry<String, String[]> e:temp.entrySet()){
			map.put(e.getKey(), e.getValue()[0]);
		}

		Report reportType = Report.valueOf(map.get("item").toString());
		
		List data = getDataForType(reportType, map);
		System.out.println("Data:"+data);
		if(data != null){
			if(!data.isEmpty())	map.put("sdata", data.get(0));
			if(!data.isEmpty())	map.put("edata", data.get(data.size()-1));
			PostProcess process = PostProcess.get(reportType);
			if(process != null)	process.process(data, map);
		}
		byte[] item = null;
		if("PDF".equalsIgnoreCase(request.getParameter("type"))){
			response.setHeader("Content-disposition", "attachment; filename=" + reportType+".pdf");
			item = ReportFactory.getReportPDF(reportType, data, map);
		}
		else {
			response.setHeader("Content-disposition", "attachment; filename=" + reportType+".xls");
			item = ReportFactory.getReportXLS(reportType, data, map);
		}
		response.setContentLength(item.length);
		response.getOutputStream().write(item);
		
		return null;
	}
	
	public List getDataForType(Report reportType, Map<String, Object> params) throws Exception{
		if(params.get("q") != null){
			Connection conn = new ctrlKoneksiDB().openConnection();
			JDBCExecutor jdbc = new JDBCExecutor(conn);
			List list = jdbc.execQuery(params.get("q").toString());
			conn.close();
			return list;
		}
		else {
			Object o = ListMap.get(reportType);
			if(o == null)	return null;
			if(o instanceof String){
				Connection conn = new ctrlKoneksiDB().openConnection();
				JDBCExecutor jdbc = new JDBCExecutor(conn);
				List list = params.containsKey("params")?jdbc.execQuery(o.toString(), ((Object[])params.get("params").toString().split(":"))):jdbc.execQuery(o.toString());
				conn.close();
				return list;
			} else if(o instanceof String[]){
				Connection conn = new ctrlKoneksiDB().openConnection();
				JDBCExecutor jdbc = new JDBCExecutor(conn);
				int x = 0;
				for(String s:(String[]) o){
					String add = x > 0?""+x:"";
					List list = params.containsKey("params"+add)?jdbc.execQuery(s, ((Object[])params.get("params"+add).toString().split(":"))):
							    (params.containsKey("params"))?jdbc.execQuery(s, ((Object[])params.get("params").toString().split(":"))):jdbc.execQuery(s);
					params.put("data"+add, list);
					++x;
				}
				conn.close();
				return null;
			}else {
				Method m = o.getClass().getMethod("findAll");
				return (List) m.invoke(o);
			}
		}
	}
}
