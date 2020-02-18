/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the siac_d_ruolo database table.
 * 
 */
@Entity
@Table(name = "siac_d_ruolo")
public class SiacDRuolo extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1489820269858333496L;

	@Id
	@SequenceGenerator(name = "SIAC_D_RUOLO_RUOLOID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_D_RUOLO_RUOLO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_D_RUOLO_RUOLOID_GENERATOR")
	@Column(name = "ruolo_id")
	private Integer uid;

	@Column(name = "ruolo_code")
	private String codice;

	@Column(name = "ruolo_desc")
	private String descrizione;

	@OneToMany(mappedBy = "ruolo")
	private List<SiacRSoggettoRuolo> soggetti;

	public SiacDRuolo(Integer uid) {
		this();
		this.uid = uid;
	}

	public SiacDRuolo() {
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

	public List<SiacRSoggettoRuolo> getSoggetti() {
		return soggetti;
	}

	public void setSoggetti(List<SiacRSoggettoRuolo> soggetti) {
		this.soggetti = soggetti;
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