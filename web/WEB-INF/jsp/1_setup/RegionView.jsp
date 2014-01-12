<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Region</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        	com.app.wms.engine.db.dto.Region dto = (com.app.wms.engine.db.dto.Region) request.getAttribute("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">
                <!--div class="box">Search Branch</div -->

                <div class="box">


                    <table class="collapse tblForm row-select">
                        <caption>Region - Detail</caption>
                        <tbody class="tbl-nohover">
                            <tr>
                                <td class="style1">Region Code</td>
                                <td class="style1">
                                    <%= dto.getRegionCode()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Name</td>
                                <td class="style1">
                                    <%= dto.getName()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Is Active</td>
                                <td class="style1">
                                    <%= dto.getIsActive()%>
                                </td>
                            </tr>
                            <%--
                                                    <tr>
                                                        <td class="style1">Created By</td>
                                                        <td class="style1">
                                                            <%= dto.getCreatedBy() %>
                                                        </td>
                                                    </tr>

                         <tr>
                            <td class="style1">Created Date</td>
                            <td class="style1">
                                <%= dto.getCreatedDate() %>
                            </td>
                        </tr>

                         <tr>
                            <td class="style1">Updated By</td>
                            <td class="style1">
                                <%= dto.getUpdatedBy() %>
                            </td>
                        </tr>

                         <tr>
                            <td class="style1">Updated Date</td>
                            <td class="style1">
                                <%= dto.getUpdatedDate() %>
                            </td>
                        </tr>
                            --%>
                        </tbody>
                        <tfoot>
                            <td colspan="2">
                                <form action="Region.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="regionCode" value="<%= dto.getRegionCode()%>" />
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
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Region.htm';
                });


            });
        </script>
    </body>
</html>