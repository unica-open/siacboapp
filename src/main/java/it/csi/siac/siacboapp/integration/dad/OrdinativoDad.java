/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.CriteriRicercaOrdinativi;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.backofficeModificaPianoDeiContiOrdinativo.ModificaPianoDeiContiOrdinativo;
import it.csi.siac.siacboapp.integration.dad.helper.AssociaOrdinativiProvvisoriCassaHelper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTOrdinativoDataAnnullamentoMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapper;
import it.csi.siac.siacboapp.integration.dao.ordinativi.SiacTOrdinativoDao;
import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;
import it.csi.siac.siacboapp.integration.repository.SiacROrdinativoProvCassaRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTOrdinativoRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;

@Component 
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrdinativoDad extends BoBaseDad {
	
	@Autowired
	protected SiacTOrdinativoDao siacTOrdinativoDao;
	
	@Autowired
	protected SiacROrdinativoProvCassaRepository siacROrdinativoProvCassaRepository;
	
	@Autowired
	protected AssociaOrdinativiProvvisoriCassaHelper associaOrdinativiProvvisoriCassaHelper;

	@Autowired
	protected SiacTOrdinativoRepository siacTOrdinativoRepository;
	
	public List<SiacTOrdinativoWrapper> ricercaOrdinativi(int idEnte, CriteriRicercaOrdinativi criteri, 
			Class<? extends EntityWrapperMapper>... mapperTypes) {

		
		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTOrdinativo> elencoOrdinativi = internalRicercaOrdinativi(idEnte, criteri);
		
//			if (elencoOrdinativi.size() > 300)
//				throw new BusinessException(ErroreCore.RICERCA_TROPPO_ESTESA.getErrore());

			return mapToWrapper(elencoOrdinativi, mapperTypes);
		}
		catch (PersistenceException e) {
			// FIXME
			checkForLongSearch(currentTimeMillis0);
			
//			handlePersistenceException(e);
		}
		
		return null;
	}
	
	/**
	 * SIAC-7615
	 * 
	 * @param uid
	 * @param anno
	 * @param codiceTipoOrdinativi
	 * @return
	 */
	public List<SiacTOrdinativoWrapper> ricercaOrdinativiDaFlusso(Integer uid, String anno, String codiceTipoOrdinativi, 
			Class<? extends EntityWrapperMapper>... mapperTypes) {
		
		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTOrdinativo> elencoOrdinativi = 
					siacTOrdinativoDao.ricercaOrdinativiDaFlusso(uid, anno, codiceTipoOrdinativi);
		
			return mapToWrapper(elencoOrdinativi, mapperTypes);
		}
		catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		
		return null;
	}

	/**
	 * SIAC-7639
	 * 
	 * @param idEnte
	 * @param criteri
	 * @param entityWrapperMappings
	 * @return
	 */
	public List<SiacTOrdinativoWrapper> ricercaOrdinativo(int idEnte, CriteriRicercaOrdinativi criteri, 
			Class<? extends EntityWrapperMapper>... mapperTypes) {

		
		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTOrdinativo> elencoOrdinativi = internalRicercaOrdinativo(idEnte, criteri);
			
			return mapToWrapper(elencoOrdinativi, mapperTypes);
		
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		
		return null;
	}

	public List<SiacTOrdinativoWrapper> ricercaOrdinativiSiopePlus(int idEnte, CriteriRicercaOrdinativi criteri, 
			Class<? extends EntityWrapperMapper>... mapperTypes) {


		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTOrdinativo> elencoOrdinativi = internalRicercaOrdinativiSiopePlus(idEnte, criteri);
			
//			if (elencoOrdinativi.size() > 300)
//				throw new BusinessException(ErroreCore.RICERCA_TROPPO_ESTESA.getErrore());
			
			return mapToWrapper(elencoOrdinativi, mapperTypes);
		}
		catch (PersistenceException e) {
			// FIXME
			checkForLongSearch(currentTimeMillis0);
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
			Class<? extends EntityWrapperMapper>... mapperTypes) {
		
		return mapEntityListToWrapperListExt(elencoOrdinativi, SiacTOrdinativoWrapper.class, 
				SiacTOrdinativoDataAnnullamentoMapper.class, mapperTypes);
		
//		List<SiacTOrdinativoWrapper> elencoOrdinativiWrapper = new ArrayList<SiacTOrdinativoWrapper>();
//		
//		for (SiacTOrdinativo o : elencoOrdinativi) {
//			
//			SiacTOrdinativoWrapper ow = map(o, SiacTOrdinativoWrapper.class);
//			
//			mapEntityToWrapper(o, ow, SiacTOrdinativoDataAnnullamentoMapper.class);
//			mapEntityToWrapper(o, ow, mapperTypes);
//			
//			elencoOrdinativiWrapper.add(ow);
//		}
//
//		return elencoOrdinativiWrapper;
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

	private List<SiacTOrdinativo> internalRicercaOrdinativo(int idEnte, CriteriRicercaOrdinativi criteri) {
		return siacTOrdinativoDao.internalRicercaOrdinativo(idEnte, 
					criteri.getCodiceTipo(),
					criteri.getAnno(),
					criteri.getNumeroDa(),
					criteri.getNumeroA(),
					criteri.getDataEmissioneDa(), 
					DateUtils.ceiling(criteri.getDataEmissioneA(), Calendar.DAY_OF_MONTH),
					criteri.getDataTrasmissioneOilDa(),
					DateUtils.ceiling(criteri.getDataTrasmissioneOilA(), Calendar.DAY_OF_MONTH));
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
	
	//SIAC-7639
	public String modificaPianoDeiContiOrdinativoBackoffice(ModificaPianoDeiContiOrdinativo request) {
		return siacTOrdinativoDao.modificaPianoDeiContiOrdinativoBackoffice(
					StringEscapeUtils.escapeSql(request.getNumeroRemedy()),
					request.getIdEnte(),
					request.getTipoOrdinativo(),
					request.getAnnoBilancio().toString(),
					request.getOrdinativo().getNumero(),
					StringUtils.isBlank(request.getEventoCodice()) ? 
							"" : request.getEventoCodice(),
					StringUtils.isBlank(request.getTipoEventoCodice()) ?
							"" : request.getTipoEventoCodice(),
					request.getPianoDeiConti().getCodice(),
					"I".equals(request.getTipoOrdinativo()) && request.getModificaAccertamento() ? 1 : 0,
					request.getAggiornaGenerale() ? 1 : 0,
					request.getAggiornaGSAGenerale() ? 1 : 0,
					request.getInserisciGenerale() ? 1 : 0,
					request.getInserisciGSAGenerale() ? 1 : 0
				);
	}
	//
	
//	public List<SiacTOrdinativo> internalRicercaOrdinativiDaFlusso(Integer uid, String anno, String codiceTipoOrdinativi) {
//		return siacTOrdinativoDao.ricercaOrdinativiDaFlusso(
//				uid != null ? uid : 0, 
//				anno != null ? anno : "", 
//				codiceTipoOrdinativi != null ? codiceTipoOrdinativi : "");
//	}

	public List<SiacTOrdinativo> internalRicercaOrdinativiDaFlusso(Integer uid, String codiceTipoOrdinativi) {
		return siacTOrdinativoDao.ricercaOrdinativiDaFlusso(
				uid != null ? uid : 0, codiceTipoOrdinativi != null ? codiceTipoOrdinativi : "");
	}

}
