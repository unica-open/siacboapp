/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the siac_t_subdoc database table.
 * 
 */
@Entity
@Table(name="siac_t_subdoc")
public class SiacTSubdoc extends SiacTEnteBase {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_T_SUBDOC_SUBDOCID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_SUBDOC_SUBDOC_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_SUBDOC_SUBDOCID_GENERATOR")
	@Column(name="subdoc_id")
	private Integer uid;

	@Column(name="subdoc_convalida_manuale")
	private String convalidaManuale;

	@Column(name="subdoc_data_pagamento_cec")
	private Date dataPagamentoCec;

	@Column(name="subdoc_data_scadenza")
	private Date dataScadenza;

	@Column(name="subdoc_desc")
	private String descrizione;

	@Column(name="subdoc_importo")
	private BigDecimal importo;

	@Column(name="subdoc_importo_da_dedurre")
	private BigDecimal importoDaDedurre;

	@Column(name="subdoc_nreg_iva")
	private String nregIva;

	@Column(name="subdoc_numero")
	private Integer numero;

	@Column(name="subdoc_pagato_cec")
	private Boolean pagatoCec;

	@Column(name="subdoc_splitreverse_importo")
	private BigDecimal splitreverseImporto;

//	//bi-directional many-to-one association to SiacRCartacontDetSubdoc
//	@OneToMany(mappedBy="siacTSubdoc")
//	private List<SiacRCartacontDetSubdoc> siacRCartacontDetSubdocs;
//
//	//bi-directional many-to-one association to SiacRElencoDocSubdoc
//	@OneToMany(mappedBy="siacTSubdoc")
//	private List<SiacRElencoDocSubdoc> siacRElencoDocSubdocs;
//
//	//bi-directional many-to-one association to SiacRPredocSubdoc
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRPredocSubdoc> siacRPredocSubdocs;
//
//	//bi-directional many-to-one association to SiacRSubdoc
//	/** The siac r subdocs a. */
//	@OneToMany(mappedBy="siacTSubdocA", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdoc> siacRSubdocsA;
//
//	//bi-directional many-to-one association to SiacRSubdoc
//	/** The siac r subdocs b. */
//	@OneToMany(mappedBy="siacTSubdocB", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdoc> siacRSubdocsB;
//
//	//bi-directional many-to-one association to SiacRSubdocAttoAmm
//	/** The siac r subdoc atto amms. */
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdocAttoAmm> siacRSubdocAttoAmms;
//
//	//bi-directional many-to-one association to SiacRSubdocAttr
//	/** The siac r subdoc attrs. */
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdocAttr> siacRSubdocAttrs;
//
//	//bi-directional many-to-one association to SiacRSubdocClass
//	/** The siac r subdoc classes. */
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdocClass> siacRSubdocClasses;
//
//	//bi-directional many-to-one association to SiacRSubdocLiquidazione
//	/** The siac r subdoc liquidaziones. */
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdocLiquidazione> siacRSubdocLiquidaziones;
//
//	//bi-directional many-to-one association to SiacRSubdocModpag
//	/** The siac r subdoc modpags. */
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdocModpag> siacRSubdocModpags;
//
//	//bi-directional many-to-one association to SiacRSubdocMovgestT
//	/** The siac r subdoc movgest ts. */
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdocMovgestT> siacRSubdocMovgestTs;
//
//	//bi-directional many-to-one association to SiacRSubdocProvCassa
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdocProvCassa> siacRSubdocProvCassas;
//
//	//bi-directional many-to-one association to SiacRSubdocOrdinativoT
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdocOrdinativoT> siacRSubdocOrdinativoTs;
//
//	//bi-directional many-to-one association to SiacRSubdocSog
//	/** The siac r subdoc sogs. */
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacRSubdocSog> siacRSubdocSogs;
//	
//	//bi-directional many-to-one association to SiacRSubdocSubdocIva
//	/** The siac r subdoc subdoc ivas. */
//	@OneToMany(mappedBy="siacTSubdoc")
//	private List<SiacRSubdocSubdocIva> siacRSubdocSubdocIvas;
//
//	//bi-directional many-to-one association to SiacRSubdocSubdocIva
//	/** The siac r subdoc subdoc ivas. */
//	@OneToMany(mappedBy="siacTSubdoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
//	private List<SiacTSubdocSospensione> siacTSubdocSospensiones;


	//bi-directional many-to-one association to SiacTDoc
	/** The siac t doc. */
	@ManyToOne
	@JoinColumn(name="doc_id")
	private SiacTDoc siacTDoc;
	

	public SiacTSubdoc() {
	}

	@PrePersist
	@PreUpdate
	public void prePersist() {
		if(this.pagatoCec == null) {
			this.pagatoCec = Boolean.FALSE;
		}
	}


	/* (non-Javadoc)
	 * @see it.csi.siac.siaccommonser.integration.entity.SiacTBase#getUid()
	 */
	@Override
	public Integer getUid() {
		return uid;
	}

	/* (non-Javadoc)
	 * @see it.csi.siac.siaccommonser.integration.entity.SiacTBase#setUid(java.lang.Integer)
	 */
	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getConvalidaManuale() {
		return convalidaManuale;
	}

	public void setConvalidaManuale(String convalidaManuale) {
		this.convalidaManuale = convalidaManuale;
	}

	public Date getDataPagamentoCec() {
		return dataPagamentoCec;
	}

	public void setDataPagamentoCec(Date dataPagamentoCec) {
		this.dataPagamentoCec = dataPagamentoCec;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public BigDecimal getImportoDaDedurre() {
		return importoDaDedurre;
	}

	public void setImportoDaDedurre(BigDecimal importoDaDedurre) {
		this.importoDaDedurre = importoDaDedurre;
	}

	public String getNregIva() {
		return nregIva;
	}

	public void setNregIva(String nregIva) {
		this.nregIva = nregIva;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getPagatoCec() {
		return pagatoCec;
	}

	public void setPagatoCec(Boolean pagatoCec) {
		this.pagatoCec = pagatoCec;
	}

	public BigDecimal getSplitreverseImporto() {
		return splitreverseImporto;
	}

	public void setSplitreverseImporto(BigDecimal splitreverseImporto) {
		this.splitreverseImporto = splitreverseImporto;
	}

	public SiacTDoc getSiacTDoc() {
		return siacTDoc;
	}

	public void setSiacTDoc(SiacTDoc siacTDoc) {
		this.siacTDoc = siacTDoc;
	}
}