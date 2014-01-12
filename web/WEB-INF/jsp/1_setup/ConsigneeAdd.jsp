<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>IMS - New Consignee</title>
		<%@include file="../metaheader.jsp" %>
	</head>
	<body>
		 <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            com.app.wms.engine.db.dto.Consignee dto = (com.app.wms.engine.db.dto.Consignee) m.get("dto");
            String mode = (String) m.get("mode");
        %>
        
         <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Consignee.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <input type="hidden" name="consigneeCode" value="<%= dto.getConsigneeCode()%>"/>
                        <table class="collapse tblForm row-select">
                            <caption>Consignee - Add</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td class="style1">Consignee Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="consigneeName" value="<%=dto.getConsigneeName() %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                	<td></td>
                                	<td></td>
                                </tr>
                                <tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td class="style1">Consignee PIC</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="consigneePIC" value="<%=dto.getConsigneePIC()!=null? dto.getConsigneePIC(): "" %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                	<td></td>
                                	<td></td>
                                </tr>
                                <tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td class="style1">Consignee Phone</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="consigneePhone" value="<%=dto.getConsigneePhone() %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                	<td></td>
                                	<td></td>
                                </tr>
                                <tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td class="style1">Consignee Address</td>
                                    <td class="style1">
                                        <label>
                                            <%-- <input type="text" name="address1" value="<%=dto.getAddress1()%>" size="50" />--%>
                                        	<textarea style="width: 324px; height: 41px;" id="address1" name="address1"><%=dto.getAddress1()%></textarea>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                	<td></td>
                                	<td></td>
                                </tr>
                                <%-- 
                                <tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td class="style1">Address2</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="address2" value="<%=dto.getAddress2()%>" size="50" />
                                        </label>
                                    </td>
                                    <td></td>
                                	<td></td>
                                	<td></td>
                                </tr>
                                <tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td class="style1">Address3</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="address3" value="<%=dto.getAddress3()%>" size="50" />
                                        </label>
                                    </td>
                                    <td></td>
                                	<td></td>
                                	<td></td>
                                </tr>
                                --%>
                            </tbody>
                            <tfoot>
                                <td colspan="2">
                                    <span class="style1">
                                        <label>
                                            <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                        </label>
                                        <input type="button" class="style1" name="button" id="btnBack" value="Back" />
                                    </span>
                                </td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                              
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
                    location.href = 'Consignee.htm';
                });
                
           

               
            });
        </script>
	</body>
</html>