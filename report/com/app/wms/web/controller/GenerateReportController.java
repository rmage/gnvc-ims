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
import net.sf.jett.jdbc.ResultSetRow;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.util.ctrlKoneksiDB;
import com.report.test.ReportFactory;
import com.report.test.ReportFactory.Report;

@SuppressWarnings({"rawtypes","unchecked"})
public class GenerateReportController extends MultiActionController {
	public static final Map<Report, Object> ListMap = new HashMap<Report, Object>();
	public static final Map<Report, PostProcess> PostProcess = new HashMap<Report, PostProcess>();
	
	static {
		ListMap.put(Report.FWS, 
            "SELECT f.code AS kode, wdsd.description AS nama_barang, " +
            "'FISH' AS tipe, wdsd.qty, wdsd.uom_code AS satuan " +
            "FROM inventory..fish_wds_detail wdsd " +
            "LEFT JOIN inventory..fish f ON f.id = wdsd.fish_id " +
            "LEFT JOIN inventory..fish_storage fs ON fs.id = wdsd.storage_id " +
            "WHERE wds_id=?");
		ListMap.put(Report.FWSABF, null);
		ListMap.put(Report.FWSBR, null);
		ListMap.put(Report.FWSHR, null);
		ListMap.put(Report.FWSL, null);
        ListMap.put(Report.FWSNC, null);
		ListMap.put(Report.FWeightSlip, 
            "SELECT f.code, SUM(wsd.total_weight) AS total_weight " +
            "FROM inventory..fish_ws_detail wsd " +
            "LEFT JOIN inventory..fish f ON f.id = wsd.fish_id " +
            "WHERE wsd.ws_id = ? " +
            "GROUP BY f.code");
		ListMap.put(Report.FRR, 
            "SELECT MAX(ft.description) AS description, f.code, " +
            "SUM(frd.good_weight) AS qty, 'Kg' AS unit " +
            "FROM inventory..fish_rr_detail frd " +
            "LEFT JOIN inventory..fish f ON f.id = frd.fish_id " +
            "LEFT JOIN inventory..fish_type ft ON ft.id = f.fish_type_id " +
            "WHERE frd.rr_id = ? " +
            "GROUP BY f.code");
		ListMap.put(Report.FTS, 
            "SELECT tsd.description, f.code, " +
            "tsd.qty, tsd.uom_code AS unit " +
            "FROM inventory..fish_ts_detail tsd " +
            "LEFT JOIN inventory..fish f ON f.id = tsd.fish_id " +
            "LEFT JOIN inventory..fish_storage fs ON fs.id = tsd.storage_id " +
            "WHERE ts_id=?");
		
		ListMap.put(Report.FSummaryWSSlip, 
			"SELECT ws.ws_no, su.name AS supplier_name, fv.name AS boat_name,"
                + "fv.batch_no, ws.date_shift, ws.time_shift, f.code AS type, wsd.total_weight AS data,"
                + "(SELECT ISNULL(SUM(fs.cooked_weight), 0.00)FROM inventory..fish_spoilage fs "
                + "WHERE fs.vessel_id = ws.vessel_id AND fs.fish_id = wsd.fish_id "
                + "AND fs.date_shift = ws.date_shift) AS spoilage "
                + "FROM inventory..fish_ws_detail wsd "
                + "LEFT JOIN inventory..fish_ws ws ON ws.id = wsd.ws_id "
                + "LEFT JOIN inventory..fish_vessel fv ON fv.id = ws.vessel_id "
                + "LEFT JOIN inventory..fish_supplier su ON su.id = fv.supplier_id "
                + "LEFT JOIN inventory..fish f ON f.id = wsd.fish_id "
                + "WHERE ws.vessel_id = ? AND ws.date_shift = ?");
		ListMap.put(Report.FSpoilagereport, 
			"SELECT catcher_no, f.code, cooked_weight, raw_weight, total_processed, reason, batch_no, date_shift, time_shift, supplier_name " +
			"FROM inventory..fish_spoilage fs " +
			"LEFT JOIN inventory..fish f ON fs.fish_id = f.id " +
			"LEFT JOIN inventory..fish_vessel fv ON fv.id = fs.vessel_id " +
			"LEFT JOIN inventory..fish_supplier s ON fv.supplier_id = s.id " +
			"WHERE fv.id = ? AND fs.date_shift = ? AND fs.time_shift = ?");
		
		ListMap.put(Report.FMDailyProductionReport, null);
		ListMap.put(Report.FGBOR, null);
		ListMap.put(Report.FGTunaVayaReport, null);
		ListMap.put(Report.FGOFAL, null);

		ListMap.put(Report.FGBadStockReport, 
			"select * " +
			"from product p " +
			"inner join product_category pc " +
			"on p.product_category = pc.category_name " +
			"inner join stock_balance s " +
			"on p.product_code = s.product_code " +
			"inner join stock_inventory si " +
			"on p.product_code = si.product_code");
		ListMap.put(Report.FFrozenFishStock, new String[]{
			"DECLARE @sDate DATETIME " +
			"SET @sDate = ? " +
			"DECLARE @eDate DATETIME " +
			"SET @eDate = ? " +
			"SELECT f.code, SUM(fb.balance) rqty, SUM(fwd.total_weight) wqty, SUM(frd.good_weight) eqty, @eDate edate " +
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
//                "select * " +
//                "from goodreceive gr " +
//                "LEFT JOIN goodreceive_detail grd ON gr.grnumber = grd.grnumber " +
//                "LEFT JOIN product p ON p.product_code = grd.productcode " +
//                "LEFT JOIN department d ON d.department_code = gr.department"
                "SELECT rrd.rr_code, CONVERT(VARCHAR(10), rr.rr_date, 103) as rr_date, rr.rr_from, po.po_code, " +
                "CONVERT(VARCHAR(10), po.po_date, 103) as po_date, " +
                "p.product_name, p.product_code, rrd.department_code, rrd.qty_g, rrd.uom, u.name " +
                "FROM rr_detail rrd " +
                "INNER JOIN rr ON rr.rr_code = rrd.rr_code " +
                "INNER JOIN po ON po.po_code = rr.po_code " +
                "LEFT JOIN product p ON p.product_code = rrd.product_code " +
                "LEFT JOIN \"user\" u ON u.user_id = rr.created_by " +
                "WHERE rrd.rr_code = ?");
		
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
			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name "+
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
			"SELECT prs.prsnumber, pod.productcode, cvd.productname, " +
			"SUM(pod.qty) as qty, dep.department_code, pod.currencyCode, pod.unitprice, " +
			"SUM(pod.amount) as amount, po.ponumber, po.supplier_name, cv.canvassername, prs.remarks " +
			"FROM inventory..po po " +
			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prs.prsnumber " +
			"LEFT JOIN inventory..canvassing cv ON cv.prsnumber = prs.prsnumber " +
			"LEFT JOIN inventory..canvassing_detail cvd ON cvd.prsnumber = prs.prsnumber " +
			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name " +
			"GROUP BY prs.prsnumber, pod.productcode, cvd.productname, dep.department_code, " +
			"pod.currencyCode, pod.unitprice, po.ponumber, po.supplier_name, cv.canvassername, prs.remarks"
		);

		
            ListMap.put(Report.PPoForm, 
//                "SELECT * " +
//                "FROM inventory..po po " +
//                "LEFT JOIN inventory..po_detail pod ON po.ponumber = pod.ponumber " +
//                "LEFT JOIN inventory..supplier su ON po.supplier_name = su.supplier_name " +
//                "LEFT JOIN inventory..product p ON p.product_code = pod.productcode " +
//                "LEFT JOIN inventory..department dep ON dep.department_name = po.department_name"
                "SELECT p.po_code, p.po_date, p.discount, p.pph, p.ppn, s.supplier_code, s.supplier_name, " +
                "s.supplier_address, acp.unit_price, p.approved_by, p.approved_date, p.created_date, u.name, " +
                "pr.product_name, pr.product_code, pd.department_code, prs.qty, prs.uom_name, pd.sub_total " +
                "FROM po_detail pd " +
                "LEFT JOIN po p ON p.po_code = pd.po_code " +
                "LEFT JOIN \"user\" u ON u.user_id = p.created_by " +
                "LEFT JOIN supplier s ON s.supplier_code = p.supplier_code " +
                "LEFT JOIN product pr ON pr.product_code = pd.product_code " +
                "LEFT JOIN prs_detail prs ON prs.prsnumber = pd.prsnumber AND prs.productcode = pd.product_code " +
                "LEFT JOIN assign_canv_prc acp ON acp.prsnumber = pd.prsnumber AND acp.productcode = pd.product_code " +
                "AND acp.supplier_code = ? WHERE p.po_code = ?");
		
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
//                "SELECT * " +
//                "FROM inventory..canvassing cv " +
//                "LEFT JOIN inventory..canvassing_detail cvd ON cv.prsnumber = cvd.prsnumber " +
//                "LEFT JOIN inventory..supplier su ON su.supplier_code = cvd.supplier_code "+
//                "WHERE su.supplier_code = ?"
                "SELECT s.supplier_code, s.supplier_name, s.contact_person, null as id, u.name, " +
                "(CONVERT( VARCHAR(2), DAY(GETDATE()) ) + ' ' + DATENAME(MONTH, GETDATE()) + ' ' + CONVERT( VARCHAR(4), YEAR(GETDATE()))) as date, " +
                "acp.prsnumber, pd.productname, pd.qty, pd.uom_name FROM inventory..assign_canv_prc acp " +
                "LEFT JOIN inventory..supplier s ON s.supplier_code = acp.supplier_code " +
                "LEFT JOIN inventory..prs_detail pd ON pd.prsnumber = acp.prsnumber AND pd.productcode = acp.productcode " +
                "LEFT JOIN inventory..\"user\" u ON u.user_id = acp.created_by " +
                "WHERE acp.unit_price IS NULL AND acp.supplier_code = ? "
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
		ListMap.put(Report.IMDR, 
			"SELECT * FROM dbo.dr dr " +
			"LEFT JOIN dbo.dr_detail drd ON dr.drnumber = drd.drnumber " +
			"LEFT JOIN product p ON p.product_code = drd.productcode");
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
		}
		PostProcess process = PostProcess.get(reportType);
		if(process != null)	process.process(data, map);
		byte[] item = null;
		if("PDF".equalsIgnoreCase(request.getParameter("type"))){
			response.setHeader("Content-disposition", "attachment; filename=" + reportType+".pdf");
            response.setContentType("application/pdf");
			item = ReportFactory.getReportPDF(reportType, data, map);
		}
		else if("CSV".equalsIgnoreCase(request.getParameter("type"))){
			response.setHeader("Content-disposition", "attachment; filename=" + reportType+".csv");
			item = ReportFactory.getReportCSV(reportType, data, map);
		} else if("HTML".equalsIgnoreCase(request.getParameter("type"))){
			response.setHeader("Content-disposition", "attachment; filename=" + reportType+".html");
			item = ReportFactory.getReportHTML(reportType, data, map);
		} else {
			response.setHeader("Content-disposition", "attachment; filename=" + reportType+".xls");
            response.setContentType("application/vnd.ms-excel");
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
				List<ResultSetRow> list = params.containsKey("params")?jdbc.execQuery(o.toString(), ((Object[])params.get("params").toString().split(":"))):jdbc.execQuery(o.toString());
				for(int x = 0; x < list.size(); ++x){
					ResultSetRow r = list.get(x);
					if(!r.containsKey("index"))	r.put("index", x+1);
				}
				conn.close();
				return list;
			} else if(o instanceof String[]){
				Connection conn = new ctrlKoneksiDB().openConnection();
				JDBCExecutor jdbc = new JDBCExecutor(conn);
				int x = 0;
				for(String s:(String[]) o){
					String add = x > 0?""+x:"";
					List<ResultSetRow> list = params.containsKey("params"+add)?jdbc.execQuery(s, ((Object[])params.get("params"+add).toString().split(":"))):
							    (params.containsKey("params"))?jdbc.execQuery(s, ((Object[])params.get("params").toString().split(":"))):jdbc.execQuery(s);
				    for(int i = 0; i < list.size(); ++i){
						ResultSetRow r = list.get(i);
						if(!r.containsKey("index"))	r.put("index", i+1);
					}
					if(!list.isEmpty())	params.put("sdata"+add, list.get(0));
					if(!list.isEmpty())	params.put("edata"+add, list.get(list.size()-1));
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
