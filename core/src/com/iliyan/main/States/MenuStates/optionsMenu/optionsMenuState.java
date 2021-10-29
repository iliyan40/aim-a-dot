package com.iliyan.main.States.MenuStates.optionsMenu;
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
import static com.iliyan.main.Game.prefs;

public class optionsMenuState extends State {
    static Sound click;
    private BitmapFont audioWireBitmapFont;
    private SpriteBatch batch;
    private GlyphLayout glyphLayout;
    private Texture buttonTexture;
    private Texture buttonTexture2;
    private Button button;
    private Stage stage;
    private int highScore;
    private int money;
    private boolean graphicsIsTouched;
    private boolean soundIsTouched;
    private boolean musicIsTouched;
    private boolean backIsTouched;
    private InputProcessor inputProcessor;
    private ParticleEffect particleEffectOnClick;

    public optionsMenuState(GameStateManager gms) {
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
        inputProcessor = new optionsInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        Gdx.input.setCatchBackKey(true);
        this.particleEffectOnClick = new ParticleEffect();
        this.particleEffectOnClick.load(Gdx.files.internal("ParticleEffects/testParticles.paef"),
                Gdx.files.internal("ParticleEffects"));
        this.click = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));
        highScore = prefs.getInteger("highScore");
        money = State.money;
    }

    static void resetPointer() {
        pointer.x=0;
        pointer.y=0;
    }


    @Override
    public void handleInput() {
        graphicsIsTouched = DrawButton.buttonTouched(pointer, 0) && Gdx.input.isTouched();
        soundIsTouched = DrawButton.buttonTouched(pointer, -1) && Gdx.input.isTouched();
        musicIsTouched = DrawButton.buttonTouched(pointer, -2) && Gdx.input.isTouched();
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


        //draw the title of the game on screen
        menuDrawings.DrawTitle(
                this.audioWireBitmapFont,
                this.batch,
                this.glyphLayout);

        //draw options text
        audioWireBitmapFont.draw(
                batch,
                "  Options",
                WIDTH/2f-glyphLayout.width/1.7f,
                HEIGHT-HEIGHT/4f-glyphLayout.height/2);

        //draw highScore and money
        menuDrawings.DrawHighScoreAndMoney(
                this.audioWireBitmapFont,
                this.batch,
                this.highScore,
                this.money);

        //draw graphics button
        DrawButton.draw( audioWireBitmapFont,
                batch,
                buttonTexture,
                graphicsIsTouched,
                buttonTexture2,
                0,
                "Visuals"
        );
        audioWireBitmapFont.getData().setScale(0.5f);
        if(graphicsOn)
            audioWireBitmapFont.draw(batch,"good",220,465);
        else
            audioWireBitmapFont.draw(batch,"bad",230,465);

        //draw sound button
        DrawButton.draw( audioWireBitmapFont,
                batch,
                buttonTexture,
                soundIsTouched,
                buttonTexture2,
                -1,
                " Sound"
        );
        audioWireBitmapFont.getData().setScale(0.5f);
        if(soundOn)
            audioWireBitmapFont.draw(batch,"on",235,465-100);
        else
            audioWireBitmapFont.draw(batch,"off",235,465-100);

        //draw music button
        DrawButton.draw( audioWireBitmapFont,
                batch,
                buttonTexture,
                musicIsTouched,
                buttonTexture2,
                -2,
                "  Music"
        );
        audioWireBitmapFont.getData().setScale(0.5f);
        if(musicOn)
            audioWireBitmapFont.draw(batch,"on",235,465-200);
        else
            audioWireBitmapFont.draw(batch,"off",235,465-200);
        //draw back button
        DrawButton.draw( audioWireBitmapFont,
                batch,
                buttonTexture,
                backIsTouched,
                buttonTexture2,
                -3,
                "   Back"
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
