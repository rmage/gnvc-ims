<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update Tallyman</title>
        <%@include file="../../metaheader.jsp" %>
    </head>
 
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        	Tallyman dto = (Tallyman) m.get("dto");
        	String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">

                <div class="box">
                    <form action="Tallyman.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="id" value="<%= dto.getId()%>"/>

                        <table class="collapse tblForm row-select">
                            <caption>Tallyman - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="code" value="<%=dto.getCode()!=null? dto.getCode(): "" %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="name" value="<%=dto.getName()!=null? dto.getName(): "" %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                               
                                <tr>
                                    <td class="style1">Job Function</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="jobfunction" value="<%=dto.getJobfunction()!=null? dto.getJobfunction(): "" %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                               
                                <tr>
                                    <td class="style1">Is Active</td>
                                    <td class="style1">
                                        <label>
                                            <input type="radio" name="isActive" value="Y" <% if (dto.getIsActive().equals("Y")) {%> checked="checked" <% }%> /> Y
					</label>
					<label>
					    <input type="radio" name="isActive" value="N" <% if (dto.getIsActive().equals("N")) {%> checked="checked" <% }%> /> N
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
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Tallyman.htm';
                    return false;
                });


            });
        </script>
    </body>








</html>