package nl.pinch.apt_ksp_demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import nl.pinch.apt_ksp_demo.model.Person
import nl.pinch.apt_ksp_demo.model.pinchEmail

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val ruben = Person("Ruben", "Weerts")
        findViewById<TextView>(R.id.email).apply {
            text = """
                $ruben 
                   =>
                ${ruben.pinchEmail()}
            """.trimIndent()
        }

    }
}