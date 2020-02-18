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
 * The persistent class for the siac_d_gruppo_azioni database table.
 * 
 */
@Entity
@Table(name = "siac_d_gruppo_azioni")
public class SiacDGruppoAzioni extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2657756260480648866L;

	@Id
	@SequenceGenerator(name = "SIAC_D_GRUPPO_AZIONI_GRUPPOAZIONIID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_D_GRUPPO_AZIONI_GRUPPO_AZIONI_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_D_GRUPPO_AZIONI_GRUPPOAZIONIID_GENERATOR")
	@Column(name = "gruppo_azioni_id")
	private Integer uid;

	@Column(name = "gruppo_azioni_code")
	private String codice;

	@Column(name = "gruppo_azioni_desc")
	private String descrizione;

	private String titolo;

	public SiacDGruppoAzioni() {
		super();
	}

	public SiacDGruppoAzioni(Integer uid) {
		this();
		setUid(uid);
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

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
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