/* Init user interaction for turbopajak application */
$(function() {
	
	function init_currency() {
		//$('.uang').css('text-align', 'right').attr('alt', 'p0p3c0S').autoNumeric();
		$('.uang').css('text-align', 'right').autoNumeric({aSep: '.', aDec: ',', mNum: 13, mDec: 0});
		$('.uang').each(function() {
			var value = $(this).val();
			if(value == '') {
				$(this).val('0');
			} else if(value !== '' && value !== '0') {
				$(this).val(currency_format('idr', value));
			}
		});
	}
	
	// flash message handling //
	if ($.trim($('#flashMessage').text()) == "") {
		$('#flashMessage').hide();
	} else {
		$.gritter.add({
			title: 'Notice',
			text: $('#flashMessage').text(),
			time: 3000
		});					
	}

	// set tableless form label, can be overriden //
	$('.input label').addClass('span-5');

	// clickable rows //
	if($('.row-select').length) {
		var row = $('.row-select tbody tr');
		row.click(function(event) {
			if(event.target.type !== 'checkbox') {
				$(':checkbox', this).trigger('click');
			}
		});		
	}
	
	// apply datepicker selector if any //
	if($('.date').length) {
		$('.date').datepicker({dateFormat: 'yy-mm-dd', yearRange: '-10:+10', highlightWeek: true, changeMonth: true, changeYear: true});
	}

	if($('.birth_date').length) {
		$('.birth_date').datepicker({dateFormat: 'yy-mm-dd', yearRange: '-100:+0', highlightWeek: true});
	}
	// apply datepicker selector if any //
	if($('.datetime').length) {
		$('.datetime').datetimepicker({dateFormat: 'yy-mm-dd', yearRange: '-10:+10', highlightWeek: true, changeMonth: true, changeYear: true, ampm: false});
	}
	// Init Toggle Combo Box (if any) //
	if($('.checkAll').length) {
		$('.checkAll').click(function() {
			var checked_status = this.checked;
			$('.cbo').each(function() {
				this.checked = checked_status;
			});
		});
	}
	if($('.checkAll2').length) {
		$('.checkAll2').click(function() {
			var checked_status = this.checked;
			$('.cbo2').each(function() {
				this.checked = checked_status;
			});
		});
	}

	// apply money class (idr format) //
	if($('input.uang').length) {
		//init_currency();
	}

	if($('.kurs').length) {
		$('.kurs').css('text-align', 'right').addClass('numeric').attr('alt', 'p0p3c2S');
		$('.kurs').each(function() {
			$(this).val(kurs_format('idr', $(this).val()));
		});		
	}
	
	if($('.persen').length) {
		$('.persen').css('text-align', 'right').addClass('numeric').attr('alt', 'p3x3p2S');
	}

	// Cancel Action (if any)
	if($('#btnCancel').length) {
		$('#btnCancel').click(function() {
			form_init();
		});
	}
					
	// set css readonly //
	if($('form input[type=text]').length) {
		$('form input[type=text]').each(function() {
			if($(this).attr('readonly')) {
				//$(this).css('background-color', '#eee');
				$(this).addClass('readonly');
			}
		});
	}

	// apply form effect //
	/*
	if($('form').length) {
		$('form').css({
			'border':'1px solid #ccc', 'padding':'10px',
			'-moz-border-radius':'10px', '-webkit-border-radius':'10px',
			//'-moz-box-shadow':'5px 5px 0 #ccc', '-webkit-box-shadow':'5px 5px 0 #ccc',
			'background-color':'#fbfbfb'
			});
		$('form .tblDisplay, form .tblDisplay caption').css('background-color', '#fbfbfb');
	}
	*/
	
	if($('.tblDisplay').length) {
		//$('.tblDisplay tbody tr:nth-child(odd)').css('background', '#fafafa');
		//$('.tblDisplay tbody tr:nth-child(even)').css('background', '#efefef');
	}
	
	// apply npwp class (if any) //
	if($('.npwp').length) {
		$('.npwp').mask('99.999.999-9.999.999');
	}

	// firefox fix //
	if($('form').length) {
		$('form').attr('autocomplete', 'on');
	}
	
	// display content if javascript is turned on //
	$('#content').css('display', '');
	
	// apply jquery ui button //
	//$('button, input[type=button]').button();
	$('button, input[type=button], input[type=submit]').button().css('font-size', 'smaller');
	$('.btnLink').button().css('font-size', 'smaller');
	/*
	$('button, input[type=button]').each(function() {
		if($(this).attr('disabled')) {
			$(this).button({disabled: true});
		} else {
			$(this).button({disabled: false});
		}
	});
	*/
	
	// validation check //
	if($('form.cssform').length) {
		$('form.cssform').validationEngine();
	}
	
	// accordion menu //
	if($('#accordion-menu').length) {
		$('#accordion-menu').accordion({
			autoHeight: false,
			collapsible: false,
			header: 'h3',
			navigation: true,
			animated: true,
			active: true
		});
	}
	
	// toolbar //
	if($('.toolbar').length) {
		$('.toolbar').buttonset();
		if($('#btnEdit').length && $('#btnDelete').length) {
			$('#btnEdit').button({ icons: { primary: 'ui-icon-pencil' }, text: true});
			$('#btnDelete').button({ icons: { primary: 'ui-icon-trash' }, text: true});
		}
	}

	// datatables //
	/*
	if($('.tblDisplay').length) {
		$('.tblDisplay').dataTable({
									'bJQueryUI': true,
									'bPaginate': false,
									'bSort': false,
									'bLengthChange': false,
									'bFilter': true
								   }).css('margin', 0).css('padding', 0);
	}
	*/
	
	// custom header //
	if($('.center td').length) {
		$('.center td').css('text-align', 'center');
	}
	
	if($('.tblDisplay').length) {
		var check = $('.tblDisplay tbody').hasClass('tbl-nohover');
		if(!check) {
			$('.tblDisplay tbody tr').hover(
				function() {
					$(this).addClass('ui-state-hover');
				},
				function() {
					$(this).removeClass('ui-state-hover');
				}
			).css('cursor', 'pointer');					
		}
	}
	
	// menu selected //
	$('#accordion-menu h3').each(function(i) {
		$(this).next().find('li a').each(function() {
			var href = $(this).attr('href');
			var url = location.pathname + location.search;
			if(url.indexOf(href) == 9) { // != -1
                                //alert(url.indexOf(href));
				$('#accordion-menu').accordion({ active: i});
				$(this).parent().addClass('ui-state-highlight ui-corner-all');
				$(this).removeAttr('href');
			} else {
				//return false;
			}
		});
	});
	
	if($('legend.toggle').length) {
		$('legend.toggle').toggle(
			function() {
				$(this).next().hide();
			}, function() {
				$(this).next().show();
			}
		).css('cursor','pointer');
	}
	
	if($('.tblDisplay th').length) {
		$('.tblDisplay th').addClass('ui-state-default');
	}

	if($('.tblDisplay tfoot td').length) {
		$('.tblDisplay tfoot td').addClass('ui-state-default');
	}
	
	if($('.npwp').length) {
		$('.npwp').each(function() {
			$(this).attr('size', '22');
		});
	}

	if($('.date').length) {
		$('.date').each(function() {
			$(this).attr('size', '11');
		});
	}
	
	// table skin //
	if($('table.tblForm').length) {
		// skin //
		$('.tblForm').addClass('ui-widget-content');
		$('.tblForm caption').addClass('ui-widget-header left'); //span-5 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
		$('.tblForm thead td').addClass('ui-state-default');
		$('.tblForm tfoot').addClass('ui-widget-header');
		// button action //
		$('.tblForm tfoot input').button().css('font-size', 'smaller');
		// zebra //
		$('.tblForm tbody tr:nth-child(odd)').css('background', '#fafafa');
		$('.tblForm tbody tr:nth-child(even)').css('background', '#efefef');
		// caption //
		$('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
	
		
		/*
		// hover effect //
		var check = $('.tblForm tbody').hasClass('tbl-nohover');
		if(!check) {
			$('.tblForm tbody tr').hover(
				function() {
					$(this).addClass('zebra');
				},
				function() {
					$(this).removeClass('zebra');
				}
			).css('cursor', 'pointer');					
		}
		*/
	}
	
	// button generic //
	if($('#btnBack').length) {
		$('#btnBack').click(function() {
			history.go(-1);
		});
	}
	
	// tabs generic //
	if($('.tab').length) {
		$('.tab').tabs().removeClass('ui-widget');
	}
});