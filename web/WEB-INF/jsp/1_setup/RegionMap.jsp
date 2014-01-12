<%-- 
    Document   : RegionMap
    Created on : Jul 22, 2010, 10:13:55 AM
    Author     : tri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Region Map to Business Group</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">
                <div class="box">
                    <form action="Region.htm" method="post">
                        <input type="hidden" name="action" value="map" />
                        Region: <select name="regionCode" id="regionCode" >
                            <c:forEach items="${model.regions}" var="region">
                                <option value="<c:out value="${region.regionCode}" />"
                                        <c:if test="${model.regionCode==region.regionCode}">selected</c:if>><c:out value="${region.name}" />
                                </option>
                            </c:forEach>
                        </select>
                        <input type="submit" name="btnShow" id="btnShow" value="Show"/>
                    </form>
                    <c:if test="${model.bgAssigned !=null}">
                        <table class="collapse tblForm row-select">
                            <caption>Business Group On Region</caption>
                            <thead>
                                <tr>
                                    <td class="style1">No</td>
                                    <td width="1%"><input type="checkbox" class="checkAll" /></td>
                                    <td class="style1">Code</td>
                                    <td class="style1">Name</td>
                                </tr>
                            </thead>
                            <form action="Region.htm" >
                                <input type="hidden" name="action" value="map" />
                                <input type="hidden" name="regionCode" value="<c:out value="${model.regionCode}"/>" />
                                <tbody id="main">
                                    <c:set scope="page" var="nomor" value="1" />
                                    <c:forEach items="${model.bgAssigned}" var="bg">
                                        <tr>
                                            <td class="style1" width="1%"><c:out value="${nomor}" /></td>
                                            <td width="1%">
                                                <%--
                                                                                            <c:url value="Region.htm" var="urlRemove">
                                                                                                <c:param name="action" value="map" />
                                                                                                <c:param name="regionCode" value="${model.regionCode}" />
                                                                                                <c:param name="bgCode" value="${bg.bgCode}" />
                                                                                                <c:param name="mode" value="remove" />
                                                                                            </c:url>
                                                                                            <a href="<c:out value="${urlRemove}"/>">
                                                                                                Remove
                                                                                            </a>
                                                --%>
                                                <input type="checkbox" class="cbo" name="bgCode" value="<c:out value="${bg.bgCode}"/>"/>
                                            </td>
                                            <td class="style1"><c:out value="${bg.bgCode}" /></td>
                                            <td class="style1"><c:out value="${bg.name}" /></td>
                                        </tr>
                                        <c:set scope="page" var="nomor" value="${nomor+1}" />
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <td colspan="4">
                                        <span class="style1">
                                            <label>
                                                <input type="submit" name="btnAction" value="Delete" />
                                            </label>
                                        </span>
                                    </td>
                                </tfoot>
                            </form>
                        </table>
                    </c:if>
                    <c:if test="${model.bgUnassigned !=null}">
                        <table class="collapse tblForm row-select">
                            <caption>Business Group With No Region</caption>
                            <thead>
                                <tr>
                                    <td class="style1">No</td>
                                    <td width="1%"><input type="checkbox" class="checkAll2" /></td>
                                    <td class="style1">Code</td>
                                    <td class="style1">Name</td>
                                </tr>
                            </thead>
                            <form action="Region.htm">
                                <input type="hidden" name="action" value="map" />
                                <input type="hidden" name="regionCode" value="<c:out value="${model.regionCode}"/>" />
                                <tbody id="main">
                                    <c:set var="nomor" value="1" />
                                    <c:forEach items="${model.bgUnassigned}" var="bg">
                                        <tr>
                                            <td class="style1" width="1%"><c:out value="${nomor}" /></td>
                                            <td width="1%">
                                                <%--
                                                                                                <c:url value="Region.htm" var="urlAssign">
                                                                                                    <c:param name="action" value="map" />
                                                                                                    <c:param name="regionCode" value="${model.regionCode}" />
                                                                                                    <c:param name="bgCode" value="${bg.bgCode}" />
                                                                                                    <c:param name="mode" value="assign" />
                                                                                                </c:url>
                                                                                                <a href="<c:out value="${urlAssign}"/>">
                                                                                                    Assign
                                                                                                </a>
                                                --%>
                                                <input type="checkbox" class="cbo2" name="bgCode" value="<c:out value="${bg.bgCode}"/>"/>
                                            </td>
                                            <td class="style1"><c:out value="${bg.bgCode}" /></td>
                                            <td class="style1"><c:out value="${bg.name}" /></td>
                                        </tr>
                                        <c:set scope="page" var="nomor" value="${nomor+1}" />
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <td colspan="4">
                                        <span class="style1">
                                            <label>
                                                <input type="submit" name="btnAction" value="Add" />
                                            </label>
                                        </span>
                                    </td>
                                </tfoot>
                            </form>
                        </table>
                    </c:if>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
    </body>
</html>
