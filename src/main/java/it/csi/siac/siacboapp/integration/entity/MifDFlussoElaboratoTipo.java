/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mif_d_flusso_elaborato_tipo")
public class MifDFlussoElaboratoTipo extends SiacTEnteBase {
	private static final long serialVersionUID = 500661442638505715L;

	@Id
	@Column(name = "flusso_elab_mif_tipo_id")
	private Integer uid;

	@Column(name = "flusso_elab_mif_tipo_code")
	private String codice;

	@Column(name = "flusso_elab_mif_tipo_desc")
	private String descrizione;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}