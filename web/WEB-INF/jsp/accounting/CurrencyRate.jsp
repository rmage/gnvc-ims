<%@page import="java.util.Date"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Accounting :: Currency Rate</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            input[type="text"] {
                -moz-border-bottom-colors: none;
                -moz-border-left-colors: none;
                -moz-border-right-colors: none;
                -moz-border-top-colors: none;
                background: none repeat scroll 0 0 transparent;
                border-color: -moz-use-text-color -moz-use-text-color #A6A6A6;
                border-image: none;
                border-style: none none solid;
                border-width: 0 0 1px;
                text-align: right;
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
                    <table class="collapse tblForm row-select">
                        <caption>Currency Rate</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Currency</td>
                                <td>Rate to IDR</td>
                                <td>Rate Date</td>
                                <td>By</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${model.crs}" var="x" varStatus="i">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td>${x.currencyCode}</td>
                                    <td>
                                        <input type="text" value="${x.rateValue == null ? '0.00' : x.rateValue}"  data-currency="${x.currencyCode}" data-old="${x.rateValue}" pattern="^\d+(\.|\,)\d{2}$" />
                                        <input type="button" value="Set" />
                                    </td>
                                    <td><c:if test="${x.rateDate != null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${x.rateDate}" /></c:if></td>
                                    <td>${x.createdBy}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            
            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            
            /*global $, confirm*/
            $('input[type="button"]').on('click', function () {
                'use strict';
                if (confirm('Set currency rate?')) {
                    var $t = $(this);
                    var $i = $(this).parent().parent().find('input[type="text"]');
                    if ($i[0].checkValidity()) {
                        $.ajax({
                            type: 'POST',
                            data: {action: 'setRate', cc: $(this).parent().prev().html(), v: $i.val()},
                            dataType: 'json',
                            beforeSend: function () {
                                $t.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
                            },
                            success: function (data) {
                                if(data.status === 0) {
                                    alert('Server error, please contact your administrator!');
                                    $i.val($i.data('old'));
                                } else {
                                    $i.data('old', $i.val());
                                    $t.parent().next().html(data.date);
                                    $t.parent().next().next().html(data.by);
                                }
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                alert('Server error (' + textStatus + ') : ' + errorThrown + '\n' + jq);
                                $i.val($i.data('old'));
                            },
                            complete: function () {
                                $('#load').remove();
                            }
                        });
                    }
                }
            });
            
        </script>
    </body>
</html>
