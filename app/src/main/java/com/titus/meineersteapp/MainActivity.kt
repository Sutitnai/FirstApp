package com.titus.meineersteapp

import android.os.Bundle
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.titus.meineersteapp.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lvTodo: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var shoppingItams: ArrayList<String>
    private lateinit var itemAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lvTodo = binding.lvToDo
        fab = binding.fabAdd
        shoppingItams = ArrayList()
        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, shoppingItams)
        lvTodo.adapter = itemAdapter


        fab.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Hinzufügen")

            val input = EditText(this)
            input.hint = "Text eingeben"
            input.inputType = InputType.TYPE_CLASS_TEXT

            builder.setView(input)

            builder.setPositiveButton("OK"){ _, _ ->
                val inputText = input.text.toString()
                if (inputText.isNotEmpty()) {
                    shoppingItams.add(inputText)
                    itemAdapter.notifyDataSetChanged()
                }
                else {
                    Toast.makeText(applicationContext, "Eingabefeld darf nicht leer sein", Toast.LENGTH_SHORT).show()
                }


            }

            builder.setNegativeButton("Abbrechen"){ _, _ ->
                Toast.makeText(applicationContext, "Abgebrochen", Toast.LENGTH_SHORT).show()
            }

            builder.show()

        }

        lvTodo.setOnItemLongClickListener { _, _, position, _ ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Soll das element entfern werden?")

            builder.setPositiveButton("JA"){ _, _ ->
                shoppingItams.removeAt(position)
                itemAdapter.notifyDataSetChanged()
            }

            builder.setNegativeButton("Nein"){ _, _ ->}
            builder.show()


            true
        }

    }

}