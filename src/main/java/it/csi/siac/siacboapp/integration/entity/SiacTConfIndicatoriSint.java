/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_conf_indicatori_sint")
public class SiacTConfIndicatoriSint extends SiacTConfIndicatoriBase {

	private static final long serialVersionUID = -4462700791076241401L;

	@Id
	@SequenceGenerator(name = "SIAC_T_CONF_INDICATORI_SINT_CONF_IND_ID_GENERATOR", allocationSize = 1, 
					   sequenceName = "siac_t_conf_indicatori_sint_conf_ind_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_CONF_INDICATORI_SINT_CONF_IND_ID_GENERATOR")
	@Column(name = "conf_ind_id")
	private Integer uid;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "voce_conf_ind_id")
	private SiacTVoceConfIndicatoriSint voce;

	@Column(name = "conf_ind_valore_anno")
	private BigDecimal valoreAnno;

	@Column(name = "conf_ind_valore_anno_1")
	private BigDecimal valoreAnno1;

	@Column(name = "conf_ind_valore_anno_2")
	private BigDecimal valoreAnno2;

	@Column(name = "conf_ind_valore_tot_miss_13_anno")
	private BigDecimal valoreTotMiss13Anno;

	@Column(name = "conf_ind_valore_tot_miss_13_anno_1")
	private BigDecimal valoreTotMiss13Anno1;

	@Column(name = "conf_ind_valore_tot_miss_13_anno_2")
	private BigDecimal valoreTotMiss13Anno2;

	@Column(name = "conf_ind_valore_tutte_spese_anno")
	private BigDecimal valoreTutteSpeseAnno;

	@Column(name = "conf_ind_valore_tutte_spese_anno_1")
	private BigDecimal valoreTutteSpeseAnno1;

	@Column(name = "conf_ind_valore_tutte_spese_anno_2")
	private BigDecimal valoreTutteSpeseAnno2;

	
	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTVoceConfIndicatoriSint getVoce()
	{
		return voce;
	}

	public void setVoce(SiacTVoceConfIndicatoriSint voce)
	{
		this.voce = voce;
	}

	public BigDecimal getValoreAnno()
	{
		return valoreAnno;
	}

	public void setValoreAnno(BigDecimal valoreAnno)
	{
		this.valoreAnno = valoreAnno;
	}

	public BigDecimal getValoreAnno1()
	{
		return valoreAnno1;
	}

	public void setValoreAnno1(BigDecimal valoreAnno1)
	{
		this.valoreAnno1 = valoreAnno1;
	}

	public BigDecimal getValoreAnno2()
	{
		return valoreAnno2;
	}

	public void setValoreAnno2(BigDecimal valoreAnno2)
	{
		this.valoreAnno2 = valoreAnno2;
	}

	public BigDecimal getValoreTotMiss13Anno()
	{
		return valoreTotMiss13Anno;
	}

	public void setValoreTotMiss13Anno(BigDecimal valoreTotMiss13Anno)
	{
		this.valoreTotMiss13Anno = valoreTotMiss13Anno;
	}

	public BigDecimal getValoreTotMiss13Anno1()
	{
		return valoreTotMiss13Anno1;
	}

	public void setValoreTotMiss13Anno1(BigDecimal valoreTotMiss13Anno1)
	{
		this.valoreTotMiss13Anno1 = valoreTotMiss13Anno1;
	}

	public BigDecimal getValoreTotMiss13Anno2()
	{
		return valoreTotMiss13Anno2;
	}

	public void setValoreTotMiss13Anno2(BigDecimal valoreTotMiss13Anno2)
	{
		this.valoreTotMiss13Anno2 = valoreTotMiss13Anno2;
	}

	public BigDecimal getValoreTutteSpeseAnno()
	{
		return valoreTutteSpeseAnno;
	}

	public void setValoreTutteSpeseAnno(BigDecimal valoreTutteSpeseAnno)
	{
		this.valoreTutteSpeseAnno = valoreTutteSpeseAnno;
	}

	public BigDecimal getValoreTutteSpeseAnno1()
	{
		return valoreTutteSpeseAnno1;
	}

	public void setValoreTutteSpeseAnno1(BigDecimal valoreTutteSpeseAnno1)
	{
		this.valoreTutteSpeseAnno1 = valoreTutteSpeseAnno1;
	}

	public BigDecimal getValoreTutteSpeseAnno2()
	{
		return valoreTutteSpeseAnno2;
	}

	public void setValoreTutteSpeseAnno2(BigDecimal valoreTutteSpeseAnno2)
	{
		this.valoreTutteSpeseAnno2 = valoreTutteSpeseAnno2;
	}




}