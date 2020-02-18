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
@Table(name="siac_r_soggetto_classe")
@NamedQuery(name="SiacRSoggettoClasse.findAll", query="SELECT s FROM SiacRSoggettoClasse s")
public class SiacRSoggettoClasse extends SiacTEnteBase {

	private static final long serialVersionUID = -2718545466708992819L;

	@Id
	@SequenceGenerator(name="SIAC_R_SOGGETTO_CLASSE_SOGGETTOCLASSERID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_SOGGETTO_CLASSE_SOGGETTO_CLASSE_R_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_SOGGETTO_CLASSE_SOGGETTOCLASSERID_GENERATOR")
	@Column(name="soggetto_classe_r_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="soggetto_classe_id")
	private SiacDSoggettoClasse classe;

	@ManyToOne
	@JoinColumn(name="soggetto_id")
	private SiacTSoggetto soggetto;

	public SiacRSoggettoClasse() {
	}

	@Override
	public Integer getUid() {
		return this.uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacDSoggettoClasse getClasse() {
		return classe;
	}

	public void setClasse(SiacDSoggettoClasse classe) {
		this.classe = classe;
	}

	public SiacTSoggetto getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggetto soggetto) {
		this.soggetto = soggetto;
	}

}