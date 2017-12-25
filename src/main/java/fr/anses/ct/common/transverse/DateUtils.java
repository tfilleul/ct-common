package fr.anses.ct.common.transverse;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;

/**
 * Classe utilitaire pour la manipulation des dates.
 * 
 * @author $Author: fstephan $
 * @version $Revision: 26 $
 */
public final class DateUtils {

  /** Message d'erreur lors de la verification de la nullité des params. */
  private static final String NULL_ERROR_PATTERN = "le paramètre '%s' est null";

  /** Format pour tout les traitement yyyy-MM-dd. */
  public static final String FORMAT_DATE = "yyyy-MM-dd";

  /** Format pour l'affichage du bas de page dd/MM/yyyy | HH:mm:ss. */
  public static final String FORMAT_DATE_BAS_PAGE = "dd/MM/yyyy HH:mm:ss";

  /** Format pour la base de données yyyyMMddHHmmss. */
  public static final String FORMAT_DATE_BASE_DE_DONNEES = "yyyyMMddHHmmss";

  /** Format pour tout les traitement yyyy-MM-dd'T'HH:mm:ss,SZ. */
  public static final String FORMAT_ISO_DATE = "yyyy-MM-dd'T'HH:mm:ss,SZ";

  /** Format pour la base de données yyyy-MM-dd HH:mm:ss.SSS. */
  public static final String FORMAT_DATE_BASE_DE_DONNEES_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

  /** Format pour tout les traitement yyyy-MM-dd HH:mm:ss. */
  public static final String FORMAT_ISO_DATE_RACVISION = "yyyy-MM-dd HH:mm:ss";

  /** Format pour l'affichage uniquement dd/MM/yyyy. */
  public static final String FORMAT_AFFICHAGE_DATE = "dd-MM-yyyy";

  /** Format pour l'affichage année + mois (AAMM). */
  public static final String FORMAT_AAMM = "yyMM";

  /** Format de l'année sur 4 chiffres. */
  public static final String FORMAT_AAAA = "yyyy";

  /** Format utilisé pour la date de naissance. */
  public static final String FORMAT_DATE_AAAAMMJJ = "yyyyMMdd";

  /** Format jjmmaaaa. */
  public static final String FORMAT_DATE_JJMMAAAA = "ddMMyyyy";

  /** CALENDAR_ARG. */
  public static final String CALENDAR_ARG = "calendar";

  /** C1_ARG. */
  public static final String C1_ARG = "c1";

  /** C2_ARG. */
  public static final String C2_ARG = "c2";

  /** FORMAT_ARG. */
  public static final String FORMAT_ARG = "format";

  /** DATE_ARG. */
  public static final String DATE_ARG = "date";

  /** 31. */
  private static final int TRENTE_ET_UN = 31;

  /**
   * Constructeur de la classe.
   */
  private DateUtils() {
    // Constructeur vide.
  }

  /**
   * Retourne la date courante <b>à la Time Zone du Système</b>.
   * 
   * 
   * @return la date courante <b>à la Time Zone du Système</b>
   */
  public static Calendar getDateCourante() {
    return Calendar.getInstance();
  }

  /**
   * Transforme en chaine de caractère la date passée en paramètre.
   * 
   * @param calendar
   *          calendrier qui représente la date : ne doit pas être null
   * @param format
   *          chaine de caractère représentant le format de sortie (ex: ddMMyyyy) : ne doit pas être
   *          null
   * @return la chaine de caractère représentant la date en paramètre
   */
  public static String toString(final Calendar calendar, final String format) {
    return new DateTime(notNull(calendar, NULL_ERROR_PATTERN, CALENDAR_ARG).getTime()).toString(notNull(format,
      NULL_ERROR_PATTERN, FORMAT_ARG));
  }

  /**
   * Convertie la date passe en parametre sous la forme ISO_DATE_RACVISION : yyyy-MM-dd hh:mm:ss.
   * 
   * @param cal
   *          la date
   * @return yyyy-MM-dd HH:mm:ss
   */
  public static String toStringISODateRacvision(final Calendar cal) {
    return toString(cal, FORMAT_ISO_DATE_RACVISION);
  }

  /**
   * <B>Uniquement pour l'affichage</B> Convertie la date passe en parametre sous la forme
   * JJ/MM/AAAA.
   * 
   * @param cal
   *          la date à convertir au format JJ-MM-AAAA
   * @return JJ-MM-AAAA
   */
  public static String toStringJJMMAAAA(final Calendar cal) {
    return toString(cal, FORMAT_AFFICHAGE_DATE);
  }

  /**
   * Convertit la date passé en paramètre au format AAMM.
   * 
   * @param calendar
   *          la date à convertir au format AAMM
   * @return l'année et le mois au format AAMM
   */
  public static String toStringAAMM(final Calendar calendar) {
    return toString(calendar, FORMAT_AAMM);
  }

  /**
   * <B>Uniquement pour l'affichage</B> Convertie une chaine JJ-MM-AAAA en la date.
   * 
   * @param jjmmaaaa
   *          la date au format JJ-MM-AAAA à convertir en <code>Calendar</code>
   * @return Calendar
   */
  public static Calendar toCalendarFormatAffichageDate(final String jjmmaaaa) {
    return toCalendar(jjmmaaaa, FORMAT_AFFICHAGE_DATE);
  }

  /**
   * Convertie une chaine de caractère en objet calendar en fonction du format passé en paramètre.
   * 
   * @param date
   *          date
   * @param format
   *          format de tranformation
   * @return calendar
   */
  public static Calendar toCalendar(final String date, final String format) {

    return DateTimeFormat.forPattern(notNull(format, NULL_ERROR_PATTERN, FORMAT_ARG))
      .parseDateTime(notNull(date, NULL_ERROR_PATTERN, DATE_ARG)).toCalendar(Locale.getDefault());
  }

  /**
   * Methode qui permet de transformer une année, mois et jour en un calendar correspondant.
   * 
   * @param annee
   *          obligatoire, doit être un numérique
   * @param mois
   *          non obligatoire, si valorisé doit être un numérique. Si non saisi, équivalent à 1
   * @param jour
   *          non obligatoire, si valorisé doit être un numérique. Si non saisi, équivalent à 1
   * @return le calendrier représentant le jour.
   */
  public static Calendar toCalendar(final String annee, final String mois, final String jour) {

    isTrue(isNumber(annee), "L'année doit être un numérique : %s", annee);
    isTrue(isBlank(mois) || isNumber(mois), "Le mois doit être un numérique : %s", mois);
    isTrue(isBlank(jour) || isNumber(jour), "Le jour doit être un numérique : %s", jour);

    int year = Integer.valueOf(annee);
    int month = isBlank(mois) || Integer.valueOf(mois) == 0 ? 1 : Integer.valueOf(mois);
    int day = isBlank(jour) || Integer.valueOf(jour) == 0 ? 1 : Integer.valueOf(jour);

    final LocalDate date = new LocalDate(year, month, day);
    return date.toDateTimeAtStartOfDay().toCalendar(Locale.getDefault());
  }

  /**
   * Converti la date sous le format yyyy-MM-dd HH:mm:ss.SSSSSS.
   * 
   * @param calendar
   *          la date à convertir sous le format yyyy-MM-dd HH:mm:ss.SSSSSS
   * @return la date au format yyyy-MM-dd HH:mm:ss.SSSSSS
   */
  public static String toStringTimeStamp(final Calendar calendar) {
    return toString(calendar, FORMAT_DATE_BASE_DE_DONNEES_TIMESTAMP);
  }

  /**
   * Retourne l'année de la date passée en paramètre.
   * 
   * @param cal
   *          date
   * @return année de la date passé en paramètre sous forme de chaine de caractère
   */
  public static String toStringAAAA(final Calendar cal) {
    return toString(cal, FORMAT_AAAA);
  }

  /**
   * La date au premier Janvier de l'année passée/futur à 00:00 (reset de l'heure).
   * 
   * @param annee
   *          l'année auquelle il faut calculer la date du 1er janvier
   * @return le <code>Calendar</code> qui représente le 1er Janvier de l'année <code>annee</code>
   */
  public static Calendar get1erJanvier(final int annee) {
    Calendar c = getDateCourante();
    c.set(Calendar.MONTH, Calendar.JANUARY);
    c.set(Calendar.DAY_OF_MONTH, 1);
    c.set(Calendar.HOUR, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);
    c.clear(Calendar.AM_PM);
    c.clear(Calendar.HOUR_OF_DAY);
    c.set(Calendar.YEAR, annee);
    return c;
  }

  /**
   * La date au 31 Decembre de l'année passée/futur.
   * 
   * @param annee
   *          l'année auquelle il faut calculer la date du 31 décembre
   * @return le <code>Calendar</code> qui représente le 31 décembre de l'année <code>annee</code>
   */
  public static Calendar get31Decembre(final int annee) {
    Calendar c = getDateCourante();
    c.set(Calendar.MONTH, Calendar.DECEMBER);
    c.set(Calendar.DAY_OF_MONTH, TRENTE_ET_UN);
    c.set(Calendar.HOUR, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);
    c.clear(Calendar.AM_PM);
    c.clear(Calendar.HOUR_OF_DAY);
    c.set(Calendar.YEAR, annee);
    return c;
  }

  /**
   * Mise à 00:00 de la date passé (reset de l'heure).
   * 
   * @param date
   *          Calendar a mettre à 00:00
   */
  public static void miseAZeroHeure(final Calendar date) {
    notNull(date, NULL_ERROR_PATTERN, DATE_ARG);
    date.set(Calendar.HOUR, 0);
    date.set(Calendar.MINUTE, 0);
    date.set(Calendar.SECOND, 0);
    date.set(Calendar.MILLISECOND, 0);
  }

  /**
   * Wrapper after pour les calendar avec une précision JOUR.
   * 
   * @param c1
   *          Calendar 1
   * @param c2
   *          Calendar 2
   * @return idem que pour la methode after
   * @see java.util.Calendar#after(Object)
   * @since 1.0.7
   */
  public static boolean afterPrecisionJour(final Calendar c1, final Calendar c2) {
    final DateTime d1 = new DateTime(notNull(c1, NULL_ERROR_PATTERN, C1_ARG));
    final DateTime d2 = new DateTime(notNull(c2, NULL_ERROR_PATTERN, C2_ARG));
    return d1.toLocalDate().isAfter(d2.toLocalDate());
  }

  /**
   * Wrapper before pour les calendar avec une précision JOUR.
   * 
   * @param c1
   *          Calendar 1
   * @param c2
   *          Calendar 2
   * @return idem que pour la methode after
   * @see java.util.Calendar#before(Object)
   * @since 1.0.7
   */
  public static boolean beforePrecisionJour(final Calendar c1, final Calendar c2) {
    final DateTime d1 = new DateTime(notNull(c1, NULL_ERROR_PATTERN, C1_ARG));
    final DateTime d2 = new DateTime(notNull(c2, NULL_ERROR_PATTERN, C2_ARG));
    return d1.toLocalDate().isBefore(d2.toLocalDate());
  }

  /**
   * Wrapper compareTo pour les calendar avec une précision JOUR.
   * 
   * @param c1
   *          Calendar 1
   * @param c2
   *          Calendar 2
   * @return idem que pour la methode after
   * @see java.util.Calendar#compareTo(Object)
   * @since 1.0.7
   */
  public static int compareToPrecisionJour(final Calendar c1, final Calendar c2) {
    final DateTime d1 = new DateTime(notNull(c1, NULL_ERROR_PATTERN, C1_ARG));
    final DateTime d2 = new DateTime(notNull(c2, NULL_ERROR_PATTERN, C2_ARG));
    return d1.toLocalDate().compareTo(d2.toLocalDate());
  }

  /**
   * Convertit la date <code>cal</code> sous la forme {@link #FORMAT_DATE}.
   * 
   * @param cal
   *          le calendar à convertir
   * @return la chaine au format {@link #FORMAT_DATE}, <code>null</code> sinon
   */
  public static String toStringAAAAMMJJNullSafe(final Calendar cal) {
    if (cal == null) {
      return null;
    }
    return toString(cal, FORMAT_DATE);
  }

  /**
   * Convertit la chaine <code>aaaammjj</code> au format {@link #FORMAT_DATE} en la date
   * correspondante.
   * 
   * @param aaaammjj
   *          la date au format {@link #FORMAT_DATE}
   * @return l'objet <code>java.util.Calendar</code> correspondant à la chaine <code>aaaammjj</code>
   *         , <code>null</code> sinon
   */
  public static Calendar toCalendarFormatISO(final String aaaammjj) {
    if (aaaammjj == null) {
      return null;
    }
    return toCalendar(aaaammjj, FORMAT_DATE);
  }

  /**
   * Convertit la chaine <code>yyyyMMddHHmmss</code> au format {@link #FORMAT_DATE_BASE_DE_DONNEES}
   * en la date correspondante.
   * 
   * @param yyyyMMddHHmmss
   *          la date au format {@link #FORMAT_DATE_BASE_DE_DONNEES}
   * @return l'objet <code>java.util.Calendar</code> correspondant à la chaine
   *         <code>yyyyMMddHHmmss</code>
   */
  public static Calendar toCalendarFormatyyyyMMddHHmmss(final String yyyyMMddHHmmss) {
    return toCalendar(yyyyMMddHHmmss, FORMAT_DATE_BASE_DE_DONNEES);
  }

  /**
   * Convertit la chaine <code>yyyyMMdd</code> au format {@link #FORMAT_DATE_AAAAMMJJ} en la date
   * correspondante.
   * 
   * @param yyyyMMdd
   *          la date au format {@link #FORMAT_DATE_AAAAMMJJ}
   * @return l'objet <code>java.util.Calendar</code> correspondant à la chaine <code>yyyyMMdd</code>
   */
  public static Calendar toCalendarFormatyyyyMMdd(final String yyyyMMdd) {
    return toCalendar(yyyyMMdd, FORMAT_DATE_AAAAMMJJ);
  }

  /**
   * Convertit la chaine <code>yyyyMMdd</code> au format {@link #FORMAT_DATE_JJMMAAAA} en la date
   * correspondante.
   * 
   * @param ddMMyyyy
   *          la date au format {@link #FORMAT_DATE_JJMMAAAA}
   * @return l'objet <code>java.util.Calendar</code> correspondant à la chaine <code>yyyyMMdd</code>
   */
  public static Calendar toCalendarFormatddMMyyyy(final String ddMMyyyy) {
    return toCalendar(ddMMyyyy, FORMAT_DATE_JJMMAAAA);
  }

  /**
   * Convertie une chaine issue d'un header HTTP en Calendar.
   * 
   * @param httpHeaderDateString
   *          La date à convertir
   * @return la date au format Calendar
   */
  // public static Calendar getHTTPHeaderCalendar(final String httpHeaderDateString) {
  // notEmpty(httpHeaderDateString, NULL_ERROR_PATTERN, "httpHeaderDateString");
  // Calendar calendar = getDateCourante();
  // try {
  // calendar.setTime(org.apache.http.impl.cookie.DateUtils.parseDate(httpHeaderDateString));
  // return calendar;
  // } catch (DateParseException e) {
  // throw new IllegalArgumentException(e);
  // }
  // }

  /**
   * Retourne une chaine sous la forme {@link #FORMAT_DATE_BASE_DE_DONNEES} représentant le
   * <code>calendar</code>.
   * 
   * @param calendar
   *          le calendar à convertir
   * @return la chaine sous la forme {@link #FORMAT_DATE_BASE_DE_DONNEES} représentant le
   *         <code>calendar</code>
   */
  public static String toStringyyyyMMddHHmmss(final Calendar calendar) {
    return toString(notNull(calendar, NULL_ERROR_PATTERN, CALENDAR_ARG), FORMAT_DATE_BASE_DE_DONNEES);
  }

  /**
   * Retourne une chaine sous la forme {@link #FORMAT_DATE_AAAAMMJJ} représentant le
   * <code>calendar</code>.
   * 
   * @param calendar
   *          le calendar à convertir
   * @return la chaine sous la forme {@link #FORMAT_DATE_AAAAMMJJ} représentant le
   *         <code>calendar</code>
   */
  public static String toStringyyyyMMdd(final Calendar calendar) {
    return toString(notNull(calendar, NULL_ERROR_PATTERN, CALENDAR_ARG), FORMAT_DATE_AAAAMMJJ);
  }

  /**
   * Convertit la date <code>cal</code> sous la forme {@link #FORMAT_ISO_DATE}.
   * 
   * @param cal
   *          le calendar à convertir
   * @return la chaine au format {@link #FORMAT_ISO_DATE}, <code>null</code> sinon
   */
  public static String toStringISONullSafe(final Calendar cal) {
    if (cal == null) {
      return null;
    }
    return toString(cal, FORMAT_ISO_DATE);
  }

  /**
   * Calcule le nombre de jours entre deux dates données.
   * 
   * @param debutPeriode
   *          début période
   * @param finPeriode
   *          fin période
   * @return nombre de jours entre les deux dates
   */
  public static long nbJoursEcart(final Calendar debutPeriode, final Calendar finPeriode) {
    final LocalDate debut = new DateTime(notNull(debutPeriode, NULL_ERROR_PATTERN, "debutPeriode")).toLocalDate();
    final LocalDate fin = new DateTime(notNull(finPeriode, NULL_ERROR_PATTERN, "finPeriode")).toLocalDate();
    return Days.daysBetween(debut, fin).getDays();
  }

  
  /**
   * Vérifie si l'ecart entre les 2 dates est supérieur au nombre de minutes passées en paramètre
   * 
   * @param debutPeriode
   *          début période
   * @param finPeriode
   *          fin période
   * @return nombre de jours entre les deux dates
   */
  public static boolean isEcartSuperieiurMinute(final Calendar debutPeriode, final Calendar finPeriode, int minutes) {
	  long diffMs = finPeriode.getTimeInMillis() - finPeriode.getTimeInMillis();
	  long diffMin = diffMs/60000 ;
	  return (diffMin > minutes);
  }
  
  /**
   * Implémentation de la méthode qui teste l'égalité de Date ou de Calendar.
   * 
   * @param premiereDate
   *          date ou calendar
   * @param secondeDate
   *          date ou calendar
   * @return true si les deux sont égales en terme de jour.
   */
  public static boolean egaliteDateJourMoisAn(final Object premiereDate, final Object secondeDate) {
    boolean res = false;
    if (premiereDate == null && secondeDate == null) {
      res = true;
    } else if (premiereDate == null && secondeDate != null) {
      res = false;
    } else if (premiereDate != null && secondeDate == null) {
      res = false;
    } else {
      final LocalDate d1 = new DateTime(premiereDate).toLocalDate();
      final LocalDate d2 = new DateTime(secondeDate).toLocalDate();
      res = d1.isEqual(d2);
    }
    return res;
  }

  /**
   * Methode permettant de vérifier que deux Calendar possèdent la même valeur timeInMillis.
   * 
   * @param premiereDate
   *          Calendar a comparer
   * @param secondeDate
   *          Calendar a comparer
   * @return true si les deux dates ont le meme valeure de timeInMillis
   */
  public static boolean egaliteDateTimeInMillis(final Calendar premiereDate, final Calendar secondeDate) {
    return notNull(premiereDate, NULL_ERROR_PATTERN, "premiereDate").getTimeInMillis() == notNull(secondeDate,
      NULL_ERROR_PATTERN, "secondeDate").getTimeInMillis();
  }

  /**
   * Verifie que les dates sont espacées d'au moins un certain nombre d'années. <br/>
   * 
   * @param date1
   *          date1
   * @param date2
   *          date2
   * @param annees
   *          nombre d'années
   * @return vrai si date2 >= date1 + annees + 1j
   */
  public static boolean possedeDelais(final Date date1, final Date date2, final int annees) {
    final LocalDate d1 = new DateTime(notNull(date1, NULL_ERROR_PATTERN, "date1")).toLocalDate();
    final LocalDate d2 = new DateTime(notNull(date2, NULL_ERROR_PATTERN, "date2")).toLocalDate();
    return d1.plusYears(annees).compareTo(d2) < 0;
  }
  
  

  /**
   * Retourne le nombre d'années pleines d'écart entre 2 dates.
   * 
   * @param date1
   *          date 1 (null = date système)
   * @param date2
   *          date 2 (null = date système)
   * @return nombre d'années positif si date2 > date1, un nb d'annèe nègatif si l'inverse et 0 si
   *         les dates sont dans la même année.
   */
  public static int getAnneesDiff(final Calendar date1, final Calendar date2) {
    return Years.yearsBetween(new DateTime(date1).toLocalDate(), new DateTime(date2).toLocalDate()).getYears();
  }

  
  /**
   * Transforme une date en calendar (null safe).
   * 
   * @param date
   *          date
   * @return calendar
   */
  public static Calendar dateToCalendar(final Date date) {
    if (date == null) {
      return null;
    }

    Calendar instance = getDateCourante();
    instance.setTime(date);
    return instance;
  }

  /**
   * Transforme un calendar en date (null safe).
   * 
   * @param calendar
   *          calendar
   * @return date
   */
  public static Date calendarToDate(final Calendar calendar) {
    if (calendar == null) {
      return null;
    }
    return calendar.getTime();
  }

  /**
   * Clone un calendar (null safe).
   * 
   * @param calendar
   *          Le calendar à copier
   * @return Le calendar cloné
   */
  public static Calendar cloneCalendar(final Calendar calendar) {
    if (calendar == null) {
      return null;
    }
    return (Calendar) calendar.clone();
  }

}

