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

import it.csi.siac.siacboapp.frontend.ui.model.common.CriteriRicercaSoggetti;
import it.csi.siac.siacboapp.integration.repository.SiacTSoggettoRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SoggettoDad extends BoBaseDad {

	@Autowired
	private SiacTSoggettoRepository siacTSoggettoRepository;
	
	public List<SiacTSoggettoWrapper> ricercaSoggetti(Integer idEnte, CriteriRicercaSoggetti criteri) {

		return mapList(siacTSoggettoRepository.ricercaSoggetti(
				idEnte, 
				criteri.getCodice(), 
				criteri.getCodiceFiscale(), 
				criteri.getPartitaIva(), 
				criteri.getDenominazione(), 
				criteri.getIdClasse()
		), SiacTSoggettoWrapper.class);
	}
}
