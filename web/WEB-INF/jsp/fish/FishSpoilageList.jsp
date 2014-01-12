<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Fish Spoilage Data List</title>
        <%@include file="../metaheader.jsp" %>
        <script type="text/javascript" language="javascript">
        	$(document).ready(function() {
				$('#btnAdd').click(function() {
					location.href="FishSpoilageData.htm?action=create";
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
    </head>
    <body>
        <%
        	com.app.web.engine.search.ProductSearch criteria = new com.app.web.engine.search.ProductSearch(); 
            if (request.getSession().getAttribute("FishRRDataSearch") != null) {
                criteria = (com.app.web.engine.search.ProductSearch) request.getSession().getAttribute("FishRRDataSearch");
            }
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishSpoilageData.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Fish Spoilage Data</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Batch No
                                    </td>
                                    <td>
                                        <input type="text" name="batchNo" value=""/>
                                    </td>
                                </tr>
                                <tr></tr>
                            </tbody>
                            <tfoot>
                            <tr>
	                            <td colspan="4">
	                                <span class="style1">
	                                    <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
	                                </span>
	                                 <label>
	                                    <input type="button" name="button" id="btnAdd" value="Add" />
	                                </label>
	                            </td>
	                            <td></td>
                            </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Fish Spoilage Data - Search Result</caption>
                        <thead>
                            <tr>
                                <td class="center">No</td>
                                <td class="center">Action</td>
                                <td class="center">Batch No</td>
                                <td class="center">Date Shift</td>
                                <td class="center">Time Shift</td>
                                <td class="center">Total Cooked Weight</td>
                                <td class="center">Total Raw Weight</td>
                                <td class="center">Total Processed Weight</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.spoilageDataList!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.spoilageDataList}" var="spoilageData">
                                    <tr class="ganjil">
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                       
                                        <c:url value="FishSpoilageData.htm" var="urlEdit">
                                            <c:param name="wsDataId" value="${spoilageData.id}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="mode" value="edit"/>
                                        </c:url>
                                       
                                        <c:url value="FishSpoilageData.htm" var="urlDelete">
                                            <c:param name="wsDataId" value="${spoilageData.id}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="action" value="inactivate"/>
                                        </c:url>
                                        
                                        <c:url value="GeneralReport.htm" var="urlReportXLS">
                                            <c:param name="vesselId" value="${spoilageData.vesselId}"/>
                                            <c:param name="dateShift" value="${spoilageData.dateShift}"/>
                                            <c:param name="timeShift" value="${spoilageData.timeShift}"/>
                                            <c:param name="format" value="xls" />
                                            <c:param name="action" value="getFishSpoilageReport"/>
                                        </c:url>
                                        
                                        <c:url value="GeneralReport.htm" var="urlReportPDF">
                                            <c:param name="vesselId" value="${spoilageData.vesselId}"/>
                                            <c:param name="dateShift" value="${spoilageData.dateShift}"/>
                                            <c:param name="timeShift" value="${spoilageData.timeShift}"/>
                                            <c:param name="format" value="pdf" />
                                            <c:param name="action" value="getFishSpoilageReport"/>
                                        </c:url>
                                        <td class="center" width="5%">
                                        <%-- 
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" /></a>
                                           
                                            <a href='<c:out value="${urlDelete}"/>'>
                                                <img src="resources/images/delete.gif" width="16" height="16" /></a>
                                         --%>
                                            <a href='<c:out value="${urlReportPDF}"/>'>
                                            	<img src="resources/images/print.jpg" width="16" height="16" alt="pdf" /></a>&nbsp;&nbsp;
                                            <a href='<c:out value="${urlReportXLS}"/>'>
                                            	<img src="resources/images/printxls.jpg" width="16" height="16" alt="xls" /></a>
                                        </td>
                                        <td class="center"><c:out value="${spoilageData.vessel.batchNo}"/></td>
                                        <td class="center"><c:out value="${spoilageData.dateShift}"/></td>
                                        <td class="center"><c:out value="${spoilageData.timeShift}"/></td>
                                        <td class="center"><c:out value="${spoilageData.cookedWeight}"/></td>
                                        <td class="center"><c:out value="${spoilageData.rawWeight}"/></td>
                                        <td class="center"><c:out value="${spoilageData.totalProcessed}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                          <tr>
                                <td colspan="10">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="FishSpoilageData.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
						    				<a href="FishSpoilageData.htm?page=<c:out value="${model.page+1}" />">
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

                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>  
    </body>

</html>