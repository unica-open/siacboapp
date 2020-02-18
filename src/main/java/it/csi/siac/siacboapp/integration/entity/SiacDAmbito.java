/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the siac_d_ambito database table.
 * 
 */
@Entity
@Table(name = "siac_d_ambito")
public class SiacDAmbito extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 635546469167825569L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ambito_id")
	private Integer uid;

	@Column(name = "ambito_code")
	private String codice;

	@Column(name = "ambito_desc")
	private String descrizione;

	public SiacDAmbito(Integer uid) {
		this();
		setUid(uid);
	}

	public SiacDAmbito() {
		super();
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

	@Override
	public Integer getUid() {
		return this.uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

}