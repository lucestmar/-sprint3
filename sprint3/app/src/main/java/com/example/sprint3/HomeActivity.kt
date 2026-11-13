package com.example.sprint3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprint3.databinding.ActivityHomeBinding

/**
 * HomeActivity - Pantalla principal promocional para La Velada del Año 2027.
 *
 * ============================================================================
 * PRINCIPIOS Y CONSIDERACIONES DE DISEÑO (UI / UX / ACCESIBILIDAD / CÓDIGO)
 * ============================================================================
 *
 * 1. ACCESIBILIDAD (A11y):
 *    - Lectores de pantalla (TalkBack): La imagen principal incluye un `contentDescription`
 *      descriptivo y contextual, mientras que las vistas puramente decorativas (el degradado)
 *      tienen `importantForAccessibility="no"` para evitar ruido auditivo.
 *    - Escalabilidad tipográfica: Todas las fuentes usan unidades `sp` y los contenedores usan
 *      `wrap_content` dentro de un `NestedScrollView` para soportar fuentes ampliadas sin recortes.
 *
 * 2. EXPERIENCIA E INTERFAZ DE USUARIO (UI + UX):
 *    - Jerarquía visual clara: El ojo sigue un flujo natural en vertical:
 *      Imagen inmersiva -> Etiqueta de contexto -> Título de impacto -> Descripción -> CTA.
 *    - Personalización estética: Uso de `ShapeableImageView` con esquinas inferiores curvas,
 *      un `shape` XML (`bg_badge_shape`) para la insignia superior, un gradiente de transición
 *      y un `MaterialButton` con icono, esquinas redondeadas y efecto *ripple* personalizado.
 *    - Ergonomía (Thumb Zone): El botón de acción principal está anclado hacia la zona
 *      inferior de la pantalla, donde el pulgar alcanza con mayor comodidad en dispositivos grandes.
 *
 * 3. BUENAS PRÁCTICAS DE INGENIERÍA ANDROID:
 *    - Cero Hardcoding: Todos los textos, colores y dimensiones están externalizados en `strings.xml`
 *      y `colors.xml` para facilitar el mantenimiento y la futura traducción (i18n).
 *    - Rendimiento de Layout: Uso de `ConstraintLayout` para mantener una jerarquía de vistas
 *      plana y evitar el anidamiento excesivo de `LinearLayout`.
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicialización segura de vistas mediante ViewBinding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindowInsets()
        setupViews()
        setupListeners()
    }

    /**
     * Ajusta los márgenes inferiores dinámicamente para evitar que la barra de navegación
     * del sistema solape el botón de acción principal en pantallas Edge-to-Edge.
     */
    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                0, // Permitimos que la imagen principal llegue hasta el borde superior (status bar)
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }

    /**
     * Configura los recursos visuales iniciales de la pantalla.
     */
    private fun setupViews() {
        // Carga del recurso gráfico del evento (reemplazar R.drawable.img_velada_2027 por el asset real)
         binding.ivVeladaBanner.setImageResource(R.drawable.img_velada_2027)
    }

   
    private fun setupListeners() {
        binding.btnAction.setOnClickListener {
            Toast.makeText(
                this,
                getString(R.string.toast_not_implemented),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}