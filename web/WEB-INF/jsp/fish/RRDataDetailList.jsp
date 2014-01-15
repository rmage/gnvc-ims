<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RR Details</title>
    </head>
    <body>
        <table width="100%" border="0">
   			<thead>
			    <tr>
					<td class="style1">No.</td>
                    <td class="style1">WS No.</td>
			        <td class="style1">Fish Type</td>
			        <td class="style1">Fish Name</td>
			        <td class="style1">Storage</td>
			        <td class="style1">Total Good Weight</td>
			        <td class="style1">Total Spoilage Weight</td>
			        <td class="style1">Total Weight</td>
			    </tr>
			</thead>
            <tbody id="main">
            	<c:if test="${requestScope.model.tableMap!=null}">
		                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
						<c:forEach var="map" items="${requestScope.model.tableMap}" varStatus="mapCounter">
						    <tr class="ganjil">
								<td align="right">&nbsp;${mapCounter.count}</td>
								<td align="center">&nbsp;
					      			<c:if test="${map.value.wsNo ne null}">
					      				${map.value.wsNo}
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
					      		<td align="center">&nbsp;
					      			<c:if test="${map.value.totalWeight ne null}">
					      				${map.value.storage}
					      			</c:if>
					      		</td>
					      		<td class="right" widht="150px">&nbsp;
					      			<c:if test="${map.value.goodWeight ne null}">
					      				<fmt:formatNumber type="number" 
					      					maxFractionDigits="2" minFractionDigits="2"
					      					value="${map.value.goodWeight}"></fmt:formatNumber>
					      			</c:if>
					      		</td>
					      		<td class="right" widht="150px">&nbsp;
					      			<c:if test="${map.value.spoilageWeight ne null}">
					      				<fmt:formatNumber type="number" 
					      					maxFractionDigits="2" minFractionDigits="2"
					      					value="${map.value.spoilageWeight}"></fmt:formatNumber>
					      			</c:if>
					      		</td>
					      		<td class="right" widht="150px">&nbsp;
					      			<c:if test="${map.value.totalWeight ne null}">
					      				<fmt:formatNumber type="number" 
					      					maxFractionDigits="2" minFractionDigits="2"
					      					value="${map.value.totalWeight}"></fmt:formatNumber>
					      			</c:if>
					      		</td>
						</c:forEach>
					  </c:if>
			</tbody>
            
        </table>
    </body>
</html>