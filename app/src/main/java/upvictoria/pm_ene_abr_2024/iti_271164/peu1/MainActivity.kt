package upvictoria.pm_ene_abr_2024.iti_271164.peu1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import upvictoria.pm_ene_abr_2024.iti_271164.peu1.Assets.ClassEntity
import upvictoria.pm_ene_abr_2024.iti_271164.pg1u1_eq02.ClassEntityListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val classEntityListView = ClassEntityListView(this)

        setContentView(classEntityListView)
    }
}