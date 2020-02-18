/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() {
		$("#upload-file").change(function() {
		
		$("#nome-file").text($(this).val().split(/[\\/]/).pop());
	});
});
