/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.gruppoazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.GruppoAzioniService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.gruppoazioni.GruppoAzioniModel;
import it.csi.siac.siacboapp.integration.entity.SiacDGruppoAzioni;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class GruppoAzioniAction extends GenericBoAction<GruppoAzioniModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1473932076038756220L;

	@Autowired
	private GruppoAzioniService gruppoAzioniService;

	private Integer uid;

	public void prepareExecute() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int enteId = sessionHandler.getEnte().getUid();

		if (uid != null)
			model.setGruppoAzioni(gruppoAzioniService.read(uid, enteId));

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

	public String update() {
		gruppoAzioniService.update(initGruppoAzioni());

		return "elencoGruppiAzioni";

	}

	public String create() {
		gruppoAzioniService.create(initGruppoAzioni());

		return "elencoGruppiAzioni";
	}

	private SiacDGruppoAzioni initGruppoAzioni() {
		SiacDGruppoAzioni gruppoAzioni = model.getGruppoAzioni();

		int enteId = sessionHandler.getEnte().getUid();

		String codiceFiscale = sessionHandler.getOperatore().getCodiceFiscale();

		gruppoAzioni.setEnteProprietario(new SiacTEnteProprietario(enteId));
		gruppoAzioni.setLoginOperazione(codiceFiscale);
		
		return gruppoAzioni;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
