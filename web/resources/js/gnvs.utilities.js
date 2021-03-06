var gnvs = {
    objLoader: undefined,
    ajaxCall: function(data, success) {
        $.ajax({
            url: document.location.pathname,
            data: data,
            type: 'post',
            dataType: 'json',
            beforeSend: function(jqXHR, settings) {
                if (this.objLoader === undefined) {
                    this.objLoader = $('<div class="gnvs-overlay"><div class="gnvs-overlay-process">Processing request ....</div></div>');
                    this.objLoader.appendTo('body');
                }
            },
            success: function(json) {
                success(json);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('[' + textStatus + '] ' + errorThrown);
            },
            complete: function(jqXHR, textStatus) {
                this.objLoader.remove();
            }
        });
    },
    util: {
        getParent: function(object, level) {
            for (var i = 0; i < level; i++) {
                object = object.parent();
            }

            return object;
        },
        reNumbering: function(tbody, col) {
            tbody.find('td:nth-child(' + col + '):visible').each(function(i) {
                $(this).html(i + 1);
            });
        },
        toDBDate: function(idnDate) {
            if (idnDate !== '') {
                idnDate = idnDate.split("/");
                var date = new Date(idnDate[2], idnDate[1] - 1, idnDate[0]);
                var curr_date = date.getDate();
                var curr_month = date.getMonth() + 1; //Months are zero based
                var curr_year = date.getFullYear();

                return curr_year + "-" + curr_month + "-" + curr_date;
            } else {
                return '';
            }
        },
        toViewDate: function(dbDate) {
            dbDate = dbDate.substr(0, 10).split('-');
            return dbDate[2] + '/' + dbDate[1] + '/' + dbDate[0];
        },
        parseFloat: function(v) {
            v = v.replace(/,/g, '');
            return parseFloat(v);
        }
    },
    validator: {
        backDate: function($datePicker, dateText, inst) {
            $.ajax({
                url: 'Validator.htm', type: 'get',
                data: { action: 'validateBackDate' },
                dataType: 'json',
                success: function(json) {
                    // fya} update with previous value
                    if (json.validity === '0') {
                        if (json.currentDate !== undefined) {
                            $datePicker.datepicker().datepicker('setDate', json.currentDate);
                        } else {
                            $datePicker.datepicker().datepicker('setDate', inst.lastVal);
                        }

                        alert('Please contact administrator to allow backdate process!');
                    } else {
                        var currTime = $.datepicker.parseDate('dd/mm/yy', dateText).getTime(),
                            servTime = $.datepicker.parseDate('dd/mm/yy', json.currentDate).getTime();

                        if (currTime > servTime) {
                            $datePicker.datepicker().datepicker('setDate', json.currentDate);
                            alert('Future date not allowed!');
                        }
                    }
                }
            });
        }
    }
};
