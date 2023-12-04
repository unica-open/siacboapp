/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.propostepreliminari;

import org.apache.commons.lang.StringUtils;

import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.messaggio.MessaggioBo;
import it.csi.siac.siaccommonapp.model.GenericModel;

public abstract class BasePropostePreliminariAction<M extends GenericModel> extends GenericBoAction<M> {
	private static final long serialVersionUID = -1768768598424260002L;
	
	protected String tipoProposta;

	public String getTipoProposta() {
		return tipoProposta;
	}
	

	public String capitalize(String str) {
		return StringUtils.capitalize(str);
	}
	
	public boolean isAzioneSettore() {
		return sessionHandler.getAzione().getNome().endsWith("Settore");
	}
	
	public boolean isAzioneDirigente() {
		return sessionHandler.getAzione().getNome().endsWith("Dirigente");
	}
	
	protected void addSuccessMessage() {
		addRedirectActionMessage(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
	}
}
