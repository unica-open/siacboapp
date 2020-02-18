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

import it.csi.siac.siacboapp.business.service.setprogetti.ProgettoService;
import it.csi.siac.siacboapp.business.service.setprogetti.SetProgettiService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.setprogetti.ElencoProgettiModel;
import it.csi.siac.siacboapp.integration.entity.SiacRFpvSetCronop;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ElencoProgettiAction extends GenericBoAction<ElencoProgettiModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1229153475270135664L;
	/**
	 * 
	 */

	@Autowired
	private ProgettoService progettoService;

	@Autowired
	private SetProgettiService setProgettiService;

	private int setProgettiId;

	@Override
	public void prepare() throws Exception {
		super.prepare();

		int enteId = sessionHandler.getEnte().getUid();
		
		List<SiacRFpvSetCronop> elencoProgetti = progettoService.getElencoProgetti(setProgettiId, enteId);
		
		model.setElencoProgetti(elencoProgetti);
		
		model.setSetProgetti(setProgettiService.read(setProgettiId, enteId));
		
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

	public int getSetProgettiId() {
		return setProgettiId;
	}

	public void setSetProgettiId(int setProgettiId) {
		this.setProgettiId = setProgettiId;
	}

}
