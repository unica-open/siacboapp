/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_periodo")
public class SiacTPeriodo extends SiacTEnteBase {
	private static final long serialVersionUID = 623293138521580371L;

	@Id
	@Column(name = "periodo_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERIODO")
	@SequenceGenerator(name = "SEQ_PERIODO", allocationSize = 1, sequenceName = "siac_t_periodo_periodo_id_seq")
	private Integer uid;

	@Column(name = "periodo_code")
	private String codice;

	@Basic
	@Column(name = "data_inizio")
	private Date dataInizio;

	@Basic
	@Column(name = "data_fine")
	private Date dataFine;

	@Basic
	@Column(name = "anno")
	private String anno;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

}
