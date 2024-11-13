package fr.ccm2.projetm2prgd.firebase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import fr.ccm2.projetm2prgd.firebase.repository.FirebaseAuthRepository

class FirebaseAuthViewModel : ViewModel() {

    private val mFirebaseAuthRepository = FirebaseAuthRepository()
    val mCurrentUser = mFirebaseAuthRepository.mCurrentUser
    val mErrorProcess = mFirebaseAuthRepository.mErrorProcess

    fun loginUser(email: String, password: String) {
        mFirebaseAuthRepository.loginUser(email, password)
    }

    fun registerNewUser(email: String, password: String) {
        mFirebaseAuthRepository.registerNewUser(email, password)
    }

    fun disconnectUser() {
        mFirebaseAuthRepository.disconnectUser()
    }
}

