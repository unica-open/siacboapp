/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.ruoloop;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDRuoloOp;
import it.csi.siac.siacboapp.integration.entity.SiacTAzione;
import it.csi.siac.siacboapp.integration.entity.SiacTBil;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class RuoloOpModel extends GenericModel {

	private static final long serialVersionUID = -8639132367132414137L;

	private List<SiacTAzione> elencoAzioni;
	private List<SiacTBil> elencoBilanci;

	private SiacDRuoloOp ruoloOp;

	public SiacDRuoloOp getRuoloOp() {
		return ruoloOp;
	}

	public void setRuoloOp(SiacDRuoloOp ruoloOp) {
		this.ruoloOp = ruoloOp;
	}

	public List<SiacTAzione> getElencoAzioni() {
		return elencoAzioni;
	}

	public void setElencoAzioni(List<SiacTAzione> elencoAzioni) {
		this.elencoAzioni = elencoAzioni;
	}

	public List<SiacTBil> getElencoBilanci()
	{
		return elencoBilanci;
	}

	public void setElencoBilanci(List<SiacTBil> elencoBilanci)
	{
		this.elencoBilanci = elencoBilanci;
	}

}
