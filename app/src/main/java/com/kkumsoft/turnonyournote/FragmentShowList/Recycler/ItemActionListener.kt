package com.kkumsoft.turnonyournote.FragmentShowList.Recycler

interface ItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position:Int)
}