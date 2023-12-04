/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the siac_t_movgest database table.
 * 
 */
@Entity
@Table(name="siac_t_movgest")
@NamedQuery(name="SiacTMovgest.findAll", query="SELECT s FROM SiacTMovgest s")
public class SiacTMovgest extends SiacTEnteBase {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The movgest id. */
	@Id
	@SequenceGenerator(name="SIAC_T_MOVGEST_MOVGESTID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_MOVGEST_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_MOVGEST_MOVGESTID_GENERATOR")
	@Column(name="movgest_id")
	private Integer movgestId;

	/** The movgest anno. */
	@Column(name="movgest_anno")
	private Integer movgestAnno;

	/** The movgest desc. */
	@Column(name="movgest_desc")
	private String movgestDesc;

	/** The movgest numero. */
	@Column(name="movgest_numero")
	private BigDecimal movgestNumero;

	//bi-directional many-to-one association to SiacRMovgestBilElem
	/** The siac r movgest bil elems. */
	@OneToMany(mappedBy="siacTMovgest")
	private List<SiacRMovgestBilElem> siacRMovgestBilElems;

	//bi-directional many-to-one association to SiacDMovgestTipo
	/** The siac d movgest tipo. */
	@ManyToOne
	@JoinColumn(name="movgest_tipo_id")
	private SiacDMovgestTipo siacDMovgestTipo;

	//bi-directional many-to-one association to SiacTBil
	/** The siac t bil. */
	@ManyToOne
	@JoinColumn(name="bil_id")
	private SiacTBil siacTBil;

	//bi-directional many-to-one association to SiacTMovgestT
	/** The siac t movgest ts. */
	@OneToMany(mappedBy="siacTMovgest")
	private List<SiacTMovgestT> siacTMovgestTs;

	@Column(name = "parere_finanziario")
	private Boolean parereFinanziario;

	@Transient
	private SiacTMovgestT testata;
	
	@Column(name = "parere_finanziario_login_operazione")
	private String parereFinanziarioLoginOperazione;
	
	@Column(name = "parere_finanziario_data_modifica")
	private String parereFinanziarioDataModifica;
	
	


	/**
	 * Instantiates a new siac t movgest.
	 */
	public SiacTMovgest() {
	}

	/**
	 * Gets the movgest id.
	 *
	 * @return the movgest id
	 */
	public Integer getMovgestId() {
		return this.movgestId;
	}

	/**
	 * Sets the movgest id.
	 *
	 * @param movgestId the new movgest id
	 */
	public void setMovgestId(Integer movgestId) {
		this.movgestId = movgestId;
	}

	/**
	 * Gets the movgest anno.
	 *
	 * @return the movgest anno
	 */
	public Integer getMovgestAnno() {
		return this.movgestAnno;
	}

	/**
	 * Sets the movgest anno.
	 *
	 * @param movgestAnno the new movgest anno
	 */
	public void setMovgestAnno(Integer movgestAnno) {
		this.movgestAnno = movgestAnno;
	}

	/**
	 * Gets the movgest desc.
	 *
	 * @return the movgest desc
	 */
	public String getMovgestDesc() {
		return this.movgestDesc;
	}

	/**
	 * Sets the movgest desc.
	 *
	 * @param movgestDesc the new movgest desc
	 */
	public void setMovgestDesc(String movgestDesc) {
		this.movgestDesc = movgestDesc;
	}

	/**
	 * Gets the movgest numero.
	 *
	 * @return the movgest numero
	 */
	public BigDecimal getMovgestNumero() {
		return this.movgestNumero;
	}

	/**
	 * Sets the movgest numero.
	 *
	 * @param movgestNumero the new movgest numero
	 */
	public void setMovgestNumero(BigDecimal movgestNumero) {
		this.movgestNumero = movgestNumero;
	}


	/**
	 * Gets the siac r movgest bil elems.
	 *
	 * @return the siac r movgest bil elems
	 */
	public List<SiacRMovgestBilElem> getSiacRMovgestBilElems() {
		return this.siacRMovgestBilElems;
	}

	/**
	 * Sets the siac r movgest bil elems.
	 *
	 * @param siacRMovgestBilElems the new siac r movgest bil elems
	 */
	public void setSiacRMovgestBilElems(List<SiacRMovgestBilElem> siacRMovgestBilElems) {
		this.siacRMovgestBilElems = siacRMovgestBilElems;
	}

	/**
	 * Adds the siac r movgest bil elem.
	 *
	 * @param siacRMovgestBilElem the siac r movgest bil elem
	 * @return the siac r movgest bil elem
	 */
	public SiacRMovgestBilElem addSiacRMovgestBilElem(SiacRMovgestBilElem siacRMovgestBilElem) {
		getSiacRMovgestBilElems().add(siacRMovgestBilElem);
		siacRMovgestBilElem.setSiacTMovgest(this);

		return siacRMovgestBilElem;
	}

	/**
	 * Removes the siac r movgest bil elem.
	 *
	 * @param siacRMovgestBilElem the siac r movgest bil elem
	 * @return the siac r movgest bil elem
	 */
	public SiacRMovgestBilElem removeSiacRMovgestBilElem(SiacRMovgestBilElem siacRMovgestBilElem) {
		getSiacRMovgestBilElems().remove(siacRMovgestBilElem);
		siacRMovgestBilElem.setSiacTMovgest(null);

		return siacRMovgestBilElem;
	}

	/**
	 * Gets the siac d movgest tipo.
	 *
	 * @return the siac d movgest tipo
	 */
	public SiacDMovgestTipo getSiacDMovgestTipo() {
		return this.siacDMovgestTipo;
	}

	/**
	 * Sets the siac d movgest tipo.
	 *
	 * @param siacDMovgestTipo the new siac d movgest tipo
	 */
	public void setSiacDMovgestTipo(SiacDMovgestTipo siacDMovgestTipo) {
		this.siacDMovgestTipo = siacDMovgestTipo;
	}

	/**
	 * Gets the siac t bil.
	 *
	 * @return the siac t bil
	 */
	public SiacTBil getSiacTBil() {
		return this.siacTBil;
	}

	/**
	 * Sets the siac t bil.
	 *
	 * @param siacTBil the new siac t bil
	 */
	public void setSiacTBil(SiacTBil siacTBil) {
		this.siacTBil = siacTBil;
	}

	/**
	 * Gets the siac t movgest ts.
	 *
	 * @return the siac t movgest ts
	 */
	public List<SiacTMovgestT> getSiacTMovgestTs() {
		return this.siacTMovgestTs;
	}

	/**
	 * Sets the siac t movgest ts.
	 *
	 * @param siacTMovgestTs the new siac t movgest ts
	 */
	public void setSiacTMovgestTs(List<SiacTMovgestT> siacTMovgestTs) {
		this.siacTMovgestTs = siacTMovgestTs;
	}

	/**
	 * Adds the siac t movgest t.
	 *
	 * @param siacTMovgestT the siac t movgest t
	 * @return the siac t movgest t
	 */
	public SiacTMovgestT addSiacTMovgestT(SiacTMovgestT siacTMovgestT) {
		getSiacTMovgestTs().add(siacTMovgestT);
		siacTMovgestT.setSiacTMovgest(this);

		return siacTMovgestT;
	}

	/**
	 * Removes the siac t movgest t.
	 *
	 * @param siacTMovgestT the siac t movgest t
	 * @return the siac t movgest t
	 */
	public SiacTMovgestT removeSiacTMovgestT(SiacTMovgestT siacTMovgestT) {
		getSiacTMovgestTs().remove(siacTMovgestT);
		siacTMovgestT.setSiacTMovgest(null);

		return siacTMovgestT;
	}

	/* (non-Javadoc)
	 * @see it.csi.siac.siacbilser.integration.entity.SiacTBase#getUid()
	 */
	@Override
	public Integer getUid() {
		return movgestId;
	}

	/* (non-Javadoc)
	 * @see it.csi.siac.siacbilser.integration.entity.SiacTBase#setUid(java.lang.Integer)
	 */
	@Override
	public void setUid(Integer uid) {
		movgestId = uid;
	}

	public Integer getAnno() {
		return getMovgestAnno();
	}
	
	public BigDecimal getNumero() {
		return getMovgestNumero();
	}
	
	public String getDescrizione() {
		return getMovgestDesc();
	}

	public SiacTMovgestT getTestata() {
		if (testata != null) {
			return testata;
		}	
		
		if (siacTMovgestTs == null) {
			return null;
		}
		
		for (SiacTMovgestT siacTMovgestT : siacTMovgestTs) {
			if ("T".equals(siacTMovgestT.getSiacDMovgestTsTipo().getMovgestTsTipoCode()) && siacTMovgestT.isEntitaValida()) {
				return testata = siacTMovgestT;
			}
		}
				
		return null;
	}
	
	public String getCup() {
		return getTestata() == null ? null : getTestata().getCup();
	}
	
	public Boolean getFlagSoggettoDurc() {
		return getTestata() == null ? null : getTestata().getFlagSoggettoDurc();
	}

	public Boolean getFlagSdf() {
		return getTestata() == null ? null : getTestata().getFlagSdf();
	}

	//SIAC-7884
	public Boolean getFlagPrenotazione() {
		return getTestata() == null ? null : getTestata().getFlagPrenotazione();
	}

	public Boolean getFlagPrenotazioneLiquidabile() {
		return getTestata() == null ? null : getTestata().getFlagPrenotazioneLiquidabile();
	}

	public SiacTSoggetto getSoggetto() {
		return getTestata() == null ? null : getTestata().getSoggetto();
	}
	
	public SiacDSoggettoClasse getClasseSoggetto() {
		return getTestata() == null ? null : getTestata().getClasseSoggetto();
	}	
	
	public SiacTAttoAmm getAttoAmministrativo() {
		return getTestata() == null ? null : getTestata().getAttoAmministrativo();
	}

	public Boolean getParereFinanziario() {
		return parereFinanziario;
	}

	public void setParereFinanziario(Boolean parereFinanziario) {
		this.parereFinanziario = parereFinanziario;
	}

	public String getParereFinanziarioLoginOperazione() {
		return parereFinanziarioLoginOperazione;
	}

	public void setParereFinanziarioLoginOperazione(String parereFinanziarioLoginOperazione) {
		this.parereFinanziarioLoginOperazione = parereFinanziarioLoginOperazione;
	}

	public String getParereFinanziarioDataModifica() {
		return parereFinanziarioDataModifica;
	}

	public void setParereFinanziarioDataModifica(String parereFinanziarioDataModifica) {
		this.parereFinanziarioDataModifica = parereFinanziarioDataModifica;
	}
}