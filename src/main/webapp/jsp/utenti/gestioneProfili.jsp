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
				<li><a href="<s:property value="#urlCruscotto" />">Home</a></li>
			</ul>
		</div>
	</div>

	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">


				<form class="form-horizontal">
					<h4>Backoffice utenti</h4>

					<div class="Border_line  margin-medium"></div>
					<br>

					<div class="content-backoffice">
						<ul class="titles-container margin-large">
							<li><a href="soggetto/elencoSoggetti.do" class="title-btn"><i
									class="icon-subject gest-soggetti"></i><span>gestione
										soggetti</span></a></li>
							<li><a href="account/elencoAccount.do" class="title-btn"><i
									class="icon-user gest-account"></i><span>gestione
										account</span></a></li>
							<li><a href="gruppo/elencoGruppi.do" class="title-btn"><i
									class="icon-group gest-gruppi"></i><span>gestione gruppi</span></a></li>
							<li><a href="ruoloOp/elencoRuoliOp.do" class="title-btn"><i
									class="icon-group-cube gest-ruoli"></i><span>gestione
										ruoli</span></a></li>
							<li><a href="gruppoAzioni/elencoGruppiAzioni.do"
								class="title-btn"><i class="icon-tasks gestGruppiAzioni"></i><span>gestione
										gruppi<br> azioni
								</span></a></li>
							<li><a href="azione/elencoAzioni.do" class="title-btn"><i
									class="icon-edit gestAzioni"></i><span>gestione azioni</span></a></li>
						</ul>
					</div>

					<div class="clear"></div>

					<div class="Border_line  margin-large"></div>

					<p class="margin-medium">
						<a class="btn btn-secondary" href="/siacboapp/homeUtenti.do">indietro</a>
					</p>



				</form>

			</div>
		</div>
	</div>

	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
	

</body>
</html>