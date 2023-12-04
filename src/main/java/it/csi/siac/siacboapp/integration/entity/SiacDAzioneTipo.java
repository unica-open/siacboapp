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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the siac_d_azione_tipo database table.
 * 
 */
@Entity
@Table(name = "siac_d_azione_tipo")
public class SiacDAzioneTipo extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2187527741779626559L;

	@Id
	@SequenceGenerator(name = "SIAC_D_AZIONE_TIPO_AZIONETIPOID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_D_AZIONE_TIPO_AZIONE_TIPO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_D_AZIONE_TIPO_AZIONETIPOID_GENERATOR")
	@Column(name = "azione_tipo_id")
	private Integer uid;

	@Column(name = "azione_tipo_code")
	private String codice;

	@Column(name = "azione_tipo_desc")
	private String descrizione;

	public SiacDAzioneTipo(Integer uid) {
		this();
		setUid(uid);
	}

	public SiacDAzioneTipo() {
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
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

}