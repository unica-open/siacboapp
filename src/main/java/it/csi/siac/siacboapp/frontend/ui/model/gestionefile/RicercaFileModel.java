/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.gestionefile;

import java.util.ArrayList;
import java.util.List;

import it.csi.siac.siaccommonapp.model.GenericModel;
import it.csi.siac.siaccorser.model.file.StatoFile;
import it.csi.siac.siaccorser.model.file.TipoFile;

public class RicercaFileModel extends GenericModel {
	private static final long serialVersionUID = 4687058893221652870L;

	private String codice;
	private String codiceStato;
	private String dataUpload;
	private List<TipoFile> elencoTipoFile = new ArrayList<TipoFile>();
	private List<StatoFile> elencoStatoFile = new ArrayList<StatoFile>();
	private Integer idTipo;
	private String nome;

	public String getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(String dataUpload) {
		this.dataUpload = dataUpload;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TipoFile> getElencoTipoFile() {
		return elencoTipoFile;
	}

	public void setElencoTipoFile(List<TipoFile> elencoTipoFile) {
		this.elencoTipoFile = elencoTipoFile;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public List<StatoFile> getElencoStatoFile() {
		return elencoStatoFile;
	}

	public void setElencoStatoFile(List<StatoFile> elencoStatoFile) {
		this.elencoStatoFile = elencoStatoFile;
	}

	public String getCodiceStato() {
		return codiceStato;
	}

	public void setCodiceStato(String codiceStato) {
		this.codiceStato = codiceStato;
	}

}
