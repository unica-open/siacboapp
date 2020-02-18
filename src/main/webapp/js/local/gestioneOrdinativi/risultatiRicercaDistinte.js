/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(document).ready(function() 
{
	
	$.extend(jQuery.fn.simplePagination.defaults, 
			{
				items_per_page: 10,
				number_of_visible_page_numbers: 5
					}			
	);

	$('#simple-pagination').simplePagination();

	$('.modalConsultaButton').click(function (event) {

		var button = $(this);

	    $('#modalConsulta div.modal-body').html('Attendere, prego...');
				
		  $.ajax({
			  url: 'consultaDistinta.do?uid=' + button.data('uid-distinta') + "&codiceTipoOrdinativi=" + $('#codiceTipoDistinte').val(),
			  cache: false
			})
			  .done(function( html ) {  
			    $('#modalConsulta div.modal-body').html(html);
			    $('.codiceDistintaConsulta').html(button.data('numero-distinta'))
			  });
		})

});
