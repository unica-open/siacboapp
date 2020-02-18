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
@Table(name="siac_r_atto_amm_class")
public class SiacRAttoAmmClass extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_ATTO_AMM_CLASS_ATTOAMMCLASSID_GENERATOR", allocationSize=1, sequenceName="siac_r_atto_amm_class_atto_amm_class_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_ATTO_AMM_CLASS_ATTOAMMCLASSID_GENERATOR")
	@Column(name="atto_amm_class_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="attoamm_id")
	private SiacTAttoAmm attoAmministrativo;

	@ManyToOne
	@JoinColumn(name="classif_id")
	private SiacTClass sac;

	public SiacRAttoAmmClass() {
	}

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

	public SiacTClass getSac() {
		return sac;
	}

	public void setSac(SiacTClass sac) {
		this.sac = sac;
	}
}