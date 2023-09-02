package com.hectorg13.thesimpsons.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hectorg13.thesimpsons.R
import com.hectorg13.thesimpsons.models.Character

class CharacterAdapter(
    val context: Context,
    var listCharacters: List<Character>
): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val cvCharacter = item.findViewById(R.id.cv_character) as CardView
        val ivCharacter = item.findViewById(R.id.iv_character) as ImageView
        val tvCharacter = item.findViewById(R.id.tv_name_character) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_character, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = listCharacters[position]

        Glide
            .with(context)
            .load(character.image)
            .centerInside()
            .into(holder.ivCharacter)

        holder.tvCharacter.text = character.character

        holder.cvCharacter.setOnClickListener {
            showPhrase(character.phrase)
        }
    }

    override fun getItemCount(): Int = listCharacters.size

    private fun showPhrase(phrase: String) {
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_frase)
        val tvPhrase = bottomSheetDialog.findViewById<TextView>(R.id.tv_phrase)
        tvPhrase!!.text = phrase
        bottomSheetDialog.show()
    }
}