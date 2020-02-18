/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(document).ready(function() 
{
	$("input.cronoprogramma + label").each(function() { 
		$(this).prev("input").prependTo(this);  
	});
	
	$("#usaGestione").change(function() 
	{
		initUsaGestione(this);
	});
	
	initUsaGestione($("#usaGestione")[0]);

});

function initUsaGestione(cb)
{
	if (cb && cb.checked)
		$(".cronoprogramma").prop('checked', false).attr('disabled', 'disabled');
	else
		$(".cronoprogramma").removeAttr('disabled');

}
