/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() { 

    $("#pulsanteApriModaleSoggetto").click(function(){
		hideErrors();
        $("#modaleGuidaSoggetto").modal("show");
    });
    
    
    $("#scollegaSoggetto").click(function() {
    	$('.codiceSoggetto').val('');
    });

    $("#scollegaClasseSoggetto").click(function() {
    	$('#classeSoggetto').find('option:first-child').prop('selected', true).end().trigger('chosen:updated');
    });

});