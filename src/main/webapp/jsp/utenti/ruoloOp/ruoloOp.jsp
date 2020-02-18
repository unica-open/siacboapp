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
				<li><a href="/siacboapp/ruoloOp/elencoRuoliOp.do">Gestione
						ruoli</a><span
					class="divider">&gt;</span></li>
				<li class="active">
					<s:if test="uid eq null">Inserisci</s:if>
					<s:else>Aggiorna</s:else>
				    	ruolo </li>						
			</ul>
		</div>
	</div>




	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">

				<h4 class="titleBO">
					<i class="icon-tasks"></i>Gestione Ruoli
				</h4>
				<div class="Border_line"></div>

				<fieldset class="form-horizontal margin-medium minHeight">


					<s:form action="ruoloOp">

						<s:hidden name="ruoloOp.uid" />

						<div class="control-group">
							<label class="control-label">Codice</label>
							<div class="controls">
								<s:textfield cssClass="span3" name="ruoloOp.codice" />
							</div>
						</div>




						<div class="control-group">
							<label class="control-label">Descrizione</label>
							<div class="controls">
								<s:textfield cssClass="span10" name="ruoloOp.descrizione" />
							</div>
						</div>


						<div class="control-group">
							<label class="control-label">Azioni</label>
							<div class="controls">
								<%-- http://harvesthq.github.io/chosen/#multiple-select --%>
								<s:select cssClass="span10 chosen-select" multiple="true" size="10"
									name="ruoloOp.azioniId" headerKey="" headerValue=""
									list="elencoAzioni" listKey="uid"
									listValue="%{codice + ' - ' + descrizione}" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Anni di bilancio</label>
							<div class="controls">
								<%-- http://harvesthq.github.io/chosen/#multiple-select --%>
								<s:select cssClass="span10 chosen-select" multiple="true" size="10"
									name="ruoloOp.bilanciId" headerKey="" headerValue=""
									list="elencoBilanci" listKey="uid"
									listValue="%{periodo.anno + ' - ' + descrizione}" />
							</div>
						</div>

						<br />


						<div class="Border_line"></div>


						<p>
							<a class="btn btn-secondary"
								href="/siacboapp/ruoloOp/elencoRuoliOp.do">indietro</a> <span
								class="pull-right"> <s:if test="ruoloOp == null">
									<s:submit cssClass="btn btn-primary" method="create"
										value="inserisci ruolo" />
								</s:if> <s:else>
									<s:submit cssClass="btn btn-primary" method="update"
										value="aggiorna ruolo" />
								</s:else>
							</span>
						</p>

					</s:form>
				</fieldset>

				<br />
			</div>
		</div>
	</div>

	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />

	

</body>
</html>