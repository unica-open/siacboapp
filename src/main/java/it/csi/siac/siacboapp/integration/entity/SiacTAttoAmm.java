/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="siac_t_atto_amm")
public class SiacTAttoAmm extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_T_ATTO_AMM_ATTOAMMID_GENERATOR", allocationSize=1, sequenceName="siac_t_atto_amm_attoamm_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_ATTO_AMM_ATTOAMMID_GENERATOR")
	@Column(name="attoamm_id")
	private Integer uid;

	@Column(name="attoamm_anno")
	private Integer anno;

	@Column(name="attoamm_numero")
	private Integer numero;

	@Column(name="attoamm_oggetto")
	private String oggetto;

	@OneToMany(mappedBy="attoAmministrativo")
	private Set<SiacRAttoAmmClass> elencoSac;

	@ManyToOne
	@JoinColumn(name="attoamm_tipo_id")
	private SiacDAttoAmmTipo tipo;

	
	@OneToMany(mappedBy="attoAmministrativo")
	private List<SiacRAttoAmmStato> stati;
	
	@Column(name="attoamm_blocco")
	private Boolean bloccoRagioneria;


	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public SiacDAttoAmmTipo getTipo() {
		return tipo;
	}

	public void setTipo(SiacDAttoAmmTipo tipo) {
		this.tipo = tipo;
	}

	public Set<SiacRAttoAmmClass> getElencoSac() {
		return elencoSac;
	}

	public void setElencoSac(Set<SiacRAttoAmmClass> elencoSac) {
		this.elencoSac = elencoSac;
	}

	public SiacTClass getSac() {
		return elencoSac != null && ! elencoSac.isEmpty() ? elencoSac.iterator().next().getSac() : null;
	}

	public List<SiacRAttoAmmStato> getStati() {
		return stati;
	}

	public void setStati(List<SiacRAttoAmmStato> stati) {
		this.stati = stati;
	}
	
	public SiacDAttoAmmStato getStato() {
		return stati != null && ! stati.isEmpty() ? stati.iterator().next().getStato() : null;
	}

	public Boolean getBloccoRagioneria() {
		return bloccoRagioneria;
	}

	public void setBloccoRagioneria(Boolean bloccoRagioneria) {
		this.bloccoRagioneria = bloccoRagioneria;
	}
}