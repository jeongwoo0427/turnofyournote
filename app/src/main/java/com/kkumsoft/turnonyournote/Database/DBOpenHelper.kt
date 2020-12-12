package com.kkumsoft.turnonyournote.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcel
import android.os.Parcelable

class DBOpenHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object{
        const val DATABASE_VERSION = 5
        const val DATABASE_NAME = "Word.db"
    }

    override fun onCreate(db: SQLiteDatabase) {

        var qry = "CREATE TABLE wordTbl(wordID INTEGER PRIMARY KEY AUTOINCREMENT, word TEXT NOT NULL, summary TEXT NOT NULL, imageSrc TEXT NOT NULL)"
        db.execSQL(qry)

        qry = "INSERT INTO wordTbl(wordID,word,summary,imageSrc) VALUES (1,'켜는 것은 메모장','안녕하세요 켜는 것은 메모장 개발자입니다!','')"
        db.execSQL(qry)

        qry = "INSERT INTO wordTbl(wordID,word,summary,imageSrc) VALUES (2,'사과','달콤한 과일','')"
        db.execSQL(qry)

        qry = "INSERT INTO wordTbl(wordID,word,summary,imageSrc) VALUES (3,'Turn','켜다','')"
        db.execSQL(qry)

        qry = "INSERT INTO wordTbl(wordID,word,summary,imageSrc) VALUES (4,'Press','압력을 가하다','')"
        db.execSQL(qry)

        qry = "INSERT INTO wordTbl(wordID,word,summary,imageSrc) VALUES (5,'Protein','단백질','')"
        db.execSQL(qry)

        qry = "INSERT INTO wordTbl(wordID,word,summary,imageSrc) VALUES (6,'Evolution','혁명','')"
        db.execSQL(qry)




        qry = "CREATE TABLE showingTbl(showID INTEGER PRIMARY KEY, wordID INTEGER NOT NULL, CONSTRAINT fk_word FOREIGN KEY (wordID) REFERENCES wordTbl(wordID))"
        db.execSQL(qry)

        qry = "INSERT INTO showingTbl(showID,wordID) VALUES(1,1)"
        db.execSQL(qry)

        qry = "INSERT INTO showingTbl(showID,wordID) VALUES(2,2)"
        db.execSQL(qry)

        qry = "INSERT INTO showingTbl(showID,wordID) VALUES(3,4)"
        db.execSQL(qry)

        qry = "INSERT INTO showingTbl(showID,wordID) VALUES(4,5)"
        db.execSQL(qry)

        qry = "INSERT INTO showingTbl(showID,wordID) VALUES(5,3)"
        db.execSQL(qry)

        qry = "INSERT INTO showingTbl(showID,wordID) VALUES(6,6)"
        db.execSQL(qry)


    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

        var qry = "DROP TABLE IF EXISTS showingTbl"
        db.execSQL(qry)

        qry = "DROP TABLE IF EXISTS wordTbl"
        db.execSQL(qry)

        onCreate(db)


    }


}