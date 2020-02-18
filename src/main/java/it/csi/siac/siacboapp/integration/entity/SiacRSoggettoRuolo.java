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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_r_soggetto_ruolo")
public class SiacRSoggettoRuolo extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6728868010489076300L;

	@Id
	@SequenceGenerator(name = "SIAC_R_SOGGETTO_RUOLO_SOGGETORUOLOID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_SOGGETTO_RUOLO_SOGGETO_RUOLO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_SOGGETTO_RUOLO_SOGGETORUOLOID_GENERATOR")
	@Column(name = "soggeto_ruolo_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "ruolo_id")
	private SiacDRuolo ruolo;

	@ManyToOne
	@JoinColumn(name = "soggetto_id")
	private SiacTSoggetto soggetto;

	@OneToMany(mappedBy = "soggettoRuolo")
	private List<SiacTAccount> account;

	public SiacRSoggettoRuolo(SiacDRuolo ruolo) {
		this();
		this.ruolo = ruolo;
	}

	public SiacRSoggettoRuolo() {
		super();
	}

	public SiacDRuolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(SiacDRuolo ruolo) {
		this.ruolo = ruolo;
	}

	public SiacTSoggetto getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggetto soggetto) {
		this.soggetto = soggetto;
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