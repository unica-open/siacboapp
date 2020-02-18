/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.utenti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.integration.dao.utenti.account.SiacTAccountDao;
import it.csi.siac.siacboapp.integration.entity.SiacRSoggettoRuolo;
import it.csi.siac.siacboapp.integration.entity.SiacTAccount;
import it.csi.siac.siacboapp.integration.entity.SiacTGruppo;
import it.csi.siac.siacboapp.integration.repository.SiacRAccountCassaEconRepository;
import it.csi.siac.siacboapp.integration.repository.SiacRAccountClassRepository;
import it.csi.siac.siacboapp.integration.repository.SiacRAccountRuoloOpRepository;
import it.csi.siac.siacboapp.integration.repository.SiacRGruppoAccountRepository;
import it.csi.siac.siacboapp.integration.repository.SiacRSoggettoRuoloRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTAccountRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTGruppoRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class AccountService extends BoService {
	@Autowired
	private SiacTAccountDao accountDao;

	@Autowired
	private SiacTAccountRepository siacTAccountRepository;

	@Autowired
	private SiacTGruppoRepository siacTGruppoRepository;

	@Autowired
	private SiacRGruppoAccountRepository siacRGruppoAccountRepository;

	@Autowired
	private SiacRAccountRuoloOpRepository siacRAccountRuoloOpRepository;

	@Autowired
	private SiacRAccountCassaEconRepository siacRAccountCassaEconRepository;

	@Autowired
	private SiacRAccountClassRepository siacRAccountClassRepository;

	@Autowired
	private SiacRSoggettoRuoloRepository soggettoRuoloRepository;

	public void create(SiacTAccount account) {
		accountDao.create(account);
	}

	public List<SiacTAccount> getElencoAccount(int enteId) {
		return siacTAccountRepository.getElencoAccount(enteId);
	}

	public SiacTAccount read(Integer uid, int enteId) {
		SiacTAccount siacTAccount = siacTAccountRepository.findOne(uid, enteId);

		fetchEntities(siacTAccount.getGruppi());
		fetchEntities(siacTAccount.getRuoliOp());
		fetchEntities(siacTAccount.getStruttureAmministrativeContabili());
		fetchEntities(siacTAccount.getCasseEconomali());

		return siacTAccount;
	}

	public void update(SiacTAccount account) {
		siacRGruppoAccountRepository.deleteByAccount(account.getUid());
		siacRAccountRuoloOpRepository.deleteByAccount(account.getUid());
		siacRAccountCassaEconRepository.deleteByAccount(account.getUid());
		siacRAccountClassRepository.deleteByAccount(account.getUid());

		accountDao.update(account);
	}

	public List<SiacRSoggettoRuolo> getElencoSoggettiRuoli(int enteId) {
		return soggettoRuoloRepository.getElencoSoggettiRuoli(enteId);
	}

	public List<SiacTGruppo> getElencoGruppi(int enteId) {
		return siacTGruppoRepository.getElencoGruppi(enteId);
	}

}
