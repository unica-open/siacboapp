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
@Table(name="siac_r_doc_stato")
public class SiacRDocStato extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_DOC_STATO_DOCSTATORID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_DOC_STATO_DOC_STATO_R_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_DOC_STATO_DOCSTATORID_GENERATOR")
	@Column(name="doc_stato_r_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="doc_stato_id")
	private SiacDDocStato stato;

	@ManyToOne
	@JoinColumn(name="doc_id")
	private SiacTDoc documento;

	public SiacRDocStato() {
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
		
	}

	public SiacDDocStato getStato() {
		return stato;
	}

	public void setStato(SiacDDocStato stato) {
		this.stato = stato;
	}

	public SiacTDoc getDocumento() {
		return documento;
	}

	public void setDocumento(SiacTDoc documento) {
		this.documento = documento;
	}

	

}