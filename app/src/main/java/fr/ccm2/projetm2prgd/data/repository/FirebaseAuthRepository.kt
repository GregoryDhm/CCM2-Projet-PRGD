package fr.ccm2.projetm2prgd.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthRepository {

    private var mFirebaseAuth: FirebaseAuth = Firebase.auth
    var mCurrentUser = MutableLiveData<FirebaseUser?>()
    var mErrorProcess = MutableLiveData<Int>()

    init {
        // Initialiser l'utilisateur actuel
        mCurrentUser.postValue(mFirebaseAuth.currentUser)
    }

    fun registerNewUser(email: String, password: String) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mCurrentUser.postValue(mFirebaseAuth.currentUser)
                    mErrorProcess.postValue(0) // Code pour succès
                    //println("Enregistrement réussi") // Debug
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        mErrorProcess.postValue(12) // Code pour utilisateur déjà existant
                        //println("Erreur : Utilisateur déjà existant") // Debug
                    }else if (task.exception is FirebaseAuthWeakPasswordException){
                        mErrorProcess.postValue(13)
                    } else {
                        mErrorProcess.postValue(10) // Code pour erreur inconnue
                        //println("Erreur inconnue") // Debug
                    }
                }
            }
    }

    fun loginUser(email: String, password: String) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mCurrentUser.postValue(mFirebaseAuth.currentUser)
                    mErrorProcess.postValue(0) // Code pour succès
                } else {
                    mErrorProcess.postValue(11) // Code pour erreur de connexion
                }
            }
    }

    fun disconnectUser() {
        mFirebaseAuth.signOut()
        mCurrentUser.postValue(null)
        mErrorProcess.postValue(5) // Code pour déconnexion réussie
    }
}