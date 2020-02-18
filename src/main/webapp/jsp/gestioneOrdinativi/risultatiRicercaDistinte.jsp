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
				<li><a href="ricercaDistinte.do">Ricerca distinte</a>
					<span class="divider">&gt;</span></li>
		        <li class="active">Elenco distinte</li>
			</ul>
		</div>
	</div>

 


<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">    
    
<s:form action="%{#context['struts.actionMapping'].name}" method="post" id="form">

<s:hidden name="codiceTipoDistinte" id="codiceTipoDistinte" />

					<s:if test="hasActionMessages()">
						<%-- Messaggio di WARNING --%>
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<ul>
								<s:actionmessage />
							</ul>
						</div>
					</s:if>


					<s:if test="hasActionErrors()">
						<%-- Messaggio di ERROR --%>
						<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Attenzione!!</strong><br>
					<ul>
						<s:actionerror />
					</ul>
			</div>
			</s:if>


		
		<h3>
		        Ricerca distinte</h3>   
		        
		        
	<s:if test="not elencoDistinte.empty">	        
		<fieldset class="form-horizontal">

		<h4><span class="num_result"><s:property value="elencoDistinte.size()"/></span> 
		distint<s:if test="elencoDistinte.size() == 1">a</s:if><s:else>e</s:else></h4>                 
		
		<div id="simple-pagination">
		
			<table class="table table-hover tab_left"  summary="...." >
				<thead>
					<tr>
						<th>Anno</th>
						<th>Data</th>
						<th>Numero</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				
				
				<s:iterator value="elencoDistinte" status="st" var="ord">
	<tr>
		<td class="ord-tipo"><s:property value="#ord.anno" /></td>
		<td class="ord-codice"><s:date name="#ord.dataCreazione" format="dd/MM/yyyy" /></td>
		<td><s:property value="#ord.numero" /></td>
		<td >
		
<!-- 						<div class="btn-group"> -->
<%-- 										<button class="btn dropdown-toggle" data-toggle="dropdown">Azioni<span class="caret"></span></button> --%>
<!-- 										<ul class="dropdown-menu pull-right"> -->
<!-- 											<li><a href="#modalConsulta" data-toggle="modal">consulta</a></li> -->
<!-- 										</ul> -->
<!-- 									</div> -->

						<button  class="btn modalConsultaButton" data-toggle="modal" data-target="#modalConsulta"  
							data-uid-distinta="<s:property value="#ord.uid" />"
							data-numero-distinta="<s:property value="#ord.numero" />"
						>consulta</button>
		
		
		
		
<%-- 		<s:url  --%>
<%-- 								action="consultaDistinta"><s:param name="uid" > --%>
<%-- 									<s:property value="uid" /></s:param>  --%>
<%-- 						</s:url> --%>
		</td>

	</tr>
	
					
				</s:iterator>	
				

				</tbody>
				<tfoot>
				</tfoot>

			</table>
			
			

		
	<div class="Border_line margin-medium"></div>

	<div class="simple-pagination-navigation">
			<div class="simple-pagination-first"></div>
			<div class="simple-pagination-previous"></div>
			<div class="simple-pagination-page-numbers"></div>
			<div class="simple-pagination-next"></div>
			<div class="simple-pagination-last"></div>
	</div>
	
			





		</fieldset>
	</s:if>
	<s:else>
		Nessuna distinta presente secondo i criteri di ricerca scelti
	</s:else>
		         <p class="margin-medium">
			  <a class="btn btn-secondary" href="ricercaDistinte.do">indietro</a>   
		  </p> 
          
          
          
          
          
          
          
          
  <div id="modalConsulta" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="guidaProvLabel" aria-hidden="false" >
  
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="nostep-pane">Consulta distinta</h4>	

	</div>
    <div class="modal-body">
  		             
	
	
	
	
	
    </div>   
	<div class="modal-footer">
		 <abutton class="btn btn-secondary" data-dismiss="modal" >indietro</button>     
	</div>

</div>  
</div>  	

</div>        
          
          
          
          
          
          
          
          
          
          
          
      </s:form>
    </div>
  </div>	 
</div>	

	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
<s:include value="/jsp/gestioneFile/include/javascript.jsp" />
	
<r:include url="/ris/servizi/siac/include/myCheckbox.html" resourceProvider="rp"/>
	
	
<s:include value="/jsp/include/jquery.simple.pagination.jsp" />
	
	
	<script type="text/javascript" src="${jspath}gestioneOrdinativi/risultatiRicercaDistinte.js" charset="utf-8"></script>	
	
	
	
</body>
</html>