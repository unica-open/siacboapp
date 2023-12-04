/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.accertamenti;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDSoggettoClasse;
import it.csi.siac.siacboapp.util.entitywrapper.AccertamentoWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class EliminaSoggettoCollegatoModel extends GenericModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5641657766495121532L;
	
	
	private CriteriRicercaAccertamenti criteri;
	private AccertamentoWrapper accertamento;
	private String codiceInc;
	private List<SiacDSoggettoClasse> elencoClassiSoggetto;
	
	public String getCodiceInc() {
		return codiceInc;
	}

	public void setCodiceInc(String codiceInc) {
		this.codiceInc = codiceInc;
	}

	public CriteriRicercaAccertamenti getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaAccertamenti criteri) {
		this.criteri = criteri;
	}

	public AccertamentoWrapper getAccertamento() {
		return accertamento;
	}

	public void setAccertamento(AccertamentoWrapper accertamento) {
		this.accertamento = accertamento;
	}
	
	public void reset() {
		accertamento = null;
		criteri = null;
		codiceInc = null;
	}

	public List<SiacDSoggettoClasse> getElencoClassiSoggetto() {
		return elencoClassiSoggetto;
	}

	public void setElencoClassiSoggetto(List<SiacDSoggettoClasse> elencoClassiSoggetto) {
		this.elencoClassiSoggetto = elencoClassiSoggetto;
	}

}
