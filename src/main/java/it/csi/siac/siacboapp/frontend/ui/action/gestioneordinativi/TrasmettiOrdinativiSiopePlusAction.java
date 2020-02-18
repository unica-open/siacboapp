/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.gestioneordinativi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.gestioneordinativi.BaseOrdinativoService;
import it.csi.siac.siacboapp.business.service.gestioneordinativi.OrdinativoSiopePlusService;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class TrasmettiOrdinativiSiopePlusAction extends TrasmettiOrdinativiAction {
	private static final long serialVersionUID = 3400467158052313587L;

	@Autowired
	protected OrdinativoSiopePlusService ordinativoSiopePlusService;

	@Override
	protected BaseOrdinativoService getOrdinativoService() {
		return ordinativoSiopePlusService;
	}
}
