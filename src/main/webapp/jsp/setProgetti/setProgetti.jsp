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
				<li><a href="<s:url action="elencoSetProgetti" />">Gestione set progetti</a><span
					class="divider">&gt;</span></li>
			<li class="active">
				<s:if test="uid eq null">Inserisci</s:if>
				<s:else>Aggiorna</s:else>
			    	set progetti</li>	
			</ul>
		</div>
	</div>



	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">

				<h4 class="titleBO">
					<i class="icon-tasks"></i>Gestione set progetti
				</h4>
				<div class="Border_line"></div>

				<fieldset class="form-horizontal margin-medium minHeight">

					<s:form action="setProgetti">



		<s:if test="hasActionMessages()">
						<%-- Messaggio di WARNING --%>
						<div class="alert alert-warning">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Attenzione!!</strong><br>
							<ul>
								<s:iterator value="messaggi">
									<li><s:property value="codice"/> - <s:property value="descrizione"/> </li>
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
									<li><s:property value="testo"/> </li>
								</s:iterator>
							</ul>
						</div>
			</s:if>
			
			
			
						<s:hidden name="setProgetti.uid" />






						<!-- tolti tutti i br ed impaginato  ho aggiunto le classi a tutti gli input e select -->


						<div class="control-group">
							<label class="control-label">Codice</label>
							<div class="controls">
								<s:textfield cssClass="span2" name="setProgetti.codice" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Descrizione</label>
							<div class="controls">
								<s:textfield name="setProgetti.descrizione" cssClass="span10" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Preimposta crono con</label>
							<div class="controls">
								<s:select cssClass="span6"  name="initTipoCronoprogramma"
											       headerKey="" headerValue=""
											       list="#{'PREV':'previsione','GEST':'gestione'}" />	
							</div>
						</div>




						<div class="Border_line"></div>

						<p>

												<a class="btn btn-secondary" href="<s:url action="elencoSetProgetti" />">indietro</a>	


							<span class="pull-right"> <s:if
									test="accountEntity == null">
									<s:submit cssClass="btn btn-primary" action="setProgetti_create"
										value="inserisci set" />
								</s:if> <s:else>
									<s:submit cssClass="btn btn-primary" action="setProgetti_update"
										value="aggiorna set" />
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