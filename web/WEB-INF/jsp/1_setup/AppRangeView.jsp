<%@page import="com.app.wms.engine.db.dto.ApprovalRange"%>
<%@page import="java.math.BigDecimal"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Approval Range </title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
            ApprovalRange dto = (ApprovalRange) request.getAttribute("dto");
            String roleCode = dto.getRoleCode();
            String userName = dto.getUsername();
            BigDecimal fromAmount = dto.getFromAmount();
            BigDecimal toAmount = dto.getToAmount();
            String isActive = dto.getIsActive();
		 		
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Approval Range  - Detail</caption>
                        <tbody class="tbl-nohover">
                        	<tr>
                                <td class="style1">Role Code</td>
                                <td class="style1">
                                	<%=roleCode %>
                                </td>
                            </tr>
                                <!--<tr>
                                <td class="style1">User Name</td>
                                <td class="style1">
                                	<%=userName %>
                                </td>
                            </tr>-->
                            
                            <tr>
                                <td class="style1">From Amount</td>
                                <td class="style1">
                                	<%=fromAmount %>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">To Amount</td>
                                <td class="style1">
                                	<%=toAmount %>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">IsActive</td>
                                <td class="style1">
                                	<%=isActive %>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="2">
                                <form action="ApprovalRange.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="id" value="<%= dto.getId()%>" />
                                    <input type="submit" value="Edit"/>
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
                    location.href = 'ApprovalRange.htm';
                });


            });
        </script>
    </body>
</html>