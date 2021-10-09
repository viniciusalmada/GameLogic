import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.math.roundToInt

class Interactive : JFrame() {
    private val screen: JPanel by lazy {
        object : JPanel() {
            override fun paintComponent(g: Graphics) {
                g.color = Color.WHITE
                g.fillRect(0, 0, screen.width, screen.height)
                val x: Int = screen.getWidth() / 2 - 20 + px
                val y: Int = screen.getHeight() / 2 - 20 + py
                g.color = Color.BLUE
                g.fillRect(x, y, 40, 40)
                g.drawString("Agora eu estou em $x x $y", 5, 10)
            }
        }
    }
    private var px: Int = 0
    private var py: Int = 0
    private var playing: Boolean = true

    private val fps: Int = 60
    private val frametimeMs: Int = (1.0/fps.toDouble() * 1000).roundToInt()

    init {
        super.getContentPane().add(screen)
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(640, 480)
        isVisible = true
        super.addKeyListener(object : KeyListener {
            override fun keyTyped(p0: KeyEvent?) {
            }

            override fun keyReleased(p0: KeyEvent?) {
            }

            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_ESCAPE -> {
                        playing = false
                        dispose()
                    }
                    KeyEvent.VK_UP -> py -= MOVE_RATE
                    KeyEvent.VK_DOWN -> py += MOVE_RATE
                    KeyEvent.VK_LEFT -> px -= MOVE_RATE
                    KeyEvent.VK_RIGHT -> px += MOVE_RATE
                }
//                screen.repaint()
            }
        })
    }

    fun start() {
        var nextUpdate: Long = 0
        while (playing) {
            if (Utils.timeMs() >= nextUpdate) {
                screen.repaint()
                nextUpdate = Utils.timeMs() + frametimeMs
            }
        }
    }

    companion object {
        private const val MOVE_RATE = 1
    }
}