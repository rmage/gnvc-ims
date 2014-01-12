<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RR Detail</title>
    </head>
    <body>
        <table width="100%" border="0">
   			<thead>
			    <tr>
					<td class="style1">No.</td>
                    <td class="style1">RR Code</td>
			        <td class="style1">Product Code</td>
			        <td class="style1">Product Name</td>
			        <!--<td class="style1">Qty PO</td>-->
			        <td class="style1">Qty</td>
			        <td class="style1">Status</td>
			        <td class="style1">Exp Date</td>
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
					      			<c:if test="${prod.value.gr_number ne null}">
					      				${prod.value.gr_number}
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
<!--					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.quantity ne null}">
					      				${prod.value.quantityPo}
					      			</c:if>
					      		</td>-->
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.quantity ne null}">
					      				${prod.value.quantity}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.status ne null}">
					      				${prod.value.status}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.expdate ne null}">
					      				${prod.value.expdate}
					      			</c:if>
					      		</td>
					      		<td align="center">&nbsp;
					      			<c:if test="${prod.value.remark ne null}">
					      				${prod.value.remark}
					      			</c:if>
					      		</td>
						    </tr>
		
						</c:forEach>
					  </c:if>
			</tbody>
            
        </table>
    </body>
</html>
