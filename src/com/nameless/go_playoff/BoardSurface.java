package com.nameless.go_playoff;

import javax.swing.*;
import java.awt.*;

public class BoardSurface extends JPanel {
    private void drawBoard(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle bounds = this.getBounds();
        float offset_x = bounds.width * 0.1f / 2.0f;
        float offset_y = bounds.height * 0.1f / 2.0f;
        float step_x = (bounds.width - 2 * offset_x) / 19;
        float step_y = (bounds.height - 2 * offset_y) / 19;

        for (int i = 0; i <= 19; i++ ) {
            g2d.drawLine((int)offset_x, (int)(offset_y + i * step_y),
                    (int)(bounds.width - offset_x), (int)(offset_y + i * step_y));

            g2d.drawLine((int)(offset_x + i * step_x), (int)offset_y,
                    (int)(offset_x + i * step_x), (int)(bounds.height - offset_y));
        }

        int stone_radius = (int)((step_x > step_y) ? step_y : step_x);

        int stones[] = DataHolder.getInstance().getBoardData();
        for (int i = 0; i < 20; i++ ) {
            for (int j = 0; j < 20; j++) {
                int pos = stones[i * 20 + j];
                if (pos == 0 ) {
                    continue;
                }
                g2d.setColor((pos == 1) ? Color.BLACK : Color.WHITE);
                g2d.fillOval((int)(offset_x + j * step_x - stone_radius / 2 ),
                        (int)(offset_y + i * step_y  - stone_radius / 2),
                        stone_radius, stone_radius);

                g2d.setColor(Color.BLACK);
                g2d.drawOval((int)(offset_x + j * step_x - stone_radius / 2 ),
                        (int)(offset_y + i * step_y  - stone_radius / 2),
                        stone_radius, stone_radius);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }
}
