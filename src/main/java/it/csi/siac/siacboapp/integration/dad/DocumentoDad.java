/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.frontend.ui.model.documenti.CriteriRicercaDocumenti;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTDocSoggettoMapper;
import it.csi.siac.siacboapp.integration.dao.documenti.SiacTDocDao;
import it.csi.siac.siacboapp.integration.entity.SiacTDoc;
import it.csi.siac.siacboapp.integration.repository.SiacTAttoAmmRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTDocRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTSubdocRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTDocWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSubdocWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DocumentoDad extends BoBaseDad {

	@Autowired
	private SiacTDocDao siacTDocDao;

	@Autowired
	private SiacTDocRepository siacTDocRepository;

	@Autowired
	private SiacTSubdocRepository siacTSubdocRepository;
	
	@Autowired
	private SiacTAttoAmmRepository siacTAttoAmmRepository;
	

	public SiacTDocWrapper cercaDocumento(int idEnte, CriteriRicercaDocumenti criteri) {

		long currentTimeMillis0 = System.currentTimeMillis();

		try {

			List<SiacTDoc> list = siacTDocDao.ricercaDocumenti(idEnte, criteri.getCodiceTipologia(), criteri.getCodiceTipo(),
					criteri.getAnno(), criteri.getNumero(), criteri.getDataEmissione(), criteri.getSoggetto().getCodice());

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

	@SuppressWarnings("unchecked")
	public List<SiacTSubdocWrapper> cercaQuoteAttoAmministrativoSoggetto(int idEnte, Integer idAttoAmministrativo, Integer idSac, String codiceSoggetto) {
		return 
				mapEntityListToWrapperList(siacTSubdocRepository.findQuoteAttoAmmSoggetto(idEnte, idAttoAmministrativo, idSac, codiceSoggetto), 
						SiacTSubdocWrapper.class);
	}
	
	private SiacTDocWrapper mapToWrapper(SiacTDoc siacTDoc) {
		SiacTDocWrapper siacTDocWrapper = map(siacTDoc, SiacTDocWrapper.class);
		
		mapEntityToWrapper(siacTDoc, siacTDocWrapper, SiacTDocSoggettoMapper.class);
		
		return siacTDocWrapper;
	}

	public void annullaAttivazioniContabili(int idEnte, SiacTDocWrapper documento, String loginOperazione) {

		String ret = siacTDocRepository.annullaAttivazioniContabili(
				idEnte, 
				documento.getAnno(), 
				documento.getNumero(),
				documento.getTipo().getCodice(),
				documento.getSoggetto().getCodice(),
				loginOperazione);

		if (ret != null) {
			throw new BusinessException(ErroreCore.OPERAZIONE_TERMINATA_CON_ERRORI.getErrore(ret));
		}
	}

	public void modificaModalitaPagamentoAttoAmministrativo(int idEnte, int idBilancio, Integer idAttoAmministrativo, 
			String codiceSoggetto, Integer idModalitaPagamentoSoggetto, String loginOperazione) {
		
		log.info("modificaModalitaPagamentoAttoAmministrativo", 
				String.format("idEnte %d, idBilancio %d, idAttoAmministrativo %s, codiceSoggetto %s, idModalitaPagamentoSoggetto %s, loginOperazione %s", 
						idEnte, idBilancio, idAttoAmministrativo, codiceSoggetto, String.valueOf(idModalitaPagamentoSoggetto), loginOperazione));

		String ret = siacTAttoAmmRepository.modificaModalitaPagamentoAttoAmministrativo(
				idEnte, 
				idBilancio,
				idAttoAmministrativo,
				codiceSoggetto,
				idModalitaPagamentoSoggetto,
				loginOperazione);

		if (ret != null) {
			throw new BusinessException(ErroreCore.OPERAZIONE_TERMINATA_CON_ERRORI.getErrore(ret));
		}
	}
}