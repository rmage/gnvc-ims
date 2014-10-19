<%@page import="com.app.wms.engine.db.dto.Warehouse"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update Warehouse</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            Warehouse dto = (Warehouse) m.get("dto");
     		String mode = (String) m.get("mode");
     		String whCode = (String) dto.getWhCode();
     		String whName = (String) dto.getWhName();
     		String productCategory = (String)dto.getCategoryName();
     		String id = Integer.toString(dto.getId());
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Wh.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="id" value="<%=id%>"/>

                        <table class="collapse tblForm row-select">
                            <caption>Warehouse - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Warehouse Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="code" value="<%=whCode%>" maxlength="10" size="12" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Warehouse Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="name"  value="<%=whName%>" maxlength="25" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Product Category</td>
                                     <td class="style1">
									 <label>
	                                        <select name="categoryName">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListCategory}">
	                                        		  <option value="${droplist.categoryName}" ${(droplist.categoryName eq requestScope.model.categoryName)? "selected": ""}>
	                                        		  	${droplist.categoryName}
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                   </label>
                                   </td>
                                </tr>
                                <tr>
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
                                <td colspan="2">
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
                    location.href = 'Wh.htm';
                    return false;
                });


            });
        </script>
    </body>

</html>