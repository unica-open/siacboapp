/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SiacTConfIndicatoriBase extends SiacTEnteBase {

	private static final long serialVersionUID = 7107115413410518527L;
	
	
	@ManyToOne
	@JoinColumn(name = "bil_id")
	private SiacTBil bilancio;

	public SiacTBil getBilancio()
	{
		return bilancio;
	}

	public void setBilancio(SiacTBil bilancio)
	{
		this.bilancio = bilancio;
	}
}
