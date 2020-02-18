/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.decodifica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.DecodificaService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.decodifica.ElencoDecodificheModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ElencoDecodificheAction extends GenericBoAction<ElencoDecodificheModel> {

	private static final long serialVersionUID = 3340761143861616687L;

	@Autowired
	private DecodificaService decodificaService;

	@Override
	public void prepare() throws Exception {
		super.prepare();

		model.setElencoTabelleDecodifica(decodificaService.getElencoTabelleDecodifica());
	}

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		if (model.getIdTabellaSelezionata() != null)
			model.setElencoDecodifiche(decodificaService.getElencoDecodifiche(model.getIdTabellaSelezionata(),
					sessionHandler.getEnte()));

		return SUCCESS;
	}

}
