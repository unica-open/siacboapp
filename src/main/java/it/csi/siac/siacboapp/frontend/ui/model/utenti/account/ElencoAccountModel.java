/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.account;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTAccount;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoAccountModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3985700813931064038L;
	/**
	 * 
	 */

	private List<SiacTAccount> elencoAccount;

	public List<SiacTAccount> getElencoAccount() {
		return elencoAccount;
	}

	public void setElencoAccount(List<SiacTAccount> elencoAccount) {
		this.elencoAccount = elencoAccount;
	}

}
