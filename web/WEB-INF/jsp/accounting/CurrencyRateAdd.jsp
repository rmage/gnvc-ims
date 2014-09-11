<%@page import="com.app.wms.engine.db.dto.CurrencyRate"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Accounting : Add Currency Rate</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function(){
                $('#addForm').validationEngine('attach'); 
            });
        </script>
    </head>
    <body>
        <%
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            CurrencyRate dto = (CurrencyRate) m.get("dto");
            String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="CurrencyRate.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>Currency Rate - Add</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                   <td style="width: 20%">Currency Code From</td>
                                   <td class="style1" >
                                       <select name="groupCurrencyCodeFrom" id="groupCurrencyCodeFrom" style="width: 40%">
					    <c:if test="${model.currs!=null}">
						<c:forEach items="${model.currs}" var="curr">
						    <option value=<c:out value="${curr.currencyCode}"/>
							    <c:if test="${curr.currencyCode==model.currencyCode}"> selected </c:if>>
							<c:out value="${curr.currencyCode}" />
                                                    </option>
						</c:forEach>
					    </c:if>
					</select>
				    </td>
                                </tr>
                                <tr>
                                   <td style="width: 20%">Currency Code To</td>
                                   <td class="style1" >
                                       <select name="groupCurrencyCodeTo" id="groupCurrencyCodeTo" style="width: 40%">
					    <c:if test="${model.currs!=null}">
						<c:forEach items="${model.currs}" var="curr">
						    <option value=<c:out value="${curr.currencyCode}"/>
							    <c:if test="${curr.currencyCode==model.currencyCode}"> selected </c:if>>
							<c:out value="${curr.currencyCode}" />
                                                    </option>
						</c:forEach>
					    </c:if>
					</select>
				    </td>
                                </tr>
                                <tr>
                                    <td>Rate Value</td>
                                    <td>
                                        <label>
                                            <input type="text" name="rateValue" value="" size="50" pattern="^\S+[0-9 ]{1,}" required="true" style="width: 40%"/>
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Rate Date</td>
                                    <td>
                                        <label>
                                            <input type="text" name="rateDate" id="rateDate" value="" required="true" style="width: 20%"/>
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover"></tbody>
                            <tfoot class="ui-widget-header">
                                <tr>
                                    <td colspan="7">
                                        <label>
                                            <input type="submit" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnSave" id="btnSave" value="Save" class="simpan" />
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
                    location.href = 'CurrencyRate.htm';
                });
                
                $('#btnSave').click(function() {
                    var currSelect = document.getElementById('groupCurrencyCode');
                    if (currSelect.selectedIndex == 0) {
                        alert("Please select Currency Code...");
                        document.getElementById("groupCurrencyCode").focus();
                        return false;
                    }
                });
                
            });
            
            
            $('#rateDate').datepicker({
                dateFormat: "dd/mm/yy"
            });
            
        </script>                                
    </body>
</html>
