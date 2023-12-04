/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.csi.siac.siacboapp.frontend.ui.util.comparator.SiacTClassWrapperComparator;
import it.csi.siac.siacboapp.integration.entity.SiacDClassTipo;
import it.csi.siac.siaccorser.model.TipoClassificatore;

public class ElementoPianoDeiContiWrapper extends BaseEntityWrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer uidParent;
	private String codice;
	private String descrizioneCompleta;
	private TipoClassificatore tipoClassificatore;
	private List<ElementoPianoDeiContiWrapper> children;

	public ElementoPianoDeiContiWrapper(SiacTClassWrapper classificatore) {
		this.setUid(classificatore.getUid());
		this.uidParent = classificatore.getParent().getUid();
		this.codice = classificatore.getCodice();
		this.descrizioneCompleta = String.format("%s - %s", classificatore.getCodice(), classificatore.getDescrizione()) ;
		this.tipoClassificatore = getTipoFromTipoWrapper(classificatore.getTipo());
		this.children = wrapChildren(classificatore.getChildren());
	}

	private TipoClassificatore getTipoFromTipoWrapper(SiacDClassTipo tipoWrapper) {
		TipoClassificatore tipoClass = new TipoClassificatore(tipoWrapper.getCodice(), tipoWrapper.getDescrizione());
		tipoClass.setUid(tipoWrapper.getUid());
		return tipoClass;
	}

	private List<ElementoPianoDeiContiWrapper> wrapChildren(List<SiacTClassWrapper> children) {
		List<ElementoPianoDeiContiWrapper> list = new ArrayList<ElementoPianoDeiContiWrapper>();

		Collections.sort(children, SiacTClassWrapperComparator.INSTANCE);

		for (SiacTClassWrapper classificatore : children) {
			list.add(new ElementoPianoDeiContiWrapper(classificatore));
		}
			
		return list;
	}
	

	/**
	 * @return the uidParent
	 */
	public Integer getUidParent() {
		return uidParent;
	}

	/**
	 * @param uidParent the uidParent to set
	 */
	public void setUidParent(Integer uidParent) {
		this.uidParent = uidParent;
	}

	/**
	 * @return the descrizioneCompleta
	 */
	public String getDescrizioneCompleta() {
		return descrizioneCompleta;
	}

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
	 * @param descrizioneCompleta the descrizioneCompleta to set
	 */
	public void setDescrizioneCompleta(String descrizioneCompleta) {
		this.descrizioneCompleta = descrizioneCompleta;
	}

	/**
	 * @return the children
	 */
	public List<ElementoPianoDeiContiWrapper> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ElementoPianoDeiContiWrapper> children) {
		this.children = children;
	}

	/**
	 * @return the tipoClassificatore
	 */
	public TipoClassificatore getTipoClassificatore() {
		return tipoClassificatore;
	}

	/**
	 * @param tipoClassificatore the tipoClassificatore to set
	 */
	public void setTipoClassificatore(TipoClassificatore tipoClassificatore) {
		this.tipoClassificatore = tipoClassificatore;
	}
	
}
