/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.ruoloop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.RuoloOpService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.ruoloop.ElencoRuoliOpModel;
import it.csi.siac.siacboapp.integration.entity.SiacDRuoloOp;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ElencoRuoliOpAction extends GenericBoAction<ElencoRuoliOpModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8138418859203081585L;
	@Autowired
	private RuoloOpService ruoloOpService;

	@Override
	public void prepare() throws Exception {
		super.prepare();

		List<SiacDRuoloOp> elencoRuoliOp = ruoloOpService.getElencoRuoliOp(sessionHandler.getEnte()
				.getUid());

		model.setElencoRuoliOp(elencoRuoliOp);
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
