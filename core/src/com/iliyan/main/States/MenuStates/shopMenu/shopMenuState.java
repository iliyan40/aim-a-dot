package com.iliyan.main.States.MenuStates.shopMenu;
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
import com.iliyan.main.States.State;

import static com.iliyan.main.Game.HEIGHT;
import static com.iliyan.main.Game.TITLE;
import static com.iliyan.main.Game.WIDTH;
import static com.iliyan.main.Game.prefs;

public class shopMenuState extends State {
    static Sound click;
    private BitmapFont audioWireBitmapFont;
    private SpriteBatch batch;
    private GlyphLayout glyphLayout;
    private Texture buttonTexture;
    private Texture buttonTexture2;
    private Button button;
    private Stage stage;
    private int highScore;
    public static int money;
    public static boolean[] shapesUnlocked;
    public static boolean[] colorsUnlocked;
    private InputProcessor inputProcessor;
    private ParticleEffect particleEffectOnClick;
    private boolean backIsTouched;
    private boolean chooseButtonTouched1;
    private boolean chooseButtonTouched2;
    private static boolean setToShape;
    private static boolean setToColor;
    private static boolean sel1Touched;
    private static boolean sel2Touched;
    private static boolean sel3Touched;
    private static boolean sel4Touched;
    private static boolean sel5Touched;
    private static boolean sel6Touched;
    private shopDrawings shopDrawings;
    public static int selectedShape;
    public static int selectedColor;
    public static int[] colorPrices;
    public static int[] shapePrices;

    public shopMenuState(GameStateManager gms) {
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
        inputProcessor = new shopInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        Gdx.input.setCatchBackKey(true);
        this.particleEffectOnClick = new ParticleEffect();
        this.particleEffectOnClick.load(Gdx.files.internal("ParticleEffects/testParticles.paef"),
                Gdx.files.internal("ParticleEffects"));
        click = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));

        shapesUnlocked = new boolean[6];
        shapesUnlocked = moneyAndScoreHandler.convertUnlockedStringToBoolean(prefs.getString("unlockedShapes"));


        colorsUnlocked = new boolean[6];
        colorsUnlocked = moneyAndScoreHandler.convertUnlockedStringToBoolean(prefs.getString("unlockedColors"));


        colorPrices = new int[6];
        shapePrices = new int[6];
        selectedColor = prefs.getInteger("selectedColor");
        selectedShape = prefs.getInteger("selectedShape");
        highScore = prefs.getInteger("highScore");
        money = State.money;
        setToShape();
        shopDrawings = new  shopDrawings();

    }
    private void drawColorPrices(){
        {
            colorPrices[0] = 0;
            colorPrices[1] = 100;
            colorPrices[2] = 200;
            colorPrices[3] = 200;
            colorPrices[4] = 300;
            colorPrices[5] = 300;
            audioWireBitmapFont.setColor(1,1,1,1);
            if(!colorsUnlocked[1]){
                audioWireBitmapFont.draw(batch,String.valueOf(colorPrices[1]) + "$",235,525);
            }
            if(!colorsUnlocked[2]){
                audioWireBitmapFont.draw(batch,String.valueOf(colorPrices[2]) + "$",60,397);
            }
            if(!colorsUnlocked[3]){
                audioWireBitmapFont.draw(batch,String.valueOf(colorPrices[3]) + "$",230,397);
            }
            if(!colorsUnlocked[4]){
                audioWireBitmapFont.draw(batch,String.valueOf(colorPrices[4]) + "$",60,269);
            }
            if(!colorsUnlocked[5]){
                audioWireBitmapFont.draw(batch,String.valueOf(colorPrices[5]) + "$",230,269);
            }
        }
    }
    private void drawShapesPrices(){
        shapePrices[0] = 0;
        shapePrices[1] = 100;
        shapePrices[2] = 200;
        shapePrices[3] = 300;
        shapePrices[4] = 400;
        shapePrices[5] = 500;
        audioWireBitmapFont.setColor(1,1,1,1);
        if(!shapesUnlocked[1]){
            audioWireBitmapFont.draw(batch,String.valueOf(shapePrices[1]) + "$",235,525);
        }
        if(!shapesUnlocked[2]){
            audioWireBitmapFont.draw(batch,String.valueOf(shapePrices[2]) + "$",60,397);
        }
        if(!shapesUnlocked[3]){
            audioWireBitmapFont.draw(batch,String.valueOf(shapePrices[3]) + "$",230,397);
        }
        if(!shapesUnlocked[4]){
            audioWireBitmapFont.draw(batch,String.valueOf(shapePrices[4]) + "$",60,269);
        }
        if(!shapesUnlocked[5]){
            audioWireBitmapFont.draw(batch,String.valueOf(shapePrices[5]) + "$",230,269);
        }
    }
    static void resetPointer() {
        pointer.x=0;
        pointer.y=0;
    }

    public static Vector3 getPointer() {
        return new Vector3(pointer);
    }

    public static void setToShape() {
        setToColor=false;
        setToShape=true;
    }

    public static void setToColor() {
        setToColor=true;
        setToShape=false;
    }

    @Override
    protected void handleInput() {
        backIsTouched = DrawButton.buttonTouched(pointer, -3) && Gdx.input.isTouched();
        chooseButtonTouched1 = DrawButton.reallySmallButtonTouched(pointer,0) && Gdx.input.isTouched();
        chooseButtonTouched2 = DrawButton.reallySmallButtonTouched(pointer,1) && Gdx.input.isTouched();
        sel1Touched = DrawButton.miniTouched(pointer,0,0)&& Gdx.input.isTouched();
        sel2Touched = DrawButton.miniTouched(pointer,1,0)&& Gdx.input.isTouched();
        sel3Touched = DrawButton.miniTouched(pointer,0,1)&& Gdx.input.isTouched();
        sel4Touched = DrawButton.miniTouched(pointer,1,1)&& Gdx.input.isTouched();
        sel5Touched = DrawButton.miniTouched(pointer,0,2)&& Gdx.input.isTouched();
        sel6Touched = DrawButton.miniTouched(pointer,1,2)&& Gdx.input.isTouched();
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

        //draw "player shop"
        audioWireBitmapFont.getData().setScale(1,1);
        audioWireBitmapFont.setColor(1,1,1,1);
        audioWireBitmapFont.draw(
                batch,
                "Player shop",
                WIDTH/2f-glyphLayout.width/1.7f-16,
                HEIGHT-HEIGHT/4f-glyphLayout.height/2+120);

        //draw highScore and money
        menuDrawings.DrawHighScoreAndMoney(
                this.audioWireBitmapFont,
                this.batch,
                this.highScore,
                this.money);

        //draw back
        DrawButton.draw( audioWireBitmapFont,
                batch,
                buttonTexture,
                backIsTouched,
                buttonTexture2,
                -3,
                "   Back"
        );

        //draw really small Button 1
        DrawButton.drawReallySmall( audioWireBitmapFont,
                batch,
                buttonTexture,
                chooseButtonTouched1,
                buttonTexture2,
                0,
                "shape",setToShape);
        //draw really small Button 2
        DrawButton.drawReallySmall( audioWireBitmapFont,
                batch,
                buttonTexture,
                chooseButtonTouched2,
                buttonTexture2,
                1,
                " color",setToColor);
        //draw select button 1
        DrawButton.drawMini(
                batch,
                buttonTexture,
                sel1Touched,
                buttonTexture2,
                0,0);
        //draw select button 2
        DrawButton.drawMini(
                batch,
                buttonTexture,
                sel2Touched,
                buttonTexture2,
                1,0);
        //draw select button 3
        DrawButton.drawMini(
                batch,
                buttonTexture,
                sel3Touched,
                buttonTexture2,
                0,1);
        //draw select button 4
        DrawButton.drawMini(
                batch,
                buttonTexture,
                sel4Touched,
                buttonTexture2,
                1,1);
        //draw select button 5
        DrawButton.drawMini(
                batch,
                buttonTexture,
                sel5Touched,
                buttonTexture2,
                0,2);
        //draw select button 6
        DrawButton.drawMini(
                batch,
                buttonTexture,
                sel6Touched,
                buttonTexture2,
                1,2);

        if(setToShape) {
            shopDrawings.drawShapes(batch, shapesUnlocked,selectedShape);
            drawShapesPrices();
        }
        else {
            shopDrawings.drawColors(batch,colorsUnlocked,selectedColor);
            drawColorPrices();
        }
        this.particleEffectOnClick.draw(batch);
        batch.end();
    }

    public static String getShapeOrColor() {
        if(setToColor){
            return "Color";
        }else return "Shape";
    }

    @Override
    public void dispose() {

    }
}
