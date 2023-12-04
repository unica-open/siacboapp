/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.documenti;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDDocTipo;
import it.csi.siac.siacboapp.integration.entity.SiacDSoggettoClasse;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTDocWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class AnnullaAttivazioniContabiliModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2663766596200208802L;
	
	private List<SiacDSoggettoClasse> elencoClassiSoggetto;
	private List<SiacDDocTipo> elencoTipiDocumentoEntrata;
	private List<SiacDDocTipo> elencoTipiDocumentoSpesa;
	private CriteriRicercaDocumenti criteri;
	private SiacTDocWrapper documento;
	private String codiceInc;


	
	public CriteriRicercaDocumenti getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaDocumenti criteri) {
		this.criteri = criteri;
	}

	public List<SiacDDocTipo> getElencoTipiDocumentoEntrata() {
		return elencoTipiDocumentoEntrata;
	}

	public void setElencoTipiDocumentoEntrata(List<SiacDDocTipo> elencoTipiDocumentoEntrata) {
		this.elencoTipiDocumentoEntrata = elencoTipiDocumentoEntrata;
	}

	public List<SiacDDocTipo> getElencoTipiDocumentoSpesa() {
		return elencoTipiDocumentoSpesa;
	}

	public void setElencoTipiDocumentoSpesa(List<SiacDDocTipo> elencoTipiDocumentoSpesa) {
		this.elencoTipiDocumentoSpesa = elencoTipiDocumentoSpesa;
	}

	public List<SiacDSoggettoClasse> getElencoClassiSoggetto() {
		return elencoClassiSoggetto;
	}

	public void setElencoClassiSoggetto(List<SiacDSoggettoClasse> elencoClassiSoggetto) {
		this.elencoClassiSoggetto = elencoClassiSoggetto;
	}

	public SiacTDocWrapper getDocumento() {
		return documento;
	}

	public void setDocumento(SiacTDocWrapper documento) {
		this.documento = documento;
	}

	public String getCodiceInc() {
		return codiceInc;
	}

	public void setCodiceInc(String codiceInc) {
		this.codiceInc = codiceInc;
	}
	
	public void reset() {
		documento = null;
		criteri = null;
		codiceInc = null;
	}
}
