/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_proposta_preliminare")
public class SiacTPropostaPreliminare extends SiacTEnteBase {
	private static final long serialVersionUID = 4633898963705839125L;
	
	@Id
	@SequenceGenerator(name = "SIAC_T_PROPOSTA_PRELIMINARE_PROPID_GENERATOR", allocationSize = 1, sequenceName = "siac_t_proposta_preliminare_prop_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_PROPOSTA_PRELIMINARE_PROPID_GENERATOR")
	@Column(name = "prop_id")
	private Integer uid;

	@Column(name = "prop_desc")
	private String descrizione;

	@Column(name = "prop_desc2")
	private String descrizioneArticolo;

	@Column(name = "anno")
	private Integer anno;

	@Column(name = "prop_numero")
	private Integer numero;

	@Column(name = "imp_competenza_anno")
	private BigDecimal importoCompetenzaAnno;

	@Column(name = "imp_competenza_anno_1")
	private BigDecimal importoCompetenzaAnno1;

	@Column(name = "imp_competenza_anno_2")
	private BigDecimal importoCompetenzaAnno2;

	@Column(name = "imp_cassa_anno")
	private BigDecimal importoCassaAnno;

	@Column(name = "imp_cassa_anno_1")
	private BigDecimal importoCassaAnno1;

	@Column(name = "imp_cassa_anno_2")
	private BigDecimal importoCassaAnno2;

	@Column(name = "imp_residuo_anno")
	private BigDecimal importoResiduoAnno;

	@Column(name = "imp_residuo_anno_1")
	private BigDecimal importoResiduoAnno1;

	@Column(name = "imp_residuo_anno_2")
	private BigDecimal importoResiduoAnno2;

	@Column(name = "note")
	private String note;

	@OneToOne
	@JoinColumn(name = "bil_elem_id")
	private SiacTBilElem capitolo;

	@ManyToOne
	@JoinColumn(name = "prop_stato_id")
	private SiacDPropostaPreliminareStato stato;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getImportoCompetenzaAnno() {
		return importoCompetenzaAnno;
	}

	public void setImportoCompetenzaAnno(BigDecimal importoCompetenzaAnno) {
		this.importoCompetenzaAnno = importoCompetenzaAnno;
	}

	public BigDecimal getImportoCompetenzaAnno1() {
		return importoCompetenzaAnno1;
	}

	public void setImportoCompetenzaAnno1(BigDecimal importoCompetenzaAnno1) {
		this.importoCompetenzaAnno1 = importoCompetenzaAnno1;
	}

	public BigDecimal getImportoCompetenzaAnno2() {
		return importoCompetenzaAnno2;
	}

	public void setImportoCompetenzaAnno2(BigDecimal importoCompetenzaAnno2) {
		this.importoCompetenzaAnno2 = importoCompetenzaAnno2;
	}

	public BigDecimal getImportoCassaAnno() {
		return importoCassaAnno;
	}

	public void setImportoCassaAnno(BigDecimal importoCassaAnno) {
		this.importoCassaAnno = importoCassaAnno;
	}

	public BigDecimal getImportoCassaAnno1() {
		return importoCassaAnno1;
	}

	public void setImportoCassaAnno1(BigDecimal importoCassaAnno1) {
		this.importoCassaAnno1 = importoCassaAnno1;
	}

	public BigDecimal getImportoCassaAnno2() {
		return importoCassaAnno2;
	}

	public void setImportoCassaAnno2(BigDecimal importoCassaAnno2) {
		this.importoCassaAnno2 = importoCassaAnno2;
	}

	public BigDecimal getImportoResiduoAnno() {
		return importoResiduoAnno;
	}

	public void setImportoResiduoAnno(BigDecimal importoResiduoAnno) {
		this.importoResiduoAnno = importoResiduoAnno;
	}

	public BigDecimal getImportoResiduoAnno1() {
		return importoResiduoAnno1;
	}

	public void setImportoResiduoAnno1(BigDecimal importoResiduoAnno1) {
		this.importoResiduoAnno1 = importoResiduoAnno1;
	}

	public BigDecimal getImportoResiduoAnno2() {
		return importoResiduoAnno2;
	}

	public void setImportoResiduoAnno2(BigDecimal importoResiduoAnno2) {
		this.importoResiduoAnno2 = importoResiduoAnno2;
	}

	public SiacTBilElem getCapitolo() {
		return capitolo;
	}

	public void setCapitolo(SiacTBilElem capitolo) {
		this.capitolo = capitolo;
	}

	public SiacDPropostaPreliminareStato getStato() {
		return stato;
	}

	public void setStato(SiacDPropostaPreliminareStato stato) {
		this.stato = stato;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDescrizioneArticolo() {
		return descrizioneArticolo;
	}

	public void setDescrizioneArticolo(String descrizioneArticolo) {
		this.descrizioneArticolo = descrizioneArticolo;
	}
	
	public String getDescrizioneCompleta() {
		return String.format("%s - %s", descrizione, descrizioneArticolo);
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}