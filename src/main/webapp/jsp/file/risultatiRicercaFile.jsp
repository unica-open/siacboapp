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
				<li><a href="ricercaFile.do">Ricerca file</a>
					<span class="divider">&gt;</span></li>
		        <li class="active">Risultato ricerca file</li>
			</ul>
		</div>
	</div>

 


<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">    
    
<s:form action="xxxxxx" method="post">

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


		
		<h3>Risultato ricerca file caricati</h3>   
		<fieldset class="form-horizontal">

		<h4 class="step-pane">Elenco</h4>
		<h4><span class="num_result"><s:property value="listaPaginata.totaleElementi"/></span> Risultati trovati</h4>                 
		
			<table class="table table-hover tab_left"  summary="...." >
				<thead>
					<tr>
						<th class="span2">Tipo file</th>
						<th>Codice</th>
						<th>Nome</th>
						<th>Stato</th>
						<th class="span2">Data caricamento</th>	
						<th class="span2 tab_Right">&nbsp;</th>			
					</tr>
				</thead>
				<tbody>
				
				
				<s:iterator value="listaPaginata" status="st"  var="file">

	
	<tr>
		<td class="file-tipo"><s:property value="#file.tipo.descrizione" /></td>
		<td class="file-codice"><s:property value="#file.codice" /></td>
		<td><span class="file-index hide"><s:property value="#st.index" /></span><a class="file-nome" data-content="<s:property value="#file.note" />" 
			rel="popover" data-trigger="hover" href="#" data-original-title="Note"><s:property value="#file.nome" /></a></td>
		<td class="file-stato"><s:property value="#file.statoFile.descrizione" /></td>
		<td class="file-dataUpload"><s:date name="#file.dataCreazione" format="dd/MM/yyyy H:mm" /></td>
		<td class="tab_Right">
			<div class="btn-group">
				<button class="btn dropdown-toggle" data-toggle="dropdown">Azioni<span class="caret"></span></button>
				<ul class="dropdown-menu pull-right">
				
				
				<s:iterator value="getAzioniTipoFile(#file.tipo.codice)" status="statusAzioniTipoFile">
					<s:if test="mostraAzione(#file, #statusAzioniTipoFile.index)" >
						<li><a class="azione-file" 
							href="<s:url forceAddSchemeHostAndPort="true" action="risultatiRicercaFile_azioneTipoFile"><s:param name="index" 
																value="#st.index"/><s:param name="indexAzioneTipoFile" 
																			value="#statusAzioniTipoFile.index"/></s:url>"><s:property /></a></li>
					</s:if>
				</s:iterator>
				
				
				<s:if test="daElaborare or elaboratoConErrori" >
					<li><a  class="azione-file" href="#msgAnnulla" data-toggle="modal">annulla</a></li>
				</s:if>
				
				<s:if test="tipo.azioneServizio != null" >
					<s:if test="#file.daElaborare" >				
					<li><a  class="azione-file" href="#msgElabora" data-toggle="modal">elabora</a></li>
					</s:if>
				</s:if>
					
					<li><a href="<s:url action="risultatiRicercaFile_download"><s:param name="index" value="#st.index"/></s:url>">download</a></li> 	
					<li><a class="consulta-file" href="#msgConsulta" data-toggle="modal">consulta</a></li> 								
				</ul>
			</div>
		</td>
	</tr>
	
					
				</s:iterator>	
				

				</tbody>
				<tfoot>
				</tfoot>

			</table>
			
			<div class="row pagination_conth">
				  <div id="risultatiricerca_info2" class="span6"><s:property value="listaPaginata.numeroElementoInizio"/> - <s:property value="listaPaginata.numeroElementoFine"/>
				  	 di <s:property value="listaPaginata.totaleElementi"/> risultati</div>   
				  <div class="span6">                               
					<div id="paginazione2" class="dataTables_paginate paging_bootstrap pagination">
					  <ul>
						<li><s:if test="not listaPaginata.primaPagina"><a href="<s:url action="risultatiRicercaFile_cerca"><s:param name="pagina" value="0"/></s:url>">&laquo; inizio</a></s:if><s:else><a href="#">&laquo; inizio</a></s:else></li>
						<li><s:if test="listaPaginata.hasPaginaPrecedente"><a href="<s:url action="risultatiRicercaFile_cerca"><s:param name="pagina" value="%{pagina-1}"/></s:url>">&laquo; prec</a></s:if><s:else><a href="#">&laquo; prec</a></s:else></li>
						<!-- SIAC-8699 id > var -->
						<s:iterator var="p" begin="numeroPaginaInizio" end="numeroPaginaFine">
							<li <s:if test="%{(#p-1) eq listaPaginata.paginaCorrente}">class="active"</s:if>><a href="<s:url action="risultatiRicercaFile_cerca"><s:param name="pagina" value="%{#p-1}"/></s:url>"><s:property/></a></li>
						</s:iterator>
						
						<li><s:if test="listaPaginata.hasPaginaSuccessiva"><a href="<s:url action="risultatiRicercaFile_cerca"><s:param name="pagina" value="%{pagina + 1}"/></s:url>">succ &raquo;</a></s:if><s:else><a href="#">succ &raquo;</a></s:else></li>
						<li><s:if test="not listaPaginata.ultimaPagina"><a href="<s:url action="risultatiRicercaFile_cerca"><s:param name="pagina" value="%{listaPaginata.totalePagine-1}"/></s:url>">fine &raquo;</a></s:if><s:else><a href="#">fine &raquo;</a></s:else></li>              
					  </ul>
					</div>         
				  </div>  
			</div> 
			<div class="Border_line"></div>


			
<!-- Modal msgAnnulla -->
	<div id="msgAnnulla" class="modal hide fade modal-azione" tabindex="-1" role="dialog" aria-labelledby="msgAnnullaLabel" aria-hidden="true">
	  <div class="modal-body">
		<div class="alert alert-error">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <p><strong>Attenzione!</strong></p>
		  <p><strong>Elemento selezionato:.....</strong></p>
		  <p>Stai per annullare l'elemento selezionato, questo cambier&agrave; lo stato dell'elemento: sei sicuro di voler proseguire?</p>
		</div>
	  </div>
	  <div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">no, indietro</button>
		<button class="btn btn-primary conferma-azione" data-dismiss="modal" aria-hidden="true">si, prosegui</button>
		<a class="anchor-azione" href="<s:url forceAddSchemeHostAndPort="true" action="risultatiRicercaFile_annulla"/>"><span class="hide"></span></a>
	  </div>
	</div>  
<!--/modale annulla --> 


<!-- Modal msgElabora -->
<div id="msgElabora" class="modal hide fade modal-azione" tabindex="-1" role="dialog" aria-labelledby="msgElaboraLabel" aria-hidden="true">	
	 <div class="modal-body">
		<div class="alert alert-error">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
			<p><strong>Attenzione!</strong></p> 
			<p><strong>Hai chiesto di elaborare il file: sei sicuro di voler proseguire?</strong></p>
		</div>
	  </div>
	  <div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">no, indietro</button>
		<button class="btn btn-primary conferma-azione" data-dismiss="modal" aria-hidden="true">si, prosegui</button>
		<a class="anchor-azione" href="<s:url forceAddSchemeHostAndPort="true" action="risultatiRicercaFile_elabora"/>"><span class="hide"></span></a>
	  </div>
</div>  
<!-- end Modal -->	 


<!-- Modal msgEstrazioneUpload -->
<div id="msgEstrazioneUpload" class="modal hide fade modal-azione" tabindex="-1" role="dialog" aria-labelledby="msgElaboraLabel" aria-hidden="true">	
	 <div class="modal-body">
		<div class="alert alert-error">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
			<p><strong>Attenzione!</strong></p> 
			<p><strong>Hai chiesto di eseguire l'estrazione per upload: sei sicuro di voler proseguire?</strong></p>
		</div>
	  </div>
	  <div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">no, indietro</button>
		<button class="btn btn-primary conferma-azione" data-dismiss="modal" aria-hidden="true">si, prosegui</button>
		<a class="anchor-azione" href="<s:url forceAddSchemeHostAndPort="true" action="risultatiRicercaFile_estrazioneUpload"/>"><span class="hide"></span></a>
	  </div>
</div>  
<!-- end Modal -->	 



<!-- Modal msgConsulta -->
<div id="msgConsulta" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="msgConsultaLabel" aria-hidden="true">	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="nostep-pane">Consulta file</h4>
	</div>
	
	<div class="modal-body">
		<fieldset class="form-horizontal">
				<div class="boxOrSpan2">
					<div class="boxOrInline"> 
						<p>Dati</p>
						<ul class="htmlelt">
							<li>
								<dfn>Tipo file</dfn> 
								<dl id="consulta-tipo"></dl>
							</li>
							<li>
								<dfn>Codice</dfn> 
								<dl id="consulta-codice"></dl>
							</li>
							<li>
								<dfn>Nome</dfn> 
								<dl id="consulta-nome"></dl>
							</li>
							<li>
								<dfn>Stato</dfn> 
								<dl id="consulta-stato"></dl>
							</li>
							<li>
								<dfn>Data caricamento</dfn> 
								<dl id="consulta-dataUpload"></dl>
							</li>
							<li>
								<dfn>Note</dfn> 
								<dl id="consulta-note"></dl>
							</li>			
						</ul>							
					</div>
				</div>
		</fieldset>
	</div>
	<div class="modal-footer">
		<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">chiudi</button>
	</div>
</div>  
<!-- end Modal -->






		</fieldset>
		<p class="margin-medium"><a class="btn btn-secondary" href="ricercaFile.do">indietro</a> </p>       
          
      </s:form>
    </div>
  </div>	 
</div>	

	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	
<r:include url="/ris/servizi/siac/include/myCheckbox.html" resourceProvider="rp"/>
	
	
	
	<script type="text/javascript" src="${jspath}file/risultatiRicercaFile.js" charset="utf-8"></script>
	
	
	
	
</body>
</html>