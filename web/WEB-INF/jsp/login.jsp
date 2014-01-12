<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3c.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page">
<html >
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <title>.: IMS - Inventory Management System :.</title>
            <%@include file="metaheaderz.jsp" %>
            
             <script type="text/javascript">
		    	function formfocus() {
				    document.getElementById('autofocus').focus();
				}
				window.onload = formfocus;
			</script>
        </head>
        <body id="body">
        	<div class="box">
        	<div class="box">
        	<div class="box">
            <div class="container">
            <div id="content" style="display: none" class="span-17 last">
            <div class="box">
                     	<fieldset class="ui-widget-content ui-corner-all span-10">
                        <table  style="height: 170px;"> 
                             <tr>
                                 <td>
                                 <div id="admin">
                                 <form id="frmlogin" action="index.htm" method="post" autocomplete="off">
                                 <input type="hidden" name="action" value="login"/>
                                 <table>
                                 	<tr><td class="center"><img src="resources/img/logo.png" alt="SPFI"/></td></tr>
                                 	<tr>
                                 		<td class="center">
                                 			<font size="5" color="#0d236b">&nbsp;&nbsp;&nbsp;&nbsp;Inventory Management System</font>
                                 		</td>
                                 	</tr>

                                    <tr>
                                        <td><input id="autofocus" type="text" name="username" placeholder="Username" maxlength="50"/></td>
                                    </tr>
                                    <tr>
                                        <td><input type="password" name="password" placeholder="Password" maxlength="50"/></td>
                                    </tr>
                                    <tr>
                                        <td class="center"><input type="submit" value="Sign In" class="button"/></td>
                                    </tr>
                       			</table>
                       			<table><tr><td><marquee><font color="#0d236b">Please check your data before using iMS - .:Inventory Management System:. transaction &copy; 2013 PT.Sinar Pure Foods International Jl. Raya Madidir, Bitung 95517, Sulawesi Utara, Telp:+62 438 31235, Fax:+62 438 21808, Web: http://www.ptsinarpurefoods.com, Email: info@sinarpurefoods.com</font></marquee></td></tr></table>
							                <% 
							                       String errorMsg = (String) ((java.util.HashMap) request.getAttribute("model")).get("msg");
							                       if (errorMsg != null) {
											%>
													<div id="dialog" title="Info" style="display:none">
													    <ul>
													        <%
													        	String[] listErrorMsg = errorMsg.split(com.app.wms.web.util.AppConstant.EOL);
							                                    for (int i = 0; i < listErrorMsg.length; i++) 
							                                    {
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
											%>
		            			</form>
		            			</div>
                        </td>
                        </tr>
                        </table>
                 		</fieldset>
            </div>
            </div>
        	</div>
			</div>
			</div>
			</div>
        </body>
</html>