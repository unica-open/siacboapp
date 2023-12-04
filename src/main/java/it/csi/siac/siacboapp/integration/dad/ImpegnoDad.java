/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacbilser.model.movimentogestione.TipoMovimentoGestione;
import it.csi.siac.siacboapp.frontend.ui.model.impegni.CriteriRicercaImpegni;
import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapper;
import it.csi.siac.siacboapp.integration.entity.SiacTMovgest;
import it.csi.siac.siacboapp.util.entitywrapper.ImpegnoWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ImpegnoDad extends MovimentoGestioneDad {
	

	public ImpegnoWrapper cercaImpegno(int idEnte, int idBilancio, CriteriRicercaImpegni criteri, Class<? extends EntityWrapperMapper>... mapperTypes) {

		long currentTimeMillis0 = System.currentTimeMillis();

		try {

			List<SiacTMovgest> list = findMovgestByTipoAnnoNumero(idEnte, idBilancio, TipoMovimentoGestione.IMPEGNO, criteri);

			if (list.isEmpty()) {
				throw new BusinessException(ErroreCore.NESSUN_DATO_REPERITO.getErrore());
			}

			if (list.size() > 1) {
				throw new BusinessException(ErroreCore.RICERCA_TROPPO_ESTESA.getErrore());
			}

			return mapToWrapper(list.get(0), mapperTypes);

		}
		catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}

		return null;
	}

	public void modificaParereFinanziario(Integer idMovgest, Boolean parereFinanziario, String loginOperazione) {
		SiacTMovgest siacTMovgest = siacTMovgestRepository.findOne(idMovgest);
		
		if (parereFinanziario.equals(siacTMovgest.getParereFinanziario())) {
			return;
		}
		
		siacTMovgestRepository.modificaParereFinanziario(idMovgest, parereFinanziario, loginOperazione);
	}

	private ImpegnoWrapper mapToWrapper(SiacTMovgest siacTMovgest, Class<? extends EntityWrapperMapper>... mapperTypes) {
		ImpegnoWrapper impegnoWrapper = map(siacTMovgest, ImpegnoWrapper.class);
		
		mapEntityToWrapper(siacTMovgest, impegnoWrapper, mapperTypes);

		return impegnoWrapper;
	}


}