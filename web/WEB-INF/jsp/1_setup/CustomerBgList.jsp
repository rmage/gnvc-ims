<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Customer Bg List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
	<%
		    java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
		    String bgCode = (String) m.get("bgCode");
	%>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">
                <!--div class="box">Search Customer Bg /div -->
                <div class="box">

		    <table class="collapse tblForm row-select">
			<caption>Business Group</caption>
			<form action="CustomerBg.htm" >
			    <input type="hidden" name="mode" value="list" />
			    <tbody class="tbl-nohover" id="main">
				<tr>
				    <td class="style1">Business Group Name:</td>
				    <td class="style1">
					<select name="bgCode">
					    <c:if test="${model.bgs!=null}">
						<c:forEach items="${model.bgs}" var="bg">
						    <option value=<c:out value="${bg.bgCode}"/>
							    <c:if test="${bg.bgCode==model.bgCode}"> selected </c:if>>
							<c:out value="${bg.name}" /></option>
						    </c:forEach>
						</c:if>
					</select>
				    </td>
				</tr>
				<tr>
				    <td colspan="2">
					<span class="style1">
					    <label>
						<input type="submit" name="btnSearch" value="Search" />
					    </label>
					</span>
				    </td>
				</tr>
			    </tbody>
			</form>
		    </table>
		    <table class="collapse tblForm row-select">
                        <caption>Customer Bg - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td><input type="checkbox" class="checkAll" /></td>
                                <!--td class="style1">Action</td-->
				<td class="style1">BG Code</td>
				<td class="style1">Code</td>
				<td class="style1">Name</td>
                            </tr>
                        </thead>
                        <form action="CustomerBg.htm" >
			    <input type="hidden" name="action" value="save" />
			    <input type="hidden" name="bgCode" value="<%=bgCode%>"/>
			    <tbody id="main">
				<c:if test="${model.customerBgs!=null}">
				    <% int rowNumber = 1;%>
				    <c:forEach items="${model.customerBgs}" var="customerBg">
					<tr>
					    <td class="center"><%=rowNumber++%></td>
					    <td align="center"><input type="checkbox" class="cbo"  name="customerId" value="<c:out value="${customerBg.customerId}"/>"/></td>
					    <!--td class="center">
					    <c:url
						value="CustomerBg.htm" var="urlDelete">
						<c:param name="customerId" value="${customerBg.customerId}"/>
						<c:param name="bgCode" value="${customerBg.bgCode}"/>
						<c:param name="action" value="delete" />
					    </c:url>
						<a href='<c:out value="${urlDelete}"/>'>
						    <img src="resources/images/delete.gif" width="16" height="16" alt="icon delete" />
						</a>
					    </td-->
					    <td class="style1"><c:out value="${customerBg.bgCode}"/></td>
					    <td class="style1"><c:out value="${customerBg.code}"/></td>
					    <td class="style1"><c:out value="${customerBg.name}"/></td>
					</tr>
				    </c:forEach>
				</c:if>
			    </tbody>
			    <tfoot>
				<td colspan="5">
				    <span class="style1">
					<label>
					    <!--input type="submit" name="btnAction" value="Change Status" /-->
					</label>
					<input type="submit" name="btnAction" value="Delete" />
				    </span>
				</td>
			    </tfoot>
			</form>
		    </table>

		    <table class="collapse tblForm row-select">
                        <caption>Customer - List</caption>
                        <thead>
			    <form action="CustomerBg.htm" >
				<input type="hidden" name="mode" value="list" />
				<input type="hidden" name="bgCode" value="<%=bgCode%>"/>
				<tr>
				    <td colspan="5">
					<span class="style1">
					    <label>
						<input type="submit" name="btnSearch" value="Search" />
					    </label>
					</span>
				    </td>
				</tr>
			    </form>
			    <tr>
				<td class="style1">No</td>
				<td><input type="checkbox" class="checkAll2" /></td>
				<td class="style1">Code</td>
				<td class="style1">Name</td>
				<td class="style1">Active</td>
			    </tr>
                        </thead>
			<form action="CustomerBg.htm">
			    <input type="hidden" name="action" value="save" />
			    <input type="hidden" name="bgCode" value="<%=bgCode%>"/>
			    <tbody id="main">
				<c:if test="${model.customers!=null}">
				    <% int rowP = 1;%>
				    <c:forEach items="${model.customers}" var="customer">
					<tr class="ganjil">
					    <td class="center"><%= rowP++%></td>
					    <td class="mid"><input type="checkbox" class="cbo2"  name="customerId" value="<c:out value="${customer.customerId}"/>"/></td>
					    <td class="style1"><c:out value="${customer.code}"/></td>
					    <td class="style1"><c:out value="${customer.name}"/></td>
					    <td class="style1"><c:out value="${customer.isActive}"/></td>
					</tr>
				    </c:forEach>
				</c:if>
			    </tbody>
			    <tfoot>
				<td colspan="10">
				    <span class="style1">
					<label>
					    <input type="submit" name="btnAction" value="Add" />
					</label>
				    </span>
				</td>
			    </tfoot>
			</form>
		    </table>
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
                $('#btnAdd').click(function() {
                    //location.href = 'CustomerBg.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
                // adjust width for tables //
                $('.tblForm tbody').each(function() {
                    if($(this).attr('id') != 'main') {
                        $(this).find('td:eq(0)').css('width', '1%').next().addClass('span-5');
                        $(this).find('.checkbox').addClass('span-2');
                    }
                });
            });
        </script>
        <!-- ini end if script untuk button -->

        <!-- smp sini di cut -->
    </body>
</html>