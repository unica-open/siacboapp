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
@Table(name="siac_r_ordinativo_soggetto")
@NamedQuery(name="SiacROrdinativoSoggetto.findAll", query="SELECT s FROM SiacROrdinativoSoggetto s")
public class SiacROrdinativoSoggetto extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_ORDINATIVO_SOGGETTO_ORDSOGGETTOID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_ORDINATIVO_SOGGETTO_ORD_SOGGETTO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_ORDINATIVO_SOGGETTO_ORDSOGGETTOID_GENERATOR")
	@Column(name="ord_soggetto_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="ord_id")
	private SiacTOrdinativo ordinativo;

	@ManyToOne
	@JoinColumn(name="soggetto_id")
	private SiacTSoggetto soggetto;

	public SiacROrdinativoSoggetto() {
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTOrdinativo getOrdinativo() {
		return ordinativo;
	}

	public void setOrdinativo(SiacTOrdinativo ordinativo) {
		this.ordinativo = ordinativo;
	}

	public SiacTSoggetto getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggetto soggetto) {
		this.soggetto = soggetto;
	}
}