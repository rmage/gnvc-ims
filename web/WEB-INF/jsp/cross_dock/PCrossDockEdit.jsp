<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Picking Cross Dock Update</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="PAForm" method="post" action="PCrossDock.htm" autocomplete="off">
                        <input type="hidden" name="action" value="edit" >
                        <input type="hidden" name="pCode" value="${model.list.PCode}" />
                        <input type="hidden" id="tallymanCode" name="tallymanCode" value="${model.list.tallymanCode}" />
                        <input type="hidden" id="detail" name="detail" value="" />
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Picking Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Picking No</td>
                                    <td class="style1"><label>${model.list.PCode}</label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Sales Order No</td>
                                    <td class="style1"><label>${model.list.SOCode}</label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Picker</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="tallymanName" name="tallymanName" size="30" maxlength="30" value="${model.list.updatedBy}" />
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Picking Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="pDate" name="pDate" size="30" maxlength="30" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${model.list.PDate}" />" />
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="3">
                                    <span class="style1">
                                        <label>
                                            <input type="submit" value="Save" id="btnSave" name="btnSave" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;">
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
                                    <td colspan="4"><span style="float: right">Location: <b>${model.listPA.createdBy}</b></span></td>
                                </tr>
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
        <div id="dialog3" style="display:none; z-index:1;" status="">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        <script type="text/javascript">
            $(function(){
                $.ajax({
                    url: "PCrossDock.htm",
                    data: "action=so&soCode=${model.list.SOCode}",
                    dataType: "text",
                    success: function(html){
                        html = html.trim();
                        if(html != 'N'){
                            $('#detail').val("Data valid");
                            $('#list').html(html);
                        } else {
                            $('#detail').val("");
                            $('#soCode').val("");
                            $('#list').html("");
                        }
                    },
                    error: function(jqXHR, textStatus, data){
                        alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                    }
                });
                $('#pDate').datepicker({dateFormat: "dd/mm/yy"});
                $('#btnSave').click(function(){
                    if($('#PForm').validationEngine('validate') && $('#detail').val() != ""){
                        $('#PForm').submit();
                    }
                });
                
                /* pop up selector */
                /* select tallyman function */
                $("#tallymanName").click(function() {
                    if($("#dialog3").attr("status") != "tallyman"){
                        jQuery("#list3").jqGrid({
                            url:'PCrossDock.htm?action=json&type=tallyman', 
                            datatype: "json", 
                            hidegrid: false, 
                            shrinkToFit: true, 
                            autowidth: true,
                            colNames:['Tallyman No','Tallyman Name','Job Function', 'Created By', 'Created Date'], 
                            colModel:[{name:'tallyId',index:'tally_id'}, {name:'name',index:'name'}, {name:'jobfunction',index:'jobfunction'}, {name:'createdBy',index:'created_by'}, {name:'createdDate',index:'created_date'}], 
                            rowNum:10, 
                            rowList:[10,20,30], 
                            jsonReader :{
                                repeatitems: false
                            },
                            onSelectRow: function(id){ 
                                if(id != null){
                                    $("#tallymanCode").val($(this).getRowData(id).tallyId);
                                    $("#tallymanName").val($(this).getRowData(id).name);
                                    $("#dialog3").dialog('close');
                                }
                            },
                            pager: '#pager3', 
                            sortname: 'tallyId', 
                            viewrecords: true, 
                            sortorder: "asc", 
                            caption:"Select Tallyman" }); 
                        jQuery("#list3").jqGrid('navGrid','#pager3',{edit:false,add:false,del:false});
                        $("#dialog3").attr("status","tallyman");
                    }
                    $("#dialog3").dialog({ width: "auto", height: "auto", position: "center", modal: true, zindex: 9999, title: 'Select Tallyman' });
                });
            });
        </script>
    </body>
</html>