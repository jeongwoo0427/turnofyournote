package com.kkumsoft.turnonyournote.FragmentShowList

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kkumsoft.turnonyournote.FragmentShowList.Recycler.*
import com.kkumsoft.turnonyournote.MainActivity
import com.kkumsoft.turnonyournote.R
import kotlinx.android.synthetic.main.fragment_show_list.*


class FragmentShowList(activity: MainActivity) : Fragment() ,ItemDragListener{

    var activityMain : MainActivity

    lateinit var showListAdapterDAO:ShowListAdapterDAO

    lateinit var itemTouchHelper: ItemTouchHelper
    lateinit var ibtnMore : ImageButton
    lateinit var showListAdapter:ShowListAdapter
    lateinit var recyclerView:RecyclerView

    init {
        this.activityMain = activity

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_show_list, container, false)



        setRecyclerView(view)
        ibtnMore = view.findViewById(R.id.fgshowlist_ibtn_more) as ImageButton

        return view
    }

    fun setRecyclerView(view : View){
        recyclerView = view.findViewById(R.id.fragmentshow_recycler) as RecyclerView
        showListAdapter = ShowListAdapter(activityMain,this)
        initRecyclerItem()
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(showListAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun initRecyclerItem(){


        val cursor : Cursor
        val showListAdapterDAO = ShowListAdapterDAO(activityMain)
        cursor = showListAdapterDAO.getShowList()

        showListAdapter.list = ArrayList<ShowListAdapterDO>()

        while(cursor.moveToNext()){

            showListAdapter.addItem(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
            )

        }


        recyclerView.layoutManager= LinearLayoutManager(requireContext()) //레이아웃메니저로 표시할 모습정의 : 이거 없으면 다 잘 해도 아무것도 안뜸..
        recyclerView.adapter = showListAdapter

    }

    fun saveCurrentShowList(){
        showListAdapterDAO = ShowListAdapterDAO(activityMain)

        showListAdapterDAO.clearShowList()

        var order = 1

        for(sdo : ShowListAdapterDO in showListAdapter.list){
            showListAdapterDAO.insertShowing(order,sdo.wordID)
            order++
        }

    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

}