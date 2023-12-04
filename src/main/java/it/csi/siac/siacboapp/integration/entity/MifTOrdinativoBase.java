/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class MifTOrdinativoBase extends SiacTEnteBase {
	private static final long serialVersionUID = -8737131425681509595L;

	@Id
	@Column(name = "mif_ord_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "mif_ord_ord_id")
	private SiacTOrdinativo ordinativo;

	@ManyToOne
	@JoinColumn(name = "mif_ord_flusso_elab_mif_id")
	private MifTFlussoElaborato flussoElaborato;

	@Column(name = "mif_ord_codice_flusso_oil")
	private String codiceFlusso;

	@ManyToOne
	@JoinColumn(name = "mif_ord_bil_id")
	private SiacTBil bilancio;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTOrdinativo getOrdinativo() {
		return ordinativo;
	}

	public void setOrdinativo(SiacTOrdinativo ordinativo) {
		this.ordinativo = ordinativo;
	}

	public String getCodiceFlusso() {
		return codiceFlusso;
	}

	public void setCodiceFlusso(String codiceFlusso) {
		this.codiceFlusso = codiceFlusso;
	}

	public MifTFlussoElaborato getFlussoElaborato() {
		return flussoElaborato;
	}

	public void setFlussoElaborato(MifTFlussoElaborato flussoElaborato) {
		this.flussoElaborato = flussoElaborato;
	}

	public SiacTBil getBilancio() {
		return bilancio;
	}

	public void setBilancio(SiacTBil bilancio) {
		this.bilancio = bilancio;
	}
	
	
}