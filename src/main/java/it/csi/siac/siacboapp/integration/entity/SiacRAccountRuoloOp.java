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

/**
 * The persistent class for the siac_r_account_ruolo_op database table.
 * 
 */
@Entity
@Table(name = "siac_r_account_ruolo_op")
public class SiacRAccountRuoloOp extends SiacTEnteBase {

	private static final long serialVersionUID = -2075954460885703609L;

	@Id
	@SequenceGenerator(name = "SIAC_R_ACCOUNT_RUOLO_OP_ACCOUNTRUOLOOPID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_ACCOUNT_RUOLO_OP_ACCOUNT_RUOLO_OP_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_ACCOUNT_RUOLO_OP_ACCOUNTRUOLOOPID_GENERATOR")
	@Column(name = "account_ruolo_op_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "ruolo_operativo_id")
	private SiacDRuoloOp ruoloOp;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private SiacTAccount account;

	public SiacRAccountRuoloOp(SiacDRuoloOp ruoloOp) {
		this();
		this.ruoloOp = ruoloOp;
	}

	public SiacRAccountRuoloOp() {
		super();
	}

	public SiacDRuoloOp getRuoloOp() {
		return ruoloOp;
	}

	public void setRuoloOp(SiacDRuoloOp ruoloOp) {
		this.ruoloOp = ruoloOp;
	}

	public SiacTAccount getAccount() {
		return account;
	}

	public void setAccount(SiacTAccount account) {
		this.account = account;
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