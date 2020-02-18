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
@Table(name = "siac_r_movgest_ts_programma")
public class SiacRMovgestTsProgramma extends SiacTEnteBase {
	private static final long serialVersionUID = 3899795534911977762L;

	@Id
	@SequenceGenerator(name = "SIAC_R_MOVGEST_TS_PROGRAMMA_MOVGESTTSPROGRAMMAID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_MOVGEST_TS_PROGRAMMA_MOVGEST_TS_PROGRAMMA_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_MOVGEST_TS_PROGRAMMA_MOVGESTTSPROGRAMMAID_GENERATOR")
	@Column(name = "movgest_ts_programma_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "programma_id")
	private SiacTProgramma programma;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTProgramma getProgramma() {
		return programma;
	}

	public void setProgramma(SiacTProgramma programma) {
		this.programma = programma;
	}

}