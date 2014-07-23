<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS &therefore; Product &therefore; Create</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            com.app.wms.engine.db.dto.Product dto = (com.app.wms.engine.db.dto.Product) m.get("dto");
            String mode = (String) m.get("mode");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Product.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="action" value="save" />
                        <table class="collapse tblForm row-select">
                            <caption>Product &therefore; Information</caption>
                            <tbody>
                                <tr>
                                    <td>Item Code</td>
                                    <td><input type="text" id="itemCode" name="itemCode" required="required" pattern="[^&quot;]{1,}" /> *</td>
                                    <td>Item Name</td>
                                    <td><input type="text" id="itemName" name="itemname" size="50" required="required" pattern="[^&quot;]{1,}" /> *</td>
                                </tr>
                                <tr>
                                    <td>Description</td>
                                    <td><input type="text" id="itemDescription" name="itemDescription" size="50" pattern="[^&quot;]{1,}" /></td>
                                    <td>Category</td>
                                    <td>
                                        <select id="category" name="category" required="required">
                                            <c:forEach var="droplist" items="${requestScope.model.dropListCategory}">
                                                <option value="<c:out value='${droplist.categoryCode}'/>"  <c:if test="${droplist.categoryCode == model.dto.productCategory}">selected="true"</c:if>>
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
                                                <option value="${droplist.uomName}" <c:if test="${droplist.uomName == model.dto.uom}">selected="true"</c:if>>
                                                    ${droplist.uomName}
                                                </option> 
                                            </c:forEach>
                                        </select> *
                                    </td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover"></tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                        <label>
                                            <input type="submit" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnSave" id="btnSave" value="Save" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" />
                                        </label>
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
        <script type="text/javascript">

            $('#itemCode').bind('blur', function() {
                var $o = $(this);
                if (!$o.val())
                    return;
                $.ajax({
                    url: 'Product.htm?term=' + $o.val(),
                    method: 'post',
                    data: { action: 'getUnique', term: $o.val() },
                    dataType: 'json',
                    success: function(json) {
                        if (json.status) {
                            alert('The code is already exist!');
                            $o.val(null);
                            $o.focus();
                        }
                    }
                });
            });
            
        </script>
    </body>
</html>