package tp3_Activite3;

import java.text.Bidi;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

public class main {
	public static boolean isValidFormat(String format, String value) {
	    LocalDateTime ldt = null;
	    DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format);

	    try {
	        ldt = LocalDateTime.parse(value, fomatter);
	        String result = ldt.format(fomatter);
	        return result.equals(value);
	    } catch (DateTimeParseException e) {
	        try {
	            LocalDate ld = LocalDate.parse(value, fomatter);
	            String result = ld.format(fomatter);
	            return result.equals(value);
	        } catch (DateTimeParseException exp) {
	            try {
	                LocalTime lt = LocalTime.parse(value, fomatter);
	                String result = lt.format(fomatter);
	                return result.equals(value);
	            } catch (DateTimeParseException e2) {
		            }
	        }
	    }

	    return false;
	}
	public static int calculateAge(LocalDate dateN)   
	{  
		
		LocalDate today = LocalDate.now();  
		if ((dateN != null) && (today != null)) 
			return Period.between(dateN, today).getYears();  
		else return 0;  
	}  
	
	public static String findDay(LocalDate d1) {
			
		String day = d1.getDayOfWeek().getDisplayName(TextStyle.FULL,Locale.FRENCH);
		
		return day;
	}
	public static LocalDate currentAnniversary(LocalDate birthdayDate) {
		LocalDate today = LocalDate.now();  
        int currentYear = today.getYear();
        int month = birthdayDate.getMonthValue();
        int day = birthdayDate.getDayOfMonth();
        LocalDate currentAniversary = LocalDate.of(currentYear,month,day);
       
        return currentAniversary;
	}
	

	public static void main(String[] args) {
		String date = "";
		String patern= "dd/MM/yyyy";
		Scanner sc = new Scanner(System.in);  // Create a Scanner object
	    while(!isValidFormat(patern,date)) {
	    		System.out.println("Veuillez introduire une date valide avec le format "+patern);
	    		date = sc.next();
	    }
	    
	    System.out.println("Maintenant veuillez introduire votre date de naissance avec le format "+patern);
	    date = sc.next();
	    
	    while(!isValidFormat(patern,date)) {
    		System.out.println("Veuillez introduire une date de naissance avec le format "+patern);
    		date = sc.next();
	    }
	    System.out.println("\nLa date de ta naissance est : "+date);
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate dateN = LocalDate.parse(date, formatter);
	    System.out.println("\n");
        System.out.println("Le jour de naissance est : "+findDay(dateN));
        System.out.println("\n");
	    System.out.println("L'age est : "+calculateAge(dateN));
	    System.out.println("\n");
	    //R�cup�rer le format full de la date 
	    String formattedDate = dateN.format(DateTimeFormatter
	    	    .ofLocalizedDate(FormatStyle.FULL));

        System.out.println("Convertir la date en format jeudi 05 juillet 1962\nVoilà : "+formattedDate);
        System.out.println("\n");
        System.out.println("La jour d'anniversaire cette année est "+findDay(currentAnniversary(dateN)));
        
      //Day of week and month in Arabic
        
        //Locale arabicLocale = new Locale.Builder().setLanguageTag("ARABIC").build();
       // SimpleDateFormat arabicPattern = new SimpleDateFormat("yyyy dd MMM yyyy");

        //String arabicPattern = "EEEE dd MMM yyyy";
        
        Locale arabicLocale = new Locale.Builder().setLanguage("ar").setRegion("DZ")
                .setExtension(Locale.UNICODE_LOCALE_EXTENSION, "nu-arab").build();
        DateTimeFormatter formateTOArabic = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(arabicLocale);
       String dateInArabic = dateN.format(formateTOArabic);
       
       System.out.println("Date en arabe : "+dateInArabic);


	}
	}
