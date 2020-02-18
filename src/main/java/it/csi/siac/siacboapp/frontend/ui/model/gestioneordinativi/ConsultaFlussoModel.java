/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ConsultaFlussoModel extends GenericModel {
	private static final long serialVersionUID = -7920036784911470784L;

	private List<SiacTOrdinativo> elencoOrdinativi;

	public List<SiacTOrdinativo> getElencoOrdinativi() {
		return elencoOrdinativi;
	}

	public void setElencoOrdinativi(List<SiacTOrdinativo> elencoOrdinativi) {
		this.elencoOrdinativi = elencoOrdinativi;
	}

}
