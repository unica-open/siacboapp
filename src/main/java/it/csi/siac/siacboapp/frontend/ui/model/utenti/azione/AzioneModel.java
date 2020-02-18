/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.azione;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDAzioneTipo;
import it.csi.siac.siacboapp.integration.entity.SiacDGruppoAzioni;
import it.csi.siac.siacboapp.integration.entity.SiacDRuoloOp;
import it.csi.siac.siacboapp.integration.entity.SiacTAzione;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class AzioneModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3108801744643204091L;

	private List<SiacDAzioneTipo> elencoTipiAzione;
	private List<SiacDGruppoAzioni> elencoGruppiAzioni;
	private List<SiacDRuoloOp> elencoRuoliOp;

	private SiacTAzione azione;

	public List<SiacDAzioneTipo> getElencoTipiAzione() {
		return elencoTipiAzione;
	}

	public void setElencoTipiAzione(List<SiacDAzioneTipo> list) {
		this.elencoTipiAzione = list;
	}

	public List<SiacDGruppoAzioni> getElencoGruppiAzioni() {
		return elencoGruppiAzioni;
	}

	public void setElencoGruppiAzioni(List<SiacDGruppoAzioni> elencoGruppiAzioni) {
		this.elencoGruppiAzioni = elencoGruppiAzioni;
	}

	public List<SiacDRuoloOp> getElencoRuoliOp() {
		return elencoRuoliOp;
	}

	public void setElencoRuoliOp(List<SiacDRuoloOp> elencoRuoliOp) {
		this.elencoRuoliOp = elencoRuoliOp;
	}

	public SiacTAzione getAzione() {
		return azione;
	}

	public void setAzione(SiacTAzione azione) {
		this.azione = azione;
	}

}
