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
@Table(name = "siac_t_conf_indicatori_entrata")
public class SiacTConfIndicatoriEntrata extends SiacTConfIndicatoriBase {

	private static final long serialVersionUID = -4147007264323439643L;

	@Id
	@SequenceGenerator(name = "SIAC_T_CONF_INDICATORI_ENTRATA_CONF_IND_ID_GENERATOR", allocationSize = 1, 
					   sequenceName = "siac_t_conf_indicatori_entrata_conf_ind_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_CONF_INDICATORI_ENTRATA_CONF_IND_ID_GENERATOR")
	@Column(name = "conf_ind_id")
	private Integer uid;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "classif_id_titolo")
	private SiacTClass titolo;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "classif_id_tipologia")
	private SiacTClass tipologia;

	@Column(name = "conf_ind_importo_accert_anno_prec")
	private BigDecimal importoAccertamentoAnnoPrec;

	@Column(name = "conf_ind_importo_accert_anno_prec_1")
	private BigDecimal importoAccertamentoAnnoPrec1;

	@Column(name = "conf_ind_importo_accert_anno_prec_2")
	private BigDecimal importoAccertamentoAnnoPrec2;


	@Column(name = "conf_ind_importo_riscoss_anno_prec")
	private BigDecimal importoRiscossioneAnnoPrec;

	@Column(name = "conf_ind_importo_riscoss_anno_prec_1")
	private BigDecimal importoRiscossioneAnnoPrec1;

	@Column(name = "conf_ind_importo_riscoss_anno_prec_2")
	private BigDecimal importoRiscossioneAnnoPrec2;

	
	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTClass getTitolo()
	{
		return titolo;
	}

	public void setTitolo(SiacTClass titolo)
	{
		this.titolo = titolo;
	}

	public SiacTClass getTipologia()
	{
		return tipologia;
	}

	public void setTipologia(SiacTClass tipologia)
	{
		this.tipologia = tipologia;
	}

	public BigDecimal getImportoAccertamentoAnnoPrec()
	{
		return importoAccertamentoAnnoPrec;
	}

	public void setImportoAccertamentoAnnoPrec(BigDecimal importoAccertamentoAnnoPrec)
	{
		this.importoAccertamentoAnnoPrec = importoAccertamentoAnnoPrec;
	}

	public BigDecimal getImportoAccertamentoAnnoPrec1()
	{
		return importoAccertamentoAnnoPrec1;
	}

	public void setImportoAccertamentoAnnoPrec1(BigDecimal importoAccertamentoAnnoPrec1)
	{
		this.importoAccertamentoAnnoPrec1 = importoAccertamentoAnnoPrec1;
	}

	public BigDecimal getImportoAccertamentoAnnoPrec2()
	{
		return importoAccertamentoAnnoPrec2;
	}

	public void setImportoAccertamentoAnnoPrec2(BigDecimal importoAccertamentoAnnoPrec2)
	{
		this.importoAccertamentoAnnoPrec2 = importoAccertamentoAnnoPrec2;
	}

	public BigDecimal getImportoRiscossioneAnnoPrec()
	{
		return importoRiscossioneAnnoPrec;
	}

	public void setImportoRiscossioneAnnoPrec(BigDecimal importoRiscossioneAnnoPrec)
	{
		this.importoRiscossioneAnnoPrec = importoRiscossioneAnnoPrec;
	}

	public BigDecimal getImportoRiscossioneAnnoPrec1()
	{
		return importoRiscossioneAnnoPrec1;
	}

	public void setImportoRiscossioneAnnoPrec1(BigDecimal importoRiscossioneAnnoPrec1)
	{
		this.importoRiscossioneAnnoPrec1 = importoRiscossioneAnnoPrec1;
	}

	public BigDecimal getImportoRiscossioneAnnoPrec2()
	{
		return importoRiscossioneAnnoPrec2;
	}

	public void setImportoRiscossioneAnnoPrec2(BigDecimal importoRiscossioneAnnoPrec2)
	{
		this.importoRiscossioneAnnoPrec2 = importoRiscossioneAnnoPrec2;
	}


}