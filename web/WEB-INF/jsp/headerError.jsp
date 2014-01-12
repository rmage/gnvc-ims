<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="span-24 last border-bottom">
    <div class="box-header">
        <table>
            <tr>
                <td rowspan="2" class="center">
                    <a href="#"><img src="resources/img/logo.png" alt="SPFI Group"/></a>
                </td>
                <td>

                    <h2>Inventory Management System SPFI</h2>

                </td>
            </tr>
            <tr>
            </tr>
        </table>
    </div>
</div>


<%
            if (((java.util.HashMap) request.getAttribute("model")) != null) {
                String errorMsg = (String) ((java.util.HashMap) request.getAttribute("model")).get("msg");
                if (errorMsg != null) {
%>
<div id="dialog" title="Info" style="display:none">
    <ul>
        <%
        	String[] listErrorMsg = errorMsg.split(com.app.wms.web.util.AppConstant.EOL);
                                    for (int i = 0; i < listErrorMsg.length; i++) {
                                        String msg = listErrorMsg[i];
        %>
        <li><%=msg%></li>
        <%
                            }
        %>
    </ul>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#dialog").dialog();
        });
    </script>
</div>
<%
                }
            }
%>