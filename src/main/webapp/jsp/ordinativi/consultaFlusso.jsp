<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ taglib uri="http://www.csi.it/taglibs/remincl-1.0" prefix="r"%> 




		
	
		        
	<s:if test="elencoOrdinativi != null">	        
		<fieldset class="form-horizontal">

		<h4><span class="num_result"><s:property value="elencoOrdinativi.size()"/></span> ordinativi</h4>                 
		
<!-- 	<div id="simple-pagination-proposte"> -->
		
			<table class="table table-hover tab_left"  summary="...." >
				<thead>
					<tr>
						<th class="span2">Tipo</th>
						<th>Numero</th>
						<th>Descrizione</th>
						<th>Stato</th>
						<th class="span2">Data emissione</th>	
						<th class="span2">Data trasmissione</th>	
						<th class="span2">Codice distinta</th>	
						<th class="span2">Codice distinta attuale</th>	
					</tr>
				</thead>
				<tbody>
				
				
				<s:iterator value="elencoOrdinativi" status="st" var="ord">

	
				<tr>
					<td class="ord-tipo"><s:property value="#ord.tipo.descrizione" /></td>
					<td class="ord-codice"><s:property value="#ord.numero" /></td>
					<td><s:property value="#ord.descrizione" /></td>
					<td class="ord-stato"><s:property value="#ord.stato.descrizione" /></td>
					<td class="ord-dataUpload"><s:date name="#ord.dataEmissione" format="dd/MM/yyyy" /></td>
					<td class="ord-dataUpload"><s:date name="#ord.dataTrasmissioneOil" format="dd/MM/yyyy" /></td>
					<td class="ord-dataUpload codiceFlussoConsulta">***</td>
					<td class="ord-dataUpload"><s:property value="#ord.codiceFlusso" /></td>
					

				</tr>
	
					
				</s:iterator>	
				

				</tbody>
				<tfoot>
				</tfoot>

			</table>
			
			

		
	<div class="Border_line margin-medium"></div>

<!-- 	<div class="simple-pagination-proposte-navigation"> -->
<!-- 		<div class="simple-pagination-proposte-first"></div> -->
<!-- 		<div class="simple-pagination-proposte-previous"></div> -->
<!-- 		<div class="simple-pagination-proposte-page-numbers"></div> -->
<!-- 		<div class="simple-pagination-proposte-next"></div> -->
<!-- 		<div class="simple-pagination-proposte-last"></div> -->
<!-- </div> -->
<!-- 	</div> -->
	
			





		</fieldset>
	</s:if>
	<s:else>
		Nessun ordinativo presente secondo i criteri di ricerca scelti
	</s:else>

     