<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PRS Detail</title>
    </head>
    <body>
        <table width="100%" border="0">
   			<thead>
			    <tr>
					<td class="style1">No.</td>
                    <td class="style1">PRS No.</td>
			        <td class="style1">Item Code</td>
			        <td class="style1">Item Name</td>
			        <td class="style1">Qty</td>
			        <td class="style1">Unit</td>
			        <td class="style1">Stock on Hand</td>
			        <td class="style1">Charged to Department</td>
			        <td class="style1">Date Needed</td>
			        <td class="style1">Remark</td>
			    </tr>
			</thead>
            <tbody id="main">
            	<c:if test="${requestScope.model.tableMap!=null}">
		                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
						<c:forEach var="prod" items="${requestScope.model.tableMap}" varStatus="prodCounter">
						    <tr class="ganjil">
								<td align="right">&nbsp;${prodCounter.count}</td>
								<td align="center">&nbsp;
					      			<c:if test="${prod.value.prsnumber ne null}">
					      				${prod.value.prsnumber}
					      			</c:if>
					      		</td>
								<td align="center">&nbsp;
					      			<c:if test="${prod.value.productCode ne null}">
					      				${prod.value.productCode}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.productName ne null}">
					      				${prod.value.productName}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.qty ne null}">
					      				${prod.value.qty}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.uom ne null}">
					      				${prod.value.uom}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.balance ne null}">
					      				${prod.value.balance}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.department ne null}">
					      				${prod.value.department}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.requestdate ne null}">
					      				${prod.value.requestdate}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.remarks ne null}">
					      				${prod.value.remarks}
					      			</c:if>
					      		</td>
						</c:forEach>
					  </c:if>
			</tbody>
            
        </table>
    </body>
</html>