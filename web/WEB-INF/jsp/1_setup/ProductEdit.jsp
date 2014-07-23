<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS &therefore; Product &therefore; Update</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Product.htm" method="post">
                        <input type="hidden" name="action" value="saveUpdate" />
                        <input type="hidden" name="productId" value="${model.p.productId}"/>
                        <table class="collapse tblForm row-select">
                            <caption>Product &therefore; Information</caption>
                            <tbody>
                                <tr>
                                    <td>Item Code</td>
                                    <td><input type="text" id="itemCode" name="itemCode" readonly="readonly" required="required" pattern="[^&quot;]{1,}" value="${model.p.productCode}" /> *</td>
                                    <td>Item Name</td>
                                    <td><input type="text" id="itemName" name="itemname" size="50" required="required" pattern="[^&quot;]{1,}" value="${model.p.productName}" /> *</td>
                                </tr>
                                <tr>
                                    <td>Description</td>
                                    <td><input type="text" id="itemDescription" name="itemDescription" size="50" pattern="[^&quot;]{1,}" value="${model.p.productDescription}" /></td>
                                    <td>Category</td>
                                    <td>
                                        <select id="category" name="category" required="required">
                                            <c:forEach var="droplist" items="${requestScope.model.dropListCategory}">
                                                <option value="<c:out value='${droplist.categoryCode}'/>"  <c:if test="${droplist.categoryCode == model.p.productCategory}">selected="true"</c:if>>
                                                    <c:out value="${droplist.categoryName}" />
                                                </option> 
                                            </c:forEach>
                                        </select> *
                                    </td>
                                </tr>
                                <tr>
                                    <td>Unit of Measurement</td>
                                    <td>
                                        <select name="uom" required="required">
                                            <c:forEach var="droplist" items="${requestScope.model.dropListUOM}">
                                                <option value="${droplist.uomName}" <c:if test="${droplist.uomName == model.p.uom}">selected="true"</c:if>>
                                                    ${droplist.uomName}
                                                </option> 
                                            </c:forEach>
                                        </select> *
                                    </td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <span>
                                            <label>
                                                <input type="submit" name="btnSave" id="btnSave" value="Update" />
                                            </label>
                                            <input type="button" name="button" id="btnBack" value="Back" onclick="windows.location.replace('Product.htm')" />
                                        </span>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
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
