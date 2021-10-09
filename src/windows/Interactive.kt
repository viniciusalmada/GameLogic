package windows

import utils.Utils
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
                val x: Int = screen.width / 2 - 20 + px
                val y: Int = screen.height / 2 - 20 + py
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
    private val frametimeMs: Int = (1.0 / fps.toDouble() * 1000).roundToInt()

    private var upKeyControl: Boolean = false
    private var downKeyControl: Boolean = false
    private var rightKeyControl: Boolean = false
    private var leftKeyControl: Boolean = false

    init {
        super.getContentPane().add(screen)
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(640, 480)
        isVisible = true
        super.addKeyListener(object : KeyListener {
            override fun keyTyped(p0: KeyEvent?) {
            }

            override fun keyReleased(e: KeyEvent) {
                setKey(e.keyCode, false)
            }

            override fun keyPressed(e: KeyEvent) {
                setKey(e.keyCode, true)
            }
        })
    }

    private fun setKey(keyCode: Int, status: Boolean) {
        when (keyCode) {
            KeyEvent.VK_ESCAPE -> {
                playing = false
                dispose()
            }
            KeyEvent.VK_UP -> upKeyControl = status
            KeyEvent.VK_DOWN -> downKeyControl = status
            KeyEvent.VK_LEFT -> leftKeyControl = status
            KeyEvent.VK_RIGHT -> rightKeyControl = status
        }
    }

    fun startLoop() {
        var nextUpdate: Long = 0
        while (playing) {
            if (Utils.timeMs() >= nextUpdate) {
                updateGame()
                screen.repaint()
                nextUpdate = Utils.timeMs() + frametimeMs
            }
        }
    }

    private fun updateGame() {
        when {
            upKeyControl -> py -= MOVE_RATE
            downKeyControl -> py += MOVE_RATE
        }

        when {
            leftKeyControl -> px -= MOVE_RATE
            rightKeyControl -> px += MOVE_RATE
        }
    }

    companion object {
        private const val MOVE_RATE = 2
    }
}