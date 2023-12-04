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
@Table(name = "siac_r_ordinativo_stato")
public class SiacROrdinativoStato extends SiacTEnteBase {
	private static final long serialVersionUID = -7692104152073985805L;

	@Id
	@SequenceGenerator(name = "SIAC_R_ORDINATIVO_STATO_ORDINATIVOSTATORID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_ORDINATIVO_STATO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_ORDINATIVO_STATO_ORDINATIVOSTATORID_GENERATOR")
	@Column(name = "ord_stato_r_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "ord_stato_id")
	private SiacDOrdinativoStato stato;

	@ManyToOne
	@JoinColumn(name = "ord_id")
	private SiacTOrdinativo ordinativo;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacDOrdinativoStato getStato() {
		return stato;
	}

	public void setStato(SiacDOrdinativoStato stato) {
		this.stato = stato;
	}

	public SiacTOrdinativo getOrdinativo() {
		return ordinativo;
	}

	public void setOrdinativo(SiacTOrdinativo ordinativo) {
		this.ordinativo = ordinativo;
	}

	
}