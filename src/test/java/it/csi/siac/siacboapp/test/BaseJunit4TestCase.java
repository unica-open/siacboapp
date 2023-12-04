/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.csi.siac.siaccommon.util.JAXBUtility;
import it.csi.siac.siaccommon.util.log.LogUtil;
import it.csi.siac.siaccorser.frontend.webservice.msg.AsyncServiceRequestWrapper;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.Azione;
import it.csi.siac.siaccorser.model.AzioneRichiesta;
import it.csi.siac.siaccorser.model.Bilancio;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Entita;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.ServiceRequest;
import it.csi.siac.siaccorser.model.paginazione.ParametriPaginazione;
import junit.framework.TestCase;

/**
 * Classe di base dei test junit:
 * - aggancia il contesto descritto tramite ContextConfiguration
 * - fa autowiring del contesto stesso
 * 
 * Le sottoclassi devono poi solo ottenere i bean in questo modo:
 * 
 * <pre>
 * 	&#64;Before
 *	public void setUp() {
 *		voceDiBilancioDao = (VoceDiBilancioDao) applicationContext
 *			.getBean("voceDiBilancioDaoBean");
 *	}
 * </pre>	
 * 	Oppure più semplicemente basta dichiarare il proprio bean con annotazione <code>&#64;Autowired</code>
 * 	(Domenico)
 * 
 * @author alagna
 * @version $Id: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"/spring/applicationContext-test.xml", 
		"/spring/serviceClientContext-test.xml", 
		"/spring/dozer-test.xml",
		"/spring/jpa-test.xml",
		"/spring/datasource-test.xml" 
		})
public abstract class BaseJunit4TestCase extends TestCase {

	/** The log. */
	protected LogUtil log = new LogUtil(this.getClass());

	/** The application context. */
	@Autowired
	protected ApplicationContext applicationContext;
	
	private Properties accountProperties;
	
	@PostConstruct
	private void init() {
		final String methodName = "init";
		accountProperties = new Properties();
		
		InputStream is = null;
		
		try {
			is = getClass().getClassLoader().getResourceAsStream("./spring/account.properties");
			if(is != null) {
				try {
					accountProperties.load(is);
				} catch (IOException e) {
					// Non fare nulla
					log.error(methodName, "Errore nella lettura delle properties", e);
				}
			} else {
				log.error(methodName, "Properties non lette");
			}
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// Ignore
				}
			}
		}
		
		
	}

	/**
	 * Sets the application context.
	 *
	 * @param applicationContext the new application context
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	
	/**
	 * Test del marshall e unmarshall utile per verificare request e response .
	 *
	 * @param <T> the generic type
	 * @param o the o
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	protected static <T> T marshallUnmarshall(T o) {
		return marshallUnmarshall(o, (Class<T>)o.getClass());
	}
	

	/**
	 * Test del marshall e unmarshall utile per verificare request e response .
	 *
	 * @param <T> the generic type
	 * @param o the o
	 * @param clazz the clazz
	 * @return the t
	 */
	protected static <T> T marshallUnmarshall(T o, Class<T> clazz) {
		String xml = JAXBUtility.marshall(o);
		T res = JAXBUtility.unmarshall(xml, clazz);
		return res;
	}
	
	
	
	/**
	 * Ottiene dei Parametri Paginazione di test.
	 * 
	 * @return i parametri
	 */
	protected ParametriPaginazione getParametriPaginazioneTest() {
		return getParametriPaginazione(0, 10);
	}
	
	
	protected Bilancio getBilancioTest(int uid, int anno) {
		Bilancio bilancio = new Bilancio();
		bilancio.setUid(uid);
		bilancio.setAnno(anno);
		return bilancio;
	}
	
	
	
	/**
	 * Ottiene il bilancio con id 1.
	 *
	 * @return the bilancio test
	 */
	protected Bilancio getBilancioTest() {
		return getBilancioTest(16,2015);
	}
	
	/**
	 * Ottiene il bilancio 2014 con id 6.
	 *
	 * @return the bilancio test
	 */
	protected Bilancio getBilancio2014Test() {
		return getBilancioTest(6,2014);
	}
	
	/**
	 * Ottiene il bilancio 2015 con id 16.
	 *
	 * @return the bilancio test
	 */
	protected Bilancio getBilancio2015Test() {
		return getBilancioTest(16,2015);
	}
	
	/**
	 * Ottiene il bilancio 2016 con id 16.
	 *
	 * @return the bilancio test
	 */
	protected Bilancio getBilancio2016Test() {
		return getBilancioTest(43,2016);
	}
	

	/**
	 * Ottiene l'ente con id 1.
	 *
	 * @return the ente test
	 */
	protected Ente getEnteTest() {
		return getEnteTest(1);
	}
	
	/**
	 * Ottiene l'ente con id selezionato.
	 *
	 * @param uid l'uid dell'ente
	 * @return the ente test
	 */
	protected Ente getEnteTest(int uid) {
		Ente ente = new Ente();
		ente.setUid(uid);
		return ente;
	}

	/**
	 * Ottiene un richiedente di test.
	 *
	 * @return the richiedente test
	 * @deprecated utilizzare {@link #getRichiedenteByProperties(String, String)}
	 */
	@Deprecated
	protected Richiedente getRichiedenteTest() {
		return getRichiedenteByProperties("dev", "ente1");
	}
	
	/**
	 * Ottiene un richiedente di test.
	 *
	 * @param codiceFiscaleOperatore il codice fiscale dell'operatore
	 * @param uidAccount l'uid dell'account
	 * @param uidEnte l'uid dell'ente
	 * 
	 * @return the richiedente test
	 * @deprecated utilizzare {@link #getRichiedenteByProperties(String, String)}
	 */
	@Deprecated
	protected Richiedente getRichiedenteTest(String codiceFiscaleOperatore, int uidAccount, int uidEnte) {
		Richiedente richiedente = new Richiedente();
		Operatore operatore = new Operatore();
		operatore.setCodiceFiscale(codiceFiscaleOperatore);
		richiedente.setOperatore(operatore);
		Account account = getAccountTest(uidAccount, uidEnte);
		richiedente.setAccount(account);
		return richiedente;
	}
	
	/**
	 * Ottiene un richiedente di test dal file di properties.
	 *
	 * @param ambiente l'ambiente da usare (forn2, coll, ...)
	 * @param codiceEnte il codice dell'ente (coto, regp, crp, edisu...)
	 * 
	 * @return the richiedente test
	 */
	protected Richiedente getRichiedenteByProperties(String ambiente, String codiceEnte) {
		String codiceFiscale = accountProperties.getProperty(ambiente + "." + codiceEnte + ".codicefiscale");
		int uidAccount = Integer.parseInt(accountProperties.getProperty(ambiente + "." + codiceEnte + ".accountid"));
		int uidEnte = Integer.parseInt(accountProperties.getProperty(ambiente + "." + codiceEnte + ".enteproprietarioid"));
		
		return getRichiedenteTest(codiceFiscale, uidAccount, uidEnte);
	}
	
	/**
	 * Ottiene un richiedente di test.
	 *
	 * @param codiceFiscaleOperatore il codice fiscale dell'operatore
	 * @param uidAccount l'uid dell'account
	 * @param uidEnte l'uid dell'ente
	 * 
	 * @return the richiedente test
	 */
	protected Richiedente getRichiedenteFORN2() {
		// EDISU
		return getRichiedenteByProperties("forn2", "edisu");
	}
	
	protected Ente getEnteForn2() {
		return getEnteTest(13);
	}

	protected Bilancio getBilancioForn2() {
		Bilancio bilancio = new Bilancio();
		bilancio.setUid(51);
		bilancio.setAnno(2016);
		return bilancio;
	}
	
	
	/**
	 * Gets the account test.
	 *
	 * @param uidAccount l'uid dell'account
	 * @param uidEnte l'uid dell'ente
	 * 
	 * @return the account test
	 */
	private Account getAccountTest(int uidAccount, int uidEnte) {
		Account account = new Account();
		account.setUid(uidAccount);
		account.setEnte(getEnteTest(uidEnte));
		account.setNome("MioNomeFinto");
		return account;
	}
	
	/**
	 * Gets the parametri paginazione.
	 *
	 * @param numeroPagina the numero pagina
	 * @param elementiPerPagina the elementi per pagina
	 * @return the parametri paginazione
	 */
	protected ParametriPaginazione getParametriPaginazione(int numeroPagina, int elementiPerPagina) {
		ParametriPaginazione pp = new ParametriPaginazione();
		pp.setNumeroPagina(numeroPagina);
		pp.setElementiPerPagina(elementiPerPagina);
		return pp;
	}
	
	
	/**
	 * Gets the test file bytes.
	 *
	 * @param fileName the file name
	 * @return the test file bytes
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] getTestFileBytes(String fileName) throws IOException {
		
		byte[] byteArray;
		java.io.File f = new java.io.File(fileName);
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		 
		try {
			baos = new ByteArrayOutputStream();
			fis = new FileInputStream(f);
			int b;
			while ((b = fis.read()) != -1){
				baos.write(b);
			}
			
			byteArray = baos.toByteArray();
			
		} finally {
			try {
				if(fis!=null) {
					fis.close();
				}
				fis = null;
				f = null;
				if(baos!=null){
					baos.close();
				}
				baos = null;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return byteArray;
	}
	
	/**
	 * Prints the test file bytes.
	 *
	 * @param fileName the file name
	 * @param bytes the bytes to write
	 * @return the test file bytes
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected static void printTestFileBytes(String fileName, byte[] bytes) throws IOException {
		
		java.io.File f = new java.io.File(fileName);
		ByteArrayInputStream bais = null;
		FileOutputStream fos = null;
		
		 
		try {
			bais = new ByteArrayInputStream(bytes);
			fos = new FileOutputStream(f, false);
			
			fos.write(bytes);
		} finally {
			
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			fos = null;
			f = null;
			if(bais!=null){
				try {
					bais.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			bais = null;
		}
	}
	
	/**
	 * Crea un'entit&agrave; con l'uid fornito
	 * @param clazz la classe dell'entit&agrave:
	 * @param uid l'uid dell'istanza
	 * @return l'entit&agrave; creata
	 */
	protected <T extends Entita> T create(Class<T> clazz, int uid) {
		T obj;
		try {
			obj = clazz.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("Impossibile creare un'istanza per la classe " + clazz + " con uid " + uid, e);
		}
		obj.setUid(uid);
		return obj;
	}
	
	
	/**
	 * Log dell'eventuale eccezione.
	 * 
	 * @author Marchino Alessandro
	 * @version 1.0.0 - 05/09/2014
	 *
	 */
	private class ExceptionLoggingRule implements TestRule {
		ExceptionLoggingRule() {
		}
		@Override
		public Statement apply(Statement base, Description description) {
			return statement(base, description);
		}

		private Statement statement(final Statement base, final Description description) {
			return new Statement() {
				@Override
				public void evaluate() throws Throwable {
					try {
						base.evaluate();
					} catch (Throwable e) {
						log.error(description.getMethodName(), "Errore nell'esecuzione del test", e);
						throw e;
					}
				}
			};
		}
	}
	/** Aggancio della regola */
	@Rule public ExceptionLoggingRule exceptionLoggingRule = new ExceptionLoggingRule();
	
	/**
	 * Log del tempo intercorso.
	 * 
	 * @author Marchino Alessandro
	 * @version 1.0.0 - 20/11/2014
	 *
	 */
	private class TimingLoggingRule implements TestRule {
		TimingLoggingRule() {
		}
		
		@Override
		public Statement apply(Statement base, Description description) {
			return statement(base, description);
		}

		private Statement statement(final Statement base, final Description description) {
			return new Statement() {
				@Override
				public void evaluate() throws Throwable {
					final String methodName = description.getMethodName();
					long time = System.nanoTime();
					try {
						base.evaluate();
					} finally {
						time = System.nanoTime() - time;
						long milliseconds = TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS) % 1000;
						long seconds = TimeUnit.SECONDS.convert(time, TimeUnit.NANOSECONDS) % 60;
						long minutes = TimeUnit.MINUTES.convert(time, TimeUnit.NANOSECONDS) % 60;
						long hours = TimeUnit.HOURS.convert(time, TimeUnit.NANOSECONDS) % 24;
						log.debug(methodName, String.format("Elapsed %d ns: %02d:%02d:%02d.%03d hh:mm:ss", time, hours, minutes, seconds, milliseconds));
					}
				}
			};
		}
	}
	/** Aggancio della regola */
	@Rule public TimingLoggingRule timingLoggingRule = new TimingLoggingRule();
	
	
	
	/**
	 * Crea un oggetto a partire da un file xml di test.
	 * 
	 * @param fileName ad esempio it/csi/siac/siacbilser/test/business/cassaeconomale/InserisceRichiestaEconomaleAnticipoSpese.xml 
	 * @return la request
	 * 
	 * @author Domenico
	 */
	public <T> T getTestFileObject(Class<T> reqClass, String fileName){
		byte[] byteArray;
		String fullFileName = "src/test/java/" + fileName;
		try {
			byteArray = getTestFileBytes(fullFileName);
		} catch (IOException e1) {
			e1.printStackTrace();
			fail("Impossibile ottenere il file di test: "+ fullFileName);
			return null;
		}
		String xml;
		try {
			xml = new String(byteArray, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			fail("Codifica UTF-8 non supporata. Impossile leggere il file di test: "+ fullFileName); //UTF-8 dovrebbe essere sempre supportato.
			return null;
		}
		
		return JAXBUtility.unmarshall(xml, reqClass);
	}
	
	protected Date parseDate(String str) {
		return parseDate(str, "dd/MM/yyyy");
	}
	
	protected Date parseDate(String str, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Illegal string (" + str + ") for format " + format, e);
		}
	}
	
	protected <R extends ServiceRequest> AsyncServiceRequestWrapper<R> wrapAsync(R req, int uidAzione) {
		AsyncServiceRequestWrapper<R> wrapper = new AsyncServiceRequestWrapper<R>();
		
		wrapper.setAccount(req.getRichiedente().getAccount());
		wrapper.setAnnoBilancio(req.getAnnoBilancio());
		wrapper.setDataOra(req.getDataOra());
		wrapper.setEnte(req.getRichiedente().getAccount().getEnte());
		wrapper.setRequest(req);
		wrapper.setRichiedente(req.getRichiedente());
		
		wrapper.setAzioneRichiesta(create(AzioneRichiesta.class, 0));
		wrapper.getAzioneRichiesta().setAzione(create(Azione.class, uidAzione));
		
		return wrapper;
	}

}
