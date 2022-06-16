package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.TableColumn;

public class MainFrame {

	JFrame f = new JFrame();; // 定义窗体
	Container c = f.getContentPane();; // 定义主容器

	JTextField text = new JTextField();

	public void init() {

		// 设置窗体的基本信息
		f.setTitle("MainFrame -- custom ui"); // 设置标题
		f.setSize(800, 600); // 设置大小
		f.setLocationRelativeTo(null);// 窗口居中

		// 设置菜单栏
		JMenuBar menubar = new JMenuBar();

		JMenu menu1 = new JMenu("File");
		menu1.setFont(Font.getFont("Consolas"));
		menu1.setMnemonic(KeyEvent.VK_F);
		menu1.add(new JMenuItem("新建(N)", KeyEvent.VK_N));
		menu1.add(new JMenuItem("保存(S)", KeyEvent.VK_S));
		menu1.addSeparator();
		menu1.add(new JMenuItem("退出(Q)", KeyEvent.VK_Q));

		JMenu menu2 = new JMenu("About");
		menu2.setMnemonic(KeyEvent.VK_A);
		menu2.add(new JMenuItem("关于(A)", KeyEvent.VK_A));
		menu2.add(new JCheckBoxMenuItem("单选"));

		menubar.add(menu1);
		menubar.add(menu2);
		f.setJMenuBar(menubar);

		// 设置工具栏
		// 与菜单栏不同的是，工具栏可以放在容器内
		JPanel toolBarPanel = new JPanel();
		JToolBar toolBar = new JToolBar();
		JButton toolBarBtn1 = new JButton("保存");
		toolBar.add(toolBarBtn1);
		toolBarPanel.setLayout(new BorderLayout());
		toolBarPanel.add(toolBar,BorderLayout.WEST);
		
		
		// 设置主要内容
		JPanel p1 = new JPanel(); // 创建一个JPanel对象
		p1.setLayout(new BorderLayout());
		text.setPreferredSize(new Dimension(700, 40));
		text.setText(Integer.toString(f.getWidth()));
		p1.add(text,BorderLayout.BEFORE_FIRST_LINE);

		JTable t = new JTable(10,5);
		for (int i = 0 ;i <5;i++) {
			TableColumn col1 = t.getColumnModel().getColumn(i);
			col1.setPreferredWidth(10);
			col1.setMaxWidth(100);
		}
		
		p1.add(t);
		
		f.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				fun1();
			}

		});

		c.setLayout(new BorderLayout() );
		c.add(toolBarPanel,BorderLayout.NORTH);
		c.add(p1,BorderLayout.CENTER); // 将面板添加到窗口
		f.setVisible(true); // 设置窗口可见

	}

	public void fun1() {
		text.setText(Integer.toString(f.getWidth()));
		text.setPreferredSize(new Dimension(f.getWidth() - 10, 40));
	}

}
