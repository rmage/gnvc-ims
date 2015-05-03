<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS &there4; Transfer Slip &there4; Create</title>
        <%@include file="../../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishTs.htm" method="post" name="form" id="addForm">
                        <input type="hidden" id="totalData" name="totalData" value="">
                        <input type="hidden" name="action" value="save">
                        <input type="hidden" id="vesselId" name="vesselId" value="0">
                        <table class="collapse tblForm row-select">
                            <caption>Transfer Slip &there4; Header</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                    <td></td>
                                    <td class="style1">TS No.</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="tsNo" name="tsNo" value="" size="30" class="validate[required] text-input numeric" readonly>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="tsDate" name="tsDate" value="" size="30" class="text-input" required>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td class="style1">WDS No.</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="wdsNo" name="wdsNo" readonly value="" size="30" class="validate[required] text-input">
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                        <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                    </td>
                                    <td></td>
                                    <td class="style1">Issued By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="issuedBy" name="issuedBy" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td class="style1">Batch No.</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="batchNo" name="batchNo" value="" 
                                                   readonly="readonly" size="30" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Approved By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="approvedBy" name="approvedBy" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td class="style1">Noted By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="notedBy" name="notedBy" value="" 
                                                   size="30" class="text-input"/>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Received By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="receivedBy" name="receivedBy" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <table class="collapse tblForm row-select" id="main">
                            <caption>Transfer Slip &there4; Detail</caption>
                            <thead>
                                <tr>
                                    <td class="style1">No.</td>
                                    <td class="center">Fish Code</td>
                                    <td class="center">Description</td>
                                    <td class="center">Request Qty.</td>
                                    <td class="center">UOM</td>
                                    <td class="center">Storage</td>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>

                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover">
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnSave" id="btnSave" value="Save" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" onclick="location.href = 'FishTs.htm';">
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
            $('#tsDate').datepicker({ dateFormat: "dd/mm/yy", changeYear: true, changeMonth: true });
        </script>
    </body>
</html>