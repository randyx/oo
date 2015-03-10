/**
 * @date 2012
 * @author XIAOQI
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
/**
 * 主类聊天面板 
 */
class ooPanel extends JFrame implements Runnable,ActionListener
{
	int x,y,i=0,j=1,k,inPort,outPort;
	/**
	 * edge1 安放字体，振动等4个图标
	 */
	JPanel edge1=new JPanel(new FlowLayout(0)); 
	/**
	 * p 安放edge1，输入面板和belowJ
	 */
	JPanel p=new JPanel(new BorderLayout());
	/**
	 * mainJ 安放p和显示信息的面板
	 */
	JPanel mainJ=new JPanel(new BorderLayout());
	/**
	 * topJ 上部的一条显示双方的头像和昵称
	 */
	JPanel topJ=new JPanel(new FlowLayout(0));
	/**
	 * rightJ 右方显示双方的oo秀形象
	 */
	JPanel rightJ=new JPanel(new GridLayout(2,1));
	/**
	 * belowJ 下方安放发送和取消按钮  
	 */
	JPanel belowJ=new JPanel(new FlowLayout(2));
	/**
	 * outMessage  显示消息的面板
	 */
	public JTextPane outMessage=new JTextPane();
	/**
	 * inMessage 输入消息的面板
	 */
	public JTextPane inMessage=new JTextPane();
	/**
	 * 样式化文档
	 */
	private StyledDocument doc = null;
	/**
	 * 自己的昵称
	 */
	JTextField myname=new JTextField("");
	/**
	 * 对方的昵称，先设为等待接入
	 */
	JTextField name=new JTextField("等待接入");
	/**
	 * 一直文本 在自己昵称和对方昵称中间放置
	 */
	JTextField chat=new JTextField("  CHAT  WITH  ");
	/**
	 * 设置字体的按钮 加上图标
	 */
	JButton font = new JButton(new ImageIcon("images/button/font.png"));	
	/**
	 * 发送表情的按钮
	 */
	JButton gif = new JButton(new ImageIcon("images/button/gif.png"));
	/**
	 *发送图片的按钮
	 */
	JButton tup = new JButton(new ImageIcon("images/button/tup.png"));
	/**
	 * 发送振动的按钮
	 */
	JButton zhd = new JButton(new ImageIcon("images/button/chen.png"));
	/**
	 * 自己的头像按钮 设置了默认图标
	 */
	JButton myimghead=new JButton(new ImageIcon("images/headpic/4.png"));
	JButton imghead=new JButton(new ImageIcon("images/headpic/00.png"));
	/**
	 * 自己oo秀的按钮 设置了默认图标
	 */
	JButton imgxiu=new JButton(new ImageIcon("images/qxiu/2.png"));
	JButton myimgxiu=new JButton(new ImageIcon("images/qxiu/1.png"));
	/**
	 * 发送按钮
	 */
	JButton send=new JButton(new ImageIcon("images/button/send.png"));
	JButton exit=new JButton(new ImageIcon("images/button/exit.png"));
	/**
	 * 自己设置的一个比较舒服的背景颜色
	 */
	final Color back=new Color(203,220,197);
	/**
	 * 自己构建的FontAttrib类的对象
	 */
	FontAttrib att = new FontAttrib();
	/**
	 * 字符串ip 存储ip值
	 */
	String ip;
	/**
	 * 将登录框中所输信息，图片信息，昵称，ip地址等等通过
	 * 带参数的构造方法ooPanel传入面板中，来设置一些属性
	 * 参数：
	 * @param m  数字表示现在是那一张头像
	 * @param ip1  ip信息
	 * @param name1    昵称信息
	 * @param inport  接收端口信息
	 * @param outport 发送端口信息
	 */
ooPanel(int m,String ip1,String name1,String inport,String outport)
{
	super();
	ip=ip1;
	k=m;
	myname.setText(name1);
	myimghead.setIcon(new ImageIcon("images/headpic/"+m+".png"));
	inPort=Integer.parseInt(inport);
	outPort=Integer.parseInt(outport);
	Container cp = getContentPane();
	//使cp操纵布局是从右到左的
	cp.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	//设置文本不能被编辑
    inMessage.setEditable(false);
    inMessage.setSize(360, 500);
    //获取与编辑器关联的模型给doc
    doc = inMessage.getStyledDocument();
    //设置outMessage的首选大小     Dimension类封装单个对象中组件的宽度和高度
    //outMessage.setPreferredSize(new Dimension(60,80));
    outMessage.addKeyListener(new EnterKey());//设置enter键监听器
    setSize(550,500);
    setVisible(true);
    /**
     * 加含有图标的按钮
     */
    font.setBorder(null);
    font.setContentAreaFilled(false);
    font.addActionListener(this);
    gif.setBorder(null);
    gif.setContentAreaFilled(false);
    gif.addActionListener(this);
    tup.setBorder(null);
    tup.addActionListener(this);
    tup.setContentAreaFilled(false);
    zhd.setBorder(null);
    zhd.addActionListener(this);
    zhd.setContentAreaFilled(false);
    imghead.setBorder(null);
    imghead.setContentAreaFilled(false);
    myimghead.setBorder(null);
    imgxiu.setBorder(null);
    imgxiu.setContentAreaFilled(false);
    myimgxiu.setBorder(null);
    myimgxiu.setContentAreaFilled(false);
    myimgxiu.addActionListener(this);
    send.setBorder(null);
    send.setContentAreaFilled(false);
    send.addActionListener(this);
    exit.setBorder(null);
    exit.setContentAreaFilled(false);
    edge1.add(font);
    edge1.add(gif);
    edge1.add(tup);
    edge1.add(zhd);
    edge1.setBorder(null);
    edge1.setBackground(back);
    name.setBorder(null);
    name.setBackground(back);
    name.setEditable(false);
    myname.setEditable(false);
    myname.setBorder(null);
    myname.setBackground(back);
    chat.setEditable(false);
    chat.setBorder(null);
    chat.setBackground(back);
    topJ.add(myimghead);
    topJ.add(myname);
    topJ.add(chat);
    topJ.add(imghead);
    topJ.add(name);
    topJ.setSize(550,80);
    topJ.setBackground(back);
    topJ.setBorder(null);
    rightJ.setSize(100, 420);
    rightJ.setBackground(back);
    rightJ.add(imgxiu);
    rightJ.add(myimgxiu);
    belowJ.add(exit);
    belowJ.add(send);
    belowJ.setBackground(back);
    p.add(edge1,BorderLayout.NORTH);
    p.add(new JScrollPane(outMessage),BorderLayout.CENTER);
    p.add(belowJ,BorderLayout.SOUTH);
    mainJ.add(new JScrollPane(inMessage),BorderLayout.CENTER);
    mainJ.add(p,BorderLayout.SOUTH);
    /**
     * 整个的总布局，使用GridBagLayout方式，主要是排布topJ，rightJ和mainJ这三个组件
     * 使得这个布局可以拉升 压缩都没有问题 都很和谐
     */
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    //给cp
    cp.setLayout(gridbag);
    c.gridwidth=0;
    c.fill=GridBagConstraints.BOTH;
    c.weightx =1;
    c.weighty=0;
    gridbag.setConstraints(topJ, c);
    cp.add(topJ);
    c.gridwidth=1;
    c.gridheight=0;
    c.weightx = 0;
    gridbag.setConstraints(rightJ,c);
    cp.add(rightJ);
    c.gridwidth=1;
    c.gridheight=1;
    c.weightx=1;
    c.weighty=1;
    c.fill=GridBagConstraints.BOTH;
    gridbag.setConstraints(mainJ,c);
    cp.add(mainJ);
    /**
     * 给att默认字体信息值
     */
	att.setName("宋体");
	att.setSize(20);
	/**
	 * 开启一个新的线程，以随时收取信息
	 */
    Thread thread=new Thread(this);
    thread.start();
    /**
     * 向对方发送自己的信息（昵称和图片代号）
     */
    String b1=new String("$"+m+myname.getText());
	sendMessage(b1);
	setLocation(400,100);  //设置位置
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
/**
 * 
 * FontAttrib类  构建字体类
 *
 */
public class FontAttrib
{
	public static final int GENERAL = 0; // 常规
	public static final int BOLD = 1; // 粗体
	public static final int ITALIC = 2; // 斜体
	public static final int BOLD_ITALIC = 3; // 粗斜体
	public SimpleAttributeSet attrSet = null; // 属性集
	public String text = null, name = null; // 要输入的文本和字体名称
	public int style = 0, size = 2; // 样式和字号
	public Color color = null; // 文字颜色和背景颜色
	/**
	 * * 一个空的构造
	 * */
	public FontAttrib(){}
	/**
	 * 通过StyleConstants把属性值传给attrSet对象，包括字体名称，颜色，大小，是否加宽加粗
	 * @return SimpleAttributeSet 返回一个新的属性集
	 */
	public SimpleAttributeSet getAttrSet()
	{
		attrSet = new SimpleAttributeSet();
		if (name != null)
			/**
			 * StyleConstants一个已知的 或常见的属性键和方法的集合，可通过应用 AttributeSet 或 MutableAttributeSet 方法以类型安全的方式获取/设置属性
			 */
			StyleConstants.setFontFamily(attrSet, name);
		if (style == FontAttrib.GENERAL)
		{
			StyleConstants.setBold(attrSet, false);
			StyleConstants.setItalic(attrSet, false);
			} 
		else if (style == FontAttrib.BOLD)
		{
			StyleConstants.setBold(attrSet, true);
			StyleConstants.setItalic(attrSet, false);
			} 
		else if (style == FontAttrib.ITALIC)
		{
			StyleConstants.setBold(attrSet, false);
			StyleConstants.setItalic(attrSet, true);
			} 
		else if(style == FontAttrib.BOLD_ITALIC)
		{
			StyleConstants.setBold(attrSet, true);
			StyleConstants.setItalic(attrSet, true);
			}
		StyleConstants.setFontSize(attrSet, size);
		if (color != null)
			StyleConstants.setForeground(attrSet, color);
		return attrSet;
		}
	/**
	 * @param attrSet  返回一个属性集
	 */
	public void setAttrSet(SimpleAttributeSet attrSet) {
		this.attrSet = attrSet;
		}
	public String getText() {
		return text;
		}
	public void setText(String text) {
		this.text = text;
		}
	public Color getColor() {
		return color;
		}
	public void setColor(Color color) {
		this.color = color;
		}
	public String getName() {
		return name;
		}
	public void setName(String name) {
		this.name = name;
		}
	public int getSize() {
		return size;
		}
	public void setSize(int size) {
		this.size = size;
		}
	public int getStyle() {
		return style;
		}
	public void setStyle(int style) {
		this.style = style;
		}
	}
/**
 * 插入文本的操作，主要是想聊天面板上插入自己定义风格的文字信息
 * @param attrib  返回一个属性集
 */
private void insert(FontAttrib attrib) {
	try {
		doc.insertString(doc.getLength(), attrib.getText() + "\n", attrib.getAttrSet());
		}
	catch (BadLocationException e) {
		e.printStackTrace();
		}
	}
/**
 * shake（）类 负责窗口的抖动操作
 * <p>获取窗口左顶点坐标，然后用setLocation方法操作，循环形成抖动效果
 */
public void shake() {
	int x=getX();
	int y=getY();
	for(int i=1;i<200;i++)
	{
		setLocation(x, y+5);
		setLocation(x+5,y+5);
		setLocation(x+5,y);
		setLocation(x, y);
	}
  }
/**
 * 监听各种按钮的类
 */
public void actionPerformed(ActionEvent event)        //点击按扭发送数据。
{
	//如果按钮为发送按钮
	if(event.getSource()==send){
		byte b[]=outMessage.getText().trim().getBytes();
		try{
			//得到自己设定的ip地址
			InetAddress address=InetAddress.getByName(ip);
			//显示自己名字还有冒号，表明是自己发的这个消息
			att.setText(myname.getText()+":");
          	insert(att);
          	//在加入表情时，我人为的在表情前加了#号，以识别是表情还是文字
          	//当是表情时，就向对方发送消息类型#号，和x，y确定的表情信息（xy为表情的名字）
	        if(outMessage.getText().toString().charAt(0)=='#'){
	        	String m2=new String("#"+x+y);
	        	inMessage.setCaretPosition(doc.getLength());
	           	inMessage.insertIcon(new ImageIcon("images/face1/"+x+y+".png"));
	           	insert(new FontAttrib()); 
	        	b=m2.trim().getBytes();
	        	}
	        else{
	        	att.setText(outMessage.getText());
	        	insert(att);
	        }
	        DatagramPacket data=new DatagramPacket(b,b.length,address,outPort);
	        DatagramSocket mail=new DatagramSocket();
	        mail.send(data);
	        }
		catch(Exception e){}
		outMessage.setText("");
		} 
	//如果按钮为font也就是设置子类的按钮  调用设置字体内部类
	//获取左上角坐标，目的是通过构造方法让fontDia显示在聊天框合适确定的位置
	else if(event.getSource()==font){
		x=getX();
		y=getY();
		new fontDia(x+7,y+273);
	}
	//如果按钮为gif也就是发送表情 调用有各种表情的面板
	//获取左上角坐标，目的是通过构造方法让imgDia显示在聊天框合适确定的位置
	else if(event.getSource()==gif){
		x=getX();
		y=getY();
		new imageDia(this,x+7,y+135);
	}
	//如果按钮为tup也就是是发送图片 调用一个可以进行文件查找的类
	else if(event.getSource()==tup){
			JFileChooser f = new JFileChooser(); // 查找文件
			f.showOpenDialog(null);
			try {
				insertImg(f.getSelectedFile());
			} catch (IOException e) {
				e.printStackTrace();
			} // 插入图片
			}
	//如果按钮为zhd也就是抖动  调用shake（）使自己窗口抖动
	//发送消息，内容为表明消息类型的符号* 对方接受识别后会产生调用振动函数
	else if(event.getSource()==zhd){
		shake();
		String b1=new String("*");
		sendMessage(b1);
		}
	//如果按钮是自己的oo秀，点击可以更换oo秀，设置j的目的是为了循环更换
	//更换完了以后想对方发一条消息，内容为表明消息类型的符号&和表明是哪一张图片的参数j
	//对方可以接受并改变你的oo秀，实现同步
	else if(event.getSource()==myimgxiu){
		j++;
		if(j==6)
			j=1;
		myimgxiu.setIcon(new ImageIcon("images/qxiu/"+j+".png"));
		String b1=new String("&"+j);
		sendMessage(b1);
	}
	}
/**
 * 实现对按下enter键的监听  内容与按下send键操作一样 不做注释
 */
private class EnterKey extends KeyAdapter{
	public void keyPressed(KeyEvent kevent){
		if(kevent.getKeyCode()==10){
			byte b[]=outMessage.getText().trim().getBytes();
			try{
				InetAddress address=InetAddress.getByName(ip);
				att.setText(myname.getText()+":");
	          	insert(att);
		        if(outMessage.getText().toString().charAt(0)=='#'){
		        	String m2=new String("#"+x+y);
		        	inMessage.setCaretPosition(doc.getLength());
		           	inMessage.insertIcon(new ImageIcon("images/face1/"+x+y+".png"));
		           	insert(new FontAttrib()); 
		        	b=m2.trim().getBytes();
		        	}
		        else{
		        	att.setText(outMessage.getText());
		        	insert(att);
		        }
		        DatagramPacket data=new DatagramPacket(b,b.length,address,outPort);
		        DatagramSocket mail=new DatagramSocket();
		        mail.send(data);
		        }
			catch(Exception e){}
			outMessage.setText("");
			}
		}
	}
/**
 * 发送邮报的类 
 * @param b1  所要发送的信息 字符串形式传入
 */
public void sendMessage(String b1){
	//trim()方法可用于截去字符串开头和末尾的空白
	//getBytes() 使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
	byte b[]=b1.trim().getBytes();
	try{
		InetAddress address=InetAddress.getByName(ip);
		DatagramPacket data=new DatagramPacket(b,b.length,address,outPort);
        DatagramSocket mail=new DatagramSocket();
        mail.send(data);
        }
	catch(Exception e){}
}
/**
 * 接收发送来的信息，并在窗口显示
 * 主要识别不同类型信息的前缀给予不同的方法
 */
public void run()                                //接收数据。
{  
	DatagramPacket pack=null;
	DatagramSocket mail=null;
	byte b[]=new byte[8192];
	try{
		pack=new DatagramPacket(b,b.length);
		mail=new DatagramSocket(inPort);
		}
	catch(Exception e){}
	while(true)
    {  
      try{
    	  mail.receive(pack); 
            String message=new String(pack.getData(),0,pack.getLength());
            //如果第一个字符是*，就识别出来是振动信息，就调用振动函数
            if(message.charAt(0)=='*') 
           	 shake();
            //如果第一个字符是#，则识别出发送的是表情信息，在把第二个，第三个字符信息读入，得到具体的表情信息，显示出来
            else if(message.charAt(0)=='#'){
            	att.setText(name.getText()+":");
                insert(att);
           	 x=(int)(message.charAt(1))-48;
           	 y=(int)message.charAt(2)-48;
           	 inMessage.setCaretPosition(doc.getLength());
           	 inMessage.insertIcon(new ImageIcon("images/face1/"+x+y+".png"));
           	 insert(new FontAttrib()); 
            }
            //如果第一个字符是&，则识别出发送的是更换了oo秀的图片，则读入第二个字符信息，就是具体的oo秀名字，setIcon方法变换oo秀
            else if(message.charAt(0)=='&'){
                imgxiu.setIcon(new ImageIcon("images/qxiu/"+message.charAt(1)+".png"));
            }
            //如果如果第一个字符是$，这是一个把自己的图片，昵称传给对方的消息，只执行一次，传自己的昵称和图片在构造方法中
            //第二个进入后就会向第一个进入的发送这个消息，这接受到以后，在给对方发自己信息的消息，这样两个都识别的对方
            //为了避免循环一直发送，就设了个i,让这个语句只能执行一次。
            else if(message.charAt(0)=='$'){
            	if(i==0){
            		i++;
            		imghead.setIcon(new ImageIcon("images/headpic/"+message.charAt(1)+".png"));
            		inMessage.setText("对方已接入，准备talk");
            		insert(new FontAttrib());
            		System.out.println(message.substring(2));
            		name.setText(message.substring(2));
            		String b1=new String("$"+k+myname.getText());
            		sendMessage(b1);
            		}
            	}
            //如果是普通的文字消息信息，就调用att的setText方法，然后调用insert方法写入到聊天框中
            else{
            	 att.setText(name.getText()+":");
                 insert(att);
                 att.setText(message);
                 insert(att);
            }
            }
      catch(Exception e){}
      } 
	}
/**
 * 本类只显示了选择图片并且在自己的框中显示的作用
 * 传输尤其是在另一端接受还是存在问题 没有解决
 * @param file  文件
 * @throws IOException
 */
private void insertImg(File file) throws IOException{
	inMessage.setCaretPosition(doc.getLength()); // 设置插入位置
	inMessage.insertIcon(new ImageIcon(file.getPath())); // 插入图片
	insert(new FontAttrib()); // 这样做可以换行
	InputStream in = this.getClass().getResourceAsStream("1.jpg"); 
	System.out.println(in);
	byte b[]=new byte[8192];
	InetAddress address=InetAddress.getByName(ip);
	int len = 0;
	while((len = in.read(b))!=-1){
		DatagramSocket mail=new DatagramSocket();
		DatagramPacket data1= new DatagramPacket(b,len,address,outPort+2);
		mail.send(data1);
		}
	}
/**
 * 表情图标类显示表情图标的对话框窗口
 */
public class  imageDia extends JDialog{
	JLabel label;
	final private int WIDTH=30,HEIGHT=30;
	JTabbedPane tabbedPane = new JTabbedPane();
	public imageDia (JFrame frame,int xx,int yy)
	{
		    super(frame);
			setLayout(new GridLayout(4,5));//采用多行多列布局方式
			setBounds(xx, yy, 250, 200);
			setVisible(true);
			setBackground(Color.blue);
			//依次添加指定路径文件夹下的图标，图标命名 00，01，02，10···
			for(y=0;y<4;y++){
				for(x=0;x<5;x++)
				{
					label=new JLabel(new ImageIcon("images/face1/"+x+y+".png"));
					label.setBorder(null);   //设置无边框
					add(label);   //添加label对象
			}
			}
			addMouseListener(new Action());
	}
	/**
	 *鼠标点击类，获取鼠标点击位置，进行一些换算后 得到x，y值 
	 *正好对应对图标的名称（xy.png），插入指定表情
	 */
	class Action extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			x=e.getX()/48;
			y=(e.getY()-30)/40;
            outMessage.setText("#");
            outMessage.insertIcon(new ImageIcon("images/face1/"+x+y+".png"));
		}
	}
	}
/**
 * 设置字体类的继承JDialog，Runnable,ActionListener
 * 用于设置字体的属性值
 */
public class fontDia extends JDialog implements Runnable,ActionListener{
	Box box = null;
	JComboBox fontName = null, fontSize = null;
	JRadioButton jiacu,xieti,fontcolor;
	boolean xxx=true;
	/**
	 * 使用Box进行布局，
	 */
	public fontDia(int xx,int yy){
		//给两个JComboBox下拉选择条里设置内容，并添加监听器
		String[] str_name = { "楷体 ","宋体", "黑体", "幼圆", "方正姚体"};
		String[] str_Size = { "12", "14", "18", "22", "30", "40" };
		fontName = new JComboBox(str_name); // 字体名称
		fontSize = new JComboBox(str_Size); // 字号
		fontName.addActionListener(this);
		fontSize.addActionListener(this);
		setLocation(xx, yy);
		box = Box.createHorizontalBox();  //平行的排列方式
		//给三个按钮设置图标并添加监听器
		jiacu=new JRadioButton(new ImageIcon("images/button/jia.png"),true);
		jiacu.addActionListener(this);
		xieti=new JRadioButton(new ImageIcon("images/button/xie.png"),false);
		xieti.addActionListener(this);
		fontcolor=new JRadioButton(new ImageIcon("images/button/se.png"),false);
		fontcolor.setBorder(null);
		fontcolor.addActionListener(this);
		//向box添加组件 并设置间隔
		box.add(fontName);
		box.add(Box.createHorizontalStrut(8));
		box.add(fontSize);
		box.add(Box.createHorizontalStrut(8));
		box.add(jiacu);
		box.add(Box.createHorizontalStrut(8));
		box.add(xieti);
		box.add(Box.createHorizontalStrut(8));
		box.add(fontcolor);
		this.setVisible(true);
		add(box);
		pack();
	}
	/**
	 * 按钮点击简单监听类
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jiacu||e.getSource()==xieti){
			Thread thread=new Thread(this);
			xxx=true;
			thread.start();
			  try
			  {
				  thread.sleep(1);
				  }
	          catch(InterruptedException ee){}
			xxx=false;//停止run（）方法  睡眠以后停止才有效   但是这是我找出的
			          //让它点击之后再调用run方法，并且自己停止的方法，感觉很奇怪 
		}
		if(e.getSource()==fontcolor){
			Color newColor=JColorChooser.showDialog(this,"调色板",outMessage.getForeground());
	         att.setColor(newColor);
		}
		else{
			att.setSize(Integer.parseInt((String) fontSize.getSelectedItem()));
			att.setName((String) fontName.getSelectedItem());
		}
	}
	/**
	 * 设置字体类开的线程 
	 * 用于监听几个按钮被选择的情况，进而设置字体的加宽斜体属性
	 */
	public void run()
	{
			while(xxx)
			{
				if(jiacu.isSelected()&&xieti.isSelected())
					att.setStyle(FontAttrib.BOLD_ITALIC);
				else if(jiacu.isSelected())
					att.setStyle(FontAttrib.BOLD);
				else if(xieti.isSelected())
					att.setStyle(FontAttrib.ITALIC);

				else
					att.setStyle(FontAttrib.GENERAL);
				}
			}
	}
}
