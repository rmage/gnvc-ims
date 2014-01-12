<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3c.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page">
<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>.: Login IMS - Warehouse Management System :.</title>
        <link href="resources/css/style.css" rel="stylesheet" type="text/css" />

        <style type="text/css">
            <!--
            body {
                background-color: #FFFFFF;
            }
            .style2 {color: #FFFFFF}
            .style3 {color: #FFFFFF; font-size: 24px; }
            .style5 {color: #FFFFFF; font-size: 14px; }
            -->
        </style></head>
    <body>
        <form id="frmlogin" action="User.htm" method="post">
            <input type="hidden" name="action" value="login"/>
            <p>&nbsp;</p>
            <p>&nbsp;</p>

            <table width="450px" border="1" align="center" cellpadding="5" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#999999">

                <tr>
                    <td width="200px">
                        <p align="center" class="style2"><br />
                            <img src="resources/images/logo02.gif" width="56" height="71" />
                        </p>
                        <p align="center" class="style2">IMS - Warehouse Management System</p>
                        <p align="center" class="style2">SPFI Group &copy; 2012</p></td>

                    <td width="250px">
                        <br/>
                        <table width="250px" border="0" cellpadding="1" cellspacing="1">
                            <tr>
                                <td colspan="3" class="judul"><p class="style3">Login</p>
                                    <p><br />
                                    </p></td>
                            </tr>
                            <tr>
                                <td width="74"><span class="style5">Warehouse</span></td>
                                <td width="6">&nbsp;</td>
                                <td width="160"><input name="warehouse" type="text" id="textfield" /></td>
                            </tr>
                            <tr>
                                <td><span class="style5">Corporate</span></td>
                                <td>&nbsp;</td>
                                <td><input name="corporate" type="text" id="textfield2" /></td>

                            </tr>
                            <tr>

                                <td width="74"><span class="style5">User Name</span></td>
                                <td width="6">&nbsp;</td>
                                <td width="160"><input name="username" type="text" id="textfield" /></td>
                            </tr>
                            <tr>
                                <td><span class="style5">Password</span></td>
                                <td>&nbsp;</td>
                                <td><input name="password" type="password" id="textfield2" /></td>

                            </tr>

                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td><input type="submit" name="button" id="button" value="Submit"/>
                                    <input type="reset" name="button2" id="button2" value="Reset" /></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>

                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>          </td>
                </tr>
            </table>
            <p>&nbsp;</p>
        </form>
    </body>
</html>
