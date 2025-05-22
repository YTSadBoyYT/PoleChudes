package ru.words;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static final float SCREEN_WIDTH = 900;
    public static final float SCREEN_HEIGHT = 1600;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont fontWhite;
    public BitmapFont fontYellow;
    public BitmapFont fontGray;
    public BitmapFont fontBrown;

    ScreenSettings screenSettings;
    ScreenGame screenGame;
    ScreenMenu screenMenu;
    ScreenAbout screenAbout;
    Fourth_Screen fourthScreen;

    @Override
    public void create() {

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,SCREEN_WIDTH,SCREEN_HEIGHT);

        touch = new Vector3();
        fontWhite = new BitmapFont(Gdx.files.internal("stylo90white.fnt"));
        fontYellow = new BitmapFont(Gdx.files.internal("stylo90yellow.fnt"));
        fontGray = new BitmapFont(Gdx.files.internal("stylo90gray.fnt"));
        fontBrown = new BitmapFont(Gdx.files.internal("stylo90brown.fnt"));
        screenSettings = new ScreenSettings(this);
        screenGame = new ScreenGame(this);
        screenMenu = new ScreenMenu(this);
        screenAbout = new ScreenAbout(this);
        fourthScreen = new Fourth_Screen(this);

        setScreen(screenMenu);


    }



    @Override
    public void dispose() {
        batch.dispose();
        fontWhite.dispose();

    }
}
