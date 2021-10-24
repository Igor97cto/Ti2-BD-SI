package libs;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class SQLCV 
{	
	private Field[] fields;
	private String[] fieldnames;	
	private String[] fieldtypes;
	private Object[] fieldvalues;
	private Map<Integer, String> sqltypemap;
	
	
	public SQLCV(Object obj) throws IllegalArgumentException, IllegalAccessException
	{	
		fields= obj.getClass().getDeclaredFields();
		fieldnames= new String[fields.length];
		fieldtypes= new String[fields.length];
		fieldvalues= new Object[fields.length];
		AccessibleObject.setAccessible(fields, true);
		
		for (int i = 0; i< fields.length; i++)
		{
			fieldnames[i] = fields[i].getName();
			fieldtypes[i] = fields[i].getType().getSimpleName();
			fieldvalues[i] = fields[i].get(obj);
		}
	}
	
	private Map<Integer, String> setSqlTypeMap() throws IllegalArgumentException, IllegalAccessException
	{
		Field[] sqltypefield= Types.class.getFields();
		Map<Integer, String> result= new HashMap<Integer, String>();
		
		for (Field field : sqltypefield)
		{
	        result.put((Integer)field.get(null), field.getName());
	    }
		
		return result;
	}
	
	
	public String createTable()
	{
		
		return "";
	}
	
	
	public String dropTable()
	{
		return "";
	}
	
	
	public String insertRegister()
	{
		return "";
	}
	
	public String deleteRegister()
	{
		return "";
	}
	
	@SuppressWarnings("rawtypes")
	private String convertType(Class clazz)
	{
		String type= clazz.getName();
		
		if(type.equals("int"))
		{
			System.out.println("Int");
		}
		
		return "";
	}
	
	@Override
	public String toString() 
	{
		String s= "";
		
		for(int i = 0; i < fieldnames.length; i++)
		{
			s = s + fieldtypes[i].toString()
					+ " " + fieldnames[i] 
					+ " = " + fieldvalues[i] 
					+ "\n";
		}
				
		return s.substring(0, (s.length() - 1));
	}
	
	

}
