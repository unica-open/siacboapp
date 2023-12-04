/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="siac_d_class_fam")
public class SiacDClassFam extends SiacTEnteBase {
	

	private static final long serialVersionUID = -1968473200024503302L;

	@Id
	@SequenceGenerator(name="SIAC_D_CLASS_FAM_CLASSIFFAMID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_CLASS_FAM_CLASSIF_FAM_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_CLASS_FAM_CLASSIFFAMID_GENERATOR")
	@Column(name="classif_fam_id")
	private Integer uid;

	@Column(name="classif_fam_code")
	private String codice;

	
	@OneToMany(mappedBy="tipoFamigliaClassificatori")
	private Set<SiacTClassFamTree> famiglieClassificatori;


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

	public Set<SiacTClassFamTree> getFamiglieClassificatori() {
		return famiglieClassificatori;
	}

	public void setFamiglieClassificatori(Set<SiacTClassFamTree> famiglieClassificatori) {
		this.famiglieClassificatori = famiglieClassificatori;
	}

}