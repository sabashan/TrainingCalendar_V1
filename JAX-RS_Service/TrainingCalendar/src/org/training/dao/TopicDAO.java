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

import org.training.model.Topic;
import org.training.util.DBConnection;

public class TopicDAO {

	/* To find all topics */
	public List<Topic> findAllTopic() {
		List<Topic> list = new ArrayList<Topic>();
		Connection c = null;
		String sql = "SELECT * FROM topic";
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

	/* To find topic by topicId */
	public Topic findByTopicIdTopic(int topicId) {

		String sql = "SELECT id_topic, topic, trainer FROM topic WHERE id_topic=?";
		Topic topic = null;
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, topicId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				topic = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return topic;
	}

	/* To find topic by topic */
	public Topic findByTopicTopic(String topi) {

		String sql = "SELECT id_topic, topic, trainer FROM topic WHERE topic=?";
		Topic topic = null;
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, topi);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				topic = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return topic;
	}

	/* To find topic by trainer */
	public Topic findByTrainerTopic(String trainer) {

		String sql = "SELECT id_topic, topic, trainer FROM topic WHERE trainer=?";
		Topic topic = null;
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, trainer);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				topic = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return topic;
	}

	/* To create topic */
	public Topic createTopic(Topic topic) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DBConnection.getConnection();
			ps = c.prepareStatement(
					"INSERT INTO topic (id_topic, topic, trainer) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, topic.getTopicId());
			ps.setString(2, topic.getTopic());
			ps.setString(3, topic.getTrainer());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {// return topic;
			DBConnection.close(c);
		}
		return topic;
	}

	/* To update topic */
	public Topic updateTopic(Topic topic) {
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE topic SET topic=?, trainer=? WHERE id_topic=?");
			ps.setString(1, topic.getTopic());
			ps.setString(2, topic.getTrainer());
			ps.setInt(3, topic.getTopicId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
		return topic;
	}

	/* To remove topic */
	public boolean removeTopic(int topicId) {
		Connection c = null;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c
					.prepareStatement("DELETE FROM topic WHERE id_topic=?");
			ps.setInt(1, topicId);
			int count = ps.executeUpdate();
			return count == 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(c);
		}
	}

	/* To list out single topic */
	protected Topic processRow(ResultSet rs) throws SQLException {
		Topic topic = new Topic();
		topic.setTopicId(rs.getInt("id_topic"));
		topic.setTopic(rs.getString("topic"));
		topic.setTrainer(rs.getString("trainer"));
		return topic;
	}

	/* To list out list of topics */
	protected Topic processSummaryRow(ResultSet rs) throws SQLException {
		Topic topic = new Topic();
		topic.setTopicId(rs.getInt("id_topic"));
		topic.setTopic(rs.getString("topic"));
		topic.setTrainer(rs.getString("trainer"));
		return topic;
	}
}
