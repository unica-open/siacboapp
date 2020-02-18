/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.azione;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.AzioneService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.azione.ElencoAzioniModel;
import it.csi.siac.siacboapp.integration.entity.SiacTAzione;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ElencoAzioniAction extends GenericBoAction<ElencoAzioniModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3340761143861616687L;

	@Autowired
	private AzioneService azioneService;

	@Override
	public void prepare() throws Exception {
		super.prepare();

		List<SiacTAzione> elencoAzioni = azioneService.getElencoAzioni(sessionHandler.getEnte()
				.getUid());

		model.setElencoAzioni(elencoAzioni);
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
