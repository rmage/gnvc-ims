<%@page contentType="application/json; charset=ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
    String json = (String) m.get("data");
%>

<%= json %>