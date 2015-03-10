import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * <p>Title: Login.java<��p>
 * <p>Description: <��p>
 * <p>Copyright: Copyright (c) 2013<��p>
 * <p>Company: LZUXXXY<��p>
 * @author XIAOQI
 * @date 2013-6-25
 * @version 1.0
 */

/**
 * @author xiaoqi
 * ����Ĺ���ʹ����eclipse�е� ���ӻ���Swing Designer���
 * <p>ѡ����Absolute���ַ�ʽ  λ�ô�С���ǿ����Լ����� �Լ��ڷ�
 * <p>���ܼܺ򵥾������˵�¼���� ���������Լ����ǳƣ��Է�ip���˿ںŵ���Ϣ
 * <p>���ù��캯���Ѳ������룬Ȼ���½�ooPanel���󣬽���ʵ������
 */
public class ooLogin extends JFrame  implements ActionListener{

	private JPanel contentPane;
	private JTextField name;
	private JTextField ipt;
	private JTextField textField;
	private JTextField txtip;
	JButton pic ;
	JButton login;
	int i=1;
	private JTextField port;
	private JTextField inport;
	private JTextField outport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ooLogin frame = new ooLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * ������һ����¼��� ��ʹ����������Ϣ
	 */
	public ooLogin(){
		setForeground(Color.GRAY);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 349, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pic = new JButton(new ImageIcon("images/headpicb/1.png"));
		pic.setBounds(10, 68, 98, 90);
	    pic.setBorder(null);
	    pic.setContentAreaFilled(false);
	    pic.addActionListener(this);
		contentPane.add(pic);

		login=new JButton(new ImageIcon("images/button/login.png"));
		login.setBorder(null);
		login.setContentAreaFilled(false);
		login.setFont(new Font("������κ", Font.PLAIN, 15));
		login.setBounds(63, 168, 212, 34);
		login.addActionListener(this);
		contentPane.add(login);
		
		name = new JTextField();
		name.setBounds(180, 68, 142, 28);
		contentPane.add(name);
		name.setColumns(10);
		
		ipt = new JTextField();
		ipt.setBounds(180, 106, 142, 28);
		contentPane.add(ipt);
		ipt.setColumns(10);
		
		textField = new JTextField();
		textField.setText("\u6635\u79F0");
		textField.setFont(new Font("������κ", Font.PLAIN, 15));
		textField.setEditable(false);
		textField.setBounds(124, 68, 46, 28);
		textField.setBorder(null);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtip = new JTextField();
		txtip.setFont(new Font("������κ", Font.PLAIN, 15));
		txtip.setText("\u5BF9\u65B9ip");
		txtip.setEditable(false);
		txtip.setBounds(118, 106, 52, 28);
		txtip.setBorder(null);
		contentPane.add(txtip);
		txtip.setColumns(10);
		
		port = new JTextField();
		port.setFont(new Font("������κ", Font.PLAIN, 15));
		port.setText("\u7AEF\u53E3\u53F7");
		port.setEditable(false);
		port.setBounds(118, 144, 52, 21);
		port.setBorder(null);
		contentPane.add(port);
		port.setColumns(10);
		
		inport = new JTextField();
		inport.setToolTipText("inport");
		inport.setBounds(180, 144, 66, 21);
		contentPane.add(inport);
		inport.setColumns(10);
		
		outport = new JTextField();
		outport.setToolTipText("outport");
		outport.setBounds(256, 143, 66, 21);
		contentPane.add(outport);
		outport.setColumns(10);
	}
	public void actionPerformed(ActionEvent e) {
		//�����Զ����Լ���ͷ�� ֻ����4��ͼ ʹ���߿����Լ����
		if(e.getSource()==pic){
			i++;
			if(i==5)
				i=1;
			pic.setIcon(new ImageIcon("images/headpicb/"+i+".png"));
		}
		//ͨ�����������췽��������������
		else if(e.getSource()==login){
			new ooPanel(i,ipt.getText(),name.getText(),inport.getText(),outport.getText());
			this.dispose();
		}
	}
}
