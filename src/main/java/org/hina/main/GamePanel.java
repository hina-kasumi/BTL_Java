package org.hina.main;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

import static org.hina.main.Game.GAME_HEIGHT;
import static org.hina.main.Game.GAME_WIDTH;

@Getter
public class GamePanel extends JPanel {
    private final Game game;

    public GamePanel(Game game) {
        this.game = game;
        setPanelSize();
    }

    private void setPanelSize(){
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }
}
