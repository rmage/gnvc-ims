<%-- 
    Document   : CategoryItemCurrencyTypeEdit
    Created on : Oct 1, 2014, 13:35:25 AM
    Author     : Faridzi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Edit : Category Currency Type</title>
        <%@include file="../metaheader.jsp" %>

        <script type="text/javascript">

        </script>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="CategoryItemCurrencyType.htm" method="post" id="addForm">
                        <input type="hidden" name="action" value="add" />
                        <table class="collapse tblForm row-select">
                            <caption>Search Filter Currency Rate History</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Category
                                    </td>
                                    <td class="style1">
                                        <select name="currency_code_from" id="dropdownProdCode" style="width: 20%" onchange="throwValue()">
                                            <option value="%">--- All Category ---</option>
                                            <c:if test="${model.productCategoryList!=null}">
                                                <c:forEach items="${model.productCategoryList}" var="prodCat">
                                                    <option value=<c:out value="${prodCat.categoryCode}"/>
                                                            <c:if test="${prodCat.categoryCode == model.selectedCatCode}"> selected </c:if>>
                                                        <c:out value="${prodCat.categoryCode} - ${prodCat.categoryName}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">
                                        Item Name
                                    </td>
                                    <td>
                                        ${model.categoryItemCurrencyType.productId}
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">
                                        Currency Type
                                    </td>
                                    <td>
                                        <select name="groupCurrencyType" id="groupCurrencyType" style="width: 20%" onchange="throwType()">
                                            <option value="%">--- All Type ---</option>
                                            <option value="DAILY">DAILY</option>
                                            <option value="WEEKLY">WEEKLY</option>
                                            <option value="MONTHLY">MONTHLY</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <span>
                                            <input class="simpan" type="submit" value="Save" id="btnSave" name="btnSave" onclick="return confirm('Are you sure you want to continue ?')" />
                                            <label>
                                                <input type="button" name="button" id="btnCancel" value="Cancel" />
                                            </label>
                                        </span>					
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>


                </div>
            </div>
        </div>

        <script>
            $(function() {

                $('#btnCancel').click(function() {
                    window.history.back();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });

        </script>
    </body>
</html>
