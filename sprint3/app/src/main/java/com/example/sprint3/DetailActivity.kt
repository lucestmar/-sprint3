package com.example.sprint3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sprint3.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCard()
    }

    private fun setupCard() {
        with(binding.myDetailCard) {
            setTitle("Encabezado o título")
            setSubtitle("Subtítulo")
            setDescription("Esta es una breve descripción sobre la Card.")

            setMainImageResource(
                resId = R.drawable.img_velada_2027,
                contentDesc = "Fotografía principal del artículo"
            )
            setAvatarResource(R.drawable.ic_launcher_foreground)

            setOnPrimaryActionClickListener {
                Toast.makeText(this@DetailActivity, "Acción principal pulsada", Toast.LENGTH_SHORT).show()
            }

            setOnSecondaryActionClickListener {
                Toast.makeText(this@DetailActivity, "Acción secundaria pulsada", Toast.LENGTH_SHORT).show()
            }

            setOnMenuClickListener {
                Toast.makeText(this@DetailActivity, "Menú de opciones", Toast.LENGTH_SHORT).show()
            }
        }
    }
}