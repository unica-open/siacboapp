/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.base;

import org.apache.commons.lang.StringUtils;

import it.csi.siac.siacboapp.frontend.ui.model.base.BaseAggiornaConBloccoRagioneriaModel;

public abstract class BaseAggiornaConBloccoRagioneriaAction<BACBRM extends BaseAggiornaConBloccoRagioneriaModel> extends GenericBoAction<BACBRM> {

	private static final long serialVersionUID = 6140900460307236811L;
	

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		log.debugEnd(methodName, "");
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
	

	
	protected String defaultGetCodiceInc() {
		return String.format("%s-%s", 
				sessionHandler.getRichiedente().getOperatore().getCodiceFiscale(), 
				StringUtils.isBlank(model.getCodiceInc()) ? getDefaultCodiceInc() : model.getCodiceInc());
	}

	protected abstract String getDefaultCodiceInc();
	
	
}
