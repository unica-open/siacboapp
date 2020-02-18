/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(document).ready(function() 
{
	$("#tabellaSelezionata").change(function() {
		window.location='elencoDecodifiche.do?idTabellaSelezionata=' + this.value;
	});
	
	
});