<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update User</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        	com.app.wms.engine.db.dto.User dto = (com.app.wms.engine.db.dto.User) m.get("dto");
        	String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="User.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="userId" value="<%= dto.getUserId()%>"/>

                        <table class="collapse tblForm row-select">
                            <caption>User - Detail</caption>
                            <tbody class="tbl-nohover">
                                <%-- 
                                 <tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td width="20%" class="style1">Corporate Id</td>
                                    <td width="80%" class="style1">
                                        <select name="corpId" id="corporate">
                                            <c:forEach items="${model.corporates}" var="corporate">
                                                <option value="<c:out value="${corporate.id}" />">
                                                    <c:out value="${corporate.id}" />
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                
                                <tr>
                            	 <td class="style1">&nbsp;</td>
                                 <td class="style1">Warehouse Id</td>
								    <td class="style1">
								    		
								    --%>
	                                        
	                                        	<%--
	                                        	<select name="whCode" id="warehouse">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListWarehouse}">
	                                        		  <option value="${droplist.whCode}" ${(droplist.whCode eq requestScope.model.whCode)? "selected": ""}>
	                                        		  	${droplist.whCode}
	                                        		  </option> 
	                                        	</c:forEach>
	                                            --%>
	                                          <%--   
	                                        </select>
	                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                            		</td>
                                </tr>
                                --%>
                                <%-- 
                                
                                <tr>
                                	<td class="style1">&nbsp;</td>
                                    <td class="style1">Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="code" value="<%= dto.getCode()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                --%>
                                <tr>
                                	<td class="style1">&nbsp;</td>
                                    <td class="style1">Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="name" value="<%= dto.getName()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">&nbsp;</td>
                                    <td class="style1">Username</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="username" value="<%= dto.getUsername()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td class="style1">Old Password</td>
                                    <td class="style1">
                                        <input name="oldPwd" type="password" value="" />
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">&nbsp;</td>
                                    <td class="style1">New Password</td>
                                    <td class="style1">
                                        <input name="newPwd" type="password" value="" />
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">&nbsp;</td>
                                    <td class="style1">Role Code</td>
                                    <td class="style1">
                                        <select name="roleCode" id="roleCode">
                                            <c:forEach items="${model.roles}" var="role">
                                                <option value="<c:out value="${role.roleCode}" />" <c:if test="${role.roleCode == model.dto.roleCode}">selected</c:if>>
                                                    <c:out value="${role.roleCode}" />
                                                </option>
                                            </c:forEach>
                                        </select>

                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">&nbsp;</td>
                                    <td class="style1">Email</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="email" value="<%= dto.getEmail()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">&nbsp;</td>
                                    <td class="style1">Is Active</td>
                                    <td class="style1">
                                        <label>
                                            <input type="radio" name="isActive" value="Y" <% if (dto.getIsActive().equalsIgnoreCase("Y")) {%> checked="checked" <% }%> /> Y
										</label>
										<label>
					    					<input type="radio" name="isActive" value="N" <% if (dto.getIsActive().equalsIgnoreCase("N")) {%> checked="checked" <% }%> /> N
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="10">
                                    <span class="style1">
                                        <label>
                                            <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                        </label>

                                    </span>
                                    <input type="button" class="style1" name="button" id="btnBack" value="Back" />
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
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'User.htm';
                    return false;
                });


            });
        </script>
    </body>

</html>