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
@Table(name="siac_r_ordinativo")
public class SiacROrdinativo extends SiacTEnteBase {

	private static final long serialVersionUID = 3406344834014137593L;

	@Id
	@SequenceGenerator(name="SIAC_R_ORDINATIVO_ORDRID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_ORDINATIVO_ORD_R_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_ORDINATIVO_ORDRID_GENERATOR")
	@Column(name="ord_r_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="relaz_tipo_id")
	private SiacDRelazTipo tipoRelazione;

	@ManyToOne
	@JoinColumn(name="ord_id_da")
	private SiacTOrdinativo ordinativoDa;

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

	public SiacTOrdinativo getOrdinativoDa() {
		return ordinativoDa;
	}

	public void setOrdinativoDa(SiacTOrdinativo ordinativoDa) {
		this.ordinativoDa = ordinativoDa;
	}
	
	
}