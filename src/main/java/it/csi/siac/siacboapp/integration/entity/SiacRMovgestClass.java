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
@Table(name="siac_r_movgest_class")
public class SiacRMovgestClass extends SiacTEnteBase {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_MOVGEST_CLASS_MOVGESTCLASSIFID_GENERATOR", allocationSize = 1, sequenceName="SIAC_R_MOVGEST_CLASS_MOVGEST_CLASSIF_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_MOVGEST_CLASS_MOVGESTCLASSIFID_GENERATOR")
	@Column(name="movgest_classif_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="classif_id")
	private SiacTClass classe;

	@ManyToOne
	@JoinColumn(name="movgest_ts_id")
	private SiacTMovgestT siacTMovgestT;

	public SiacRMovgestClass() {
	}


	public SiacTMovgestT getSiacTMovgestT() {
		return this.siacTMovgestT;
	}

	public void setSiacTMovgestT(SiacTMovgestT siacTMovgestT) {
		this.siacTMovgestT = siacTMovgestT;
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public SiacTClass getClasse() {
		return classe;
	}


	public void setClasse(SiacTClass classe) {
		this.classe = classe;
	}

}