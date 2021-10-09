import java.awt.Dimension
import javax.swing.JFrame

class Window : JFrame() {
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(640, 480)
        isVisible = true
    }
}