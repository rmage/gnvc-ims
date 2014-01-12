<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	java.util.List<com.app.wms.engine.db.dto.AppMenu> loginMenus =
		    (java.util.List<com.app.wms.engine.db.dto.AppMenu>) request.getSession().getAttribute("menu");
	    java.util.List<com.app.wms.engine.db.dto.AppMenuGroup> loginMenuGroups =
		    (java.util.List<com.app.wms.engine.db.dto.AppMenuGroup>) request.getSession().getAttribute("menuGroup");
%>

<link rel="stylesheet" type="text/css" href="resources/jcss/jquerycssmenu.css" />
<script type="text/javascript" src="resources/jquery/jquerycssmenu.js"></script>

<div id="myjquerymenu" class="jquerycssmenu">
    <ul>
    
       <li><a href="#">Home</a>
        
            <ul>
                <li><a href="index.htm">IMS - SPFI</a></li>
                <li><a href="ChangePwd.htm">Change Password</a></li>
                <li><a href="index.htm?action=logout">Logout</a></li>
            </ul>
      </li>
		
        <%
        	if (loginMenuGroups != null) {
        	java.util.Iterator itg = loginMenuGroups.iterator();
        	while (itg.hasNext()) {
        	    com.app.wms.engine.db.dto.AppMenuGroup mg = (com.app.wms.engine.db.dto.AppMenuGroup) itg.next();
        %>
        <li><a href="#"><%=mg.getName()%></a>

     
            <ul>
                <%
                	java.util.Iterator it = loginMenus.iterator();
                                			    while (it.hasNext()) {
                                				com.app.wms.engine.db.dto.AppMenu m = (com.app.wms.engine.db.dto.AppMenu) it.next();
                                				if (m.getGroupCode().equalsIgnoreCase(mg.getGroupCode())) {
                                				   
                %>
                <li><a href="<%= m.getUrl()%>"><%= m.getName()%></a></li>
                <%
						} // end of checking apakah item ini dalam group yang betul
					    } // end of looping menu item dalam sebuah menu group

                %>
            </ul>
       </li>
        <%
				}
			    } else {
        %>
	<script type="text/javascript">
	    $(function() {
		location.href = 'index.htm?action=logout';
	    });
	</script>
	
        <%		    }
		    /* end of looping menu group*/
        %>

    </ul>
    <br style="clear: left" />
</div>

