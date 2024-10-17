package fr.ccm2.projetm2prgd.firebase.view

import android.text.TextUtils
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthView {

//    override fun onStart() {
//        super.onStart()
//        mViewModel.mCurrentUser.observe(this, mObserverUser)
//        mViewModel.mErrorProcess.observe(this, mObserverError)
//    }
//
//
//    override fun onStop() {
//        mViewModel.mCurrentUser.removeObserver(mObserverUser)
//        mViewModel.mErrorProcess.removeObserver(mObserverError)
//        super.onStop()
//    }
//
//
//    private fun checkConformityFields(): Boolean {
//        var isValid = true
//
//
//        if (TextUtils.isEmpty(firebaseUserEmail.toString()) || TextUtils.isEmpty(firebaseUserPassword.toString())) {
//            isValid = false
//        }
//        firebaseError.text = "empty field"
//        return isValid
//    }
//
//    private fun login() {
//        if (checkConformityFields()) {
//            mViewModel.loginUser(firebaseUserEmail.text.toString(), firebaseUserPassword.text.toString())
//        }
//    }
//
//    private fun register() {
//        if (checkConformityFields()) {
//            mViewModel.registerNewUser(firebaseUserEmail.text.toString(), firebaseUserPassword.text.toString())
//        }
//    }
//
//    private fun updateUser(user : FirebaseUser) {
//        user.let {
//            firebaseLog.text = "${user.uid}-${user.email}"
//        }
//    }

}