<link href="resources/img/favicon.ico" type="image/x-icon" rel="icon" />
<link href="resources/img/favicon.ico" type="image/x-icon" rel="shortcut icon" />
<link rel="stylesheet" type="text/css" href="resources/jcss/reset.css" />
<link rel="stylesheet" type="text/css" href="resources/jcss/screen.css" media="screen, projection" />
<link rel="stylesheet" type="text/css" href="resources/jcss/print.css" media="print" />
<!--[if IE]><link rel="stylesheet" href="../css/ie.css" type="text/css" media="screen, projection"><![endif]-->
<link rel="stylesheet" type="text/css" href="resources/jcss/stylies.css" />
<link rel="stylesheet" type="text/css" href="resources/jcss/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="resources/jcss/validationEngine.jquery.css" />
<link rel="stylesheet" type="text/css" href="resources/jcss/themes/redmond/jquery.ui.tabs.css" />

<script type="text/javascript" src="resources/jquery/jquery-1.4.2.min.js"></script>
<!--[if lte IE 8]><script type="text/javascript" src="../js/jquery.dropdown.js"></script><![endif]-->
<script type="text/javascript" src="resources/jquery/utils.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.validationEngine-id.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.validationEngine.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.init.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.autoNumeric.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.gritter.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-ui-timepicker-addon-0.5.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.cookies.2.2.0.js"></script>
<script src="resources/js/scripts.js" type="text/javascript"></script>

<!-- script type="text/javascript" src="resources/js/pagination.js"></script -->

<!--    
     jqgrid utility
-->
<link rel="stylesheet" type="text/css" media="screen" href="resources/jcss/ui.jqgrid.css" />
<script src="resources/js/jquery.jqGrid.min_1.js" type="text/javascript"></script>
<script src="resources/js/grid.locale-en.js" type="text/javascript"></script>

<style>
   .ui-autocomplete-loading {
       background: white url('resources/ui-anim_basic_16x16.gif') right center no-repeat;
   }
   .modal {
        display:    none;
        position:   fixed;
        z-index:    1000;
        top:        0;
        left:       0;
        height:     100%;
        width:      100%;
        background: rgba( 255, 255, 255, .8 ) 
                    url('resources/img/loading.gif') 
                    50% 50% 
                    no-repeat;
     }

     /* When the body has the loading class, we turn
       the scrollbar off with overflow:hidden */
     body.loading {
        overflow: hidden;   
     }

     /* Anytime the body has the loading class, our
       modal element will be visible */
     body.loading .modal {
        display: block;
     }
</style>

<script>
        $('.myhover').live("mouseenter", function() {$(this).css('background-color', '#EFEFEF');})
                                .live("mouseleave", function() {$(this).css("background-color","");}); 
                                
        $("body").on({
            ajaxStart: function() { 
                $(this).addClass("loading"); 
            },
            ajaxStop: function() { 
                $(this).removeClass("loading"); 
            }    
        });
</script>

<!--
    notification
-->
<script src="resources/jquery/jquery.pnotify.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen" href="resources/css/jquery.pnotify.default.css" />

<!-- integer validation 22 jan 2013 added by edw -->
<script>
     $(document).ready(function() {                 
        $('.intValidate').live("keyup", function() {
             var $th = $(this);
            $th.val( $th.val().replace(/[^0-9]/g, function(str) { return ''; } ) );
        });
     });   
</script>

<!-- Global Alert -->
<div id="dialog-confirm1" title="Product Search" style="display:none;z-index:1;">
    Save Data?
</div>
<div id="dialog-incomplete1" title="Product Search" style="display:none;z-index:1;">
    Tolong Lengkapi Semua Data
</div>
