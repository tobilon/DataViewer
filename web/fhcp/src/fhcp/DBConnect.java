/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  数据库连接常用操作的函数
 */


package fhcp;

import java.sql.*;

public class DBConnect {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement prepstmt = null;
	private DBConnectionManager dcm=null;

    void init() {
        dcm = DBConnectionManager.getInstance();
		conn = dcm.getConnection("oracle");
    }

	/**
	 * 构造数据库的连接和访问类
	 */
	public DBConnect() throws Exception {
        init();
		stmt = conn.createStatement();
	}

    public DBConnect(int resultSetType, int resultSetConcurrency)
            throws Exception {
        init();
        stmt = conn.createStatement(resultSetType, resultSetConcurrency);
    }

    /**
     * 构造数据库的连接和访问类
     * 预编译SQL语句
     * @param sql SQL语句
     */
	public DBConnect(String sql) throws Exception {
        init();
		this.prepareStatement(sql);
	}

	public DBConnect(String sql, int resultSetType, int resultSetConcurrency)
            throws Exception {
        init();
		this.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * 返回连接
	 * @return Connection 连接
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * PreparedStatement
	 * @return sql 预设SQL语句
	 */
	public void prepareStatement(String sql) throws SQLException {
		prepstmt = conn.prepareStatement(sql);
	}

	public void prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
		prepstmt = conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * 设置对应值
     *
	 * @param index 参数索引
	 * @param value 对应值
	 */
	public void setString(int index,String value) throws SQLException {
		prepstmt.setString(index, value);
	}
	public void setInt(int index,int value) throws SQLException {
		prepstmt.setInt(index,value);
	}
	public void setBoolean(int index,boolean value) throws SQLException {
		prepstmt.setBoolean(index,value);
	}
	public void setDate(int index,Date value) throws SQLException {
		prepstmt.setDate(index,value);
	}
	public void setLong(int index,long value) throws SQLException {
		prepstmt.setLong(index,value);
	}
	public void setFloat(int index,float value) throws SQLException {
		prepstmt.setFloat(index,value);
	}
	public void setBytes(int index,byte[] value) throws SQLException{
		prepstmt.setBytes(index,value);
	}



    public void clearParameters()
        throws SQLException
    {
        prepstmt.clearParameters();
	prepstmt=null;
    }
	/**
	 * 返回预设状态
	 */
	public PreparedStatement getPreparedStatement() {
		return prepstmt;
	}
	/**
	 * 返回状态
	 * @return Statement 状态
	 */
	public Statement getStatement() {
		return stmt;
	}
	/**
	 * 执行SQL语句返回字段集
	 * @param sql SQL语句
	 * @return ResultSet 字段集
	 */
	public ResultSet executeQuery(String sql) throws SQLException {
		if (stmt != null) {
			return stmt.executeQuery(sql);
		}
		else return null;
	}

	public ResultSet executeQuery() throws SQLException {
		if (prepstmt != null) {
			return prepstmt.executeQuery();
		}
		else return null;
	}
	
	public void execute(String sql) throws SQLException {
		if (stmt != null) {
			stmt.execute(sql);
		}
	}

	public void execute() throws SQLException {
		if (prepstmt != null) {
			prepstmt.execute();
		}
	}

	/**
	 * 执行SQL语句
	 * @param sql SQL语句
	 */
	public void executeUpdate(String sql) throws SQLException {
		if (stmt != null)
			stmt.executeUpdate(sql);
	}
	public void executeUpdate() throws SQLException {
		if (prepstmt != null)
			prepstmt.executeUpdate();
	}
	/**
	 * 关闭连接
	 */
	public void close() throws Exception {
		if (stmt != null)  {
			stmt.close();
			stmt = null;
		}
		if (prepstmt != null) {
			prepstmt.close();
			prepstmt = null;
		}
		if (conn!=null)
		{

			dcm.freeConnection("oracle",conn);

		}

	}
}
