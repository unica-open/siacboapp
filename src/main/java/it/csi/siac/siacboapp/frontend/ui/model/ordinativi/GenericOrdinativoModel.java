/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDOrdinativoTipo;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;
import it.csi.siac.siaccorser.model.Bilancio;

public abstract class GenericOrdinativoModel extends GenericModel {

	/** Per la serializzazione */
	private static final long serialVersionUID = 1L;

	private List<SiacDOrdinativoTipo> elencoTipiOrdinativo;
	private SiacTOrdinativoWrapper ordinativo;
	private CriteriRicercaOrdinativi criteri;
	private Bilancio bilancio;
	
	/**
	 * @return the elencoTipiOrdinativo
	 */
	public List<SiacDOrdinativoTipo> getElencoTipiOrdinativo() {
		return elencoTipiOrdinativo;
	}

	/**
	 * @param elencoTipiOrdinativo the elencoTipiOrdinativo to set
	 */
	public void setElencoTipiOrdinativo(List<SiacDOrdinativoTipo> elencoTipiOrdinativo) {
		this.elencoTipiOrdinativo = elencoTipiOrdinativo;
	}

	/**
	 * @return the criteri
	 */
	public CriteriRicercaOrdinativi getCriteri() {
		return criteri;
	}

	/**
	 * @param criteri the criteri to set
	 */
	public void setCriteri(CriteriRicercaOrdinativi criteri) {
		this.criteri = criteri;
	}

	/**
	 * @return the ordinativo
	 */
	public SiacTOrdinativoWrapper getOrdinativo() {
		return ordinativo;
	}

	/**
	 * @param ordinativo the ordinativo to set
	 */
	public void setOrdinativo(SiacTOrdinativoWrapper ordinativo) {
		this.ordinativo = ordinativo;
	}

	/**
	 * @return the bilancio
	 */
	public Bilancio getBilancio() {
		return bilancio;
	}

	/**
	 * @param bilancio the bilancio to set
	 */
	public void setBilancio(Bilancio bilancio) {
		this.bilancio = bilancio;
	}
	
	
}
