package com.parker.tank.flyweight;

import com.parker.tank.Bullet;
import com.parker.tank.Tank;
import com.parker.tank.dist.Dir;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.flyweight
 * @Author: Parker
 * @CreateTime: 2020-08-15 22:56
 * @Description: 子弹池
 *
 * 享元模式
 *
 */
public enum BulletPool {

    INSTANCE;

    private LinkedBlockingDeque<Bullet> waitBullet = new LinkedBlockingDeque<>();

    private LinkedBlockingDeque<Bullet> usedBullet = new LinkedBlockingDeque<>();


    public Bullet getLiveBullet(Tank tank,Dir dir) {
        Bullet bullet;
        // 如果为空则在使用栈里新加子弹
        if(waitBullet.isEmpty()){
            //System.out.println("新建开始子弹 ----------------------- 当前等待池中子弹数量："+waitBullet.size()+"  当前使用池中子弹的数量："+usedBullet.size());
            bullet = new Bullet(tank.getX(),tank.getY(),dir,tank);
            try {
                usedBullet.put(bullet);
            }catch (Exception e){
                System.out.println("子弹存入使用池失败！");
            }
            //System.out.println("新建结束子弹 ----------------------- 当前等待池中子弹数量："+waitBullet.size()+"  当前使用池中子弹的数量："+usedBullet.size());

        }else{
            // 如果等待池中子弹大于500发 则清空
            if(waitBullet.size() > 500){
                waitBullet.clear();
                return this.getLiveBullet(tank,dir);
            }

            //System.out.println("池中取子弹 开始 *****  当前等待池中子弹数量："+waitBullet.size()+"  当前使用池中子弹的数量："+usedBullet.size());
            try {
                bullet = waitBullet.take();
            }catch (Exception e){
                System.out.println("子弹从等待池删除失败！");
                bullet = new Bullet(tank.getX(),tank.getY(),dir,tank);
            }
            // 重制子弹
            bullet.revertBullet(tank.getX(),tank.getY(),dir,tank);
            // 如果put不进去子弹，则放弃这颗子弹
            try {
                usedBullet.put(bullet);
            }catch (Exception e){
                System.out.println("子弹存入使用池失败！");
            }
            //System.out.println("池中取子弹 结束 *****  当前等待池中子弹数量："+waitBullet.size()+"  当前使用池中子弹的数量："+usedBullet.size());

        }
        return bullet;
    }

    /**
     * 归还子弹
     * @param bullet
     */
    public void revertBullet(Bullet bullet){
        //System.out.println("归还子弹 开始 ¥¥¥ 当前等待池中子弹数量："+waitBullet.size()+"  当前使用池中子弹的数量："+usedBullet.size());
        // 删除当前子弹
        usedBullet.remove(bullet);
        // 如果put不进去子弹，则放弃这颗子弹
        try {
            // 子弹重新设置为 等待状态
            waitBullet.put(bullet);
        }catch (Exception e){
            System.out.println("子弹存入使用池失败！");
        }
        //System.out.println("归还子弹 结束 ¥¥¥ 当前等待池中子弹数量："+waitBullet.size()+"  当前使用池中子弹的数量："+usedBullet.size());
    }

}
