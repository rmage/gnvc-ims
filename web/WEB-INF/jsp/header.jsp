<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--<head>
    <script language="javascript" src="resources/jquery/liveclock.js"></script>
</head>-->

<%
    com.app.wms.engine.db.dto.map.LoginUser lu = (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
    java.lang.String urlDB = (java.lang.String) request.getSession().getAttribute("urlDB");
    java.lang.String serverIP = request.getLocalAddr();
    java.lang.String clientIP = request.getRemoteAddr();
    java.lang.String namaApp = "<b>SPFI</b>";
    java.lang.String releaseNumber = "20121217.0900";
    java.lang.String date = (java.lang.String) request.getSession().getAttribute("date");
    if (serverIP != null && (serverIP.equals("192.168.1.1") || serverIP.equals("127.0.0.1"))) {
        namaApp = "<b>Development</b>";
    }
%>

<body>
    <div>
        <div>
            <table>
                <tr>
                    <td rowspan="3" class="left">
                        <a href="#"><img src="resources/img/ims.png" alt="inventory" style="display: block; width: 1285px;" /></a>
                        <br>
                        &nbsp;&nbsp;&nbsp;&nbsp;<font color="#3b5998">  
                            <%if (lu != null) {%>
                            User Name: <%= lu != null ? lu.getUsername() : "-"%>
                            | User Group: <%= lu != null ? lu.getRoleName() : "-"%>
                            | Last Login: <%out.println(date);%>
                            <%} %>
                        </font>
                        <span id="LiveClockIE"> </span>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <%--
    <c:if test="${model.msg !=null}">
        <div id="dialog" title="Info" style="display:none">
            <c:out value="${model.msg}" />
            <script type="text/javascript">
                $(document).ready(function() {
                    $("#dialog").dialog();
                });
            </script>
        </div>
    </c:if>
    --%>
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
</body>