/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi.backofficeModificaPianoDeiContiOrdinativo;

import javax.xml.bind.annotation.XmlType;

import it.csi.siac.siacbilser.model.ElementoPianoDeiConti;
import it.csi.siac.siaccorser.model.Bilancio;
import it.csi.siac.siaccorser.model.ServiceRequest;
import it.csi.siac.siacfinser.frontend.webservice.FINSvcDictionary;
import it.csi.siac.siacfinser.model.ordinativo.Ordinativo;

@XmlType(namespace = FINSvcDictionary.NAMESPACE)
public class ModificaPianoDeiContiOrdinativo extends ServiceRequest {

	private Integer idEnte;
	private String numeroRemedy;
	private String tipoOrdinativo;
	private Ordinativo ordinativo;
	private ElementoPianoDeiConti pianoDeiConti;
	private Bilancio bilancio;
	private Integer idTitolo;
	private Integer idTipologia;
	private Integer idCategoria;
	private Integer idMacroaggregato;
	private String eventoCodice;
	private String tipoEventoCodice;
	private String collegamentoTipoEventoCodice;
	private Boolean modificaAccertamento, aggiornaGenerale, aggiornaGSAGenerale, inserisciGenerale, inserisciGSAGenerale = Boolean.FALSE;

	public ModificaPianoDeiContiOrdinativo() {}

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
	 * @return the tipoOrdinativo
	 */
	public String getTipoOrdinativo() {
		return tipoOrdinativo;
	}

	/**
	 * @param tipoOrdinativo the tipoOrdinativo to set
	 */
	public void setTipoOrdinativo(String tipoOrdinativo) {
		this.tipoOrdinativo = tipoOrdinativo;
	}
	
	/**
	 * @return the ordinativo
	 */
	public Ordinativo getOrdinativo() {
		return ordinativo;
	}

	/**
	 * @param ordinativo the ordinativo to set
	 */
	public void setOrdinativo(Ordinativo ordinativo) {
		this.ordinativo = ordinativo;
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
	 * @return the bilancio
	 */
	public Bilancio getBilancio() {
		return bilancio;
	}

	/**
	 * @param bilancio the bilancio to set
	 */
	public void setBilancio(Bilancio bilancio) {
		this.bilancio = bilancio;
	}

	/**
	 * @return the idTitolo
	 */
	public Integer getIdTitolo() {
		return idTitolo;
	}

	/**
	 * @param idTitolo the idTitolo to set
	 */
	public void setIdTitolo(Integer idTitolo) {
		this.idTitolo = idTitolo;
	}

	/**
	 * @return the idTipologia
	 */
	public Integer getIdTipologia() {
		return idTipologia;
	}

	/**
	 * @param idTipologia the idTipologia to set
	 */
	public void setIdTipologia(Integer idTipologia) {
		this.idTipologia = idTipologia;
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
	 * @return the idMacroaggregato
	 */
	public Integer getIdMacroaggregato() {
		return idMacroaggregato;
	}

	/**
	 * @param idMacroaggregato the idMacroaggregato to set
	 */
	public void setIdMacroaggregato(Integer idMacroaggregato) {
		this.idMacroaggregato = idMacroaggregato;
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

}