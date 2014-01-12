<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Put Away Cross Dock View</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="PAForm" method="post" action="PACrossDock.htm" autocomplete="off">
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Put Away Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Put Away Number</td>
                                    <td class="style1"><label>${model.list.PACode}</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Good Receive Number</td>
                                    <td class="style1"><label>${model.list.GRCode}</td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Location</td>
                                    <td class="style1">${model.list.createdBy}</td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Tallyman</td>
                                    <td class="style1">${model.list.updatedBy}</td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Put Away Info</td>
                                    <td class="style1">${model.list.PAInfo}</td>
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
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Put Away Cross Dock - Detail</caption>
                            <thead>
                                <tr>
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
                $('#btnBack').unbind('click').click(function(){
                    window.location.href = "?action=list";
                });
                $('#btnApprove').click(function(){
                    window.location.href = "?action=approved&paCode=${model.list.PACode}";
                });
                $.ajax({
                    url: "PACrossDock.htm",
                    data: "action=gr&grCode=${model.list.GRCode}",
                    dataType: "text",
                    success: function(html){
                        html = html.trim();
                        if(html != 'N'){
                            $('#detail').val("Data valid");
                            $('#list').html(html);
                        } else {
                            $('#detail').val("");
                            $('#grCode').val("");
                            $('#list').html("");
                        }
                    },
                    error: function(jqXHR, textStatus, data){
                        alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                    }
                });
            });
        </script>
    </body>
</html>