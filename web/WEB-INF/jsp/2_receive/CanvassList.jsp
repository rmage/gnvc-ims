<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Canvassing List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Canvassing.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Canvassing - Search</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Prs Number
                                    </td>
                                    <td>
                                        <input type="text" name="prsnumber" value="" />
                                    </td>
                                    <td >
                                        Supplier
                                    </td>
                                    <td>
                                        <input type="text" name="supplierCode" value=""/>
                                    </td>
                                    <td colspan="2">
                                    </td>
                                </tr>
                                <tr>
                                   
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                    </span>
                                    <label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    </label>
                                </td>
                                <td></td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Canvassing - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">PRS Number</td>
                                <td class="style1">Item Code</td>
                                <td class="style1">Description</td>
                                <td class="style1">Unit Price</td>
                                <td class="style1">Supplier</td>
                                <td width="10%" class="style1">Selected Canvassing</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.canvassing!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.canvassing}" var="can">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                        		<c:url value="Canvassing.htm" var="urlEdit">
		                                            <c:param name="id" value="${can.id}"/>
		                                            <c:param name="prsnumber" value="${can.prsnumber}"/>
		                                            <c:param name="productcode" value="${can.productcode}"/>
		                                            <c:param name="page" value="${model.page}" />
		                                            <c:param name="mode" value="edit"/>
                                        		</c:url>
                                        		<a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" /></a>
                                                
                                                <c:if test="${can.priceunit > 0}">
                                                <c:url  value="Canvassing.htm"  var="urlPrintResult">
                                                    <c:param name="prsnumber" value="${can.prsnumber}"/>
                                                    <c:param name="templateName" value="rptCanvassingResult"/>
                                                    <c:param name="parametersKey" value="P_DOC_NO"/>
                                                    <c:param name="action" value="doPrintResult" />
                                                </c:url>
                                                <a href='<c:out value="${urlPrintResult}"/>'>
                                                        <img src="resources/images/print.jpg" width="16" height="16" alt="View" />
                                                </a>
                                                </c:if>
                                                
                                                <c:if test="${can.priceunit == null || can.priceunit < 1}">
                                                <c:url  value="Canvassing.htm"  var="urlPrintForm">
                                                    <c:param name="suppliercode" value="${can.supplierCode}"/>
                                                    <c:param name="prsnumber" value="${can.prsnumber}"/>
                                                    <c:param name="templateName" value="rptCanvassingForm"/>
                                                    <c:param name="parametersKey" value="P_DOC_NO"/>
                                                    <c:param name="action" value="doPrintForm" />
                                                </c:url>
                                                <a href='<c:out value="${urlPrintForm}"/>'>
                                                        <img src="resources/images/detail.png" width="16" height="16" alt="View" />
                                                </a>
                                                </c:if>
                                                
                                                <c:url  value="GenerateReport.htm"  var="urlPrintForm1">
                                                    <c:param name="action" value="index"/>
                                                    <c:param name="item" value="PCanvassingForm"/>
                                                    <c:param name="type" value="xls"/>
                                                    <c:param name="params" value="${can.supplierCode}:${can.prsnumber}"/>
                                                </c:url>
                                                <a href='<c:out value="${urlPrintForm1}"/>'>
                                                        <img src="resources/images/copy.png" width="16" height="16" alt="View" />
                                                </a>
                                        </td>
                                        <td class="style1">
                                            <a href="#" class="no-decoration" onclick="csbShowDetail('CAN', '<c:out value="${can.prsnumber}" />', 'Canvassing Detail')">
                                                <c:out value="${can.prsnumber}"/>
                                            </a>
                                        </td>
                                        <td class="style1"><c:out value="${can.productcode}"/></td>
                                        <td class="style1"><c:out value="${can.productname}"/></td>
                                        <td class="style1"><fmt:formatNumber type="currency" currencySymbol="" value="${can.priceunit}" /></td>
                                        <td class="style1"><c:out value="${can.supplierCode}"/></td>
                                        <td class="center" width="5%">
	                                        <c:if test="${can.isSelected == 'Y' && can.priceunit > 0}">
	                                        	
	                                                <img src="resources/images/checkmark.gif" width="16" height="16" alt="View" />
	                                           
	                                        </c:if>
	                                        <c:if test="${can.isSelected == 'N'}">
	                                        	
	                                                <img src="resources/images/Forbidden.png" width="16" height="16" alt="View" />
	                                           
	                                        </c:if>
	                                        <c:if test="${can.isSelected == 'Y' && can.priceunit < 1}">
	                                        	
	                                                <img src="resources/images/Forbidden.png" width="16" height="16" alt="View" />
	                                           
	                                        </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                          <tr>
                                <td colspan="15">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Canvassing.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
						    				<a href="Canvassing.htm?page=<c:out value="${model.page+1}" />">
											&gt;
						    				</a>
										</c:if>
				    				</span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
							<tr>
	                            <td colspan="15">
		                          
	                            </td>
                            </tr>
                        </tfoot>

                    </table>

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
                    location.href = 'Canvassing.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
            });
        </script>
        
    </body>

</html>