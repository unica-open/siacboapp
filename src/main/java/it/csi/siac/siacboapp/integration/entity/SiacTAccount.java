/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the siac_t_account database table.
 * 
 */
@Entity
@Table(name = "siac_t_account")
public class SiacTAccount extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3171493995216379463L;

	@Id
	@SequenceGenerator(name = "SIAC_T_ACCOUNT_ACCOUNTID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_ACCOUNT_ACCOUNT_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_ACCOUNT_ACCOUNTID_GENERATOR")
	@Column(name = "account_id")
	private Integer uid;

	@Column(name = "account_code")
	private String codice;

	private String descrizione;

	private String email;

	private String nome;

	@ManyToOne
	@JoinColumn(name = "soggeto_ruolo_id")
	private SiacRSoggettoRuolo soggettoRuolo;

	@OneToMany(mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRGruppoAccount> gruppi;

	@OneToMany(mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRAccountRuoloOp> ruoliOp;

	@OneToMany(mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRAccountClass> struttureAmministrativeContabili;

	@OneToMany(mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRAccountCassaEcon> casseEconomali;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SiacRSoggettoRuolo getSoggettoRuolo() {
		return soggettoRuolo;
	}

	public void setSoggettoRuolo(SiacRSoggettoRuolo soggettoRuolo) {
		this.soggettoRuolo = soggettoRuolo;
	}

	public Integer getSoggettoRuoloId() {
		return soggettoRuolo == null ? null : soggettoRuolo.getUid();
	}

	public void setSoggettoRuoloId(Integer soggettoRuoloId) {
		soggettoRuolo = new SiacRSoggettoRuolo();
		soggettoRuolo.setUid(soggettoRuoloId);
	}

	public Integer[] getGruppiId() {
		if (gruppi != null) {
			Integer[] gruppiId = new Integer[gruppi.size()];

			int i = 0;
			for (SiacRGruppoAccount ag : gruppi)
				gruppiId[i++] = ag.getGruppo().getUid();

			return gruppiId;
		}

		return null;
	}

	public void setGruppiId(Integer[] gruppiId) {
		gruppi = new HashSet<SiacRGruppoAccount>();

		for (Integer id : gruppiId) {
			SiacRGruppoAccount ag = new SiacRGruppoAccount(new SiacTGruppo(id));
			ag.setAccount(this);
			gruppi.add(ag);
		}
	}

	public Integer[] getRuoliOpId() {
		if (ruoliOp != null) {
			Integer[] ruoliOpId = new Integer[ruoliOp.size()];

			int i = 0;
			for (SiacRAccountRuoloOp rop : ruoliOp)
				ruoliOpId[i++] = rop.getRuoloOp().getUid();

			return ruoliOpId;
		}

		return null;
	}

	public void setRuoliOpId(Integer[] ruoliOpId) {
		ruoliOp = new HashSet<SiacRAccountRuoloOp>();

		for (Integer id : ruoliOpId) {
			SiacRAccountRuoloOp ar = new SiacRAccountRuoloOp(new SiacDRuoloOp(id));
			ar.setAccount(this);
			ruoliOp.add(ar);
		}
	}

	public Integer[] getStruttureAmministrativeContabiliId() {
		if (struttureAmministrativeContabili != null) {
			Integer[] struttureAmministrativeContabiliId = new Integer[struttureAmministrativeContabili
					.size()];

			int i = 0;
			for (SiacRAccountClass asac : struttureAmministrativeContabili)
				struttureAmministrativeContabiliId[i++] = asac
						.getStrutturaAmministrativaContabile().getUid();

			return struttureAmministrativeContabiliId;
		}

		return null;
	}

	public void setStruttureAmministrativeContabiliId(Integer[] struttureAmministrativeContabiliId) {
		struttureAmministrativeContabili = new HashSet<SiacRAccountClass>();

		for (Integer id : struttureAmministrativeContabiliId) {
			SiacRAccountClass asac = new SiacRAccountClass(new SiacTClass(id));
			asac.setAccount(this);
			struttureAmministrativeContabili.add(asac);
		}
	}

	public Integer[] getCasseEconomaliId() {
		if (casseEconomali != null) {
			Integer[] casseEconomaliId = new Integer[casseEconomali.size()];

			int i = 0;
			for (SiacRAccountCassaEcon ace : casseEconomali)
				casseEconomaliId[i++] = ace.getCassaEconomale().getUid();

			return casseEconomaliId;
		}

		return null;
	}

	public void setCasseEconomaliId(Integer[] casseEconomaliId) {
		casseEconomali = new HashSet<SiacRAccountCassaEcon>();

		for (Integer id : casseEconomaliId) {
			SiacRAccountCassaEcon ace = new SiacRAccountCassaEcon(new SiacTCassaEcon(id));
			ace.setAccount(this);
			casseEconomali.add(ace);
		}
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Set<SiacRGruppoAccount> getGruppi() {
		return gruppi;
	}

	public void setGruppi(Set<SiacRGruppoAccount> gruppi) {
		this.gruppi = gruppi;
	}

	public Set<SiacRAccountRuoloOp> getRuoliOp() {
		return ruoliOp;
	}

	public void setRuoliOp(Set<SiacRAccountRuoloOp> ruoliOp) {
		this.ruoliOp = ruoliOp;
	}

	public Set<SiacRAccountClass> getStruttureAmministrativeContabili() {
		return struttureAmministrativeContabili;
	}

	public void setStruttureAmministrativeContabili(
			Set<SiacRAccountClass> struttureAmministrativeContabili) {
		this.struttureAmministrativeContabili = struttureAmministrativeContabili;
	}

	public Set<SiacRAccountCassaEcon> getCasseEconomali() {
		return casseEconomali;
	}

	public void setCasseEconomali(Set<SiacRAccountCassaEcon> casseEconomali) {
		this.casseEconomali = casseEconomali;
	}

}