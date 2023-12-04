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
  .chosen-container { width: 35% !important; }
  .radio { padding-left: 0px; padding-right: 20px; }
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
		        <li class="active">Aggiorna impegno con blocco ragioneria</li>
			</ul>
		</div>
	</div>

 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">       
     
        <s:form action="aggiornaImpegnoConBloccoRagioneria" method="post" cssClass="form-horizontal" id="form">
        
				
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
			


			<h3>Aggiorna impegno con blocco ragioneria</h3>

			<div class="step-content">
			<div class="step-pane active" id="step1">
			
			<h4>Dati</h4> 
			<fieldset class="form-horizontal margin-large">


			
			
					
			<div class="control-group">
				<label class="control-label">Impegno *</label>
				<div class="controls">
					<s:textfield cssClass="lbTextSmall span1" id="anno"  name="criteri.anno" placeholder="anno" />
					<s:textfield cssClass="lbTextSmall span2" id="numero"  name="criteri.numero" placeholder="numero" />
				
				</div>
			</div>

			</fieldset>  

		</div>
		</div>


					<p class="margin-medium">

			 <a class="btn btn-secondary" href="<s:property value="#urlCruscotto" />">indietro</a>   
			<!-- SIAC-8699 -->
			<s:submit cssClass="btn btn-primary pull-right" action="aggiornaImpegnoConBloccoRagioneria_cerca" id="cerca" value="cerca"  />

		  </p>

<s:if test="impegno != null">

<br/><div class="step-content"><div class="step-pane"><h4></h4></div></div>

		<h3>Impegno</h3>


		<ul class="htmlelt step-content">
			<li>
				<dfn>Anno</dfn>
				<dl><s:property value="impegno.anno"/>&nbsp;</dl>
			</li>
			<li>
				<dfn>Numero</dfn>
				<dl><s:property value="impegno.numero"/>&nbsp;</dl>
			</li>

			<li>
				<dfn>Atto amministrativo</dfn>
				<dl><s:property value="impegno.attoAmministrativo.anno"/> / <s:property value="impegno.attoAmministrativo.numero"/>
				    <s:if test="impegno.attoAmministrativo.sac != null"> / 
 				    		<s:property value="impegno.attoAmministrativo.sac.codice"/> <%-- <s:property value="impegno.attoAmministrativo.sac.descrizione"/> --%>
				    </s:if> 
					- <s:property value="impegno.attoAmministrativo.tipo.descrizione"/> 
					- Blocco ragioneria: <s:if test="impegno.attoAmministrativo.bloccoRagioneria">S&Igrave;</s:if><s:else>NO</s:else>&nbsp;</dl>
			</li>
		</ul>




<div class="control-group">
		    <span class="control-label">Parere finanziario</span>
		    <div class="controls">      
		          
 <s:radio theme="simple"  name="impegno.parereFinanziario" cssClass="radio inline" list="#{'true':'Si', 'false':'No'}" />&nbsp;
<%-- 				<s:submit cssClass="btn btn-primary" method="aggiornaParereFinanziario"  value="aggiorna parere finanziario" /> --%>
		    </div>
		</div>

<div class="control-group">
		    <span class="control-label">Prenotazione</span>
		    <div class="controls">      
		          
 <s:radio theme="simple"  name="impegno.flagPrenotazione" cssClass="radio inline flagPrenotazione" list="#{'true':'Si', 'false':'No'}" />&nbsp;
<%-- 				<s:submit cssClass="btn btn-primary" method="aggiornaFlagPrenotazione"  value="aggiorna prenotazione " /> --%>
		    </div>
		</div>

<div class="control-group">
		    <span class="control-label">Prenotazione liquidabile</span>
		    <div class="controls">      
		          
 <s:radio theme="simple"  name="impegno.flagPrenotazioneLiquidabile" cssClass="radio inline flagPrenotazioneLiquidabile" list="#{'true':'Si', 'false':'No'}" />&nbsp;
<%-- 				<s:submit cssClass="btn btn-primary" method="aggiornaFlagPrenotazioneLiquidabile"  value="aggiorna prenotazione liquidabile" /> --%>
		    </div>
		</div>


<div class="control-group">
		    <span class="control-label">Soggetto a DURC</span>
		    <div class="controls">      
		          
 		<s:radio theme="simple"  name="impegno.flagSoggettoDurc" cssClass="radio inline" list="#{'true':'Si', 'false':'No'}" />&nbsp;
<%-- 				<s:submit cssClass="btn btn-primary" method="aggiornaFlagSoggettoDurc"  value="aggiorna soggetto a DURC" /> --%>
		    </div>
		</div>


<div class="control-group">
		    <span class="control-label">Attributo SDF</span>
		    <div class="controls">      
		          
 <s:radio theme="simple"  name="impegno.flagSdf" cssClass="radio inline" list="#{'true':'Si', 'false':'No'}" />&nbsp;
<%-- 				<s:submit cssClass="btn btn-primary" method="aggiornaFlagSdf"  value="aggiorna attributo SDF" /> --%>
		    </div>
		</div>


<div class="control-group">
		    <span class="control-label">CUP</span>
		    <div class="controls">      
		          
 <s:textfield name="impegno.cup" cssClass="lbTextSmall span2" maxlength="15" />&nbsp;
<%-- 				<s:submit cssClass="btn btn-primary" method="aggiornaCup"  value="aggiorna CUP" /> --%>
		    </div>
</div>



<div class="control-group">
		    <span class="control-label">SAC</span>
		    <div id="impegno-sac" data-codice-sac="<s:property value="impegno.strutturaAmministrativoContabile.codice" />" class="controls">      
			 <s:textfield name="impegno.strutturaAmministrativoContabile.codice" cssClass="lbTextSmall span2"  />&nbsp;
			 <s:hidden name="impegno.strutturaAmministrativoContabile.uid" />
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
			<s:submit cssClass="btn btn-primary pull-right" action="aggiornaImpegnoConBloccoRagioneria_aggiornaTutto"  value="aggiorna" />

		  </p>

</s:if>
					          
          
          
		  </s:form>     	
    </div>	
  </div>	 
</div>	



	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	
	

<script src="${jspath}common/soggetto.js"></script>
<script src="${jspath}impegni/aggiornaImpegnoConBloccoRagioneria.js"></script>
	
	
	
</body>
</html>