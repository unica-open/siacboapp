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
@Table(name="siac_d_ordinativo_stato")
public class SiacDOrdinativoStato extends SiacTEnteBase {
	private static final long serialVersionUID = 5962355283922615418L;

	@Id
	@SequenceGenerator(name="SIAC_D_ORDINATIVO_STATO_ORDINATIVOSTATOID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_ORDINATIVO_STATO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_ORDINATIVO_STATO_ORDINATIVOSTATOID_GENERATOR")
	@Column(name="ord_stato_id")
	private Integer uid;

	@Column(name="ord_stato_code")
	private String codice;

	@Column(name="ord_stato_desc")
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