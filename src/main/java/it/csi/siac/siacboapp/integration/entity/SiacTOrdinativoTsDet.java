/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.math.BigDecimal;

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
@Table(name="siac_t_ordinativo_ts_det")
public class SiacTOrdinativoTsDet extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_T_ORDINATIVO_TS_DET_ORDTSDETID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_ORDINATIVO_TS_DET_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_ORDINATIVO_TS_DET_ORDTSDETID_GENERATOR")
	@Column(name="ord_ts_det_id")
	private Integer uid;

	@Column(name="ord_ts_det_importo")
	private BigDecimal importo;

	@ManyToOne
	@JoinColumn(name="ord_ts_id")
	private SiacTOrdinativoTs ordinativoTs;

	@ManyToOne
	@JoinColumn(name="ord_ts_det_tipo_id")
	private SiacDOrdinativoTsDetTipo tipo;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public SiacTOrdinativoTs getOrdinativoTs() {
		return ordinativoTs;
	}

	public void setOrdinativoTs(SiacTOrdinativoTs ordinativoTs) {
		this.ordinativoTs = ordinativoTs;
	}

	public SiacDOrdinativoTsDetTipo getTipo() {
		return tipo;
	}

	public void setTipo(SiacDOrdinativoTsDetTipo tipo) {
		this.tipo = tipo;
	}
}