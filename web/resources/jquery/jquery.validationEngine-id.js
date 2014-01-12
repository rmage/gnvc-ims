(function($) {
	$.fn.validationEngineLanguage = function() {};
	$.validationEngineLanguage = {
		newLang: function() {
			$.validationEngineLanguage.allRules = 	{"required":{    			// Add your regex rules here, you can take telephone as an example
						"regex":"none",
						"alertText":"* Must be required.",
						"alertTextCheckboxMultiple":"* Pilih salah satu.",
						"alertTextCheckboxe":"* Pilih salah satu."},
					"length":{
						"regex":"none",
						"alertText":"* Jumlah karakter antara ",
						"alertText2":" dan ",
						"alertText3": " yang diperbolehkan."},
					"maxCheckbox":{
						"regex":"none",
						"alertText":"* Pilihan anda melebihi batas."},	
					"minCheckbox":{
						"regex":"none",
						"alertText":"* Pilih minimal ",
						"alertText2":" pilihan."},	
					"confirm":{
						"regex":"none",
						"alertText":"* Isian anda tidak cocok."},		
					"telephone":{
						"regex":"/^[0-9\-\(\)\ ]+$/",
						"alertText":"* Isian anda bukan merupakan nomor telepon."},	
					"email":{
						"regex":"/^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/",
						"alertText":"* Alamat email salah."},	
					"date":{
                                                "regex":"/^[0-9]{1,2}\/[0-9]{1,2}\/[0-9]{4}$/",
                                                "alertText":"* Tanggal anda salah, harus dalam format DD/MM/YYYY."},
					"onlyNumber":{
						"regex":"/^[0-9\ ]+$/",
						"alertText":"* Isian harus nomor."},	
					"noSpecialCaracters":{
						"regex":"/^[0-9a-zA-Z]+$/",
						"alertText":"* Tidak boleh ada karakter spesial."},	
					"ajaxUser":{
						"file":"validateUser.php",
						"extraData":"name=eric",
						"alertTextOk":"* Nama diperbolehkan.",	
						"alertTextLoad":"* Dalam proses, harap tunggu ...",
						"alertText":"* Nama ini telah ada, pilih yang lain."},	
					"ajaxName":{
						"file":"validateUser.php",
						"alertText":"* Nama ini telah digunakan.",
						"alertTextOk":"* Nama diperbolehkan.",	
						"alertTextLoad":"* Dalam proses, harap tunggu ..."},
					"onlyLetter":{
						"regex":"/^[a-zA-Z\ \']+$/",
						"alertText":"* Isian harus huruf."},
					"validate2fields":{
    					"nname":"validate2fields",
    					"alertText":"* You must have a firstname and a lastname"}	
					}	
					
		}
	}
})(jQuery);

$(document).ready(function() {	
	$.validationEngineLanguage.newLang()
});
