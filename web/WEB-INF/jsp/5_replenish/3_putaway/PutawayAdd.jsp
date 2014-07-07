<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Put-away</title>
        <%@include file="../metaheader.jsp" %>
        
         <style>
            .tablepopup table, .tablepopup tr, .tablepopup td, .tablepopup th {
                border: 1px solid gray;
            }
        </style>
        
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
//            com.app.wms.engine.db.dto.Wh dto = (com.app.wms.engine.db.dto.Wh) m.get("dto");
            String mode = (String) m.get("mode");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            
            <script language="JavaScript">
                    $(document).ready(function(){
                        
                        
                        $('#pickingAddForm').validationEngine('attach');     
                        
                         $('.check').live("click", function() {
                             
                            // clear values
                            $('#productcode').val('');  
                            $('#productname').val('');  
                            $('#qty').val('');   
                            
                            $('#location2').val('0');   
                            $('#location3').val('0');   
                            $('#location4').val('0');   
                            $('#location5').val('0'); 
                            $('#location6').val('0'); 
                            $('#location7').val('0'); 
                            $('#location8').val('0'); 
                            $('#location9').val('0'); 
                            $('#location10').val('0'); 
                            $('#location11').val('0'); 
                            
                            // clear table
                            $("#locationAvailable tbody").html('');
                            $("#locationAlternative tbody").html('');
                            
                            var karantina = 'false';
                            if($(this).attr('qtygood') == 0)
                                karantina = 'true';
                            
                            var producttype = $(this).attr('producttype');
                            
                            
                            /* 
                             *  ajax call to location available
                             *          **********      important       ********
                             * */
                            $.ajax({
                                dataType: 'json',
                                success: function(data) {
                                     $.each(data.vlocationproducts, function(k,v){
                                        var rowCount = $('#locationAvailable tr').length+1;
                                        $("<tr class=\"myhover\">"+                                            
                                                "<td class=\"style1\">"+v.locationCode+"</label></td>"+
                                                "<td class=\"style1\">"+v.locationName+"</td>"+
                                                "<td class=\"style1\">"+v.balance+"</td>"+
                                               "<td class=\"style1\">"+v.minProduct+"</td>"+
                                                "<td class=\"style1\">"+v.maxProduct+"</td>"+
                                                "<td class=\"style1\">"+(v.maxProduct-v.balance)+"</td>"+
                                                "<td><input type=\"text\" balance=\""+(v.maxProduct-v.balance)+"\" maxProduct=\""+v.maxProduct+"\" class=\"intValidate\" name=\""+v.locationCode+"\" name1=\""+v.locationName+"\" value=\"0\" id=\"location"+rowCount+"\" /></td>"+
                                            "</tr>").appendTo("#locationAvailable tbody")                                                                   
                                        });
                                    },
                                    url: 'vlocationproduct.htm?productId='+$(this).attr('productcode')+'&karantina='+karantina+'&wh='+producttype
                                });
                                
                                
                            if(karantina == 'true')
                                karantina = 'false';
                            else
                                karantina = 'true';
                                
                                
                            /* 
                             *  ajax call to location alternative
                             *          **********      important       ********
                             * */
                            $.ajax({
                                dataType: 'json',
                                success: function(data) {
                                     $.each(data.vlocationproducts, function(k,v){
                                        var rowCount = $('#locationAlternative tr').length+1;
                                        $("<tr class=\"myhover\">"+                                            
                                                "<td class=\"style1\">"+v.locationCode+"</label></td>"+
                                                "<td class=\"style1\">"+v.locationName+"</td>"+
                                                "<td class=\"style1\">"+v.balance+"</td>"+
                                               "<td class=\"style1\">"+v.minProduct+"</td>"+
                                                "<td class=\"style1\">"+v.maxProduct+"</td>"+
                                                "<td class=\"style1\">"+(v.maxProduct-v.balance)+"</td>"+
                                                "<td><input type=\"text\" balance=\""+(v.maxProduct-v.balance)+"\" maxProduct=\""+v.maxProduct+"\" class=\"intValidate\" name=\""+v.locationCode+"\" name1=\""+v.locationName+"\" value=\"0\" id=\"locationAlternative"+rowCount+"\" /></td>"+
                                            "</tr>").appendTo("#locationAlternative tbody")                                                                   
                                        });
                                    },
                                    url: 'vlocationproduct.htm?productId='+$(this).attr('productcode')+'&karantina='+karantina+'&wh='+producttype
                                });
                            

                            // setup values
                            $('#productcode').val($(this).attr('productcode')); 
                            var productCode = $(this).attr('productcode');
                            $('#productname').val($(this).attr('productname'));                        
                            $('#qty').val(parseInt($(this).attr('qty')) - parseInt($(this).attr('qtyplus'))); 
                            $('#qtyplus').val($(this).attr('qtyplus')); 
                                                                                    
                            var qtygood = $(this).attr('qtygood');
                            var producttype = $(this).attr('producttype');
                             
                            $("#dialog").dialog({ width: 800, height: 600, position: "center", modal: true, 
                                buttons: {
                                    "Cancel": function() {                                       
                                            $( this ).dialog( "close" );                                        
                                    },
                                    "Save": function() {
                                        var location = productCode+"-"+producttype+"-"+qtygood+"-location"; // 001-0-location
                                        var location1 = productCode+"-"+producttype+"-"+qtygood+"-location1";
                                        var locationUnit1 = ""; // ini untuk view
                                        var locationUnit = ""; // ini untuk post
                                       
                                       /*
                                        * location real 2 s/d 11
                                        */
                                       if($("#location2").length) {  
                                            if($('#location2').val() != "0") {
                                                locationUnit1 = locationUnit1+" "+$('#location2').attr('name1')+"<br />"; 
                                                locationUnit = locationUnit+" "+$('#location2').attr('name')+"<br />";
                                            }                                                 
                                       }
                                       if($("#location3").length) {
                                           if($('#location3').val() != "0") {
                                               locationUnit = locationUnit+" "+$('#location3').attr('name')+"<br />";
                                               locationUnit1 = locationUnit1+" "+$('#location3').attr('name1')+"<br />";
                                           }                                                
                                       }
                                       if($("#location4").length) {
                                            if($('#location4').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#location4').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#location4').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#location5").length) {
                                            if($('#location5').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#location5').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#location5').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#location6").length) {
                                            if($('#location6').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#location6').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#location6').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#location7").length) {
                                            if($('#location7').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#location7').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#location7').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#location8").length) {
                                            if($('#location8').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#location8').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#location8').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#location9").length) {
                                            if($('#location9').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#location9').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#location9').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#location10").length) {
                                            if($('#location10').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#location10').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#location10').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#location11").length) {
                                            if($('#location11').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#location11').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#location11').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       
                                       
                                       /*
                                        *   location alternative 2 s/d 11
                                        */
                                       if($("#locationAlternative2").length) {
                                            if($('#locationAlternative2').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#locationAlternative2').attr('name')+"<br />";
                                                locationUnit1 = locationUnit1+" "+$('#locationAlternative2').attr('name1')+"<br />";
                                            }                                                                                          
                                       }
                                       if($("#locationAlternative3").length) {
                                            if($('#locationAlternative3').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#locationAlternative3').attr('name')+"<br />";   
                                                locationUnit1 = locationUnit1+" "+$('#locationAlternative3').attr('name1')+"<br />";   
                                            }                                                                                       
                                       }
                                       if($("#locationAlternative4").length) {
                                            if($('#locationAlternative4').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#locationAlternative4').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#locationAlternative4').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#locationAlternative5").length) {
                                            if($('#locationAlternative5').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#locationAlternative5').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#locationAlternative5').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#locationAlternative6").length) {
                                            if($('#locationAlternative6').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#locationAlternative6').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#locationAlternative6').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#locationAlternative7").length) {
                                            if($('#locationAlternative7').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#locationAlternative7').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#locationAlternative7').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#locationAlternative8").length) {
                                            if($('#locationAlternative8').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#locationAlternative8').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#locationAlternative8').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#locationAlternative9").length) {
                                            if($('#locationAlternative9').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#locationAlternative9').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#locationAlternative9').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#locationAlternative10").length) {
                                            if($('#locationAlternative10').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#locationAlternative10').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#locationAlternative10').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                       if($("#locationAlternative11").length) {
                                            if($('#locationAlternative11').val() != "0") {
                                                locationUnit = locationUnit+" "+$('#locationAlternative11').attr('name')+"<br />";    
                                                locationUnit1 = locationUnit1+" "+$('#locationAlternative11').attr('name1')+"<br />";  
                                            }                                                                                      
                                       }
                                        
                                       
                                        $("#"+location).html(locationUnit1); // view
                                        $("#"+location1).val(locationUnit); // post
                                        
                                        
                                        var qty = productCode+"-"+producttype+"-"+qtygood+"-qty";
                                        var qty1 = productCode+"-"+producttype+"-"+qtygood+"-qty1";
                                        var qtyUnit = "";
                                        
                                        var productqty = parseInt($('#qty').val())+parseInt($('#qtyplus').val());
                                        var productafterqty = 0;
                                        var qtyerror = false;
                                       
                                        if($("#location2").length) { 
                                            if($('#location2').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#location2').val()+"<br />";
                                                productafterqty  += parseInt($('#location2').val());
                                                if(parseInt($('#location2').val()) > (parseInt($('#location2').attr('balance'))))
                                                    qtyerror = true;
                                                
//                                                console.log(parseInt($('#location2').val()));
//                                                console.log((parseInt($('#location2').attr('balance')))); 
//                                                console.log(parseInt($('#location2').attr('balance'))); 
//                                                console.log((parseInt(productqty) + parseInt($('#location2').attr('balance')))); 
//                                                console.log(parseInt($('#location2').attr('maxProduct')) < (parseInt(productqty) + parseInt($('#location2').attr('balance')))); 
                                            }
                                        }
                                        if($("#location3").length) {
                                            if($('#location3').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#location3').val()+"<br />";
                                                productafterqty  += parseInt($('#location3').val());
                                                if(parseInt($('#location3').val()) > (parseInt($('#location3').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                        }
                                        if($("#location4").length) {
                                            if($('#location4').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#location4').val()+"<br />";
                                                productafterqty  += parseInt($('#location4').val());
                                                if(parseInt($('#location4').val()) > (parseInt($('#location4').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                        }                                        
                                        if($("#location5").length) {
                                            if($('#location5').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#location5').val()+"<br />";
                                                productafterqty  += parseInt($('#location5').val());
                                                if(parseInt($('#location5').val()) > (parseInt($('#location5').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                        }
                                        if($("#location6").length) {
                                            if($('#location6').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#location6').val()+"<br />";
                                                productafterqty  += parseInt($('#location6').val());
                                                if(parseInt($('#location6').val()) > (parseInt($('#location6').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                        }
                                        if($("#location7").length) {
                                            if($('#location7').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#location7').val()+"<br />";
                                                productafterqty  += parseInt($('#location7').val());
                                                if(parseInt($('#location7').val()) > (parseInt($('#location7').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                        }
                                        if($("#location8").length) {
                                            if($('#location8').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#location8').val()+"<br />";
                                                productafterqty  += parseInt($('#location8').val());
                                                if(parseInt($('#location8').val()) > (parseInt($('#location8').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                        }
                                        if($("#location9").length) {
                                            if($('#location9').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#location9').val()+"<br />";
                                                productafterqty  += parseInt($('#location9').val());
                                                if(parseInt($('#location9').val()) > (parseInt($('#location9').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                        }
                                        if($("#location10").length) {
                                            if($('#location10').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#location10').val()+"<br />";
                                                productafterqty  += parseInt($('#location10').val());
                                                if(parseInt($('#location10').val()) > (parseInt($('#location10').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                        }
                                        if($("#location11").length) {
                                            if($('#location11').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#location11').val()+"<br />";
                                                productafterqty  += parseInt($('#location11').val());
                                                if(parseInt($('#location11').val()) > (parseInt($('#location11').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                        }
                                        
                                        if($("#locationAlternative2").length) {
                                            if($('#locationAlternative2').val() != "0"){
                                                qtyUnit = qtyUnit+" "+$('#locationAlternative2').val()+"<br />";   
                                                productafterqty  += parseInt($('#locationAlternative2').val());
                                                if(parseInt($('#locationAlternative2').val()) > (parseInt($('#locationAlternative2').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                       }
                                       if($("#locationAlternative3").length) {
                                            if($('#locationAlternative3').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#locationAlternative3').val()+"<br />";
                                                productafterqty  += parseInt($('#locationAlternative3').val());
                                                if(parseInt($('#locationAlternative3').val()) > (parseInt($('#locationAlternative3').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                       }
                                       if($("#locationAlternative4").length) {
                                            if($('#locationAlternative4').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#locationAlternative4').val()+"<br />";  
                                                productafterqty  += parseInt($('#locationAlternative4').val());
                                                if(parseInt($('#locationAlternative4').val()) > (parseInt($('#locationAlternative4').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                       }
                                       if($("#locationAlternative5").length) {
                                            if($('#locationAlternative5').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#locationAlternative5').val()+"<br />";  
                                                productafterqty  += parseInt($('#locationAlternative5').val());
                                                if(parseInt($('#locationAlternative5').val()) > (parseInt($('#locationAlternative5').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                       }
                                       if($("#locationAlternative6").length) {
                                            if($('#locationAlternative6').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#locationAlternative6').val()+"<br />";  
                                                productafterqty  += parseInt($('#locationAlternative6').val());
                                                if(parseInt($('#locationAlternative6').val()) > (parseInt($('#locationAlternative6').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                       }
                                       if($("#locationAlternative7").length) {
                                            if($('#locationAlternative7').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#locationAlternative7').val()+"<br />";  
                                                productafterqty  += parseInt($('#locationAlternative7').val());
                                                if(parseInt($('#locationAlternative7').val()) > (parseInt($('#locationAlternative7').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                       }
                                       if($("#locationAlternative8").length) {
                                            if($('#locationAlternative8').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#locationAlternative8').val()+"<br />";  
                                                productafterqty  += parseInt($('#locationAlternative8').val());
                                                if(parseInt($('#locationAlternative8').val()) > (parseInt($('#locationAlternative8').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                       }
                                       if($("#locationAlternative9").length) {
                                            if($('#locationAlternative9').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#locationAlternative9').val()+"<br />";  
                                                productafterqty  += parseInt($('#locationAlternative9').val());
                                                if(parseInt($('#locationAlternative9').val()) > (parseInt($('#locationAlternative9').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                       }
                                       if($("#locationAlternative10").length) {
                                            if($('#locationAlternative10').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#locationAlternative10').val()+"<br />";  
                                                productafterqty  += parseInt($('#locationAlternative10').val());
                                                if(parseInt($('#locationAlternative10').val()) > (parseInt($('#locationAlternative10').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                       }
                                       if($("#locationAlternative11").length) {
                                            if($('#locationAlternative11').val() != "0") {
                                                qtyUnit = qtyUnit+" "+$('#locationAlternative11').val()+"<br />";  
                                                productafterqty  += parseInt($('#locationAlternative11').val());
                                                if(parseInt($('#locationAlternative11').val()) > (parseInt($('#locationAlternative11').attr('balance'))))
                                                    qtyerror = true;
                                            }
                                       }
                                       
//                                       console.log(parseInt(productafterqty));
//                                       console.log(parseInt(productqty)); 
                                       
                                       if(parseInt(productafterqty) != parseInt(productqty)) {
                                           alert('Quantity Total is not the same');
                                           return false;
                                       }
                                       
                                       if(qtyerror) {
                                           alert('Maximum Item Reached');
                                           return false;
                                       }
                                        
                                        
                                        $("#"+qty).html(qtyUnit);
                                        $("#"+qty1).val(qtyUnit);
                                        
                                        
                                        
//                                        console.log(locationUnit);
//                                        console.log("#"+location); 
                                        $( this ).dialog( "close" );
                                    }
                                },
                                zindex: 1, title: 'Set Product Status' });
                         });
                       
                       
                       
                       $("#list2").jqGrid({ url:'tallymanjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['Tallyman Code','Name', 'Tally Name','Job Function'], 
                                colModel:[ {name:'code',index:'code'}, {name:'name',index:'name'}, {name:'tallyname',index:'tallyname'}, 
                                    {name:'jobfunction',index:'jobfunction'}                                    
                                     ], 
                                sortname: 'code',
                                rowNum:10, rowList:[10,20,30], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) {         
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#tallyman").val(localRowData.code+" ~ "+localRowData.name);                                                                           
                                            $("#dialog2").dialog('close');
                                        } 
                                },
                                pager: '#pager2', sortname: 'code', viewrecords: true, sortorder: "desc"}); 
                            jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                            
                            
                            
                            /*
                             *  show gr
                             */
                        $("#list3").jqGrid({ url:'goodreceivejson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['GR Number','PO Number', 'Received Date', 'Lot Id'], 
                                colModel:[ {name:'grnumber',index:'grnumber',width:'250'}, {name:'ponumber',index:'ponumber'}, 
                                    {name:'receiveddate',index:'receiveddate'},                                 
                                    {name:'lotid',index:'lotid'}                                 
                                     ], 
                                sortname: 'grnumber',
                                rowNum:10, rowList:[10,20,30], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) {         
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#grnumber").val(localRowData.grnumber); 
                                            var lotid = localRowData.lotid; 
                                            $('#lotid').val(lotid);
                                                                                        
                                            // clear table
                                            $("#main tbody").html('');
                                            
                                            // ajax call
                                            $.ajax({
                                                dataType: 'json',
                                                success: function(data) {
                                                     $.each(data.grDetails, function(k,v){                                                         
                                                        var rowCount = $('#main tr').length-1;                                                                                                                                                                                                                                      
                                                               
                                                                var plusplus = "0";
                                                                if(v.qtyplus != "0"){
                                                                    plusplus = Math.abs( parseInt(v.qtygood) - parseInt(v.qtyplus) );
                                                                } else {
                                                                    plusplus = parseInt(v.qtygood);
                                                                }
                                                               
                                                                $("<tr class=\"myhover\">"+
                                                                "<td class=\"style1\">"+rowCount+"</td>"+
                                                                "<td class=\"style1\">"+v.productcode+"</td><input type=\"hidden\" name=\"productcode1\" value=\""+v.productcode+"\">"+
                                                                "<td class=\"style1\">"+v.productName+"</td>"+
                                                                "<td class=\"style1\">"+v.productType+"</td><input type=\"hidden\" name=\"productType1\" value=\""+v.productType+"\">"+
                                                                "<td class=\"style1\">"+v.qtyreal+"</td><input type=\"hidden\" name=\"qty1\" value=\""+v.qtygood+"\">"+
                                                                "<td class=\"style1\">"+plusplus+"</td><input type=\"hidden\" name=\"qty1\" value=\""+v.qtygood+"\">"+
                                                                "<td class=\"style1\">"+v.qtydmg+"</td><input type=\"hidden\" name=\"qty1\" value=\""+v.qtydmg+"\">"+
                                                                "<td class=\"style1\">"+v.qtyplus+"</td>"+
                                                                "<td class=\"style1\"><span id=\""+v.productcode+"-"+v.productType+"-"+v.qtygood+"-location\"></span>"+"</td><input type=\"hidden\" name=\"qty11\" id=\""+v.productcode+"-"+v.productType+"-"+v.qtygood+"-qty1\" />"+
                                                                "<td class=\"style1\"><span id=\""+v.productcode+"-"+v.productType+"-"+v.qtygood+"-qty\"></span></td><input type=\"hidden\" name=\"location1\" id=\""+v.productcode+"-"+v.productType+"-"+v.qtygood+"-location1\" />"+
                                                                "<td class=\"style1\"><a productcode=\""+v.productcode+"\" productname=\""+v.productName+"\" producttype=\""+v.productType+"\""+
                                                                "qty=\""+(v.qtygood != 0 ? v.qtygood : v.qtydmg)+"\" qtygood=\""+v.qtygood+"\" qtyplus=\""+v.qtyplus+"\" class=\"check\" href=\"javascript:void(0)\">Check</a></td>"+
                                                                "</tr>").appendTo("#main tbody")                                                                   
                                                    });
                                                },
                                                url: 'goodReceiveDetailJson.htm?param='+localRowData.grnumber
                                            });
                                            
                                            $("#dialog3").dialog('close');
                                        } 
                                },
                                pager: '#pager3', sortname: 'code', viewrecords: true, sortorder: "desc"}); 
                            jQuery("#list3").jqGrid('navGrid','#pager3',{edit:false,add:false,del:false});
                       
                    });
            </script>

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="putawayAdd.htm" method="post" id="pickingAddForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <input type="hidden" name="lotid" id="lotid" value=""/>
                        <table class="collapse tblForm row-select">
                            <caption>Putaway</caption>
                            <tbody class="tbl-nohover">
                               <%-- <tr>
                                    <td class="style1">Putaway Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="putawayNumber1" id="putawayNumber1" value="<%= m.get("punumber") %>" size="30" />
                                            <input type="hidden" name="putawayNumber" id="putawayNumber" value="<%= m.get("punumber") %>"  />
                                        </label>                                        
                                    </td>
                                </tr> --%>
                                <tr>
                                    <td class="style1">GR Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="grnumber" id="grnumber" size="30" 
                                                   class="validate[required] datepicker readonly" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                            <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                        </label>                                        
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">TallyMan</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="tallyman" id="tallyman" size="30" 
                                                   class="validate[required] datepicker readonly" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                            <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                        </label>                                        
                                    </td>
                                </tr>

                            </tbody>
                           
                        </table>
                   
                        
                        <table class="collapse tblForm row-select" id="main">
							<caption>Product - List</caption>
								<thead>
								    <tr>
                                            <td class="style1">No.</td>
	                                    <td class="style1">Product Code </td>
	                                    <td class="style1">Product Name</td>
	                                    <td class="style1">Product Type</td>
	                                    <td class="style1">Qty PO</td>
	                                    <td class="style1">Qty Good</td>
	                                    <td class="style1">Qty Damaged</td>
	                                    <td class="style1">More</td>
<!--	                                    <td class="style1">Kategori / Tipe Barang</td>-->
	                                    <td class="style1">Location</td>
	                                    <td class="style1">Qty Location</td>
	                                    <td class="style1">Action</td>
								    </tr>
								</thead>

                       
								<tbody class="tbl-nohover">
                                        <%--
                                        <tr>
                                            <td class="style1">1.</td>
	                                    <td class="style1">001</td>
	                                    <td class="style1">Lipstik Merah Delima</td>	                                    
	                                    <td class="style1">20 Lusin</td>
	                                    <td class="style1">Pecah Belah</td>
	                                    <td class="style1">Rak 001</td>
	                                    <td class="style1"><a class="check" href="javascript:void(0)">Check</a></td>
                                        </tr>
                                        --%>                            
                                                             
                                                                          
								</tbody>
								
	                            <tfoot><br/>
	                                <tr>
	                                    <td colspan="10"></td>
	                                    <td></td>
	                                </tr>
	                            </tfoot>
                        </table>
                </form>
                        
                        <table class="collapse tblForm row-select ui-widget-content">
                           
                            <tbody class="tbl-nohover">

                                
			    		</tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="9">
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnSavePurchase" id="btnSavePurchase" value="Save" />
                                    </label>
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" />
                                    </label>
                                </td>
                            </tr></tfoot>
                  </table>
                        
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        
       
                        
        <div id="dialog" title="Product Search" style="display:none;z-index:1;">
             <form>
                 <table>
                     <tbody><tr>
                         <td width="20%"> <label for="name">Product Code</label></td>
                         <td><input type="text" name="productcode" id="productcode" readonly /> </td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                     </tr>
                     <tr>
                         <td><label for="email">Product Name</label></td>
                         <td><input type="text" name="productname" id="productname" value="" readonly /></td>
                          <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                     </tr>
                     <tr>
                         <td><label for="password">Qty </label></td>
                         <td> <input type="text" name="qty" id="qty" value="" readonly /></td>
                          <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                     </tr>                     
                     <tr>
                         <td><label for="password">More</label></td>
                         <td> <input type="text" name="qtyplus" id="qtyplus" value="" readonly /></td>
                          <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                     </tr>                     
                 </tbody></table>  
                 <br />                 

                  <table class="collapse tblForm row-select" id="locationAvailable">
                    <caption>Location Available</caption>
                    <thead>
                            <tr>
                                <td> <label for="name">Location</label></td>
                                <td> <label for="name">Location Name</label></td>
                                <td> <label for="name">Qty Now</label></td>
                                <td> <label for="name">Qty Min</label></td>
                                <td> <label for="name">Qty Max</label></td>                         
                                <td> <label for="name">Available Qty</label></td>                         
                                <td> <label for="name">Value</label></td>                         
                            </tr>
                    </thead>
                    <tbody>
                        <%-- 
                        <tr>
                            <td><label for="email">R001</label></td>
                            <td><label for="email">R001</label></td>
                            <td><label for="email">10</label></td>
                            <td><label for="email">10</label></td>
                            <td><label for="email">50</label></td>
                            <td><label for="email">40</label></td>
                            <td><input type="text" name="R001" id="location2" /></td>
                        </tr>
                        --%>
                        
                 </tbody>
              </table>  
                 
                 <br />
                 
                  <table class="collapse tblForm row-select" id="locationAlternative">
                    <caption>Location Alternative</caption>
                    <thead>
                     <tr>
                         <td> <label for="name">Location</label></td>
                         <td> <label for="name">Location Name</label></td>
                         <td> <label for="name">Qty Now</label></td>
                         <td> <label for="name">Qty Min</label></td>
                         <td> <label for="name">Qty Max</label></td>                         
                         <td> <label for="name">Available Qty</label></td>                         
                         <td> <label for="name">Value</label></td>                          
                     </tr>
                     </thead>
                     <tbody>
                         <%-- 
                            <tr>
                                <td><label for="email">R003</label></td>
                                <td><label for="email">R003</label></td>
                                <td><label for="email">10</label></td>
                                <td><label for="email">10</label></td>
                                <td><label for="email">50</label></td>
                                <td><label for="email">40</label></td>
                                <td><input type="text" name="R003" id="location4" /></td>
                            </tr>
                         --%>
                 </tbody>
                  </table>  
                 
                 <br />
                 <table>
                     <tr>
                         <td>
                             Remark 
                         </td>
                         <td>
                             : &nbsp;
                         </td>
                         <td>
                             <textarea style="width: 377px; height: 81px;" value="" id="remark" name="password" type="text"></textarea>
                         </td>
                     </tr>
                 </table>
                 
            </form>
        </div>
                        
                        <script>
                             $("#tallyman").click(function () {
                                        $("#dialog2").dialog({ width: 650, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Tallyman' });
                             });
                             
                             $("#grnumber").click(function () {
                                        $("#dialog3").dialog({ width: 740, height: 275, position: "center", modal: true, zindex: 1, title: 'Select GR Number' });
                             }); 
                             
                             $('#btnCancel').click(function() {
                                location.href = 'Putaway.htm?action=findAll';
                            });
                             
                             $("#btnSavePurchase").click(function () {
                                 
                                  //if invalid do nothing
                                if(!$("#pickingAddForm").validationEngine('validate')){
                        
                                    $("#dialog-incomplete").dialog({
                                                open: function () {
                                                    $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
                                                    $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
                                                },
                                                title: 'Incomplete Form',
                                                resizable: false,
                                                height: 120,
                                                modal: true,
                                                buttons: {
                                                    "Ok" : function () {
                                                        $(this).dialog("close");
                                                    }
                                                }
                                            });
                                        return false;
                                 }
                                 
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
                        
        <div id="dialog2" title="Tallyman Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
                        
        <div id="dialog3" title="Tallyman Search" style="display:none;z-index:1;">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
                                        
        <div id="dialog-confirm" title="Product Search" style="display:none;z-index:1;">
            Save Data?
        </div>
                 
                 
        <div id="dialog-incomplete" title="Product Search" style="display:none;z-index:1;">
            Tolong Lengkapi Semua Data
        </div>
                 
                        
    </body>
</html>