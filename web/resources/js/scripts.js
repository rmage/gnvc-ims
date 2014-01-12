// JavaScript Document
function validateUser(username){	
    if (username == 'admin') {		//scm
        redirect('main_admin.html');
    }

    else if (username == 'scm') {		//scm
        redirect('main_scm.html');
    }
    else if (username == 'acc') {	//accounting
        redirect('main_acc.html');
    }
    else if (username == 'ic') {	//accounting
        redirect('main_ic.html');
    }
    //WIC
    else if (username == 'wicadmin') {	//admin wic
        redirect('main_wic_admin.html');
    }
    else if (username == 'wicbm') {		//branch manager
        redirect('main_wic_bm.html');
    }
    else if (username == 'wicsm'){		//store manager
        redirect('main_wic_sm.html');
    }
    else if (username == 'wicspv'){		//store supervisor
        redirect('main_wic_spv.html');
    }
    else if (username == 'wicsa') {		//store agent
        redirect('main_wic_sa.html');
    }
    else if (username == 'picservice'){
        redirect('main_pic_repair_service.html');
    }
	
    //DSA
    else if (username == 'dsaadmin') {	//admin wic
        redirect('main_dsa_admin.html');
    }
    else if (username == 'dsabm') {		//branch manager
        redirect('main_dsa_bm.html');
    }
    else if (username == 'dsasm'){		//store manager
        redirect('main_dsa_sm.html');
    }
    else if (username == 'dsaspv'){		//store supervisor
        redirect('main_dsa_spv.html');
    }
    else if (username == 'dsasa') {		//store agent
        redirect('main_dsa_sa.html');
    }
    else
    {
        alert("username or passsword doesn\'t exist");
    }
}

function redirect(URLStr) { 
    document.location = URLStr;
}

function loadPage(URLStr){
    document.getElementById("myFrame").src=URLStr;
}

function loadFromParentPage(URLStr){
    parent.document.getElementById("myFrame").src=URLStr;
}

function logout(){
    if (confirm("Are you sure to logout")) {
        redirect('index.html');
    }
}

/* toggle layer */
function toggleLayer( whichLayer )
{
    var elem, vis;
    if( document.getElementById ) // this is the way the standards work
        elem = document.getElementById( whichLayer );
    else if( document.all ) // this is the way old msie versions work
        elem = document.all[whichLayer];
    else if( document.layers ) // this is the way nn4 works
        elem = document.layers[whichLayer];
    vis = elem.style;
    // if the style.display value is blank we try to figure it out here
    if(vis.display==''&&elem.offsetWidth!=undefined&&elem.offsetHeight!=undefined)
        vis.display = (elem.offsetWidth!=0&&elem.offsetHeight!=0)?'block':'none';
    vis.display = (vis.display==''||vis.display=='block')?'none':'block';
}

/* function open window */
function openWindow(url,width,height)
{
    if (width == null) {
        width=800;
        height=500;
    }
    if (window.showModalDialog) { //MSIE
        window.showModalDialog(url,"", "dialogWidth:" + width + "px;dialogHeight:" + height + "px;center:yes;scroll:no;resizeable:no;status=no;");
    }
    else {
        var left = parseInt((screen.availWidth/2) - (width/2));
        var top = parseInt((screen.availHeight/2) - (height/2));
        var windowFeatures = "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top + "screenX=" + left + ",screenY=" + top + ",toolbar=false,menubar=false,scrollbars=auto,modal=yes";
        alert(windowFeatures);
        childWindow = window.open(url,'name',windowFeatures);
        childWindow.focus();
    }
//childWindow = window.open(url,'','toolbar=false,menubar=false,scrollbars=auto');
}

/* overlay */
// (make it last for 3 seconds)
function showOverlay(){
    var myOverlay = (new documentOverlay()).init();
    document.onkeyup = function(event){
        var keycode = window.event ? window.event.keyCode : event.which;
        // 27 === Esc. keycode
        if (keycode == 27) {
            myOverlay.remove();
        }
    }
}

/*
function documentOverlay() {
    // @version 0.12
    // @author James Padolsey
    // @info http://james.padolsey.com/javascript/the-perfect-document-overlay/
    
    // Shortcut to current instance of object:
    var instance = this,
    
    // Cached body height:
    bodyHeight = (function(){
        return getDocDim('Height','min');    
    })();
    
    // CSS helper function:
    function css(el,o) {
        for (var i in o) { el.style[i] = o[i]; }
        return el;
    };
    
    // Document height/width getter:
    function getDocDim(prop,m){
        m = m || 'max';
        return Math[m](
            Math[m](document.body["scroll" + prop], document.documentElement["scroll" + prop]),
            Math[m](document.body["offset" + prop], document.documentElement["offset" + prop]),
            Math[m](document.body["client" + prop], document.documentElement["client" + prop])
	);
    }
    
    // get window height: (viewport):
    function getWinHeight() {
        return window.innerHeight ||
                (document.compatMode == "CSS1Compat" && document.documentElement.clientHeight || document.body.clientHeight);
    }
    
    // Public properties:
    
    // Expose CSS helper, for public usage:
    this.css = function(o){
        css(instance.element, o);
        return instance;
    };
    
    // The default duration is infinity:
    this.duration = null;
    
    // Creates and styles new div element:
    this.element = (function(){
        return css(document.createElement('div'),{
            width: '100%',
            height: getDocDim('Height') + 'px',
            position: 'absolute', zIndex: 999,
            left: 0, top: 0,
            cursor: 'wait'
        });
    })();
    
    // Resize cover when window is resized:
    window.onresize = function(){
        
        // No need to do anything if document['body'] is taller than viewport
        if(bodyHeight>getWinHeight()) { return; }
        
        // We need to hide it before showing
        // it again, due to scrollbar issue.
        instance.css({display: 'none'});
        setTimeout(function(){
            instance.css({
                height: getDocDim('Height') + 'px',
                display: 'block'
            });
        }, 10);
        
    };
    
    // Remove the element:
    this.remove = function(){
        this.element.parentNode && this.element.parentNode.removeChild(instance.element);
    };
    
    // Show element:
    this.show = function(){};
    
    // Event handling helper:
    this.on = function(what,handler){
        what.toLowerCase() === 'show' ? (instance.show = handler)
        : instance.element['on'+what] = handler;
        return instance;
    };
    
    // Begin:
    this.init = function(duration){
    
        // Overwrite duration if parameter is supplied:
        instance.duration = duration || instance.duration;
        
        // Inject overlay element into DOM:
        document.getElementsByTagName('body')[0].appendChild(instance.element);
        
        // Run show() (by default, an empty function):
        instance.show.call(instance.element,instance);
        
        // If a duration is supplied then remove element after
        // the specified amount of time:
        instance.duration && setTimeout(function(){instance.remove();}, instance.duration);
        
        // Return instance, for reference:
        return instance;
        
    };
    
}
*/


function calculateInvoice()
{
    var quantity = document.getElementById('quantity').value;
    var totalPriceVat = document.getElementById('totalPriceVat');
    var price = document.getElementById('price').value;
    var vat = document.getElementById('vat').value;
    var finalPrice = (price * quantity) + (price * quantity * vat);
    document.getElementById('totalPriceVat').value=finalPrice;

}

function calculateFormInvoice()
{
    var total = 0;
    var frm = document.getElementById('formInvoice');
    for (var i=0; i < frm.elements.length; ++i) {

        form_field = frm.elements[i]
        form_name = form_field.name
        if (form_name == 'price') {
            total+=parseInt(form_field.value);
        }
    }
    document.getElementById('totalPriceVat').value=total;
}
/*
 *below for agent out
 */
function calculateAgentOutProduct()
{
    var quantity = document.getElementById('quantity').value;
    var totalPriceVat = document.getElementById('totalPriceVat');
    var price = document.getElementById('price').value;
    var vat = document.getElementById('vat').value;
    var finalPrice = (price * quantity) + (price * quantity * vat);
    document.getElementById('totalPriceVat').value=finalPrice;

}

function calculateFormAgentOutDtl()
{
    var total = 0;
    var frm = document.getElementById('formAgentOutDtl');
    for (var i=0; i < frm.elements.length; ++i) {

        form_field = frm.elements[i]
        form_name = form_field.name
        if (form_name == 'price') {
            total+=parseInt(form_field.value);
        }
    }
    document.getElementById('totalPriceVat').value=total;
    document.getElementById('outAmount').value=total;
}

//function calculateAgentInProduct()
//{
//    var quantity = document.getElementById('quantity').value;
//    var totalPriceVat = document.getElementById('totalPriceVat');
//    var price = document.getElementById('price').value;
//    var vat = document.getElementById('vat').value;
//    var finalPrice = (price * quantity) + (price * quantity * vat);
//    document.getElementById('totalPriceVat').value=finalPrice;
//
//}
//function calculateFormAgentIntDtl()
//{
//    var total = 0;
//    var frm = document.getElementById('formAgentInDtl');
//    for (var i=0; i < frm.elements.length; ++i) {
//
//        form_field = frm.elements[i]
//        form_name = form_field.name
//        if (form_name == 'price') {
//            total+=parseInt(form_field.value);
//        }
//    }
//    document.getElementById('totalPriceVat').value=total;
//    document.getElementById('outAmount').value=total;
//}

function gotoPage (page)
{
    document.location.href = page;
}

/* popup search test */
function ShowSearch(ctrlID, dest)
{
    var retval= new Array(1);
    retval=window.showModalDialog(dest,window, "resizable:yes;dialogWidth:700px;dialogHeight:650px");
    //retval=window.open(dest);
    if(retval!=null)
    {
        //alert(retval[0]);
        ctrlID.value = retval[0];
    }
}

function ShowSearchProductAll()
{
    targetitem = document.getElementById('itemCode');
    dataitem = window.open("Product.htm?mode=popup&type=all", "dataitem", "toolbar=no,menubar=no,scrollbars=yes,width=750px,height=650px");
    dataitem.moveTo(0,0);
    dataitem.targetitem = targetitem;
}

function ShowSearchProductStock(stockCode, whCode)
{
    targetitem = document.getElementById('itemCode');
    dataitem = window.open("Product.htm?mode=popup&type=stock&stockCode=" + stockCode + "&whCode=" + whCode, "dataitem", "toolbar=no,menubar=no,scrollbars=yes,width=750px,height=650px");
    dataitem.moveTo(0,0);
    dataitem.targetitem = targetitem;
}

function ShowSearchCustomer()
{
    targetitemId = document.getElementById('customerId');
    targetitemName = document.getElementById('customerName');
    dataitem = window.open("Customer.htm?mode=popup", "dataitem", "toolbar=no,menubar=no,scrollbars=yes,width=750px,height=650px");
    dataitem.moveTo(0,0);
    dataitem.targetitemId = targetitemId;
    dataitem.targetitemName = targetitemName;
}

function ShowSearchCustomerRepair()
{
    targetitemId = document.getElementById('customerId');
    targetitemName = document.getElementById('customerName');
    targetitemPhone = document.getElementById('customerPhone');
    targetitemEmail = document.getElementById('customerEmail');
    targetitemAddress = document.getElementById('customerAddress');
    targetitemStatus = document.getElementById('customerStatus');
    dataitem = window.open("Customer.htm?mode=popup&for=repair", "dataitem", "toolbar=no,menubar=no,scrollbars=yes,width=750px,height=650px");
    dataitem.moveTo(0,0);
    dataitem.targetitemId = targetitemId;
    dataitem.targetitemName = targetitemName;
    dataitem.targetitemEmail = targetitemEmail;
    dataitem.targetitemPhone = targetitemPhone;
    dataitem.targetitemAddress = targetitemAddress;
    dataitem.targetitemStatus = targetitemStatus;
    
}

/*
 *  December 04, 2011
 */

function csbShowDetail(flag, params, title){
	if(flag == 0 && params == 0){
		$('#dtl-panel').fadeOut(500, function(){
                    $('#dtl-panel').html('');
                });
	} else{
            if(flag == "IR" || flag == "RA" || flag == "GR" || flag == "SR" || 
               flag == "REPAIR" || flag == "SO" || flag == "PO" || 
               flag == "putaway" || flag == "PICK" || flag == "KITT" || 
               flag == "PACK" || flag == "CONS" || flag == "PRS" ||
               flag == "DELI" || flag == "BU"){
	                if($('#dtl-panel').length == 0){
		                    $('body').append('<div id="dtl-panel" class="div-dtl" ondblclick="csbShowDetail(0, 0)"></div>');
		                    $('#dtl-panel').css('width', $(window).width() - 12);
		                    $('#dtl-panel').fadeOut(0, null);
		                    $('#dtl-panel').fadeIn(500, function(){
		                        $('#dtl-panel').html('<center><img src="resources/img/load-spin.gif" /></center>');
		                        csbShowDetailImpl(flag, params, title);
		                    });
					} else{
		                    $('#dtl-panel').fadeIn(500, function(){
		                        $('#dtl-panel').html('<center><img src="resources/img/load-spin.gif" /></center>');
		                        csbShowDetailImpl(flag, params, title);
		                    });                        
					}
            }             
	}
}

function csbShowDetailImpl(flag, params, title){
	
	if (flag == 'DELI'){
        $.ajax({
            url: "Delivery.htm",
            data: "action=ajaxDocument&deliveryNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
	}
	else if (flag == 'PRS'){
        $.ajax({
            url: "PurchaseRequisition.htm",
            data: "action=ajaxDocument&prsNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
	}
	else if (flag == 'FWS'){
        $.ajax({
            url: "FishWs.htm",
            data: "action=ajaxDocument&prsNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
	}
	else if (flag == 'FRR'){
        $.ajax({
            url: "FishRr.htm",
            data: "action=ajaxDocument&prsNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
	}
	else if (flag == 'CONS'){
        $.ajax({
            url: "Consolidate.htm",
            data: "action=ajaxDocument&consolidateNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
	}
	else if (flag == 'PACK'){
        $.ajax({
            url: "Packing.htm",
            data: "action=ajaxDocument&packingNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
	}
	else if (flag == 'KITT'){
        $.ajax({
            url: "Kitting.htm",
            data: "action=ajaxDocument&kittingNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
	}
	else if (flag == 'PICK'){
        $.ajax({
            url: "PickingAdm.htm",
            data: "action=ajaxDocument&pickingNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
	}else if (flag == 'SO'){
        $.ajax({
            url: "SalesOrder.htm",
            data: "action=ajaxDocument&salesOrderNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
    }else if (flag == 'IR'){
        $.ajax({
            url: "IrMst.htm",
            data: "action=ajaxDocument&documentNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
    } else if (flag == "RA"){
        $.ajax({
            url: "IrApproval.htm",
            data: "action=ajaxDocument&documentNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
    } else if (flag == "GR"){
        $.ajax({
            url: "GoodReceive.htm",
            data: "action=ajaxDocument&grNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
    } 
    else if(flag == "SR"){
    	alert("*** SOD ***");
        $.ajax({
            url: "SalesOrder.htm",
            data: "action=ajaxDocument&salesOrderNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        }); 
    } else if(flag == "REPAIR"){
        $.ajax({
            url: "Repair.htm",
            data: "action=getRepairDetail&documentNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        });
    } else if(flag == "putaway"){
        $.ajax({
            url: "Putaway.htm",
            data: "action=ajaxDocument&ponumber=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        });
    } else if(flag == "PO"){
        $.ajax({
            url: "Purchase.htm",
            data: "action=ajaxDocument&poNo=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        });
    }
    else if(flag == "BU"){
        $.ajax({
            url: "Bundle.htm",
            data: "action=ajaxDocument&bundleCode=" + params,
            success: function(html){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(html);
            },
            error: function(jqXHR, textStatus){
                $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                $('#dtl-panel').append(jqXHR + " - " + textStatus);
            }
        });
    }
}

function csbCombo(params, action){
    if(params == 'IR'){
        switch(action){
            case '0':{
                    $('#csbSearchDiv').html('<input type="text" name="documentNo" value="" /><input class="datetime" type="hidden" id="documentStartDate" name="documentStartDate" value="" />&nbsp;s.d.&nbsp;'
                        + '<input class="datetime" type="hidden" id="documentEndDate" name="documentEndDate" value="" />');
            }break;
            case '1':{
                    $('#csbSearchDiv').html('<input type="hidden" name="documentNo" value="" /><input class="datetime" type="text" id="documentStartDate" name="documentStartDate" value="" />&nbsp;s.d.&nbsp;'
                        + '<input class="datetime" type="text" id="documentEndDate" name="documentEndDate" value="" />');
                    $('#documentStartDate').mask('9999-99-99 99:99');
                    $('#documentEndDate').mask('9999-99-99 99:99');
            }break;
            case '2':{
                    $('#csbSearchDiv').html(' - not impemented yet - ');
            }break;
            default:{
                    $('#csbSearchDiv').html(' - something wrong - ');
            }
        }
    } else if(params == 'IRA'){
        switch(action){
            case '0':{
                    $('#csbSearchDiv').html('<input type="text" name="documentNo" value="" /><input class="datetime" type="hidden" id="documentStartDate" name="documentStartDate" value="" />&nbsp;s.d.&nbsp;'
                        + '<input class="datetime" type="hidden" id="documentEndDate" name="documentEndDate" value="" />'
                        + '<input type="hidden" name="businessGroupName" value="" />');
            }break;
            case '1':{
                    $('#csbSearchDiv').html('<input type="hidden" name="documentNo" value="" /><input class="datetime" type="text" id="documentStartDate" name="documentStartDate" value="" />&nbsp;s.d.&nbsp;'
                        + '<input class="datetime" type="text" id="documentEndDate" name="documentEndDate" value="" />'
                        + '<input type="hidden" name="businessGroupName" value="" />');
                    $('#documentStartDate').mask('9999-99-99 99:99');
                    $('#documentEndDate').mask('9999-99-99 99:99');
            }break;
            case '2':{
                    $('#csbSearchDiv').html('<input type="hidden" name="documentNo" value="" /><input class="datetime" type="hidden" id="documentStartDate" name="documentStartDate" value="" />&nbsp;s.d.&nbsp;'
                        + '<input class="datetime" type="hidden" id="documentEndDate" name="documentEndDate" value="" />'
                        + '<select type="text" name="transType">'
                        + '<option value="0">Inventory Request</option>'
                        + '<option value="1">Stoct Return</option>'
                        + '<option value="2">Stoct Adjustment Credit</option>'
                        + '<option value="3">Stoct Moving</option>'
                        + '</select><input type="hidden" name="businessGroupName" value="" />');
                    $('#documentStartDate').mask('9999-99-99 99:99');
                    $('#documentEndDate').mask('9999-99-99 99:99');
            }break;
            case '3':{
                    $('#csbSearchDiv').html('<input type="hidden" name="documentNo" value="" /><input class="datetime" type="hidden" id="documentStartDate" name="documentStartDate" value="" />&nbsp;s.d.&nbsp;'
                        + '<input class="datetime" type="hidden" id="documentEndDate" name="documentEndDate" value="" />'
                        + '<input type="text" name="businessGroupName" value="" />');
                    $('#documentStartDate').mask('9999-99-99 99:99');
                    $('#documentEndDate').mask('9999-99-99 99:99');
            }break;
            default:{
                    $('#csbSearchDiv').html(' - something wrong - ');
            }
        }
    }
}