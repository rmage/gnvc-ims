function util_number_format( number, decimals, dec_point, thousands_sep ) {
    // http://kevin.vanzonneveld.net
    // +   original by: Jonas Raoni Soares Silva (http://www.jsfromhell.com)
    // +   improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // +     bugfix by: Michael White (http://getsprink.com)
    // +     bugfix by: Benjamin Lupton
    // +     bugfix by: Allan Jensen (http://www.winternet.no)
    // +    revised by: Jonas Raoni Soares Silva (http://www.jsfromhell.com)
    // +     bugfix by: Howard Yeend
    // *     example 1: number_format(1234.5678, 2, '.', '');
    // *     returns 1: 1234.57     
 
    var n = number, c = isNaN(decimals = Math.abs(decimals)) ? 2 : decimals;
    var d = dec_point === undefined ? "." : dec_point;
    var t = thousands_sep === undefined ? "," : thousands_sep, s = n < 0 ? "-" : "";
    var i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "", j = (j = i.length) > 3 ? j % 3 : 0;
    
    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
}

function implode( glue, pieces ) {
    // http://kevin.vanzonneveld.net
    // +   original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // +   improved by: Waldo Malqui Silva
    // *     example 1: implode(' ', ['Kevin', 'van', 'Zonneveld']);
    // *     returns 1: 'Kevin van Zonneveld'
 
    return ( ( pieces instanceof Array ) ? pieces.join ( glue ) : pieces );
}

function explode( delimiter, string, limit ) {
   // http://kevin.vanzonneveld.net
    // +     original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // +     improved by: kenneth
    // +     improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // +     improved by: d3x
    // +     bugfixed by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // *     example 1: explode(' ', 'Kevin van Zonneveld');
    // *     returns 1: {0: 'Kevin', 1: 'van', 2: 'Zonneveld'}
    // *     example 2: explode('=', 'a=bc=d', 2);
    // *     returns 2: ['a', 'bc=d']
 
    var emptyArray = { 0: '' };
    
    // third argument is not required
    if ( arguments.length < 2 || typeof arguments[0] == 'undefined' || typeof arguments[1] == 'undefined' )
    {
        return null;
    }
 
    if ( delimiter === '' || delimiter === false || delimiter === null )
    {
        return false;
    }
 
    if ( typeof delimiter == 'function' || typeof delimiter == 'object' || typeof string == 'function' || typeof string == 'object' )
    {
        return emptyArray;
    }
 
    if ( delimiter === true ) {
        delimiter = '1';
    }
    
    if (!limit) {
        return string.toString().split(delimiter.toString());
    } else {
        // support for limit argument
        var splitted = string.toString().split(delimiter.toString());
        var partA = splitted.splice(0, limit - 1);
        var partB = splitted.join(delimiter.toString());
        partA.push(partB);
        return partA;
    }
}

function value_to_money(number) {
	return util_number_format(number, '', ',', '.');
}

function money_to_value(money) {
	return implode('', explode('.', money));	
}

function value_to_money_desimal(number) {
	var result = util_number_format(number, '2', ',', '.');
	var arr = explode(',', result);
	if(arr[1][0] == '0' && arr[1][1] == '0')
		return arr[0];
	else
		return result;
}	

function money_desimal_to_value(money) {
	var result = implode('', explode('.', money));
	var arr = explode(',', result);
	return implode('.', arr);	
}

function parse_npwp(npwp, target) {
	npwp = explode("#", npwp);
	for(i=0, c=npwp.length; i < c; i++)
		target.children('input').eq(i).val(npwp[i]);
	return false;
}

function clear_npwp(target) {
	parse_npwp('#####', target);
	return false;
}

function form_edit(tbl, url, cbo_class) {
	var breaks = 0;
	var id = '';
	cbo_class = typeof(cbo_class) != 'undefined' ? cbo_class : 'cbo';
	tbl.each(function(index) {
		check = $(this).find('.'+cbo_class).attr('checked');
		if (check && !breaks) {
			id = $.trim($(this).find('#dataId').text());
			if(id == '') {
				id = $.trim($(this).find('#dataId').val());
			}
			window.location.href = url+'/'+id;
			breaks++;
		}
	});
}

function form_add(data, url) {
	$.post(url, data, function(data) {
		if(data == "Gagal") {
			alert('Data gagal ditambah!');
		} else {
			window.location.reload();
		}
	});
}

function form_delete(tbl, url, cbo_class) {
	var idToDelete = new Array();
	cbo_class = typeof(cbo_class) != 'undefined' ? cbo_class : 'cbo';
	tbl.each(function(index) {
		check = $(this).find('.'+cbo_class).attr('checked');
		if (check) {
			var id = $.trim($(this).find('#dataId').text());
			if(id == '')
				id = $.trim($(this).find('#dataId').val());
			
			idToDelete.push(id);
		}
	});

	if (confirm("Apakah anda yakin ingin menghapus data ?")) {
		$.post(url, { "id[]": idToDelete }, function(data) {
			if(data == "Gagal") {
				alert('Data gagal dihapus!');
			} else {
				alert('Data telah dihapus.');
				window.location.reload();
			}			
		});
		loop = 0;
	}
}

function form_init() {
	$('form input[type="text"]').val('');
	$('form select').val('');
	$('form checkbox').removeAttr('checked');
	$('form radio').removeAttr('checked');
	$('form .uang').val('0');
	$('form .kurs').val('0');
	$('form .persen').val('0');
	$('form textarea').text('');
	$('form textarea').val('');
	//$('#tinymce').html('');
}

function idr_to_value(money) {
	return implode('', explode('.', money));
}

function usd_to_value(money) {
	return implode('', explode(',', money));
}

function idr_format(money, dec) {
	return util_number_format(money, dec, ',', '.');
}

function usd_format(money) {
	return util_number_format(money, '2', '.', ',');
}

function currency_format(type, money) {
	if(type == 'idr' || type == '') {
		return idr_format(money, 0);
	} else {
		return usd_format(money);
	}
}

function currency_to_value(type, money) {
	if(type == 'idr' || type == '') {
		return idr_to_value(money);
	} else {
		return usd_to_value(money);
	}
}

function kurs_format(type, money) {
	if(type == 'idr') {
		return idr_format(money, 2);
	} else {
		return usd_format(money);
	}	
}