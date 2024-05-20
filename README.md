# Java-JDBC
### 這是利用Java JDBC 製作的 商品資料庫連結
# 應用技術
### Java、SQL

# 資料庫建立
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/database.jpg" width="1000" height="600">

# 視窗設置
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/JFrame.jpg" width="1000" height="600">
# 資料庫CRUD
## 增

> ProductDaoImpl.java 
```Java
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
```
> WinPage.java
```Java
btn_add = new JButton("新增");
btn_add.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		if(input_name.getText().equals("")||input_price.getText().equals("")||input_intro.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"請輸入內容");
		}else {
			ProductDao dao=new ProductDaoImpl();
			Product p=new Product();
			p.setName(input_name.getText());
			p.setPrice(Integer.parseInt(input_price.getText()));
			p.setIntro(input_intro.getText());

			input_name.setText("");
			input_price.setText("");
			input_intro.setText("");
			dao.add(p);
			getView();
			JOptionPane.showMessageDialog(null,"新增成功");
		}
	}
});
btn_add.setBounds(53, 85, 85, 23);
contentPane.add(btn_add);
```
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/add%20(1).jpg" width="1000" height="600">
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/add%20(2).jpg" width="1000" height="600">
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/add%20(3).jpg" width="1000" height="600">
## 刪
> ProductDaoImpl.java
```Java
public void delete(int id) {
	// TODO Auto-generated method stub
	Connection conn=DBUtils.getDB().getConn();
	String deleteSQL="delete from product where id=?";
	try {
		PreparedStatement ps=conn.prepareStatement(deleteSQL);
		ps.setInt(1, id);
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
}
```
> WinPage.java
```Java
btn_delete = new JButton("刪除");
btn_delete.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		ProductDao dao=new ProductDaoImpl();
		dao.delete(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
		getView();
		JOptionPane.showMessageDialog(null,"刪除成功");
	}
});
btn_delete.setBounds(362, 85, 85, 23);
contentPane.add(btn_delete);
```
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/delete%20(1).jpg" width="1000" height="600">
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/delete%20(2).jpg" width="1000" height="600">
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/delete%20(3).jpg" width="1000" height="600">
## 改
> ProductDaoImpl.java
```Java
public void update(Product p) {
	// TODO Auto-generated method stub
	Connection conn=DBUtils.getDB().getConn();
	String updateSQL="update product set name=?,price=?,intro=? where id=?";
	try {
		PreparedStatement ps=conn.prepareStatement(updateSQL);
		ps.setString(1, p.getName());
		ps.setInt(2, p.getPrice());
		ps.setString(3, p.getIntro());
		ps.setInt(4, p.getId());
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
```
> WinPage.java
```Java
btn_update = new JButton("修改");
btn_update.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		ProductDao dao=new ProductDaoImpl();
		Product p=new Product();
		p.setName(input_name.getText());
		p.setPrice(Integer.parseInt(input_price.getText()));
		p.setIntro(input_intro.getText());
		p.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
		dao.update(p);
		input_name.setText("");
		input_price.setText("");
		input_intro.setText("");
		getView();
		JOptionPane.showMessageDialog(null,"修改成功");
	}
});
btn_update.setBounds(206, 85, 85, 23);
contentPane.add(btn_update);
```
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/update%20(1).jpg" width="1000" height="600">
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/update%20(2).jpg" width="1000" height="600">
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/update%20(3).jpg" width="1000" height="600">
## 查
> ProductDaoImpl.java
```Java
public ResultSet Search(Product p) {
	// TODO Auto-generated method stub
	Connection conn=DBUtils.getDB().getConn();
	String searchSQL="select id as 編號,name as 商品名稱,price as 商品價錢,intro as 商品種類 from product where intro=?";
	ResultSet rs=null;
	try {
		PreparedStatement ps = conn.prepareStatement(searchSQL);
		ps.setString(1, p.getIntro());
		rs=ps.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return rs;
}
```
> WinPage.java
```Java
btn_search = new JButton("搜尋");
btn_search.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		if(input_search.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"請輸入商品種類");
		}else {
			ProductDao dao=new ProductDaoImpl();
			Product p=new Product();
			p.setIntro(input_search.getText());
			ResultSet rs=dao.Search(p);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			input_search.setText("");
			JOptionPane.showMessageDialog(null,"搜尋成功");
		}
	}
});
btn_search.setBounds(681, 85, 85, 23);
contentPane.add(btn_search);
```
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/search%20(1).jpg" width="1000" height="600">
<img src="https://github.com/s1063724/Java-JDBC/blob/main/image/search%20(2).jpg" width="1000" height="600">
