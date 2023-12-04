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

/**
 * The persistent class for the siac_t_account database table.
 * 
 */
@Entity
@Table(name = "siac_s_report_importi")
public class SiacSReportImporti extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1850044347730231522L;

	@Id
	@SequenceGenerator(name = "siac_s_report_importi_repimps_id_seq_GENERATOR", allocationSize = 1, sequenceName = "siac_s_report_importi_repimps_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "siac_s_report_importi_repimps_id_seq_GENERATOR")
	@Column(name = "repimps_id")
	private Integer uid;

	@Column(name = "repimps_codice")
	private String codice;

	@Column(name = "repimps_desc")
	private String descrizione;

	@Column(name = "repimps_importo")
	private BigDecimal importo;

	@ManyToOne
	@JoinColumn(name = "repimp_id")
	private SiacTReportImporti reportImporto;

	public SiacSReportImporti(SiacTReportImporti siacTReportImporti) {
		codice = siacTReportImporti.getCodice();
		descrizione = siacTReportImporti.getDescrizione();
		importo = siacTReportImporti.getImporto();
		reportImporto = siacTReportImporti;

		super.setEnteProprietario(siacTReportImporti.getEnteProprietario());
		super.setDataCreazione(siacTReportImporti.getDataCreazione());
		super.setDataInizioValidita(siacTReportImporti.getDataInizioValidita());
		super.setDataFineValidita(siacTReportImporti.getDataFineValidita());
		super.setDataModifica(siacTReportImporti.getDataModifica());
		super.setDataCancellazione(siacTReportImporti.getDataCancellazione());
		super.setLoginOperazione(siacTReportImporti.getLoginOperazione());
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

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public SiacTReportImporti getReportImporto() {
		return reportImporto;
	}

	public void setReportImporto(SiacTReportImporti reportImporto) {
		this.reportImporto = reportImporto;
	}

}