/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.soggetto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.SoggettoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.soggetto.ElencoSoggettiModel;
import it.csi.siac.siacboapp.integration.entity.SiacTSoggetto;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ElencoSoggettiAction extends GenericBoAction<ElencoSoggettiModel> {
	private static final long serialVersionUID = 5221371874273974078L;

	@Autowired
	private SoggettoService soggettoService;

	@Override
	public void prepare() throws Exception {
		super.prepare();

		List<SiacTSoggetto> elencoSoggetti = soggettoService.getElencoSoggetti(sessionHandler
				.getEnte().getUid());

		model.setElencoSoggetti(elencoSoggetti);
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

}
