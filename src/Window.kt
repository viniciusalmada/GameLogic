import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JPanel

class Window : JFrame() {

    private val screen: JPanel by lazy {
        object : JPanel() {
            override fun paintComponent(g: Graphics) {
                g.color = Color.BLUE
                g.drawLine(0, 240, 640, 240)
                g.drawRect(10, 25, 20, 20)
                g.drawOval(30, 20, 40, 30)
                g.color = Color.YELLOW
                g.drawLine(320, 0, 320, 480)
                g.fillRect(110, 125, 120, 120)
                g.fillOval(230, 220, 240, 230)
                g.color = Color.RED
                g.drawString("Eu seria um ótimo Score!", 5, 10)

            }
        }
    }

    init {
        super.getContentPane().add(screen)
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(640, 480)
        isVisible = true
    }
}