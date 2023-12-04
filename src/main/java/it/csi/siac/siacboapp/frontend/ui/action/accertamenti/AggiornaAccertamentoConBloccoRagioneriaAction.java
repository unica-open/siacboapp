/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.accertamenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.accertamenti.AccertamentoService;
import it.csi.siac.siacboapp.business.service.common.SoggettoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.BaseAggiornaConBloccoRagioneriaAction;
import it.csi.siac.siacboapp.frontend.ui.model.accertamenti.AggiornaAccertamentoConBloccoRagioneriaModel;
import it.csi.siac.siacboapp.frontend.ui.model.accertamenti.CriteriRicercaAccertamenti;
import it.csi.siac.siacboapp.frontend.ui.model.messaggio.MessaggioBo;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestAttoAmministrativoMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestClasseSoggettoMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestSacMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestSoggettoMapper;
import it.csi.siac.siacboapp.util.entitywrapper.AccertamentoWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class AggiornaAccertamentoConBloccoRagioneriaAction extends BaseAggiornaConBloccoRagioneriaAction<AggiornaAccertamentoConBloccoRagioneriaModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8509238249371543454L;
	
	@Autowired
	private AccertamentoService accertamentoService;
	
	@SuppressWarnings("unchecked")
	public String cerca() throws Exception {
		
		CriteriRicercaAccertamenti criteri = model.getCriteri();
		
		int idEnte = sessionHandler.getEnte().getUid();
		int idBilancio = sessionHandler.getBilancio().getUid();
	
		AccertamentoWrapper accertamento = null;
		
		try {
			accertamento = accertamentoService.cercaAccertamento(idEnte, idBilancio, criteri, 
					SiacTMovgestAttoAmministrativoMapper.class,
					SiacTMovgestSoggettoMapper.class,
					SiacTMovgestClasseSoggettoMapper.class,
					SiacTMovgestSacMapper.class
			);
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		model.setAccertamento(accertamento);
		
		return SUCCESS;
	}
	
	public String aggiornaTutto() throws Exception{
		
		int idEnte = sessionHandler.getEnte().getUid();

		try {
			accertamentoService.aggiornaAccertamentoConBloccoRagioneria(idEnte, model.getAccertamento(), defaultGetCodiceInc());
			addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
			model.reset();
		} catch (BusinessException be) {
			addErrore(be.getErrore());
			cerca();
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
			cerca();
		}
		
		return SUCCESS;
	}
	
	@Override
	protected String getDefaultCodiceInc() {
		return "BackofficeAggiornaAccertamentoConBloccoRagioneria";
	}

}
