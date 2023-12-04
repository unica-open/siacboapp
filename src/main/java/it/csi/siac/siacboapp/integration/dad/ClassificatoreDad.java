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

import it.csi.siac.siacboapp.integration.entity.SiacTClass;
import it.csi.siac.siacboapp.integration.repository.SiacTClassRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.TipoClassificatoreEnum;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

/**
 * @author Alessandro Todesco
 */
@Component 
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ClassificatoreDad extends BoBaseDad {

	@Autowired
	private SiacTClassRepository siacTClassRepository;
	
	public List<SiacTClassWrapper> readPianoDeiContiByCapitoloUid(Integer uidCapitolo, int idEnte) {

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> elencoClassificatori = siacTClassRepository.readPianoDeiContiByCapitoloUid(uidCapitolo, idEnte);
			return mapToWrapper(elencoClassificatori);
		
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		
		return null;
	}
	
	public List<SiacTClassWrapper> readPianoDeiContiByOrdinativoUid(Integer uidOrdinativo, int idEnte) {

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> elencoClassificatori = siacTClassRepository.readPianoDeiContiByOrdinativoUid(uidOrdinativo, idEnte);
			return mapToWrapper(elencoClassificatori);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		
		return null;
	}

	public List<SiacTClassWrapper> findMacroAggregatoByCapitolo(Integer uidCapitolo, int idEnte){
		

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> siacTClasses = siacTClassRepository.findMacroAggregatoByCapitolo(uidCapitolo, idEnte);
			if (siacTClasses == null || siacTClasses.isEmpty()) {
				throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("codifica/macroaggregato"));
			}
			
			return mapToWrapper(siacTClasses);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		return null;
		
	}

	public List<SiacTClassWrapper> findMacroAggregatoByOrdinativoUid(Integer uidOrdinativo, int idEnte){
		

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> siacTClasses = siacTClassRepository.findMacroAggregatoByOrdinativoUid(uidOrdinativo, idEnte);
			if (siacTClasses == null || siacTClasses.isEmpty()) {
				throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("codifica/macroaggregato"));
			}
			
			return mapToWrapper(siacTClasses);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		return null;
		
	}

	public List<SiacTClassWrapper> readTitoliEntrataSpesaByEnteProprietario(String tipoCodice, String uscitaEntrata, int idEnte){
		

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> siacTClasses = siacTClassRepository.readTitoliEntrataSpesaByEnteProprietario(tipoCodice, uscitaEntrata, idEnte);
			if (siacTClasses == null || siacTClasses.isEmpty()) {
				throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("titolo"));
			}
			
			return mapToWrapper(siacTClasses);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		return null;
		
	}

	public List<SiacTClassWrapper> findChildrenByParentUid(Integer uidPadre, int idEnte){
		

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> siacTClasses = siacTClassRepository.findChildrenByParentUid(uidPadre, idEnte);
			if (siacTClasses == null || siacTClasses.isEmpty()) {
				throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("classificatore"));
			}
			
			return mapToWrapper(siacTClasses);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		return null;
		
	}

	public List<SiacTClassWrapper> findSiblingsByChildUid(Integer uidPadre, int idEnte){
		

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> siacTClasses = siacTClassRepository.findSiblingsByChildUid(uidPadre, idEnte);
			if (siacTClasses == null || siacTClasses.isEmpty()) {
				throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("classificatore"));
			}
			
			return mapToWrapper(siacTClasses);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		return null;
		
	}
	
	public List<SiacTClassWrapper> findParentSiblingsByChildUid(Integer uidPadre, int idEnte){
		

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> siacTClasses = siacTClassRepository.findParentSiblingsByChildUid(uidPadre, idEnte);
			if (siacTClasses == null || siacTClasses.isEmpty()) {
				throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("classificatore"));
			}
			
			return mapToWrapper(siacTClasses);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		return null;
		
	}

	public List<SiacTClassWrapper> findPdcByCategoriaMacroaggregato(Integer uidPadre, int idEnte){
		

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> siacTClasses = siacTClassRepository.findPdcByCategoriaMacroaggregato(uidPadre, idEnte);
			if (siacTClasses == null || siacTClasses.isEmpty()) {
				throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("piano dei conti"));
			}
			
			return mapToWrapper(siacTClasses);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		return null;
		
	}
	
	public List<SiacTClassWrapper> findPdcQuintoLivelloDaOrdinativo(Integer uidOrdinativo, int idEnte){
		

		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> siacTClasses = siacTClassRepository.findPdcQuintoLivelloDaOrdinativo(uidOrdinativo, idEnte);
			if (siacTClasses == null || siacTClasses.isEmpty()) {
				throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("piano dei conti"));
			}
			
			return mapToWrapper(siacTClasses);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		return null;
	}
	
	// SIAC-8485 RICERCA DELLE STRUTTURE AMMINISTRATIVO CONTABILI
	public List<SiacTClassWrapper> findStruttureAmministrativoContabili(int idEnte) {
		List<SiacTClassWrapper> centriDiResponsabilita = findStruttureAmministrativoContabiliCDR(idEnte);
		
		if(centriDiResponsabilita != null && !centriDiResponsabilita.isEmpty()) {
			for(SiacTClassWrapper centroDiResponsabilita : centriDiResponsabilita) {
				//aggiungo i settori
				centroDiResponsabilita.setChildren(findStruttureAmministrativoContabiliCDC(centroDiResponsabilita.getUid(), idEnte));
			}
		}
		
		return centriDiResponsabilita != null && !centriDiResponsabilita.isEmpty() ? centriDiResponsabilita : new ArrayList<SiacTClassWrapper>();
	}

	// SIAC-8485 RICERCA DEI CENTRI DI RESPONSABILITA' (DIREZIONI)
	public List<SiacTClassWrapper> findStruttureAmministrativoContabiliCDR(int idEnte){
		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> siacTClasses = siacTClassRepository.findStruttureAmministrativoContabiliCDR(idEnte, TipoClassificatoreEnum.CENTRO_DI_RESPONSABILITA.getCodice());
			if (siacTClasses == null || siacTClasses.isEmpty()) {
				throw new BusinessException(ErroreCore.ENTITA_NON_TROVATA.getErrore("strutture amministrative contabili - " + TipoClassificatoreEnum.CENTRO_DI_RESPONSABILITA.getCodice()));
			}
			
			return mapToWrapper(siacTClasses);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		return null;
	}
	
	// SIAC-8485 RICERCA DEI CENTRI DI CONTROLLO (SETTORI)
	public List<SiacTClassWrapper> findStruttureAmministrativoContabiliCDC(int uidPadre, int idEnte){
		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTClass> siacTClasses = siacTClassRepository.findCentriDiControlloChildrenByParentCentroDiResponsabilitaUid(uidPadre, idEnte, TipoClassificatoreEnum.CENTRO_DI_CONTROLLO.getCodice());
			if (siacTClasses == null || siacTClasses.isEmpty()) {
				log.info("findStruttureAmministrativoContabiliCDC", "Nessun settore trovato (" + TipoClassificatoreEnum.CENTRO_DI_CONTROLLO.getCodice() + " per il centro di responsabilita' selezionato");
				return new ArrayList<SiacTClassWrapper>();
			}
			
			return mapToWrapper(siacTClasses);
			
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		return null;
	}
	
	private List<SiacTClassWrapper> mapToWrapper(List<SiacTClass> elencoClassificatori) {
		
		List<SiacTClassWrapper> elencoClassificatoriWrapper = new ArrayList<SiacTClassWrapper>();
		
		for (SiacTClass c : elencoClassificatori) {
			
			SiacTClassWrapper ow = map(c, SiacTClassWrapper.class);
			elencoClassificatoriWrapper.add(ow);
		}

		return elencoClassificatoriWrapper;
	}
	

}
