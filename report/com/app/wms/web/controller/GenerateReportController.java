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

@SuppressWarnings({"rawtypes", "unchecked"})
public class GenerateReportController extends MultiActionController {

    public static Map<Report, Object> ListMap = new HashMap<Report, Object>();
    public static Map<Report, PostProcess> PostProcess = new HashMap<Report, PostProcess>();

    static {
        //  Fish Module | Form and Report List
        ListMap.put(Report.FishWSHR,
                "SELECT " +
                "	fws.ws_no, DATENAME(dw, fws.date_shift) day_shift, CONVERT(varchar(12), fws.date_shift, 107) date_shift, fws.time_shift, fws.regu, " +
                "	fs.name as supplier_name, " +
                "	fv.batch_no, fv.name as boat_name, " +
                "	fwsd.total_weight, " +
                "	ft.code as fish_type, " +
                "	fwt.code as fish_weight " +
                "FROM fish_ws fws " +
                "	INNER JOIN fish_ws_type fwst ON fwst.id = fws.ws_type_id " +
                "	INNER JOIN fish_vessel fv ON fv.id = fws.vessel_id " +
                "	INNER JOIN fish_supplier fs ON fs.id = fv.supplier_id " +
                "	INNER JOIN fish_ws_detail fwsd ON fwsd.ws_id = fws.id " +
                "	INNER JOIN fish f ON f.id = fwsd.fish_id " +
                "	INNER JOIN fish_type ft ON ft.id = f.fish_type_id " +
                "	INNER JOIN fish_weight_type fwt ON fwt.id = f.fish_weight_type_id " +
                "WHERE fws.id = ? AND fws.is_active = 'Y' AND fwst.code = 'WSHR' " +
                "ORDER BY fish_type"
        );
        ListMap.put(Report.FishWSNC,
                "SELECT " +
                "	fws.ws_no, DATENAME(dw, fws.date_shift) day_shift, CONVERT(varchar(12), fws.date_shift, 107) date_shift, fws.time_shift, fws.regu, " +
                "	fs.name as supplier_name, " +
                "	fv.batch_no, fv.name as boat_name, " +
                "	fwsd.total_weight, " +
                "	ft.code as fish_type, " +
                "	fwt.code as fish_weight " +
                "FROM fish_ws fws " +
                "	INNER JOIN fish_ws_type fwst ON fwst.id = fws.ws_type_id " +
                "	INNER JOIN fish_vessel fv ON fv.id = fws.vessel_id " +
                "	INNER JOIN fish_supplier fs ON fs.id = fv.supplier_id " +
                "	INNER JOIN fish_ws_detail fwsd ON fwsd.ws_id = fws.id " +
                "	INNER JOIN fish f ON f.id = fwsd.fish_id " +
                "	INNER JOIN fish_type ft ON ft.id = f.fish_type_id " +
                "	INNER JOIN fish_weight_type fwt ON fwt.id = f.fish_weight_type_id " +
                "WHERE fws.id = ? AND fws.is_active = 'Y' AND fwst.code = 'WSNC' " +
                "ORDER BY fish_type"
        );
        ListMap.put(Report.FishWSBF,
                "SELECT " +
                "	fws.ws_no, DATENAME(dw, fws.date_shift) day_shift, CONVERT(varchar(12), fws.date_shift, 107) date_shift, fws.time_shift, fws.regu, " +
                "	fs.name as supplier_name, " +
                "	fv.batch_no, fv.name as boat_name, " +
                "	fwsd.total_weight, " +
                "	ft.code as fish_type, " +
                "	fwt.code as fish_weight " +
                "FROM fish_ws fws " +
                "	INNER JOIN fish_ws_type fwst ON fwst.id = fws.ws_type_id " +
                "	INNER JOIN fish_vessel fv ON fv.id = fws.vessel_id " +
                "	INNER JOIN fish_supplier fs ON fs.id = fv.supplier_id " +
                "	INNER JOIN fish_ws_detail fwsd ON fwsd.ws_id = fws.id " +
                "	INNER JOIN fish f ON f.id = fwsd.fish_id " +
                "	INNER JOIN fish_type ft ON ft.id = f.fish_type_id " +
                "	INNER JOIN fish_weight_type fwt ON fwt.id = f.fish_weight_type_id " +
                "WHERE fws.id = ? AND fws.is_active = 'Y' AND fwst.code = 'WSBF' " +
                "ORDER BY fish_type"
        );
        ListMap.put(Report.FishWSNR,
                "SELECT " +
                "	fws.ws_no, DATENAME(dw, fws.date_shift) day_shift, CONVERT(varchar(12), fws.date_shift, 107) date_shift, fws.time_shift, fws.regu, " +
                "	fs.name as supplier_name, " +
                "	fv.batch_no, fv.name as boat_name, " +
                "	fwsd.total_weight, " +
                "	ft.code as fish_type, " +
                "	fwt.code as fish_weight " +
                "FROM fish_ws fws " +
                "	INNER JOIN fish_ws_type fwst ON fwst.id = fws.ws_type_id " +
                "	INNER JOIN fish_vessel fv ON fv.id = fws.vessel_id " +
                "	INNER JOIN fish_supplier fs ON fs.id = fv.supplier_id " +
                "	INNER JOIN fish_ws_detail fwsd ON fwsd.ws_id = fws.id " +
                "	INNER JOIN fish f ON f.id = fwsd.fish_id " +
                "	INNER JOIN fish_type ft ON ft.id = f.fish_type_id " +
                "	INNER JOIN fish_weight_type fwt ON fwt.id = f.fish_weight_type_id " +
                "WHERE fws.id = ? AND fws.is_active = 'Y' AND fwst.code = 'WSNR' " +
                "ORDER BY fish_type"
        );
        ListMap.put(Report.FishWSABF,
                "SELECT " +
                "	fws.ws_no, DATENAME(dw, fws.date_shift) day_shift, CONVERT(varchar(12), fws.date_shift, 107) date_shift, fws.time_shift, fws.regu, " +
                "	fs.name as supplier_name, " +
                "	fv.batch_no, fv.name as boat_name, " +
                "	fwsd.total_weight, " +
                "	ft.code as fish_type, " +
                "	fwt.code as fish_weight " +
                "FROM fish_ws fws " +
                "	INNER JOIN fish_ws_type fwst ON fwst.id = fws.ws_type_id " +
                "	INNER JOIN fish_vessel fv ON fv.id = fws.vessel_id " +
                "	INNER JOIN fish_supplier fs ON fs.id = fv.supplier_id " +
                "	INNER JOIN fish_ws_detail fwsd ON fwsd.ws_id = fws.id " +
                "	INNER JOIN fish f ON f.id = fwsd.fish_id " +
                "	INNER JOIN fish_type ft ON ft.id = f.fish_type_id " +
                "	INNER JOIN fish_weight_type fwt ON fwt.id = f.fish_weight_type_id " +
                "WHERE fws.id = ? AND fws.is_active = 'Y' AND fwst.code = 'WSABF' " +
                "ORDER BY fish_type"
        );
        ListMap.put(Report.FishWSL,
                "SELECT " +
                "	fws.ws_no, DATENAME(dw, fws.date_shift) day_shift, CONVERT(varchar(12), fws.date_shift, 107) date_shift, fws.time_shift, fws.regu, " +
                "	fs.name as supplier_name, " +
                "	fv.batch_no, fv.name as boat_name, " +
                "	fwsd.total_weight, " +
                "	ft.code as fish_type, " +
                "	fwt.code as fish_weight " +
                "FROM fish_ws fws " +
                "	INNER JOIN fish_ws_type fwst ON fwst.id = fws.ws_type_id " +
                "	INNER JOIN fish_vessel fv ON fv.id = fws.vessel_id " +
                "	INNER JOIN fish_supplier fs ON fs.id = fv.supplier_id " +
                "	INNER JOIN fish_ws_detail fwsd ON fwsd.ws_id = fws.id " +
                "	INNER JOIN fish f ON f.id = fwsd.fish_id " +
                "	INNER JOIN fish_type ft ON ft.id = f.fish_type_id " +
                "	INNER JOIN fish_weight_type fwt ON fwt.id = f.fish_weight_type_id " +
                "WHERE fws.id = ? AND fws.is_active = 'Y' AND fwst.code = 'WSL' " +
                "ORDER BY fish_type"
        );
        ListMap.put(Report.FishSR,
                "SELECT fs.catcher_no, CONVERT(VARCHAR(11), fs.date_shift, 106) as date_shift, fs.time_shift, fs.cooked_weight, fs.raw_weight, fs.total_processed, fs.reason, " +
                "	fv.batch_no, " +
                "	f.code as fish " +
                "FROM fish_spoilage fs " +
                "	INNER JOIN fish_vessel fv ON fv.id = fs.vessel_id " +
                "	INNER JOIN fish f ON f.id = fs.fish_id " +
                "WHERE fs.vessel_id = ? AND fs.date_shift = ? AND fs.time_shift = ? AND fs.is_active='Y'"
        );
        ListMap.put(Report.FishWssFresh, "EXEC RPT_WEIGHT_SLIP_SUMMARY ?, ?, ?, ?");
        ListMap.put(Report.FishWssFrozen, "EXEC RPT_WEIGHT_SLIP_SUMMARY ?, ?, ?, ?");
        ListMap.put(Report.FishRR, "EXEC RPT_RECEIVING_REPORT_F ?");
        ListMap.put(Report.FishSumPerSupp, "EXEC RPT_FR_SUMMARY_PER_SUPPLIER ?");
        ListMap.put(Report.FishSumPerCS, "EXEC RPT_FR_SUMMARY_PER_COLDSTORAGE ?");
        ListMap.put(Report.FishStockCard, "EXEC RPT_FR_STOCK_CARD ?");
        ListMap.put(Report.FishTS, "EXEC PRT_F_TRANSFER_SLIP ?");
        ListMap.put(Report.FishBF, "EXEC PRT_F_BRINE_FREEZING ?");
        ListMap.put(Report.FishABF, "EXEC PRT_F_AIR_BLAST_FREEZING ?");
        ListMap.put(Report.FishSumPerSuppActual, "EXEC RPT_FR_SUMMARY_PER_SUPPLIER_ACTUAL ?");
        ListMap.put(Report.FishSumPerCSActual, "EXEC RPT_FR_SUMMARY_PER_COLDSTORAGE_ACTUAL ?");
        ListMap.put(Report.FishStockCardActual, "EXEC RPT_FR_STOCK_CARD_ACTUAL ?");
        ListMap.put(Report.FishWDS, "EXEC PRT_F_WITHDRAWAL ?");
        ListMap.put(Report.FishRECC, "EXEC PRT_F_RECLASSIFICATION ?");
        ListMap.put(Report.FishFMov, "EXEC PRT_F_FISH_MOVING ?");
        ListMap.put(Report.FishWsSF, "EXEC PRT_WEIGHT_SLIP_SUMMARY ?");
        ListMap.put(Report.FishWsSZ, "EXEC PRT_WEIGHT_SLIP_SUMMARY ?");
        //  ***END*** | Fish Module | Form and Report List

        ListMap.put(Report.FWS,
                "SELECT f.code AS kode, wdsd.description AS nama_barang, "
                + "'FISH' AS tipe, wdsd.qty, wdsd.uom_code AS satuan "
                + "FROM inventory..fish_wds_detail wdsd "
                + "LEFT JOIN inventory..fish f ON f.id = wdsd.fish_id "
                + "LEFT JOIN inventory..fish_storage fs ON fs.id = wdsd.storage_id "
                + "WHERE wds_id=?"
        );

        ListMap.put(Report.FWSABF, null);

        ListMap.put(Report.FWSBR, null);

        ListMap.put(Report.FWSHR, null);

        ListMap.put(Report.FWSL, null);

        ListMap.put(Report.FWSNC, null);

        ListMap.put(Report.FWeightSlip,
                "SELECT f.code, SUM(wsd.total_weight) AS total_weight "
                + "FROM inventory..fish_ws_detail wsd "
                + "LEFT JOIN inventory..fish f ON f.id = wsd.fish_id "
                + "WHERE wsd.ws_id = ? "
                + "GROUP BY f.code"
        );

        ListMap.put(Report.FRR,
                "SELECT MAX(ft.description) AS description, f.code, "
                + "SUM(frd.good_weight) AS qty, 'Kg' AS unit "
                + "FROM inventory..fish_rr_detail frd "
                + "LEFT JOIN inventory..fish f ON f.id = frd.fish_id "
                + "LEFT JOIN inventory..fish_type ft ON ft.id = f.fish_type_id "
                + "WHERE frd.rr_id = ? "
                + "GROUP BY f.code"
        );

        ListMap.put(Report.FTS,
                "SELECT tsd.description, f.code, "
                + "tsd.qty, tsd.uom_code AS unit "
                + "FROM inventory..fish_ts_detail tsd "
                + "LEFT JOIN inventory..fish f ON f.id = tsd.fish_id "
                + "LEFT JOIN inventory..fish_storage fs ON fs.id = tsd.storage_id "
                + "WHERE ts_id=?"
        );

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
                + "LEFT JOIN inventory..fish_ws_type fwt ON fwt.id = ws.ws_type_id "
                + "WHERE ws.vessel_id = ? AND replace(convert(varchar, ws.date_shift, 111), '/', '-') = ? "
                + "AND (fwt.code = 'WSBF' OR fwt.code = 'WSABF' OR fwt.code = 'WSNC') "
        );

        ListMap.put(Report.FSpoilagereport,
                "SELECT catcher_no, f.code, cooked_weight, raw_weight, total_processed, reason, batch_no, date_shift, time_shift, supplier_name "
                + "FROM inventory..fish_spoilage fs "
                + "LEFT JOIN inventory..fish f ON fs.fish_id = f.id "
                + "LEFT JOIN inventory..fish_vessel fv ON fv.id = fs.vessel_id "
                + "LEFT JOIN inventory..fish_supplier s ON fv.supplier_id = s.id "
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

        ListMap.put(Report.IMRR,
                //                "select * " +
                //                "from goodreceive gr " +
                //                "LEFT JOIN goodreceive_detail grd ON gr.grnumber = grd.grnumber " +
                //                "LEFT JOIN product p ON p.product_code = grd.productcode " +
                //                "LEFT JOIN department d ON d.department_code = gr.department"
                "SELECT rrd.rr_code, CONVERT(VARCHAR(10), rr.rr_date, 103) as rr_date, rr.rr_from, po.po_code, "
                + "CONVERT(VARCHAR(10), po.po_date, 103) as po_date, "
                + "p.product_name, p.product_code, rrd.department_code, rrd.qty_g, rrd.uom, u.name "
                + "FROM rr_detail rrd "
                + "INNER JOIN rr ON rr.rr_code = rrd.rr_code "
                + "INNER JOIN po ON po.po_code = rr.po_code "
                + "LEFT JOIN product p ON p.product_code = rrd.product_code "
                + "LEFT JOIN \"user\" u ON u.user_id = rr.created_by "
                + "WHERE rrd.rr_code = ?");

        ListMap.put(Report.FGEDS,
                "");

        ListMap.put(Report.FMDR, "SELECT * "
                + "FROM dbo.dr dr, dbo.dr_detail drd "
                + "WHERE dr.drnumber = drd.drnumber");

        ListMap.put(Report.PPrsNotyetPO,
                "SELECT '' as prs_id, prd.prsnumber as prs_number, REPLACE(CONVERT(VARCHAR(9), prs.prsdate, 6), ' ', '-') as prs_date, \n"
                + "	p.product_name, prd.qty, prs.department_name, '' as date_received, u.name, \n"
                + "	DATENAME(MM, GETDATE()) + RIGHT(CONVERT(VARCHAR(12), GETDATE(), 107), 9) as gen_date \n"
                + "FROM prs_detail prd \n"
                + "	LEFT JOIN prs ON prs.prsnumber = prd.prsnumber \n"
                + "	LEFT JOIN po_detail pod ON pod.prsnumber = prd.prsnumber AND pod.product_code = prd.productcode \n"
                + "	LEFT JOIN product p ON p.product_code = prd.productcode \n"
                + "	LEFT JOIN assign_canv_dtl acd ON acd.prsnumber = prd.prsnumber AND acd.productcode = prd.productcode \n"
                + "	LEFT JOIN \"user\" u ON u.user_id = acd.user_id \n"
                + "WHERE pod.po_code IS NULL AND u.user_id = ? ORDER BY prs_date, prs_number, p.product_name"
        //			"SELECT * " +
        //			"FROM dbo.prs prs, dbo.prs_detail prsd, dbo.po po " +
        //			"WHERE prs.prsnumber = prsd.prsnumber AND po.prsnumber = prs.prsnumber"
        );

        ListMap.put(Report.PPoNotyetDeliveredDP,
                "SELECT * "
                + "FROM inventory..po po "
                + "LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber "
                + "LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber "
                + "LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber "
                + "LEFT JOIN inventory..department dep ON dep.department_name = po.department_name "
                + "WHERE pod.payment = 'DP'"
        );

        ListMap.put(Report.PPoNotyetDeliveredCash,
                "SELECT '' as prs_id, pod.prsnumber as prs_number, REPLACE(CONVERT(VARCHAR(9), prs.prsdate, 6), ' ', '-') as prs_date, \n"
                + "	p.product_name, prsd.qty, prs.department_name, '' as date_received, '' as currency, (pod.sub_total/prsd.qty) as price, \n"
                + "	pod.sub_total, REPLACE(CONVERT(VARCHAR(9), po.po_date, 6), ' ', '-') as po_date, po.po_code, s.supplier_name, '' as rfp_date, \n"
                + "	'' as rfp_paid, acp.top_desc, po.remarks, u.name, DATENAME(MM, GETDATE()) + RIGHT(CONVERT(VARCHAR(12), GETDATE(), 107), 9) as gen_date \n"
                + "FROM po_detail pod \n"
                + "LEFT JOIN po ON po.po_code = pod.po_code \n"
                + "LEFT JOIN supplier s ON s.supplier_code = po.supplier_code \n"
                + "LEFT JOIN prs ON prs.prsnumber = pod.prsnumber \n"
                + "LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code \n"
                + "LEFT JOIN assign_canv_prc acp ON acp.prsnumber = prs.prsnumber AND acp.productcode = prsd.productcode \n"
                + "	AND acp.is_selected = 'Y' AND acp.[top] = 'CASH' \n"
                + "LEFT JOIN product p ON p.product_code = prsd.productcode \n"
                + "LEFT JOIN \"user\" u ON u.user_id = acp.created_by \n"
                + "LEFT JOIN ( \n"
                + "	SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM inventory..rr \n"
                + "		INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code \n"
                + ") x ON x.po_code = pod.po_code AND x.product_code = pod.product_code \n"
                + "WHERE pod.po_code IN ( \n"
                + "	SELECT po.po_code FROM po WHERE po_code IN ( \n"
                + "	SELECT DISTINCT pod.po_code FROM po_detail pod \n"
                + "	LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code \n"
                + "	LEFT JOIN ( \n"
                + "	SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM inventory..rr \n"
                + "		 INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code \n"
                + "	) x ON x.po_code = pod.po_code AND x.product_code = pod.product_code \n"
                + "	WHERE rr_code IS NULL OR x.qty_g < prsd.qty ) \n"
                + ") AND po.is_approved = 'Y' AND (rr_code IS NULL OR x.qty_g < prsd.qty) AND acp.created_by = ? ORDER BY prs_date ASC"
        //			"SELECT * " +
        //			"FROM inventory..po po " +
        //			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
        //			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
        //			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
        //			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name"+
        //			"WHERE pod.payment = 'CASH'"
        );

        ListMap.put(Report.PPoNotyetDeliveredCredit,
                "SELECT '' as prs_id, pod.prsnumber as prs_number, REPLACE(CONVERT(VARCHAR(9), prs.prsdate, 6), ' ', '-') as prs_date, \n"
                + "	p.product_name, prsd.qty, prs.department_name, '' as date_received, '' as currency, (pod.sub_total/prsd.qty) as price, \n"
                + "	pod.sub_total, REPLACE(CONVERT(VARCHAR(9), po.po_date, 6), ' ', '-') as po_date, po.po_code, s.supplier_name, '' as rfp_date, \n"
                + "	'' as rfp_paid, acp.top_desc, po.remarks, u.name, DATENAME(MM, GETDATE()) + RIGHT(CONVERT(VARCHAR(12), GETDATE(), 107), 9) as gen_date \n"
                + "FROM po_detail pod \n"
                + "LEFT JOIN po ON po.po_code = pod.po_code \n"
                + "LEFT JOIN supplier s ON s.supplier_code = po.supplier_code \n"
                + "LEFT JOIN prs ON prs.prsnumber = pod.prsnumber \n"
                + "LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code \n"
                + "LEFT JOIN assign_canv_prc acp ON acp.prsnumber = prs.prsnumber AND acp.productcode = prsd.productcode \n"
                + "	AND acp.is_selected = 'Y' AND acp.[top] = 'CREDIT' \n"
                + "LEFT JOIN product p ON p.product_code = prsd.productcode \n"
                + "LEFT JOIN \"user\" u ON u.user_id = acp.created_by \n"
                + "LEFT JOIN ( \n"
                + "	SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM inventory..rr \n"
                + "		INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code \n"
                + ") x ON x.po_code = pod.po_code AND x.product_code = pod.product_code \n"
                + "WHERE pod.po_code IN ( \n"
                + "	SELECT po.po_code FROM po WHERE po_code IN ( \n"
                + "	SELECT DISTINCT pod.po_code FROM po_detail pod \n"
                + "	LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code \n"
                + "	LEFT JOIN ( \n"
                + "	SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM inventory..rr \n"
                + "		 INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code \n"
                + "	) x ON x.po_code = pod.po_code AND x.product_code = pod.product_code \n"
                + "	WHERE rr_code IS NULL OR x.qty_g < prsd.qty ) \n"
                + ") AND po.is_approved = 'Y' AND (rr_code IS NULL OR x.qty_g < prsd.qty) AND acp.created_by = ? ORDER BY prs_date ASC"
        //			"SELECT * " +
        //			"FROM inventory..po po " +
        //			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
        //			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
        //			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
        //			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name "+
        //			"WHERE pod.payment = 'CREDIT'"
        );

        ListMap.put(Report.PPoRegisterPerPeriode,
                "SELECT '' as prs_id, prd.prsnumber as prs_number, REPLACE(CONVERT(VARCHAR(9), prs.prsdate, 6), ' ', '-') as prs_date, \n"
                + "	p.product_code, p.product_name, prd.qty, prs.department_name, '' as date_received, '' as currency, acp.unit_price as price, \n"
                + "	(acp.unit_price * prd.qty) as amount, REPLACE(CONVERT(VARCHAR(9), po.po_date, 6), ' ', '-') as po_date, po.po_code, \n"
                + "	s.supplier_name, u.name, po.remarks, ? as periode \n"
                + "FROM prs_detail prd \n"
                + "	LEFT JOIN prs ON prs.prsnumber = prd.prsnumber \n"
                + "	LEFT JOIN po_detail pod ON pod.prsnumber = prd.prsnumber AND pod.product_code = prd.productcode \n"
                + "	LEFT JOIN po ON po.po_code = pod.po_code \n"
                + "	LEFT JOIN product p ON p.product_code = prd.productcode \n"
                + "	LEFT JOIN assign_canv_prc acp ON acp.prsnumber = prs.prsnumber AND acp.productcode = prd.productcode \n"
                + "		AND acp.is_selected = 'Y' \n"
                + "	LEFT JOIN \"user\" u ON u.user_id = acp.created_by \n"
                + "	LEFT JOIN supplier s ON s.supplier_code = po.supplier_code \n"
                + "WHERE po.is_approved = 'Y' AND YEAR(po.po_date) = ? AND MONTH(po.po_date) = ? AND DAY(po.po_date) BETWEEN ? AND ? \n"
                + "ORDER BY prs_date, prs_number, product_name"
        );

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

        ListMap.put(Report.PurchasedItems,
                "DECLARE @YEAR INT, @MONTH INT "
                + "SET @YEAR = ? "
                + "SET @MONTH = ? "
                + "SELECT p.product_code, p.product_name, ? periode, "
                + "	SUM(CASE WHEN MONTH(po.po_date) = @MONTH THEN prsd.qty END) as qty1, "
                + "	SUM(CASE WHEN po.currency = 'IDR' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as idr1, "
                + "	SUM(CASE WHEN po.currency = 'USD' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as usd1, "
                + "	SUM(CASE WHEN po.currency = 'EUR' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as eur1, "
                + "	SUM(CASE WHEN po.currency = 'JPY' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as jpy1, "
                + "	SUM(CASE WHEN 1 = 1 THEN prsd.qty END) as qty2, "
                + "	SUM(CASE WHEN po.currency = 'IDR' THEN pod.sub_total END) as idr2, "
                + "	SUM(CASE WHEN po.currency = 'USD' THEN pod.sub_total END) as usd2, "
                + "	SUM(CASE WHEN po.currency = 'EUR' THEN pod.sub_total END) as eur2, "
                + "	SUM(CASE WHEN po.currency = 'JPY' THEN pod.sub_total END) as jpy2 "
                + "FROM po "
                + "	INNER JOIN po_detail pod ON pod.po_code = po.po_code "
                + "	LEFT JOIN product p ON p.product_code = pod.product_code "
                + "	INNER JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code "
                + "WHERE po.is_approved = 'Y' AND YEAR(po.po_date) = @YEAR "
                + "GROUP BY p.product_code, p.product_name ORDER BY p.product_name"
        );

        ListMap.put(Report.PurchasedPerSupplier,
                "DECLARE @YEAR INT, @MONTH INT "
                + "SET @YEAR = ? "
                + "SET @MONTH = ? "
                + "SELECT s.supplier_code, s.supplier_name, ? periode, "
                + "	SUM(CASE WHEN MONTH(po.po_date) = @MONTH THEN prsd.qty END) as qty1, "
                + "	SUM(CASE WHEN po.currency = 'IDR' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as idr1, "
                + "	SUM(CASE WHEN po.currency = 'USD' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as usd1, "
                + "	SUM(CASE WHEN po.currency = 'EUR' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as eur1, "
                + "	SUM(CASE WHEN po.currency = 'JPY' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as jpy1, "
                + "	SUM(CASE WHEN 1 = 1 THEN prsd.qty END) as qty2, "
                + "	SUM(CASE WHEN po.currency = 'IDR' THEN pod.sub_total END) as idr2, "
                + "	SUM(CASE WHEN po.currency = 'USD' THEN pod.sub_total END) as usd2, "
                + "	SUM(CASE WHEN po.currency = 'EUR' THEN pod.sub_total END) as eur2, "
                + "	SUM(CASE WHEN po.currency = 'JPY' THEN pod.sub_total END) as jpy2 "
                + "FROM po "
                + "	INNER JOIN po_detail pod ON pod.po_code = po.po_code "
                + "	INNER JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code "
                + "	INNER JOIN supplier s ON s.supplier_code = po.supplier_code "
                + "WHERE po.is_approved = 'Y' AND YEAR(po.po_date) = @YEAR "
                + "GROUP BY s.supplier_code, s.supplier_name ORDER BY s.supplier_name"
        );

        ListMap.put(Report.PPoIssuedPerSupplier,
                "SELECT *,  RANK() OVER(PARTITION BY idr, usd, php, jpy ORDER BY idr DESC, usd DESC, php DESC, jpy DESC) as rank, \n"
                + "	? as periode \n"
                + "FROM (\n"
                + "	SELECT s.supplier_name, pc.category_name, \n"
                + "		SUM(CASE WHEN po.currency = 'IDR' THEN pod.sub_total END) as idr, \n"
                + "		SUM(CASE WHEN po.currency = 'USD' THEN pod.sub_total END) as usd, \n"
                + "		SUM(CASE WHEN po.currency = 'PHP' THEN pod.sub_total END) as php, \n"
                + "		SUM(CASE WHEN po.currency = 'JPY' THEN pod.sub_total END) as jpy \n"
                + "	FROM prs_detail prd \n"
                + "		LEFT JOIN product p ON p.product_code = prd.productcode \n"
                + "		LEFT JOIN product_category pc ON pc.category_code = p.product_category \n"
                + "		LEFT JOIN po_detail pod ON pod.prsnumber = prd.prsnumber AND pod.product_code = prd.productcode \n"
                + "		LEFT JOIN po ON po.po_code = pod.po_code \n"
                + "		LEFT JOIN supplier s ON s.supplier_code = po.supplier_code \n"
                + "		LEFT JOIN assign_canv_prc acp ON acp.prsnumber = prd.prsnumber AND acp.productcode = prd.productcode \n"
                + "			AND acp.is_selected = 'Y' \n"
                + "	WHERE pod.po_code IS NOT NULL AND po.is_approved = 'Y' AND YEAR(po.po_date) = ? AND MONTH(po.po_date) = ? \n"
                + "	GROUP BY s.supplier_name, pc.category_name \n"
                + ") x ORDER BY category_name, idr DESC, usd DESC, php DESC, jpy DESC"
        );

        ListMap.put(Report.PPoIssuedPerItem,
                "SELECT prs.prsnumber, pod.productcode, cvd.productname, "
                + "SUM(pod.qty) as qty, dep.department_code, pod.currencyCode, pod.unitprice, "
                + "SUM(pod.amount) as amount, po.ponumber, po.supplier_name, cv.canvassername, prs.remarks "
                + "FROM inventory..po po "
                + "LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber "
                + "LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber "
                + "LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prs.prsnumber "
                + "LEFT JOIN inventory..canvassing cv ON cv.prsnumber = prs.prsnumber "
                + "LEFT JOIN inventory..canvassing_detail cvd ON cvd.prsnumber = prs.prsnumber "
                + "LEFT JOIN inventory..department dep ON dep.department_name = po.department_name "
                + "GROUP BY prs.prsnumber, pod.productcode, cvd.productname, dep.department_code, "
                + "pod.currencyCode, pod.unitprice, po.ponumber, po.supplier_name, cv.canvassername, prs.remarks"
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
            "FROM inventory..fish f "
            + "LEFT JOIN inventory..fish_balance fb ON f.id = fb.fish_id "
            + "LEFT JOIN inventory..fish_ws_detail fwd ON f.id = fwd.fish_id "
            + "LEFT JOIN inventory..fish_rr_detail frd ON f.id = fwd.fish_id "
            + "LEFT JOIN inventory..fish_ws fw ON fwd.ws_id = fw.id "
            + "LEFT JOIN inventory..fish_ws_type fwt ON fw.ws_type_id = fwt.id "
            + "WHERE  fb.created_date >= @sDate AND fb.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') OR "
            + "fwd.created_date >= @sDate AND fwd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') OR "
            + "frd.created_date >= @sDate AND frd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') "
            + "GROUP BY f.code "
            + "ORDER BY f.code",
            "SELECT fv.name, SUM(fb.balance) qty "
            + "FROM inventory..fish_balance fb "
            + "LEFT JOIN inventory..fish_vessel fv ON fb.vessel_id = fv.id "
            + "WHERE fb.created_date >= ? AND fb.created_date <= ? "
            + "GROUP BY fv.name"
        });

        ListMap.put(Report.FFrozenFishStockCS, new String[]{
            "DECLARE @sDate DATETIME "
            + "SET @sDate = ? "
            + "DECLARE @eDate DATETIME "
            + "SET @eDate = ? "
            + "SELECT f.code, SUM(fb.balance) rqty, SUM(fwd.total_weight) wqty, SUM(frd.good_weight) eqty,  convert(NVARCHAR, @eDate, 106) as edate " + // added by edw, fixing date format on ms.sqlserver 2008
            "FROM inventory..fish f "
            + "LEFT JOIN inventory..fish_balance fb ON f.id = fb.fish_id "
            + "LEFT JOIN inventory..fish_ws_detail fwd ON f.id = fwd.fish_id "
            + "LEFT JOIN inventory..fish_rr_detail frd ON f.id = fwd.fish_id "
            + "LEFT JOIN inventory..fish_ws fw ON fwd.ws_id = fw.id "
            + "LEFT JOIN inventory..fish_ws_type fwt ON fw.ws_type_id = fwt.id "
            + "WHERE  fb.created_date >= @sDate AND fb.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fw.storage_id = ? OR "
            + "fwd.created_date >= @sDate AND fwd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fw.storage_id = ? OR "
            + "frd.created_date >= @sDate AND frd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fw.storage_id = ? "
            + "GROUP BY f.code "
            + "ORDER BY f.code",
            "SELECT fv.name, SUM(fb.balance) qty "
            + "FROM inventory..fish_balance fb "
            + "LEFT JOIN inventory..fish_vessel fv ON fb.vessel_id = fv.id "
            + "WHERE fb.created_date >= ? AND fb.created_date <= ? or (fv.code = ? or fv.code = ? or fv.code = ? ) "
            + "GROUP BY fv.name"
        });

        ListMap.put(Report.FFrozenFishStockBatchNo, new String[]{
            "DECLARE @sDate DATETIME "
            + "SET @sDate = ? "
            + "DECLARE @eDate DATETIME "
            + "SET @eDate = ? "
            + "SELECT f.code, SUM(fb.balance) rqty, SUM(fwd.total_weight) wqty, SUM(frd.good_weight) eqty,  convert(NVARCHAR, @eDate, 106) as edate " + // added by edw, fixing date format on ms.sqlserver 2008
            "FROM inventory..fish f "
            + "LEFT JOIN inventory..fish_balance fb ON f.id = fb.fish_id "
            + "LEFT JOIN inventory..fish_ws_detail fwd ON f.id = fwd.fish_id "
            + "LEFT JOIN inventory..fish_rr_detail frd ON f.id = fwd.fish_id "
            + "LEFT JOIN inventory..fish_ws fw ON fwd.ws_id = fw.id "
            + "LEFT JOIN inventory..fish_ws_type fwt ON fw.ws_type_id = fwt.id "
            + " LEFT JOIN inventory..fish_vessel fv\n"
            + "    ON\n"
            + "        fw.vessel_id = fv.id "
            + "WHERE  fb.created_date >= @sDate AND fb.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? OR "
            + "fwd.created_date >= @sDate AND fwd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? OR "
            + "frd.created_date >= @sDate AND frd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? "
            + "GROUP BY f.code "
            + "ORDER BY f.code",
            "SELECT fv.name, SUM(fb.balance) qty "
            + "FROM inventory..fish_balance fb "
            + "LEFT JOIN inventory..fish_vessel fv ON fb.vessel_id = fv.id "
            + "WHERE fb.created_date >= ? AND fb.created_date <= ? or (fv.code = ? or fv.code = ? or fv.code = ? ) "
            + "GROUP BY fv.name"
        });

        ListMap.put(Report.FFrozenFishStockSupplier, new String[]{
            "DECLARE @sDate DATETIME "
            + "SET @sDate = ? "
            + "DECLARE @eDate DATETIME "
            + "SET @eDate = ? "
            + "SELECT f.code, SUM(fb.balance) rqty, SUM(fwd.total_weight) wqty, SUM(frd.good_weight) eqty,  convert(NVARCHAR, @eDate, 106) as edate " + // added by edw, fixing date format on ms.sqlserver 2008
            "FROM inventory..fish f "
            + "LEFT JOIN inventory..fish_balance fb ON f.id = fb.fish_id "
            + "LEFT JOIN inventory..fish_ws_detail fwd ON f.id = fwd.fish_id "
            + "LEFT JOIN inventory..fish_rr_detail frd ON f.id = fwd.fish_id "
            + "LEFT JOIN inventory..fish_ws fw ON fwd.ws_id = fw.id "
            + "LEFT JOIN inventory..fish_ws_type fwt ON fw.ws_type_id = fwt.id "
            + " LEFT JOIN inventory..fish_vessel fv\n"
            + "    ON\n"
            + "        fw.vessel_id = fv.id "
            + "WHERE  fb.created_date >= @sDate AND fb.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? OR "
            + "fwd.created_date >= @sDate AND fwd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? OR "
            + "frd.created_date >= @sDate AND frd.created_date <= @eDate AND fwt.code IN ('WSBF','WSABF','WSNC') AND fv.batch_no = ? "
            + "GROUP BY f.code "
            + "ORDER BY f.code",
            "SELECT fv.name, SUM(fb.balance) qty "
            + "FROM inventory..fish_balance fb "
            + "LEFT JOIN inventory..fish_vessel fv ON fb.vessel_id = fv.id "
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

        ListMap.put(Report.IMRR,
                //                "select * " +
                //                "from goodreceive gr " +
                //                "LEFT JOIN goodreceive_detail grd ON gr.grnumber = grd.grnumber " +
                //                "LEFT JOIN product p ON p.product_code = grd.productcode " +
                //                "LEFT JOIN department d ON d.department_code = gr.department"
                "SELECT rrd.rr_code, CONVERT(VARCHAR(10), rr.rr_date, 103) as rr_date, rr.rr_from, po.po_code, "
                + "CONVERT(VARCHAR(10), po.po_date, 103) as po_date, "
                + "p.product_name, p.product_code, rrd.department_code, rrd.qty_g, rrd.uom, u.name "
                + "FROM rr_detail rrd "
                + "INNER JOIN rr ON rr.rr_code = rrd.rr_code "
                + "INNER JOIN po ON po.po_code = rr.po_code "
                + "LEFT JOIN product p ON p.product_code = rrd.product_code "
                + "LEFT JOIN \"user\" u ON u.user_id = rr.created_by "
                + "WHERE rrd.rr_code = ?");

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

        ListMap.put(Report.PPrsNotyetPO,
                "SELECT '' as prs_id, prd.prsnumber as prs_number, REPLACE(CONVERT(VARCHAR(9), prs.prsdate, 6), ' ', '-') as prs_date, \n"
                + "	p.product_name, prd.qty, prs.department_name, '' as date_received, u.name, \n"
                + "	DATENAME(MM, GETDATE()) + RIGHT(CONVERT(VARCHAR(12), GETDATE(), 107), 9) as gen_date \n"
                + "FROM prs_detail prd \n"
                + "	LEFT JOIN prs ON prs.prsnumber = prd.prsnumber \n"
                + "	LEFT JOIN po_detail pod ON pod.prsnumber = prd.prsnumber AND pod.product_code = prd.productcode \n"
                + "	LEFT JOIN product p ON p.product_code = prd.productcode \n"
                + "	LEFT JOIN assign_canv_dtl acd ON acd.prsnumber = prd.prsnumber AND acd.productcode = prd.productcode \n"
                + "	LEFT JOIN \"user\" u ON u.user_id = acd.user_id \n"
                + "WHERE pod.po_code IS NULL AND u.user_id = ? ORDER BY prs_date, prs_number, p.product_name"
        //			"SELECT * " +
        //			"FROM dbo.prs prs, dbo.prs_detail prsd, dbo.po po " +
        //			"WHERE prs.prsnumber = prsd.prsnumber AND po.prsnumber = prs.prsnumber"
        );

        ListMap.put(Report.PPoNotyetDeliveredDP,
                "SELECT * "
                + "FROM inventory..po po "
                + "LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber "
                + "LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber "
                + "LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber "
                + "LEFT JOIN inventory..department dep ON dep.department_name = po.department_name "
                + "WHERE pod.payment = 'DP'"
        );

        ListMap.put(Report.PPoNotyetDeliveredCash,
                "SELECT '' as prs_id, pod.prsnumber as prs_number, REPLACE(CONVERT(VARCHAR(9), prs.prsdate, 6), ' ', '-') as prs_date, \n"
                + "	p.product_name, prsd.qty, prs.department_name, '' as date_received, '' as currency, (pod.sub_total/prsd.qty) as price, \n"
                + "	pod.sub_total, REPLACE(CONVERT(VARCHAR(9), po.po_date, 6), ' ', '-') as po_date, po.po_code, s.supplier_name, '' as rfp_date, \n"
                + "	'' as rfp_paid, acp.top_desc, po.remarks, u.name, DATENAME(MM, GETDATE()) + RIGHT(CONVERT(VARCHAR(12), GETDATE(), 107), 9) as gen_date \n"
                + "FROM po_detail pod \n"
                + "LEFT JOIN po ON po.po_code = pod.po_code \n"
                + "LEFT JOIN supplier s ON s.supplier_code = po.supplier_code \n"
                + "LEFT JOIN prs ON prs.prsnumber = pod.prsnumber \n"
                + "LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code \n"
                + "LEFT JOIN assign_canv_prc acp ON acp.prsnumber = prs.prsnumber AND acp.productcode = prsd.productcode \n"
                + "	AND acp.is_selected = 'Y' AND acp.[top] = 'CASH' \n"
                + "LEFT JOIN product p ON p.product_code = prsd.productcode \n"
                + "LEFT JOIN \"user\" u ON u.user_id = acp.created_by \n"
                + "LEFT JOIN ( \n"
                + "	SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM inventory..rr \n"
                + "		INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code \n"
                + ") x ON x.po_code = pod.po_code AND x.product_code = pod.product_code \n"
                + "WHERE pod.po_code IN ( \n"
                + "	SELECT po.po_code FROM po WHERE po_code IN ( \n"
                + "	SELECT DISTINCT pod.po_code FROM po_detail pod \n"
                + "	LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code \n"
                + "	LEFT JOIN ( \n"
                + "	SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM inventory..rr \n"
                + "		 INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code \n"
                + "	) x ON x.po_code = pod.po_code AND x.product_code = pod.product_code \n"
                + "	WHERE rr_code IS NULL OR x.qty_g < prsd.qty ) \n"
                + ") AND po.is_approved = 'Y' AND (rr_code IS NULL OR x.qty_g < prsd.qty) AND acp.created_by = ? ORDER BY prs_date ASC"
        //			"SELECT * " +
        //			"FROM inventory..po po " +
        //			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
        //			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
        //			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
        //			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name"+
        //			"WHERE pod.payment = 'CASH'"
        );

        ListMap.put(Report.PPoNotyetDeliveredCredit,
                "SELECT '' as prs_id, pod.prsnumber as prs_number, REPLACE(CONVERT(VARCHAR(9), prs.prsdate, 6), ' ', '-') as prs_date, \n"
                + "	p.product_name, prsd.qty, prs.department_name, '' as date_received, '' as currency, (pod.sub_total/prsd.qty) as price, \n"
                + "	pod.sub_total, REPLACE(CONVERT(VARCHAR(9), po.po_date, 6), ' ', '-') as po_date, po.po_code, s.supplier_name, '' as rfp_date, \n"
                + "	'' as rfp_paid, acp.top_desc, po.remarks, u.name, DATENAME(MM, GETDATE()) + RIGHT(CONVERT(VARCHAR(12), GETDATE(), 107), 9) as gen_date \n"
                + "FROM po_detail pod \n"
                + "LEFT JOIN po ON po.po_code = pod.po_code \n"
                + "LEFT JOIN supplier s ON s.supplier_code = po.supplier_code \n"
                + "LEFT JOIN prs ON prs.prsnumber = pod.prsnumber \n"
                + "LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code \n"
                + "LEFT JOIN assign_canv_prc acp ON acp.prsnumber = prs.prsnumber AND acp.productcode = prsd.productcode \n"
                + "	AND acp.is_selected = 'Y' AND acp.[top] = 'CREDIT' \n"
                + "LEFT JOIN product p ON p.product_code = prsd.productcode \n"
                + "LEFT JOIN \"user\" u ON u.user_id = acp.created_by \n"
                + "LEFT JOIN ( \n"
                + "	SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM inventory..rr \n"
                + "		INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code \n"
                + ") x ON x.po_code = pod.po_code AND x.product_code = pod.product_code \n"
                + "WHERE pod.po_code IN ( \n"
                + "	SELECT po.po_code FROM po WHERE po_code IN ( \n"
                + "	SELECT DISTINCT pod.po_code FROM po_detail pod \n"
                + "	LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code \n"
                + "	LEFT JOIN ( \n"
                + "	SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM inventory..rr \n"
                + "		 INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code \n"
                + "	) x ON x.po_code = pod.po_code AND x.product_code = pod.product_code \n"
                + "	WHERE rr_code IS NULL OR x.qty_g < prsd.qty ) \n"
                + ") AND po.is_approved = 'Y' AND (rr_code IS NULL OR x.qty_g < prsd.qty) AND acp.created_by = ? ORDER BY prs_date ASC"
        //			"SELECT * " +
        //			"FROM inventory..po po " +
        //			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
        //			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
        //			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
        //			"LEFT JOIN inventory..department dep ON dep.department_name = po.department_name "+
        //			"WHERE pod.payment = 'CREDIT'"
        );

        ListMap.put(Report.PPoRegisterPerPeriode,
                "SELECT '' as prs_id, prd.prsnumber as prs_number, REPLACE(CONVERT(VARCHAR(9), prs.prsdate, 6), ' ', '-') as prs_date, \n"
                + "	p.product_code, p.product_name, prd.qty, prs.department_name, '' as date_received, '' as currency, acp.unit_price as price, \n"
                + "	(acp.unit_price * prd.qty) as amount, REPLACE(CONVERT(VARCHAR(9), po.po_date, 6), ' ', '-') as po_date, po.po_code, \n"
                + "	s.supplier_name, u.name, po.remarks, ? as periode \n"
                + "FROM prs_detail prd \n"
                + "	LEFT JOIN prs ON prs.prsnumber = prd.prsnumber \n"
                + "	LEFT JOIN po_detail pod ON pod.prsnumber = prd.prsnumber AND pod.product_code = prd.productcode \n"
                + "	LEFT JOIN po ON po.po_code = pod.po_code \n"
                + "	LEFT JOIN product p ON p.product_code = prd.productcode \n"
                + "	LEFT JOIN assign_canv_prc acp ON acp.prsnumber = prs.prsnumber AND acp.productcode = prd.productcode \n"
                + "		AND acp.is_selected = 'Y' \n"
                + "	LEFT JOIN \"user\" u ON u.user_id = acp.created_by \n"
                + "	LEFT JOIN supplier s ON s.supplier_code = po.supplier_code \n"
                + "WHERE po.is_approved = 'Y' AND YEAR(po.po_date) = ? AND MONTH(po.po_date) = ? AND DAY(po.po_date) BETWEEN ? AND ? \n"
                + "ORDER BY prs_date, prs_number, product_name"
        );

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

        ListMap.put(Report.PurchasedItems,
                "DECLARE @YEAR INT, @MONTH INT "
                + "SET @YEAR = ? "
                + "SET @MONTH = ? "
                + "SELECT p.product_code, p.product_name, ? periode, "
                + "	SUM(CASE WHEN MONTH(po.po_date) = @MONTH THEN prsd.qty END) as qty1, "
                + "	SUM(CASE WHEN po.currency = 'IDR' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as idr1, "
                + "	SUM(CASE WHEN po.currency = 'USD' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as usd1, "
                + "	SUM(CASE WHEN po.currency = 'EUR' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as eur1, "
                + "	SUM(CASE WHEN po.currency = 'JPY' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as jpy1, "
                + "	SUM(CASE WHEN 1 = 1 THEN prsd.qty END) as qty2, "
                + "	SUM(CASE WHEN po.currency = 'IDR' THEN pod.sub_total END) as idr2, "
                + "	SUM(CASE WHEN po.currency = 'USD' THEN pod.sub_total END) as usd2, "
                + "	SUM(CASE WHEN po.currency = 'EUR' THEN pod.sub_total END) as eur2, "
                + "	SUM(CASE WHEN po.currency = 'JPY' THEN pod.sub_total END) as jpy2 "
                + "FROM po "
                + "	INNER JOIN po_detail pod ON pod.po_code = po.po_code "
                + "	LEFT JOIN product p ON p.product_code = pod.product_code "
                + "	INNER JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code "
                + "WHERE po.is_approved = 'Y' AND YEAR(po.po_date) = @YEAR "
                + "GROUP BY p.product_code, p.product_name ORDER BY p.product_name"
        );

        ListMap.put(Report.PurchasedPerSupplier,
                "DECLARE @YEAR INT, @MONTH INT "
                + "SET @YEAR = ? "
                + "SET @MONTH = ? "
                + "SELECT s.supplier_code, s.supplier_name, ? periode, "
                + "	SUM(CASE WHEN MONTH(po.po_date) = @MONTH THEN prsd.qty END) as qty1, "
                + "	SUM(CASE WHEN po.currency = 'IDR' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as idr1, "
                + "	SUM(CASE WHEN po.currency = 'USD' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as usd1, "
                + "	SUM(CASE WHEN po.currency = 'EUR' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as eur1, "
                + "	SUM(CASE WHEN po.currency = 'JPY' AND MONTH(po.po_date) = @MONTH THEN pod.sub_total END) as jpy1, "
                + "	SUM(CASE WHEN 1 = 1 THEN prsd.qty END) as qty2, "
                + "	SUM(CASE WHEN po.currency = 'IDR' THEN pod.sub_total END) as idr2, "
                + "	SUM(CASE WHEN po.currency = 'USD' THEN pod.sub_total END) as usd2, "
                + "	SUM(CASE WHEN po.currency = 'EUR' THEN pod.sub_total END) as eur2, "
                + "	SUM(CASE WHEN po.currency = 'JPY' THEN pod.sub_total END) as jpy2 "
                + "FROM po "
                + "	INNER JOIN po_detail pod ON pod.po_code = po.po_code "
                + "	INNER JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code "
                + "	INNER JOIN supplier s ON s.supplier_code = po.supplier_code "
                + "WHERE po.is_approved = 'Y' AND YEAR(po.po_date) = @YEAR "
                + "GROUP BY s.supplier_code, s.supplier_name ORDER BY s.supplier_name"
        );

        ListMap.put(Report.PPoIssuedPerSupplier,
                "SELECT *,  RANK() OVER(PARTITION BY idr, usd, php, jpy ORDER BY idr DESC, usd DESC, php DESC, jpy DESC) as rank, \n"
                + "	? as periode \n"
                + "FROM (\n"
                + "	SELECT s.supplier_name, pc.category_name, \n"
                + "		SUM(CASE WHEN po.currency = 'IDR' THEN pod.sub_total END) as idr, \n"
                + "		SUM(CASE WHEN po.currency = 'USD' THEN pod.sub_total END) as usd, \n"
                + "		SUM(CASE WHEN po.currency = 'PHP' THEN pod.sub_total END) as php, \n"
                + "		SUM(CASE WHEN po.currency = 'JPY' THEN pod.sub_total END) as jpy \n"
                + "	FROM prs_detail prd \n"
                + "		LEFT JOIN product p ON p.product_code = prd.productcode \n"
                + "		LEFT JOIN product_category pc ON pc.category_code = p.product_category \n"
                + "		LEFT JOIN po_detail pod ON pod.prsnumber = prd.prsnumber AND pod.product_code = prd.productcode \n"
                + "		LEFT JOIN po ON po.po_code = pod.po_code \n"
                + "		LEFT JOIN supplier s ON s.supplier_code = po.supplier_code \n"
                + "		LEFT JOIN assign_canv_prc acp ON acp.prsnumber = prd.prsnumber AND acp.productcode = prd.productcode \n"
                + "			AND acp.is_selected = 'Y' \n"
                + "	WHERE pod.po_code IS NOT NULL AND po.is_approved = 'Y' AND YEAR(po.po_date) = ? AND MONTH(po.po_date) = ? \n"
                + "	GROUP BY s.supplier_name, pc.category_name \n"
                + ") x ORDER BY category_name, idr DESC, usd DESC, php DESC, jpy DESC"
        );

        ListMap.put(Report.PPoIssuedPerItem,
                "SELECT prs.prsnumber, pod.productcode, cvd.productname, "
                + "SUM(pod.qty) as qty, dep.department_code, pod.currencyCode, pod.unitprice, "
                + "SUM(pod.amount) as amount, po.ponumber, po.supplier_name, cv.canvassername, prs.remarks "
                + "FROM inventory..po po "
                + "LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber "
                + "LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber "
                + "LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prs.prsnumber "
                + "LEFT JOIN inventory..canvassing cv ON cv.prsnumber = prs.prsnumber "
                + "LEFT JOIN inventory..canvassing_detail cvd ON cvd.prsnumber = prs.prsnumber "
                + "LEFT JOIN inventory..department dep ON dep.department_name = po.department_name "
                + "GROUP BY prs.prsnumber, pod.productcode, cvd.productname, dep.department_code, "
                + "pod.currencyCode, pod.unitprice, po.ponumber, po.supplier_name, cv.canvassername, prs.remarks"
        );

        ListMap.put(Report.PPoForm,
                //                "SELECT * " +
                //                "FROM inventory..po po " +
                //                "LEFT JOIN inventory..po_detail pod ON po.ponumber = pod.ponumber " +
                //                "LEFT JOIN inventory..supplier su ON po.supplier_name = su.supplier_name " +
                //                "LEFT JOIN inventory..product p ON p.product_code = pod.productcode " +
                //                "LEFT JOIN inventory..department dep ON dep.department_name = po.department_name"
                "SELECT prs.id as prsid, p.po_code, p.po_date, p.discount, p.pph, p.ppn, p.currency, p.remarks, s.supplier_code, s.supplier_name, "
                + "s.supplier_address, acp.unit_price, p.approved_by, p.approved_date, p.created_date, u.name, "
                + "pr.product_name, pr.product_code, pd.department_code, prs.qty, prs.uom_name, pd.sub_total, acp.top_desc "
                + "FROM po_detail pd "
                + "LEFT JOIN po p ON p.po_code = pd.po_code "
                + "LEFT JOIN \"user\" u ON u.user_id = p.created_by "
                + "LEFT JOIN supplier s ON s.supplier_code = p.supplier_code "
                + "LEFT JOIN product pr ON pr.product_code = pd.product_code "
                + "LEFT JOIN prs_detail prs ON prs.prsnumber = pd.prsnumber AND prs.productcode = pd.product_code "
                + "LEFT JOIN assign_canv_prc acp ON acp.prsnumber = pd.prsnumber AND acp.productcode = pd.product_code "
                + "AND acp.supplier_code = ? WHERE p.po_code = ?");

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
        ListMap.put(Report.PPrsForm,
                //                "SELECT * " +
                //                "FROM inventory..prs " +
                //                "LEFT JOIN prs_detail prsd ON prs.prsnumber = prsd.prsnumber " +
                //                "WHERE prsd.prsnumber = ?"
                "SELECT prsd.id as prsdid, prs.prsnumber, CONVERT(VARCHAR(10), prs.prsdate, 103) as prsdate, prsd.productcode, "
                + "prsd.productname, prsd.qty, prsd.uom_name, prs.department_name, prs.remarks, "
                + "CONVERT(VARCHAR(20), prs.requestdate, 106)as requestdate, u.name "
                + "FROM inventory..prs LEFT JOIN prs_detail prsd ON prs.prsnumber = prsd.prsnumber "
                + "LEFT JOIN \"user\" u ON u.user_id = prs.createdby "
                + "WHERE prs.prsnumber = ?"
        );
        ListMap.put(Report.PCanvassingForm,
                //                "SELECT * " +
                //                "FROM inventory..canvassing cv " +
                //                "LEFT JOIN inventory..canvassing_detail cvd ON cv.prsnumber = cvd.prsnumber " +
                //                "LEFT JOIN inventory..supplier su ON su.supplier_code = cvd.supplier_code "+
                //                "WHERE su.supplier_code = ?"
                "SELECT s.supplier_code, s.supplier_name, s.contact_person, null as id, u.name, "
                + "(CONVERT( VARCHAR(2), DAY(GETDATE()) ) + ' ' + DATENAME(MONTH, GETDATE()) + ' ' + CONVERT( VARCHAR(4), YEAR(GETDATE()))) as date, "
                + "acp.prsnumber, pd.productname, pd.qty, pd.uom_name FROM inventory..assign_canv_prc acp "
                + "LEFT JOIN inventory..supplier s ON s.supplier_code = acp.supplier_code "
                + "LEFT JOIN inventory..prs_detail pd ON pd.prsnumber = acp.prsnumber AND pd.productcode = acp.productcode "
                + "LEFT JOIN inventory..\"user\" u ON u.user_id = acp.created_by "
                + "WHERE acp.unit_price IS NULL AND acp.supplier_code = ? "
        );

        ListMap.put(Report.PPoConfirmatory,
                "SELECT '' as prs_id, prd.prsnumber as prs_number, REPLACE(CONVERT(VARCHAR(9), prs.prsdate, 6), ' ', '-') as prs_date, \n"
                + "	p.product_code, p.product_name, prd.qty, prs.department_name, '' as date_received, '' as currency, acp.unit_price as price, \n"
                + "	(acp.unit_price * prd.qty) as amount, REPLACE(CONVERT(VARCHAR(9), po.po_date, 6), ' ', '-') as po_date, po.po_code, \n"
                + "	s.supplier_name, u.name, po.remarks \n"
                + "FROM prs_detail prd \n"
                + "	LEFT JOIN prs ON prs.prsnumber = prd.prsnumber \n"
                + "	LEFT JOIN po_detail pod ON pod.prsnumber = prd.prsnumber AND pod.product_code = prd.productcode \n"
                + "	LEFT JOIN po ON po.po_code = pod.po_code \n"
                + "	LEFT JOIN product p ON p.product_code = prd.productcode \n"
                + "	LEFT JOIN assign_canv_prc acp ON acp.prsnumber = prs.prsnumber AND acp.productcode = prd.productcode \n"
                + "		AND acp.is_selected = 'Y' \n"
                + "	LEFT JOIN \"user\" u ON u.user_id = acp.created_by \n"
                + "	LEFT JOIN supplier s ON s.supplier_code = po.supplier_code \n"
                + "WHERE po.is_approved = 'Y' AND YEAR(po.po_date) = ? AND MONTH(po.po_date) = ? AND DAY(po.po_date) BETWEEN ? AND ? \n"
                + "	AND po.remarks LIKE 'CONFIRMATORY%' \n"
                + "ORDER BY prs_date, prs_number, product_name"
        //			"SELECT * " +
        //			"FROM inventory..po po " +
        //			"LEFT JOIN inventory..po_detail pod ON pod.ponumber = po.ponumber " +
        //			"LEFT JOIN inventory..prs ON prs.prsnumber = po.prsnumber " +
        //			"LEFT JOIN inventory..prs_detail prsd ON prsd.prsnumber = prsd.prsnumber " +
        //			"LEFT JOIN inventory..canvassing cnv ON cnv.prsnumber = prs.prsnumber " +
        //                      "LEFT JOIN inventory..department dep ON dep.department_name = po.department_name"
        );

        ListMap.put(Report.PPoPerDepartment,
                "SELECT p.product_code, p.product_name, prd.qty, prs.department_name as department_code, acp.unit_price, \n"
                + "	MAX(CASE WHEN po.currency = 'IDR' THEN pod.sub_total END) as idr, \n"
                + "	MAX(CASE WHEN po.currency = 'USD' THEN pod.sub_total END) as usd, \n"
                + "	MAX(CASE WHEN po.currency = 'PHP' THEN pod.sub_total END) as php, \n"
                + "	MAX(CASE WHEN po.currency = 'EUR' THEN pod.sub_total END) as eur, \n"
                + "	REPLACE(CONVERT(VARCHAR(9), po.po_date, 6), ' ', '-') as po_date, po.po_code, \n"
                + "	d.department_name, ? as periode\n "
                + "FROM prs_detail prd \n"
                + "	LEFT JOIN prs ON prs.prsnumber = prd.prsnumber \n"
                + "	LEFT JOIN product p ON p.product_code = prd.productcode \n"
                + "	LEFT JOIN assign_canv_prc acp ON acp.prsnumber = prd.prsnumber AND acp.productcode = prd.productcode \n"
                + "		AND acp.is_selected = 'Y' \n"
                + "	LEFT JOIN po_detail pod ON pod.prsnumber = prd.prsnumber AND pod.product_code = prd.productcode \n"
                + "	LEFT JOIN po ON po.po_code = pod.po_code \n"
                + "       LEFT JOIN department d ON d.department_code = prs.department_name \n"
                + "WHERE po.is_approved = 'Y' AND YEAR(po.po_date) = ? AND MONTH(po.po_date) = ? \n"
                + "GROUP BY p.product_code, p.product_name, prd.qty, prs.department_name, acp.unit_price, po.po_date, po.po_code, d.department_name \n"
                + "ORDER BY prs.department_name, po_date, po_code, product_name"
        //			"SELECT * "+
        //			"FROM inventory..po_detail pod " +
        //			"LEFT JOIN po ON po.ponumber = pod.ponumber " +
        //			"LEFT JOIN product p ON p.product_code = pod.productcode "+
        //			"WHERE MONTH(podate) = ? AND YEAR(podate) = ?"
        );

        ListMap.put(Report.IMSWS,
                //                "SELECT * " +
                //                "FROM dbo.sws sws, dbo.sws_detail swsd " +
                //                "WHERE sws.swsnumber = swsd.swsnumber"
                "SELECT sws.sws_code, sws.sws_info, CONVERT(VARCHAR(10), sws.sws_date, 103) as sws_date, "
                + "d.department_code, d.department_name, u.name, p.product_code, p.product_name, p.product_category, "
                + "swsd.qty, swsd.uom FROM sws "
                + "INNER JOIN sws_detail swsd ON swsd.sws_code = sws.sws_code "
                + "LEFT JOIN product p ON p.product_code = swsd.product_code "
                + "LEFT JOIN \"user\" u ON u.user_id = sws.created_by "
                + "LEFT JOIN department d ON d.department_code = sws.department_code "
                + "WHERE sws.sws_code = ?");

        ListMap.put(Report.IMTS,
                //                "SELECT * " +
                //                "FROM dbo.ts ts, dbo.ts_detail tsd " +
                //                "WHERE ts.tsnumber = tsd.tsnumber"
                "SELECT ts.ts_code, CONVERT(VARCHAR(10), ts.ts_date, 103) as ts_date, d.department_name as [from], "
                + "u.name, ts.sws_code, ts.ts_info, p.product_name, p.product_code, p.product_category, "
                + "td.qty, sd.uom, d2.department_name as [to] FROM ts "
                + "LEFT JOIN sws ON sws.sws_code = ts.sws_code "
                + "LEFT JOIN sws_detail sd ON sd.sws_code = sws.sws_code "
                + "LEFT JOIN ts_detail td ON td.ts_code = ts.ts_code AND td.product_code = sd.product_code "
                + "LEFT JOIN product p ON p.product_code = sd.product_code "
                + "LEFT JOIN \"user\" u ON u.user_id = ts.created_by "
                + "LEFT JOIN user_role ur ON ur.role_code = u.role_code "
                + "LEFT JOIN department d ON d.department_code = ur.department_code "
                + "LEFT JOIN department d2 ON d2.department_code = sws.department_code "
                + "WHERE ts.ts_code = ? ");

        ListMap.put(Report.IMDR,
                //                "SELECT * FROM dbo.dr dr " +
                //                "LEFT JOIN dbo.dr_detail drd ON dr.drnumber = drd.drnumber " +
                //                "LEFT JOIN product p ON p.product_code = drd.productcode"
                "SELECT dr.dr_code, CONVERT(VARCHAR(10), dr.dr_date, 103) as dr_date, dr.dr_from, dr.dr_fromloc, "
                + "dr.or_code, dr.dr_toloc, dr.dm_code, dr.dr_remarks, dd.dr_qty, dd.dr_uom, s.supplier_name, "
                + "p.product_code, p.product_name, u.name FROM dr "
                + "INNER JOIN dr_detail dd ON dd.dr_code = dr.dr_code "
                + "LEFT JOIN supplier s ON s.supplier_code = dr.supplier_code "
                + "LEFT JOIN product p ON p.product_code = dd.product_code "
                + "LEFT JOIN \"user\" u ON u.user_id = dr.created_by "
                + "WHERE dr.dr_code = ?");

        ListMap.put(Report.FGPTS,
                //                "SELECT * "+
                //                "FROM inventory..pts"
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
        System.out.println("Data:" + data);
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
        byte[] item = null;
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

                String paramString = params.containsKey("params") ? params.get("params").toString() : null;

                // added by edw, override FSummaryWSSlip
                if (reportType.toString().equals("FSummaryWSSlip")) {
                    o = "SELECT ws.ws_no, su.name AS supplier_name, fv.name AS boat_name,"
                            + "fv.batch_no, replace(convert(varchar, ws.date_shift, 111), '/', '-') as date_shift, ws.time_shift, f.code AS type, wsd.total_weight AS data,"
                            + "(SELECT ISNULL(SUM(fs.cooked_weight), 0.00)FROM inventory..fish_spoilage fs "
                            + "WHERE fs.vessel_id = ws.vessel_id AND fs.fish_id = wsd.fish_id "
                            + "AND fs.date_shift = ws.date_shift) AS spoilage "
                            + "FROM inventory..fish_ws_detail wsd "
                            + "LEFT JOIN inventory..fish_ws ws ON ws.id = wsd.ws_id "
                            + "LEFT JOIN inventory..fish_vessel fv ON fv.id = ws.vessel_id "
                            + "LEFT JOIN inventory..fish_supplier su ON su.id = fv.supplier_id "
                            + "LEFT JOIN inventory..fish f ON f.id = wsd.fish_id "
                            + "LEFT JOIN inventory..fish_ws_type fwt ON fwt.id = ws.ws_type_id "
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

                System.out.println("size list >>>> " + list.size());
                for (ResultSetRow resultSetRow : list) {
                    System.out.println("disiini >>> " + resultSetRow.toString());
                }

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
