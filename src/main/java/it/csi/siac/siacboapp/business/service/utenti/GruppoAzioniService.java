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
import it.csi.siac.siacboapp.integration.dao.utenti.gruppoazioni.SiacDGruppoAzioniDao;
import it.csi.siac.siacboapp.integration.entity.SiacDGruppoAzioni;
import it.csi.siac.siacboapp.integration.repository.SiacDGruppoAzioniRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class GruppoAzioniService extends BoService {
	@Autowired
	private SiacDGruppoAzioniDao gruppoAzioniDao;

	@Autowired
	private SiacDGruppoAzioniRepository gruppoAzioniRepository;

	public void create(SiacDGruppoAzioni gruppo) {
		gruppoAzioniDao.create(gruppo);
	}

	public List<SiacDGruppoAzioni> getElencoGruppiAzioni(int enteId) {
		return gruppoAzioniRepository.getElencoGruppiAzioni(enteId);
	}

	public SiacDGruppoAzioni read(Integer uid, int enteId) {
		return gruppoAzioniRepository.findOne(uid, enteId);
	}

	public void update(SiacDGruppoAzioni gruppoAzioni) {
		gruppoAzioniDao.update(gruppoAzioni);
	}

}
