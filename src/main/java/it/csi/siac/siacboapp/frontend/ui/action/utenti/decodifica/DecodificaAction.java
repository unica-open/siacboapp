/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.decodifica;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.action.decodifiche.BaseDecodificaAction;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class DecodificaAction extends BaseDecodificaAction {

	private static final long serialVersionUID = -2578450698188346227L;

	@Override
	protected String getPostOperationActionName() {
		return "elencoDecodifiche";
	}
}
