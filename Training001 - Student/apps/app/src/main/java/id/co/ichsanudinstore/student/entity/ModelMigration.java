package id.co.ichsanudinstore.student.entity;

import android.support.annotation.NonNull;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class ModelMigration implements RealmMigration {

    @Override
    public void migrate(@NonNull DynamicRealm realm, long oldVersion, long newVersion) {
        // DynamicRealm exposes an editable schema
        RealmSchema schema = realm.getSchema();

        /*if (oldVersion == 0) {

        }*/
    }
}