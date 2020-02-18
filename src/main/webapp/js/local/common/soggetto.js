/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() 
{
	hideSpinner('#pulsanteCercaSoggetto');

	function initDataTable(elencoSoggetti) {
		
		var dt = $('#risultatiRicercaSoggetti');
		
        var opts = {
                aaData: elencoSoggetti,
                bPaginate: true,
                bLengthChange: false,
                bSort: false,
                bInfo: true,
                bAutoWidth: false,
                bFilter: false,
                bProcessing: true,
                bDestroy: true,
                iDisplayLength: 3,
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
                    	return '<input data-codice="' + src.codice 
                    			+ '" name="soggettoRadio" type="radio" value="'+src.uid+'"/>'; }},
                    {aTargets: [1], mData : 'codice'},
                    {aTargets: [2], mData : 'codiceFiscale'},
                    {aTargets: [3], mData : 'partitaIva'},
                    {aTargets: [4], mData : 'descrizione'},
                    {aTargets: [5], mData : 'stato.descrizione'}
                ],
                createdRow: function (row, data, index) {
                	var radio = $('input[name="soggettoRadio"]', row);
            		radio.click(function(){ dt.selectedRadio = radio.val();  });
            		radio.attr('checked', $(this).val() === dt.selectedRadio);	
                }
        };
        if($.fn.DataTable.fnIsDataTable(dt[0])) {
            dt.dataTable().fnDestroy();
        }
        // Inizializzazione del dataTable
        dt.dataTable(opts);
	}
	
	
	$('#pulsanteAnnullaRicercaSoggetto').click(function(){
        $("#modaleGuidaSoggetto").modal("hide");
	});
	
	
	$("#pulsanteCercaSoggetto").click(function() {
		hideErrors();
		
		showSpinner('#pulsanteCercaSoggetto');
		
		postJSON("ricercaSoggettiJson.do", 
				$("#fieldsetRicercaGuidateSoggetto").serializeObject(),
				doneCallback,
				failCallback,
				alwaysCallback
		);
	});
	
	function doneCallback(data) {
		if (showErrors(data.errori)) {
			return;
		}
		
		initDataTable(data.elencoSoggetti);
		$('#divTabellaSoggetti').slideDown();
	}
	
	function failCallback(data) {}
	
	function alwaysCallback(data) {
		hideSpinner('#pulsanteCercaSoggetto');
	}
	
	$('#pulsanteConfermaSoggetto').click(function(e) {
       
		e.preventDefault();
		
        var sel = $('input[name="soggettoRadio"]:checked');

		var uid = sel.val();
		var codice = sel.data('codice');
		
		$('#idSoggetto').val(uid);
		$('#codiceSoggetto').val(codice).attr('readonly', true);
		
        
        $("#modaleGuidaSoggetto").modal("hide");

	});
	
});