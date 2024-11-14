package fr.ccm2.projetm2prgd.firebase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import fr.ccm2.projetm2prgd.firebase.repository.FirebaseAuthRepository

class AuthViewModel : ViewModel() {

    private val mFirebaseAuthRepository = FirebaseAuthRepository()
    //val mCurrentUser = mFirebaseAuthRepository.mCurrentUser
    val mErrorProcess = mFirebaseAuthRepository.mErrorProcess
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _mCurrentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val mCurrentUser: LiveData<FirebaseUser?> get() = _mCurrentUser

    init {
        firebaseAuth.addAuthStateListener { auth ->
            _mCurrentUser.value = auth.currentUser // Met à jour l'utilisateur Firebase en temps réel
        }
    }

    fun loginUser(email: String, password: String) {
        mFirebaseAuthRepository.loginUser(email, password)
    }

    fun registerNewUser(email: String, password: String) {
        mFirebaseAuthRepository.registerNewUser(email, password)
    }

    fun disconnectUser() {
        //FirebaseAuth.getInstance().signOut()
        mFirebaseAuthRepository.disconnectUser()
        //mCurrentUser.postValue(null)
    }
}

