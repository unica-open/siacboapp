/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.variazioni;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.variazioni.VariazioneService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.messaggio.MessaggioBo;
import it.csi.siac.siacboapp.frontend.ui.model.variazioni.CriteriRicercaVariazioni;
import it.csi.siac.siacboapp.frontend.ui.model.variazioni.DefinisciVariazioneSenzaBonitaModel;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTVariazioneWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class DefinisciVariazioneSenzaBonitaAction extends GenericBoAction<DefinisciVariazioneSenzaBonitaModel> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 845465345274127855L;

	@Autowired
	private VariazioneService variazioneService;
	
	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();
		
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
	
	@SuppressWarnings("unchecked")
	public String cerca() throws Exception {
		
		CriteriRicercaVariazioni criteri = model.getCriteri();
		
		int idEnte = sessionHandler.getEnte().getUid();
		int idBilancio = sessionHandler.getBilancio().getUid();
	
		SiacTVariazioneWrapper variazione = null;
		
		try {
			variazione = variazioneService.cercaVariazione(idEnte, idBilancio, criteri);
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		model.setVariazione(variazione);
		
		return SUCCESS;
	}

	public String definisciVariazione() {
		
		int idEnte = sessionHandler.getEnte().getUid();

		try {
			variazioneService.definisciVariazione(idEnte, sessionHandler.getBilancio().getAnno(), model.getVariazione(), defaultGetCodiceInc());
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
				StringUtils.isBlank(model.getCodiceInc()) ? "BackofficeDefinisciVariazioneSenzaBonita" : model.getCodiceInc());
	}


}
