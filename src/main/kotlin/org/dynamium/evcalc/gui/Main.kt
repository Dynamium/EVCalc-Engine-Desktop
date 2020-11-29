import androidx.compose.desktop.Window
import androidx.compose.material.MaterialTheme
import org.dynamium.evcalc.gui.MainView

fun main() = Window(title = "EVCalc") {
    MaterialTheme {
        MainView()
    }
}

