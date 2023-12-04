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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_r_ruolo_op_bil")
public class SiacRRuoloOpBil extends SiacTEnteBase {

	private static final long serialVersionUID = 5322695760085973570L;

	@Id
	@SequenceGenerator(name = "SIAC_R_RUOLO_OP_BIL_RUOLOOPBILID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_RUOLO_OP_BIL_RUOLO_OP_BIL_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_RUOLO_OP_BIL_RUOLOOPBILID_GENERATOR")
	@Column(name = "ruolo_op_bil_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "ruolo_op_id")
	private SiacDRuoloOp ruoloOp;

	@ManyToOne
	@JoinColumn(name = "bil_id")
	private SiacTBil bilancio;

	public SiacRRuoloOpBil() {
		super();
	}

	public SiacRRuoloOpBil(SiacDRuoloOp ruoloOp) {
		this();
		setRuoloOp(ruoloOp);
	}

	public SiacRRuoloOpBil(SiacTBil bilancio) {
		this();
		setBilancio(bilancio);
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;

	}

	public SiacDRuoloOp getRuoloOp() {
		return ruoloOp;
	}

	public void setRuoloOp(SiacDRuoloOp ruoloOp) {
		this.ruoloOp = ruoloOp;
	}

	public SiacTBil getBilancio()
	{
		return bilancio;
	}

	public void setBilancio(SiacTBil bilancio)
	{
		this.bilancio = bilancio;
	}
}