<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Purchase Order List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Purchase.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Purchase Order</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        PO No
                                    </td>
                                    <td>
                                        <input type="text" name="purchaseNo" value=""/>
                                    </td>
                                    <td width="20%">
                                        PO Date
                                    </td>
                                    <td>
                                        <input type="text" name="podate" value="" id="podate"/>
                                    </td>
                                    
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="6">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                    	<label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    	</label>
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Purchase Order - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">PO No</td>
                                <td class="style1">PO Date</td>  
                                <td class="style1">Supplier</td>
                                <td class="style1">Total Amount</td>
                                <td class="style1">Status Approved</td>  
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.pos!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.pos}" var="po">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                            <c:url  value="Purchase.htm"  var="urlPrint">
                                                <c:param name="sonumber" value="${po.ponumber}"/>
                                                <c:param name="templateName" value="rptPurchaseOrderList"/>
                                                <c:param name="parametersKey" value="P_DOC_NO"/>
                                                <c:param name="action" value="doPrint" />
                                            </c:url>
                                            <a href='<c:out value="${urlPrint}"/>'>
                                                <img src="resources/images/print.jpg" width="16" height="16" alt="View" />
                                            </a>
                                        </td>
                                        <td class="style1">
                                            <a href="#" class="no-decoration" onclick="csbShowDetail('PO', '<c:out value="${po.ponumber}" />', 'Purchase Order Detail')">
                                                <c:out value="${po.ponumber}"/>
                                            </a>
                                        </td>
                                        <td class="style1"><fmt:formatDate pattern="dd-MM-yyyy" value="${po.podate}" /></td>
                                        <td class="style1"><c:out value="${po.supplierName}"/></td>
                                        <td class="style1"><fmt:formatNumber type="currency" currencySymbol="" value="${po.createdby}" /></td>
                                        <td class="style1">
                                            <c:if test="${po.corpid == 'Y'}">
                                                <input class="mark" type="button" code="${po.ponumber}" name="button" value="Approve" />
                                                &nbsp;
                                                <input class="markb" type="button" code="${po.ponumber}" name="button" value="Cancel" />
                                            </c:if>
                                            <c:choose>
                                                <c:when test="${po.status == 'N'}"><img src="resources/images/ilocked.gif" width="16" height="="" alt="Wait" title="Waiting Approval" /></c:when>
                                                <c:when test="${po.status == 'X'}"><img src="resources/images/forbidden.png" width="16" height="16" alt="Cancel" title="PO Cancel" /></c:when>
                                                <c:otherwise><img src="resources/images/checkmark.gif" width="16" height="16" alt="Approved" title="PO Approved" /></c:otherwise>
                                            </c:choose>
                                        </td>     
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="15">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Purchase.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="Purchase.htm?page=<c:out value="${model.page+1}" />">
												&gt;
										    </a>
										</c:if>
				    				</span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="10">
                                <span class="style1">
                               
                                </span>
                            </td>
                        </tfoot>
                    </table>
                     <div id="ab_dialog" style="display: none">
                    	<span id="ab_dialog_doc"></span>: Are you sure?
                	 </div>
                	 
                        <script>
                            $('.mark').click(function(){
                                var code = $(this).attr('code');
                                $('#ab_dialog_doc').html($(this).attr('code'));
                                $('#ab_dialog').dialog({
                                    buttons: {
                                        "Close": function() {
                                            $(this).dialog("close");
                                        },
                                        "Ok": function() {
                                            window.location.href = 'Purchase.htm?action=approvedPO&purchaseNo=' + code;
                                        }
                                    },
                                    modal: true,
                                    width: 500,
                                    height: 150,
                                    'title': 'PO Approved'
                                });
                            });
                        </script>
                        
                        <script>
                            $('.markb').click(function(){
                                var code = $(this).attr('code');
                                $('#ab_dialog_doc').html($(this).attr('code'));
                                $('#ab_dialog').dialog({
                                    buttons: {
                                        "Close": function() {
                                            $(this).dialog("close");
                                        },
                                        "Ok": function() {
                                            window.location.href = 'Purchase.htm?action=cancelPO&purchaseNo=' + code;
                                        }
                                    },
                                    modal: true,
                                    width: 500,
                                    height: 150,
                                    'title': 'PO Cancel'
                                });
                            });
                        </script>

                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
                                                                                
        <script type="text/javascript">
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'Purchase.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });
                
                $('#btnPrintAll').click(function() {
                    window.location  = '<c:out value="${urlPrintAll}"/>';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
              
                
                $('#podate').datepicker({                        
                    dateFormat: "dd/mm/yy"                        
                });
              
            });
        </script>
      
    </body>
</html>