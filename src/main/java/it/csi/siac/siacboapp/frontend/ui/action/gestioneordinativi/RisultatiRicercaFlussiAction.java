/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.gestioneordinativi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.gestioneordinativi.OrdinativoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi.CriteriRicercaFlussi;
import it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi.RisultatiRicercaFlussiModel;
import it.csi.siac.siacboapp.integration.entity.MifTFlussoElaborato;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RisultatiRicercaFlussiAction
		extends GenericBoAction<RisultatiRicercaFlussiModel> {
	private static final long serialVersionUID = -1422785685386636162L;
	
	@Autowired
	protected OrdinativoService ordinativoService;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		cerca();

		log.debugEnd(methodName, "");
	}

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void cerca() {
		CriteriRicercaFlussi criteri = sessionHandler.getParametro(
				BoSessionParameter.RICERCA_FLUSSI_CRITERI_RICERCA_FLUSSI, CriteriRicercaFlussi.class);

		int idEnte = sessionHandler.getEnte().getUid();

		List<MifTFlussoElaborato> elencoFlussi = ordinativoService.ricercaFlussi(idEnte, criteri);

		model.setElencoFlussi(elencoFlussi);
		model.setCodiceTipoFlussi(criteri.getCodiceTipo());
	}


	
	public String consultaFlusso() {
		return SUCCESS;
	}
	
}
