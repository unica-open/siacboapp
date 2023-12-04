<%--
SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
SPDX-License-Identifier: EUPL-1.2
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.csi.it/taglibs/remincl-1.0" prefix="r" %>

<%-- Inclusione dell'HEAD --%>
<r:include url="/ris/servizi/siac/include/head.html" resourceProvider="rp"/>
<%--
	NOTA: l'inclusione riportata sopra comprende già la definizione dei tag
		<!DOCTYPE>
		<html>
			<head>
				<meta ... />
				<link rel="stylesheet" .../> (Risorse CSS)
				
	Non è presente un tag di chiusura </head> per facilitare l'inclusione del codice Javascript nell'head.
--%>

<link rel="stylesheet" href="/siacboapp/css/siacboapp.css" type="text/css"/>
<link rel="stylesheet" href="/siacboapp/css/validation.css" type="text/css"/>
<!-- <link rel="stylesheet" href="${jspathexternal}jquery-chosen/chosen.css" type="text/css"> -->

 <link rel="stylesheet" href="/siacboapp/css/jquery.simple.pagination.plugin.css" type="text/css"/>
