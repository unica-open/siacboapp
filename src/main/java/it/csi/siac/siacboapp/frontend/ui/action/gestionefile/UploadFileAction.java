/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.gestionefile;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.gestionefile.UploadFileModel;
import it.csi.siac.siaccorser.frontend.webservice.FileService;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.GetElencoTipoFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.UploadFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.UploadFileResponse;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siaccorser.model.file.File;
import it.csi.siac.siaccorser.model.file.StatoFile.CodiceStatoFile;
import it.csi.siac.siaccorser.model.file.TipoFile;
import it.csi.siac.siaccorser.model.messaggio.MessaggioCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class UploadFileAction extends GenericBoAction<UploadFileModel> {

	private static final long serialVersionUID = -2104635016448629800L;

	@Autowired
	private transient FileService fileService;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		List<TipoFile> elencoTipoFile = fileService.getElencoTipoFileByAccount(
				new GetElencoTipoFile(sessionHandler.getAccount(), sessionHandler.getEnte(),
						sessionHandler.getRichiedente())).getElencoTipoFile();

		model.setElencoTipoFile(elencoTipoFile);

		log.debugEnd(methodName, "");
	}

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void validateUpload() {
		if (model.getIdTipo() == null)
			addErrore(ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("Tipo file"));
		if (model.getFile() == null)
			addErrore(ErroreCore.PARAMETRO_NON_INIZIALIZZATO.getErrore("File"));
	}

	public String upload() throws Exception {
		UploadFile req = new UploadFile();

		File file = new File();

		file.setCodice(model.getCodice());
		file.setNome(model.getFileFileName());
		file.setNote(model.getNote());
		file.setIdTipo(model.getIdTipo());
		file.setMimeType(model.getFileContentType());
		file.setContenuto(FileUtils.readFileToByteArray(model.getFile()));
		file.setStatoFile(CodiceStatoFile.DA_ELABORARE);

		req.setFile(file);

		req.setRichiedente(sessionHandler.getRichiedente());
		req.setEnte(sessionHandler.getEnte());

		UploadFileResponse res = fileService.uploadFile(req);

		if (res.isFallimento()) {
			addErrori(res);
			return INPUT;
		}

		addMessaggio(MessaggioCore.MESSAGGIO_DI_SISTEMA.getMessaggio("File inviato correttamente"));

		return SUCCESS;

	}
}
