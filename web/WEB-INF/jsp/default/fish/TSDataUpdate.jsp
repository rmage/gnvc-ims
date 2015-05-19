<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Update &there4; Transfer Slip &there4; IMS</title>
        <%@include file="../../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishTs.htm" method="post" name="updateForm" id="updateForm">
                        <input type="hidden" id="tsId" name="tsId" value="${model.tss[0].ts_id}">
                        <input type="hidden" id="wdsId" name="wdsId" value="${model.tss[0].wds_id}">
                        <input type="hidden" id="vesselId" name="vesselId" value="${model.tss[0].vessel_id}">
                        <table class="collapse tblForm row-select">
                            <caption>Transfer Slip &there4; Header</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                    <td></td>
                                    <td>TS No.</td>
                                    <td>
                                        <label>
                                            <input type="text" id="tsNo" name="tsNo" value="${model.tss[0].ts_no}" size="30" readonly>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td>Date</td>
                                    <td>
                                        <label>
                                            <input type="text" id="tsDate" name="tsDate" value="" size="30" required>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>WDS No.</td>
                                    <td>
                                        <label>
                                            <input type="text" id="wdsNo" name="wdsNo" readonly value="${model.tss[0].wds_no}" size="30">
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td>Issued By</td>
                                    <td>
                                        <label>
                                            <input type="text" id="issuedBy" name="issuedBy" value="${model.tss[0].issued_by}" size="30">
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>Batch No.</td>
                                    <td>
                                        <label>
                                            <input type="text" id="batchNo" name="batchNo" value="${model.tss[0].batch_no}" readonly="readonly" size="30">
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td>Approved By</td>
                                    <td>
                                        <label>
                                            <input type="text" id="approvedBy" name="approvedBy" value="${model.tss[0].approved_by}" size="30">
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>Noted By</td>
                                    <td>
                                        <label>
                                            <input type="text" id="notedBy" name="notedBy" value="${model.tss[0].noted_by}" size="30">
                                        </label>
                                    </td>
                                    <td></td>
                                    <td>Received By</td>
                                    <td>
                                        <label>
                                            <input type="text" id="receivedBy" name="receivedBy" value="${model.tss[0].received_by}" size="30">
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <table class="collapse tblForm row-select" id="main">
                            <caption>Transfer Slip &there4; Detail</caption>
                            <thead>
                                <tr>
                                    <td>No.</td>
                                    <td class="center">Fish Code</td>
                                    <td class="center">Description</td>
                                    <td class="center">Request Qty.</td>
                                    <td class="center">UOM</td>
                                    <td class="center">Storage</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${model.tss}" var="x" varStatus="vs">
                                    <tr data-id="${x.id}" data-fish="${x.fish_id}" data-storage="${x.storage_id}">
                                        <td>${vs.index + 1}</td>
                                        <td>${x.fish_code}</td>
                                        <td>${x.fish_description}</td>
                                        <td><input type="text" value="${x.qty}" name="qty" id="qty" data-value="${x.qty}"></td>
                                        <td>${x.uom_code}</td>
                                        <td>${x.storage_name}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover">
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                        <label>
                                            <input type="submit" style="font-size: smaller;" name="btnSave" id="btnSave" value="Update" class="simpan">
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" onclick="location.href = 'FishTs.htm';">
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
            $('#tsDate').datepicker({dateFormat: "dd/mm/yy", changeYear: true, changeMonth: true}).val(gnvs.util.toViewDate('${model.tss[0].ts_date}'));
            $('#updateForm').bind('submit', function() {
                var data = '', header;
                header = $('#tsId').val() + ':s:' + $('#wdsId').val() + ':s:' + $('#vesselId').val() + ':s:' + $('#tsNo').val() + ':s:' + gnvs.util.toDBDate($('#tsDate').val()) + ':s:' + $('#issuedBy').val() + ':s:' + $('#notedBy').val() + ':s:' + $('#approvedBy').val() + ':s:' + $('#receivedBy').val() + ':s:';

                $('#main tbody tr[data-status]').each(function() {
                    data = data + header + $(this).data('id') + ':s:' + $(this).data('fish') + ':s:' + $(this).data('storage') + ':s:' + $(this).find('td:eq(2)').html() + ':s:' + $(this).find('input').val() + ':s:' + $(this).find('td:eq(4)').html() + ':s::se:';
                });

                if (confirm('Update Fish Transfer #' + $('#tsNo').val() + ' ?')) {
                    if (data === '') {
                        data = data + header + '-1:s::se:';
                    }
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNUpdate', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }

                return false;
            });

            $('#main tbody tr input').live('blur', function() {
                if (isNaN(this.value)) {
                    this.value = 0;
                }
                
                if (parseFloat($(this).data('value')) !== parseFloat(this.value)) {
                    $(this).parent().parent().attr('data-status', 'U');
                } else {
                    $(this).parent().parent().removeAttr()('data-status');
                }
            });
        </script>
    </body>
</html>