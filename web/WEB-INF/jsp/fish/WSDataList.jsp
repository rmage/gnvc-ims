<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="freemarker.template.SimpleDate"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Fish WS Data List</title>
        <%@include file="../metaheader.jsp" %>
        <script type="text/javascript">
        	$(document).ready(function() {
        		$('#queryWsDate').datepicker({                        
                    dateFormat: "dd/mm/yy"
                });	
        		
        		$('#btnSearch').click(function() {
        			var wsNo = $('#queryWsNo').val();
        			var wsDate = $('#queryWsDate').val();
                    
                    if(wsDate != '') {
                        location.href = "FishWs.htm?search=true&wsNo="+wsNo+"&wsDate="+wsDate;   
                    }
                    else {
                        location.href = "FishWs.htm?search=true&wsNo="+wsNo;
                    }
        		});	
        		
        		$('#btnCleanFilter').click(function() {
        			location.href = "FishWs.htm";
        		});
        	});
        	
        	function showDetails(selectedRow) {
        		var title = 'WS Details';
        		var wsId = selectedRow.getAttribute('id');
				$('#dtl-panel').fadeIn(500, function() {
					$.ajax({
	                    url: "FishWs.htm",
	                    data: "action=ajaxDocument&wsId=" + wsId,
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
            if (request.getSession().getAttribute("FishWsDataSearch") != null) {
                criteria = (com.app.web.engine.search.ProductSearch) request.getSession().getAttribute("FishWsDataSearch");
            }
            
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String querySearch = m.get("querySearch") == null ? "" : (String) m.get("querySearch");
            String queryWsNo = m.get("queryWsNo") == null ? "" : (String) m.get("queryWsNo");
            String queryWsDate = m.get("queryWsDate") == null ? "" : (String) m.get("queryWsDate");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
			<div id="dtl-panel" class="div-dtl" style="width: 100%; display: block;" ondblclick="csbShowDetail(0, 0)"></div>
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishWs.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Fish WS Data</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        WS No
                                    </td>
                                    <td>
                                        <input type="text" id="queryWsNo" name="wsNo" value="<%=queryWsNo%>"/>
                                    </td>
                                    <td>
                                        Date
                                    </td>
                                    <td>
                                        <input type="text" id="queryWsDate" name="wsDate" value="<%=queryWsDate%>" />
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
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Fish WS Data - Search Result</caption>
                        <thead>
                            <tr>
                                <td class="center">No</td>
                                <td class="center">Action</td>
                                <td class="center">WS No</td>
                                <td class="center">WS Type</td>
                                <td class="center">Batch No</td>
                                <td class="center">Supplier Name</td>
                                <td class="center">Date Created</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.fishWsData!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.fishWsData}" var="wsData">
                                    <tr class="ganjil">
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                       
                                        <c:url value="FishWs.htm" var="urlEdit">
                                            <c:param name="wsDataId" value="${wsData.id}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="mode" value="edit"/>
                                        </c:url>
                                       
                                        <c:url value="FishWs.htm" var="urlDelete">
                                            <c:param name="wsDataId" value="${wsData.id}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="action" value="inactivate"/>
                                        </c:url>
                                        
                                        <c:url value="GeneralReport.htm" var="urlReportXLS">
                                            <c:param name="wsId" value="${wsData.id}"/>
                                            <c:param name="wsType" value="${wsData.wsType.code}" />
                                            <c:param name="format" value="xls" />
                                            <c:param name="action" value="getWsReportById"/>
                                        </c:url>
                                        
                                        <c:url value="GeneralReport.htm" var="urlReportPDF">
                                            <c:param name="wsId" value="${wsData.id}"/>
                                            <c:param name="wsType" value="${wsData.wsType.code}" />
                                            <c:param name="format" value="pdf" />
                                            <c:param name="action" value="getWsReportById"/>
                                        </c:url>
                                        
                                        <c:url value="GenerateReport.htm" var="urlReportCSV">
                                            <c:param name="action" value="index"/>
                                            <c:param name="item" value="FWSNC" />
                                            <c:param name="type" value="csv" />
                                            <c:param name="params" value="${wsData.id}"/>
                                        </c:url>
                                        
                                        <td class="center" width="10%">
                                        <%-- 
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" /></a>   
                                            <a href='<c:out value="${urlDelete}"/>'>
                                                <img src="resources/images/delete.gif" width="16" height="16" /></a>
                                                --%> 
                                            <a href='<c:out value="${urlReportPDF}"/>'>
                                            	<img src="resources/images/print.jpg" width="16" height="16" alt="pdf" /></a>&nbsp;&nbsp;
                                            <a href='<c:out value="${urlReportXLS}"/>'>
                                            	<img src="resources/images/printxls.jpg" width="16" height="16" alt="xls" /></a>&nbsp;&nbsp;
                                            <a href='<c:out value="${urlReportCSV}"/>'>
                                            	<img src="resources/images/csv.png" width="16" height="16" alt="csv" /></a>
                                        </td>
                                        <td class="center"><a id="${wsData.id}" onclick="showDetails(this)"><c:out value="${wsData.wsNo}"/></a></td>
                                        <td class="center"><c:out value="${wsData.wsType.code}"/></td>
                                        <td class="center"><c:out value="${wsData.vessel.batchNo}"/></td>
                                        <td class="center"><c:out value="${wsData.vessel.supplier.name}"/></td>
                                        <td class="center"><c:out value="${wsData.createdDate}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                          <tr>
                                <td colspan="7">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="FishWs.htm?page=<c:out value="${model.page-1}" /><%=querySearch%>">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
						    				<a href="FishWs.htm?page=<c:out value="${model.page+1}"/><%=querySearch%>">
											&gt;
						    				</a>
										</c:if>
				    				</span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="7">
                                <span class="style1"></span>
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
        <script type="text/javascript">
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'FishWs.htm?action=create';
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