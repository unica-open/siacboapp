/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDClassTipo;
import it.csi.siac.siacboapp.integration.entity.SiacTClassFamTree;

public class SiacTClassWrapper extends BaseEntityWrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codice;
	private String descrizione;
	private SiacDClassTipo tipo;
	private SiacTClassWrapper parent;
	private List<SiacTClassWrapper> children;
	private SiacTClassFamTree famigliaClassificatori;
	//SIAC-8485
	private String descrizioneCompleta;
	
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
	 * @return the tipo
	 */
	public SiacDClassTipo getTipo() {
		return tipo;
	}
	
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(SiacDClassTipo tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @return the parent
	 */
	public SiacTClassWrapper getParent() {
		return parent;
	}
	
	/**
	 * @param parent the parent to set
	 */
	public void setParent(SiacTClassWrapper parent) {
		this.parent = parent;
	}
	/**
	 * @return the children
	 */
	public List<SiacTClassWrapper> getChildren() {
		return children;
	}
	
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<SiacTClassWrapper> children) {
		this.children = children;
	}
	
	/**
	 * @return the famigliaClassificatori
	 */
	public SiacTClassFamTree getFamigliaClassificatori() {
		return famigliaClassificatori;
	}
	
	/**
	 * @param famigliaClassificatori the famigliaClassificatori to set
	 */
	public void setFamigliaClassificatori(SiacTClassFamTree famigliaClassificatori) {
		this.famigliaClassificatori = famigliaClassificatori;
	}

	/**
	 * @return the descrizioneCompleta
	 */
	public String getDescrizioneCompleta() {
		return descrizioneCompleta;
	}

	/**
	 * @param descrizioneCompleta the descrizioneCompleta to set
	 */
	public void setDescrizioneCompleta(String descrizioneCompleta) {
		this.descrizioneCompleta = descrizioneCompleta;
	}
	
}
