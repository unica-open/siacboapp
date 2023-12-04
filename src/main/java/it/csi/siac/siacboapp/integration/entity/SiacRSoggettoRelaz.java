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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="siac_r_soggetto_relaz")
public class SiacRSoggettoRelaz extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_SOGGETTO_RELAZ_SOGGETTORELAZID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_SOGGETTO_RELAZ_SOGGETTO_RELAZ_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_SOGGETTO_RELAZ_SOGGETTORELAZID_GENERATOR")
	@Column(name="soggetto_relaz_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="relaz_tipo_id")
	private SiacDRelazTipo tipoRelazione;

	@ManyToOne
	@JoinColumn(name="soggetto_id_da")
	private SiacTSoggetto soggettoDa;

	@ManyToOne
	@JoinColumn(name="soggetto_id_a")
	private SiacTSoggetto soggettoA;


	public SiacRSoggettoRelaz() {
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacDRelazTipo getTipoRelazione() {
		return tipoRelazione;
	}

	public void setTipoRelazione(SiacDRelazTipo tipoRelazione) {
		this.tipoRelazione = tipoRelazione;
	}

	public SiacTSoggetto getSoggettoDa() {
		return soggettoDa;
	}

	public void setSoggettoDa(SiacTSoggetto soggettoDa) {
		this.soggettoDa = soggettoDa;
	}

	public SiacTSoggetto getSoggettoA() {
		return soggettoA;
	}

	public void setSoggettoA(SiacTSoggetto soggettoA) {
		this.soggettoA = soggettoA;
	}

}