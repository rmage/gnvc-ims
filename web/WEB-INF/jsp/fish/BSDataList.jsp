<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Transfer Slip (Bad Stock) List</title>
        <%@include file="../metaheader.jsp" %>
        <script type="text/javascript" language="javascript">
        	$(document).ready(function() {
        		$('#btnCleanFilter').click(function() {
        			location.href = "FishBs.htm";	
        		});
        		
        		$('#queryBsDate').datepicker({                        
                    dateFormat: "dd/mm/yy"
                });	
        		
        		$('#btnSearch').click(function() {
        			var bsNo = $('#queryBsNo').val();
        			var bsDate = $('#queryBsDate').val();
                    
                    if(bsDate != '') {
                        location.href = "FishBs.htm?search=true&bsNo="+bsNo+"&bsDate="+bsDate;
                    }
                    else {
                        location.href = "FishBs.htm?search=true&bsNo="+bsNo;
                    }
        		});
        		
				$('#btnAdd').click(function() {
					location.href="FishBs.htm?action=create";
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
        	
        	function showDetails(selectedRow) {
        		var title = 'Bad Stock Details: ' + selectedRow.innerHTML;
        		var wsId = selectedRow.getAttribute('id');
				$('#dtl-panel').fadeIn(500, function() {
					$.ajax({
	                    url: "FishBs.htm",
	                    data: "action=ajaxDocument&bsId=" + wsId,
	                    success: function(html){
	                        $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
	                        $('#dtl-panel').append(html);
	                    },
	                    error: function(jqXHR, textStatus){
	                        $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
	                        $('#dtl-panel').append(jqXHR + " - " + textStatus);
	                    }
	                });
				});
        	}
		</script>
    </head>
    <body>
        <%
        	com.app.web.engine.search.ProductSearch criteria = new com.app.web.engine.search.ProductSearch(); 
            if (request.getSession().getAttribute("FishRRDataSearch") != null) {
                criteria = (com.app.web.engine.search.ProductSearch) request.getSession().getAttribute("FishRRDataSearch");
            }
            
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String querySearch = m.get("querySearch") == null ? "" : (String) m.get("querySearch");
            String queryBsNo = m.get("queryBsNo") == null ? "" : (String) m.get("queryBsNo");
            String queryBsDate = m.get("queryBsDate") == null ? "" : (String) m.get("queryBsDate");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
			<div id="dtl-panel" class="div-dtl" style="width: 99%; display: block;" ondblclick="csbShowDetail(0, 0)"></div>
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishBs.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Transfer Slip (Bad Stock)</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        TS No.
                                    </td>
                                    <td>
                                        <input type="text" id="queryBsNo" name="bsNo" value="<%=queryBsNo%>"/>
                                    </td>
                                    <td>
                                        Date
                                    </td>
                                    <td>
                                        <input type="text" id="queryBsDate" name="bsDate" value="<%=queryBsDate%>" />
                                    </td>
                                    <td colspan="2">
                                    </td>
                                </tr>
                                <tr></tr>
                            </tbody>
                            <tfoot>
                            <tr>
	                            <td colspan="4">
	                                <span class="style1">
	                                    <input class ="style1" type="button" value="Search" id="btnSearch" name="btnSearch" />
	                                </span>
	                                 <label>
	                                    <input type="button" name="button" id="btnAdd" value="Add" />
	                                </label>
	                                <label>
	                                    <input type="button" name="button" id="btnCleanFilter" value="Clean Filter" />
	                                </label>
	                            </td>
	                            <td></td>
                            </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Fish Bad Stock Slip - Search Result</caption>
                        <thead>
                            <tr>
                                <td class="left">No</td>
                                <td class="left">Action</td>
                                <td class="left">BS NO</td>
                                <td class="left">Received By</td>
                                <td class="left">Approved By</td>
                                <td class="left">Date Created</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.bsDataList!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.bsDataList}" var="bsData">
                                    <tr class="ganjil">
                                        <td class="left" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                       
                                        <c:url value="FishBs.htm" var="urlEdit">
                                            <c:param name="wsDataId" value="${bsData.id}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="mode" value="edit"/>
                                        </c:url>
                                       
                                        <c:url value="FishBs.htm" var="urlDelete">
                                            <c:param name="wsDataId" value="${bsData.id}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="action" value="inactivate"/>
                                        </c:url>
                                        
                                        <c:url value="GeneralReport.htm" var="urlReportXLS">
                                            <c:param name="bsId" value="${bsData.id}"/>
                                            <c:param name="format" value="xls" />
                                            <c:param name="action" value="getBsReportById"/>
                                        </c:url>
                                        
                                        <c:url value="GeneralReport.htm" var="urlReportPDF">
                                            <c:param name="bsId" value="${bsData.id}"/>
                                            <c:param name="format" value="pdf" />
                                            <c:param name="action" value="getBsReportById"/>
                                        </c:url>
                                        
                                        <td class="left" width="5%">
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
                                        <td class="left"><a id="${bsData.id}" onclick="showDetails(this)"><c:out value="${bsData.bsNo}"/></a></td>
                                        <td class="left"><c:out value="${bsData.receivedBy}"/></td>
                                        <td class="left"><c:out value="${bsData.approvedBy}"/></td>
                                        <td class="left"><c:out value="${bsData.createdDate}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                          <tr>
                                <td colspan="10">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="FishBs.htm?page=<c:out value="${model.page-1}" /><%=querySearch%>">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
						    				<a href="FishBs.htm?page=<c:out value="${model.page+1}" /><%=querySearch%>">
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