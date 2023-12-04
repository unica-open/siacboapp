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
@Table(name="siac_d_ordinativo_ts_det_tipo")
public class SiacDOrdinativoTsDetTipo extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_D_ORDINATIVO_TS_DET_TIPO_ORDTSDETTIPOID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_ORDINATIVO_TS_DET_TIPO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_ORDINATIVO_TS_DET_TIPO_ORDTSDETTIPOID_GENERATOR")
	@Column(name="ord_ts_det_tipo_id")
	private Integer uid;

	@Column(name="ord_ts_det_tipo_code")
	private String codice;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
}