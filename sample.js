import java.util.*;

int playerX;
int playerY;
int playerSize = 50;

ArrayList<Integer> playerBulletX = new ArrayList<Integer>();
ArrayList<Integer> playerBulletY = new ArrayList<Integer>();
int playerBulletSize = 10;
int playerBulletSpeed = 5;
boolean isBulletIntervalTime = false;    // 自機の連射無効フラグ
int bulletIntervalTime = 10;              // 自機の連射無効時間
int currentBulletIntervalTime = 0;       // 自機の弾を発射してからの時間

ArrayList<Integer> enemyX = new ArrayList<Integer>();
ArrayList<Integer> enemyY = new ArrayList<Integer>();
ArrayList<Integer> enemySpeed = new ArrayList<Integer>();
ArrayList<Boolean> enemyIsDead = new ArrayList<Boolean>();
ArrayList<Integer> enemySize = new ArrayList<Integer>();

// 敵のタイプ
// 0：上から下にきて、途中で止まって上に戻る
// 1：上から下に流れていく
// 2：左から右に流れていく
// 3：右から左に流れていく
ArrayList<Integer> enemyType = new ArrayList<Integer>();
ArrayList<Integer> enemyCount = new ArrayList<Integer>();

void setup()
{
  size(640, 480);
  
  noStroke();
  
  playerX = width/2;
  playerY = height/2;
}

void draw()
{
  background(220);
  
  // 自機の弾を発射する
  if (mousePressed)
  {
    if (!isBulletIntervalTime)
    {
      playerBulletX.add(playerX);
      playerBulletY.add(playerY);
      
      isBulletIntervalTime = true;
    }
  }
  
  if (isBulletIntervalTime)
  {
    currentBulletIntervalTime++;
    
    if (currentBulletIntervalTime > bulletIntervalTime)
    {
      isBulletIntervalTime = false;
      currentBulletIntervalTime = 0;
    }
  }
  
  if (mouseX != 0 && mouseY != 0)
  {
    playerX = mouseX;
    playerY = mouseY;
  }
  
  fill(255, 255, 255);
  circle(playerX, playerY, playerSize);
  
  for (int i = 0; i < playerBulletX.size(); i++)
  {
    int y = playerBulletY.get(i);
    
    //playerBulletY[i] = y - playerBulletSpeed;
    playerBulletY.set(i, y - playerBulletSpeed);
    
    fill(255, 255, 255);
    circle(playerBulletX.get(i), playerBulletY.get(i), playerBulletSize);
  }
  
  Iterator<Integer> itX = playerBulletX.iterator();
  Iterator<Integer> itY = playerBulletY.iterator();
  while (itX.hasNext() && itY.hasNext())
  {
    itX.next();
    int y = itY.next();
    
    if (y < 0)
    {
      itX.remove();
      itY.remove();
    }
  }
  

  if ((int)random(0, 100) == 0)
  {
    int type = (int)random(0, 4);
    //int type = 0;
    int size = (int)random(30, 70);
    
    if (type == 0)
    {
      enemyX.add((int)random(0, width - size));
      enemyY.add(-size);
      enemySpeed.add((int)random(2, 6));
    }
    else if (type == 1)
    {
      enemyX.add((int)random(0, width - size));
      enemyY.add(-size);
      enemySpeed.add((int)random(2, 4));      
    }
    else if (type == 2)
    {
      enemyX.add(width);
      enemyY.add((int)random(0, height/3));
      enemySpeed.add((int)random(2, 4));
    }
    else if (type == 3)
    {
      enemyX.add(-size);
      enemyY.add((int)random(0, height/3));
      enemySpeed.add((int)random(2, 4));
    }
    
    enemyType.add(type);
    enemySize.add(size);
    enemyCount.add(0);
    enemyIsDead.add(false);
  }

  for (int i = 0; i < enemyX.size(); i++)
  {
    int type = enemyType.get(i);
    int count = enemyCount.get(i);
    
    if (type == 0)
    {
      int y = enemyY.get(i);
      
      if (count < 50)
      {
        y += enemySpeed.get(i);
      }
      else if (count > 150)
      {
        y -= enemySpeed.get(i);
      }
      
      enemyY.set(i, y);
    }
    else if (type == 1)
    {
      int y = enemyY.get(i);
      y += enemySpeed.get(i);
      enemyY.set(i, y);      
    }
    else if (type == 2)
    {
      int x = enemyX.get(i);
      x -= enemySpeed.get(i);
      enemyX.set(i, x);
    }
    else if (type == 3)
    {
      int x = enemyX.get(i);
      x += enemySpeed.get(i);
      enemyX.set(i, x);
    }
    
    count++;
    enemyCount.set(i, count);
    
    fill(224, 255, 253);
    rect(enemyX.get(i), enemyY.get(i), enemySize.get(i), enemySize.get(i));
  }
  
  Iterator<Integer> itEnemyX = enemyX.iterator();
  Iterator<Integer> itEnemyY = enemyY.iterator();
  Iterator<Integer> itEnemySpeed = enemySpeed.iterator();
  Iterator<Boolean> itEnemyIsDead = enemyIsDead.iterator();
  Iterator<Integer> itEnemySize = enemySize.iterator();
  Iterator<Integer> itEnemyType = enemyType.iterator();
  Iterator<Integer> itEnemyCount = enemyCount.iterator();
  while (itEnemyIsDead.hasNext())
  {
    boolean isDead = itEnemyIsDead.next();
    itEnemyX.next();
    itEnemyY.next();
    itEnemySpeed.next();
    itEnemySize.next();
    itEnemyType.next();
    itEnemyCount.next();
    
    if (isDead)
    {
      itEnemyIsDead.remove();
      itEnemyX.remove();
      itEnemyY.remove();
      itEnemySpeed.remove();
      itEnemySize.remove();
      itEnemyType.remove();
      itEnemyCount.remove();
    }
  }
}
