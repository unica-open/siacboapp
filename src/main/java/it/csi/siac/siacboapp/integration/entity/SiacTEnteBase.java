/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import it.csi.siac.siaccommonser.integration.entity.SiacTBase;

@MappedSuperclass
public abstract class SiacTEnteBase extends SiacTBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4325308115755417001L;

	@ManyToOne
	@JoinColumn(name = "ente_proprietario_id")
	private SiacTEnteProprietario enteProprietario;

	public void init(int enteId, String loginOperazione) {
		super.setDataModificaInserimento(new Date());
		setEnteProprietario(new SiacTEnteProprietario(enteId));
		setLoginOperazione(loginOperazione);	
	}

	public SiacTEnteProprietario getEnteProprietario() {
		return enteProprietario;
	}

	public void setEnteProprietario(SiacTEnteProprietario enteProprietario) {
		this.enteProprietario = enteProprietario;
	}

}