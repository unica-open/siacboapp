<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!--modale soggetto -->
<div id="modaleGuidaSoggetto" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="guidaSogLabel" aria-hidden="true">
	<div class="row-fluid">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="nostep-pane">Seleziona soggetto</h4>
		</div>
		<div class="modal-body">
		
			<div class="alert alert-error hide" id="ERRORI_MODALE_SOGGETTO">
				<button type="button" class="close" data-hide="alert">&times;</button>
				<strong>Attenzione!!</strong><br>
				<ul>
				</ul>
			</div>

			<fieldset class="form-horizontal" id="fieldsetRicercaGuidateSoggetto">
				<div id="campiRicercaSog" class="accordion-body collapse in">
					<div class="control-group">
						<label class="control-label" for="codiceSoggetto_modale">Codice</label>
						<div class="controls">
							<s:textfield id="codiceSoggetto_modale" cssClass="span3" name="criteriSoggetti.codice" />
							<label class="radio inline" for="codiceFiscaleSoggetto_modale">Codice Fiscale</label>
							<s:textfield id="codiceFiscaleSoggetto_modale" cssClass="span4 uppercase" name="criteriSoggetti.codiceFiscale" maxlength="16" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="partitaIvaSoggetto_modale">Partita IVA</label>
						<div class="controls">
							<s:textfield id="partitaIvaSoggetto_modale" cssClass="span3" name="criteriSoggetti.partitaIva" />
							<label class="radio inline" for="denominazioneSoggetto_modale">Denominazione</label>
							<s:textfield id="denominazioneSoggetto_modale" cssClass="span4" name="criteriSoggetti.denominazione" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="classificatoreSoggetto_modale">Classificatore</label>
						<div class="controls">
							<s:select list="elencoClassiSoggetto" cssClass="span3" name="criteriSoggetti.idClasse" id="classificatoreSoggetto_modale" 
									headerKey="" headerValue="" listValue="%{codice + ' - ' + descrizione}" listKey="uid" />
						</div>
					</div>
				</div>
				<button type="button" id="pulsanteAnnullaRicercaSoggetto<s:property value='%{suffisso}' default='' />" class="btn btn-secondary">annulla</button>
				<a class="btn btn-primary pull-right" id="pulsanteCercaSoggetto">
					<i class="icon-search icon"></i>&nbsp;cerca&nbsp;<i class="icon-spin icon-refresh hide spinner" id="SPINNER_RicercaSoggetto"></i>
				</a>
			</fieldset>
			<div id="divTabellaSoggetti" class="hide">
				<h4>Elenco soggetti</h4>

				<table class="table table-hover" id="risultatiRicercaSoggetti" summary="....">
					<thead>
						<tr>
							<th scope="col"></th>
							<th scope="col">Codice</th>
							<th scope="col">Codice fiscale</th>
							<th class="header headerSortDown">Partita IVA</th>
							<th scope="col">Denominazione</th>
							<th scope="col">Stato</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>

		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" id="pulsanteConfermaSoggetto">conferma</button>
		</div>
	</div>
</div>
<!--/modale soggetto -->