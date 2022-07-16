import java.util.*;

EnemyManager enemyManager;
BulletManager bulletManager;
Player player;

class Collision
{
  int x;
  int y;
  int size;
  
  boolean hitTest(Collision dstColi)
  {
    if (dstColi.x < this.x && dstColi.y < this.y && dstColi.x + dstColi.size > this.x && dstColi.y + dstColi.size > this.y)
    {
      return true;
    }
    
    return false;
  }
  
  boolean hitTestCircle(Collision dstColi)
  {
    double dist = Math.sqrt((dstColi.x - this.x) * (dstColi.x - this.x) + (dstColi.y - this.y) * (dstColi.y - this.y));
    
    if (dist < dstColi.size/2)
    {
      return true;
    }
    
    return false;
  }
}

void setup()
{
  size(640, 480);
  
  noStroke();
  
  enemyManager = new EnemyManager();
  bulletManager = new BulletManager();
  
  // 自機の実体化
  player = new Player();
  
}

void draw()
{
  background(220);
  
  ArrayList<Enemy> enemies = enemyManager.getEnemies();
  
  bulletManager.update(enemies);
  bulletManager.draw();
  
  if (mousePressed)
  {
    // 自機の弾を発射する
    player.shot();
  }
  
  player.update();
  player.draw();
  
  if ((int)random(0, 100) == 0)
  {
    int type = (int)random(0, 4);
    this.enemyManager.createEnemy(type);
  }
  
  enemyManager.update();
  enemyManager.draw();
  
}
