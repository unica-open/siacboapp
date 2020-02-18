/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.gestionefile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.gestionefile.RisultatiRicercaFileModel;
import it.csi.siac.siaccommonapp.action.DownloadFileAction;
import it.csi.siac.siaccorser.frontend.webservice.FileService;
import it.csi.siac.siaccorser.frontend.webservice.msg.AsyncServiceRequestWrapper;
import it.csi.siac.siaccorser.frontend.webservice.msg.AsyncServiceResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.ModificaStatoFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.ModificaStatoFileResponse;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFile.CriteriRicercaFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.RicercaFileResponse;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.file.File;
import it.csi.siac.siaccorser.model.file.StatoFile.CodiceStatoFile;
import it.csi.siac.siaccorser.model.messaggio.MessaggioCore;
import it.csi.siac.siaccorser.model.paginazione.ListaPaginata;
import it.csi.siac.siacintegser.frontend.webservice.ElaboraFileService;
import it.csi.siac.siacintegser.frontend.webservice.msg.ElaboraFile;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RisultatiRicercaFileAction extends GenericBoAction<RisultatiRicercaFileModel> {
	private static final long serialVersionUID = 6546026061074017809L;

	@Autowired
	private transient FileService fileService;

	@Autowired
	private transient ElaboraFileService elaboraFileService;  


	@SuppressWarnings("unchecked")
	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		model.setListaPaginata((ListaPaginata<File>) sessionHandler
				.getParametro(BoSessionParameter.GESTIONE_FILE_RISULTATI_RICERCA_FILE));

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

	public String elabora() throws Exception {
		// FIXME e' presente anche il contenuto... un po' pesante, modificare in futuro
		File file = model.getElementoByIndex();

		ElaboraFile elaboraFile = new ElaboraFile();

		elaboraFile.setAccount(sessionHandler.getAccount());
		elaboraFile.setEnte(sessionHandler.getEnte());
		elaboraFile.setFile(file);
		elaboraFile.setRichiedente(sessionHandler.getRichiedente());
		elaboraFile.setBilancio(sessionHandler.getBilancio());

		AsyncServiceRequestWrapper<ElaboraFile> elaboraFileWrapper = new AsyncServiceRequestWrapper<ElaboraFile>();
		
		elaboraFileWrapper.setRequest(elaboraFile);
		
		elaboraFileWrapper.setRichiedente(elaboraFile.getRichiedente());
		elaboraFileWrapper.setEnte(elaboraFile.getEnte());
		elaboraFileWrapper.setAccount(elaboraFile.getAccount());
		elaboraFileWrapper.setAzioneRichiesta(new AzioneRichiesta(file.getTipo().getAzioneServizio()));
		
		// FIXME AsyncServiceResponse è in bilitf, andrebbe spostato su core...
	 	AsyncServiceResponse res = elaboraFileService.elaboraFileAsync(elaboraFileWrapper);

	 	if (res.isFallimento()) {
	 		addErrori(res);
			return INPUT;
	 	}
	
 		addMessaggio(MessaggioCore.MESSAGGIO_DI_SISTEMA.getMessaggio("File in elaborazione"));

		cerca();

		return "risultatiRicercaFile";
	}

	public String annulla() throws Exception {
		ModificaStatoFile req = new ModificaStatoFile();

		req.setEnte(sessionHandler.getEnte());
		req.setRichiedente(sessionHandler.getRichiedente());
		req.setUid(model.getElementoByIndex().getUid());
		req.setStato(CodiceStatoFile.ANNULLATO);

		ModificaStatoFileResponse res = fileService.modificaStatoFile(req);

		if (res.isFallimento())
			addErrori(res);
		else
			addMessaggio(MessaggioCore.MESSAGGIO_DI_SISTEMA.getMessaggio("File annullato"));

		cerca();

		return "risultatiRicercaFile";
	}

	public String download() throws Exception {
		// FIXME: non serve per ora, anche se pesante, il contenuto del file e'
		// dentro
		// listaPaginata
		//
		// RicercaFile req = new RicercaFile();
		// CriteriRicercaFile criteri = new CriteriRicercaFile();
		// criteri.setUid(model.getElementoByIndex().getUid());
		// req.setCriteri(criteri);
		// req.setEnte(sessionHandler.getEnte());
		// req.setRichiedente(sessionHandler.getRichiedente());
		// RicercaFileResponse res = fileService.ricercaFile(req);
		// file = res.getElencoPaginato().iterator().next();

		request.put(DownloadFileAction.FILE, model.getElementoByIndex());

		return "downloadFile";
	}

	public String cerca() throws Exception {
		RicercaFileResponse res = fileService.ricercaFile(RicercaFile.buildRicercaFileRequest(
				sessionHandler.getParametro(BoSessionParameter.GESTIONE_FILE_CRITERI_RICERCA_FILE,
						CriteriRicercaFile.class), model.getPagina(), sessionHandler
						.getRichiedente(), sessionHandler.getEnte()));

		if (res.isFallimento()) {
			addErrori(res);
			return INPUT;
		}

		sessionHandler.setParametro(BoSessionParameter.GESTIONE_FILE_RISULTATI_RICERCA_FILE,
				res.getElencoPaginato());

		model.setListaPaginata(res.getElencoPaginato());

		return SUCCESS;
	}

}
