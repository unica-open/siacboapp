/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.indicatori;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.model.indicatori.ConfIndicatoriEnum;
import it.csi.siac.siacboapp.frontend.ui.model.indicatori.ConfIndicatoriSinteticiModel;

@Component
@Primary
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ConfIndicatoriSinteticiAction extends BaseConfIndicatoriAction<ConfIndicatoriSinteticiModel> {

	private static final long serialVersionUID = -5345586406527287298L;

	
	@Override
	public String execute() throws Exception
	{
		model.setElencoConfIndicatoriSintetici(indicatoriService.readElencoConfIndicatoriSintetici(getIdEnte(), getIdBilancio()));
		
		return super.execute();
	}

	@Override
	public String update() {

		indicatoriService.updateIndicatori(ConfIndicatoriEnum.SINT.getEntityName(),
				ConfIndicatoriEnum.SINT.getValueFields(), model.deserializeValues());
		
		return getUpdateReturn();
	}

	protected String getUpdateReturn()
	{
		return "confIndicatoriSintetici";
	}
}
