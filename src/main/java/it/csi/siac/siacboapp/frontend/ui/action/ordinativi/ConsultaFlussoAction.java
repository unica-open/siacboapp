/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.ordinativi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.ordinativi.OrdinativoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.ConsultaFlussoModel;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ConsultaFlussoAction extends GenericBoAction<ConsultaFlussoModel> {
	private static final long serialVersionUID = -483226945282125901L;

	@Autowired
	protected OrdinativoService ordinativoService;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

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

	public String cerca() {
		List<SiacTOrdinativoWrapper> elencoOrdinativi = ordinativoService.ricercaOrdinativiDaFlusso(model.getUid(), 
				model.getAnno(), model.getCodiceTipoOrdinativi());

		model.setElencoOrdinativi(elencoOrdinativi);
		
		return SUCCESS;
	}
	
}
