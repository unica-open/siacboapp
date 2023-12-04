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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the siac_t_azione database table.
 * 
 */
@Entity
@Table(name = "siac_t_azione")
public class SiacTAzione extends SiacTEnteBase {

	private static final long serialVersionUID = -3662017111550324911L;

	@Id
	@SequenceGenerator(name = "SIAC_T_AZIONE_AZIONEID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_AZIONE_AZIONE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_AZIONE_AZIONEID_GENERATOR")
	@Column(name = "azione_id")
	private Integer uid;

	@Column(name = "azione_code")
	private String codice;

	@Column(name = "azione_desc")
	private String descrizione;

	@Column(name = "nomeprocesso")
	private String nomeProcesso;

	@Column(name = "nometask")
	private String nomeTask;

	@Column(name = "urlapplicazione")
	private String urlApplicazione;

	@Column(name = "verificauo")
	private Boolean verificaUo;

	@OneToMany(mappedBy = "azione", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRRuoloOpAzione> ruoliOp;

	@ManyToOne
	@JoinColumn(name = "azione_tipo_id")
	private SiacDAzioneTipo tipoAzione;

	@ManyToOne
	@JoinColumn(name = "gruppo_azioni_id")
	private SiacDGruppoAzioni gruppoAzioni;

	public SiacTAzione() {
		super();
	}

	public SiacTAzione(Integer uid) {
		this();
		this.uid = uid;
	}

	public Integer getTipoAzioneId() {
		return tipoAzione == null ? null : tipoAzione.getUid();
	}

	public void setTipoAzioneId(Integer tipoAzioneId) {
		setTipoAzione(new SiacDAzioneTipo(tipoAzioneId));
	}

	public Integer getGruppoAzioniId() {
		return gruppoAzioni == null ? null : gruppoAzioni.getUid();
	}

	public void setGruppoAzioniId(Integer gruppoAzioneId) {
		if (gruppoAzioneId != null) {
			setGruppoAzioni(new SiacDGruppoAzioni(gruppoAzioneId));
		}
	}

	public Integer getRuoloOpId() {
		try {
			return ruoliOp.iterator().next().getRuoloOp().getUid();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void setRuoloOpId(Integer ruoloOpId) {
		ruoliOp = new HashSet<SiacRRuoloOpAzione>();
		SiacRRuoloOpAzione roa = new SiacRRuoloOpAzione(new SiacDRuoloOp(ruoloOpId));
		roa.setAzione(this);
		ruoliOp.add(roa);
	}

	public Integer[] getRuoliOpId() {
		if (ruoliOp != null) {
			Integer[] ruoliOpId = new Integer[ruoliOp.size()];

			int i = 0;
			for (SiacRRuoloOpAzione roa : ruoliOp)
				ruoliOpId[i++] = roa.getRuoloOp().getUid();

			return ruoliOpId;
		}

		return null;
	}

	public void setRuoliOpId(Integer[] ruoliOpId) {
		ruoliOp = new HashSet<SiacRRuoloOpAzione>();

		for (Integer id : ruoliOpId) {
			SiacRRuoloOpAzione roa = new SiacRRuoloOpAzione(new SiacDRuoloOp(id));
			roa.setAzione(this);
			ruoliOp.add(roa);
		}
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

	public String getNomeProcesso() {
		return nomeProcesso;
	}

	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}

	public String getNomeTask() {
		return nomeTask;
	}

	public void setNomeTask(String nomeTask) {
		this.nomeTask = nomeTask;
	}

	public String getUrlApplicazione() {
		return urlApplicazione;
	}

	public void setUrlApplicazione(String urlApplicazione) {
		this.urlApplicazione = urlApplicazione;
	}

	public Boolean getVerificaUo() {
		return verificaUo;
	}

	public void setVerificaUo(Boolean verificaUo) {
		this.verificaUo = verificaUo;
	}

	public Set<SiacRRuoloOpAzione> getRuoliOp() {
		return ruoliOp;
	}

	public void setRuoliOp(Set<SiacRRuoloOpAzione> ruoliOp) {
		this.ruoliOp = ruoliOp;
	}

	public SiacDAzioneTipo getTipoAzione() {
		return tipoAzione;
	}

	public void setTipoAzione(SiacDAzioneTipo tipoAzione) {
		this.tipoAzione = tipoAzione;
	}

	public SiacDGruppoAzioni getGruppoAzioni() {
		return gruppoAzioni;
	}

	public void setGruppoAzioni(SiacDGruppoAzioni gruppoAzioni) {
		this.gruppoAzioni = gruppoAzioni;
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

}