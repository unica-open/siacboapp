/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
!function(global, $){
	'use strict'
	
	var StrutturaAmministrativaContabile = {};
	
	function inizializzaZtreeStrutturaAmministrativaContabile(suffix, url){
		var oggettoPerChiamataAjax = {};

		// Controllo per le sac di un utente dencentrato
		if($('#nomeAzioneSAC').val() !== undefined && $('#nomeAzioneSAC').val() !== null){
			oggettoPerChiamataAjax['nomeAzioneDecentrata'] = $('#nomeAzioneSAC').val();
		}
		
	    postJSON(url, oggettoPerChiamataAjax)
	    .then(function (data) {
	        var listaStrutturaAmministrativoContabile = (data.elencoStruttureAmministrativoContabili);
	        var zTree = new ZtreeSAC(suffix);
	        zTree.inizializza(listaStrutturaAmministrativoContabile);
	    });
	}

	StrutturaAmministrativaContabile.inizializzaZtreeStrutturaAmministrativaContabile = inizializzaZtreeStrutturaAmministrativaContabile;

    global.StrutturaAmministrativaContabile = StrutturaAmministrativaContabile;
	
}(this, jQuery);