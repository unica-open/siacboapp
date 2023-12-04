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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="siac_r_soggetto_stato")
@NamedQuery(name="SiacRSoggettoStato.findAll", query="SELECT s FROM SiacRSoggettoStato s")
public class SiacRSoggettoStato extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_SOGGETTO_STATO_SOGGETTOSTATORID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_SOGGETTO_STATO_SOGGETTO_STATO_R_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_SOGGETTO_STATO_SOGGETTOSTATORID_GENERATOR")
	@Column(name="soggetto_stato_r_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="soggetto_stato_id")
	private SiacDSoggettoStato stato;

	@ManyToOne
	@JoinColumn(name="soggetto_id")
	private SiacTSoggetto soggetto;

	public SiacRSoggettoStato() {
	}

	@Override
	public Integer getUid() {
		return this.uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacDSoggettoStato getStato() {
		return stato;
	}

	public void setStato(SiacDSoggettoStato stato) {
		this.stato = stato;
	}

	public SiacTSoggetto getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggetto soggetto) {
		this.soggetto = soggetto;
	}

}