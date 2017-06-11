package event.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import com.sun.awt.AWTUtilities;

import event.control.UserManager;

/**
 * 用户登录窗体
 * @author Admin
 *
 */
public class FrmRegister extends JFrame {

    private static final long serialVersionUID = 1L;
    private Point origin; // 用于移动窗体
    private BufferedImage img; // 用来设定窗体不规则样式的图片

    private ImageIcon background;
    private JTextField userText = new JTextField(30);
    private JPasswordField passwordText = new JPasswordField(30);
    private JPasswordField passwordText2 = new JPasswordField(30);
    private JLabel userLabel = new JLabel("用户名");
    private JLabel passwordLabel = new JLabel("设定密码 ");
    private JLabel passwordLabel2 = new JLabel("密码确认 ");
    ImageIcon iconOk = new ImageIcon("bin/img/注册.png");
    //ImageIcon iconCa = new ImageIcon("H:/imag/按钮/关闭.png");
    ImageIcon iconEx = new ImageIcon("bin/img/×.png");
    private JButton okbtn = new JButton(iconOk);
    //private JButton cabtn = new JButton(iconCa);
    private JButton exit = new JButton(iconEx);
    
    /**
     * 初始化窗体
     */
    public FrmRegister() {
    	
        super();
        setTitle("事务提醒系统");
        background = new ImageIcon("bin/img/login.png");
        JLabel back = new JLabel(background);
        back.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        /*
         * 首先初始化一张图片，我们可以选择一张有透明部分的不规则图片
         *  (要想图片能够显示透明，必须使用PNG格式的图片)
         */
        MediaTracker mt = new MediaTracker(this);

        try {
            img = ImageIO.read(new File("bin/img/login.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        mt.addImage(img, 0);

        try {
            mt.waitForAll(); // 开始加载由此媒体跟踪器跟踪的所有图像
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            initialize(); // 窗体形状初始化
        } catch (IOException e) {
            e.printStackTrace();
        }

        addMenu();
        this.add(back);
        this.setVisible(true);
    }

    /**
     * 组件初始化
     */
    public void addMenu() {
        this.setLayout(null);
        //设置字体
        Font font = new Font("", 0, 18);
        userLabel.setForeground(Color.black);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel2.setForeground(Color.BLACK);
        userLabel.setFont(font);
        passwordLabel.setFont(font);
        passwordLabel2.setFont(font);
        passwordText.setEchoChar('*');
        passwordText2.setEchoChar('*');

        userLabel.setBounds(375, 120, 100, 50);
        passwordLabel.setBounds(375, 200, 100, 50);
        passwordLabel2.setBounds(375, 280, 100, 50);
        userText.setBounds(375, 160, 170, 30);
        passwordText.setBounds(375, 240, 170, 30);
        passwordText2.setBounds(375, 320, 170, 30);
        okbtn.setBorderPainted(false);
        okbtn.setBounds(425, 375, 60, 25);
       // cabtn.setBounds(475, 375, 60, 25);
        exit.setBorderPainted(false);
        exit.setBounds(614, 0, 34, 33);
        this.add(userLabel);
        this.add(userText);
        this.add(passwordLabel);
        this.add(passwordText);
        this.add(passwordText2);
        this.add(passwordLabel2);
        this.add(okbtn);
       // this.add(cabtn);
        this.add(exit);
        
        okbtn.addMouseListener(new OwnListener());
       // cabtn.addMouseListener(new OwnListener());
        userText.addKeyListener(new KeyOwnListener());
        passwordText.addKeyListener(new KeyOwnListener());
        passwordText2.addKeyListener(new KeyOwnListener());
        exit.addMouseListener(new OwnListener());
        //userText.setText("20160601");
        //passwordText.setText("84878323");
    }

    /**
     * 创建和图片形状一样的窗体
     * @throws IOException
     */
    private void initialize() throws IOException { // 窗体初始化
        // 设定窗体大小和图片一样大
        this.setSize(img.getWidth(null), img.getHeight(null));
        // 设定禁用窗体装饰，这样就取消了默认的窗体结构
        this.setUndecorated(true);
        // 初始化用于移动窗体的原点
        this.origin = new Point();

        // 调用AWTUtilities的setWindowShape方法设定本窗体为制定的Shape形状
        AWTUtilities.setWindowShape(this, getImageShape(img));
        // 设定窗体可见度
        AWTUtilities.setWindowOpacity(this, 0.8f);

        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(false);
        // 由于取消了默认的窗体结构，所以我们要手动设置一下移动窗体的方法
        this.addMouseListener(new OwnListener());
        //监听鼠标移动事件
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
                        - origin.y);
            }
        });
    }

    /**
     * 将Image图像转换为Shape图形
     * @param img
     * @return
     */
    public Shape getImageShape(Image img) {
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        int width = img.getWidth(null);// 图像宽度
        int height = img.getHeight(null);// 图像高度

        // 筛选像素
        // 首先获取图像所有的像素信息
        PixelGrabber pgr = new PixelGrabber(img, 0, 0, -1, -1, true);
        try {
            pgr.grabPixels();
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
        int pixels[] = (int[]) pgr.getPixels();

        // 循环像素
        for (int i = 0; i < pixels.length; i++) {
            // 筛选，将不透明的像素的坐标加入到坐标ArrayList x和y中
            int alpha = getAlpha(pixels[i]);
            if (alpha == 0) {
                continue;
            } else {
                x.add(i % width > 0 ? i % width - 1 : 0);
                y.add(i % width == 0 ? (i == 0 ? 0 : i / width - 1) : i / width);
            }
        }

        // 建立图像矩阵并初始化(0为透明,1为不透明)
        int[][] matrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = 0;
            }
        }

        // 导入坐标ArrayList中的不透明坐标信息
        for (int c = 0; c < x.size(); c++) {
            matrix[y.get(c)][x.get(c)] = 1;
        }

        /*
         * 由于Area类所表示区域可以进行合并，我们逐一水平"扫描"图像矩阵的每一行，
         * 将不透明的像素生成为Rectangle，再将每一行的Rectangle通过Area类的rec
         * 对象进行合并，最后形成一个完整的Shape图形
         */
        Area rec = new Area();
        int temp = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 1) {
                    if (temp == 0)
                        temp = j;
                    else if (j == width) {
                        if (temp == 0) {
                            Rectangle rectemp = new Rectangle(j, i, 1, 1);
                            rec.add(new Area(rectemp));
                        } else {
                            Rectangle rectemp = new Rectangle(temp, i,
                                    j - temp, 1);
                            rec.add(new Area(rectemp));
                            temp = 0;
                        }
                    }
                } else {
                    if (temp != 0) {
                        Rectangle rectemp = new Rectangle(temp, i, j - temp, 1);
                        rec.add(new Area(rectemp));
                        temp = 0;
                    }
                }
            }
            temp = 0;
        }
        return rec;
    }

    /**
     * 取得透明度
     * @param pixel
     * @return
     */
    private int getAlpha(int pixel) {
        return (pixel >> 24) & 0xff;
    }

    /**
     * 事件监听
     * @author Admin
     *
     */
    private class OwnListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            origin.x = e.getX();
            origin.y = e.getY();
        }

        // 窗体上单击鼠标右键关闭程序
        public void mouseClicked(MouseEvent e) {
            //如果点击的区域位于右上角红色按钮，则关闭程序
            if (e.getSource() == exit) {
                System.exit(0);
            } else if(e.getSource() == okbtn)
            {
            	String userid=userText.getText();
            	String pwd=new String (passwordText.getPassword());
            	String pwd2=new String (passwordText2.getPassword());
            	try
            	{
            		UserManager um=new UserManager();
            		um.createUser(userid, pwd, pwd2);
            		setVisible(false);
            		FrmLogin dlg=new FrmLogin();
                	dlg.setVisible(true);
            	}
            	catch(Exception e1)
            	{
            		
            		JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            	}
            }
        }

        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
        }
    }

    /**
     * 监听键盘Enter键，实现Enter登录
     * @author Admin
     *
     */
    private class KeyOwnListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (10 == e.getKeyCode()) {
                //验证用户是否合法，合法打开主程序
            }
        }
    }
    public static void main(String args[]) {  
    	   
        new FrmRegister();  
    } 
}
