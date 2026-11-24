package bibliotheque.metier

// Journal : consultable sur place uniquement
class Journal(titre: String, dateDeParution: String) : Media(titre, dateDeParution), Consultable {

    override val typeLibelle = "Journal"

    override fun afficher() =
        println("Journal: Titre = '$titre', Date de parution = '$dateDeParution'")

    override fun consulter() =
        println("Le journal '$titre' du '$dateDeParution' est consulté sur place.")
}
