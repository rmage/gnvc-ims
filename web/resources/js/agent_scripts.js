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

/*
 *below for agent in
 */

function calculateAgentInProduct()
{   alert('calculate totalPriceVatProduct');
    var quantity = document.getElementById('quantity').value;
    var totalPriceVat = document.getElementById('totalPriceVatProduct');
    var price = document.getElementById('price').value;
    var vat = document.getElementById('vat').value;
    var finalPrice = (price * quantity) + (price * quantity * vat);
    document.getElementById('totalPriceVatProduct').value=finalPrice;

}
function calculateFormAgentIntDtl()
{
    calculateAgentInProduct()
    alert('calculate price from list for Total Transaction ');
    var total = 0;
    var frm = document.getElementById('agentInForm');
    for (var i=0; i < frm.elements.length; ++i) {

        form_field = frm.elements[i]
        form_name = form_field.name
        if (form_name == 'price2') {
            total+=parseInt(form_field.value);
        }
    }
    document.getElementById('totalPriceVat').value=total;
    document.getElementById('outAmount').value=total;
}
