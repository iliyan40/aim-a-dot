package com.iliyan.main.States.playState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.iliyan.main.States.GameStateManager;
import com.iliyan.main.States.State;
import com.iliyan.main.States.playState.gameObjects.BonusPoint;
import com.iliyan.main.States.playState.gameObjects.Player;
import com.iliyan.main.States.playState.gameObjects.ScorePoint;
import com.iliyan.main.States.playState.gameObjects.ScoreOnScreenDraw;

import java.security.Key;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import static com.iliyan.main.Game.HEIGHT;
import static com.iliyan.main.Game.TITLE;
import static com.iliyan.main.Game.WIDTH;

public class gamePlayState extends State {
    private World world;
    public static Player player;
    private SpriteBatch batch;
    private Box2DDebugRenderer b2dr;
    private Stage stage;
    static Body playerBody;
    Body scoreBody;
    Body scoreBody2;
    Body bonusBody;
    private BitmapFont audioWireBitmapFont;
    private GlyphLayout glyphLayout;
    public static int score;
    public static int moneyAdded;
    private static int totalMoney;
    private RayHandler rayHandler;
    private PointLight playerLight;
    private PointLight scoreLight;
    private PointLight scoreLight2;
    private PointLight bonusLight;
    public static ScorePoint scorePoint;
    public static ScorePoint scorePoint2;
    private ContactListener gamePlayContactListener;
    public static ScoreOnScreenDraw scoreOnScreenDraw;
    static BonusPoint bonusPoint;

    public gamePlayState(GameStateManager gms) {
        super(gms);
        stage = new Stage();
        player = new Player(world);
        batch = new SpriteBatch();
        this.audioWireBitmapFont = new BitmapFont(Gdx.files.internal("Font/Audiowide.fnt"));
        this.glyphLayout = new GlyphLayout();
        this.glyphLayout.setText(audioWireBitmapFont,TITLE);
        Gdx.input.setCatchBackKey(true);

        world = new World(new Vector2(0,0),true);
        b2dr = new Box2DDebugRenderer();

        //Player body
        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.position.set(player.coordinates.x,player.coordinates.y);
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBody = world.createBody(playerBodyDef);

        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(10,24,new Vector2(0,12),0);

        FixtureDef playerFixtureDef = new FixtureDef();
        playerFixtureDef.shape = playerShape;
        playerBody.createFixture(playerFixtureDef);
        playerBody.setUserData("player");
        playerFixtureDef.isSensor = true;

        //playerBody.setAngularVelocity(-2.16f);

        //score bodies
        BodyDef scoreBodyDef = new BodyDef();
        scoreBodyDef.position.set(0,0);
        scoreBodyDef.type = BodyDef.BodyType.DynamicBody;
        scoreBody = world.createBody(scoreBodyDef);
        scoreBody2 = world.createBody(scoreBodyDef);
        bonusBody = world.createBody(scoreBodyDef);


        PolygonShape scoreShape = new PolygonShape();
        scoreShape.setAsBox(13,13);  //original was 10, 10

        FixtureDef scoreFixtureDef = new FixtureDef();
        scoreFixtureDef.shape = scoreShape;
        scoreBody.createFixture(scoreFixtureDef);
        scoreBody2.createFixture(scoreFixtureDef);
        scoreBody.setUserData("scorePoint");
        scoreBody2.setUserData("scorePoint2");
        bonusBody.createFixture(scoreFixtureDef);
        bonusBody.setUserData("bonusPoint");
        //


        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(1);
        rayHandler.setAmbientLight(0.11f, 0.11f, 0.11f, 1);
        playerLight = new PointLight(rayHandler,100, Color.WHITE,300,0,0);
        playerLight.attachToBody(playerBody,0,16);

        scoreLight = new PointLight(rayHandler,100,Color.YELLOW,200,0,0);
        scoreLight.attachToBody(scoreBody,0,0);
        scoreLight2 = new PointLight(rayHandler,100,Color.YELLOW,200,0,0);
        scoreLight2.attachToBody(scoreBody2,0,0);
        bonusLight = new PointLight(rayHandler,100,Color.CYAN,200,0,0);
        bonusLight.attachToBody(bonusBody);

        playerLight.setColor(player.setUpColor());
        score = 0;
        totalMoney = State.money;
        moneyAdded=0;


        scorePoint = new ScorePoint(1);
        scorePoint2 = new ScorePoint(2);

        bonusPoint = new BonusPoint();

        gamePlayContactListener = new gamePlayContactListener();
        world.setContactListener(gamePlayContactListener);

        scoreOnScreenDraw = new ScoreOnScreenDraw();


    }




    public static void giveMoney(int money){
        moneyAdded+=money;
        totalMoney +=money;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if(!player.isMoving)
            player.startMove();
            //else player.isMoving=false;
        }
    }

    @Override
    public void update(float deltaTime) {
        bonusPoint.update();
        if(scorePoint.givesMoney){
            scoreLight.setColor(0.3f,1,0.3f,1);
        }else {
            scoreLight.setColor(1,1,0,1);
        }

        if(scorePoint2.givesMoney){
            scoreLight2.setColor(0.3f,1,0.3f,1);
        }else {
            scoreLight2.setColor(1,1,0,1);
        }

        handleInput();
        world.step(Gdx.graphics.getDeltaTime(),6,2);
        rayHandler.update();

        playerBody.setTransform(player.coordinates.x,player.coordinates.y-22,player.rotation/57.15f-0.03f);  // ¯\_(ツ)_/¯ //
        //playerBody.setTransform(player.coordinates.x,player.coordinates.y-22,player.rotation/57.15f-0.03f);  // ¯\_(ツ)_/¯ //
        scoreBody.setTransform(scorePoint.x+16,scorePoint.y+16,scorePoint.rotation/57.15f-0.03f);
        scoreBody2.setTransform(scorePoint2.x+16,scorePoint2.y+16,scorePoint2.rotation/57.15f-0.03f);

        bonusBody.setTransform(bonusPoint.x+16,bonusPoint.y+16,bonusPoint.rotation/57.15f-0.03f);
        //playerBody.setTransform(player.coordinates.x,player.coordinates.y-22,player.rotation/60f);
        player.update();

        //if(player.hitWith(bad object)){
        //  player.gotHit();
        //}else if(player.hitWith(good object)){
        //  player.isSafe();
        //  score++;
        //}
        scorePoint.update();
        scorePoint2.update();
        scoreOnScreenDraw.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if(State.graphicsOn)
        rayHandler.render();
        camera.setToOrtho(false, WIDTH,HEIGHT);
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        rayHandler.setCombinedMatrix(camera.combined);
        player.draw(batch);
        audioWireBitmapFont.getData().setScale(0.4f);
        audioWireBitmapFont.draw(
                batch,
                "score: " + score,
                8,
                HEIGHT - 8);
        audioWireBitmapFont.draw(
                batch,
                "Money: " + totalMoney + " (+" + moneyAdded + ")",
                8,
                HEIGHT - 22);

        scorePoint2.draw(batch);
        scorePoint.draw(batch);
        bonusPoint.draw(batch);
        scoreOnScreenDraw.draw(batch,audioWireBitmapFont);
        batch.end();
        //b2dr.render(world,batch.getProjectionMatrix());
    }



    @Override
    public void dispose() {
        player.dispose();
        batch.dispose();
        world.dispose();
        b2dr.dispose();
        stage.dispose();
        rayHandler.dispose();
    }
}
