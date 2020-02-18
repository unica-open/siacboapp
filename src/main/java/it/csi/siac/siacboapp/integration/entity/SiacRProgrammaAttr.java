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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_r_programma_attr")
@NamedQuery(name = "SiacRProgrammaAttr.findAll", query = "SELECT s FROM SiacRProgrammaAttr s")
public class SiacRProgrammaAttr extends SiacTEnteBase {

	private static final long serialVersionUID = -1897187250621131826L;

	@Id
	@SequenceGenerator(name = "SIAC_R_PROGRAMMA_ATTR_PROGRAMMAATTRID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_PROGRAMMA_ATTR_PROGRAMMA_ATTR_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_PROGRAMMA_ATTR_PROGRAMMAATTRID_GENERATOR")
	@Column(name = "programma_attr_id")
	private Integer uid;

	@Column(name = "boolean")
	private String boolean_;

	private BigDecimal numerico;

	private BigDecimal percentuale;

	private String testo;

	@ManyToOne
	@JoinColumn(name = "attr_id")
	private SiacTAttr attributo;

	@ManyToOne
	@JoinColumn(name = "programma_id")
	private SiacTProgramma programma;

	public String getBoolean_() {
		return boolean_;
	}

	public void setBoolean_(String boolean_) {
		this.boolean_ = boolean_;
	}

	public BigDecimal getNumerico() {
		return numerico;
	}

	public void setNumerico(BigDecimal numerico) {
		this.numerico = numerico;
	}

	public BigDecimal getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public SiacTAttr getAttributo() {
		return attributo;
	}

	public void setAttributo(SiacTAttr attributo) {
		this.attributo = attributo;
	}

	public SiacTProgramma getProgramma() {
		return programma;
	}

	public void setProgramma(SiacTProgramma programma) {
		this.programma = programma;
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;

	}

}