package libs;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.Types;
import java.time.LocalDate;
import java.util.HashMap;


public class SQLCV 
{	
	private Field[] fields;							//Obj reference
	private String[] fieldnames;					//Obj reference
	private String[] fieldtypes;					//Obj reference
	private Object[] fieldvalues;					//Obj reference
	private HashMap<Integer, String> idsqltype;		//Data reference
	private HashMap<String, Integer> javarelid;		//Data reference
	
	
	public SQLCV(Object obj) throws IllegalArgumentException, IllegalAccessException
	{	
		fields= obj.getClass().getDeclaredFields();
		fieldnames= new String[fields.length];
		fieldtypes= new String[fields.length];
		fieldvalues= new String[fields.length];
		
		AccessibleObject.setAccessible(fields, true);
		
		for (int i = 0; i< fields.length; i++)
		{
			fieldnames[i] = fields[i].getName();
			fieldtypes[i] = fields[i].getType().getSimpleName();
			fieldvalues[i] = fields[i].get(obj);
		}
		
		idsqltype= setIdSqlType();
		javarelid= setJavaRelId();
	}
	
	
	private HashMap<Integer, String> setIdSqlType() throws IllegalArgumentException, IllegalAccessException
	{
		Field[] sqltypefields= Types.class.getFields();
		HashMap<Integer, String> result= new HashMap<>();
		
		for (Field field : sqltypefields)
		{
	        result.put((Integer)field.get(null), field.getName());
	    }
		
		return result;
	}
	
	
	private HashMap<String, Integer> setJavaRelId()
	{
		HashMap<String, Integer> result= new HashMap<>();
			
		@SuppressWarnings("rawtypes")
		Class[] javatypes= 	{
								double.class,		//id= 8
								float.class,		//id= 7
								int.class,			//id= 4								
								String.class,		//id= 12
								LocalDate.class,	//id= 91
							};
		
		result.put(javatypes[0].getSimpleName(), 8);
		result.put(javatypes[1].getSimpleName(), 7);
		result.put(javatypes[2].getSimpleName(), 4);
		result.put(javatypes[3].getSimpleName(), 12);
		result.put(javatypes[4].getSimpleName(), 91);
		
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
	
	
	public String toSqlType(String javatype)
	{
		return idsqltype.get(javarelid.get(javatype));
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
