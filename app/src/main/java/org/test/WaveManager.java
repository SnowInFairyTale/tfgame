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
                this.waves.add(new Wave(game, 8, 20 + level * 5, 1f, 1500.0, 1, 5, 5, MonsterType.Peasant));
                this.waves.add(new Wave(game, 8, 20 + level * 5, 1f, 1000.0, 1, 5, 5, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 30 + level * 5, 1.6f, 1000.0, 2, 5, 5, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 30 + level * 5, 1f, 200.0, 2, 5, 5, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 30 + level * 5, 1f, 1000.0, 2, 5, 5, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 50 + level * 5, 1f, 1000.0, 2, 5, 5, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 70 + level * 5, 1f, 1000.0, 2, 5, 5, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 80 + level * 5, 1f, 200.0, 2, 5, 5, MonsterType.Berserker));
                this.waves.add(new Wave(game, 1, 600 + level * 5, 1f, 1000.0, 0x19, 5, 5, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 100 + level * 5, 1.6f, 300.0, 3, 5, 5, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 130 + level * 5, 1f, 1000.0, 3, 5, 5, MonsterType.Berserker));
                this.waves.add(new Wave(game, 13, 110 + level * 5, 1.2f, 800.0, 3, 5, 5, MonsterType.Chicken));
                this.waves.add(new Wave(game, 2, 0x3e8 + level * 5, 1f, 1000.0, 0x19, 5, 5, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 150 + level * 5, 1f, 200.0, 3, 5, 5, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 100 + level * 5, 1.6f, 1000.0, 3, 5, 5, MonsterType.Peon));
                game.Components().add(this);
                this.timeUntilNextWave = -1.0;
                break;

            case Medium:
                this.waves.add(new Wave(game, 10, 40 + level * 5, 1f, 1500.0, 1, 10, 10, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x2d + level * 5, 1.6f, 1000.0, 2, 10, 10, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 50 + level * 5, 1f, 1000.0, 1, 10, 10, MonsterType.Chicken));
                this.waves.add(new Wave(game, 5, 100 + level * 5, 1f, 400.0, 4, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 60 + level * 5, 1f, 1000.0, 2, 10, 10, MonsterType.Chicken));
                this.waves.add(new Wave(game, 5, 0x4b + level * 5, 1f, 200.0, 8, 10, 10, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 80 + level * 5, 1.6f, 1000.0, 2, 10, 10, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 100 + level * 5, 1f, 1000.0, 2, 10, 10, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 120 + level * 5, 1f, 1000.0, 2, 10, 10, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 130 + level * 5, 1f, 200.0, 2, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 150 + level * 5, 1f, 1000.0, 2, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 1, 0x7d0 + level * 5, 1f, 1000.0, 0x19, 10, 10, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 0xaf + level * 5, 1.6f, 300.0, 3, 10, 10, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 200 + level * 5, 1f, 1000.0, 3, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 13, 200 + level * 5, 1.2f, 800.0, 3, 10, 10, MonsterType.Chicken));
                this.waves.add(new Wave(game, 3, 0x3e8 + level * 5, 1f, 1000.0, 20, 10, 10, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 200 + level * 5, 1f, 200.0, 3, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 220 + level * 5, 1.6f, 1000.0, 3, 10, 10, MonsterType.Peon));
                this.waves.add(new Wave(game, 1, 0xbb8 + level * 5, 1f, 1000.0, 30, 10, 10, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 250 + level * 5, 1f, 1000.0, 4, 10, 10, MonsterType.Peasant));
                this.waves.add(new Wave(game, 5, 800 + level * 5, 1f, 3000.0, 20, 10, 10, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 300 + level * 5, 1f, 1000.0, 4, 10, 10, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 300 + level * 5, 1f, 1000.0, 4, 10, 0, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 350 + level * 5, 1f, 1000.0, 4, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 1, 0xdac + level * 5, 1f, 1000.0, 4, 10, 10, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 400 + level * 5, 1f, 200.0, 5, 10, 10, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 450 + level * 5, 1f, 1000.0, 5, 10, 10, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 500 + level * 5, 1f, 500.0, 5, 10, 10, MonsterType.Chicken));
                this.waves.add(new Wave(game, 1, 0x157c + level * 5, 1f, 1000.0, 40, 10, 10, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 4, 0x4b0 + level * 5, 1f, 1000.0, 40, 10, 10, MonsterType.Doctor));
                game.Components().add(this);
                this.timeUntilNextWave = -1.0;
                break;
            case Hard:
                this.waves.add(new Wave(game, 12, 60 + level * 5, 1f, 1000.0, 1, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 12, 0x41 + level * 5, 1.8f, 1000.0, 1, 20, 20, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 60 + level * 5, 1f, 1000.0, 1, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 12, 80 + level * 5, 1f, 1000.0, 1, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 12, 130 + level * 5, 1f, 1000.0, 1, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 6, 100 + level * 5, 1f, 2000.0, 2, 20, 20, MonsterType.Doctor));
                this.waves.add(new Wave(game, 12, 120 + level * 5, 1f, 1000.0, 1, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 90 + level * 5, 1f, 1000.0, 1, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 12, 150 + level * 5, 1f, 1000.0, 2, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 12, 150 + level * 5, 1.8f, 1000.0, 2, 20, 20, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 190 + level * 5, 1f, 300.0, 2, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 8, 100 + level * 5, 1f, 1000.0, 2, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 110 + level * 5, 1f, 200.0, 2, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 100 + level * 5, 1f, 1000.0, 2, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 120 + level * 5, 1f, 1000.0, 2, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 130 + level * 5, 1f, 200.0, 2, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 150 + level * 5, 1f, 1000.0, 2, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 1, 0x7d0 + level * 5, 1f, 1000.0, 0x19, 20, 20, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 0xaf + level * 5, 1.6f, 300.0, 3, 20, 20, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 200 + level * 5, 1f, 1000.0, 3, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 13, 200 + level * 5, 1.2f, 800.0, 3, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 3, 0x3e8 + level * 5, 1f, 1000.0, 20, 20, 20, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 200 + level * 5, 1f, 200.0, 3, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 10, 220 + level * 5, 1.6f, 1000.0, 3, 20, 20, MonsterType.Peon));
                this.waves.add(new Wave(game, 1, 0xbb8 + level * 5, 1f, 1000.0, 30, 20, 20, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 250 + level * 5, 1f, 1000.0, 4, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 5, 800 + level * 5, 1f, 3000.0, 20, 20, 20, MonsterType.Doctor));
                this.waves.add(new Wave(game, 10, 300 + level * 5, 1f, 1000.0, 4, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 300 + level * 5, 1f, 1000.0, 4, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 350 + level * 5, 1f, 1000.0, 4, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 1, 0xdac + level * 5, 1f, 1000.0, 4, 20, 20, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 400 + level * 5, 1f, 200.0, 5, 20, 20, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 450 + level * 5, 1f, 1000.0, 5, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 500 + level * 5, 1f, 500.0, 5, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 1, 0x157c + level * 5, 1f, 1000.0, 40, 20, 20, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 4, 0x4b0 + level * 5, 1f, 1000.0, 40, 20, 20, MonsterType.Doctor));

                switch (level) {
                    case 1:
                        this.waves.add(new Wave(game, 12, 500, 1.8f, 1000.0, 2, 20, 20, MonsterType.Peon));
                        this.waves.add(new Wave(game, 2, 800, 1f, 1000.0, 0x19, 20, 20, MonsterType.Chieftain));
                        break;
                    case 2:
                        this.waves.add(new Wave(game, 12, 600, 1.8f, 1000.0, 2, 30, 30, MonsterType.Peon));
                        this.waves.add(new Wave(game, 3, 800, 1f, 1000.0, 0x19, 30, 30, MonsterType.Chieftain));
                        this.waves.add(new Wave(game, 12, 600, 1.8f, 1000.0, 2, 30, 30, MonsterType.Peasant));
                        this.waves.add(new Wave(game, 3, 800, 1f, 1000.0, 0x19, 30, 30, MonsterType.Berserker));
                        break;
                    case 3:
                        this.waves.add(new Wave(game, 12, 700, 1.8f, 1000.0, 2, 30, 30, MonsterType.Peon));
                        this.waves.add(new Wave(game, 3, 700, 1f, 1000.0, 0x19, 30, 30, MonsterType.Chicken));
                        this.waves.add(new Wave(game, 12, 700, 1.8f, 1000.0, 2, 30, 30, MonsterType.Peasant));
                        this.waves.add(new Wave(game, 4, 1000, 1f, 1000.0, 0x19, 40, 40, MonsterType.Berserker));
                        this.waves.add(new Wave(game, 12, 1000, 1.8f, 1000.0, 2, 40, 40, MonsterType.Doctor));
                        this.waves.add(new Wave(game, 4, 1000, 1f, 1000.0, 0x19, 40, 40, MonsterType.Chieftain));
                        break;
                }
                this.waves.add(new Wave(game, 12, 500, 1.8f, 1000.0, 2, 30, 30, MonsterType.Peon));
                this.waves.add(new Wave(game, 2, 800, 1f, 1000.0, 0x19, 30, 30, MonsterType.Chieftain));
                game.Components().add(this);
                this.timeUntilNextWave = -1.0;
                break;

            default:
                throw new RuntimeException("Unknown difficulty in wavemanager!");
        }
        this.waves.add(new Wave(game, 0x12, 200 + (level - 1) * 50, 1f, 1000.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1.8f, 600.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 3, 500 + (level - 1) * 50, 1f, 2000.0, 4, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 8, 0x9b + (level - 1) * 50, 1f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 12, 220 + (level - 1) * 50, 1f, 300.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 280 + (level - 1) * 50, 2f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 170 + (level - 1) * 50, 1f, 600.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 360 + (level - 1) * 50, 1.8f, 200.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 10 + (level - 1) * 3, 10, MonsterType.Berserker));
        this.waves.add(new Wave(game, 1, 0xdac + (level - 1) * 50, 1f, 1000.0, 30, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Chieftain));
        this.waves.add(new Wave(game, 10, 310 + (level - 1) * 50, 1f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 900 + (level - 1) * 50, 1f, 2000.0, 6, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 20, 550 + (level - 1) * 50, 1f, 1000.0, 2, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 700 + (level - 1) * 50, 1.8f, 400.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 12, 800 + (level - 1) * 50, 1f, 5000.0, 3, 10 + (level - 1) * 3, 10 + (level - 1) * 3, MonsterType.Peasant));


        this.waves.add(new Wave(game, 0x12, 200 + (level - 1) * 50, 1f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1.8f, 600.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 3, 500 + (level - 1) * 50, 1f, 2000.0, 4, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 8, 0x9b + (level - 1) * 50, 1f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 12, 220 + (level - 1) * 50, 1f, 300.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 280 + (level - 1) * 50, 2f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 170 + (level - 1) * 50, 1f, 600.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 360 + (level - 1) * 50, 1.8f, 200.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 1, 0xdac + (level - 1) * 50, 1f, 1000.0, 30, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chieftain));
        this.waves.add(new Wave(game, 10, 310 + (level - 1) * 50, 1f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 900 + (level - 1) * 50, 1f, 2000.0, 6, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 20, 550 + (level - 1) * 50, 1f, 1000.0, 2, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 700 + (level - 1) * 50, 1.8f, 400.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 12, 800 + (level - 1) * 50, 1f, 5000.0, 3, 15 + (level - 1) * 3, 15 + (level - 1) * 3, MonsterType.Peasant));


        this.waves.add(new Wave(game, 0x12, 200 + (level - 1) * 50, 1f, 1000.0, 2, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1.8f, 600.0, 2, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 3, 500 + (level - 1) * 50, 1f, 2000.0, 4, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 8, 0x9b + (level - 1) * 50, 1f, 1000.0, 3, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 12, 220 + (level - 1) * 50, 1f, 300.0, 2, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1f, 1000.0, 3, 20 + (level - 1) * 3, 0 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 280 + (level - 1) * 50, 2f, 1000.0, 3, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 170 + (level - 1) * 50, 1f, 600.0, 3, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 360 + (level - 1) * 50, 1.8f, 200.0, 3, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 1, 0xdac + (level - 1) * 50, 1f, 1000.0, 30, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Chieftain));
        this.waves.add(new Wave(game, 10, 310 + (level - 1) * 50, 1f, 1000.0, 3, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 900 + (level - 1) * 50, 1f, 2000.0, 6, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 20, 550 + (level - 1) * 50, 1f, 1000.0, 2, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 700 + (level - 1) * 50, 1.8f, 400.0, 3, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 12, 800 + (level - 1) * 50, 1f, 5000.0, 3, 20 + (level - 1) * 3, 20 + (level - 1) * 3, MonsterType.Peasant));


        this.waves.add(new Wave(game, 0x12, 200 + (level - 1) * 50, 1f, 1000.0, 2, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1.8f, 600.0, 2, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 3, 500 + (level - 1) * 50, 1f, 2000.0, 4, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 8, 0x9b + (level - 1) * 50, 1f, 1000.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 12, 220 + (level - 1) * 50, 1f, 300.0, 2, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1f, 1000.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 280 + (level - 1) * 50, 2f, 1000.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 170 + (level - 1) * 50, 1f, 600.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 360 + (level - 1) * 50, 1.8f, 200.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 1, 0xdac + (level - 1) * 50, 1f, 1000.0, 30, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Chieftain));
        this.waves.add(new Wave(game, 10, 310 + (level - 1) * 50, 1f, 1000.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 900 + (level - 1) * 50, 1f, 2000.0, 6, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 20, 550 + (level - 1) * 50, 1f, 1000.0, 2, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 700 + (level - 1) * 50, 1.8f, 400.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 12, 800 + (level - 1) * 50, 1f, 5000.0, 3, 25 + (level - 1) * 3, 25 + (level - 1) * 3, MonsterType.Peasant));


        this.waves.add(new Wave(game, 0x12, 200 + (level - 1) * 50, 1f, 1000.0, 2, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1.8f, 600.0, 2, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 3, 500 + (level - 1) * 50, 1f, 2000.0, 4, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 8, 0x9b + (level - 1) * 50, 1f, 1000.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 12, 220 + (level - 1) * 50, 1f, 300.0, 2, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1f, 1000.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 280 + (level - 1) * 50, 2f, 1000.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 170 + (level - 1) * 50, 1f, 600.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 360 + (level - 1) * 50, 1.8f, 200.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 1, 0xdac + (level - 1) * 50, 1f, 1000.0, 30, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Chieftain));
        this.waves.add(new Wave(game, 10, 310 + (level - 1) * 50, 1f, 1000.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 900 + (level - 1) * 50, 1f, 2000.0, 6, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 20, 550 + (level - 1) * 50, 1f, 1000.0, 2, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 700 + (level - 1) * 50, 1.8f, 400.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 12, 800 + (level - 1) * 50, 1f, 5000.0, 3, 30 + (level - 1) * 3, 30 + (level - 1) * 3, MonsterType.Peasant));

        this.waves.add(new Wave(game, 0x12, 200 + (level - 1) * 50, 1f, 1000.0, 2, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1.8f, 600.0, 2, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 500, 1f, 2000.0, 4, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 8, 0x9b + (level - 1) * 50, 1f, 1000.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 12, 220 + (level - 1) * 50, 1f, 300.0, 2, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1f, 1000.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 280 + (level - 1) * 50, 2f, 1000.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 170 + (level - 1) * 50, 1f, 600.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 360 + (level - 1) * 50, 1.8f, 200.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 3, 0xdac + (level - 1) * 50, 1f, 1000.0, 30, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Chieftain));
        this.waves.add(new Wave(game, 10, 310 + (level - 1) * 50, 1f, 1000.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 900 + (level - 1) * 50, 1f, 2000.0, 6, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 20, 550 + (level - 1) * 50, 1f, 1000.0, 2, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 700 + (level - 1) * 50, 1.8f, 400.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 12, 800 + (level - 1) * 50, 1f, 5000.0, 3, 35 + (level - 1) * 3, 35 + (level - 1) * 3, MonsterType.Peasant));

        this.waves.add(new Wave(game, 0x12, 200 + (level - 1) * 50, 1f, 1000.0, 2, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1.8f, 600.0, 2, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 500 + (level - 1) * 50, 1f, 2000.0, 4, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 8, 0x9b + (level - 1) * 50, 1f, 1000.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 12, 220 + (level - 1) * 50, 1f, 300.0, 2, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 50, 1f, 1000.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 280 + (level - 1) * 50, 2f, 1000.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 170 + (level - 1) * 50, 1f, 600.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 360 + (level - 1) * 50, 1.8f, 200.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 3, 0xdac + (level - 1) * 50, 1f, 1000.0, 30, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Chieftain));
        this.waves.add(new Wave(game, 10, 310 + (level - 1) * 50, 1f, 1000.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 900 + (level - 1) * 50, 1f, 2000.0, 6, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Doctor));
        this.waves.add(new Wave(game, 20, 550 + (level - 1) * 50, 1f, 1000.0, 2, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 50, 1f, 1000.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 700 + (level - 1) * 50, 1.8f, 400.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Peon));
        this.waves.add(new Wave(game, 12, 800 + (level - 1) * 50, 1f, 5000.0, 3, 40 + (level - 1) * 3, 40 + (level - 1) * 3, MonsterType.Peasant));

        this.waves.add(new Wave(game, 0x12, 200 + (level - 1) * 100, 1f, 1000.0, 2, 45, 45, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 100, 1.8f, 600.0, 2, 45, 45, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 500 + (level - 1) * 100, 1f, 2000.0, 4, 45, 45, MonsterType.Doctor));
        this.waves.add(new Wave(game, 8, 0x9b + (level - 1) * 100, 1f, 1000.0, 3, 45, 45, MonsterType.Chicken));
        this.waves.add(new Wave(game, 12, 220 + (level - 1) * 100, 1f, 300.0, 2, 45, 45, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 100, 1f, 1000.0, 3, 45, 45, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 280 + (level - 1) * 100, 2f, 1000.0, 3, 45, 45, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 170 + (level - 1) * 100, 1f, 600.0, 3, 45, 45, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 360 + (level - 1) * 100, 1.8f, 200.0, 3, 45, 45, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 100, 1f, 1000.0, 3, 45, 45, MonsterType.Berserker));
        this.waves.add(new Wave(game, 3, 0xdac + (level - 1) * 100, 1f, 1000.0, 30, 45, 45, MonsterType.Chieftain));
        this.waves.add(new Wave(game, 10, 310 + (level - 1) * 100, 1f, 1000.0, 3, 45, 45, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 100, 1f, 1000.0, 3, 45, 45, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 900 + (level - 1) * 100, 1f, 2000.0, 6, 45, 45, MonsterType.Doctor));
        this.waves.add(new Wave(game, 20, 550 + (level - 1) * 100, 1f, 1000.0, 2, 45, 45, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 100, 1f, 1000.0, 3, 45, 45, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 700 + (level - 1) * 100, 1.8f, 400.0, 3, 45, 45, MonsterType.Peon));
        this.waves.add(new Wave(game, 12, 800 + (level - 1) * 100, 1f, 5000.0, 3, 45, 45, MonsterType.Peasant));

        this.waves.add(new Wave(game, 0x12, 200 + (level - 1) * 100, 1f, 1000.0, 2, 50, 50, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 100, 1.8f, 600.0, 2, 50, 50, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 500 + (level - 1) * 100, 1f, 2000.0, 4, 50, 50, MonsterType.Doctor));
        this.waves.add(new Wave(game, 8, 0x9b + (level - 1) * 100, 1f, 1000.0, 3, 50, 50, MonsterType.Chicken));
        this.waves.add(new Wave(game, 12, 220 + (level - 1) * 100, 1f, 300.0, 2, 50, 50, MonsterType.Peasant));
        this.waves.add(new Wave(game, 12, 260 + (level - 1) * 100, 1f, 1000.0, 3, 50, 50, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 280 + (level - 1) * 100, 2f, 1000.0, 3, 50, 50, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 170 + (level - 1) * 100, 1f, 600.0, 3, 50, 50, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 360 + (level - 1) * 100, 1.8f, 200.0, 3, 50, 50, MonsterType.Peon));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 100, 1f, 1000.0, 3, 50, 50, MonsterType.Berserker));
        this.waves.add(new Wave(game, 3, 0xdac + (level - 1) * 100, 1f, 1000.0, 30, 50, 50, MonsterType.Chieftain));
        this.waves.add(new Wave(game, 10, 310 + (level - 1) * 100, 1f, 1000.0, 3, 50, 50, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 100, 1f, 1000.0, 3, 50, 50, MonsterType.Peasant));
        this.waves.add(new Wave(game, 5, 900 + (level - 1) * 100, 1f, 2000.0, 6, 50, 50, MonsterType.Doctor));
        this.waves.add(new Wave(game, 20, 550 + (level - 1) * 100, 1f, 1000.0, 2, 50, 50, MonsterType.Berserker));
        this.waves.add(new Wave(game, 10, 500 + (level - 1) * 100, 1f, 1000.0, 3, 50, 50, MonsterType.Chicken));
        this.waves.add(new Wave(game, 10, 700 + (level - 1) * 100, 1.8f, 400.0, 3, 50, 50, MonsterType.Peon));
        this.waves.add(new Wave(game, 12, 800 + (level - 1) * 100, 1f, 5000.0, 3, 50, 50, MonsterType.Peasant));

        switch (level) {
            case 1:
                this.waves.add(new Wave(game, 10, 700 + (level - 1) * 150, 1f, 1000.0, 3, 0, 0, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xc80 + (level - 1) * 150, 1f, 1000.0, 30, 0, 0, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 420 + (level - 1) * 150, 1f, 1000.0, 3, 0, 0, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 800 + (level - 1) * 150, 1f, 1000.0, 3, 0, 0, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 950 + (level - 1) * 150, 2f, 1000.0, 3, 0, 0, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x44c + (level - 1) * 150, 1f, 500.0, 3, 0, 0, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xce4 + (level - 1) * 150, 1f, 4000.0, 30, 0, 0, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 700 + (level - 1) * 150, 1f, 1000.0, 3, 5, 5, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xc80 + (level - 1) * 150, 1f, 1000.0, 30, 5, 5, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 420 + (level - 1) * 150, 1f, 1000.0, 3, 5, 5, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 800 + (level - 1) * 150, 1f, 1000.0, 3, 5, 5, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 950 + (level - 1) * 150, 2f, 1000.0, 3, 5, 5, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x44c + (level - 1) * 150, 1f, 500.0, 3, 5, 5, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xce4 + (level - 1) * 150, 1f, 4000.0, 30, 5, 5, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 700 + (level - 1) * 150, 1f, 1000.0, 3, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xc80 + (level - 1) * 150, 1f, 1000.0, 30, 10, 10, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 420 + (level - 1) * 150, 1f, 1000.0, 3, 10, 10, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 800 + (level - 1) * 150, 1f, 1000.0, 3, 10, 10, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 950 + (level - 1) * 150, 2f, 1000.0, 3, 10, 10, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x44c + (level - 1) * 150, 1f, 500.0, 3, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xce4 + (level - 1) * 150, 1f, 4000.0, 30, 10, 10, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 700 + (level - 1) * 150, 1f, 1000.0, 3, 15, 15, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xc80 + (level - 1) * 150, 1f, 1000.0, 30, 15, 15, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 420 + (level - 1) * 150, 1f, 1000.0, 3, 15, 15, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 800 + (level - 1) * 150, 1f, 1000.0, 3, 15, 15, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 950 + (level - 1) * 150, 2f, 1000.0, 3, 15, 15, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x44c + (level - 1) * 150, 1f, 500.0, 3, 15, 15, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xce4 + (level - 1) * 150, 1f, 4000.0, 30, 15, 15, MonsterType.Chieftain));


                this.waves.add(new Wave(game, 10, 700 + (level - 1) * 150, 1f, 1000.0, 3, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xc80 + (level - 1) * 150, 1f, 1000.0, 30, 20, 20, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 420 + (level - 1) * 150, 1f, 1000.0, 3, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 800 + (level - 1) * 150, 1f, 1000.0, 3, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 950 + (level - 1) * 150, 2f, 1000.0, 3, 20, 20, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x44c + (level - 1) * 150, 1f, 500.0, 3, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xce4 + (level - 1) * 150, 1f, 4000.0, 30, 20, 20, MonsterType.Chieftain));


                this.waves.add(new Wave(game, 10, 700 + (level - 1) * 150, 1f, 1000.0, 3, 25, 25, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xc80 + (level - 1) * 150, 1f, 1000.0, 30, 25, 25, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 420 + (level - 1) * 150, 1f, 1000.0, 3, 25, 25, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 800 + (level - 1) * 150, 1f, 1000.0, 3, 25, 25, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 950 + (level - 1) * 150, 2f, 1000.0, 3, 25, 25, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x44c + (level - 1) * 150, 1f, 500.0, 3, 25, 25, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xce4 + (level - 1) * 150, 1f, 4000.0, 30, 25, 25, MonsterType.Chieftain));


                this.waves.add(new Wave(game, 10, 700 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xc80 + (level - 1) * 150, 1f, 1000.0, 30, 30, 30, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 420 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 800 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 950 + (level - 1) * 150, 2f, 1000.0, 3, 30, 30, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x44c + (level - 1) * 150, 1f, 500.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xce4 + (level - 1) * 150, 1f, 4000.0, 30, 30, 30, MonsterType.Chieftain));


                this.waves.add(new Wave(game, 10, 700 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xc80 + (level - 1) * 150, 1f, 1000.0, 30, 30, 30, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 420 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 800 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 950 + (level - 1) * 150, 2f, 1000.0, 3, 30, 30, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x44c + (level - 1) * 150, 1f, 500.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xce4 + (level - 1) * 150, 1f, 4000.0, 30, 30, 30, MonsterType.Chieftain));


                this.waves.add(new Wave(game, 10, 700 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xc80 + (level - 1) * 150, 1f, 1000.0, 30, 30, 30, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 420 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 800 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 950 + (level - 1) * 150, 2f, 1000.0, 3, 30, 30, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x44c + (level - 1) * 150, 1f, 500.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xce4 + (level - 1) * 150, 1f, 4000.0, 30, 30, 30, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 700 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xc80 + (level - 1) * 150, 1f, 1000.0, 30, 30, 30, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 420 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 800 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 950 + (level - 1) * 150, 2f, 1000.0, 3, 30, 30, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x44c + (level - 1) * 150, 1f, 500.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xce4 + (level - 1) * 150, 1f, 4000.0, 30, 30, 30, MonsterType.Chieftain));

                break;
            case 2:
                this.waves.add(new Wave(game, 10, 900 + (level - 1) * 150, 1f, 1000.0, 3, 0, 0, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xfa0 + (level - 1) * 150, 1f, 1000.0, 30, 0, 0, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 450 + (level - 1) * 150, 1f, 1000.0, 3, 0, 0, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x3e8 + (level - 1) * 150, 1f, 1000.0, 3, 0, 0, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x41a + (level - 1) * 150, 2f, 1000.0, 3, 0, 0, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x4b0 + (level - 1) * 150, 1f, 500.0, 3, 0, 0, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfa0 + (level - 1) * 150, 1f, 4000.0, 30, 0, 0, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 900 + (level - 1) * 150, 1f, 1000.0, 3, 5, 5, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xfa0 + (level - 1) * 150, 1f, 1000.0, 30, 5, 5, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 450 + (level - 1) * 150, 1f, 1000.0, 3, 5, 5, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x3e8 + (level - 1) * 150, 1f, 1000.0, 3, 5, 5, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x41a + (level - 1) * 150, 2f, 1000.0, 3, 5, 5, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x4b0 + (level - 1) * 150, 1f, 500.0, 3, 5, 5, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfa0 + (level - 1) * 150, 1f, 4000.0, 30, 5, 5, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 900 + (level - 1) * 150, 1f, 1000.0, 3, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xfa0 + (level - 1) * 150, 1f, 1000.0, 30, 10, 10, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 450 + (level - 1) * 150, 1f, 1000.0, 3, 10, 10, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x3e8 + (level - 1) * 150, 1f, 1000.0, 3, 10, 10, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x41a + (level - 1) * 150, 2f, 1000.0, 3, 10, 10, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x4b0 + (level - 1) * 150, 1f, 500.0, 3, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfa0 + (level - 1) * 150, 1f, 4000.0, 30, 10, 10, MonsterType.Chieftain));


                this.waves.add(new Wave(game, 10, 900 + (level - 1) * 150, 1f, 1000.0, 3, 15, 15, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xfa0 + (level - 1) * 150, 1f, 1000.0, 30, 15, 15, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 450 + (level - 1) * 150, 1f, 1000.0, 3, 15, 15, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x3e8 + (level - 1) * 150, 1f, 1000.0, 3, 15, 15, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x41a + (level - 1) * 150, 2f, 1000.0, 3, 15, 15, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x4b0 + (level - 1) * 150, 1f, 500.0, 3, 15, 15, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfa0 + (level - 1) * 150, 1f, 4000.0, 30, 15, 15, MonsterType.Chieftain));


                this.waves.add(new Wave(game, 10, 900 + (level - 1) * 150, 1f, 1000.0, 3, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xfa0 + (level - 1) * 150, 1f, 1000.0, 30, 20, 20, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 450 + (level - 1) * 150, 1f, 1000.0, 3, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x3e8 + (level - 1) * 150, 1f, 1000.0, 3, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x41a + (level - 1) * 150, 2f, 1000.0, 3, 20, 20, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x4b0 + (level - 1) * 150, 1f, 500.0, 3, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfa0 + (level - 1) * 150, 1f, 4000.0, 30, 20, 20, MonsterType.Chieftain));


                this.waves.add(new Wave(game, 10, 900 + (level - 1) * 150, 1f, 1000.0, 3, 25, 25, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xfa0 + (level - 1) * 150, 1f, 1000.0, 30, 25, 25, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 450 + (level - 1) * 150, 1f, 1000.0, 3, 25, 25, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x3e8 + (level - 1) * 150, 1f, 1000.0, 3, 25, 25, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x41a + (level - 1) * 150, 2f, 1000.0, 3, 25, 25, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x4b0 + (level - 1) * 150, 1f, 500.0, 3, 25, 25, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfa0 + (level - 1) * 150, 1f, 4000.0, 30, 25, 25, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 900 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xfa0 + (level - 1) * 150, 1f, 1000.0, 30, 30, 30, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 450 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x3e8 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x41a + (level - 1) * 150, 2f, 1000.0, 3, 30, 30, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x4b0 + (level - 1) * 150, 1f, 500.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfa0 + (level - 1) * 150, 1f, 4000.0, 30, 30, 30, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 900 + (level - 1) * 150, 1f, 1000.0, 3, 35, 35, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xfa0 + (level - 1) * 150, 1f, 1000.0, 30, 35, 35, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 450 + (level - 1) * 150, 1f, 1000.0, 3, 35, 35, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x3e8 + (level - 1) * 150, 1f, 1000.0, 3, 35, 35, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x41a + (level - 1) * 150, 2f, 1000.0, 3, 35, 35, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x4b0 + (level - 1) * 150, 1f, 500.0, 3, 35, 35, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfa0 + (level - 1) * 150, 1f, 4000.0, 30, 35, 35, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 900 + (level - 1) * 150, 1f, 1000.0, 3, 40, 40, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xfa0 + (level - 1) * 150, 1f, 1000.0, 30, 40, 40, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 450 + (level - 1) * 150, 1f, 1000.0, 3, 40, 40, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x3e8 + (level - 1) * 150, 1f, 1000.0, 3, 40, 40, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x41a + (level - 1) * 150, 2f, 1000.0, 3, 40, 40, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x4b0 + (level - 1) * 150, 1f, 500.0, 3, 40, 40, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfa0 + (level - 1) * 150, 1f, 4000.0, 30, 40, 40, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 900 + (level - 1) * 150, 1f, 1000.0, 3, 40, 40, MonsterType.Berserker));
                this.waves.add(new Wave(game, 2, 0xfa0 + (level - 1) * 150, 1f, 1000.0, 30, 40, 40, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 450 + (level - 1) * 150, 1f, 1000.0, 3, 40, 40, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x3e8 + (level - 1) * 150, 1f, 1000.0, 3, 40, 40, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x41a + (level - 1) * 150, 2f, 1000.0, 3, 40, 40, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x4b0 + (level - 1) * 150, 1f, 500.0, 3, 40, 40, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfa0 + (level - 1) * 150, 1f, 4000.0, 30, 40, 40, MonsterType.Chieftain));

                break;
            case 3:
                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 0, 0, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 0, 0, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 0, 0, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 0, 0, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 0, 0, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 0, 0, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 0, 0, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 5, 5, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 5, 5, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 5, 5, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 5, 5, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 5, 5, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 5, 5, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 5, 5, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 10, 10, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 10, 10, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 10, 10, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 10, 10, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 10, 10, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 10, 10, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 15, 15, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 15, 15, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 15, 15, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 15, 15, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 15, 15, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 15, 15, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 15, 15, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 20, 20, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 20, 20, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 20, 20, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 20, 20, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 20, 20, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 20, 20, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 25, 25, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 25, 25, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 25, 25, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 25, 25, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 25, 25, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 25, 25, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 25, 25, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 30, 30, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 30, 30, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 30, 30, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 30, 30, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 30, 30, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 35, 35, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 35, 35, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 35, 35, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 35, 35, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 35, 35, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 35, 35, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 35, 35, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 40, 40, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 40, 40, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 40, 40, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 40, 40, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 40, 40, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 40, 40, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 40, 40, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 45, 45, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 45, 45, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 45, 45, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 45, 45, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 45, 45, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 45, 45, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 45, 45, MonsterType.Chieftain));

                this.waves.add(new Wave(game, 10, 1100 + (level - 1) * 150, 1f, 1000.0, 3, 50, 50, MonsterType.Berserker));
                this.waves.add(new Wave(game, 3, 0xfff + (level - 1) * 150, 1f, 1000.0, 30, 50, 50, MonsterType.Chieftain));
                this.waves.add(new Wave(game, 10, 650 + (level - 1) * 150, 1f, 1000.0, 3, 50, 50, MonsterType.Chicken));
                this.waves.add(new Wave(game, 10, 0x430 + (level - 1) * 150, 1f, 1000.0, 3, 50, 50, MonsterType.Peasant));
                this.waves.add(new Wave(game, 10, 0x530 + (level - 1) * 150, 2f, 1000.0, 3, 50, 50, MonsterType.Peon));
                this.waves.add(new Wave(game, 10, 0x560 + (level - 1) * 150, 1f, 500.0, 3, 50, 50, MonsterType.Berserker));
                this.waves.add(new Wave(game, 5, 0xfff + (level - 1) * 150, 1f, 4000.0, 30, 50, 50, MonsterType.Chieftain));

                break;
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