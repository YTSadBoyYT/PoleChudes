package ru.words;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;


public class ScreenGame implements  Screen{
    // Список слов, из которых случайным образом будет выбрано одно
    private static final String[] words = {
        "ALBUM", "CERTIFICATE", "ATLAS", "BIOLOGY", "VARIANT", "GEOGRAPHY",
        "GLOBE", "TWO", "DICTATION", "DIARY", "BOARD", "UNIT",
        "NATURAL SCIENCE", "CHRISTMAS TREE", "PAINTING", "ANIMALS", "ASSIGNMENT",
        "TASK", "BELL", "KNOWLEDGE", "HISTORY", "IODINE", "PENCIL", "MAP",
        "CLASS", "PAINTS", "ERASER", "RULER", "LITERATURE", "RAY",
        "MARKER", "CHALK", "SCISSORS", "CLASSMATES", "ANSWER", "GRADE",
        "DESK", "BREAK", "HINT", "RULES", "EXAMPLE", "BACKPACK",
        "PEN", "ESSAY", "PREFECT", "CAFETERIA", "TABLE",
        "PROTRACTOR", "NOTEBOOK", "SET SQUARE", "POINTER", "LESSON", "TEXTBOOK",
        "STUDENT", "STUDIES", "TEACHER", "PHYSICAL EDUCATION", "UNIFORM", "FORMULAS",
        "CHEMISTRY", "COMPASS", "DRAWING", "QUARTER", "READING", "CHEAT SHEET",
        "ALKALINE", "EXAM", "SKIRT", "LANGUAGE"
    };

    // Переменная для хранения выбранного слова fi
    private static String wordToGuess;

    // Массив для отображения текущего состояния угадываемого слова (с буквами или подчеркиваниями)
    private static char[] guessedWord;
    private String guessedWord2;

    // Список для хранения угаданных букв
    private static List<Character> guessedLetters = new ArrayList<>();

    // Переменная для отслеживания оставшихся попыток
    private static int attemptsLeft = 6; // Количество попыток (можно изменить)

    // Переменная для подсчета очков (правильных угаданных букв)
    private static int score = 0;

    Button btn;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Main main;
    private Texture d11;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public ScreenGame(Main main){
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        btn6 = new Button(font,"back",0,1600);
        btn = new Button(font,"second_screen",150,1000);
        btn2 = new Button(font,"first_screen",150,1300);
        btn3 = new Button(font,"third_screen",150,700);
        btn4 = new Button(font,"fourth_screen",150,400);
        d11 = new Texture("d4.png");


    }
    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            if(btn6.hit(touch.y,touch.x)){
                main.setScreen(main.screenMenu);
            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(d11,0,0,900,1600);
        btn6.font.draw(batch,btn6.text,btn6.x,btn6.y);





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
        d11.dispose();

    }
}
