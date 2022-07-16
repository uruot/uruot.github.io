
class EnemyManager
{
  ArrayList<Enemy> enemies;
  
  EnemyManager()
  {
    this.enemies = new ArrayList<Enemy>();
  }
  
  ArrayList<Enemy> getEnemies()
  {
    return this.enemies;
  }
  
  void update()
  {
    for (int i = 0; i < this.enemies.size(); i++)
    {
      this.enemies.get(i).update();
    }
    
    Iterator<Enemy> it = this.enemies.iterator();
    while (it.hasNext())
    {
      boolean isDead = it.next().isDead;
      
      if (isDead)
      {
        it.remove();
      }
    }
  }
  
  void draw()
  {
    for (int i = 0; i < this.enemies.size(); i++)
    {
      this.enemies.get(i).draw();
    }
  }
  
  void createEnemy(int type)
  {
    Enemy enemy = null;
    
    if (type == 0)
    {
      enemy = new ReturnEnemy();
    }
    else if (type == 1)
    {
      enemy = new StraightEnemy();
    }
    else if (type == 2)
    {
      enemy = new LeftMoveEnemy();
    }
    else if (type == 3)
    {
      enemy = new RightMoveEnemy();
    }
    
    if (enemy != null)
    {
      this.enemies.add(enemy);
    }
  }
}

//-----------------------------------------
// 敵のベース
//-----------------------------------------
class Enemy extends Collision
{
  int speed;
  int count;
  boolean isDead;
  int life;
  
  Enemy()
  {
    this.count = 0;
    this.size = (int)random(30, 70);
    this.isDead = false;
  }
  
  void update()
  {
    this.count++;
  }
  
  void draw()
  {
    fill(224, 255, 253);
    rect(this.x, this.y, this.size, this.size);
  }
  
  void shot()
  {
    Bullet b = bulletManager.createBullet(1);
    b.x = this.x + this.size/2;
    b.y = this.y + this.size/2;
    b.size = 10;
    b.speed = 5;
  }
  
  void damege()
  {
    this.life--;
    
    if (this.life <= 0)
    {
      this.isDead = true;
    }
  }
}

//-----------------------------------------
// 上から下にきて、途中で止まって上に戻る敵
//-----------------------------------------
class ReturnEnemy extends Enemy
{
  ReturnEnemy()
  {
    this.x = (int)random(0, width - this.size);
    this.y = -this.size;
    this.speed = (int)random(2, 6);
    this.life = 3;
  }
  
  void update()
  {
    super.update();
    
    if (this.count < 50)
    {
      this.y += this.speed;
    }
    else if (this.count > 150)
    {
      this.y -= this.speed;
      
      if (this.y + this.size < 0)
      {
        this.isDead = true;
      }
    }
    
    if ((int)random(0, 50) == 0)
    {
      this.shot();
    }
  }
}

//-----------------------------------------
// 上から下にきて、途中で止まって上に戻る敵
//-----------------------------------------
class StraightEnemy extends Enemy
{
  StraightEnemy()
  {
    this.x = (int)random(0, width - this.size);
    this.y = -this.size;
    this.speed = (int)random(2, 4);
    this.life = 5;
  }
  
  void update()
  {
    super.update();
    
    this.y += this.speed;
    
    if (this.y > height)
    {
      this.isDead = true;
    }
    
    if ((int)random(0, 50) == 0)
    {
      this.shot();
    }

  }
}

//-----------------------------------------
// 左から右に流れていく敵
//-----------------------------------------
class RightMoveEnemy extends Enemy
{
  RightMoveEnemy()
  {
    this.x = 0;
    this.y = (int)random(0, height/3);
    this.speed = (int)random(2, 4);
    this.life = 1;
  }
  
  void update()
  {
    super.update();
    
    this.x += this.speed;
    
    if (this.x > width)
    {
      this.isDead = true;
    }
    
    if ((int)random(0, 50) == 0)
    {
      this.shot();
    }
  }
}

//-----------------------------------------
// 右から左に流れていく敵
//-----------------------------------------
class LeftMoveEnemy extends Enemy
{
  LeftMoveEnemy()
  {
    this.x = width;
    this.y = (int)random(0, height/3);
    this.speed = (int)random(2, 4);
    this.life = 1;
  }
  
  void update()
  {
    super.update();
    
    this.x -= this.speed;
    
    if (this.x + this.size < 0)
    {
      this.isDead = true;
    }

    if ((int)random(0, 50) == 0)
    {
      this.shot();
    }
  }
}
