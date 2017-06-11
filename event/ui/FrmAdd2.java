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
import javax.swing.JTextField;


import com.sun.awt.AWTUtilities;

import event.control.EventManager;

/**
 * �û���¼����
 * @author Admin
 *
 */
public class FrmAdd2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private Point origin; // �����ƶ�����
    private BufferedImage img; // �����趨���岻������ʽ��ͼƬ

    private ImageIcon background;
    private JTextField monText = new JTextField(3);
    private JTextField dayText = new JTextField(3);
    private JTextField houText = new JTextField(3);
    private JTextField minText = new JTextField(3);
    private JTextField cntText = new JTextField(30);
    private JLabel monLabel = new JLabel("��");
    private JLabel dayLabel = new JLabel("��");
    private JLabel houLabel = new JLabel("ʱ");
    private JLabel minLabel = new JLabel("��");
    private JLabel timLabel = new JLabel("ʱ��");
    private JLabel cntLabel = new JLabel("����");
    ImageIcon iconEx = new ImageIcon("bin/img/��.png");
    ImageIcon iconOk = new ImageIcon("bin/img/���-ȷ��.png");
    ImageIcon iconAdd = new ImageIcon("bin/img/���.png");
    private JButton okbtn = new JButton(iconOk);
    private JButton addbtn = new JButton(iconAdd);
    private JButton exit = new JButton(iconEx);
    /**
     * ��ʼ������
     */
    public FrmAdd2() {
    	
        super();
        setTitle("��������ϵͳ");
        background = new ImageIcon("bin/img/add2.png");
        JLabel back = new JLabel(background);
        back.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        /*
         * ���ȳ�ʼ��һ��ͼƬ�����ǿ���ѡ��һ����͸�����ֵĲ�����ͼƬ
         *  (Ҫ��ͼƬ�ܹ���ʾ͸��������ʹ��PNG��ʽ��ͼƬ)
         */
        MediaTracker mt = new MediaTracker(this);

        try {
            img = ImageIO.read(new File("bin/img/add2.png"));
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
        monLabel.setForeground(Color.black);
        dayLabel.setForeground(Color.BLACK);
        houLabel.setForeground(Color.BLACK);
        minLabel.setForeground(Color.BLACK);
        timLabel.setForeground(Color.BLACK);
        cntLabel.setForeground(Color.BLACK);
        dayLabel.setFont(font);
        houLabel.setFont(font);
        minLabel.setFont(font);
        monLabel.setFont(font);
        timLabel.setFont(font);
        cntLabel.setFont(font);
        
        //passwordText.setEchoChar('*');
        //passwordText2.setEchoChar('*');

        monLabel.setBounds(71, 95, 100, 30);
        dayLabel.setBounds(141, 95, 100, 30);
        houLabel.setBounds(211, 95, 100, 30);
        minLabel.setBounds(281, 95, 100, 30);
        timLabel.setBounds(20, 60, 100, 30);
        cntLabel.setBounds(20, 200, 100, 30);
        monText.setBounds(30, 95, 40, 30);
        dayText.setBounds(100, 95, 40, 30);
        houText.setBounds(170, 95, 40, 30);
        cntText.setBounds(70, 205, 200, 100);
        minText.setBounds(240, 95, 40, 30);
        okbtn.setBorderPainted(false);
        okbtn.setBounds(180, 390, 107, 24);
       // cabtn.setBounds(475, 375, 60, 25);
        addbtn.setBorderPainted(false);
        addbtn.setBounds(100, 18, 107, 24);
        exit.setBorderPainted(false);
        exit.setBounds(280, 0, 34, 33);
        this.add(monLabel);
        this.add(dayLabel);
        this.add(houLabel);
        this.add(minLabel);
        this.add(monText);
        this.add(dayText);
        this.add(houText);
        this.add(cntText);
        this.add(okbtn);
        this.add(addbtn);
        this.add(exit);      
        this.add(timLabel);
        this.add(minText);
        this.add(cntLabel);
        okbtn.addMouseListener(new OwnListener());
       // cabtn.addMouseListener(new OwnListener());
        monText.addKeyListener(new KeyOwnListener());
        dayText.addKeyListener(new KeyOwnListener());
        houText.addKeyListener(new KeyOwnListener());
        minText.addKeyListener(new KeyOwnListener());
        cntText.addKeyListener(new KeyOwnListener());
        addbtn.addMouseListener(new OwnListener());
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
        public void mouseClicked(MouseEvent e){
            //������������λ�����ϽǺ�ɫ��ť����رճ���
        	if(e.getSource() == exit)
        	{
        		dispose();
        	}
        	else if(e.getSource() == okbtn)
        	{
        		String time="2017-"+monText.getText()+"-"+dayText.getText()+" "+houText.getText()+":"+minText.getText();
        		String contect=cntText.getText();
        		EventManager em = new EventManager();
        	     try {
        	    	 int num = em.getaddEventNum(contect, time, "0", "0", "0");
        	    	 if(num>0){
        	    		//���num�����㣬�򵯳�����"��ʱ������num���¼����Ƿ�������"���������ӷ������������
        	    	 }
        	    	 em.addEvent(contect,time, "0", "0", "0");
        	    	 setVisible(false);
        	    	 FrmMain dlg=new FrmMain();
                 	dlg.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
    	   
        new FrmAdd();  
    } 
}


