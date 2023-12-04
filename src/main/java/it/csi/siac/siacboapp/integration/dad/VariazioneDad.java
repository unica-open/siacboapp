/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.frontend.ui.model.variazioni.CriteriRicercaVariazioni;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTVariazioneStatoMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapper;
import it.csi.siac.siacboapp.integration.entity.SiacTVariazione;
import it.csi.siac.siacboapp.integration.repository.SiacTVariazioneRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTVariazioneWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class VariazioneDad extends BoBaseDad {

	@Autowired
	private SiacTVariazioneRepository siacTVariazioneRepository;


	public SiacTVariazioneWrapper cercaVariazione(int idEnte, int idBilancio, CriteriRicercaVariazioni criteri, Class<? extends EntityWrapperMapper>... mapperTypes) {

		long currentTimeMillis0 = System.currentTimeMillis();

		try {

			List<SiacTVariazione> list = siacTVariazioneRepository.findByNumero(idEnte, idBilancio, criteri.getNumero());

			if (list.isEmpty()) {
				throw new BusinessException(ErroreCore.NESSUN_DATO_REPERITO.getErrore());
			}

			if (list.size() > 1) {
				throw new BusinessException(ErroreCore.RICERCA_TROPPO_ESTESA.getErrore());
			}

			return mapToWrapper(list.get(0));

		}
		catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}

		return null;
	}


	private SiacTVariazioneWrapper mapToWrapper(SiacTVariazione siacTVariazione) {
		SiacTVariazioneWrapper siacTVariazioneWrapper = map(siacTVariazione, SiacTVariazioneWrapper.class);
		
		mapEntityToWrapper(siacTVariazione, siacTVariazioneWrapper, SiacTVariazioneStatoMapper.class);
		
		return siacTVariazioneWrapper; 
	}

	public void definisciVariazione(int idEnte, Integer annoBilancio, SiacTVariazioneWrapper variazione, String loginOperazione) {

		String ret = siacTVariazioneRepository.definisciVariazione(
				idEnte, 
				annoBilancio,
				variazione.getNumero(),
				loginOperazione);
		
		String[] tmp = StringUtils.split(ret, "|");
		
		if (tmp == null || tmp.length <= 1) {
			throw new BusinessException(ErroreCore.ERRORE_DI_SISTEMA.getErrore("La funzione ha restituito un valore non valido"));
		}

		if (! "0".equals(tmp[0])) {
			throw new BusinessException(ErroreCore.OPERAZIONE_TERMINATA_CON_ERRORI.getErrore(tmp[1]));
		}		
	}



}