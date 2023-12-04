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
@Table(name="siac_r_doc_sog")
public class SiacRDocSog extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_DOC_SOG_DOCSOGID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_DOC_SOG_DOC_SOG_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_DOC_SOG_DOCSOGID_GENERATOR")
	@Column(name="doc_sog_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="doc_id")
	private SiacTDoc documento;

	@ManyToOne
	@JoinColumn(name="soggetto_id")
	private SiacTSoggetto soggetto;

	@Override
	public Integer getUid() {
		return uid;
	}

	public SiacTDoc getDocumento() {
		return documento;
	}

	public void setDocumento(SiacTDoc documento) {
		this.documento = documento;
	}

	public SiacTSoggetto getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggetto soggetto) {
		this.soggetto = soggetto;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
		
	}

}