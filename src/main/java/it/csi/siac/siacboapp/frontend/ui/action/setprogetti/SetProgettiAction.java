/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.setprogetti;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.setprogetti.SetProgettiService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.setprogetti.SetProgettiModel;
import it.csi.siac.siacboapp.integration.entity.SiacTBil;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.entity.SiacTFpvSetCronop;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class SetProgettiAction extends GenericBoAction<SetProgettiModel> {
	private static final long serialVersionUID = 5358402191943338026L;


	@Autowired
	private SetProgettiService setProgettiService;

	private Integer uid;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int enteId = sessionHandler.getEnte().getUid();

		if (uid != null)
			model.setSetProgetti(setProgettiService.read(uid, enteId));

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
		if (hasErrori())
			return INPUT;
		
		setProgettiService.update(initSetProgetti());

		return "elencoSetProgetti";
	}

	public String delete() {
		
		setProgettiService.elimina(uid, sessionHandler.getRichiedente().getOperatore().getCodiceFiscale());
		
		return "elencoSetProgetti";
	}
	
	public void validateUpdate() {
		_validate();
	}

	public void validateCreate() {
		_validate();
	}

	private void _validate() {
		SiacTFpvSetCronop setProgetti = model.getSetProgetti();
		
		if (StringUtils.isBlank(setProgetti.getCodice()))
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("codice"));

		if (StringUtils.isBlank(setProgetti.getDescrizione()))
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("descrizione"));
		
		if (StringUtils.isBlank(model.getInitTipoCronoprogramma()))
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("preimposta crono"));
	}

	
	public String create() {
		if (hasErrori())
			return INPUT;
		
		SiacTFpvSetCronop setProgetti = initSetProgetti();
		
		setProgetti.setBilancio(new SiacTBil(sessionHandler.getBilancio().getUid()));
		
		setProgettiService.create(setProgetti, "GEST".equals(model.getInitTipoCronoprogramma()));
		
		return "elencoSetProgetti";
	}

	private SiacTFpvSetCronop initSetProgetti() {
		SiacTFpvSetCronop setProgetti = model.getSetProgetti();

		int enteId = sessionHandler.getEnte().getUid();
		String codiceFiscale = sessionHandler.getOperatore().getCodiceFiscale();

	
		setProgetti.setLoginOperazione(codiceFiscale);
		setProgetti.setEnteProprietario(new SiacTEnteProprietario(enteId));
		setProgetti.setDataModificaInserimentoIfNotSet(new Date());

		return setProgetti;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
