<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.app.wms.engine.db.dto.FishWSType"%>
<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Generate Report</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function(){
                
                $('#addForm').validationEngine('attach');   
                
                $('#startDate').datepicker({                        
                    dateFormat: "yy-mm-dd"
                });
                
                $('#endDate').datepicker({                        
                    dateFormat: "yy-mm-dd"
                });
                
                $('#btnGenerate').click(function() {
                	var type = $('#type').val();
                    var item = $('#item').val();
                	var params = $('#params').val();
                	var url = "GenerateReport.htm?action=index&type=" + type + "&item=" + $('#typeCustom').val() + 
                            '&params=' + $('#startDate').val() + ':' + $('#endDate').val()+
                            '&periode1=' + $('#startDate').val() + '&periode2=' + $('#endDate').val();
                	
//                	if(!(params === "null")) {
//                            url += "&params="+params;
//                	} 
                	
                	location.href = url;
                });
                
            });
        </script>
    </head>
    <body>
        <%
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        	String item = (String) m.get("item");
        	String params = (String) m.get("params");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
			
            <div id="content" style="display: none" class="span-24 last">
	
                <div class="box">
                    <form action="FishReport.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="mode" value="" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <input type="hidden" id="item" name="item" value="<%=item%>" />
                        <input type="hidden" id="params" name="params" value="<%=params%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Generate <%=item%></caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                   <td class="style1">Start Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="startDate" name="startDate" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                   <td class="style1">End Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="endDate" name="endDate" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">Report Type</td>
                                    <td class="style1">
                                        <select id="typeCustom" name="typeCustom">
                                        	<option value="FLaporanPemasukanBarangPerDokumenPabean">Laporan pemasukan barang per dokumen pabean</option>
                                        	<option value="FLaporanPengeluaranBarangPerDokumenPabean">Laporan pengeluaran barang per dokumen pabean</option>
                                                <option value="FLaporanPengeluaranBahanBakuDanBahanPenolong">Laporan pertanggung jawaban mutasi barang</option>
                                                <option value="FLaporanPengeluaranMutasiBarangJadi">Laporan pertanggung jawaban mutasi barang jadi</option>
                                                <option value="FLaporanPengeluaranMutasiBarangDanScrap">Laporan pertanggung jawaban mutasi barang sisa dan scrap</option>
                                                <option value="FLaporanPengeluaranMutasiMesin">Laporan pertanggung jawaban mutasi mesin dan peralatan perkantoran</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">Format</td>
                                    <td class="style1">
                                        <select id="type" name="type">
                                        	<option value="pdf">PDF</option>
                                        	<option value="xls">XLS</option>
                                            <option value="csv">CSV</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">

                            <tbody class="tbl-nohover">
                                
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" 
                                            	role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                            	name="btnGenerate" id="btnGenerate" value="Generate Report" class="save" />
                                        </label>
                                    </td>
                                </tr>
                           </tfoot>
                        </table>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>                      
    </body>
</html>