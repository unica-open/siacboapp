<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%-- Informazioni sull'Utente effettuante il login --%>
<div class="navbarLogin">
	<div class="container-fluid">
		<%-- SIAC-7705 si rimuove il pull-left --%> 
		<p class="login-text" <s:if test="evidenziaAnnoSelezionato">style="color: red"</s:if>>
			BILANCIO <s:property value="sessionHandler.descrizioneAnnoBilancio" />
			<s:if test="evidenziaAnnoSelezionato">
				<%-- SIAC-7779 --%>
				<i class="icon-info-sign" data-toggle="tooltip" title="Attenzione! L'anno selezionato risulta essere precedente rispetto all'anno corrente"></i>
			</s:if>
		</p>
		<p class="login-text pull-right">
			<s:property value="sessionHandler.account.ente.nome" /> - <s:property value="sessionHandler.account.nome" /> - 
			<s:property value="sessionHandler.operatore.nome" />  <s:property value="sessionHandler.operatore.cognome" />
			<a href="/siaccruapp/logout.do" class="navbar-link"></a>
		</p>
	</div>
</div>
