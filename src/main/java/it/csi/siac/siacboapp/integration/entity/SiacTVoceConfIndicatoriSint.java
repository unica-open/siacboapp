/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_voce_conf_indicatori_sint")
public class SiacTVoceConfIndicatoriSint extends SiacTEnteBase {

	private static final long serialVersionUID = 6818464059475149110L;

	@Id
	@SequenceGenerator(name = "SIAC_T_VOCE_CONF_INDICATORI_SINT_VOCE_CONF_IND_ID_GENERATOR", allocationSize = 1, 
					   sequenceName = "siac_t_voce_conf_indicatori_sint_voce_conf_ind_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_VOCE_CONF_INDICATORI_SINT_VOCE_CONF_IND_ID_GENERATOR")
	@Column(name = "voce_conf_ind_id")
	private Integer uid;

	@Column(name = "voce_conf_ind_codice")
	private String codice;

	@Column(name = "voce_conf_ind_desc")
	private String descrizione;

	@Column(name = "voce_conf_ind_decimali")
	private Integer decimali;

	@Column(name = "voce_conf_ind_num_anni_input")
	private Integer numeroAnniInput;

	@Column(name = "voce_conf_ind_split_missione_13")
	private Boolean splitMissione13;

	@Column(name = "voce_conf_ind_tipo")
	private String tipo;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
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

	public Integer getDecimali()
	{
		return decimali;
	}

	public void setDecimali(Integer decimali)
	{
		this.decimali = decimali;
	}

	public Integer getNumeroAnniInput()
	{
		return numeroAnniInput;
	}

	public void setNumeroAnniInput(Integer numeroAnniInput)
	{
		this.numeroAnniInput = numeroAnniInput;
	}

	public Boolean getSplitMissione13()
	{
		return splitMissione13;
	}

	public void setSplitMissione13(Boolean splitMissione13)
	{
		this.splitMissione13 = splitMissione13;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}