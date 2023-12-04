/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.documenti;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.SoggettoService;
import it.csi.siac.siacboapp.business.service.documenti.DocumentoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.documenti.AnnullaAttivazioniContabiliModel;
import it.csi.siac.siacboapp.frontend.ui.model.documenti.CriteriRicercaDocumenti;
import it.csi.siac.siacboapp.frontend.ui.model.documenti.TipoDocumento;
import it.csi.siac.siacboapp.frontend.ui.model.messaggio.MessaggioBo;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTDocWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class AnnullaAttivazioniContabiliAction extends GenericBoAction<AnnullaAttivazioniContabiliModel> {

	private static final long serialVersionUID = -8932201412515570325L;

	@Autowired 
	private DocumentoService documentoService;

	@Autowired 
	private SoggettoService soggettoService;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int idEnte = sessionHandler.getEnte().getUid();

		model.setElencoTipiDocumentoEntrata(documentoService.readElencoTipiDocumento(idEnte, TipoDocumento.ENTRATA.getCodice()));
		model.setElencoTipiDocumentoSpesa(documentoService.readElencoTipiDocumento(idEnte, TipoDocumento.SPESA.getCodice()));

		model.setElencoClassiSoggetto(soggettoService.readElencoClassiSoggetto(idEnte));
		
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
	
	public String cerca() throws Exception {
		
		CriteriRicercaDocumenti criteri = model.getCriteri();
		
		int idEnte = sessionHandler.getEnte().getUid();
	
		SiacTDocWrapper documento = null;
		try {
			documento = documentoService.cercaDocumento(idEnte, criteri);
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		model.setDocumento(documento);
		
		return SUCCESS;
	}

	public String annullaAttivazioniContabili() {
		
		int idEnte = sessionHandler.getEnte().getUid();

		try {
			documentoService.annullaAttivazioniContabili(idEnte, model.getDocumento(), defaultGetCodiceInc());
			addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
			model.reset();
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		return SUCCESS;
	}

	private String defaultGetCodiceInc() {
		return String.format("%s-%s", 
				sessionHandler.getRichiedente().getOperatore().getCodiceFiscale(), 
				StringUtils.isBlank(model.getCodiceInc()) ? "BackofficeAnnullaAttivazioniContabili" : model.getCodiceInc());
	}


}
