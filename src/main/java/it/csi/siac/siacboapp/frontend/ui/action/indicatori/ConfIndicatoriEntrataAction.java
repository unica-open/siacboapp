/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.indicatori;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.model.indicatori.ConfIndicatoriEntrataModel;
import it.csi.siac.siacboapp.frontend.ui.model.indicatori.ConfIndicatoriEnum;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ConfIndicatoriEntrataAction extends BaseConfIndicatoriAnaliticiAction<ConfIndicatoriEntrataModel> {

	private static final long serialVersionUID = -5345586406527287298L;


	@Override
	public String execute() throws Exception
	{
		model.setElencoConfIndicatoriEntrata(indicatoriService.readElencoConfIndicatoriEntrata(getIdEnte(), getIdBilancio()));
		
		return super.execute();
	}
	
	@Override
	public String update() {

		indicatoriService.updateIndicatori(ConfIndicatoriEnum.ENTRATA.getEntityName(),
				ConfIndicatoriEnum.ENTRATA.getValueFields(), model.deserializeValues());
		
		return "confIndicatoriEntrata";
	}
}
