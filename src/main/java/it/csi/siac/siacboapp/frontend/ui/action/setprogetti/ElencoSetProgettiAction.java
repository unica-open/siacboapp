/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.setprogetti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.setprogetti.SetProgettiService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.setprogetti.ElencoSetProgettiModel;
import it.csi.siac.siacboapp.integration.entity.SiacTFpvSetCronop;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ElencoSetProgettiAction extends GenericBoAction<ElencoSetProgettiModel> {


	private static final long serialVersionUID = 6702738807881887736L;

	@Autowired
	private SetProgettiService setProgettiService;

	@Override
	public void prepare() throws Exception {
		super.prepare();

		List<SiacTFpvSetCronop> elencoSetProgetti = setProgettiService.getElencoSetProgetti(sessionHandler.getBilancio().getUid(),
				sessionHandler.getEnte()
				.getUid());

		model.setElencoSetProgetti(elencoSetProgetti);
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
