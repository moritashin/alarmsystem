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


import javafx.scene.shape.Circle;

/**
 * �û���¼����
 * @author Admin
 *
 */
public class FrmMain extends JFrame {

    private static final long serialVersionUID = 1L;
    private Point origin; // �����ƶ�����
    private BufferedImage img; // �����趨���岻������ʽ��ͼƬ

    private ImageIcon background;
    ImageIcon iconUnf = new ImageIcon("bin/img/δ�������.png");
    ImageIcon iconEx = new ImageIcon("bin/img/��.png");
    ImageIcon iconCof = new ImageIcon("bin/img/���������.png");
    ImageIcon iconReu = new ImageIcon("bin/img/����վ.png");
    ImageIcon iconSet = new ImageIcon("bin/img/����.png");
    ImageIcon iconHep = new ImageIcon("bin/img/����.png");
    ImageIcon iconOut = new ImageIcon("bin/img/ע��.png");
    ImageIcon iconCha = new ImageIcon("bin/img/�л��û�.png");
    ImageIcon iconAdd = new ImageIcon("bin/img/ͼƬ5.png");
    ImageIcon iconAdd2 = new ImageIcon("bin/img/ͼƬ5.png");
    private JButton unfbtn = new JButton(iconUnf);
    private JButton exit = new JButton(iconEx); 
    private JButton cofbtn = new JButton(iconCof);
    private JButton reubtn = new JButton(iconReu);
    private JButton settbtn = new JButton(iconSet);
    private JButton heptbtn = new JButton(iconHep);
    private JButton outtbtn = new JButton(iconOut);
    private JButton chatbtn = new JButton(iconCha);
    private JButton addtbtn = new JButton(iconAdd);
    private JButton add2btn = new JButton(iconAdd2);
    /**
     * ��ʼ������
     */
    public FrmMain() {
        super();
        setTitle("��������ϵͳ");
        background = new ImageIcon("bin/img/main.png");
        JLabel back = new JLabel(background);
        back.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        /*
         * ���ȳ�ʼ��һ��ͼƬ�����ǿ���ѡ��һ����͸�����ֵĲ�����ͼƬ
         *  (Ҫ��ͼƬ�ܹ���ʾ͸��������ʹ��PNG��ʽ��ͼƬ)
         */
        MediaTracker mt = new MediaTracker(this);

        try {
            img = ImageIO.read(new File("bin/img/main.png"));
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
        
        

        unfbtn.setBorderPainted(false);
        exit.setBorderPainted(false);
        cofbtn.setBorderPainted(false);
        reubtn.setBorderPainted(false);
        settbtn.setBorderPainted(false);
        heptbtn.setBorderPainted(false);
        outtbtn.setBorderPainted(false);
        chatbtn.setBorderPainted(false);
        addtbtn.setBorderPainted(false);
        add2btn.setBorderPainted(false);
        unfbtn.setBounds(405, 77, 77, 27);
        cofbtn.setBounds(495, 77, 77, 27);
        reubtn.setBounds(585, 77, 62, 27);
        settbtn.setBounds(660, 77, 77, 27);
        heptbtn.setBounds(750, 77, 77, 27);
        outtbtn.setBounds(840, 77, 77, 27);
        chatbtn.setBounds(930, 77, 77, 27);
        addtbtn.setBounds(285, 145, 39, 42);
        add2btn.setBounds(805, 145, 39, 42);
        exit.setBounds(986, 0, 34, 33);
        
        this.add(unfbtn);
       // this.add(cabtn);
        this.add(cofbtn);
        this.add(reubtn);
        this.add(settbtn);
        this.add(heptbtn);
        this.add(outtbtn);
        this.add(chatbtn);
        this.add(exit);
        this.add(add2btn);
        this.add(addtbtn);
        
        unfbtn.addMouseListener(new OwnListener());
       // cabtn.addMouseListener(new OwnListener());
        cofbtn.addMouseListener(new OwnListener());
        reubtn.addMouseListener(new OwnListener());
        settbtn.addMouseListener(new OwnListener());
        heptbtn.addMouseListener(new OwnListener());
        outtbtn.addMouseListener(new OwnListener());
        chatbtn.addMouseListener(new OwnListener());
        exit.addMouseListener(new OwnListener());
        add2btn.addMouseListener(new OwnListener());
        addtbtn.addMouseListener(new OwnListener());
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
            } else if(e.getSource() == unfbtn)
            {
            	
            }else if(e.getSource() == unfbtn)
            {
            	
            }else if(e.getSource() == cofbtn)
            {
            	
            }
            else if(e.getSource()==cofbtn)
            {
            	
            }
            else if(e.getSource() == addtbtn)
            {
            	FrmAdd dlg=new FrmAdd();
            	dlg.setVisible(true);
            }
            else if(e.getSource() == add2btn)
            {
            	FrmAdd2 dlg=new FrmAdd2();
            	dlg.setVisible(true);
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
    	   
        new FrmMain();  
    } 
}
