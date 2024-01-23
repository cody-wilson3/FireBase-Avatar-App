package com.example.cs3200firebasestarter.ui.repositories

import android.util.Log
import com.example.cs3200firebasestarter.ui.models.Avatar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object AvatarsRepository {

    private val avatarCache = mutableListOf<Avatar>()
    private var cacheInitialized = false

    // returns the list of firestore avatars whether cached or not
    // adds all the firestore avatars with that userId to the avatarCache
    suspend fun getAvatars(): List<Avatar> {
        if (cacheInitialized){
            return avatarCache
        } else {
            val snapshot = Firebase.firestore
                .collection("avatars")
                .whereEqualTo("userId", UserRepository.getCurrentUserId())
                .get()
                .await()
            avatarCache.addAll(snapshot.toObjects(Avatar::class.java))
            cacheInitialized = true
            return avatarCache
        }
    }

    // takes in the needed values, creates an Avatar object with them,
    // adds that object to the firestore document and avatarCache and
    // returns the avatar
    suspend fun createAvatar(
        name: String,
        age: String,
        race: String,
        occupation: String,
        height: String,
        description: String,
        gender: String
    ): Avatar {
        val doc = Firebase.firestore.collection("avatars").document()
        val avatar = Avatar(
            name = name,
            description = description,
            age = age,
            race = race,
            id = doc.id,
            userId = UserRepository.getCurrentUserId(),
            occupation = occupation,
            height = height,
            gender = gender

        )
        doc.set(avatar).await()
        avatarCache.add(avatar)
        return avatar
    }


    // finds the avatar in firebase with that id, updates it with the new info
    // in the Avatar object and same with the avatarCache
    suspend fun updateAvatar(avatar: Avatar) {
        Firebase.firestore.collection("avatars")
            .document(avatar.id!!)
            .set(avatar)
            .await()

        val oldAvatarIndex = avatarCache.indexOfFirst {
            it.id == avatar.id
        }
        avatarCache[oldAvatarIndex] = avatar
    }
}