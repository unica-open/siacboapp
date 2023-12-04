/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.utenti;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.integration.dad.ClassificatoreDad;
import it.csi.siac.siacboapp.integration.dao.common.SiacTClassDao;
import it.csi.siac.siacboapp.integration.entity.SiacTClass;
import it.csi.siac.siacboapp.util.BoConstants;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;

/**
 * @author Alessandro Todesco
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class ClassificatoreService extends BoService {
	
	@Autowired
	private ClassificatoreDad classificatoreDad;
	
	@Autowired
	private SiacTClassDao siacTClassDao;

	public List<SiacTClass> getElencoStruttureAmministrativeContabili(int enteId, Integer anno) {
		List<SiacTClass> list = siacTClassDao.getElencoClassificatoriByTipoFamiglia(enteId,
				BoConstants.ID_FAMIGLIA_TREE_STRUTTURA_AMMINISTRATIVA_CONTABILE.getVal(), anno);
		
		Set<Integer> sacIdSet = new HashSet<Integer>();
		
		for (SiacTClass siacTClass : list)
			sacIdSet.add(siacTClass.getUid());
		
		List<SiacTClass> elencoClassificatoriByTipoFamiglia = new ArrayList<SiacTClass>();
		
		for (SiacTClass siacTClass : list)
			if (siacTClass.getParent() == null || sacIdSet.contains(siacTClass.getParent().getUid()))
				elencoClassificatoriByTipoFamiglia.add(siacTClass);
		
		return elencoClassificatoriByTipoFamiglia;
	}

	public List<SiacTClassWrapper> readPianoDeiContiByCapitoloUid(Integer uidCapitolo, Integer enteProprietarioId) {
		return classificatoreDad.readPianoDeiContiByCapitoloUid(uidCapitolo, enteProprietarioId);
	}

	public List<SiacTClassWrapper> readPianoDeiContiByOrdinativoUid(Integer uidOrdinativo, Integer enteProprietarioId) {
		return classificatoreDad.readPianoDeiContiByOrdinativoUid(uidOrdinativo, enteProprietarioId);
	}

	public List<SiacTClassWrapper> readTitoliEntrataSpesaByEnteProprietario(String tipo, String uscitaEntrata, Integer enteProprietarioId) {
		return classificatoreDad.readTitoliEntrataSpesaByEnteProprietario(tipo, uscitaEntrata, enteProprietarioId);
	}

	public List<SiacTClassWrapper> findMacroAggregatoByCapitolo(Integer uidCapitolo, Integer enteProprietarioId) {
		return classificatoreDad.findMacroAggregatoByCapitolo(uidCapitolo, enteProprietarioId);
	}

	public List<SiacTClassWrapper> findMacroAggregatoByOrdinativoUid(Integer uidOrdinativo, Integer enteProprietarioId) {
		return classificatoreDad.findMacroAggregatoByOrdinativoUid(uidOrdinativo, enteProprietarioId);
	}

	public List<SiacTClassWrapper> findChildrenByParentUid(Integer uidParent, Integer enteProprietarioId) {
		return classificatoreDad.findChildrenByParentUid(uidParent, enteProprietarioId);
	}

	public List<SiacTClassWrapper> findSiblingsByChildUid(Integer uidParent, Integer enteProprietarioId) {
		return classificatoreDad.findSiblingsByChildUid(uidParent, enteProprietarioId);
	}
	
	public List<SiacTClassWrapper> findParentSiblingsByChildUid(Integer uidParent, Integer enteProprietarioId) {
		return classificatoreDad.findParentSiblingsByChildUid(uidParent, enteProprietarioId);
	}
	
	public List<SiacTClassWrapper> findPdcByCategoriaMacroaggregato(Integer uidParent, Integer enteProprietarioId) {
		return classificatoreDad.findPdcByCategoriaMacroaggregato(uidParent, enteProprietarioId);
	}
	
	public List<SiacTClassWrapper> findPdcQuintoLivelloDaOrdinativo(Integer uidOrdinativo, Integer enteProprietarioId) {
		return classificatoreDad.findPdcQuintoLivelloDaOrdinativo(uidOrdinativo, enteProprietarioId);
	}

}
