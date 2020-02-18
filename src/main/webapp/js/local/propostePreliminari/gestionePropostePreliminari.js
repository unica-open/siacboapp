/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/

$(document).ready(function() 
{
	$('#form').on('keyup keypress', function(e) {
		  var keyCode = e.keyCode || e.which;
		  if (keyCode === 13) { 
		    e.preventDefault();
		    return false;
		  }
	});

	$('#missione').change(function() {
		window.location='gestionePropostePreliminari<s:property value="capitalize(tipoProposta)" />.do?mid=' + this.value;
	});


	var zTreeObj = $.fn.zTree.init($("#treeStruttAmm"),
		{   
			check: { 
					radioType: "all",
					enable: true,
					chkStyle: "radio"
				},
			data: {	
					key: { 
							name: "descrizioneCompleta",
							children: "sottoElementi"
						}
				}
		}, 
		elencoStruttureAmministrativoContabiliJson
	);

	$('#confermaStrutturaAmministrativoContabile').click(function(e) {
		var checkedNodes = zTreeObj.getCheckedNodes(true);

		if (checkedNodes.length > 0)
		{
			$('#idStrutturaAmministrativoContabile').val(checkedNodes[0].uid);
			$('#SPAN_StrutturaAmministrativoContabile').text(checkedNodes[0].descrizioneCompleta);
		}
		
	});
	
	$('#deselezionaStrutturaAmministrativoContabile').click(function(e) {
		var checkedNodes = zTreeObj.getCheckedNodes(true);

		if (checkedNodes.length > 0)
			zTreeObj.checkNode(checkedNodes[0], false, true, false);
		
		e.stopPropagation();
		return false;
	});

});