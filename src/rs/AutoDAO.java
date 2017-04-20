package rs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.util.ArrayList;

public class AutoDAO {

	// DEFINICIJA KONEKCIONIH STRINGOVA
	private static String INSERTAUTO = "INSERT INTO auto (marka, model, godiste, kubikaza, boja, cena) VALUES (?, ?, ?, ?, ?, ?)";
	private static String DELETEAUTO = "DELETE FROM auto WHERE marka=?";
	private static String UPDATEAUTO = "UPDATE auto SET marka=? WHERE auto_id=?";
	private static String GETAUTOBYID = "SELECT * FROM auto WHERE auto_id=?";
	private static String GETAUTOLIST10 = "SELECT * FROM auto ORDER BY auto_id LIMIT 10";

	private DataSource ds;
	private InitialContext cxt;

	// DEFINICIJA KONSTRUKTORA ZA PODESAVANJE KONEKCIJE
	
	public AutoDAO() {
		try {
			if (cxt == null) {
				cxt = new InitialContext();
			}
			if (ds == null) {
				ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/mysql");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// DEFINICIJA METODE KOJA VRACA LISTU ZADNJIH 10 AUTOMOBILA IZ BAZE
	
	public ArrayList<Auto> getAutoList10() {
		Auto auto = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Auto> lo = new ArrayList<Auto>();

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETAUTOLIST10);

			pstm.execute();

			rs = pstm.getResultSet();

			while (rs.next()) {
				auto = new Auto();
				auto.setAuto_id(rs.getInt("auto_id"));
				auto.setMarka(rs.getString("marka"));
				auto.setModel(rs.getString("model"));
				auto.setGodiste(rs.getInt("godiste"));
				auto.setKubikaza(rs.getFloat("kubikaza"));
				auto.setBoja(rs.getString("boja"));
				auto.setCena(rs.getFloat("cena"));

				lo.add(auto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lo;
	}

	// DEFINICIJA METODE ZA PREUZIMANJE AUTA PO ID-u
	
	public Auto getAutoById(int auto_id) {
		Auto auto = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETAUTOBYID);
			pstm.setInt(1, auto_id);

			pstm.execute();

			rs = pstm.getResultSet();

			if (rs.next()) {
				auto = new Auto();
				auto.setAuto_id(rs.getInt("auto_id"));
				auto.setMarka(rs.getString("marka"));
				auto.setModel(rs.getString("model"));
				auto.setGodiste(rs.getInt("godiste"));
				auto.setKubikaza(rs.getFloat("kubikaza"));
				auto.setBoja(rs.getString("boja"));
				auto.setCena(rs.getFloat("cena"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return auto;
	}

	// DEFINICIJA METODE ZA UNOS AUTOMOBILA U BAZU
	
	public void insertAuto(Auto auto) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTAUTO);

			pstm.setString(1, auto.getMarka());
			pstm.setString(2, auto.getModel());
			pstm.setInt(3, auto.getGodiste());
			pstm.setFloat(4, auto.getKubikaza());
			pstm.setString(5, auto.getBoja());
			pstm.setFloat(6, auto.getCena());

			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DEFINICIJA METODE ZA IZMENU MARKE AUTA IZ BAZE NA OSNOVU ID-a
	
	public void updateAuto(Auto auto) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATEAUTO);
			
			pstm.setString(1, auto.getMarka());
			pstm.setInt(2, auto.getAuto_id());

			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DEFINICIJA METODE ZA BRISANJE SVIH AUTOMOBILA IZ BAZE SA PROSLEĐENOM MARKOM
	
	public void deleteAuto(Auto auto) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETEAUTO);

			pstm.setString(1, auto.getMarka());

			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}