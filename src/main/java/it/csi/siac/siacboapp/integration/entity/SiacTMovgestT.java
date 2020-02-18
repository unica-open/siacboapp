/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.List;

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


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the siac_t_movgest_ts database table.
 * 
 */
@Entity
@Table(name="siac_t_movgest_ts")
@NamedQuery(name="SiacTMovgestT.findAll", query="SELECT s FROM SiacTMovgestT s")
public class SiacTMovgestT extends SiacTEnteBase {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The movgest ts id. */
	@Id
	@SequenceGenerator(name="SIAC_T_MOVGEST_TS_MOVGESTTSID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_MOVGEST_TS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_MOVGEST_TS_MOVGESTTSID_GENERATOR")
	@Column(name="movgest_ts_id")
	private Integer movgestTsId;

	/** The livello. */
	private Integer livello;

	/** The movgest ts code. */
	@Column(name="movgest_ts_code")
	private String movgestTsCode;

	/** The movgest ts desc. */
	@Column(name="movgest_ts_desc")
	private String movgestTsDesc;
	
	/** The siac t movgest id padre. */
	@ManyToOne
	@JoinColumn(name="movgest_ts_id_padre")
	private SiacTMovgestT siacTMovgestIdPadre;	
	

	/** The ordine. */
	private String ordine;

	//bi-directional many-to-one association to SiacRMovgestTsStato
	@OneToMany(mappedBy="siacTMovgestT")
	private List<SiacRMovgestTsStato> siacRMovgestTsStatos;

	//bi-directional many-to-one association to SiacTMovgest
	/** The siac t movgest. */
	@ManyToOne
	@JoinColumn(name="movgest_id")
	private SiacTMovgest siacTMovgest;
	
	//bi-directional many-to-one association to SiacDMovgestTsTipo
	/** The siac d movgest ts tipo. */
	@ManyToOne
	@JoinColumn(name="movgest_ts_tipo_id")
	private SiacDMovgestTsTipo siacDMovgestTsTipo;

	//bi-directional many-to-one association to SiacTMovgestTsDet
	/** The siac t movgest ts dets. */
	@OneToMany(mappedBy="siacTMovgestT")
	private List<SiacTMovgestTsDet> siacTMovgestTsDets;
	
	/**
	 * Instantiates a new siac t movgest t.
	 */
	public SiacTMovgestT() {
	}

	/**
	 * Gets the movgest ts id.
	 *
	 * @return the movgest ts id
	 */
	public Integer getMovgestTsId() {
		return this.movgestTsId;
	}

	/**
	 * Sets the movgest ts id.
	 *
	 * @param movgestTsId the new movgest ts id
	 */
	public void setMovgestTsId(Integer movgestTsId) {
		this.movgestTsId = movgestTsId;
	}

	/**
	 * Gets the livello.
	 *
	 * @return the livello
	 */
	public Integer getLivello() {
		return this.livello;
	}

	/**
	 * Sets the livello.
	 *
	 * @param livello the new livello
	 */
	public void setLivello(Integer livello) {
		this.livello = livello;
	}

	/**
	 * Gets the movgest ts code.
	 *
	 * @return the movgest ts code
	 */
	public String getMovgestTsCode() {
		return this.movgestTsCode;
	}

	/**
	 * Sets the movgest ts code.
	 *
	 * @param movgestTsCode the new movgest ts code
	 */
	public void setMovgestTsCode(String movgestTsCode) {
		this.movgestTsCode = movgestTsCode;
	}

	/**
	 * Gets the movgest ts desc.
	 *
	 * @return the movgest ts desc
	 */
	public String getMovgestTsDesc() {
		return this.movgestTsDesc;
	}

	/**
	 * Sets the movgest ts desc.
	 *
	 * @param movgestTsDesc the new movgest ts desc
	 */
	public void setMovgestTsDesc(String movgestTsDesc) {
		this.movgestTsDesc = movgestTsDesc;
	}

	/**
	 * Gets the ordine.
	 *
	 * @return the ordine
	 */
	public String getOrdine() {
		return this.ordine;
	}

	/**
	 * Gets the siac t movgest id padre.
	 *
	 * @return the siacTMovgestIdPadre
	 */
	public SiacTMovgestT getSiacTMovgestIdPadre() {
		return siacTMovgestIdPadre;
	}

	/**
	 * Sets the siac t movgest id padre.
	 *
	 * @param siacTMovgestIdPadre the siacTMovgestIdPadre to set
	 */
	public void setSiacTMovgestIdPadre(SiacTMovgestT siacTMovgestIdPadre) {
		this.siacTMovgestIdPadre = siacTMovgestIdPadre;
	}

	/**
	 * Sets the ordine.
	 *
	 * @param ordine the new ordine
	 */
	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public List<SiacRMovgestTsStato> getSiacRMovgestTsStatos() {
		return this.siacRMovgestTsStatos;
	}

	public void setSiacRMovgestTsStatos(List<SiacRMovgestTsStato> siacRMovgestTsStatos) {
		this.siacRMovgestTsStatos = siacRMovgestTsStatos;
	}

	public SiacRMovgestTsStato addSiacRMovgestTsStato(SiacRMovgestTsStato siacRMovgestTsStato) {
		getSiacRMovgestTsStatos().add(siacRMovgestTsStato);
		siacRMovgestTsStato.setSiacTMovgestT(this);

		return siacRMovgestTsStato;
	}

	public SiacRMovgestTsStato removeSiacRMovgestTsStato(SiacRMovgestTsStato siacRMovgestTsStato) {
		getSiacRMovgestTsStatos().remove(siacRMovgestTsStato);
		siacRMovgestTsStato.setSiacTMovgestT(null);

		return siacRMovgestTsStato;
	}

	/**
	 * Gets the siac t movgest.
	 *
	 * @return the siac t movgest
	 */
	public SiacTMovgest getSiacTMovgest() {
		return this.siacTMovgest;
	}

	/**
	 * Sets the siac t movgest.
	 *
	 * @param siacTMovgest the new siac t movgest
	 */
	public void setSiacTMovgest(SiacTMovgest siacTMovgest) {
		this.siacTMovgest = siacTMovgest;
	}

	/**
	 * @return the siacDMovgestTsTipo
	 */
	public SiacDMovgestTsTipo getSiacDMovgestTsTipo() {
		return this.siacDMovgestTsTipo;
	}

	/**
	 * @param siacDMovgestTsTipo the siacDMovgestTsTipo to set
	 */
	public void setSiacDMovgestTsTipo(SiacDMovgestTsTipo siacDMovgestTsTipo) {
		this.siacDMovgestTsTipo = siacDMovgestTsTipo;
	}

	/**
	 * Gets the siac t movgest ts dets.
	 *
	 * @return the siac t movgest ts dets
	 */
	public List<SiacTMovgestTsDet> getSiacTMovgestTsDets() {
		return this.siacTMovgestTsDets;
	}

	/**
	 * Sets the siac t movgest ts dets.
	 *
	 * @param siacTMovgestTsDets the new siac t movgest ts dets
	 */
	public void setSiacTMovgestTsDets(List<SiacTMovgestTsDet> siacTMovgestTsDets) {
		this.siacTMovgestTsDets = siacTMovgestTsDets;
	}

	/**
	 * Adds the siac t movgest ts det.
	 *
	 * @param siacTMovgestTsDet the siac t movgest ts det
	 * @return the siac t movgest ts det
	 */
	public SiacTMovgestTsDet addSiacTMovgestTsDet(SiacTMovgestTsDet siacTMovgestTsDet) {
		getSiacTMovgestTsDets().add(siacTMovgestTsDet);
		siacTMovgestTsDet.setSiacTMovgestT(this);

		return siacTMovgestTsDet;
	}

	/**
	 * Removes the siac t movgest ts det.
	 *
	 * @param siacTMovgestTsDet the siac t movgest ts det
	 * @return the siac t movgest ts det
	 */
	public SiacTMovgestTsDet removeSiacTMovgestTsDet(SiacTMovgestTsDet siacTMovgestTsDet) {
		getSiacTMovgestTsDets().remove(siacTMovgestTsDet);
		siacTMovgestTsDet.setSiacTMovgestT(null);

		return siacTMovgestTsDet;
	}

	@Override
	public Integer getUid() {
		return movgestTsId;
	}

	@Override
	public void setUid(Integer uid) {
		movgestTsId = uid;
	}

}