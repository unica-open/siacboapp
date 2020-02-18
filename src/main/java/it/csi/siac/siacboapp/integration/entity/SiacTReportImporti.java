/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the siac_t_account database table.
 * 
 */
@Entity
@Table(name = "siac_t_report_importi")
public class SiacTReportImporti extends SiacTEnteBase {

	private static final long serialVersionUID = 1340909592883408349L;

	@Id
	@SequenceGenerator(name = "siac_t_report_importi_repimp_id_seq_GENERATOR", allocationSize = 1, sequenceName = "siac_t_report_importi_repimp_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "siac_t_report_importi_repimp_id_seq_GENERATOR")
	@Column(name = "repimp_id")
	private Integer uid;

	@Column(name = "repimp_progr_riga")
	private Integer riga;

	@Column(name = "repimp_codice")
	private String codice;

	@Column(name = "repimp_desc")
	private String descrizione;

	@Column(name = "repimp_modificabile")
	private String modificabile;

	@Column(name = "repimp_importo")
	private BigDecimal importo;

	@OneToMany(mappedBy = "importo", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRReportImporti> report;

	@ManyToOne
	@JoinColumn(name = "bil_id")
	private SiacTBil bilancio;

	@ManyToOne
	@JoinColumn(name = "periodo_id")
	private SiacTPeriodo periodo;

	public String getModificabile() {
		return modificabile;
	}

	public void setModificabile(String modificabile) {
		this.modificabile = modificabile;
	}

	public Set<SiacRReportImporti> getReport() {
		return report;
	}

	public void setReport(Set<SiacRReportImporti> report) {
		this.report = report;
	}

	public SiacTBil getBilancio() {
		return bilancio;
	}

	public void setBilancio(SiacTBil bilancio) {
		this.bilancio = bilancio;
	}

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean isModificabile() {
		return !"N".equalsIgnoreCase(modificabile);
	}

	public SiacTPeriodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(SiacTPeriodo periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public Integer getRiga() {
		return riga;
	}

	public void setRiga(Integer riga) {
		this.riga = riga;
	}

}