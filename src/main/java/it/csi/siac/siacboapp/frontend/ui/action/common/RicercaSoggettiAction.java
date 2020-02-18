/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.SoggettoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.common.RicercaSoggettiModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RicercaSoggettiAction extends GenericBoAction<RicercaSoggettiModel> {


	private static final long serialVersionUID = -1992019106036597400L;
	
	@Autowired
	private SoggettoService soggettoService;

	private boolean isValid() {
		return true; 
	}
	
	@Override
	public String execute() throws Exception {

		if (!isValid()) {
			return SUCCESS;
		}

		Integer idEnte = sessionHandler.getEnte().getUid();

		model.setElencoSoggetti(soggettoService.ricercaSoggetti(idEnte, model.getCriteriSoggetti()));

		return SUCCESS;
	}

}
