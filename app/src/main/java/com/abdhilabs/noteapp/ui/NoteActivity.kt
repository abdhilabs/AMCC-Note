package com.abdhilabs.noteapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.abdhilabs.noteapp.R
import com.abdhilabs.noteapp.data.db.RoomDb
import com.abdhilabs.noteapp.data.model.Note
import com.abdhilabs.noteapp.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private var note: Note? = null

    private val db by lazy {
        RoomDb.provideDb(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note)
        note = intent.getParcelableExtra("NOTE")
        setUpdateData(note ?: return)
    }

    private fun setUpdateData(note: Note) {
        binding.etTitle.setText(note.title)
        binding.etContent.setText(note.content)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_item -> itemSaveClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun itemSaveClicked() {
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()
        if (note == null) {
            val note = Note(title = title, content = content)
            Thread { db.noteDao().insertNote(note) }.start()
            Toast.makeText(this, "Berhasil menambahkan catatan", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            note = Note(id = note?.id, title = title, content = content)
            Thread { db.noteDao().updateNote(note ?: return@Thread) }.start()
            Toast.makeText(this, "Berhasil mengupdate catatan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}