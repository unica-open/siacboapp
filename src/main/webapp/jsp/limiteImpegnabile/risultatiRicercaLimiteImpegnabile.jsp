<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<li>
					<a href="<s:property value="#urlCruscotto" />">Home</a>
					<span class="divider">&gt;</span>
				</li>
				<li>
					<a href="gestioneLimiteImpegnabile.do">Gestione limite impegnabile</a> <span class="divider">&gt;</span>
				</li>
				<li class="active">Capitoli limite impegnabile</li>
			</ul>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">
				<s:form action="%{#context['struts.actionMapping'].name}" method="post" id="form">
					<s:hidden name="codiceTipoFlussi" id="codiceTipoFlussi" />
					<s:if test="hasActionMessages()">
						<%-- Messaggio di WARNING --%>
						<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<ul>
								<s:iterator value="messaggi">
									<li><s:property value="codice" /> - <s:property value="descrizione" /></li>
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
									<li><s:property value="testo" /></li>
								</s:iterator>
							</ul>
						</div>
					</s:if>
					<h3>Capitoli limite impegnabile</h3>

					<s:if test="not elencoCapitoliLimiteImpegnabile.empty">
						<fieldset class="form-horizontal">

							<h4>
								<span class="num_result"><s:property value="elencoCapitoliLimiteImpegnabile.size()" /></span> 
								capitol<s:if test="elencoCapitoliLimiteImpegnabile.size() == 1">o</s:if><s:else>i</s:else>
							</h4>

							<div id="simple-pagination">

								<table class="table table-hover tab_left" summary="....">
									<thead>
										<tr>
											<th>Capitolo</th>
											<th>Struttura amministrativa responsabile</th>
											<th>Descrizione</th>
											<th>&nbsp;</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="elencoCapitoliLimiteImpegnabile" status="st" var="ord">
											<tr>
												<td><s:property value="#ord.codiceCompleto" /></td>
												<td><s:property value="#ord.classificatori.iterator().next.codiceDescrizione" /></td>
												<td><s:property value="#ord.descrizioneCompleta" /></td>
												<td><a class="btn btn-secondary" href='importiLimiteImpegnabile.do?cuid=<s:property value="#ord.uid" />'>aggiorna</a></td>
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
							</div>
						</fieldset>
					</s:if>
					<s:else>Nessun capitolo presente secondo i criteri di ricerca scelti</s:else>
					<p class="margin-medium">
						<a class="btn btn-secondary" href="gestioneLimiteImpegnabile.do">indietro</a>
					</p>
				</s:form>
			</div>
		</div>
	</div>


	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	
	<r:include url="/ris/servizi/siac/include/myCheckbox.html" resourceProvider="rp" />
	<s:include value="/jsp/include/jquery.simple.pagination.jsp" />


<script type="text/javascript" src="${jspath}limiteImpegnabile/risultatiRicercaLimiteImpegnabile.js" charset="utf-8"></script>

</body>
</html>