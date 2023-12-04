/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

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

@Entity
@Table(name = "siac_t_bil")
public class SiacTBil extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3252486385805855023L;

	@Id
	@Column(name = "bil_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_BILANCIO")
	@SequenceGenerator(name = "SEQ_T_BILANCIO", allocationSize = 1, sequenceName = "siac_t_bilancio_bil_id_seq")
	private Integer uid;

	@Column(name = "bil_code")
	private String codice;

	@Column(name = "bil_desc")
	private String descrizione;

	@ManyToOne
	@JoinColumn(name = "periodo_id")
	private SiacTPeriodo periodo;
	
	@OneToMany(mappedBy = "bilancio", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRRuoloOpBil> ruoliOp;

	public SiacTBil(int uid) {
		this();
		this.uid = uid;
	}

	public SiacTBil() {
		super();
	}

	public SiacTBil(Integer uid) {
		this();
		this.uid = uid;
	}
	
	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTPeriodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(SiacTPeriodo periodo) {
		this.periodo = periodo;
	}

	public Set<SiacRRuoloOpBil> getRuoliOp()
	{
		return ruoliOp;
	}

	public void setRuoliOp(Set<SiacRRuoloOpBil> ruoliOp)
	{
		this.ruoliOp = ruoliOp;
	}

	public String getCodice()
	{
		return codice;
	}

	public void setCodice(String codice)
	{
		this.codice = codice;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}
}
