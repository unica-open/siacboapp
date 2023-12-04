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
@Table(name="siac_t_ordinativo_ts")
public class SiacTOrdinativoTs extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_T_ORDINATIVO_TS_ORDTSID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_ORDINATIVO_TS_ORD_TS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_ORDINATIVO_TS_ORDTSID_GENERATOR")
	@Column(name="ord_ts_id")
	private Integer uid;

	@OneToMany(mappedBy="ordinativoTs")
	private List<SiacTOrdinativoTsDet> dettagliOrdinativoTs;

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


	public SiacTOrdinativo getOrdinativo() {
		return ordinativo;
	}

	public void setOrdinativo(SiacTOrdinativo ordinativo) {
		this.ordinativo = ordinativo;
	}

	public List<SiacTOrdinativoTsDet> getDettagliOrdinativoTs() {
		return dettagliOrdinativoTs;
	}

	public void setDettagliOrdinativoTs(List<SiacTOrdinativoTsDet> dettagliOrdinativoTs) {
		this.dettagliOrdinativoTs = dettagliOrdinativoTs;
	}
}