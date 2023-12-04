/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() {
    $("#pulsanteApriModaleSoggetto").click(function(){
		hideErrors();
        $("#modaleGuidaSoggetto").modal("show");
    });

	$('#form').on('keyup keypress', function(e) {
		  var keyCode = e.keyCode || e.which;
		  if (keyCode === 13) { 
		    e.preventDefault();
		    return false;
		  }
	});

	
	$('#anno').placeholder('anno');
	$('#numero').placeholder('numero');
	$('#data').placeholder('gg/mm/aaaa');

	var tipologiaE_click = function(){
        $("#codiceTipo").hide();
        $("#codiceTipoEntrata").show().attr('disabled', false);
        $("#codiceTipoSpesa").hide().attr('disabled', true);
    };
	
	var tipologiaS_click = function(){
        $("#codiceTipo").hide();
        $("#codiceTipoEntrata").hide().attr('disabled', true);
        $("#codiceTipoSpesa").show().attr('disabled', false);
    };
	
	$('#tipologiaE').click(tipologiaE_click);
	$('#tipologiaS').click(tipologiaS_click);
	
    $("#codiceTipo").show();
    $("#codiceTipoEntrata, #codiceTipoSpesa").hide().attr('disabled', true);

    if ($('#tipologiaE').prop('checked')) {
    	tipologiaE_click();
    }
	
    if ($('#tipologiaS').prop('checked')) {
    	tipologiaS_click();
    }

    $('label[for="tipologiaE"]').addClass('radio inline').prepend($('#tipologiaE'));
    $('label[for="tipologiaS"]').addClass('radio inline').prepend($('#tipologiaS'));

});