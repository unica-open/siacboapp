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
import it.csi.siac.siacboapp.integration.dao.utenti.gruppo.SiacTGruppoDao;
import it.csi.siac.siacboapp.integration.entity.SiacTGruppo;
import it.csi.siac.siacboapp.integration.repository.SiacRGruppoRuoloOpRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTGruppoRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class GruppoService extends BoService {
	@Autowired
	private SiacTGruppoDao gruppoDao;

	@Autowired
	private SiacTGruppoRepository gruppoRepository;

	@Autowired
	private SiacRGruppoRuoloOpRepository siacRGruppoRuoloOpRepository;

	public void create(SiacTGruppo gruppo) {
		gruppoDao.create(gruppo);
	}

	public List<SiacTGruppo> getElencoGruppi(int enteId) {
		return gruppoRepository.getElencoGruppi(enteId);
	}

	public SiacTGruppo read(Integer uid, int enteId) {
		SiacTGruppo siacTGruppo = gruppoRepository.findOne(uid, enteId);
		
		fetchEntities(siacTGruppo.getRuoliOp());
		
		return siacTGruppo;
	}

	public void update(SiacTGruppo gruppo) {
		siacRGruppoRuoloOpRepository.deleteByGruppo(gruppo.getUid());

		gruppoDao.update(gruppo);
	}

}
