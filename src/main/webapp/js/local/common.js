/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
function postJSON(url, data, doneCallback = function(){}, failCallback = function(){}, alwaysCallback = function(){}) {
	
	return $.post(url, data, "json")
	.done(doneCallback)
	.fail(failCallback)
	.always(alwaysCallback);
}

/**
 * Effettua un log su console del messaggio
 * @param msg il messaggio da loggare
 * @param level il livello del log (Optional, default: log)
 */
function doLog(msg, level) {
    var innerLevel = level || 'log';
    if(window.console && typeof window.console[innerLevel] === 'function') {
        window.console[innerLevel](msg);
    }
}

function showErrors(errors) {
	if (errors.length > 0) {
		
		var alertErr = $(".alert-error");
		var alertErrUl = alertErr.find('ul');
		
		for (var ei in errors) {
			alertErrUl.append('<li>' + errors[ei].codice +  ' - '  + errors[ei].descrizione +  '</li>');	
		}
		
		alertErr.show();
		
		alertErr.get(0).scrollIntoView();
	}
	
	return errors.length > 0;
}

function hideErrors() {

	var alertErr = $(".alert-error");

	alertErr.find("ul li").remove();

	alertErr.hide();
}

function hideSpinner(id) {
	$(id).find(".spinner").hide();
}


function showSpinner(id) {
	$(id).find(".spinner").show();
}


function importoToFloat(importo) {
	return parseFloat(importo.replace(/\./g, '').replace(',', '.'));
}


function floatToImporto(n) {
    var s = Number(n).toString().replace(/\.(\d{1,2})\d*$/g, ',$1')
		 .replace(/\./g, '');

    if (s.match(/^([1-9]\d*|0)(\,|\,\d{0,2})?$/) == null)
    	return ''

    return s.replace(/[^\d\,]/g, '')
			.replace(/^(\d+)\,?$/, '$1,00')
			.replace(/(\,\d)$/, '$10')
			.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
}


/**
 * Gestione dei decimali.
 *
 * @param positiveMultiplier il moltiplicatore da utilizzare per i numeri positivi
 * @param negativeMultiplier il moltiplicatore da utilizzare per i numeri negativi
 *
 * @returns (jQuery) l'oggetto jQuery originale
 */
jQuery.fn.gestioneDeiDecimali = function(positiveMultiplier, negativeMultiplier) {
    return this.on("blur", onblur);
    // this.on("keyup", keyup);

    /**
     * Gestione del blur.
     */
    function onblur() {
        var self = $(this);
        var importo = self.val();
        if(positiveMultiplier !== undefined && importo > 0){
            self.val(importo * positiveMultiplier);
        }
        if(negativeMultiplier !== undefined && importo < 0){
            self.val(importo * negativeMultiplier);
        }
        var numberString = self.val().replace(/\./g, "").replace(/,/g, ".");
        var number = parseFloat(numberString);

        if (!isNaN(number)) {
            self.val(formatMoney(number));
        }
    }


    /**
     * Controlla che il codice fornito non sia invalido, ovvero corrispondente a un carattere speciale.
     *
     * @param code (Number) il key/char-code dell'evento di tastiera
     * @param e    (Event)  l'evento scatenante
     *
     * @returns true se il codice non &eacute; valido; false in caso contrario
     */
    function isInvalidCode(code, e) {
        var isCodeNotBackspaceOrDelete = code !== 8 && code !== 46;
        var isCodeSpecialOrArrow = code < 32 || code === 37 || code === 39;
        var isCodeModified = e.ctrlKey || e.altKey;
        return isCodeNotBackspaceOrDelete && (isCodeSpecialOrArrow || isCodeModified);
    }

    /**
     * Controlla che il carattere non sia invalido.
     *
     * @param nextChar (String) il carattere da validare
     * @param delim    (String) il delimitatore
     *
     * @returns true se il carattere &eacute; valido; false in caso contrario
     */
    function isNotInvalidChar(nextChar, delim) {
        return nextChar !== ' ' && nextChar !== '-' && nextChar !== delim;
    }

    /**
     * Gestione del keyup.
     *
     * @param e (Event) l'evento
     */
    function keyup(e) {
        var delim = '.';
        var decimalSeparator = ",";
        var str = this.value;
        var res = [];
        var j = 0;
        var k = 0;
        var decimalPart = "";

        var code = e.charCode || e.keyCode;
        // Inizializzo le altre variabili
        var selectionEnd, originalLength, i, nextChar, result;
        if (isInvalidCode(code, e)) {
            return;
        }

        selectionEnd = this.selectionEnd;
        originalLength = str.length;

        str = str.replace(/\./g, "").replace(/[a-zA-Z]/g, "");

        // Determino la posizione di partenza
        i = str.lastIndexOf(decimalSeparator) - 1;
        if (i >= 0) {
            decimalPart = decimalSeparator + str.split(decimalSeparator)[1].substring(0, 2);
        } else {
            i = str.length - 1;
        }

        while (i >= 0) {
            res[j] = str.charAt(i);
            nextChar = str.charAt(i - 1);
            k++;
            if (i > 0 && k === 3 && isNotInvalidChar(nextChar, delim)) {
                j++;
                res[j] = delim;
                k = 0;
            }
            j++;
            i--;
        }

        // Imposto il risultato
        result = res.reverse().join("") + decimalPart;
        this.value = result;

        // Riposiziono il cursore
        if (result.length > originalLength) {
            selectionEnd++;
        } else if (result.length < originalLength) {
            selectionEnd--;
        }
        this.setSelectionRange(selectionEnd, selectionEnd);
    }

    /**
     * Test per il blocco della scrittura dei decimali.
     *
     * @param e (Event) l'evento scatenante
     *
     * @returns <code>false</code> se devo rifiutare l'evento di scrittura; <code>undefined</code> in caso contrario
     */
    function test(e) {
        var self = jQuery(this);
        var split;
        var text = extractTextFromEvent(e);
        if(!text) {
            return;
        }
        // Giustappongo il testo al termine del contenuto del campo
        text = self.val() + text;
        // Se ho la virgola, vuol dire che ho scritto con notazione italiana. Cancellare e riformattare con notazione internazionale
        if(text.indexOf(",") !== -1) {
            text = text.replace(/\./g, "").replace(/,/g, ".");
        }
        // Splitto la stringa al punto decimale
        split = text.split(".");
        // Se ho termini dopo il punto e questi termini hanno lunghezza maggiore di 2, allora rifiuto l'evento di scrittura
        if(split[1] && split[1].length > 2) {
            e.preventDefault();
            e.returnValue = false;
            return false;
        }
    }

    /**
     * Estrae il testo dall'evento.
     *
     * @param event (Event) l'evento da analizzare
     *
     * @returns (String) il testo
     */
    function extractTextFromEvent(event) {
        var text;
        var code;
        // Controllo il testo
        if (event.type === "textinput" || event.type === "textInput") {
            text = (event.originalEvent) ? event.originalEvent.data : event.data;
        } else {
            code = event.charCode || event.keyCode;
            if (code < 32 || event.charCode === 0 || event.ctrlKey || event.altKey) {
                return;
            }
            text = String.fromCharCode(code);
        }
        return text;
    }
    
    function formatMoney(val) {
        var res = '';
        if(val === undefined || val === null || isNaN(val) || val === '') {
            return '';
        }
        try {
            res = new BigNumber(val).toFormat(2, BigNumber.ROUND_HALF_UP);
        } catch(err) {
            // Per sicurezza
            doLog('Error: ' + err);
            if (err && err.stack) {
                doLog(err.stack);
            }
        }
        return res;
    }
};

$(function() 
{
	
	BigNumber.config({
	    FORMAT: {
	        decimalSeparator: ',',
	        groupSeparator: '.',
	        groupSize : 3, secondaryGroupSize : 0,
	        fractionGroupSeparator : '\xA0',
	        fractionGroupSize : 0
	    }
	});
	
	/**
	 * Serializes to an object.
	 *
	 * @returns (Object) the serialized object
	 */
	jQuery.fn.serializeObject = function() {
	    var o = {};
	    var a = this.serializeArray();
	    if (a.length === 0) {
	        // Workaround for jQuery bug #8883
	        // see http://bugs.jquery.com/ticket/8883
	        a = this.filter(":input").add(this.find(":input")).serializeArray();
	    }
	    jQuery.each(a, function() {
	        if (o[this.name] !== undefined) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });

	    // Puts also the unchecked checkboxes into the object
	    this.find("input[type='checkbox']").each(function() {
	        var self = $(this);
	        var name = self.attr("name");
	        var checked = self.prop("checked");
	        if (!o[name]) {
	            o[name] = checked;
	        }
	    });

	    return o;
	};
	
	
	
	
	
	
	
	
	$(".navbarLogin p.login-text.pull-left").remove(); // remove header description 
    
    
    
    $.fn.formatImporto = function() {

    	var selected = false;
    	
    	var format = function(input) {
    		input.val(floatToImporto(input.val()));
    	}
    	
    	$(this).each(function(){
    		format($(this));
    	});
    	
    	$(this).keypress(function(e) 
    	{
    		if (e.which < 32)
    			return true;

    	    var t = [$(this).val().slice(0, this.selectionStart), e.key, $(this).val().slice(this.selectionEnd)].join('');

    	    if (t.match(/^([1-9]\d*|0)(\,|\,\d{0,2})?$/) != null) {
    	     // $(this).val(t);
    	      selected = false;
    	      return true;
    	    }

    	    return false;

    	  }).focus(function(e) {
    	    $(this).val($(this).val().replace(/\./g, ''));
    	    
    	  }).blur(function(e) {
    	      format($(this));
    	  }).select(function(e) {
    	    selected = true;
    	  });    
    }
    
    
    
    
    
    $.fn.formatSignedImporto = function() {

    	var selected = false;
    	
    	var format = function(input) {
    	    input.val(input.val().replace(/\.(\d{1,2})\d*$/g, ',$1')
    	    					.replace(/\./g, '')
    	    );
    	    
    	    if (input.val().match(/^[\-\+]?([1-9]\d*|0)(\,|\,\d{0,2})?$/) == null)
    	    	input.val('')
    	    else
    	    	input.val(input.val().replace(/[^\+\-\d\,]/g, '')
    	    						.replace(/^([\-\+]?\d+)\,?$/, '$1,00')
    	    						.replace(/(\,\d)$/, '$10')
    	    						.replace(/\B(?=(\d{3})+(?!\d))/g, '.'));
    	}
    	
    	$(this).each(function(){
    		format($(this));
    	});
    	
    	$(this).keypress(function(e) 
    	{
    		if (e.which < 32)
    			return true;

    	    var t = [$(this).val().slice(0, this.selectionStart), e.key, $(this).val().slice(this.selectionEnd)].join('');

    	    if (t.match(/^([\-\+]|[\-\+]?[1-9]\d*|[\-\+]?0)(\,|\,\d{0,2})?$/) != null) {
    	     // $(this).val(t);
    	      selected = false;
    	      return true;
    	    }

    	    return false;

    	  }).focus(function(e) {
    	    $(this).val($(this).val().replace(/\./g, ''));
    	    
    	  }).blur(function(e) {
    	      format($(this));
    	  }).select(function(e) {
    	    selected = true;
    	  });    
    }
    
    
    
        
    jQuery(".decimale").gestioneDeiDecimali();
    
    
    $.fn.formatIntero = function() {

    	var selected = false;
    	
    	var format = function(input) {
    	    input.val(input.val().replace(/\./g, ''));
    	    
    	    if (input.val().match(/^([1-9]\d*|0)$/) == null)
    	    	input.val('')
    	    else
    	    	input.val(input.val().replace(/[^\d\,]/g, '')
    	    						.replace(/\B(?=(\d{3})+(?!\d))/g, '.'));
    	}
    	
    	$(this).each(function(){
    		format($(this));
    	});
    	
    	$(this).keypress(function(e) 
    	{
    		if (e.which < 32)
    			return true;

    	    var t = [$(this).val().slice(0, this.selectionStart), e.key, $(this).val().slice(this.selectionEnd)].join('');

    	    if (t.match(/^([1-9]\d*|0)$/) != null) {
    	     // $(this).val(t);
    	      selected = false;
    	      return true;
    	    }

    	    return false;

    	  }).focus(function(e) {
    	    $(this).val($(this).val().replace(/\./g, ''));
    	    
    	  }).blur(function(e) {
    	      format($(this));
    	  }).select(function(e) {
    	    selected = true;
    	  });    
    }    
    
    
    
});