/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.impegni;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDSoggettoClasse;
import it.csi.siac.siacboapp.util.entitywrapper.ImpegnoWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class EliminaSoggettoCollegatoModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3251692949154172380L;

	private CriteriRicercaImpegni criteri;
	private ImpegnoWrapper impegno;
	private String codiceInc;
	private List<SiacDSoggettoClasse> elencoClassiSoggetto;
	
	public String getCodiceInc() {
		return codiceInc;
	}

	public void setCodiceInc(String codiceInc) {
		this.codiceInc = codiceInc;
	}

	public CriteriRicercaImpegni getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaImpegni criteri) {
		this.criteri = criteri;
	}

	public ImpegnoWrapper getImpegno() {
		return impegno;
	}

	public void setImpegno(ImpegnoWrapper impegno) {
		this.impegno = impegno;
	}
	
	public void reset() {
		impegno = null;
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
