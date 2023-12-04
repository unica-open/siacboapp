/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.indicatori;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ConfIndicatoriSinteticiMissione13Action extends ConfIndicatoriSinteticiAction {

	private static final long serialVersionUID = 7541060499200775817L;

	@Override
	protected String getUpdateReturn()
	{
		return "confIndicatoriSinteticiMissione13";
	}
}
