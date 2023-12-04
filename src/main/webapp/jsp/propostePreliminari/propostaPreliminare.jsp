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
				<li><a href="/siacboapp/gestionePropostePreliminari<s:property value="capitalize(tipoProposta)" />.do">Gestione
						proposte preliminari <s:property value="tipoProposta" /></a><span
					class="divider">&gt;</span></li>
							<li><a href="/siacboapp/elencoPropostePreliminari<s:property value="capitalize(tipoProposta)" />.do">Elenco
						proposte preliminari <s:property value="tipoProposta" /></a><span
					class="divider">&gt;</span></li>
			<li class="active">
				<s:if test="proposta.uid == null">Inserisci</s:if>
				<s:else>Aggiorna</s:else>
			    	proposta preliminare <s:property value="tipoProposta" /></li>	
			</ul>
		</div>
	</div>


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
			




	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">


				<div class="Border_line"></div>

				<fieldset class="form-horizontal margin-medium minHeight">

					<s:form >

						<s:hidden name="proposta.uid"  />

					<s:if test="proposta.numero != null">
						<div class="control-group">
							<label class="control-label">Numero</label>
							<div class="controls">
								<s:textfield name="proposta.numero" cssClass="span10" readonly="true" />
							</div>
						</div>
					</s:if>			
									
						<div class="control-group">
							<label class="control-label">Descrizione</label>
							<div class="controls">
								<s:textfield name="proposta.descrizione" cssClass="span10" />
							</div>
						</div>
									
									
					<div class="control-group">
							<label class="control-label">Descrizione articolo</label>
							<div class="controls">
								<s:textfield name="proposta.descrizioneArticolo" cssClass="span10" />
							</div>
						</div>



						<div class="control-group">
							<label class="control-label">Capitolo</label>
							<div class="controls">
								<s:select cssClass="span6 chosen-select-capitolo-proposta" size="3"
									name="proposta.capitolo.uid" headerKey="" headerValue=""
									list="elencoCapitoli" listKey="uid" listValue="%{codiceCompleto + ' - ' + descrizioneCompleta}" />
							</div>
						</div>

	
<div class="control-group">
	<table class="table table-bordered importi">

		<tbody>
		<tr>
			<th>&nbsp;</th>
			<th scope="col"  class="text-center"><s:property value="proposta.anno" /><s:hidden name="proposta.anno" /></th>
			<th scope="col"  class="text-center"><s:property value="%{proposta.anno + 1}" /></th>
			<th scope="col"  class="text-center"><s:property value="%{proposta.anno + 2}" /></th>
		</tr>
		<tr>
			<th>Competenza</th>
			<td class="text-right"><s:textfield name="importoCompetenzaAnno" /></td>
			<td class="text-right"><s:textfield name="importoCompetenzaAnno1" /></td>
			<td class="text-right"><s:textfield name="importoCompetenzaAnno2" /></td>
		</tr>

		<tr>
			<th>Residuo</th>
			<td class="text-right"><s:textfield name="importoResiduoAnno" /></td>
			<td class="text-right"><s:textfield readonly="true" name="importoResiduoAnno1" /></td>
			<td class="text-right"><s:textfield readonly="true" name="importoResiduoAnno2" /></td>
		</tr>
		<tr>
			<th>Cassa</th>
			<td class="text-right"><s:textfield name="importoCassaAnno" /></td>
			<td class="text-right"><s:textfield readonly="true" name="importoCassaAnno1" /></td>
			<td class="text-right"><s:textfield readonly="true" name="importoCassaAnno2" /></td>
		</tr>
		</tbody>
	</table>
</div>



					<div class="control-group">
							<label class="control-label">Note</label>
							<div class="controls">
								<s:textarea name="proposta.note" cssClass="span10" />
							</div>
						</div>



						<div class="Border_line"></div>

						<p>

							<a class="btn btn-secondary"
								href="/siacboapp/elencoPropostePreliminari<s:property value="capitalize(tipoProposta)" />.do" class="btn">indietro</a>

							<span class="pull-right"> 
								
								<s:if test="proposta.uid == null">
									<s:submit cssClass="btn btn-primary editPropostaPreliminare" action='propostaPreliminare<s:property value="capitalize(tipoProposta)"_create'
										value="inserisci proposta" />
								</s:if> <s:else>
								
								
									<s:if test="proposta.stato.codice != 'APPROVATA'" > 
									<s:submit cssClass="btn btn-primary editPropostaPreliminare" action='propostaPreliminare<s:property value="capitalize(tipoProposta)"_update' 
										value="aggiorna proposta" />
										</s:if>
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

	
<script type="text/javascript" src="${jspath}propostePreliminari/propostaPreliminare.js" charset="utf-8"></script>


</body>
</html>