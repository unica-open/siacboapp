/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/

$(document).ready(function() {
	var trs = $('#indicatori tr');
	var trsa = $('#indicatori.analitici tr');
	var tdimp = $('td.importo input');
	var tdint = $('td.intero input');
	var tabScroll = $('table.scrolling');

	trsa.each(function() {
		var id, tds;
		var td = $('td.classif', this);

		if (td.length > 0) {
			id = td.data('classif-id');
			tds = $('td[data-classif-id="' + id + '"]', trsa);
			td.attr('rowspan', tds.length);
			tds.not(td).remove();
		}
	}); 
	
	trs.each(function(){
		var tds = $('td.value', this); 
		tds.each(function(){
			$(this).data('index', tds.index(this));
		});
	});	

	tdimp.formatSignedImporto();
	tdint.formatIntero();

	$('td.value input').change(function(evt) {
		$(this).attr('data-value', $(this).val());
	});
	
	
	if (tabScroll) {
		var show = tabScroll.data('scroll-show');
		var max = tabScroll.data('scroll-max');
		
		var scroll = function(incr) {
			var pos = tabScroll.data('scroll-pos') || 0;
			var curr = pos + incr;
			
			if (curr < 0 || curr + show > max) {
				return;
			}
			
			tabScroll.data('scroll-pos', curr);
			
			for (var i = 0; i < curr; i++)
				$('[data-scroll-idx="'+i+'"]').hide();

			for (var i = curr; i < curr + show && i < max; i++)
				$('[data-scroll-idx="'+i+'"]').show();
			
			for (var i = curr + show; i < max; i++)
				$('[data-scroll-idx="'+i+'"]').hide();
		}
		
		$('#scroll-right').click(function() { scroll(+1) });
		$('#scroll-left').click(function() { scroll(-1) });
		scroll(0);
	}
	
	
	$('#update').click(function() {
		var serialized = "";
		
		$('input[data-value]').each(function() {
			serialized += $(this).closest('tr').data('uid') + ':' + $(this).closest('td').data('index') + '=' + $(this).data('value') + '/';
		});
	
		$('#serializedValues').val(serialized.slice(0, -1));	
	});
});