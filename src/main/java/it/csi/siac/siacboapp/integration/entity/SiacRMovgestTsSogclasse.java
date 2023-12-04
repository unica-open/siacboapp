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
@Table(name="siac_r_movgest_ts_sogclasse")
public class SiacRMovgestTsSogclasse extends SiacTEnteBase {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_MOVGEST_TS_SOGCLASSE_MOVGESTTSSOGCLASSEID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_MOVGEST_TS_SOGCLASSE_MOVGEST_TS_SOGCLASSE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_MOVGEST_TS_SOGCLASSE_MOVGESTTSSOGCLASSEID_GENERATOR")
	@Column(name="movgest_ts_sogclasse_id")

	private Integer uid;

	

	@ManyToOne
	@JoinColumn(name="soggetto_classe_id")
	private SiacDSoggettoClasse classeSoggetto;

	@ManyToOne
	@JoinColumn(name="movgest_ts_id")
	private SiacTMovgestT movimentoGestioneTS;


	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
		
	}

	public SiacDSoggettoClasse getClasseSoggetto() {
		return classeSoggetto;
	}

	public void setClasseSoggetto(SiacDSoggettoClasse classeSoggetto) {
		this.classeSoggetto = classeSoggetto;
	}

	public SiacTMovgestT getMovimentoGestioneTS() {
		return movimentoGestioneTS;
	}

	public void setMovimentoGestioneTS(SiacTMovgestT movimentoGestioneTS) {
		this.movimentoGestioneTS = movimentoGestioneTS;
	}

}