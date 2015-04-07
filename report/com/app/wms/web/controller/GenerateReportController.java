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
import java.util.EnumMap;

@SuppressWarnings({"rawtypes", "unchecked"})
public class GenerateReportController extends MultiActionController {

    public static final Map<Report, Object> ListMap = new EnumMap<Report, Object>(Report.class);
    public static final Map<Report, PostProcess> PostProcess = new EnumMap<Report, PostProcess>(Report.class);

    static {
        //  Fish Module | Form and Report List
        ListMap.put(Report.FishWSHR, "EXEC PRT_F_WS ?, ?");
        ListMap.put(Report.FishWSNC, "EXEC PRT_F_WS ?, ?");
        ListMap.put(Report.FishWSBF, "EXEC PRT_F_WS ?, ?");
        ListMap.put(Report.FishWSNR, "EXEC PRT_F_WS ?, ?");
        ListMap.put(Report.FishWSL, "EXEC PRT_F_WS ?, ?");
        ListMap.put(Report.FishWSABF, "EXEC PRT_F_WS ?, ?");
        ListMap.put(Report.FishSR, "EXEC PRT_F_SR ?, ?, ?");
        ListMap.put(Report.FishWssFresh, "EXEC RPT_F_WSS ?, ?, ?, ?");
        ListMap.put(Report.FishWssFrozen, "EXEC RPT_F_WSS ?, ?, ?, ?");
        ListMap.put(Report.FishRR, "EXEC PRT_F_RR ?");
//        ListMap.put(Report.FishSumPerSupp, "EXEC RPT_FR_SUMMARY_PER_SUPPLIER ?");
//        ListMap.put(Report.FishSumPerCS, "EXEC RPT_FR_SUMMARY_PER_COLDSTORAGE ?");
//        ListMap.put(Report.FishStockCard, "EXEC RPT_FR_STOCK_CARD ?");
        ListMap.put(Report.FishTS, "EXEC PRT_F_TRANSFER_SLIP ?");
        ListMap.put(Report.FishBF, "EXEC PRT_F_BF ?");
        ListMap.put(Report.FishABF, "EXEC PRT_F_ABF ?");
//        ListMap.put(Report.FishSumPerSuppActual, "EXEC RPT_FR_SUMMARY_PER_SUPPLIER_ACTUAL ?");
//        ListMap.put(Report.FishSumPerCSActual, "EXEC RPT_FR_SUMMARY_PER_COLDSTORAGE_ACTUAL ?");
//        ListMap.put(Report.FishStockCardActual, "EXEC RPT_FR_STOCK_CARD_ACTUAL ?");
        ListMap.put(Report.FishDailyInCS, "EXEC RPT_F_DAILY_IN_CS ?, ?");
        ListMap.put(Report.FishSummaryInCS, "EXEC RPT_F_SUMMARY_IN_CS ?, ?");
        ListMap.put(Report.FishWDS, "EXEC PRT_F_WDS ?");
        ListMap.put(Report.FishRECC, "EXEC PRT_F_RECLASSIFICATION ?");
        ListMap.put(Report.FishFMov, "EXEC PRT_F_FISH_MOVING ?");
        ListMap.put(Report.FishWsSF, "EXEC PRT_F_WSS ?");
        ListMap.put(Report.FishWsSZ, "EXEC PRT_F_WSS ?");
        //  ***END*** | Fish Module | Form and Report List

        //  Purcashing Module | Form and Report List
        ListMap.put(Report.PRCPrs, "EXEC PRT_PRC_PURCHASE_REQUISITION_SLIP ?");
        ListMap.put(Report.PRCPo, "EXEC PRT_PRC_PURCHASE_ORDER ?, ?");
        ListMap.put(Report.PRCPrsNotYetPo, "EXEC RPT_PRC_PRS_NOT_YET_PO ?, ?, ?");
        ListMap.put(Report.PRCPoNotYetRr, "EXEC RPT_PRC_PO_NOT_YET_RR ?, ?, ?, ?");
        ListMap.put(Report.PRCPoRegisteredPerPeriod, "EXEC RPT_PRC_PO_PER_PERIODE ?, ?");
        ListMap.put(Report.PRCPoRegisteredPerPeriodConfirmatory, "EXEC RPT_PRC_PO_PER_PERIODE_CONFIRMATORY ?, ?");
        ListMap.put(Report.PRCPoRegisteredPerDepartment, "EXEC RPT_PRC_PO_PER_DEPARTMENT ?, ?");
        ListMap.put(Report.PRCPoRegisteredPerItem, "EXEC RPT_PRC_PO_PER_ITEM ?");
        ListMap.put(Report.PRCPoRegisteredPerSupplier, "EXEC RPT_PRC_PO_PER_SUPPLIER ?");
        ListMap.put(Report.PRCPrsRegister, "EXEC RPT_PRC_PRS_REGISTER ?, ?, ?");
        //  ***END*** | Purcashing Module | Form and Report List

        //  Non-Fish Module | Form and Report List
        ListMap.put(Report.NFRr, "EXEC PRT_NF_RR ?");
        ListMap.put(Report.NFSws, "EXEC PRT_NF_SWS ?");
        ListMap.put(Report.NFSwsP, "EXEC PRT_NF_SWS ?");
        ListMap.put(Report.NFTs, "EXEC PRT_NF_TS ?");
        ListMap.put(Report.NFDr, "EXEC PRT_NF_DR ?");
        ListMap.put(Report.NFSIPerCat, "EXEC RPT_NF_STOCK_INVENTORY_PER_CATEGORY ?, ?, ?, ?, ?");
        ListMap.put(Report.NFTPerCat, "EXEC RPT_NF_TRANSACTION_PER_CATEGORY ?, ?, ?");
        ListMap.put(Report.NFRrRegisterPerPeriod, "EXEC RPT_NF_RECEIVING_PER_PERIOD ?, ?");
        ListMap.put(Report.NFSwsRegisterPerPeriod, "EXEC RPT_NF_STORES_WITHDRAWAL_PER_PERIOD ?, ?, ?");
        ListMap.put(Report.NFTsRegisterPerPeriod, "EXEC RPT_NF_TRANSFER_PER_PERIOD ?, ?, ?");
        ListMap.put(Report.NFDrRegisterPerPeriod, "EXEC RPT_NF_DELIVERY_PER_PERIOD ?, ?");
        //  ***END*** | Non-Fish Module | Form and Report List

        //  Finished Goods Module | Form and Report List
        ListMap.put(Report.FGBor, "EXEC PRT_FG_BOOKED_ORDER_REPORT ?");
        ListMap.put(Report.FGBor15, "EXEC PRT_FG_BOR15_REPORT ?");
        ListMap.put(Report.FGPts, "EXEC PRT_FG_PALLET_TRANSFER_SLIP ?");
        ListMap.put(Report.FGOfal, "EXEC PRT_FG_ORDERFILL_AUTHORITY_TO_LABEL ?");
        ListMap.put(Report.FGLmr, "EXEC PRT_FG_LABELING_MONITORING_REPORT ?");
        ListMap.put(Report.FGPtsPerPeriod, "EXEC RPT_FG_PALLET_TRANSFER_REGISTER ?, ?, ?");
        ListMap.put(Report.FGStockInventory, "EXEC RPT_FG_STOCK_INVENTORY_PER_PACKSIZE ?, ?");
        ListMap.put(Report.FGPtsCheckList, "EXEC RPT_FG_PTS_CHECKLIST");
        ListMap.put(Report.FGActualInventory, "EXEC RPT_FG_INVENTORY_PER_COUNT ?, ?");
        ListMap.put(Report.FGBor15Report, "EXEC RPT_FG_BOR15_REPORT ?, ?");
        //  ***END*** | Finished Goods Module | Form and Report List    

        //  Rendering Module | Form and Report List
        ListMap.put(Report.RendDailyReport, "EXEC RPT_REND_DAILY ?");
        ListMap.put(Report.RendSummaryReport, "EXEC RPT_REND_SUMMARY ?, ?");
        ListMap.put(Report.RendSales, "EXEC PRT_REND_SALES ?");
        //  ***END*** | Finished Goods Module | Form and Report List
        
        //  Master Module | Report List
        ListMap.put(Report.MSupplier, "EXEC RPT_M_SUPPLIER");
        ListMap.put(Report.MProduct, "EXEC RPT_M_PRODUCT");
        //  ***END*** | Finished Goods Module | Form and Report List

        ListMap.put(Report.FWS,
                "SELECT f.code AS kode, wdsd.description AS nama_barang, "
                + "'FISH' AS tipe, wdsd.qty, wdsd.uom_code AS satuan "
                + "FROM fish_wds_detail wdsd "
                + "LEFT JOIN fish f ON f.id = wdsd.fish_id "
                + "LEFT JOIN fish_storage fs ON fs.id = wdsd.storage_id "
                + "WHERE wds_id=?"
        );

        ListMap.put(Report.FWSABF, null);

        ListMap.put(Report.FWSBR, null);

        ListMap.put(Report.FWSHR, null);

        ListMap.put(Report.FWSL, null);

        ListMap.put(Report.FWSNC, null);

        ListMap.put(Report.FWeightSlip,
                "SELECT f.code, SUM(wsd.total_weight) AS total_weight "
                + "FROM fish_ws_detail wsd "
                + "LEFT JOIN fish f ON f.id = wsd.fish_id "
                + "WHERE wsd.ws_id = ? "
                + "GROUP BY f.code"
        );

        ListMap.put(Report.FRR,
                "SELECT MAX(ft.description) AS description, f.code, "
                + "SUM(frd.good_weight) AS qty, 'Kg' AS unit "
                + "FROM fish_rr_detail frd "
                + "LEFT JOIN fish f ON f.id = frd.fish_id "
                + "LEFT JOIN fish_type ft ON ft.id = f.fish_type_id "
                + "WHERE frd.rr_id = ? "
                + "GROUP BY f.code"
        );

        ListMap.put(Report.FTS,
                "SELECT tsd.description, f.code, "
                + "tsd.qty, tsd.uom_code AS unit "
                + "FROM fish_ts_detail tsd "
                + "LEFT JOIN fish f ON f.id = tsd.fish_id "
                + "LEFT JOIN fish_storage fs ON fs.id = tsd.storage_id "
                + "WHERE ts_id=?"
        );

        ListMap.put(Report.FSummaryWSSlip,
                "SELECT ws.ws_no, su.name AS supplier_name, fv.name AS boat_name,"
                + "fv.batch_no, ws.date_shift, ws.time_shift, f.code AS type, wsd.total_weight AS data,"
                + "(SELECT ISNULL(SUM(fs.cooked_weight), 0.00)FROM fish_spoilage fs "
                + "WHERE fs.vessel_id = ws.vessel_id AND fs.fish_id = wsd.fish_id "
                + "AND fs.date_shift = ws.date_shift) AS spoilage "
                + "FROM fish_ws_detail wsd "
                + "LEFT JOIN fish_ws ws ON ws.id = wsd.ws_id "
                + "LEFT JOIN fish_vessel fv ON fv.id = ws.vessel_id "
                + "LEFT JOIN fish_supplier su ON su.id = fv.supplier_id "
                + "LEFT JOIN fish f ON f.id = wsd.fish_id "
                + "LEFT JOIN fish_ws_type fwt ON fwt.id = ws.ws_type_id "
                + "WHERE ws.vessel_id = ? AND replace(convert(varchar, ws.date_shift, 111), '/', '-') = ? "
                + "AND (fwt.code = 'WSBF' OR fwt.code = 'WSABF' OR fwt.code = 'WSNC') "
        );

        ListMap.put(Report.FSpoilagereport,
                "SELECT catcher_no, f.code, cooked_weight, raw_weight, total_processed, reason, batch_no, date_shift, time_shift, supplier_name "
                + "FROM fish_spoilage fs "
                + "LEFT JOIN fish f ON fs.fish_id = f.id "
                + "LEFT JOIN fish_vessel fv ON fv.id = fs.vessel_id "
                + "LEFT JOIN fish_supplier s ON fv.supplier_id = s.id "
                + "WHERE fv.id = ? AND fs.date_shift = ? AND fs.time_shift = ?"
        );

        ListMap.put(Report.RPDailyProduction,
                "DECLARE  @YEAR INT, @MONTH INT, @PERIODE VARCHAR(30), @DATE VARCHAR(10) "
                + "SET @YEAR = ? SET @MONTH = ? SET @DATE = (CAST(@YEAR AS VARCHAR(4)) + '-' + CAST(@MONTH AS VARCHAR(2)) + '-01') "
                + "SET @PERIODE = DATENAME(MONTH, @DATE) + ' ' + LEFT(CONVERT(VARCHAR(10), DATEADD(s,-1,DATEADD(mm, DATEDIFF(m,0, @DATE)+1,0)), 105), 2) + ', ' + CAST(@YEAR AS VARCHAR(4)) "
                + "SELECT CONVERT(VARCHAR(10), fmd.fm_date, 105) date, fmd.fm_bi_bags bags_begin, fmd.fm_bi_kilos kilos_begin, fmd.fm_ttd_bags bags_ttd, fmd.fm_ttd_kilos kilos_ttd, fmd.fm_i_bags bags_issuances, fmd.fm_i_kilos kilos_issuances, fmd.fm_i_price qty_issuances, fmd.fm_ei_bags bags_end, fmd.fm_ei_kilos kilos_end, ? title, @PERIODE periode, "
                + "SUBSTRING(sil.log, 1, CHARINDEX(':', sil.log) - 1) adj_bags, SUBSTRING(sil.log, CHARINDEX(':', sil.log) + 1, LEN(sil.log)) adj_kilos "
                + "FROM fishmeal fm "
                + "	LEFT JOIN fishmeal_detail fmd ON fmd.fm_id = fm.fm_id "
                + "       LEFT JOIN stock_inventory_log sil ON sil.product_code = ? AND YEAR(sil.created_date) = @YEAR AND MONTH(sil.created_date) = @MONTH "
                + "WHERE fm.fm_year = @YEAR AND fm.fm_month = @MONTH ORDER BY fmd.fm_date"
        );

        ListMap.put(Report.FGBOR,
                "DECLARE "
                + "	@cols AS VARCHAR(MAX), "
                + "	@colsin AS VARCHAR(MAX), "
                + "	@query AS VARCHAR(MAX), "
                + "       @id AS VARCHAR(30); "
                + "       SET @id = ?; "
                + "	SET @cols = STUFF((SELECT distinct  ',' + QUOTENAME(bd.id) + ' AS data' + CAST(ROW_NUMBER() OVER (ORDER BY id) AS VARCHAR(10)) "
                + "		FROM bor_detail bd WHERE bd.bor_code = @id FOR XML PATH(''), TYPE).value('.', 'VARCHAR(MAX)') "
                + "		, 1, 1, ''); "
                + "		 "
                + "	SET @colsin = STUFF((SELECT distinct  ',' + QUOTENAME(bd.id) "
                + "		FROM bor_detail bd WHERE bd.bor_code = @id FOR XML PATH(''), TYPE).value('.', 'VARCHAR(MAX)') "
                + "		, 1, 1, ''); "
                + "	 "
                + "	SET @query = 'SELECT id = col, title, ' + @cols + ', bor.bor_code, CONVERT(VARCHAR(30), bor.bor_date, 106) as bor_date,  "
                + "		bor.prepared_by, CONVERT(VARCHAR(30), bor.prepared_date, 106) as prepared_date, bor.reviewed_by,  "
                + "		CONVERT(VARCHAR(30), bor.reviewed_date, 106) as reviewed_date FROM ( "
                + "		SELECT id, col, title, value FROM ( "
                + "			SELECT * FROM bor_detail bd  "
                + "			WHERE bd.bor_code =  ''' + @id + ''' "
                + "		) as q "
                + "		CROSS APPLY( "
                + "			SELECT 1, ''Pack Style'', bor_packstyle UNION ALL "
                + "			SELECT 2, ''Can Size / NW'', bor_cansize UNION ALL "
                + "			SELECT 3, ''Qty (FCL)'', CAST(bor_qty as VARCHAR(10)) + '' FCL'' UNION ALL "
                + "			SELECT 4, ''Cases per FCL'', CAST(bor_cs as VARCHAR(10)) + '' CS'' UNION ALL "
                + "			SELECT 5, ''CNF Prise / case'', CAST(bor_cnfprice as VARCHAR(10)) UNION ALL "
                + "			SELECT 6, ''Commission'', bor_commission UNION ALL "
                + "			SELECT 7, ''Buyer'', bor_buyer UNION ALL "
                + "			SELECT 8, ''Label / Brand'', bor_brand UNION ALL "
                + "			SELECT 9, ''Shipment Date'', bor_shipmentdate UNION ALL "
                + "			SELECT 10, ''Destination Port'', bor_destinationport UNION ALL "
                + "			SELECT 11, ''PO Number'', bor_ponumber UNION ALL "
                + "			SELECT 12, ''Other'', NULL UNION ALL "
                + "			SELECT 13, ''       Drained / Pressed Wt'', CAST(bor_o_dpw as VARCHAR(10)) + '' g'' UNION ALL "
                + "			SELECT 14, ''       Freight Terms'', bor_o_ft UNION ALL "
                + "			SELECT 15, ''       Payment Terms'', bor_o_pt UNION ALL "
                + "			SELECT 16, ''       GSP Form'', bor_o_gf UNION ALL "
                + "			SELECT 17, ''       Can Code Max'', bor_o_ccm UNION ALL "
                + "			SELECT 18, ''       Oil Medium'', bor_o_om UNION ALL "
                + "			SELECT 19, ''       GMO / Non GMO'', bor_o_gmo UNION ALL "
                + "			SELECT 20, ''       Extra Cartons / FCL'', bor_o_ec UNION ALL "
                + "			SELECT 21, ''       Percent Flakes'', bor_o_pf UNION ALL "
                + "			SELECT 22, ''       Oil Water Ratio'', bor_o_owr UNION ALL "
                + "			SELECT 23, ''       Number of Loins'', bor_o_nol UNION ALL "
                + "			SELECT 24, ''       Additional Info'', bor_o_info  "
                + "		) c(col, title, value) "
                + "	) q "
                + "	PIVOT ( "
                + "		MAX(value) "
                + "		FOR id IN (' + @colsin + ') "
                + "	) pvt  "
                + "	LEFT JOIN bor ON bor.bor_code = ''' + @id + ''' "
                + "	ORDER BY id ASC'; "
                + "		 "
                + "	execute (@query);"
        );

        ListMap.put(Report.FGTunaVayaReport, null);

        ListMap.put(Report.FDailyInventoryFrozenFish, // added by edw
                "SELECT\n"
                + "    dbo.fish_storage.code,\n"
                + "    sum(dbo.fish_ws_detail.total_weight) as data,\n"
                + "      dbo.fish.code as type\n"
                + "FROM\n"
                + "    dbo.fish_storage\n"
                + "left JOIN dbo.fish_ws\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_storage.id = dbo.fish_ws.storage_id\n"
                + "    )\n"
                + "left  JOIN dbo.fish_ws_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_ws.id = dbo.fish_ws_detail.ws_id\n"
                + "    ) \n"
                + "left  JOIN dbo.fish\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_ws_detail.fish_id = dbo.fish.id\n"
                + "    ) \n"
                + "where fish_storage.is_delete = 'N'\n"
                + "group by dbo.fish_storage.code, dbo.fish.code"
        );

        ListMap.put(Report.FFrozenFishPerBatch, // added by edw
                "SELECT\n"
                + "    dbo.fish_vessel.batch_no,\n"
                + "    sum(dbo.fish_ws_detail.total_weight) as data,\n"
                + "      dbo.fish.code as type\n"
                + "FROM\n"
                + "    dbo.fish_vessel\n"
                + "left JOIN dbo.fish_ws\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_vessel.id = dbo.fish_ws.vessel_id\n"
                + "    )\n"
                + "left  JOIN dbo.fish_ws_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_ws.id = dbo.fish_ws_detail.ws_id\n"
                + "    ) \n"
                + "left  JOIN dbo.fish\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_ws_detail.fish_id = dbo.fish.id\n"
                + "    ) \n"
                + "group by dbo.fish_vessel.batch_no, dbo.fish.code"
        );

        ListMap.put(Report.FLaporanPemasukanBarangPerDokumenPabean, // added by edw 
                "SELECT\n"
                + "    dbo.rr.rr_from,\n" + " "
                + "    dbo.product.product_code,\n"
                + "    dbo.product.product_name,\n"
                + "    dbo.rr_detail.uom,\n"
                + "    dbo.rr_detail.qty_g,\n"
                + "    dbo.product_price.unit_price\n"
                + "FROM\n"
                + "    dbo.rr\n"
                + "INNER JOIN dbo.rr_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr.rr_code = dbo.rr_detail.rr_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr_detail.product_code = dbo.product.product_code\n"
                + "    ) \n"
                + "    \n"
                + "INNER JOIN dbo.product_category\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_category = dbo.product_category.category_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_price\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_code = dbo.product_price.product_code\n"
                + "    )\n"
                + "where rr_date >= ? AND rr_date <= ? and product_category.category_code = ?"
        );

        ListMap.put(Report.FLaporanPengeluaranBarangPerDokumenPabean, // added by edw 
                "SELECT\n"
                + "    dbo.dr_detail.dr_code,\n"
                + "    dbo.supplier.supplier_name,\n"
                + "    dbo.dr_detail.product_code,\n"
                + "    dbo.product.product_name,\n"
                + "    dbo.dr_detail.dr_qty,\n"
                + "    dbo.dr_detail.dr_uom,\n"
                + "    dbo.product_price.unit_price\n"
                + "FROM\n"
                + "    dbo.dr\n"
                + "INNER JOIN dbo.dr_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.dr.dr_code = dbo.dr_detail.dr_code\n"
                + "    )\n"
                + "INNER JOIN dbo.supplier\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.dr.supplier_code = dbo.supplier.supplier_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.dr_detail.product_code = dbo.product.product_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_price\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.dr_detail.product_code = dbo.product_price.product_code\n"
                + "    )\n"
                + "WHERE dr_date >= ? AND dr_date <= ? and supplier.supplier_code= ? "
        );

        ListMap.put(Report.FLaporanPengeluaranBahanBakuDanBahanPenolong, // added by edw 
                "DECLARE @DATE1 VARCHAR(10), @DATE2 VARCHAR(10)\n"
                + "SET @DATE1 = ?\n"
                + "SET @DATE2 = ?\n"
                + "SELECT\n"
                + "    \n"
                + "    dbo.product.product_code,\n"
                + "    dbo.product.product_name, \n"
                + "    dbo.rr_detail.uom,\n"
                + "    sum(dbo.rr_detail.qty_g) as qyt_g,\n"
                + "    sum(dbo.ts_detail.qty) as qyt_a\n"
                + "FROM\n"
                + "    dbo.rr\n"
                + "INNER JOIN dbo.rr_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr.rr_code = dbo.rr_detail.rr_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr_detail.product_code = dbo.product.product_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_category\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_category = dbo.product_category.category_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_price\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_code = dbo.product_price.product_code\n"
                + "    )\n"
                + "INNER JOIN dbo.ts_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.ts_detail.product_code = dbo.rr_detail.product_code\n"
                + "    )\n"
                + "WHERE\n"
                + "rr_date >= @DATE1 and rr_date <= @DATE2 and ts_detail.created_date >= @DATE1 and ts_detail.created_date <= @DATE2 and\n"
                + "    (\n"
                + "        product_category.category_code = 'OS'\n"
                + "     OR product_category.category_code = 'FS'\n"
                + "    )\n"
                + "group by product.product_code, product.product_name, rr_detail.uom"
        );

        ListMap.put(Report.FLaporanPengeluaranMutasiBarangJadi, // added by edw 
                "DECLARE @DATE1 VARCHAR(10), @DATE2 VARCHAR(10)\n"
                + "SET @DATE1 = ?\n"
                + "SET @DATE2 = ?\n"
                + "SELECT\n"
                + "    \n"
                + "    dbo.product.product_code,\n"
                + "    dbo.product.product_name, \n"
                + "    dbo.rr_detail.uom,\n"
                + "    sum(dbo.rr_detail.qty_g) as qyt_g,\n"
                + "    sum(dbo.ts_detail.qty) as qyt_a\n"
                + "FROM\n"
                + "    dbo.rr\n"
                + "INNER JOIN dbo.rr_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr.rr_code = dbo.rr_detail.rr_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr_detail.product_code = dbo.product.product_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_category\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_category = dbo.product_category.category_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_price\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_code = dbo.product_price.product_code\n"
                + "    )\n"
                + "INNER JOIN dbo.ts_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.ts_detail.product_code = dbo.rr_detail.product_code\n"
                + "    )\n"
                + "WHERE\n"
                + "rr_date >= @DATE1 and rr_date <= @DATE2 and ts_detail.created_date >= @DATE1 and ts_detail.created_date <= @DATE2 and\n"
                + "    (\n"
                + "        product_category.category_code = 'OS'\n"
                + "     OR product_category.category_code = 'FS'\n"
                + "    )\n"
                + "group by product.product_code, product.product_name, rr_detail.uom"
        );

        ListMap.put(Report.FLaporanPengeluaranMutasiBarangDanScrap, // added by edw 
                "DECLARE @DATE1 VARCHAR(10), @DATE2 VARCHAR(10)\n"
                + "SET @DATE1 = ?\n"
                + "SET @DATE2 = ?\n"
                + "SELECT\n"
                + "    \n"
                + "    dbo.product.product_code,\n"
                + "    dbo.product.product_name, \n"
                + "    dbo.rr_detail.uom,\n"
                + "    sum(dbo.rr_detail.qty_g) as qyt_g,\n"
                + "    sum(dbo.ts_detail.qty) as qyt_a\n"
                + "FROM\n"
                + "    dbo.rr\n"
                + "INNER JOIN dbo.rr_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr.rr_code = dbo.rr_detail.rr_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr_detail.product_code = dbo.product.product_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_category\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_category = dbo.product_category.category_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_price\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_code = dbo.product_price.product_code\n"
                + "    )\n"
                + "INNER JOIN dbo.ts_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.ts_detail.product_code = dbo.rr_detail.product_code\n"
                + "    )\n"
                + "WHERE\n"
                + "rr_date >= @DATE1 and rr_date <= @DATE2 and ts_detail.created_date >= @DATE1 and ts_detail.created_date <= @DATE2 and\n"
                + "    (\n"
                + "        product_category.category_code = 'OS'\n"
                + "     OR product_category.category_code = 'FS'\n"
                + "    )\n"
                + "group by product.product_code, product.product_name, rr_detail.uom"
        );

        ListMap.put(Report.FLaporanPengeluaranMutasiMesin, // added by edw 
                "DECLARE @DATE1 VARCHAR(10), @DATE2 VARCHAR(10)\n"
                + "SET @DATE1 = ?\n"
                + "SET @DATE2 = ?\n"
                + "SELECT\n"
                + "    \n"
                + "    dbo.product.product_code,\n"
                + "    dbo.product.product_name, \n"
                + "    dbo.rr_detail.uom,\n"
                + "    sum(dbo.rr_detail.qty_g) as qyt_g,\n"
                + "    sum(dbo.ts_detail.qty) as qyt_a\n"
                + "FROM\n"
                + "    dbo.rr\n"
                + "INNER JOIN dbo.rr_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr.rr_code = dbo.rr_detail.rr_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr_detail.product_code = dbo.product.product_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_category\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_category = dbo.product_category.category_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_price\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_code = dbo.product_price.product_code\n"
                + "    )\n"
                + "INNER JOIN dbo.ts_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.ts_detail.product_code = dbo.rr_detail.product_code\n"
                + "    )\n"
                + "WHERE\n"
                + "rr_date >= @DATE1 and rr_date <= @DATE2 and ts_detail.created_date >= @DATE1 and ts_detail.created_date <= @DATE2 and\n"
                + "    (\n"
                + "        product_category.category_code = 'OS'\n"
                + "     OR product_category.category_code = 'FS'\n"
                + "    )\n"
                + "group by product.product_code, product.product_name, rr_detail.uom"
        );

        ListMap.put(Report.FGEDS,
                "");

        ListMap.put(Report.FMDR, "SELECT * "
                + "FROM dbo.dr dr, dbo.dr_detail drd "
                + "WHERE dr.drnumber = drd.drnumber");

        ListMap.put(Report.RRPeriode,
                "DECLARE @YEAR INT, @MONTH INT "
                + "SET @YEAR = ? "
                + "SET @MONTH = ? "
                + "SELECT rr.rr_code, CONVERT(VARCHAR(10), rr.rr_date, 105) rr_date, rr.rr_from, p.product_code, p.product_name, rrd.qty_g, rrd.qty_b, rrd.uom, "
                + "	rr.po_code, CONVERT(VARCHAR(10), po.po_date, 105) po_date, u.name, po.remarks, ? periode "
                + "FROM rr "
                + "	INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code "
                + "	INNER JOIN product p ON p.product_code = rrd.product_code "
                + "	INNER JOIN po ON po.po_code = rr.po_code "
                + "	LEFT JOIN \"user\" u ON u.user_id = po.created_by "
                + "WHERE YEAR(rr.rr_date) = @YEAR AND MONTH(rr.rr_date) = @MONTH ORDER BY rr.rr_code"
        );

        ListMap.put(Report.TSPeriode,
                "DECLARE @YEAR INT, @MONTH INT "
                + "SET @YEAR = ? "
                + "SET @MONTH = ? "
                + "SELECT ts.ts_code, CONVERT(VARCHAR(10), ts.ts_date, 105) ts_date, p.product_code, p.product_name, pc.category_name, tsd.qty,  "
                + "	p.uom_name, ? periode "
                + "FROM ts "
                + "	INNER JOIN ts_detail tsd ON tsd.ts_code = ts.ts_code "
                + "	LEFT JOIN product p ON p.product_code = tsd.product_code "
                + "	LEFT JOIN product_category pc ON pc.category_code = p.product_category "
                + "WHERE YEAR(ts.ts_date) = @YEAR AND MONTH(ts.ts_date) = @MONTH "
                + "ORDER BY ts_code"
        );

        ListMap.put(Report.CanvassingHistory,
                "SELECT po.po_code, CONVERT(VARCHAR(10), po.po_date, 105) po_date, p.product_code, p.product_name, acp.prsnumber, s.supplier_name, p.uom_name, "
                + "acp.unit_price, acp.[top], acp.top_desc, acp.tod, CONVERT(VARCHAR(10), acp.wp, 105)wp FROM po "
                + "	INNER JOIN po_detail pod ON pod.po_code = po.po_code "
                + "	INNER JOIN assign_canv_prc acp ON acp.productcode = pod.product_code AND acp.prsnumber = pod.prsnumber "
                + "	LEFT JOIN product p ON p.product_code = pod.product_code "
                + "	LEFT JOIN supplier s ON s.supplier_code = acp.supplier_code "
                + "WHERE po.po_code = ? AND pod.is_active = 'Y' "
                + "ORDER BY prsnumber, product_code, (CASE WHEN is_selected = 'Y' THEN 0 ELSE 1 END), supplier_name"
        );

        ListMap.put(Report.FGBadStockReport,
                "select * "
                + "from product p "
                + "inner join product_category pc "
                + "on p.product_category = pc.category_name "
                + "inner join stock_balance s "
                + "on p.product_code = s.product_code "
                + "inner join stock_inventory si "
                + "on p.product_code = si.product_code"
        );

        ListMap.put(Report.FFrozenFishStock, new String[]{
            "DECLARE @sDate DATETIME "
            + "SET @sDate = ? "
            + "DECLARE @eDate DATETIME "
            + "SET @eDate = ? "
            + "SELECT f.code, SUM(fb.balance) rqty, SUM(fwd.total_weight) wqty, SUM(frd.good_weight) eqty,  convert(NVARCHAR, @eDate, 106) as edate " + // added by edw, fixing date format on ms.sqlserver 2008
            "FROM fish f "
            + "LEFT JOIN fish_balance fb ON f.id = fb.fish_id "
            + "LEFT JOIN fish_ws_detail fwd ON f.id = fwd.fish_id "
            + "LEFT JOIN fish_rr_detail frd ON f.id = fwd.fish_id "
            + "LEFT JOIN fish_ws fw ON fwd.ws_id = fw.id "
            + "LEFT JOIN fish_ws_type fwt ON fw.ws_type_id = fwt.id "
            + "WHERE  fb.created_date >= @sDate AND fb.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') OR "
            + "fwd.created_date >= @sDate AND fwd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') OR "
            + "frd.created_date >= @sDate AND frd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') "
            + "GROUP BY f.code "
            + "ORDER BY f.code",
            "SELECT fv.name, SUM(fb.balance) qty "
            + "FROM fish_balance fb "
            + "LEFT JOIN fish_vessel fv ON fb.vessel_id = fv.id "
            + "WHERE fb.created_date >= ? AND fb.created_date <= ? "
            + "GROUP BY fv.name"
        });

        ListMap.put(Report.FFrozenFishStockCS, new String[]{
            "DECLARE @sDate DATETIME "
            + "SET @sDate = ? "
            + "DECLARE @eDate DATETIME "
            + "SET @eDate = ? "
            + "SELECT f.code, SUM(fb.balance) rqty, SUM(fwd.total_weight) wqty, SUM(frd.good_weight) eqty,  convert(NVARCHAR, @eDate, 106) as edate " + // added by edw, fixing date format on ms.sqlserver 2008
            "FROM fish f "
            + "LEFT JOIN fish_balance fb ON f.id = fb.fish_id "
            + "LEFT JOIN fish_ws_detail fwd ON f.id = fwd.fish_id "
            + "LEFT JOIN fish_rr_detail frd ON f.id = fwd.fish_id "
            + "LEFT JOIN fish_ws fw ON fwd.ws_id = fw.id "
            + "LEFT JOIN fish_ws_type fwt ON fw.ws_type_id = fwt.id "
            + "WHERE  fb.created_date >= @sDate AND fb.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fw.storage_id = ? OR "
            + "fwd.created_date >= @sDate AND fwd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fw.storage_id = ? OR "
            + "frd.created_date >= @sDate AND frd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fw.storage_id = ? "
            + "GROUP BY f.code "
            + "ORDER BY f.code",
            "SELECT fv.name, SUM(fb.balance) qty "
            + "FROM fish_balance fb "
            + "LEFT JOIN fish_vessel fv ON fb.vessel_id = fv.id "
            + "WHERE fb.created_date >= ? AND fb.created_date <= ? or (fv.code = ? or fv.code = ? or fv.code = ? ) "
            + "GROUP BY fv.name"
        });

        ListMap.put(Report.FFrozenFishStockBatchNo, new String[]{
            "DECLARE @sDate DATETIME "
            + "SET @sDate = ? "
            + "DECLARE @eDate DATETIME "
            + "SET @eDate = ? "
            + "SELECT f.code, SUM(fb.balance) rqty, SUM(fwd.total_weight) wqty, SUM(frd.good_weight) eqty,  convert(NVARCHAR, @eDate, 106) as edate " + // added by edw, fixing date format on ms.sqlserver 2008
            "FROM fish f "
            + "LEFT JOIN fish_balance fb ON f.id = fb.fish_id "
            + "LEFT JOIN fish_ws_detail fwd ON f.id = fwd.fish_id "
            + "LEFT JOIN fish_rr_detail frd ON f.id = fwd.fish_id "
            + "LEFT JOIN fish_ws fw ON fwd.ws_id = fw.id "
            + "LEFT JOIN fish_ws_type fwt ON fw.ws_type_id = fwt.id "
            + " LEFT JOIN fish_vessel fv\n"
            + "    ON\n"
            + "        fw.vessel_id = fv.id "
            + "WHERE  fb.created_date >= @sDate AND fb.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? OR "
            + "fwd.created_date >= @sDate AND fwd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? OR "
            + "frd.created_date >= @sDate AND frd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? "
            + "GROUP BY f.code "
            + "ORDER BY f.code",
            "SELECT fv.name, SUM(fb.balance) qty "
            + "FROM fish_balance fb "
            + "LEFT JOIN fish_vessel fv ON fb.vessel_id = fv.id "
            + "WHERE fb.created_date >= ? AND fb.created_date <= ? or (fv.code = ? or fv.code = ? or fv.code = ? ) "
            + "GROUP BY fv.name"
        });

        ListMap.put(Report.FFrozenFishStockSupplier, new String[]{
            "DECLARE @sDate DATETIME "
            + "SET @sDate = ? "
            + "DECLARE @eDate DATETIME "
            + "SET @eDate = ? "
            + "SELECT f.code, SUM(fb.balance) rqty, SUM(fwd.total_weight) wqty, SUM(frd.good_weight) eqty,  convert(NVARCHAR, @eDate, 106) as edate " + // added by edw, fixing date format on ms.sqlserver 2008
            "FROM fish f "
            + "LEFT JOIN fish_balance fb ON f.id = fb.fish_id "
            + "LEFT JOIN fish_ws_detail fwd ON f.id = fwd.fish_id "
            + "LEFT JOIN fish_rr_detail frd ON f.id = fwd.fish_id "
            + "LEFT JOIN fish_ws fw ON fwd.ws_id = fw.id "
            + "LEFT JOIN fish_ws_type fwt ON fw.ws_type_id = fwt.id "
            + " LEFT JOIN fish_vessel fv\n"
            + "    ON\n"
            + "        fw.vessel_id = fv.id "
            + "WHERE  fb.created_date >= @sDate AND fb.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? OR "
            + "fwd.created_date >= @sDate AND fwd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? OR "
            + "frd.created_date >= @sDate AND frd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? "
            + "GROUP BY f.code "
            + "ORDER BY f.code",
            "SELECT fv.name, SUM(fb.balance) qty "
            + "FROM fish_balance fb "
            + "LEFT JOIN fish_vessel fv ON fb.vessel_id = fv.id "
            + "WHERE fb.created_date >= ? AND fb.created_date <= ? or (fv.code = ? or fv.code = ? or fv.code = ? ) "
            + "GROUP BY fv.name"
        });

        ListMap.put(Report.FDailyInventoryFrozenFish, // added by edw
                "SELECT\n"
                + "    dbo.fish_storage.code,\n"
                + "    sum(dbo.fish_ws_detail.total_weight) as data,\n"
                + "      dbo.fish.code as type\n"
                + "FROM\n"
                + "    dbo.fish_storage\n"
                + "left JOIN dbo.fish_ws\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_storage.id = dbo.fish_ws.storage_id\n"
                + "    )\n"
                + "left  JOIN dbo.fish_ws_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_ws.id = dbo.fish_ws_detail.ws_id\n"
                + "    ) \n"
                + "left  JOIN dbo.fish\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_ws_detail.fish_id = dbo.fish.id\n"
                + "    ) \n"
                + "where fish_storage.is_delete = 'N'\n"
                + "group by dbo.fish_storage.code, dbo.fish.code"
        );

        ListMap.put(Report.FFrozenFishPerBatch, // added by edw
                "SELECT\n"
                + "    dbo.fish_vessel.batch_no,\n"
                + "    sum(dbo.fish_ws_detail.total_weight) as data,\n"
                + "      dbo.fish.code as type\n"
                + "FROM\n"
                + "    dbo.fish_vessel\n"
                + "left JOIN dbo.fish_ws\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_vessel.id = dbo.fish_ws.vessel_id\n"
                + "    )\n"
                + "left  JOIN dbo.fish_ws_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_ws.id = dbo.fish_ws_detail.ws_id\n"
                + "    ) \n"
                + "left  JOIN dbo.fish\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.fish_ws_detail.fish_id = dbo.fish.id\n"
                + "    ) \n"
                + "group by dbo.fish_vessel.batch_no, dbo.fish.code"
        );

        ListMap.put(Report.FLaporanPemasukanBarangPerDokumenPabean, // added by edw 
                "SELECT\n"
                + "    dbo.rr.rr_from,\n" + " "
                + "    dbo.product.product_code,\n"
                + "    dbo.product.product_name,\n"
                + "    dbo.rr_detail.uom,\n"
                + "    dbo.rr_detail.qty_g,\n"
                + "    dbo.product_price.unit_price\n"
                + "FROM\n"
                + "    dbo.rr\n"
                + "INNER JOIN dbo.rr_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr.rr_code = dbo.rr_detail.rr_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.rr_detail.product_code = dbo.product.product_code\n"
                + "    ) \n"
                + "    \n"
                + "INNER JOIN dbo.product_category\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_category = dbo.product_category.category_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_price\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.product.product_code = dbo.product_price.product_code\n"
                + "    )\n"
                + "where rr_date >= ? AND rr_date <= ? and product_category.category_code = ?"
        );

        ListMap.put(Report.FLaporanPengeluaranBarangPerDokumenPabean, // added by edw 
                "SELECT\n"
                + "    dbo.dr_detail.dr_code,\n"
                + "    dbo.supplier.supplier_name,\n"
                + "    dbo.dr_detail.product_code,\n"
                + "    dbo.product.product_name,\n"
                + "    dbo.dr_detail.dr_qty,\n"
                + "    dbo.dr_detail.dr_uom,\n"
                + "    dbo.product_price.unit_price\n"
                + "FROM\n"
                + "    dbo.dr\n"
                + "INNER JOIN dbo.dr_detail\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.dr.dr_code = dbo.dr_detail.dr_code\n"
                + "    )\n"
                + "INNER JOIN dbo.supplier\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.dr.supplier_code = dbo.supplier.supplier_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.dr_detail.product_code = dbo.product.product_code\n"
                + "    )\n"
                + "INNER JOIN dbo.product_price\n"
                + "ON\n"
                + "    (\n"
                + "        dbo.dr_detail.product_code = dbo.product_price.product_code\n"
                + "    )\n"
                + "WHERE dr_date >= ? AND dr_date <= ? and supplier.supplier_code= ? "
        );

        ListMap.put(Report.FGEDS,
                "SELECT eds.*, bd.bor_buyer , bd.bor_packstyle, bd.bor_brand, bd.bor_ponumber, bd.bor_destinationport, qpts.qty, qpts.pts_cancode, "
                + "	SUBSTRING(bd.bor_cansize, 1, CHARINDEX('/', bd.bor_cansize) - 1) bor_cansize, CONVERT(VARCHAR(10), eds.eds_date, 105) cdate "
                + "FROM eds "
                + "	INNER JOIN bor_detail bd ON bd.bor_code = eds.bor_code AND bd.id = eds.bor_id "
                + "	LEFT JOIN ( "
                + "		SELECT o.bor_code, o.bor_id, pts.pts_cancode, SUM(od.qty) qty FROM ofal o "
                + "			INNER JOIN ofal_detail od ON od.ofal_id = o.ofal_id "
                + "			INNER JOIN pts ON pts.pts_code = od.pts_code "
                + "		GROUP BY o.bor_code, o.bor_id, pts.pts_cancode "
                + "	) qpts ON qpts.bor_code = eds.bor_code AND qpts.bor_id = eds.bor_id "
                + "WHERE eds.eds_code = ?");

        ListMap.put(Report.FMDR, "SELECT * "
                + "FROM dbo.dr dr, dbo.dr_detail drd "
                + "WHERE dr.dr_code = ?");

        ListMap.put(Report.RRPeriode,
                "DECLARE @YEAR INT, @MONTH INT "
                + "SET @YEAR = ? "
                + "SET @MONTH = ? "
                + "SELECT rr.rr_code, CONVERT(VARCHAR(10), rr.rr_date, 105) rr_date, rr.rr_from, p.product_code, p.product_name, rrd.qty_g, rrd.qty_b, rrd.uom, "
                + "	rr.po_code, CONVERT(VARCHAR(10), po.po_date, 105) po_date, u.name, po.remarks, ? periode "
                + "FROM rr "
                + "	INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code "
                + "	INNER JOIN product p ON p.product_code = rrd.product_code "
                + "	INNER JOIN po ON po.po_code = rr.po_code "
                + "	LEFT JOIN \"user\" u ON u.user_id = po.created_by "
                + "WHERE YEAR(rr.rr_date) = @YEAR AND MONTH(rr.rr_date) = @MONTH ORDER BY rr.rr_code"
        );

        ListMap.put(Report.TSPeriode,
                "DECLARE @YEAR INT, @MONTH INT "
                + "SET @YEAR = ? "
                + "SET @MONTH = ? "
                + "SELECT ts.ts_code, CONVERT(VARCHAR(10), ts.ts_date, 105) ts_date, p.product_code, p.product_name, pc.category_name, tsd.qty,  "
                + "	p.uom_name, ? periode "
                + "FROM ts "
                + "	INNER JOIN ts_detail tsd ON tsd.ts_code = ts.ts_code "
                + "	LEFT JOIN product p ON p.product_code = tsd.product_code "
                + "	LEFT JOIN product_category pc ON pc.category_code = p.product_category "
                + "WHERE YEAR(ts.ts_date) = @YEAR AND MONTH(ts.ts_date) = @MONTH "
                + "ORDER BY ts_code"
        );

        ListMap.put(Report.CanvassingHistory,
                "SELECT po.po_code, CONVERT(VARCHAR(10), po.po_date, 105) po_date, p.product_code, p.product_name, acp.prsnumber, s.supplier_name, p.uom_name, "
                + "acp.unit_price, acp.[top], acp.top_desc, acp.tod, CONVERT(VARCHAR(10), acp.wp, 105)wp FROM po "
                + "	INNER JOIN po_detail pod ON pod.po_code = po.po_code "
                + "	INNER JOIN assign_canv_prc acp ON acp.productcode = pod.product_code AND acp.prsnumber = pod.prsnumber "
                + "	LEFT JOIN product p ON p.product_code = pod.product_code "
                + "	LEFT JOIN supplier s ON s.supplier_code = acp.supplier_code "
                + "WHERE po.po_code = ?  "
                + "ORDER BY prsnumber, product_code, (CASE WHEN is_selected = 'Y' THEN 0 ELSE 1 END), supplier_name"
        );

        ListMap.put(Report.IMStockCardperCategory,
                "DECLARE @YEAR INT, @MONTH INT, @DAY INT "
                + "SET @YEAR = ? SET @MONTH = ? SET @DAY = ? "
                + "SELECT "
                + "	p.product_code, p.product_name, p.uom_name, "
                + "	CASE WHEN q.[date] < CAST((CAST(@YEAR AS VARCHAR) + '-' + CAST(@MONTH AS VARCHAR) + '-1') AS DATETIME) THEN q.[end] ELSE "
                + "		ISNULL((SELECT TOP(1) sb.[end] FROM stock_balance sb WHERE sb.product_code = p.product_code AND sb.[date] < DATEADD(s,-1,DATEADD(mm, DATEDIFF(m,0,CAST((CAST(@YEAR AS VARCHAR) + '-' + CAST(@MONTH AS VARCHAR) + '-1') AS DATETIME)),0)) "
                + "		ORDER BY sb.[date] DESC), 0) "
                + "	END begin_inventory, "
                + "	CASE WHEN q.[date] < CAST((CAST(@YEAR AS VARCHAR) + '-' + CAST(@MONTH AS VARCHAR) + '-1') AS DATETIME) THEN 0 ELSE "
                + "		ISNULL((SELECT SUM(sb.qty_in1) FROM stock_balance sb WHERE product_code = p.product_code AND YEAR(sb.date) = @YEAR AND MONTH(sb.date) = @MONTH AND DAY(sb.date) BETWEEN 1 AND @DAY "
                + "		GROUP BY sb.product_code), 0) "
                + "	END rr, "
                + "	CASE WHEN q.[date] < CAST((CAST(@YEAR AS VARCHAR) + '-' + CAST(@MONTH AS VARCHAR) + '-1') AS DATETIME) THEN 0 ELSE "
                + "		ISNULL((SELECT SUM(sb.qty_out1) FROM stock_balance sb WHERE product_code = p.product_code AND YEAR(sb.date) = @YEAR AND MONTH(sb.date) = @MONTH AND DAY(sb.date) BETWEEN 1 AND @DAY "
                + "		GROUP BY sb.product_code), 0) "
                + "	END ts, "
                + "	CASE WHEN q.[date] < CAST((CAST(@YEAR AS VARCHAR) + '-' + CAST(@MONTH AS VARCHAR) + '-1') AS DATETIME) THEN 0 ELSE "
                + "		ISNULL((SELECT SUM(sb.qty_out2) FROM stock_balance sb WHERE product_code = p.product_code AND YEAR(sb.date) = @YEAR AND MONTH(sb.date) = @MONTH AND DAY(sb.date) BETWEEN 1 AND @DAY "
                + "		GROUP BY sb.product_code), 0) "
                + "	END tso, "
                + "	CASE WHEN q.[date] < CAST((CAST(@YEAR AS VARCHAR) + '-' + CAST(@MONTH AS VARCHAR) + '-1') AS DATETIME) THEN 0 ELSE "
                + "		ISNULL((SELECT SUM(sb.qty_out3) FROM stock_balance sb WHERE product_code = p.product_code AND YEAR(sb.date) = @YEAR AND MONTH(sb.date) = @MONTH AND DAY(sb.date) BETWEEN 1 AND @DAY "
                + "		GROUP BY sb.product_code), 0) "
                + "	END dr, "
                + "	q.[end], UPPER(pc.category_name) category, UPPER(?) asof "
                + "FROM ( "
                + "	SELECT *, ROW_NUMBER() OVER (PARTITION BY product_code ORDER BY date DESC) lc FROM stock_balance "
                + ")q "
                + "	INNER JOIN product p ON p.product_code = q.product_code "
                + "	INNER JOIN product_category pc ON pc.category_code = p.product_category "
                + "WHERE q.lc = 1 AND q.[end] != 0 AND p.product_category = ? "
                + "ORDER BY q.product_code"
        );

        ListMap.put(Report.IMStockCardperItem,
                "DECLARE @YEAR INT, @MONTH INT, @DAY INT "
                + "SET @YEAR = ? SET @MONTH = ? SET @DAY = ? "
                + "SELECT p.product_code, p.product_name, p.uom_name, CONVERT(VARCHAR(10), q.[date], 105) trx_date, q.[begin] inv_beg, q.qty_in1 rr,  "
                + "	q.qty_out1 ts, q.qty_out2 tso, q.qty_out3 dr, q.[end] inv_end, UPPER(pc.category_name) category, UPPER(?) asof "
                + "FROM ( "
                + "	SELECT *, ROW_NUMBER() OVER (PARTITION BY product_code ORDER BY date DESC) lc FROM stock_balance sb "
                + "	WHERE YEAR(sb.[date]) = @YEAR AND MONTH(sb.[date]) = @MONTH AND DAY(sb.[date]) BETWEEN 1 AND @DAY "
                + "	UNION "
                + "	SELECT * FROM ( "
                + "		SELECT *, ROW_NUMBER() OVER (PARTITION BY product_code ORDER BY date DESC) lc FROM stock_balance sb "
                + "		WHERE sb.product_code NOT IN ( "
                + "			SELECT product_code FROM stock_balance WHERE YEAR([date]) = @YEAR AND MONTH([date]) = @MONTH "
                + "			GROUP BY product_code "
                + "		) AND sb.[date] <= DATEADD(s,-1,DATEADD(mm, DATEDIFF(m,0,CAST((CAST(@YEAR AS VARCHAR) + '-' + CAST(@MONTH AS VARCHAR) + '-1') AS DATETIME)),0)) "
                + "	) x WHERE x.lc = 1 AND x.[end] != 0 "
                + ") q "
                + "	INNER JOIN product p ON p.product_code = q.product_code "
                + "	INNER JOIN product_category pc ON pc.category_code = p.product_category "
                + "WHERE (q.qty_in1 != 0 OR q.qty_out1 != 0 OR q.qty_out2 != 0 OR q.qty_out3 != 0) AND p.product_category = ? "
                + "ORDER BY [date], q.product_code"
        );

        ListMap.put(Report.IMStockCardTransactionReport,
                "DECLARE @YEAR INT, @MONTH INT, @DAY INT "
                + "SET @YEAR = ? SET @MONTH = ? SET @DAY = ? "
                + "SELECT p.product_code, z.doc_date, z.doc_code, z.doc_number, z.qty, p.product_name, pc.category_name, ? periode "
                + "FROM product p  "
                + "	INNER JOIN ( "
                + "		SELECT CONVERT(VARCHAR(10), rr.rr_date, 105) doc_date, 'RR' doc_code, rr.rr_code doc_number, rrd.qty_g qty, rrd.product_code FROM rr "
                + "			INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code "
                + "		WHERE YEAR(rr.rr_date) = @YEAR AND MONTH(rr.rr_date) = @MONTH AND DAY(rr.rr_date) BETWEEN 1 AND @DAY "
                + "		UNION ALL "
                + "		SELECT CONVERT(VARCHAR(10), ts.ts_date, 105), 'TS', ts.ts_code, tsd.qty, tsd.product_code FROM ts "
                + "			INNER JOIN ts_detail tsd ON tsd.ts_code = ts.ts_code "
                + "		WHERE YEAR(ts.ts_date) = @YEAR AND MONTH(ts.ts_date) = @MONTH AND DAY(ts.ts_date) BETWEEN 1 AND @DAY "
                + "	) z ON z.product_code = p.product_code "
                + "	LEFT JOIN product_category pc ON pc.category_code = p.product_category "
                + "WHERE p.product_category = ? "
                + "ORDER BY z.doc_date, p.product_name"
        );

        ListMap.put(Report.PCanvassingForm,
                "SELECT s.supplier_code, s.supplier_name, s.contact_person, null as id, u.name, "
                + "(CONVERT( VARCHAR(2), DAY(GETDATE()) ) + ' ' + DATENAME(MONTH, GETDATE()) + ' ' + CONVERT( VARCHAR(4), YEAR(GETDATE()))) as date, "
                + "acp.prsnumber, pd.productname, pd.qty, pd.uom_name FROM assign_canv_prc acp "
                + "LEFT JOIN supplier s ON s.supplier_code = acp.supplier_code "
                + "LEFT JOIN prs_detail pd ON pd.prsnumber = acp.prsnumber AND pd.productcode = acp.productcode "
                + "LEFT JOIN \"user\" u ON u.user_id = acp.created_by "
                + "WHERE acp.unit_price IS NULL AND acp.supplier_code = ? AND acp.is_active = 'Y' "
        );

        ListMap.put(Report.FGPTS,
                //                "SELECT * "+
                //                "FROM pts"
                "SELECT pts.pts_code, pts.pts_cancode, pts.bor_code, pts.pts_cs, pts.pts_location, pts.coe_flk, "
                + "pts.coe_nw, pts.coe_dw, pts.coe_pw, pts.qad_releaseto, pts.qad_remarks, pd.pts_shift, CONVERT(VARCHAR(10), pd.pts_date, 103) as pts_date, "
                + "pd.pts_prodbatch, pd.pts_basket, pd.pts_qty, p.brand_name, p.packstyle, p.packsize FROM pts "
                + "INNER JOIN pts_detail pd ON pd.pts_code = pts.pts_code "
                + "LEFT JOIN product p ON p.product_code = pts.product_code "
                + "WHERE pts.pts_code = ?");

        ListMap.put(Report.FGTS,
                "SELECT * "
                + "FROM dbo.ts ts, dbo.ts_detail tsd "
                + "WHERE ts.tsnumber = tsd.tsnumber");

        ListMap.put(Report.FGFM,
                "DECLARE  @YEAR INT, @MONTH INT, @PERIODE VARCHAR(30), @DATE VARCHAR(10) "
                + "SET @YEAR = ? SET @MONTH = ? SET @DATE = (CAST(@YEAR AS VARCHAR(4)) + '-' + CAST(@MONTH AS VARCHAR(2)) + '-01') "
                + "SET @PERIODE = DATENAME(MONTH, @DATE) + ', ' + CAST(@YEAR AS VARCHAR(4)) "
                + "SELECT CONVERT(VARCHAR(10), fmd.fm_date, 105) date, fmd.fm_bi_bags bags_begin, fmd.fm_bi_kilos kilos_begin, fmd.fm_ttd_bags bags_ttd, fmd.fm_ttd_kilos kilos_ttd, fmd.fm_i_bags bags_issuances, fmd.fm_i_kilos kilos_issuances, fmd.fm_i_price qty_issuances, fmd.fm_ei_bags bags_end, fmd.fm_ei_kilos kilos_end, @PERIODE asof, "
                + "SUBSTRING(sil.log, 1, CHARINDEX(':', sil.log) - 1) adj_bags, SUBSTRING(sil.log, CHARINDEX(':', sil.log) + 1, LEN(sil.log)) adj_kilos "
                + "FROM fishmeal fm "
                + "	LEFT JOIN fishmeal_detail fmd ON fmd.fm_id = fm.fm_id "
                + "       LEFT JOIN stock_inventory_log sil ON sil.product_code = ? AND YEAR(sil.created_date) = @YEAR AND MONTH(sil.created_date) = @MONTH "
                + "WHERE fm.fm_year = @YEAR AND fm.fm_month = @MONTH ORDER BY fmd.fm_date");

        ListMap.put(Report.FGFO,
                "DECLARE @YEAR INT, @MONTH INT, @PERIODE VARCHAR(30), @DATE VARCHAR(10) "
                + "SET @YEAR = ? SET @MONTH = ? SET @DATE = (CAST(@YEAR AS VARCHAR(4)) + '-' + CAST(@MONTH AS VARCHAR(2)) + '-01') "
                + "SET @PERIODE = DATENAME(MONTH, @DATE) + ', ' + CAST(@YEAR AS VARCHAR(4)) "
                + "SELECT CONVERT(VARCHAR(10), fod.fo_date, 105) date, fod.fo_bi_drums drums_begin, fod.fo_bi_kilos kilos_begin, fod.fo_ttd_drums drums_ttd, fod.fo_ttd_kilos kilos_ttd, fod.fo_i_drums drums_issuances, fod.fo_i_kilos kilos_issuances, fod.fo_i_price qty_issuances, fod.fo_ei_drums drums_end, fod.fo_ei_kilos kilos_end, @PERIODE asof, "
                + "SUBSTRING(sil.log, 1, CHARINDEX(':', sil.log) - 1) adj_bags, SUBSTRING(sil.log, CHARINDEX(':', sil.log) + 1, LEN(sil.log)) adj_kilos "
                + "FROM fishoil fo "
                + "     LEFT JOIN fishoil_detail fod ON fod.fo_id = fo.fo_id "
                + "    LEFT JOIN stock_inventory_log sil ON sil.product_code = ? AND YEAR(sil.created_date) = @YEAR AND MONTH(sil.created_date) = @MONTH "
                + "WHERE fo.fo_year = @YEAR AND fo.fo_month = @MONTH ORDER BY fod.fo_date");

        ListMap.put(Report.FGSC,
                "DECLARE @YEAR INT, @MONTH INT, @DAY INT "
                + "SELECT @YEAR = ?, @MONTH = ?, @DAY = ? "
                + "SELECT p.product_code, p.packstyle, p.packsize, ? asof, "
                + "	CASE WHEN csb.pts IS NULL THEN ISNULL(psb.[begin], 0) ELSE "
                + "		(SELECT TOP(1) sb.[begin] FROM stock_balance sb WHERE sb.product_code = p.product_code AND YEAR(sb.[date]) = @YEAR AND MONTH(sb.[date]) = @MONTH ORDER BY sb.id ASC) "
                + "	END [begin], "
                + "	CASE WHEN csb.pts IS NULL THEN ISNULL(psb.[end], 0) ELSE "
                + "		(SELECT TOP(1) sb.[end] FROM stock_balance sb WHERE sb.product_code = p.product_code AND YEAR(sb.[date]) = @YEAR AND MONTH(sb.[date]) = @MONTH ORDER BY sb.id DESC) "
                + "	END [end], "
                + "	CASE WHEN csb.pts IS NULL THEN 0 ELSE csb.pts END pts,  "
                + "	CASE WHEN csb.dr_shipping IS NULL THEN 0 ELSE csb.dr_shipping END dr_shipping,  "
                + "	CASE WHEN csb.dr_others IS NULL THEN 0 ELSE csb.dr_others END dr_others,  "
                + "	CASE WHEN csb.ts_badstocks IS NULL THEN 0 ELSE csb.ts_badstocks END ts_badstocks,  "
                + "	CASE WHEN csb.ts_others IS NULL THEN 0 ELSE csb.ts_others END ts_others "
                + "FROM product p "
                + "	/* current stock card */ "
                + "	LEFT JOIN ( "
                + "		SELECT sb.product_code, SUM(sb.qty_in2) pts, SUM(sb.qty_out4) dr_shipping, SUM(sb.qty_out5) dr_others, SUM(sb.qty_out6) ts_badstocks, SUM(sb.qty_out2) ts_others "
                + "		FROM stock_balance sb  "
                + "		WHERE YEAR(sb.[date]) = @YEAR AND MONTH(sb.[date]) = @MONTH  "
                + "		GROUP BY product_code "
                + "	) csb ON csb.product_code = p.product_code "
                + "	/* previous stock card for no current transaction */ "
                + "	LEFT JOIN ( "
                + "		SELECT ROW_NUMBER() OVER (PARTITION BY sb.product_code ORDER BY sb.[date] DESC) rn, sb.product_code, sb.[begin], sb.[end] "
                + "		FROM stock_balance sb  "
                + "		WHERE YEAR(sb.[date]) = @YEAR AND MONTH(sb.[date]) < (@MONTH - 1) "
                + "	) psb ON psb.product_code = p.product_code AND psb.rn = 1 "
                + "WHERE p.product_category = 'FG' "
                + "order by p.packsize");

    }

    public ModelAndView getReportXLS(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> temp = new HashMap(request.getParameterMap());
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (Entry<String, String[]> e : temp.entrySet()) {
            map.put(e.getKey(), e.getValue()[0]);
        }

        Report reportType = Report.valueOf(map.get("item").toString());

        List data = getDataForType(reportType, map);
        System.out.println("Data" + (data != null ? (":" + data.size()) : "") + ":" + data);
        if (data != null) {
            if (!data.isEmpty()) {
                map.put("sdata", data.get(0));
            }
            if (!data.isEmpty()) {
                map.put("edata", data.get(data.size() - 1));
            }
            PostProcess process = PostProcess.get(reportType);
            if (process != null) {
                process.process(data, map);
            }
        }
        byte[] item = ReportFactory.getReportXLS(reportType, data, map);
        response.setHeader("Content-disposition", "attachment; filename=" + reportType + ".xls");
        response.setContentLength(item.length);
        response.getOutputStream().write(item);

        return null;
    }

    public ModelAndView getReportPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> temp = new HashMap(request.getParameterMap());
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (Entry<String, String[]> e : temp.entrySet()) {
            map.put(e.getKey(), e.getValue()[0]);
        }

        Report reportType = Report.valueOf(map.get("item").toString());

        List data = getDataForType(reportType, map);

        //  DEBUG | Data that will be injected to excel template
//        System.out.println("Data:" + data);
        if (data != null) {
            if (!data.isEmpty()) {
                map.put("sdata", data.get(0));
            }
            if (!data.isEmpty()) {
                map.put("edata", data.get(data.size() - 1));
            }
            PostProcess process = PostProcess.get(reportType);
            if (process != null) {
                process.process(data, map);
            }
        }
        byte[] item = ReportFactory.getReportPDF(reportType, data, map);
        response.setHeader("Content-disposition", "attachment; filename=" + reportType + ".pdf");
        response.setContentLength(item.length);
        response.getOutputStream().write(item);

        return null;
    }

    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> temp = new HashMap(request.getParameterMap());
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (Entry<String, String[]> e : temp.entrySet()) {
            map.put(e.getKey(), e.getValue()[0]);
        }
        System.out.println("params: " + request.getParameter("params"));
        System.out.println("map: " + map);
        Report reportType = Report.valueOf(map.get("item").toString());

        List data = getDataForType(reportType, map);
        System.out.println("Data:" + data);
        if (data != null) {
            if (!data.isEmpty()) {
                map.put("sdata", data.get(0));
            }
            if (!data.isEmpty()) {
                map.put("edata", data.get(data.size() - 1));
            }
        }
        PostProcess process = PostProcess.get(reportType);
        if (process != null) {
            process.process(data, map);
        }
        byte[] item;
        if ("PDF".equalsIgnoreCase(request.getParameter("type"))) {
            response.setHeader("Content-disposition", "attachment; filename=" + reportType + ".pdf");
            response.setContentType("application/pdf");
            item = ReportFactory.getReportPDF(reportType, data, map);
        } else if ("CSV".equalsIgnoreCase(request.getParameter("type"))) {
            response.setHeader("Content-disposition", "attachment; filename=" + reportType + ".csv");
            item = ReportFactory.getReportCSV(reportType, data, map);
        } else if ("HTML".equalsIgnoreCase(request.getParameter("type"))) {
            response.setHeader("Content-disposition", "attachment; filename=" + reportType + ".html");
            item = ReportFactory.getReportHTML(reportType, data, map);
        } else {
            response.setHeader("Content-disposition", "attachment; filename=" + reportType + ".xls");
            response.setContentType("application/vnd.ms-excel");
            item = ReportFactory.getReportXLS(reportType, data, map);
        }
        response.setContentLength(item.length);
        response.getOutputStream().write(item);

        return null;
    }

    public List getDataForType(Report reportType, Map<String, Object> params) throws Exception {
        if (params.get("q") != null) {
            Connection conn = new ctrlKoneksiDB().openConnection();
            JDBCExecutor jdbc = new JDBCExecutor(conn);
            List list = jdbc.execQuery(params.get("q").toString());
            conn.close();
            return list;
        } else {
            Object o = ListMap.get(reportType);
            if (o == null) {
                return null;
            }
            if (o instanceof String) {

                String paramString = params.containsKey("params") ? params.get("params").toString() : "";

                // added by edw, override FSummaryWSSlip
                if (reportType.toString().equals("FSummaryWSSlip")) {
                    o = "SELECT ws.ws_no, su.name AS supplier_name, fv.name AS boat_name,"
                            + "fv.batch_no, replace(convert(varchar, ws.date_shift, 111), '/', '-') as date_shift, ws.time_shift, f.code AS type, wsd.total_weight AS data,"
                            + "(SELECT ISNULL(SUM(fs.cooked_weight), 0.00)FROM fish_spoilage fs "
                            + "WHERE fs.vessel_id = ws.vessel_id AND fs.fish_id = wsd.fish_id "
                            + "AND fs.date_shift = ws.date_shift) AS spoilage "
                            + "FROM fish_ws_detail wsd "
                            + "LEFT JOIN fish_ws ws ON ws.id = wsd.ws_id "
                            + "LEFT JOIN fish_vessel fv ON fv.id = ws.vessel_id "
                            + "LEFT JOIN fish_supplier su ON su.id = fv.supplier_id "
                            + "LEFT JOIN fish f ON f.id = wsd.fish_id "
                            + "LEFT JOIN fish_ws_type fwt ON fwt.id = ws.ws_type_id "
                            + "WHERE ws.vessel_id = ? AND replace(convert(varchar, ws.date_shift, 111), '/', '-') = ? ";

                    if (paramString.contains(":frozen")) {
                        o = o + "AND (\n"
                                + "        fwt.code = 'WSBF'\n"
                                + "     OR fwt.code = 'WSABF'\n"
                                + "     OR fwt.code = 'WSNC'\n"
                                + "    ) ";
                        paramString = paramString.replace(":frozen", "");
                    } else if (paramString.contains(":fresh")) {
                        o = o + "AND (\n"
                                + "        fwt.code = 'WSHR'\n"
                                + "     OR fwt.code = 'WSBR'\n"
                                + "     OR fwt.code = 'WSL'\n"
                                + "    ) ";
                        paramString = paramString.replace(":fresh", "");
                    }
                }
                // end of added by edw

                Connection conn = new ctrlKoneksiDB().openConnection();
                JDBCExecutor jdbc = new JDBCExecutor(conn);

                System.out.println("disini >>>> " + o.toString());

                if (params.containsKey("params")) {
                    testQuery(o.toString(), ((Object[]) paramString.split(":")));
                }

                List<ResultSetRow> list = params.containsKey("params") ? jdbc.execQuery(o.toString(), ((Object[]) paramString.split(":"))) : jdbc.execQuery(o.toString());
                for (int x = 0; x < list.size(); ++x) {
                    ResultSetRow r = list.get(x);
                    if (!r.containsKey("index")) {
                        r.put("index", x + 1);
                    }
                }
                conn.close();

                //  DEBUG | Purposes uncomment it if you want to see the report data
//                System.out.println("size list >>>> " + list.size());
//                for (ResultSetRow resultSetRow : list) {
//                    System.out.println("disiini >>> " + resultSetRow.toString());
//                }
                return list;
            } else if (o instanceof String[]) {
                Connection conn = new ctrlKoneksiDB().openConnection();
                JDBCExecutor jdbc = new JDBCExecutor(conn);
                int x = 0;
                for (String s : (String[]) o) {

                    System.out.println("++++++ " + s.toString());

                    String add = x > 0 ? "" + x : "";
                    List<ResultSetRow> list = params.containsKey("params" + add) ? jdbc.execQuery(s, ((Object[]) params.get("params" + add).toString().split(":")))
                            : (params.containsKey("params")) ? jdbc.execQuery(s, ((Object[]) params.get("params").toString().split(":"))) : jdbc.execQuery(s);
                    for (int i = 0; i < list.size(); ++i) {
                        ResultSetRow r = list.get(i);
                        if (!r.containsKey("index")) {
                            r.put("index", i + 1);
                        }
                    }
                    if (!list.isEmpty()) {
                        params.put("sdata" + add, list.get(0));
                    }
                    if (!list.isEmpty()) {
                        params.put("edata" + add, list.get(list.size() - 1));
                    }
                    params.put("data" + add, list);
                    ++x;
                }
                conn.close();
                return null;
            } else {
                Method m = o.getClass().getMethod("findAll");
                return (List) m.invoke(o);
            }
        }
    }

    private void testQuery(String sql, Object... bindVariableValues) {
        for (Object object : bindVariableValues) {
            sql = sql.replaceFirst("\\?", object.toString());
        }
        System.out.println("==== this is my sql >>>> " + sql);
    }
}
