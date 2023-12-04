/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/

$(function() { 
	
	//SIAC-7884
	$('input[value="false"].flagPrenotazione').click(function() {
		alert('Attenzione, si modifica anche flag prenotazione liquidabile');
		$('input[value="false"].flagPrenotazioneLiquidabile').trigger('click');
	});
	
	
});

