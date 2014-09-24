<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Finished Goods Item</title>
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
                    <form action="FGItem.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Finished Goods Item &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Item Code</td>
                                    <td><input type="text" name="item_code" /></td>
                                    <td style="width: 200px;">Item Name</td>
                                    <td><input type="text" name="item_name" /></td>
                                </tr>
                                <tr>
                                    <td>Pack Style</td>
                                    <td><input type="text" name="pack_style" /></td>
                                    <td>Pack Size</td>
                                    <td>
                                        <input type="hidden" id="packSize" name="pack_size" />
                                        <select name="packSize" onchange="document.getElementById('packSize').value = this.value">
                                            <option value="">-</option>
                                            <option value="307">307</option>
                                            <option value="603">603</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('FGItem.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Finished Goods Item &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td column="item_code">Item Code</td>
                                <td column="item_name">Item Name</td>
                                <td column="fgps.pack_style">Pack Style</td>
                                <td column="fgps.pack_size">Pack Size</td>
                                <td column="item_oil">Oil</td>
                                <td column="item_lid">Lid</td>
                                <td column="item_nw">Net Weight</td>
                                <td coulmn="item_dwpw">Drained\Pressed Weight</td>
                                <td>Created By</td>
                                <td>Created Date</td>
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
            util.initListTable($('#list'), 'u:d');
        </script>
    </body>
</html>
