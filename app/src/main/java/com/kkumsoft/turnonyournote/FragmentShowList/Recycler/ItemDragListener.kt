package com.kkumsoft.turnonyournote.FragmentShowList.Recycler

import androidx.recyclerview.widget.RecyclerView

interface ItemDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}