package fr.ccm2.projetm2prgd.firebase.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthRepository {

    private var mFirebaseAuth: FirebaseAuth = Firebase.auth
    var mCurrentUser = MutableLiveData<FirebaseUser?>()
    var mErrorProcess = MutableLiveData<Int>()

    init {
        mCurrentUser.postValue(mFirebaseAuth.currentUser)
        if (mFirebaseAuth.currentUser == null) {
            mErrorProcess.postValue(9)
        }
    }

    fun registerNewUser(email: String, password: String) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mCurrentUser.postValue(mFirebaseAuth.currentUser)
                    mErrorProcess.postValue(0)
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        mErrorProcess.postValue(12)
                    } else {
                        mErrorProcess.postValue(10)
                    }
                }
            }
    }

    fun loginUser(email: String, password: String) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mCurrentUser.postValue(mFirebaseAuth.currentUser)
                    mErrorProcess.postValue(0)
                } else {
                    mErrorProcess.postValue(11)
                }
            }
    }

    fun disconnectUser() {
        mFirebaseAuth.signOut()
        mCurrentUser.postValue(null)
        mErrorProcess.postValue(5)
    }
}