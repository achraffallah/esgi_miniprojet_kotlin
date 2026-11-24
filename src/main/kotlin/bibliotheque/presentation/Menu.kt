package bibliotheque.presentation

import bibliotheque.acces.Bibliotheque
import bibliotheque.metier.Media


class Menu(
    private val bibliotheque: Bibliotheque,
    private val lire: () -> String? = { readlnOrNull() }
) {

    fun lancer() {
        while (true) {
            afficherOptions()
            val choix = lireEntier("Votre choix", 0..8) ?: break
            when (choix) {
                0 -> break
                1 -> bibliotheque.afficherCatalogue()
                2 -> choisirMedia()?.let { bibliotheque.emprunter(it) }
                3 -> choisirMedia()?.let { bibliotheque.retourner(it) }
                4 -> choisirMedia()?.let { bibliotheque.consulter(it) }
                5 -> bibliotheque.afficherEmprunts()
                6 -> bibliotheque.afficherDisponibles()
                7 -> rechercher()
                8 -> bibliotheque.afficherStatistiques()
            }
        }
        println("Au revoir !")
    }

    private fun afficherOptions() {
        println(
            """
            
            ===== BIBLIOTHÈQUE =====
            1. Afficher le catalogue
            2. Emprunter un média
            3. Retourner un média
            4. Consulter un média sur place
            5. Afficher les emprunts en cours
            6. Afficher les médias disponibles
            7. Rechercher par titre
            8. Statistiques
            0. Quitter
            """.trimIndent()
        )
    }

    private fun rechercher() {
        print("Titre à rechercher : ")
        val saisie = lire() ?: return
        if (saisie.isBlank()) println("Saisie vide.") else bibliotheque.afficherRecherche(saisie)
    }

    // Fait choisir un média dans le catalogue
    private fun choisirMedia(): Media? {
        val catalogue = bibliotheque.catalogue
        if (catalogue.isEmpty()) {
            println("Le catalogue est vide.")
            return null
        }
        bibliotheque.afficherCatalogue()
        val numero = lireEntier("Numéro du média (0 pour annuler)", 0..catalogue.size) ?: return null
        return if (numero == 0) null else catalogue[numero - 1]
    }


    private fun lireEntier(invite: String, plage: IntRange): Int? {
        while (true) {
            print("$invite : ")
            val ligne = lire() ?: return null
            val n = ligne.trim().toIntOrNull()
            if (n != null && n in plage) return n
            println("Saisie invalide : entrez un nombre entre ${plage.first} et ${plage.last}.")
        }
    }
}
