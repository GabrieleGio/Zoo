package com.zoo;

import java.util.Scanner;

import com.zoo.entity.Dipendente;
import com.zoo.entity.Ruolo;
import com.zoo.service.DipendenteService;
import com.zoo.service.AnimaleService;
import com.zoo.service.ZonaService;
import com.zoo.service.DipendenteAnimaleService;
public class Main {
	private static Dipendente loggedInUser = null;
	private static final Scanner scanner = new Scanner(System.in);
	   
    private static DipendenteService dipendenteService = new DipendenteService();
    private static AnimaleService animaleService = new AnimaleService();
    private static ZonaService zonaService = new ZonaService();
    private static DipendenteAnimaleService dipendenteAnimaleService = new DipendenteAnimaleService();
    
	public static void main(String[] args) {
		boolean running = true;
        while (running) {
            if (loggedInUser == null) {
                showAuthMenu();
            } else {
                showAppMenu();
            }
        }
    }
	
	 private static void showAuthMenu() {
	        System.out.println("\nRegistrati o effettua l'accesso!\n");
	        System.out.println("1. Registrazione");
	        System.out.println("2. Login");
	        System.out.println("3. Esci");
	        System.out.print("\nScegli un'opzione: ");

	        String choice = scanner.nextLine();
	        switch (choice) {
	            case "1":
	                register();
	                break;
	            case "2":
	                login();
	                break;
	            case "3":
	                System.out.println("Grazie per aver usato l'app. Arrivederci!");
	                System.exit(0);
	                break;
	            default:
	                System.out.println("Opzione non valida. Riprova.");
	        }
	    }
	 
	 private static void showAppMenu() {
	        System.out.println("\nMenu Principale - " + loggedInUser.getNome());
	        System.out.println();
	        System.out.println("1. Da implementare");
	        System.out.print("\nScegli un'opzione: ");

	        String choice = scanner.nextLine();
	        switch (choice) {
	        
	            case "1":
	                break;
	                
	            default:
	                System.out.println("Opzione non valida. Riprova.");
	        }
	    }
	 
	 //Servizi di autenticazione
	 private static void register() {
	        System.out.print("Inserisci nome: ");
	        String nomeReg = scanner.nextLine().trim();
	        System.out.print("Inserisci cognome: ");
	        String cognomeReg = scanner.nextLine().trim();
	        System.out.println("Inserisci username: ");
	        String usernameReg = scanner.nextLine().trim();
	        System.out.print("Inserisci email: ");
	        String emailReg = scanner.nextLine().trim();
	        System.out.print("Inserisci password: ");
	        String passwordReg = scanner.nextLine().trim();

	        if (nomeReg.isEmpty() || cognomeReg.isEmpty() || emailReg.isEmpty() || passwordReg.isEmpty()) {
	            System.out.println("Errore: Tutti i campi sono obbligatori e non possono essere vuoti.");
	            return;
	        }
	        
	        if (nomeReg.length() > 25) {
	            System.out.println("Registrazione annullata: Nome non conforme (max 25 caratteri).");
	            return;
	        }
	        
	        if (cognomeReg.length() > 25) {
	            System.out.println("Registrazione annullata: Cognome non conforme (max 25 caratteri).");
	            return;
	        }
	        
	        if (usernameReg.length() > 25) {
	        	System.out.println("Registrazione annullata: Username non conforme (max 25 caratteri).");
	        }

	        if (!emailReg.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
	            System.out.println("Registrazione annullata: L'indirizzo email fornito non è valido.");
	            return;
	        }
	        
	        if (passwordReg.length() < 8) {
	            System.out.println("Registrazione annullata: La password deve contenere almeno 8 caratteri.");
	            return;
	        }
	        
	        Dipendente dipendente = dipendenteService.trovaPerEmail(emailReg);
	        if (dipendente != null) {
	        	System.out.println("Esiste già un utente con questa email.");
	        	return;
	        }
	        //Da rifattorizzare
	        Dipendente dipendente2 = dipendenteService.trovaPerUsername(usernameReg);
	        if (dipendente2 != null) {
	        	System.out.println("Esiste già un utente con questo username.");
	        	return;
	        }

	        Dipendente newUser = new Dipendente();
	        newUser.setNome(nomeReg);
	        newUser.setCognome(cognomeReg);
	        newUser.setUsername(usernameReg);
	        newUser.setEmail(emailReg);
	        newUser.setPassword(passwordReg); 
	        newUser.setRuolo(Ruolo.IN_ATTESA);

	        dipendenteService.creaDipendente(newUser);

	        System.out.println("Registrazione completata con successo! Ora puoi effettuare il login.");
	    }
	 
	 private static void login() {
	        System.out.print("Inserisci username: ");
	        String username = scanner.nextLine().trim();
	        System.out.print("Inserisci password: ");
	        String password = scanner.nextLine().trim();

	        Dipendente dipendente = dipendenteService.trovaPerUsername(username);

	        if (dipendente == null) {
	            System.out.println("Login fallito: L'username o la password sono errate.");
	            return;
	        }
	        
	        if (dipendente.getPassword().equals(password)) {
	        	//TODO Aggiungere qui un controllo per vedere se il ruolo è 'bloccato'
	        	
	            loggedInUser = dipendente;
	            System.out.println("Login effettuato con successo! Benvenuto, " + loggedInUser.getNome() + ".");
	        } else {
	            System.out.println("Login fallito: L'username o la password sono errate.");
	        }
	    }

	    private static void logout() {
	        loggedInUser = null;
	        System.out.println("Logout effettuato.");
	    }

}
