<%-- created by FYA --%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Good Receive Cross Dock Create</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="GRForm" method="post" action="GRCrossDock.htm" autocomplete="off">
                        <input type="hidden" value="save" name="action">
                        <input type="hidden" id="poCode" name="poCode" value="" />
                        <input type="hidden" id="detail" name="detail" value="" />
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Good Receive Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Purchase Order No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="poNumber" name="poNumber" size="30" maxlength="30" readonly />
                                        </label>
                                        <img src="resources/images/search.png" width="16" />
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Good Receive Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" size="30" value="<fmt:formatDate pattern="dd/MM/yyyy" value="<%= new Date() %>" />" id="grDate" name="grDate" />
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="2">
                                    <span class="style1">
                                        <label>
                                            <input type="button" value="Save" id="btnSave" name="btnSave" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                        </label>
                                        <input type="button" value="Cancel" id="btnBack" name="button" class="style1 ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                    </span>
                                </td>
                            </tr></tfoot>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Good Receive Cross Dock - Detail</caption>
                            <thead>
                                <tr>
                                    <td>Product Code</td>
                                    <td>Product Name</td>
                                    <td>Quantity</td>
                                    <td>Type</td>
                                    <td>Status</td>
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
                function getPO(){
                    $(this).attr("disabled", "disabled");
                    var poCode = $('#poCode').val().trim();
                    if(poCode != ""){
                        $.ajax({
                            url: "GRCrossDock.htm",
                            data: "action=po&poCode=" + poCode,
                            dataType: "text",
                            success: function(html){
                                html = html.trim();
                                if(html != 'N'){
                                    $('#list').html(html);
                                } else {
                                    $('#list').html("");
                                }
                            },
                            error: function(jqXHR, textStatus, data){
                                alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                            }
                        });
                    } else {
                        $('#list').html("");
                    }
                    $(this).removeAttr("disabled");
                }
                $(function(){
                    $('#grDate').datepicker({                        
                        dateFormat: "dd/mm/yy"
                    });
                    $('#btnSave').mousedown(function(){
                        $('#detail').val("");
                        $('#list tr').each(function(){
                            var data = $(this).attr('code') + "," + $(this).attr('status');
                            
                            if($('#detail').val() == ""){
                                $('#detail').val(data);
                            } else{
                                $('#detail').val($('#detail').val() + ":" + data);
                            }
                        });
                    })
                    $('#btnSave').click(function(){
                        if($('#GRForm').validationEngine('validate') && $('#detail').val() != ""){
                            $('#GRForm').submit();
                        }
                    });
                    
                    $('.list-status').live('click', function(){
                        var flag = $(this).parent('td').parent('tr');
                        if(flag.attr('status') == 'N'){
                            flag.attr('status', 'Y')
                        } else {
                            flag.attr('status', 'N')
                        }
                    });
                    
                    $("#poNumber").click(function () {
                        $("#dialog2").dialog({ width: "auto", height: "auto", position: "center", modal: true, zindex: 9999, title: 'Select Purchase Order No' });
                    });
                    jQuery("#list2").jqGrid({
                        url:'GRCrossDock.htm?action=json&type=po&c=gr', 
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
                });
            </script>
    </body>
</html>