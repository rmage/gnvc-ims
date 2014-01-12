<%@page import="com.app.wms.engine.db.dto.Corporate"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New User</title>
        <%@include file="../metaheader.jsp" %>
        
        <script>
                $(document).ready(function() {
                    
                    $.ajax({
                        dataType: 'json',
                        success: function(data) {
                                $('#warehouse').find('option').remove();
                                $.each(data.wh, function(k,v){                                                         
                                    $('#warehouse').append('<option value="'+v.whCode+'">'+v.whCode+'</option>');
                                });
                            },
                            url: 'index.htm?action=getWarehouse&corpid='+$("select#corporate").val()
                    });     
                    
                    
                    $('#corporate').change(function(event) {
                        var corporate=$("select#corporate").val();
                        
                        $.ajax({
                        dataType: 'json',
                        success: function(data) {
                                $('#warehouse').find('option').remove();
                                $.each(data.wh, function(k,v){                                                         
                                    $('#warehouse').append('<option value="'+v.whCode+'">'+v.whCode+'</option>');
                                });
                            },
                            url: 'index.htm?action=getWarehouse&corpid='+corporate
                        });                                                		        
                    });
                });          
		</script>
    </head>
    <%--
        Document   : UserAdd
    --%>

    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
      		com.app.wms.engine.db.dto.User dto = (com.app.wms.engine.db.dto.User) m.get("dto");
            List<Corporate> corporates =  (List<Corporate>)m.get("corporates");
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
                        <input type="hidden" name="isActive" value="Y"/>
                        <input type="hidden" name="userId" value="-1"/>

                        <table class="collapse tblForm row-select">
                            <caption>User - Detail</caption>
                            <tbody class="tbl-nohover">
                            <%-- 
                            	<tr>
                                 	<td class="style1">&nbsp;</td>
                                    <td class="style1">Corporate Id</td>
                                    <td class="style1">
                                        <select name="corpId" id="corporate">
                                            <c:forEach items="${model.corporates}" var="corporate">
                                                <option value="<c:out value="${corporate.id}" />">
                                                    <c:out value="${corporate.id}" />
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                            
                            	<tr>
                            	 <td class="style1">&nbsp;</td>
                                 <td class="style1">Warehouse Id</td>
								    <td class="style1">
								    		
								    
	                                        <select name="whCode" id="warehouse">
	                                        --%>
	                                        	<%--
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
                                            <input type="text" class="shorttext" name="username" value="<%= dto.getUsername()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">&nbsp;</td>
                                    <td class="style1">Password</td>
                                    <td class="style1">
                                        <label>
                                            <input type="password" class="shorttext" name="password" value="<%= dto.getPassword()%>" size="50" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">&nbsp;</td>
                                    <td class="style1">Password Verification</td>
                                    <td class="style1">
                                        <label>
                                            <input type="password" class="shorttext" name="passwordVer" size="50" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
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
                                    <td  class="style1">Email</td>
                                    <td width="75%" class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="email" value="<%= dto.getEmail()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
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