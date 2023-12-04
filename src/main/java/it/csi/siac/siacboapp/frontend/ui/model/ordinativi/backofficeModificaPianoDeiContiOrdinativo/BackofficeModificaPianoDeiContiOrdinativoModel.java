/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi.backofficeModificaPianoDeiContiOrdinativo;

import org.apache.commons.lang3.StringUtils;

import it.csi.siac.siacbilser.model.ElementoPianoDeiConti;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.GenericOrdinativoModel;
import it.csi.siac.siaccorser.model.Bilancio;
import it.csi.siac.siacfinser.model.ordinativo.Ordinativo;

/**
 * Classe di modello per il Backoffice delle modifiche sull'ordinativo 
 * per il piando dei conti.
 * 
 * @author Alessandro Todesco
 */
public class BackofficeModificaPianoDeiContiOrdinativoModel extends GenericOrdinativoModel {

	/** Per la serializzazione */
	private static final long serialVersionUID = -3624463588486028949L;
	
	//il nome dell'azione che viene chiamata
	private final String AZIONE = "OP-BKOF013-";
	
	private String numeroRemedy, tipoEventoCodice, eventoCodice, collegamentoTipoEventoCodice = null;
	private Integer idBilancio, idMacroAggregato, idTitoloCapitolo, idCategoria, idTipologiaCapitolo, idEnte = null; 
	private ElementoPianoDeiConti pianoDeiConti;
	private Boolean aggiornaGenerale, aggiornaGSAGenerale, inserisciGenerale, inserisciGSAGenerale = Boolean.FALSE;
	private Boolean modificaAccertamento = Boolean.FALSE;
	
	/** Costruttore vuoto di default */
	public BackofficeModificaPianoDeiContiOrdinativoModel() {
		setTitolo("Ordinativi - Backoffice modifica piano dei conti");
	}
	
	/**
	 * @return the idBilancio
	 */
	public Integer getIdBilancio() {
		return idBilancio;
	}

	/**
	 * @param idBilancio the idBilancio to set
	 */
	public void setIdBilancio(Integer idBilancio) {
		this.idBilancio = idBilancio;
	}

	/**
	 * @return the numeroRemedy
	 */
	public String getNumeroRemedy() {
		return numeroRemedy;
	}

	/**
	 * @param numeroRemedy the numeroRemedy to set
	 */
	public void setNumeroRemedy(String numeroRemedy) {
		this.numeroRemedy = numeroRemedy;
	}

	/**
	 * @return the idMacroAggregato
	 */
	public Integer getIdMacroAggregato() {
		return idMacroAggregato;
	}

	/**
	 * @param idMacroAggregato the idMacroAggregato to set
	 */
	public void setIdMacroAggregato(Integer idMacroAggregato) {
		this.idMacroAggregato = idMacroAggregato;
	}

	/**
	 * @return the idTitoloCapitolo
	 */
	public Integer getIdTitoloCapitolo() {
		return idTitoloCapitolo;
	}

	/**
	 * @param idTitoloCapitolo the idTitoloCapitolo to set
	 */
	public void setIdTitoloCapitolo(Integer idTitoloCapitolo) {
		this.idTitoloCapitolo = idTitoloCapitolo;
	}

	/**
	 * @return the idCategoria
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return the idTipologiaCapitolo
	 */
	public Integer getIdTipologiaCapitolo() {
		return idTipologiaCapitolo;
	}

	/**
	 * @param idTipologiaCapitolo the idTipologiaCapitolo to set
	 */
	public void setIdTipologiaCapitolo(Integer idTipologiaCapitolo) {
		this.idTipologiaCapitolo = idTipologiaCapitolo;
	}

	/**
	 * @return the pianoDeiConti
	 */
	public ElementoPianoDeiConti getPianoDeiConti() {
		return pianoDeiConti;
	}

	/**
	 * @param pianoDeiConti the pianoDeiConti to set
	 */
	public void setPianoDeiConti(ElementoPianoDeiConti pianoDeiConti) {
		this.pianoDeiConti = pianoDeiConti;
	}

	/**
	 * @return the aggiornaGenerale
	 */
	public Boolean getAggiornaGenerale() {
		return aggiornaGenerale;
	}

	/**
	 * @param aggiornaGenerale the aggiornaGenerale to set
	 */
	public void setAggiornaGenerale(Boolean aggiornaGenerale) {
		this.aggiornaGenerale = aggiornaGenerale;
	}

	/**
	 * @return the inserisciGenerale
	 */
	public Boolean getInserisciGenerale() {
		return inserisciGenerale;
	}

	/**
	 * @param inserisciGenerale the inserisciGenerale to set
	 */
	public void setInserisciGenerale(Boolean inserisciGenerale) {
		this.inserisciGenerale = inserisciGenerale;
	}

	/**
	 * @return the aggiornaGSAGenerale
	 */
	public Boolean getAggiornaGSAGenerale() {
		return aggiornaGSAGenerale;
	}

	/**
	 * @param aggiornaGSAGenerale the aggiornaGSAGenerale to set
	 */
	public void setAggiornaGSAGenerale(Boolean aggiornaGSAGenerale) {
		this.aggiornaGSAGenerale = aggiornaGSAGenerale;
	}

	/**
	 * @return the inserisciGSAGenerale
	 */
	public Boolean getInserisciGSAGenerale() {
		return inserisciGSAGenerale;
	}

	/**
	 * @param inserisciGSAGenerale the inserisciGSAGenerale to set
	 */
	public void setInserisciGSAGenerale(Boolean inserisciGSAGenerale) {
		this.inserisciGSAGenerale = inserisciGSAGenerale;
	}

	/**
	 * @return the modificaAccertamento
	 */
	public Boolean getModificaAccertamento() {
		return modificaAccertamento;
	}

	/**
	 * @param modificaAccertamento the modificaAccertamento to set
	 */
	public void setModificaAccertamento(Boolean modificaAccertamento) {
		this.modificaAccertamento = modificaAccertamento;
	}

	/**
	 * @return the tipoEventoCodice
	 */
	public String getTipoEventoCodice() {
		return tipoEventoCodice;
	}

	/**
	 * @param tipoEventoCodice the tipoEventoCodice to set
	 */
	public void setTipoEventoCodice(String tipoEventoCodice) {
		this.tipoEventoCodice = tipoEventoCodice;
	}

	/**
	 * @return the eventoCodice
	 */
	public String getEventoCodice() {
		return eventoCodice;
	}

	/**
	 * @param eventoCodice the eventoCodice to set
	 */
	public void setEventoCodice(String eventoCodice) {
		this.eventoCodice = eventoCodice;
	}

	/**
	 * @return the collegamentoTipoEventoCodice
	 */
	public String getCollegamentoTipoEventoCodice() {
		return collegamentoTipoEventoCodice;
	}

	/**
	 * @param collegamentoTipoEventoCodice the collegamentoTipoEventoCodice to set
	 */
	public void setCollegamentoTipoEventoCodice(String collegamentoTipoEventoCodice) {
		this.collegamentoTipoEventoCodice = collegamentoTipoEventoCodice;
	}

	/**
	 * @return the azione
	 */
	public String getAzione() {
		return AZIONE;
	}

	/**
	 * @return the idEnte
	 */
	public Integer getIdEnte() {
		return idEnte;
	}

	/**
	 * @param idEnte the idEnte to set
	 */
	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public ModificaPianoDeiContiOrdinativo creaRequestModificaPianoDeiContiOrdinativoBackoffice() {
		ModificaPianoDeiContiOrdinativo mpdco = new ModificaPianoDeiContiOrdinativo();
		
		mpdco.setAnnoBilancio(getCriteri().getAnno());
		mpdco.setNumeroRemedy(StringUtils.isBlank(getNumeroRemedy()) ? 
				"BackofficeModificaPianoDeiContiOrdinativo" : getNumeroRemedy().trim());
		mpdco.setTipoOrdinativo(getCriteri().getCodiceTipo());
		mpdco.setIdEnte(getIdEnte());
		
		// Bilancio
		Bilancio b = new Bilancio();
		b.setAnno(getCriteri().getAnno());
		mpdco.setBilancio(b);
		
		Ordinativo o = new Ordinativo();
		o.setNumero(getCriteri().getNumeroDa());
		o.setAnno(getCriteri().getAnno());
		o.setCodTipoFinanziamento(getCriteri().getCodiceTipo());
		mpdco.setOrdinativo(o);
		
		mpdco.setIdTitolo(getIdTitoloCapitolo());
		mpdco.setIdTipologia(getIdTipologiaCapitolo());
		mpdco.setIdMacroaggregato(getIdMacroAggregato());
		mpdco.setPianoDeiConti(getPianoDeiConti());
		
		// Boolean
		mpdco.setAggiornaGenerale(getAggiornaGenerale());
		mpdco.setAggiornaGSAGenerale(getAggiornaGSAGenerale());
		mpdco.setInserisciGenerale(getInserisciGenerale());
		mpdco.setInserisciGSAGenerale(getInserisciGSAGenerale());
		mpdco.setModificaAccertamento(getModificaAccertamento() != null 
				&& Boolean.TRUE.equals(getModificaAccertamento()) ? Boolean.TRUE : Boolean.FALSE);
		
		// Evento
		mpdco.setTipoEventoCodice(getTipoEventoCodice() == null ? "" : getTipoEventoCodice());
//		mpdco.setCollegamentoTipoEventoCodice(getCollegamentoTipoEventoCodice());
		mpdco.setEventoCodice(getEventoCodice() == null ? "" : getEventoCodice());
		
		return mpdco;
	}
	
}
