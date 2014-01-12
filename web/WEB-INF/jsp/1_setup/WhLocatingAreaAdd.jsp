<%@page import="com.app.wms.engine.db.dto.WhLocatingArea"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>IMS - New Locating Area Add</title>
		<%@include file="../metaheader.jsp" %>
	</head>
	<body>
		 <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            WhLocatingArea dto = (WhLocatingArea) m.get("dto");
            String mode = (String) m.get("mode");
        %>
        
         <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="LocatingArea.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <input type="hidden" name="locatingId" value="<%= dto.getLocatingId()%>"/>
                        <table class="collapse tblForm row-select">
                            <caption>Locating Area - Add</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td class="style1">Locating Area</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="locatingArea" value="<%=dto.getLocatingArea() %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                	<td></td>
                                	<td></td>
                                </tr>
                                <tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td class="style1">Location Condition</td>
                                    <td class="style1">                                                                
		                                <input type="radio" name="locatingCondition" value="GOOD" checked="">Good&nbsp; &nbsp;
		                                <input type="radio" name="locatingCondition" value="DAMAGE">Damage                                                                
				                    </td>
                                    <td></td>
                                	<td></td>
                                	<td></td>
                                </tr>
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
                    location.href = 'LocatingArea.htm';
                });
            });
        </script>
	</body>
</html>