/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import it.csi.siac.siacbilser.model.Missione;
import it.csi.siac.siacbilser.model.Programma;
import it.csi.siac.siaccommonapp.model.GenericModel;
import it.csi.siac.siaccorser.model.ClassificatoreGenerico;
import it.csi.siac.siaccorser.model.TipoClassificatore;

public class GestionePropostePreliminariModel extends GenericModel {
	private static final long serialVersionUID = 1575550111028687375L;

	private List<Missione> elencoMissioni;
	private List<Programma> elencoProgrammi;
	private String elencoStruttureAmministrativoContabiliJson;
	private Map<TipoClassificatore, List<ClassificatoreGenerico>> elencoClassificatoriGenerici;

	private CriteriSelezioneCapitoli criteriSelezioneCapitoli = new CriteriSelezioneCapitoli();

	public List<Missione> getElencoMissioni() {
		return elencoMissioni;
	}

	public void setElencoMissioni(List<Missione> elencoMissioni) {
		this.elencoMissioni = elencoMissioni;
	}

	public List<Programma> getElencoProgrammi() {
		return elencoProgrammi;
	}

	public void setElencoProgrammi(List<Programma> elencoProgrammi) {
		this.elencoProgrammi = elencoProgrammi;
	}

	public CriteriSelezioneCapitoli getCriteriSelezioneCapitoli() {
		return criteriSelezioneCapitoli;
	}

	public void setCriteriSelezioneCapitoli(CriteriSelezioneCapitoli criteriSelezioneCapitoli) {
		this.criteriSelezioneCapitoli = criteriSelezioneCapitoli;
	}

	public String getElencoStruttureAmministrativoContabiliJson() {
		return elencoStruttureAmministrativoContabiliJson;
	}

	public void setElencoStruttureAmministrativoContabiliJson(String elencoStruttureAmministrativoContabiliJson) {
		this.elencoStruttureAmministrativoContabiliJson = elencoStruttureAmministrativoContabiliJson;
	}

	public Map<TipoClassificatore, List<ClassificatoreGenerico>> getElencoClassificatoriGenerici() {
		return elencoClassificatoriGenerici;
	}

	public void setElencoClassificatoriGenerici(
			Map<TipoClassificatore, List<ClassificatoreGenerico>> elencoClassificatoriGenerici) {
		this.elencoClassificatoriGenerici = elencoClassificatoriGenerici;
	}

	public void setElencoClassificatoriGenerici(List<ClassificatoreGenerico> elencoClassificatoriGenerici) {
		Map<TipoClassificatore, List<ClassificatoreGenerico>> mapElencoClassificatoriGenerici = new LinkedHashMap<TipoClassificatore, List<ClassificatoreGenerico>>();

		String prevCodiceTipoClassificatore = null;
		List<ClassificatoreGenerico> currList = null;
		
		for (ClassificatoreGenerico c : elencoClassificatoriGenerici) {
			if (! c.getTipoClassificatore().getCodice().equals(prevCodiceTipoClassificatore)) {
				prevCodiceTipoClassificatore = c.getTipoClassificatore().getCodice();
				
				mapElencoClassificatoriGenerici.put(c.getTipoClassificatore(), currList = new ArrayList<ClassificatoreGenerico>());
			}
			
			currList.add(c);
		}

		setElencoClassificatoriGenerici(mapElencoClassificatoriGenerici);
	}
}
