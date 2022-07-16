
class Player extends Collision
{
  boolean isBulletIntervalTime;      // 自機の連射無効フラグ
  int bulletIntervalTime;              // 自機の連射無効時間
  int currentBulletIntervalTime;       // 自機の弾を発射してからの時間

  // -----------------------------------------
  // コンストラクタ
  // -----------------------------------------
  Player()
  {
    this.x = width/2;
    this.y = height/2;
    this.size = 50;
    this.isBulletIntervalTime = false;
    this.bulletIntervalTime = 10;
    this.currentBulletIntervalTime = 0;
  }
  
  // -----------------------------------------
  // 更新用
  // -----------------------------------------
  void update()
  {
    if (mouseX != 0 && mouseY != 0)
    {
      this.x = mouseX;
      this.y = mouseY;
    }
    
    if (this.isBulletIntervalTime)
    {
      this.currentBulletIntervalTime++;
      
      if (this.currentBulletIntervalTime > this.bulletIntervalTime)
      {
        this.isBulletIntervalTime = false;
        this.currentBulletIntervalTime = 0;
      }
    }
  }

  // -----------------------------------------
  // 表示用
  // -----------------------------------------
  void draw()
  {
    fill(255, 255, 255);
    circle(this.x, this.y, this.size);
  }
  
  // -----------------------------------------
  // 弾を発射する
  // -----------------------------------------
  void shot()
  {
    if (!this.isBulletIntervalTime)
    {
      Bullet b = bulletManager.createBullet(0);
      b.x = this.x;
      b.y = this.y;
      b.size = 10;
      b.speed = 5;
      
      this.isBulletIntervalTime = true;
    }
  }
}
