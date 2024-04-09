package com.davidlekei.mbll4l.database;

public class DatabaseConnection
{
	private static Database db;

	public static Database get()
	{
		if(db == null)
		{
			db = new MySQLDatabase();
		}

		return db;
	}
}
