<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Good Receive Cross Dock List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="GRForm" action="GRCrossDock.htm">
                        <input type="hidden" name="action" value="list" />
                        <table class="collapse tblForm row-select">
                            <caption>Search Good Receive Cross Dock</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">Good Receive No</td>
                                    <td>
                                        <input type="text" id="sGRCode" name="sGRCode" value=""/>
                                    </td>
                                    <td width="20%">Good Receive Date</td>
                                    <td>
                                        <input type="text" id="sGRDate" name="sGRDate" value="" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="7">
                                    <span class="style1">
                                        <input class ="style1" type="button" value="Search" id="btnSearch" name="btnSearch" />
                                        <input class ="style1" type="submit" value="View All" id="btnViewAll" name="btnViewAll" />
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Good Receive Cross Dock - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Good Receive No</td>
                                <td class="style1">Purchase Order No</td>
                                <td class="style1">Good Receive Date</td>
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
                                            <c:if test="${list.approvedBy == null}">
                                                <a href="<c:url value="GRCrossDock.htm?action=update&grCode=${list.GRCode}" />">
                                                    <img src="resources/images/edit.gif" />
                                                </a>
                                            </c:if>
                                            <a href="<c:url value="GRCrossDock.htm?action=view&grCode=${list.GRCode}" />">
                                                <img src="resources/images/detail.png" width="16" />
                                            </a>
                                        </td>
                                        <td class="style1"><c:out value="${list.GRCode}"/></td>
                                        <td class="style1"><c:out value="${list.updatedBy}"/></td>
                                        <td class="style1"><fmt:formatDate pattern="dd/MM/yyyy" value="${list.GRDate}" /></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="5">
                                    <span class="style1">
                                    <c:if test="${model.page != null && model.page > 1}">
                                        <a href="GRCrossDock.htm?action=list&page=<c:out value="${model.page - 1}" />">&lt;</a>
                                    </c:if>
                                        
                                    &nbsp;page: <c:out value="${model.page}" />&nbsp;
                                    
                                    <a href="GRCrossDock.htm?action=list&page=<c:out value="${model.page + 1}" />">&gt;</a>
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="5">
                                <span class="style1">
                                    <label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    </label>
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
                    location.href = 'GRCrossDock.htm?action=create';
                });
                $('#btnSearch').click(function(){
                    var criteria;
                    var flag = true;
                    var grCode = $('#sGRCode');
                    var grDate = $('#sGRDate');
                    
                    if(grCode.val() != ""){
                        criteria = "grCode=" + grCode.val();
                    } else if(grDate.val() != ""){
                        criteria = "grDate=" + grDate.val();
                    } else{
                        flag = false;
                    }
                    
                    if(flag){
                        $.ajax({
                            url: "GRCrossDock.htm",
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
                $('#sGRDate').datepicker({                        
                    dateFormat: "dd/mm/yy"
                });
              
            });
        </script>
      
    </body>
</html>