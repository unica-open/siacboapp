/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.frontend.ui.model.common.CriteriRicercaProvvedimenti;
import it.csi.siac.siacboapp.integration.repository.SiacTAttoAmmRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTAttoAmmWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProvvedimentoDad extends BoBaseDad {

	@Autowired
	private SiacTAttoAmmRepository siacTAttoAmmRepository;
	
//	@Autowired
//	private TestDao testDao;
	
	public List<SiacTAttoAmmWrapper> ricercaProvvedimenti(Integer idEnte, CriteriRicercaProvvedimenti criteri) {

//		testDao.test();
		
		return mapList(siacTAttoAmmRepository.ricercaAttiAmministrativi(
				idEnte, 
				criteri.getAnno(), 
				criteri.getNumero(), 
				criteri.getIdTipo(), 
				criteri.getIdStrutturaAmministrativoContabile(), 
				criteri.getOggetto()
		), SiacTAttoAmmWrapper.class);
	}
}
