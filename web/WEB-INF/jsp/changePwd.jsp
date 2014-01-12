<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  
<html>
    <head>
        <title>IMS - Change Password</title>
        <%@include file="metaheader.jsp" %>

    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            com.app.wms.engine.db.dto.map.LoginUser dto = (com.app.wms.engine.db.dto.map.LoginUser) m.get("dto");
        %>
        <div class="container">
            <%@include file="header.jsp" %>
            <jsp:include page="dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="ChangePwd.htm" method="post" >
                        <input type="hidden" name="action" value="doChangePwd" />
                        <input type="hidden" name="userID" value="<%=dto.getUserId()%>" />
                        <input type="hidden" name="userName" value="<%=dto.getUsername()%>" />
                        <input type="hidden" name="userRole" value="<%=dto.getRoleCode()%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Change Password</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">User Name</td>
                                    <td class="style1"> <%=dto.getUsername()%>
									
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Old Password</td>
                                    <td class="style1">
                                        <input name="oldPwd" type="password" value="" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">New Password</td>
                                    <td class="style1">
                                        <input name="newPwd" type="password" value="" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                            <td colspan="2">
                                <span class="style1">
                                    <label>
                                        <input type="submit" name="btnSave" id="btnSave" value="Change" />
                                    </label>

                                </span>
                            </td>
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
    </body>
</html>
