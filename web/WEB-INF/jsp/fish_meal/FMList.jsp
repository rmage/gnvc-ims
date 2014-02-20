<%@page import="java.util.Date"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Fish Meal</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            #fyaQPanelBody tr:nth-child(2n+1) {
                background-color: #EEEEEE;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            
            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishMeal.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Generate Report</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">FM Date</td>
                                    <td>
                                        <select id="year">
                                            <c:forEach items="${model.year}" var="x">
                                                <option>${x}</option>
                                            </c:forEach>
                                        </select>
                                        <select id="month">    
                                            <c:forEach items="${model.month}" var="x">
                                                <option value="${x[0]}" <c:if test="${x[2] == '1'}">selected="true"</c:if>>${x[1]}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Generate" name="btnSearch" />
                                        <!--<input type="button" value="Add" name="btnAdd" onclick="window.location.replace('FishMeal.htm?action=create');" />-->
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>List</caption>
                        <thead>
                            <tr>
                                <td colspan="21">
                                    <select id="year">
                                        <c:forEach items="${model.year}" var="x">
                                            <option>${x}</option>
                                        </c:forEach>
                                    </select>
                                    <select id="month">    
                                        <c:forEach items="${model.month}" var="x">
                                            <option value="${x[0]}" <c:if test="${x[2] == '1'}">selected="true"</c:if>>${x[1]}</option>
                                        </c:forEach>
                                    </select>
                                    <input id="view" type="button" value="View" />
                                </td>
                            </tr>
                            <tr>
                                <td rowspan="2">DATE</td>
                                <td colspan="2" rowspan="2">BEG INVENTORY</td>
                                <td colspan="9">RECEIPTS</td>
                                <td colspan="2" rowspan="2">TOTAL TO DAY</td>
                                <td colspan="3" rowspan="2">ISSUANCES</td>
                                <td colspan="2" rowspan="2">END INVENTORY</td>
                                <td colspan="2" rowspan="2">REMARKS</td>
                            </tr>
                            <tr>
                                <td colspan="3">SHIFT I</td>
                                <td colspan="3">SHIFT II</td>
                                <td colspan="3">SHIFT III</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>BAGS</td>
                                <td>KILOS</td>
                                <td>R.M</td>
                                <td>BAGS</td>
                                <td>KILOS</td>
                                <td>R.M</td>
                                <td>BAGS</td>
                                <td>KILOS</td>
                                <td>R.M</td>
                                <td>BAGS</td>
                                <td>KILOS</td>
                                <td>BAGS</td>
                                <td>KILOS</td>
                                <td>BAGS</td>
                                <td>KILOS</td>
                                <td>PRICE</td>
                                <td>BAGS</td>
                                <td>KILOS</td>
                                <td>M.HRS</td>
                                <td>OTHERS</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
<!--                        <tfoot>
                            <tr>
                                <td colspan="14">
                                    <c:if test="${model.page !=null && model.page > 1}">
                                        <a href="FishMeal.htm?page=<c:out value="${model.page-1}" />">&lt</a>
                                    </c:if>
                                    &nbsp;page: ${model.page}&nbsp;
                                    <c:if test="${model.page < model.totalRows/model.paging}">
                                        <a href="FishMeal.htm?page=<c:out value="${model.page+1}" />">&gt;</a>
                                    </c:if>
                                </td>
                            </tr>
                        </tfoot>-->
                    </table>
                </div>
            </div>
            
            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <div id="fyaQPanel" class="div-dtl" style="width: 100%; display: none;" ondblclick="fyaQPanel(0)"></div>
        <script>
           
           $('#view').bind('click', function() {
               $o = $(this);
               $o.attr('disabled', 'true');
               $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
           });
           
        </script>
    </body>
</html>
