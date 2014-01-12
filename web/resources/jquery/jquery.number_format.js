/**
 * jQuery plugin to format numbers in input text fields
 *
 * Author: Hugo Hideki Yamashita <hugo.hideki@gmail.com>
 * Version: 1.0
 * Release date: January 01, 2009
 */

/* Examples

  // Input
  <input type="text" class="numeric" value="" />

  // Brazilian currency format
  $(".numeric").number_format({precision: 2,
                               decimal: ',',
                               thousands: '.'});

  // Changing precisio to 5 decimal digits
  $(".numeric").number_format({precision: 5});
*/

(function ($) {
  $.fn.number_format = function (opts) {
    // Merges default options with parameter opts
    var options = $.extend({precision:     2,
                            decimal:       '.',
                            thousands:     ',',
                            allow_negative: true}, opts);


    return $(this).each(function () {

      // Set the default value unless the input has any value
      if(get_numeric($(this).val()) == 0 || get_numeric($(this).val()) == '')
        $(this).val('0');

      // Set event handlers
      $(this)

      // Formats the value
      .keyup(function(e) {
        var e = window.event || e;
        var keyUnicode = e.charCode || e.keyCode;

        if (e !== undefined) {
            switch (keyUnicode) {
                case  8: break; // backspace
                case 27: break; // Esc
                case 37: break; // cursor left
                case 38: break; // cursor up
                case 39: break; // cursor right
                case 40: break; // cursor down
                default:
                  var signal = ($(this).val().indexOf('-') == 0 && options.allow_negative) ? '-' : '';
                  var new_value = get_numeric($(this).val());
                      new_value = signal + format_value(new_value);
                  $(this).val(new_value);
            }
         }
      })

      // Trigger keyup event to format the value again, if necessary
      .blur(function() {
        $(this).trigger('keyup');
      })

      // Change focus behaviour to select the value
      .focus(function() {
        $(this).select();
      });
    });



    // Returns the numeric part of "val", preserving the signal
    function get_numeric(val) {
      var value = val.toString().replace(/[^\d]*/g, '');
      var mult = val.toString().indexOf('-') == 0 ? -1 : 1;
      return parseInt(value * mult);
    }



    // Formats "val"
    function format_value(val) {
      var value = val.toString();
      var new_value = '';
      var offset = value[0] == '-' ? 1 : 0;
      var integer_part = '';
      var decimal_part = '';
      var formatted_number = '';

      // If there is a precision, formats the number
      if(options.precision > 0) {

        // If the number of digits is lesser than the precision, no need to format
        if(value.length <= options.precision) {
          integer_part = '0';

          // Adds the correct number of digits in the decimal part
          if(value.length < options.precision)
            for(var i = 0; i < options.precision - value.length; i++)
              decimal_part += '0';

          decimal_part += value;
          formatted_number = integer_part + options.decimal + decimal_part;

        // If the number of digits is greater than the precision, formats the number
        } else {
          integer_part = value.slice(0, value.length - options.precision);
          decimal_part = value.slice(value.length - options.precision);
          formatted_number = format_integer_part(integer_part) + options.decimal + decimal_part;
        }

      // If no precision is given, the number is integer only
      } else {
        formatted_number = format_integer_part(value);
      }

      return formatted_number;
    }



    // Formats the integer part of the number
    function format_integer_part(val) {
      var counter = 0;
      var formatted = '';
      var separator = '';
      var offset = val[0] == '-' ? 1 : 0;

      // Adds the thousands separator in every 3 digits
      for(var i = val.length - 1; i >= offset; i--) {
        separator = (counter > 0 && counter % 3 == 0) ? options.thousands : '';
        formatted = val[i] + separator + formatted;
        counter++;
      }

      return formatted;
    }
  };
})(jQuery);