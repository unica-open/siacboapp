/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.ordinativi;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.AssociaProvvisoriCassaModel;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.CriteriRicercaOrdinativi;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTOrdinativoImportoMapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class AssociaProvvisoriCassaAction extends ConfermaGestioneOrdinativiAction<AssociaProvvisoriCassaModel> {

	private static final long serialVersionUID = 4258370401421134191L;  

	@SuppressWarnings("unchecked")
	@Override
	protected List<SiacTOrdinativoWrapper> ricercaOrdinativi(int idEnte, CriteriRicercaOrdinativi criteri) {
		return getOrdinativoService().ricercaOrdinativi(idEnte, criteri, SiacTOrdinativoImportoMapper.class);
	}
	
	@Override
	public void executeConferma() throws Exception {
		getOrdinativoService().associaOrdinativiProvvisoriCassa(model.getIdOrdinativi(), model.getIdProvvisoriCassa(), 
				sessionHandler.getOperatore().getCodiceFiscale());
	}

}