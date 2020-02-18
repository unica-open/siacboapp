/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.AccountService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.account.AccountModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class EliminaAccountAction extends GenericBoAction<AccountModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 912884746634506056L;

	@Autowired
	private AccountService accountService;

	private Integer uid;

	@Override
	public String execute() throws Exception {
		int enteId = sessionHandler.getEnte().getUid();

		model.setAccountEntity(accountService.read(uid, enteId));

		model.getAccountEntity().setDataCancellazione(new Date());

		accountService.update(model.getAccountEntity());

		return SUCCESS;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
