<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Replenish</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            java.lang.String error = (String)m.get("msg");
            java.lang.String type = request.getParameter("type").equals("Warehouse") ? "-1" : "-2";
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <table class="collapse tblForm row-select">
                        <caption>Replenish</caption>
                        <tbody class="tbl-nohover">
                            <tr class="detail_genap">
                                <td width="1%"></td>
                                <td width="19%">Replenish No</td>
                                <td class="style1"><label><input type="text" name="replenishNo" value="" size="30" readonly="readonly" /></label></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>Corporate</td>
                                <td class="style1">
                                <label><input type="text" name="corporate" value="${model.user.corpId}" size="30" readonly="readonly" /></label></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>Replenish Date</td>
                                <td class="style1"><label><input type="text" name="replenishDate" value="<fmt:formatDate pattern="dd-MM-yyyy" value="<%= new java.util.Date() %>" />" size="30" readonly="readonly" /></label></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>Warehouse</td>
                                <td class="style1"><label><input type="text" name="warehouse" value="${model.user.whCode}" size="30" readonly="readonly" /></label></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>

                    <div id="tabs">
                        <ul>
                            <li><a href="#processing">Replenish Product</a></li>
                        </ul>

                        <div id="processing">
                            <table>
                                <tbody>
                                    <tr>
                                        <td style="width: 10%">From Location</td>
                                        <td style="width: 20%">
                                            <input type="text" id="fromLocation" size="30" readonly="readonly" />
                                            <img src="resources/images/search.png" width="16" height="16" alt="Search Product" />
                                        </td>
                                        <td style="width: 10%">To Location</td>
                                        <td>
                                            <input type="text" id="toLocation" size="30" readonly="readonly" />
                                            <img src="resources/images/search.png" width="16" height="16" alt="Search Product" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Product</td>
                                        <td>
                                            <input type="text" id="productName" size="30" readonly="readonly" />
                                        </td>
                                        <td>Quantity</td>
                                        <td>
                                            <input type="text" id="quantity" size="10" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4"><input type="button" id="addItem" value="Add" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <br />
                    <table class="collapse tblForm row-select">
                        <caption>Product Replenish - List</caption>
                        <thead>
                            <tr>
                                <td>No.</td>
                                <td>Product</td>
                                <td>From Location</td>
                                <td>To Location</td>
                                <td>Quantity</td>
                                <% if(type == "-2") out.print("<td>Warehouse</td>"); %>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot>
                            <tr>
                                <td colspan="6">
                                    <input type="button" name="btnSaveReplenish" id="btnSaveReplenish" value="Save" />
                                    <input type="button" name="btnCancel" id="btnCancel" value="Cancel" />
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                    
                    <% if (error != null) { %>
                    <div id="dialog" title="Info" style="display:none">
                        <ul>
                        <%
                        String[] listErrorMsg = error.split(com.app.wms.web.util.AppConstant.EOL);
                        for (int i = 0; i < listErrorMsg.length; i++) {
                            String msg = listErrorMsg[i];
                        %>
                            <li><%=msg%></li>
                        <%
                        }
                        %>
                        </ul>
                        <script type="text/javascript">
                        $(document).ready(function() {
                            $("#dialog").dialog();
                        });
                        </script>
                    </div>
                    <% } %>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &COPY; 2013 SPFI
                </div>
            </div>
        </div>
        <div id="dlgFromLoc" style="display:none; z-index:1;">
            <table id="lFromLoc" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pFromLoc"></div> 
        </div>
        <div id="dlgToLoc" style="display:none; z-index:1;">
            <table id="lToLoc" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pToLoc"></div> 
        </div>
                    
        <script type="text/javascript">
            $(function() {
                /* added by FYA : 22 May 2013 */
                $('#btnSaveReplenish').bind('click', function(){
                    var data = 'action=save&type=<%=type%>';
                    $('#main tr').each(function(){
                        var td = $(this).find('td');
                        data += '&from=' + td.eq(2).html() + '&to=' + td.eq(3).html() + '&qty=' + td.eq(4).html();
                        
                        /* if mode : corporate */
                        if('<%=type%>' === '-2')
                            data += '&wh=' + td.eq(5).html();
                    });
                    
                    $.ajax({
                        url: "Replenish.htm",
                        data: data,
                        dataType: "text",
                        success: function(html){
                            if($.trim(html) === ''){
                                alert('Failed to save data!');
                                return;
                            } else {
                                window.location.replace("Replenish.htm?action=view&replenishNo=" + html);
                            }
                            
                            /* redirect to view data */
                        },
                        error: function(jqXHR, textStatus, data){
                            alert("jqXHR: " + jqXHR.statusText + " \ntextStatus: " + textStatus + " \ndata: " + data);
                        }
                    });
                });
                
                $('#main tr').live('dblclick', function(){
                    $(this).remove();
                    fyaReNumbering();
                });
                
                /* jqGrid to location */
                $('#toLocation').click(function(){
                    if($('#fromLocation').val() == ''){
                        $('#fromLocation').trigger('click');
                        return;
                    }
                    
                    $("#dlgToLoc").dialog({ width: "auto", height: "auto", position: "center", modal: true, zindex: 9999, title: 'Select Product To Location' });
                    if('<%=type%>' === '-1')
                        jQuery("#lToLoc").jqGrid().setGridParam({url:'Replenish.htm?action=json&type=toLocation&not=' + $('#fromLocation').val() + '&product=' + $('#productName').attr('code')}).trigger('reloadGrid');
                    else
                        jQuery("#lToLoc").jqGrid().setGridParam({url:'Replenish.htm?action=json&type=toLocation&status=<%=type%>&product=' + $('#productName').attr('code')}).trigger('reloadGrid');
                });
                jQuery("#lToLoc").jqGrid({
                    url:'Replenish.htm?action=json&type=toLocation', 
                    datatype: "json", 
                    hidegrid: false, 
                    shrinkToFit: true, 
                    autowidth: true,
                    colNames:['Location Code','Product Code','Product Name','Balance','Unit','Warehouse', 'Corporate', 'Limit'], 
                    colModel:[{name:'locationCode',index:'location_code'}, {name:'productCode',index:'product_code'}, {name:'productId',index:'product_id'}, 
                        {name:'balance',index:'balance'}, {name:'unitCode',index:'unit_code'}, {name:'whCode',index:'wh_code'}, {name:'corpId',index:'corp_id'},
                        {name:'lotid',index:'lotid',hidden:true}], 
                    rowNum:10, 
                    rowList:[10,20,30], 
                    jsonReader :{
                        repeatitems: false
                    },
                    onSelectRow: function(id){ 
                        if(id != null){
                            var max = parseInt($(this).getRowData(id).lotid) - parseInt($(this).getRowData(id).balance);
                            if(max < parseInt($("#quantity").attr('max')))
                                $("#quantity").attr('max', max);
                            
                            /* if mode : corporate */
                            if('<%=type%>' === '-2')
                                $('#toLocation').attr('wh', $(this).getRowData(id).whCode);
                            
                            $('#toLocation').val($(this).getRowData(id).locationCode);
                            $("#dlgToLoc").dialog('close');
                            $("#quantity").focus();
                        }
                    },
                    pager: '#pToLoc', 
                    sortname: 'location_code', 
                    viewrecords: true, 
                    sortorder: "asc" }); 
                jQuery("#lToLoc").jqGrid('navGrid','#pToLoc',{edit:false,add:false,del:false});
                
                /* added by FYA : 14 May 2013 */
                /* jqGrid from location */
                $('#fromLocation').click(function(){
                    $("#dlgFromLoc").dialog({ width: "auto", height: "auto", position: "center", modal: true, zindex: 9999, title: 'Select Product From Location' });
                    fyaResetForm();
                });
                jQuery("#lFromLoc").jqGrid({
                    url:'Replenish.htm?action=json&type=fromLocation', 
                    datatype: "json", 
                    hidegrid: false, 
                    shrinkToFit: true, 
                    autowidth: true,
                    colNames:['Location Code','Product Code','Product Name','Balance','Unit','Warehouse', 'Corporate', 'Limit'], 
                    colModel:[{name:'locationCode',index:'location_code'}, {name:'productCode',index:'product_code'}, {name:'productId',index:'product_id'}, 
                        {name:'balance',index:'balance'}, {name:'unitCode',index:'unit_code'}, {name:'whCode',index:'wh_code'}, {name:'corpId',index:'corp_id'},
                        {name:'lotid',index:'lotid',hidden:true}], 
                    rowNum:10, 
                    rowList:[10,20,30], 
                    jsonReader :{
                        repeatitems: false
                    },
                    onSelectRow: function(id){ 
                        if(id != null){
                            $('#productName').val($(this).getRowData(id).productId);
                            $('#productName').attr('code', $(this).getRowData(id).productCode);
                            $('#fromLocation').val($(this).getRowData(id).locationCode);
                            $("#quantity").attr('max', $(this).getRowData(id).balance);
                            $("#dlgFromLoc").dialog('close');
                            $("#toLocation").trigger('click');
                        }
                    },
                    pager: '#pFromLoc', 
                    sortname: 'location_code', 
                    viewrecords: true, 
                    sortorder: "asc" }); 
                jQuery("#lFromLoc").jqGrid('navGrid','#pFromLoc',{edit:false,add:false,del:false});
                
                /* add Item if no empty value */
                $('#addItem').click(function(){
                    var qty = parseInt($("#quantity").val()) || 0;
                    
                    if($('#fromLocation').val() != '' && $('#toLocation').val() != '' &&
                        $("#quantity").val() != '' && qty != 0){
                        
                        if(qty > parseInt($("#quantity").attr('max'))){
                            alert('Quantity not valid! whether more than allowed product or less than product from original location');
                            return;
                        }
                        
                        var noDupe = true;
                        $('#main tr').each(function(){
                            var tr = $(this).find('td');
                            if(tr.eq(1).html() == $('#productName').val() && tr.eq(2).html() == $('#fromLocation').val() &&
                                tr.eq(3).html() == $('#toLocation').val() && tr.eq(5).html() == $('#toLocation').attr('wh')){
                                noDupe = false;
                                alert('Product with same location has been added, please delete first!');
                                return false;
                            }
                        });
                        
                        if(!noDupe)
                            return;
                        
                        var no = $('#main tr').length;
                        $('#main').append('<tr max="' + $("#quantity").attr('max') + '"><td>'+ (no + 1) +'</td><td>' + $('#productName').val() + '</td>'
                            + '<td>' + $('#fromLocation').val() + '</td><td>' + $('#toLocation').val() + '</td>'
                            + '<td>' + qty + '</td><% if(type == "-2") out.print("<td>' + $('#toLocation').attr('wh') + '</td>"); %></tr>');
                        fyaResetForm();
                    } else {
                        alert('Please fill all form');
                    }
                });
                
                $('#btnCancel').click(function() {
                    location.href = 'Replenish.htm';
                });

                var cookieName, $tabs, stickyTab;

                cookieName = 'stickyTab';
                $tabs = $( '#tabs' );

                $tabs.tabs( {
                    select: function( e, ui )
                    {
                        $.cookies.set( cookieName, ui.index );
                    }
                } );

                stickyTab = $.cookies.get( cookieName );
                if( ! isNaN( stickyTab )  )
                {
                    $tabs.tabs( 'select', stickyTab );
                }
                
            });
                
            function fyaResetForm(){
                $("#fromLocation").val('');
                $("#toLocation").val('');
                $("#quantity").val('');
                $("#quantity").removeAttr('max');
                $('#productName').val('');
            }
            
            function fyaReNumbering(){
                $('#main tr').each(function(i){
                    $(this).find('td:first').html(i + 1);
                });
            }
        </script>
    </body>
</html>
