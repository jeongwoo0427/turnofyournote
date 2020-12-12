package com.kkumsoft.turnonyournote.FragmentShowList.Recycler

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.kkumsoft.turnonyournote.Database.DBOpenHelper

class ShowListAdapterDAO {

    lateinit var helper : DBOpenHelper
    lateinit var db : SQLiteDatabase
    var context : Context

    constructor(context:Context){
        this.context = context
    }

    fun getShowList() : Cursor{
        helper = DBOpenHelper(context)
        db = helper.readableDatabase

        val qry = "SELECT S.showID,W.wordID,W.word,W.summary,W.imageSrc FROM wordTbl W INNER JOIN showingTbl S ON W.wordID = S.wordID ORDER BY S.showID ASC"

        return db.rawQuery(qry,null)
    }

    fun getLastIndex() : Int{
        helper = DBOpenHelper(context)
        db = helper.readableDatabase

        val qry = "SELECT showID FROM showingTbl ORDER BY showID desc"

        val cursor :Cursor = db.rawQuery(qry,null)

        cursor.moveToFirst()

        return cursor.getInt(0)
    }

    fun insertShowing(showID : Int, wordID : Int){
        helper = DBOpenHelper(context)
        db = helper.writableDatabase

        val qry = "INSERT INTO showingTbl(showID,wordID) VALUES ("+showID+","+wordID+")"

        db.execSQL(qry)
    }

    fun clearShowList(){
        helper = DBOpenHelper(context)
        db = helper.readableDatabase

        val qry = "DELETE FROM showingTbl"

        db.execSQL(qry)
    }


}