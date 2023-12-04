/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
!function(window, $) { 
	
	var StrutturaAmministrativaContabile = window.StrutturaAmministrativaContabile;

	
    $("#pulsanteApriModaleProvvedimento, .apriModaleProvvedimento").click(function(){
		hideErrors();
		//SIAC-8485
		StrutturaAmministrativaContabile.inizializzaZtreeStrutturaAmministrativaContabile( '_modale', 
			'siacboapp/modificaModalitaPagamentoAttoAllegato_caricaStrutture.do'
		);
        $("#modaleGuidaProvvedimento").modal("show");
    });

    $("#pulsanteApriModaleSoggetto").click(function(){
		hideErrors();
        $("#modaleGuidaSoggetto").modal("show");
    });

    
    $("#idModalitaPagamentoSoggetto").change(function() {
    	$("#aggiorna").attr('disabled', $(this).val().length == 0);
    });
    
    
	$('#pulsanteConfermaProvvedimento').click(function(e) {
		var idSac = $('input[name="provvedimentoRadio"]:checked').data('sac-uid');
		$('#strutturaAmministrativoContabile option').prop('selected', false);
		$('#strutturaAmministrativoContabile option[value="' + idSac + '"]').prop('selected', true);
		$('#idSac').val(idSac);
	});

	$('#strutturaAmministrativoContabile option').prop('selected', false);
	$('#strutturaAmministrativoContabile option[value="' + $('#idSac').val() + '"]').prop('selected', true);

}(this, jQuery);