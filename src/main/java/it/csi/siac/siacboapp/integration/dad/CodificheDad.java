/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.entity.SiacDEvento;
import it.csi.siac.siacboapp.integration.repository.SiacDEventoRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacDEventoWrapper;

@Component 
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CodificheDad extends BoBaseDad {
	
	@Autowired
	private SiacDEventoRepository siacDEventoRepository;

	private List<SiacDEventoWrapper> mapToEvento(List<SiacDEvento> elencoEventi) {
		
		List<SiacDEventoWrapper> elencoCalassificatoriWrapper = new ArrayList<SiacDEventoWrapper>();
		
		for (SiacDEvento sde : elencoEventi) {
			SiacDEventoWrapper evento = map(sde, SiacDEventoWrapper.class);
			elencoCalassificatoriWrapper.add(evento);
		}
		
		return elencoCalassificatoriWrapper;
	}

	public List<SiacDEventoWrapper> ricercaEventoModificaPianoDeiContiEntrata(String tipoMovimento, Integer idEnte) {
		
		final String methodName = "ricercaEventoModificaPianoDeiContiEntrata";
		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacDEvento> elencoCalassificatori = siacDEventoRepository.cercaEventiByTipoMovimentoEntrata( idEnte);
			return mapToEvento(elencoCalassificatori);
		
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		
		return null;
	}
	
	public List<SiacDEventoWrapper> ricercaEventoModificaPianoDeiContiSpesa(String tipoMovimento, Integer idEnte) {
		
		final String methodName = "ricercaEventoModificaPianoDeiContiSpesa";
		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacDEvento> elencoCalassificatori = siacDEventoRepository.cercaEventiByTipoMovimentoSpesa(idEnte);
			return mapToEvento(elencoCalassificatori);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		
		return null;
	}

}
