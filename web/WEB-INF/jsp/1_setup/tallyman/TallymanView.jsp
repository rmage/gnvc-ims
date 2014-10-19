<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Tallyman</title>
        <%@include file="../../metaheader.jsp" %>
    </head>
    <body>
        <%
        Tallyman dto = (Tallyman) request.getAttribute("dto");              	
        %>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <!--div class="box">Search Branch</div -->

                <div class="box">


                    <table class="collapse tblForm row-select">
                        <caption>Tallyman - Detail</caption>
                        <tbody class="tbl-nohover">
                            <%--<input type="hidden" name="userId" value="<%= dto.getUserId()%>"/>--%>
                            <tr>
                                <td class="style1">Code</td>
                                <td class="style1">
                                    <%= dto.getCode()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Name</td>
                                <td class="style1">
                                    <%= dto.getName()%>
                                </td>
                            </tr>
                           
                            <tr>
                                <td class="style1">Job Function</td>
                                <td class="style1">
                                    <%= dto.getJobfunction()%>
                                </td>
                            </tr>
                           
                            
                        </tbody>
                        <tfoot>
                            <td colspan="2">
                                <form action="Tallyman.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="code" value="<%= dto.getCode()%>" />
                                   <%--  <input type="submit" value="Edit"/>--%>
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
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Tallyman.htm';
                });


            });
        </script>
    </body>
</html>