/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.setprogetti;

import it.csi.siac.siacboapp.integration.entity.SiacTFpvSetCronop;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class SetProgettiModel extends GenericModel {

	private static final long serialVersionUID = 5207667906609666762L;

	private SiacTFpvSetCronop setProgetti;

	private String initTipoCronoprogramma;

	public SiacTFpvSetCronop getSetProgetti() {
		return setProgetti;
	}

	public void setSetProgetti(SiacTFpvSetCronop setProgetti) {
		this.setProgetti = setProgetti;
	}

	public String getInitTipoCronoprogramma() {
		return initTipoCronoprogramma;
	}

	public void setInitTipoCronoprogramma(String initTipoCronoprogramma) {
		this.initTipoCronoprogramma = initTipoCronoprogramma;
	}


	
	
}
