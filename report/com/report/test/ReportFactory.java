package com.report.test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.jett.jdbc.ResultSetRow;

import com.report.test.ReportFactory.Report;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReportFactory {
    public static enum Report {
        FWSHR, FWSBR, FWSABF, FWSNC, FWeightSlip,
        FWSNR, FWSL, FSummaryWSSlip,
        FSpoilagereport, FRR, FWS, 
        FDR, FTS, FFrozenFishStock, 
        FFrozenFishStockCS, FFrozenFishStockBatchNo, FFrozenFishStockSupplier, FDailyInventoryFrozenFish, FFrozenFishPerBatch, // added by edw
        FLaporanPemasukanBarangPerDokumenPabean, FLaporanPengeluaranBarangPerDokumenPabean, FLaporanPengeluaranBahanBakuDanBahanPenolong, //added by edw
        FLaporanPengeluaranMutasiBarangJadi, FLaporanPengeluaranMutasiBarangDanScrap, FLaporanPengeluaranMutasiMesin, // added by edw
        RPDailyProduction, FMDR, PPrsNotyetPO,
        PPoNotyetDeliveredCash, PPoNotyetDeliveredCredit, PPoRegisterPerPeriode,
        PPoIssuedPerSupplier, PPoIssuedPerItem, PPrsForm,
        PCanvassingForm, PPoForm, PPoConfirmatory,
        PPoPerDepartment, IMRR, IMSWS,
        IMTS, IMDR, IMStockCardperCategory, IMStockCardTransactionReport,
        FGPTS, FGOFAL, FGBOR,
        FGTS, FGEDS, FGBadStockReport,
        FGTunaVayaReport, Accounting, PPoNotyetDeliveredDP,
        RRPeriode, PurchasedItems, PurchasedPerSupplier, TSPeriode, CanvassingHistory //,
//        Laporanpertanggungjawabanmutasibarangjadi, // added by edw
//        Laporanpertanggungjawabanmutasibarangsisadanscrap, Laporanpertanggungjawabanmutasimesindanperalatanperkantoran // added by edw
        ; 
    }
	
    public static final Map<String, ReportModel> reportTemplateMap = new HashMap<String, ReportModel>();
    public static final Map<Report, ReportModel> reportMap = new HashMap<Report, ReportModel>();
    public static final Map<Report, ArrayList<String>> REPORT_COLUMN = new HashMap<Report, ArrayList<String>>();
    public static final HashSet<String> IGNORED_COLUMN = new HashSet<String>();
	
    static {
        reportTemplateMap.put("deliverReceipt", new ReportModel("deliverReceipt", false));
        reportTemplateMap.put("canvassingForm", new ReportModel("canvassingForm", false));
        reportTemplateMap.put("eds", new ReportModel("eds", false));
        
        reportTemplateMap.put("FrozenFishStock", new ReportModel("FrozenFishStock1")); // add by edw
        reportTemplateMap.put("FrozenFishStockCS", new ReportModel("FrozenFishStock2")); // add by edw
        reportTemplateMap.put("FrozenFishStockBatchNo", new ReportModel("FrozenFishStock2")); // add by edw
        reportTemplateMap.put("FrozenFishStockSupplier", new ReportModel("FrozenFishStock2")); // add by edw
        reportTemplateMap.put("DailyInventoryFrozenFish", new ReportModel("DailyInventoryFrozenFish")); // add by edw
        reportTemplateMap.put("FrozenFishPerBatch", new ReportModel("FrozenFishPerBatch")); // add by edw
        reportTemplateMap.put("LaporanPemasukanBarangPerDokumenPabean", new ReportModel("LaporanPemasukanBarangPerDokumenPabean")); // add by edw
        reportTemplateMap.put("LaporanPengeluaranBarangPerDokumenPabean", new ReportModel("LaporanPengeluaranBarangPerDokumenPabean")); // add by edw
        reportTemplateMap.put("LaporanPengeluaranBahanBakuDanBahanPenolong", new ReportModel("LaporanPengeluaranBahanBakuDanBahanPenolong")); // add by edw
        
        reportTemplateMap.put("poConfirmatory", new ReportModel("poConfirmatory"));
        reportTemplateMap.put("poForm", new ReportModel("poForm", false));
        reportTemplateMap.put("poIssuedPerSupplier", new ReportModel("poIssuedPerSupplier"));
        reportTemplateMap.put("poNotyetDeliveredCash", new ReportModel("poNotyetDeliveredCash"));
        reportTemplateMap.put("poNotyetDeliveredCredit", new ReportModel("poNotyetDeliveredCredit"));
        reportTemplateMap.put("poPerDepartment", new ReportModel("poPerDepartment"));
        reportTemplateMap.put("poRegisterPerPeriode", new ReportModel("poRegisterPerPeriode"));
        reportTemplateMap.put("prsForm", new ReportModel("prsForm", false));
        reportTemplateMap.put("prsNotyetPO", new ReportModel("prsNotyetPO"));
        reportTemplateMap.put("pts", new ReportModel("pts", true));
        reportTemplateMap.put("receivingReport", new ReportModel("receivingReport", false));
        reportTemplateMap.put("receivingReportNF", new ReportModel("receivingReportNF", false));
        reportTemplateMap.put("stockCardReport", new ReportModel("stockCardReport", false));
        reportTemplateMap.put("summaryWeightSlip", new ReportModel("summaryWeightSlip"));
        reportTemplateMap.put("transferSlip", new ReportModel("transferSlip", false));
        reportTemplateMap.put("transferSlipNF", new ReportModel("transferSlipNF", false));
        reportTemplateMap.put("spoilageReport", new ReportModel("spoilageReport", false));
        reportTemplateMap.put("weightslip", new ReportModel("weightslip", false));
        reportTemplateMap.put("wsabf", new ReportModel("wsabf", false));
        reportTemplateMap.put("wsbr", new ReportModel("wsbr", false));
        reportTemplateMap.put("wshr", new ReportModel("wshr", false));
        reportTemplateMap.put("wsnc", new ReportModel("wsnc", false));
        reportTemplateMap.put("wsnr", new ReportModel("wsnr", false));
        reportTemplateMap.put("ofal", new ReportModel("ofal", false));
        reportTemplateMap.put("bookedOrderReport", new ReportModel("bookedOrderReport", false));
        reportTemplateMap.put("poIssuedPerItem", new ReportModel("poIssuedPerItem", false));
        reportTemplateMap.put("viandTunaInv", new ReportModel("viandTunaInv", false));
        reportTemplateMap.put("withdrawalSlip", new ReportModel("withdrawalSlip", false));
        reportTemplateMap.put("badStockCard", new ReportModel("badStockCard", false));	
        reportTemplateMap.put("IMStockCardperCategory", new ReportModel("gnvStockCardIM", false));
        reportTemplateMap.put("IMStockCardTransactionReport", new ReportModel("gnvTransactionReportIM", false));
        reportTemplateMap.put("RPDailyProduction", new ReportModel("gnvRenderingPlantIM", false));
        reportTemplateMap.put("RRPeriode", new ReportModel("gnvRRPeriode", false));
        reportTemplateMap.put("PurchasedItems", new ReportModel("gnvPurchasedItems", false));
        reportTemplateMap.put("PurchasedPerSupplier", new ReportModel("gnvPurchasedPerSupplier", false));
        reportTemplateMap.put("TSPeriode", new ReportModel("gnvTSPeriode", false));
        reportTemplateMap.put("CanvassingHistory", new ReportModel("gnvCanvassingHistory", false));

        reportMap.put(Report.FWeightSlip, reportTemplateMap.get("weightslip"));
        reportMap.put(Report.FWSHR, reportTemplateMap.get("wshr"));
        reportMap.put(Report.FWSBR, reportTemplateMap.get("wsbr"));
        reportMap.put(Report.FWSABF, reportTemplateMap.get("wsabf"));
        reportMap.put(Report.FWSNC, reportTemplateMap.get("wsnc"));
        reportMap.put(Report.FWSNR, reportTemplateMap.get("wsnr"));
        reportMap.put(Report.FSummaryWSSlip, reportTemplateMap.get("summaryWeightSlip"));
        reportMap.put(Report.FSpoilagereport, reportTemplateMap.get("spoilageReport"));
        reportMap.put(Report.FRR, reportTemplateMap.get("receivingReport"));
        reportMap.put(Report.FWS, reportTemplateMap.get("withdrawalSlip"));
        reportMap.put(Report.FDR, reportTemplateMap.get("deliverReceipt"));
        reportMap.put(Report.FTS, reportTemplateMap.get("transferSlip"));
        
        reportMap.put(Report.FFrozenFishStock, reportTemplateMap.get("FrozenFishStock")); // added by edw
        reportMap.put(Report.FFrozenFishStockCS, reportTemplateMap.get("FrozenFishStockCS")); // added by edw
        reportMap.put(Report.FFrozenFishStockBatchNo, reportTemplateMap.get("FrozenFishStockBatchNo")); // added by edw
        reportMap.put(Report.FFrozenFishStockSupplier, reportTemplateMap.get("FrozenFishStockSupplier")); // added by edw
        reportMap.put(Report.FDailyInventoryFrozenFish, reportTemplateMap.get("DailyInventoryFrozenFish")); // added by edw
        reportMap.put(Report.FFrozenFishPerBatch, reportTemplateMap.get("FrozenFishPerBatch")); // added by edw
        reportMap.put(Report.FLaporanPemasukanBarangPerDokumenPabean, reportTemplateMap.get("LaporanPemasukanBarangPerDokumenPabean")); // added by edw
        reportMap.put(Report.FLaporanPengeluaranBarangPerDokumenPabean, reportTemplateMap.get("LaporanPengeluaranBarangPerDokumenPabean")); // added by edw
        reportMap.put(Report.FLaporanPengeluaranBahanBakuDanBahanPenolong, reportTemplateMap.get("LaporanPengeluaranBahanBakuDanBahanPenolong")); // added by edw
        reportMap.put(Report.FLaporanPengeluaranMutasiBarangJadi, reportTemplateMap.get("LaporanPengeluaranMutasiBarangJadi")); // added by edw
        reportMap.put(Report.FLaporanPengeluaranMutasiBarangDanScrap, reportTemplateMap.get("LaporanPengeluaranMutasiBarangDanScrap")); // added by edw
        reportMap.put(Report.FLaporanPengeluaranMutasiMesin, reportTemplateMap.get("LaporanPengeluaranMutasiMesin")); // added by edw
        
        reportMap.put(Report.FMDR, reportTemplateMap.get("deliverReceipt"));
        reportMap.put(Report.PPrsNotyetPO, reportTemplateMap.get("prsNotyetPO"));
        reportMap.put(Report.PPoNotyetDeliveredCash, reportTemplateMap.get("poNotyetDeliveredCash"));
        reportMap.put(Report.PPoNotyetDeliveredCredit, reportTemplateMap.get("poNotyetDeliveredCredit"));
        reportMap.put(Report.PPoNotyetDeliveredDP, reportTemplateMap.get("poNotyetDeliveredCash"));
        reportMap.put(Report.PPoRegisterPerPeriode, reportTemplateMap.get("poRegisterPerPeriode"));
        reportMap.put(Report.PPoIssuedPerSupplier, reportTemplateMap.get("poIssuedPerSupplier"));
        reportMap.put(Report.PPoIssuedPerItem, reportTemplateMap.get("poIssuedPerItem"));
        reportMap.put(Report.PPrsForm, reportTemplateMap.get("prsForm"));
        reportMap.put(Report.PCanvassingForm, reportTemplateMap.get("canvassingForm"));
        reportMap.put(Report.PPoForm, reportTemplateMap.get("poForm"));
        reportMap.put(Report.PPoConfirmatory, reportTemplateMap.get("poConfirmatory"));
        reportMap.put(Report.PPoPerDepartment, reportTemplateMap.get("poPerDepartment"));
        reportMap.put(Report.IMRR, reportTemplateMap.get("receivingReportNF"));
        reportMap.put(Report.IMSWS, reportTemplateMap.get("withdrawalSlip"));
        reportMap.put(Report.IMTS, reportTemplateMap.get("transferSlipNF"));
        reportMap.put(Report.IMStockCardperCategory, reportTemplateMap.get("IMStockCardperCategory"));
        reportMap.put(Report.IMStockCardTransactionReport, reportTemplateMap.get("IMStockCardTransactionReport"));
        reportMap.put(Report.FGPTS, reportTemplateMap.get("pts"));
        reportMap.put(Report.FGOFAL, reportTemplateMap.get("ofal"));
        reportMap.put(Report.FGBOR, reportTemplateMap.get("bookedOrderReport"));
        reportMap.put(Report.FGTS, reportTemplateMap.get("transferSlip"));
        reportMap.put(Report.FGEDS, reportTemplateMap.get("eds"));

        reportMap.put(Report.RPDailyProduction, reportTemplateMap.get("RPDailyProduction"));
        reportMap.put(Report.RRPeriode, reportTemplateMap.get("RRPeriode"));
        reportMap.put(Report.PurchasedItems, reportTemplateMap.get("PurchasedItems"));
        reportMap.put(Report.PurchasedPerSupplier, reportTemplateMap.get("PurchasedPerSupplier"));
        reportMap.put(Report.TSPeriode, reportTemplateMap.get("TSPeriode"));
        reportMap.put(Report.CanvassingHistory, reportTemplateMap.get("CanvassingHistory"));

        reportMap.put(Report.FWSL, reportTemplateMap.get("wshr"));
        reportMap.put(Report.FGBadStockReport, reportTemplateMap.get("badStockCard"));
        reportMap.put(Report.FGTunaVayaReport, reportTemplateMap.get("viandTunaInv"));
        reportMap.put(Report.Accounting, reportTemplateMap.get("stockCardReport"));
        

        IGNORED_COLUMN.add("index");
    }	
	
	public static void main(String [] str){
		try {
			String name = "wsnr";
			OutputStream xls = new FileOutputStream("Report.xls");
			OutputStream pdf = new FileOutputStream("Report.pdf");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] getReportPDF(Report reportType, List data, Map map) throws Exception{
		return reportMap.get(reportType).getReportPDF(data, map);
	}

	public static byte[] getReportXLS(Report reportType, List data, Map map) throws Exception{
		return reportMap.get(reportType).getReportXLS(data, map);
	}
	
	public static byte[] getReportCSV(Report reportType, List data, Map map) throws Exception{
//		return reportMap.get(reportType).getReportCSV(data, map);
    	String csv = "";
    	List<ResultSetRow> m = (List<ResultSetRow>) data;
    	if(m == null) m = (List<ResultSetRow>) map.get("data");
    	List<String> column = REPORT_COLUMN.get(reportType);
    	if(column ==  null){
    		if(!m.isEmpty())	
    			column = new ArrayList<String>(m.get(0).keySet());
    	}
		for(String c:column){
			if(!IGNORED_COLUMN.contains(c))	csv += c+",";
		}
		csv += "\r\n";
    	for(ResultSetRow r:m){
    		for(String c:column){
    			if(!IGNORED_COLUMN.contains(c))	csv += r.get(c)+",";
    		}
    		csv += "\r\n";
    	}
    	return csv.getBytes();
	}

	public static byte[] getReportHTML(Report reportType, List data, HashMap<String, Object> map) {
		return reportMap.get(reportType).getReportHTML(data, map);
	}
}
