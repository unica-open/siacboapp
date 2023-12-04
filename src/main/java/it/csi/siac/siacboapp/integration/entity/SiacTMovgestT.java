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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="siac_t_movgest_ts")
public class SiacTMovgestT extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_T_MOVGEST_TS_MOVGESTTSID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_MOVGEST_TS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_MOVGEST_TS_MOVGESTTSID_GENERATOR")
	@Column(name="movgest_ts_id")
	private Integer movgestTsId;

	private Integer livello;

	@Column(name="movgest_ts_code")
	private String movgestTsCode;

	@Column(name="movgest_ts_desc")
	private String movgestTsDesc;
	
	@ManyToOne
	@JoinColumn(name="movgest_ts_id_padre")
	private SiacTMovgestT siacTMovgestIdPadre;	
	

	private String ordine;

	@OneToMany(mappedBy="siacTMovgestT")
	private List<SiacRMovgestTsStato> siacRMovgestTsStatos;

	@ManyToOne
	@JoinColumn(name="movgest_id")
	private SiacTMovgest siacTMovgest;
	
	@ManyToOne
	@JoinColumn(name="movgest_ts_tipo_id")
	private SiacDMovgestTsTipo siacDMovgestTsTipo;

	@OneToMany(mappedBy="siacTMovgestT")
	private List<SiacRMovgestClass> classi;

	@OneToMany(mappedBy="siacTMovgestT")
	private List<SiacTMovgestTsDet> siacTMovgestTsDets;	
	
	@OneToMany(mappedBy="movimentoGestioneTS")
	private List<SiacRMovgestTsSog> soggetti;

	@Transient
	private SiacDSoggettoClasse classeSoggetto;

	@OneToMany(mappedBy="movimentoGestioneTS")
	private List<SiacRMovgestTsSogclasse> classiSoggetto;
	
	@Transient
	private SiacTSoggetto soggetto;

	@Transient
	private String cup;

	@Transient
	private Boolean flagSoggettoDurc;

	@Transient
	private Boolean flagSdf;

	//SIAC-7884
	@Transient
	private Boolean flagPrenotazione;

	@Transient
	private Boolean flagPrenotazioneLiquidabile;

	@OneToMany(mappedBy="movimentoGestioneTS")
	private List<SiacRMovgestTsAttoAmm> attiAmministrativi;
	
	@OneToMany(mappedBy="movimentoGestioneTS")
	private List<SiacRMovgestTsAttr> attributi;
	
	@Transient
	private SiacTAttoAmm attoAmministrativo;

	public SiacTMovgestT() {
	}

	public Integer getMovgestTsId() {
		return this.movgestTsId;
	}

	public void setMovgestTsId(Integer movgestTsId) {
		this.movgestTsId = movgestTsId;
	}


	public Integer getLivello() {
		return this.livello;
	}

	public void setLivello(Integer livello) {
		this.livello = livello;
	}

	public String getMovgestTsCode() {
		return this.movgestTsCode;
	}

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

	public List<SiacRMovgestTsSog> getSoggetti() {
		return soggetti;
	}

	public void setSoggetti(List<SiacRMovgestTsSog> soggetti) {
		this.soggetti = soggetti;
	}

	public List<SiacRMovgestTsSogclasse> getClassiSoggetto() {
		return classiSoggetto;
	}

	public void setClassiSoggetto(List<SiacRMovgestTsSogclasse> classiSoggetto) {
		this.classiSoggetto = classiSoggetto;
	}


	public SiacDSoggettoClasse getClasseSoggetto() {
		if (classeSoggetto != null) {
			return classeSoggetto;
		}	
		
		if (classiSoggetto == null) {
			return null;
		}
		
		for (SiacRMovgestTsSogclasse siacRMovgestTsSogclasse : classiSoggetto) {
			if (siacRMovgestTsSogclasse.isEntitaValida()) {
				return classeSoggetto = siacRMovgestTsSogclasse.getClasseSoggetto();
			}
		}
				
		return null;
	}


	public SiacTSoggetto getSoggetto() {
		if (soggetto != null) {
			return soggetto;
		}	
		
		if (soggetti == null) {
			return null;
		}
		
		for (SiacRMovgestTsSog siacRMovgestTsSog : soggetti) {
			if (siacRMovgestTsSog.isEntitaValida()) {
				return soggetto = siacRMovgestTsSog.getSoggetto();
			}
		}
				
		return null;
	}


	public SiacTAttoAmm getAttoAmministrativo() {
		if (attoAmministrativo != null) {
			return attoAmministrativo;
		}	
		
		if (attiAmministrativi == null) {
			return null;
		}
		
		for (SiacRMovgestTsAttoAmm siacRMovgestTsAttoAmm : attiAmministrativi) {
			if (siacRMovgestTsAttoAmm.isEntitaValida()) {
				return attoAmministrativo = siacRMovgestTsAttoAmm.getAttoAmministrativo();
			}
		}
				
		return null;
	}

	public List<SiacRMovgestTsAttoAmm> getAttiAmministrativi() {
		return attiAmministrativi;
	}

	public void setAttiAmministrativi(List<SiacRMovgestTsAttoAmm> attiAmministrativi) {
		this.attiAmministrativi = attiAmministrativi;
	}

	public List<SiacRMovgestTsAttr> getAttributi() {
		return attributi;
	}

	public void setAttributi(List<SiacRMovgestTsAttr> attributi) {
		this.attributi = attributi;
	}

	public String getCup() {
		if (cup != null) {
			return cup;
		}	
		
		if (attributi == null) {
			return null;
		}
		
		for (SiacRMovgestTsAttr siacRMovgestTsAttr : attributi) {
			if (siacRMovgestTsAttr.isEntitaValida() && "cup".equals(siacRMovgestTsAttr.getAttributo().getCodice())) {
				return cup = siacRMovgestTsAttr.getTesto();
			}
		}
				
		return null;
	}
	
	public Boolean getFlagSoggettoDurc() {
		if (flagSoggettoDurc != null) {
			return flagSoggettoDurc;
		}	
		
		if (attributi == null) {
			return null;
		}
		
		for (SiacRMovgestTsAttr siacRMovgestTsAttr : attributi) {
			if (siacRMovgestTsAttr.isEntitaValida() && "flagSoggettoDurc".equals(siacRMovgestTsAttr.getAttributo().getCodice())) {
				return flagSoggettoDurc = convertBooleanAttrToBoolean(siacRMovgestTsAttr.getBoolean_());
			}
		}
				
		return null;
	}

	public Boolean getFlagSdf() {
		if (flagSdf != null) {
			return flagSdf;
		}	
		
		if (attributi == null) {
			return null;
		}
		
		for (SiacRMovgestTsAttr siacRMovgestTsAttr : attributi) {
			if (siacRMovgestTsAttr.isEntitaValida() && "flagSDF".equals(siacRMovgestTsAttr.getAttributo().getCodice())) {
				return flagSdf = convertBooleanAttrToBoolean(siacRMovgestTsAttr.getBoolean_());
			}
		}
				
		return null;
	}
	
	//SIAC-7884
	public Boolean getFlagPrenotazione() {
		if (flagPrenotazione != null) {
			return flagPrenotazione;
		}	
		
		if (attributi == null) {
			return null;
		}
		
		for (SiacRMovgestTsAttr siacRMovgestTsAttr : attributi) {
			if (siacRMovgestTsAttr.isEntitaValida() && "flagPrenotazione".equals(siacRMovgestTsAttr.getAttributo().getCodice())) {
				return flagPrenotazione = convertBooleanAttrToBoolean(siacRMovgestTsAttr.getBoolean_());
			}
		}
		
		return null;
	}
	
	public Boolean getFlagPrenotazioneLiquidabile() {
		if (flagPrenotazioneLiquidabile != null) {
			return flagPrenotazioneLiquidabile;
		}	
		
		if (attributi == null) {
			return null;
		}
		
		for (SiacRMovgestTsAttr siacRMovgestTsAttr : attributi) {
			if (siacRMovgestTsAttr.isEntitaValida() && "flagPrenotazioneLiquidabile".equals(siacRMovgestTsAttr.getAttributo().getCodice())) {
				return flagPrenotazioneLiquidabile = convertBooleanAttrToBoolean(siacRMovgestTsAttr.getBoolean_());
			}
		}
				
		return null;
	}
	
	private Boolean convertBooleanAttrToBoolean(String boolean_) {
		return boolean_ == null ? null : "S".equals(boolean_) ? Boolean.TRUE : Boolean.FALSE;
	}

	public List<SiacRMovgestClass> getClassi() {
		return classi;
	}
		
}