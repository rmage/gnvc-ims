var input = {
    /* 
     * CHECK | data-valid attribute and bind event keyup to ensure user select the autocomplete item
     */
    checkDataValid: function (el) {
        if (!el.dataset.valid) {
            el.value = '';
        }
        
        if (typeof el.onkeyup !== 'function') {
            el.onkeyup = function() {
                this.removeAttribute('data-valid');
            };
        }
    },
    decimalOnly: function (e) {
        
    },
    resetForm: function(el) {
        var forms;
        if (el.self !== window) {
            forms = el.getElementsByTagName('form');
        } else {
            forms = el.document.getElementsByTagName('form');
        }
        
        
        for(var i = 0; i < forms.length; i++) {
            forms[i].reset();
        }
    }
};
