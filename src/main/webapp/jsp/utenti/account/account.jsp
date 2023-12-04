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
				<li><a href="/siacboapp/account/elencoAccount.do">Gestione
						account</a><span
					class="divider">&gt;</span></li>
			<li class="active">
				<s:if test="uid eq null">Inserisci</s:if>
				<s:else>Aggiorna</s:else>
			    	account</li>	
			</ul>
		</div>
	</div>



	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">

				<h4 class="titleBO">
					<i class="icon-tasks"></i>Gestione Account
				</h4>
				<div class="Border_line"></div>

				<fieldset class="form-horizontal margin-medium minHeight">

					<s:form action="account">

						<s:hidden name="accountEntity.uid" />






						<!-- tolti tutti i br ed impaginato  ho aggiunto le classi a tutti gli input e select -->


						<div class="control-group">
							<label class="control-label">Codice</label>
							<div class="controls">
								<s:textfield cssClass="span2" name="accountEntity.codice" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Nome</label>
							<div class="controls">
								<s:textfield name="accountEntity.nome" cssClass="span6" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Descrizione</label>
							<div class="controls">
								<s:textfield name="accountEntity.descrizione" cssClass="span10" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Email</label>
							<div class="controls">
								<s:textfield name="accountEntity.email" cssClass="span6" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Soggetto</label>
							<div class="controls">
								<s:select cssClass="span6 chosen-select" name="accountEntity.soggettoRuoloId"
									headerKey="" headerValue="" list="elencoSoggettiRuoli"
									listKey="uid"
									listValue="%{soggetto.codiceFiscale + ' - ' + soggetto.codice}" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Gruppo</label>
							<div class="controls">
								<s:select cssClass="span4 chosen-select" name="accountEntity.gruppiId" multiple="true" 
									headerKey="" headerValue="" list="elencoGruppi" listKey="uid"
									listValue="codice" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">SAC</label>
							<div class="controls">
							
								<ul id="treeStruttAmm" class="ztree"></ul>
			
							<div id="struttureAmministrativeContabiliId">
								<s:iterator value="accountEntity.struttureAmministrativeContabiliId" var="obj">
									<s:hidden name="accountEntity.struttureAmministrativeContabiliId" value="%{#obj}" />
								</s:iterator>									
							</div>
							
<%-- 								<s:select multiple="true" name="accountEntity.struttureAmministrativeContabiliId" --%>
<%-- 										list="elencoStruttureAmministrativeContabili" listKey="uid" --%>
<%-- 										cssClass="span4 chosen-select" listValue="%{codiceGerarchico + ' - ' + descrizione}" /> --%>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Cassa economale</label>
							<div class="controls">
								<s:select multiple="true" name="accountEntity.casseEconomaliId"
										list="elencoCasseEconomali" listKey="uid" 
										cssClass="span4 chosen-select" listValue="%{codice + ' - ' + descrizione}" /> 
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Ruoli</label>
							<div class="controls">
								<s:select cssClass="span4 chosen-select" multiple="true" size="3"
									name="accountEntity.ruoliOpId" headerKey="" headerValue=""
									list="elencoRuoliOp" listKey="uid" listValue="codice" />
							</div>
						</div>



						<div class="Border_line"></div>

						<p>

							<a class="btn btn-secondary"
								href="/siacboapp/account/elencoAccount.do" class="btn">indietro</a>

							<span class="pull-right"> 
								<s:if
									test="accountEntity == null">
									<!-- SIAC-8699 -->
									<s:submit cssClass="btn btn-primary" action="account_create"  value="inserisci account" />
								</s:if> 
								<s:else>
									<!-- SIAC-8699 -->
									<s:submit cssClass="btn btn-primary" action="account_update"  value="aggiorna account" />
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





<script>
	var elencoStruttureAmministrativoContabiliJson = <s:property escapeHtml="false" value="elencoStruttureAmministrativoContabiliJson" />;
</script>
	
<script type="text/javascript" src="${jspath}utenti/account/account.js" charset="utf-8"></script>

	
	
	
	







</body>
</html>