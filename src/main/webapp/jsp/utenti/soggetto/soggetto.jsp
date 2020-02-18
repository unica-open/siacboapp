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


<%-- Inclusione librerie JavaScript --%>
<s:include value="/jsp/include/javascript.jsp" />
<%-- Eventuali altre librerie JavaScript --%>

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
				<li><a href="/siacboapp/homeUtenti.do">Backoffice</a><span
					class="divider">&gt;</span></li>
				<li><a href="/siacboapp/soggetto/elencoSoggetti.do">Gestione
						soggetti</a><span
					class="divider">&gt;</span></li>
				<li class="active">
					<s:if test="uid eq null">Inserisci</s:if>
					<s:else>Aggiorna</s:else>
				    	soggetto </li>
			</ul>
		</div>
	</div>


	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">




				<h4 class="titleBO">
					<i class="icon-subject"></i>Gestione soggetti
				</h4>

				<div class="Border_line"></div>

				<!--  aggiunta  fieldset che contiene tutto  -->
				<fieldset class="form-horizontal margin-medium minHeight">



					<s:form id="form" action="soggetto">



						<s:hidden name="soggetto.uid" />




						<div class="control-group">
							<label class="control-label">Codice</label>
							<div class="controls">
								<s:textfield name="soggetto.codice" cssClass="span3" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Descrizione</label>
							<div class="controls">
								<s:textfield cssClass="span10" name="soggetto.descrizione" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Codice fiscale</label>
							<div class="controls">
								<s:textfield cssClass="span6" name="soggetto.codiceFiscale" />
							</div>
						</div>

					
						<div class="control-group">
							<label class="control-label">Ruolo</label>
							<div class="controls">
								<s:select cssClass="span10 chosen-select" name="soggetto.ruoloId" headerKey=""
									headerValue="" list="elencoRuoli" listKey="uid"
									listValue="descrizione" />
							</div>
						</div>
<%-- 	
						<div class="control-group">
							<label class="control-label">Ambito</label>
							<div class="controls">
								<s:select cssClass="span10" name="soggetto.ambitoId"
									headerKey="" headerValue="" list="elencoAmbiti" listKey="uid"
									listValue="codice" />
							</div>
						</div>
					--%>

						<div class="Border_line"></div>




						<p>

							<a class="btn btn-secondary"
								href="/siacboapp/soggetto/elencoSoggetti.do">indietro</a> <span
								class="pull-right"> <s:if test="soggetto == null">
									<s:submit cssClass="btn btn-primary" method="create"
										value="inserisci soggetto" />
								</s:if> <s:else>
									<s:submit cssClass="btn btn-primary" method="update"
										value="aggiorna soggetto" />
								</s:else>
							</span>
						</p>









					</s:form>
				</fieldset>







			</div>
		</div>
	</div>

	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	

</body>
</html>