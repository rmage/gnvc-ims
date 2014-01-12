<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Putaway Detail</title>
    </head>
    <body>
        <table width="100%" border="0">
   			<thead>
			    <tr>
					<td class="style1">No.</td>
                    <td class="style1">Putaway Number</td>
			        <td class="style1">Product Code</td>
			        <!--<td class="style1">Product Name</td>-->
			        <td class="style1">Qty</td>
			        <td class="style1">WH Code</td>
			        <td class="style1">Location Code</td>
			    </tr>
			</thead>
            <tbody id="main">
					  <c:if test="${requestScope.model.tableMap!=null}">
		                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
						<c:forEach var="prod" items="${requestScope.model.tableMap}" varStatus="prodCounter">
						   
						    <tr class="ganjil">
								<td align="right">&nbsp;${prodCounter.count}</td>
								<td align="center">&nbsp;
					      			<c:if test="${prod.value.putawayId ne null}">
					      				${prod.value.putawayId}
					      			</c:if>
					      		</td>
								<td align="center">&nbsp;
					      			<c:if test="${prod.value.productCode ne null}">
					      				${prod.value.productCode}
					      			</c:if>
					      		</td>
<!--					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.productId ne null}">
					      				${prod.value.productId}
					      			</c:if>
					      		</td>-->
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.quantity ne null}">
					      				${prod.value.quantity}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.whCode ne null}">
					      				${prod.value.whCode}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.locationCode ne null}">
					      				${prod.value.locationCode}
					      			</c:if>
					      		</td>
						    </tr>
		
						</c:forEach>
					  </c:if>
			</tbody>
            
        </table>
    </body>
</html>
