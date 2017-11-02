package entities;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageSelector {

	//Variables
	private String country;
	private String language;
	
	//Constructor
	public LanguageSelector(String language, String country) {
		this.language = language;
		this.country = country;
	}
	//
	public ResourceBundle selectLanguage(String language, String country) {

		Locale currentLocale;
		ResourceBundle messages;

		currentLocale = new Locale(language, country);
		messages = ResourceBundle.getBundle("MessageBundle", currentLocale);
		
		return messages;
	}
	
	public ResourceBundle selectLanguage() {
		Locale currentLocale;
		ResourceBundle messages;

		currentLocale = new Locale(language, country);
		messages = ResourceBundle.getBundle("MessageBundle", currentLocale);
		
		return messages;
	}
}