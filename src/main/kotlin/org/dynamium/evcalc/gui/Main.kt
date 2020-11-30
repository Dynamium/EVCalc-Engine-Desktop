import androidx.compose.desktop.Window
import androidx.compose.material.MaterialTheme
import org.dynamium.evcalc.gui.MainScreen

fun main() = Window(title = "EVCalc") {
    MaterialTheme {
        MainScreen()
    }
}

