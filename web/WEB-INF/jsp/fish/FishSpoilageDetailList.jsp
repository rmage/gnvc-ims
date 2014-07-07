<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../metaheader.jsp" %>
        <title>WS Details</title>
    </head>
    <body>
        <table width="100%" border="0">
   			<thead>
			    <tr class="panel-head">
					<td class="left">No.</td>
                    <td class="left">Batch No.</td>
                    <td class="left">Catcher No.</td>
			        <td class="left">Fish Type</td>
			        <td class="left">Fish Name</td>
			        <td class="left">Cooked Weight</td>
			        <td class="left">Raw Weight</td>
			        <td class="left">Total Processed</td>
			        <td class="left">Reason</td>
                    <td class="left">Action</td>
			    </tr>
			</thead>
            <tbody id="main">
            	<c:if test="${requestScope.model.tableMap!=null}">
		                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
						<c:forEach var="map" items="${requestScope.model.tableMap}" varStatus="mapCounter">
						    <tr class="${mapCounter.count%2 == 1 ? 'panel-row-odd' : ''}">
								<td align="left">&nbsp;${mapCounter.count}</td>
								<td align="left">&nbsp;
					      			<c:if test="${map.value.batchNo ne null}">
					      				${map.value.batchNo}
					      			</c:if>
					      		</td>
					      		<td align="left">&nbsp;
					      			<c:if test="${map.value.catcherNo ne null}">
					      				${map.value.catcherNo}
					      			</c:if>
					      		</td>
								<td align="left">&nbsp;
					      			<c:if test="${map.value.fishType ne null}">
					      				${map.value.fishType}
					      			</c:if>
					      		</td>
					      		<td align="left">&nbsp;
					      			<c:if test="${map.value.fishName ne null}">
					      				${map.value.fishName}
					      			</c:if>
					      		</td>
					      		<td class="right">&nbsp;
					      			<c:if test="${map.value.rawWeight ne null}">
					      				<fmt:formatNumber type="number" 
					      					maxFractionDigits="2" minFractionDigits="2"
					      					value="${map.value.rawWeight}"></fmt:formatNumber>
					      			</c:if>
					      		</td>
					      		<td class="right">&nbsp;
					      			<c:if test="${map.value.cookedWeight ne null}">
					      				<fmt:formatNumber type="number" 
					      					maxFractionDigits="2" minFractionDigits="2"
					      					value="${map.value.cookedWeight}"></fmt:formatNumber>
					      			</c:if>
					      		</td>
					      		<td class="right">&nbsp;
					      			<c:if test="${map.value.totalProcessed ne null}">
					      				<fmt:formatNumber type="number" 
					      					maxFractionDigits="2" minFractionDigits="2"
					      					value="${map.value.totalProcessed}"></fmt:formatNumber>
					      			</c:if>
					      		</td>
					      		<td align="left">&nbsp;
					      			<c:if test="${map.value.reason ne null}">
					      				${map.value.reason}
					      			</c:if>
					      		</td>
                                <td align="left">&nbsp;
                                    <c:url value="FishSpoilageData.htm" var="urlDelete">
                                        <c:param name="id" value="${map.value.id}"/>
                                        <c:param name="action" value="inactivate"/>
                                    </c:url>
					      			<a class="urlDelete" href='<c:out value="${urlDelete}"/>'>
                                        <img src="resources/images/delete.gif" width="16" height="16" /></a>
					      		</td>
						</c:forEach>
					  </c:if>
			</tbody>
            
        </table>
    </body>
</html>