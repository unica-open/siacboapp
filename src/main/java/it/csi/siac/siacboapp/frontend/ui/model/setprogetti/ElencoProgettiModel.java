/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.setprogetti;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacRFpvSetCronop;
import it.csi.siac.siacboapp.integration.entity.SiacTFpvSetCronop;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoProgettiModel extends GenericModel {
	private static final long serialVersionUID = 3487914304187040545L;

	private List<SiacRFpvSetCronop> elencoProgetti;
	
	private SiacTFpvSetCronop setProgetti;

	public List<SiacRFpvSetCronop> getElencoProgetti() {
		return elencoProgetti;
	}

	public void setElencoProgetti(List<SiacRFpvSetCronop> elencoProgetti) {
		this.elencoProgetti = elencoProgetti;
	}

	public SiacTFpvSetCronop getSetProgetti() {
		return setProgetti;
	}

	public void setSetProgetti(SiacTFpvSetCronop setProgetti) {
		this.setProgetti = setProgetti;
	}

}
