/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.ruoloop;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.RuoloOpService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.ruoloop.RuoloOpModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class EliminaRuoloOpAction extends GenericBoAction<RuoloOpModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 912884746634506056L;

	@Autowired
	private RuoloOpService ruoloOpService;

	private Integer uid;

	@Override
	public String execute() throws Exception {
		int enteId = sessionHandler.getEnte().getUid();

		model.setRuoloOp(ruoloOpService.read(uid, enteId));

		model.getRuoloOp().setDataCancellazione(new Date());

		ruoloOpService.update(model.getRuoloOp());

		return SUCCESS;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
