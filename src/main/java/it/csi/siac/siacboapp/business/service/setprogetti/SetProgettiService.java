/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.setprogetti;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.integration.dao.setprogetti.SiacTFpvSetCronopDao;
import it.csi.siac.siacboapp.integration.entity.SiacRFpvSetCronop;
import it.csi.siac.siacboapp.integration.entity.SiacTFpvSetCronop;
import it.csi.siac.siacboapp.integration.entity.SiacTProgramma;
import it.csi.siac.siacboapp.integration.repository.SiacTFpvSetCronopRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTProgrammaRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class SetProgettiService {
	@Autowired
	private SiacTFpvSetCronopDao siacTFpvSetCronopDao;

	@Autowired
	private SiacTFpvSetCronopRepository siacTFpvSetCronopRepository;

	@Autowired
	private SiacTProgrammaRepository siacTProgrammaRepository;

	public void create(SiacTFpvSetCronop setProgetti, boolean sceltoGestione) {
		List<SiacTProgramma> elencoProgrammi = siacTProgrammaRepository
				.getElencoProgrammi(setProgetti.getEnteProprietario().getUid(),
						setProgetti.getBilancio().getUid());
		
		Set<SiacRFpvSetCronop> progetti = new HashSet<SiacRFpvSetCronop>();
		
		for (SiacTProgramma programma : elencoProgrammi) {
			SiacRFpvSetCronop progetto = new SiacRFpvSetCronop();
					
			if (sceltoGestione && programma.getEsisteCronoprogrammaGestione()) {
				progetto.setUsaGestione(Boolean.TRUE);
				progetto.setCronoprogramma(null);
			} else {
				progetto.setUsaGestione(Boolean.FALSE);
				progetto.setCronoprogramma(programma.getCronoprogrammi().iterator().next());
			}
			
			progetto.setSetProgetti(setProgetti);
			progetto.setProgramma(programma);
			
			progetto.setLoginOperazione(setProgetti.getLoginOperazione());
			progetto.setEnteProprietario(setProgetti.getEnteProprietario());
			progetto.setDataModificaInserimento(setProgetti.getDataCreazione());
			
			progetti.add(progetto);
		}
		
		setProgetti.setProgrammi(progetti);

		siacTFpvSetCronopDao.create(setProgetti);
	}

	public List<SiacTFpvSetCronop> getElencoSetProgetti(int bilancioId, int enteId) {
		return siacTFpvSetCronopRepository.getElencoSetProgetti(bilancioId, enteId);
	}

	public SiacTFpvSetCronop read(Integer uid, int enteId) {
		SiacTFpvSetCronop setProgetti = siacTFpvSetCronopRepository.findOne(uid, enteId);

		return setProgetti;
	}

	public void update(SiacTFpvSetCronop setProgetti) {

		siacTFpvSetCronopDao.update(setProgetti);
	}

	public void elimina(Integer setProgettiId, String loginOperazione) {

		siacTFpvSetCronopRepository.cancellaSetProgetti(setProgettiId, loginOperazione);
	}

}
