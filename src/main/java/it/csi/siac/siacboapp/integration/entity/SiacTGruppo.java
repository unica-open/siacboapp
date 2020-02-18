/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.siac.siaccommon.util.CoreUtils;

/**
 * The persistent class for the siac_t_gruppo database table.
 * 
 */
@Entity
@Table(name = "siac_t_gruppo")
public class SiacTGruppo extends SiacTEnteBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3451598533022965596L;

	@Id
	@SequenceGenerator(name = "SIAC_T_GRUPPO_GRUPPOID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_GRUPPO_GRUPPO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_GRUPPO_GRUPPOID_GENERATOR")
	@Column(name = "gruppo_id")
	private Integer uid;

	@Column(name = "gruppo_code")
	private String codice;

	@Column(name = "gruppo_desc")
	private String descrizione;

	@OneToMany(mappedBy = "gruppo")
	private List<SiacRGruppoAccount> account;

	@OneToMany(mappedBy = "gruppo", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRGruppoRuoloOp> ruoliOp;

	private transient List<Integer> classificatoriId;

	public SiacTGruppo(Integer uid) {
		this();
		this.uid = uid;
	}

	public SiacTGruppo() {
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

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<SiacRGruppoAccount> getAccount() {
		return account;
	}

	public void setAccount(List<SiacRGruppoAccount> account) {
		this.account = account;
	}

	public Set<SiacRGruppoRuoloOp> getRuoliOp() {
		return ruoliOp;
	}

	public void setRuoliOp(Set<SiacRGruppoRuoloOp> ruoliOp) {
		this.ruoliOp = ruoliOp;
	}

	public Integer[] getRuoliOpId() {
		if (ruoliOp != null) {
			Integer[] ruoliOpId = new Integer[ruoliOp.size()];

			int i = 0;
			for (SiacRGruppoRuoloOp rop : ruoliOp)
				ruoliOpId[i++] = rop.getRuoloOp().getUid();

			return ruoliOpId;
		}

		return null;
	}

	public void setRuoliOpId(Integer[] ruoliOpId) {
		ruoliOp = new HashSet<SiacRGruppoRuoloOp>();

		for (Integer id : ruoliOpId) {
			SiacRGruppoRuoloOp ar = new SiacRGruppoRuoloOp(new SiacDRuoloOp(id));
			ar.setGruppo(this);
			ruoliOp.add(ar);
		}
	}

	public List<Integer> getClassificatoriId() {
		if (classificatoriId == null) {
			classificatoriId = new ArrayList<Integer>();

			for (SiacRGruppoRuoloOp ar : ruoliOp)
				classificatoriId.add(ar.getClassificatore().getUid());
		}

		return classificatoriId;
	}

	public void setClassificatoriId(List<Integer> classificatoriId) {
		this.classificatoriId = classificatoriId;
	}

	public void copyClassificatoriIdToRuoliOp() {
		SiacRGruppoRuoloOp grop = ruoliOp.iterator().next();

		ruoliOp = new HashSet<SiacRGruppoRuoloOp>();

		for (Integer classificatoreId : classificatoriId) {
			SiacRGruppoRuoloOp gropClone = CoreUtils.dupObject(grop);
			gropClone.setClassificatore(new SiacTClass(classificatoreId));

			ruoliOp.add(gropClone);
		}
	}

}