<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%-- Inclusione head e CSS --%>
<s:include value="/jsp/include/head.jsp" />

</head>
<body>
	<%-- Inclusione header --%>
	<s:include value="/jsp/include/header.jsp" />

	<%-- BREADCRUMB --%>
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="<s:property value="#urlCruscotto" />">Home</a>
					<span class="divider">&gt;</span></li>
		        <li class="active">Backoffice Modifica Piano Dei Conti</li>
			</ul>
		</div>
	</div>
	<%-- BREADCRUMB --%>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">
				<s:form id="formBackofficeOrdinativoModificaPianoDeiConti" cssClass="form-horizontal form-inline" novalidate="novalidate" action="modificaPianoDeiContiOrdinativo_modifica" method="post">
				<%-- <s:include value="/jsp/include/actionMessagesErrors.jsp" />  --%>
					
					<%-- solo l'informazione verra da un submit --%>
					<s:if test="hasActionMessages()">
						<%-- Messaggio di INFO --%>
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<%-- Rimosso Attenzione!! per JIRA SIAC-5248 --%>
							<strong></strong><br>
							<ul>
								<s:actionmessage escape="false" />
							</ul>
						</div>
					</s:if>
					
					<%-- Messaggio di WARNING --%>
					<div class="alert alert-warning hide">
						<button type="button" class="close">&times;</button>
						<strong>Attenzione!!</strong><br>
						<ul>
							<s:iterator value="messaggi">
								<li><s:property value="codice"/> - <s:property value="descrizione"/> </li>
							</s:iterator>
						</ul>
					</div>

					<%-- Messaggio di ERROR --%>
					<div class="alert alert-error hide">
						<button type="button" class="close close-error">&times;</button>
						<strong>Attenzione!!</strong><br>
						<ul>
							<s:iterator value="errori">
								<li><s:property value="testo"/> </li>
							</s:iterator>
						</ul>
					</div>

					<h3 style="margin-bottom: 1%">Backoffice Modifica Piano Dei Conti</h3>
					<fieldset id="fieldset_backofficeOrdinativoModificaPianoDeiConti" class="form-horizontal imputazioniContabiliMovimentoGestione">
					<%-- STRUTTURA ALBERO --%>
					<s:hidden id="codiceClassificatorePdc" name="pianoDeiConti.tipoClassificatore.codice"/>
					<s:hidden id="codicePianoDeiContiCapitolo" name="pianoDeiConti.codice"/>
					<s:hidden id="descrizionePianoDeiContiCapitolo" name="pianoDeiConti.descrizione"/>
					<%-- STRUTTURA ALBERO --%>
						<div class="control-group inline">
							<div class="row-fluid">
								<label for="annoOrindativoDaModificare" class="control-label">Anno</label>
								<div class="controls">
									<s:textfield id="annoOrindativoDaModificare" cssClass="lbTextSmall span1" name="criteri.anno" readonly="true" />

									<span class="inline spacing-lr">
										<label for="numeroOrindativoDaModificare" class="alRight">Numero *</label>
									</span>
									<s:textfield id="numeroOrindativoDaModificare" cssClass="lbTextSmall soloNumeri span2" maxlength="7" name="criteri.numeroDa" placeholder="numero ordinativo"/>
									<%-- <s:hidden id="numeroA" name="criteri.numeroA" /> --%>
									<span class="inline spacing-lr">
										<label for="codiceTipo" class="alRight">Tipo *</label>
									</span>
									<s:select cssClass="span3"
									 	id="codiceTipo"
									    name="criteri.codiceTipo"
									    headerKey=""
									    headerValue=""
										list="#{'I':'INCASSO','P':'PAGAMENTO'}" />

									<span class="radio guidata pull-right">
										<button type="button" class="btn btn-info" id="pulsanteCercaOrdinativo">cerca
											<i class="icon-spin icon-refresh spinner" id="spinnerCercaOrdinativo"></i>
										</button>
										<%-- <button type="button" class="btn btn-primary"
											id="pulsanteCompilazioneGuidataOrdinativo">compilazione guidata</button> --%>
									</span>
								</div>
							</div>
						</div>
						<div class="control-group">
							<div class="row-fluid">
								<label for="numeroRemedyDaAssociare" class="control-label radio-inline">Login operazione 
									<a class="tooltip-test" data-original-title="Inserire CF operatore o il numero Remedy">
										<i class="icon-info-sign">&nbsp;
											<span class="nascosto">Inserire CF operatore o il numero Remedy</span>
										</i>
									</a>
								</label>
								<div class="controls">
									<s:textfield id="numeroRemedyDaAssociare"
										name="numeroRemedy" cssClass="lbTextSmall span8"
										/>
								</div>
							</div>
						</div>


						<%-- INIZIO CAPPELLO HIDDEN --%>
						<div id="riepilogoOrdinativoCaricato" class="hide">
							<input type="hidden" id="HIDDEN_uidCapitolo" />
							<div class="Border_line"></div>
							<div id="ordinativoCaricato" class="control-group">
								<div id="accordionMainOrdinativoCaricato" class="accordion" data-overlay>
									<div class="accordion-group">
										<div class="accordion-heading">
											<a href="#collapseOrdinativoCaricato" id="accordionCapitoloSelezionato"
												data-parent="#divOrdinativoCaricato" data-toggle="collapse"
												class="accordion-toggle collapsed"> Dettaglio Ordinativo <span class="icon">&nbsp;</span>
											</a>
										</div>
										<div class="accordion-body collapse"
											id="collapseOrdinativoCaricato">
											<div class="accordion-inner">
												<table class="table table-hover tab_left"
													id="tabellaModaleOrdinativoCaricato">
													<thead>
														<tr>
															<th scope="col">Ordinativo</th>
															<th scope="col">Descrizione</th>
															<th scope="col">Capitolo</th>
															<th scope="col">Stato</th>
															<th scope="col">Tipo</th>
															<th scope="col">Data emissione</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td scope="col" id="tabellaModaleOrdinativoCaricato_ordinativo"></td>
															<td scope="col" id="tabellaModaleOrdinativoCaricato_descrizione"></td>
															<td scope="col" id="tabellaModaleOrdinativoCaricato_capitoloAssociato"></td>
															<td scope="col" id="tabellaModaleOrdinativoCaricato_stato"></td>
															<td scope="col" id="tabellaModaleOrdinativoCaricato_tipo"></td>
															<td scope="col" id="tabellaModaleOrdinativoCaricato_dataEmissione"></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div id="pdcCaricarto" class="control-group " data-info-impegno>
								<div id="parte_spesa" class="hide">
									<div class="row-fluid">
										<div class="control-group">
											<label for="selectTitoloSpesa"  class="alRight control-label inline">Titolo *</label>
											<div class="controls">
												<select class="span6" id="selectTitoloSpesa" name="idTitoloCapitolo"></select>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="control-group">
											<label for="selectMacroAggregato"  class="alRight control-label inline macroAggregato">Macroaggregato *
												<a class="tooltip-test" data-original-title="Selezionare prima il Titolo">
													<i class="icon-info-sign">&nbsp;
														<span class="nascosto">Selezionare prima il Titolo</span>
													</i>
												</a>
											</label>
											<div class="controls">
												<select class="span6" id="selectMacroAggregato" name="idMacroAggregato"></select>
											</div>
										</div>
									</div>
								</div>
								<div id="parte_entrata" class="hide">
									<div class="row-fluid">
										<div class="control-group">
											<label for="selectTitoloEntrata"  class="alRight control-label inline">Titolo *</label>
											<div class="controls">
												<select class="span6" id="selectTitoloEntrata" name="idTitoloCapitolo"></select>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="control-group">
											<label for="selectTipologia" class="alRight control-label inline">Tipologia *
												<a class="tooltip-test" data-original-title="Selezionare prima il Titolo">
													<i class="icon-info-sign">&nbsp;
														<span class="nascosto">Selezionare prima il Titolo</span>
													</i>
												</a>
											</label>
											<div class="controls">
												<select class="span6" id="selectTipologia" name="idTipologiaCapitolo"></select>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="control-group">
											<label for="selectCategoria" class="alRight control-label inline categoria">Categoria *
												<a class="tooltip-test" data-original-title="Selezionare prima la Tipologia">
													<i class="icon-info-sign">&nbsp;
														<span class="nascosto">Selezionare prima la Tipologia</span>
													</i>
												</a>
											</label>
											<div class="controls">
												<select class="span6" id="selectCategoria" name="idMacroAggregato"></select>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="control-group">
										<label for="pdc" class="control-label inline">
											<abbr title="Piano dei Conti">P.d.C.</abbr> finanziario *
												<a class="tooltip-test" data-original-title="Selezionare prima la Categoria o il Macroaggregato">
													<i class="icon-info-sign">&nbsp;
														<span class="nascosto">Selezionare prima la Categoria o il Macroaggregato</span>
													</i>
												</a>
										</label>
										<div class="controls">
											<span id="SPAN_pdcFin" style="padding: 0 2% 0 0;"></span>
											<a href="#myModal" id="pulsanteSelezionaPDC" role="button"class="btn btn-primary" data-toggle="modal">Seleziona il Piano dei Conti
												<i class="icon-spin icon-refresh spinner" id="spinnerElementoPdcModificaOrdinativo"></i>
											</a>

											<%-- ZTREE --%>
											<div id="myModal" class="modal hide fade" tabindex="-1"
												role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true" onclick="resetLastTreeNode()">x</button>
												<h3 id="myModalLabel">Piano dei Conti</h3>
											</div>

											<!-- ALBERO VISUALIZZATO -->
											<div class="modal-body" id="elementiPdcModificaOrdinativoDiv">
												<ul id="elementiPdcModificaOrdinativo" class="ztree"></ul>
											</div>

											<!-- ALBERO IN ATTESA -->
											<div class="modal-body" id="elementiModificaPdcOrdinativoWait">
												Attendere prego..
											</div>

											<div class="modal-footer">
												<button value="conferma" name="conferma"
													id="confermaPdc" class="btn btn-primary"
													aria-hidden="true">conferma</button>
											</div>
											<input type="hidden" id="HIDDEN_pdcSelezionato" name="pianoDeiConti.uid"/>
										</div>
									</div>
								</div>
							</div>

						</div>
						<%-- FINE CAPPELLO HIDDEN --%>
						<div id="parte_entrata_modifiche" class="hide">
							<div class="control-group">
								<div class="row-fluid">
									<span class="control-label">Modifica su</span>
									<div class="controls">
										<input type="checkbox" id="modificaAccertamentoCheckbox" name="modificaAccertamento" value="false" />
										<label for="modificaAccertamento" class="alRight inline spacing-lr">Accertamento</label>
									</div>
								</div>
							</div>
						</div>
						<%-- BLOCCO GENERALE E GSA --%>
						<div class="control-group">
							<div class="row-fluid">
								<span class="control-label">Aggiorna generale</span>
								<div class="controls">
									<s:radio theme="simple" label="Aggiorna generale" id="aggiornaGenerale" name="aggiornaGenerale" cssClass="flagResidenza radio inline" list="#{true:'Si',false:'No'}" value="false"></s:radio>
								</div>
							</div>
						</div>
						<div id="inserisciGeneraleHidden" class="control-group hide" >
							<div class="row-fluid">
								<span class="control-label">Inserisci generale</span>
								<div class="controls">
									<div class="span1" class="inline f-left">
										<s:radio theme="simple" id="inserisciGenerale" name="inserisciGenerale" cssClass="flagResidenza radio inline" list="#{true:'Si',false:'No'}" value="false"></s:radio>
									</div>
								</div>
							</div>
						</div>
						<div class="control-group">
							<div class="row-fluid">
								<span class="control-label">Aggiorna generale GSA</span>
								<div class="controls">
									<s:radio theme="simple" label="Aggiorna generale GSA" id="aggiornaGSAGenerale" name="aggiornaGSAGenerale" cssClass="flagResidenza radio inline" list="#{true:'Si',false:'No'}" value="false"></s:radio>
								</div>
							</div>
						</div>
						<div id="inserisciGSAGeneraleHidden" class="control-group hide" >
							<div class="row-fluid">
								<span class="control-label">
									<label for="inserisciGSAGenerale" class="alRight">Inserisci generale GSA</label>
								</span>
								<div class="controls">
									<div class="span1 inline f-left">
										<s:radio theme="simple" id="inserisciGSAGenerale" name="inserisciGSAGenerale" cssClass="flagResidenza radio inline" list="#{true:'Si',false:'No'}" value="false"></s:radio>
									</div>
									<%-- <div id="handlerGSAGenerale" class="inline span8 hide f-left" >
										<label for="selectTipoEventoGSAGenerale" class="alRight inline">Tipo evento GSA *</label>
										TIPO EVENTO
										<select id="selectTipoEventoGSAGenerale" name="idTipoEventoGSAGenerale" disabled ></select>
										<s:hidden id="HIDDEN_selectTipoEventoGSAGenerale" name="idTipoEventoGSAGenerale" />
										<s:hidden id="HIDDEN_idCollegamentoTipoEventoGSA" name="idCollegamentoTipoEventoGSA" />
										EVENTO
										<label for="selectEventoGSAGenerale" class="alRight inline">Evento GSA *</label>
										<select id="selectEventoGSAGenerale" name="idEventoGSA" class="span6"></select>
									</div> --%>
								</div>
							</div>
						</div>
						<%-- BLOCCO GENERALE E GSA --%>
						
						<div id="handlerGenerale" class="control-group hide" >
							<div class="row-fluid">
								<span class="control-label">
									<%-- TIPO EVENTO --%>
									<label for="selectTipoEvento" class="alRight inline">Tipo evento *</label>
								</span>
								<div class="controls">
									<select id="selectTipoEvento" name="tipoEventoCodice" disabled ></select>
									<s:hidden id="HIDDEN_selectTipoEvento" name="tipoEventoCodice" />
									<%-- <s:hidden id="HIDDEN_collegamentoTipoEvento" name="collegamentoTipoEventoCodice" /> --%>
									<%-- <s:hidden id="HIDDEN_selectEvento" name="eventoCodice" /> --%>
									<%-- EVENTO --%>
									<label for="selectEvento" class="alRight inline spacing-lr">Evento *</label>
									<select id="selectEvento" name="eventoCodice" class="span6"></select>
								</div>
							</div>
						</div>



					</fieldset>
	          		<p class="margin-medium">
						<a class="btn btn-secondary" href="<s:property value="#urlCruscotto" />">indietro</a>
						<a class="btn btn-secondary" href="modificaPianoDeiContiOrdinativo.do">annulla</a>
						<span class="pull-right">
							<s:submit id="confermaModifica" cssClass="btn btn-primary hide" value="conferma" />
						</span>
				  	</p>
				</s:form>
			</div>
		</div>
	</div>

	<s:include value="/jsp/include/javascript.jsp" />

	<%-- <script type="text/javascript" src="${jspath}ordinativi/gestioneOrdinativi.js"></script> --%>
	<%-- <script type="text/javascript" src="${jspath}common/pianoDeiConti.js"></script> --%>
	<%-- <script type="text/javascript" src="${jspath}ordinativi/backofficeModificaPianoDeiContiOrdinativo/pianoDeiContiModificaOrdinativo.js"></script> --%>
	<script type="text/javascript" src="${jspath}ordinativi/backofficeModificaPianoDeiContiOrdinativo/backofficeModificaPianoDeiContiOrdinativo.js"></script>

	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />