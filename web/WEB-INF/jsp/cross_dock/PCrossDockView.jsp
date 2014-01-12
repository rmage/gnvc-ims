<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Picking Cross Dock Update</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="PAForm" method="post" action="PCrossDock.htm" autocomplete="off">
                        <input type="hidden" name="action" value="edit" >
                        <input type="hidden" name="pCode" value="${model.list.PCode}" />
                        <input type="hidden" id="detail" name="detail" value="" />
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Picking Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Picking Number</td>
                                    <td class="style1"><label>${model.list.PCode}</label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Sales Order Number</td>
                                    <td class="style1"><label>${model.list.SOCode}</label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Picker</td>
                                    <td class="style1">${model.list.updatedBy}</td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Picking Date</td>
                                    <td class="style1"><fmt:formatDate pattern="dd/MM/yyyy" value="${model.list.PDate}" /></td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="3">
                                    <span class="style1">
                                        <c:if test="${user.roleCode == 'SPV' && list.approvedBy == null}">
                                            <input type="button" value="Approve" id="btnApprove" name="button" class="style1 ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                        </c:if>
                                        <label>
                                            <input type="submit" value="Print" id="btnSave" name="btnSave" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                        </label>
                                        <input type="button" value="Back" id="btnBack" name="button" class="style1 ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                    </span>
                                </td>
                            </tr></tfoot>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Sales Order Cross Dock - Detail</caption>
                            <thead>
                                <tr>
                                    <td colspan="5"><span style="float: right">Location: <b>${model.listPA.createdBy}</b></span></td>
                                </tr>
                                <tr>
                                    <td>No</td>
                                    <td>Product Code</td>
                                    <td>Product Name</td>
                                    <td>Quantity</td>
                                    <td>Type</td>
                                </tr>
                            </thead>
                            <tbody id="list" class="tbl-nohover">
                                
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>
        <script type="text/javascript">
            $(function(){
                $.ajax({
                    url: "PCrossDock.htm",
                    data: "action=so&soCode=${model.list.SOCode}",
                    dataType: "text",
                    success: function(html){
                        html = html.trim();
                        if(html != 'N'){
                            $('#detail').val("Data valid");
                            $('#list').html(html);
                        } else {
                            $('#detail').val("");
                            $('#soCode').val("");
                            $('#list').html("");
                        }
                    },
                    error: function(jqXHR, textStatus, data){
                        alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                    }
                });
                $('#btnBack').unbind('click').click(function(){
                    window.location.href = "?action=list";
                });
                $('#btnApprove').click(function(){
                   window.location.href = "?action=approved&pCode=${model.list.PCode}"; 
                });
            });
        </script>
    </body>
</html>