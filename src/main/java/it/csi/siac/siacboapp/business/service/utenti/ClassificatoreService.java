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
import it.csi.siac.siacboapp.integration.dao.common.SiacTClassDao;
import it.csi.siac.siacboapp.integration.entity.SiacTClass;
import it.csi.siac.siacboapp.util.BoConstants;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class ClassificatoreService extends BoService {
	@Autowired
	private SiacTClassDao siacTClassDao;

	public List<SiacTClass> getElencoStruttureAmministrativeContabili(int enteId, String anno) {
		List<SiacTClass> list = siacTClassDao.getElencoClassificatoriByTipoFamiglia(enteId,
				BoConstants.ID_FAMIGLIA_TREE_STRUTTURA_AMMINISTRATIVA_CONTABILE.getVal(), Integer.parseInt(anno));
		
		Set<Integer> sacIdSet = new HashSet<Integer>();
		
		for (SiacTClass siacTClass : list)
			sacIdSet.add(siacTClass.getUid());
		
		List<SiacTClass> elencoClassificatoriByTipoFamiglia = new ArrayList<SiacTClass>();
		
		for (SiacTClass siacTClass : list)
			if (siacTClass.getParent() == null || sacIdSet.contains(siacTClass.getParent().getUid()))
				elencoClassificatoriByTipoFamiglia.add(siacTClass);
		
		return elencoClassificatoriByTipoFamiglia;
	}

}
