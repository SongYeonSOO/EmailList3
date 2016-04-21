package com.estsoftemailList.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.estsoft.DB.DBConnection;
import com.estsoft.DB.MySQLWebDBConnection;
import com.estsoftemailList.vo.EmailListVo;
@Repository
public class EmailListDao {
	private DBConnection dbConnection;

	// 기본생성자로 container가 생성을 하기 때문에 만들어줘야함
	public EmailListDao() {
		this.dbConnection =  new MySQLWebDBConnection();
	}

	
	// 이거하고싶으면 @autowired annotation이 아니라 <bean></bean> ~~~~해줘야하는데 지금은 하지마ㅡㅡ;
	public EmailListDao(DBConnection mySQLWebDBConnection) {
		this.dbConnection = mySQLWebDBConnection;
	}

	public void insert(EmailListVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();
			String sql = "INSERT INTO emaillist VALUES(null,? ,?,?)";

			//System.out.println(vo.getFirstName());
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  vo.getFirstName());
			pstmt.setString(2,  vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			System.out.println("Error" + ex);
			ex.printStackTrace();
		}
	}

	public List<EmailListVo> getList() {
		List<EmailListVo> list = new ArrayList<EmailListVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT no, first_name, last_name, email From emaillist";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);

				EmailListVo vo = new EmailListVo();
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);

				list.add(vo);
			}

		} catch (SQLException ex) {
			System.out.println("error:" + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return list;

	}

}
