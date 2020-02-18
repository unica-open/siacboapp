<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.csi.it/taglibs/remincl-1.0" prefix="r"%>
<%-- Inclusione head e CSS --%>
<s:include value="/jsp/include/head.jsp" />

<style>
.chosen-container {
	width: 35% !important;
}
</style>

</head>
<body>
	<%-- Inclusione header --%>
	<s:include value="/jsp/include/header.jsp" />
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="<s:property value="#urlCruscotto" />">Home</a> <span class="divider">&gt;</span></li>
				<li><a href="gestioneLimiteImpegnabile.do">Gestione limite impegnabile</a> <span class="divider">&gt;</span></li>
				<li><a href="risultatiRicercaLimiteImpegnabile.do">Capitoli limite impegnabile</a> <span class="divider">&gt;</span></li>
				<li class="active">Importi limite impegnabile</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">
				<s:form action="importiLimiteImpegnabile" method="post" cssClass="form-horizontal" id="form">
					<s:hidden name="cuid" />
					<s:hidden name="anno" />
					<s:if test="hasActionMessages()">
						<%-- Messaggio di WARNING --%>
						<div class="alert alert-warning">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Attenzione!!</strong><br>
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
					<h3>Importi capitolo limite impegnabile</h3>

					<div class="step-content">
						<div class="step-pane active" id="step1">

							<fieldset class="form-horizontal margin-large">

								<div class="control-group">
									<label class="control-label" for="tipoFile">Importo anno <s:property value="anno" />
									</label>
									<div class="controls">
										<s:textfield cssClass="lbTextSmall span2 importoAnno" id="codFile" name="importoAnno" />
										<s:hidden name="idImportoAnno" />
									</div>
								</div>

								<div class="control-group">
									<label class="control-label" for="tipoFile">Importo anno <s:property value="%{anno + 1}" />
									</label>
									<div class="controls">
										<s:textfield cssClass="lbTextSmall span2 importoAnno" id="codFile" name="importoAnno1" />
										<s:hidden name="idImportoAnno1" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="tipoFile">Importo anno <s:property value="%{anno + 2}" />
									</label>
									<div class="controls">
										<s:textfield cssClass="lbTextSmall span2 importoAnno" id="codFile" name="importoAnno2" />
										<s:hidden name="idImportoAnno2" />
									</div>
								</div>
							</fieldset>
						</div>
					</div>

					<p class="margin-medium">
						<a class="btn btn-secondary" href="risultatiRicercaLimiteImpegnabile.do">indietro</a>
						<a class="btn btn-secondary" href='importiLimiteImpegnabile.do?cuid=<s:property value="cuid" />'>annulla</a>
						<s:submit cssClass="btn btn-primary pull-right" method="update" value="aggiorna" />
					</p>
				</s:form>
			</div>
		</div>
	</div>
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />

<s:include value="/jsp/include/javascript.jsp" />
	

<script type="text/javascript" src="${jspath}limiteImpegnabile/importiLimiteImpegnabile.js" charset="utf-8"></script>

</body>
</html>