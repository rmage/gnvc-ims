<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WS Details</title>
    </head>
    <body>
        <table width="100%" border="0">
   			<thead>
			    <tr>
					<td class="style1">No.</td>
                    <td class="style1">Batch No.</td>
                    <td class="style1">Catcher No.</td>
			        <td class="style1">Fish Type</td>
			        <td class="style1">Fish Name</td>
			        <td class="style1">Raw Weight</td>
			        <td class="style1">Cooked Weight</td>
			        <td class="style1">Total Processed</td>
			        <td class="style1">Reason</td>
			    </tr>
			</thead>
            <tbody id="main">
            	<c:if test="${requestScope.model.tableMap!=null}">
		                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
						<c:forEach var="map" items="${requestScope.model.tableMap}" varStatus="mapCounter">
						    <tr class="ganjil">
								<td align="right">&nbsp;${mapCounter.count}</td>
								<td align="center">&nbsp;
					      			<c:if test="${map.value.batchNo ne null}">
					      				${map.value.batchNo}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${map.value.catcherNo ne null}">
					      				${map.value.catcherNo}
					      			</c:if>
					      		</td>
								<td align="center">&nbsp;
					      			<c:if test="${map.value.fishType ne null}">
					      				${map.value.fishType}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
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
					      		<td align="center">&nbsp;
					      			<c:if test="${map.value.reason ne null}">
					      				${map.value.reason}
					      			</c:if>
					      		</td>
						</c:forEach>
					  </c:if>
			</tbody>
            
        </table>
    </body>
</html>