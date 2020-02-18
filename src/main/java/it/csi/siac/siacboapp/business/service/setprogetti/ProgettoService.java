/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.setprogetti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.integration.entity.SiacRFpvSetCronop;
import it.csi.siac.siacboapp.integration.entity.SiacTCronop;
import it.csi.siac.siacboapp.integration.repository.SiacRFpvSetCronopRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTCronopRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class ProgettoService {
	@Autowired
	private SiacRFpvSetCronopRepository siacRFpvSetCronopRepository;

	@Autowired
	private SiacTCronopRepository siacTCronopRepository;

	
	public List<SiacRFpvSetCronop> getElencoProgetti(int setProgettiId, int enteId) {
		return siacRFpvSetCronopRepository.getElencoProgetti(setProgettiId, enteId);
	}

	public SiacRFpvSetCronop read(Integer uid, int enteId) {
		SiacRFpvSetCronop r = siacRFpvSetCronopRepository.findOne(uid);

		r.getProgramma().initEsisteCronoprogrammaGestione();
		
		return r;

	}

	public List<SiacTCronop> getElencoCronoprogrammi(Integer programmaId, int bilancioId) {
		return siacTCronopRepository.getElencoCronoprogrammi(programmaId, bilancioId);
	}

	public void update(SiacRFpvSetCronop progetto) {
		siacRFpvSetCronopRepository.updateCronoprogramma(progetto.getUid(), progetto.getCronoprogramma());		
	}
}
