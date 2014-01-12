<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update Corporate</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
    	java.util.List<com.app.wms.engine.db.dto.Wh> dropListWarehouse = (java.util.List<com.app.wms.engine.db.dto.Wh>) m.get("dropListWarehouse");
        com.app.wms.engine.db.dto.Corp dto = (com.app.wms.engine.db.dto.Corp) m.get("dto");
        String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Corporate.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="corpId" value="<%= dto.getCorpId()%>"/>

                      <table class="collapse tblForm row-select">
                            <caption>Corporate - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                	<td>&nbsp;</td>
                                    <td class="style1">Corporate Id</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="corpId" value="<%= dto.getCorpId()%>" size="30" readonly/>
                                        </label>
                                       
                                    </td>
                                </tr>
                                <tr>
                                	<td>&nbsp;</td>
                                    <td class="style1">Corporate Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="name" value="<%= dto.getCorpName()%>" maxlength="45" size="50" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                
                                 <tr>
                                 	<td>&nbsp;</td>
                                    <td class="style1">Address1</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="address1" value="<%= dto.getAddress1() %>" size="50" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                
                                 <tr>
                                 	<td>&nbsp;</td>
                                    <td class="style1">Address2</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="address2" value="<%= dto.getAddress2()%>" size="50" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                
                                <tr>
                                	<td>&nbsp;</td>
                                    <td class="style1">Address3</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="address3" value="<%= dto.getAddress3()%>" size="50" />
                                        </label>
                                    </td>
                                </tr>
                                
                                <tr>
                                	<td>&nbsp;</td>
                                    <td class="style1">E-Mail</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="email" value="<%= dto.getEmail()%>" size="30" />
                                        </label>
                                         <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                
                                <tr>
                                	<td>&nbsp;</td>
                                 	<td class="style1">City Code</td>
								    <td class="style1">
                                    	<label>
	                                        <select name="city">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListCity}">
	                                        		  <option value="${droplist.cityCode}" ${(droplist.cityCode eq requestScope.model.cityCode)? "selected": ""}>
	                                        		  	${droplist.cityCode} - ${droplist.name}
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                            		</td>
                                </tr>
                                
                                <tr>
                                 	<td>&nbsp;</td>
                                 	<td class="style1">Province</td>
								    <td class="style1">
                                    	<label>
	                                        <select name="province">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListProvince}">
	                                        		  <option value="${droplist.provinceCode}" ${(droplist.provinceCode eq requestScope.model.provinceCode)? "selected": ""}>
	                                        		  	${droplist.provinceCode}
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                            		</td>
                                </tr>
                                
                                <tr>
                                	<td>&nbsp;</td>
                                    <td class="style1">Zip Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="zipcode" value="<%= dto.getZipcode()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                 <tr>
                                 	<td>&nbsp;</td>
                                    <td class="style1">Phone1</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="phone1" value="<%= dto.getPhone1()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                 <tr>
                                 	<td>&nbsp;</td>
                                    <td class="style1">Phone2</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="phone2" value="<%= dto.getPhone2()%>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                 <tr>
                                 	<td>&nbsp;</td>
                                    <td class="style1">Fax</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="fax" value="<%= dto.getFax()%>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                
                                 <tr>
                                 	<td>&nbsp;</td>
                                    <td width="20%" class="style1">Is Active</td>
                                    <td width="80%" class="style1">
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
                                <td colspan="20">
                                    <span class="style1">
                                        <label>
                                            <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                        </label>
                                        <input type="button" class="style1" name="button" id="btnBack" value="Back" />
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
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Corporate.htm';
                });

               
            });
        </script>
    </body>
</html>