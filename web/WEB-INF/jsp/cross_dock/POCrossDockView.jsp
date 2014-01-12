<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Purchase Order Cross Dock View</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="formPO" method="post" action="POCrossDock.htm" autocomplete="off">
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Purchase Order Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Purchase Order No</td>
                                    <td class="style1">
                                        <label>${model.list.PONumber}</label>
                                        <c:if test="${model.list.approvedBy == 'Quarantine'}"> (Quarantine)</c:if>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Estimation Delivery Date</td>
                                    <td class="style1"><label><fmt:formatDate pattern="dd/MM/yyyy" value="${model.list.PODate}" /></label></td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="2">
                                    <span class="style1">
                                        <label>
                                            <input type="button" value="Print" id="btnPrint" name="btnPrint" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                        </label>
                                        <input type="button" value="Back" id="btnBack" name="button" class="style1 ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                    </span>
                                </td>
                            </tr></tfoot>
                        </table>
                        <table id="infoPutaway" class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Putaway Info</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Putaway No</td>
                                    <td><label id="putawayCode"></label></td>
                                </tr>
                                <tr>
                                    <td>Putaway Date</td>
                                    <td><label id="putawayDate"></label></td>
                                </tr>
                                <tr>
                                    <td>Good Receive No</td>
                                    <td><label id="grCode"></label></td>
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
                                <c:forEach items="${model.listDetail}" var="list" varStatus="status">
                                    <tr>
                                        <td>${list.productCode}</td>  
                                        <td>${model.listProduct[status.index].productName}</td>  
                                        <td>${list.POQty}</td>  
                                        <td>${list.POType}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>
        <script type="text/javascript">
            $(function(){
                /* quarantine mode */
                if('${model.list.approvedBy}' == 'Quarantine'){
                    $('#addProduct').hide();
                    $('#infoPutaway').show();
                    $.ajax({
                        url: "POCrossDock.htm",
                        data: "action=quarantine&poCode=${model.list.POCode}",
                        dataType: "text",
                        success: function(html){
                            html = html.trim();
                            if(html != 'N'){
                                var split = html.split('|');
                                var putaway = split[0].split(',');
                                $('#putawayCode').html(putaway[0]);
                                $('#putawayDate').html(putaway[1]);
                                $('#grCode').html(putaway[2]);
                                //FIXME: read list from pocd_dtl or putaway_detail ? 13 February 2013
                                /*for(var i = 1; i < split.length; i++) {
                                    var item = split[i].split(',');
                                    var data = item[0] + "," + item[1] + "," + item[2] + ",Normal";
                                    var product = '<tr class="detail-item" data="' + data + '">' +  
                                        '<td>' + item[0] + '</td>' + 
                                        '<td>' + item[1] + '</td>' + 
                                        '<td>' + item[2] + '</td>' + 
                                        '<td>Normal</td></tr>';

                                    $('#list').html(product);
                                }*/
                            }
                        },
                        error: function(jqXHR, textStatus, data){
                            alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                        }
                    });
                }
            });
        </script>
    </body>
</html>