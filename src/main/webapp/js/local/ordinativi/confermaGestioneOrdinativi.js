/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() {

	$.extend(jQuery.fn.simplePagination.defaults, 
			{
				items_per_page: 10,
				number_of_visible_page_numbers: 5
					}			
	);

	jQuery('#simple-pagination').simplePagination();

	$("#ord-sel-all").change(function() 
	{
		(this.checked ? $('.idOrdinativi').not(":checked") : $('.idOrdinativi:checked')).trigger('click');
	});


    $("#form").submit(function(event) {
      
      var checkedOrd = $('.ord-sel > input:checkbox:checked'); 
    	
      if (checkedOrd.length == 0) {
    	  event.stopPropagation();
    	  return false;
      }
      
 	  if (!$(this).data("skip-confirm") && !confirm("Si desidera continuare con l'operazione?")) {
 		  return false;
   	  }
   	  
   	  var elencoIdOrdinativi = '';
   	  
   	  checkedOrd.each(function(){
   		elencoIdOrdinativi += ',' + this.value; 
   	  });
   	  
   	  $("#fieldsetConfermaGestioneOrdinativi").append('<input type="hidden" name="elencoIdOrdinativi" value="' + elencoIdOrdinativi + '"/>');
   	  
   	  return true;
	});

	$(".contentLoading").hide();
	$("#content").removeClass("nascosto");
	
	$(".ord-sel > input").attr("checked", false);
	
    $("#pulsanteApriModaleProvvisorioCassa").click(function(e) {
    	if ($(this).hasClass('disabled')) {
    		e.preventDefault();
    		return;
    	}
    	
    	var codiceTipoOrdinativo = $("#codiceTipoOrdinativi").val(); 
    	
    	$("#modale_hidden_tipoProvvisorioDiCassa").val(
    			codiceTipoOrdinativo === 'P' ? 'S' : 
    			codiceTipoOrdinativo === 'I' ? 'E' : 
    			'');
    	
		hideErrors();
		hideSpinner('#modale_pulsanteRicercaProvvisorioCassa');

		$('#sommaImportiRegProvvisoriSelezionati').text("0,00");
		$('#numeroProvvisoriSelezionati').text("0");

		$('#modale_divElencoProvvisorioCassa').hide();

		$('#modale_annoDa, #modale_annoA, #modale_numeroDa, #modale_numeroA, #modale_dataEmissioneDa, #modale_dataEmissioneA, #modale_importoDa, #modale_importoA').val('');
		
        $("#modaleRicercaProvvisorioDiCassa").modal("show");
    });
    
	$('.idOrdinativi').click(function() {
		var sommaImportiOrdinativiSelezionati = $('#sommaImportiOrdinativiSelezionati');

		sommaImportiOrdinativiSelezionati.text(
			floatToImporto(
					new BigNumber(importoToFloat(sommaImportiOrdinativiSelezionati.text()))
					.plus(new BigNumber((this.checked ? +1 : -1) * importoToFloat($(this).closest('td').data('importo'))))
					.toNumber()
		));
		
		$('#pulsanteApriModaleProvvisorioCassa').toggleClass('disabled', $('.idOrdinativi:checked').size() === 0);
	});

    //
	
});