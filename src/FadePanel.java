import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FadePanel extends JPanel {
    private float alpha = 0f;

    public FadePanel(JPanel content) {
        setLayout(new GridLayout(1, 1));  // Ensures child fills all space
        setOpaque(false);
        add(content); // No need for BorderLayout.CENTER

        startFadeIn();
    }

    private void startFadeIn() {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.01f;
                if (alpha >= 1f) {
                    alpha = 1f;
                    ((Timer) e.getSource()).stop();
                }
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        super.paintComponent(g2d);
        g2d.dispose();
    }
}