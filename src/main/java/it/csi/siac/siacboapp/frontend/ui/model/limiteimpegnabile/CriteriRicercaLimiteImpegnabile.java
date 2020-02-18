/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.limiteimpegnabile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CriteriRicercaLimiteImpegnabile implements Serializable {
	private static final long serialVersionUID = 7243865622816171126L;

	private String numeroCapitolo;
	private String numeroArticolo;
	private String numeroUeb;
	private List<Integer> idStruttureAmministrativoContabili = new ArrayList<Integer>();
	private String anno;

	public String getNumeroCapitolo() {
		return numeroCapitolo;
	}

	public void setNumeroCapitolo(String numeroCapitolo) {
		this.numeroCapitolo = numeroCapitolo;
	}

	public String getNumeroArticolo() {
		return numeroArticolo;
	}

	public void setNumeroArticolo(String numeroArticolo) {
		this.numeroArticolo = numeroArticolo;
	}

	public String getNumeroUeb() {
		return numeroUeb;
	}

	public void setNumeroUeb(String numeroUeb) {
		this.numeroUeb = numeroUeb;
	}

	public Integer getIdStrutturaAmministrativoContabile() {
		if (idStruttureAmministrativoContabili.isEmpty())
			return null;

		return idStruttureAmministrativoContabili.get(0);
	}

	public void setIdStrutturaAmministrativoContabile(Integer idStrutturaAmministrativoContabile) {
		if (idStrutturaAmministrativoContabile != null) {
			this.idStruttureAmministrativoContabili = new ArrayList<Integer>();
			this.idStruttureAmministrativoContabili.add(idStrutturaAmministrativoContabile);
		}
	}

	public List<Integer> getIdStruttureAmministrativoContabili() {
		return idStruttureAmministrativoContabili;
	}

	public void setIdStruttureAmministrativoContabili(List<Integer> idStruttureAmministrativoContabili) {
		this.idStruttureAmministrativoContabili = idStruttureAmministrativoContabili;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}
}
