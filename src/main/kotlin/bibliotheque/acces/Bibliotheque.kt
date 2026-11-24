package bibliotheque.acces

import bibliotheque.metier.Consultable
import bibliotheque.metier.Empruntable
import bibliotheque.metier.Media

/**
 * Gère la collection de médias et le suivi des emprunts.
 */
class Bibliotheque {

    private val medias: MutableList<Media> = mutableListOf()
    private val emprunts: MutableList<Media> = mutableListOf()

    // Vue en lecture seule du catalogue
    val catalogue: List<Media> get() = medias.toList()

    // Vue en lecture seule des emprunts en cours
    val empruntsEnCours: List<Media> get() = emprunts.toList()

    // Ajoute un média au catalogue
    fun ajouterMedia(media: Media): Boolean {
        if (medias.any { it === media }) {
            println("Le média '${media.titre}' est déjà dans la bibliothèque.")
            return false
        }
        medias.add(media)
        return true
    }

    // Tente d'emprunter un média
    fun emprunter(media: Media): Boolean {
        if (media !is Empruntable) {
            println("Le ${media.typeLibelle.lowercase()} '${media.titre}' ne peut pas être emprunté (consultation sur place uniquement).")
            return false
        }
        enregistrerSiAbsent(media)
        if (!media.emprunter()) return false
        emprunts.add(media)
        return true
    }

    // Tente de retourner un média emprunté
    fun retourner(media: Media): Boolean {
        if (media !is Empruntable || emprunts.none { it === media }) {
            println("Le média '${media.titre}' ne fait pas partie des emprunts en cours.")
            return false
        }
        if (!media.retourner()) return false
        emprunts.removeAll { it === media }
        return true
    }

   // Consultation sur place
    fun consulter(media: Media): Boolean {
        if (media !is Consultable) {
            println("Le ${media.typeLibelle.lowercase()} '${media.titre}' ne peut pas être consulté sur place.")
            return false
        }
        enregistrerSiAbsent(media)
        media.consulter()
        return true
    }

  //Affiche tous les médias actuellement empruntés
    fun afficherEmprunts() {
        if (emprunts.isEmpty()) {
            println("Aucun emprunt en cours.")
            return
        }
        println("Emprunts en cours:")
        emprunts.forEach { print(" - "); it.afficher() }
    }

   //----------------------- Bonus -----------------------

    // Recherche par titre
    fun rechercher(titre: String): List<Media> {
        val critere = titre.trim()
        if (critere.isEmpty()) return emptyList()
        return medias.filter { it.titre.contains(critere, ignoreCase = true) }
    }

    fun afficherRecherche(titre: String) {
        val resultats = rechercher(titre)
        if (resultats.isEmpty()) {
            println("Aucun média trouvé pour '${titre.trim()}'.")
            return
        }
        println("Résultats pour '${titre.trim()}':")
        resultats.forEach { print(" - "); it.afficher() }
    }

    //Médias empruntables qui ne sont pas actuellement empruntés
    fun mediasDisponibles(): List<Media> =
        medias.filter { it is Empruntable && !it.estEmprunte }

    fun afficherDisponibles() {
        val dispos = mediasDisponibles()
        if (dispos.isEmpty()) {
            println("Aucun média empruntable n'est disponible.")
            return
        }
        println("Médias disponibles à l'emprunt:")
        dispos.forEach { print(" - "); it.afficher() }
    }

    //Nombre de médias par type
    fun statistiques(): Map<String, Int> =
        medias.groupingBy { it.typeLibelle }.eachCount()

    fun afficherStatistiques() {
        if (medias.isEmpty()) {
            println("La bibliothèque est vide.")
            return
        }
        println("Statistiques (${medias.size} médias):")
        statistiques().forEach { (type, nb) -> println(" - $type: $nb") }
    }

    fun afficherCatalogue() {
        if (medias.isEmpty()) {
            println("Le catalogue est vide.")
            return
        }
        println("Catalogue:")
        medias.forEachIndexed { i, m -> print("  ${i + 1}. "); m.afficher() }
    }

    private fun enregistrerSiAbsent(media: Media) {
        if (medias.none { it === media }) medias.add(media)
    }
}
