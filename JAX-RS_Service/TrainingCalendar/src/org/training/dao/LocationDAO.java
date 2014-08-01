package org.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.training.model.Location;
import org.training.util.DBConnection;

public class LocationDAO {

	/* To find all locations */
	public List<Location> findAllLocation() {
		List<Location> list = new ArrayList<Location>();
		Connection c = null;
		String sql = "SELECT * FROM location";
		try {
			c = DBConnection.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(processSummaryRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return list;
	}

	/* To find location by locationId */
	public Location findByLocationIdLocation(int locationId) {
		String sql = "SELECT id_location, location FROM location WHERE id_location=?";
		Location location = null;
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, locationId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				location = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return location;
	}

	/* To find location by location */
	public Location findByLocationLocation(String loca) {

		String sql = "SELECT id_location, location FROM location WHERE location=?";
		Location location = null;
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, loca);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				location = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return location;
	}

	/* To create location */
	public Location createLocation(Location location) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DBConnection.getConnection();
			ps = c.prepareStatement(
					"INSERT INTO location (id_location, location) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, location.getLocationId());
			ps.setString(2, location.getLocation());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return location;
	}

	/* To update location */
	public Location updateLocation(Location location) {
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE location SET location=? WHERE id_location=?");
			ps.setString(1, location.getLocation());
			ps.setInt(2, location.getLocationId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return location;
	}

	/* To remove location */
	public boolean removeLocation(int locationId) {
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c
					.prepareStatement("DELETE FROM location WHERE id_location=?");
			ps.setInt(1, locationId);
			int count = ps.executeUpdate();
			return count == 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
	}

	/* To list out single location */
	protected Location processRow(ResultSet rs) throws SQLException {
		Location location = new Location();
		location.setLocationId(rs.getInt("id_location"));
		location.setLocation(rs.getString("location"));
		return location;
	}

	/* To list out list of locations */
	protected Location processSummaryRow(ResultSet rs) throws SQLException {
		Location location = new Location();
		location.setLocationId(rs.getInt("id_location"));
		location.setLocation(rs.getString("location"));
		return location;
	}
}