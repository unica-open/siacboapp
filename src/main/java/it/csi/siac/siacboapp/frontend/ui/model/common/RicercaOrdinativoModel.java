package it.csi.siac.siacboapp.frontend.ui.model.common;

import java.util.List;

import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.GenericOrdinativoModel;
import it.csi.siac.siacboapp.integration.entity.SiacTClass;
import it.csi.siac.siacboapp.util.entitywrapper.ElementoPianoDeiContiWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacDEventoWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTBilElemWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
public class RicercaOrdinativoModel extends GenericOrdinativoModel {

	/** Per la serializzazione */
	private static final long serialVersionUID = 1L; 
	
	/**
	 * Model temporaneo che esiste solo durante la chiamata ajax
	 */
	
	private List<SiacTOrdinativoWrapper> elencoOrdinativi;
	private SiacTBilElemWrapper capitoloAssociato;
	private SiacTClass pdcCapitolo;
	private Integer uidPadre;
	private String tipoClassificatore;
	private List<SiacTClassWrapper> classificatori;
	private List<SiacTClassWrapper> titoli;
	private List<SiacTClassWrapper> tipologie;
	private List<SiacTClassWrapper> listaCategorieMacroAggregati;
	private List<ElementoPianoDeiContiWrapper> classificatoriWrapper;
	private List<SiacDEventoWrapper> eventi;

	
	/**
	 * @return the elencoOrdinativi
	 */
	public List<SiacTOrdinativoWrapper> getElencoOrdinativi() {
		return elencoOrdinativi;
	}

	/**
	 * @param elencoOrdinativi the elencoOrdinativi to set
	 */
	public void setElencoOrdinativi(List<SiacTOrdinativoWrapper> elencoOrdinativi) {
		this.elencoOrdinativi = elencoOrdinativi;
	}

	/**
	 * @return the capitoloAssociato
	 */
	public SiacTBilElemWrapper getCapitoloAssociato() {
		return capitoloAssociato;
	}

	/**
	 * @param capitoloAssociato the capitoloAssociato to set
	 */
	public void setCapitoloAssociato(SiacTBilElemWrapper capitoloAssociato) {
		this.capitoloAssociato = capitoloAssociato;
	}

	/**
	 * @return the pdcCapitolo
	 */
	public SiacTClass getPdcCapitolo() {
		return pdcCapitolo;
	}

	/**
	 * @param pdcCapitolo the pdcCapitolo to set
	 */
	public void setPdcCapitolo(SiacTClass pdcCapitolo) {
		this.pdcCapitolo = pdcCapitolo;
	}

	/**
	 * @return the uidPadre
	 */
	public Integer getUidPadre() {
		return uidPadre;
	}

	/**
	 * @return the tipoClassificatore
	 */
	public String getTipoClassificatore() {
		return tipoClassificatore;
	}

	/**
	 * @param tipoClassificatore the tipoClassificatore to set
	 */
	public void setTipoClassificatore(String tipoClassificatore) {
		this.tipoClassificatore = tipoClassificatore;
	}

	/**
	 * @param uidPadre the uidPadre to set
	 */
	public void setUidPadre(Integer uidPadre) {
		this.uidPadre = uidPadre;
	}

	/**
	 * @return the classificatori
	 */
	public List<SiacTClassWrapper> getClassificatori() {
		return classificatori;
	}

	/**
	 * @param classificatori the classificatori to set
	 */
	public void setClassificatori(List<SiacTClassWrapper> classificatori) {
		this.classificatori = classificatori;
	}

	/**
	 * @return the classificatoriWrapper
	 */
	public List<ElementoPianoDeiContiWrapper> getClassificatoriWrapper() {
		return classificatoriWrapper;
	}

	/**
	 * @param classificatoriWrapper the classificatoriWrapper to set
	 */
	public void setClassificatoriWrapper(List<ElementoPianoDeiContiWrapper> classificatoriWrapper) {
		this.classificatoriWrapper = classificatoriWrapper;
	}

	/**
	 * @return the titoli
	 */
	public List<SiacTClassWrapper> getTitoli() {
		return titoli;
	}

	/**
	 * @param titoli the titoli to set
	 */
	public void setTitoli(List<SiacTClassWrapper> titoli) {
		this.titoli = titoli;
	}

	/**
	 * @return the tipologia
	 */
	public List<SiacTClassWrapper> getTipologie() {
		return tipologie;
	}

	/**
	 * @param tipologia the tipologia to set
	 */
	public void setTipologie(List<SiacTClassWrapper> tipologie) {
		this.tipologie = tipologie;
	}

	/**
	 * @return the listaCategorieMacroAggregati
	 */
	public List<SiacTClassWrapper> getListaCategorieMacroAggregati() {
		return listaCategorieMacroAggregati;
	}

	/**
	 * @param listaCategorieMacroAggregati the listaCategorieMacroAggregati to set
	 */
	public void setListaCategorieMacroAggregati(List<SiacTClassWrapper> listaCategorieMacroAggregati) {
		this.listaCategorieMacroAggregati = listaCategorieMacroAggregati;
	}

	/**
	 * @return the evento
	 */
	public List<SiacDEventoWrapper> getEventi() {
		return eventi;
	}

	/**
	 * @param evento the evento to set
	 */
	public void setEventi(List<SiacDEventoWrapper> eventi) {
		this.eventi= eventi;
	}

}
