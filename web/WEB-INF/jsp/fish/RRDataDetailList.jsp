<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <td class="style1">RR No.</td>
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
					      			<c:if test="${map.value.rrNo ne null}">
					      				${map.value.rrNo}
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
					      		<td align="center">&nbsp;
					      			<c:if test="${map.value.goodWeight ne null}">
					      				${map.value.goodWeight}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${map.value.spoilageWeight ne null}">
					      				${map.value.spoilageWeight}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${map.value.totalWeight ne null}">
					      				${map.value.totalWeight}
					      			</c:if>
					      		</td>
						</c:forEach>
					  </c:if>
			</tbody>
            
        </table>
    </body>
</html>