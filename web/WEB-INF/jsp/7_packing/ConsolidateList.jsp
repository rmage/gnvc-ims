<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Consolidate List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
        	//java.util.HashMap map = (java.util.HashMap) request.getAttribute("model");
        	com.app.web.engine.search.ConsolidateSearch criteria = new com.app.web.engine.search.ConsolidateSearch(); 
        
   		    if (request.getSession().getAttribute("ConsolidateSearch") != null) {
   			criteria = (com.app.web.engine.search.ConsolidateSearch) request.getSession().getAttribute("ConsolidateSearch");
   		    }
        
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Consolidate.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search - Consolidate</caption>
                            <tbody>
                                <tr>
                                   <td width="2%"></td>
                                    <td width="10%">
                                        Consolidate No
                                    </td>
                                    <td>
                                        <input size="30" type="text" name="consolidateNo" value=""/>
                                    </td>
                                   <td width="10%">
                                        Consolidate Date
                                    </td>
                                    <td width="50%" >
                                        <input size="30" type="text" name="consolidateDate" value="" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                    </span>
                                    <span class="style1">
                                    <label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    </label>

                                </span>
                                </td>
                                <td></td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Consolidate - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Consolidate No</td>
                                <td class="style1">Consolidate Date</td>                                
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.consolidate!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.consolidate}" var="cons">
                                    <tr>
                                        <td width="1%" class="center" >
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td width="5%" class="style1">
                                            <c:url  value="Consolidate.htm"  var="urlPrint">
                                                <c:param name="consolidateNo" value="${cons.consolidateNo}"/>
                                                <c:param name="templateName" value="rptConsolidateList"/>
                                                <c:param name="parametersKey" value="P_DOC_NO"/>
                                                <c:param name="action" value="doPrint" />
                                            </c:url>
                                            <a href='<c:out value="${urlPrint}"/>'>
                                                <img src="resources/images/print.jpg" width="16" height="16" alt="Print" />
                                            </a>
                                            
                                        </td>
                                        <td class="style1"><a href="#" class="no-decoration" onclick="csbShowDetail('CONS', '<c:out value="${cons.consolidateNo}"></c:out>', 'Consolidate List Detail')"><c:out value="${cons.consolidateNo}" /></a></td>
                                        <td class="style1"><c:out value="${cons.consolidateDate}"/></td> 
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="5">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Consolidate.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="Consolidate.htm?page=<c:out value="${model.page+1}" />">
												&gt;
										    </a>
										</c:if>
				    				</span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="10">
                               
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
                    location.href = 'Consolidate.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
              
            });
        </script>
      
    </body>
</html>