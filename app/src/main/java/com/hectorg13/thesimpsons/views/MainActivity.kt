package com.hectorg13.thesimpsons.views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hectorg13.thesimpsons.databinding.ActivityMainBinding
import com.hectorg13.thesimpsons.viewmodels.MainViewModel
import com.hectorg13.thesimpsons.views.adapters.CharacterAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupRecyclerView()

        viewModel.getAllCharacters()

        viewModel.listCharacters.observe(this) {
            hideKeyboard()
            adapter.listCharacters = it
            adapter.notifyDataSetChanged()
        }

        binding.tilSearch.setEndIconOnClickListener {
            hideKeyboard()
            if (binding.tietSearch.text.toString() == "") {
                viewModel.getAllCharacters()
            } else {
                viewModel.getCharacter(binding.tietSearch.text.toString().trim())
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvCharacters.layoutManager = GridLayoutManager(this, 3)
        adapter = CharacterAdapter(this, arrayListOf())
        binding.rvCharacters.adapter = adapter
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}