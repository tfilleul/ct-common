package fr.anses.ct.common.transverse;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Classe utilitaire pour la manipulation des dates.
 * 
 * @author $Author: fstephan $
 * @version $Revision: 26 $
 */
public final class LdapUtils {
	
	/** Le logger. */
	  private static final Logger LOGGER = LoggerFactory.getLogger(LdapUtils.class);
	  


  /**
   * dn est supposé etre de la forme : cn=test6,ou=test unitaire,ou=people,dc=afssa,dc=fr
   * Découpé la chaine pour renvoyé ou=test unitaire,ou=people,dc=afssa,dc=fr
   * @param dn String
   * @return ou String
   */
  public static String getOuFromDn(final String dn) {
	  int indexFirstVirgule = dn.indexOf(",");
	  String retStr = dn.substring(indexFirstVirgule+1);
	  return retStr;
  }
  
  /**
   * dn est supposé etre de la forme : cn=test6,ou=test unitaire,ou=people,dc=afssa,dc=fr
   * Découpé la chaine pour renvoyé test6
   * @param dn String
   * @return ou String
   */
  public static String getNameFromDn(final String dn) {
	try {
		String[] tabString1 = dn.split(",");
		String[] tabString2 = tabString1[0].split("=");
		return tabString2[1];
		}
		catch (Exception e){
			LOGGER.warn("Impossible de découper : " + dn);
		}
		return null;
  }

  /**
   * Rrenvoie la liste des unités
   * @param obj
   * @return
   */
  public static List<String> getUnits(Object obj){
	  String[] tabString = null;
  try {
	  // MemberOf : attribut multi evalué
	  tabString = (String[]) obj;
  	}
	  catch (ClassCastException cce){
			 LOGGER.warn("Impossible de lire l'attribut memberof pour calculer l'unité");
			 return null;
	}
	  
  if (tabString != null){
	  List<String> listStr = new ArrayList<String>();
	  int lg = tabString.length;
	  for (int i=0;i<lg;i++) {
		  listStr.add(tabString[i]);
	 }
	  return listStr;
  }
  	
  return null;
  }

}

