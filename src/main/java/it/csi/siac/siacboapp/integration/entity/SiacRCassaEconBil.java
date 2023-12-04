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
@Table(name = "siac_r_cassa_econ_bil")
public class SiacRCassaEconBil extends SiacTEnteBase {
	private static final long serialVersionUID = 2705323996725573960L;

	@Id
	@SequenceGenerator(name = "SIAC_R_CASSA_ECON_BIL_CASSAECONBILID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_CASSA_ECON_BIL_CASSAECONBIL_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_CASSA_ECON_BIL_CASSAECONBILID_GENERATOR")
	@Column(name = "cassaeconbil_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "cassaecon_id")
	private SiacTCassaEcon cassaEconomale;

	@ManyToOne
	@JoinColumn(name = "bil_id")
	private SiacTBil bilancio;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTCassaEcon getCassaEconomale() {
		return cassaEconomale;
	}

	public void setCassaEconomale(SiacTCassaEcon cassaEconomale) {
		this.cassaEconomale = cassaEconomale;
	}

	public SiacTBil getBilancio() {
		return bilancio;
	}

	public void setBilancio(SiacTBil bilancio) {
		this.bilancio = bilancio;
	}
}