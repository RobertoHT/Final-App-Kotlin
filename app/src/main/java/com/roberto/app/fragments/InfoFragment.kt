package com.roberto.app.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*

import com.roberto.app.R
import com.roberto.app.models.TotalMessagesEvent
import com.roberto.app.toast
import com.roberto.app.utils.CircleTransform
import com.roberto.app.utils.RxBus
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_info.view.*
import java.util.EventListener

class InfoFragment : Fragment() {
    private lateinit var _view: View

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var currentUser: FirebaseUser

    private val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var chatDBref: CollectionReference

    private var chatSubscription: ListenerRegistration? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _view = inflater.inflate(R.layout.fragment_info, container, false)

        setupChatDB()
        setupCurrentUser()
        setupCurrentUserInfo()

        //subscribeToTotalMessagesFirebaseStyle()
        subscribeToTotalMessagesEventStyle()

        return _view
    }

    private fun setupChatDB() {
        chatDBref = store.collection("chat")
    }

    private fun setupCurrentUser() {
        currentUser = mAuth.currentUser!!
    }

    private fun setupCurrentUserInfo() {
        _view.textViewInfoEmail.text = currentUser.email
        _view.textViewInfoName.text = currentUser.displayName?.let { currentUser.displayName } ?: run { getString(R.string.info_no_name) }
        currentUser.photoUrl?.let {
            Picasso.get().load(currentUser.photoUrl).resize(100, 100).centerCrop()
                    .transform(CircleTransform()).into(_view.imageViewInfoAvatar)
        } ?: run {
            Picasso.get().load(R.drawable.ic_person).resize(100, 100).centerCrop()
                    .transform(CircleTransform()).into(_view.imageViewInfoAvatar)
        }
    }

    private fun subscribeToTotalMessagesFirebaseStyle() {
        chatSubscription = chatDBref.addSnapshotListener(object: EventListener, com.google.firebase.firestore.EventListener<QuerySnapshot> {
            override fun onEvent(querySnapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) {
                exception?.let {
                    activity!!.toast("Exception")
                    return
                }

                querySnapshot?.let { _view.textViewInfoTotalMessages.text = "${it.size()}" }
            }
        })
    }

    private fun subscribeToTotalMessagesEventStyle() {
        RxBus.listen(TotalMessagesEvent::class.java).subscribe({
            _view.textViewInfoTotalMessages.text = "${it.total}"
        })
    }

    override fun onDestroyView() {
        chatSubscription?.remove()

        super.onDestroyView()
    }
}
