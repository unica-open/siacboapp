/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() 
{
	hideSpinner('#modale_pulsanteRicercaProvvisorioCassa');
	$('#modale_pulsanteRicercaProvvisorioCassa').attr('disabled', false);

	function initDataTable(elencoProvvisoriCassa) {
		
		var dt = $('#modale_tabellaProvvisorioCassa');
		
        var opts = {
                aaData: elencoProvvisoriCassa,
                bPaginate: true,
                bLengthChange: false,
                bSort: false,
                bInfo: true,
                bAutoWidth: false,
                bFilter: false,
                bProcessing: true,
                bDestroy: true,
                iDisplayLength: 10,
                oLanguage: {
                    sInfo: '_START_ - _END_ di _MAX_ risultati',
                    sInfoEmpty: '0 risultati',
                    sProcessing: 'Attendere prego...',
                    sZeroRecords: 'Non sono presenti risultati di ricerca secondo i parametri inseriti',
                    oPaginate: {
                        sFirst: 'inizio',
                        sLast: 'fine',
                        sNext: 'succ.',
                        sPrevious: 'prec.',
                        sEmptyTable: 'Nessun dato disponibile'
                    }
                },
                aoColumnDefs: [
                    {aTargets: [0], mData : function(src) { 
                    	return '<input class="idProvvisori" name="provvCassaUid" type="checkbox" value="'+src.uid+'"/>'; }},
                    {aTargets: [1], mData : 'anno'},
                    {aTargets: [2], mData : 'numero'},
                    {aTargets: [3], mData : function(src) { 
                    	return '<a title="'+src.descrizioneContoEvidenza+'">'+src.codiceContoEvidenza+'</a>'; }},
                    {aTargets: [4], mData : 'dataEmissioneStr'},
                    {aTargets: [5], mData : 'causale'},
                    {aTargets: [6], mData : 'descrizioneSoggetto'},
                    {aTargets: [7], mData : function(src) { return floatToImporto(src.importo); }, sClass: 'importo'},
                    {aTargets: [8], mData : function(src) { return floatToImporto(src.importoDaRegolarizzare); }, sClass: 'importoDaRegolarizzare'}
                ]
        };
        if($.fn.DataTable.fnIsDataTable(dt[0])) {
            dt.dataTable().fnDestroy();
        }
        // Inizializzazione del dataTable
        dt.dataTable(opts);

        $(dt.dataTable().fnGetNodes()).find(".idProvvisori").click(function() {
    		calcNumeroEImportiProvvisori($(this));
    	});
        
        dt.get(0).scrollIntoView();
	}

	
	function calcNumeroEImportiProvvisori(item) {
		var signum = $(item).is(':checked') ? +1 : -1;
		var numeroProvvisoriSelezionati = $('#numeroProvvisoriSelezionati');
		var numeroProvvisoriSelezionatiNumber = Number(numeroProvvisoriSelezionati.text()) + signum;
		
		$('#modale_pulsanteConfermaProvvisorioCassa').attr('disabled', numeroProvvisoriSelezionatiNumber == 0);
		
		numeroProvvisoriSelezionati.text(numeroProvvisoriSelezionatiNumber);
		
		var sommaImportiRegProvvisoriSelezionati = $('#sommaImportiRegProvvisoriSelezionati');

		sommaImportiRegProvvisoriSelezionati.text(
			floatToImporto(
				new BigNumber(importoToFloat(sommaImportiRegProvvisoriSelezionati.text()))
				.plus(new BigNumber(signum * importoToFloat(item.closest('td').siblings('.importoDaRegolarizzare').text())))
				.toNumber()
		));
	}

	
//	$('#pulsanteAnnullaRicercaSoggetto').click(function(){
//        $("#modaleGuidaSoggetto").modal("hide");
//	});
//	
	
	$("#provv-sel-all").click(function() {
		
		var cb = $($('#modale_tabellaProvvisorioCassa').dataTable().fnGetNodes()).find(".idProvvisori");
		var checked = $(this).is(":checked"); 
		
		cb.each(function(){
			if (this.checked !== checked) {
				this.checked = checked;
				calcNumeroEImportiProvvisori($(this));
			}
		});
	});
	
	
	$("#modale_pulsanteRicercaProvvisorioCassa").click(function() {
		hideErrors();
		showSpinner('#modale_pulsanteRicercaProvvisorioCassa');
		$('#modale_pulsanteRicercaProvvisorioCassa').attr('disabled', true);
		
		$('#sommaImportiRegProvvisoriSelezionati').text("0,00");
		$('#numeroProvvisoriSelezionati').text("0");
		
		postJSON("ricercaProvvisoriCassaJson.do", 
				$("#fieldsetRicercaProvvCassa").serializeObject(),
				doneCallback,
				failCallback,
				alwaysCallback
		);
	});
	
	function doneCallback(data) {
		if (showErrors(data.errori)) {
			$('#modale_divElencoProvvisorioCassa').slideUp();
			return;
		}
		
		initDataTable(data.elencoProvvisoriCassa);
		$('#modale_divElencoProvvisorioCassa').slideDown();
	}
	
	function failCallback(data) {
		showErrors(data.errori);
	}
	
	function alwaysCallback(data) {
		hideSpinner('#modale_pulsanteRicercaProvvisorioCassa');
		$('#modale_pulsanteRicercaProvvisorioCassa').attr('disabled', false);
	}
	
	$('#modale_pulsanteConfermaProvvisorioCassa').click(function(e) {
       
		e.preventDefault();

		var sommaImportiOrdinativiSelezionati = importoToFloat($('#sommaImportiOrdinativiSelezionati').text());
		var sommaImportiRegProvvisoriSelezionati = importoToFloat($('#sommaImportiRegProvvisoriSelezionati').text());
		var proceed;
		
		if(sommaImportiOrdinativiSelezionati > sommaImportiRegProvvisoriSelezionati) {
			showErrors([{ codice: '', descrizione: "Importo provvisori selezionati non sufficiente per gli ordinativi selezionati" }] );
			return;
		}
		

		if(sommaImportiOrdinativiSelezionati < sommaImportiRegProvvisoriSelezionati) {
			proceed = confirm("I provvisori selezionati non risultano completamente utilizzati: vuoi proseguire comunque?");
		} else {
			proceed = confirm("Si desidera continuare con l'operazione?");
		}
		
		if (! proceed) {
			return;
		}
		
//		$($('#modale_tabellaProvvisorioCassa').dataTable().fnGetNodes()).find(".idProvvisori:checked").each(function(){
//			$("#fieldsetConfermaGestioneOrdinativi").append('<input type="hidden" name="idProvvisoriCassa" value="' + this.value + '"/>');
//		});

		var elencoIdProvvisoriCassa = '';
		
		$($('#modale_tabellaProvvisorioCassa').dataTable().fnGetNodes()).find(".idProvvisori:checked").each(function() {
			elencoIdProvvisoriCassa += ',' + this.value; 
		});

		$("#fieldsetConfermaGestioneOrdinativi").append('<input type="hidden" name="elencoIdProvvisoriCassa" value="' + elencoIdProvvisoriCassa + '"/>');

		$("#modaleRicercaProvvisorioDiCassa").modal("hide");

        $("#form").data("skip-confirm", true);
        $("#conferma").trigger("click");
	});
	
});