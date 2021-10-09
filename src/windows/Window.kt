package windows

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JPanel

class Window : JFrame() {
    private val fps = 1000 / 20 // 50
    private var ct = 0
    private var animate = true

    fun startAnim() {
        var nextUpdate = 0L

        while (animate) {
            if (System.nanoTime() / 1000 >= nextUpdate) {
                ct++
                screen.repaint()

                nextUpdate = System.nanoTime() / 1000 + fps
                if (ct == 100)
                    animate = true
            }
        }
    }

    private val screen: JPanel by lazy {
        object : JPanel() {
            override fun paintComponent(g: Graphics) {
                g.color = Color.WHITE
                g.fillRect(0, 0, screen.width, screen.height)
                g.color = Color.BLUE
                g.drawLine(0, 240 + ct, 640, 240 + ct)
                g.drawRect(10, 25 + ct, 20, 20)
                g.drawOval(30 + ct, 20, 40, 30)
                g.color = Color.YELLOW
                g.drawLine(320 - ct, 0, 320 - ct, 480)
                g.fillRect(110, 125, 120 - ct, 120 - ct)
                g.fillOval(230, 220, 240 + ct, 230)
                g.color = Color.RED
                g.drawString("Eu seria um ótimo Score! $ct", 5, 10)


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