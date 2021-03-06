<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>System Option &therefore; Master &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            
            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <table class="collapse tblForm row-select">
                        <caption>Available &centerdot; System Option</caption>
                        <thead>
                            <tr>
                                <td class="center" style="width: 25px;">#</td>
                                <td class="center" style="width: 250px;">Option Name</td>
                                <td class="center">Value</td>
                                <td class="center" style="width: 50px;">Action</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${list}" var="x" varStatus="vs">
                                <tr>
                                    <td class="center">${vs.index + 1}</td>
                                    <td>${x.name}</td>
                                    <td>${x.value}</td>
                                    <td class="center"><img class="edit" src="resources/images/edit.gif" data-code="${x.code}" data-id="${x.id}" data-type="${x.type}" style="cursor: pointer;"></td>
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

<!--         javascript block HERE 
        <div id="management" style="display: none;">
            <h3>User List</h3>
            <table class="collapse tblForm row-select" id="managementTable">
                <thead>
                    <tr>
                        <td class="center">Username</td>
                        <td class="center">Name</td>
                        <td class="center">Role Name</td>
                        <td class="center">Department</td>
                        <td class="center" style="width: 25px;">Action</td>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
            
            <div>
                Name: <input type="text" id="name" style="border-width: medium medium 1px; border-style: none none dashed; border-image: none; border-color: rgb(119, 119, 119);">
                <input type="button" id="btnAdd" value="Add User">
            </div>
        </div>-->
        <script>
            /*
             * You can say this page global variable
             */
            var TYPE = -1, ID = -1;
            /*
             * System Option Types
             * 1 - List
             */
            $('.edit').live('click', function() {
                if (confirm('Continue to edit this option?')) {
                    TYPE = $(this).data('type');
                    ID = $(this).data('id');
                    
                    var $td = $(this).parent().prev();
                    $td.html('<input type="text" class="value" value="' + $td.text().trim() + '" style="width: 100%;">');
                    $('.value').focus();
                }
            });
            $('.value').live('blur', function() {
                var value = prepareValue(this.value);
                $(this).parent().text(value);
                $.ajax({
                    url: 'SystemOption.htm', type: 'post',
                    data: { action: 'postUpdate', id: ID, value: value },
                    dataType: 'json',
                    success: function(json) {
                        if (json.status !== 1) {
                            alert(json.message);
                        }
                    },
                    error: function() {
                        alert('Sorry! Got error return from server!');
                    }
                });
            });
            
            function prepareValue(data) {
                if (typeof TYPE === 'string') {
                    TYPE = parseInt(TYPE);
                }
                
                switch (TYPE) {
                    case 1: {
                        data = data.replace(/ /g, '');
                    } break;
                }
                
                return data;
            }
//            // fya} bind onClick fro edit button
//            var appMenuCode;
//            $('.edit').on('click', function() {
//                appMenuCode = $(this).data('code');
//                $('#name').autocomplete('option', 'source', function(request, response) {
//                    $.getJSON('BackDateManagement.htm?action=getUserByName&appMenuCode=' + appMenuCode + '&term=' + $('#name').val(), function(data) {
//                        response(data.users);
//                    });
//                });
//                
//                $('#management').dialog({
//                    title: $(this).parent().prev().prev().text().trim(),
//                    modal: true,
//                    draggable: false,
//                    resizable: false,
//                    width: 1000,
//                    open: function() { loadDataUserList(); },
//                    close: function() { location.href = location.href; }
//                });
//            });
//            
//            // fya} bind autocomplete for name field
//            $('#name').autocomplete({
//                source: '',
//                minLength: 2,
//                select: function(event, ui) {
//                    $('#name').val(ui.item.name).data('userId', ui.item.user_id);
//                    return false;
//                }
//            }).data('autocomplete')._renderItem = function(ul, item) {
//                return $('<li>')
//                        .data("item", item)
//                        .append('<a><b>' + item.username + ' | ' + item.name +'</b><br /> Role : ' + item.role_name + ' | Department: ' + item.department_name + '</a></li>')
//                        .appendTo(ul);
//            };
//            
//            // fya} bind onClick for btnAdd
//            $('#btnAdd').on('click', function() {
//                if ($('#name').data('userId') === undefined || $('#name').data('userId') === null || $('#name').data('userId') === '') {
//                    return false;
//                } else {
//                    gnvs.ajaxCall({action: 'addUser', appMenuCode: appMenuCode, userId: $('#name').data('userId')}, function() { loadDataUserList(); });
//                    $('#name').val('').data('userId', '');
//                }
//            });
//            
//            // fya} bind onClick for btnDelete
//            $('body').on('click', '.delete', function() {
//                if (confirm('Continue to delete the user?')) {
//                    gnvs.ajaxCall({action: 'deleteUser', id: $(this).data('id')}, function() { loadDataUserList(); });
//                }
//            });
//            
//            // fya} function to load user list data
//            function loadDataUserList() {
//                $.ajax({
//                    url: 'BackDateManagement.htm', type: 'get',
//                    data: { action: 'getUser', appMenuCode: appMenuCode },
//                    dataType: 'json',
//                    beforeSend: function() { $('#managementTable tbody').text(''); },
//                    success: function(json) {
//                        if (json.users !== undefined && json.users.length > 0) {
//                            for (var i = 0; i < json.users.length; i++) {
//                                $('#managementTable tbody').append('<tr>' + 
//                                        '<td>' + json.users[i].username + '</td>' +
//                                        '<td>' + json.users[i].name + '</td>' +
//                                        '<td>' + json.users[i].role_name + '</td>' +
//                                        '<td>' + json.users[i].department_name + '</td>' +
//                                        '<td class="center"><img src="resources/images/delete.gif" class="delete" data-id="' + json.users[i].id + '"></td>' +
//                                        '</tr>');
//                            }
//                        } else {
//                            $('#managementTable tbody').append('<tr><td colspan="5" class="center"><em>-- no data --</em></td></tr>');
//                        }
//                    }
//                });
//            }
        </script>
    </body>
</html>
