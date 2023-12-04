/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.setprogetti;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTFpvSetCronop;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoSetProgettiModel extends GenericModel {

	private static final long serialVersionUID = -8939482817639594969L;

	private List<SiacTFpvSetCronop> elencoSetProgetti;

	public List<SiacTFpvSetCronop> getElencoSetProgetti() {
		return elencoSetProgetti;
	}

	public void setElencoSetProgetti(List<SiacTFpvSetCronop> elencoSetProgetti) {
		this.elencoSetProgetti = elencoSetProgetti;
	}

	

}
