
<%@page import="com.app.wms.engine.db.dto.Distributor"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Distributor </title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%            Distributor dto = (Distributor) request.getAttribute("dto");
            String distributorCode = (String) dto.getDistributorCode();
            String distributorName = (String) dto.getDistributorName();
            String distributorAddress = (String) dto.getDistributorAddress();
            String telephone = (String) dto.getTelephone();
            String fax = (String) dto.getFax();
            String email = (String) dto.getEmail();
            String contactPerson = (String) dto.getContactPerson();
            String id = Integer.toString(dto.getId());
            String isActive = (String) dto.getIsActive();
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Distributor  - Detail</caption>
                        <tbody class="tbl-nohover">
                            <tr>
                                <td class="style1">Distributor Code</td>
                                <td class="style1">
                                    <%=distributorCode%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Distributor Name</td>
                                <td class="style1">
                                    <%=distributorName%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Distributor Address</td>
                                <td class="style1">
                                    <%=distributorAddress%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Distributor Telephone</td>
                                <td class="style1">
                                    <%=telephone%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Distributor Fax</td>
                                <td class="style1">
                                    <%=fax%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Distributor Email</td>
                                <td class="style1">
                                    <%=email%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Contact Person</td>
                                <td class="style1">
                                    <%=contactPerson%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">IsActive</td>
                                <td class="style1">
                                    <%=isActive%>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="2">
                                <form action="Distributor.htm" >
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
                    location.href = 'Distributor.htm';
                });


            });
        </script>
    </body>
</html>