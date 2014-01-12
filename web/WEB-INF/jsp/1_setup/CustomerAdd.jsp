<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Branch</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <%--
        Document   : CustomerEdit
        Created on : Jun 16, 2010, 8:03:00 PM
        Author     : ruli
    --%>

    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        		    com.app.wms.engine.db.dto.Customer dto = (com.app.wms.engine.db.dto.Customer) m.get("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">

                <div class="box">
                    <form action="Customer.htm" >
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <input type="hidden" name="isDefault" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Customer - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Code</td>
                                    <td class="style1">
                                        <input type="hidden"  name="customerId" value="-1" size="30"/>
                                        <label>
                                            <input type="text" class="shorttext" name="code" value="" size="30"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Salutation</td>
                                    <td class="style1">
                                        <select name="salutation">
                                            <option value="Mr">Mr.</option>
                                            <option value="Miss">Miss.</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="name" value="" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Name Tax</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="nameTax" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Address</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="address" value="" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Npwp</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="npwp" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Pkp Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="pkpNumber" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Pkp Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="datetime" name="pkpDate" size="30" id="pkpDate" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Phone</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="phone" value="" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>


                                <tr>
                                    <td class="style1">Email</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="email" value="" size="30" />
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
                    location.href = 'Customer.htm';
                });


            });
        </script>
    </body>

</html>