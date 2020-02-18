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

@Entity
@Table(name = "siac_t_ente_oil")
public class SiacTEnteOil extends SiacTEnteBase {

	private static final long serialVersionUID = 6776877576748699124L;

	@Id
	@SequenceGenerator(name = "SIAC_T_ENTE_OIL_ENTE_OILID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_ENTE_OIL_ENTE_OIL_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_ENTE_OIL_ENTE_OILID_GENERATOR")
	@Column(name = "ente_oil_id")
	private Integer uid;

	@Column(name = "ente_oil_invio_escl_annulli")
	private Boolean escludiAnnulli;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Boolean getEscludiAnnulli() {
		return escludiAnnulli;
	}

	public void setEscludiAnnulli(Boolean escludiAnnulli) {
		this.escludiAnnulli = escludiAnnulli;
	}

}