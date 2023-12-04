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

import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapper;
import it.csi.siac.siacboapp.integration.dao.limiteimpegnabile.SiacTBilElemDao;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.repository.SiacTBilElemRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTBilElemWrapper;

@Component 
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CapitoloDad extends BoBaseDad {

	@Autowired
	protected SiacTBilElemDao SiacTBilElemDao;

	@Autowired
	protected SiacTBilElemRepository siacTBilElemRepository;
	
	public List<SiacTBilElemWrapper> ricercaCapitoloByOrdinativoUid(Integer uidOrdinativo, int idEnte, 
			Class<? extends EntityWrapperMapper>... mapperTypes) {
		
		long currentTimeMillis0 = System.currentTimeMillis();
		
		try {
			
			List<SiacTBilElem> elencoCapitoli = siacTBilElemRepository.readCapitoloByOrdinativoUid(uidOrdinativo, idEnte);
		
			return mapToWrapper(elencoCapitoli, mapperTypes);
		
		} catch (PersistenceException e) {
			checkForLongSearch(currentTimeMillis0);
		}
		
		return null;
	}

	private List<SiacTBilElemWrapper> mapToWrapper(List<SiacTBilElem> elencoCapitoli,
			Class<? extends EntityWrapperMapper>... mapperTypes) {
		
		List<SiacTBilElemWrapper> elencoCapitoliWrapper = new ArrayList<SiacTBilElemWrapper>();
		
		for (SiacTBilElem o : elencoCapitoli) {
			
			SiacTBilElemWrapper ow = map(o, SiacTBilElemWrapper.class);
			mapEntityToWrapper(o, ow, mapperTypes);
			
			elencoCapitoliWrapper.add(ow);
		}

		return elencoCapitoliWrapper;
	}
	
	
}
