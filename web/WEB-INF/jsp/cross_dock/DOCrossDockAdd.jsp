<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Delivery Order Cross Dock Create</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="DOForm" method="post" action="DOCrossDock.htm" autocomplete="off">
                        <input type="hidden" value="save" name="action" />
                        <input type="hidden" value="" name="detail" />
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Delivery Order Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Sales Order No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="soCode" name="soCode" size="30" maxlength="30" />
                                        </label>
                                        <img src="resources/images/search.png" width="16" />
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                    <td class="style1" style="vertical-align: top" rowspan="3" width="500">Delivery Address: <br /><label id="cc" style="font-size: 18px"></label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Delivery Order Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="doDate" name="doDate" size="30" maxlength="30" />
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Transporter Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" size="30" value="" id="doName" name="doName" />
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">leave blank if use internal transporter</label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="3">
                                    <span class="style1">
                                        <label>
                                            <input type="button" value="Save" id="btnSave" name="btnSave" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;">
                                        </label>
                                        <input type="button" value="Cancel" id="btnBack" name="button" class="style1 ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                    </span>
                                </td>
                            </tr></tfoot>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Delivery Order Cross Dock - Detail</caption>
                            <thead>
                                <tr>
                                    <td>No</td>
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
        <script type="text/javascript">
            function getSO(){
                var soCode = $('#soCode').val().trim();
                if(soCode != ""){
                    $.ajax({
                        url: "DOCrossDock.htm",
                        data: "action=so&soCode=" + soCode,
                        dataType: "text",
                        success: function(html){
                            html = html.trim();
                            if(html != 'N'){
                                $('#list').html(html);
                                $('#detail').val("Data is valid")

                                /* get consignee detail */
                                $.ajax({
                                    url: "DOCrossDock.htm",
                                    data: "action=getCC&soCode=" + soCode,
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
            $(function(){
                $('#doDate').datepicker({dateFormat: "dd/mm/yy"});
                $('#btnSave').click(function(){
                    if($('#DOForm').validationEngine('validate') && $('#detail').val() != ""){
                        $('#DOForm').submit();
                    }
                });

                /* Pop Up Selector */
                $("#soCode").click(function () {
                    $("#dialog2").dialog({ width: "auto", height: "auto", position: "center", modal: true, zindex: 9999, title: 'Select Sales Order' });
                });
                jQuery("#list2").jqGrid({
                    url:'DOCrossDock.htm?action=json&type=so&c=do', 
                    datatype: "json", 
                    hidegrid: false, 
                    shrinkToFit: true, 
                    autowidth: true,
                    colNames:['Sales Order No','Purchase Order No','Consignee', 'Sales Order Date'], 
                    colModel:[{name:'sOCode',index:'so_code'}, {name:'createdBy',index:'po_number'}, {name:'updatedBy',index:'consignee_name'}, {name:'sODate',index:'so_date'}], 
                    rowNum:10, 
                    rowList:[10,20,30], 
                    jsonReader :{
                        repeatitems: false
                    },
                    onSelectRow: function(id){ 
                        if(id != null){
                            $("#soCode").val($(this).getRowData(id).sOCode);
                            $("#dialog2").dialog('close');
                            getSO();
                        }
                    },
                    pager: '#pager2', 
                    sortname: 'so_date', 
                    viewrecords: true, 
                    sortorder: "asc", 
                    caption:"Select Sales Order" }); 
                jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
            });
        </script>
    </body>
</html>