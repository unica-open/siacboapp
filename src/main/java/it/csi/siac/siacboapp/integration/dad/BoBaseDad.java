/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.base.MapToEntityWrapperMapper;
import it.csi.siac.siacboapp.util.entitywrapper.BaseEntityWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccommonser.integration.dad.base.BaseDadImpl;
import it.csi.siac.siaccommonser.integration.entity.SiacTBase;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public abstract class BoBaseDad extends BaseDadImpl {

	@Autowired
	protected ApplicationContext appCtx;

	protected <A, DA> List<A> mapList(Iterable<DA> srcList, Class<A> destClass) {
		return convertiLista(srcList, destClass);
	}

	protected <O extends SiacTBase, OW extends BaseEntityWrapper> 
		void mapEntityToWrapper(O o, OW ow, Class<? extends EntityWrapperMapper>... mapperTypes) {
		
		if (mapperTypes == null || o == null || ow == null) { 
			return;
		}
			
		for (Class<? extends EntityWrapperMapper> mapperClass : mapperTypes) {
			mapEntityToWrapper(o, ow, mapperClass);
		}
	}
	
	protected <O extends SiacTBase, OW extends BaseEntityWrapper> List<OW> mapEntityListToWrapperList(List<O> ol, Class<OW> owc, 
			Class<? extends EntityWrapperMapper>... mapperTypes) {

		List<OW> owl = new ArrayList<OW>();
		
		for (O o : ol) {
			OW ow = map(o, owc);
			mapEntityToWrapper(o, ow, mapperTypes);
			owl.add(ow);
		}

		return owl;
	}

	protected <O extends SiacTBase, OW extends BaseEntityWrapper> List<OW> mapEntityListToWrapperListExt(List<O> ol, Class<OW> owc, 
			Class<? extends EntityWrapperMapper> mapperType, Class<? extends EntityWrapperMapper>... mapperTypes) {

		List<OW> owl = new ArrayList<OW>();
		
		for (O o : ol) {
			OW ow = map(o, owc);
			mapEntityToWrapper(o, ow, mapperType);
			mapEntityToWrapper(o, ow, mapperTypes);
			owl.add(ow);
		}

		return owl;
	}	
	

	protected <O extends SiacTBase, OW extends BaseEntityWrapper> List<OW> mapEntityListToWrapperList(List<O> ol, Class<OW> owc, 
			Class<? extends EntityWrapperMapper>[] mapperTypes1, Class<? extends EntityWrapperMapper>... mapperTypes2) {
	
		return mapEntityListToWrapperList(ol, owc, ArrayUtils.addAll(mapperTypes1, mapperTypes2));
	}
		
	protected <O extends SiacTBase, OW extends BaseEntityWrapper>
		void mapEntityToWrapper(O o, OW ow, Class<? extends EntityWrapperMapper> mapperClass) {

		EntityWrapperMapper customMapper = appCtx.getBean(WordUtils.uncapitalize(mapperClass.getSimpleName()), mapperClass);
		customMapper.map(o, ow);	
	}

	protected <OW extends BaseEntityWrapper> List<OW> mapMapListToWrapperList(List<Map<String, Object>> ml, Class<OW> owc, 
			Class<? extends MapToEntityWrapperMapper>... mapperTypes) {

		List<OW> owl = new ArrayList<OW>();
		
		for (Map<String, Object> m : ml) {
			OW ow = map(m, owc);
			mapMapToWrapper(m, ow, mapperTypes);
			owl.add(ow);
		}

		return owl;
	}

	protected <OW extends BaseEntityWrapper> 
	void mapMapToWrapper(Map<String, Object> m, OW ow, Class<? extends MapToEntityWrapperMapper>... mapperTypes) {
	
		if (mapperTypes == null || m == null || m.isEmpty() || ow == null) { 
			return;
		}
			
		for (Class<? extends MapToEntityWrapperMapper> mapperClass : mapperTypes) {
			mapMapToWrapper(m, ow, mapperClass);
		}
	}

	protected <O extends SiacTBase, OW extends BaseEntityWrapper>
	void mapMapToWrapper(Map<String, Object> m, OW ow, Class<? extends MapToEntityWrapperMapper> mapperClass) {
	
		MapToEntityWrapperMapper customMapper = appCtx.getBean(WordUtils.uncapitalize(mapperClass.getSimpleName()), mapperClass);
		customMapper.map(m, ow);	
	}

	
	protected void checkForLongSearch(long currentTimeMillis0) {
		long elapsed = System.currentTimeMillis() - currentTimeMillis0;

		log.warn("checkForLongSearch", "elapsed " + elapsed);

		if (elapsed > 30000) {
			throw new BusinessException(ErroreCore.RICERCA_TROPPO_ESTESA.getErrore());
		}
	}
}
