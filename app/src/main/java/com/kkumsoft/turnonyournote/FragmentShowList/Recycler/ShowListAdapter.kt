package com.kkumsoft.turnonyournote.FragmentShowList.Recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kkumsoft.turnonyournote.FragmentShowList.FragmentShowList
import com.kkumsoft.turnonyournote.MainActivity
import com.kkumsoft.turnonyournote.R
import kotlinx.android.synthetic.main.item_fragment_showlist.view.*

class ShowListAdapter(context: Context, private val listener: FragmentShowList) : RecyclerView.Adapter<ShowListAdapter.ViewHolder>(),ItemActionListener {

    var list = ArrayList<ShowListAdapterDO>()

    val context: Context



    init {
        Log.v("이닛","0")
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.v("온크리에이트","1")
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_fragment_showlist, parent, false),listener
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.v("온바인드뷰홀더","2")
        var sdo = list.get(position)
        holder.tvWord.text = sdo.word

    }

    fun addItem(showID: Int, wordID: Int, word: String, summary: String, imageSrc: String){
        var sdo = ShowListAdapterDO(showID,wordID,word,summary,imageSrc)
        list.add(sdo)
    }



    override fun onItemMoved(from: Int, to: Int) {
        if(from == to){
            return
        }
        val fromItem = list.removeAt(from)
        list.add(to,fromItem)
        notifyItemMoved(from,to)
        (context as MainActivity).changeEditMode(true)

    }

    override fun onItemSwiped(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        (context as MainActivity).changeEditMode(true)

    }



    class ViewHolder(itemView: View,dragListener: ItemDragListener) : RecyclerView.ViewHolder(itemView) {

        init{
            itemView.item_showlist_iv_mover.setOnTouchListener { view, motionEvent ->
                if(motionEvent.action == MotionEvent.ACTION_DOWN){
                    dragListener.onStartDrag(this)
                }
                false

            }

        }

        val tvWord = itemView.item_routinemanager_tv_word
    }



}
