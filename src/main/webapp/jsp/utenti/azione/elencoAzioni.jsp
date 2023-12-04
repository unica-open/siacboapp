<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ taglib uri="http://www.csi.it/taglibs/remincl-1.0" prefix="r"%> 


<%-- Inclusione head e CSS --%>
<s:include value="/jsp/include/head.jsp" />



<%-- Inclusione librerie JavaScript --%>
<s:include value="/jsp/include/javascript.jsp" />



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
				<li class="active">Gestione azioni</li>
			</ul>
		</div>
	</div>

	
	<%-- Pagina JSP vera e propria --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">
			



<h4 class="titleBO"><i class="icon-user"></i>Gestione azioni</h4>
				
				<fieldset class="form-horizontal margin-medium minHeight">


<div id="simple-pagination">
	
<table class="table tab_left"> 
		<tbody>
		<s:iterator value="elencoAzioni">
		<tr>
			<td  id="descrizione" class="descrizione"><i class="icon-angle-right"></i><a  class="marginLeft2" href="<s:url 
				action="azione"><s:param name="uid" >
					<s:property value="uid" /></s:param>
		</s:url>"><s:property value="%{codice + ' - ' + descrizione}" /></a></td>
				
			<td class="elimina tab_Right">	<a  class="btn" onclick="return confirm('Eliminare <s:property value="%{codice + ' - ' + descrizione}" />?')" href="<s:url 
						action="azione_delete" ><s:param name="uid" >
							<s:property value="uid" /></s:param>
		</s:url>">elimina<i class="icon-trash marginLeft1"></i></a> </td>
				
		</tr>
		</s:iterator>
		</tbody>
	</table>


	<div class="Border_line margin-medium"></div>

<div class="simple-pagination-navigation">
		<div class="simple-pagination-first"></div>
		<div class="simple-pagination-previous"></div>
		<div class="simple-pagination-page-numbers"></div>
		<div class="simple-pagination-next"></div>
		<div class="simple-pagination-last"></div>
</div>

		</div>

				<p>
					<a class="btn btn-secondary" href="/siacboapp/homeUtenti.do">indietro</a>	
					<span class="pull-right">
<a class="btn btn-primary" href="<s:url 
				action="azione"/>">inserisci azione</a>					</span>
					
				</p>

			</fieldset>

		</div>
		  
	</div>	 
</div>	
	
	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
			
	
<%-- Eventuali altre librerie JavaScript --%>

<s:include value="/jsp/include/navigation.jsp" />

	
</body>
</html>