package fr.anses.ct.common.transverse;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;




/**
 * Factory des exceptions.
 */
public final class ExceptionFactory {

  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionFactory.class);

  /** Source des messages de l'application. */
  private MessageSource messageSource;

  /**
   * Throw une exception depuis un code (message sans argument)
   * @param code
   * @throws RfaException
   */
  public void throwRfaException(final String code) throws RfaException {
	  throwRfaException(code, (String[]) null, null);
  }

  /**
   * Throw une exception depuis un code et un tableau d'arguments (message avec plusieurs arguments)
   * @param code
   * @param args
   * @throws RfaException
   */
  public void throwRfaException(final String code, final String[] args) throws RfaException {
	  throwRfaException(code, args, null);
  }

  /**
   * Throw une exception depuis un code et un argument (message avec 1 seul argument)
   * @param code
   * @param arg
   * @throws RfaException
   */
  public void throwRfaException(final String code, final String arg) throws RfaException {
	  throwRfaException(code, new String[] {arg }, null);
  }

  /**
   * Throw une exception depuis un code (message sans argument) et une cause
   * @param code
   * @param cause
   * @throws RfaException
   */
  public void throwRfaException(final String code, final Throwable cause) throws RfaException {
	  throwRfaException(code, (String[]) null, cause);
  }

  /**
   * Throw une exception depuis un code, un argument (message avec 1 seul argument) et une cause
   * @param code
   * @param arg
   * @param ex
   * @throws RfaException
   */
  public void throwRfaException(final String code, final String arg, final Throwable ex) throws RfaException {
	  throwRfaException(code, new String[] {arg }, ex);
  }

  /**
   * Throw une exception depuis un code et un tableau d'arguments (message avec plusieurs arguments) et une cause
   * @param code
   * @param args
   * @param cause
   * @throws RfaException
   */
  public void throwRfaException(final String code, final String[] args, final Throwable cause)
    throws RfaException {
    String message = logMessage(code, args);
    throw getRfaException(code, message, cause);
  }

    /**
   * Retourne une exception depuis l'exception <code>ex</code>avec le code
   * <code>code</code> et le message <code>message</code>.
   * 
   * @param code
   *          le code de l'exception
   * @param message
   *          le message de l'exception
   * @param ex
   *          l'exception d'origine (nullable)
   * @return une exception depuis l'exception <code>e</code>avec le code
   *         <code>code</code> et le message <code>message</code>
   */
  public RfaException getRfaException(final String code, final String message, final Throwable ex) {
    RfaException ret = null;
    if (ex == null) {
      ret = new RfaException(message);
    } else {
      ret = new RfaException(message, ex);
    }
    ret.setCode(code);
    return ret;
  }

  /**
   * Trace en niveau ERROR le message qui a le code <code>code</code>.
   * 
   * @param code
   *          le code du message à tracer
   * @param args
   *          les valeurs des arguments du message
   * @return le message tracé
   */
  public String logMessage(final String code, final String[] args) {
    String message = getMessage(code, args);
    LOGGER.error("[" + code + "] " + message);
    return message;
  }

  /**
   * Retourne le message qui a le code <code>code</code> en remplaçant les variables du messages par
   * les valeurs du paramètre <code>args</code>.
   * 
   * @param code
   *          le code du message à retourner
   * @param args
   *          les valeurs des arguments du message
   * @return le message qui a le code <code>code</code> en remplaçant les variables du messages par
   *         les valeurs du paramètre <code>args</code>
   */
  public String getMessage(final String code, final String[] args) {
    return messageSource.getMessage(code, args, "Libelle erreur introuvable", new Locale(""));
  }

  /**
   * Accesseur en lecture de l'attribut <code>messageSource</code>.
   * 
   * @return MessageSource L'attribut messageSource à lire.
   */
  public MessageSource getMessageSource() {
    return messageSource;
  }

  /**
   * Accesseur en écriture de l'attribut <code>messageSource</code>.
   * 
   * @param messageSource
   *          L'attribut messageSource à modifier.
   */
  public void setMessageSource(final MessageSource messageSource) {
    this.messageSource = messageSource;
  }

}
