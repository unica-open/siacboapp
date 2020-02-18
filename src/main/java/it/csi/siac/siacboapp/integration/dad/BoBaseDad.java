/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapping;
import it.csi.siac.siacboapp.util.entitywrapper.BaseEntityWrapper;
import it.csi.siac.siaccommonser.integration.dad.base.BaseDadImpl;
import it.csi.siac.siaccommonser.integration.entity.SiacTBase;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public abstract class BoBaseDad extends BaseDadImpl {

	@Autowired
	protected ApplicationContext appCtx;

	protected <A, DA> List<A> mapList(Iterable<DA> srcList, Class<A> destClass) {
		return convertiLista(srcList, destClass);
	}

	protected <O extends SiacTBase, OW extends BaseEntityWrapper> 
		void mapEntityToWrapper(O o, OW ow, EntityWrapperMapping[] entityWrapperMappings) {
		
		if (entityWrapperMappings == null || o == null || ow == null) { 
			return;
		}
			
		for (EntityWrapperMapping mapping : entityWrapperMappings) {
			
			Class<? extends EntityWrapperMapper> mapperClass = mapping.getMapperClass();
			
			mapWithMapper(mapperClass, o, ow);
		}
	}

	protected <O extends SiacTBase, OW extends BaseEntityWrapper> void mapWithMapper(
			Class<? extends EntityWrapperMapper> mapperClass, O o, OW ow) {
		
		EntityWrapperMapper customMapper = appCtx.getBean(WordUtils.uncapitalize(mapperClass.getSimpleName()), mapperClass);
		
		customMapper.map(o, ow);
	}
}
