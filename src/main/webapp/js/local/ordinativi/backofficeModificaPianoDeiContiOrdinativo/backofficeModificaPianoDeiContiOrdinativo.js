/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
	var urlAjaxPerSelect = "ricercaClassificatoriByParentJson.do";
	var urlAjaxPerPDC = "cercaPianoDeiContiJson.do";
	var $spanPDC = $('#SPAN_pdcFin');
	var $fieldset = $("fieldset[id^='fieldset_']");
	var $pulsanteCerca = $("#pulsanteCercaOrdinativo"); 
	var $pulsanteModifica = $("#confermaModifica");
	var $uidCapitolo = $('#HIDDEN_uidCapitolo');
	var $idTitoloCapitolo = $('#idTitoloCapitolo');
	var $idTipologiaCapitolo = $('#idTipologiaCapitolo');
	//utilita
	var $selectTitolo;
	var tipoOrdinativo;
	var uidTitoloSelezionato;
	var uidTipologiaSelezionata;
	//SPESA
	var $spesa = $('#parte_spesa');
	var $idMacroAggregato = $('#idMacroaggregatoCapitolo');
	var $selectMacroAggregato = $('#selectMacroAggregato');
	var $selectTitoloSpesa = $('#selectTitoloSpesa');
	//ENTRATA
	var $entrata = $('#parte_entrata');
	var $entrataModifiche = $('#parte_entrata_modifiche');
	var $idCategoria = $('#idCategoriaCapitolo');
	var $selectCategoria = $('#selectCategoria');
	var $selectTipologia = $('#selectTipologia');
	var $selectTitoloEntrata = $('#selectTitoloEntrata');
	//GENERALE 
	var $handlerGenerale = $('#handlerGenerale');
	var $inserisciGeneraleHidden = $('#inserisciGeneraleHidden');
	//GSA
	var $handlerGSAGenerale = $('#handlerGSAGenerale');
	var $inserisciGSAGeneraleHidden = $('#inserisciGSAGeneraleHidden');
	//Cappello HIDDEN
	var elencoPdcOrdinativo;
	var zTreeObjModale;
	var elDis;

	$(document).ready(function(){

		$('#modificaAccertamentoCheckbox').change(function(){
			if($('#modificaAccertamentoCheckbox').is(':checked')) {
				$(this).val('true');
			} else {
				$(this).val('false');
			}
		});
		
		$(".close-error").on('click',function(e){
			e && e.preventDefault();
			hideErrors();
		});

		hideSpinner('#pulsanteCercaOrdinativo');
		hideSpinner('#pulsanteSelezionaPDC');
		
		resettaCheckBox();

		//GENERALE
		$('input[id^=aggiornaGenerale]').click(function(){
			if($('#aggiornaGeneraletrue').is(':checked')) {
				$inserisciGeneraleHidden.slideDown();
			} else {
				$inserisciGeneraleHidden.slideUp();
				nascondiBene($('#inserisciGeneralefalse'), true);
			}
			if($('#aggiornaGSAGeneralefalse').is(':checked')
				&& $('#aggiornaGeneralefalse').is(':checked')
				|| ($('#aggiornaGeneralefalse').is(':checked')
				&& $('#inserisciGSAGeneralefalse').is(':checked')) ){
				chiudiHandlerGenerale();
			}
		});

		$('input[id^=inserisciGenerale]').click(function(){
			if($('#inserisciGeneraletrue').is(':checked')) {
				if($('#selectEvento')["0"].length <= 0){
					cercaEventoByTipo(this.closest("div[id$='Hidden']").id);
				}
				$handlerGenerale.slideDown();
			}
			if($('#inserisciGeneralefalse').is(':checked') 
				&& $('#inserisciGSAGeneralefalse').is(':checked')) {
				chiudiHandlerGenerale();
			}	
		});
		//
		
		//GENERALE GSA
		$('input[id^=aggiornaGSAGenerale]').click(function(){	
			if($('#aggiornaGSAGeneraletrue').is(':checked')) {
				$inserisciGSAGeneraleHidden.slideDown();
			} else {
				$inserisciGSAGeneraleHidden.slideUp();
				nascondiBene($('#inserisciGSAGeneralefalse'), true);
			}
			if(($('#aggiornaGSAGeneralefalse').is(':checked')
				&& $('#aggiornaGeneralefalse').is(':checked'))
				|| ($('#aggiornaGSAGeneralefalse').is(':checked')
					&& $('#inserisciGeneralefalse').is(':checked')) ){
					chiudiHandlerGenerale();
			}
		});

		$('input[id^=inserisciGSAGenerale]').click(function(){	
			if($('#inserisciGSAGeneraletrue').is(':checked')) {
				if($('#selectEvento')["0"].length <= 0){
					cercaEventoByTipo(this.closest("div[id$='Hidden']").id);
				}
				$handlerGenerale.slideDown();
			}
			if($('#inserisciGeneralefalse').is(':checked') 
				&& $('#inserisciGSAGeneralefalse').is(':checked')) {
				chiudiHandlerGenerale();
			}	
		});
		//

		$pulsanteCerca.on('click', function(e){
			hideErrors();
			
			var urlAjax = '/siacboapp/ricercaOrdinativoJson.do';
			var oggettoPerChiamataAjax = $fieldset.serializeObject();

			e && e.preventDefault();

			$pulsanteCerca.addClass("disabled");
			$pulsanteCerca.prop("disabled", true);
			
			$fieldset.addClass('form-submitted');
			
			showSpinner('#pulsanteCercaOrdinativo');
			hideErrors();

			$('#riepilogoOrdinativoCaricato').slideUp();
			$('#confermaModifica').hide();
			
			postJSON(urlAjax, 
				oggettoPerChiamataAjax,
				doneCallback,
				failCallback,
				alwaysCallback
			);
		});

		//ricerca alberi a cascata
		$selectTitoloSpesa.on('change',function(e){
			if(this.value != 0){
				blockAndEmpty($selectMacroAggregato);
				bloccaElemento($pulsanteModifica, true);
				$idTitoloCapitolo.val(this.value);
				var objAjax = {
					"uidPadre": this.value,
					"criteri.codiceTipo" : tipoOrdinativo,
					"capitoloAssociato.uid" : $uidCapitolo.val()
				};
				cercaFigli($selectMacroAggregato, urlAjaxPerSelect, objAjax);
			}
		});

		$selectTitoloEntrata.on('change',function(e){
			if(this.value != 0){
				blockAndEmpty($selectTipologia);
				bloccaElemento($pulsanteModifica, true);
				$idTitoloCapitolo.val(this.value);
				var objAjax = {
					"uidPadre": this.value,
					"criteri.codiceTipo" : tipoOrdinativo,
					"capitoloAssociato.uid" : $uidCapitolo.val()
				};
				cercaFigli($selectTipologia, urlAjaxPerSelect, objAjax);
			}
		});

		$selectTipologia.on('change',function(e){
			if(this.value != 0){
				blockAndEmpty($selectCategoria);
				bloccaElemento($pulsanteModifica, true);
				$idTipologiaCapitolo.val(this.value);
				var objAjax = {
					"uidPadre": this.value,
					"criteri.codiceTipo" : tipoOrdinativo,
					"capitoloAssociato.uid" : $uidCapitolo.val()
				};
				cercaFigli($selectCategoria, urlAjaxPerSelect, objAjax);
			}
		});
		
		//devo cercare i pdc
		$selectCategoria.on('change',function(e){
			if(this.value != 0){
				bloccaElemento($pulsanteModifica, true);
				$idCategoria.val(this.value);
				var objAjax = {
					"uidPadre": this.value,
					"criteri.codiceTipo" : tipoOrdinativo,
					"capitoloAssociato.uid" : $uidCapitolo.val()
				};
				showSpinner('#pulsanteSelezionaPDC');
				bloccaElemento($('#pulsanteSelezionaPDC'), true);
				// $('#pulsanteSelezionaPDC').addClass("disabled");
				// $('#pulsanteSelezionaPDC').prop("disabled", true);
				cercaFigli(e, urlAjaxPerPDC, objAjax);
			}
		});
		
		$selectMacroAggregato.on('change',function(e){
			if(this.value != 0){
				bloccaElemento($pulsanteModifica, true)
				$idMacroAggregato.val(this.value);
				var objAjax = {
					"uidPadre": this.value,
					"criteri.codiceTipo" : tipoOrdinativo,
					"capitoloAssociato.uid" : $uidCapitolo.val()
				};
				showSpinner('#pulsanteSelezionaPDC');
				bloccaElemento($('#pulsanteSelezionaPDC'), true);
				// $('#pulsanteSelezionaPDC').addClass("disabled");
				// $('#pulsanteSelezionaPDC').prop("disabled", true);
				cercaFigli(e, urlAjaxPerPDC, objAjax);
			}
		});

		// validazione dei controlli formali
		// se superati ne segue un submit
		$pulsanteModifica.on('click', function(e) {
			var url = '/siacboapp/modificaPianoDeiContiOrdinativo_controlliFormali.do';
			var oggettoPerChiamataAjax = pulisciOggetto($fieldset.serializeObject());

			e && e.preventDefault();

			hideErrors();
			bloccaElemento($pulsanteModifica, true);

			$pulsanteCerca.addClass("disabled");
			$pulsanteCerca.prop("disabled", true);
			
			// $alertWarning.slideUp();
			$fieldset.addClass('form-submitted');

			postJSON(
				url,
				oggettoPerChiamataAjax,
				function(data){
					if(showErrors(data.errori)) {
						return;
					}
					if(showWarnings(data.messaggi)){
						return;
					}
					$('#formBackofficeOrdinativoModificaPianoDeiConti').submit();
				},
				failCallback,
				alwaysCallback
			);

		});
		
	});

	function chiudiHandlerGenerale(){
		$handlerGenerale.slideUp();
		$('#selectEvento')["0"].length = 0;
		$('#selectTipoEvento')["0"].length = 0;
		$('HIDDEN_selectTipoEvento').val("");
		$('HIDDEN_collegamentoTipoEvento').val("");
		$('HIDDEN_selectEvento').val("");
	}

	function resettaCheckBox(){
		$('#aggiornaGeneralefalse')[0].checked = true;
		$('#aggiornaGeneralefalse').click();
		$('#inserisciGeneralefalse')[0].checked = true;
		$('#inserisciGeneralefalse').click();
		//GSA
		$('#aggiornaGSAGeneralefalse')[0].checked = true;
		$('#aggiornaGSAGeneralefalse').click();
		$('#inserisciGSAGeneralefalse')[0].checked = true;
		$('#inserisciGSAGeneralefalse').click();
	}

	function cercaEventoByTipo(idParent){
		postJSON('/siacboapp/ricercaEventoModificaPianoDeiConti.do',
			{"criteri.codiceTipo": tipoOrdinativo},
			function(data){
				if(showErrors(data.errori)) {
					return;
				}
				if(showWarnings(data.messaggi)){
					return;
				}
				if(idParent.indexOf('inserisciGeneraleHidden') >= 0 
					|| idParent.indexOf('inserisciGSAGeneraleHidden') >= 0){
					creaElencoCLassificatori($('#selectEvento'), data.eventi, 0, true);
					creaElencoCLassificatori($('#selectTipoEvento'), 
						[data.eventi[0].siacDEventoTipo], data.eventi[0].siacDEventoTipo.uid);
						$('#HIDDEN_selectTipoEvento').val(data.eventi[0].siacDEventoTipo.codice);
						$('#HIDDEN_collegamentoTipoEvento').val(data.eventi[0].siacDCollegamentoTipo.codice);						
				}
			}, 
			failCallback,
			alwaysCallback
		);

	}

	/**
	 * Metodo che blocca o sblocca l'elemento jQuery passato
	 * 
	 * @param $elemento 
	 * @param blocca 
	 */
	function bloccaElemento($elemento, blocca){
		if(blocca){
			$elemento.addClass("disabled");
			$elemento.prop("disabled", true);
		} else {
			$elemento.removeClass("disabled");
			$elemento.removeProp("disabled");
		}
	}

	function nascondiBene(elemento, bool){
		//400 default jquery time for slideUp/slideDown
		setTimeout(function(){ elemento.prop('checked', true); }, bool ? 400 : 0);
	}

	function doneCallback(data) {
		if(showErrors(data.errori)) {
			return;
		}

		if(showWarnings(data.messaggi)){
			return;
		}
		
		tipoOrdinativo = data.elencoOrdinativi[0].tipo.codice;
		
		resettaCheckBox();
		azzeraControparteAlberaturaHidden();

		//imposto la tabella
		impostaDatiInTabella(data);

		//pulisco i risultati
		data.listaCategorieMacroAggregati[0].parent.children = 
			allineaPadreFigli(data.listaCategorieMacroAggregati[0]);

		//scelgo cosa mostrare
		if(tipoOrdinativo === 'P'){
			$spesa.show();
			$entrata.hide();
			$entrataModifiche.hide();
			$selectTitolo = $('#selectTitoloSpesa');
			uidTitoloSelezionato = data.listaCategorieMacroAggregati[0].parent.uid;
		} else {
			$spesa.hide();
			$entrata.show();
			$entrataModifiche.show();
			$selectTitolo = $('#selectTitoloEntrata');
			uidTitoloSelezionato = data.listaCategorieMacroAggregati[0].parent.parent.uid;
			uidTipologiaSelezionata = data.listaCategorieMacroAggregati[0].parent.uid;
		} 

		//controllo 
		impostaSelectFromResult(data.titoli, uidTitoloSelezionato, $selectTitolo);
		$idTitoloCapitolo.val(uidTitoloSelezionato);
		
		//se e' un ordinativo di incasso popolo le tipologie
		if(tipoOrdinativo === 'I'){ 
			impostaSelectFromResult(data.tipologie, 
				uidTipologiaSelezionata, $selectTipologia);

			$idTipologiaCapitolo.val(uidTipologiaSelezionata);
		}
		
		//imposto le categorie o i macroaggregati		
		impostaMacroaggregatiCategorie(data);

		//ordino i genitori
		var elencoPdcOrdinativo = data.classificatoriWrapper.sort(function(a, b){
			var a = a.codice;
			var b = b.codice;
			if (a < b) {
				return -1;
			}
			if (a > b) {
				return 1;
			}
			return 0;
		});

		if(data.classificatoriWrapper.length > 0){
			$('#elementiModificaPdcOrdinativoWait').hide();
		}

		//imposto lo span e lo ztree
		//ZTREE
		zTreeObjModale = initZTree("#elementiPdcModificaOrdinativo", elencoPdcOrdinativo);
		handleZTreeClick(zTreeObjModale, "_modale");
		var checkedNode = zTreeObjModale.getNodeByParam("uid", data.classificatori[0].uid);
		zTreeObjModale.checkNode(checkedNode, true, true);
		
		impostaPdcHidden(checkedNode);
		//SPAN
		impostaSpanPDC(checkedNode.descrizioneCompleta, true);
		//mostro il riepilogo
		$('#riepilogoOrdinativoCaricato').slideDown();
		//mostro il submit
		$('#confermaModifica').slideDown();

	}
	
	function failCallback(data) {
		alwaysCallback()
	}

	function alwaysCallback() {
		hideSpinner('#spinnerElementoPdcModificaOrdinativo');
		hideSpinner('#pulsanteCercaOrdinativo');
		$pulsanteModifica.removeClass("disabled");
		$pulsanteModifica.removeProp("disabled");

		$pulsanteCerca.removeClass("disabled");
		$pulsanteCerca.removeProp("disabled");
		
		$fieldset.removeClass('form-submitted');
	}

	function pulisciOggetto($oggettoDaPulire){
		var obj = $oggettoDaPulire;
		obj["idTitoloCapitolo"] = $oggettoDaPulire["criteri.codiceTipo"] === 'I' 
			? $('#selectTitoloEntrata').val() : $('#selectTitoloSpesa').val();
		obj["idMacroAggregato"] = $oggettoDaPulire["criteri.codiceTipo"] === 'I' 
			? $('#selectCategoria').val() : $('#selectMacroAggregato').val();
		return obj;
	}

	function impostaPdcHidden(node){
		$('#idPianoDeiContiCapitolo').val(node.uid);
		$('#codiceClassificatorePdc').val(node.tipoClassificatore.codice);
		$('#idPianoDeiContiCapitoloPadrePerAggiorna').val(node.uid);
		$('#codicePianoDeiContiCapitolo').val(node.codice);
		$('#descrizionePianoDeiContiCapitolo').val(node.descrizioneCompleta);
	}

	function impostaDatiInTabella(data) {
		var ordinativo = data.elencoOrdinativi[0];
		var capitolo = data.capitoloAssociato;
		var $elencoTD = $('td[id^="tabellaModaleOrdinativoCaricato_"]');
		$elencoTD[0].innerHTML = ordinativo.anno + "/" + ordinativo.numero;
		$elencoTD[1].innerHTML = ordinativo.descrizione;
		$elencoTD[2].innerHTML = capitolo.bilancio.periodo.anno + "/" + capitolo.codice + "/" + 
			capitolo.codiceArticolo + "/" + capitolo.codiceUeb + " - " + capitolo.descrizione;
		$elencoTD[3].innerHTML = ordinativo.stato.descrizione;
		$elencoTD[4].innerHTML = ordinativo.tipo.descrizione;
		$elencoTD[5].innerHTML = ordinativo.dataEmissione;
		$uidCapitolo.val(capitolo.uid);
	}

	function azzeraControparteAlberaturaHidden(tipo){
		//non gestisco il titolo, viene modificato dopo
		if(tipo === 'Spesa'){
			$idCategoria.val(0);
			$idTipologiaCapitolo.val(0);
			$('#codicePianoDeiContiCapitolo').val("");
			$('#descrizionePianoDeiContiCapitolo').val("");
		}
		if(tipo === 'Entrata'){
			$idMacroAggregato.val(0);
			$('#codicePianoDeiContiCapitolo').val("");
			$('#descrizionePianoDeiContiCapitolo').val("");
		}
	}

	function impostaSpanPDC(data, fromZtree){
		if(fromZtree){
			$spanPDC.html(data);
			return;
		}
		$spanPDC.html(data.classificatoriWrapper[0].descrizioneCompleta);
	}

	function impostaSelectFromResult(arraySelect, uidSelected, $select){
		creaElencoCLassificatori($select, arraySelect, uidSelected);
	}

	function impostaMacroaggregatiCategorie(data){
		var selectByTipo = data.elencoOrdinativi[0].tipo.codice === 'P' ?
			$selectMacroAggregato : $selectCategoria;
		//popolo la lista
		creaElencoCLassificatori(selectByTipo, 
			data.listaCategorieMacroAggregati[0].parent.children, 
			data.listaCategorieMacroAggregati[0].uid);

		$idMacroAggregato.val(data.elencoOrdinativi[0].tipo.codice === 'P' ?
			data.listaCategorieMacroAggregati[0].uid : 0);
		$idCategoria.val(data.elencoOrdinativi[0].tipo.codice === 'I' ?
			data.listaCategorieMacroAggregati[0].uid : 0);
	}

	function allineaPadreFigli($listaClassificatori){
		var listaPulita = $listaClassificatori.parent.children != null ?
			$listaClassificatori.parent.children : [];
		listaPulita.push($listaClassificatori);
		for (let i = 0; i < listaPulita.length; i++) {
			if(listaPulita[i] === null) listaPulita.splice(i,1);
		}
		listaPulita.sort(function(a, b){
			var a = a.codice;
			var b = b.codice;
			if (a < b) {
				return -1;
			}
			if (a > b) {
				return 1;
			}
			return 0;
		});
		return listaPulita;
	}

	function creaElencoPianoDeiConti(data){
		//se un pdc scelgo la gestione completa
		//aggiorno l'elenco
		elencoPdcOrdinativo = data.classificatoriWrapper.sort(function(a, b){
			var a = a.codice;
			var b = b.codice;
			if (a < b) {
				return -1;
			}
			if (a > b) {
				return 1;
			}
			return 0;
		});
		//SPAN
		impostaSpanPDC(data, false);
		//ZTREE
		zTreeObjModale = initZTree("#elementiPdcModificaOrdinativo", elencoPdcOrdinativo);
		handleZTreeClick(zTreeObjModale, "_modale");
	}

	function creaElencoCLassificatori($select, elenco, $uidSelezionato, isCodice){
		var option = '<option value="0"></option>';
		elenco.forEach(function(elemento){
			if(elemento.uid === $uidSelezionato){
				option += '<option value="' + (isCodice ? elemento.codice : elemento.uid) + '" selected>' 
					+ elemento.codice + " - " + elemento.descrizione 
				+ '</option>';
			} else {
				option += '<option value="' + (isCodice ? elemento.codice : elemento.uid) + '">' 
					+ elemento.codice + " - " + elemento.descrizione 
				+ '</option>';
			}
		});
		$select.html(option);
	}

	function initZTree(id, elencoZtree) {
		return $.fn.zTree.init($(id), {
	        check : {
	            radioType : "all",
	            enable : true,
	            chkStyle : "radio"
	        },
	        data : {
	           	key : {
	           	    name : "descrizioneCompleta",
	           	    children : "children"
	        	},
				simpleData: {
					enable: true,
					idKey: "uid",
					pIdKey: "uidParent",
					rootPId: 0
				}
	        }
	    }, elencoZtree);
	}

    function handleZTreeClick(zt, suff = '') {
        $('#confermaPdc').click(function(e) {
            var checkedNodes = zt.getCheckedNodes(true);
			e.preventDefault();
			
            if (checkedNodes.length > 0) {
                impostaPdcHidden(checkedNodes[0]);
                impostaSpanPDC(checkedNodes[0].descrizioneCompleta, true);
			}
			
			$('#myModal').hide();
			$('.modal-backdrop').hide();
        });

	}
	
	function cercaFigli(select, url, oggettoPerChiamataAjax) {
		hideErrors();
		elDis = select;
		postJSON(url, 
			oggettoPerChiamataAjax,
			doneCercaFigli,
			function(){},
			function(){
				hideSpinner('#pulsanteSelezionaPDC');
				bloccaElemento($pulsanteModifica, false);
				bloccaElemento($('#pulsanteSelezionaPDC'), false);
				if(elDis[0] && (elDis[0].id === 'selectCategoria'
					|| elDis[0].id === 'selectMacroAggregato'
					|| elDis[0].id === 'selectTipologia')){
					elDis.prop('disabled', false);
				}
			}
		);
	}

	function doneCercaFigli(data){
		if(showErrors(data.errori)) {
			return;
		}

		if(showWarnings(data.messaggi)){
			return;
		}

		if(data.classificatori === null || typeof data.classificatori === 'undefined'){
			creaElencoPianoDeiConti(data);
		} else {
			creaElencoCLassificatori(elDis, data.classificatori, 0);
		}
	}

	function blockAndEmpty($select){
		$select.val(0);
		$select.prop('disabled', true);
	}


