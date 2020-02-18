/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriteriSelezioneCapitoli implements Serializable {
	private static final long serialVersionUID = 7242388710932608589L;

	private Integer idMissione;
	private List<Integer> idProgrammi = new ArrayList<Integer>();
	private List<Integer> idStruttureAmministrativoContabili = new ArrayList<Integer>();
	private Map<String, Integer> idClassificatoriGenerici = new HashMap<String, Integer>();

	public Integer getIdMissione() {
		return idMissione;
	}

	public void setIdMissione(Integer idMissione) {
		this.idMissione = idMissione;
	}

	public Integer getIdProgramma() {
		if (idProgrammi.isEmpty())
			return null;

		return idProgrammi.get(0);
	}

	public void setIdProgramma(Integer idProgramma) {
		this.idProgrammi = new ArrayList<Integer>();
		this.idProgrammi.add(idProgramma);
	}

	public Integer getIdClassificatoreGenerico(String codiceTipoClassificatore) {
		if (! idClassificatoriGenerici.containsKey(codiceTipoClassificatore))
			return null;
		
		return idClassificatoriGenerici.get(codiceTipoClassificatore);
	}

	public void setIdClassificatoreGenerico(String codiceTipoClassificatore, Integer idClassificatoreGenerico) {
		idClassificatoriGenerici.put(codiceTipoClassificatore, idClassificatoreGenerico);
	}

	public List<Integer> getIdProgrammi() {
		return idProgrammi;
	}

	public void setIdProgrammi(List<Integer> idProgrammi) {
		this.idProgrammi = idProgrammi;
	}

	public Integer getIdStrutturaAmministrativoContabile() {
		if (idStruttureAmministrativoContabili.isEmpty())
			return null;

		return idStruttureAmministrativoContabili.get(0);
	}

	public void setIdStrutturaAmministrativoContabile(Integer idStrutturaAmministrativoContabile) {
		this.idStruttureAmministrativoContabili = new ArrayList<Integer>();
		this.idStruttureAmministrativoContabili.add(idStrutturaAmministrativoContabile);
	}

	public List<Integer> getIdStruttureAmministrativoContabili() {
		return idStruttureAmministrativoContabili;
	}

	public void setIdStruttureAmministrativoContabili(List<Integer> idStruttureAmministrativoContabili) {
		this.idStruttureAmministrativoContabili = idStruttureAmministrativoContabili;
	}

	public Map<String, Integer> getIdClassificatoriGenerici() {
		return idClassificatoriGenerici;
	}
	
	
}
