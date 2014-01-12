<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>.: IMS - Inventory Management System :.</title>
        <%@include file="metaheader.jsp" %>
    <%
    	if( request.getSession().getAttribute("user") != null) {        
	%>
        <script type="text/javascript">    
        $.pnotify.defaults.styling = "jqueryui";
        $.pnotify.defaults.history = false;
        
        
        $.ajax({
            dataType: 'json',
            success: function(data) {
                $.each(data.alerts, function(k,v) {  
                    if((/^Product/).test(v.title)) {
                        $.pnotify({
                            title: ''+v.title,
                            text: ''+v.text,  
                            sticker: false,
                            animation: "slide",
                            hide: false
                        });  
                    } else {
                        $.pnotify({
                            title: ''+v.title,
                            text: ''+v.text,                            
                            type: 'error',   
                            sticker: false,
                            animation: "slide",
                            hide: false
                        }); 
                    }



                });
            },
            url: 'Alert.htm'
        });                
        </script>
	<%
	   }
	%>
    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp" %>
            <jsp:include page="dynmenu.jsp" />
            <div class="box">
            <div>
                 <div class="box-header">
				        <table>
				            <tr>
				                <td rowspan="1" class="center">
				                    <a href="#"><img src="resources/img/main-ims.jpg" alt="SPFI"/></a>
				                </td>
				            </tr>
				        </table>
    			</div>
    			
            </div>

            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        </div>
    </body>
</html>
