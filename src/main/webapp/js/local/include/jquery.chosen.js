/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() {
	$(".chosen-select").chosen({
		placeholder_text_multiple: '',
		placeholder_text_single: '',
		search_contains: true,
		no_results_text: '',
		disable_search_threshold: 5
	}); 
});