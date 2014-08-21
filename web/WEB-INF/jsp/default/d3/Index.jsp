<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; D3 &therefore; Data-Driven Documents</title>
        <%@include file="../../metaheader.jsp" %>
        <script src="resources/default/js/d3.min.js"></script>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
            }

            /*  CSS | D3 */
            .d3axis path, .d3axis line {
                fill: none;
                shape-rendering: crispedges;
                stroke: #000;
            }
            .d3line {
                fill: none;
                stroke: steelblue;
                stroke-width: 1.5px;
            }
            div.d3tooltip {
                background: none repeat scroll 0 0 lightsteelblue;
                border: 0 none;
                border-radius: 8px;
                font: 12px sans-serif;
                height: 28px;
                padding: 2px;
                pointer-events: none;
                position: absolute;
                text-align: center;
                width: 100px;
                z-index: 1003;
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
                    <form id="form" name="form" action="#">
                        <table class="collapse tblForm row-select">
                            <caption>D3 &therefore; Data-Driven Documents</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">D3</td>
                                    <td>
                                        <select id="d3" name="d3" required="required">
                                            <option value="">-- Select D3 to Show --</option>
                                            <option value="NFUnitPriceTrend">Item Unit Price Trend</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" id="btnSelect" name="btnSelect" value="Select" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>

                <div id="d3modal" title="Title"></div>
                <div id="d3tooltip" class="d3tooltip" style="opacity: 0;"></div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>

            //  D3 | Event change to remove parameter form
            $("#d3").bind("change", function() {
                $('#params').remove();
            });

            //  D3 | Action form on submit
            $('#form').bind('submit', function() {
                //  Validation | Make sure user select proper document
                if ($('#d3').val() === '') {
                    return;
                }

                //  AJAX | Request ui for that document
                $.ajax({
                    url: "?", type: "get",
                    data: {action: $("#d3").val(), mode: 0},
                    dataType: "json",
                    success: function(json) {
                        d3BuiltUI(json);
                    }
                });

                return false;
            });

            //  D3 | Built ui based on AJAX request
            function d3BuiltUI(json) {
                //  ELEMENT | Fade in then remove old parameter
                $('#params').remove();

                var html = '<form id="fD3"><table id="params" class="collapse tblForm row-select ui-widget-content"><caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl">D3 &therefore; Parameter</caption><tbody>';

                for (var i = 0; i < json.params.length; i++) {
                    html = html + '<tr><td' + (i === 0 ? ' style="width: 200px;"' : '') + '>' + json.params[i].title + '</td><td>';
                    switch (json.params[i].type) {
                        case 'text':
                            html = html + '<input type="text" id="' + json.params[i].name + '" name="' + json.params[i].name + '" size="' + json.params[i].size + '" required="required" /></td></tr>';
                            break;
                        case 'select' :
                            html = html + '<select id="' + json.params[i].name + '" name="' + json.params[i].name + '">';
                            for (var j = 0; j < json.params[i].options.length; j++) {
                                html = html + '<option value="' + json.params[i].options[j].value + '">' + json.params[i].options[j].html + '</option>';
                            }
                            html = html + '</select></td></tr>';
                            break;
                    }
                }

                //  ELEMENT | Append #content .box
                $('#content .box').append(html + '</tbody><tfoot class="ui-widget-header"><tr><td colspan="2"><input type="submit" value="D3 View" id="btnD3View" name="btnD3View" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;"></td></tr></tfoot></table></form>');

                //  DIALOG  | Prepared dialog
                $("#d3modal").dialog({
                    modal: true,
                    autoOpen: false,
                    resizable: false,
                    width: 1000,
                    height: 498,
                    minHeight: 396,
                    maxWidth: 1000,
                    buttons: {
                        OK: function() {
                            $(this).dialog("close");
                        }
                    },
                    create: function() {
                        $(this).css("maxHeight", 400);
                    },
                    open: function() {
                        //  D3 | Initialize
                        $(this).dialog("option", "title", $('#d3 option:selected').html() + " &therefore; " + $("#" + json.title).data("title"));
                        $("#d3modal").html("");
                        var
                                div = d3.select($('#d3tooltip')[0]),
                                formatTime = d3.time.format("%e %B %Y"),
                                margin = {top: 20, right: 20, bottom: 30, left: 100},
                        width = 987 - margin.left - margin.right,
                                height = 397 - margin.top - margin.bottom,
                                x = d3.time.scale().range([0, width]),
                                y = d3.scale.linear().range([height, 0]),
                                xAxis = d3.svg.axis().scale(x).orient("bottom"),
                                yAxis = d3.svg.axis().scale(y).orient("left"),
                                line = d3.svg.line()
                                .x(function(d) {
                                    return x(d.x);
                                })
                                .y(function(d) {
                                    return y(d.y);
                                }),
//                            parseDate = d3.time.format("%d-%b-%y").parse,
                                parseDate = d3.time.format("%Y-%m-%d").parse,
                                svg = d3.select($("#d3modal")[0]).append("svg")
                                .attr("width", width + margin.left + margin.right)
                                .attr("height", height + margin.top + margin.bottom)
                                .append("g")
                                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

                        //  D3 | Get data with json format
                        var data = "";
                        for (var i = 0; i < json.data.length; i++) {
                            data = data + "&" + json.data[i].key + "=" + $("#" + json.data[i].key).val();
                        }
                        d3.json("?action=NFUnitPriceTrend&mode=99" + data, function(error, json) {
                            json.data.forEach(function(d) {
                                d.x = parseDate(d.x);
                                d.y = +d.y;
                            });

                            x.domain(d3.extent(json.data, function(d) {
                                return d.x;
                            }));

                            y.domain(d3.extent(json.data, function(d) {
                                return d.y;
                            }));

                            svg.append("g")
                                    .attr("class", "x d3axis")
                                    .attr("transform", "translate(0," + height + ")")
                                    .call(xAxis)
                                    .append("text")
                                    .attr("x", width)
                                    .attr("y", -6)
                                    .style("text-anchor", "end")
                                    .text(json.titlex);

                            svg.append("g")
                                    .attr("class", "y d3axis")
                                    .call(yAxis)
                                    .append("text")
                                    .attr("transform", "rotate(-90)")
                                    .attr("y", 6)
                                    .attr("dy", ".71em")
                                    .style("text-anchor", "end")
                                    .text(json.titley);

                            svg.append("path")
                                    .datum(json.data)
                                    .attr("class", "d3line")
                                    .attr("d", line);

                            svg.selectAll("dot")
                                    .data(json.data)
                                    .enter().append("circle")
                                    .attr("r", 3)
                                    .attr("cx", function(d) {
                                        return x(d.x);
                                    })
                                    .attr("cy", function(d) {
                                        return y(d.y);
                                    })
                                    .style("fill", "steelblue").style("stroke", "steelblue")
                                    .on("mouseover", function(d) {
                                        div.transition()
                                                .duration(200)
                                                .style("opacity", .9);
                                        div.html(formatTime(d.x) + "<br />" + d.y.toLocaleString())
                                                .style("left", (d3.event.pageX) + "px")
                                                .style("top", (d3.event.pageY - 28) + "px");
                                    })
                                    .on("mouseout", function(d) {
                                        div.transition()
                                                .duration(500)
                                                .style("opacity", 0);
                                    });
                        });
                    }
                });

                d3AssignEventUI(json);
            }

            // D3 | Assign any event on parameter
            function d3AssignEventUI(json) {
                for (var i = 0; i < json.params.length; i++) {
                    switch (json.params[i].type) {
                        case 'text':
                            if (json.params[i].is_autocomplete === 1) {
                                $('#' + json.params[i].name).autocomplete({
                                    source: json.params[i].autocomplete,
                                    minLength: 5,
                                    delay: 1000,
                                    select: function(event, ui) {
                                        return false;
                                    }
                                }).data('autocomplete')._renderItem = function(ul, item) {
                                    $("#" + json.title).data("title", item.itemCode + " | " + item.itemName);
                                    return $('<li>')
                                            .data("item", item)
                                            .append('<a><b>[' + item.itemCode + '] ' + item.itemName + '</b></a></li>')
                                            .appendTo(ul);
                                };
                            }
                            if (json.params[i].is_datepicker === 1) {
                                $('#' + json.params[i].name).datepicker({
                                    dateFormat: "dd/mm/yy",
                                    changeMonth: true,
                                    changeYear: true
                                });
                            }
                            break;
                    }
                }

                //  EVENT | D3View button
                $('#fD3').bind("submit", function() {
                    $('#d3modal').dialog("open");

                    return false;
                });
            }

        </script>
    </body>
</html>
