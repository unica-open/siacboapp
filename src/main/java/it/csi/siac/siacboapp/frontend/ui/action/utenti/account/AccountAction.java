/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.account;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.AccountService;
import it.csi.siac.siacboapp.business.service.utenti.CassaEconomaleService;
import it.csi.siac.siacboapp.business.service.utenti.ClassificatoreService;
import it.csi.siac.siacboapp.business.service.utenti.RuoloOpService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.account.AccountModel;
import it.csi.siac.siacboapp.frontend.ui.util.comparator.SiacTClassComparator;
import it.csi.siac.siacboapp.integration.entity.SiacRAccountCassaEcon;
import it.csi.siac.siacboapp.integration.entity.SiacRAccountClass;
import it.csi.siac.siacboapp.integration.entity.SiacRAccountRuoloOp;
import it.csi.siac.siacboapp.integration.entity.SiacRGruppoAccount;
import it.csi.siac.siacboapp.integration.entity.SiacTAccount;
import it.csi.siac.siacboapp.integration.entity.SiacTClass;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class AccountAction extends GenericBoAction<AccountModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7874943551552866292L;

	@Autowired
	private AccountService accountService;

	@Autowired
	private RuoloOpService ruoloOpService;

	@Autowired
	private ClassificatoreService classificatoreService;

	@Autowired
	private CassaEconomaleService cassaEconomaleService;

	private Integer uid;

	public void prepareExecute() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int enteId = sessionHandler.getEnte().getUid();

		if (uid != null)
			model.setAccountEntity(accountService.read(uid, enteId));

		model.setElencoSoggettiRuoli(accountService.getElencoSoggettiRuoli(enteId));
		model.setElencoGruppi(accountService.getElencoGruppi(enteId));
		model.setElencoRuoliOp(ruoloOpService.getElencoRuoliOp(enteId));
		
		
		model.setElencoCasseEconomali(cassaEconomaleService.getElencoCasseEconomali(enteId, sessionHandler.getBilancio().getUid()));

		log.debugEnd(methodName, "");
	}

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		int enteId = sessionHandler.getEnte().getUid();

		List<SiacTClass> elencoStruttureAmministrativeContabili = classificatoreService
				.getElencoStruttureAmministrativeContabili(enteId, sessionHandler.getAnnoBilancio());
		
		Collections.sort(elencoStruttureAmministrativeContabili, SiacTClassComparator.INSTANCE);
		
		//model.setElencoStruttureAmministrativeContabili(elencoStruttureAmministrativeContabili);
		
		model.setElencoStruttureAmministrativoContabiliJson(elencoStruttureAmministrativeContabili);

		return SUCCESS;
	}

	public String update() {
		accountService.update(initAccount(), sessionHandler.getAnnoBilancio());

		return "elencoAccount";
	}

	public String create() {
		accountService.create(initAccount(), sessionHandler.getAnnoBilancio());

		return "elencoAccount";
	}

	private SiacTAccount initAccount() {
		SiacTAccount account = model.getAccountEntity();

		int enteId = sessionHandler.getEnte().getUid();
		String codiceFiscale = sessionHandler.getOperatore().getCodiceFiscale();

		if (account.getRuoliOp() != null)
			for (SiacRAccountRuoloOp rop : account.getRuoliOp())
				rop.init(enteId, codiceFiscale);

		if (account.getStruttureAmministrativeContabili() != null)
			for (SiacRAccountClass asac : account.getStruttureAmministrativeContabili())
				asac.init(enteId, codiceFiscale);

		if (account.getCasseEconomali() != null)
			for (SiacRAccountCassaEcon ace : account.getCasseEconomali())
				ace.init(enteId, codiceFiscale);

		if (account.getGruppi() != null)
			for (SiacRGruppoAccount ag : account.getGruppi())
				ag.init(enteId, codiceFiscale);

		account.setLoginOperazione(codiceFiscale);
		account.setEnteProprietario(new SiacTEnteProprietario(enteId));

		return account;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
