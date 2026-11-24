package bibliotheque.metier

/**
 * Média empruntable : factorise la gestion de l'état emprunté / disponible
 * pour Livre, EnregistrementAudio et DVD.
 */
abstract class MediaEmpruntable(titre: String, dateDeParution: String) :
    Media(titre, dateDeParution), Empruntable {

    final override var estEmprunte: Boolean = false
        private set

    //Désignation utilisée dans les messages
    protected abstract val designation: String

    // Vrai si le nom du type est féminin
    protected open val feminin: Boolean = false

    private val accord: String get() = if (feminin) "e" else ""

    override fun emprunter(): Boolean {
        if (estEmprunte) {
            println("$designation est déjà emprunté$accord.")
            return false
        }
        estEmprunte = true
        println("$designation a été emprunté$accord.")
        return true
    }

    override fun retourner(): Boolean {
        if (!estEmprunte) {
            println("$designation n'est pas emprunté$accord, il ne peut pas être retourné$accord.")
            return false
        }
        estEmprunte = false
        println("$designation a été retourné$accord.")
        return true
    }
}
