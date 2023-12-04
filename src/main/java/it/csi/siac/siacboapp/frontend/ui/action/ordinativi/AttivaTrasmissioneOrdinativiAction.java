/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.ordinativi;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.AttivaTrasmissioneOrdinativiModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class AttivaTrasmissioneOrdinativiAction
		extends BaseGestioneDaTrasmettereOrdinativiAction<AttivaTrasmissioneOrdinativiModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void executeConferma() throws Exception {
		setDaTrasmettere(true);
	}

}