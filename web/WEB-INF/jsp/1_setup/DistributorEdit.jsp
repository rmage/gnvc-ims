<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Update Distributor</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Distributor.htm" method="post">
                        <input type="hidden" name="action" value="edit" />
                        <input type="hidden" name="id" value="${model.dist.id}"/>

                        <table class="collapse tblForm row-select">
                            <caption>Distributor - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Distributor Code</td>
                                    <td>
                                        <label>
                                            <input type="text" name="distributorCode" value="${model.dist.distributorCode}" maxlength="10" size="12" readonly />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Distributor Name</td>
                                    <td>
                                        <label>
                                            <input type="text" name="distributorName"  value="${model.dist.distributorName}" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Distributor Address</td>
                                    <td>
                                        <label>
                                            <input type="text" name="distributorAddress"  value="${model.dist.distributorAddress}" maxlength="55" size="55" pattern="^\S+[^'\x22]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Distributor Telephone</td>
                                    <td>
                                        <label>
                                            <input type="text" name="telephone"  value="${model.dist.telephone}" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Distributor Fax</td>
                                    <td>
                                        <label>
                                            <input type="text" name="fax"  value="${model.dist.fax}" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Distributor Email</td>
                                    <td>
                                        <label>
                                            <input type="text" name="email"  value="${model.dist.email}" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Contact Person</td>
                                    <td>
                                        <label>
                                            <input type="text" name="contactPerson"  value="${model.dist.contactPerson}" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
                                        </label>
                                        <label>*</label>
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
                    location.href = 'Distributor.htm';
                    return false;
                });


            });
        </script>
    </body>
</html>