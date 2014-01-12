<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - SO Cross Dock Update</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="DOForm" method="post" action="DOCrossDock.htm" autocomplete="off">
                        <input type="hidden" name="action" value="edit" />
                        <input type="hidden" name="doCode" value="${model.list.DOCode}" />
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Delivery Order Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Delivery Order No</td>
                                    <td class="style1"><label>${model.list.DOCode}</label></td>
                                    <td class="style1" style="vertical-align: top" rowspan="4" width="500">Consignee Detail: <br /><label id="cc" style="font-size: 18px"></label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Sales Order No</td>
                                    <td class="style1"><label>${model.list.SOCode}</label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Delivery Order Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="validate[required]" id="doDate" name="doDate" size="30" maxlength="30" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${model.list.DODate}" />" />
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">*</label>
                                    </td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Transporter Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" size="30" id="doName" name="doName" value="${model.list.DOName}" />
                                        </label>
                                        <label title="This Field Is Required!" class="requiredfield">leave blank if use internal transporter</label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="3">
                                    <span class="style1">
                                        <label>
                                            <input type="button" value="Save" id="btnSave" name="btnSave" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                        </label>
                                        <input type="button" value="Cancel" id="btnBack" name="button" class="style1 ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                    </span>
                                </td>
                            </tr></tfoot>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Sales Order Cross Dock - Detail</caption>
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
                $('#doDate').datepicker({dateFormat: "dd/mm/yy"});
                $.ajax({
                    url: "DOCrossDock.htm",
                    data: "action=so&soCode=${model.list.SOCode}",
                    dataType: "text",
                    success: function(html){
                        html = html.trim();
                        if(html != 'N'){
                            $('#list').html(html);
                            
                            /* get consignee detail */
                            $.ajax({
                                url: "DOCrossDock.htm",
                                data: "action=getCC&soCode=${model.list.SOCode}",
                                dataType: "text",
                                success: function(html){
                                    html = html.trim();
                                    if(html != 'N'){
                                        $('#cc').html(html);
                                    } else {
                                        $('#cc').html("");
                                    }
                                },
                                error: function(jqXHR, textStatus, data){
                                    alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                                }
                            });
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
                $('#btnSave').click(function(){
                    if($('#DOForm').validationEngine('validate') && $('#detail').val() != ""){
                        $('#DOForm').submit();
                    }
                });
            })
        </script>
    </body>
</html>