/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(document).ready(function() 
{
	var zTreeObj = $.fn.zTree.init($("#treeStruttAmm"),
	{
		callback: {
			onCheck: onCheckHandler
		},
		check: { 
			enable: true,
			chkboxType: { "Y": "", "N": "" },
		},
		data: {	
			key: { name: "descr" },
			simpleData: { enable: true, idKey: "uid", pIdKey: "puid", rootPId: -1 }
		}
	}, 

		elencoStruttureAmministrativoContabiliJson
	);

	function onCheckHandler(event, treeId, treeNode) {
	    if (treeNode.checked)
	    	$('#struttureAmministrativeContabiliId').append('<input type="hidden" name="accountEntity.struttureAmministrativeContabiliId" value="' + treeNode.uid + '" />');
	    else
			$('input[value=' + treeNode.uid + ']').remove();
	};
});