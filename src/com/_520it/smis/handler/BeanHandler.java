package com._520it.smis.handler;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

/*
 * new BeanHandler(Student.class); 把一行数据封装成学生 
 * new BeanHandler(Person.class); 把一行数据封装成人 
 */
//表示吧结果集中的一行数据，封装成一个对象专门针对结果集中只有一行数据的情况
public class BeanHandler<T> implements IResultSetHandler<T> {
	private Class<T> classType;//把结果集中的一行数据，封装成什么类型的对象
	public BeanHandler(Class<T> classType) {
		this.classType=classType;
	}
	/*
	 * 规范
	 * 1.规定表的列名必须和对象的属性相同
	 * 2 规定表的列名类型必须和Java中的类型匹配 decimal-->BigDecimal /  bigint--> Long
	 */
	public T handle(ResultSet rs) throws Exception {
		//处理结果集三步操作
		 
		 //1 创建对应类的一个对象	
		T obj=classType.newInstance();
		 //2 取出结果集当前光标所在行的某一列数据
		BeanInfo beanInfo=Introspector.getBeanInfo(classType,Object.class);
		PropertyDescriptor[] pds=beanInfo.getPropertyDescriptors(); 
		if(rs.next()) {
	
			for (PropertyDescriptor pd : pds) {
				String columnName=pd.getName();
				Object val= rs.getObject(columnName);
				//3 调用该对象的setter方法，把某一列的数据设置进sql语句
				pd.getWriteMethod().invoke(obj, val);
				
			}
		}
		
		return obj;
	}

}
