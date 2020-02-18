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
@Table(name="siac_d_distinta")
public class SiacDDistinta extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_D_DISTINTA_DISTID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_DISTINTA_DIST_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_DISTINTA_DISTID_GENERATOR")
	@Column(name="dist_id")
	private Integer uid;

	@Column(name="dist_code")
	private String codice;

	@Column(name="dist_desc")
	private String descrizione;	

	@ManyToOne
	@JoinColumn(name="dist_tipo_id")
	private SiacDDistintaTipo tipo;

	public SiacDDistinta() {
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


	public SiacDDistintaTipo getTipo() {
		return tipo;
	}


	public void setTipo(SiacDDistintaTipo tipo) {
		this.tipo = tipo;
	}

}