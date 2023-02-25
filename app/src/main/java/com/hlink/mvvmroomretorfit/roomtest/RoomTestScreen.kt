package com.hlink.mvvmroomretorfit.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.room.Room
import com.hlink.mvvmroomretorfit.databinding.ActivityRoomTestScreenBinding
import com.hlink.mvvmroomretorfit.roomtest.db.MyDatabase
import com.hlink.mvvmroomretorfit.roomtest.model.Contact
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomTestScreen : AppCompatActivity() {
    lateinit var binding: ActivityRoomTestScreenBinding

    lateinit var myDatabase: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomTestScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDatabase =
            Room.databaseBuilder(applicationContext, MyDatabase::class.java, "mydb").build()

        GlobalScope.launch {
            myDatabase.contactDao().insertContact(Contact(0,"Kamlesh","1234567890"))
        }

        getData()

    }

    private fun getData() {
        myDatabase.contactDao().getContact().observe(this, Observer {
            it?.let {
                it.forEach {
                    binding.textViewMyContact.text = binding.textViewMyContact.text.toString() + it.id.toString().plus(" ${it.name}").plus(" ${it.phone}\n")
                }

            }
        })
    }
}