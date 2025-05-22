package ru.words;

import static ru.words.Main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

    // Список для хранения угаданных букв
    private static List<Character> guessedLetters = new ArrayList<>();

    // Переменная для отслеживания оставшихся попыток
    private static int attemptsLeft = 16; // Количество попыток (можно изменить)

    // Переменная для подсчета очков (правильных угаданных букв)
    private static int score = 0;

    List<Button> letters = new ArrayList<>();
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
    public BitmapFont fontWhite;
    public BitmapFont fontYellow;
    public BitmapFont fontGray;
    public BitmapFont fontBrown;
    String comment = "";

    public ScreenGame(Main main){
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        fontWhite = main.fontWhite;
        fontYellow = main.fontYellow;
        fontGray = main.fontGray;
        fontBrown = main.fontBrown;

        btn6 = new Button(fontWhite,"back",0,1600);
        btn = new Button(fontWhite,"second_screen",150,1000);
        btn2 = new Button(fontWhite,"first_screen",150,1300);
        btn3 = new Button(fontWhite,"third_screen",150,700);
        btn4 = new Button(fontWhite,"fourth_screen",150,400);
        //String s = "АБВГДЕЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ";
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        float x = 0;
        float y = SCREEN_HEIGHT/2.7f;
        float w = 100, h = 100;

        for (int i = 0; i < s.length(); i++) {
            letters.add(new Button(fontWhite, ""+s.charAt(i), 0, 0));
            if(x + w < SCREEN_WIDTH - w) {
                x += w;
            } else {
                x = 0;
                x += w;
                y -= h;
            }
            letters.get(i).x = x;
            letters.get(i).y = y;
        }
        d11 = new Texture("d4.png");

        gameplay();
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
            for (int i = 0; i < letters.size(); i++) {
                if(letters.get(i).hit(touch)){
                    Character c = letters.get(i).text.charAt(0);
                    if (guessedLetters.contains(c)) {
                        comment = "Это повтор";
                        break;
                    }
                    // Добавляем букву в список угаданных
                    guessedLetters.add(c);
                    // Проверяем, есть ли буква в слове
                    if (checkLetter(letters.get(i).text.charAt(0))) {
                        comment = "Есть такая буква";
                        score++; // Увеличиваем счет за правильную букву
                    } else {
                        comment = "Нет такой буквы";
                        attemptsLeft--; // Уменьшаем количество попыток за неправильный ответ
                    }
                    // Проверяем, угадано ли всё слово
                    if (isWordGuessed()) {
                        comment = "Вы угадали слово: " + wordToGuess;
                        break; // Игра завершена, так как слово угадано
                    }
                }
            }
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(d11,0,0,900,1600);
        btn6.font.draw(batch,btn6.text,btn6.x,btn6.y);
        fontWhite.draw(batch, "Угадайте слово:", 0, 1400, SCREEN_WIDTH, Align.center, true);
        fontWhite.draw(batch, guessedWordShow(), 0, 1200, SCREEN_WIDTH, Align.center, true);
        fontWhite.draw(batch, comment, 0, 1000, SCREEN_WIDTH, Align.center, true);
        fontWhite.draw(batch, "Попыток: " + attemptsLeft, 0, 800, SCREEN_WIDTH, Align.center, true);
        fontWhite.draw(batch, "Ваши очки: " + score, 0, 700, SCREEN_WIDTH, Align.center, true);

        for (Button b: letters) {
            b.font.draw(batch, b.text, b.x, b.y);
        }
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

    private String guessedWordShow(){
        String s = "";
        for (int i = 0; i < guessedWord.length; i++) {
            s += guessedWord[i];
        }
        return s;
    }

    private void gameplay(){
        // Создаем объект для случайного выбора слова из массива
        Random random = new Random();

        // Выбираем случайное слово из списка
        wordToGuess = words[random.nextInt(words.length)];

        // Инициализируем массив guessedWord, который будет хранить текущее состояние слова (подчеркивания вместо букв)
        guessedWord = new char[wordToGuess.length()];
        for (int i = 0; i < wordToGuess.length(); i++) {
            guessedWord[i] = '_'; // Каждая буква изначально скрыта
        }

    }
    // Метод для проверки, есть ли введенная буква в слове
    private static boolean checkLetter(char letter) {
        boolean correct = false; // Флаг, указывающий, правильно ли угадана буква
        // Перебираем все буквы в слове
        for (int i = 0; i < wordToGuess.length(); i++) {
            // Если буква совпала, заменяем подчеркивание на саму букву в массиве guessedWord
            if (wordToGuess.charAt(i) == letter) {
                guessedWord[i] = letter;
                correct = true; // Если хоть одна буква угадана, ставим флаг в true
            }
        }
        return correct; // Возвращаем результат проверки
    }

    // Метод для проверки, угадано ли всё слово (то есть нет ли еще подчеркиваний)
    private static boolean isWordGuessed() {
        // Перебираем массив guessedWord и проверяем, остались ли еще подчеркивания
        for (char c : guessedWord) {
            if (c == '_') {
                return false; // Если хотя бы одно подчеркивание, значит слово не угадано
            }
        }
        return true; // Если нет подчеркиваний, значит слово угадано
    }

}
