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
@Table(name="siac_r_ordinativo_prov_cassa")
public class SiacROrdinativoProvCassa extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_ORDINATIVO_PROV_CASSA_ORDPROVCID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_ORDINATIVO_PROV_CASSA_ORD_PROVC_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_ORDINATIVO_PROV_CASSA_ORDPROVCID_GENERATOR")
	@Column(name="ord_provc_id")
	private Integer uid;

	@Column(name="ord_provc_importo")
	private BigDecimal importo;

	@ManyToOne
	@JoinColumn(name="ord_id")
	private SiacTOrdinativo ordinativo;

	@ManyToOne
	@JoinColumn(name="provc_id")
	private SiacTProvCassa provvisorioCassa;

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

	public SiacTOrdinativo getOrdinativo() {
		return ordinativo;
	}

	public void setOrdinativo(SiacTOrdinativo ordinativo) {
		this.ordinativo = ordinativo;
	}

	public SiacTProvCassa getProvvisorioCassa() {
		return provvisorioCassa;
	}

	public void setProvvisorioCassa(SiacTProvCassa provvisorioCassa) {
		this.provvisorioCassa = provvisorioCassa;
	}
}