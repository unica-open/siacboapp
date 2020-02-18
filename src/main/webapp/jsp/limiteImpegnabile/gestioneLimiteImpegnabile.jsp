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
				<li class="active">Gestione limite impegnabile</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">
				<s:form action="gestioneLimiteImpegnabile" method="post" cssClass="form-horizontal" id="form">
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

					<h3>Gestione limite impegnabile</h3>
					<div class="step-content">
						<div class="step-pane active" id="step1">

							<h4>Dati</h4>
							<p>&Egrave; necessario inserire almeno il numero del capitolo oppure la struttura amministrativa responsabile</p>
							<fieldset class="form-horizontal margin-large">

								<div class="control-group">
									<label class="control-label" for="tipoFile">Numero capitolo *</label>
									<div class="controls">
										<s:textfield cssClass="lbTextSmall span2" id="codFile" name="criteri.numeroCapitolo" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="tipoFile">Numero articolo</label>
									<div class="controls">
										<s:textfield cssClass="lbTextSmall span2" id="codFile" name="criteri.numeroArticolo" />
									</div>
								</div>
								<s:if test="gestioneUEB">
									<div class="control-group">
										<label class="control-label" for="tipoFile">Numero UEB</label>
										<div class="controls">
											<s:textfield cssClass="lbTextSmall span2" id="codFile" name="criteri.numeroUeb" />
										</div>
									</div>
								</s:if><s:else>
									<s:hidden name="criteri.numeroUeb" value="1" />
								</s:else>

								<div class="control-group">
									<s:hidden id="idStrutturaAmministrativoContabile" name="criteri.idStrutturaAmministrativoContabile" />
									<label for="bottoneSAC" class="control-label">Struttura Amministrativa Responsabile * </label>
									<div class="controls">
										<a role="button" class="btn" id="bottoneSAC" data-toggle="modal" href="#struttAmm"> Seleziona la Struttura amministrativa </a>
										<div id="struttAmm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
												<h3 id="myModalLabel2">Struttura Amministrativa Responsabile</h3>
											</div>
											<div class="modal-body">
												<ul id="treeStruttAmm" class="ztree"></ul>
											</div>
											<div class="modal-footer">
												<button id="deselezionaStrutturaAmministrativoContabile" class="btn">Deseleziona</button>
												<button id="confermaStrutturaAmministrativoContabile" type="button" class="btn btn-primary pull-right" data-dismiss="modal" aria-hidden="true">Conferma</button>
											</div>
										</div>
										&nbsp; <span id="SPAN_StrutturaAmministrativoContabile">Nessuna struttura amministrativa responsabile selezionata</span>
									</div>
								</div>
							</fieldset>
						</div>
					</div>

					<p class="margin-medium">
						<a class="btn btn-secondary" href="<s:property value="#urlCruscotto" />">indietro</a>
						<a class="btn btn-secondary" href="gestioneLimiteImpegnabile.do">annulla</a>
						
						<s:submit id="cerca" cssClass="btn btn-primary pull-right" method="cerca" value="cerca" />
					</p>
				</s:form>
			</div>
		</div>
	</div>

	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />

	<script type="text/javascript">
var elencoStruttureAmministrativoContabiliJson = <s:property escapeHtml="false" value="elencoStruttureAmministrativoContabiliJson" />; 
	</script>
	

<script type="text/javascript" src="${jspath}limiteImpegnabile/gestioneLimiteImpegnabile.js" charset="utf-8"></script>
	

</body>
</html>