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
 * The persistent class for the siac_r_gruppo_ruolo_op database table.
 * 
 */
@Entity
@Table(name = "siac_r_gruppo_ruolo_op")
public class SiacRGruppoRuoloOp extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1094003853946285840L;

	@Id
	@SequenceGenerator(name = "SIAC_R_GRUPPO_RUOLO_OP_GRUPPORUOLOOPID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_GRUPPO_RUOLO_OP_GRUPPO_RUOLO_OP_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_GRUPPO_RUOLO_OP_GRUPPORUOLOOPID_GENERATOR")
	@Column(name = "gruppo_ruolo_op_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "ruolo_operativo_id")
	private SiacDRuoloOp ruoloOp;

	@ManyToOne
	@JoinColumn(name = "classif_id")
	private SiacTClass classificatore;

	@ManyToOne
	@JoinColumn(name = "gruppo_id")
	private SiacTGruppo gruppo;

	public SiacRGruppoRuoloOp(SiacDRuoloOp ruoloOp) {
		this();
		this.ruoloOp = ruoloOp;
	}

	public SiacRGruppoRuoloOp() {
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

	public SiacDRuoloOp getRuoloOp() {
		return ruoloOp;
	}

	public void setRuoloOp(SiacDRuoloOp ruoloOp) {
		this.ruoloOp = ruoloOp;
	}

	public SiacTClass getClassificatore() {
		return classificatore;
	}

	public void setClassificatore(SiacTClass classificatore) {
		this.classificatore = classificatore;
	}

	public SiacTGruppo getGruppo() {
		return gruppo;
	}

	public void setGruppo(SiacTGruppo gruppo) {
		this.gruppo = gruppo;
	}

}