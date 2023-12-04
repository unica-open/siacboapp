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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_r_fpv_set_cronop")
public class SiacRFpvSetCronop extends SiacTEnteBase {

	private static final long serialVersionUID = 3554382572272291699L;

	@Id
	@SequenceGenerator(name = "SIAC_R_FPV_SET_CRONOP_ID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_R_FPV_SET_CRONOP_SET_CRONOP_R_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_R_FPV_SET_CRONOP_ID_GENERATOR")
	@Column(name = "set_cronop_r_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name = "set_cronop_id")
	private SiacTFpvSetCronop setProgetti;

	@OneToOne
	@JoinColumn(name = "cronop_id")
	private SiacTCronop cronoprogramma;

	@OneToOne
	@JoinColumn(name = "programma_id")
	private SiacTProgramma programma;

	@Column(name = "usa_gestione")
	private Boolean usaGestione;

	public SiacTFpvSetCronop getSetProgetti() {
		return setProgetti;
	}

	public void setSetProgetti(SiacTFpvSetCronop setProgetti) {
		this.setProgetti = setProgetti;
	}

	public SiacTCronop getCronoprogramma() {
		return cronoprogramma;
	}

	public void setCronoprogramma(SiacTCronop cronoprogramma) {
		this.cronoprogramma = cronoprogramma;
	}

	public SiacTProgramma getProgramma() {
		return programma;
	}

	public void setProgramma(SiacTProgramma programma) {
		this.programma = programma;
	}

	public Boolean getUsaGestione() {
		return usaGestione == null ? Boolean.FALSE : usaGestione;
	}

	public void setUsaGestione(Boolean usaGestione) {
		this.usaGestione = usaGestione;
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