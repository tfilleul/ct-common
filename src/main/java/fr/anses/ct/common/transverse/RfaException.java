package fr.anses.ct.common.transverse;

import java.io.Serializable;

public class RfaException extends RuntimeException implements Serializable{
	 /**
	   * Identifiant de l'exception
	   */
	  private byte[] uuid;
	  /**
	   * UUID pour la sérialisation.
	   */
	  private static final long serialVersionUID = 8010050913147582331L;
	  
	  /**
	   * message de l'exception
	   */
	  protected String message;
	  
	  /**
	   * code de l'exception
	   */
	  protected String code;
	  
	  /**
	   * sévérité de l'exception
	   */
	  protected String severite;
	  
	  /**
	   * Constructeur de la classe
	   */
	  public RfaException() {
	    super();
	    uuid = UUIDUtils.genererRandom16ByteUuid();
	  }
	  
	  /**
	   * Constructeur de la classe
	   * @param cause
	   *          Cause de l'erreur
	   */
	  public RfaException(Throwable cause) {
	    super(cause);
	    uuid = UUIDUtils.genererRandom16ByteUuid();
	  }
	  
	  public RfaException(String message) {
		  super(message);
		  this.message = message;
		  uuid = UUIDUtils.genererRandom16ByteUuid();
	}

	  public RfaException(String message, Throwable cause) {
		  super(message, cause);
		  this.message = message;
		  uuid = UUIDUtils.genererRandom16ByteUuid();
	}
	  
	/**
	   * Accesseur sur l'attribut uuid
	   * @return byte[] uuid
	   */
	  public byte[] getUuid() {
	    return uuid;
	  }
	  
	  /**
	   * Mutateur sur l'attribut uuid
	   * @param uuidIn
	   *          l'attribut uuid à modifier
	   */
	  public void setUuid(
	    byte[] uuidIn) {
	    if (uuidIn == null) {
	      this.uuid = null;
	    } else {
	      this.uuid = uuidIn.clone();
	    }
	  }
	  
	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public String getMessage() {
	    return message;
	  }
	  
	  /**
	   * {@inheritDoc}
	   */
	  public void setMessage(
	    String message) {
	    this.message = message;
	  }
	  
	  /**
	   * {@inheritDoc}
	   */
	  public String getCode() {
	    return code;
	  }
	  
	  /**
	   * {@inheritDoc}
	   */
	  public void setCode(
	    String code) {
	    this.code = code;
	  }
	  
	  /**
	   * {@inheritDoc}
	   */
	  public String getSeverite() {
	    return severite;
	  }
	  
	  /**
	   * {@inheritDoc}
	   */
	  public void setSeverite(
	    String severite) {
	    this.severite = severite;
	  }

}
