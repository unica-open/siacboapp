/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.ordinativi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.GestioneDaTrasmettereOrdinativiModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public abstract class BaseGestioneDaTrasmettereOrdinativiAction<GDTOM extends GestioneDaTrasmettereOrdinativiModel> 
	extends ConfermaGestioneOrdinativiAction<GDTOM> {

	private static final long serialVersionUID = 4072801523610586161L;

	protected void setDaTrasmettere(Boolean daTrasmettere) {
		List<Integer> idOrdinativi = new ArrayList<Integer>();
		
		for (Integer idOrdinativo : model.getIdOrdinativi()) {
			idOrdinativi.add(idOrdinativo);
		}
		
		ordinativoService.setDaTrasmettere(idOrdinativi, daTrasmettere);
	}
	
}