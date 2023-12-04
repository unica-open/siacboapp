/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.setprogetti;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacRFpvSetCronop;
import it.csi.siac.siacboapp.integration.entity.SiacTCronop;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ProgettoModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8915314802844357604L;

	private SiacRFpvSetCronop progetto;
	private List<SiacTCronop> elencoCronoprogrammi;

	public SiacRFpvSetCronop getProgetto() {
		return progetto;
	}

	public void setProgetto(SiacRFpvSetCronop progetto) {
		this.progetto = progetto;
	}

	public List<SiacTCronop> getElencoCronoprogrammi() {
		return elencoCronoprogrammi;
	}

	public void setElencoCronoprogrammi(List<SiacTCronop> elencoCronoprogrammi) {
		this.elencoCronoprogrammi = elencoCronoprogrammi;
	}
	

}
