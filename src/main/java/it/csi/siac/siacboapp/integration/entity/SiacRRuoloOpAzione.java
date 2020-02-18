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
 * The persistent class for the siac_r_ruolo_op_azione database table.
 * 
 */
@Entity
@Table(name = "siac_r_ruolo_op_azione")
public class SiacRRuoloOpAzione extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3283370117780814490L;

	@Id
	@SequenceGenerator(name = "SIAC_R_RUOLO_OP_AZIONE_RUOLOOPAZIONEID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_RUOLO_OP_AZIONE_RUOLO_OP_AZIONE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_RUOLO_OP_AZIONE_RUOLOOPAZIONEID_GENERATOR")
	@Column(name = "ruolo_op_azione_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "ruolo_op_id")
	private SiacDRuoloOp ruoloOp;

	@ManyToOne
	@JoinColumn(name = "azione_id")
	private SiacTAzione azione;

	public SiacRRuoloOpAzione() {
		super();
	}

	public SiacRRuoloOpAzione(SiacDRuoloOp ruoloOp) {
		this();
		setRuoloOp(ruoloOp);
	}

	public SiacRRuoloOpAzione(SiacTAzione azione) {
		this();
		setAzione(azione);
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

	public SiacTAzione getAzione() {
		return azione;
	}

	public void setAzione(SiacTAzione azione) {
		this.azione = azione;
	}

}