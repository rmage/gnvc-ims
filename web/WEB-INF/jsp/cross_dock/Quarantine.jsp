<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Quarantine List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="GRForm" action="Quarantine.htm">
                        <input type="hidden" name="action" value="list" />
                        <table class="collapse tblForm row-select">
                            <caption>Search Quarantine</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">Quarantine No</td>
                                    <td>
                                        <input type="text" id="qCode" value=""/>
                                    </td>
                                    <td width="20%">Quarantine Date</td>
                                    <td>
                                        <input type="text" id="qDate" value="" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="7">
                                    <span class="style1">
                                        <input class ="style1" type="button" value="Search" id="btnSearch" name="btnSearch" />
                                        <input class ="style1" type="submit" value="View All" id="btnViewAll" />
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Quarantine - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Quarantine No</td>
                                <td class="style1">PO Cross Dock No</td>
                                <td class="style1">Put Away No</td>
                                <td class="style1">Quarantine Date</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.list != null}">
                                <c:set scope="page" value="${((model.page - 1) * 10) + 1}" var="nomor"/>
                                <c:forEach items="${model.list}" var="list">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="7%">
                                            <a href="<c:url value="Quarantine.htm?action=view&qCode=${list.QCode}" />">
                                                <img src="resources/images/detail.png" width="16" />
                                            </a>
                                        </td>
                                        <td class="style1"><c:out value="${list.QCode}"/></td>
                                        <td class="style1"><c:out value="${list.createdBy}"/></td>
                                        <td class="style1"><c:out value="${list.putawayCode}"/></td>
                                        <td class="style1"><fmt:formatDate pattern="dd/MM/yyyy" value="${list.QDate}" /></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="6">
                                    <span class="style1">
                                    <c:if test="${model.page != null && model.page > 1}">
                                        <a href="Quarantine.htm?action=list&page=<c:out value="${model.page - 1}" />">&lt;</a>
                                    </c:if>
                                        
                                    &nbsp;page: <c:out value="${model.page}" />&nbsp;
                                    
                                    <a href="Quarantine.htm?action=list&page=<c:out value="${model.page + 1}" />">&gt;</a>
                                    </span>
                                </td>
                            </tr>
                        </tbody>
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
                    location.href = 'Quarantine.htm?action=create';
                });
                $('#btnSearch').click(function(){
                    var criteria;
                    var flag = true;
                    var qCode = $('#qCode');
                    var qDate = $('#qDate');
                    
                    if(qCode.val() != ""){
                        criteria = "qCode=" + qCode.val();
                    } else if(qDate.val() != ""){
                        criteria = "qDate=" + qDate.val();
                    } else{
                        flag = false;
                    }
                    
                    if(flag){
                        $.ajax({
                            url: "Quarantine.htm",
                            data: "action=search&" + criteria,
                            success: function(html){
                                $('#main').html("");
                                $(html).find("#main tr").not(":last").each(function(){
                                    $('#main').append('<tr>' + $(this).html() + '</tr>');
                                });
                            },
                            error: function(jqXHR, textStatus, data){
                                alert(jqXHR.statusText + " - " + textStatus + ": " + data);
                            }
                        });
                    }
                });
                
                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
                $('#qDate').datepicker({                        
                    dateFormat: "dd/mm/yy"
                });
              
            });
        </script>
      
    </body>
</html>