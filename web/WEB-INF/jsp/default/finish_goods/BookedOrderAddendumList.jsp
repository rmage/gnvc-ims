<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Booked Order Addendum</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker { display: none; }
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
                    <form action="FGLocation.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Booked Order Addendum &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Bor Number</td>
                                    <td><input type="text" id="borCode" name="fb.bor_code"></td>
                                    <td>Bor Date</td>
                                    <td><input type="text" id="borDate" name="fb.bor_date"></td>
                                </tr>
                                <tr>
                                    <td>Addendum Number</td>
                                    <td><input type="text" id="boradNumber" name="fba.borad_number"></td>
                                    <td>Addendum Date</td>
                                    <td><input type="text" id="boradDate" name="fba.borad_date"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('FGBookedOrderAddendum.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Booked Order Addendum &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td column="fb.bor_code">Bor Number</td>
                                <td column="fb.bor_date">Bor Date</td>
                                <td column="fba.borad_number">Addendum Number</td>
                                <td column="fba.borad_date">Addendum Date</td>
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
            // bind date element
            $('#borDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"});
            $('#boradDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"});

            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'R_FGBorAddendum_Booked Order Addendum Report (xls)');
        </script>
    </body>
</html>
