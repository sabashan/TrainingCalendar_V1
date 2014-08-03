/**
* Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*/


package org.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.training.model.Groups;
import org.training.util.DBConnection;

public class GroupDAO {

	/* To find all groups */
	public List<Groups> findAllGroups() {
		List<Groups> list = new ArrayList<Groups>();
		Connection c = null;
		String sql = "SELECT * FROM groups";
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

	/* To find group by groupId */
	public Groups findByGroupIdGroups(int groupId) {

		String sql = "SELECT id_group, group_name FROM groups WHERE id_group=?";
		Groups group = null;
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, groupId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				group = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return group;
	}

	/* To find group by group name */
	public Groups findByGroupGroups(String gro) {

		String sql = "SELECT id_group, group_name FROM groups WHERE group_name=?";
		Groups group = null;
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, gro);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				group = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		// return topic;
		return group;
	}

	/* To create group */
	public Groups createGroups(Groups group) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DBConnection.getConnection();
			ps = c.prepareStatement(
					"INSERT INTO groups (id_group,group_name) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, group.getGroupId());
			ps.setString(2, group.getGroup());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return group;
	}

	/* To update group */
	public Groups updateGroups(Groups group) {
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE groups SET group_name=? WHERE id_group=?");
			ps.setString(1, group.getGroup());
			ps.setInt(2, group.getGroupId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return group;
	}

	/* To remove group */
	public boolean removeGroups(int groupId) {
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c
					.prepareStatement("DELETE FROM groups WHERE id_group=?");
			ps.setInt(1, groupId);
			int count = ps.executeUpdate();
			return count == 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
	}

	/* To list out single group */
	protected Groups processRow(ResultSet rs) throws SQLException {
		Groups group = new Groups();
		group.setGroupId(rs.getInt("id_group"));
		group.setGroup(rs.getString("group_name"));
		return group;
	}

	/* To list out list of groups */
	protected Groups processSummaryRow(ResultSet rs) throws SQLException {
		Groups group = new Groups();
		group.setGroupId(rs.getInt("id_group"));
		group.setGroup(rs.getString("group_name"));
		return group;
	}
}
