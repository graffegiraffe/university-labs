package by.rublevskaya.fractal;

import java.awt.*;
import java.awt.geom.Path2D;
import javax.swing.*;

public class PythagorasTreeFractal extends JPanel {

    final int depthLimit = 6;
    Color[] colors = {new Color(0xEA0BBD), new Color(0x1277E8), new Color(0x12E012)};


    public PythagorasTreeFractal() {
        setPreferredSize(new Dimension(640, 640));
        setBackground(Color.white);
    }

    private void drawTree(Graphics2D g, float x1, float y1, float x2, float y2,
                          int depth) {

        if (depth == depthLimit)
            return;

        float dx = x2 - x1;
        float dy = y1 - y2;

        float x3 = x2 - dy;
        float y3 = y2 - dx;
        float x4 = x1 - dy;
        float y4 = y1 - dx;


        Path2D square = new Path2D.Float();
        square.moveTo(x1, y1);
        square.lineTo(x2, y2);
        square.lineTo(x3, y3);
        square.lineTo(x4, y4);
        square.closePath();

        g.setColor(colors[depth % colors.length]); // Cycle through colors
        g.fill(square);
        g.setColor(Color.lightGray);
        g.draw(square);


        float x5 = x4 + 0.5F * (dx - dy);
        float y5 = y4 - 0.5F * (dx + dy);

        drawTree(g, x4, y4, x5, y5, depth + 1);
        drawTree(g, x5, y5, x3, y3, depth + 1);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree((Graphics2D) g, 275, 500, 375, 500, 0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Pythagoras Tree");
            f.setResizable(false);
            f.add(new PythagorasTreeFractal(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
        
    }
}