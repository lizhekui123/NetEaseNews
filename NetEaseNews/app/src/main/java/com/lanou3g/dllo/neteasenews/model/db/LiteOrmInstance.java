package com.lanou3g.dllo.neteasenews.model.db;

import com.lanou3g.dllo.neteasenews.ui.app.NetEaseNewsApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.List;

/**
 * Created by dllo on 16/10/11.
 * 数据库操作单例类
 */
public class LiteOrmInstance {

    private static LiteOrmInstance instance;

    /**
     * 数据库名
     */
    private static final String DB_NAME = "collect.db";

    /**
     * LiteOrm对象
     */
    private LiteOrm liteOrm;

    private LiteOrmInstance() {
        liteOrm = LiteOrm.newSingleInstance(NetEaseNewsApp.getContext(), DB_NAME);
    }
    public static LiteOrmInstance getInstance() {
        if(instance == null){
            synchronized (LiteOrmInstance.class){
                if(instance == null){
                    instance = new LiteOrmInstance();
                }
            }
        }
        return instance;
    }

    /*******增删改查*******/

    /**
     * 插入一条数据
     */
    public void insert (CollectBean cb) {
        liteOrm.insert(cb);
    }
    public void insert (UserInfoBean uib) {
        liteOrm.insert(uib);
    }

    /**
     * 插入集合数据
     */
    public void insert (List<CollectBean> cbs) {
        liteOrm.insert(cbs);
    }

    /**
     * 多张表查询所有
     */
    public <T> List<T> queryAllData(Class<T> cls) {
        return liteOrm.query(cls);
    }

    /**
     * 查询所有收藏数据
     */
    public List<CollectBean> queryAllCollect(){
        return liteOrm.query(CollectBean.class);
    }

    /**
     * 查询所有用户数据
     */
    public List<UserInfoBean> queryAllUser(){
        return liteOrm.query(UserInfoBean.class);
    }

    /**
     * 按用户查找收藏内容
     */
    public List<CollectBean> queryByUser(String username){
        QueryBuilder<CollectBean> qb = new QueryBuilder<>(CollectBean.class);
        qb.where("username = ? ", new String[] {username});
        return liteOrm.query(qb);
    }

    /**
     * 按用户名+url查询收藏数据
     */
    public List<CollectBean> queryByUserUrl(String userUrl){
        QueryBuilder<CollectBean> qb = new QueryBuilder<>(CollectBean.class);
        qb.where("userUrl = ? ", new String[] {userUrl});
        return liteOrm.query(qb);
    }

    /**
     * 按用户名+url删除收藏数据
     */
    public void deleteByUserUrl(String userUrl){
        WhereBuilder wb = new WhereBuilder(CollectBean.class);
        wb.where("userUrl = ? ",new String[]{userUrl});
        liteOrm.delete(wb);
    }

    /**
     * 按用户删除收藏数据
     */
    public void deleteByUser(String username){
        WhereBuilder wb = new WhereBuilder(CollectBean.class);
        wb.where("username = ? ",new String[]{username});
        liteOrm.delete(wb);
    }

    /**
     * 删除所有收藏数据
     */
    public void deleteAllCollect(){
        liteOrm.deleteAll(CollectBean.class);
    }
    /**
     * 删除所有用户数据
     */
    public void deleteAllUser(){
        liteOrm.deleteAll(UserInfoBean.class);
    }

}
