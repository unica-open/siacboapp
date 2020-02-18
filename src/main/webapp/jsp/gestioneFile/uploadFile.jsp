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
<%-- Eventuali altre librerie JavaScript --%>

</head>
<body>
	<%-- Inclusione header --%>
	<s:include value="/jsp/include/header.jsp" />



<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="<s:property value="#urlCruscotto" />">Home</a>
					<span class="divider">&gt;</span></li>
		        <li class="active">Upload file</li>
			</ul>
		</div>
	</div>



<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12 contentPage">        
<s:form cssClass="form-horizontal" action="uploadFile" method="post" enctype="multipart/form-data">

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


			<h3>Upload file </h3>
			<div class="step-content">
			<div class="step-pane active" id="step1">

		

			<fieldset class="form-horizontal margin-large">


					<div class="control-group">
						<label class="control-label" for="codFile">Codice </label>
						<div class="controls">
							<s:textfield maxlength="100" cssClass="lbTextSmall span2"
								id="codFile" name="codice" />
						</div>
					</div>

						<div class="control-group">
					<label class="control-label" for="tipoFile">Tipo  </label>
				  
					<div class="controls">   
			
					<s:select cssClass="span4"
       name="idTipo"
       headerKey="" 
       headerValue=""
		list="elencoTipoFile" 
		listKey="uid"
       listValue="descrizione"
      />
      </div>
				</div> 
				
				<div class="control-group">
					<label class="control-label" for="NoteFile">Note </label>								
						<div class="controls">
						
						<s:textarea id="NoteFile" rows="2" cols="15" 
						 cssClass="input-medium span9" name="note" />
						
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="FileUP">File * </label>	
					<div class="controls">
						<div class="input-group">
						<span class="input-group-btn">
							<span class="btn btn-primary btn-file" >
								Upload 	<s:file name="file" cssClass="typeFile" id="upload-file"/>
							</span>
						</span>
												  <span id="nome-file">Nessun file selezionato</span>
						
						</div>						
					</div>
				</div>
				
				

				<br>
				
			</fieldset>  

		</div>
		
		
		</div>
			
          <p class="margin-medium">
				  <a class="btn btn-secondary" href="<s:property value="#urlCruscotto" />">indietro</a>   
			  <a class="btn btn-secondary" href="uploadFile.do">annulla</a>

			  <s:submit cssClass="btn btn-primary pull-right"  method="upload"  value="invia" />
		  </p>
          
		  </s:form>     	
    </div>	
  </div>	 
</div>			
		
	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			
	
	<%-- Caricamento del footer --%>
	<s:include value="/jsp/include/footer.jsp" />
		
	<script type="text/javascript" src="${jspath}gestioneFile/uploadFile.js" charset="utf-8"></script>
	
	
</body>
</html>