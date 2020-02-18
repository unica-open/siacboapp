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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the siac_t_class database table.
 * 
 */
@Entity
@Table(name = "siac_t_class")
@SecondaryTables({ @SecondaryTable(name = "siac_r_class_fam_tree", pkJoinColumns = {
		@PrimaryKeyJoinColumn(name = "classif_id") }) })
public class SiacTClass extends SiacTEnteBase {

	private static final long serialVersionUID = 1211435199802843604L;

	@Id
	@SequenceGenerator(name = "SIAC_T_CLASS_CLASSIFID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_CLASS_CLASSIF_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_CLASS_CLASSIFID_GENERATOR")
	@Column(name = "classif_id")
	private Integer uid;

	@Column(name = "classif_code")
	private String codice;

	@Column(name = "classif_desc")
	private String descrizione;

	@ManyToOne
	@JoinColumn(name = "classif_tipo_id")
	private SiacDClassTipo tipo;

	@ManyToOne
	@JoinColumn(name = "classif_id_padre", referencedColumnName = "classif_id", table = "siac_r_class_fam_tree", insertable = false, updatable = false)
	private SiacTClass parent;

	@OneToMany
	@JoinTable(name = "siac_r_class_fam_tree", joinColumns = @JoinColumn(name = "classif_id_padre"), inverseJoinColumns = @JoinColumn(name = "classif_id"))
	private Set<SiacTClass> children;

	@ManyToOne
	@JoinColumn(name = "classif_fam_tree_id", referencedColumnName = "classif_fam_tree_id", table = "siac_r_class_fam_tree", insertable = false, updatable = false)
	private SiacTClassFamTree famigliaClassificatori;

	private transient String codiceGerarchico;

	public SiacTClass(Integer uid) {
		this();
		setUid(uid);
	}

	public SiacTClass() {
		super();
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

	public String getCodiceDescrizione() {
		return String.format("%s - %s", codice, descrizione);
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public SiacTClass getParent() {
		return parent;
	}

	public void setParent(SiacTClass parent) {
		this.parent = parent;
	}

	public SiacTClassFamTree getFamigliaClassificatori() {
		return famigliaClassificatori;
	}

	public void setFamigliaClassificatori(SiacTClassFamTree famigliaClassificatori) {
		this.famigliaClassificatori = famigliaClassificatori;
	}

	public String getCodiceGerarchico() {
		if (codiceGerarchico == null) {
			codiceGerarchico = codice;

			SiacTClass p = parent;

			while (p != null) {
				codiceGerarchico = p.codice + "-" + codiceGerarchico;

				p = p.parent;
			}
		}

		return codiceGerarchico;
	}

	public SiacDClassTipo getTipo() {
		return tipo;
	}

	public void setTipo(SiacDClassTipo tipo) {
		this.tipo = tipo;
	}

	public Set<SiacTClass> getChildren()
	{
		return children;
	}

	public void setChildren(Set<SiacTClass> children)
	{
		this.children = children;
	}

}