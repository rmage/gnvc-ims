<%-- 
    Document   : CategoryItemCurrencyTypeList
    Created on : Oct 1, 2014, 11:35:25 AM
    Author     : Faridzi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Setup : Category Currency Type</title>
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
                    <form action="CategoryItemCurrencyType.htm" method="post" id="search">
                        <table class="collapse tblForm row-select">
                            <caption>Search Filter Category Currency Type</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Category
                                    </td>
                                    <td class="style1">
                                        <select name="dropdownProdCode" id="dropdownProdCode" style="width: 20%" onchange="throwValue()">
                                            <option value="%">--- All Category ---</option>
                                            <c:if test="${model.productCategoryList!=null}">
                                                <c:forEach items="${model.productCategoryList}" var="prodCat">
                                                    <option value=<c:out value="${prodCat.categoryCode}"/>
                                                            <c:if test="${prodCat.categoryCode == model.selectedCatCode}"> selected </c:if>>
                                                        <c:out value="${prodCat.categoryCode}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
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
                                        <input type="text" id="currencyTypeHidden" name="currency_type" style="visibility: hidden;" />
                                        <input type="text" id="prodCatHidden" name="category_code" style="visibility: hidden;" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                        <label>
<!--                                            <input type="button" name="button" id="btnAdd" value="Add" />-->
                                        </label>
                                        <label>
                                            <input type="button" name="button" id="btnClear" value="Clear" />
                                        </label>					
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>

                    <table class="collapse tblForm row-select" id="list">
                        <caption>Category Currency Type Result</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td></td>
                                <td column="currency_type">Category</td>
                                <td column="currency_code_from">Item Name</td>
                                <td>Currency Type</td>
                                <td>Updated By</td>
                                <td>Updated Date</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                        </tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
            </div>
        </div>

        <script>
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'CategoryItemCurrencyType.htm?action=create';
                });

                $('#btnClear').click(function() {
                    location.href = 'CategoryItemCurrencyType.htm';
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });
            
            function throwType() {
                var elem = document.getElementById("currencyTypeHidden");
                elem.value = $('#groupCurrencyType').val();
            }
            
            function throwValue() {
                var elem = document.getElementById("prodCatHidden");
                elem.value = $('#dropdownProdCode').val();
            }
            
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:z');
        </script>
    </body>
</html>
