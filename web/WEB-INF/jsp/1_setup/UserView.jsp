<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View User</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        	com.app.wms.engine.db.dto.User dto = (com.app.wms.engine.db.dto.User) request.getAttribute("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <table class="collapse tblForm row-select">
                        <caption>User - Detail</caption>
                        <tbody class="tbl-nohover">
                         <%-- 
                        	<tr>
                                <td class="style1">&nbsp;&nbsp;&nbsp;Corporate Id</td>
                                <td class="style1">
                                    <%= dto.getCorpId()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">&nbsp;&nbsp;&nbsp;Warehouse Id</td>
                                <td class="style1">
                                    <%= dto.getWhCode()%>
                                </td>
                            </tr>
                          --%>  
                            <%-- 
                            <tr>
                                <td class="style1">&nbsp;&nbsp;&nbsp;Code</td>
                                <td class="style1">
                                    <%= dto.getCode()%>
                                </td>
                            </tr>
                            --%>
                            <tr>
                                <td class="style1">&nbsp;&nbsp;&nbsp;Name</td>
                                <td class="style1">
                                    <%= dto.getName()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">&nbsp;&nbsp;&nbsp;Username</td>
                                <td class="style1">
                                    <%= dto.getUsername()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">&nbsp;&nbsp;&nbsp;Role Code</td>
                                <td class="style1">
                                    <%= dto.getRoleCode()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">&nbsp;&nbsp;&nbsp;Email</td>
                                <td class="style1">
                                    <%= dto.getEmail()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">&nbsp;&nbsp;&nbsp;Is Active</td>
                                <td class="style1">
                                    <%=dto.getIsActive()%>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="2">
                                <form action="User.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="userId" value="<%= dto.getUserId()%>" />
                                    <%-- <input type="submit" value="Edit"/>--%>
                                    <input type="button" class="style1" name="button" id="btnBack" value="Back" />
                                </form>

                            </td>
                        </tfoot>
                    </table>

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
                    location.href = 'User.htm';
                });


            });
        </script>
    </body>
</html>