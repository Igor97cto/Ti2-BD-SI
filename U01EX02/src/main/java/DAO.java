import java.sql.*;

public class DAO
{
	private Connection conn;

	
	public DAO()
	{
		conn = null;
		connect();
		create();
	}

	
	public boolean connect()
	{
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "teste";
		int port = 5432;
		String url = "jdbc:postgresql://" 
					+ serverName + ":"
					+ port + "/" 
					+ mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try
		{
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			status = (conn == null);
			System.out.println("Conexão efetuada com o postgres!");
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("Conexão NÃO efetuada com o postgres"
					+ " -- Driver não encontrado -- "
					+ e.getMessage());
		}
		catch (SQLException e)
		{
			System.err.println("Conexão NÃO efetuada com o postgres -- "
					+ e.getMessage());
		}

		return status;
	}
	
	
	public boolean close()
	{
		boolean status = false;

		try
		{
			conn.close();
			status = true;
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	
	public boolean create()
	{
		boolean status = false;
		
		String query= "CREATE TABLE IF NOT EXISTS \"User\"("
					+ "id integer NOT NULL, "
				    + "name text, "
				    + "lastname text, "
				    + "email text NOT NULL, "
				    + "password integer NOT NULL, "
				    + "gender character(1), "
				    + "birthday date"
				    + ")";
		
		try
		{		
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			
		}catch (SQLException u)
		{
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	
	public boolean delete(int id)
	{
		boolean status= false;
		String query= "DELETE FROM \"public\".\"User\""
					+ "WHERE id = " + id;
		
		try
		{
			Statement st= conn.createStatement();
			st.executeUpdate(query);
			st.close();
			status= true;
		} catch (SQLException u)
		{
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	public boolean drop()
	{
		boolean status= false;
		String query= "DROP TABLE IF EXISTS \"public\".\"User\""
						+ "CASCADE";
		
		try
		{
			Statement st= conn.createStatement();
			st.execute(query);
			st.close();
			status= true;
		} catch (SQLException u)
		{
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	public boolean insert(User usr)
	{
		int id= 0;
		boolean status = false;
		String query0;
		String query1;
		
		try
		{	
			query0= "SELECT * FROM \"public\".\"User\"";
			
			Statement st= conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs= st.executeQuery(query0);
			rs.last();
			
			if(rs.getRow() != 0)
			id= rs.getInt("id");
			
			id++;
			
			query1= "INSERT INTO \"public\".\"User\" (id, name, lastname,"
					+ "email, password, gender, birthday)"
					+ "VALUES ("
					+ id + ", "
					+ "'" + usr.getName() + "', "
					+ "'" + usr.getLastname() + "', "
					+ "'" + usr.getEmail() + "', "
					+ "'" + usr.getPassword() + "', "
					+ "'" + usr.getGender() + "', "
					+ "?"  + ");" ;
			
			PreparedStatement pst = conn.prepareStatement(query1);
			pst.setObject(1, usr.getLdt());
			pst.executeUpdate();
			pst.close();
			status = true;
		} catch (SQLException u)
		{
			throw new RuntimeException(u);
		}
		
		return status;
	}
	
	
	public User read(int id)
	{
		User usr= null;
		String query= "SELECT * FROM \"public\".\"User\""
					+ "WHERE id = " + id + ";";
		
		try
		{
			PreparedStatement pst= conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = pst.executeQuery();
			
			rs.next();
			
			usr= new User(rs.getInt("id"), rs.getString("name"),
					rs.getString("lastname"), rs.getString("email"),
					rs.getInt("password"), rs.getString("gender").charAt(0),
					rs.getDate("birthday").toLocalDate());
			
			pst.close();			
		} catch (SQLException u)
		{
			throw new RuntimeException(u);
		}
		
		return usr;
	}
	
	
	public boolean update(User usr)
	{
		boolean status= false;
		
		String query= "UPDATE \"public\".\"User\" SET \n"
					+ "id= "  + usr.getId() + ", \n"
					+ "name= '" + usr.getName() + "', \n"
					+ "lastname= '" + usr.getLastname() + "', \n"
					+ "email= '" + usr.getEmail() + "', \n"
					+ "password= '" + usr.getPassword() + "', \n"
					+ "gender= '" + usr.getGender() + "', \n"
					+ "birthday= ? \n" 
					+ "WHERE id= " + usr.getId() + "; \n" ;
		
		if(usr != null)
		{
			try
			{
				PreparedStatement pst= conn.prepareStatement(query);
				pst.setObject(1, usr.getLdt());
				pst.executeUpdate();
				pst.close();
				status= true;
			} catch (SQLException u)
			{
				throw new RuntimeException(u);
			}
		}
		
		return status;
	}
	
	
	public User[] list()
	{
		User[] usr = null;
		String query= "SELECT * FROM \"public\".\"User\""; 
		
		try
		{
			PreparedStatement pst= conn.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = pst.executeQuery();

			if(rs.next())
			{
				rs.last();
				usr = new User[rs.getRow()];
				rs.beforeFirst();

				for(int i = 0; rs.next(); i++)
				{
					usr[i]= new User(rs.getInt("id"), rs.getString("name"),
							rs.getString("lastname"), rs.getString("email"),
							rs.getInt("password"), 
							rs.getString("gender").charAt(0),
							rs.getDate("birthday").toLocalDate());
				}
			}
			
			pst.close();
		} catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
		
		return usr;
	}
}