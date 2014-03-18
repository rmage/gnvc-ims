<%@page import="com.app.wms.engine.db.dto.Currency"%>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Update Currency</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%            
        java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            Currency dto = (Currency) m.get("dto");
            String mode = (String) m.get("mode");
            String currencyCode = (String) dto.getCurrencyCode();
            String currencyName = (String) dto.getCurrencyName();
            String currencySymbol = (String) dto.getCurrencySymbol();
            String id = Integer.toString(dto.getId());
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Currency.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="id" value="<%=id%>"/>

                        <table class="collapse tblForm row-select">
                            <caption>Currency - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Currency Code</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="code" value="<%=currencyCode%>" maxlength="10" size="12" readonly />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Currency Name</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="name"  value="<%=currencyName%>" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Currency Symbol</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="symbol"  value="<%=currencySymbol%>" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Is Active</td>
                                    <td>
                                        <label>
                                            <input type="radio" name="isActive" value="Y" <% if (dto.getIsActive().equalsIgnoreCase("Y")) {%> checked="checked" <% }%> /> Y
                                        </label>
                                        <label>
                                            <input type="radio" name="isActive" value="N" <% if (dto.getIsActive().equalsIgnoreCase("N")) {%> checked="checked" <% }%> /> N
                                        </label>
                                    </td>
                               	</tr>
                            </tbody>
                            <tfoot>
                            <td colspan="2">
                                <span>
                                    <label>
                                        <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                    </label>
                                </span>
                                <input type="button" name="button" id="btnBack" value="Back" />
                            </td>
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
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Currency.htm';
                    return false;
                });
            });
        </script>
    </body>
</html>
