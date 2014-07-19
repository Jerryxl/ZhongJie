package com.zj.util;


import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author xueliang
 * 数据库操作类，实现数据库操作，此类为常用类，实现对数据库的各种操作
 * 主要包含带约束的和不带约束的
 * 带约束的是参数中有不定参数的
 */
public class DbHelper {

	private static Connection conn = null;
	private static PreparedStatement preparedStatement = null;
	private static CallableStatement callableStatement = null;

	/**
	 * 获得下一个id编号，用于编码的生成，需要实现同步
	 * @param tablename 待查询的表名
	 * @param keyname 自增主键列
	 * @return 下一个返回值
	 * @throws FileNotFoundException 
	 * */
	public synchronized static int getNextId(String tablename,String keyname) throws SQLException{
		String sql="select max("+keyname+") as "+keyname+" from "+tablename;
		System.out.println(sql);
		ResultSet rs=null;
		try{
			getPreparedStatement(sql);
			rs=preparedStatement.executeQuery();
			while(rs.next()){
				return rs.getInt(keyname)+1;
			}
			return 0;
		}catch(SQLException e){
			System.out.println("无前置数据，当前编码应为0");
			return 0;
		}finally{
			free(rs);
		}
	}
	/**
	 * 查询操作，采用prestatement,不带约束参数
	 * @param sql
	 * @return 数据集
	 * @throws SQLException
	 * 返回的是List list中的内容是Map
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	public static List query(String sql) throws SQLException {
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			rs = preparedStatement.executeQuery();
			return ResultToListMap(rs);
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs);
		}

	}

	/**
	 * 获得查询结果
	 * 采用prestatement ,带有约束参数
	 * @param sql
	 * @param paramters,不定参数
	 * @return 结果集合
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	public static List query(String sql, Object... paramters)
			throws SQLException {

		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			rs = preparedStatement.executeQuery();
			return ResultToListMap(rs);
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs);
		}
	}
	
	/**
	 * 注意这里不能连表查询，Bean对应的表应该和sql中的表名一样
	 * 只适合单表查询构造bean 返回
	 * 
	 * 获得查询结果,返回结果为Bean形式，是一个Object对象
	 * 采用prestatement ,带有约束参数
	 * @param bean 待填充的对象，填充后返回,参数bean必须是已经实例化的
	 * @param sql
	 * @param paramters,不定参数
	 * @return 填充后的返回结果
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */ 
	@SuppressWarnings("rawtypes")
	public static Object querySingleBean(Object bean,String sql, Object... paramters)
			throws SQLException {
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			rs = preparedStatement.executeQuery();
			List list= ResultToListMap(rs);
			if(list==null||list.size()==0){
				return null;
			}
			//下面封装Bean根据查询的Map结果封装返回的bean
			HashMap map=(HashMap) list.get(0);
			
			try {
				BeanUtils.populate(bean, map);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return bean;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs);
		}
	}
	
	/**
	 * 注意这里Bean对已的表必须和sql中表一致
	 * 
	 * 获得查询结果,返回结果为Bean形式，是一个Object对象
	 * 采用prestatement ,带有约束参数
	 * @param bean 待填充的对象，填充后返回,参数bean必须是已经实例化的
	 * @param sql
	 * @param paramters,不定参数
	 * @return 填充后的返回结果
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws FileNotFoundException 
	 */ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List queryMultiBean(Object bean,String sql, Object... paramters)
			throws SQLException, InstantiationException, IllegalAccessException {
		List result=new ArrayList();
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			rs = preparedStatement.executeQuery();
			List list= ResultToListMap(rs);
			if(list==null||list.size()==0){
				return null;
			}
			for(int i=0;i<list.size();i++){
				HashMap map=(HashMap) list.get(i);
				Object obj=bean.getClass().newInstance();
				try {
					BeanUtils.populate(obj, map);
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				result.add(obj);
			}
			return result;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs);
		}
	}


	/**
	 * 返回单个结果的值，如count\min\max等等，没有参数约束
	 * @param sql
	 * @return  单一结果
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public static Object getSingle(String sql) throws SQLException {
		Object result = null;
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getObject(1);
			}
			return result;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs);
		}

	}

	/**
	 * 返回单个结果的值，如count\min\max等等，有参数约束
	 * @param sql 执行语句
	 * @param paramters  约束
	 * @return 查询结果
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public static Object getSingle(String sql, Object... paramters)
			throws SQLException {
		Object result = null;
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getObject(1);
			}
			return result;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs);
		}
	}

	
	/**
	 * 更新操作，无约束
	 * 
	 * @param sql
	 * @return 更新条目数量
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public static int update(String sql) throws SQLException {

		try {
			getPreparedStatement(sql);

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free();
		}
	}

	/**
	 * 更新操作，带有约束
	 * @param sql
	 * @param paramters 约束条件
	 * @return 更新条目数量
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public static int update(String sql, Object... paramters)
			throws SQLException {
		try {
			getPreparedStatement(sql);

			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free();
		}
	}

	/**
	 * 插入后返回主键值，无约束，单条记录
	 * @param sql
	 * @return  主键值
	 * @throws FileNotFoundException 
	 * @throws Exception
	 */
	public static Object insertWithReturnPrimeKey(String sql)
			throws SQLException {
		ResultSet rs = null;
		Object result = null;
		try {
			conn = DbUnits.getConnection();
			preparedStatement = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.execute();
			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getObject(1);
			}
			return result;
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}

	/**
	 * 插入后返回主键，有约束，单条记录
	 * 需要返回值，自己转化为相应的类型
	 * @param sql
	 * @param paramters 约束条件
	 * @return  主键值的字符串类型
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public static String insertWithReturnPrimeKey(String sql,
			Object... paramters) throws SQLException {
		ResultSet rs = null;
		Object result = null;
		try {
			conn = DbUnits.getConnection();
			preparedStatement = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			preparedStatement.execute();
			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getObject(1);
			}
			return ""+result;
		} catch (SQLException e) {
			throw new SQLException(e);
		}

	}

	/**
	 * 调用存储过程执行查询
	 * @param procedureSql 存储过程语句
	 * @return 结果集
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	public static List callableQuery(String procedureSql) throws SQLException {
		ResultSet rs = null;
		try {
			getCallableStatement(procedureSql);
			rs = callableStatement.executeQuery();
			return ResultToListMap(rs);
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs);
		}
	}

	/**
	 *调用存储过程执行查询，无约束
	 * @param procedureSql  存储过程
	 * @param paramters  约束
	 * @return 结果集
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	public static List callableQuery(String procedureSql, Object... paramters)
			throws SQLException {
		ResultSet rs = null;
		try {
			getCallableStatement(procedureSql);

			for (int i = 0; i < paramters.length; i++) {
				callableStatement.setObject(i + 1, paramters[i]);
			}
			rs = callableStatement.executeQuery();
			return ResultToListMap(rs);
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs);
		}
	}

	/**
	 * 调用存储过程，查询单个值，不带约束
	 * @param procedureSql 过程语句
	 * @return 单一结果
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public static Object callableGetSingle(String procedureSql)
			throws SQLException {
		Object result = null;
		ResultSet rs = null;
		try {
			getCallableStatement(procedureSql);
			rs = callableStatement.executeQuery();
			while (rs.next()) {
				result = rs.getObject(1);
			}
			return result;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs);
		}
	}

	/**
	 * 调用存储过程，查询单个值，带约束
	 * @param procedureSql
	 * @param parameters 约束条件，不定参数
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public static Object callableGetSingle(String procedureSql,Object... paramters) throws SQLException {
		Object result = null;
		ResultSet rs = null;
		try {
			getCallableStatement(procedureSql);

			for (int i = 0; i < paramters.length; i++) {
				callableStatement.setObject(i + 1, paramters[i]);
			}
			rs = callableStatement.executeQuery();
			while (rs.next()) {
				result = rs.getObject(1);
			}
			return result;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free(rs);
		}
	}

//	public static Object callableWithParamters(String procedureSql)
//			throws SQLException {
//		try {
//			getCallableStatement(procedureSql);
//			callableStatement.registerOutParameter(0, Types.OTHER);
//			callableStatement.execute();
//			return callableStatement.getObject(0);
//		} catch (SQLException e) {
//			throw new SQLException(e);
//		} finally {
//			free();
//		}
//
//	}

	/**
	 * 调用存储过程执行更新操作，无约束
	 * @param procedureSql
	 * @return 更新的数量
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public static int callableUpdate(String procedureSql) throws SQLException {
		try {
			getCallableStatement(procedureSql);
			return callableStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free();
		}
	}

	/**
	 * 调用存储过程执行更新操作，有约束
	 * @param procedureSql
	 * @param parameters
	 * @return 更新数量
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public static int callableUpdate(String procedureSql, Object... parameters)
			throws SQLException {
		try {
			getCallableStatement(procedureSql);
			for (int i = 0; i < parameters.length; i++) {
				callableStatement.setObject(i + 1, parameters[i]);
			}
			return callableStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			free();
		}
	}

	/**
	 * 批量更新，返回更新结果
	 * @param sqlList
	 * @return
	 */
	public static int[] batchUpdate(List<String> sqlList) {

		int[] result = new int[] {};
		Statement statenent = null;
		try {
			conn = DbUnits.getConnection();
			conn.setAutoCommit(false);
			statenent = conn.createStatement();
			for (String sql : sqlList) {
				statenent.addBatch(sql);
			}
			result = statenent.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new ExceptionInInitializerError(e1);
			}
			throw new ExceptionInInitializerError(e);
		} finally {
			free(statenent, null);
		}
		return result;
	}

	/**
	 * 根据返回的数据集构造集合
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static List ResultToListMap(ResultSet rs) throws SQLException {
		List list = new ArrayList();
		while (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			Map map = new HashMap();
			for (int i =1; i <= md.getColumnCount(); i++) {
				map.put(md.getColumnLabel(i), rs.getObject(i));
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 获取PreparedStatement
	 * @param sql
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	private static void getPreparedStatement(String sql) throws SQLException {
		conn = DbUnits.getConnection();
		preparedStatement = conn.prepareStatement(sql);
	}

	/**
	 * 获取CallableStatement
	 * 
	 * @param procedureSql
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	private static void getCallableStatement(String procedureSql)
			throws SQLException {
		conn = DbUnits.getConnection();
		callableStatement = conn.prepareCall(procedureSql);
	}

	/**
	 * 释放资源
	 * @param rs
	 */
	public static void free(ResultSet rs) {

		DbUnits.free(conn, preparedStatement, rs);
	}

	/**
	 * 释放资源
	 * @param statement
	 * @param rs
	 */
	public static void free(Statement statement, ResultSet rs) {
		DbUnits.free(conn, statement, rs);
	}

	/**
	 * 释放资源
	 */
	public static void free() {

		free(null);
	}
	
	
//	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException{
//		List list=new ArrayList();
//		list=DbHelper.queryMultyBean(new Srwarehouse(), "select * from srwareouse where warehouseName=?", "asdfdf");
//		Srwarehouse h1=(Srwarehouse) list.get(0);
//		Srwarehouse h2=(Srwarehouse) list.get(1);
//		System.out.println(h1.getAddress());
//		System.out.println(h2.getWarehouseNumber());
//		System.out.println(list.size());
//		
//		
//	}

}
