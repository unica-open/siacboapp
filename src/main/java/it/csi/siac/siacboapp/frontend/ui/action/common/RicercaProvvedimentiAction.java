/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.ProvvedimentoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.common.RicercaProvvedimentiModel;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RicercaProvvedimentiAction extends GenericBoAction<RicercaProvvedimentiModel> {

	private static final long serialVersionUID = 3509628656906689611L;

	@Autowired
	private ProvvedimentoService provvedimentoService;

	
	private boolean isValid() {
		if(model.getCriteriProvvedimenti().getAnno() == null) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("anno"));
		}
		
		if(model.getCriteriProvvedimenti().getNumero() == null && model.getCriteriProvvedimenti().getIdTipo() == null) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("inserire almeno il numero atto oppure il tipo atto"));
		}
		
		return ! hasActionErrors(); 
	}
	
	@Override
	public String execute() throws Exception {
		
		if (!isValid()) {
			return SUCCESS;
		}

		Integer idEnte = sessionHandler.getEnte().getUid();

		model.setElencoProvvedimenti(provvedimentoService.ricercaProvvedimenti(idEnte, model.getCriteriProvvedimenti()));
		
		if (model.getElencoProvvedimenti().isEmpty()) {
			addErrore(ErroreCore.NESSUN_DATO_REPERITO.getErrore());
		}

		return SUCCESS;
	}
}
