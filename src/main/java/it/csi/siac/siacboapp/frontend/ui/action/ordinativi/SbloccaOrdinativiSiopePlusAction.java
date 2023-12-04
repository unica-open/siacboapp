/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.ordinativi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.ordinativi.BaseOrdinativoService;
import it.csi.siac.siacboapp.business.service.ordinativi.OrdinativoSiopePlusService;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class SbloccaOrdinativiSiopePlusAction extends SbloccaOrdinativiAction {
	private static final long serialVersionUID = 1887025564047063704L;

	@Autowired
	protected OrdinativoSiopePlusService ordinativoSiopePlusService;

	@Override
	protected BaseOrdinativoService getOrdinativoService() {
		return ordinativoSiopePlusService;
	}
}