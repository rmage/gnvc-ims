<%@page import="com.app.wms.engine.db.dto.Uom"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Update UoM</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%            
            //java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            //Uom dto = (Uom) m.get("dto");
            //String mode = (String) m.get("mode");
            //String id = Integer.toString(dto.getId());
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Uom.htm" method="post">
                        <input type="hidden" name="mode" value="<%//=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="id" value="${model.mode.id}"/>

                        <table class="collapse tblForm row-select">
                            <caption>UOM - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>UoM Code</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="uomCode" value="${model.mode.uomCode}" size="30" readonly />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>UoM Name</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="uomName" value="${model.mode.uomName}" size="30" pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="remarks" value="${model.mode.remarks}" size="30" />
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                            <td colspan="2">
                                <span>
                                    <label>
                                        <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                    </label>
                                </span>
                                <input type="button" name="button" id="btnBack" value="Back" />
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
