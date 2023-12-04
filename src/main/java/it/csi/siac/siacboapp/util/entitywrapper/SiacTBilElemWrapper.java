/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import it.csi.siac.siacboapp.integration.entity.SiacDBilElemTipo;

public class SiacTBilElemWrapper extends BaseEntityWrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codice;
	private String codiceArticolo;
	private String codiceUeb;
	private String descrizione;
	private String descrizioneArticolo;
	private Integer uidPadre;
	private String ordine;
	private SiacDBilElemTipo tipo;
	private SiacTBilWrapper bilancio;

	
	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}
	
	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	/**
	 * @return the codiceArticolo
	 */
	public String getCodiceArticolo() {
		return codiceArticolo;
	}
	/**
	 * @param codiceArticolo the codiceArticolo to set
	 */
	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}
	
	/**
	 * @return the codiceUeb
	 */
	public String getCodiceUeb() {
		return codiceUeb;
	}
	
	/**
	 * @param codiceUeb the codiceUeb to set
	 */
	public void setCodiceUeb(String codiceUeb) {
		this.codiceUeb = codiceUeb;
	}
	
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * @return the descrizioneArticolo
	 */
	public String getDescrizioneArticolo() {
		return descrizioneArticolo;
	}
	
	/**
	 * @param descrizioneArticolo the descrizioneArticolo to set
	 */
	public void setDescrizioneArticolo(String descrizioneArticolo) {
		this.descrizioneArticolo = descrizioneArticolo;
	}
	
	/**
	 * @return the uidPadre
	 */
	public Integer getUidPadre() {
		return uidPadre;
	}
	
	/**
	 * @param uidPadre the uidPadre to set
	 */
	public void setUidPadre(Integer uidPadre) {
		this.uidPadre = uidPadre;
	}
	
	/**
	 * @return the ordine
	 */
	public String getOrdine() {
		return ordine;
	}
	
	/**
	 * @param ordine the ordine to set
	 */
	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}
	
	/**
	 * @return the tipo
	 */
	public SiacDBilElemTipo getTipo() {
		return tipo;
	}
	
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(SiacDBilElemTipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the bilancio
	 */
	public SiacTBilWrapper getBilancio() {
		return bilancio;
	}

	/**
	 * @param bilancio the bilancio to set
	 */
	public void setBilancio(SiacTBilWrapper bilancio) {
		this.bilancio = bilancio;
	}

}
