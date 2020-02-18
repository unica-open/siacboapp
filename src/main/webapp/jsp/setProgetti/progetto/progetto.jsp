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
				<li><a href="<s:url action="elencoProgetti" />">Dettaglio set progetti</a><span
					class="divider">&gt;</span></li>
			<li class="active">
				Dettaglio progetto</li>	
			</ul>
		</div>
	</div>




	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">




			<s:if test="hasActionMessages()">
						<%-- Messaggio di WARNING --%>
						<div class="alert alert-success">
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




				<h4 class="titleBO">
					<i class="icon-tasks"></i>Dettaglio progetto <s:property value="programma.codice"/> 
				</h4>
				<div class="Border_line"></div>

				<fieldset class="form-horizontal margin-medium minHeight">

					<s:form action="progetto">

						<s:hidden name="uid" />
						<s:hidden name="progetto.uid" />
						<s:hidden name="setProgettiId" />




					<s:radio  cssClass="cronoprogramma" list="elencoCronoprogrammi" listKey="uid" listValue="codice" name="progetto.cronoprogramma.uid" />
					
					<s:if test="progetto.programma.esisteCronoprogrammaGestione"> 
					
					<label class="inline" for="usaGestione">
						<s:checkbox id="usaGestione" name="progetto.usaGestione"    /> usa gestione 
					</label>
</s:if>
						<div class="Border_line"></div>

						<p>

							<a class="btn btn-secondary"
								href="<s:url action="elencoProgetti" ><s:param name="setProgettiId" value="setProgettiId" /></s:url>" class="btn">indietro</a>

							<span class="pull-right">  
									<s:submit cssClass="btn btn-primary" method="update"
										value="aggiorna progetto" />
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



<script type="text/javascript" src="${jspath}setProgetti/progetto/progetto.js" charset="utf-8"></script>


</body>
</html>