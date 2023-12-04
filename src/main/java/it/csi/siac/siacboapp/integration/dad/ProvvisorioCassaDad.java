/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.frontend.ui.model.common.CriteriRicercaProvvisoriCassa;
import it.csi.siac.siacboapp.integration.entity.SiacTProvCassa;
import it.csi.siac.siacboapp.integration.repository.SiacTProvCassaRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTProvCassaWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProvvisorioCassaDad extends BoBaseDad {

	@Autowired
	private SiacTProvCassaRepository siacTProvCassaRepository;
	
	public List<SiacTProvCassaWrapper> ricercaProvvisoriCassa(Integer idEnte, CriteriRicercaProvvisoriCassa criteri) {
		final String methodName = "ricercaProvvisoriCassa";

		List<SiacTProvCassaWrapper> elencoProvvisoriCassa = new ArrayList<SiacTProvCassaWrapper>(); 

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
		
			for (SiacTProvCassa pc : siacTProvCassaRepository.ricercaProvvisoriCassa( 
					idEnte, 
					criteri.getAnnoDa(), 
					criteri.getAnnoA(), 
					criteri.getNumeroDa(), 
					criteri.getNumeroA(), 
					StringUtils.isBlank(criteri.getDescrizioneCausale()) ? null : criteri.getDescrizioneCausale(),
					StringUtils.isBlank(criteri.getDescrizioneSottoCausale()) ? null : criteri.getDescrizioneSottoCausale(),
					StringUtils.isBlank(criteri.getDescrizioneSoggetto()) ? null : criteri.getDescrizioneSoggetto(),
					criteri.getCodiceTipo(),
					criteri.getDataEmissioneDa(),
					criteri.getDataEmissioneA(),
					criteri.getImportoDa(),
					criteri.getImportoA(),
					criteri.getDataRegolarizzazionePresente(),
					criteri.getDataAnnullamentoPresente())) {
				
				BigDecimal importoDaRegolarizzare = siacTProvCassaRepository.calcolaImportoDaRegolarizzare(pc.getUid());
				
				if (! criteri.isImportoDaRegolarizzarePositivo() || importoDaRegolarizzare.compareTo(BigDecimal.ZERO) > 0) {
					SiacTProvCassaWrapper pcw = map(pc, SiacTProvCassaWrapper.class);
					
					pcw.setImportoDaRegolarizzare(importoDaRegolarizzare);
					elencoProvvisoriCassa.add(pcw);
				}
			}
		} catch (PersistenceException e) {
			log.error(methodName, e);
			
			checkForLongSearch(currentTimeMillis0);
		}
		
		return elencoProvvisoriCassa; 
	}
}
