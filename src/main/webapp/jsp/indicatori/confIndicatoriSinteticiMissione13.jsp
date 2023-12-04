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

			<li class="active">Configura indicatori sintetici</li>	
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
			<th rowspan="2" style="width: 200px;">&nbsp;</th>
			<th colspan="3" style="text-align: center;">TOTALE MISSIONI</th>
			<th colspan="3" style="text-align: center;">SOLO PER  MISSIONE 13 - TUTELA DELLA SALUTE</th>
			<th colspan="3" style="text-align: center;">TUTTE LE SPESE AL NETTO MISSIONE 13</th>
		</tr>

		<tr>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno + 1}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno + 2}"/></th>

			<th style="text-align: center; width: 70px;"><s:property value="%{anno}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno + 1}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno + 2}"/></th>

			<th style="text-align: center; width: 70px;"><s:property value="%{anno}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno + 1}"/></th>
			<th style="text-align: center; width: 70px;"><s:property value="%{anno + 2}"/></th>
		</tr>
	</thead>

	<tbody id="indicatori" class="sintetici">
	<s:iterator value="elencoConfIndicatoriSintetici" status="st" var="ord">
		<tr data-uid="<s:property value="#ord.uid"/>">
			<td class="voce"><s:property value="#ord.voce.descrizione"/></td>
			
			<s:if test="voce.numeroAnniInput < 1"><td class="value">&nbsp;</td></s:if>
			<s:else>
			<td class="value <s:if test="voce.decimali == 0">intero</s:if>
					<s:else>importo</s:else>"><input type="text" value="<s:property value="#ord.valoreAnno"/>"/></td>
			</s:else>

			<s:if test="voce.numeroAnniInput < 2"><td class="value">&nbsp;</td></s:if>
			<s:else>
			<td class="value <s:if test="voce.decimali == 0">intero</s:if>
					<s:else>importo</s:else>"><input type="text" value="<s:property value="#ord.valoreAnno1"/>"/></td>
			</s:else>

			<s:if test="voce.numeroAnniInput < 3"><td class="value">&nbsp;</td></s:if>
			<s:else>
			<td class="value <s:if test="voce.decimali == 0">intero</s:if>
					<s:else>importo</s:else>"><input type="text" value="<s:property value="#ord.valoreAnno2"/>"/></td>
			</s:else>

	<s:if test="voce.splitMissione13">

			<s:if test="voce.numeroAnniInput < 1"><td class="value">&nbsp;</td></s:if>
			<s:else>
			<td class="value <s:if test="voce.decimali == 0">intero</s:if>
					<s:else>importo</s:else>"><input type="text" value="<s:property value="#ord.valoreTotMiss13Anno"/>"/></td>
			</s:else>

			<s:if test="voce.numeroAnniInput < 2"><td class="value">&nbsp;</td></s:if>
			<s:else>
			<td class="value <s:if test="voce.decimali == 0">intero</s:if>
					<s:else>importo</s:else>"><input type="text" value="<s:property value="#ord.valoreTotMiss13Anno1"/>"/></td>
			</s:else>

			<s:if test="voce.numeroAnniInput < 3"><td class="value">&nbsp;</td></s:if>
			<s:else>
			<td class="value <s:if test="voce.decimali == 0">intero</s:if>
					<s:else>importo</s:else>"><input type="text" value="<s:property value="#ord.valoreTotMiss13Anno2"/>"/></td>
			</s:else>

			<s:if test="voce.numeroAnniInput < 1"><td class="value">&nbsp;</td></s:if>
			<s:else>
			<td class="value <s:if test="voce.decimali == 0">intero</s:if>
					<s:else>importo</s:else>"><input type="text" value="<s:property value="#ord.valoreTutteSpeseAnno"/>"/></td>
			</s:else>

			<s:if test="voce.numeroAnniInput < 2"><td class="value">&nbsp;</td></s:if>
			<s:else>
			<td class="value <s:if test="voce.decimali == 0">intero</s:if>
					<s:else>importo</s:else>"><input type="text" value="<s:property value="#ord.valoreTutteSpeseAnno1"/>"/></td>
			</s:else>

			<s:if test="voce.numeroAnniInput < 3"><td class="value">&nbsp;</td></s:if>
			<s:else>
			<td class="value <s:if test="voce.decimali == 0">intero</s:if>
					<s:else>importo</s:else>"><input type="text" value="<s:property value="#ord.valoreTutteSpeseAnno2"/>"/></td>
			</s:else>
			
	</s:if>
			
			<s:else>
				<td class="value">&nbsp;</td>			
				<td class="value">&nbsp;</td>			
				<td class="value">&nbsp;</td>			
				<td class="value">&nbsp;</td>			
				<td class="value">&nbsp;</td>			
				<td class="value">&nbsp;</td>			
			</s:else>
			
		</tr>
	</s:iterator>
	</tbody>
</table>

						<div class="Border_line"></div>


					<s:form action="confIndicatoriSinteticiMissione13">

						<p>

							<a class="btn btn-secondary"
								href="<s:property value="#urlCruscotto" />" class="btn">indietro</a>

							<s:hidden name="serializedValues" id="serializedValues"/>
							
							<span class="pull-right"> 
									<!-- SIAC-8699 -->
									<s:submit cssClass="btn btn-primary" action="confIndicatoriSinteticiMissione13_update" id="update"
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