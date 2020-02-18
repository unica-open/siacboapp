/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() {
	$(".chosen-select-stato-ord").chosen({
		allow_single_deselect: true,
		placeholder_text_multiple: ' ',
		search_contains: true,
		no_results_text: '',
	    width: "35%"
	}); 
	
	$(".chosen-select-flusso").chosen({
		allow_single_deselect: true,
		placeholder_text_single: ' ',
		search_contains: true,
		no_results_text: '',
	    width: "15%"
	}); 
	    	
	$(".chosen-select-distinta").chosen({
		allow_single_deselect: true,
		placeholder_text_single: ' ',
		search_contains: true,
		no_results_text: '',
	    width: "15%"
	}); 
	

	function disableElencoCodiciOrdinativiEntrataSpesa(name) {
		$("#elencoCodici" + name + "OrdinativiEntrata_chosen, #elencoCodici" + name + "OrdinativiSpesa_chosen, #elencoCodici" + name + "OrdinativiEmpty_chosen")
			.attr("disabled", true)
			.toggleClass('chosen-disabled', true);
		
		$("#elencoCodici" + name + "OrdinativiEntrata, #elencoCodici" + name + "OrdinativiSpesa").attr("disabled", true);
		
		$("#elencoCodici" + name + "OrdinativiEntrata_chosen, #elencoCodici" + name + "OrdinativiSpesa_chosen").hide();
		$("#elencoCodici" + name + "OrdinativiEmpty_chosen").show();
	}
	
	disableElencoCodiciOrdinativiEntrataSpesa('Flusso');
	disableElencoCodiciOrdinativiEntrataSpesa('Distinta');

	
	function changeElencoCodiciOrdinativiEntrataSpesa(name, entrataSel) {
		$("#elencoCodici" + name + "OrdinativiEntrata_chosen").attr("disabled", ! entrataSel).toggle(entrataSel).toggleClass('chosen-disabled', ! entrataSel);
		$("#elencoCodici" + name + "OrdinativiEntrata").attr("disabled", ! entrataSel);
		
		$("#elencoCodici" + name + "OrdinativiSpesa_chosen").attr("disabled", entrataSel).toggle(! entrataSel).toggleClass('chosen-disabled', entrataSel);
		$("#elencoCodici" + name + "OrdinativiSpesa").attr("disabled", entrataSel);
		
		$("#elencoCodici" + name + "OrdinativiEmpty_chosen").hide();
	}
	
	
	$("#codiceTipo").change(function(){
		if ($(this).val() === "") {
			disableElencoCodiciOrdinativiEntrataSpesa('Flusso');
			disableElencoCodiciOrdinativiEntrataSpesa('Distinta');
			return;
		}
		
		var entrataSel = $(this).val() === "I"; 
		
		changeElencoCodiciOrdinativiEntrataSpesa('Flusso', entrataSel);		
		changeElencoCodiciOrdinativiEntrataSpesa('Distinta', entrataSel);		
	});
	
    $("#pulsanteApriModaleProvvedimento").click(function(){
		hideErrors();
        $("#modaleGuidaProvvedimento").modal("show");
    });

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

	
	function setAttivaTrasmissione(b) {
		$('#attivaTrasmissione').attr('disabled', !b);
		$('#disattivaTrasmissione').attr('disabled', b);
	}
	
	$('#daTrasmettereTrue').click(function() {
		setAttivaTrasmissione(false);
	});
	
	$('#daTrasmettereFalse').click(function() {
		setAttivaTrasmissione(true);
	});
	
	$('#daTrasmettereNull').click(function() {
		$('#attivaTrasmissione').attr('disabled', true);
		$('#disattivaTrasmissione').attr('disabled', true);
	});
	
	$('#daTrasmettereNull').trigger('click');
	
	$("#form").submit(function(evt) {
		if ($('#dataTrasmissioneOilDa').val() || $('#dataTrasmissioneOilA').val())
			$('#dataTrasmissioneOilPresente').val(1);
	});
	
	function initZTree(id) {
		return $.fn.zTree.init($(id), {
	        check : {
	            radioType : "all",
	            enable : true,
	            chkStyle : "radio"
	        },
	        data : {
	           	key : {
	           	    name : "descrizioneCompleta",
	           	    children : "sottoElementi"
	        	}
	        }
	    }, elencoStruttureAmministrativoContabiliJson);
	}
	
    function handleZTreeClick(zt, suff = '') {
        $('#confermaStrutturaAmministrativoContabile' + suff).click(function(e) {
            var checkedNodes = zt.getCheckedNodes(true);
            
            if (checkedNodes.length > 0) {
                $('#HIDDEN_StrutturaAmministrativoContabileUid' + suff).val(checkedNodes[0].uid);
                $('#SPAN_StrutturaAmministrativoContabile' + suff).text(checkedNodes[0].descrizioneCompleta);
            }
        });

        $('#deselezionaStrutturaAmministrativoContabile' + suff).click(function(e) {
            var checkedNodes = zt.getCheckedNodes(true);
            if (checkedNodes.length > 0) {
                zt.checkNode(checkedNodes[0], false, true, false);
            }
            e.stopPropagation();
            return false;
        });
    }

    var zTreeObj = initZTree("#treeStruttAmm");
    handleZTreeClick(zTreeObj);
    
    var zTreeObjModale = initZTree("#treeStruttAmm_modale");
    handleZTreeClick(zTreeObjModale, "_modale");
});