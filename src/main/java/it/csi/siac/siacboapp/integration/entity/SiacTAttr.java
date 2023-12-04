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
@Table(name = "siac_t_attr")
public class SiacTAttr extends SiacTEnteBase {

	private static final long serialVersionUID = -8005787827069003434L;

	@Id
	@SequenceGenerator(name = "SIAC_T_ATTR_ATTRID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_ATTR_ATTR_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_ATTR_ATTRID_GENERATOR")
	@Column(name = "attr_id")
	private Integer uid;

	@Column(name = "attr_code")
	private String codice;

	@OneToMany(mappedBy = "attributo")
	private List<SiacRProgrammaAttr> programmi;

	@OneToMany(mappedBy="attributo")
	private List<SiacRMovgestTsAttr> movimentiGestioneTS;

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public List<SiacRProgrammaAttr> getProgrammi() {
		return programmi;
	}

	public void setProgrammi(List<SiacRProgrammaAttr> programmi) {
		this.programmi = programmi;
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

}