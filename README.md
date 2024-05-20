# Java-JDBC
### 這是利用Java JDBC 製作的 商品資料庫連結
# 應用技術
### Java、SQL

# 資料庫建立
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/database.jpg" width="1000" height="600">

# 視窗設置

# 資料庫CRUD
## 增
'''Java=
public void add(Product p) {
		// TODO Auto-generated method stub
		Connection conn=DBUtils.getDB().getConn();
		String addSQL="insert into product(name,price,intro)values(?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(addSQL);
			ps.setString(1, p.getName());
			ps.setInt(2, p.getPrice());
			ps.setString(3, p.getIntro());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
'''
