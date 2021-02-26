package com.abdhilabs.noteapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.abdhilabs.noteapp.R
import com.abdhilabs.noteapp.adapter.NoteAdapter
import com.abdhilabs.noteapp.data.db.RoomDb
import com.abdhilabs.noteapp.data.model.Note
import com.abdhilabs.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapterNote by lazy { NoteAdapter() }
    private val db by lazy { RoomDb.provideDb(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOnClick()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvNote.adapter = adapterNote
    }

    override fun onResume() {
        super.onResume()
        getDataFromDb()
    }

    private fun getDataFromDb() {
        Thread {
            val listNote = db.noteDao().getAllNote()
            runOnUiThread { adapterNote.fillNote(listNote) }
        }.start()
    }

    private fun initOnClick() {
        adapterNote.setOnItemClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            intent.putExtra("NOTE", it)
            startActivity(intent)
        }

        adapterNote.setOnItemDeleteClickListener {
            Toast.makeText(this, "Berhasil menghapus catatan", Toast.LENGTH_SHORT).show()
            Thread {
                db.noteDao().deleteNote(it)
                getDataFromDb()
            }.start()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> itemAddClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun itemAddClicked() {
        val intent = Intent(this, NoteActivity::class.java)
        startActivity(intent)
    }

}