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

			<li class="active">Configura indicatori analitici di spesa</li>	
			</ul>
		</div>
	</div>




	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">




				<fieldset class="form-horizontal margin-medium minHeight">
									
<table class="table tab_left scrolling" data-scroll-show="3" data-scroll-max="5">
	<thead>
		<tr>
            <th rowspan="2" style="vertical-align:middle; width: 80px;">Missione</th>
			<th rowspan="2" style="vertical-align:middle; width: 200px;">Programma</th>


			<th id="scroll-left" rowspan="2" style="width: 20px; text-align: center; vertical-align: middle; cursor: pointer">&#9668;</th>
			<th data-scroll-idx="0" style="text-align: center;" colspan="3">FPV</th>
			<th data-scroll-idx="1" style="text-align: center;" colspan="3">Impegni</th>
			<th data-scroll-idx="2" style="text-align: center;" colspan="3">Pagamenti C/Competenza</th>
			<th data-scroll-idx="3" style="text-align: center;" colspan="3">Pagamenti C/Residui</th>
			<th data-scroll-idx="4" style="text-align: center;" colspan="3">Residui definitivi</th>
			<th id="scroll-right" rowspan="2" style="width: 20px; text-align: center; vertical-align: middle; cursor: pointer">&#9658;</th>
		</tr>
    
		<tr>
			<th data-scroll-idx="0" style="text-align: center; width: 70px;"><s:property value="%{anno - 3}"/></th>
			<th data-scroll-idx="0" style="text-align: center; width: 70px;"><s:property value="%{anno - 2}"/></th>
			<th data-scroll-idx="0" style="text-align: center; width: 70px;"><s:property value="%{anno - 1}"/></th>
			
			<th data-scroll-idx="1" style="text-align: center; width: 70px;"><s:property value="%{anno - 3}"/></th>
			<th data-scroll-idx="1" style="text-align: center; width: 70px;"><s:property value="%{anno - 2}"/></th>
			<th data-scroll-idx="1" style="text-align: center; width: 70px;"><s:property value="%{anno - 1}"/></th>
			
			<th data-scroll-idx="2" style="text-align: center; width: 70px;"><s:property value="%{anno - 3}"/></th>
			<th data-scroll-idx="2" style="text-align: center; width: 70px;"><s:property value="%{anno - 2}"/></th>
			<th data-scroll-idx="2" style="text-align: center; width: 70px;"><s:property value="%{anno - 1}"/></th>
			
			<th data-scroll-idx="3" style="text-align: center; width: 70px;"><s:property value="%{anno - 3}"/></th>
			<th data-scroll-idx="3" style="text-align: center; width: 70px;"><s:property value="%{anno - 2}"/></th>
			<th data-scroll-idx="3" style="text-align: center; width: 70px;"><s:property value="%{anno - 1}"/></th>
			
			<th data-scroll-idx="4" style="text-align: center; width: 70px;"><s:property value="%{anno - 3}"/></th>
			<th data-scroll-idx="4" style="text-align: center; width: 70px;"><s:property value="%{anno - 2}"/></th>
			<th data-scroll-idx="4" style="text-align: center; width: 70px;"><s:property value="%{anno - 1}"/></th>
		</tr>
	</thead>

	<tbody id="indicatori" class="analitici">
	<s:iterator value="elencoConfIndicatoriSpesa" status="st" var="ord">
		<tr data-uid="<s:property value="#ord.uid"/>">
			<td style="vertical-align: middle;" data-classif-id="<s:property value="#ord.missione.uid"/>" class="classif">Missione <s:property value="#ord.missione.codice"/> -
			<s:property value="#ord.missione.descrizione"/></td>
			<td class="tipologia"><s:property value="#ord.programma.codice.substring(2)"/>: <s:property value="#ord.programma.descrizione"/></td>

			<td>&nbsp;</td>
			
			<s:if test="numeroAnniBilancioPrevisone < 3"><td data-scroll-idx="0" class="value">&nbsp;</td></s:if><s:else>
			<td data-scroll-idx="0" class="value importo"><input type="text" value="<s:property value="#ord.importoFpvAnnoPrec2"/>"></td>
			</s:else>
			<s:if test="numeroAnniBilancioPrevisone < 2"><td data-scroll-idx="0" class="value">&nbsp;</td></s:if><s:else>
			<td data-scroll-idx="0" class="value importo"><input type="text" value="<s:property value="#ord.importoFpvAnnoPrec1"/>"/></td>
			</s:else>
			<td data-scroll-idx="0" class="value importo"><input type="text" value="<s:property value="#ord.importoFpvAnnoPrec"/>"/></td>
			
			<s:if test="numeroAnniBilancioPrevisone < 3"><td data-scroll-idx="1" class="value">&nbsp;</td></s:if><s:else>
			<td data-scroll-idx="1" class="value importo"><input type="text" value="<s:property value="#ord.importoImpegniAnnoPrec2"/>"/></td>
			</s:else>
			<s:if test="numeroAnniBilancioPrevisone < 2"><td data-scroll-idx="1" class="value">&nbsp;</td></s:if><s:else>
			<td data-scroll-idx="1" class="value importo"><input type="text" value="<s:property value="#ord.importoImpegniAnnoPrec1"/>"/></td>
			</s:else>
			<td data-scroll-idx="1" class="value importo"><input type="text" value="<s:property value="#ord.importoImpegniAnnoPrec"/>"/></td>
			
			<s:if test="numeroAnniBilancioPrevisone < 3"><td data-scroll-idx="2" class="value">&nbsp;</td></s:if><s:else>
			<td data-scroll-idx="2" class="value importo"><input type="text" value="<s:property value="#ord.importoPagCompAnnoPrec2"/>"/></td>
			</s:else>
			<s:if test="numeroAnniBilancioPrevisone < 2"><td data-scroll-idx="2" class="value">&nbsp;</td></s:if><s:else>
			<td data-scroll-idx="2" class="value importo"><input type="text" value="<s:property value="#ord.importoPagCompAnnoPrec1"/>"/></td>
			</s:else>
			<td data-scroll-idx="2" class="value importo"><input type="text" value="<s:property value="#ord.importoPagCompAnnoPrec"/>"/></td>
			
			<s:if test="numeroAnniBilancioPrevisone < 3"><td data-scroll-idx="3" class="value">&nbsp;</td></s:if><s:else>
			<td data-scroll-idx="3" class="value importo"><input type="text" value="<s:property value="#ord.importoPagResAnnoPrec2"/>"/></td>
			</s:else>
			<s:if test="numeroAnniBilancioPrevisone < 2"><td data-scroll-idx="3" class="value">&nbsp;</td></s:if><s:else>
			<td data-scroll-idx="3" class="value importo"><input type="text" value="<s:property value="#ord.importoPagResAnnoPrec1"/>"/></td>
			</s:else>
			<td data-scroll-idx="3" class="value importo"><input type="text" value="<s:property value="#ord.importoPagResAnnoPrec"/>"/></td>
			
			<s:if test="numeroAnniBilancioPrevisone < 3"><td data-scroll-idx="4" class="value">&nbsp;</td></s:if><s:else>
			<td data-scroll-idx="4" class="value importo"><input type="text" value="<s:property value="#ord.importoResDefAnnoPrec2"/>"/></td>
			</s:else>
			<s:if test="numeroAnniBilancioPrevisone < 2"><td data-scroll-idx="4" class="value">&nbsp;</td></s:if><s:else>
			<td data-scroll-idx="4" class="value importo"><input type="text" value="<s:property value="#ord.importoResDefAnnoPrec1"/>"/></td>
			</s:else>
			<td data-scroll-idx="4" class="value importo"><input type="text" value="<s:property value="#ord.importoResDefAnnoPrec"/>"/></td>

			<td>&nbsp;</td>
		</tr>
	</s:iterator>
	</tbody>
</table>

						<div class="Border_line"></div>


					<s:form action="confIndicatoriSpesa">

						<p>

							<a class="btn btn-secondary"
								href="<s:property value="#urlCruscotto" />" class="btn">indietro</a>

							<s:hidden name="serializedValues" id="serializedValues"/>
							
							<span class="pull-right"> 
									<s:submit cssClass="btn btn-primary" method="update" id="update"
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