
class BulletManager
{
  ArrayList<Bullet> bullets;
  
  BulletManager()
  {
    this.bullets = new ArrayList<Bullet>();
  }
  
  Bullet createBullet(int type)
  {
    Bullet b = null;
    
    // 味方の弾
    if (type == 0)
    {
      b = new Bullet();
    }
    // 敵の弾
    else if (type == 1)
    {
      b = new EnemyBullet();
    }
    
    if (b != null)
    {
      this.bullets.add(b);
    }
    
    return b;
  }
  
  void update(ArrayList<Enemy> enemies)
  {
     Iterator<Bullet> it = this.bullets.iterator();
     while (it.hasNext())
     {
       Bullet b = it.next();
       
       if (b.y < 0 || b.y > height)
       {
         it.remove();
         continue;
       }
       
       // 味方の弾だったとき
       if (!b.isEnemyBullet)
       {
         for (int i = 0; i < enemies.size(); i++)
         {
           // 弾と敵の当たり判定
           if (b.hitTest(enemies.get(i)))
           {
              enemies.get(i).damege();
             
             it.remove();
             break;
           }
         }
       }
       // 敵の弾だったとき
       else
       {
         // 弾と味方の当たり判定
         if (b.hitTestCircle(player))
         {
           it.remove();
         }
       }
     }
    
    for (int i = 0; i < this.bullets.size(); i++)
    {
      this.bullets.get(i).move();
    } 
  }
  
  void draw()
  {
    for (int i = 0; i < this.bullets.size(); i++)
    {
      this.bullets.get(i).draw();
    }
  }
}

class Bullet extends Collision
{
  int speed;
  boolean isEnemyBullet;
  
  Bullet()
  {
  }
  
  void move()
  {
    this.y -= this.speed;
  }
  
  void draw()
  {
    fill(255, 255, 255);
    circle(this.x, this.y, this.size);
  }
}

class EnemyBullet extends Bullet
{
  EnemyBullet()
  {
    this.isEnemyBullet = true;
  }
  
  void move()
  {
    this.y += this.speed;
  }
  
  void draw()
  {
    fill(255, 255, 0);
    circle(this.x, this.y, this.size);
  }
}
