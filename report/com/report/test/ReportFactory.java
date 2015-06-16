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

@SuppressWarnings({"unchecked", "rawtypes"})
public class ReportFactory {

    public static enum Report {

        FWSHR, FWSBR, FWSABF, FWSNC, FWeightSlip,
        FWSNR, FWSL, FSummaryWSSlip,
        FSpoilagereport, FRR, FWS,
        FDR, FTS, FFrozenFishStock,
        FFrozenFishStockCS, FFrozenFishStockBatchNo, FFrozenFishStockSupplier, FDailyInventoryFrozenFish, FFrozenFishPerBatch, // added by edw
        FLaporanPemasukanBarangPerDokumenPabean, FLaporanPengeluaranBarangPerDokumenPabean, FLaporanPengeluaranBahanBakuDanBahanPenolong, //added by edw
        FLaporanPengeluaranMutasiBarangJadi, FLaporanPengeluaranMutasiBarangDanScrap, FLaporanPengeluaranMutasiMesin, // added by edw
        RPDailyProduction, FMDR,
        PCanvassingForm,
        IMStockCardperItem,
        IMStockCardperCategory, IMStockCardTransactionReport,
        FGPTS, FGOFAL, FGBOR, FGFM, FGFO, FGSC,
        FGTS, FGEDS, FGBadStockReport,
        FGTunaVayaReport, Accounting,
        RRPeriode, TSPeriode, CanvassingHistory //,
        //        Laporanpertanggungjawabanmutasibarangjadi, // added by edw
        //        Laporanpertanggungjawabanmutasibarangsisadanscrap, Laporanpertanggungjawabanmutasimesindanperalatanperkantoran // added by edw
        //  Fish Module | Form and Report List
        , FishWSHR, FishWSNC, FishWSBF, FishWSNR, FishWSABF, FishWSL, FishSR, FishWssFresh, FishWssFrozen, FishRR/*, FishSumPerSupp, FishSumPerCS, FishStockCard*/, FishTS, FishBF, FishABF/*, FishSumPerSuppActual, FishSumPerCSActual, FishStockCardActual*/, FishWDS, FishRECC, FishFMov, FishWsSF, FishWsSZ, FishDailyInCS, FishSummaryInCS //  Purchase Module | Form and Report List
        , PRCPrs, PRCPo, PRCPrsNotYetPo, PRCPoNotYetRr, PRCPoRegisteredPerPeriod, PRCPoRegisteredPerPeriodConfirmatory, PRCPoRegisteredPerDepartment, PRCPoRegisteredPerItem, PRCPoRegisteredPerSupplier, PRCPrsRegister //  Non-Fish Module | Form and Report List
        , NFRr, NFSws, NFSwsP, NFTs, NFDr, NFSIPerCat, NFTPerCat, NFRrRegisterPerPeriod, NFSwsRegisterPerPeriod, NFTsRegisterPerPeriod, NFDrRegisterPerPeriod //  Finished Goods Module | Form and Report List
        , FGPtsPerPeriod, FGStockInventory, FGPtsCheckList, FGActualInventory, FGBor, FGBor15, FGPts, FGOfal, FGLmr, FGBor15Report //  Rendering Module | Form and Report List
        , RendDailyReport, RendSummaryReport, RendSales
        , MSupplier, MProduct
        , reportBC23 //iCore Module | Report List BC 2.3;
        , reportBC40 //iCore Module | Report List BC 4.0;
        , reportBC30 //iCore Module | Report List BC 3.0
        , reportBC41 //iCore Module | Report List BC 4.1
        , reportFinishGood; //iCore Module | Report List Finish Good  
    }

    public static final Map<String, ReportModel> reportTemplateMap = new HashMap<String, ReportModel>();
    public static final Map<Report, ReportModel> reportMap = new HashMap<Report, ReportModel>();
    public static final Map<Report, ArrayList<String>> REPORT_COLUMN = new HashMap<Report, ArrayList<String>>();
    public static final HashSet<String> IGNORED_COLUMN = new HashSet<String>();

    static {
        //  Fish Module | Form and Report List
        reportTemplateMap.put("FishWSHR", new ReportModel("fish/wshr", false));
        reportMap.put(Report.FishWSHR, reportTemplateMap.get("FishWSHR"));
        reportTemplateMap.put("FishWSNC", new ReportModel("fish/wsnc", false));
        reportMap.put(Report.FishWSNC, reportTemplateMap.get("FishWSNC"));
        reportTemplateMap.put("FishWSBF", new ReportModel("fish/wsbf", false));
        reportMap.put(Report.FishWSBF, reportTemplateMap.get("FishWSBF"));
        reportTemplateMap.put("FishWSNR", new ReportModel("fish/wsnr", false));
        reportMap.put(Report.FishWSNR, reportTemplateMap.get("FishWSNR"));
        reportTemplateMap.put("FishWSABF", new ReportModel("fish/wsabf", false));
        reportMap.put(Report.FishWSABF, reportTemplateMap.get("FishWSABF"));
        reportTemplateMap.put("FishWSL", new ReportModel("fish/wsl", false));
        reportMap.put(Report.FishWSL, reportTemplateMap.get("FishWSL"));
        reportTemplateMap.put("FishSR", new ReportModel("fish/sr", false));
        reportMap.put(Report.FishSR, reportTemplateMap.get("FishSR"));
        reportTemplateMap.put("FishWssFresh", new ReportModel("fish/wssfresh", false));
        reportMap.put(Report.FishWssFresh, reportTemplateMap.get("FishWssFresh"));
        reportTemplateMap.put("FishWssFrozen", new ReportModel("fish/wssfrozen", false));
        reportMap.put(Report.FishWssFrozen, reportTemplateMap.get("FishWssFrozen"));
        reportTemplateMap.put("FishRR", new ReportModel("fish/rr", false));
        reportMap.put(Report.FishRR, reportTemplateMap.get("FishRR"));
//        reportTemplateMap.put("FishSumPerSupp", new ReportModel("fish/sumpsupp", false));
//        reportMap.put(Report.FishSumPerSupp, reportTemplateMap.get("FishSumPerSupp"));
//        reportTemplateMap.put("FishSumPerCS", new ReportModel("fish/sumpcs", false));
//        reportMap.put(Report.FishSumPerCS, reportTemplateMap.get("FishSumPerCS"));
//        reportTemplateMap.put("FishStockCard", new ReportModel("fish/sc", false));
//        reportMap.put(Report.FishStockCard, reportTemplateMap.get("FishStockCard"));
        reportTemplateMap.put("FishTS", new ReportModel("fish/ts", false));
        reportMap.put(Report.FishTS, reportTemplateMap.get("FishTS"));
        reportTemplateMap.put("FishBF", new ReportModel("fish/bf", false));
        reportMap.put(Report.FishBF, reportTemplateMap.get("FishBF"));
        reportTemplateMap.put("FishABF", new ReportModel("fish/abf", false));
        reportMap.put(Report.FishABF, reportTemplateMap.get("FishABF"));
//        reportTemplateMap.put("FishSumPerSuppActual", new ReportModel("fish/sumpsuppa", false));
//        reportMap.put(Report.FishSumPerSuppActual, reportTemplateMap.get("FishSumPerSuppActual"));
//        reportTemplateMap.put("FishSumPerCSActual", new ReportModel("fish/sumpcsa", false));
//        reportMap.put(Report.FishSumPerCSActual, reportTemplateMap.get("FishSumPerCSActual"));
//        reportTemplateMap.put("FishStockCardActual", new ReportModel("fish/sca", false));
//        reportMap.put(Report.FishStockCardActual, reportTemplateMap.get("FishStockCardActual"));
        reportTemplateMap.put("FishDailyInCS", new ReportModel("fish/dfics", false));
        reportMap.put(Report.FishDailyInCS, reportTemplateMap.get("FishDailyInCS"));
        reportTemplateMap.put("FishSummaryInCS", new ReportModel("fish/sfics", false));
        reportMap.put(Report.FishSummaryInCS, reportTemplateMap.get("FishSummaryInCS"));
        reportTemplateMap.put("FishWDS", new ReportModel("fish/wds", false));
        reportMap.put(Report.FishWDS, reportTemplateMap.get("FishWDS"));
        reportTemplateMap.put("FishRECC", new ReportModel("fish/fr", false));
        reportMap.put(Report.FishRECC, reportTemplateMap.get("FishRECC"));
        reportTemplateMap.put("FishFMov", new ReportModel("fish/fm", false));
        reportMap.put(Report.FishFMov, reportTemplateMap.get("FishFMov"));
        reportTemplateMap.put("FishWsSF", new ReportModel("fish/wssfreshl", false));
        reportMap.put(Report.FishWsSF, reportTemplateMap.get("FishWsSF"));
        reportTemplateMap.put("FishWsSZ", new ReportModel("fish/wssfrozenl", false));
        reportMap.put(Report.FishWsSZ, reportTemplateMap.get("FishWsSZ"));

        //  Purchasing Module | Form and Report List
        reportTemplateMap.put("PRCPrs", new ReportModel("purchase/prs", false));
        reportMap.put(Report.PRCPrs, reportTemplateMap.get("PRCPrs"));
        reportTemplateMap.put("PRCPo", new ReportModel("purchase/po", false));
        reportMap.put(Report.PRCPo, reportTemplateMap.get("PRCPo"));
        reportTemplateMap.put("PRCPrsNotYetPo", new ReportModel("purchase/prsnypo", false));
        reportMap.put(Report.PRCPrsNotYetPo, reportTemplateMap.get("PRCPrsNotYetPo"));
        reportTemplateMap.put("PRCPoNotYetRr", new ReportModel("purchase/ponyrr", false));
        reportMap.put(Report.PRCPoNotYetRr, reportTemplateMap.get("PRCPoNotYetRr"));
        reportTemplateMap.put("PRCPoRegisteredPerPeriod", new ReportModel("purchase/porpp", false));
        reportMap.put(Report.PRCPoRegisteredPerPeriod, reportTemplateMap.get("PRCPoRegisteredPerPeriod"));
        reportTemplateMap.put("PRCPoRegisteredPerPeriodConfirmatory", new ReportModel("purchase/porppc", false));
        reportMap.put(Report.PRCPoRegisteredPerPeriodConfirmatory, reportTemplateMap.get("PRCPoRegisteredPerPeriodConfirmatory"));
        reportTemplateMap.put("PRCPoRegisteredPerDepartment", new ReportModel("purchase/porpd", false));
        reportMap.put(Report.PRCPoRegisteredPerDepartment, reportTemplateMap.get("PRCPoRegisteredPerDepartment"));
        reportTemplateMap.put("PRCPoRegisteredPerItem", new ReportModel("purchase/porpi", false));
        reportMap.put(Report.PRCPoRegisteredPerItem, reportTemplateMap.get("PRCPoRegisteredPerItem"));
        reportTemplateMap.put("PRCPoRegisteredPerSupplier", new ReportModel("purchase/porps", false));
        reportMap.put(Report.PRCPoRegisteredPerSupplier, reportTemplateMap.get("PRCPoRegisteredPerSupplier"));
        reportTemplateMap.put("PRCPrsRegister", new ReportModel("purchase/prsrpp", false));
        reportMap.put(Report.PRCPrsRegister, reportTemplateMap.get("PRCPrsRegister"));

        //  Non-Fish Module | Form and Report List
        reportTemplateMap.put("NFRr", new ReportModel("non_fish/rr", false));
        reportMap.put(Report.NFRr, reportTemplateMap.get("NFRr"));
        reportTemplateMap.put("NFSws", new ReportModel("non_fish/sws", false));
        reportMap.put(Report.NFSws, reportTemplateMap.get("NFSws"));
        reportTemplateMap.put("NFSwsP", new ReportModel("non_fish/sws-print", false));
        reportMap.put(Report.NFSwsP, reportTemplateMap.get("NFSwsP"));
        reportTemplateMap.put("NFTs", new ReportModel("non_fish/ts", false));
        reportMap.put(Report.NFTs, reportTemplateMap.get("NFTs"));
        reportTemplateMap.put("NFDr", new ReportModel("non_fish/dr", false));
        reportMap.put(Report.NFDr, reportTemplateMap.get("NFDr"));
        reportTemplateMap.put("NFSIPerCat", new ReportModel("non_fish/sipcat", false));
        reportMap.put(Report.NFSIPerCat, reportTemplateMap.get("NFSIPerCat"));
        reportTemplateMap.put("NFTPerCat", new ReportModel("non_fish/tpcat", false));
        reportMap.put(Report.NFTPerCat, reportTemplateMap.get("NFTPerCat"));
        reportTemplateMap.put("NFRrRegisterPerPeriod", new ReportModel("non_fish/rrrpp", false));
        reportMap.put(Report.NFRrRegisterPerPeriod, reportTemplateMap.get("NFRrRegisterPerPeriod"));
        reportTemplateMap.put("NFSwsRegisterPerPeriod", new ReportModel("non_fish/swsrpp", false));
        reportMap.put(Report.NFSwsRegisterPerPeriod, reportTemplateMap.get("NFSwsRegisterPerPeriod"));
        reportTemplateMap.put("NFTsRegisterPerPeriod", new ReportModel("non_fish/tsrpp", false));
        reportMap.put(Report.NFTsRegisterPerPeriod, reportTemplateMap.get("NFTsRegisterPerPeriod"));
        reportTemplateMap.put("NFDrRegisterPerPeriod", new ReportModel("non_fish/drrpp", false));
        reportMap.put(Report.NFDrRegisterPerPeriod, reportTemplateMap.get("NFDrRegisterPerPeriod"));

        // Finished Goods Module | Form and Report List
        reportTemplateMap.put("FGBor", new ReportModel("finished_goods/bor", false));
        reportMap.put(Report.FGBor, reportTemplateMap.get("FGBor"));
        reportTemplateMap.put("FGBor15", new ReportModel("finished_goods/bor15", false));
        reportMap.put(Report.FGBor15, reportTemplateMap.get("FGBor15"));
        reportTemplateMap.put("FGPts", new ReportModel("finished_goods/pts", false));
        reportMap.put(Report.FGPts, reportTemplateMap.get("FGPts"));
        reportTemplateMap.put("FGOfal", new ReportModel("finished_goods/ofal", false));
        reportMap.put(Report.FGOfal, reportTemplateMap.get("FGOfal"));
        reportTemplateMap.put("FGLmr", new ReportModel("finished_goods/lmr", false));
        reportMap.put(Report.FGLmr, reportTemplateMap.get("FGLmr"));
        reportTemplateMap.put("FGPtsPerPeriod", new ReportModel("finished_goods/ptsr", false));
        reportMap.put(Report.FGPtsPerPeriod, reportTemplateMap.get("FGPtsPerPeriod"));
        reportTemplateMap.put("FGStockInventory", new ReportModel("finished_goods/fgir", false));
        reportMap.put(Report.FGStockInventory, reportTemplateMap.get("FGStockInventory"));
        reportTemplateMap.put("FGPtsCheckList", new ReportModel("finished_goods/fgpcl", false));
        reportMap.put(Report.FGPtsCheckList, reportTemplateMap.get("FGPtsCheckList"));
        reportTemplateMap.put("FGActualInventory", new ReportModel("finished_goods/fgair", false));
        reportMap.put(Report.FGActualInventory, reportTemplateMap.get("FGActualInventory"));
        reportTemplateMap.put("FGBor15Report", new ReportModel("finished_goods/borr", false));
        reportMap.put(Report.FGBor15Report, reportTemplateMap.get("FGBor15Report"));
        
        // Rendering Module | Form and Report List
        reportTemplateMap.put("RendDailyReport", new ReportModel("rendering/daily", false));
        reportMap.put(Report.RendDailyReport, reportTemplateMap.get("RendDailyReport"));
        reportTemplateMap.put("RendSummaryReport", new ReportModel("rendering/summary", false));
        reportMap.put(Report.RendSummaryReport, reportTemplateMap.get("RendSummaryReport"));
        reportTemplateMap.put("RendSales", new ReportModel("rendering/rs", false));
        reportMap.put(Report.RendSales, reportTemplateMap.get("RendSales"));
        
        // Master Module | Report List
        reportTemplateMap.put("MSupplier", new ReportModel("master/supplier", false));
        reportMap.put(Report.MSupplier, reportTemplateMap.get("MSupplier"));
        reportTemplateMap.put("MProduct", new ReportModel("master/product", false));
        reportMap.put(Report.MProduct, reportTemplateMap.get("MProduct"));

        reportTemplateMap.put("FGSC", new ReportModel("gnvStockCardFG", false));

        reportTemplateMap.put("deliverReceipt", new ReportModel("deliverReceipt", false));
        reportTemplateMap.put("canvassingForm", new ReportModel("canvassingForm", false));
        reportTemplateMap.put("FGEDS", new ReportModel("gnvEDS", false));

        reportTemplateMap.put("FrozenFishStock", new ReportModel("FrozenFishStock1")); // add by edw
        reportTemplateMap.put("FrozenFishStockCS", new ReportModel("FrozenFishStock2")); // add by edw
        reportTemplateMap.put("FrozenFishStockBatchNo", new ReportModel("FrozenFishStock2")); // add by edw
        reportTemplateMap.put("FrozenFishStockSupplier", new ReportModel("FrozenFishStock2")); // add by edw
        reportTemplateMap.put("DailyInventoryFrozenFish", new ReportModel("DailyInventoryFrozenFish")); // add by edw
        reportTemplateMap.put("FrozenFishPerBatch", new ReportModel("FrozenFishPerBatch")); // add by edw
        reportTemplateMap.put("LaporanPemasukanBarangPerDokumenPabean", new ReportModel("LaporanPemasukanBarangPerDokumenPabean")); // add by edw
        reportTemplateMap.put("LaporanPengeluaranBarangPerDokumenPabean", new ReportModel("LaporanPengeluaranBarangPerDokumenPabean")); // add by edw
        reportTemplateMap.put("LaporanPengeluaranBahanBakuDanBahanPenolong", new ReportModel("LaporanPengeluaranBahanBakuDanBahanPenolong")); // add by edw

        reportTemplateMap.put("pts", new ReportModel("pts", true));
        reportTemplateMap.put("receivingReport", new ReportModel("receivingReport", false));
        reportTemplateMap.put("stockCardReport", new ReportModel("stockCardReport", false));
        reportTemplateMap.put("summaryWeightSlip", new ReportModel("summaryWeightSlip"));
        reportTemplateMap.put("transferSlip", new ReportModel("transferSlip", false));
        reportTemplateMap.put("spoilageReport", new ReportModel("spoilageReport", false));
        reportTemplateMap.put("weightslip", new ReportModel("weightslip", false));
        reportTemplateMap.put("wsabf", new ReportModel("wsabf", false));
        reportTemplateMap.put("wsbr", new ReportModel("wsbr", false));
        reportTemplateMap.put("wshr", new ReportModel("wshr", false));
        reportTemplateMap.put("wsnc", new ReportModel("wsnc", false));
        reportTemplateMap.put("wsnr", new ReportModel("wsnr", false));
        reportTemplateMap.put("ofal", new ReportModel("ofal", false));
        reportTemplateMap.put("bookedOrderReport", new ReportModel("bookedOrderReport", false));
        reportTemplateMap.put("viandTunaInv", new ReportModel("viandTunaInv", false));
        reportTemplateMap.put("IMStockCardperItem", new ReportModel("gnvStockCardIMperItem", false));
        reportTemplateMap.put("badStockCard", new ReportModel("badStockCard", false));
        reportTemplateMap.put("IMStockCardperCategory", new ReportModel("gnvStockCardIM", false));
        reportTemplateMap.put("IMStockCardTransactionReport", new ReportModel("gnvTransactionReportIM", false));
        reportTemplateMap.put("RPDailyProduction", new ReportModel("gnvRenderingPlantIM", false));
        reportTemplateMap.put("RRPeriode", new ReportModel("gnvRRPeriode", false));
        reportTemplateMap.put("TSPeriode", new ReportModel("gnvTSPeriode", false));
        reportTemplateMap.put("CanvassingHistory", new ReportModel("gnvCanvassingHistory", false));
        reportTemplateMap.put("FGFM", new ReportModel("gnvFishMeal", false));
        reportTemplateMap.put("FGFO", new ReportModel("gnvFishOil", false));

        reportMap.put(Report.FGSC, reportTemplateMap.get("FGSC"));

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
        reportMap.put(Report.PCanvassingForm, reportTemplateMap.get("canvassingForm"));

        reportMap.put(Report.IMStockCardperItem, reportTemplateMap.get("IMStockCardperItem"));
        reportMap.put(Report.IMStockCardperCategory, reportTemplateMap.get("IMStockCardperCategory"));
        reportMap.put(Report.IMStockCardTransactionReport, reportTemplateMap.get("IMStockCardTransactionReport"));
        reportMap.put(Report.FGPTS, reportTemplateMap.get("pts"));
        reportMap.put(Report.FGOFAL, reportTemplateMap.get("ofal"));
        reportMap.put(Report.FGBOR, reportTemplateMap.get("bookedOrderReport"));
        reportMap.put(Report.FGTS, reportTemplateMap.get("transferSlip"));
        reportMap.put(Report.FGEDS, reportTemplateMap.get("FGEDS"));

        reportMap.put(Report.RPDailyProduction, reportTemplateMap.get("RPDailyProduction"));
        reportMap.put(Report.RRPeriode, reportTemplateMap.get("RRPeriode"));
        reportMap.put(Report.TSPeriode, reportTemplateMap.get("TSPeriode"));
        reportMap.put(Report.CanvassingHistory, reportTemplateMap.get("CanvassingHistory"));

        reportMap.put(Report.FWSL, reportTemplateMap.get("wshr"));
        reportMap.put(Report.FGBadStockReport, reportTemplateMap.get("badStockCard"));
        reportMap.put(Report.FGTunaVayaReport, reportTemplateMap.get("viandTunaInv"));
        reportMap.put(Report.FGFM, reportTemplateMap.get("FGFM"));
        reportMap.put(Report.FGFO, reportTemplateMap.get("FGFO"));
        reportMap.put(Report.Accounting, reportTemplateMap.get("stockCardReport"));
        
         // iCore Module | Report List
        reportTemplateMap.put("reportBC23", new ReportModel("bccode/RptBcCode_bc23", false));
        reportMap.put(Report.reportBC23, reportTemplateMap.get("reportBC23"));
        
        reportTemplateMap.put("reportBC40", new ReportModel("bccode/RptBcCode_bc40", false));
        reportMap.put(Report.reportBC40, reportTemplateMap.get("reportBC40"));
        
        reportTemplateMap.put("reportBC30", new ReportModel("bccode/RptBcCode_bc30", false));
        reportMap.put(Report.reportBC30, reportTemplateMap.get("reportBC30"));
        
        reportTemplateMap.put("reportBC41", new ReportModel("bccode/RptBcCode_bc41", false));
        reportMap.put(Report.reportBC41, reportTemplateMap.get("reportBC41"));
        
        reportTemplateMap.put("reportFinishGood", new ReportModel("bccode/RptBcCode_bc_finishGood", false));
        reportMap.put(Report.reportFinishGood, reportTemplateMap.get("reportFinishGood"));

        IGNORED_COLUMN.add("index");
    }

    public static void main(String[] str) {
        try {
            String name = "wsnr";
            OutputStream xls = new FileOutputStream("Report.xls");
            OutputStream pdf = new FileOutputStream("Report.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] getReportPDF(Report reportType, List data, Map map) throws Exception {
        return reportMap.get(reportType).getReportPDF(data, map);
    }

    public static byte[] getReportXLS(Report reportType, List data, Map map) throws Exception {
        return reportMap.get(reportType).getReportXLS(data, map);
    }

    public static byte[] getReportCSV(Report reportType, List data, Map map) throws Exception {
//		return reportMap.get(reportType).getReportCSV(data, map);
        String csv = "";
        List<ResultSetRow> m = (List<ResultSetRow>) data;
        if (m == null) {
            m = (List<ResultSetRow>) map.get("data");
        }
        List<String> column = REPORT_COLUMN.get(reportType);
        if (column == null) {
            if (!m.isEmpty()) {
                column = new ArrayList<String>(m.get(0).keySet());
            }
        }
        for (String c : column) {
            if (!IGNORED_COLUMN.contains(c)) {
                csv += c + ",";
            }
        }
        csv += "\r\n";
        for (ResultSetRow r : m) {
            for (String c : column) {
                if (!IGNORED_COLUMN.contains(c)) {
                    csv += r.get(c) + ",";
                }
            }
            csv += "\r\n";
        }
        return csv.getBytes();
    }

    public static byte[] getReportHTML(Report reportType, List data, HashMap<String, Object> map) {
        return reportMap.get(reportType).getReportHTML(data, map);
    }
}
