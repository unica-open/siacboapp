/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.gruppoazioni;

import it.csi.siac.siacboapp.integration.entity.SiacDGruppoAzioni;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class GruppoAzioniModel extends GenericModel {

	/**
	 *  
	 */
	private static final long serialVersionUID = 766054512260095361L;
	private SiacDGruppoAzioni gruppoAzioni;

	public SiacDGruppoAzioni getGruppoAzioni() {
		return gruppoAzioni;
	}

	public void setGruppoAzioni(SiacDGruppoAzioni gruppoAzioni) {
		this.gruppoAzioni = gruppoAzioni;
	}

}
