<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Export Delivery Slip (EDS) List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Eds.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search EDS</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        EDS No
                                    </td>
                                    <td>
                                        <input type="text" name="edsnumber" value=""/>
                                    </td>
                                    <td width="20%">
                                        EDS Date
                                    </td>
                                    <td>
                                        <input type="text" name="edsdate" value="${model.edsdate}" id="edsdate" />
                                    </td>
                                    
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="6">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                    	<label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    	</label>
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>EDS - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">EDS No</td>
                                <td class="style1">EDS Date</td>  
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.EDSlip!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.EDSlip}" var="eds">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                                <c:url  value="Eds.htm"  var="urlPrint">
                                                    <c:param name="edsNo" value="${eds.edsnumber}"/>
                                                    <c:param name="templateName" value="rptStoresWithdrawalList"/>
                                                    <c:param name="parametersKey" value="P_DOC_NO"/>
                                                    <c:param name="action" value="doPrint" />
                                                </c:url>
                                                <a href='<c:out value="${urlPrint}"/>'>
                                                        <img src="resources/images/print.jpg" width="16" height="16" alt="View" />
                                                </a>
                                        </td>
                                        <td class="style1">
                                            <a href="#" class="no-decoration" onclick="csbShowDetail('EDS', '<c:out value="${eds.edsnumber}"></c:out>', 'EDS Detail')">
                                                <c:out value="${eds.edsnumber}"/>
                                            </a>
                                        </td>
                                        <td class="style1"><fmt:formatDate pattern="dd-MM-yyyy" value="${eds.edsdate}" /></td> 
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="5">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Eds.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="Eds.htm?page=<c:out value="${model.page+1}" />">
												&gt;
										    </a>
										</c:if>
				    				</span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="10">
                                <span class="style1">
                                </span>
                            </td>
                        </tfoot>
                    </table>

                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
                                                                                
       

        <script type="text/javascript">
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'Eds.htm?action=create';
                });

                $('#edsdate').datepicker({                        
                    dateFormat: "dd/mm/yy"                        
                });
              
            });
        </script>
      
    </body>
</html>