/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
jQuery(function() {
	
	jQuery.extend(jQuery.fn.simplePagination.defaults, 
			{
				pagination_container: 'tbody',
				html_prefix: 'simple-pagination',
				navigation_element: 'a',//button, span, div, et cetera
				items_per_page: 5,
				number_of_visible_page_numbers: 10,
				//
				use_page_numbers: true,
				use_first: true,
				use_previous: true,
				use_next: true,
				use_last: true,
				//
				use_page_x_of_x: true,
				use_page_count: true,// Can be used to combine page_x_of_x and specific_page_list
				use_showing_x_of_x: true,
				use_item_count: true,
				use_items_per_page: true,
				use_specific_page_list: true,
				//
				first_content: '&laquo; inizio',  //e.g. '<<'
				previous_content: '&lsaquo; prec',  //e.g. '<'
				next_content: 'succ &rsaquo;',  //e.g. '>'
				last_content: 'fine &raquo;', //e.g. '>>'
				page_x_of_x_content: 'Pag.',
				showing_x_of_x_content: '',
				//
				items_per_page_content: {
					'-': 100
				},
		thousands_separator: ','
	}			
	);
	    
		
		
	jQuery('#simple-pagination').simplePagination();

});
