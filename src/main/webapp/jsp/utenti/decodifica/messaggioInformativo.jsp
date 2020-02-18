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

			<li class="active">Modifica messaggio informativo</li>	
			</ul>
		</div>
	</div>




	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">

				<h4 class="titleBO">
					<i class="icon-tasks"></i>Modifica messaggio informativo
				</h4>
				<div class="Border_line"></div>

				<fieldset class="form-horizontal margin-medium minHeight">
					<s:form action="messaggioInformativo">

						<s:hidden name="decodifica.uid" />
						<s:hidden name="decodifica.tabella.id" />

						 <s:iterator id="col" value="decodifica.tabella.columns"> 
						
							<s:if test="#col.visible">
							
								<s:if test="not #col.primaryKey or decodifica.values[#col.name] != null">
									<div class="control-group">
										<label class="control-label"><s:property value="#col.label"  /></label>
										<div class="controls">
										
										<s:if test="#col.hasForeignKey()" >
											<s:select disabled="not #col.editable" cssClass="span6 chosen-select"
													name="%{'decodifica.values[\\''+#col.name+'\\']'}"
													headerKey="" headerValue="" list="decodifica.foreignKeyValues[#col.name]"
													listKey="uid" listValue="descrizione" value="decodifica.values[#col.name]" />
										</s:if>
										<s:else>
											<s:if test="decodifica.values[#col.name] == null">
													<s:textfield value=""
													name="%{'decodifica.values[\\''+#col.name+'\\']'}" cssClass="span11 " maxlength="500" />
											</s:if>
											<s:else>
												<s:textfield maxlength="500"  disabled="not #col.editable or #col.primaryKey or not decodifica.values[#col.name] instanceof java.lang.String" 
													name="%{'decodifica.values[\\''+#col.name+'\\']'}" cssClass="span11 " />
											</s:else>
										</s:else>
										
										</div>
									</div>
									</s:if>
								</s:if>

						 </s:iterator> 





						<div class="Border_line"></div>

						<p>

							<a class="btn btn-secondary"
								href="/siacboapp/homeUtenti.do" class="btn">indietro</a>

							<span class="pull-right"> <s:if test="decodifica.uid == null">
									<s:submit cssClass="btn btn-primary" method="create"
										value="inserisci decodifica" />
								</s:if> <s:else>
									<s:submit cssClass="btn btn-primary" method="update"
										value="aggiorna" />
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