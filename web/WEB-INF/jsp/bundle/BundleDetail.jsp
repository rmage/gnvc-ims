<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bundle Detail</title>
    </head>
    <body>
        <table width="100%" border="0">
   			<thead>
			    <tr>
					<td class="style1">No.</td>
                    <td class="style1">Bundle Code</td>
			        <td class="style1">Product Code</td>
			        <td class="style1">Product Name</td>
			        <td class="style1">Quantity Bundle</td>
			    </tr>
			</thead>
            <tbody id="main">
					  <c:if test="${requestScope.model.tableMap!=null}">
		                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
						<c:forEach var="prod" items="${requestScope.model.tableMap}" varStatus="prodCounter">
						   
						    <tr class="ganjil">
								<td align="right">&nbsp;${prodCounter.count}</td>
								<td align="center">&nbsp;
					      			<c:if test="${prod.value.bundleCode ne null}">
					      				${prod.value.bundleCode}
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
					      			<c:if test="${prod.value.quantity ne null}">
					      				${prod.value.quantity}
					      			</c:if>
					      		</td>
						    </tr>
		
						</c:forEach>
					  </c:if>
			</tbody>
            
        </table>
    </body>
</html>
