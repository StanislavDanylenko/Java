package ua.nure.danylenko.Practice8;

import ua.nure.danylenko.Practice8.db.entity.Group;
import ua.nure.danylenko.Practice8.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

	private static DBManager instance;

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
		// ...?
	}

	///////////////////////////////////////

    private static final String URL = "jdbc:mysql://127.0.0.1/epam"
                    + "?user=testuser&password=testpass" +
                    "&useUnicode=true" +
                    "&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false" +
                    "&serverTimezone=Europe/Moscow" +
                    "&useSSL=false";
	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
	private static final String SQL_FIND_ALL_GROUPS = "SELECT * FROM grops";
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_FIND_GROUP_BY_NAME = "SELECT * FROM grops WHERE name=?";
	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?)";
    private static final String SQL_INSERT_GROUP = "INSERT INTO grops VALUES (DEFAULT, ?)";
    private static final String SQL_INSERT_USER_GROUP = "INSERT INTO users_groups VALUES (?, ?)";
    private static final String SQL_FIND_USER_GROUP = "SELECT * FROM grops WHERE id IN (SELECT grop_id FROM users_groups WHERE user_id=?)";
    private static final String SQL_DELETE_GROUP_BY_ID = "DELETE FROM grops WHERE id=?";
    private static final String SQL_UPDATE_GROUP_BY_ID = "UPDATE grops SET name=? WHERE id=?";

	public List<User> findAllUsers() throws DBException {
		List<User> users = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_USERS);

			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			// (1) write to log (log4j, logback, slf4j)
			// LOG.error("Cannot find all users", ex);

			// (2)
			throw new DBException("Cannot find all users", ex);
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}

		return users;
	}

    public List<Group> findAllGroups() throws DBException {
        List<Group> groups = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_GROUPS);

            while (rs.next()) {
                groups.add(extractGroup(rs));
            }
        } catch (SQLException ex) {
            // (1) write to log (log4j, logback, slf4j)
            // LOG.error("Cannot find all users", ex);

            // (2)
            throw new DBException("Cannot find all users", ex);
        } finally {
            close(rs);
            close(stmt);
            close(con);
        }

        return groups;
    }

	public boolean setGroupsForUser(User user, Group... groups) throws DBException {
		boolean res = true;
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			for (int i = 0; i < groups.length; i++) {
			    res = insertUserToGroup(user, groups[i]);
            }
			con.commit();
		} catch (SQLException | DBException ex) {
			// (1) write to log

			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					throw new DBException("Cannot do rollback", e);
				}
			}
			throw new DBException("Cannot insert user to groups", ex);
		} finally {
			close(con);
		}
		return res;
	}

    public boolean insertUserToGroup(User user, Group group) throws DBException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT_USER_GROUP, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            ps.setString(k++, ""+user.getId());
            ps.setString(k++, ""+group.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DBException("Cannot add user to group: " + user + " -> " + group, e);
        } finally {
            close(resultSet);
            close(ps);
            close(con);
        }
    }

	public boolean insertUser(User user) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		int resultId = 0;

		try {
			con = getConnection();
			ps = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getLogin());
			ps.executeUpdate();

			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				resultId = resultSet.getInt(1);
				user.setId(resultId);
			}
			return true;
		} catch (SQLException e) {
                throw new DBException("Cannot create a new user: " + user, e);
		} finally {
			close(resultSet);
			close(ps);
			close(con);
		}
	}

    public boolean insertGroup(Group group) throws DBException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        int resultId = 0;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, group.getName());
            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                resultId = resultSet.getInt(1);
                group.setId(resultId);
            }
            return true;
        } catch (SQLException e) {
            throw new DBException("Cannot create a new user: " + group, e);
        } finally {
            close(resultSet);
            close(ps);
            close(con);
        }
    }

	public User getUser(String login) throws DBException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            int k = 1;
            pstmt.setString(k++, login);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException e) {
            throw new DBException("Cannot find such user" + user, e);
        } finally {
            close(resultSet);
            close(ps);
            close(con);
        }
        return user;
	}

    public Group getGroup(String name) throws DBException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Group group = null;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_FIND_GROUP_BY_NAME);
            int k = 1;
            pstmt.setString(k++, name);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                group = extractGroup(rs);
            }
        } catch (SQLException e) {
            throw new DBException("Cannot find such user" + group, e);
        } finally {
            close(resultSet);
            close(ps);
            close(con);
        }
        return group;
    }

    public List<Group> getUserGroups(User user) throws DBException {
        List<Group> groups = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_GROUP);
            int k = 1;
            pstmt.setString(k++, ""+user.getId());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                groups.add(extractGroup(rs));
            }
        } catch (SQLException ex) {
            // (1) write to log (log4j, logback, slf4j)
            // LOG.error("Cannot find all users", ex);

            // (2)
            throw new DBException("Cannot find user groups", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return groups;
    }

    public void deleteGroup(Group group) throws DBException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE_GROUP_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ""+group.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Cannot delete a new group: " + group, e);
        } finally {
            close(resultSet);
            close(ps);
            close(con);
        }
    }

    public void updateGroup(Group group) throws DBException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE_GROUP_BY_ID, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            ps.setString(k++, ""+group.getName());
            ps.setString(k++, ""+group.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Cannot update a new group: " + group, e);
        } finally {
            close(resultSet);
            close(ps);
            close(con);
        }
    }

	// ======================================
	// UTIL METHODS
	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		return user;
	}

    private Group extractGroup(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));
        return group;
    }

	private Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(URL);
		// adjust your connection!
		return con;
	}

	private void close(ResultSet rs) throws DBException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				// (1)
				// write to log!!!

				// (2)
				// throw your own exception
				throw new DBException("Cannot close a resource", ex);
			}
		}
	}

	private void close(Statement stmt) throws DBException {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				// (1)
				// write to log!!!

				// (2)
				// throw your own exception
				throw new DBException("Cannot close a resource", ex);
			}
		}
	}

	private void close(Connection con) throws DBException {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				// (1)
				// write to log!!!

				// (2)
				// throw your own exception
				throw new DBException("Cannot close a resource", ex);
			}
		}
	}

}
