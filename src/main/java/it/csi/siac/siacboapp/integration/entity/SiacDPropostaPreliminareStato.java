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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_d_proposta_preliminare_stato")
public class SiacDPropostaPreliminareStato extends SiacTEnteBase {
	private static final long serialVersionUID = -8100071299450607108L;

	@Id
	@SequenceGenerator(name = "SIAC_T_PROPOSTA_PRELIMINARE_STATO_PROP_STATO_ID_GENERATOR", allocationSize = 1, sequenceName = "siac_d_proposta_preliminare_stato_prop_stato_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_PROPOSTA_PRELIMINARE_STATO_PROP_STATO_ID_GENERATOR")
	@Column(name = "prop_stato_id")
	private Integer uid;

	@Column(name = "prop_stato_code")
	private String codice;

	@Column(name = "prop_stato_desc")
	private String descrizione;
	
	
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

	

}