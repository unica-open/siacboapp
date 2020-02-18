/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.gruppoazioni;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.GruppoAzioniService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.gruppoazioni.GruppoAzioniModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class EliminaGruppoAzioniAction extends GenericBoAction<GruppoAzioniModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 912884746634506056L;

	@Autowired
	private GruppoAzioniService gruppoAzioniService;

	private Integer uid;

	@Override
	public String execute() throws Exception {
		int enteId = sessionHandler.getEnte().getUid();

		model.setGruppoAzioni(gruppoAzioniService.read(uid, enteId));

		model.getGruppoAzioni().setDataCancellazione(new Date());

		gruppoAzioniService.update(model.getGruppoAzioni());

		return SUCCESS;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
