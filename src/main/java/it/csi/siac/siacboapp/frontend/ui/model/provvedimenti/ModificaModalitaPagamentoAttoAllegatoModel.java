/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.provvedimenti;

import java.util.List;

import it.csi.siac.siacboapp.frontend.ui.model.common.CriteriRicercaProvvedimenti;
import it.csi.siac.siacboapp.integration.entity.SiacDAttoAmmTipo;
import it.csi.siac.siacboapp.integration.entity.SiacDSoggettoClasse;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTAttoAmmWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTModpagWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSubdocWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ModificaModalitaPagamentoAttoAllegatoModel extends GenericModel {

	private static final long serialVersionUID = 3687747561690934018L;
	
	
	private CriteriRicercaProvvedimenti criteri;
	private String codiceInc;
	private List<SiacDAttoAmmTipo> elencoTipiAttoAmministrativo;
	private List<SiacDSoggettoClasse> elencoClassiSoggetto;
	private SiacTAttoAmmWrapper attoAmministrativo;
	private SiacTSoggettoWrapper soggetto;
	private List<SiacTSubdocWrapper> quote;
	private List<SiacTModpagWrapper> elencoModalitaPagamentoSoggetto;
	private Integer idModalitaPagamentoSoggetto;
	private List<SiacTClassWrapper> elencoStruttureAmministrativoContabiliNoTree;
	private List<SiacTClassWrapper> elencoStruttureAmministrativoContabili;

	public String getCodiceInc() {
		return codiceInc;
	}

	public void setCodiceInc(String codiceInc) {
		this.codiceInc = codiceInc;
	}

	public CriteriRicercaProvvedimenti getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaProvvedimenti criteri) {
		this.criteri = criteri;
	}
	
	public void reset() {
		attoAmministrativo = null;
		soggetto = null;
		quote = null;
		idModalitaPagamentoSoggetto = null;
		criteri = null;
		codiceInc = null;
	}

	public List<SiacDAttoAmmTipo> getElencoTipiAttoAmministrativo() {
		return elencoTipiAttoAmministrativo;
	}

	public void setElencoTipiAttoAmministrativo(List<SiacDAttoAmmTipo> elencoTipiAttoAmministrativo) {
		this.elencoTipiAttoAmministrativo = elencoTipiAttoAmministrativo;
	}

	public List<SiacDSoggettoClasse> getElencoClassiSoggetto() {
		return elencoClassiSoggetto;
	}

	public void setElencoClassiSoggetto(List<SiacDSoggettoClasse> elencoClassiSoggetto) {
		this.elencoClassiSoggetto = elencoClassiSoggetto;
	}

	public SiacTSoggettoWrapper getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggettoWrapper soggetto) {
		this.soggetto = soggetto;
	}

	public SiacTAttoAmmWrapper getAttoAmministrativo() {
		return attoAmministrativo;
	}

	public void setAttoAmministrativo(SiacTAttoAmmWrapper attoAmministrativo) {
		this.attoAmministrativo = attoAmministrativo;
	}

	public List<SiacTSubdocWrapper> getQuote() {
		return quote;
	}

	public void setQuote(List<SiacTSubdocWrapper> quote) {
		this.quote = quote;
	}

	public Integer getIdModalitaPagamentoSoggetto() {
		return idModalitaPagamentoSoggetto;
	}

	public void setIdModalitaPagamentoSoggetto(Integer idModalitaPagamentoSoggetto) {
		this.idModalitaPagamentoSoggetto = idModalitaPagamentoSoggetto;
	}

	public List<SiacTModpagWrapper> getElencoModalitaPagamentoSoggetto() {
		return elencoModalitaPagamentoSoggetto;
	}

	public void setElencoModalitaPagamentoSoggetto(List<SiacTModpagWrapper> elencoModalitaPagamentoSoggetto) {
		this.elencoModalitaPagamentoSoggetto = elencoModalitaPagamentoSoggetto;
	}

	public List<SiacTClassWrapper> getElencoStruttureAmministrativoContabiliNoTree() {
		return elencoStruttureAmministrativoContabiliNoTree;
	}

	public void setElencoStruttureAmministrativoContabiliNoTree(
			List<SiacTClassWrapper> elencoStruttureAmministrativoContabiliNoTree) {
		this.elencoStruttureAmministrativoContabiliNoTree = elencoStruttureAmministrativoContabiliNoTree;
	}

	public List<SiacTClassWrapper> getElencoStruttureAmministrativoContabili() {
		return elencoStruttureAmministrativoContabili;
	}

	public void setElencoStruttureAmministrativoContabili(List<SiacTClassWrapper> elencoStruttureAmministrativoContabili) {
		this.elencoStruttureAmministrativoContabili = elencoStruttureAmministrativoContabili;
	}

}
