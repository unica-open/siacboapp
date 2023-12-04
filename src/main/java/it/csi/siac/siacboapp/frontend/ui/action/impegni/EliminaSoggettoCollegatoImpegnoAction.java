/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.impegni;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.SoggettoService;
import it.csi.siac.siacboapp.business.service.impegni.ImpegnoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.impegni.CriteriRicercaImpegni;
import it.csi.siac.siacboapp.frontend.ui.model.impegni.EliminaSoggettoCollegatoModel;
import it.csi.siac.siacboapp.frontend.ui.model.messaggio.MessaggioBo;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestSoggettoMapper;
import it.csi.siac.siacboapp.util.entitywrapper.ImpegnoWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class EliminaSoggettoCollegatoImpegnoAction extends GenericBoAction<EliminaSoggettoCollegatoModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1993388855339574474L;

	
	@Autowired
	private ImpegnoService impegnoService;

	@Autowired 
	private SoggettoService soggettoService;
	
	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();
		
		model.setElencoClassiSoggetto(soggettoService.readElencoClassiSoggetto(sessionHandler.getEnte().getUid()));

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
		
		CriteriRicercaImpegni criteri = model.getCriteri();
		
		int idEnte = sessionHandler.getEnte().getUid();
		int idBilancio = sessionHandler.getBilancio().getUid();
	
		ImpegnoWrapper impegno = null;
		
		try {
			impegno = impegnoService.cercaImpegno(idEnte, idBilancio, criteri, SiacTMovgestSoggettoMapper.class);
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		model.setImpegno(impegno);
		
		return SUCCESS;
	}

	public String aggiornaSoggettoCollegato() {
		
		int idEnte = sessionHandler.getEnte().getUid();

		try {
			impegnoService.aggiornaSoggettoClasseSoggettoCollegati(idEnte, model.getImpegno(), defaultGetCodiceInc());

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
				StringUtils.isBlank(model.getCodiceInc()) ? "BackofficeEliminaSoggettoCollegatoImpegno" : model.getCodiceInc());
	}


}
