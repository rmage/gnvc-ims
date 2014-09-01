<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    Date cDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfPicker = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Booked Order &therefore; Create</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker { display: none; }
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
            }
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
                    <form action="#" id="fBor" name="fBor" method="post">
                        <input type="hidden" id="borDate" name="borDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Booked Order &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 175px;">Bor Code</td>
                                    <td><input tabindex="1" type="text" id="borCode" name="borCode" required="required" onblur="validateIt(this)" data-type="string" /></td>
                                    <td>Bor Date</td>
                                    <td><input tabindex="2" type="text" id="borDatePicker" name="borDatePicker" size="10" required="required" value="<%=sdfPicker.format(cDate)%>" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="reset" value="Cancel" name="btnCancel" onclick="window.location.replace('FGBookedOrder.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm">
                        <caption>Booked Order &therefore; Detail</caption>
                        <tbody>
                            <tr>
                                <th colspan="4"><h2>#1 Data</h2></th>
                        </tr>
                        <tr>
                            <td style="width: 137px">Pack Style</td>
                            <td>
                                <input tabindex="3" id="i0" type="text" size="10" />
                                Item:
                                <select id="i24">
                                    
                                </select>
                            </td>
                            <td>Drained / Pressed Wt</td>
                            <td><input tabindex="15" id="i12" type="text" size="3" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Can Size / NW</td>
                            <td>
                                <input tabindex="4" id="i1"  type="text" size="3" />
                                <input tabindex="5" id="i2" tabindex="4" type="text" size="7" value="0" />
                            </td>
                            <td>Freight Terms</td>
                            <td><input tabindex="16" id="i13" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Quantity (FCL)</td>
                            <td><input tabindex="6" id="i3" type="text" size="1" value="0" /> FCL</td>
                            <td>Payment Terms</td>
                            <td><input tabindex="17" id="i14" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Cases per FCL</td>
                            <td><input tabindex="7" id="i4" type="text" size="3" value="0" /> Cs</td>
                            <td>GSP Form</td>
                            <td><input tabindex="18" id="i15" type="text" /></td>
                        </tr>
                        <tr>
                            <td>CNF Price per Case</td>
                            <td><input tabindex="8" id="i5" type="text" value="0" /></td>
                            <td>Can Code Max per FCL</td>
                            <td><input tabindex="19" id="i16" type="text" size="3" value="1" /> CAN CODES</td>
                        </tr>
                        <tr>
                            <td>Commission</td>
                            <td><input tabindex="9" id="i6" type="text" /></td>
                            <td>Oil Medium</td>
                            <td><input tabindex="20" id="i17" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Buyer</td>
                            <td><input tabindex="10" id="i7" type="text" size="30" /></td>
                            <td>GMO / Non GMO</td>
                            <td><input tabindex="21" id="i18" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Label / Brand</td>
                            <td><input tabindex="11" id="i8" type="text" /></td>
                            <td>Extra Cartons Per FCL</td>
                            <td><input tabindex="22" id="i19" type="text" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Shipment Date</td>
                            <td><input tabindex="12" id="i9" type="text" size="30" /></td>
                            <td>Percents Flake</td>
                            <td><input tabindex="23" id="i20" type="text" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Destination Port</td>
                            <td><input tabindex="13" id="i10" type="text" /></td>
                            <td>Oil Water Ratio</td>
                            <td><input tabindex="24" id="i21" type="text" /></td>
                        </tr>
                        <tr>
                            <td>PO Number</td>
                            <td><input tabindex="14" id="i11" type="text" /></td>
                            <td>Number of Loins</td>
                            <td><input tabindex="25" id="i22" type="text" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Additional Info</td>
                            <td colspan="3"><input tabindex="26" id="i23" type="text" size="50" /></td>
                        </tr>
                        <!------------------------------------------------->
                        <!--#2--------------------------------------------->
                        <!------------------------------------------------->
                        <tr>
                            <th colspan="4"><h2>#2 Data</h2></th>
                        </tr>
                        <tr>
                            <td style="width: 137px">Pack Style</td>
                            <td>
                                <input tabindex="27" id="j0" type="text" size="10" />
                                Item:
                                <select id="j24">
                                    
                                </select>
                            </td>
                            <td>Drained / Pressed Wt</td>
                            <td><input tabindex="39" id="j12" type="text" size="3" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Can Size / NW</td>
                            <td>
                                <input tabindex="28" id="j1" type="text" size="3" />
                                <input tabindex="29" id="j2" type="text" size="7" value="0" />
                            </td>
                            <td>Freight Terms</td>
                            <td><input tabindex="40" id="j13" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Quantity (FCL)</td>
                            <td><input tabindex="30" id="j3" type="text" size="1" value="0" /> FCL</td>
                            <td>Payment Terms</td>
                            <td><input tabindex="41" id="j14" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Cases per FCL</td>
                            <td><input tabindex="31" id="j4" type="text" size="3" value="0" /> Cs</td>
                            <td>GSP Form</td>
                            <td><input tabindex="42" id="j15" type="text" /></td>
                        </tr>
                        <tr>
                            <td>CNF Price per Case</td>
                            <td><input tabindex="32" id="j5" type="text" value="0" /></td>
                            <td>Can Code Max per FCL</td>
                            <td><input tabindex="43" id="j16" type="text" size="3" value="1" /> CAN CODES</td>
                        </tr>
                        <tr>
                            <td>Commission</td>
                            <td><input tabindex="33" id="j6" type="text" /></td>
                            <td>Oil Medium</td>
                            <td><input tabindex="44" id="j17" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Buyer</td>
                            <td><input tabindex="34" id="j7" type="text" size="30" /></td>
                            <td>GMO / Non GMO</td>
                            <td><input tabindex="45" id="j18" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Label / Brand</td>
                            <td><input tabindex="35" id="j8" type="text" /></td>
                            <td>Extra Cartons Per FCL</td>
                            <td><input tabindex="46" id="j19" type="text" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Shipment Date</td>
                            <td><input tabindex="36" id="j9" type="text" size="30" /></td>
                            <td>Percents Flake</td>
                            <td><input tabindex="47" id="j20" type="text" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Destination Port</td>
                            <td><input tabindex="37" id="j10" type="text" /></td>
                            <td>Oil Water Ratio</td>
                            <td><input tabindex="48" id="j21" type="text" /></td>
                        </tr>
                        <tr>
                            <td>PO Number</td>
                            <td><input tabindex="38" id="j11" type="text" /></td>
                            <td>Number of Loins</td>
                            <td><input tabindex="49" id="j22" type="text" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Additional Info</td>
                            <td colspan="3"><input tabindex="50" id="j23" type="text" size="50" /></td>
                        </tr>
                        <!------------------------------------------------->
                        <!--#3--------------------------------------------->
                        <!------------------------------------------------->
                        <tr>
                            <th colspan="4"><h2>#3 Data</h2></th>
                        </tr>
                        <tr>
                            <td style="width: 137px">Pack Style</td>
                            <td>
                                <input tabindex="51" id="k0" type="text" size="10" />
                                Item:
                                <select id="k24">
                                    
                                </select>
                            </td>
                            <td>Drained / Pressed Wt</td>
                            <td><input tabindex="63" id="k12" type="text" size="3" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Can Size / NW</td>
                            <td>
                                <input tabindex="52" id="k1" type="text" size="3" />
                                <input tabindex="53" id="k2" type="text" size="7" value="0" />
                            </td>
                            <td>Freight Terms</td>
                            <td><input tabindex="64" id="k13" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Quantity (FCL)</td>
                            <td><input tabindex="54" id="k3" type="text" size="1" value="0" /> FCL</td>
                            <td>Payment Terms</td>
                            <td><input tabindex="65" id="k14" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Cases per FCL</td>
                            <td><input tabindex="55" id="k4" type="text" size="3" value="0" /> Cs</td>
                            <td>GSP Form</td>
                            <td><input tabindex="66" id="k15" type="text" /></td>
                        </tr>
                        <tr>
                            <td>CNF Price per Case</td>
                            <td><input tabindex="56" id="k5" type="text" value="0" /></td>
                            <td>Can Code Max per FCL</td>
                            <td><input tabindex="67" id="k16" type="text" size="3" value="1" /> CAN CODES</td>
                        </tr>
                        <tr>
                            <td>Commission</td>
                            <td><input tabindex="57" id="k6" type="text" /></td>
                            <td>Oil Medium</td>
                            <td><input tabindex="68" id="k17" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Buyer</td>
                            <td><input tabindex="58" id="k7" type="text" size="30" /></td>
                            <td>GMO / Non GMO</td>
                            <td><input tabindex="69" id="k18" type="text" /></td>
                        </tr>
                        <tr>
                            <td>Label / Brand</td>
                            <td><input tabindex="59" id="k8" type="text" /></td>
                            <td>Extra Cartons Per FCL</td>
                            <td><input tabindex="70" id="k19" type="text" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Shipment Date</td>
                            <td><input tabindex="60" id="k9" type="text" size="30" /></td>
                            <td>Percents Flake</td>
                            <td><input tabindex="71" id="k20" type="text" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Destination Port</td>
                            <td><input tabindex="61" id="k10" type="text" /></td>
                            <td>Oil Water Ratio</td>
                            <td><input tabindex="72" id="k21" type="text" /></td>
                        </tr>
                        <tr>
                            <td>PO Number</td>
                            <td><input tabindex="62" id="k11" type="text" /></td>
                            <td>Number of Loins</td>
                            <td><input tabindex="73" id="k22" type="text" value="0" /></td>
                        </tr>
                        <tr>
                            <td>Additional Info</td>
                            <td colspan="3"><input tabindex="74" id="k23" type="text" size="50" /></td>
                        </tr>
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
            //  BIND | Date Picker to bor date
            $('#borDatePicker').datepicker({
                dateFormat: "dd/mm/yy", 
                altFormat: "yy-mm-dd",
                altField: "#borDate",
                changeYear: true, 
                changeMonth: true
            });

            //  BIND | KEYDOWN and AUTOCOMPLETE in packstyle field
            $("#i0,#j0,#k0").each(function() {
                var $o = $(this);
                var $i = $("#" + $o.attr("id").substring(0, 1) +  "24");
                $o.bind("keyup", function() {
                    $o.data("id", "");
                    $i.html("");
                });
                $o.autocomplete({
                    source: "?action=getPackStyle",
                    minLength: 3,
                    delay: 1000,
                    select: function(event, ui) {
                        $o.data("id", ui.item[1]);
                        $o.val(ui.item[2]);
                        
                        // ITEM | Get item list from selected pack_style
                        $.ajax({
                            url: "?", type: "post",
                            data: {action: "getItem", key: $o.data("id")},
                            dataType: "json",
                            success: function(json) {
                                $i.html("");
                                for (var i = 0; i < json.length; i++) {
                                    $i.append('<option value="' + json[i][1] + '">' + json[i][2] + ' | ' + json[i][3] + '</option>');
                                }
                            }
                        });
                        return false;
                    }
                }).data('autocomplete')._renderItem = function(ul, item) {
                    return $('<li>')
                            .data("item", item)
                            .append('<a><b>' + item[2] + '(' + item[3] + ') </b><br />Quantity Per CS: ' + item[4] + '</a></li>')
                            .appendTo(ul);
                };
            });

            //  BIND | Form on save function
            $("#fBor").bind("submit", function() {
                var data = "";
                $("#i0,#j0,#k0").each(function() {
                    if ($(this).data("id") !== "" && $(this).data("id") !== undefined) {
                        data = data + $("#borCode").val() + "^" + $("#borDate").val() + "^";

                        var prefix = $(this).attr("id").substring(0, 1);
                        data = data + $(this).data("id") + "^";
                        for (var i = 1; i < 25; i++) {
                            data = data + $("#" + prefix + i).val() + "^";
                        }
                        data = data + "~*";
                    }
                });
                
                if (data !== "") {
                    window.location.replace("?action=save&data=" + data + "@");
                }
                
                return false;
            });

            //  FUNCTION | Validation on value
            var format = {
                string: "^((?!\").)*$",
                test: function(f, v) {
                    return (new RegExp(f).test(v));
                }
            };

            function validateIt(e) {
                if (!$(e).data("type")) {
                    alert("Tell IT Department about this issue!");
                    return;
                }

                var $e = $(e);
                if (!format.test(format[$e.data("type")], $e.val())) {
                    $e.val("");
                }
            }
        </script>
    </body>
</html>
