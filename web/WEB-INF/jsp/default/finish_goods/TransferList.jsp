<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            
            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FGTransfer.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Transfer &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">TS Number</td>
                                    <td><input type="text" name="ts_code" /></td>
                                    <td>TS Type</td>
                                    <td>
                                        <input type="hidden" id="tsType" name="ts_type" />
                                        <select name="packSize" onchange="document.getElementById('tsType').value = this.value">
                                            <option value="">-</option>
                                            <option value="B">Bad Stocks</option>
                                            <option value="O">Others</option>
                                            <option value="R">Re-Process</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('FGTransfer.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Transfer &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 60px">Action</td>
                                <td column="ts_code">TS Number</td>
                                <td column="ts_type">TS Type</td>
                                <td>TS Date</td>
                                <td column="ts_from">From</td>
                                <td column="ts_to">To</td>
                                <td>Remarks</td>
                                <td>Created By</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
            </div>
            
            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d:R_Transfer Slip (xls)');
        </script>
    </body>
</html>
