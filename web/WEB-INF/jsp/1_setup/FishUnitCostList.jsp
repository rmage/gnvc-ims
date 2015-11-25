<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Fish Unit Cost List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishUnitCost.htm" method="post" id="search">
                        <table class="collapse tblForm row-select">
                            <caption>Search Fish Cost</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Contract Number
                                    </td>
                                    <td>
                                        <input type="text" id="contract_number" name="contract_number" />
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">
                                        Supplier Name
                                    </td>
                                    <td>
                                        <select name="supplier_code" id="supplier_code" style="width: 40%">
                                            <option value="ALL">--- All Supplier ---</option>
                                            <c:if test="${model.suppliers!=null}">
                                                <c:forEach items="${model.suppliers}" var="supplier">
                                                    <option value=<c:out value="${supplier.code}"/>
                                                            <c:if test="${supplier.code == model.supplierCode}"> selected </c:if>>
                                                        <c:out value="${supplier.code} - ${supplier.name}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span>
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                        <label>
                                            <input type="button" name="button" id="btnAdd" value="Add" />
                                        </label>
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Fish Unit Cost - List</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Action</td>
                                <td column="contract_number">Contract Number</td>
                                <td column="supplier_code">Supplier Code</td>
                                <td>Begin Date</td>
                                <td>End Date</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot></tfoot>
                    </table>

                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'FishUnitCostList.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:l');
        </script>
    </body>
</html>