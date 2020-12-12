package com.kkumsoft.turnonyournote

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.kkumsoft.turnonyournote.FragmentAllList.FragmentAllList
import com.kkumsoft.turnonyournote.FragmentSetting.FragmentSetting
import com.kkumsoft.turnonyournote.FragmentShowList.FragmentShowList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var fragmentShowList: FragmentShowList
    lateinit var fragmentAllList: FragmentAllList
    lateinit var fragmentSetting: FragmentSetting

    var isEditing : Boolean = false

    var currentFragment : Int = 1;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewSetting()
        changeFragment(1)
        changeEditMode(false)


    }

    fun viewSetting() {
        activitymain_bottomnav.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.showlist -> {
                    if(currentFragment !=1) {
                        changeFragment(1)
                    }
                }

                R.id.alllist ->{
                    if(currentFragment !=2) {
                        changeFragment(2)
                    }
                }

                R.id.setting ->{
                    if(currentFragment != 3) {
                        changeFragment(3)
                    }
                }


            }

            true

        }

        activitymain_btn_add.setOnClickListener{
           changeEditMode(true)
        }

        activitymain_btn_apply.setOnClickListener {
            changeEditMode(false)
            fragmentShowList.saveCurrentShowList()
            fragmentShowList.initRecyclerItem()
        }

        activitymain_btn_cancel.setOnClickListener {
            fragmentShowList.initRecyclerItem()
            changeEditMode(false)
        }

    }



    fun changeFragment(selection : Int){
        when(selection){
            1 -> {
                fragmentShowList =
                    FragmentShowList(this)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.activitymain_framelayout, fragmentShowList)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                currentFragment = selection
                showAddButton(false)
            }

            2 -> {
                fragmentAllList =
                    FragmentAllList()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.activitymain_framelayout,fragmentAllList)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                currentFragment = selection
                showAddButton(true)
            }

            3->{fragmentSetting =
                FragmentSetting()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.activitymain_framelayout,fragmentSetting)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                currentFragment = selection
                showAddButton(false)
                activitymain_bottom_rl1.animate().translationY(500f).setDuration(300)
            }

        }
    }



    fun changeEditMode(isEditing : Boolean){
        var animDuration : Long = 300
        if(isEditing){


            if(this.isEditing == false) {
                activitymain_bottomnav.animate().translationY(500f).setDuration(animDuration)
                activitymain_bottom_rl1.animate().translationY(500f).setDuration(animDuration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                        }
                    })
                activitymain_bottom_rl2.animate().translationY(0f).setDuration(animDuration)
            }


        }else{

            activitymain_bottomnav.animate().translationY(0f).setDuration(animDuration)
            activitymain_bottom_rl1.animate().translationY(0f).setDuration(animDuration)
            activitymain_bottom_rl2.animate().translationY(500f).setDuration(animDuration).setListener(object:AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                }
            })
          // fragmentShowList.ibtnMore.animate().translationX(0f).setDuration(animDuration)


        }
        this.isEditing = isEditing
    }

    fun showAddButton(isShowing : Boolean){
        var animDuration : Long = 300
        if(isShowing){
            activitymain_bottom_rl1.animate().translationY(500f).setDuration(animDuration)
            activitymain_fab.animate().translationY(0f).setDuration(animDuration)

        }else{
            activitymain_bottom_rl1.animate().translationY(0f).setDuration(animDuration)
            activitymain_fab.animate().translationY(500f).setDuration(animDuration)
        }
    }


}


