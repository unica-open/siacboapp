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
@Table(name="siac_r_ordinativo_atto_amm")
public class SiacROrdinativoAttoAmm extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_ORDINATIVO_ATTO_AMM_ORDAMMID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_ORDINATIVO_ATTO_AMM_ORD_AMM_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_ORDINATIVO_ATTO_AMM_ORDAMMID_GENERATOR")
	@Column(name="ord_amm_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="attoamm_id")
	private SiacTAttoAmm attoAmministrativo;

	@ManyToOne
	@JoinColumn(name="ord_id")
	private SiacTOrdinativo ordinativo;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTAttoAmm getAttoAmministrativo() {
		return attoAmministrativo;
	}

	public void setAttoAmministrativo(SiacTAttoAmm attoAmministrativo) {
		this.attoAmministrativo = attoAmministrativo;
	}

	public SiacTOrdinativo getOrdinativo() {
		return ordinativo;
	}

	public void setOrdinativo(SiacTOrdinativo ordinativo) {
		this.ordinativo = ordinativo;
	}

}