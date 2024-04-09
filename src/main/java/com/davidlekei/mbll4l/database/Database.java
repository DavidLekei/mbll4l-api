package com.davidlekei.mbll4l.database;

import com.davidlekei.mbll4l.Lawyer;

import java.sql.SQLException;
import java.util.List;

public interface Database {

	public List<Lawyer> getLawyers() throws SQLException;

	public Lawyer getLawyer(int id) throws SQLException;

}
