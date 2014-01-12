<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Delivery</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
       

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="StockTakeSave.htm" method="post" id="pickingAddForm">  
                        
                        <table class="collapse tblForm row-select">
                            <caption>Stock Take Add</caption>
                            <tbody class="tbl-nohover">

                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Accounting</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="stockname" id="stockname" value="" size="30"                                                    
                                                   />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>                                 
                               </tr>                            
                              
                                 
                            </tbody>                           
                        </table>
                        
                        <table class="collapse tblForm row-select">
                            <caption>List Product per Location</caption>
                            <thead>
                                <tr>
                                    <td class="style1">No</td>                                                                
                                    <td class="style1">Product Code</td>
                                    <td class="style1">Product Name</td>                                
                                    <td class="style1">Location</td>
                                    <td class="style1">Actual Qty</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover">
                                <c:set scope="page" value="1" var="nomor"/>
                                <c:if test="${model.stockTakes!=null}">
                                    <c:forEach items="${model.stockTakes}" var="stockTake">
                                        <tr>
                                            <td class="style1">
                                                <c:out value="${nomor}" />
                                                <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                            </td>                                                                
                                            <td class="style1">${stockTake.productCode}</td> <input type="hidden" name="productCode" value="${stockTake.productCode}"  />
                                            <td class="style1">${stockTake.productName}</td>                                
                                            <td class="style1">${stockTake.locationName}</td><input type="hidden" name="location" value="${stockTake.location}"  />
                                            <td class="style1"><input type="text" name="qty" value="0" /></td>
                                        </tr>   
                                    </c:forEach>
                                </c:if>
                            </tbody>
                            <tfoot>
                                <td colspan="5">
                                    <span class="style1">
                                        <label>
                                            <input type="button" name="btnSave" id="btnSavePurchase" value="Save" />
                                        </label>
                                        <input type="button" class="style1" name="button" id="btnBack" value="Back" />
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
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
            $(document).ready(function(){
                $("#list2").jqGrid({ url:'productjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['Product Code','Product Name', 'Category','Brand Name', 'Description'], 
                                colModel:[ {name:'productCode',index:'productCode'}, {name:'productName',index:'productName'}, {name:'productCategory',index:'productCategory'}, 
                                    {name:'brandName',index:'brandName'},
                                    {name:'productDescription',index:'productDescription'}
                                     ], 
                                sortname: 'productCode',
                                rowNum:10, rowList:[10,20,30], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) {         
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#productcode").val(localRowData.productCode);
                                            $("#productname").val(localRowData.productName);                                                                               
                                            $("#dialog").dialog('close');
                                        } 
                                },
                                pager: '#pager2', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                            jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});

               
            });
            
            
             $("#productcode").click(function () {
                        $("#dialog").dialog({ width: 650, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Product' });
                });
                
                $("#btnSavePurchase").click(function () {
                    $("#dialog-confirm").dialog({ width: 300, height: 150, position: "center", modal: true, 
                        buttons: {
                            "Cancel": function() {                                       
                                    $( this ).dialog( "close" );                                        
                            },
                            "Save": function() {                               
                                $("form#pickingAddForm").submit();
                            }
                        },
                        zindex: 1, title: 'Confirm' });
                });
            
        </script>
        
         <div id="dialog" title="Product Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        
         <div id="dialog-confirm" title="Product Search" style="display:none;z-index:1;">
            Save Data?
        </div>
        
        
    </body>
</html>