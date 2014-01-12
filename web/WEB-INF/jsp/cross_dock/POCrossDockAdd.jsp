<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Purchase Order Cross Dock Create</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form method="post" id="formPO" action="POCrossDock.htm" autocomplete="off">
                        <input type="hidden" value="save" name="action" />
                        <input type="hidden" id="detail" name="detail" value="" />
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Purchase Order Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Purchase Order No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" name="poNumber" size="30" maxlength="30" />
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Estimation Delivery Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" size="30" value="" id="poDate" name="poDate" />
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Type</td>
                                    <td class="style1">
                                        <select id="type" name="type">
                                            <option>Normal</option>
                                            <option>Quarantine</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="2">
                                    <span class="style1">
                                        <label>
                                            <input type="button" value="Save" id="btnSave" name="btnSave" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;">
                                        </label>
                                        <input type="button" value="Cancel" id="btnBack" name="button" class="style1 ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;">
                                    </span>
                                </td>
                            </tr></tfoot>
                        </table>
                        <table id="addProduct" class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Product Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Product Code</td>
                                    <td><input type="text" id="addCode" size="20" maxlength="30" /> <img src="resources/images/search.png" width="16" /></td>
                                </tr>
                                <tr>
                                    <td>Quantity:</td>
                                    <td><input type="text" id="addQty" size="4" maxlength="6" /></td>
                                </tr>
                                <tr>
                                    <td>Type:</td>
                                    <td>
                                        <select id="addType">
                                            <option>Normal</option>
                                            <option>Promo</option>
                                            <option>Tester</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr><td colspan="2"><input type="button" id="btnAdd" value="Add Product" /></td></tr>
                            </tfoot>
                        </table>
                        <table id="infoPutaway" class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Putaway Info</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Putaway No</td>
                                    <td><input type="text" id="putawayCode" name="putawayCode" size="30" readonly /></td>
                                </tr>
                                <tr>
                                    <td>Putaway Date</td>
                                    <td><input type="text" id="putawayDate" size="30" readonly /></td>
                                </tr>
                                <tr>
                                    <td>Good Receive No</td>
                                    <td><input type="text" id="grCode" size="30" readonly /></td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Purchase Order Cross Dock - Detail</caption>
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
        <script type="text/javascript">
            $('#infoPutaway').hide();
            function resetAdd(){
                $('#addCode').val("");
                $('#addQty').val("");
                $('#addType').val("");
                $('#addCode').focus();
            }
            function btnAdd(){
                if(!$('#addCode').val().isEmpty){
                    $.ajax({
                        url: "POCrossDock.htm",
                        data: "action=product&productCode=" + $('#addCode').val(),
                        dataType: "text",
                        success: function(html){
                            html = html.trim();
                            if(html != 'N'){
                                var split = html.split(',');
                                var data = split[0] + "," + split[1] + "," + $('#addQty').val() + "," + $('#addType').val();
                                var product = '<tr class="detail-item" data="' + data + '">' +  
                                    '<td>' + split[0] + '</td>' + 
                                    '<td>' + split[1] + '</td>' + 
                                    '<td>' + $('#addQty').val() + '</td>' + 
                                    '<td>' + $('#addType').val() + '</td></tr>';

                                $('#list').append(product);

                                resetAdd()
                            }
                        },
                        error: function(jqXHR, textStatus, data){
                            alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                        }
                    });
                }
            }
            function btnEdit(){
                var tr = $('.detail-item[data="' + $('#detail').val() + '"]');
                var split = tr.attr('data').split(',');
                var data = $('#addCode').val() + "," + split[1] + "," + $('#addQty').val() + "," + $('#addType').val();
                tr.attr('data', data);

                split = data.split(',');
                var i = 0;
                $('.detail-item[data="' + data + '"] td').each(function(){
                    $(this).html(split[i]);
                    i++;
                });
                resetAdd();
                $('#btnAdd').val('Add Product');
                $('#btnAdd').unbind('click').click(btnAdd);
            }

            $(function(){
                $('#poDate').datepicker({                        
                    dateFormat: "dd/mm/yy"
                });
                $('#btnAdd').click(btnAdd);
                $('#btnSave').focus(function(){
                    $('#detail').val("");
                    $('.detail-item').each(function(){
                        if($('#detail').val() == ""){
                            $('#detail').val($(this).attr('data'));
                        } else{
                            $('#detail').val($('#detail').val() + ":" + $(this).attr('data'));
                        }
                    });
                });
                $('#btnSave').click(function(){
                    if($('#formPO').validationEngine('validate') && $('#detail').val() != ""){
                        $('#formPO').submit();
                    }
                });

                $('.detail-item').live('click', function(){
                    var data = $(this).attr('data');
                    $('#detail').val(data);

                    var split = data.split(',');

                    $('#addCode').val(split[0]);
                    $('#addQty').val(split[2]);
                    $('#addType').val(split[3]);
                    $('#btnAdd').val('Edit Product');
                    $('#btnAdd').unbind('click').click(btnEdit);
                });
                $('.detail-item').live('dblclick', function(){
                    resetAdd();
                    $(this).remove();
                });
                
                /* pop up selector */
                $("#addCode").click(function () {
                    $("#dialog2").dialog({ width: "auto", height: "auto", position: "center", modal: true, zindex: 9999, title: 'Select Product' });
                });
                jQuery("#list2").jqGrid({
                    url:'POCrossDock.htm?action=json&type=product', 
                    datatype: "json", 
                    hidegrid: false, 
                    shrinkToFit: true, 
                    autowidth: true,
                    colNames:['Product Code','Product Name','Product Category', 'Created By', 'Created Date'], 
                    colModel:[{name:'productCode',index:'product_code'}, {name:'productName',index:'product_name'}, {name:'productCategory',index:'product_category'}, {name:'createdBy',index:'created_by'}, {name:'createdDate',index:'created_date'}], 
                    rowNum:10, 
                    rowList:[10,20,30], 
                    jsonReader :{
                        repeatitems: false
                    },
                    onSelectRow: function(id){ 
                        if(id != null){
                            $("#addCode").val($(this).getRowData(id).productCode);
                            $("#dialog2").dialog('close');
                            $("#addQty").focus();
                        }
                    },
                    pager: '#pager2', 
                    sortname: 'product_code', 
                    viewrecords: true, 
                    sortorder: "asc", 
                    caption:"Select Product" }); 
                jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                
                /* quarantine mode */
                $('#type').change(function(){
                    if($(this).val() == 'Quarantine'){
                        $('#addProduct').hide();
                        $('#infoPutaway').show();
                        $.ajax({
                            url: "POCrossDock.htm",
                            data: "action=quarantine",
                            dataType: "text",
                            success: function(html){
                                html = html.trim();
                                if(html != 'N'){
                                    var split = html.split('|');
                                    var putaway = split[0].split(',');
                                    $('#putawayCode').val(putaway[0]);
                                    $('#putawayDate').val(putaway[1]);
                                    $('#grCode').val(putaway[2]);
                                    for(var i = 1; i < split.length; i++) {
                                        var item = split[i].split(',');
                                        var data = item[0] + "," + item[1] + "," + item[2] + ",Normal";
                                        var product = '<tr class="detail-item" data="' + data + '">' +  
                                            '<td>' + item[0] + '</td>' + 
                                            '<td>' + item[1] + '</td>' + 
                                            '<td>' + item[2] + '</td>' + 
                                            '<td>Normal</td></tr>';

                                        $('#list').append(product);
                                    }
                                }
                            },
                            error: function(jqXHR, textStatus, data){
                                alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                            }
                        });
                    } else {
                        $('#addProduct').show();
                        $('#infoPutaway').hide();
                        $('#putawayCode').val("");
                        $('#putawayDate').val("");
                        $('#grCode').val("");
                        $('#list').html("");
                    }
                });
            });
        </script>
    </body>
</html>