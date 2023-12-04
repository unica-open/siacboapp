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
				<li><a href="/siacboapp/azioni/elencoAzioni.do">Gestione
						azioni</a><span
					class="divider">&gt;</span></li>
			<li class="active">
				<s:if test="uid eq null">Inserisci</s:if>
				<s:else>Aggiorna</s:else>
			    	azione</li>	
			</ul>
		</div>
	</div>




	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">

				<h4 class="titleBO">
					<i class="icon-tasks"></i>Gestione Azioni
				</h4>
				<div class="Border_line"></div>

				<fieldset class="form-horizontal margin-medium minHeight">

					<s:form action="azione">

						<s:hidden name="azione.uid" />






						<div class="control-group">
							<label class="control-label">Codice</label>
							<div class="controls">
								<s:textfield name="azione.codice" cssClass="span3" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Descrizione</label>
							<div class="controls">
								<s:textfield name="azione.descrizione" cssClass="span10" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Tipo</label>
							<div class="controls">
								<s:select cssClass="span6" name="azione.tipoAzioneId"
									headerKey="" headerValue="" list="elencoTipiAzione"
									listKey="uid" listValue="codice" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Url applicazione</label>
							<div class="controls">
								<s:textfield name="azione.urlApplicazione" cssClass="span6" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Sezione</label>
							<div class="controls">
								<s:select cssClass="span6" name="azione.gruppoAzioniId"
									headerKey="" headerValue="" list="elencoGruppiAzioni"
									listKey="uid" listValue="codice" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Ruoli</label>
							<div class="controls">
								<s:select multiple="true" cssClass="span6 chosen-select" size="3"
									name="azione.ruoliOpId" headerKey="" headerValue=""
									list="elencoRuoliOp" listKey="uid" listValue="codice" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Processo</label>
							<div class="controls">
								<s:textfield name="azione.nomeProcesso" cssClass="span6" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Task</label>
							<div class="controls">
								<s:textfield name="azione.nomeTask" cssClass="span6" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Verifica UO</label>
							<div class="controls">


									<s:checkbox name="azione.verificaUo"  />

							</div>
						</div>



						<div class="Border_line"></div>

						<p>

							<a class="btn btn-secondary"
								href="/siacboapp/azioni/elencoAzioni.do" class="btn">indietro</a>

							<span class="pull-right"> 
								<s:if test="azione == null">
									<!-- SIAC-8699 -->
									<s:submit cssClass="btn btn-primary" action="azione_create"	value="inserisci azione" />
								</s:if> 
								<s:else>
									<!-- SIAC-8699 -->
									<s:submit cssClass="btn btn-primary" action="azione_update"	value="aggiorna azione" />
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

	
<s:include value="/jsp/include/jquery.chosen.jsp" />


</body>
</html>