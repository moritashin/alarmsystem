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
import event.model.BeanUser;
import event.util.BusinessException;
import javafx.scene.shape.Circle;

/**
 * �û���¼����
 * @author Admin
 *
 */
public class FrmLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private Point origin; // �����ƶ�����
    private BufferedImage img; // �����趨���岻������ʽ��ͼƬ

    private ImageIcon background;
    private JTextField userText = new JTextField(30);
    private JPasswordField passwordText = new JPasswordField(30);
    private JLabel userLabel = new JLabel("�û���");
    private JLabel passwordLabel = new JLabel("��  �� ");
    ImageIcon iconLo = new ImageIcon("bin/img/��¼.png");
    ImageIcon iconRe = new ImageIcon("bin/img/ע��.png");
    ImageIcon iconEx = new ImageIcon("bin/img/��.png");
    private JButton okbtn = new JButton(iconLo);
    private JButton register = new JButton(iconRe);
    private JButton exit = new JButton(iconEx);
    
    /**
     * ��ʼ������
     */
    public FrmLogin() {
        super();
        background = new ImageIcon("bin/img/login.png");
        JLabel back = new JLabel(background);
        back.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        /*
         * ���ȳ�ʼ��һ��ͼƬ�����ǿ���ѡ��һ����͸�����ֵĲ�����ͼƬ
         *  (Ҫ��ͼƬ�ܹ���ʾ͸��������ʹ��PNG��ʽ��ͼƬ)
         */
        MediaTracker mt = new MediaTracker(this);
        setTitle("��������ϵͳ");
        try {
            img = ImageIO.read(new File("bin/img/login.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        mt.addImage(img, 0);

        try {
            mt.waitForAll(); // ��ʼ�����ɴ�ý����������ٵ�����ͼ��
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            initialize(); // ������״��ʼ��
        } catch (IOException e) {
            e.printStackTrace();
        }

        addMenu();
        this.add(back);
        this.setVisible(true);
    }

    /**
     * �����ʼ��
     */
    public void addMenu() {
        this.setLayout(null);
        //��������
        Font font = new Font("", 0, 18);
        userLabel.setForeground(Color.black);
        passwordLabel.setForeground(Color.BLACK);
        userLabel.setFont(font);
        passwordLabel.setFont(font);
        passwordText.setEchoChar('*');

        userLabel.setBounds(375, 140, 100, 50);
        passwordLabel.setBounds(375, 220, 100, 50);
        userText.setBounds(375, 180, 170, 30);
        passwordText.setBounds(375, 260, 170, 30);
        okbtn.setBorderPainted(false);
        okbtn.setBounds(375, 320, 60, 25);
        register.setBorderPainted(false);
        register.setBounds(475, 320, 60, 25);
        exit.setBorderPainted(false);
        exit.setBounds(614, 0, 34, 33);
        this.add(userLabel);
        this.add(userText);
        this.add(passwordLabel);
        this.add(passwordText);
        this.add(okbtn);
        this.add(register);
        this.add(exit);
        okbtn.addMouseListener(new OwnListener());
        register.addMouseListener(new OwnListener());
        userText.addKeyListener(new KeyOwnListener());
        passwordText.addKeyListener(new KeyOwnListener());
        exit.addMouseListener(new OwnListener());
        //userText.setText("20160601");
        //passwordText.setText("84878323");
    }

    /**
     * ������ͼƬ��״һ���Ĵ���
     * @throws IOException
     */
    private void initialize() throws IOException { // �����ʼ��
        // �趨�����С��ͼƬһ����
        this.setSize(img.getWidth(null), img.getHeight(null));
        // �趨���ô���װ�Σ�������ȡ����Ĭ�ϵĴ���ṹ
        this.setUndecorated(true);
        // ��ʼ�������ƶ������ԭ��
        this.origin = new Point();

        // ����AWTUtilities��setWindowShape�����趨������Ϊ�ƶ���Shape��״
        AWTUtilities.setWindowShape(this, getImageShape(img));
        // �趨����ɼ���
        AWTUtilities.setWindowOpacity(this, 0.8f);

        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(false);
        // ����ȡ����Ĭ�ϵĴ���ṹ����������Ҫ�ֶ�����һ���ƶ�����ķ���
        this.addMouseListener(new OwnListener());
        //��������ƶ��¼�
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
                        - origin.y);
            }
        });
    }

    /**
     * ��Imageͼ��ת��ΪShapeͼ��
     * @param img
     * @return
     */
    public Shape getImageShape(Image img) {
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        int width = img.getWidth(null);// ͼ����
        int height = img.getHeight(null);// ͼ��߶�

        // ɸѡ����
        // ���Ȼ�ȡͼ�����е�������Ϣ
        PixelGrabber pgr = new PixelGrabber(img, 0, 0, -1, -1, true);
        try {
            pgr.grabPixels();
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
        int pixels[] = (int[]) pgr.getPixels();

        // ѭ������
        for (int i = 0; i < pixels.length; i++) {
            // ɸѡ������͸�������ص�������뵽����ArrayList x��y��
            int alpha = getAlpha(pixels[i]);
            if (alpha == 0) {
                continue;
            } else {
                x.add(i % width > 0 ? i % width - 1 : 0);
                y.add(i % width == 0 ? (i == 0 ? 0 : i / width - 1) : i / width);
            }
        }

        // ����ͼ����󲢳�ʼ��(0Ϊ͸��,1Ϊ��͸��)
        int[][] matrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = 0;
            }
        }

        // ��������ArrayList�еĲ�͸��������Ϣ
        for (int c = 0; c < x.size(); c++) {
            matrix[y.get(c)][x.get(c)] = 1;
        }

        /*
         * ����Area������ʾ������Խ��кϲ���������һˮƽ"ɨ��"ͼ������ÿһ�У�
         * ����͸������������ΪRectangle���ٽ�ÿһ�е�Rectangleͨ��Area���rec
         * ������кϲ�������γ�һ��������Shapeͼ��
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
     * ȡ��͸����
     * @param pixel
     * @return
     */
    private int getAlpha(int pixel) {
        return (pixel >> 24) & 0xff;
    }

    /**
     * �¼�����
     * @author Admin
     *
     */
    private class OwnListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            origin.x = e.getX();
            origin.y = e.getY();
        }

        // �����ϵ�������Ҽ��رճ���
        public void mouseClicked(MouseEvent e) {
            //������������λ�����ϽǺ�ɫ��ť����رճ���
        	
            if (e.getSource() == exit) {
                System.exit(0);
            } else if (e.getSource() == okbtn) {
                //��֤�û��Ƿ�Ϸ����Ϸ���������
            	
            	String userid=userText.getText();
            	String pwd=new String (passwordText.getPassword());
            	
            	try
            	{
            		UserManager um=new UserManager();
            		BeanUser u=new BeanUser();
            		um.login(userid, pwd);
            		u=um.loaduser(userid);
            		UserManager.currentLoginUser=u;
            		FrmMain dlg=new FrmMain();
                	dlg.setVisible(true);
            		setVisible(false);
            	}catch (BusinessException e1) {
    				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
    				return;
    			} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            
            } else if (e.getSource() == register) {
                //��ע��ҳ��
            	FrmRegister dlg=new FrmRegister();
            	dlg.setVisible(true);
            	dlg.setTitle("��������ϵͳ");
            	setVisible(false);
            	
            }
        }

        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
        }
    }

    /**
     * ��������Enter����ʵ��Enter��¼
     * @author Admin
     *
     */
    private class KeyOwnListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (10 == e.getKeyCode()) {
                //��֤�û��Ƿ�Ϸ����Ϸ���������
            }
        }
    }
    public static void main(String args[]) {  
    	   
        new FrmLogin();  
    } 
}