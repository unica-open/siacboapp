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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="siac_r_movgest_ts_atto_amm")
@NamedQuery(name="SiacRMovgestTsAttoAmm.findAll", query="SELECT s FROM SiacRMovgestTsAttoAmm s")
public class SiacRMovgestTsAttoAmm extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	/** The movgest atto amm id. */
	@Id
	@SequenceGenerator(name="SIAC_R_MOVGEST_TS_ATTO_AMM_MOVGESTATTOAMMID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_MOVGEST_TS_ATTO_AMM_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_MOVGEST_TS_ATTO_AMM_MOVGESTATTOAMMID_GENERATOR")
	@Column(name="movgest_atto_amm_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="attoamm_id")
	private SiacTAttoAmm attoAmministrativo;

	//bi-directional many-to-one association to SiacTMovgestT
	/** The siac t movgest t. */
	@ManyToOne
	@JoinColumn(name="movgest_ts_id")
	private SiacTMovgestT movimentoGestioneTS;

	/**
	 * Instantiates a new siac r movgest ts atto amm.
	 */
	public SiacRMovgestTsAttoAmm() {
	}

	/* (non-Javadoc)
	 * @see it.csi.siac.siaccommonser.integration.entity.SiacTBase#getUid()
	 */
	@Override
	public Integer getUid() {
		return uid;
	}

	/* (non-Javadoc)
	 * @see it.csi.siac.siaccommonser.integration.entity.SiacTBase#setUid(java.lang.Integer)
	 */
	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacTAttoAmm getAttoAmministrativo() {
		return attoAmministrativo;
	}

	public void setAttoAmministrativo(SiacTAttoAmm attoAmministrativo) {
		this.attoAmministrativo = attoAmministrativo;
	}

	public SiacTMovgestT getMovimentoGestioneTS() {
		return movimentoGestioneTS;
	}

	public void setMovimentoGestioneTS(SiacTMovgestT movimentoGestioneTS) {
		this.movimentoGestioneTS = movimentoGestioneTS;
	}

}