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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="siac_d_relaz_tipo")
@NamedQuery(name="SiacDRelazTipo.findAll", query="SELECT s FROM SiacDRelazTipo s")
public class SiacDRelazTipo extends SiacTEnteBase {

	private static final long serialVersionUID = -3422155101711442363L;

	@Id
	@SequenceGenerator(name="SIAC_D_RELAZ_TIPO_RELAZTIPOID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_RELAZ_TIPO_RELAZ_TIPO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_RELAZ_TIPO_RELAZTIPOID_GENERATOR")
	@Column(name="relaz_tipo_id")
	private Integer uid;

	@Column(name="relaz_tipo_code")
	private String codice;

	@OneToMany(mappedBy="tipoRelazione")
	private List<SiacROrdinativo> ordinativi;

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

	public List<SiacROrdinativo> getOrdinativi() {
		return ordinativi;
	}

	public void setOrdinativi(List<SiacROrdinativo> ordinativi) {
		this.ordinativi = ordinativi;
	}
	
	
}