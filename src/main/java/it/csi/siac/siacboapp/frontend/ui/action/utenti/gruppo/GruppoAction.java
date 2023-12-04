/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.gruppo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.GruppoService;
import it.csi.siac.siacboapp.business.service.utenti.RuoloOpService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.gruppo.GruppoModel;
import it.csi.siac.siacboapp.integration.entity.SiacRGruppoRuoloOp;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.entity.SiacTGruppo;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class GruppoAction extends GenericBoAction<GruppoModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7874943551552866292L;

	@Autowired
	private GruppoService gruppoService;

	@Autowired
	private RuoloOpService ruoloOpService;

	private Integer uid;

	public void prepareExecute() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int enteId = sessionHandler.getEnte().getUid();

		if (uid != null)
			model.setGruppo(gruppoService.read(uid, enteId));

		model.setElencoRuoliOp(ruoloOpService.getElencoRuoliOp(enteId));
		// model.setElencoStruttureAmministrativeContabili(classificatoreService
		// .getElencoStruttureAmministrativeContabili(enteId));

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
		int enteId = sessionHandler.getEnte().getUid();

		String codiceFiscale = sessionHandler.getOperatore().getCodiceFiscale();

		SiacTGruppo gruppo = model.getGruppo();

		if (gruppo.getRuoliOp() != null)
			for (SiacRGruppoRuoloOp rop : gruppo.getRuoliOp())
				rop.init(enteId, codiceFiscale);

		gruppo.setLoginOperazione(codiceFiscale);
		gruppo.setEnteProprietario(new SiacTEnteProprietario(enteId));

		gruppoService.update(gruppo);

		return "elencoGruppi";

	}

	public String create() {
		int enteId = sessionHandler.getEnte().getUid();
		SiacTGruppo gruppo = model.getGruppo();
		String codiceFiscale = sessionHandler.getOperatore().getCodiceFiscale();

		if (gruppo.getRuoliOp() != null)
			for (SiacRGruppoRuoloOp rop : gruppo.getRuoliOp())
				rop.init(enteId, codiceFiscale);

		gruppo.setEnteProprietario(new SiacTEnteProprietario(enteId));
		gruppo.setLoginOperazione(codiceFiscale);

		gruppoService.create(gruppo);

		return "elencoGruppi";
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
