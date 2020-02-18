/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper.base;

import it.csi.siac.siacboapp.util.entitywrapper.BaseEntityWrapper;
import it.csi.siac.siaccommonser.integration.entity.SiacTBase;

public abstract class BaseEntityWrapperMapper<O extends SiacTBase, OW extends BaseEntityWrapper> implements EntityWrapperMapper {
	
	public abstract void map(O o, OW ow);
	
	@SuppressWarnings("unchecked")
	@Override
	public void map(Object o, Object ow) {
		map((O) o, (OW) ow);
	}
}
