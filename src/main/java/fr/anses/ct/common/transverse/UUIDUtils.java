package fr.anses.ct.common.transverse;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Classe utilitaire de génération d'UUID.
 * 
 */
public final class UUIDUtils {

  /** Taille du byte. */
  private static final int BYTE_SIZE = 16;

  /** Taille du byte / 2. */
  private static final int HUIT = 8;

  /** Notation hexadécimale. */
  private static final long NOTATION_HEXA = 0xff;

  /**
   * Constructeur de la classe.
   */
  private UUIDUtils() {
    // Constructeur vide
  }

  /**
   * Génération d'un tableau de 16 octets correspondant à un UUID généré aléatoirement.
   * 
   * @return byte[] UUID généré aléatoirement
   */
  public static byte[] genererRandom16ByteUuid() {
    UUID uuid = UUID.randomUUID();
    ByteBuffer bb = ByteBuffer.wrap(new byte[BYTE_SIZE]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
  }

  /**
   * Génération d'un UUID au format String à partir d'un tableau de byte.
   * 
   * @param byteArray
   *          liste de byte
   * @return retourne un UUID au format String
   */
  public static String genererFrom16ByteString(final byte[] byteArray) {
    long msb = 0;
    long lsb = 0;
    for (int i = 0; i < HUIT; i++) {
      msb = (msb << HUIT) | (byteArray[i] & NOTATION_HEXA);
    }
    for (int i = HUIT; i < BYTE_SIZE; i++) {
      lsb = (lsb << HUIT) | (byteArray[i] & NOTATION_HEXA);
    }
    UUID result = new UUID(msb, lsb);

    return result.toString();
  }
  
  /**
   * Génére un UUID aléatoire
   * @return Un UID au format String
   */
  public static String genererRandomUuid()
  {
	  return genererFrom16ByteString(genererRandom16ByteUuid());
  }
}
