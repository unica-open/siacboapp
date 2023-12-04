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

/**
 * The persistent class for the siac_t_class_fam_tree database table.
 * 
 */
@Entity
@Table(name = "siac_t_class_fam_tree")
public class SiacTClassFamTree extends SiacTEnteBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8817840166521658909L;

	@Id
	@SequenceGenerator(name = "SIAC_T_CLASS_FAM_TREE_CLASSIFFAMTREEID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_CLASS_FAM_TREE_CLASSIF_FAM_TREE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_CLASS_FAM_TREE_CLASSIFFAMTREEID_GENERATOR")
	@Column(name = "classif_fam_tree_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "classif_fam_id")
	private SiacDClassFam tipoFamigliaClassificatori;

	
	public SiacDClassFam getTipoFamigliaClassificatori() {
		return tipoFamigliaClassificatori;
	}

	public void setTipoFamigliaClassificatori(SiacDClassFam tipoFamigliaClassificatori) {
		this.tipoFamigliaClassificatori = tipoFamigliaClassificatori;
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