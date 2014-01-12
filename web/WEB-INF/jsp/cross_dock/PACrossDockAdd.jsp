<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Put Away Cross Dock Create</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="PAForm" method="post" action="PACrossDock.htm" autocomplete="off">
                        <input type="hidden" value="save" name="action" />
                        <input type="hidden" id="detail" name="detail" />
                        <input type="hidden" id="locationCode" name="locationCode" value="" />
                        <input type="hidden" id="tallymanCode" name="tallymanCode" value="" />
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Put Away Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Good Receive No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="grCode" name="grCode" size="30" maxlength="30" readonly />
                                        </label>
                                        <img src="resources/images/search.png" width="16" />
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Location</td>
                                    <td class="style1">
                                        <input type="text" class="validate[required]" id="locationName" name="locationName" size="30" readonly />
                                        <img src="resources/images/search.png" width="16" />
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Tallyman</td>
                                    <td class="style1">
                                        <input type="text" class="validate[required]" id="tallymanName" name="tallymanName" size="30" readonly />
                                        <img src="resources/images/search.png" width="16" />
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Put Away Info</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="paInfo" name="paInfo" size="100" maxlength="100" />
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
        <div id="dialog2" style="display:none; z-index:1;" status="">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        <div id="dialog3" style="display:none; z-index:1;" status="">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        <div id="dialog4" style="display:none; z-index:1;" status="">
            <table id="list4" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        <script type="text/javascript">
            function getGR(){
                var grCode = $('#grCode').val().trim();
                if(grCode != ""){
                    $.ajax({
                        url: "PACrossDock.htm",
                        data: "action=gr&grCode=" + grCode,
                        dataType: "text",
                        success: function(html){
                            html = html.trim();
                            if(html != 'N'){
                                $('#detail').val("Data valid");
                                $('#list').html(html);
                            } else {
                                $('#detail').val("");
                                $('#grCode').val("");
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
                $('#btnSave').click(function(){
                    if($('#PAForm').validationEngine('validate') && $('#detail').val() != ""){
                        $('#PAForm').submit();
                    }
                });
                
                /* select good receive function */
                $("#grCode").click(function() {
                    if($("#dialog2").attr("status") != "gr"){
                        jQuery("#list2").jqGrid({
                            url:'PACrossDock.htm?action=json&type=gr', 
                            datatype: "json", 
                            hidegrid: false, 
                            shrinkToFit: true, 
                            autowidth: true,
                            colNames:['Good Receive No','Purchase Order No','Good Receive Date', 'Created By', 'Created Date'], 
                            colModel:[{name:'gRCode',index:'gr_code'}, {name:'updatedBy',index:'po_number'}, {name:'gRDate',index:'gr_date'}, {name:'createdBy',index:'created_by'}, {name:'createdDate',index:'created_date'}], 
                            rowNum:10, 
                            rowList:[10,20,30], 
                            jsonReader :{
                                repeatitems: false
                            },
                            onSelectRow: function(id){ 
                                if(id != null){
                                    $("#grCode").val($(this).getRowData(id).gRCode);
                                    $("#dialog2").dialog('close');
                                    getGR();
                                }
                            },
                            pager: '#gRDate', 
                            sortname: 'created_by', 
                            viewrecords: true, 
                            sortorder: "asc", 
                            caption:"Select Good Receive" }); 
                        jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                        $("#dialog2").attr("status","gr");
                    }
                    $("#dialog2").dialog({ width: "auto", height: "auto", position: "center", modal: true, zindex: 9999, title: 'Select Good Receive' });
                });
                /* select tallyman function */
                $("#tallymanName").click(function() {
                    if($("#dialog3").attr("status") != "tallyman"){
                        jQuery("#list3").jqGrid({
                            url:'PACrossDock.htm?action=json&type=tallyman', 
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
                /* select location function */
                $("#locationName").click(function() {
                    if($("#dialog4").attr("status") != "location"){
                        jQuery("#list4").jqGrid({
                            url:'PACrossDock.htm?action=json&type=location', 
                            datatype: "json", 
                            hidegrid: false, 
                            shrinkToFit: true, 
                            autowidth: true,
                            colNames:['Location Code','Location Name','Location Area', 'Created By', 'Created Date'], 
                            colModel:[{name:'locationCode',index:'location_code'}, {name:'locationName',index:'location_name'}, {name:'locating_area',index:'locating_area'}, {name:'createdBy',index:'created_by'}, {name:'createdDate',index:'created_date'}], 
                            rowNum:10, 
                            rowList:[10,20,30], 
                            jsonReader :{
                                repeatitems: false
                            },
                            onSelectRow: function(id){ 
                                if(id != null){
                                    $("#locationCode").val($(this).getRowData(id).locationCode);
                                    $("#locationName").val($(this).getRowData(id).locationName);
                                    $("#dialog4").dialog('close');
                                }
                            },
                            pager: '#pager4', 
                            sortname: 'locationCode', 
                            viewrecords: true, 
                            sortorder: "asc", 
                            caption:"Select Location" }); 
                        jQuery("#list4").jqGrid('navGrid','#pager4',{edit:false,add:false,del:false});
                        $("#dialog4").attr("status","location");
                    }
                    $("#dialog4").dialog({ width: "auto", height: "auto", position: "center", modal: true, zindex: 9999, title: 'Select Location' });
                });
            });
        </script>
    </body>
</html>