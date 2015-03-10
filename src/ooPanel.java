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
 * ����������� 
 */
class ooPanel extends JFrame implements Runnable,ActionListener
{
	int x,y,i=0,j=1,k,inPort,outPort;
	/**
	 * edge1 �������壬�񶯵�4��ͼ��
	 */
	JPanel edge1=new JPanel(new FlowLayout(0)); 
	/**
	 * p ����edge1����������belowJ
	 */
	JPanel p=new JPanel(new BorderLayout());
	/**
	 * mainJ ����p����ʾ��Ϣ�����
	 */
	JPanel mainJ=new JPanel(new BorderLayout());
	/**
	 * topJ �ϲ���һ����ʾ˫����ͷ����ǳ�
	 */
	JPanel topJ=new JPanel(new FlowLayout(0));
	/**
	 * rightJ �ҷ���ʾ˫����oo������
	 */
	JPanel rightJ=new JPanel(new GridLayout(2,1));
	/**
	 * belowJ �·����ŷ��ͺ�ȡ����ť  
	 */
	JPanel belowJ=new JPanel(new FlowLayout(2));
	/**
	 * outMessage  ��ʾ��Ϣ�����
	 */
	public JTextPane outMessage=new JTextPane();
	/**
	 * inMessage ������Ϣ�����
	 */
	public JTextPane inMessage=new JTextPane();
	/**
	 * ��ʽ���ĵ�
	 */
	private StyledDocument doc = null;
	/**
	 * �Լ����ǳ�
	 */
	JTextField myname=new JTextField("");
	/**
	 * �Է����ǳƣ�����Ϊ�ȴ�����
	 */
	JTextField name=new JTextField("�ȴ�����");
	/**
	 * һֱ�ı� ���Լ��ǳƺͶԷ��ǳ��м����
	 */
	JTextField chat=new JTextField("  CHAT  WITH  ");
	/**
	 * ��������İ�ť ����ͼ��
	 */
	JButton font = new JButton(new ImageIcon("images/button/font.png"));	
	/**
	 * ���ͱ���İ�ť
	 */
	JButton gif = new JButton(new ImageIcon("images/button/gif.png"));
	/**
	 *����ͼƬ�İ�ť
	 */
	JButton tup = new JButton(new ImageIcon("images/button/tup.png"));
	/**
	 * �����񶯵İ�ť
	 */
	JButton zhd = new JButton(new ImageIcon("images/button/chen.png"));
	/**
	 * �Լ���ͷ��ť ������Ĭ��ͼ��
	 */
	JButton myimghead=new JButton(new ImageIcon("images/headpic/4.png"));
	JButton imghead=new JButton(new ImageIcon("images/headpic/00.png"));
	/**
	 * �Լ�oo��İ�ť ������Ĭ��ͼ��
	 */
	JButton imgxiu=new JButton(new ImageIcon("images/qxiu/2.png"));
	JButton myimgxiu=new JButton(new ImageIcon("images/qxiu/1.png"));
	/**
	 * ���Ͱ�ť
	 */
	JButton send=new JButton(new ImageIcon("images/button/send.png"));
	JButton exit=new JButton(new ImageIcon("images/button/exit.png"));
	/**
	 * �Լ����õ�һ���Ƚ�����ı�����ɫ
	 */
	final Color back=new Color(203,220,197);
	/**
	 * �Լ�������FontAttrib��Ķ���
	 */
	FontAttrib att = new FontAttrib();
	/**
	 * �ַ���ip �洢ipֵ
	 */
	String ip;
	/**
	 * ����¼����������Ϣ��ͼƬ��Ϣ���ǳƣ�ip��ַ�ȵ�ͨ��
	 * �������Ĺ��췽��ooPanel��������У�������һЩ����
	 * ������
	 * @param m  ���ֱ�ʾ��������һ��ͷ��
	 * @param ip1  ip��Ϣ
	 * @param name1    �ǳ���Ϣ
	 * @param inport  ���ն˿���Ϣ
	 * @param outport ���Ͷ˿���Ϣ
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
	//ʹcp���ݲ����Ǵ��ҵ����
	cp.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	//�����ı����ܱ��༭
    inMessage.setEditable(false);
    inMessage.setSize(360, 500);
    //��ȡ��༭��������ģ�͸�doc
    doc = inMessage.getStyledDocument();
    //����outMessage����ѡ��С     Dimension���װ��������������Ŀ�Ⱥ͸߶�
    //outMessage.setPreferredSize(new Dimension(60,80));
    outMessage.addKeyListener(new EnterKey());//����enter��������
    setSize(550,500);
    setVisible(true);
    /**
     * �Ӻ���ͼ��İ�ť
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
     * �������ܲ��֣�ʹ��GridBagLayout��ʽ����Ҫ���Ų�topJ��rightJ��mainJ���������
     * ʹ��������ֿ������� ѹ����û������ ���ܺ�г
     */
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    //��cp
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
     * ��attĬ��������Ϣֵ
     */
	att.setName("����");
	att.setSize(20);
	/**
	 * ����һ���µ��̣߳�����ʱ��ȡ��Ϣ
	 */
    Thread thread=new Thread(this);
    thread.start();
    /**
     * ��Է������Լ�����Ϣ���ǳƺ�ͼƬ���ţ�
     */
    String b1=new String("$"+m+myname.getText());
	sendMessage(b1);
	setLocation(400,100);  //����λ��
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
/**
 * 
 * FontAttrib��  ����������
 *
 */
public class FontAttrib
{
	public static final int GENERAL = 0; // ����
	public static final int BOLD = 1; // ����
	public static final int ITALIC = 2; // б��
	public static final int BOLD_ITALIC = 3; // ��б��
	public SimpleAttributeSet attrSet = null; // ���Լ�
	public String text = null, name = null; // Ҫ������ı�����������
	public int style = 0, size = 2; // ��ʽ���ֺ�
	public Color color = null; // ������ɫ�ͱ�����ɫ
	/**
	 * * һ���յĹ���
	 * */
	public FontAttrib(){}
	/**
	 * ͨ��StyleConstants������ֵ����attrSet���󣬰����������ƣ���ɫ����С���Ƿ�ӿ�Ӵ�
	 * @return SimpleAttributeSet ����һ���µ����Լ�
	 */
	public SimpleAttributeSet getAttrSet()
	{
		attrSet = new SimpleAttributeSet();
		if (name != null)
			/**
			 * StyleConstantsһ����֪�� �򳣼������Լ��ͷ����ļ��ϣ���ͨ��Ӧ�� AttributeSet �� MutableAttributeSet ���������Ͱ�ȫ�ķ�ʽ��ȡ/��������
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
	 * @param attrSet  ����һ�����Լ�
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
 * �����ı��Ĳ�������Ҫ������������ϲ����Լ��������������Ϣ
 * @param attrib  ����һ�����Լ�
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
 * shake������ ���𴰿ڵĶ�������
 * <p>��ȡ�����󶥵����꣬Ȼ����setLocation����������ѭ���γɶ���Ч��
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
 * �������ְ�ť����
 */
public void actionPerformed(ActionEvent event)        //�����Ť�������ݡ�
{
	//�����ťΪ���Ͱ�ť
	if(event.getSource()==send){
		byte b[]=outMessage.getText().trim().getBytes();
		try{
			//�õ��Լ��趨��ip��ַ
			InetAddress address=InetAddress.getByName(ip);
			//��ʾ�Լ����ֻ���ð�ţ��������Լ����������Ϣ
			att.setText(myname.getText()+":");
          	insert(att);
          	//�ڼ������ʱ������Ϊ���ڱ���ǰ����#�ţ���ʶ���Ǳ��黹������
          	//���Ǳ���ʱ������Է�������Ϣ����#�ţ���x��yȷ���ı�����Ϣ��xyΪ��������֣�
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
	//�����ťΪfontҲ������������İ�ť  �������������ڲ���
	//��ȡ���Ͻ����꣬Ŀ����ͨ�����췽����fontDia��ʾ����������ȷ����λ��
	else if(event.getSource()==font){
		x=getX();
		y=getY();
		new fontDia(x+7,y+273);
	}
	//�����ťΪgifҲ���Ƿ��ͱ��� �����и��ֱ�������
	//��ȡ���Ͻ����꣬Ŀ����ͨ�����췽����imgDia��ʾ����������ȷ����λ��
	else if(event.getSource()==gif){
		x=getX();
		y=getY();
		new imageDia(this,x+7,y+135);
	}
	//�����ťΪtupҲ�����Ƿ���ͼƬ ����һ�����Խ����ļ����ҵ���
	else if(event.getSource()==tup){
			JFileChooser f = new JFileChooser(); // �����ļ�
			f.showOpenDialog(null);
			try {
				insertImg(f.getSelectedFile());
			} catch (IOException e) {
				e.printStackTrace();
			} // ����ͼƬ
			}
	//�����ťΪzhdҲ���Ƕ���  ����shake����ʹ�Լ����ڶ���
	//������Ϣ������Ϊ������Ϣ���͵ķ���* �Է�����ʶ������������񶯺���
	else if(event.getSource()==zhd){
		shake();
		String b1=new String("*");
		sendMessage(b1);
		}
	//�����ť���Լ���oo�㣬������Ը���oo�㣬����j��Ŀ����Ϊ��ѭ������
	//���������Ժ���Է���һ����Ϣ������Ϊ������Ϣ���͵ķ���&�ͱ�������һ��ͼƬ�Ĳ���j
	//�Է����Խ��ܲ��ı����oo�㣬ʵ��ͬ��
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
 * ʵ�ֶ԰���enter���ļ���  �����밴��send������һ�� ����ע��
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
 * �����ʱ����� 
 * @param b1  ��Ҫ���͵���Ϣ �ַ�����ʽ����
 */
public void sendMessage(String b1){
	//trim()���������ڽ�ȥ�ַ�����ͷ��ĩβ�Ŀհ�
	//getBytes() ʹ��ƽ̨��Ĭ���ַ������� String ����Ϊ byte ���У���������洢��һ���µ� byte �����С�
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
 * ���շ���������Ϣ�����ڴ�����ʾ
 * ��Ҫʶ��ͬ������Ϣ��ǰ׺���費ͬ�ķ���
 */
public void run()                                //�������ݡ�
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
            //�����һ���ַ���*����ʶ�����������Ϣ���͵����񶯺���
            if(message.charAt(0)=='*') 
           	 shake();
            //�����һ���ַ���#����ʶ������͵��Ǳ�����Ϣ���ڰѵڶ������������ַ���Ϣ���룬�õ�����ı�����Ϣ����ʾ����
            else if(message.charAt(0)=='#'){
            	att.setText(name.getText()+":");
                insert(att);
           	 x=(int)(message.charAt(1))-48;
           	 y=(int)message.charAt(2)-48;
           	 inMessage.setCaretPosition(doc.getLength());
           	 inMessage.insertIcon(new ImageIcon("images/face1/"+x+y+".png"));
           	 insert(new FontAttrib()); 
            }
            //�����һ���ַ���&����ʶ������͵��Ǹ�����oo���ͼƬ�������ڶ����ַ���Ϣ�����Ǿ����oo�����֣�setIcon�����任oo��
            else if(message.charAt(0)=='&'){
                imgxiu.setIcon(new ImageIcon("images/qxiu/"+message.charAt(1)+".png"));
            }
            //��������һ���ַ���$������һ�����Լ���ͼƬ���ǳƴ����Է�����Ϣ��ִֻ��һ�Σ����Լ����ǳƺ�ͼƬ�ڹ��췽����
            //�ڶ��������ͻ����һ������ķ��������Ϣ������ܵ��Ժ��ڸ��Է����Լ���Ϣ����Ϣ������������ʶ��ĶԷ�
            //Ϊ�˱���ѭ��һֱ���ͣ������˸�i,��������ֻ��ִ��һ�Ρ�
            else if(message.charAt(0)=='$'){
            	if(i==0){
            		i++;
            		imghead.setIcon(new ImageIcon("images/headpic/"+message.charAt(1)+".png"));
            		inMessage.setText("�Է��ѽ��룬׼��talk");
            		insert(new FontAttrib());
            		System.out.println(message.substring(2));
            		name.setText(message.substring(2));
            		String b1=new String("$"+k+myname.getText());
            		sendMessage(b1);
            		}
            	}
            //�������ͨ��������Ϣ��Ϣ���͵���att��setText������Ȼ�����insert����д�뵽�������
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
 * ����ֻ��ʾ��ѡ��ͼƬ�������Լ��Ŀ�����ʾ������
 * ��������������һ�˽��ܻ��Ǵ������� û�н��
 * @param file  �ļ�
 * @throws IOException
 */
private void insertImg(File file) throws IOException{
	inMessage.setCaretPosition(doc.getLength()); // ���ò���λ��
	inMessage.insertIcon(new ImageIcon(file.getPath())); // ����ͼƬ
	insert(new FontAttrib()); // ���������Ի���
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
 * ����ͼ������ʾ����ͼ��ĶԻ��򴰿�
 */
public class  imageDia extends JDialog{
	JLabel label;
	final private int WIDTH=30,HEIGHT=30;
	JTabbedPane tabbedPane = new JTabbedPane();
	public imageDia (JFrame frame,int xx,int yy)
	{
		    super(frame);
			setLayout(new GridLayout(4,5));//���ö��ж��в��ַ�ʽ
			setBounds(xx, yy, 250, 200);
			setVisible(true);
			setBackground(Color.blue);
			//�������ָ��·���ļ����µ�ͼ�꣬ͼ������ 00��01��02��10������
			for(y=0;y<4;y++){
				for(x=0;x<5;x++)
				{
					label=new JLabel(new ImageIcon("images/face1/"+x+y+".png"));
					label.setBorder(null);   //�����ޱ߿�
					add(label);   //���label����
			}
			}
			addMouseListener(new Action());
	}
	/**
	 *������࣬��ȡ�����λ�ã�����һЩ����� �õ�x��yֵ 
	 *���ö�Ӧ��ͼ������ƣ�xy.png��������ָ������
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
 * ����������ļ̳�JDialog��Runnable,ActionListener
 * �����������������ֵ
 */
public class fontDia extends JDialog implements Runnable,ActionListener{
	Box box = null;
	JComboBox fontName = null, fontSize = null;
	JRadioButton jiacu,xieti,fontcolor;
	boolean xxx=true;
	/**
	 * ʹ��Box���в��֣�
	 */
	public fontDia(int xx,int yy){
		//������JComboBox����ѡ�������������ݣ�����Ӽ�����
		String[] str_name = { "���� ","����", "����", "��Բ", "����Ҧ��"};
		String[] str_Size = { "12", "14", "18", "22", "30", "40" };
		fontName = new JComboBox(str_name); // ��������
		fontSize = new JComboBox(str_Size); // �ֺ�
		fontName.addActionListener(this);
		fontSize.addActionListener(this);
		setLocation(xx, yy);
		box = Box.createHorizontalBox();  //ƽ�е����з�ʽ
		//��������ť����ͼ�겢��Ӽ�����
		jiacu=new JRadioButton(new ImageIcon("images/button/jia.png"),true);
		jiacu.addActionListener(this);
		xieti=new JRadioButton(new ImageIcon("images/button/xie.png"),false);
		xieti.addActionListener(this);
		fontcolor=new JRadioButton(new ImageIcon("images/button/se.png"),false);
		fontcolor.setBorder(null);
		fontcolor.addActionListener(this);
		//��box������ �����ü��
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
	 * ��ť����򵥼�����
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
			xxx=false;//ֹͣrun��������  ˯���Ժ�ֹͣ����Ч   �����������ҳ���
			          //�������֮���ٵ���run�����������Լ�ֹͣ�ķ������о������ 
		}
		if(e.getSource()==fontcolor){
			Color newColor=JColorChooser.showDialog(this,"��ɫ��",outMessage.getForeground());
	         att.setColor(newColor);
		}
		else{
			att.setSize(Integer.parseInt((String) fontSize.getSelectedItem()));
			att.setName((String) fontName.getSelectedItem());
		}
	}
	/**
	 * ���������࿪���߳� 
	 * ���ڼ���������ť��ѡ��������������������ļӿ�б������
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
