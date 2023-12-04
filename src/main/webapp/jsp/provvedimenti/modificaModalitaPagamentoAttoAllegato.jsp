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


<style>
  .chosen-container {width: 35% !important;}
</style>

</head>
<body>
	<%-- Inclusione header --%>
	<s:include value="/jsp/include/header.jsp" />
	




	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="<s:property value="#urlCruscotto" />">Home</a>
					<span class="divider">&gt;</span></li>
		        <li class="active">Modifica modalit&agrave; pagamento atto allegato</li>
			</ul>
		</div>
	</div>

 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">       
     
        <s:form action="modificaModalitaPagamentoAttoAllegato" method="post" cssClass="form-horizontal" id="form">
        
				
<s:if test="hasActionMessages()">
						<%-- Messaggio di WARNING --%>
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Attenzione!!</strong><br>
							<ul>
								<s:iterator value="messaggi">
									<li><s:property value="codice"/> - <s:property value="descrizione"/> </li>
								</s:iterator>
							</ul>
						</div>
					</s:if>


			<s:if test="hasActionErrors()">
						<%-- Messaggio di ERROR --%>
						<div class="alert alert-error">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Attenzione!!</strong><br>
							<ul>
								<s:iterator value="errori">
									<li><s:property value="testo"/> </li>
								</s:iterator>
							</ul>
						</div>
			</s:if>
			


			<h3>Modifica modalit&agrave; pagamento atto allegato</h3>

			<div class="step-content">
			<div class="step-pane active" id="step1">
			
			<fieldset class="form-horizontal margin-large">


			<h4>Atto allegato *</h4> 
			<div id="datiProvvedimento" class="control-group">
				<s:hidden id="idAttoAmministrativo" name="attoAmministrativo.uid" />
				<label class="control-label" for="annoAttoAmministrativo">Anno</label>
				<div class="controls" >
					<s:textfield  id="annoAttoAmministrativo" cssClass="lbTextSmall span1 soloNumeri apriModaleProvvedimento" name="attoAmministrativo.anno" readonly="true" />
					<span class="al">
						<label class="radio inline" for="numeroAttoAmministrativo">Numero</label>
					</span>
					<s:textfield  id="numeroAttoAmministrativo" cssClass="lbTextSmall span2 soloNumeri apriModaleProvvedimento" name="attoAmministrativo.numero" maxlength="7" readonly="true" />
				 <span class="al">
						<label class="radio inline" for="tipoAtto">Struttura</label>
					</span>
				<s:hidden id="idSac" name="attoAmministrativo.sac.uid" />
	        	<s:select disabled="true"
		        	headerKey="0" headerValue="" cssClass="span5 apriModaleProvvedimento" id="strutturaAmministrativoContabile"
		        	listKey="uid" listValue="%{codice + ' - ' + descrizione}" 
		        	list="elencoStruttureAmministrativoContabiliNoTree"
					/> 
 		        	<%-- name="xxx_attoAmministrativo.sac.uid"    --%>
				 	
					<span class="radio guidata">
						<a href="#" id="pulsanteApriModaleProvvedimento" class="btn btn-primary">compilazione guidata</a>
					</span>
				</div>
			</div>
			

			<h4>Soggetto *</h4> 
			<div id="datiSoggetto" class="control-group">
				<s:hidden id="idSoggetto" name="soggetto.uid" />
				<label class="control-label" for="codiceSoggetto">Codice</label>
				<div class="controls" >
					<s:textfield id="codiceSoggetto" cssClass="lbTextSmall span2 soloNumeri" name="soggetto.codice" />

					<span class="radio guidata">
						<a href="#" id="pulsanteApriModaleSoggetto" class="btn btn-primary">compilazione guidata</a>
					</span>
				</div>
			</div>

			</fieldset>  

		</div>
		</div>


					<p class="margin-medium">

			 <a class="btn btn-secondary" href="<s:property value="#urlCruscotto" />">indietro</a> 
			   
			<!-- SIAC-8699 -->
			<s:submit cssClass="btn btn-primary pull-right" action="modificaModalitaPagamentoAttoAllegato_cerca" id="cerca" value="cerca"  />

		  </p>

<s:if test="not quote.empty">

<br/><br/><div class="step-content"><div class="step-pane"><h4></h4></div></div>

		<h3>Quote</h3>

	
		<table class="table table-hover tab_left" summary="....">
				<thead>
					<tr>
						<th>Numero</th>
						<th>Descrizione</th>
						<th class="span2">Importo</th>	
					</tr>
				</thead>
				<tbody>
		
		<s:iterator value="quote" var="q">
			<tr class="filtered visible" style="display: table-row;">
				<td><s:property value="#q.numero"/></td>
				<td><s:property value="#q.descrizione"/></td>
				<td><s:property value="#q.importo"/></td>
			</tr>
		</s:iterator>

				</tbody>
				<tfoot>
				</tfoot>

			</table>


<br/>




	<div class="control-group">
		<label class="control-label">Modalit&agrave; pagamento *</label>
		<div class="controls">
			<s:select id="idModalitaPagamentoSoggetto" cssClass="span10 chosen-select" size="3" listKey="uid" listValue="%{codice + ' - ' + descrizione}"
				name="idModalitaPagamentoSoggetto" headerKey="" headerValue=""
				list="elencoModalitaPagamentoSoggetto"  />
		</div>
	</div>


		<label for="numeroRemedyDaAssociare"
			class="control-label radio-inline">Login operazione<a
			class="tooltip-test"
			data-original-title="Inserire CF operatore o il numero Remedy">
				<i class="icon-info-sign">&nbsp; <span class="nascosto">Inserire
						CF operatore o il numero Remedy</span>
			</i>
		</a>
		</label>
		<div class="controls">
			<s:textfield id="codiceInc" name="codiceInc"  maxlength="170"
				cssClass="lbTextSmall span5" />
		</div>



						<p class="margin-medium">

			<s:hidden name="impegno.uid" />
			<!-- SIAC-8699 -->
			<s:submit cssClass="btn btn-primary pull-right" action="modificaModalitaPagamentoAttoAllegato_aggiorna" id="aggiorna" disabled="true" value="aggiorna" />

		  </p>

</s:if>
					          
			<s:include value="/jsp/include/modaleProvvedimento.jsp" />
			<s:include value="/jsp/include/modaleSoggetto.jsp" />
          
          
		  </s:form>     	
    </div>	
  </div>	 
</div>	



	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	
	

<script src="${jspath}common/soggetto.js"></script>
<script src="${jspath}common/provvedimento.js"></script>
<script src="${jspath}ztree/ztreeSAC.js"></script>	
<script src="${jspath}strutturaAmministrativaContabile/strutturaAmministrativaContabile.js"></script>
<script src="${jspath}provvedimenti/modificaModalitaPagamentoAttoAllegato.js"></script>
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>