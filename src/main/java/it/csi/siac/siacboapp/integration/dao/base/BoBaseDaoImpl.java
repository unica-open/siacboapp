/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.base;

import java.util.Date;

import it.csi.siac.siaccommonser.integration.dao.base.JpaDao;
import it.csi.siac.siaccommonser.integration.entity.SiacTBase;

public abstract class BoBaseDaoImpl<E extends SiacTBase> extends JpaDao<E, Integer> implements
		BoBaseDao<E> {

	@Override
	public E create(E entity) {
		entity.setDataCreazione(new Date());
		entity.setDataInizioValidita(new Date());
		entity.setDataModifica(new Date());

		super.save(entity);

		return entity;
	}

	@Override
	public E update(E entity) {
		entity.setDataModifica(new Date());
		return super.update(entity);
	}
}
