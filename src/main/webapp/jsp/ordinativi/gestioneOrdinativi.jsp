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
		        <li class="active">Gestione ordinativi</li>
			</ul>
		</div>
	</div>

 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">       
     
        <s:form action="gestioneOrdinativi" method="post" cssClass="form-horizontal" id="form">
        
				
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
			


			<h3>Gestione ordinativi</h3>

			<div class="step-content">
			<div class="step-pane active" id="step1">
			
			<h4>Dati</h4> 
			<fieldset class="form-horizontal margin-large">
			
				<div class="control-group">
					<label class="control-label" for="tipoFile">Tipo *</label>			  
					<div class="controls"> 
						<s:select cssClass="span4"
						   id="codiceTipo"
					       name="criteri.codiceTipo"
					       headerKey="" 
					       headerValue=""
						   list="elencoTipiOrdinativo" 
						   listKey="codice"
					       listValue="descrizione"
					      />
					</div>
				</div> 
					
				<div class="control-group">
					<label class="control-label" for="statoOrd">Stato</label>			  
					<div class="controls"> 
						<s:select cssClass="span4 chosen-select-stato-ord" 
						   multiple="true" 
					       name="criteri.codiciStato"
					       headerKey="" 
					       headerValue=""
						   list="elencoStatiOrdinativo" 
						   listKey="codice"
					       listValue="descrizione"
					      />
					</div>
				</div> 
					
				<div class="control-group">
					<label class="control-label"> Numero</label>
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
	
	
<%-- 				<div class="control-group"> --%>
<%-- 							<label class="control-label" for="tipoFile">Codice flusso</label>			   --%>
<%-- 							<div class="controls">  --%>
<%-- 						<s:textfield cssClass="lbTextSmall span2" id="codFile"  name="criteri.codiceFlusso" /> --%>
<%-- 						</div> --%>
<%-- 				</div>  --%>
	
				<div class="control-group">
							<label class="control-label" for="statoOrd">Codice flusso</label>			  
							<div class="controls"> 
							
					<s:select cssClass="span4 chosen-select-flusso"
					   id="elencoCodiciFlussoOrdinativiEntrata"
				       name="criteri.codiceFlusso"
				       headerKey="" 
				       headerValue=""
						list="elencoCodiciFlussoOrdinativiEntrata" 
				      />

					<s:select cssClass="span4 chosen-select-flusso" 
					   id="elencoCodiciFlussoOrdinativiSpesa"
				       name="criteri.codiceFlusso"
				       headerKey="" 
				       headerValue=""
						list="elencoCodiciFlussoOrdinativiSpesa" 
				      />
				      
					<select name="x" id="elencoCodiciFlussoOrdinativiEmpty" class="span4 chosen-select-flusso" disabled="disabled"></select>				      
				      
						</div>
				</div> 
	


				<div class="control-group">
							<label class="control-label" for="statoOrd">Distinta</label>			  
							<div class="controls"> 
							
					<s:select cssClass="span4 chosen-select-distinta"
					   id="elencoCodiciDistintaOrdinativiEntrata"
				       name="criteri.idCodiceDistinta"
				       headerKey="" 
				       headerValue=""
				       listKey="uid"
				       listValue="%{codice + ' - ' + descrizione}"
						list="elencoCodiciDistintaOrdinativiEntrata" 
				      />

					<s:select cssClass="span4 chosen-select-distinta" 
					   id="elencoCodiciDistintaOrdinativiSpesa"
				       name="criteri.idCodiceDistinta"
				       headerKey="" 
				       headerValue=""
				       listKey="uid"
				       listValue="%{codice + ' - ' + descrizione}"
						list="elencoCodiciDistintaOrdinativiSpesa" 
				      />
				      
					<select name="x" id="elencoCodiciDistintaOrdinativiEmpty" class="span4 chosen-select-distinta" disabled="disabled"></select>				      
				      
					</div>
				</div> 
	



				<div class="control-group">
					<label class="control-label" for="">Data emissione </label>
					<div class="controls">
						<span class="al">
							<label for="dataEmissioneDa" class="radio inline">Da</label>
						</span>
						<!-- <input type="text" name="dataDa" value="" class="lbTextSmall span2" id="dataDa" /> -->
						<input type="text" name="criteri.dataEmissioneDaStr" value="" id="dataEmissioneDa" class="lbTextSmall span2 datepicker" placeholder="gg/mm/aaaa" tabindex="-1">
						<span class="al">
							<label for="dataEmissioneA" class="radio inline">A</label>
						</span>
						<!-- <input type="text" name="dataA" value="" class="lbTextSmall span2" id="dataA" /> -->
						<input type="text" name="criteri.dataEmissioneAStr" value="" id="dataEmissioneA" class="lbTextSmall span2 datepicker" placeholder="gg/mm/aaaa" tabindex="-1">
					</div>
				</div>
					
		  		<div class="control-group">
					<label class="control-label" for="">Data trasmissione</label>
					<div class="controls">
						<span class="al">
							<label for="dataTrasmissioneOilDa" class="radio inline">Da</label>
						</span>
						<!-- <input type="text" name="dataDa" value="" class="lbTextSmall span2" id="dataDa" /> -->
						<input type="text" name="criteri.dataTrasmissioneOilDaStr" value="" id="dataTrasmissioneOilDa" class="lbTextSmall span2 datepicker" placeholder="gg/mm/aaaa" tabindex="-1">
						<span class="al">
							<label for="dataTrasmissioneoilA" class="radio inline">A</label>
						</span>
						<!-- <input type="text" name="dataA" value="" class="lbTextSmall span2" id="dataA" /> -->
						<input type="text" name="criteri.dataTrasmissioneOilAStr" value="" id="dataTrasmissioneOilA" class="lbTextSmall span2 datepicker" placeholder="gg/mm/aaaa" tabindex="-1">
					</div>
				</div>
				<s:hidden  name="criteri.dataTrasmissioneOilPresente" id="dataTrasmissioneOilPresente" />

				<!-- Evolutiva BackofficeGestioneOrdinativi -->
		  		<div class="control-group">
					<label class="control-label" for="">Data spostamento</label>
					<div class="controls">
						<span class="al">
							<label for="dataOrdSpostamentoDa" class="radio inline">Da</label>
						</span>
						<!-- <input type="text" name="dataDa" value="" class="lbTextSmall span2" id="dataDa" /> -->
						<input type="text" name="criteri.dataOrdSpostamentoDaStr" value="" id="dataOrdSpostamentoDa" class="lbTextSmall span2 datepicker" placeholder="gg/mm/aaaa" tabindex="-1">
						<span class="al">
							<label for="dataOrdSpostamentoA" class="radio inline">A</label>
						</span>
						<!-- <input type="text" name="dataA" value="" class="lbTextSmall span2" id="dataA" /> -->
						<input type="text" name="criteri.dataOrdSpostamentoAStr" value="" id="dataOrdSpostamentoA" class="lbTextSmall span2 datepicker" placeholder="gg/mm/aaaa" tabindex="-1">
					</div>
				</div>
				<!-- Evolutiva BackofficeGestioneOrdinativi -->
		
		
			  <s:if test="siopePlus">
				<div class="control-group">
					<label for="includiVBDaTrasmettere" class="control-label">Includi VB da trasmettere</label>
					<div class="controls">
						<s:checkbox name="criteri.includiVBDaTrasmettere" value="" id="includiVBDaTrasmettere"/>
					</div>
				</div>		
		
				<div class="control-group">
					<label for="includiAnnulliDaTrasmettere" class="control-label">Includi Annulli da trasmettere</label>
					<div class="controls">
						<s:checkbox name="criteri.includiAnnulliDaTrasmettere" value="" id="includiAnnulliDaTrasmettere"/>
					</div>
				</div>

		
						
			  </s:if>



				<div class="control-group">
					<span class="control-label">Da trasmettere</span>
					<div class="controls">

						<label class="radio inline" for="daTrasmettereTrue">
							<input type="radio" name="criteri.daTrasmettere" id="daTrasmettereTrue" value="1" /> S&igrave;
						</label>
							
						<label class="radio inline" for="daTrasmettereFalse">
							<input type="radio" name="criteri.daTrasmettere" id="daTrasmettereFalse" value="0" /> No
						</label>
							
						<label class="radio inline" for="daTrasmettereNull">
							<input type="radio" name="criteri.daTrasmettere" id="daTrasmettereNull" value="" /> Non applicare
						</label>
					</div>
				</div>

				<h4 class="step-pane">Provvedimento</h4>
				<div id="datiProvvedimento" class="control-group">
					<s:hidden id="idAttoAmministrativo" name="criteri.attoAmministrativo.uid" />
					<label class="control-label" for="annoAttoAmministrativo">Anno</label>
					<div class="controls" >
						<s:textfield id="annoAttoAmministrativo" cssClass="lbTextSmall span1 soloNumeri" name="criteri.attoAmministrativo.anno" />
						<span class="al">
							<label class="radio inline" for="numeroAttoAmministrativo">Numero</label>
						</span>
						<s:textfield id="numeroAttoAmministrativo" cssClass="lbTextSmall span2 soloNumeri" name="criteri.attoAmministrativo.numero" maxlength="7" />
						<span class="al">
							<label class="radio inline" for="tipoAtto">Tipo</label>
						</span>
						<s:select list="elencoTipiAttoAmministrativo" listKey="uid" listValue="descrizione" name="criteri.attoAmministrativo.tipo.uid"
							id="tipoAtto" cssClass="span4" headerKey="" headerValue="" />
						<span class="radio guidata">
							<a href="#" id="pulsanteApriModaleProvvedimento" class="btn btn-primary">compilazione guidata</a>
						</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Struttura Amministrativa</label>
					<div class="controls">
						<div class="accordion span8 struttAmm" id="accordionStrutturaAmministrativaContabileAttoAmministrativo">
							<div class="accordion-group">
								<div class="accordion-heading">
									<a class="accordion-toggle collapsed" id="accordionPadreStrutturaAmministrativa" href="#collapseStrutturaAmministrativaContabileAttoAmministrativo"
										data-toggle="collapse" data-parent="#accordionStrutturaAmministrativaContabileAttoAmministrativo">
										<span id="SPAN_StrutturaAmministrativoContabile">Seleziona la Struttura amministrativa</span>
									</a>
								</div>
								<div id="collapseStrutturaAmministrativaContabileAttoAmministrativo" class="accordion-body collapse">
									<div class="accordion-inner">
										<ul id="treeStruttAmm" class="ztree treeStruttAmm"></ul>

											<button type="button" id="confermaStrutturaAmministrativoContabile" 
												class="btn btn-primary pull-right" data-toggle="collapse" data-target="#collapseStrutturaAmministrativaContabileAttoAmministrativo">Conferma</button>
											<button type="button" class="btn btn-secondary pull-right"
													id="deselezionaStrutturaAmministrativoContabile">
												Deseleziona
											</button>
									</div>
								</div>
							</div>
						</div>

						<s:hidden id="HIDDEN_StrutturaAmministrativoContabileUid" name="criteri.attoAmministrativo.sac.uid" />
					</div>
				</div>		
		
				<h4 class="step-pane">Creditore</h4>
				<div id="datiSoggetto" class="control-group">
					<s:hidden id="idSoggetto" name="criteri.soggetto.uid" />
					<label class="control-label" for="codiceSoggetto">Codice</label>
					<div class="controls" >
						<s:textfield id="codiceSoggetto" cssClass="lbTextSmall span2 soloNumeri" name="criteri.soggetto.codice" />

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
			  <a class="btn btn-secondary" href="gestioneOrdinativi.do">annulla</a>

			<!-- SIAC-8699 -->  
			<s:submit cssClass="btn btn-primary pull-right" action="gestioneOrdinativi_preparaVB"  value="prepara vb" />
			<s:if test="siopePlus">
				<!-- SIAC-8699 -->
				<s:submit cssClass="btn btn-primary pull-right" action="gestioneOrdinativi_trasmettiSiopePlus"  value="trasmetti siope plus" />
			</s:if><s:else>
				<!-- SIAC-8699 -->
				<s:submit cssClass="btn btn-primary pull-right" action="gestioneOrdinativi_trasmetti"  value="trasmetti" />
			</s:else>
			<s:if test="siopePlus">
				<!-- SIAC-8699 -->
				<s:submit cssClass="btn btn-primary pull-right" action="gestioneOrdinativi_sbloccaSiopePlus"  value="sblocca" />
			</s:if><s:else>
				<!-- SIAC-8699 -->
				<s:submit cssClass="btn btn-primary pull-right" action="gestioneOrdinativi_sblocca"  value="sblocca" />
			</s:else>			

			<!-- SIAC-8699 -->
			<s:submit cssClass="btn btn-primary pull-right" id="disattivaTrasmissione" action="gestioneOrdinativi_disattivaTrasmissione"  value="disattiva trasmissione" />
			<s:submit cssClass="btn btn-primary pull-right" id="attivaTrasmissione" action="gestioneOrdinativi_attivaTrasmissione"  value="attiva trasmissione" />

			<s:submit cssClass="btn btn-primary pull-right" id="associaProvvisoriCassa" action="gestioneOrdinativi_associaProvvisoriCassa"  value="associa provvisorio di cassa" />

		  </p>
          
          
			<s:include value="/jsp/include/modaleProvvedimento.jsp" />
			<s:include value="/jsp/include/modaleSoggetto.jsp" />
          
		  </s:form>     	
    </div>	
  </div>	 
</div>	



	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	
	
	
<script type="text/javascript">
var elencoStruttureAmministrativoContabiliJson = 
	<s:property escapeHtml="false" value="elencoStruttureAmministrativoContabiliJson" />;
</script>	
	

<script src="${jspath}common/provvedimento.js"></script> 
<script src="${jspath}common/soggetto.js"></script>
<script src="${jspath}ordinativi/gestioneOrdinativi.js"></script>
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>