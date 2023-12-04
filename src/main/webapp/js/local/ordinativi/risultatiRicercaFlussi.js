/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(document).ready(function() 
{
	
	$.extend(jQuery.fn.simplePagination.defaults, 
		{
			items_per_page: 10,
			number_of_visible_page_numbers: 5
		}			
	);

	// $('#simple-pagination').simplePagination();

	$('.modalConsultaButton').click(function (event) {

		var button = $(this);
		// var ajaxUrl = 'consultaFlusso_cerca.do?uid=' + button.data('uid-flusso') + "&codiceTipoOrdinativi=" + $('#codiceTipoFlussi').val();
		var ajaxUrl = 'consultaFlusso_cerca.do';
		var oggettoPerChiamataAjax = {
			codiceTipoOrdinativi: $('#codiceTipoFlussi').val(),
			uid: button.data('uid-flusso'),
			anno: button.data('ord-anno')
		};

	    $('#modalConsulta div.modal-body').html('Attendere, prego...');
		
		$.ajax({
			url: ajaxUrl,
			data: oggettoPerChiamataAjax,
			method: 'GET',
			dataType : 'html'
		}).done(function(data){
			$('#modalConsulta div.modal-body').html(data);
		}).fail(function(data){
			$.noop();
		}).always(function(data){
			$.noop();
		});

		// postJSON(ajaxUrl,
		// 	oggettoPerChiamataAjax, 
		// 	function(data) {  
		// 		impostaDatiInTabella(data);
		// 		$('#modalConsulta div.modal-body').html(data);
		// 		// $('.codiceFlussoConsulta').html(button.data('numero-flusso'))
		// 	},  
		// 	$.noop(),
		// 	function(data) {
		// 		// alert('Always')
		// 	}
		// );

	});

	// function impostaDatiInTabella(data) {
	// 	var ordinativo = data.elencoOrdinativi[0];
	// 	var capitolo = data.capitoloAssociato;
	// 	var $elencoTD = $('td[id^="tabellaModaleOrdinativoCaricato_"]');
	// 	// $elencoTD[0].innerHTML = ordinativo.anno + "/" + ordinativo.numero;
	// 	// $elencoTD[1].innerHTML = ordinativo.descrizione;
	// 	// $elencoTD[2].innerHTML = capitolo.bilancio.periodo.anno + "/" + capitolo.codice + "/" + 
	// 	// 	capitolo.codiceArticolo + "/" + capitolo.codiceUeb + " - " + capitolo.descrizione;
	// 	// $elencoTD[3].innerHTML = ordinativo.stato.descrizione;
	// 	// $elencoTD[4].innerHTML = ordinativo.tipo.descrizione;
	// 	// $elencoTD[5].innerHTML = ordinativo.dataEmissione;
	// 	// $uidCapitolo.val(capitolo.uid);
	// }

});

