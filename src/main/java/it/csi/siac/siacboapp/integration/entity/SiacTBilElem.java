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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;


@Entity
@Table(name = "siac_t_bil_elem")
public class SiacTBilElem extends SiacTEnteBase {
	private static final long serialVersionUID = 1058117457513127183L;

	@Id
	@SequenceGenerator(name = "SIAC_T_BIL_ELEM_ELEMID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_BIL_ELEM_ELEM_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_BIL_ELEM_ELEMID_GENERATOR")
	@Column(name = "elem_id")
	private Integer uid;

	@Column(name = "elem_code")
	private String codice;

	@Column(name = "elem_code2")
	private String codiceArticolo;

	@Column(name = "elem_code3")
	private String codiceUeb;

	@Column(name = "elem_desc")
	private String descrizione;

	@Column(name = "elem_desc2")
	private String descrizioneArticolo;

	@Column(name = "elem_id_padre")
	private Integer uidPadre;

	private String ordine;

	@ManyToOne
	@JoinColumn(name = "elem_tipo_id")
	private SiacDBilElemTipo tipo;

	@OneToMany
	@JoinTable(name = "siac_r_bil_elem_class", joinColumns = @JoinColumn(name = "elem_id"), inverseJoinColumns = @JoinColumn(name = "classif_id"))
	private Set<SiacTClass> classificatori;

	@ManyToOne
	@JoinColumn(name="bil_id")
	private SiacTBil bilancio;

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

	public Integer getUidPadre() {
		return uidPadre;
	}

	public void setUidPadre(Integer uidPadre) {
		this.uidPadre = uidPadre;
	}

	public String getOrdine() {
		return ordine;
	}

	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public SiacDBilElemTipo getTipo() {
		return tipo;
	}

	public void setTipo(SiacDBilElemTipo tipo) {
		this.tipo = tipo;
	}

	public Set<SiacTClass> getClassificatori() {
		return classificatori;
	}

	public void setClassificatori(Set<SiacTClass> classificatori) {
		this.classificatori = classificatori;
	}

	public String getCodiceArticolo() {
		return codiceArticolo;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	public String getCodiceUeb() {
		return codiceUeb;
	}

	public void setCodiceUeb(String codiceUeb) {
		this.codiceUeb = codiceUeb;
	}

	public String getDescrizioneArticolo() {
		return descrizioneArticolo;
	}

	public void setDescrizioneArticolo(String descrizioneArticolo) {
		this.descrizioneArticolo = descrizioneArticolo;
	}

	public String getCodiceCompleto() {
		return String.format("%s/%s/%s", codice, codiceArticolo, codiceUeb);
	}

	public String getDescrizioneCompleta() {
		return descrizione + 
				(StringUtils.isNotBlank(descrizioneArticolo)  ?  " - "  + descrizioneArticolo : "");
	}

	public SiacTBil getBilancio() {
		return bilancio;
	}

	public void setBilancio(SiacTBil bilancio) {
		this.bilancio = bilancio;
	}

}