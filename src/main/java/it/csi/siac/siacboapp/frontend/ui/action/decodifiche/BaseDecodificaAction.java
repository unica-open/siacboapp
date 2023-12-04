/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.decodifiche;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siacboapp.business.service.utenti.DecodificaService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.decodifica.DecodificaModel;
import it.csi.siac.siacboapp.integration.entity.BkoTSystemTable;
import it.csi.siac.siacboapp.integration.entity.SiacDecodifica;

public abstract class BaseDecodificaAction extends GenericBoAction<DecodificaModel> {

	private static final long serialVersionUID = -2578450698188346227L;

	private Integer uid;

	@Autowired
	protected DecodificaService decodificaService;

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		Integer idTabella = model.getDecodifica().getTabella().getId();
		
		if (uid != null)
			model.setDecodifica(decodificaService.read(uid, sessionHandler.getEnte(), idTabella));  
		else {
			SiacDecodifica decodifica = new SiacDecodifica();
			
			BkoTSystemTable tabella = decodificaService.getDettaglioTabella(idTabella);
			decodifica.setTabella(tabella);
			
			decodifica.setForeignKeyValues(decodificaService.getForeignKeyValues(tabella, sessionHandler.getEnte()));

			model.setDecodifica(decodifica);
		}
		
		return SUCCESS;
	}

	public String update() {
		decodificaService.update(model.getDecodifica());

		return getPostOperationActionName();
	}

	public String create() {
		decodificaService.create(model.getDecodifica(), sessionHandler.getEnte(), sessionHandler.getOperatore()
				.getCodiceFiscale());

		return getPostOperationActionName();
	}

	protected abstract String getPostOperationActionName();

	public String delete() {
		decodificaService.delete(model.getDecodifica());

		return getPostOperationActionName();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
