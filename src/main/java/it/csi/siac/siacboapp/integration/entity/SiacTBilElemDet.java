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
@Table(name = "siac_t_bil_elem_det")
public class SiacTBilElemDet extends SiacTEnteBase {
	private static final long serialVersionUID = -4368759158468085281L;

	@Id
	@SequenceGenerator(name = "SIAC_T_BIL_ELEM_DET_ELEMDETID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_BIL_ELEM_DET_ELEM_DET_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_BIL_ELEM_DET_ELEMDETID_GENERATOR")
	@Column(name = "elem_det_id")
	private Integer uid;

	@Column(name = "elem_det_importo")
	private BigDecimal importo;

	@ManyToOne
	@JoinColumn(name = "elem_id")
	private SiacTBilElem elementoBilancio;

	@ManyToOne
	@JoinColumn(name = "elem_det_tipo_id")
	private SiacDBilElemDetTipo tipo;

	@ManyToOne
	@JoinColumn(name = "periodo_id")
	private SiacTPeriodo periodo;

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

	public SiacTBilElem getElementoBilancio() {
		return elementoBilancio;
	}

	public void setElementoBilancio(SiacTBilElem elementoBilancio) {
		this.elementoBilancio = elementoBilancio;
	}

	public SiacDBilElemDetTipo getTipo() {
		return tipo;
	}

	public void setTipo(SiacDBilElemDetTipo tipo) {
		this.tipo = tipo;
	}

	public SiacTPeriodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(SiacTPeriodo periodo) {
		this.periodo = periodo;
	}

}