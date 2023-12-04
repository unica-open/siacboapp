/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() 
{
	hideSpinner('#pulsanteRicercaProvvedimento');
	
	
	function initDataTable(elencoProvvedimenti) {
		
		var dt = $('#risultatiRicercaProvvedimento');
		
        var opts = {
                aaData: elencoProvvedimenti,
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
                    	return '<input data-tipo-uid="' + src.tipo.uid 
                    			+ '" data-anno="' + src.anno 
                    			+ '" data-numero="' + src.numero 
                    			+ '" data-sac-uid="' + (src.sac != null ? src.sac.uid : '') 
                    			+ '" name="provvedimentoRadio" type="radio" value="'+src.uid+'"/>'; }},
                    
                    {aTargets: [1], mData : 'anno'},
                    {aTargets: [2], mData : 'numero'},
                    {aTargets: [3], mData : 'tipo.descrizione'},
                    {aTargets: [4], mData : 'oggetto'},
                    {aTargets: [5], mData : function(src) { return src.sac != null ? src.sac.codice + '-' + src.sac.descrizione : ''; }},
                    {aTargets: [6], mData : 'stato.descrizione'}
                ]
        };
        if($.fn.DataTable.fnIsDataTable(dt[0])) {
            dt.dataTable().fnDestroy();
        }
        // Inizializzazione del dataTable
        dt.dataTable(opts);
	}
	
	
	$('#pulsanteAnnullaRicercaProvvedimento').click(function(){
        $("#modaleGuidaProvvedimento").modal("hide");
	});
	
	
	$("#pulsanteRicercaProvvedimento").click(function() {
		hideErrors();
		
		showSpinner('#pulsanteRicercaProvvedimento');
		
		postJSON("ricercaProvvedimentiJson.do", 
				$("#fieldsetRicercaGuidateProvvedimento").serializeObject(),
				doneCallback,
				failCallback,
				alwaysCallback
		);
	});

	
	function doneCallback(data) {
		if (showErrors(data.errori)) {
			return;
		}
		
		initDataTable(data.elencoProvvedimenti);
		$('#divContenitoreTabellaProvvedimento').slideDown();
	}
	
	function failCallback(data) {}
	
	function alwaysCallback(data) {
		hideSpinner('#pulsanteRicercaProvvedimento');
	}
	
	$('#pulsanteConfermaProvvedimento').click(function(e) {
		
        e.preventDefault();
		
		var sel = $('input[name="provvedimentoRadio"]:checked');

		var uid = sel.val();
		var anno = sel.data('anno');
		var numero = sel.data('numero');
		var tipoUid = sel.data('tipo-uid');
		var sacUid = sel.data('sac-uid');
		
		$('#idAttoAmministrativo').val(uid);
		$('#annoAttoAmministrativo').val(anno).attr('readonly', true);
		$('#numeroAttoAmministrativo').val(numero).attr('readonly', true);
		$('#tipoAtto').val(tipoUid).attr('readonly', true);
        $('#HIDDEN_StrutturaAmministrativoContabileUid').val(sacUid);
		
		var ztree = $.fn.zTree.getZTreeObj('treeStruttAmm');

		if (ztree != null) {
			var nodes = ztree.getNodes();
	        for(node in nodes) {
	            if(nodes.hasOwnProperty(node)) {
	                ztree.setChkDisabled(nodes[node], false, true, true);
	                ztree.checkNode(nodes[node], false, true, true);
	            }
	        }
	
			var node = ztree.getNodeByParam("uid", sacUid);
	        ztree.refresh();
	        ztree.checkNode(node, true, true, true);
	        $('#SPAN_StrutturaAmministrativoContabile').text(node.descrizioneCompleta);
	
	        nodes = ztree.getNodes();
	        for(node in nodes) {
	            if(nodes.hasOwnProperty(node)) {
	                ztree.setChkDisabled(nodes[node], true, true, true);
	            }
	        }
        }
        $("#modaleGuidaProvvedimento").modal("hide");

	});
	
});