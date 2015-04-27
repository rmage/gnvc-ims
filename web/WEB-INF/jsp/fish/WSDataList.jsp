<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Weight Slip</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="FishWs.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Weight Slip &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">WS Number</td>
                                    <td><input type="text" name="fw.ws_no" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="location.replace('FishWs.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Weight Slip &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 60px">Action</td>
                                <td>WS No</td>
                                <td>WS Date</td>
                                <td>WS Type</td>
                                <td>Batch No</td>
                                <td>Supplier Name</td>
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
            util.initListTable($('#list'), 'u:d:R_Fish_Weight Slip Report (xls)_1-3');
            
            $('#main tr td:nth-child(2)').live('mouseenter', function() {
                var $o = $(this).find('a:eq(2)');
                if($o.attr('href').indexOf('FishWS') < 0) {
                    $o.attr('href', $o.attr('href').replace('Fish', 'Fish' + $(this).parent().find('td:eq(4)').html().replace(/\d/g, '')));
                }
            });
        </script>
        
    </body>
</html>
