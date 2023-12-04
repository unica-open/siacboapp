/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="siac_d_doc_stato")
public class SiacDDocStato extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_D_DOC_STATO_DOCSTATOID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_DOC_STATO_DOC_STATO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_DOC_STATO_DOCSTATOID_GENERATOR")
	@Column(name="doc_stato_id")
	private Integer uid;
	
	@Column(name="doc_stato_code")
	private String codice;

	@Column(name="doc_stato_desc")
	private String descrizione;

	@OneToMany(mappedBy="stato")
	private List<SiacRDocStato> stati;

	public SiacDDocStato() {
	}


	@Override
	public Integer getUid() {
		return uid;
	}

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


	public List<SiacRDocStato> getStati() {
		return stati;
	}


	public void setStati(List<SiacRDocStato> stati) {
		this.stati = stati;
	}

}