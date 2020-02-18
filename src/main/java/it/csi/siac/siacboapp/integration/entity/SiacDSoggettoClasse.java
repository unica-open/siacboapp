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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="siac_d_soggetto_classe")
@NamedQuery(name="SiacDSoggettoClasse.findAll", query="SELECT s FROM SiacDSoggettoClasse s")
public class SiacDSoggettoClasse extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_D_SOGGETTO_CLASSE_SOGGETTOCLASSEID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_SOGGETTO_CLASSE_SOGGETTO_CLASSE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_SOGGETTO_CLASSE_SOGGETTOCLASSEID_GENERATOR")
	@Column(name="soggetto_classe_id")
	private Integer uid;

	@Column(name="soggetto_classe_code")
	private String codice;

	@Column(name="soggetto_classe_desc")
	private String descrizione;

	@Override
	public Integer getUid() {
		return this.uid;
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