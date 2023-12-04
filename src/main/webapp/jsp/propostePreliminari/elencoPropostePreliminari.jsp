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


<style>

.simple-pagination-proposte-navigation
{
	text-align: right;
	margin-bottom: 30px;
}

.simple-pagination-proposte-first, 
.simple-pagination-proposte-previous, 
.simple-pagination-proposte-page-numbers, 
.simple-pagination-proposte-next, 
.simple-pagination-proposte-last
{
	display: inline-block;
	
}


.simple-pagination-proposte-navigation a
{
	float: left;
	line-height:24px;
	font-weight: normal;
	text-align: center;
	min-width: 14px;
	padding: 0 10px;
	color: #496684;

}

.simple-pagination-proposte-page-numbers .simple-pagination-proposte-navigation-disabled
{
    color: #000;
    cursor: default;
    font-size: 1.2em;
    font-weight: 700;
}

.rangeFilterTable
{
	width: 50px;
}

</style>

</head>
<body>
	<%-- Inclusione header --%>
	<s:include value="/jsp/include/header.jsp" />
	
	<%-- 
	<s:include value="/jsp/include/spinner.jsp" />
	--%>
	
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="<s:property value="#urlCruscotto" />">Home</a><span
					class="divider">&gt;</span></li>
				<li><a href="/siacboapp/gestionePropostePreliminari<s:property value="capitalize(tipoProposta)" />.do">Gestione
						proposte preliminari <s:property value="tipoProposta" /></a><span
					class="divider">&gt;</span></li>
				<li class="active">Elenco proposte preliminari <s:property value="tipoProposta" /> </li>
			</ul>
		</div>
	</div>

	
	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">



			
<s:if test="hasActionMessages()">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>Attenzione!!</strong><br>
		<ul>
			<s:actionmessage />
		</ul>
	</div>
</s:if>



			<s:if test="hasActionErrors()">
						<div class="alert alert-error">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Attenzione!!</strong><br>
							<ul>
								<s:actionerror />
							</ul>
						</div>
			</s:if>
			



			
			
				<ul id="tabs" class="nav nav-tabs" data-tabs="tabs" >
							<li class="active"><a href="#elencoCapitoli" data-toggle="tab">Capitoli</a></li>
							<li><a href="#elencoProposte" data-toggle="tab">Proposte preliminari <s:property value="tipoProposta" /> </a></li>
						</ul>
						
				<div class="contentLoading">Attendere, prego...</div>
						
						<div id="my-tab-content" class="tab-content nascosto" >

							<div class="tab-pane active" id="elencoCapitoli">

<s:if test="elencoCapitoli.empty">
Nessun capitolo trovato
</s:if>
<s:else>	

<%-- 		<h4><span class="num_result"><s:property value="elencoCapitoli.size()"/></span>  --%>
<%-- 		capitol<s:if test="elencoCapitoli.size() == 1">o</s:if><s:else>i</s:else></h4>                  --%>
		
<div id="simple-pagination">
	
<table class="table tab_left"> 
		<thead>
            <tr>
             <th scope="col">Numero</th>
              <th scope="col">Descrizione</th>
             </tr>								
          </thead>


		<tbody>
		<s:iterator value="elencoCapitoli">
		<tr>
				<td>  	<s:property value="codiceCompleto" />      </td>
				<td  id="descrizione" class="descrizione descrizioneCapitolo"><a  href="<s:url 
				action="%{'propostaPreliminare' + capitalize(tipoProposta)}"><s:param name="cuid" >
					<s:property value="uid" /></s:param>
		</s:url>"><s:property value="descrizioneCompleta" /></a></td>
				
			
				
		</tr>
		</s:iterator>
		</tbody>
	</table>







	<div class="Border_line margin-medium"></div>

<div class="simple-pagination-navigation">
		<div class="simple-pagination-first"></div>
		<div class="simple-pagination-previous"></div>
		<div class="simple-pagination-page-numbers"></div>
		<div class="simple-pagination-next"></div>
		<div class="simple-pagination-last"></div>
</div>

		</div>
</s:else>


							</div>


							<div class="tab-pane" id="elencoProposte">
									

<s:if test="elencoProposte.empty">
Nessuna proposta preliminare presente
</s:if>
<s:else>

<%-- 		<h4><span class="num_result"><s:property value="elencoProposte.size()"/></span>  --%>
<%-- 		propost<s:if test="elencoProposte.size() == 1">a</s:if><s:else>e</s:else></h4>                  --%>

<div id="simple-pagination-proposte">
	
<table class="table tab_left"> 
			<thead>
              <tr>
                <th scope="col">Numero</th>
                <th scope="col">Descrizione</th>
                <th scope="col">Stato</th>
                <th scope="col">Capitolo</th>
 				<th class="span2 tab_Right">&nbsp;</th>			
               </tr>								
            </thead>
       		<tbody>
		<s:iterator value="elencoProposte">
		<tr>
		
		<td class="numero">  	<s:property value="numero" />      </td>
			
			<td  id="descrizione" class="descrizione"><a  href="<s:url 
				action="%{'propostaPreliminare' + capitalize(tipoProposta)}"><s:param name="puid" >
					<s:property value="uid" /></s:param>
		</s:url>"><s:property value="%{descrizioneCompleta}" /></a></td>
				
						<td >  	<s:property value="stato.descrizione" /> </td>
				
			                <td>
			                <s:if test="capitolo != null">
			                  	<s:property value="%{capitolo.codiceCompleto}" />
			                </s:if>
			                <s:else> - </s:else>
			                </td>
			
					<td class="tab_Right buttons-azioni">
			<div class="btn-group ">
				<button class="btn dropdown-toggle" data-toggle="dropdown">Azioni<span class="caret"></span></button>
				<ul class="dropdown-menu pull-right">
				
 				<s:if test="stato.codice eq 'APERTA'" > 
					<li><a class="azioneInvia"  href="#" 
							data-toggle="modal" 
							data-target="#modalInvia"
							data-uid-proposta="<s:property value="uid" />"
						 >visto settore</a></li>
				</s:if>
				
 				<s:if test="azioneDirigente" > 
 				<s:if test="stato.codice in {'APERTA', 'INVIATA'}" > 
					<li><a class="azioneApprova"  href="#" 
							data-toggle="modal" 
							data-target="#modalApprova"
							data-uid-proposta="<s:property value="uid" />"
						 >visto dirigente</a></li>
				</s:if>
				</s:if>
				
				 <s:if test="stato.codice eq 'APERTA'" > 
					<li><a class="azioneElimina"  href="#" 
							data-toggle="modal" 
							data-target="#modalElimina"
							data-uid-proposta="<s:property value="uid" />"
						 >elimina</a></li>
				</s:if>
				
				
				</ul>
			</div>
		</td>
			
			
				
		</tr>
		</s:iterator>
		</tbody>
	</table>


	<div class="Border_line margin-medium"></div>

<div class="simple-pagination-proposte-navigation">
		<div class="simple-pagination-proposte-first"></div>
		<div class="simple-pagination-proposte-previous"></div>
		<div class="simple-pagination-proposte-page-numbers"></div>
		<div class="simple-pagination-proposte-next"></div>
		<div class="simple-pagination-proposte-last"></div>
</div>

		</div>
</s:else>

							</div>
							

						</div>
				
				
				<fieldset class="form-horizontal margin-medium minHeight">


				<p>
					<a class="btn btn-secondary" href="/siacboapp/gestionePropostePreliminari<s:property value="capitalize(tipoProposta)" />.do">indietro</a>	
					<span class="pull-right">
<a id="inserisciProposta" class="btn btn-primary" href="/siacboapp/propostaPreliminare<s:property value="capitalize(tipoProposta)" />.do">inserisci proposta</a>					</span>
					
				</p>

			</fieldset>

		</div>
		  
	</div>	 
</div>	
	

<!-- Modal  -->
	<div id="modalElimina" class="modal hide fade modal-azione" tabindex="-1" role="dialog" aria-labelledby="msgAnnullaLabel" aria-hidden="true">
	  <div class="modal-body">
		<div class="alert alert-error">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <p><strong>Attenzione!</strong></p>
		  <p>Stai per eliminare la proposta selezionata: sei sicuro di voler proseguire?</p>
		</div>
	  </div>
	  <div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">no, indietro</button>
		<button class="btn btn-primary conferma-azione" data-dismiss="modal" aria-hidden="true">si, prosegui</button>
		<a  class="anchor-azione" href='<s:url action="%{'elencoPropostePreliminari' + capitalize(tipoProposta) +'_eliminaProposta'}" />?puid=0'><span class="hide"></span></a>
	  </div>
	</div>  
<!-- end modal  --> 
	
	
<!-- Modal  -->
	<div id="modalInvia" class="modal hide fade modal-azione" tabindex="-1" role="dialog" aria-labelledby="msgAnnullaLabel" aria-hidden="true">
	  <div class="modal-body">
		<div class="alert alert-error">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <p><strong>Attenzione!</strong></p>
		  <p>Stai per inviare la proposta selezionata: sei sicuro di voler proseguire?</p>
		</div>
	  </div>
	  <div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">no, indietro</button>
		<button class="btn btn-primary conferma-azione" data-dismiss="modal" aria-hidden="true">si, prosegui</button>
		<a  class="anchor-azione" href='<s:url action="%{'elencoPropostePreliminari' + capitalize(tipoProposta) +'_inviaProposta'}" />?puid=0'><span class="hide"></span></a>
	  </div>
	</div>  
<!-- end modal  --> 
	
	
	
<!-- Modal  -->
	<div id="modalApprova" class="modal hide fade modal-azione" tabindex="-1" role="dialog" aria-labelledby="msgAnnullaLabel" aria-hidden="true">
	  <div class="modal-body">
		<div class="alert alert-error">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <p><strong>Attenzione!</strong></p>
		  <p>Stai per approvare la proposta selezionata: sei sicuro di voler proseguire?</p>
		</div>
	  </div>
	  <div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">no, indietro</button>
		<button class="btn btn-primary conferma-azione" data-dismiss="modal" aria-hidden="true">si, prosegui</button>
		<!-- <a  class="anchor-azione" href='<s:url method="approvaProposta" />?puid=0'><span class="hide"></span></a>-->
		<a  class="anchor-azione" href='<s:url action="%{'elencoPropostePreliminari' + capitalize(tipoProposta) +'_approvaProposta'}" />?puid=0'><span class="hide"></span></a>
	  </div>
	</div>  
<!-- end modal  --> 
	
	
	
	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
			
	
		
	
<%-- Eventuali altre librerie JavaScript --%>

<s:include value="/jsp/include/navigation.jsp" />
	

<script type="text/javascript" src="${jspath}propostePreliminari/elencoPropostePreliminari.js" charset="utf-8"></script>

	
	
	
		




	
</body>
</html>