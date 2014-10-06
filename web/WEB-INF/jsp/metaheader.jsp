<link href="resources/img/favicon.ico" type="image/x-icon" rel="icon" />
<link href="resources/img/favicon.ico" type="image/x-icon" rel="shortcut icon" />
<link rel="stylesheet" type="text/css" href="resources/jcss/reset.css" />
<link rel="stylesheet" type="text/css" href="resources/jcss/screen.css" media="screen, projection" />
<link rel="stylesheet" type="text/css" href="resources/jcss/print.css" media="print" />
<!--[if IE]><link rel="stylesheet" href="../css/ie.css" type="text/css" media="screen, projection"><![endif]-->
<link rel="stylesheet" type="text/css" href="resources/jcss/style.css" />
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
    $('.myhover').live("mouseenter", function() {
        $(this).css('background-color', '#EFEFEF');
    })
            .live("mouseleave", function() {
                $(this).css("background-color", "");
            });

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
            $th.val($th.val().replace(/[^0-9]/g, function(str) {
                return '';
            }));
        });
    });
</script>

<!-- delete confirmation -->
<script type="text/javascript">
    $(document).ready(function() {
        $('.urlDelete').click(function(event) {
            event.preventDefault();
            var url = $(this).attr('href');
            $("#delete-confirm").dialog({
                width: 300,
                height: 150,
                position: "center",
                modal: true,
                buttons: {
                    "Cancel": function() {
                        $(this).dialog("close");
                    },
                    "Ok": function() {
                        location.href = url;
                    }
                },
                title: 'Confirm'});
        });
    });
</script>

<!-- style for ajax detail panel -->
<style type="text/css">
    .panel-head {background:#77d5f7}
    .panel-head td {font-weight: bold}
    .panel-row-odd {background: #EEEEEE}
</style>

<!-- Global Alert -->
<div id="dialog-confirm1" title="Product Search" style="display:none;z-index:1;">
    Save Data?
</div>
<div id="dialog-incomplete1" title="Product Search" style="display:none;z-index:1;">
    Tolong Lengkapi Semua Data
</div>
<div id="delete-confirm" title="confirm" style="display:none;z-index:1;">
    Delete this item?
</div>
<div id="dialog-insufficient" title="Insufficient Balance" style="display:none;z-index:1;">
    Insufficient amount in your storage
</div>

<%-- DEFAULT THEME | Add something new here --%>
<!-- JQUERY | Version 2.1.1 -->
<script src="resources/default/js/jquery-2.1.1.min.js"></script>

<!-- NOTIFICATION | IMS notification module -->
<style>
    div#fny2-notification-modules {
        -moz-user-select: none;
        position: fixed;
        right: 20px;
        top: 0;
        width: 300px;
    }

    .fny2-notification {
        background-color: #ffffff;
        border-bottom: 2px solid #0066ff;
        border-left: 2px solid #ffffff;
        border-radius: 5px;
        border-right: 2px solid #ffffff;
        height: 0;
        overflow-y: auto;
        transition: all 1s ease-out 0s;
    }

    #fny2-notification-modules ul {
        margin: 0;
        padding: 0;
    }

    .fny2-notification-button {
        background-color: #2288ff;
        border-bottom: 2px solid #ffffff;
        border-left: 2px solid #ffffff;
        border-radius: 0 0 5px 5px;
        border-right: 2px solid #ffffff;
        cursor: pointer;
    }

    .fny2-notification-button span,
    .fny2-notification-quantity {
        color: #ffffff;
        display: inline-block;
        font-size: 14px;
        font-weight: 600;
        padding: 3px 0 3px 8px;
    }

    .fny2-notification-quantity {
        bottom: -4px;
        overflow: hidden;
        position: absolute;
        right: 0;
        text-align: center;
        width: 18px;
        z-index: 51;
    }

    .fny2-notification-button > img {
        bottom: -7px;
        position: absolute;
        right: -7px;
        z-index: 50;
    }

    .fny2-notification-button > img.roll {
        animation: fny2-notification-roll 3s infinite;
    }

    @keyframes fny2-notification-roll {
        0% {
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }
</style>
<script>
    jq2 = jQuery.noConflict(true);
    jq2(function() {
        jq2("body").append(
                '<div id="fny2-notification-modules" title="You have unread notification(s)">' +
                '<div class="fny2-notification"><ul></ul></div>' +
                '<div class="fny2-notification-button" onclick="fny2_notification_fnc.showHide()">' +
                '<span>Notification</span>' +
                '<div class="fny2-notification-quantity"><div style="display: inline-block;">99</div></div>' +
                '<img src="resources/default/images/fny2-icon-notification.png" class="roll!" width="32">' +
                '</div>' +
                '</div>');
    });

    var fny2_notification_fnc = {
        content: "",
        showHide: function() {
            if (fny2_notification_fnc.content === "") {
                fny2_notification_fnc.init();
            }
            
            if (fny2_notification_fnc.content.height() === 0) {
                fny2_notification_fnc.content.height(500);
            } else {
                fny2_notification_fnc.content.height(0);
            }
        },
        init: function() {
            fny2_notification_fnc.content = jq2(".fny2-notification");
        }
    };
</script>
