/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() {
	$(".chosen-select-capitolo-proposta").chosen({
		allow_single_deselect:true,
		placeholder_text_single: 'Selezionare capitolo',
		search_contains: true,
		no_results_text: ''
	}); 
});





$(document).ready(function() 
{
	if ($('.editPropostaPreliminare').length == 0)
		$('form input, form textarea').attr('readonly', true);
	

	
	var selected = false;
	
	$('table.importi td input').keypress(function(e) 
	{
		if (e.which < 32)
			return true;

	    var t = [$(this).val().slice(0, this.selectionStart), e.key, $(this).val().slice(this.selectionEnd)].join('');

	    if (t.match(/^([1-9]\d*|0)(\,|\,\d{0,2})?$/) != null) {
	     // $(this).val(t);
	      selected = false;
	      return true;
	    }

	    return false;

	  }).focus(function(e) {
	    $(this).val($(this).val().replace(/\./g, ''));
	    
	  }).blur(function(e) {
	    $(this).val($(this).val().replace(/\./g, ''));
	    
	    if ($(this).val().match(/^([1-9]\d*|0)(\,|\,\d{0,2})?$/) == null)
	      $(this).val('')
	    else
	      $(this).val($(this).val().replace(/[^\d\,]/g, '').replace(/^(\d+)\,?$/, '$1,00').replace(/(\,\d)$/, '$10').replace(/\B(?=(\d{3})+(?!\d))/g, '.'));
	      
	  }).select(function(e) {
	    selected = true;
	  });
});
