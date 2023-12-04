/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.gruppo;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDRuoloOp;
import it.csi.siac.siacboapp.integration.entity.SiacTClass;
import it.csi.siac.siacboapp.integration.entity.SiacTGruppo;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class GruppoModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8776151414371522077L;

	private SiacTGruppo gruppo;
	private List<SiacDRuoloOp> elencoRuoliOp;
	private List<SiacTClass> elencoStruttureAmministrativeContabili;

	public SiacTGruppo getGruppo() {
		return gruppo;
	}

	public void setGruppo(SiacTGruppo gruppo) {
		this.gruppo = gruppo;
	}

	public List<SiacDRuoloOp> getElencoRuoliOp() {
		return elencoRuoliOp;
	}

	public void setElencoRuoliOp(List<SiacDRuoloOp> elencoRuoliOp) {
		this.elencoRuoliOp = elencoRuoliOp;
	}

	public List<SiacTClass> getElencoStruttureAmministrativeContabili() {
		return elencoStruttureAmministrativeContabili;
	}

	public void setElencoStruttureAmministrativeContabili(
			List<SiacTClass> elencoStruttureAmministrativeContabili) {
		this.elencoStruttureAmministrativeContabili = elencoStruttureAmministrativeContabili;
	}

}
