package com.yihaomen.test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yihaomen.mybatis.inter.IUserOperation;
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
			String userInfo = "���֣�" + user.getUserName() + ", ��ַ��"
					+ user.getUserAddress() + ", ���g��" + user.getUserAge();
			System.out.println(userInfo);
		}
	}
	
	
	public static void main2(String[] args) throws IOException {
		//mybatis�������ļ�
		String resource = "config/conf.xml";
		
		
		//ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
//		InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
		//����sqlSession�Ĺ���
//		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		
		
		
        //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
		Reader reader = Resources.getResourceAsReader(resource);
		//����sqlSession�Ĺ���
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		
		
		
		//������ִ��ӳ���ļ��е�sql��SQLSession
		SqlSession session = sessionFactory.openSession();
		
		/**
         * ӳ��sql�ı�ʶ�ַ�����
         * me.gacl.mapping.userMapper��userMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
         * getUser��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
         */
		
		String statement = "com.yihaomen.mybatis.models.User.selectUserByID";//ӳ��sql�ı�ʶ�ַ���
		User user = session.selectOne(statement,1);
		if(user != null){
			String userInfo = "���֣�" + user.getUserName() + ", ��ַ��"
					+ user.getUserAddress() + ", ���g��" + user.getUserAge();
			System.out.println(userInfo);
		}
	}
	
	public static void main3(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		
		IUserOperation userOperation = session.getMapper(IUserOperation.class);
		User user = userOperation.selectUserByID(1);
		
		if(user != null){
			String userInfo = "���֣�" + user.getUserName() + ", ��ַ��"
					+ user.getUserAddress() + ", ���g��" + user.getUserAge();
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
					String userInfo = "���֣�" + user.getUserName() + ", ��ַ��"
							+ user.getUserAddress() + ", ���g��" + user.getUserAge();
					System.out.println(userInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public static void main(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		User user = new User("songll","111","shandong zhongguo");
		
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			userOperation.addUser(user);
			session.commit();
			System.out.println("��ǰ���ӵ��û� idΪ:"+user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}
