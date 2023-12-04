/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.base;

import it.csi.siac.siaccommonser.integration.dao.base.Dao;
import it.csi.siac.siaccommonser.integration.entity.SiacTBase;

public abstract interface BoBaseDao<E extends SiacTBase> extends Dao<E, Integer> {
	@Override
	E create(E entity);
	@Override
	E update(E entity);

}
