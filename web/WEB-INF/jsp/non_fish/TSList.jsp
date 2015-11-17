<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer &therefore; IMS</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            #fyaQPanelBody tr:nth-child(2n+1) {
                background-color: #EEEEEE;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="search" action="TransferSlip.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Transfer &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">TS Number</td>
                                    <td><input type="text" name="ts_code" /></td>
                                    <td>TS Type</td>
                                    <td>
                                        <input id="ts_type" name="ts_type" type="hidden" value="" />
                                        <select name="type" onchange="document.getElementById('ts_type').value = this.value">
                                            <option value="">-- Show All --</option>
                                            <option value="NORMAL">Normal</option>
                                            <option value="OTHERS">Others</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <select id="type">
                                            <option value="NORMAL">Normal</option>
                                            <option value="OTHERS">Others</option>
                                        </select>
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('TransferSlip.htm?action=create&module=NF&type=' + document.getElementById('type').value);" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table id="list" class="collapse tblForm row-select">
                        <caption>Transfer &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 60px">Action</td>
                                <td>TS Number</td>
                                <td>TS Date</td>
                                <td>TS Type</td>
                                <td>Sws Number</td>
                                <td>Info</td>
                                <td>For Production?</td>
                                <td>Creator</td>
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
            util.initListTable($('#list'), 'u:d:R_NFTs_Transfer Slip (xls)');
        </script>
    </body>
</html>
