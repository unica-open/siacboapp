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
		        <li class="active">Annulla attivazioni contabili</li>
			</ul>
		</div>
	</div>

 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">       
     
        <s:form action="annullaAttivazioniContabili" method="post" cssClass="form-horizontal" id="form">
        
				
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
			


			<h3>Annulla attivazioni contabili</h3>

			<div class="step-content">
			<div class="step-pane active" id="step1">
			
			<h4>Dati</h4> 
			<fieldset class="form-horizontal margin-large">


			<div class="control-group">
				<div class="row-fluid">
					<span class="control-label">Tipologia</span>
					<div class="controls">
						<s:radio id="tipologia" theme="simple" name="criteri.codiceTipologia" cssClass="radio inline" list="#{'E':'Entrata', 'S':'Spesa'}"></s:radio>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="tipoFile">Tipo documento</label>
				<div class="controls">
					<select class="span4" id="codiceTipo" disabled="disabled"></select>
					<s:select cssClass="hide span4" id="codiceTipoEntrata" disabled="disabled"
						name="criteri.codiceTipo" headerKey="" headerValue=""
						list="elencoTipiDocumentoEntrata" listKey="codice"
						listValue="%{codice + ' - ' + descrizione}" />
					<s:select cssClass="hide span4" id="codiceTipoSpesa" disabled="disabled"
						name="criteri.codiceTipo" headerKey="" headerValue=""
						list="elencoTipiDocumentoSpesa" listKey="codice"
						listValue="%{codice + ' - ' + descrizione}" />
				</div>
			</div>

					
			<div class="control-group">
				<label class="control-label">Documento</label>
				<div class="controls">
					<s:textfield cssClass="lbTextSmall span1" id="anno"  name="criteri.anno" placeholder="anno" />
					<s:textfield cssClass="lbTextSmall span2" id="numero"  name="criteri.numero" placeholder="numero" />
		
					<span class="al">
							<label for="dataOrdSpostamentoA" class="radio inline">Data documento</label>
						</span>
						
						<s:date name="criteri.dataEmissione" var="dataEmissione" format="dd/MM/yyyy"/>
						
					<s:textfield cssClass="lbTextSmall span2 datepicker" id="data" value="%{dataEmissione}"  name="criteri.dataEmissione" />
				
				</div>
			</div>




		
				<h4 class="step-pane">Soggetto</h4>
				<div id="datiSoggetto" class="control-group">
					<s:hidden id="idSoggetto" name="criteri.soggetto.uid" />
					<label class="control-label" for="codiceSoggetto">Codice</label>
					<div class="controls" >
						<s:textfield id="codiceSoggetto"  cssClass="lbTextSmall span2 soloNumeri" name="criteri.soggetto.codice" />

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
			<s:submit cssClass="btn btn-primary pull-right" action="annullaAttivazioniContabili_cerca" id="cerca" value="cerca"  />

		  </p>

<s:if test="documento != null">

<br/><br/><div class="step-content"><div class="step-pane"><h4></h4></div></div>

		<h3>Documento</h3>

		<ul class="htmlelt step-content">
			<li>
				<dfn>Tipo</dfn>
				<dl><s:property value="documento.tipo.codice"/>&nbsp;-&nbsp;<s:property value="documento.tipo.descrizione"/>&nbsp;</dl>
			</li>
			<li>
				<dfn>Anno</dfn>
				<dl><s:property value="documento.anno"/>&nbsp;</dl>
			</li>
			<li>
				<dfn>Numero</dfn>
				<dl><s:property value="documento.numero"/>&nbsp;</dl>
			</li>
			<li>
				<dfn>Data emissione</dfn>
				<dl><s:date name="documento.dataEmissione" format="dd/MM/yyyy" />&nbsp;</dl>
			</li>
			<li>
				<dfn>Soggetto</dfn>  
				<dl><s:property value="documento.soggetto.codice"/> - <s:property value="documento.soggetto.descrizione"/>&nbsp;</dl>
			</li>
		</ul>


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
			<s:textfield id="codiceInc" name="codiceInc" maxlength="170" 
				cssClass="lbTextSmall span5" />
		</div>



						<p class="margin-medium">

			<s:hidden name="documento.anno" />
			<s:hidden name="documento.numero" />
			<s:hidden name="documento.tipo.codice" />
			<s:hidden name="documento.soggetto.codice" />
			<!-- SIAC-8699 -->
			<s:submit cssClass="btn btn-primary pull-right" action="annullaAttivazioniContabili_annullaAttivazioniContabili"  value="annulla attivazioni contabili" />

		  </p>

</s:if>
					          
          
			<s:include value="/jsp/include/modaleSoggetto.jsp" />
          
		  </s:form>     	
    </div>	
  </div>	 
</div>	



	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	
	

<script src="${jspath}common/soggetto.js"></script>
<script src="${jspath}documenti/annullaAttivazioniContabili.js"></script>
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>