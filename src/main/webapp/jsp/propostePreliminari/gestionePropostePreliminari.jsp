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
				<li><a href="<s:property value="#urlCruscotto" />">Home</a><span
					class="divider">&gt;</span></li>
				<li class="active">Gestione proposte preliminari <s:property value="tipoProposte" /> </li>
			</ul>
		</div>
	</div>

 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">       
     
        <s:form  method="post" cssClass="form-horizontal" id="form">
        
				
<s:if test="hasActionMessages()">
						<%-- Messaggio di WARNING --%>
						<div class="alert alert-warning">
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
			


			<h3>Gestione proposte preliminari <s:property value="tipoProposte" /></h3>

			<div class="step-content">
			<div class="step-pane active" id="step1">
			
			<fieldset class="form-horizontal margin-large">
			

					<div class="control-group">
					<label for="missione" class="control-label">Missione</label>
					<div class="controls">
						<s:select id="missione" list="elencoMissioni" name="criteriSelezioneCapitoli.idMissione" cssClass="span10"
							headerKey="" headerValue="" listKey="uid" listValue="%{codice + '-' + descrizione}" />
					</div>
				</div>
				
				<div class="control-group">
					<label for="programma" class="control-label">Programma 
							<a class="tooltip-test" title="selezionare prima la Missione" href="#">
							<i class="icon-info-sign">&nbsp;
								<span class="nascosto">selezionare prima la Missione</span>
							</i>
							</a>
					</label>
					<div class="controls">
						<s:select id="programma" list="elencoProgrammi" name="criteriSelezioneCapitoli.idProgramma" cssClass="span10"
							headerKey="" headerValue="" listKey="uid" disabled="%{elencoProgrammi.empty}"
							listValue="%{codice + '-' + descrizione}" />
					</div>
				</div>
		
						
	<div class="control-group">
		<s:hidden id="idStrutturaAmministrativoContabile" name="criteriSelezioneCapitoli.idStrutturaAmministrativoContabile"/>
		<label for="bottoneSAC" class="control-label">
			Struttura Amministrativa Responsabile 
		</label>
		<div class="controls">
			<a role="button" class="btn" id="bottoneSAC" data-toggle="modal" href="#struttAmm">
				Seleziona la Struttura amministrativa
			</a>
			<div id="struttAmm" class="modal hide fade" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
					<h3 id="myModalLabel2">
						Struttura Amministrativa Responsabile
					</h3>
				</div>
				<div class="modal-body">
					<ul id="treeStruttAmm" class="ztree"></ul>
				</div>
				<div class="modal-footer">
					<button id="deselezionaStrutturaAmministrativoContabile" class="btn">Deseleziona</button>
					<button id="confermaStrutturaAmministrativoContabile" type="button" class="btn btn-primary pull-right" data-dismiss="modal" aria-hidden="true">Conferma</button>
				</div>
			</div>
			&nbsp;
			<span id="SPAN_StrutturaAmministrativoContabile">Nessuna struttura amministrativa responsabile selezionata</span>
		</div>
	</div>		

	
	
	<s:iterator value="elencoClassificatoriGenerici.entrySet()" var="entry">
	
				<div class="control-group">
					<label for="missione" class="control-label"><s:property value="#entry.key.descrizione" /></label>
					<div class="controls">
						<s:select id="missione" list="#entry.value" name="criteriSelezioneCapitoli.idClassificatoreGenerico['%{#entry.key.codice}']" cssClass="span10"
							headerKey="" headerValue="" listKey="uid" listValue="%{codice + '-' + descrizione}" />
					</div>
				</div>
	
	</s:iterator>
	
	
	
	

	
	
		
				
			</fieldset>  

		</div>
		</div>
			
          <p class="margin-medium">
			  <a class="btn btn-secondary" href="<s:property value="#urlCruscotto" />">indietro</a>   
			  <a class="btn btn-secondary" href="gestionePropostePreliminari<s:property value="capitalize(tipoProposta)" />.do">annulla</a>
			  
			  <s:submit cssClass="btn btn-primary pull-right" method="prosegui" value="prosegui" />

		  </p>
          
		  </s:form>     	
    </div>	
  </div>	 
</div>	







	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	
	
	
<script type="text/javascript">
var elencoStruttureAmministrativoContabiliJson = <s:property escapeHtml="false" value="elencoStruttureAmministrativoContabiliJson" />;

</script>	
	
	
<script type="text/javascript" src="${jspath}propostePreliminari/gestionePropostePreliminari.js" charset="utf-8"></script>
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>