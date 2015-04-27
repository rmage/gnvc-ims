<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Weight Slip Summary</title>
        <%@include file="../../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="FishReceivingReport.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Weight Slip Summary &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">WSS Number</td>
                                    <td><input type="text" name="fwss.code" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="location.replace('FishWsSummary.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Weight Slip Summary &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 60px">Action</td>
                                <td column="code">Number</td>
                                <td column="date_from">Date From</td>
                                <td column="date_to">Date To</td>
                                <td>Batch No</td>
                                <td>Supplier</td>
                                <td>Boat</td>
                                <td>Creator</td>
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

        <script>
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d:R_FishWsS_Weight Slip Summary Report (xls)');

            // change current printout destination
            $('#main tr td:nth-child(2) a[href^="GenerateReport.htm"]').live('mouseenter', function() {
                if ($(this).attr('href').indexOf('FishWsSF') === -1 && $(this).attr('href').indexOf('FishWsSZ') === -1) {
                    var type = $(this).parent().parent().find('td:eq(5)').html().slice(-4);
                    if (type.indexOf('F') > -1) {
                        $(this).attr('href', $(this).attr('href').replace('FishWsS', 'FishWsSF'));
                    } else if (type.indexOf('Z') > -1) {
                        $(this).attr('href', $(this).attr('href').replace('FishWsS', 'FishWsSZ'));
                    }
                }
            });
        </script>

    </body>
</html>
