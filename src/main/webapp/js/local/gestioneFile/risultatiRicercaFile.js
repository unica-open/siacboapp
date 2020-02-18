/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(document).ready(function() 
{
	$(".consulta-file").click(function() {
		  var tr = $(this).parents("tr");
		  
		  $("#consulta-tipo").text($(".file-tipo", tr).text());
		  $("#consulta-codice").text($(".file-codice", tr).text());
		  $("#consulta-nome").text($(".file-nome", tr).text());
		  $("#consulta-stato").text($(".file-stato", tr).text());
		  $("#consulta-dataUpload").text($(".file-dataUpload", tr).text());
		  $("#consulta-note").text($(".file-nome", tr).attr("data-content"));
	});
	
	$(".azione-file").click(function() {
		  var tr = $(this).parents("tr");

		  $(".anchor-azione > span").text($(".file-index", tr).text());
	});
	
	$(".conferma-azione").click(function() {
		var anchor = $("A", $(this).parent());
		anchor[0].confirm = true;
		anchor.attr('href', anchor.attr('href') + '?index=' + $("span", anchor).text());
	});
	
	$(".modal-azione").on('hidden', function () {
		var anchor = $(".anchor-azione", $(this));
		//anchor.trigger('click');
		if (anchor[0].confirm) {
			anchor[0].confirm = false;
			location.href = anchor.attr('href');
		}
	});
	
	
});

