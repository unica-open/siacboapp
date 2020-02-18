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
 * The persistent class for the siac_r_gruppo_account database table.
 * 
 */
@Entity
@Table(name = "siac_r_gruppo_account")
public class SiacRGruppoAccount extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8982952584772773566L;

	@Id
	@SequenceGenerator(name = "SIAC_R_GRUPPO_ACCOUNT_GRUPPOACCOUNTID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_GRUPPO_ACCOUNT_GRUPPO_ACCOUNT_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_GRUPPO_ACCOUNT_GRUPPOACCOUNTID_GENERATOR")
	@Column(name = "gruppo_account_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private SiacTAccount account;

	@ManyToOne
	@JoinColumn(name = "gruppo_id")
	private SiacTGruppo gruppo;

	public SiacRGruppoAccount(SiacTGruppo gruppo) {
		this();
		this.gruppo = gruppo;
	}

	public SiacRGruppoAccount() {
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

	public SiacTAccount getAccount() {
		return account;
	}

	public void setAccount(SiacTAccount account) {
		this.account = account;
	}

	public SiacTGruppo getGruppo() {
		return gruppo;
	}

	public void setGruppo(SiacTGruppo gruppo) {
		this.gruppo = gruppo;
	}

}