/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/


var normalizedLocationPathname = document.location.pathname.match(/[^\/]+$/)[0].match(/^\w+/)[0];


$(function() {
	$("p.filter-table > input").val(getCookie("filterTable")).trigger("keyup");
	
	$("#simple-pagination a.simple-pagination-navigation-page[data-simple-pagination-page-number='"
	         + getCookie("simplePagination")+"']").trigger("click");
});


$(window).on('unload', function() {
	
	setCookie("filterTable", $("p.filter-table > input").val());   
	
	setCookie("simplePagination", $("#simple-pagination a.simple-pagination-navigation-disabled")
			.data("simple-pagination-page-number"));
});


function setCookie(id, value) {
	Cookies.set(normalizedLocationPathname + '-' + id, value);
}

function getCookie(id) {
	
	var c = Cookies.get(normalizedLocationPathname + '-' + id);
	
	if (c)
		return c;
	else
		return "";
}
