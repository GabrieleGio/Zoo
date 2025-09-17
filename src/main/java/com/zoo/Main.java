package com.zoo;

import java.util.List;
import java.util.Scanner;

import com.zoo.entity.Animale;
import com.zoo.entity.Dipendente;
import com.zoo.entity.DipendenteAnimale;
import com.zoo.entity.Habitat;
import com.zoo.entity.Ruolo;
import com.zoo.entity.Zona;
import com.zoo.service.DipendenteService;
import com.zoo.service.AnimaleService;
import com.zoo.service.ZonaService;
import com.zoo.utils.PasswordHashing;
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
		    } else if (loggedInUser.getRuolo() == Ruolo.ADMIN) {
		        showAdminMenu();
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
		System.out.println("1. Gestione Animali");
		System.out.println("2. Gestione Zone");
		System.out.println("3. Logout");
		System.out.print("\nScegli un'opzione: ");

		String choice = scanner.nextLine();
		switch (choice) {

		case "1":
			gestioneAnimali();
			break;

		case "2":
			gestioneZone();
			break;

		case "3":
			logout();
			break;

		default:
			System.out.println("Opzione non valida. Riprova.");
		}
	}
	
	private static void showAdminMenu() {
		System.out.println("\nMenu Principale - " + loggedInUser.getNome());
		System.out.println();
		System.out.println("1. Gestione Animali");
		System.out.println("2. Gestione Zone");
		System.out.println("3. Gestione Dipendenti");
		System.out.println("4. Logout");
		System.out.print("\nScegli un'opzione: ");

		String choice = scanner.nextLine();
		switch (choice) {

		case "1":
			gestioneAnimali();
			break;

		case "2":
			gestioneZone();
			break;

		case "3":
			gestioneDipendenti();
			break;

		case "4":
			logout();
			break;

		default:
			System.out.println("Opzione non valida. Riprova.");
		}
	}

	private static void gestioneDipendenti() {
		System.out.println("\n Gestione Dipendenti - " + loggedInUser.getNome());
		System.out.println();
		System.out.println("1. Visualizza dipendenti");
		System.out.println("2. Modifica ruolo dipendente");
		System.out.println("3. Visualizza animali assegnati a un dipendente");
		System.out.println("4. Assegna animali a dipendente");
		System.out.println("5. Rimuovi assegnazione animale da un dipendente");
		System.out.println("6. Elimina un dipendente");
		System.out.println("7. Torna al menu principale");
		System.out.print("\nScegli un'opzione: ");

		String choice = scanner.nextLine();
		switch (choice) {

		case "1":
			visualizzaDipendenti();
			break;

		case "2":
			modificaRuoloDipendente();
			break;
			
		case "3":
			visualizzaAnimaliAssegnati();
			break;

		case "4":
			assegnaAnimaleADipendente();
			break;
		
		case "5":
			rimuoviAssegnazione();
			break;
		
		case "6":
			eliminaDipendente();
			break;
		
		case "7":
			return;

		default:
			System.out.println("Opzione non valida. Riprova.");
		}
	}

	private static void gestioneZone() {
		System.out.println("\n Gestione Zone - " + loggedInUser.getNome());
		System.out.println();
		System.out.println("1. Visualizza tutte le zone");
		System.out.println("2. Aggiungi zona");
		System.out.println("3. Modifica zona");
		System.out.println("4. Elimina zona");
		System.out.println("5. Torna al menù principale");
		System.out.print("\nScegli un'opzione: ");

		String choice = scanner.nextLine();
		switch (choice) {

		case "1":
			visualizzaZone();
			break;

		case "2":
			aggiungiZona();
			break;

		case "3":
			modificaZona();
			break;

		case "4":
			eliminaZona();
			break;

		case "5":
			return;

		default:
			System.out.println("Opzione non valida. Riprova.");
		}
	}

	private static void gestioneAnimali() {
		System.out.println("\n Gestione Animali - " + loggedInUser.getNome());
		System.out.println();
		System.out.println("1. Visualizza tutti gli animali ");
		System.out.println("2. Aggiungi nuovo animale");
		System.out.println("3. Modifica animale");
		System.out.println("4. Elimina animale");
		System.out.println("5. Sposta animale");
		System.out.println("6. Torna al menù principale");
		System.out.print("\nScegli un'opzione: ");

		String choice = scanner.nextLine();
		switch (choice) {

		case "1":
			visualizzaAnimali();
			break;

		case "2":
			aggiungiAnimale();
			break;

		case "3":
			modificaAnimale();
			break;

		case "4":
			eliminaAnimale();
			break;

		case "5":
			spostaAnimale();
			break;
			
		case "6":
			return;

		default:
			System.out.println("Opzione non valida. Riprova.");
		}
	}

	private static void visualizzaAnimali() {
	    List<Animale> animali = animaleService.trovaTutti();
	    if (animali.isEmpty()) {
	        System.out.println("Nessun animale presente.");
	    } else {
	        for (Animale animale : animali) {
	            System.out.println("ID: " + animale.getId_animale());
	            System.out.println("Nome: " + animale.getNome());
	            System.out.println("Specie: " + animale.getSpecie());
	            System.out.println("Anni: " + animale.getAnni());
	            Zona zona = animale.getZona();
	            if (zona != null) {
	                long animaliPresenti = zonaService.countAnimaliInZona(zona.getId_zona());
	                int capienzaMassima = zona.getCapienza();
	                int capienzaAttuale = capienzaMassima - (int) animaliPresenti;

	                System.out.println("Zona: " + zona.getNome() + " [ID: " + zona.getId_zona() + "]");
	                System.out.println("Capienza Massima: " + capienzaMassima);
	                System.out.println("Animali Presenti: " + animaliPresenti);
	                System.out.println("Capienza Attuale: " + capienzaAttuale);
	            } else {
	                System.out.println("Zona: null");
	            }
	            System.out.println("--------------------");
	        }
	    }
	}

	private static void aggiungiAnimale() {
		System.out.println("Inserisci il nome dell' animale: ");
		String nomeAnimaleReg = scanner.nextLine().trim();
		System.out.println("Inserisci la specie dell' animale: ");
		String specieAnimaleReg = scanner.nextLine().trim();
		System.out.println("Inserisci quanti anni ha l'animale: ");
		String anniAnimaleReg = scanner.nextLine().trim();

		if (nomeAnimaleReg.isEmpty() || specieAnimaleReg.isEmpty() || anniAnimaleReg.isEmpty()) {
			System.out.println("Errore: Tutti i campi sono obbligatori e non possono essere vuoti.");
			return;
		}

		if (nomeAnimaleReg.length() > 25) {
			System.out.println("Registrazione annullata: Nome non conforme (max 25 caratteri).");
			return;
		}

		if (specieAnimaleReg.length() > 25) {
			System.out.println("Registrazione annullata: Specie non conforme (max 25 caratteri).");
			return;
		}

		if (!specieAnimaleReg.matches("^[a-zA-Z]+$")) {
			System.out.println("Registrazione annullata: Specie non conforme (contiene numeri o simboli).");
			return;
		}

		int anniAnimale;
		try {
			anniAnimale = Integer.parseInt(anniAnimaleReg);
			if (anniAnimale < 0) {
				System.out.println("Errore: L'età non può essere negativa.");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("Input non valido. Inserisci un numero intero.");
			return;
		}

		Animale newAnimale = new Animale();
		newAnimale.setNome(nomeAnimaleReg);
		newAnimale.setSpecie(specieAnimaleReg);
		newAnimale.setAnni(anniAnimale);
		newAnimale.setZona(null); // da assegnare in seguito

		animaleService.creaAnimale(newAnimale);

		System.out.println("Animale aggiunto con successo!");

	}

	private static void modificaAnimale() {
		visualizzaAnimali();

		System.out.println("Inserisci l'ID dell'animale da modificare: ");
		try {
			Long id = Long.parseLong(scanner.nextLine().trim());
			Animale animale = animaleService.trovaPerId(id);

			if (animale == null) {
				System.out.println("Animale non trovato.");
				return;
			}

			System.out.print("Nuovo nome (" + animale.getNome() + "): ");
			String nuovoNome = scanner.nextLine().trim();
			if (!nuovoNome.isEmpty()) {
				if (nuovoNome.matches("^[a-zA-ZàèìòùÀÈÌÒÙáéíóúÁÉÍÓÚçÇ\\s]{1,25}$")) {
					animale.setNome(nuovoNome);
				} else {
					System.out.println(
							"Nome non valido. Deve contenere solo lettere (max 25 caratteri). Nome non modificato.");
				}
			}

			System.out.print("Nuova specie (" + animale.getSpecie() + "): ");
			String nuovaSpecie = scanner.nextLine().trim();
			if (!nuovaSpecie.isEmpty()) {
				if (nuovaSpecie.matches("^[a-zA-ZàèìòùÀÈÌÒÙáéíóúÁÉÍÓÚçÇ\\s]{1,25}$")) {
					animale.setSpecie(nuovaSpecie);
				} else {
					System.out.println(
							"Specie non valida. Deve contenere solo lettere (max 25 caratteri). Specie non modificata.");
				}
			}

			System.out.print("Nuova età (" + animale.getAnni() + "): ");
			String nuovaEta = scanner.nextLine().trim();
			if (!nuovaEta.isEmpty()) {
				try {
					int eta = Integer.parseInt(nuovaEta);
					if (eta >= 0) {
						animale.setAnni(eta);
					} else {
						System.out.println("Errore: L'età deve essere un numero positivo. Età non modificata.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Errore: L'età inserita non è un numero valido. Età non modificata.");
				}
			}

			animaleService.aggiornaAnimale(animale);
			System.out.println("Modifica completata.");

		} catch (NumberFormatException e) {
			System.out.println("ID non valido. Operazione annullata.");
		}
	}

	private static void eliminaAnimale() {
		visualizzaAnimali();

		System.out.println("Inserisci l'ID dell'animale da eliminare: ");
		String input = scanner.nextLine().trim();

		try {
			Long id = Long.parseLong(input);
			Animale animale = animaleService.trovaPerId(id);

			if (animale == null) {
				System.out.println("Animale non trovato con l'ID specificato.");
				return;
			}

			System.out.println(
					"Sei sicuro di voler eliminare l'animale: " + animale.getNome() + " (ID: " + id + ")? (s/N)");
			String conferma = scanner.nextLine().trim().toLowerCase();

			if (conferma.equals("s")) {
				animaleService.eliminaAnimale(id);
				System.out.println("Animale eliminato con successo.");
			} else {
				System.out.println("Eliminazione annullata.");
			}

		} catch (NumberFormatException e) {
			System.out.println("ID non valido. Operazione annullata.");
		}
	}
	
	public static void spostaAnimale() {
	    visualizzaAnimali();

	    System.out.println("Inserisci l'ID dell'animale:");
	    String animaleIdStr = scanner.nextLine().trim();
	    Long animaleId;
	    try {
	        animaleId = Long.parseLong(animaleIdStr);
	        if (animaleId <= 0) {
	            System.out.println("Errore: ID dell'animale non valido.");
	            return;
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("Errore: ID animale non valido.");
	        return;
	    }

	    Animale animale = animaleService.trovaPerId(animaleId);
	    if (animale == null) {
	        System.out.println("Animale non trovato con l'ID specificato.");
	        return;
	    }

	    visualizzaZone();

	    System.out.println("Inserisci l'ID della zona di destinazione:");
	    String zonaIdStr = scanner.nextLine().trim();
	    Long zonaId;
	    try {
	        zonaId = Long.parseLong(zonaIdStr);
	        if (zonaId <= 0) {
	            System.out.println("Errore: ID della zona non valido.");
	            return;
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("Errore: ID zona non valido.");
	        return;
	    }

	    Zona nuovaZona = zonaService.trovaPerId(zonaId);
	    if (nuovaZona == null) {
	        System.out.println("Zona non trovata con l'ID specificato.");
	        return;
	    }

	    Zona vecchiaZona = animale.getZona();

	    long animaliNellaNuovaZona = zonaService.countAnimaliInZona(nuovaZona.getId_zona());
	    if (animaliNellaNuovaZona >= nuovaZona.getCapienza()) {
	        System.out.println("Errore: La zona selezionata è già piena. Impossibile spostare l'animale.");
	        return;
	    }

	    if (vecchiaZona != null && vecchiaZona.getId_zona().equals(nuovaZona.getId_zona())) {
	        System.out.println("L'animale è già in questa zona.");
	        return;
	    }

	    try {
	        animaleService.spostaAnimaleInZona(animaleId, zonaId);
	        System.out.println("Animale spostato con successo nella nuova zona.");
	    } catch (RuntimeException e) {
	        System.out.println("Errore durante lo spostamento: " + e.getMessage());
	    }
	}


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
		newUser.setPassword(PasswordHashing.hashPassword(passwordReg));
		newUser.setRuolo(Ruolo.IN_ATTESA);

		dipendenteService.creaDipendente(newUser);

		System.out.println("Registrazione completata con successo! Ora puoi effettuare il login.");
	}

	private static void visualizzaZone() {
	    List<Zona> zone = zonaService.trovaTutte();
	    if (zone.isEmpty()) {
	        System.out.println("Nessuna zona presente.");
	    } else {
	        for (Zona zona : zone) {
	            long animaliPresenti = zonaService.countAnimaliInZona(zona.getId_zona());
	            int capienzaMassima = zona.getCapienza();
	            int capienzaAttuale = capienzaMassima - (int) animaliPresenti;

	            System.out.println("Zona [ID: " + zona.getId_zona() + ", Nome: " + zona.getNome() +
	                ", Habitat: " + zona.getHabitat() +
	                ", Capienza Massima: " + capienzaMassima +
	                ", Animali Presenti: " + animaliPresenti +
	                ", Capienza Attuale: " + capienzaAttuale + "]");
	            System.out.println("--------------------");
	        }
	    }
	}

	private static void aggiungiZona() {
		System.out.println("Inserisci il nome della zona: ");
		String nomeZona = scanner.nextLine().trim();

		System.out.println("Scegli l'habitat per la zona (scrivi il nome esatto): ");
		for (Habitat habitat : Habitat.values()) {
			System.out.println("- " + habitat.name());
		}

		String habitatScelto = scanner.nextLine().trim().toUpperCase();

		Habitat habitatSelezionato = null;
		try {
			habitatSelezionato = Habitat.valueOf(habitatScelto);
		} catch (IllegalArgumentException e) {
			System.out.println("Habitat non valido. Operazione annullata.");
			return;
		}

		System.out.println("Inserisci la capienza della zona: ");
		String capienzaReg = scanner.nextLine().trim();
		int capienza;
		try {
			capienza = Integer.parseInt(capienzaReg);
			if (capienza <= 0) {
				System.out.println("La capienza deve essere un numero positivo.");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("Capienza non valida.");
			return;
		}

		Zona nuovaZona = new Zona();
		nuovaZona.setNome(nomeZona);
		nuovaZona.setHabitat(habitatSelezionato);
		nuovaZona.setCapienza(capienza);

		zonaService.creaZona(nuovaZona);
		System.out.println("Zona aggiunta con successo!");
	}

	private static void modificaZona() {
		visualizzaZone();

		System.out.println("Inserisci l'ID della zona da modificare: ");
		String input = scanner.nextLine().trim();

		try {
			Long id = Long.parseLong(input);
			Zona zona = zonaService.trovaPerId(id);

			if (zona == null) {
				System.out.println("Zona non trovata.");
				return;
			}

			System.out.print("Nuovo nome (" + zona.getNome() + "): ");
			String nuovoNome = scanner.nextLine().trim();
			if (!nuovoNome.isEmpty()) {
				zona.setNome(nuovoNome);
			}

			System.out.println("Modifica habitat (corrente: " + zona.getHabitat().name() + ")");
			System.out.println("Scegli un nuovo habitat (scrivi il nome esatto): ");
			for (Habitat habitat : Habitat.values()) {
				System.out.println("- " + habitat.name());
			}
			String habitatScelto = scanner.nextLine().trim().toUpperCase();

			Habitat habitatSelezionato = null;
			try {
				habitatSelezionato = Habitat.valueOf(habitatScelto);
			} catch (IllegalArgumentException e) {
				System.out.println("Habitat non valido. Operazione annullata.");
				return;
			}
			zona.setHabitat(habitatSelezionato);

			System.out.print("Nuova capienza (" + zona.getCapienza() + "): ");
			String nuovaCapienza = scanner.nextLine().trim();
			if (!nuovaCapienza.isEmpty()) {
				try {
					zona.setCapienza(Integer.parseInt(nuovaCapienza));
				} catch (NumberFormatException e) {
					System.out.println("Capienza non valida. Operazione annullata.");
					return;
				}
			}

			zonaService.aggiornaZona(zona);
			System.out.println("Zona modificata con successo.");

		} catch (NumberFormatException e) {
			System.out.println("ID non valido. Operazione annullata.");
		}
	}

	private static void eliminaZona() {
	    visualizzaZone();

	    System.out.println("Inserisci l'ID della zona da eliminare: ");
	    String input = scanner.nextLine().trim();

	    try {
	        Long id = Long.parseLong(input);
	        Zona zona = zonaService.trovaPerId(id);

	        if (zona == null) {
	            System.out.println("Zona non trovata con l'ID specificato.");
	            return;
	        }

	        long animaliPresenti = zonaService.countAnimaliInZona(zona.getId_zona());
	        if (animaliPresenti > 0) {
	            System.out.println("Errore: La zona contiene ancora " + animaliPresenti + " animale/i. " +
	                               "Sposta o elimina prima gli animali prima di rimuovere la zona.");
	            return;
	        }

	        System.out.println("Sei sicuro di voler eliminare la zona: " + zona.getNome() + " (ID: " + id + ")? (s/N)");
	        String conferma = scanner.nextLine().trim().toLowerCase();

	        if (conferma.equals("s")) {
	            zonaService.eliminaZona(id);
	            System.out.println("Zona eliminata con successo.");
	        } else {
	            System.out.println("Eliminazione annullata.");
	        }

	    } catch (NumberFormatException e) {
	        System.out.println("ID non valido. Operazione annullata.");
	    }
	}
	
	private static void visualizzaDipendenti() {
	    List<Dipendente> dipendenti = dipendenteService.trovaTutti();

	    if (dipendenti.isEmpty()) {
	        System.out.println("Nessun dipendente trovato.");
	        return;
	    }

	    for (Dipendente d : dipendenti) {
	        System.out.println("ID: " + d.getId_dipendente());
	        System.out.println("Nome: " + d.getNome() + " " + d.getCognome());
	        System.out.println("Username: " + d.getUsername());
	        System.out.println("Email: " + d.getEmail());
	        System.out.println("Ruolo: " + d.getRuolo());
	        System.out.println("---------------------------");
	    }
	}
	
	private static void modificaRuoloDipendente() {
	    visualizzaDipendenti();

	    System.out.print("Inserisci l'ID del dipendente da modificare: ");
	    String input = scanner.nextLine().trim();

	    try {
	        Long id = Long.parseLong(input);
	        Dipendente dipendente = dipendenteService.trovaPerId(id);

	        if (dipendente == null) {
	            System.out.println("Dipendente non trovato.");
	            return;
	        }

	        System.out.println("Ruolo attuale: " + dipendente.getRuolo());
	        System.out.println("Scegli il nuovo ruolo:");
	        for (Ruolo ruolo : Ruolo.values()) {
	            System.out.println("- " + ruolo.name());
	        }

	        String nuovoRuoloInput = scanner.nextLine().trim().toUpperCase();
	        try {
	            Ruolo nuovoRuolo = Ruolo.valueOf(nuovoRuoloInput);
	            dipendente.setRuolo(nuovoRuolo);
	            dipendenteService.aggiornaDipendente(dipendente);
	            System.out.println("Ruolo aggiornato con successo.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("Ruolo non valido. Operazione annullata.");
	        }

	    } catch (NumberFormatException e) {
	        System.out.println("ID non valido.");
	    }
	}
	
	private static void eliminaDipendente() {
	    visualizzaDipendenti();

	    System.out.print("Inserisci l'ID del dipendente da eliminare: ");
	    String input = scanner.nextLine().trim();

	    try {
	        Long id = Long.parseLong(input);
	        Dipendente dipendente = dipendenteService.trovaPerId(id);

	        if (dipendente == null) {
	            System.out.println("Dipendente non trovato.");
	            return;
	        }

	        if (loggedInUser.getId_dipendente().equals(id)) {
	            System.out.println("Non puoi eliminare te stesso.");
	            return;
	        }

	        System.out.print("Sei sicuro di voler eliminare " + dipendente.getNome() + "? (s/N): ");
	        String conferma = scanner.nextLine().trim().toLowerCase();

	        if (conferma.equals("s")) {
	            List<DipendenteAnimale> relazioni = dipendenteAnimaleService.trovaTutti();
	            for (DipendenteAnimale relazione : relazioni) {
	                if (relazione.getDipendente().getId_dipendente().equals(id)) {
	                    dipendenteAnimaleService.eliminaRelazione(relazione.getId_dipendente_animale());
	                }
	            }
	            dipendenteService.eliminaDipendente(id);
	            System.out.println("Dipendente eliminato con successo.");
	        } else {
	            System.out.println("Eliminazione annullata.");
	        }

	    } catch (NumberFormatException e) {
	        System.out.println("ID non valido.");
	    }
	}

	
	private static void assegnaAnimaleADipendente() {
	    visualizzaDipendenti();
	    System.out.print("Inserisci l'ID del dipendente a cui assegnare un animale: ");
	    Long dipId = Long.parseLong(scanner.nextLine().trim());
	    Dipendente dipendente = dipendenteService.trovaPerId(dipId);
	    if (dipendente == null) {
	        System.out.println("Dipendente non trovato.");
	        return;
	    }

	    visualizzaAnimali();
	    System.out.print("Inserisci l'ID dell'animale da assegnare: ");
	    Long aniId = Long.parseLong(scanner.nextLine().trim());
	    Animale animale = animaleService.trovaPerId(aniId);
	    if (animale == null) {
	        System.out.println("Animale non trovato.");
	        return;
	    }

	    DipendenteAnimale relazione = new DipendenteAnimale();
	    relazione.setDipendente(dipendente);
	    relazione.setAnimale(animale);
	    dipendenteAnimaleService.assegnaDipendenteAAnimale(relazione);

	    System.out.println("Assegnazione completata con successo.");
	}
	
	private static void visualizzaAnimaliAssegnati() {
	    visualizzaDipendenti();
	    System.out.print("Inserisci l'ID del dipendente: ");
	    Long dipId = Long.parseLong(scanner.nextLine().trim());

	    List<DipendenteAnimale> assegnazioni = dipendenteAnimaleService.trovaTutti();
	    boolean trovato = false;
	    for (DipendenteAnimale da : assegnazioni) {
	        if (da.getDipendente().getId_dipendente().equals(dipId)) {
	            Animale a = da.getAnimale();
	            System.out.println("Animale: " + a.getNome() + ", Specie: " + a.getSpecie() + ", Età: " + a.getAnni());
	            trovato = true;
	        }
	    }

	    if (!trovato) {
	        System.out.println("Nessun animale assegnato a questo dipendente.");
	    }
	}
	
	private static void rimuoviAssegnazione() {
	    List<DipendenteAnimale> assegnazioni = dipendenteAnimaleService.trovaTutti();

	    if (assegnazioni.isEmpty()) {
	        System.out.println("Nessuna assegnazione presente.");
	        return;
	    }

	    for (DipendenteAnimale da : assegnazioni) {
	        System.out.println("ID Assegnazione: " + da.getId_dipendente_animale() +
	            " | Dipendente: " + da.getDipendente().getNome() + " " + da.getDipendente().getCognome() +
	            " | Animale: " + da.getAnimale().getNome());
	    }

	    System.out.print("Inserisci l'ID dell'assegnazione da rimuovere: ");
	    Long id = Long.parseLong(scanner.nextLine().trim());

	    dipendenteAnimaleService.eliminaRelazione(id);
	    System.out.println("Assegnazione rimossa con successo.");
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

		if (PasswordHashing.verifyPassword(password, dipendente.getPassword())) {
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
