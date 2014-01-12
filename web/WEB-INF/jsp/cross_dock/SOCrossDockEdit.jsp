<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Sales Order Cross Dock Update</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="SOForm" method="post" action="SOCrossDock.htm" autocomplete="off">
                        <input type="hidden" name="action" value="edit" >
                        <input type="hidden" name="soCode" value="${model.list.SOCode}" />
                        <input type="hidden" id="poCode" name="poCode" value="${model.list.POCode}" />
                        <input type="hidden" id="detail" name="detail" value="" />
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Sales Order Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Sales Order Number</td>
                                    <td class="style1"><label>${model.list.SOCode}</label></td>
                                    <td class="style1" style="vertical-align: top" rowspan="4" width="500">Consignee Detail: <br /><label id="cc" style="font-size: 18px"></label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Purchase Order Number</td>
                                    <td class="style1"><label>${model.list.createdBy}</label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Consignee</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="consigneeCode" name="consigneeCode" size="30" maxlength="30" value="${model.list.consigneeCode}" />
                                        </label>
                                        <input type="button" id="btnSelectCC" value="Select" />
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Sales Order Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" size="30" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${model.list.SODate}" />" id="soDate" name="soDate" />
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
        <div id="dialog3" style="display:none; z-index:1;">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        <script type="text/javascript">
            $(function(){
                    $('#soDate').datepicker({                        
                    dateFormat: "dd/mm/yy"
                });
                $.ajax({
                    url: "SOCrossDock.htm",
                    data: "action=po&poCode=${model.list.POCode}",
                    dataType: "text",
                    success: function(html){
                        html = html.trim();
                        if(html != 'N'){
                            $('#detail').val("Data is valid");
                            $('#list').html(html);
                        } else {
                            $('#detail').val("");
                            $('#list').html("");
                        }
                    },
                    error: function(jqXHR, textStatus, data){
                        alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                    }
                });
                $('#btnSelectCC').click(function(){
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
                });
                $('#btnSave').click(function(){
                    if($('#SOForm').validationEngine('validate') && $('#detail').val() != ""){
                        $('#SOForm').submit();
                    }
                });
                $('#btnSelectCC').click();

                /* pop up slector */
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
                    colModel:[{name:'consigneeCode',index:'consignee_code'}, {name:'consigneeName',index:'consignee_name'}, {name:'consigneeAddress',index:'consignee_address'}, {name:'consigneePhone',index:'consignee_phone'}], 
                    rowNum:10, 
                    rowList:[10,20,30], 
                    jsonReader :{
                        repeatitems: false
                    },
                    onSelectRow: function(id){ 
                        if(id != null){
                            $("#consigneeCode").val($(this).getRowData(id).consigneeCode);
                            $("#dialog3").dialog('close');
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