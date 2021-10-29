package com.iliyan.main.States.MenuStates.MainMenu;
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
import com.iliyan.main.States.State;
import static com.iliyan.main.Game.HEIGHT;
import static com.iliyan.main.Game.TITLE;
import static com.iliyan.main.Game.WIDTH;
import static com.iliyan.main.Game.music;

public class MainMenuState extends State {
    private MainMenuDrawings mainMenuDrawings;
    private BitmapFont audioWireBitmapFont;
    private SpriteBatch batch;
    private GlyphLayout glyphLayout;
    private int highScore;
    private int money;
    private Texture buttonTexture;
    private Texture buttonTexture2;
    private boolean startIsTouched = false;
    private boolean shopIsTouched = false;
    private boolean optionsIsTouched = false;
    private boolean exitIsTouched = false;
    private Button button;
    private Stage stage;
    private InputProcessor inputProcessor;
    private ParticleEffect particleEffectOnClick;
    static Sound click;
    public static Sound playerCreate;

    public MainMenuState(GameStateManager gms) {
        super(gms);
        this.mainMenuDrawings = new MainMenuDrawings();
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
        this.inputProcessor = new MainInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        this.particleEffectOnClick = new ParticleEffect();
        this.particleEffectOnClick.load(Gdx.files.internal("ParticleEffects/testParticles.paef"),
                Gdx.files.internal("ParticleEffects"));
        this.click = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));
        playerCreate = Gdx.audio.newSound(Gdx.files.internal("Sound/playerCreate.wav"));
        Gdx.input.setCatchBackKey(false);
        highScore = State.highScore;
        money = State.money;
    }

    static void resetPointer() {
        pointer.x=0;
        pointer.y=0;
    }


    @Override
    public void handleInput() {
        if(DrawButton.buttonTouched(pointer,0)){
            startIsTouched = true;
        }else startIsTouched = false;

        if(DrawButton.buttonTouched(pointer,-1)){
            shopIsTouched = true;
        }else shopIsTouched = false;

        if(DrawButton.buttonTouched(pointer,-2)){
            optionsIsTouched = true;
        }else optionsIsTouched = false;

        if(DrawButton.buttonTouched(pointer,-3 )&& Gdx.input.isTouched()){
            exitIsTouched = true;
        }else exitIsTouched = false;
        if(!Gdx.input.isTouched()) {
            startIsTouched = false;
            shopIsTouched = false;
            optionsIsTouched = false;
            exitIsTouched = false;
        }
        if(Gdx.input.justTouched()&particleEffectOnClick.isComplete()){
            this.particleEffectOnClick.getEmitters().first().setPosition(pointer.x,pointer.y);
            if(graphicsOn)
                this.particleEffectOnClick.start();
        }
    }

    @Override
    public void update(float deltaTime) {

        money = State.money;
        highScore = State.highScore;
        pointer = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        handleInput();
        particleEffectOnClick.update(Gdx.graphics.getDeltaTime());
        if(!musicOn){
            music.stop();
        }else if(!music.isPlaying())
            music.play();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        this.camera.setToOrtho(false, WIDTH,HEIGHT);
        this.camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        //draw the title of the game on screen
        menuDrawings.DrawTitle(
                this.audioWireBitmapFont,
                this.batch,
                this.glyphLayout);

        //Draw "Main Menu" text on screen
        mainMenuDrawings.DrawMainMenuText(
                this.audioWireBitmapFont,
                this.batch,
                this.glyphLayout);

        //Draw High score and Money
        menuDrawings.DrawHighScoreAndMoney(
                this.audioWireBitmapFont,
                this.batch,
                this.highScore,
                this.money);

        //Draw Start button
        mainMenuDrawings.DrawStartButton(
                this.audioWireBitmapFont,
                this.batch,
                buttonTexture,
                startIsTouched,
                buttonTexture2
        );

        //Draw Shop button
        mainMenuDrawings.DrawShopButton(
                this.audioWireBitmapFont,
                this.batch,
                buttonTexture,
                shopIsTouched,
                buttonTexture2
        );

        //Draw Options button
        mainMenuDrawings.DrawOptionsButton(
                this.audioWireBitmapFont,
                this.batch,
                buttonTexture,
                optionsIsTouched,
                buttonTexture2
        );

        //Draw Exit button
        mainMenuDrawings.DrawExitButton(
                this.audioWireBitmapFont,
                this.batch,
                buttonTexture,
                exitIsTouched,
                buttonTexture2
        );
        this.particleEffectOnClick.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        audioWireBitmapFont.dispose();
        buttonTexture.dispose();
        particleEffectOnClick.dispose();
        click.dispose();
    }
    static Vector3 getPointer(){
        return new Vector3(pointer);
    }
}
