package com.fire.quotely.etc;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by LLL-Brad on 10/5/2017.
 */

public class FirebaseReferences {
    public static CollectionReference submissionsReference() {
        return FirebaseFirestore.getInstance()
                .collection(FirebaseConstants.SUBMISSIONS);
    }
}
