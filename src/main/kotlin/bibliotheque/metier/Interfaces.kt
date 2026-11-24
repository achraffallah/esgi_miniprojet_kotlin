package bibliotheque.metier

// Un média que l'on peut emporter chez soi
interface Empruntable {
   //`true` si le média est actuellement emprunté
    val estEmprunte: Boolean

    //Emprunte le média. Retourne `true` si l'emprunt a réussi, `false` s'il est déjà emprunté
    fun emprunter(): Boolean

    // Retourne le média. Retourne `true` si le retour a réussi, `false` s'il n'était pas emprunté
    fun retourner(): Boolean
}

// Un média que l'on peut consulter sur place, sans l'emporter
interface Consultable {
    // Consulte le média sur place
    fun consulter()
}
