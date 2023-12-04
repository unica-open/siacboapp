/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.indicatori;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.model.indicatori.ConfIndicatoriEnum;
import it.csi.siac.siacboapp.frontend.ui.model.indicatori.ConfIndicatoriSpesaModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ConfIndicatoriSpesaAction extends BaseConfIndicatoriAnaliticiAction<ConfIndicatoriSpesaModel> {

	private static final long serialVersionUID = -5345586406527287298L;

	@Override
	public String execute() throws Exception
	{
		model.setElencoConfIndicatoriSpesa(indicatoriService.readElencoConfIndicatoriSpesa(getIdEnte(), getIdBilancio()));
		
		return super.execute();
	}
	
	@Override
	public String update() {

		indicatoriService.updateIndicatori(ConfIndicatoriEnum.SPESA.getEntityName(),
				ConfIndicatoriEnum.SPESA.getValueFields(), model.deserializeValues());
		
		return "confIndicatoriSpesa";
	}
}
