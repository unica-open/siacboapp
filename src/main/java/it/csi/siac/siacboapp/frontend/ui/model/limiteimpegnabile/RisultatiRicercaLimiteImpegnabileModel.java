/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.limiteimpegnabile;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class RisultatiRicercaLimiteImpegnabileModel extends GenericModel {
	private static final long serialVersionUID = 1219793290751121375L;
	
	private List<SiacTBilElem> elencoCapitoliLimiteImpegnabile;

	public List<SiacTBilElem> getElencoCapitoliLimiteImpegnabile() {
		return elencoCapitoliLimiteImpegnabile;
	}

	public void setElencoCapitoliLimiteImpegnabile(List<SiacTBilElem> elencoCapitoliLimiteImpegnabile) {
		this.elencoCapitoliLimiteImpegnabile = elencoCapitoliLimiteImpegnabile;
	}

}
