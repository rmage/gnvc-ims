<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Labeling Monitoring &therefore; IMS</title>
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
                    <form action="#" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Labeling Monitoring &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">LM Number</td>
                                    <td><input type="text" name="lmr_code" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('FGLabelingMonitoring.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Labeling Monitoring &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 60px">Action</td>
                                <td column="lmr_code">LM Number</td>
                                <td>LM Date</td>
                                <td>LM Quantity</td>
                                <td column="ofal_code">OFAL Code</td>
                                <td>OFAL Date</td>
                                <td>Buyer</td>
                                <td>Brand</td>
                                <td>Reff</td>
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
        <!--<div id="fyaQPanel" class="div-dtl" style="width: 100%; display: none;" ondblclick="fyaQPanel(0)"></div>-->
        <script>
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d:R_FGLmr_Print Labeling Monitoring Report (xls)');
           
        </script>
    </body>
</html>
