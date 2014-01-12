<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
            Integer currentPage = null;
            Integer paging = null;
            Integer total = null;
            String action = null;
            String category = null;
            String text = null;
            java.util.HashMap model = (java.util.HashMap) request.getAttribute("modelProductAgentOut");
            if (model != null)
            {
                if (model.get("page") != null)
                {
                    currentPage = (Integer) model.get("page");
                }
                if (model.get("totalCount") != null)
                {
                    total = (Integer) model.get("totalCount");
                }
                if (model.get("paging") != null)
                {
                    paging = (Integer) model.get("paging");
                }
                if (model.get("action") != null)
                {
                    action = (String) model.get("action");
                }
                if (model.get("category") != null)
                {
                    category = (String) model.get("category");
                }
                if (model.get("text") != null)
                {
                    text = (String) model.get("text");
                }
                if (currentPage == null)
                {
                    currentPage = 1;
                }
                if (paging == null)
                {
                    paging = 10;
                }
            }

%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>.: Product List - Agent Out</title>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
            <link href="resources/css/style.css" rel="stylesheet" type="text/css" />

            <script language="Javascript" type="text/javascript">
                function putData(itemCode, itemName) {
                    window.opener.document.getElementById('itemCode').value = itemCode;
                    window.opener.document.getElementById('itemName').value = itemName;
                }
            </script>

    </head>

    <body>
        <div class="menu_popup" style=" background-color:#000000; vertical-align:bottom; height:50px"><br /><br />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Agent Out - Product List</div>
        <table width="100%" border="0" cellspacing="1" cellpadding="1">

            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td width="2%">&nbsp;</td>
                <td width="96%">
                    <table width="100%" cellspacing="0" cellpadding="0" class="tabletr" >
                        <tr><td>
                                <table width="100%" class="view_table" cellspacing="0" cellpadding="0">
                                    <caption>Agent Out - Search Data</caption>

                                    <tr>
                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <form method="get" action="AgentOut.htm">
                                                <select name="select" id="select">
                                                    <%
                                                                if (category != null)
                                                                {
                                                                    if (category.equalsIgnoreCase("code"))
                                                                    {
                                                                        out.write("<option value=\"Code\" selected>Code</option>");
                                                                        out.write("<option value=\"Name\">Name</option>");
                                                                    } else
                                                                    {
                                                                        out.write("<option value=\"Code\">Code</option>");
                                                                        out.write("<option value=\"Name\" selected>Name</option>");
                                                                    }
                                                                } else
                                                                {
                                                                    out.write("<option value=\"Code\">Code</option>");
                                                                    out.write("<option value=\"Name\">Name</option>");
                                                                }
                                                    %>


                                                </select>
                                                <input type="hidden" name="action" value="findByCriteria"/>
                                                <%

                                                            if (text != null)
                                                            {
                                                                out.write("<input type=\"text\" class=\"longtext\" name=\"textfield\" id=\"textfield\" value =\"" + text + "\"/>");
                                                            } else
                                                            {
                                                                out.write("<input type=\"text\" class=\"longtext\" name=\"textfield\" id=\"textfield\" />");
                                                            }

                                                %>
                                                <input type="submit" name="button" id="button" value="Search" />
                                                <input type="submit" name="button2" id="button2" value="View All" />
                                            </form>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </td></tr>
                    </table>    </td>
                <td width="2%">&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><table width="100%" cellpadding="0" cellspacing="0" class="view_table">
                        <caption>
                            Agent Out - Product - Search Result
                        </caption>
                        <tr>
                            <td><table width="100%" cellspacing="0" cellpadding="0">
                                </table></td>
                        </tr>
                        <tr>
                            <td><table width="100%" border="0" cellspacing="1" cellpadding="0">
                                    <tr class="table_header">
                                        <td width="3%">No.</td>
                                        <td width="4%">Action</td>
                                        <td width="6%">Product  Code</td>
                                        <td width="20%">Product Name</td>
                                        <td width="20%">Product Category</td>
                                        <td width="20%">Product Catalog</td>
                                        <td width="14%">Base Price</td>
                                        <td width="18%">Percent Discount</td>
                                        <td width="8%">Value Price</td>
                                    </tr>
                                    <c:if test="${modelProductAgentOut.products!=null}">
                                        <c:forEach items="${modelProductAgentOut.products}" var="prod" varStatus="prodCounter">
                                            <tr class="ganjil">
                                                <td class="number">${prodCounter.count}</td>
                                                <td class="mid">
                                                    <form method="get" action="AgentOut.htm">
                                                        <input type="hidden" name="action" value="addItemAgentOut"/>
                                                        <input type="hidden" name="agentOutItemCode" value="<c:out value="${prod.itemCode}"/>"/>
                                                        <input type="submit" name="button4" id="button4" value="Select" />
                                                    </form>
                                                    <!--
                                                    <c:url value="AgentOut.htm" var="urlSelect">
                                                        <c:param name="agentOutItemCode" value="${prod.itemCode}"/>
                                                        <c:param name="action" value="addItemAgentOut"/>
                                                    </c:url>
                                                    <a href='<c:out value="${urlSelect}"/>'>Select</a>
                                                    -->
                                                </td>
                                                <td><c:out value="${prod.itemCode}"/></td>
                                                <td><c:out value="${prod.name}"/></td>
                                                <td><c:out value="${prod.catalogCode}"/></td>
                                                <td><c:out value="${prod.categoryCode}"/></td>
                                                <td class="number"><c:out value="${prod.basePrice}"/></td>
                                                <td class="number"><c:out value="${prod.discountPercent}"/></td>
                                                <td class="number"><c:out value="${prod.valuePrice}"/></td>
                                            </tr>

                                        </c:forEach>
                                    </c:if>
                                    <tr>
                                        <td colspan="9"><table width="100%" border="0" cellspacing="0" cellpadding="4">
                                                <tbody>
                                                    <tr>
                                                        <td width="81%" style="text-align: left">
                                                            <%
                                                                        int totalPage = total / paging;
                                                                        for (int i = 1; i <= totalPage; i++)
                                                                        {
                                                                            if (i == currentPage)
                                                                            {
                                                                                out.write(String.valueOf(i));
                                                                            } else
                                                                            {
                                                                                String addendum = "";
                                                                                if (category != null)
                                                                                {
                                                                                    addendum += "&select=" + java.net.URLEncoder.encode(category, "UTF-8");

                                                                                }
                                                                                if (text != null)
                                                                                {
                                                                                    addendum += "&textfield=" + java.net.URLEncoder.encode(text, "UTF-8");

                                                                                }

                                                                                out.write("<a href=AgentOut.htm?action=" + action + addendum + "&page=" + i + ">" + i + "</a>");
                                                                            }
                                                                            out.write(" ");
                                                                            if (i % 30 == 0)
                                                                            {
                                                                                out.write("<br/>");
                                                                            }
                                                                        }
                                                                        out.write("<br/>");
                                                            %>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table></td>
                                    </tr>
                                </table></td>
                        </tr>
                    </table></td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>

                <td>
                    <form method="get" action="AgentOut.htm">
                        <input type="hidden" name="action" value="addItemAgentOut"/>
                        <input type="submit" name="button4" id="button4" value="Close" />
                    </form>
                </td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </body>
</html>
