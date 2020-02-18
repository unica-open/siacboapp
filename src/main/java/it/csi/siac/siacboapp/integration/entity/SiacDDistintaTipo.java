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
@Table(name="siac_d_distinta_tipo")
public class SiacDDistintaTipo extends SiacTEnteBase {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_D_DISTINTA_TIPO_DISTTIPOID_GENERATOR", allocationSize = 1, sequenceName="SIAC_D_DISTINTA_TIPO_DIST_TIPO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_DISTINTA_TIPO_DISTTIPOID_GENERATOR")
	@Column(name="dist_tipo_id")
	private Integer uid;

	@Column(name="dist_tipo_code")
	private String codice;

	@Column(name="dist_tipo_desc")
	private String decrizione;

	public SiacDDistintaTipo() {
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

	public String getDecrizione() {
		return decrizione;
	}

	public void setDecrizione(String decrizione) {
		this.decrizione = decrizione;
	}

}