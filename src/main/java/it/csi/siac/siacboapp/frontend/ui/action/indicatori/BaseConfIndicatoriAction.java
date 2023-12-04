/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.indicatori;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siacboapp.business.service.indicatori.IndicatoriService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.indicatori.BaseConfIndicatoriModel;

public abstract class BaseConfIndicatoriAction<CIM extends BaseConfIndicatoriModel> extends GenericBoAction<CIM> {

	private static final long serialVersionUID = -5345586406527287298L;

	@Autowired
	protected IndicatoriService indicatoriService;

	@Override
	public void prepare() throws Exception {
		super.prepare();

		model.setAnno(sessionHandler.getBilancio().getAnno());
	}

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}
	
	public abstract String update();
	
	protected Integer getIdBilancio()
	{
		return sessionHandler.getBilancio().getUid();
	}

	protected Integer getIdEnte()
	{
		return sessionHandler.getEnte().getUid();
	}

	@Override
	public String execute() throws Exception
	{
		Integer numeroAnni = indicatoriService.readNumeroAnniBilancioPrevisione(getIdEnte(), sessionHandler.getBilancio().getAnno());
		
		if (numeroAnni == null) {
			numeroAnni = Integer.MAX_VALUE;
		}
		
		model.setNumeroAnniBilancioPrevisone(numeroAnni);
		
		
		return super.execute();
	}
}
