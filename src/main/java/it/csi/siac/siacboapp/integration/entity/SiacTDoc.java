/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.math.BigDecimal;
import java.util.Date;
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
@Table(name="siac_t_doc")
public class SiacTDoc extends SiacTEnteBase {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_T_DOC_DOCID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_DOC_DOC_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_DOC_DOCID_GENERATOR")
	@Column(name="doc_id")
	private Integer uid;

	@Column(name="doc_anno")
	private Integer anno;

	@Column(name="doc_data_emissione")
	private Date dataEmissione;

	@Column(name="doc_data_scadenza")
	private Date dataScadenza;

	@Column(name="doc_desc")
	private String descrizione;

	@Column(name="doc_importo")
	private BigDecimal importo;

	@Column(name="doc_numero")
	private String numero;
	
	@ManyToOne
	@JoinColumn(name="doc_tipo_id")
	private SiacDDocTipo tipo;

	@OneToMany(mappedBy="documento")
	private Set<SiacRDocSog> soggetti;

	@OneToMany(mappedBy="siacTDoc")
	private List<SiacTSubdoc> quote;
	
	@OneToMany(mappedBy="documento")
	private List<SiacRDocStato> stati;


/*
	//bi-directional many-to-one association to SiacRDoc
	@OneToMany(mappedBy="siacTDocPadre", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	private List<SiacRDoc> siacRDocsPadre;

	//bi-directional many-to-one association to SiacRDoc
	@OneToMany(mappedBy="siacTDocFiglio", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	private List<SiacRDoc> siacRDocsFiglio;

	//bi-directional many-to-one association to SiacRDocAttr
	@OneToMany(mappedBy="siacTDoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	private List<SiacRDocAttr> siacRDocAttrs;

	//bi-directional many-to-one association to SiacRDocClass
	@OneToMany(mappedBy="siacTDoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	private List<SiacRDocClass> siacRDocClasses;

	//bi-directional many-to-one association to SiacRDocIva
	@OneToMany(mappedBy="siacTDoc")
	private List<SiacRDocIva> siacRDocIvas;
	
	//bi-directional many-to-one association to SiacRDocOnere
	@OneToMany(mappedBy="siacTDoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	private List<SiacRDocOnere> siacRDocOneres;

	//bi-directional many-to-one association to SiacRDocSog

	
	@OneToMany(mappedBy="siacTDoc") //NO Cascades!!
	private List<SiacRDocSirfel> siacRDocSirfels;

	//bi-directional many-to-one association to SiacDCodicebollo
	@ManyToOne
	@JoinColumn(name="codbollo_id")
	private SiacDCodicebollo siacDCodicebollo;

	//bi-directional many-to-one association to SiacDDocTipo
	
	//bi-directional many-to-one association to SiacDPccCodice
	@ManyToOne
	@JoinColumn(name="pcccod_id")
	private SiacDPccCodice siacDPccCodice;

	//bi-directional many-to-one association to SiacDPccUfficio
	@ManyToOne
	@JoinColumn(name="pccuff_id")
	private SiacDPccUfficio siacDPccUfficio;

	//bi-directional many-to-one association to SiacTSubdoc
	@OneToMany(mappedBy="siacTDoc")
	private List<SiacTSubdoc> siacTSubdocs;

	//bi-directional many-to-one association to SiacTSubdocNum
	@OneToMany(mappedBy="siacTDoc")
	private List<SiacTSubdocNum> siacTSubdocNums;
	
	//bi-directional many-to-one association to SiacTRegistroPcc
	@OneToMany(mappedBy="siacTDoc")
	private List<SiacTRegistroPcc> siacTRegistroPccs;
	
	//bi-directional many-to-one association to SiacRDocOrdine
	@OneToMany(mappedBy="siacTDoc")
	private List<SiacRDocOrdine> siacRDocOrdines;
	
	
	//bi-directional many-to-one association to SiacTRegistrounicoDoc
	@OneToOne(mappedBy="siacTDoc", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	private SiacTRegistrounicoDoc siacTRegistrounicoDoc;

	//bi-directional many-to-one association to SiacDSiopeDocumentoTipo
	@ManyToOne
	@JoinColumn(name="siope_documento_tipo_id")
	private SiacDSiopeDocumentoTipo siacDSiopeDocumentoTipo;

	//bi-directional many-to-one association to SiacDSiopeDocumentoTipoAnalogico
	@ManyToOne
	@JoinColumn(name="siope_documento_tipo_analogico_id")
	private SiacDSiopeDocumentoTipoAnalogico siacDSiopeDocumentoTipoAnalogico;
	
	*/
	
	public Set<SiacRDocSog> getSoggetti() {
		return soggetti;
	}

	public void setSoggetti(Set<SiacRDocSog> soggetti) {
		this.soggetti = soggetti;
	}

	public SiacDDocTipo getTipo() {
		return tipo;
	}

	public void setTipo(SiacDDocTipo tipo) {
		this.tipo = tipo;
	}

	public SiacTDoc() {
	}

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

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<SiacTSubdoc> getQuote() {
		return quote;
	}

	public void setQuote(List<SiacTSubdoc> quote) {
		this.quote = quote;
	}

	public List<SiacRDocStato> getStati() {
		return stati;
	}

	public void setStati(List<SiacRDocStato> stati) {
		this.stati = stati;
	}

	
}