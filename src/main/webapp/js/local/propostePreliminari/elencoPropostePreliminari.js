/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(document).ready(function() 
{
	jQuery('#simple-pagination-proposte').simplePagination({
		html_prefix: 'simple-pagination-proposte'
	});

	jQuery('#simple-pagination table').filterTable({
		minRows:  5
	});

	
	jQuery('#simple-pagination-proposte table').filterTable({
		lookupSelector: ".descrizione",
		rangeSelector: ".numero",
		minRows: 5,
		callback: function(term, table) 
			{ 
			jQuery('#simple-pagination-proposte').trigger({  
					type: "filtered",
					query: term
				}); 
			}
	});


	$('td.buttons-azioni').each(function() {
		var td = $(this);

		if (td.find('li').length == 0)
			td.html('&nbsp;');
	});

	jQuery(".contentLoading").hide();
	jQuery("#my-tab-content").removeClass("nascosto");

	var modalClickHandler = function(mod) {
		$('.azione'+mod).click(function (event) {
			var az = $(this);
	
			var modalAnchor = $('#modal'+mod+' .anchor-azione');
			modalAnchor[0].confirm = true;
			modalAnchor.attr('href', modalAnchor.attr('href').replace(/puid=\d+$/, 'puid=' + az.data('uid-proposta')));
		});
	
		$('#modal'+mod).on('hidden', function () {
			var modalAnchor = $(".anchor-azione", $(this));
	
			if (modalAnchor[0].confirm) {
				modalAnchor[0].confirm = false;
				location.href = modalAnchor.attr('href');
			}
		});		
	};

	var a = [ 'Elimina', 'Invia', 'Approva' ];
	for (i = 0; i < a.length; i++)
		modalClickHandler(a[i]);



	$('#inserisciProposta, td.descrizioneCapitolo a').click(function() {
		setCookie("elencoPropostePreliminari_currentTab", '#elencoProposte');
		$('#tabs').data('skip-set-cookie', true);
	});

	if (getCookie("elencoPropostePreliminari_currentTab"))
		$('#tabs li a[href="' + getCookie("elencoPropostePreliminari_currentTab") + '"]').tab('show');
});



$(function() {
$("#simple-pagination-proposte a.simple-pagination-proposte-navigation-page[data-simple-pagination-page-number='"
	         + getCookie("simplePaginationProposte")+"']").trigger("click");


});


$(window).on('unload', function() {

	setCookie("simplePaginationProposte", $("#simple-pagination-proposte a.simple-pagination-proposte-navigation-disabled")
			.data("simple-pagination-proposte-page-number"));

	if ($('#tabs').data('skip-set-cookie') !== true)
		setCookie("elencoPropostePreliminari_currentTab", $('#tabs li.active a').attr('href'));
});

