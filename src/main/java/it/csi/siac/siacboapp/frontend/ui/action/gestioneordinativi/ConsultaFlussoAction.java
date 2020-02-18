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
import it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi.ConsultaFlussoModel;
import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ConsultaFlussoAction extends GenericBoAction<ConsultaFlussoModel> {
	private static final long serialVersionUID = -483226945282125901L;

	private Integer uid;
	private String codiceTipoOrdinativi;
	
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
		List<SiacTOrdinativo> elencoOrdinativi = ordinativoService.ricercaOrdinativiDaFlusso(uid, codiceTipoOrdinativi);

		model.setElencoOrdinativi(elencoOrdinativi);
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCodiceTipoOrdinativi() {
		return codiceTipoOrdinativi;
	}

	public void setCodiceTipoOrdinativi(String codiceTipoOrdinativi) {
		this.codiceTipoOrdinativi = codiceTipoOrdinativi;
	}
}
