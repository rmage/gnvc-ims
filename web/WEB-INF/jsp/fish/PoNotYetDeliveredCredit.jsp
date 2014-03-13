<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Generate Po Not Yet Delivered Credit</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
        </style>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="#" method="post" id="genForm">
                        <table class="collapse tblForm row-select">
                            <caption>Generate Po Not Yet Delivered Credit</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                    <td style="width: 150px;">Canvasser Name</td>
                                    <td>
                                        <select id="canvasser" name="canvasser" required="true">
                                            <option value="">-- Pick Canvasser --</option>
                                            <c:forEach items="${model.u}" var="x">
                                            <option value="${x.userId}">${x.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Report Format</td>
                                    <td>
                                        <select id="type" name="type" required="true">
                                            <option value="xls">XLS</option>
                                            <option value="pdf">PDF</option>
                                            <option value="csv">CSV</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <tfoot class="ui-widget-header">
                                <tr><td>
                                        <label>
                                            <input id="generate" type="submit" value="Generate Report" />
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
        <script>
            $('#genForm').bind('submit', function() {
                window.location.replace('GenerateReport.htm?action=index&item=PPoNotyetDeliveredCredit&type=' + $('#type').val() + '&params=' + $('#canvasser').val());
                return false;
            });
        </script>
    </body>
</html>
