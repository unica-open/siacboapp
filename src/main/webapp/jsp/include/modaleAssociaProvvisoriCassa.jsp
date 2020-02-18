<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div aria-hidden="true" aria-labelledby="cercaProvvisorioDiCassaLabel" role="dialog" tabindex="-1" class="modal hide fade" id="modaleRicercaProvvisorioDiCassa">
	<div class="modal-header" id="cercaProvvisorioDiCassaLabel">
		<button aria-hidden="true" data-dismiss="modal" class="close" type="button">&times;</button>
		<h4 class="nostep-pane">Seleziona provvisorio</h4>
	</div>
	<div class="modal-body">
		<div class="alert alert-error hide" id="ERRORI_modale_ProvvisorioDiCassa">
			<button type="button" class="close" data-hide="alert">&times;</button>
			<strong>Attenzione!!</strong><br>
			<ul>
			</ul>
		</div>
		<fieldset class="form-horizontal" id="fieldsetRicercaProvvCassa">
			<div class="accordion-body collapse in" id="campiProvvisorio">
				<input type="hidden" id="modale_hidden_tipoProvvisorioDiCassa" name="criteriProvvisoriCassa.codiceTipo" data-maintain />
				<div class="control-group">
					<label class="control-label">Anno</label>
					<div class="controls">
						<span class="al">
							<label class="radio inline" for="modale_annoDa">Da</label>
						</span>
						<input type="text" name="criteriProvvisoriCassa.annoDa" class="span2 soloNumeri" id="modale_annoDa" maxlength="4" />
						<span class="al">
							<label class="radio inline" for="modale_annoA">A</label>
						</span>
						<input type="text" name="criteriProvvisoriCassa.annoA" class="span2 soloNumeri" id="modale_annoA" maxlength="4" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Numero</label>
					<div class="controls">
						<span class="al">
							<label class="radio inline" for="modale_numeroDa">Da</label>
						</span>
						<input type="text" name="criteriProvvisoriCassa.numeroDa" class="span2 soloNumeri" id="modale_numeroDa" maxlength="7" />
						<span class="al">
							<label class="radio inline" for="modale_numeroA">A</label>
						</span>
						<input type="text" name="criteriProvvisoriCassa.numeroA" class="span2 soloNumeri" id="modale_numeroA" maxlength="7" />
					</div>
				</div>


				<div class="control-group">
					<label class="control-label">Descrizione causale</label>
					<div class="controls">
						<input type="text" name="criteriProvvisoriCassa.descrizioneCausale" class="span9" id="modale_descrizioneCausale" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Sotto causale</label>
					<div class="controls">
						<input type="text" name="criteriProvvisoriCassa.descrizioneSottoCausale" class="span9" id="modale_descrizioneSottoCausale" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Descrizione soggetto</label>
					<div class="controls">
						<input type="text" name="criteriProvvisoriCassa.descrizioneSoggetto" class="span9" id="modale_descrizioneSoggetto" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Data emissione</label>
					<div class="controls">
						<span class="al">
							<label class="radio inline" for="modale_dataEmissioneDa">Da</label>
						</span>
						<input type="text" name="criteriProvvisoriCassa.dataEmissioneDa" class="span2 datepicker" id="modale_dataEmissioneDa" maxlength="10" />
						<span class="al">
							<label class="radio inline" for="modale_dataEmissioneA">A</label>
						</span>
						<input type="text" name="criteriProvvisoriCassa.dataEmissioneA" class="span2 datepicker" id="modale_dataEmissioneA" maxlength="10" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Importo</label>
					<div class="controls">
						<span class="al">
							<label class="radio inline" for="modale_importoDa">Da</label>
						</span>
						<input type="text" name="criteriProvvisoriCassa.importoDa" class="span2 soloNumeri decimale" id="modale_importoDa" />
						<span class="al">
							<label class="radio inline" for="modale_importoA">A</label>
						</span>
						<input type="text" name="criteriProvvisoriCassa.importoA" class="span2 soloNumeri decimale" id="modale_importoA" />
					</div>
				</div>
			</div>
			<button type="button" id="modale_pulsanteRicercaProvvisorioCassa" class="btn btn-primary pull-right">
				<i class="icon-search icon"></i>&nbsp;cerca<i class="icon-spin icon-refresh hide spinner" id="SPINNER_RicercaProvvCassa"></i>
			</button>
			<div id="modale_divElencoProvvisorioCassa" class="hide">
				<h4>Elenco provvisori</h4>
				<table class="table tab_left table-hover" id="modale_tabellaProvvisorioCassa">
					<thead>
						<tr>
							<th scope="col"><input type="checkbox" id="provv-sel-all" /></th>
							<th scope="col">Anno</th>
							<th scope="col">Numero</th>
							<th scope="col">Conto evidenza</th>
							<th scope="col">Data</th>
							<th scope="col">Causale</th>
							<th scope="col">Soggetto creditore</th>
							<th scope="col" class="tab_Right">Importo</th>
							<th scope="col" class="tab_Right">Importo da regolarizzare</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</fieldset>
		
		
		<div>
            <div>Numero provvisori selezionati: 
            	<span id="numeroProvvisoriSelezionati">0</span>
            </div>
            
			<div>Somma degli importi degli ordinativi selezionati: 
				<span id="sommaImportiOrdinativiSelezionati">0,00</span>
			</div>
			
			<div>Somma degli importi da regolarizzare dei provvisori selezionati:
				<span id="sommaImportiRegProvvisoriSelezionati">0,00</span>
			</div>
       </div>
		
	</div>
	<div class="modal-footer">
		<button type="button" aria-hidden="true" data-dismiss="modal" class="btn btn-secondary">annulla</button>
		<button disabled type="button" class="btn btn-primary" id="modale_pulsanteConfermaProvvisorioCassa">associa provvisori</button>
	</div>
</div>