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


<style type="text/css">
td.value input {
	width: 100%;
	padding: 2px 0px;
	text-align: right;
}
</style>

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

			<li class="active">Configura indicatori analitici d'entrata</li>	
			</ul>
		</div>
	</div>




	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">




				<fieldset class="form-horizontal margin-medium minHeight">
									
<table class="table tab_left">
	<thead>
		<tr>
            <th rowspan="2" style="vertical-align:middle; width: 80px;">Titolo</th>
			<th rowspan="2" style="vertical-align:middle; width: 200px;">Tipologia</th>
			<th style="text-align: center;" colspan="3">Accertamenti</th>
			<th style="text-align: center;" colspan="3">Riscossioni</th>
		</tr>
    
		<tr>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno - 3}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno - 2}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno - 1}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno - 3}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno - 2}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno - 1}"/></th>
		</tr>
	</thead>

	<tbody id="indicatori" class="analitici">
	<s:iterator value="elencoConfIndicatoriEntrata" status="st" var="ord">
		<tr data-uid="<s:property value="#ord.uid"/>">
			<td style="vertical-align: middle;" data-classif-id="<s:property value="#ord.titolo.uid"/>" class="classif">TITOLO <s:property value="#ord.titolo.codice"/> -
			<s:property value="#ord.titolo.descrizione"/></td>
			<td class="tipologia"><s:property value="#ord.tipologia.codice.substring(0, 3)"/>: <s:property value="#ord.tipologia.descrizione"/></td>
			
			<s:if test="numeroAnniBilancioPrevisone < 3"><td class="value">&nbsp;</td></s:if><s:else>
			<td class="value importo"><input type="text" value="<s:property value="#ord.importoAccertamentoAnnoPrec2"/>"></td>
			</s:else>
			<s:if test="numeroAnniBilancioPrevisone < 2"><td class="value">&nbsp;</td></s:if><s:else>
			<td class="value importo"><input type="text" value="<s:property value="#ord.importoAccertamentoAnnoPrec1"/>"/></td>
			</s:else>
			<td class="value importo"><input type="text" value="<s:property value="#ord.importoAccertamentoAnnoPrec"/>"/></td>
			
			<s:if test="numeroAnniBilancioPrevisone < 3"><td class="value">&nbsp;</td></s:if><s:else>
			<td class="value importo"><input type="text" value="<s:property value="#ord.importoRiscossioneAnnoPrec2"/>"/></td>
			</s:else>
			<s:if test="numeroAnniBilancioPrevisone < 2"><td class="value">&nbsp;</td></s:if><s:else>
			<td class="value importo"><input type="text" value="<s:property value="#ord.importoRiscossioneAnnoPrec1"/>"/></td>
			</s:else>
			<td class="value importo"><input type="text" value="<s:property value="#ord.importoRiscossioneAnnoPrec"/>"/></td>
		</tr>
	</s:iterator>
	</tbody>
</table>

						<div class="Border_line"></div>


					<s:form action="confIndicatoriEntrata">

						<p>

							<a class="btn btn-secondary"
								href="<s:property value="#urlCruscotto" />" class="btn">indietro</a>

							<s:hidden name="serializedValues" id="serializedValues"/>
							
							<span class="pull-right"> 
									<!-- SIAC-8699 -->
									<s:submit cssClass="btn btn-primary" action="confIndicatoriEntrata_update" id="update"
										value="aggiorna" />
							</span>

						</p>

					</s:form>
				</fieldset>


		</div>
	</div>


	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />

	

	<script type="text/javascript" src="${jspath}indicatori/indicatori.js" charset="utf-8"></script>
	
	
</body>
</html>