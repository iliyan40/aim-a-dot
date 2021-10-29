package com.iliyan.main.States.MenuStates.GameOver;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.iliyan.main.States.GameStateManager;
import com.iliyan.main.States.MenuStates.DrawButton;
import com.iliyan.main.States.MenuStates.menuDrawings;
import com.iliyan.main.States.MenuStates.moneyAndScoreHandler;
import com.iliyan.main.States.MenuStates.shopMenu.shopInputProcessor;
import com.iliyan.main.States.State;
import com.iliyan.main.States.playState.gamePlayState;

import static com.iliyan.main.Game.HEIGHT;
import static com.iliyan.main.Game.TITLE;
import static com.iliyan.main.Game.WIDTH;
import static com.iliyan.main.Game.prefs;

public class gameOverState extends State {
    static Sound click;
    private BitmapFont audioWireBitmapFont;
    private SpriteBatch batch;
    private GlyphLayout glyphLayout;
    private Texture buttonTexture;
    private Texture buttonTexture2;
    private Button button;
    private Stage stage;
    private int highScore;
    private int score;
    public static int money;
    public static int moneyAdded;
    private InputProcessor inputProcessor;
    private ParticleEffect particleEffectOnClick;
    private boolean backIsTouched;
    private boolean restartIsTouched;
    private int statsGoDownWith;
    private String congrats;
    private int oldHighScore;
    public gameOverState(GameStateManager gms) {
        super(gms);
        this.batch = new SpriteBatch();
        this.audioWireBitmapFont = new BitmapFont(Gdx.files.internal("Font/Audiowide.fnt"));
        this.glyphLayout = new GlyphLayout();
        this.glyphLayout.setText(audioWireBitmapFont,TITLE);
        this.buttonTexture = new Texture(Gdx.files.internal("ButtonSkin/1.png"));
        this.buttonTexture2 = new Texture(Gdx.files.internal("ButtonSkin/2.png"));
        this.button = new Button();
        this.button.setWidth(100);
        this.button.setHeight(200);
        this.button.setPosition(WIDTH / 2f - 107,
                HEIGHT - HEIGHT / 2f + 8);
        this.stage = new Stage();
        this.stage.addActor(button);
        Gdx.input.setInputProcessor(inputProcessor);
        Gdx.input.setCatchBackKey(true);
        this.particleEffectOnClick = new ParticleEffect();
        this.particleEffectOnClick.load(Gdx.files.internal("ParticleEffects/testParticles.paef")
                ,Gdx.files.internal("ParticleEffects"));
        click = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));

        inputProcessor = new gameOverInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);

        highScore = prefs.getInteger("highScore");
        money = State.money+gamePlayState.moneyAdded;
        score = gamePlayState.score;
        moneyAdded = gamePlayState.moneyAdded;
        State.money = money;
        oldHighScore = highScore;
        if(highScore<score){
            highScore = score;
            gamePlayState.highScore = score;
            prefs.putInteger("highScore",highScore);
        }


        prefs.putInteger("money",money);
        prefs.flush();
        statsGoDownWith = 80;
        Gdx.gl.glClearColor(0.11f, 0.11f, 0.11f, 1);
        congrats = chooseRandomCongrats();



    }

    static void resetPointer() {
        pointer.x=0;
        pointer.y=0;
    }

    public static Vector3 getPointer() {
        return new Vector3(pointer);
    }


    @Override
    protected void handleInput() {
        restartIsTouched = DrawButton.buttonTouched(pointer, -2) && Gdx.input.isTouched();
        backIsTouched = DrawButton.buttonTouched(pointer, -3) && Gdx.input.isTouched();
        if(Gdx.input.justTouched()&particleEffectOnClick.isComplete()){
            this.particleEffectOnClick.getEmitters().first().setPosition(pointer.x,pointer.y);
            if(graphicsOn)
                this.particleEffectOnClick.start();
        }
    }

    @Override
    public void update(float deltaTime) {
        pointer = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        handleInput();
        particleEffectOnClick.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        this.camera.setToOrtho(false, WIDTH,HEIGHT);
        this.camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        //draw score;
        audioWireBitmapFont.getData().setScale(0.7f,0.7f);
        audioWireBitmapFont.setColor(1,1,1,1);
        audioWireBitmapFont.draw(
                batch,
                "Score: " + score,
                WIDTH/2f-glyphLayout.width/1.7f-16,
                HEIGHT-HEIGHT/4f-glyphLayout.height/2+64-statsGoDownWith);
        //draw money;
        audioWireBitmapFont.getData().setScale(0.5f,0.5f);
        audioWireBitmapFont.draw(
                batch,
                "Money: " + money + " (+" + moneyAdded + ")",
                WIDTH/2f-glyphLayout.width/1.7f-16,
                HEIGHT-HEIGHT/4f-glyphLayout.height/2+32-statsGoDownWith);

        //draw highScore
        audioWireBitmapFont.draw(
                batch,
                "highscore: " + highScore,
                WIDTH/2f-glyphLayout.width/1.7f-16,
                HEIGHT-HEIGHT/4f-glyphLayout.height/2+8-statsGoDownWith);

        //draw "new highScore"

        if(score>oldHighScore){
            audioWireBitmapFont.getData().setScale(0.8f,0.8f);
            audioWireBitmapFont.setColor(1,0.8f,0.3f,1);
            audioWireBitmapFont.draw(
                    batch,
                    "NEW HIGHSCORE!",
                    WIDTH/2f-glyphLayout.width/1.7f-16,
                    HEIGHT-HEIGHT/4f-glyphLayout.height/2-16-statsGoDownWith);
        }else {
            audioWireBitmapFont.getData().setScale(0.8f,0.8f);
            audioWireBitmapFont.draw(
                    batch,
                    congrats,
                    WIDTH/2f-glyphLayout.width/1.7f-16,
                    HEIGHT-HEIGHT/4f-glyphLayout.height/2-16-statsGoDownWith);
        }

        //draw "Game Over"
        audioWireBitmapFont.getData().setScale(1,1);
        audioWireBitmapFont.setColor(1,1,1,1);
        audioWireBitmapFont.draw(
                batch,
                "GAME OVER",
                WIDTH/2f-glyphLayout.width/1.7f-16,
                HEIGHT-HEIGHT/4f-glyphLayout.height/2+120-statsGoDownWith/2f);

        //draw highScore and money
        menuDrawings.DrawHighScoreAndMoney(
                this.audioWireBitmapFont,
                this.batch,
                this.highScore,
                this.money);


        //draw restart button
        DrawButton.draw( audioWireBitmapFont,
                batch,
                buttonTexture,
                restartIsTouched,
                buttonTexture2,
                -2,
                "Restart"
        );

        //draw back button
        DrawButton.draw( audioWireBitmapFont,
                batch,
                buttonTexture,
                backIsTouched,
                buttonTexture2,
                -3,
                "  Menu"
        );

        this.particleEffectOnClick.draw(batch);
        batch.end();
    }

    private String chooseRandomCongrats() {
        double a = Math.round(Math.random()*10);
        switch ((int)a){
            case 1:
                return "Great!";
            case 2:
                return "Nice job";
            case 3:
                return "Good job";
            case 4:
                return "LOL";
            case 5:
                return "N I C E";
            case 6:
                return "You good";
            case 7:
                return "Holy moly";
            case 8:
                return "\\(T_T)/";
            case 9:
                return "You are PRO";
            case 10:
                return "Try again?";
        }
        return "nice!";
    }


    @Override
    public void dispose() {

    }
}
