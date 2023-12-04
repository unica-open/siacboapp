/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() {
	
	$('#simple-pagination table').filterTable({
		lookupSelector: ".descrizione",
		callback: function(term, table) 
			{ 
				$('#simple-pagination').trigger({  
					type: "filtered",
					query: term
				}); 
			}
	});


	$(document).ready(function() 
	{
		if ($('#simple-pagination .filter-table input').val() == 'undefined')
			$('#simple-pagination .filter-table input').val('').trigger('keyup');
	});
});
