<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Sales Order Cross Dock Create</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="SOForm" method="post" action="SOCrossDock.htm" autocomplete="off">
                        <input type="hidden" value="save" name="action" />
                        <input type="hidden" value="" name="detail" />
                        <input type="hidden" id="poCode" name="poCode" />
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Sales Order Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Sales Order No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" name="soCode" size="30" maxlength="30">
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                    <td class="style1" style="vertical-align: top" rowspan="4" width="500">Consignee Detail: <br /><label id="cc" style="font-size: 18px"></label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Purchase Order No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="poNumber" name="poNumber" size="30" maxlength="30" />
                                        </label>
                                        <img src="resources/images/search.png" width="16" />
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Consignee</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="consigneeCode" name="consigneeCode" size="30" maxlength="30" />
                                        </label>
                                        <img src="resources/images/search.png" width="16" />
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Sales Order Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" size="30" value="" id="soDate" name="soDate" />
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="3">
                                    <span class="style1">
                                        <label>
                                            <input type="button" value="Save" id="btnSave" name="btnSave" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;">
                                        </label>
                                        <input type="button" value="Cancel" id="btnBack" name="button" class="style1 ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;">
                                    </span>
                                </td>
                            </tr></tfoot>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Sales Order Cross Dock - Detail</caption>
                            <thead>
                                <tr>
                                    <td>Product Code</td>
                                    <td>Product Name</td>
                                    <td>Quantity</td>
                                    <td>Type</td>
                                </tr>
                            </thead>
                            <tbody id="list" class="tbl-nohover">
                                
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>
        <div id="dialog2" style="display:none; z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        <div id="dialog3" style="display:none; z-index:1;">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        <script type="text/javascript">
            function getPO(){
                var poCode = $('#poCode').val().trim();
                if(poCode != ""){
                    $.ajax({
                        url: "SOCrossDock.htm",
                        data: "action=po&poCode=" + poCode,
                        dataType: "text",
                        success: function(html){
                            html = html.trim();
                            if(html != 'N'){
                                $('#list').html(html);
                                $('#detail').val("Data is valid")
                            } else {
                                $('#detail').val("");
                                $('#list').html("");
                            }
                        },
                        error: function(jqXHR, textStatus, data){
                            alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                        }
                    });
                }
            }
            function getCC(){
                var cc = $('#consigneeCode').val().trim();
                if(cc != ""){
                    $.ajax({
                        url: "SOCrossDock.htm",
                        data: "action=cc&consigneeCode=" + cc,
                        dataType: "text",
                        success: function(html){
                            html = html.trim();
                            if(html != 'N'){
                                $('#cc').html(html);
                            } else {
                                $('#cc').html("");
                            }
                        },
                        error: function(jqXHR, textStatus, data){
                            alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                        }
                    });
                }
            }
            $(function(){
                $('#soDate').datepicker({                        
                    dateFormat: "dd/mm/yy"
                });
                $('#btnSave').click(function(){
                    if($('#SOForm').validationEngine('validate') && $('#detail').val() != ""){
                        $('#SOForm').submit();
                    }
                });

                /* Pop Up Selector */
                $("#poNumber").click(function () {
                    $("#dialog2").dialog({ width: "auto", height: "auto", position: "center", modal: true, zindex: 9999, title: 'Select Purchase Order No' });
                });
                jQuery("#list2").jqGrid({
                    url:'SOCrossDock.htm?action=json&type=po&c=so', 
                    datatype: "json", 
                    hidegrid: false, 
                    shrinkToFit: true, 
                    autowidth: true,
                    colNames:['Code','Purchase Order No','Estimation Delivery Date', 'Created By', 'Created Date'], 
                    colModel:[{name:'pOCode',index:'po_cod',hidden:true}, {name:'pONumber',index:'po_number'}, {name:'pODate',index:'po_date'}, {name:'createdBy',index:'created_by'}, {name:'createdDate',index:'created_date'}], 
                    rowNum:10, 
                    rowList:[10,20,30], 
                    jsonReader :{
                        repeatitems: false
                    },
                    onSelectRow: function(id){ 
                        if(id != null){
                            $("#poCode").val($(this).getRowData(id).pOCode);
                            $("#poNumber").val($(this).getRowData(id).pONumber);
                            $("#dialog2").dialog('close');
                            getPO();
                        }
                    },
                    pager: '#pager2', 
                    sortname: 'po_date', 
                    viewrecords: true, 
                    sortorder: "asc", 
                    caption:"Select Purchase Order" }); 
                jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                
                $("#consigneeCode").click(function () {
                    $("#dialog3").dialog({ width: "auto", height: "auto", position: "center", modal: true, zindex: 9999, title: 'Select Consignee' });
                });
                jQuery("#list3").jqGrid({
                    url:'SOCrossDock.htm?action=json&type=consignee', 
                    datatype: "json", 
                    hidegrid: false, 
                    shrinkToFit: true, 
                    autowidth: true,
                    colNames:['Consignee No','Consignee Name','Consignee Address', 'Consignee Phone'], 
                    colModel:[{name:'consigneeCode',index:'consignee_code'}, {name:'consigneeName',index:'consignee_name'}, {name:'address1',index:'address1'}, {name:'consigneePhone',index:'consignee_phone'}], 
                    rowNum:10, 
                    rowList:[10,20,30], 
                    jsonReader :{
                        repeatitems: false
                    },
                    onSelectRow: function(id){
                        if(id != null){
                            $("#consigneeCode").val($(this).getRowData(id).consigneeCode);
                            $("#dialog3").dialog('close');
                            getCC();
                        }
                    },
                    pager: '#pager3', 
                    sortname: 'consigneeName', 
                    viewrecords: true, 
                    sortorder: "asc", 
                    caption:"Select Consignee" }); 
                jQuery("#list3").jqGrid('navGrid','#pager3',{edit:false,add:false,del:false});
            });
        </script>
    </body>
</html>