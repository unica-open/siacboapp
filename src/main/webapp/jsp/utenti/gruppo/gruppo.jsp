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
				<li><a href="/siacboapp/gruppi/elencoGruppi.do">Gestione
						gruppi</a><span
					class="divider">&gt;</span></li>
			<li class="active">
				<s:if test="uid eq null">Inserisci</s:if>
				<s:else>Aggiorna</s:else>
			    	gruppo</li>						
			</ul>
		</div>
	</div>



	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">

				<h4 class="titleBO">
					<i class="icon-tasks"></i>Gestione Gruppi
				</h4>
				<div class="Border_line"></div>

				<fieldset class="form-horizontal margin-medium minHeight">

					<s:form action="gruppo">

						<s:hidden name="gruppo.uid" />



						<div class="control-group">
							<label class="control-label">Codice</label>
							<div class="controls">
								<s:textfield name="gruppo.codice" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Descrizione</label>
							<div class="controls">
								<s:textfield name="gruppo.descrizione" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Ruoli</label>
							<div class="controls">
								<s:select multiple="true" size="3" name="gruppo.ruoliOpId" cssClass="chosen-select"
									headerKey="" headerValue="" list="elencoRuoliOp" listKey="uid"
									listValue="codice" />
								
							</div>
						</div>



						<div class="Border_line"></div>

						<p>

							<a class="btn btn-secondary"
								href="/siacboapp/gruppi/elencoGruppi.do" class="btn">indietro</a>

							<span class="pull-right"> 
								<s:if test="gruppo == null">
									<!-- SIAC-8699 -->										
									<s:submit cssClass="btn btn-primary" action="gruppo_create" value="inserisci gruppo" />
								</s:if> 
								<s:else>
									<!-- SIAC-8699 -->
									<s:submit cssClass="btn btn-primary" action="gruppo_update" value="aggiorna gruppo" />
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