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

/**
 * The persistent class for the siac_t_account database table.
 * 
 */
@Entity
@Table(name = "siac_r_report_importi")
public class SiacRReportImporti extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1152721926348140494L;

	@Id
	@SequenceGenerator(name = "siac_r_report_importi_reprimp_id_seq_GENERATOR", allocationSize = 1, sequenceName = "siac_r_report_importi_reprimp_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "siac_r_report_importi_reprimp_id_seq_GENERATOR")
	@Column(name = "reprimp_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "rep_id")
	private SiacTReport report;

	@ManyToOne
	@JoinColumn(name = "repimp_id")
	private SiacTReportImporti importo;

	@Column(name = "posizione_stampa")
	private Integer posizioneStampa;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTReport getReport() {
		return report;
	}

	public void setReport(SiacTReport report) {
		this.report = report;
	}

	public SiacTReportImporti getImporto() {
		return importo;
	}

	public void setImporto(SiacTReportImporti importo) {
		this.importo = importo;
	}

	public Integer getPosizioneStampa() {
		return posizioneStampa;
	}

	public void setPosizioneStampa(Integer posizioneStampa) {
		this.posizioneStampa = posizioneStampa;
	}

}