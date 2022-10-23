package com.magicworld.mybookpremium

import android.app.Application
import androidx.room.Room
import com.magicworld.mybookpremium.data.NotesDatabase

class MyBookPremium : Application() {
    companion object {
        lateinit var database: NotesDatabase

    }

    /*val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE settings_new (foo INTEGER NOT NULL, bar TEXT NOT NULL, PRIMARY KEY(foo))")
            database.execSQL("INSERT INTO settings_new (foo, bar) SELECT foo, '[]' AS bar FROM settings")
            database.execSQL("DROP TABLE settings")
            database.execSQL("ALTER TABLE settings_new RENAME TO settings")
        }
    }*/
    /*private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE notes_table ADD COLUMN  color INTEGER  ")

        }
    }*/

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            NotesDatabase::class.java,
            "note_db"
        ).fallbackToDestructiveMigration().build()
    }
}