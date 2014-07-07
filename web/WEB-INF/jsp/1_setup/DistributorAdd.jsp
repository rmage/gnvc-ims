<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - New Supplier</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Distributor.htm" method="post" id="addForm">
                        <input type="hidden" name="mode" value="" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Distributor - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Distributor Code</td>
                                    <td>
                                        <label>
                                            <input type="text" id="distributorCode" name="distributorCode" maxlength="10" size="12" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Distributor Name</td>
                                    <td>
                                        <label>
                                            <input type="text" name="distributorName" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Distributor Address</td>
                                    <td>
                                        <label>
                                            <input type="text" name="distributorAddress" maxlength="55" size="55" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Distributor Telephone</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="telephone" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Distributor Fax</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="fax" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Distributor Email</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="email" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Contact Person</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="contactPerson" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">

                            <tbody class="tbl-nohover">

                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                        <label>
                                            <input type="submit" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnSave" id="btnSave" value="Save" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" />
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
        <script type="text/javascript">
            $(function() {
                $('#btnCancel').click(function() {
                    location.href = 'Distributor.htm';
                    return false;
                });

                $('#distributorCode').bind('blur', function() {
                    var $o = $(this);
                    if (!$o.val())
                        return;
                    $.ajax({
                        url: 'Distributor.htm?term=' + $o.val(),
                        method: 'post',
                        data: {
                            action: 'getUnique', term: $o.val()
                        },
                        dataType: 'json',
                        success: function(json) {
                            if (json.status) {
                                alert('The code is already exist!');
                                $o.val(null);
                                $o.focus();
                            }
                        }
                    });
                });
            });
        </script>
    </body>
</html>