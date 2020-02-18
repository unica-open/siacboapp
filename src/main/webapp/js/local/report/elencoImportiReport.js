/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() 
{
	$("#setReport").change(function()
	{
		window.location = "elencoImportiReport.do?idReport=" + $(this).val();
	});
	
	
	$(".importo").formatSignedImporto();
	
	$('#aggiorna').click(function() {
		addHiddenToForm('idReport', $("#setReport").val());
		
		$('.field').each(function() {
			var origVal = $(this).data('orig');
			var input = $(this).find('input');
			var val = input.val();
			
			if (String(val) !== String(origVal)) {
				addHiddenToForm(input.attr('name'), val);
			}
		});
		
	});
	
	function addHiddenToForm(name, value) {
		$('#form').append('<input type="hidden" name="' + name + '" value="' + value + '" />');
	}
	
});