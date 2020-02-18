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
@Table(name="siac_d_bil_elem_tipo")
public class SiacDBilElemTipo extends SiacTEnteBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2520073451795884782L;

	@Id
	@SequenceGenerator(name="SIAC_D_BIL_ELEM_TIPO_ELEMTIPOID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_BIL_ELEM_TIPO_ELEM_TIPO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_BIL_ELEM_TIPO_ELEMTIPOID_GENERATOR")
	@Column(name="elem_tipo_id")
	private Integer uid;

	@Column(name="elem_tipo_code")
	private String codice;

	@Column(name="elem_tipo_desc")
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