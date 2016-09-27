package com.yihaomen.test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yihaomen.mybatis.inter.IUserOperation;
import com.yihaomen.mybatis.model.Article;
import com.yihaomen.mybatis.model.User;

public class Test {

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	
	static{
		try {
			reader = Resources.getResourceAsReader("config/conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main1(String[] args) {
		
		SqlSession session = sqlSessionFactory.openSession();
		User user = (User)session.selectOne("com.yihaomen.mybatis.models.User.selectUserByID",1);
		if(user != null){
			String userInfo = "名字：" + user.getUserName() + ", 地址："
					+ user.getUserAddress() + ", 年齡：" + user.getUserAge();
			System.out.println(userInfo);
		}
	}
	
	
	public static void main2(String[] args) throws IOException {
		//mybatis的配置文件
		String resource = "config/conf.xml";
		
		
		//使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
//		InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
		//构建sqlSession的工厂
//		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		
		
		
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
		Reader reader = Resources.getResourceAsReader(resource);
		//构建sqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		
		
		
		//创建能执行映射文件中的sql的SQLSession
		SqlSession session = sessionFactory.openSession();
		
		/**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
		
		String statement = "com.yihaomen.mybatis.models.User.selectUserByID";//映射sql的标识字符串
		User user = session.selectOne(statement,1);
		if(user != null){
			String userInfo = "名字：" + user.getUserName() + ", 地址："
					+ user.getUserAddress() + ", 年齡：" + user.getUserAge();
			System.out.println(userInfo);
		}
	}
	
	public static void main3(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		User user = userOperation.selectUserByID(1);
		
		if(user != null){
			String userInfo = "名字：" + user.getUserName() + ", 地址："
					+ user.getUserAddress() + ", 年齡：" + user.getUserAge();
			System.out.println(userInfo);
		}
	}
	
	public static void main4(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			List<User> list = userOperation.selectUsers("%song%");
				
			for(User user : list){
				
				if(user != null){
					String userInfo = "名字：" + user.getUserName() + ", 地址："
							+ user.getUserAddress() + ", 年齡：" + user.getUserAge();
					System.out.println(userInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public static void main5(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		User user = new User("songll","111","shandong zhongguo");
		/**
	     * 测试增加,增加后，必须提交事务，否则不会写入到数据库.
	     */
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			userOperation.addUser(user);
			session.commit();
			System.out.println("当前增加的用户 id为:"+user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public static void main6(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		/**
		 * 先得到用户,然后修改，提交。
		 */
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			User user = userOperation.selectUserByID(5);
			user.setUserAddress("原来是魔都的浦东创新园区");
			userOperation.updateUser(user);
			session.commit();
			System.out.println("当用户 UserAddress为:"+user.getUserAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public static void main7(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		/**
		 * 删除数据，删除一定要 commit.
		 */
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			User user = userOperation.selectUserByID(5);
			userOperation.deleteUser(user);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public static void main(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		/**
		 * 删除数据，删除一定要 commit.
		 */
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			List<Article> list = userOperation.getUserArticles(1);
			
			for(Article article : list){
				User user = article.getUser();
				if(user != null){
					String userInfo = "名字：" + user.getUserName() + ", 地址："
							+ user.getUserAddress() + ", 年齡：" + user.getUserAge();
					System.out.println(userInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}
