<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Quarantine View</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <table class="collapse tblForm row-select ui-widget-content">
                        <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Quarantine</caption>
                        <tbody class="tbl-nohover">
                            <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                <td class="style1">Quarantine No</td>
                                <td class="style1"><label>${model.list.QCode}</label></td>
                            </tr>
                            <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                <td class="style1">Purchase Order No</td>
                                <td class="style1">${model.list.createdBy}</td>
                            </tr>
                            <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                <td class="style1">Put Away No</td>
                                <td class="style1">${model.list.putawayCode}</td>
                            </tr>
                            <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                <td class="style1">Quarantine Date</td>
                                <td class="style1"><fmt:formatDate pattern="dd/MM/yyyy" value="${model.list.QDate}" /></td>
                            </tr>
                        </tbody>
                        <tfoot class="ui-widget-header">
                            <tr><td colspan="3">
                                <span class="style1">
                                    <label>
                                        <input type="button" value="Print" id="btnPrint" name="btnPrint" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                    </label>
                                    <input type="button" value="Back" id="btnBack" name="btnBack" class="style1 ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                </span>
                            </td>
                        </tr></tfoot>
                    </table>
                    <table class="collapse tblForm row-select ui-widget-content">
                        <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Quarantine - Detail</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Product Code</td>
                                <td>Product Name</td>
                                <td>Put Away Qty</td>
                                <td>PO Cross Dock Qty</td>
                            </tr>
                        </thead>
                        <tbody id="list" class="tbl-nohover">

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>
        <script type="text/javascript">
            $(function(){
                $.ajax({
                    url: "Quarantine.htm",
                    data: "action=qdtl&putawayCode=${model.list.putawayCode}",
                    dataType: "text",
                    success: function(html){
                        html = html.trim();
                        if(html != 'N'){
                            $('#list').html(html);
                        } else {
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
            })
        </script>
    </body>
</html>