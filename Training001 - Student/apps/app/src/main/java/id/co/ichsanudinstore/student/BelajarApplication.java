package id.co.ichsanudinstore.student;

import android.app.Application;

import id.co.ichsanudinstore.student.entity.ModelMigration;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class BelajarApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration mConfiguration = new RealmConfiguration.Builder()
                .name("belajar_database.realm")
                .schemaVersion(0)
                .migration(new ModelMigration())
                .build();
        Realm.setDefaultConfiguration(mConfiguration);
    }
}
