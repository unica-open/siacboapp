<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<s:set var="annoIsNotRequired"><c:out value="${param.annoIsNotRequired}" default="false"/></s:set>

<!--modale provvedimento -->
<div id="modaleGuidaProvvedimento" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="guidaProvLabel" aria-hidden="true">

	<div class="row-fluid">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="nostep-pane">Seleziona provvedimento</h4>

		    
		
		<s:if test="%{#annoIsNotRequired}">
		</s:if>
		<s:else>
			<p>&Egrave; necessario inserire oltre all'anno almeno il numero atto oppure il tipo atto</p>
		</s:else>
			
		
		</div>

		<div class="modal-body">
			<div class="alert alert-error hide" id="ERRORI_MODALE_PROVVEDIMENTO">
				<button type="button" class="close" data-hide="alert">&times;</button>
				<strong>Attenzione!!</strong><br>
				<ul></ul>
			</div>
			<fieldset class="form-horizontal" id="fieldsetRicercaGuidateProvvedimento">
				<div id="campiRicercaProv" class="accordion-body collapse in">
					<div class="control-group">
					
						<s:if test="%{#annoIsNotRequired}">
							<label class="control-label" for="annoProvvedimento_modale">Anno  </label>
						</s:if>
						<s:else>
							<label class="control-label" for="annoProvvedimento_modale">Anno *</label>
						</s:else>
											
						
						<div class="controls">
							<s:textfield id="%{'annoProvvedimento_modale'}" cssClass="span2 soloNumeri" name="criteriProvvedimenti.anno" maxlength="4" />
							<span class="al">
								<label class="radio inline" for="numeroProvvedimento_modale">Numero</label>
							</span>
							<s:textfield id="%{'numeroProvvedimento_modale'}" cssClass="span2 soloNumeri" name="criteriProvvedimenti.numero" maxlength="6" />
							<span class="al">
								<label class="radio inline" for="tipoAttoProvvedimento_modale">Tipo</label>
							</span>
							<s:select list="elencoTipiAttoAmministrativo" listKey="uid" listValue="descrizione" name="criteriProvvedimenti.idTipo" id="%{'tipoAttoProvvedimento_modale'}" cssClass="lbTextSmall span3"
								headerKey="" headerValue="" />
							
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Struttura Amministrativa</label>
						<div class="controls">
							<div class="accordion span9 struttAmm">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a href="#struttAmm_modale" data-toggle="collapse" class="accordion-toggle collapsed">
											<span id="SPAN_StrutturaAmministrativoContabile_modale">Seleziona la Struttura amministrativa</span>
										</a>
									</div>
									<div id="struttAmm_modale" class="accordion-body collapse">
										<div class="accordion-inner">
											<ul id="treeStruttAmm_modale" class="ztree treeStruttAmm"></ul>
											<s:if test="zTreeMultipli()">
												<button type="button" id="confermaStrutturaAmministrativoContabile_modale" 
													class="btn btn-primary pull-right" data-toggle="collapse" data-target="#struttAmm_modale">Conferma</button>
												<button type="button" class="btn btn-secondary pull-right"
														id="deselezionaStrutturaAmministrativoContabile_modale">
													Deseleziona
												</button>
											</s:if>
										</div>
									</div>
								</div>
							</div>

							<s:hidden id="HIDDEN_StrutturaAmministrativoContabileUid_modale" name="criteriProvvedimenti.idStrutturaAmministrativoContabile" />
<%-- 							<s:hidden id="%{'HIDDEN_StrutturaAmministrativoContabileCodice_modale'}" name="criteriProvvedimenti.strutturaAmministrativoContabile.codice" /> --%>
<%-- 							<s:hidden id="%{'HIDDEN_StrutturaAmministrativoContabileDescrizione_modale'}" name="criteriProvvedimenti.strutturaAmministrativoContabile.descrizione" /> --%>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="oggettoProvvedimento_modale">Oggetto</label>
						<div class="controls">
							<s:textfield id="%{'oggettoProvvedimento_modale'}" cssClass="lbTextSmall span9" name="criteriProvvedimenti.oggetto" maxlength="500" />
						</div>
					</div>
				</div>
				<button type="button" id="pulsanteAnnullaRicercaProvvedimento" class="btn btn-secondary">annulla</button>
				<a class="btn btn-primary pull-right collapsed" href="#" id="pulsanteRicercaProvvedimento" data-toggle="collapse">
					<i class="icon-search icon"></i>&nbsp;cerca&nbsp;<i class="icon-spin icon-refresh spinner hide" id="SPINNER_Provvedimento" style="display: none;"></i>
				</a>

			</fieldset>
			<div id="divContenitoreTabellaProvvedimento" class="hide">
				<h4>Elenco provvedimenti trovati</h4>
				<table class="table table-hover" id="risultatiRicercaProvvedimento">
					<thead>
						<tr>
							<th></th>
							<th scope="col">Anno</th>
							<th scope="col">Numero</th>
							<th scope="col">Tipo</th>
							<th scope="col">Oggetto</th>
							<th scope="col"><abbr title="Struttura Amministrativa Responsabile">Str. Amm. Resp.</abbr></th>
							<th scope="col">Stato</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
					</tfoot>
				</table>
				<a class="btn" href="#" id="pulsanteDeselezionaProvvedimento">deseleziona</a>
			</div>
		</div>
		<div class="modal-footer">
			<a class="btn btn-primary" aria-hidden="true" id="pulsanteConfermaProvvedimento" href="#">conferma</a>
		</div>
	</div>
</div>
<!--/modale provvedimento -->