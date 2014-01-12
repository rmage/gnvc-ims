<%@page import="com.app.wms.engine.db.dto.Canvasserassignment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update Canvasser Assignment</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            Canvasserassignment dto = (Canvasserassignment) m.get("dto");
     		String mode = (String) m.get("mode");
     		String prsnumber = (String) dto.getPrsnumber();
     		String canvassername = (String) dto.getCanvassername();
     		String id = Integer.toString(dto.getId());
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />


            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="CanvassingAssignment.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="id" value="<%=id%>"/>

                        <table class="collapse tblForm row-select">
                            <caption>Canvasser - Assignment</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Prs No</td>
                                    <td class="style1">
                                        <label>
	                                        <select id="prsnumber" name="prsnumber">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListPrs}">
	                                        		  <option value="<c:out value='${droplist.prsnumber}'/>" 
	                                        		   <c:if test="${param.selecValue == item }"> selected</c:if> >
	                                        		  	<c:out value="${droplist.prsnumber} - ${droplist.prsdate}" />
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Canvasser Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="canvassername"  value="<%=canvassername%>" maxlength="25" size="30" />
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
                    location.href = 'CanvassingAssignment.htm';
                    return false;
                });


            });
        </script>
    </body>
</html>