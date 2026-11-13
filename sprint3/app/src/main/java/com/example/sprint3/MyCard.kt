package com.example.sprint3

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.withStyledAttributes
import coil.load
import com.example.sprint3.databinding.ViewMyCardBinding
import com.google.android.material.card.MaterialCardView

class MyCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.materialCardViewStyle
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding: ViewMyCardBinding =
        ViewMyCardBinding.inflate(LayoutInflater.from(context), this)

    init {
        radius = 12f * resources.displayMetrics.density
        cardElevation = resources.getDimension(R.dimen.card_elevation)

        // Leer atributos definidos en XML
        context.withStyledAttributes(attrs, R.styleable.MyCard) {
            setTitle(getString(R.styleable.MyCard_cardTitle))
            setSubtitle(getString(R.styleable.MyCard_cardSubtitle))
            setDescription(getString(R.styleable.MyCard_cardDescription))

            getResourceId(R.styleable.MyCard_cardImage, 0).takeIf { it != 0 }?.let {
                setMainImageResource(it)
            }
            getResourceId(R.styleable.MyCard_cardAvatar, 0).takeIf { it != 0 }?.let {
                setAvatarResource(it)
            }

            getString(R.styleable.MyCard_primaryButtonText)?.let {
                binding.btnPrimaryAction.text = it
            }
            getString(R.styleable.MyCard_secondaryButtonText)?.let {
                binding.btnSecondaryAction.text = it
            }
        }
    }

    // --- Métodos flexibles para textos (ocultan la vista si son nulos o vacíos) ---

    fun setTitle(title: String?) {
        binding.tvTitle.text = title
        binding.tvTitle.visibility = if (title.isNullOrBlank()) View.GONE else View.VISIBLE
    }

    fun setSubtitle(subtitle: String?) {
        binding.tvSubtitle.text = subtitle
        binding.tvSubtitle.visibility = if (subtitle.isNullOrBlank()) View.GONE else View.VISIBLE
    }

    fun setDescription(description: String?) {
        binding.tvDescription.text = description
        binding.tvDescription.visibility = if (description.isNullOrBlank()) View.GONE else View.VISIBLE
    }

    // --- Métodos para añadir imágenes de manera programática ---

    fun setMainImageResource(@DrawableRes resId: Int, contentDesc: String? = null) {
        binding.ivMainImage.setImageResource(resId)
        contentDesc?.let { binding.ivMainImage.contentDescription = it }
    }

    fun setMainImageDrawable(drawable: Drawable?, contentDesc: String? = null) {
        binding.ivMainImage.setImageDrawable(drawable)
        contentDesc?.let { binding.ivMainImage.contentDescription = it }
    }

    fun setMainImageBitmap(bitmap: Bitmap?, contentDesc: String? = null) {
        binding.ivMainImage.setImageBitmap(bitmap)
        contentDesc?.let { binding.ivMainImage.contentDescription = it }
    }

    fun setMainImageUrl(url: String?, contentDesc: String? = null) {
        binding.ivMainImage.load(url) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_dialog_alert)
        }
        contentDesc?.let { binding.ivMainImage.contentDescription = it }
    }

    fun setAvatarResource(@DrawableRes resId: Int) {
        binding.ivAvatar.setImageResource(resId)
    }

    // --- Listeners para acciones ---

    fun setOnPrimaryActionClickListener(listener: () -> Unit) {
        binding.btnPrimaryAction.setOnClickListener { listener() }
    }

    fun setOnSecondaryActionClickListener(listener: () -> Unit) {
        binding.btnSecondaryAction.setOnClickListener { listener() }
    }

    fun setOnMenuClickListener(listener: (View) -> Unit) {
        binding.btnMenu.setOnClickListener { listener(it) }
    }
}