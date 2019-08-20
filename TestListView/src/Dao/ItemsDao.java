package Dao;

import entity.Items;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static utils.DBHelper.getConnection;

public class ItemsDao {
    //获取所有的商品信息
    public ArrayList<Items> getAllItems(){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        //商品集合
        ArrayList<Items> list=new ArrayList<Items>();
        try{
            connection= DBHelper.getConnection();
            //sql语句
            String sql="select * from items";
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                Items item=new Items();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setCity(resultSet.getString("city"));
                item.setNumber(resultSet.getInt("number"));
                item.setPrice(resultSet.getInt("price"));
                item.setPicture(resultSet.getString("picture"));
                //每次添加一个商品
                list.add(item);
            }
            //返回商品集合
            return list;
        }catch (Exception e){
           // e.printStackTrace();
            return null;
        }finally {
            //释放数据集对象
            if (resultSet!=null){
                try {
                    resultSet.close();
                    resultSet=null;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (statement!=null){
                try {
                    statement.close();
                    statement=null;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    // 根据商品编号获得商品资料
    public Items getItemsById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            // SQL语句
            String sql = "select * from items where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCity(rs.getString("city"));
                item.setNumber(rs.getInt("number"));
                item.setPrice(rs.getInt("price"));
                item.setPicture(rs.getString("picture"));
                return item;
            } else {
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    //获取最近浏览的前五条商品信息
    public ArrayList<Items> getViewList(String list)
    {
        System.out.println("list:"+list);
        ArrayList<Items> itemlist = new ArrayList<Items>();
        //每次返回前五条记录
        int iCount=5;
        if(list!=null&&list.length()>0)
        {
            String[] arr = list.split(",");
            System.out.println("arr.length="+arr.length);
            //如果商品记录大于等于5条
            if(arr.length>=5)
            {
                for(int i=arr.length-1;i>=arr.length-iCount;i--)
                {
                    itemlist.add(getItemsById(Integer.parseInt(arr[i])));
            }
            }
            else
            {
                for(int i=arr.length-1;i>=0;i--)
                {
                    itemlist.add(getItemsById(Integer.parseInt(arr[i])));
                }
            }
            return itemlist;
        }
        else
        {
            return null;
        }

    }
}
