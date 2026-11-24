package bibliotheque.presentation

import bibliotheque.acces.Bibliotheque
import bibliotheque.metier.DVD
import bibliotheque.metier.EnregistrementAudio
import bibliotheque.metier.Journal
import bibliotheque.metier.Livre
import bibliotheque.metier.Magazine

// Crée une bibliothèque avec quelques médias d'exemple
fun creerBibliothequeInitiale(): Bibliotheque {
    val bibliotheque = Bibliotheque()
    bibliotheque.ajouterMedia(Livre("1984", "George Orwell", "Gallimard", "1972-01-01"))
    bibliotheque.ajouterMedia(DVD("Inception", 8880, "Action", "2010-01-01"))
    bibliotheque.ajouterMedia(EnregistrementAudio("Beethoven - Symphonie No.9", 960, "Classical", "1967-01-01"))
    bibliotheque.ajouterMedia(Magazine("National Geographic", "2026-10-28", "10"))
    bibliotheque.ajouterMedia(Journal("Le Monde", "2026-10-28"))
    return bibliotheque
}

// Scénario
fun scenarioEnonce() {
    println("=== Scénario de l'énoncé ===")
    val bibliotheque = Bibliotheque()
    val magazine = Magazine("National Geographic", "2026-10-28", "10")
    val journal = Journal("Le Monde", "2026-10-28")
    val enregistrementAudio = EnregistrementAudio("Beethoven - Symphonie No.9", 960, "Classical", "1967-01-01")
    val dvd = DVD("Inception", 8880, "Action", "2010-01-01") /*2h 28m*/
    val livre = Livre("1984", "George Orwell", "Gallimard", "1972-01-01")

    bibliotheque.emprunter(dvd)
    bibliotheque.emprunter(livre)
    bibliotheque.consulter(magazine)
    bibliotheque.consulter(journal)
    bibliotheque.emprunter(enregistrementAudio)

    bibliotheque.afficherEmprunts()

    bibliotheque.retourner(dvd)
    bibliotheque.retourner(livre)
    bibliotheque.afficherEmprunts()
}

//Démonstration des cas d'erreur et des bonus
fun scenarioBonusEtErreurs() {
    val bibliotheque = creerBibliothequeInitiale()
    val livre = bibliotheque.catalogue.first { it is Livre }
    val journal = bibliotheque.catalogue.first { it is Journal }
    val dvd = bibliotheque.catalogue.first { it is DVD }

    println("\n=== Cas invalides ===")
    bibliotheque.emprunter(livre)
    bibliotheque.emprunter(livre)
    bibliotheque.retourner(dvd)
    bibliotheque.emprunter(journal)
    bibliotheque.consulter(dvd)
    try {
        Journal("Date fausse", "28/10/2026")
    } catch (e: IllegalArgumentException) {
        println("Exception attendue : ${e.message}")
    }

    println("\n=== Bonus ===")
    bibliotheque.afficherRecherche("incep")
    bibliotheque.afficherRecherche("introuvable")
    bibliotheque.afficherDisponibles()
    bibliotheque.afficherStatistiques()
}

fun main(args: Array<String>) {
    if ("--menu" in args) {
        Menu(creerBibliothequeInitiale()).lancer()
        return
    }
    scenarioEnonce()
    scenarioBonusEtErreurs()
}
