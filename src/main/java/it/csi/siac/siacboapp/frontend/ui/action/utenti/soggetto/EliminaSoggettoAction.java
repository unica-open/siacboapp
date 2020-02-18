/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.soggetto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.SoggettoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.soggetto.SoggettoModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class EliminaSoggettoAction extends GenericBoAction<SoggettoModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 912884746634506056L;

	@Autowired
	private SoggettoService soggettoService;

	private Integer uid;

	@Override
	public String execute() throws Exception {
		int enteId = sessionHandler.getEnte().getUid();

		model.setSoggetto(soggettoService.read(uid, enteId));

		model.getSoggetto().setDataCancellazione(new Date());

		soggettoService.update(model.getSoggetto());

		return SUCCESS;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
