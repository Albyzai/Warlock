package data;

import com.badlogic.gdx.graphics.Texture;
import java.io.Serializable;
import java.util.UUID;

public final class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private EntityType type;
    private float x;
    private float y;
    public float dx;
    public float dy;
    private float radians;
    private float maxSpeed;
    private float acceleration;
    private float deacceleration;
    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    private float[] dists;
    private int rotationSpeed;
    private float speed;
    private int life;
    private float radius;
    private boolean isHit = false;
    private float expiration;
    private int size;
    private int numPoints;
    private float width;
    private boolean isShooting = false;
    private Texture texture;
    
    
    
    public void setIsShooting(boolean b){
        this.isShooting = b;
    }
    
    public boolean getIsShooting(){
        return this.isShooting;
    }
    public void setSpeed(float speed){
        this.speed = speed;
    }
    
    public float getSpeed(){
        return this.speed;
    }
    public void setWidth(float width){
        this.width = width;
    }
    
    public float getWidth(){
        return this.width;
    }
    
    public void setNumpoints(int numpoints){
        this.numPoints = numpoints;
    }
    public int getNumpoints(){
        return this.numPoints;
    }
    
    
    public float[] getDists(){
        return this.dists;
    }
    public void setDists(float[] floatarray){
    this.dists = floatarray;
}
    public void setSize(int size){
        this.size = size;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void reduceExpiration(float delta){
        this.expiration -= delta;
    }
    
    public float getExpiration(){
        return expiration;
    }
    
    public void setExpiration(float value){
        this.expiration = value;
    }
    
    public boolean getIsHit(){
        return isHit;
    }
    
    public void setIsHit(boolean hit){
        this.isHit = hit;
    }
    
    
    public void setRadius(float r){
        this.radius = r;
    }
    
    public float getRadius(){
        return radius;
    }
    
    public int getLife(){
        return life;
    }
    
    public void setLife(int life){
        this.life = life;
    }

    public String getID() {
        return ID.toString();
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public EntityType getType() {
        return type;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getX() {
        return x;
    }
    
    public void setX(float x){
        this.x = x;
    }

    public float getY() {
        return y;
    }
    
    public void setY(float y){
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getRadians() {
        return radians;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getDeacceleration() {
        return deacceleration;
    }

    public void setDeacceleration(float deacceleration) {
        this.deacceleration = deacceleration;
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public boolean isType(EntityType entityType) {
        return this.type.equals(entityType);
    }
    
        public boolean contains(float x, float y)
    {
        boolean b = false;
        for (int i = 0, j = shapeX.length - 1; i < shapeX.length; j = i++) {

            if ((shapeY[i] > y) != (shapeY[j] > y) && 
                    (x < (shapeX[j] - shapeX[i])
                    * (y - shapeY[i]) / (shapeY[j] - shapeY[i])
                    + shapeX[i])) {
                b = !b;
            }
        }
        return b;
    }
}
