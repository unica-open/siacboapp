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
@Table(name="siac_r_atto_amm_stato")
public class SiacRAttoAmmStato extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_ATTO_AMM_STATO_ATTATTOAMMSTATOID_GENERATOR", allocationSize=1, sequenceName="siac_r_atto_amm_stato_att_attoamm_stato_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_ATTO_AMM_STATO_ATTATTOAMMSTATOID_GENERATOR")
	@Column(name="att_attoamm_stato_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="attoamm_stato_id")
	private SiacDAttoAmmStato stato;

	@ManyToOne
	@JoinColumn(name="attoamm_id")
	private SiacTAttoAmm attoAmministrativo;

	public SiacRAttoAmmStato() {
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacDAttoAmmStato getStato() {
		return stato;
	}

	public void setStato(SiacDAttoAmmStato stato) {
		this.stato = stato;
	}

	public SiacTAttoAmm getAttoAmministrativo() {
		return attoAmministrativo;
	}

	public void setAttoAmministrativo(SiacTAttoAmm attoAmministrativo) {
		this.attoAmministrativo = attoAmministrativo;
	}
}