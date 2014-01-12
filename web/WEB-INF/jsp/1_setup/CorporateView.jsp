<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Corporate</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>

        <%
        	com.app.wms.engine.db.dto.Corp dto = (com.app.wms.engine.db.dto.Corp) request.getAttribute("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">


                    <table class="collapse tblForm row-select">
                        <caption>Corporate - Detail</caption>
                        <tbody class="tbl-nohover">
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td width="20%" class="style1">Corporate Id</td>
                                <td width="80%" class="style1">
                                    <%= dto.getCorpId()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Corporate Name</td>
                                <td class="style1">
                                    <%= dto.getCorpName()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Address1</td>
                                <td class="style1">
                                    <%= dto.getAddress1()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Address2</td>
                                <td class="style1">
                                    <%= dto.getAddress2()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Address3</td>
                                <td class="style1">
                                    <%= dto.getAddress3()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Email</td>
                                <td class="style1">
                                    <%= dto.getEmail()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">City Code</td>
                                <td class="style1">
                                    <%= dto.getCityCode()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Province</td>
                                <td class="style1">
                                    <%= dto.getProvinceCode()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Zip Code</td>
                                <td class="style1">
                                    <%= dto.getZipcode()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Phone1</td>
                                <td class="style1">
                                    <%= dto.getPhone1()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Phone2</td>
                                <td class="style1">
                                    <%= dto.getPhone2()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Fax</td>
                                <td class="style1">
                                    <%= dto.getFax()%>
                                </td>
                            </tr>
                            
                            <tr>
                            	<td class="style1">&nbsp;</td>
                                <td class="style1">Is Active</td>
                                <td class="style1">
                                    <%=dto.getIsActive()%>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="2">
                                <form action="Corporate.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="corpId" value="<%= dto.getCorpId()%>" />
                                    <%-- <input type="submit" value="Edit"/>--%>
                                    <input type="button" class="style1" name="button" id="btnBack" value="Back" />
                                </form>

                            </td>
                            <td></td>
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
                    location.href = 'Corporate.htm';
                });


            });
        </script>
    </body>
</html>