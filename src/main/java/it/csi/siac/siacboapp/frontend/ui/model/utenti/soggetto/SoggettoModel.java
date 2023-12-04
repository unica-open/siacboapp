/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.soggetto;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDAmbito;
import it.csi.siac.siacboapp.integration.entity.SiacDRuolo;
import it.csi.siac.siacboapp.integration.entity.SiacTSoggetto;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class SoggettoModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7921453052861251132L;

	private SiacTSoggetto soggetto;
	private List<SiacDRuolo> elencoRuoli;
	private List<SiacDAmbito> elencoAmbiti;

	public SiacTSoggetto getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggetto soggetto) {
		this.soggetto = soggetto;
	}

	public List<SiacDRuolo> getElencoRuoli() {
		return elencoRuoli;
	}

	public void setElencoRuoli(List<SiacDRuolo> elencoRuoli) {
		this.elencoRuoli = elencoRuoli;
	}

	public List<SiacDAmbito> getElencoAmbiti() {
		return elencoAmbiti;
	}

	public void setElencoAmbiti(List<SiacDAmbito> elencoAmbiti) {
		this.elencoAmbiti = elencoAmbiti;
	}

}
