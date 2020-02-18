<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ taglib uri="http://www.csi.it/taglibs/remincl-1.0" prefix="r"%> 


<%-- Inclusione head e CSS --%>
<s:include value="/jsp/include/head.jsp" />


<%-- Inclusione librerie JavaScript --%>
<s:include value="/jsp/include/javascript.jsp" />
<%-- Eventuali altre librerie JavaScript --%>

</head>
<body>
	<%-- Inclusione header --%>
	<s:include value="/jsp/include/header.jsp" />
	




	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="<s:property value="#urlCruscotto" />">Home</a>
					<span class="divider">&gt;</span></li>
		        <li class="active">Parametri report previsione</li>
			</ul>
		</div>
	</div>

 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">       
     
        
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


			
			
			
			<h3>Importi variabili per i report</h3>
					<fieldset class="form-horizontal">
						<div class="well">
								<div class="control-group margin-medium">
									<label class="control-label sBold">Seleziona il report</label>
									<div class="controls">
									
									 <s:select id="setReport" cssClass="span10" 
									       name="idReport"
									       headerKey="" 
									       headerValue=""
											list="elencoReport" 
											listKey="uid"
									       listValue="descrizione"
									      />
									
									</div>
								</div>
							                   
						</div>

					</fieldset>

	<s:if test="idReport != null">	
						
						<fieldset class="form-horizontal">
							
							<h4 class="step-pane"><span class="num_result"><s:property value="%{elencoImportiFissi.size+elencoImportiVariabili.size}" /></span> Risultati variabili</h4>
							
	<s:if test="not elencoImportiFissi.isEmpty()">	
							
							<h4>Fisse</h4>
	
							
							<table class="table table-hover tab_left">   
								<tbody>
									<tr>
										<th class="span12">Descrizione</th>
										<th class="span3 tab_Right">Importo <s:property value="annoEsercizio" /></th>
										<th class="span3 tab_Right">Importo  <s:property value="%{annoEsercizio+1}" /></th>
										<th class="span3 tab_Right">Importo <s:property value="%{annoEsercizio+2}" /></th>
									</tr>
									
								<s:iterator value="elencoImportiFissi">
									
									
									<tr>
										<td><s:property value="value.descrizione" /></td>
										
									<s:iterator  begin="annoEsercizio" end="%{annoEsercizio+2}" var="anno">
										
										<td class="tab_Right field" data-orig='<s:property value="%{toImporto(value.importi[#anno].importo)}" />'>
										<s:if test="value.importi.containsKey(#anno)">
										
										<%-- = <s:property value="value.importi[#anno].importo" /> = --%>
										
											<s:textfield  name="importo[%{value.importi[#anno].uid}]" cssClass="importo"
												value="%{toImporto(value.importi[#anno].importo)}" />
										</s:if>
										</td>
									</s:iterator>
									
								</s:iterator>
									

								</tbody>
							</table>
</s:if>				
			
<s:if test="not elencoImportiVariabili.isEmpty()">	
				
							<h4>Variabili</h4>
							<div class="accordion" id="accordionVariable">  
								
		<s:iterator value="elencoImportiVariabili">
								
								
								<!-- acc -->
								<div class="accordion-group">                
									<div class="accordion-heading">
									<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordionVariable_<s:property value="hashCode()" />" href="#Variable_<s:property value="hashCode()" />">
									<span class="alRight"><i id="orange" class="icon icon-copy">&nbsp;</i></span>
									Codice: <s:property value="key" /><span class="icon">&nbsp;</span>
									</a>
									</div>
									
									<div id="Variable_<s:property value="hashCode()" />" class="accordion-body collapse">
										<div class="accordion-inner">
											<table class="table table-hover tab_left">   
												<tbody>
													<tr>
														<th class="span12">Descrizione</th>
														<th class="span3 tab_Right">Importo <s:property value="%{annoEsercizio}" /></th>
														<th class="span3 tab_Right">Importo <s:property value="%{annoEsercizio+1}" /></th>
														<th class="span3 tab_Right">Importo <s:property value="%{annoEsercizio+2}" /></th>
													</tr>
													
												<s:iterator value="value" status="status">
													
													
													<tr>
														<td class="field" data-orig='<s:property value="%{value.descrizione}" />'>
															<s:textfield cssClass="span12" 
																name="descrizioneImporti['%{value.elencoIdImporti}']" value="%{value.descrizione}" />
														</td>
														
													<s:iterator  begin="annoEsercizio" end="%{annoEsercizio+2}" var="anno">
														
										<s:if test="value.importi.containsKey(#anno)">
														<td class="tab_Right field"  data-orig='<s:property value="%{toImporto(value.importi[#anno].importo)}" />'>
									<s:textfield  name="importo[%{value.importi[#anno].uid}]"  cssClass="importo"
										value="%{toImporto(value.importi[#anno].importo)}" />
										</td>
										</s:if>
										<s:else><td class="tab_Right"></td>
										</s:else>
													</s:iterator>
														

													</tr>
													
												</s:iterator>
												
													
												</tbody>
											</table>
				
										</div>
									</div>                    
								     
								</div>
								<!-- /acc -->
			</s:iterator>

							</div>
							
		</s:if>				
							
							
						</fieldset>
					
			
			  </s:if>
			
          <p class="margin-medium">
			  <a class="btn btn-secondary" href="<s:property value="#urlCruscotto" />">indietro</a>   
			  <a class="btn btn-secondary" href="<s:url action="elencoImportiReport" />">annulla</a>
			  
			  
        <s:form action="elencoImportiReport" method="post" cssClass="form-horizontal" id="form">
			  
			  	<s:if test="idReport != null">	
			  
			  <s:submit id="aggiorna" cssClass="btn btn-primary pull-right" method="update"  value="aggiorna" />
				</s:if>
		  </s:form>     	
		  </p>
  
    </div>	
  </div>	 
</div>	







	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	
	
<script type="text/javascript" src="${jspath}report/elencoImportiReport.js" charset="utf-8"></script>
		
	
	
</body>
</html>