/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.ProvvisorioCassaService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.common.CriteriRicercaProvvisoriCassa;
import it.csi.siac.siacboapp.frontend.ui.model.common.RicercaProvvisoriCassaModel;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RicercaProvvisoriCassaAction extends GenericBoAction<RicercaProvvisoriCassaModel> {

	private static final long serialVersionUID = -1992019106036597400L;
	
	@Autowired
	private ProvvisorioCassaService provvisorioCassaService;

	@Override
	public String execute() throws Exception {
		
		Integer idEnte = sessionHandler.getEnte().getUid();

		CriteriRicercaProvvisoriCassa criteriProvvisoriCassa = model.getCriteriProvvisoriCassa();
		
		criteriProvvisoriCassa.setImportoDaRegolarizzarePositivo(true);
		criteriProvvisoriCassa.setDataRegolarizzazionePresente(0);
		criteriProvvisoriCassa.setDataAnnullamentoPresente(0);
		
		try {
			model.setElencoProvvisoriCassa(provvisorioCassaService.ricercaProvvisoriCassa(idEnte, criteriProvvisoriCassa));
		}
		catch (BusinessException be) {
			model.addErrore(be.getErrore());
		}
		catch (Exception e) {
			model.addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(e.getMessage()));
		}

		return SUCCESS;
	}
}
