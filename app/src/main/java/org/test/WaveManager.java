package org.test;

import loon.action.sprite.SpriteBatch;
import loon.action.sprite.painting.DrawableGameComponent;
import loon.action.sprite.painting.IGameComponent;
import loon.core.geom.RectBox;
import loon.core.geom.Vector2f;
import loon.core.graphics.LColor;
import loon.core.graphics.LFont;
import loon.core.graphics.opengl.LTexture;
import loon.core.graphics.opengl.LTextures;
import loon.core.timer.GameTime;

public class WaveManager extends DrawableGameComponent implements
        IGameComponent {

    private java.util.ArrayList<Wave> activeWaves;
    private Vector2f drawPosition;
    private LFont font;
    private MainGame game;
    private boolean isLastWave;
    private AnimatedSprite nextWaveMonsterType;
    private LTexture texture;
    private double timeUntilNextWave;
    private int waveNumber;
    private java.util.ArrayList<Wave> waves;

    public WaveManager(MainGame game, Difficulty difficulty) {
        super(game);
        this.waves = new java.util.ArrayList<Wave>();
        this.activeWaves = new java.util.ArrayList<Wave>();
        this.drawPosition = new Vector2f(70f, -4f);
        this.game = game;
        int level = game.getGameplayScreen().getLevel();
        switch (difficulty) {
            case Easy:
                this.waves.add(new Wave(game, 8, 20, 1f, 1500.0, 1, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 8, 20, 1f, 1000.0, 1, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 30, 1.6f, 1000.0, 2, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 30, 1f, 200.0, 2, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 30, 1f, 1000.0, 2, 5 + (level - 1) * 3, 1 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 50, 1f, 1000.0, 2, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 70, 1f, 1000.0, 2, 5 + (level - 1) * 3, 1 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 80, 1f, 200.0, 2, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 1, 600, 1f, 1000.0, 0x19, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 100, 1.6f, 300.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 130, 1f, 1000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 13, 110, 1.2f, 800.0, 3, 5 + (level - 1) * 3, 1 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 2, 0x3e8, 1f, 1000.0, 0x19, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 150, 1f, 200.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 100, 1.6f, 1000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peon));

                this.waves.add(new Wave(game, 10, 50, 1f, 1000.0, 2, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 70, 1f, 1000.0, 2, 5 + (level - 1) * 3, 1 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 80, 1f, 200.0, 2, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 1, 600, 1f, 1000.0, 0x19, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 100, 1.6f, 300.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 130, 1f, 1000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 13, 110, 1.2f, 800.0, 3, 5 + (level - 1) * 3, 1 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 2, 0x3e8, 1f, 1000.0, 0x19, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 150, 1f, 200.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 100, 1.6f, 1000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 130, 1f, 1000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 13, 110, 1.2f, 800.0, 3, 5 + (level - 1) * 3, 1 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 2, 0x3e8, 1f, 1000.0, 0x19, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 150, 1f, 200.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 100, 1.6f, 1000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peon));

                this.waves.add(new Wave(game, 1, 800, 1f, 1000.0, 0x10, 50, 20 + (level - 1) * 3, MonsterType.Chieftain));

                game.Components().add(this);
                this.timeUntilNextWave = -1.0;
                break;

            case Medium:
                this.waves.add(new Wave(game, 10, 40, 1f, 1500.0, 1, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x2d, 1.6f, 1000.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 50, 1f, 1000.0, 1, 10 + (level - 1) * 3, 3 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 5, 100, 1f, 400.0, 4, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 60, 1f, 1000.0, 2, 10 + (level - 1) * 3, 3 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 5, 0x4b, 1f, 200.0, 8, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 80, 1.6f, 1000.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 100, 1f, 1000.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 120, 1f, 1000.0, 2, 10 + (level - 1) * 3, 3 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 130, 1f, 200.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 150, 1f, 1000.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 1, 0x7d0, 1f, 1000.0, 0x19, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 0xaf, 1.6f, 300.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 200, 1f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 13, 200, 1.2f, 800.0, 3, 10 + (level - 1) * 3, 3 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 3, 0x3e8, 1f, 1000.0, 20, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 200, 1f, 200.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 220, 1.6f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 1, 0xbb8, 1f, 1000.0, 30, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 250, 1f, 1000.0, 4, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 5, 800, 1f, 3000.0, 20, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 300, 1f, 1000.0, 4, 10 + (level - 1) * 3, 3 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 300, 1f, 1000.0, 4, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 350, 1f, 1000.0, 4, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 1, 0xdac, 1f, 1000.0, 4, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 400, 1f, 200.0, 5, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 450, 1f, 1000.0, 5, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 500, 1f, 500.0, 5, 10 + (level - 1) * 3, 3 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 1, 0x157c, 1f, 1000.0, 40, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 4, 0x4b0, 1f, 1000.0, 40, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Doctor));

                this.waves.add(new Wave(game, 2, 800, 1f, 1000.0, 0x10, 50, 25 + (level - 1) * 3, MonsterType.Chieftain));

                game.Components().add(this);
                this.timeUntilNextWave = -1.0;
                break;
            case Hard:
                this.waves.add(new Wave(game, 12, 60, 1f, 1000.0, 1, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 12, 0x41, 1.8f, 1000.0, 1, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 60, 1f, 1000.0, 1, 15 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 12, 80, 1f, 1000.0, 1, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 12, 130, 1f, 1000.0, 1, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 6, 100, 1f, 2000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Doctor));
                this.waves.add(new Wave(game, 12, 120, 1f, 1000.0, 1, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 90, 1f, 1000.0, 1, 15 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 12, 150, 1f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
                this.waves.add(new Wave(game, 12, 150, 1.8f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 190, 1f, 300.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 8, 100, 1f, 1000.0, 2, 15 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 110, 1f, 200.0, 2, 15 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 12, 240, 1.8f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 1, 0x4b0, 1f, 1000.0, 0x19, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 12, 200, 1.8f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 1, 0x3e8, 1f, 1000.0, 0x19, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 1, 0x4b0, 1f, 1000.0, 0x19, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 12, 200, 1.8f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 12, 150, 1.8f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 190, 1f, 300.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Berserker));
                this.waves.add(new Wave(game, 8, 100, 1f, 1000.0, 2, 15 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 110, 1f, 200.0, 2, 15 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Chicken));
                this.waves.add(new Wave(game, 12, 240, 1.8f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 1, 0x4b0, 1f, 1000.0, 0x19, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 12, 200, 1.8f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 1, 0x3e8, 1f, 1000.0, 0x19, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 1, 0x4b0, 1f, 1000.0, 0x19, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 12, 200, 1.8f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
                this.waves.add(new Wave(game, 8, 100, 1f, 1000.0, 2, 15 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Chicken));

                this.waves.add(new Wave(game, 4, 800, 1f, 1000.0, 0x10, 50, 30 + (level - 1) * 3, MonsterType.Chieftain));

                game.Components().add(this);
                this.timeUntilNextWave = -1.0;
                break;

            default:
                throw new RuntimeException("Unknown difficulty in wavemanager!");
        }
        int baseXueLang1 = 5;
        int bassNum1 = 1;
        int jiXueLang1 = 3;
        switch (difficulty) {
            case Easy:
                baseXueLang1 = 5;
                bassNum1 = 1;
                jiXueLang1 = 3;
                break;
            case Medium:
                baseXueLang1 = 7;
                bassNum1 = 2;
                jiXueLang1 = 4;
                break;
            case Hard:
                baseXueLang1 = 10;
                bassNum1 = 4;
                jiXueLang1 = 5;
                break;
        }

        for (int i = 0; i < 5; i++) {
            this.waves.add(new Wave(game, 0x12, 200 + baseXueLang1 * waves.size(), 1f, 1000.0, 2, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 12, 260 + baseXueLang1 * waves.size(), 1.8f, 600.0, 2, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 3, 500 + baseXueLang1 * waves.size(), 1f, 2000.0, 4, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Doctor));
            this.waves.add(new Wave(game, 8, 0x9b + jiXueLang1 * waves.size(), 1f, 1000.0, 3, 5 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 12, 220 + baseXueLang1 * waves.size(), 1f, 300.0, 2, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 12, 260 + baseXueLang1 * waves.size(), 1f, 1000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 10, 280 + baseXueLang1 * waves.size(), 2f, 1000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 10, 170 + jiXueLang1 * waves.size(), 1f, 600.0, 3, 5 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 10, 360 + baseXueLang1 * waves.size(), 1.8f, 200.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 10, 500 + baseXueLang1 * waves.size(), 1f, 1000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 1, 0xdac + baseXueLang1 * waves.size(), 1f, 1000.0, 30, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Chieftain));
            this.waves.add(new Wave(game, 10, 310 + jiXueLang1 * waves.size(), 1f, 1000.0, 3, 5 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 10, 500 + baseXueLang1 * waves.size(), 1f, 1000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 5, 900 + baseXueLang1 * waves.size(), 1f, 2000.0, 6, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Doctor));
            this.waves.add(new Wave(game, 20, 550 + baseXueLang1 * waves.size(), 1f, 1000.0, 2, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 10, 500 + jiXueLang1 * waves.size(), 1f, 1000.0, 3, 5 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 10, 700 + baseXueLang1 * waves.size(), 1.8f, 400.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 12, 800 + baseXueLang1 * waves.size(), 1f, 5000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 10, 700 + baseXueLang1 * waves.size(), 1.8f, 400.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 12, 800 + baseXueLang1 * waves.size(), 1f, 5000.0, 3, 5 + (level - 1) * 3, 5 + (level - 1) * 3, MonsterType.Peasant));

            this.waves.add(new Wave(game, bassNum1, 0xdac + baseXueLang1 * waves.size(), 1f, 1000.0, 3, 50, 20 + (level - 1) * 3, MonsterType.Chieftain));
        }

        int baseXueLang2 = 6;
        int bassNum2 = 1;
        int jiXueLang2 = 4;
        switch (difficulty) {
            case Easy:
                baseXueLang2 = 6;
                bassNum2 = 1;
                jiXueLang2 = 4;
                break;
            case Medium:
                baseXueLang2 = 8;
                bassNum2 = 2;
                jiXueLang2 = 5;
                break;
            case Hard:
                baseXueLang2 = 11;
                bassNum2 = 4;
                jiXueLang2 = 6;
                break;
        }

        for (int i = 0; i < 5; i++) {
            this.waves.add(new Wave(game, 0x12, 200 + baseXueLang2 * waves.size(), 1f, 1000.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 12, 260 + baseXueLang2 * waves.size(), 1.8f, 600.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 3, 500 + baseXueLang2 * waves.size(), 1f, 2000.0, 4, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Doctor));
            this.waves.add(new Wave(game, 8, 0x9b + jiXueLang2 * waves.size(), 1f, 1000.0, 3, 10 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 12, 220 + baseXueLang2 * waves.size(), 1f, 300.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 12, 260 + baseXueLang2 * waves.size(), 1f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 10, 280 + baseXueLang2 * waves.size(), 2f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 10, 170 + jiXueLang2 * waves.size(), 1f, 600.0, 3, 10 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 10, 360 + baseXueLang2 * waves.size(), 1.8f, 200.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 10, 500 + baseXueLang2 * waves.size(), 1f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 1, 0xdac + baseXueLang2 * waves.size(), 1f, 1000.0, 30, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Chieftain));
            this.waves.add(new Wave(game, 10, 310 + jiXueLang2 * waves.size(), 1f, 1000.0, 3, 10 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 10, 500 + baseXueLang2 * waves.size(), 1f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 5, 900 + baseXueLang2 * waves.size(), 1f, 2000.0, 6, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Doctor));
            this.waves.add(new Wave(game, 20, 550 + baseXueLang2 * waves.size(), 1f, 1000.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 10, 500 + jiXueLang2 * waves.size(), 1f, 1000.0, 3, 10 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 10, 700 + baseXueLang2 * waves.size(), 1.8f, 400.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 12, 800 + baseXueLang2 * waves.size(), 1f, 5000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 10, 700 + baseXueLang2 * waves.size(), 1.8f, 400.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 12, 800 + baseXueLang2 * waves.size(), 1f, 5000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));

            this.waves.add(new Wave(game, bassNum2, 0xdac + baseXueLang2 * waves.size(), 1f, 1000.0, 3, 50, 25 + (level - 1) * 3, MonsterType.Chieftain));
        }

        int baseXueLang3 = 7;
        int bassNum3 = 1;
        int jiXueLang3 = 5;
        switch (difficulty) {
            case Easy:
                baseXueLang3 = 7;
                bassNum3 = 1;
                jiXueLang3 = 5;
                break;
            case Medium:
                baseXueLang3 = 9;
                bassNum3 = 2;
                jiXueLang3 = 6;
                break;
            case Hard:
                baseXueLang3 = 12;
                bassNum3 = 4;
                jiXueLang3 = 7;
                break;
        }
        for (int i = 0; i < 5; i++) {
            this.waves.add(new Wave(game, 0x12, 200 + baseXueLang3 * waves.size(), 1f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 12, 260 + baseXueLang3 * waves.size(), 1.8f, 600.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 3, 500 + baseXueLang3 * waves.size(), 1f, 2000.0, 4, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Doctor));
            this.waves.add(new Wave(game, 8, 0x9b + jiXueLang3 * waves.size(), 1f, 1000.0, 3, 15 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 12, 220 + baseXueLang3 * waves.size(), 1f, 300.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 12, 260 + baseXueLang3 * waves.size(), 1f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 10, 280 + baseXueLang3 * waves.size(), 2f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 10, 170 + jiXueLang3 * waves.size(), 1f, 600.0, 3, 15 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 10, 360 + baseXueLang3 * waves.size(), 1.8f, 200.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 10, 500 + baseXueLang3 * waves.size(), 1f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 1, 0xdac + baseXueLang3 * waves.size(), 1f, 1000.0, 30, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chieftain));
            this.waves.add(new Wave(game, 10, 310 + jiXueLang3 * waves.size(), 1f, 1000.0, 3, 15 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 10, 500 + baseXueLang3 * waves.size(), 1f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 5, 900 + baseXueLang3 * waves.size(), 1f, 2000.0, 6, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Doctor));
            this.waves.add(new Wave(game, 20, 550 + baseXueLang3 * waves.size(), 1f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 10, 500 + jiXueLang3 * waves.size(), 1f, 1000.0, 3, 15 + (level - 1) * 3, (level - 1) * 3, MonsterType.Chicken));
            this.waves.add(new Wave(game, 10, 700 + baseXueLang3 * waves.size(), 1.8f, 400.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 12, 800 + baseXueLang3 * waves.size(), 1f, 5000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 10, 700 + baseXueLang3 * waves.size(), 1.8f, 400.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 12, 800 + baseXueLang3 * waves.size(), 1f, 5000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));

            this.waves.add(new Wave(game, bassNum3, 0xdac + baseXueLang3 * waves.size(), 1f, 1000.0, 3, 50, 30 + (level - 1) * 3, MonsterType.Chieftain));
        }

        for (int i = 0; i < 4; i++) {
            this.waves.add(new Wave(game, 8, 0xfff + baseXueLang3 * waves.size(), 1f, 1000.0, 3, 50, 35 + (level - 1) * 3, MonsterType.Chieftain));
        }

        int baseXueLang4 = 13;
        for (int i = 0; i < 10; i++) {
            this.waves.add(new Wave(game, 18, 0xfff + baseXueLang4 * waves.size(), 1.8f, 400.0, 3, 20 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 18, 0xfff + baseXueLang4 * waves.size(), 1f, 5000.0, 3, 20 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 20, 0xfff + baseXueLang4 * waves.size(), 1f, 1000.0, 2, 20 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 8, 0xfff + baseXueLang4 * waves.size(), 1f, 2000.0, 4, 20 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Doctor));
            this.waves.add(new Wave(game, 8, 0xfff + baseXueLang4 * waves.size(), 1f, 1000.0, 30, 20 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chieftain));
        }

        int baseXueLang5 = 13;
        for (int i = 0; i < 10; i++) {
            this.waves.add(new Wave(game, 18, 0xfff + baseXueLang5 * waves.size(), 1.8f, 400.0, 3, 25 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Peon));
            this.waves.add(new Wave(game, 18, 0xfff + baseXueLang5 * waves.size(), 1f, 5000.0, 3, 25 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Peasant));
            this.waves.add(new Wave(game, 20, 0xfff + baseXueLang5 * waves.size(), 1f, 1000.0, 2, 25 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Berserker));
            this.waves.add(new Wave(game, 8, 0xfff + baseXueLang5 * waves.size(), 1f, 2000.0, 4, 25 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Doctor));
            this.waves.add(new Wave(game, 8, 0xfff + baseXueLang5 * waves.size(), 1f, 1000.0, 30, 25 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Chieftain));
        }

    }

    public final void AddMonsterToCurrentWave(Monster monster) {
        this.waves.get(this.waveNumber - 1).AddMonster(monster);
    }

    @Override
    public void draw(SpriteBatch batch, GameTime gameTime) {
        batch.draw(this.texture, this.drawPosition, LColor.white);
        batch.drawString(this.font, LanguageResources.getWave() + " "
                        + this.waveNumber + " " + LanguageResources.getof() + " "
                        + this.waves.size(), this.drawPosition.x + 17f,
                this.drawPosition.y + 2f, LColor.white);
        if (!this.isLastWave) {
            Utils.DrawStringAlignRight(batch, this.font,
                    LanguageResources.getNext(), this.drawPosition.x + 136f,
                    this.drawPosition.y + 7f, LColor.white);
            int num2 = ((int) Math.ceil(this.timeUntilNextWave)) / 0x3e8;
            batch.drawString(this.font, LanguageResources.getNextWave() + " "
                            + num2, this.drawPosition.x + 17f,
                    this.drawPosition.y + 18f, LColor.white);
        }
    }

    public final java.util.ArrayList<Monster> GetAllActiveMonsters() {
        java.util.ArrayList<Monster> list = new java.util.ArrayList<Monster>();
        for (Wave wave : this.game.getGameplayScreen().getWaveManager().activeWaves) {
            list.addAll(wave.getMonsters());
        }
        return list;
    }

    public final Monster GetSelectedMonster(RectBox touchRect) {
        for (Wave wave : this.activeWaves) {
            Monster selectedMonster = wave.GetSelectedMonster(touchRect);
            if (selectedMonster != null) {
                return selectedMonster;
            }
        }
        return null;
    }

    @Override
    protected void loadContent() {
        this.texture = LTextures.loadTexture("assets/wave_x_of_y.png");
        this.font = LFont.getFont(10);
        super.loadContent();
    }

    public final void Remove() {
        for (int i = 0; i < this.activeWaves.size(); i++) {
            this.activeWaves.get(i).Remove();
        }
        if (this.nextWaveMonsterType != null) {
            super.getGame().Components().remove(this.nextWaveMonsterType);
        }
        super.getGame().Components().remove(this);
    }

    public final void RemoveActiveWave(Wave wave) {
        this.activeWaves.remove(wave);
    }

    @Override
    public void update(GameTime gameTime) {
        if (GameplayScreen.getGameState() == GameState.Started) {
            this.timeUntilNextWave -= gameTime.getMilliseconds();
            if (this.timeUntilNextWave < 0.0) {
                if (this.waveNumber < this.waves.size()) {
                    this.activeWaves.add(this.waves.get(this.waveNumber));
                    this.waves.get(this.waveNumber).setWaveState(
                            WaveState.Started);
                    if (this.nextWaveMonsterType != null) {
                        this.game.Components().remove(this.nextWaveMonsterType);
                    }
                    this.timeUntilNextWave = 8000.0;
                    if ((this.waveNumber + 1) < this.waves.size()) {
                        this.nextWaveMonsterType = AnimatedSpriteMonster
                                .GetSmallAnimatedSpriteMonster(this.game,
                                        this.waves.get(this.waveNumber + 1)
                                                .getMonsterType());
                        this.nextWaveMonsterType.setAnimationSpeedRatio(3);
                        this.nextWaveMonsterType.setObeyGameOpacity(false);
                        this.game.Components().add(this.nextWaveMonsterType);
                    } else {
                        this.isLastWave = true;
                    }
                    this.waveNumber++;
                } else {
                    boolean flag = true;
                    for (Wave wave : this.activeWaves) {
                        //?testing
                        if (wave.getMonsters().size() > 0) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        this.game.getGameplayScreen().Win();
                    }
                }
            }
        }
        super.update(gameTime);
    }

    public final int getRemainingWaves() {
        return this.waves.size();
    }
}