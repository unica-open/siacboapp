/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public final class NumberFormatter {
	private NumberFormatter() {
		// Non permettere l'instanziazione
	}
	private static final ThreadLocal<NumberFormat> TL_NF_DECIMAL = new ThreadLocal<NumberFormat>() {
		@Override
		protected NumberFormat initialValue() {
			return new DecimalFormat("0.00");
		}
	};

	private static final ThreadLocal<NumberFormat> TL_NF_IMPORTO = new ThreadLocal<NumberFormat>() {
		@Override
		protected NumberFormat initialValue() {
			return new DecimalFormat("#,##0.00");
		}
	};

	public static String numberToDecimal(Number n) {
		return format(TL_NF_DECIMAL, n);
	}
	
	public static Double decimalToDouble(String s) throws ParseException {
		return parse(TL_NF_DECIMAL, s);
	}

	public static String numberToImporto(Number n) {
		return format(TL_NF_IMPORTO, n);
	}
	
	public static Double importoToDouble(String s) throws ParseException {
		return parse(TL_NF_IMPORTO, s);
	}
	
	private static String format(ThreadLocal<NumberFormat> t, Number n) {
		return n != null ? t.get().format(n) : null;
	}
	
	private static Double parse(ThreadLocal<NumberFormat> t, String s) throws ParseException {
		return t.get().parse(s).doubleValue();
	}
	
	
}
