/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "siac_d_bil_elem_det_tipo")
@NamedQuery(name = "SiacDBilElemDetTipo.findAll", query = "SELECT s FROM SiacDBilElemDetTipo s")
public class SiacDBilElemDetTipo extends SiacTEnteBase {
	private static final long serialVersionUID = -7103059115055481413L;

	@Id
	@Column(name = "elem_det_tipo_id")
	private Integer uid;

	@Column(name = "elem_det_tipo_code")
	private String codice;

	@Column(name = "elem_det_tipo_desc")
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