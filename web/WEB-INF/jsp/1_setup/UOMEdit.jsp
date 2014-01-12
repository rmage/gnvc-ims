<%@page import="com.app.wms.engine.db.dto.Uom"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update UoM</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            Uom dto = (Uom) m.get("dto");
            String mode = (String) m.get("mode");
            String id = Integer.toString(dto.getId());
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Uom.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="id" value="<%=id%>"/>

                        <table class="collapse tblForm row-select">
                            <caption>UOM - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">UoM Code</td>
                                    <td class="style1">
                                       <label>
                                            <input type="text" class="shorttext" name="uomCode" value="<%= dto.getUomCode()%>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">UoM Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="uomName" value="<%= dto.getUomName()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Remarks</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="remarks" value="<%= dto.getRemarks()%>" size="30" />
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
                    location.href = 'Uom.htm';
                    return false;
                });


            });
        </script>
    </body>






</html>