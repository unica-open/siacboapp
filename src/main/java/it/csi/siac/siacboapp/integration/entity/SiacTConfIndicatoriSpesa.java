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
@Table(name = "siac_t_conf_indicatori_spesa")
public class SiacTConfIndicatoriSpesa extends SiacTConfIndicatoriBase {

	private static final long serialVersionUID = -4147007264323439643L;

	@Id
	@SequenceGenerator(name = "SIAC_T_CONF_INDICATORI_SPESA_CONF_IND_ID_GENERATOR", allocationSize = 1, 
					   sequenceName = "siac_t_conf_indicatori_spesa_conf_ind_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_CONF_INDICATORI_SPESA_CONF_IND_ID_GENERATOR")
	@Column(name = "conf_ind_id")
	private Integer uid;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "classif_id_missione")
	private SiacTClass missione;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "classif_id_programma")
	private SiacTClass programma;

	@Column(name = "conf_ind_importo_fpv_anno_prec")
	private BigDecimal importoFpvAnnoPrec;

	@Column(name = "conf_ind_importo_fpv_anno_prec_1")
	private BigDecimal importoFpvAnnoPrec1;

	@Column(name = "conf_ind_importo_fpv_anno_prec_2")
	private BigDecimal importoFpvAnnoPrec2;


	@Column(name = "conf_ind_importo_impegni_anno_prec")
	private BigDecimal importoImpegniAnnoPrec;

	@Column(name = "conf_ind_importo_impegni_anno_prec_1")
	private BigDecimal importoImpegniAnnoPrec1;

	@Column(name = "conf_ind_importo_impegni_anno_prec_2")
	private BigDecimal importoImpegniAnnoPrec2;


	@Column(name = "conf_ind_importo_pag_comp_anno_prec")
	private BigDecimal importoPagCompAnnoPrec;

	@Column(name = "conf_ind_importo_pag_comp_anno_prec_1")
	private BigDecimal importoPagCompAnnoPrec1;

	@Column(name = "conf_ind_importo_pag_comp_anno_prec_2")
	private BigDecimal importoPagCompAnnoPrec2;


	@Column(name = "conf_ind_importo_pag_res_anno_prec")
	private BigDecimal importoPagResAnnoPrec;

	@Column(name = "conf_ind_importo_pag_res_anno_prec_1")
	private BigDecimal importoPagResAnnoPrec1;

	@Column(name = "conf_ind_importo_pag_res_anno_prec_2")
	private BigDecimal importoPagResAnnoPrec2;


	@Column(name = "conf_ind_importo_res_def_anno_prec")
	private BigDecimal importoResDefAnnoPrec;

	@Column(name = "conf_ind_importo_res_def_anno_prec_1")
	private BigDecimal importoResDefAnnoPrec1;

	@Column(name = "conf_ind_importo_res_def_anno_prec_2")
	private BigDecimal importoResDefAnnoPrec2;

	
	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTClass getMissione()
	{
		return missione;
	}

	public void setMissione(SiacTClass missione)
	{
		this.missione = missione;
	}

	public SiacTClass getProgramma()
	{
		return programma;
	}

	public void setProgramma(SiacTClass programma)
	{
		this.programma = programma;
	}

	public BigDecimal getImportoFpvAnnoPrec()
	{
		return importoFpvAnnoPrec;
	}

	public void setImportoFpvAnnoPrec(BigDecimal importoFpvAnnoPrec)
	{
		this.importoFpvAnnoPrec = importoFpvAnnoPrec;
	}

	public BigDecimal getImportoFpvAnnoPrec1()
	{
		return importoFpvAnnoPrec1;
	}

	public void setImportoFpvAnnoPrec1(BigDecimal importoFpvAnnoPrec1)
	{
		this.importoFpvAnnoPrec1 = importoFpvAnnoPrec1;
	}

	public BigDecimal getImportoFpvAnnoPrec2()
	{
		return importoFpvAnnoPrec2;
	}

	public void setImportoFpvAnnoPrec2(BigDecimal importoFpvAnnoPrec2)
	{
		this.importoFpvAnnoPrec2 = importoFpvAnnoPrec2;
	}

	public BigDecimal getImportoImpegniAnnoPrec()
	{
		return importoImpegniAnnoPrec;
	}

	public void setImportoImpegniAnnoPrec(BigDecimal importoImpegniAnnoPrec)
	{
		this.importoImpegniAnnoPrec = importoImpegniAnnoPrec;
	}

	public BigDecimal getImportoImpegniAnnoPrec1()
	{
		return importoImpegniAnnoPrec1;
	}

	public void setImportoImpegniAnnoPrec1(BigDecimal importoImpegniAnnoPrec1)
	{
		this.importoImpegniAnnoPrec1 = importoImpegniAnnoPrec1;
	}

	public BigDecimal getImportoImpegniAnnoPrec2()
	{
		return importoImpegniAnnoPrec2;
	}

	public void setImportoImpegniAnnoPrec2(BigDecimal importoImpegniAnnoPrec2)
	{
		this.importoImpegniAnnoPrec2 = importoImpegniAnnoPrec2;
	}

	public BigDecimal getImportoPagCompAnnoPrec()
	{
		return importoPagCompAnnoPrec;
	}

	public void setImportoPagCompAnnoPrec(BigDecimal importoPagCompAnnoPrec)
	{
		this.importoPagCompAnnoPrec = importoPagCompAnnoPrec;
	}

	public BigDecimal getImportoPagCompAnnoPrec1()
	{
		return importoPagCompAnnoPrec1;
	}

	public void setImportoPagCompAnnoPrec1(BigDecimal importoPagCompAnnoPrec1)
	{
		this.importoPagCompAnnoPrec1 = importoPagCompAnnoPrec1;
	}

	public BigDecimal getImportoPagCompAnnoPrec2()
	{
		return importoPagCompAnnoPrec2;
	}

	public void setImportoPagCompAnnoPrec2(BigDecimal importoPagCompAnnoPrec2)
	{
		this.importoPagCompAnnoPrec2 = importoPagCompAnnoPrec2;
	}

	public BigDecimal getImportoPagResAnnoPrec()
	{
		return importoPagResAnnoPrec;
	}

	public void setImportoPagResAnnoPrec(BigDecimal importoPagResAnnoPrec)
	{
		this.importoPagResAnnoPrec = importoPagResAnnoPrec;
	}

	public BigDecimal getImportoPagResAnnoPrec1()
	{
		return importoPagResAnnoPrec1;
	}

	public void setImportoPagResAnnoPrec1(BigDecimal importoPagResAnnoPrec1)
	{
		this.importoPagResAnnoPrec1 = importoPagResAnnoPrec1;
	}

	public BigDecimal getImportoPagResAnnoPrec2()
	{
		return importoPagResAnnoPrec2;
	}

	public void setImportoPagResAnnoPrec2(BigDecimal importoPagResAnnoPrec2)
	{
		this.importoPagResAnnoPrec2 = importoPagResAnnoPrec2;
	}

	public BigDecimal getImportoResDefAnnoPrec()
	{
		return importoResDefAnnoPrec;
	}

	public void setImportoResDefAnnoPrec(BigDecimal importoResDefAnnoPrec)
	{
		this.importoResDefAnnoPrec = importoResDefAnnoPrec;
	}

	public BigDecimal getImportoResDefAnnoPrec1()
	{
		return importoResDefAnnoPrec1;
	}

	public void setImportoResDefAnnoPrec1(BigDecimal importoResDefAnnoPrec1)
	{
		this.importoResDefAnnoPrec1 = importoResDefAnnoPrec1;
	}

	public BigDecimal getImportoResDefAnnoPrec2()
	{
		return importoResDefAnnoPrec2;
	}

	public void setImportoResDefAnnoPrec2(BigDecimal importoResDefAnnoPrec2)
	{
		this.importoResDefAnnoPrec2 = importoResDefAnnoPrec2;
	}

}