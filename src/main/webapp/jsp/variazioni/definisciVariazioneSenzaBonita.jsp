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
		        <li class="active">Definisci variazione senza Bonita</li>
			</ul>
		</div>
	</div>

 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">       
     
        <s:form action="definisciVariazioneSenzaBonita" method="post" cssClass="form-horizontal" id="form">
        
				
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
			


			<h3>Definisci variazione senza Bonita</h3>

			<div class="step-content">
			<div class="step-pane active" id="step1">
			
			<h4>Dati</h4> 
			<fieldset class="form-horizontal margin-large">


			
			
					
			<div class="control-group">
				<label class="control-label">Variazione *</label>
				<div class="controls">
					<s:textfield cssClass="lbTextSmall span2" id="numero"  name="criteri.numero" placeholder="numero" />
				
				</div>
			</div>

			</fieldset>  

		</div>
		</div>


					<p class="margin-medium">

			 <a class="btn btn-secondary" href="<s:property value="#urlCruscotto" />">indietro</a>   
			<!-- SIAC-8699 -->
			<s:submit cssClass="btn btn-primary pull-right" action="definisciVariazioneSenzaBonita_cerca" id="cerca" value="cerca"  />

		  </p>

<s:if test="variazione != null">

<br/><br/><div class="step-content"><div class="step-pane"><h4></h4></div></div>

		<h3>Variazione</h3>


		<ul class="htmlelt step-content">
			<li>
				<dfn>Numero</dfn>
				<dl><s:property value="variazione.numero"/>&nbsp;</dl>
			</li>
			<li>
				<dfn>Descrizione</dfn>
				<dl><s:property value="variazione.descrizione"/>&nbsp;</dl>
			</li>
			<li>
				<dfn>Stato</dfn>
				<dl><s:property value="variazione.stato.descrizione"/>&nbsp;</dl>
			</li>
		</ul>

<br/>
   <s:if test="variazione.definibile">

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

			<s:hidden name="variazione.numero"/>
			<s:hidden name="variazione.uid" />
			<!-- SIAC-8699 -->
			<s:submit cssClass="btn btn-primary pull-right" action="definisciVariazioneSenzaBonita_definisciVariazione"  value="definisci variazione" />

		  </p>  
    </s:if>
    <s:else>
    	La variazione non &egrave; definibile
    </s:else>
</s:if>
					                    
          
		  </s:form>     	
    </div>	
  </div>	 
</div>	



	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	
	

<script src="${jspath}common/soggetto.js"></script>
<script src="${jspath}gestioneVariazione/definisciVariazioneSenzaBonita.js"></script>
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>