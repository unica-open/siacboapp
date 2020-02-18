/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.gruppoazioni;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.GruppoAzioniService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.gruppoazioni.ElencoGruppiAzioniModel;
import it.csi.siac.siacboapp.integration.entity.SiacDGruppoAzioni;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ElencoGruppiAzioniAction extends GenericBoAction<ElencoGruppiAzioniModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8779235375250377973L;
	@Autowired
	private GruppoAzioniService gruppoAzioniService;

	@Override
	public void prepare() throws Exception {
		super.prepare();

		List<SiacDGruppoAzioni> elencoGruppiAzioni = gruppoAzioniService
				.getElencoGruppiAzioni(sessionHandler.getEnte().getUid());

		model.setElencoGruppiAzioni(elencoGruppiAzioni);
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
