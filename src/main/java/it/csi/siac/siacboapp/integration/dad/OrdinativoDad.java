/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi.CriteriRicercaOrdinativi;
import it.csi.siac.siacboapp.integration.dad.helper.AssociaOrdinativiProvvisoriCassaHelper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTOrdinativoDataAnnullamentoMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapping;
import it.csi.siac.siacboapp.integration.dao.gestioneordinativi.SiacTOrdinativoDao;
import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;
import it.csi.siac.siacboapp.integration.repository.SiacROrdinativoProvCassaRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component 
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrdinativoDad extends BoBaseDad {
	
	@Autowired
	protected SiacTOrdinativoDao siacTOrdinativoDao;
	
	@Autowired
	protected SiacROrdinativoProvCassaRepository siacROrdinativoProvCassaRepository;
	
	@Autowired
	protected AssociaOrdinativiProvvisoriCassaHelper associaOrdinativiProvvisoriCassaHelper;
	
	public List<SiacTOrdinativoWrapper> ricercaOrdinativi(int idEnte, CriteriRicercaOrdinativi criteri, 
			EntityWrapperMapping... entityWrapperMappings) {
		final String methodName = "ricercaOrdinativi";
		
		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
		
			List<SiacTOrdinativo> elencoOrdinativi = internalRicercaOrdinativi(idEnte, criteri);
		
//			if (elencoOrdinativi.size() > 300)
//				throw new BusinessException(ErroreCore.RICERCA_TROPPO_ESTESA.getErrore());

			return mapToWrapper(elencoOrdinativi, entityWrapperMappings);
		}
		catch (PersistenceException e) {
			// FIXME
			
			long elapsed = System.currentTimeMillis() - currentTimeMillis0;
			
			log.warn(methodName, "internalRicercaOrdinativi: elapsed " + elapsed);
			
			if (elapsed > 30000) {
				throw new BusinessException(ErroreCore.RICERCA_TROPPO_ESTESA.getErrore());
			}
			
//			handlePersistenceException(e);
		}
		
		return null;
	}
	
	public List<SiacTOrdinativoWrapper> ricercaOrdinativiSiopePlus(int idEnte, CriteriRicercaOrdinativi criteri, 
			EntityWrapperMapping... entityWrapperMappings) {
		final String methodName = "ricercaOrdinativiSiopePlus";

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTOrdinativo> elencoOrdinativi = internalRicercaOrdinativiSiopePlus(idEnte, criteri);
			
//			if (elencoOrdinativi.size() > 300)
//				throw new BusinessException(ErroreCore.RICERCA_TROPPO_ESTESA.getErrore());
			
			return mapToWrapper(elencoOrdinativi, entityWrapperMappings);
		}
		catch (PersistenceException e) {
			// FIXME
			long elapsed = System.currentTimeMillis() - currentTimeMillis0;
			
			log.warn(methodName, "internalRicercaOrdinativiSiopePlus: elapsed " + elapsed);
			
			if (System.currentTimeMillis() - currentTimeMillis0 > 30000) {
				throw new BusinessException(ErroreCore.RICERCA_TROPPO_ESTESA.getErrore());
			}
			
			// handlePersistenceException(e);
		}
		
		return null;
	}

//	private void handlePersistenceException(PersistenceException e) { // FIXME
//		
//		
//		throw e;
//		/*
//		Throwable cause = e.getCause();
//		
//		if (cause != null && cause instanceof GenericJDBCException) {
//			if ("57014".equals(((GenericJDBCException)cause).getSQLState())) {
//				// ERROR: canceling statement due to statement timeout
//				throw new BusinessException(ErroreCore.RICERCA_TROPPO_ESTESA.getErrore());
//			}
//		}
//
//		throw e;
//		*/
//	}

	public void associaOrdinativiProvvisoriCassa(Integer[] idOrdinativi, Integer[] idProvvisoriCassa, String loginOperazione) {
			associaOrdinativiProvvisoriCassaHelper.associaOrdinativiProvvisoriCassa(idOrdinativi, idProvvisoriCassa, loginOperazione);
	}

	private List<SiacTOrdinativoWrapper> mapToWrapper(List<SiacTOrdinativo> elencoOrdinativi,
			EntityWrapperMapping... entityWrapperMappings) {
		
		List<SiacTOrdinativoWrapper> elencoOrdinativiWrapper = new ArrayList<SiacTOrdinativoWrapper>();
		
		for (SiacTOrdinativo o : elencoOrdinativi) {
			
			SiacTOrdinativoWrapper ow = map(o, SiacTOrdinativoWrapper.class);
			
			mapWithMapper(SiacTOrdinativoDataAnnullamentoMapper.class, o, ow);
			mapEntityToWrapper(o, ow, entityWrapperMappings);
			
			elencoOrdinativiWrapper.add(ow);
		}

		return elencoOrdinativiWrapper;
	}

	private List<SiacTOrdinativo> internalRicercaOrdinativi(int idEnte, CriteriRicercaOrdinativi criteri) {
		return siacTOrdinativoDao.ricercaOrdinativi(idEnte, 
				criteri.getCodiceTipo(), 
				criteri.getAnno(),
				criteri.getCodiciStato() != null ? Arrays.asList(criteri.getCodiciStato()) : null,
				criteri.getCodiciStatoEsclusi() != null ? Arrays.asList(criteri.getCodiciStatoEsclusi()) : null, 
				criteri.getCodiceFlusso(),
				criteri.getIdCodiceDistinta(),
				criteri.getNumeroDa(), 
				criteri.getNumeroA(),
				criteri.getDataEmissioneDa(), 
				DateUtils.ceiling(criteri.getDataEmissioneA(), Calendar.DAY_OF_MONTH),
				criteri.getDataTrasmissioneOilDa(),
				DateUtils.ceiling(criteri.getDataTrasmissioneOilA(), Calendar.DAY_OF_MONTH),
				criteri.getDataTrasmissioneOilPresente(),
				
				//Evolutive BackofficeGestioneOrdinativi
				criteri.getDataOrdSpostamentoDa() != null ? criteri.getDataOrdSpostamentoDa() : null,
				criteri.getDataOrdSpostamentoA() != null ? DateUtils.ceiling(criteri.getDataOrdSpostamentoA(), Calendar.DAY_OF_MONTH) : null,
						
				criteri.getDaTrasmettere(),
				criteri.getAttoAmministrativo(),
				criteri.getSoggetto(),
				criteri.getEscludiCollegatiProvvisoriCassa());
	}

	private List<SiacTOrdinativo> internalRicercaOrdinativiSiopePlus(int idEnte, CriteriRicercaOrdinativi criteri) {
		
		if (! Boolean.TRUE.equals(criteri.getIncludiVBDaTrasmettere()) 
					&& ! Boolean.TRUE.equals(criteri.getIncludiAnnulliDaTrasmettere())) {
			return internalRicercaOrdinativi(idEnte, criteri);
		}
		
		if (criteri.getCodiciStato() == null && 
				criteri.getNumeroDa() == null && 
				criteri.getNumeroA() == null && 
				StringUtils.isBlank(criteri.getCodiceFlusso()) &&
				criteri.getIdCodiceDistinta() == null) {

			return siacTOrdinativoDao.ricercaOrdinativiSiopePlus(
					idEnte, 
					criteri.getCodiceTipo(), 
					criteri.getAnno(), 
					criteri.getDataTrasmissioneOilPresente(),
					criteri.getDaTrasmettere(),
					criteri.getAttoAmministrativo(),
					criteri.getSoggetto(),
					criteri.getEscludiCollegatiProvvisoriCassa(),
					criteri.getIncludiVBDaTrasmettere(), 
					criteri.getIncludiAnnulliDaTrasmettere());		
		}
				
		return siacTOrdinativoDao.ricercaOrdinativiSiopePlus(
				 	idEnte, 
					criteri.getCodiceTipo(), 
					criteri.getAnno(),
					criteri.getCodiciStato() != null ? Arrays.asList(criteri.getCodiciStato()) : null,
					criteri.getCodiceFlusso(),
					criteri.getIdCodiceDistinta(),
					criteri.getNumeroDa(), 
					criteri.getNumeroA(),
					criteri.getDataEmissioneDa(), 
					DateUtils.ceiling(criteri.getDataEmissioneA(), Calendar.DAY_OF_MONTH),
					criteri.getDataTrasmissioneOilDa(),
					DateUtils.ceiling(criteri.getDataTrasmissioneOilA(), Calendar.DAY_OF_MONTH),
					criteri.getDataTrasmissioneOilPresente(),
					
					criteri.getDataOrdSpostamentoDa() != null 
//					&& criteri.getDataOrdSpostamentoDa().compareTo(CriteriRicercaOrdinativi.MIN_DATE) > 0 
					? criteri.getDataOrdSpostamentoDa() : null,
					criteri.getDataOrdSpostamentoA() != null 
//					&& criteri.getDataOrdSpostamentoA().compareTo(CriteriRicercaOrdinativi.MAX_DATE) < 0 
					? DateUtils.ceiling(criteri.getDataOrdSpostamentoA(), Calendar.DAY_OF_MONTH) : null,
							
					criteri.getDaTrasmettere(),
					criteri.getAttoAmministrativo(),
					criteri.getSoggetto(),
					criteri.getEscludiCollegatiProvvisoriCassa(),
					criteri.getIncludiVBDaTrasmettere(), 
					criteri.getIncludiAnnulliDaTrasmettere());
	}

}
