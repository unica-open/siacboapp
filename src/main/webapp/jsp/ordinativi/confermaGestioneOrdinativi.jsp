<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ taglib uri="http://www.csi.it/taglibs/remincl-1.0" prefix="r"%> 


<%-- Inclusione head e CSS --%>
<s:include value="/jsp/include/head.jsp" />

<s:include value="/jsp/include/javascript.jsp" />


</head>
<body>
	<%-- Inclusione header --%>
	<s:include value="/jsp/include/header.jsp" />
	

	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="<s:property value="#urlCruscotto" />">Home</a>
					<span class="divider">&gt;</span></li>
				<li><a href="gestioneOrdinativi.do">Gestione ordinativi</a>
					<span class="divider">&gt;</span></li>
		        <li class="active"><s:property value="operazione" /></li>
			</ul>
		</div>
	</div>

 


<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">    
    
<s:form action="%{#context['struts.actionMapping'].name}" method="post" id="form" data-skip-confirm="false">

<s:hidden name="codiceTipoOrdinativi" id="codiceTipoOrdinativi" />

					<s:if test="hasActionMessages()">
						<%-- Messaggio di WARNING --%>
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<ul>
								<s:actionmessage />
							</ul>
						</div>
					</s:if>


					<s:if test="hasActionErrors()">
						<%-- Messaggio di ERROR --%>
						<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Attenzione!!</strong><br>
					<ul>
						<s:actionerror />
					</ul>
			</div>
			</s:if>


		
		<h3><s:property value="capitalizedOperazione" /></h3>   
		        
		
							<div class="contentLoading">Attendere, prego...</div>
		
		<div id="content" class="nascosto"> 
		
	<s:if test="not elencoOrdinativi.empty">	
	        
		<fieldset class="form-horizontal" id="fieldsetConfermaGestioneOrdinativi">

		<h4><span class="num_result"><s:property value="elencoOrdinativi.size()"/></span> ordinativi</h4>                 
		
		<div id="simple-pagination">
		
			<table class="table table-hover tab_left"  summary="...." >
				<thead>
					<tr>
						<th class="span1"><input type="checkbox" id="ord-sel-all" /></th>
						<th class="span2">Tipo</th>
						<th>Numero</th>
						<th>Descrizione</th>
						<th>Stato</th>
						<th class="span2">Da trasmettere</th>	
						<th class="span2">Data emissione</th>	
						<th class="span2">Data trasmissione</th>	
						<th class="span2">Data annullamento</th>	
						<th class="span2">Codice flusso</th>	
					</tr>
				</thead>
				<tbody>
				
				
				<s:iterator value="elencoOrdinativi" status="st" var="ord">

	
	<tr>
		<td class="ord-sel" data-importo="<s:property value="#ord.importo" />"> 
			<input type="checkbox"  value="<s:property value="#ord.uid" />"  class="idOrdinativi">
		</td>
		<td class="ord-tipo"><s:property value="#ord.tipo.descrizione" /></td>
		<td class="ord-codice"><s:property value="#ord.numero" /></td>
		<td><s:property value="#ord.descrizione" /></td>
		<td class="ord-stato"><s:property value="#ord.stato.descrizione" /></td>
		<td class="ord-stato"><s:if test="#ord.daTrasmettere" >S&igrave;</s:if><s:if test="not #ord.daTrasmettere">No</s:if></td>
		<td class="ord-dataUpload"><s:date name="#ord.dataEmissione" format="dd/MM/yyyy" /></td>
		<td class="ord-dataUpload"><s:date name="#ord.dataTrasmissioneOil" format="dd/MM/yyyy" /></td>
		<td class="ord-dataUpload"><s:date name="#ord.dataAnnullamento" format="dd/MM/yyyy" /></td>
		<td class="ord-dataUpload"><s:property value="#ord.codiceFlusso" /></td>
		
		
	</tr>
	
					
				</s:iterator>	
				

				</tbody>
				<tfoot>
				</tfoot>

			</table>
			
			

		
	<div class="Border_line margin-medium"></div>

	<div class="simple-pagination-navigation">
			<div class="simple-pagination-first"></div>
			<div class="simple-pagination-previous"></div>
			<div class="simple-pagination-page-numbers"></div>
			<div class="simple-pagination-next"></div>
			<div class="simple-pagination-last"></div>
	</div>
	
			

	</div>



		</fieldset>
	</s:if>
	<s:else>
		Nessun ordinativo presente secondo i criteri di ricerca scelti
	</s:else>
		         <p class="margin-medium">
			  <a class="btn btn-secondary" href="gestioneOrdinativi.do">indietro</a>   
			  
			  	<s:if test="not elencoOrdinativi.empty">	        
				  
				  	<s:if test="mostraPulsanteApriModaleProvvisorioCassa">	        
						<s:submit cssClass="nascosto btn btn-primary pull-right" action="%{getConfermaActionName()}" id="conferma"  value="conferma" />
						
						<span class="radio guidata">
							<a href="#" id="pulsanteApriModaleProvvisorioCassa" class="btn btn-primary disabled">ricerca provvisori di cassa</a>
						</span>
					</s:if>
					<s:else>
						  <s:submit cssClass="btn btn-primary pull-right" action="%{getConfermaActionName()}" id="conferma"  value="conferma" />
					</s:else>
				</s:if>
		  </p> 

	 </div>        

          			<s:include value="/jsp/include/modaleAssociaProvvisoriCassa.jsp" />
          
      </s:form>
    </div>
  </div>	 
</div>	

	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	
	
<r:include url="/ris/servizi/siac/include/myCheckbox.html" resourceProvider="rp"/>

<s:include value="/jsp/include/jquery.simple.pagination.jsp" />


<script type="text/javascript" src="${jspath}common/provvisorioCassa.js" charset="utf-8"></script>
<script type="text/javascript" src="${jspath}ordinativi/confermaGestioneOrdinativi.js" charset="utf-8"></script>
	
</body>
</html>