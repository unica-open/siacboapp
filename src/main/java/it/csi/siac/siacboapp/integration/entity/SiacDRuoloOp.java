/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the siac_d_ruolo_op database table.
 * 
 */
@Entity
@Table(name = "siac_d_ruolo_op")
public class SiacDRuoloOp extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7521510580745929189L;

	@Id
	@SequenceGenerator(name = "SIAC_D_RUOLO_OP_RUOLOOPID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_D_RUOLO_OP_RUOLO_OP_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_D_RUOLO_OP_RUOLOOPID_GENERATOR")
	@Column(name = "ruolo_op_id")
	private Integer uid;

	@Column(name = "ruolo_op_code")
	private String codice;

	@Column(name = "ruolo_op_desc")
	private String descrizione;

	@OneToMany(mappedBy = "ruoloOp", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRRuoloOpAzione> azioni;

	@OneToMany(mappedBy = "ruoloOp", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRRuoloOpBil> bilanci;

	public SiacDRuoloOp(Integer uid) {
		this();
		this.uid = uid;
	}

	public SiacDRuoloOp() {
		super();
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<SiacRRuoloOpAzione> getAzioni() {
		return azioni;
	}

	public Set<SiacRRuoloOpBil> getBilanci() {
		return bilanci;
	}

	public void setAzioni(Set<SiacRRuoloOpAzione> azioni) {
		this.azioni = azioni;
	}

	public Integer getAzioneId() {
		try {
			return azioni.iterator().next().getAzione().getUid();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void setAzioneId(Integer azioneId) {
		azioni = new HashSet<SiacRRuoloOpAzione>();
		SiacRRuoloOpAzione roa = new SiacRRuoloOpAzione(new SiacTAzione(azioneId));
		roa.setRuoloOp(this);
		azioni.add(roa);
	}

	public Integer[] getAzioniId() {
		if (azioni != null) {
			Integer[] azioniId = new Integer[azioni.size()];

			int i = 0;
			for (SiacRRuoloOpAzione roa : azioni)
				azioniId[i++] = roa.getAzione().getUid();

			return azioniId;
		}

		return null;
	}

	public void setAzioniId(Integer[] azioniId) {
		azioni = new HashSet<SiacRRuoloOpAzione>();

		for (Integer id : azioniId) {
			SiacRRuoloOpAzione roa = new SiacRRuoloOpAzione(new SiacTAzione(id));
			roa.setRuoloOp(this);
			azioni.add(roa);
		}
	}

	public Integer getBilancioId() {
		try {
			return bilanci.iterator().next().getBilancio().getUid();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void setBilancioId(Integer bilancioId) {
		bilanci = new HashSet<SiacRRuoloOpBil>();
		SiacRRuoloOpBil rob = new SiacRRuoloOpBil(new SiacTBil(bilancioId));
		rob.setRuoloOp(this);
		bilanci.add(rob);
	}

	public Integer[] getBilanciId() {
		if (bilanci != null) {
			Integer[] bilanciId = new Integer[bilanci.size()];

			int i = 0;
			for (SiacRRuoloOpBil rob : bilanci)
				bilanciId[i++] = rob.getBilancio().getUid();

			return bilanciId;
		}

		return null;
	}

	public void setBilanciId(Integer[] bilanciId) {
		bilanci = new HashSet<SiacRRuoloOpBil>();

		for (Integer id : bilanciId) {
			SiacRRuoloOpBil rob = new SiacRRuoloOpBil(new SiacTBil(id));
			rob.setRuoloOp(this);
			bilanci.add(rob);
		}
	}

}