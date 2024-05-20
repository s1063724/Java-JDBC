package tw.com.project.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxCrud.Column;

import net.proteanit.sql.DbUtils;
import tw.com.project.dao.ProductDao;
import tw.com.project.domain.Product;
import tw.com.project.impl.ProductDaoImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class WinPage extends JFrame {

	private JPanel contentPane;
	private JTextField input_name;
	private JTextField input_price;
	private JTextField input_intro;
	private JButton btn_add;
	private JButton btn_update;
	private JButton btn_delete;
	private JButton btn_search;
	private JTable table;
	DefaultTableModel model;
	private JTextField input_search;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinPage frame = new WinPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WinPage() {
		setTitle("Product");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品名稱:");
		lblNewLabel.setBounds(53, 40, 66, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("商品價錢:");
		lblNewLabel_1.setBounds(293, 40, 66, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("商品種類:");
		lblNewLabel_2.setBounds(540, 43, 66, 22);
		contentPane.add(lblNewLabel_2);
		
		input_name = new JTextField();
		input_name.setBounds(112, 40, 96, 22);
		contentPane.add(input_name);
		input_name.setColumns(10);
		
		input_price = new JTextField();
		input_price.setBounds(351, 37, 96, 22);
		contentPane.add(input_price);
		input_price.setColumns(10);
		
		input_intro = new JTextField();
		input_intro.setBounds(595, 40, 184, 21);
		contentPane.add(input_intro);
		input_intro.setColumns(10);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 142, 726, 265);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model=new DefaultTableModel();
		String[] column= {"編號","商品名稱","商品價錢","商品種類"};
		String[] row=new String[4];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		input_search = new JTextField();
		input_search.setBounds(562, 87, 96, 21);
		contentPane.add(input_search);
		input_search.setColumns(10);
		
		getView();
	}
	public void getView() {
		
		ProductDao dao=new ProductDaoImpl();
		ResultSet rs=dao.columnName();
		table.setModel(DbUtils.resultSetToTableModel(rs));
	}
}
