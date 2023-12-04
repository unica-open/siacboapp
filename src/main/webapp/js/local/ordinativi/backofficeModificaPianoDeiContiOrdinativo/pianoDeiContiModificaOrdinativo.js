/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
var setting = {
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "level"
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		// anche se seleziona la descrizione
		// deve ceccare il radio button
		onClick: zTreeClick
	}
};


var settingProvvisoriCassa = {
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "level"
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		// anche se seleziona la descrizione
		// deve ceccare il radio button
		onClick: zTreeClick,
		onCheck: onCheckProvvisoriCassa
	}
};


var settingCompetente = {
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "all"
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		// anche se seleziona la descrizione
		// deve ceccare il radio button
		onClick: zTreeClickComp,
		onCheck: onCheckCompetente
	}
};


function zTreeClickComp(event ,treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	if (treeNode!=null) {
		$('#strutturaSelezionataCompetente').val(treeNode.uid);
		treeObj.checkNode(treeNode, true, true);

	}
    return (treeNode.id !== 1);
};

function zTreeClick(event ,treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	if (treeNode!=null) {
		treeObj.checkNode(treeNode, true, true);
	}
    return (treeNode.id !== 1);
};


var settingWithCallbackElementoPdcTransazioneElementare = {
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "level"
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		// anche se seleziona la descrizione
		// deve ceccare il radio button
		onClick: zTreeBeforeClickAlbero,
		// click su radio
		onCheck: onCheckElementoPdcTransazioneElementare
	}
};

var settingWithCallbackTreeModificaPdcOrdinativo = {
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "level"
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		// anche se seleziona la descrizione
		// deve ceccare il radio button
		onClick: zTreeBeforeClickAlbero,
		// click su radio
		onCheck: onCheckElementiPdcModificaOrdinativo
	}
};

/**
 * permette di selezionare il radio anche selezionando solamente
 * la descrizione
 * @param event
 * @param treeId
 * @param treeNode
 * @returns {Boolean}
 */
function zTreeBeforeClickAlbero(event, treeId, treeNode) {
	
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	if (treeNode!=null) {
		treeObj.checkNode(treeNode, true, true);
	}
	// chiamo la fuction che valorizza i radio
	if(treeId=='elementiPdcTransazioneElementare'){
		// PDC
		onCheckElementoPdcTransazioneElementare(event,treeId, treeNode);
	} else if(treeId=='elementiPdcModificaOrdinativo'){
		// MODFIICA PDC BACKOFFICE
		onCheckElementiPdcModificaOrdinativo(event,treeId, treeNode);
	}
	// ritorno true/false
	return (treeNode.id !== 1);
	
};

var code, lastTreeNode, lastTreeNodeSuffix, lastTreeId, lastTreeChecked = false;
var supportTreeMap = {
	"elementiPdcTransazioneElementare": "PianoDeiContiCapitolo",
	"elementiPdcModificaOrdinativo": "PianoDeiContiCapitolo"
};

function setCheck() {
	var type = $("#level").attr("checked")? "level":"all";
	var supportComponents = {};
	type="all";
	setting.check.radioType = type;
	showCode('setting.check.radioType = "' + type + '";');
	settingWithCallbackTreeModificaPdcOrdinativo.check.radioType = type;
	showCode('settingWithCallbackTreeModificaPdcOrdinativo.check.radioType = "' + type + '";');
	var dataPianoDeiConti = "";
	var dataContoEconomicoSiopeSpesa = "";
	var dataContoEconomico = "";
	var trovatoParametro = false;
	var trovatoParametroDtCESSpesa = false;
	
	if (typeof $("#idMacroaggregatoCapitolo") !== 'undefined' && $("#idMacroaggregatoCapitolo").val() != null) {
		dataPianoDeiConti = "idMacroaggregato=" + $("#idMacroaggregatoCapitolo").val(); 
		trovatoParametro = true;
	}

	if (typeof $("#idPianoDeiContiCapitolo") !== 'undefined' && $("#idPianoDeiContiCapitolo").val() != null) {
		if (trovatoParametro) {
			dataPianoDeiConti += "&";
		}
		dataPianoDeiConti += "idPianoDeiConti=" + $("#idPianoDeiContiCapitolo").val(); 
		dataContoEconomicoSiopeSpesa = "idPianoDeiConti=" + $("#idPianoDeiContiCapitolo").val();
		trovatoParametro = true;
		trovatoParametroDtCESSpesa = true;
	}
	
	// classificatore pdc
	if (typeof $("#codiceClassificatorePdc") !== 'undefined' && $("#codiceClassificatorePdc").val() != null) {
		if (trovatoParametro) {
			dataPianoDeiConti += "&";
		}
		dataPianoDeiConti += "codiceClassificatorePdc" + $("#codiceClassificatorePdc").val();
	}	
	
	if (typeof $("#ricaricaAlberoPianoDeiConti") !== 'undefined' && $("#ricaricaAlberoPianoDeiConti").val() != null) {
		if (trovatoParametro) {
			dataPianoDeiConti += "&";
		}
		dataPianoDeiConti += "ricaricaAlberoPianoDeiConti=" + $("#ricaricaAlberoPianoDeiConti").val(); 
		trovatoParametro = true;
	}

	if (typeof $("#idPianoDeiContiCapitoloPadrePerAggiorna") !== 'undefined' && $("#idPianoDeiContiCapitoloPadrePerAggiorna").val() != null) {
		if (trovatoParametro) {
			dataPianoDeiConti += "&";
		}
		dataPianoDeiConti += "idPianoDeiContiCapitoloPadrePerAggiorna=" + $("#idPianoDeiContiCapitoloPadrePerAggiorna").val(); 
		trovatoParametro = true;
	}
	
	supportComponents = {"#elementiPdcModificaOrdinativo": settingWithCallbackTreeModificaPdcOrdinativo,
			"#elementiPdcAggiornaSubimpegnoStep2": settingWithCallbackTreeModificaPdcOrdinativo};
	chiamataAjaxPerPopolaTree('ricercaPianoDeiContiJson.do', dataPianoDeiConti, 'listaPianoDeiConti', supportComponents);

}

function showCode(str) {
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}

$(document).ready(function(){
	setCheck();		
	//alert('post check : ' + setCheck);
	
	$("#level").bind("change", setCheck);
	$("#all").bind("change", setCheck);
	
	
});

function gestisciChangeTreeNode(boolTree, suffix, treeId, treeNode) {
	/*lastTreeNode = treeNode;
	lastTreeNodeSuffix = suffix;
	lastTreeId = treeId;*/
	if(boolTree){
		$("#id" + suffix).val(treeNode.uid);
		$("#codice" + suffix).val(treeNode.codice);
		$("#descrizione" + suffix).val(treeNode.name);
		if(suffix=='PianoDeiContiCapitolo'){
			$("#codiceClassificatorePdc").val(treeNode.tipoClassificatore.codice);
		}
		
	}else{
		$("#id" + suffix).val(0);
		$("#codice" + suffix).val(null);
		$("#descrizione" + suffix).val(null);
		if(suffix=='PianoDeiContiCapitolo'){
			$("#codiceClassificatorePdc").val(null);
		}
	}}

function onCheckProvvisoriCassa(e, treeId, treeNode) {
	$('#strutturaSelezionataSuPagina').val(treeNode.uid);
}

function onCheckCompetente(e, treeId, treeNode) {
	$('#strutturaSelezionataCompetente').val(treeNode.uid);
}

function onCheckElementoPdcTransazioneElementare(e, treeId, treeNode) {
	lastTreeChecked = true;
	gestisciChangeTreeNode(treeNode.checked, "PianoDeiContiCapitolo", "elementiPdcTransazioneElementare", treeNode);
	/*lastTreeNode = treeNode;
	lastTreeNodeSuffix = "PianoDeiContiCapitolo";
	if(treeNode.checked){
		$("#idPianoDeiContiCapitolo").val(treeNode.uid);
		$("#codicePianoDeiContiCapitolo").val(treeNode.codice);
		$("#descrizionePianoDeiContiCapitolo").val(treeNode.name);
	}else{ 
		$("#idPianoDeiContiCapitolo").val(0);
		$("#codicePianoDeiContiCapitolo").val(null);
		$("#descrizionePianoDeiContiCapitolo").val(null);
	}*/
}

function onCheckElementiPdcModificaOrdinativo(e, treeId, treeNode) {
	lastTreeChecked = true;
	gestisciChangeTreeNode(treeNode.checked, "PianoDeiContiCapitolo", "elementiPdcModificaOrdinativo", treeNode);
	/*lastTreeNode = treeNode;
	lastTreeNodeSuffix = "PianoDeiContiCapitolo";
	if(treeNode.checked){
		$("#idPianoDeiContiCapitolo").val(treeNode.uid);
		$("#codicePianoDeiContiCapitolo").val(treeNode.codice);
		$("#descrizionePianoDeiContiCapitolo").val(treeNode.name);
	}else{ 
		$("#idPianoDeiContiCapitolo").val(0);
		$("#codicePianoDeiContiCapitolo").val(null);
		$("#descrizionePianoDeiContiCapitolo").val(null);
	}*/
}

function resetLastTreeNode() {
	if (lastTreeChecked) {
		if (lastTreeNode) {
			gestisciChangeTreeNode(!lastTreeNode.checked, lastTreeNodeSuffix, lastTreeId, lastTreeNode);
			/*if(!lastTreeNode.checked){
				$("#id" + lastTreeNodeSuffix).val(lastTreeNode.uid);
				$("#codice" + lastTreeNodeSuffix).val(lastTreeNode.codice);
				$("#descrizione" + lastTreeNodeSuffix).val(lastTreeNode.name);
			}else{
				$("#id" + lastTreeNodeSuffix).val(0);
				$("#codice" + lastTreeNodeSuffix).val(null);
				$("#descrizione" + lastTreeNodeSuffix).val(null);
			}*/
			$.fn.zTree.getZTreeObj(lastTreeId).checkNode(lastTreeNode, !lastTreeNode.checked, true, false);
		} else if (lastTreeId) {
			var treeObj = $.fn.zTree.getZTreeObj(lastTreeId);
			var nodes = treeObj.getCheckedNodes(true);
			for (var i = 0; i < nodes.length; i++) {
				lastTreeNode = nodes[i];
				break;
			}
			if (lastTreeNode) {
				lastTreeNodeSuffix = supportTreeMap[lastTreeId];
				resetLastTreeNode();
			}
		}
		lastTreeChecked = false;
	}
	lastTreeNode = null;
	lastTreeNodeSuffix = null;
	lastTreeId = null;
}


/**
 * Metodo che fa la chiamata Ajax per la creazionde dell'albero di navigazione
 * 
 * @param url - metodo mappato su struts che si interfaccia con il servizio relativo per la creazione dell'albero
 * @param data - parametri (opzionali) che il metodo richiamato utilizza
 * @param listaDiRiferimento - nome del model che contiene il risultato del servizio
 * @param supportComponents - mappa composta da id (idComponenteAlbero) e setting (propertyAlbero)
 */
function chiamataAjaxPerPopolaTree(url, data, listaDiRiferimento, supportComponents) {
	$.ajax({
		url: url,
        dataType: 'json',
        data: data,
        contentType: "application/json",
        success: function(data, status, xhr) {
        	var supportJson = JSON.stringify(data[listaDiRiferimento]);
        	var supportString = JSON.parse(supportJson);
        	$.each(supportComponents, function(k, v) {
        		$(k+"Div").hide();
        		$(k+"Wait").show();
        		
        		var ztree = $.fn.zTree.init($(k), v, supportString);
       			$(document).trigger("ztree:init", [ ztree.setting.treeId ]);

        		$(k+"Div").show();
        		$(k+"Wait").hide()
        		// alert(" ecco "+k);
        		// il k arriva con il cancelletto davanti
        		// TE - PDC
        		if(k=="#elementiPdcTransazioneElementare"){
        			$("#spinnerElementoPianoDeiContiTE").addClass("hideContent");
        		}
        		if(k=="#elementiPdcModificaOrdinativo"){
        			$("#spinnerElementoPdcModificaOrdinativo").addClass("hideContent");
        		}
        		
        	});
        	
        },
        error: function(xhr, status, error) {}
	});
}

$(document).ready(function() {
	$('.modal')
		.on('hidden', resetLastTreeNode)
		.on('show', function() {
			var zt = $(this).find(".ztree");
			var ztId = zt.attr("id");
			var treeObj = $.fn.zTree.getZTreeObj(ztId);
			if(!treeObj){
				return;
			}
			var nodes = treeObj.getCheckedNodes(true);
			lastTreeId = ztId;
			for (var i = 0; i < nodes.length; i++) {
				lastTreeNode = nodes[i];
				break;
			}
			lastTreeNodeSuffix = supportTreeMap[lastTreeId];
		});
	

	$(this).on('ztree:init', function(evt, ztreeId) {
		if (ztreeId === undefined) return;
		
		var $ztreeId = $('#' + ztreeId);
		var ztree = $.fn.zTree.getZTreeObj(ztreeId);
		
		var updateAccordion = function(text) {
			//SIAC-7583
			//non sostuisco il valore in caso l'accordion sia della transazione elementare
			var accordion = $ztreeId.closest('.accordion').find('.accordion-toggle');
			accordion.text().indexOf('Transazione elementare') === -1 ? accordion.text(text) : $.noop();
			//
		};

		var fnClick = ztree.setting.callback.onClick || function(){};
		var fnCheck = ztree.setting.callback.onCheck || function(){};
		
		ztree.setting.callback.onClick = function(srcEvent, treeId, node, clickFlag) {
			fnClick(srcEvent, treeId, node, clickFlag);
			//    updateAccordion(node.name);
		}
		
		ztree.setting.callback.onCheck = function(srcEvent, treeId, node, clickFlag) {
			fnCheck(srcEvent, treeId, node, clickFlag);
		//    updateAccordion(node.name);
		}
	});
	
});