/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bko_t_system_column")
public class BkoTSystemColumn extends BkoBase implements Comparable<BkoTSystemColumn> {
	private static final String FIELD_READABLE = "FR";
	private static final String FIELD_UPDATABLE = "FU";

	private static final long serialVersionUID = -2496681702320299922L;

	@Id
	@Column(name = "sc_id")
	private Integer id;

	@Column(name = "sc_column_oid")
	private Integer columnOid;

	@Column(name = "st_column_name")
	private String name;

	@Column(name = "sc_type")
	private String type;

	@Column(name = "st_column_label")
	private String label;

	@Column(name = "primary_key")
	private Boolean primaryKey;

	@Column(name = "sc_order")
	private Integer ordinalPosition;

	@ManyToOne
	@JoinColumn(name = "st_id")
	private BkoTSystemTable table;

	@OneToMany(mappedBy = "referencingColumn")
	private List<BkoTSystemColumn> referencedColumns;

	@ManyToOne
	@JoinColumn(nullable = true, name = "sc_pk_id")
	private BkoTSystemColumn referencingColumn;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "bko_r_system_column_action", joinColumns = @JoinColumn(name = "sc_id"), inverseJoinColumns = @JoinColumn(name = "ca_id"))
	private List<BkoDSystemColumnAction> actions;

	private transient Map<String, BkoDSystemColumnAction> actionsMap = new HashMap<String, BkoDSystemColumnAction>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isVisible() {
		checkInitActionsMap();
		return actionsMap.containsKey(FIELD_READABLE);
	}

	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public boolean isEditable() {
		checkInitActionsMap();
		return actionsMap.containsKey(FIELD_UPDATABLE);
	}

	private void checkInitActionsMap() {
		if (actionsMap.isEmpty())
			for (BkoDSystemColumnAction a : getActions())
				actionsMap.put(a.getCode(), a);
	}

	public Integer getColumnOid() {
		return columnOid;
	}

	public void setColumnOid(Integer columnOid) {
		this.columnOid = columnOid;
	}

	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	public boolean isPrimaryKey() {
		return getPrimaryKey();
	}

	public boolean hasForeignKey() {
		return referencingColumn != null;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<BkoTSystemColumn> getReferencedColumns() {
		return referencedColumns;
	}

	public void setReferencedColumns(List<BkoTSystemColumn> referencedColumns) {
		this.referencedColumns = referencedColumns;
	}

	public BkoTSystemColumn getReferencingColumn() {
		return referencingColumn;
	}

	public void setReferencingColumn(BkoTSystemColumn referencingColumn) {
		this.referencingColumn = referencingColumn;
	}

	public List<BkoDSystemColumnAction> getActions() {
		return actions;
	}

	public void setActions(List<BkoDSystemColumnAction> actions) {
		this.actions = actions;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BkoTSystemTable getTable() {
		return table;
	}

	public void setTable(BkoTSystemTable table) {
		this.table = table;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object obj) {
		// Non compatibile con compareTo(BkoTSystemColumn)
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// Compatibile con equals(Object), non compatibile con compareTo(BkoTSystemColumn)
		return super.hashCode();
	}

	@Override
	public int compareTo(BkoTSystemColumn o) {
		// L'ordinamento naturale della classe NON e' compatibile con equals(Object)
		if (o == null || o.getOrdinalPosition() == null)
			return 1;

		if (ordinalPosition == null)
			return 0;

		return getOrdinalPosition().compareTo(o.getOrdinalPosition());
	}

}