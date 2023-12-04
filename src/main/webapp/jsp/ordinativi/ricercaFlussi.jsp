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
		        <li class="active">Ricerca distinte</li>
			</ul>
		</div>
	</div>

 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">       
     
        <s:form action="ricercaFlussi" method="post" cssClass="form-horizontal" id="form">
        
				
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
			


			<h3>Ricerca distinte</h3>

			<div class="step-content">
			<div class="step-pane active" id="step1">
			
			<h4>Dati</h4> 
			<fieldset class="form-horizontal margin-large">
			
				<div class="control-group">
							<label class="control-label" for="tipo">Tipo *</label>			  
							<div class="controls"> 
									<s:select cssClass="span4"
				       name="criteri.codiceTipo"
				       headerKey="" 
				       headerValue=""
						list="elencoTipiFlusso" 
						listKey="codice"
				       listValue="descrizione"
				      />
						</div>
						</div> 
					
				<div class="control-group">
							<label class="control-label" for="tipoFile">Anno</label>			  
							<div class="controls"> 
						<s:textfield cssClass="lbTextSmall span2" id="codFile"  name="criteri.anno" />
						</div>
						</div> 
	
					
					<div class="control-group">
					<label class="control-label" for="">Data inserimento </label>
					<div class="controls">
						<span class="al">
							<label for="dataInserimentoDa" class="radio inline">Da</label>
						</span>
						<input type="text" name="criteri.dataInserimentoDaStr" value="" id="dataInserimentoDa" class="lbTextSmall span2 datepicker" placeholder="gg/mm/aaaa" tabindex="-1">
						<span class="al">
							<label for="dataInserimentoA" class="radio inline">A</label>
						</span>
						<!-- <input type="text" name="dataA" value="" class="lbTextSmall span2" id="dataA" /> -->
						<input type="text" name="criteri.dataInserimentoAStr" value="" id="dataInserimentoA" class="lbTextSmall span2 datepicker" placeholder="gg/mm/aaaa" tabindex="-1">
					</div>
				</div>
					
						
					
				<div class="control-group">
					<label class="control-label">Numero</label>
					<div class="controls">
						<span class="al">
							<label class="radio inline" for="perDA">Da</label>
						</span>
						<s:textfield cssClass="lbTextSmall span2" id="codFile"  name="criteri.numeroDa" />
						<span class="al">
							<label for="perA" class="radio inline">A</label>
						</span>
						<s:textfield cssClass="lbTextSmall span2" id="codFile"  name="criteri.numeroA" />
					</div>
				</div>
	
	
		

				</fieldset>  

		</div>
		</div>
			
          <p class="margin-medium">
			  <a class="btn btn-secondary" href="<s:property value="#urlCruscotto" />">indietro</a>   
			  <a class="btn btn-secondary" href="ricercaFlussi.do">annulla</a>
			  
			  <!-- SIAC-8699 -->
			  <!--<s:submit cssClass="btn btn-primary pull-right" method="cerca"  value="cerca" />-->
			  <s:submit cssClass="btn btn-primary pull-right" action="ricercaFlussi_cerca"  value="cerca" />

		  </p>
          
		  </s:form>     	
    </div>	
  </div>	 
</div>	







	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
		<script type="text/javascript" src="${jspath}ordinativi/ricercaFlussi.js" charset="utf-8"></script>
	
	
	
	
	
	
	
	
	
</body>
</html>