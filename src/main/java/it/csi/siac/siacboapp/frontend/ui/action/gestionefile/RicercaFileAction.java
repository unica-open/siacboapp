/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.gestionefile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.gestionefile.RicercaFileModel;
import it.csi.siac.siaccommon.util.DataValidator;
import it.csi.siac.siaccorser.frontend.webservice.FileService;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.GetElencoStatoFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.GetElencoTipoFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFile.CriteriRicercaFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFileResponse;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siaccorser.model.file.StatoFile;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RicercaFileAction extends GenericBoAction<RicercaFileModel> {
	static final long serialVersionUID = -1644078748942543521L;

	@Autowired
	private transient FileService fileService;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		model.setElencoTipoFile(fileService.getElencoTipoFileByAccount(
				new GetElencoTipoFile(sessionHandler.getAccount(), sessionHandler.getEnte(),
						sessionHandler.getRichiedente())).getElencoTipoFile());

		List<StatoFile> elencoStatoFile = fileService.getElencoStatoFile(
				new GetElencoStatoFile(sessionHandler.getEnte(), sessionHandler.getRichiedente()))
				.getElencoStatoFile();
		model.setElencoStatoFile(elencoStatoFile);

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

	public void validateCerca() {
		if (!DataValidator.isValidDate(model.getDataUpload(), "dd/MM/yyyy"))
			addErrore(ErroreCore.PARAMETRO_ERRATO.getErrore("data caricamento",
					model.getDataUpload(), "data nel formato dd/MM/yyyy"));

		if (!DataValidator.isValidMinLength(model.getNome(), 3))
			addErrore(ErroreCore.PARAMETRO_ERRATO.getErrore("nome", model.getNome(),
					"inserire almeno tre caratteri"));
	}

	public String cerca() throws Exception {
		CriteriRicercaFile criteri = new CriteriRicercaFile();

		criteri.setCodice(model.getCodice());
		criteri.setNome(model.getNome());
		criteri.setDataUpload(model.getDataUpload());
		criteri.setIdTipo(model.getIdTipo());
		criteri.setStato(model.getCodiceStato());
		
		sessionHandler.setParametro(BoSessionParameter.GESTIONE_FILE_CRITERI_RICERCA_FILE, criteri);

		RicercaFileResponse res = fileService.ricercaFile(RicercaFile.buildRicercaFileRequest(
				criteri, 0, sessionHandler.getRichiedente(), sessionHandler.getEnte()));

		if (res.isFallimento()) {
			addErrori(res);
			return INPUT;
		}

		if (res.getElencoPaginato().isEmpty()) {
			addErrore(ErroreCore.NESSUN_DATO_REPERITO.getErrore());
			return INPUT;
		}

		sessionHandler.setParametro(BoSessionParameter.GESTIONE_FILE_RISULTATI_RICERCA_FILE,
				res.getElencoPaginato());

		return "risultatiRicercaFile";
	}
}
