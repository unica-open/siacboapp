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
		        <li class="active">Ricerca file</li>
			</ul>
		</div>
	</div>

 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">       
     
        <s:form action="ricercaFile" method="post" cssClass="form-horizontal">
        
				
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
			


			<h3>Ricerca file caricati</h3>
			<p>&Egrave; necessario inserire almeno un criterio di ricerca.</p>            
			<div class="step-content">
			<div class="step-pane active" id="step1">
			
			<h4>Dati</h4> 
			<fieldset class="form-horizontal margin-large">
				<div class="control-group">
					<label class="control-label" for="codFile">Codice </label>			  
					<div class="controls">   
					<s:textfield cssClass="lbTextSmall span2" id="codFile"  name="codice" />
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="AnnoFile">Data caricamento</label>			  
					<div class="controls">  
					<s:textfield id="AnnoFile"  cssClass="lbTextSmall span2 datepicker" name="dataUpload" /> 
					</div>
				</div> 
				
				<div class="control-group">
					<label class="control-label" for="tipoFile">Tipo</label>			  
					<div class="controls"> 
					<s:select cssClass="span4"
       name="idTipo"
       headerKey="" 
       headerValue=""
		list="elencoTipoFile" 
		listKey="uid"
       listValue="descrizione"
      />
		</div>
				</div> 

				
				<div class="control-group">
					<label class="control-label" for="statoFile">Stato </label>			  
					<div class="controls"> 
					<s:select cssClass="span4"
       name="codiceStato"
       headerKey="" 
       headerValue=""
		list="elencoStatoFile" 
		listKey="codice"
       listValue="descrizione"
      />
		</div>
				</div> 
			
				<div class="control-group">
					<label class="control-label" for="nomeFile">Nome </label>			  
					<div class="controls">  
					<s:textfield name="nome" id="nomeFile"   cssClass="lbTextSmall span9" /> 
					</div>
				</div> 

				
				<br>
				
			</fieldset>  

		</div>
		</div>
			
          <p class="margin-medium">
			  <a class="btn btn-secondary" href="<s:property value="#urlCruscotto" />">indietro</a>   
			  <a class="btn btn-secondary" href="ricercaFile.do">annulla</a>
			  
			  <!-- SIAC-8699 -->
			  <s:submit cssClass="btn btn-primary pull-right" action="ricercaFile_cerca"  value="cerca" />

		  </p>
          
		  </s:form>     	
    </div>	
  </div>	 
</div>	







	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	
</body>
</html>