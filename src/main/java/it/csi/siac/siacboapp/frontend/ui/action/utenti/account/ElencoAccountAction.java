/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.AccountService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.account.ElencoAccountModel;
import it.csi.siac.siacboapp.integration.entity.SiacTAccount;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ElencoAccountAction extends GenericBoAction<ElencoAccountModel> {
	private static final long serialVersionUID = 5221371874273974078L;

	@Autowired
	private AccountService accountService;

	@Override
	public void prepare() throws Exception {
		super.prepare();

		List<SiacTAccount> elencoAccount = accountService.getElencoAccount(sessionHandler.getEnte()
				.getUid());

		model.setElencoAccount(elencoAccount);
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

}
