package upvictoria.pm_ene_abr_2024.iti_271164.peu1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val render = SurfaceWrapper(this)
        setContentView(render)
    }
}