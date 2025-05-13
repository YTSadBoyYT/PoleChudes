package ru.words;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenMenu implements  Screen{
    Button btn;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button Menu;

    Main main;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    private Texture d1;
    public ScreenMenu(Main main){
        this.main = main;
        batch = main.batch;

        camera = main.camera;
        touch = main.touch;
        font = main.font;


        btn = new Button(font,"Settings",300,1100);
        btn2 = new Button(font,"Game",300,1300);
        btn3 = new Button(font,"About",300,900);
        btn4 = new Button(font,"Exit",300,700);
        d1 = new Texture("d1.png");
        Menu = new Button(font,"Menu",150,1300);


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            if(btn.hit(touch.y,touch.x)){
                main.setScreen(main.screenSettings);
            }
            else if(btn2.hit(touch.y,touch.x)){
                main.setScreen(main.screenGame);
            }
            else if(btn3.hit(touch.y,touch.x)){
                main.setScreen(main.screenAbout);
            }
            else if(btn4.hit(touch.y,touch.x)){
                Gdx.app.exit();
            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(d1,0,0,900,1600);
        btn.font.draw(batch,btn.text,btn.x,btn.y);
        btn3.font.draw(batch,btn3.text,btn3.x,btn3.y);
        btn4.font.draw(batch,btn4.text,btn4.x,btn4.y);
        btn2.font.draw(batch,btn2.text,btn2.x,btn2.y);




        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        d1.dispose();

    }
}
