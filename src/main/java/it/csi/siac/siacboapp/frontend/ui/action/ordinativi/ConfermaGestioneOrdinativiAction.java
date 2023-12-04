/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.ordinativi;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

import it.csi.siac.siacboapp.business.service.ordinativi.BaseOrdinativoService;
import it.csi.siac.siacboapp.business.service.ordinativi.OrdinativoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.messaggio.MessaggioBo;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.ConfermaGestioneOrdinativiModel;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.CriteriRicercaOrdinativi;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

public abstract class ConfermaGestioneOrdinativiAction<M extends ConfermaGestioneOrdinativiModel>
		extends GenericBoAction<M> {
	
	private static final long serialVersionUID = -1713816022727924767L;

	@Autowired protected OrdinativoService ordinativoService;
	
	@Override
	protected void initModel() {
		super.initModel(); 

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		
		try {
			cerca();  
		}
		catch (BusinessException e) {
			addRedirectActionError(e.getErrore());
			return "gestioneOrdinativi";
		}
		
		return SUCCESS;
	}

	public void cerca() {
		CriteriRicercaOrdinativi criteri = sessionHandler.getParametro(
				BoSessionParameter.GESTIONE_ORDINATIVI_CRITERI_RICERCA_ORDINATIVI, CriteriRicercaOrdinativi.class);

		//criteri.setAnno(Integer.parseInt(sessionHandler.getAnnoEsercizio()));
		criteri.setAnno(sessionHandler.getAnnoBilancio());
		
		int idEnte = sessionHandler.getEnte().getUid();

		List<SiacTOrdinativoWrapper> elencoOrdinativi = ricercaOrdinativi(idEnte, criteri);

		model.setElencoOrdinativi(elencoOrdinativi);
		model.setCodiceTipoOrdinativi(criteri.getCodiceTipo());
	}

	protected List<SiacTOrdinativoWrapper> ricercaOrdinativi(int idEnte, CriteriRicercaOrdinativi criteri) {
		return getOrdinativoService().ricercaOrdinativi(idEnte, criteri);
	}

	protected BaseOrdinativoService getOrdinativoService() {
		return ordinativoService;
	}

	public void validateConferma() {
		if (model.getIdOrdinativi() == null)
			addErrore(ErroreCore.NESSUN_ELEMENTO_SELEZIONATO.getErrore());
	}

	public String conferma() {
		try {
			executeConferma();
		}
		catch (Exception e) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(e.getMessage()));

			return INPUT;
		}

		cerca();

		addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());

		return SUCCESS;
	}
	
	public String getConfermaActionName() {
		return String.format("%s_conferma", StringUtils.split(ActionContext.getContext().getName(), "_")[0]);
	}
	
	protected abstract void executeConferma() throws Exception;
}
