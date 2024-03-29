import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class SelectMapScreen extends JPanel{
    // Window Game Size
    private final int FRAME_WIDTH = 1366;
    private final int FRAME_HEIGHT = 768;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    // About Font
    private Font selectMapFont, mapBoxFont, BackBtnFont, TextFont;
    
    // Color
    private Color offWhite = new Color(250, 249, 246);
    private Color black = new Color(25, 28, 32);
    private Color []mColor = {new Color(255, 104, 104,255), // 0
                    new Color(203,108,230,255), // 1
                    new Color(255,201,60,255), // 2
                    new Color(56,182,255,255), // 3
                    new Color(126,217,87,255), // 4
                    new Color(255,189,89,255), // 5
                    new Color(139,80,44,255), // 6
                    new Color(0,151,178,255), // 7
                    new Color(0,191,99,255), // 8
                    new Color(255,145,77,255), // 9
                    new Color(52,104,192,255), // 10
                    new Color(166,166,166,255), //11
    };
    
    // Label
    private JLabel selectMap;
    protected JLabel BackBtn;
    // Button but Label
    protected JLabel[] map = new JLabel[12];
    private int hGap = 50;
    private int sizeBtn = 150; 

    // Box Background 
    private ObjectMoving bgBox1 = new ObjectMoving(800,478,200,200);
    private ObjectMoving bgBox2 = new ObjectMoving(148,35,360,360);
    private ObjectMoving bgBox3 = new ObjectMoving(760,-100,310,310);
    private ObjectMoving bgBox4 = new ObjectMoving(1390,309,258,230);
    private ObjectMoving bgBox5 = new ObjectMoving(-100,389,250,250);
    private ObjectMoving bgBox6 = new ObjectMoving(1193,690,280,250);
    
    // Constructor to setup Component and Event handler
    public SelectMapScreen(){
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(offWhite);

        // Select Map Screen Label
        selectMap = new JLabel();
        selectMap.setText("Select Map !!!");
        selectMap.setBounds(500, 90, 360, 104);
        selectMap.setOpaque(false);
        selectMap.setBackground(Color.RED);
        selectMap.setForeground(black);
        selectMapFont = usingFontsBold(selectMapFont, 50f, "font/Shantell_Sans/ShantellSans-Regular.ttf");
        selectMap.setFont(selectMapFont);
        add(selectMap);

        // Button of Maps (But Label)
        for (int i = 0; i < map.length - 6; i++){ // Row 1
            map[i] = new JLabel();
            map[i].setText(String.valueOf(i));
            map[i].setBounds(new Rectangle(110 + i * (hGap + sizeBtn), 230, sizeBtn, sizeBtn));
            map[i].setOpaque(true);
            map[i].setBackground(mColor[i]);
            map[i].setForeground(offWhite);
            map[i].setHorizontalAlignment(SwingConstants.CENTER);
            map[i].setVerticalAlignment(SwingConstants.CENTER);
            mapBoxFont = usingFontsBold(mapBoxFont, 60f, "font/Oswald/Oswald-Medium.ttf");
            map[i].setFont(mapBoxFont);
            // System.out.println("Map : " + map[i].getText());
            add(map[i]);
        }
        for (int i = 6; i < map.length; i++){ // Top Section
            map[i] = new JLabel();
            map[i].setText(String.valueOf(i));
            map[i].setBounds(new Rectangle(110 + (i - 6) * (hGap + sizeBtn), 430 , sizeBtn, sizeBtn));
            map[i].setOpaque(true);
            map[i].setBackground(mColor[i]);
            map[i].setForeground(offWhite);
            map[i].setHorizontalAlignment(SwingConstants.CENTER);
            map[i].setVerticalAlignment(SwingConstants.CENTER);
            mapBoxFont = usingFontsBold(mapBoxFont, 60f, "font/Oswald/Oswald-Medium.ttf");
            map[i].setFont(mapBoxFont);
            // System.out.println("Map : " + map[i].getText());
            add(map[i]);
        }

        // BackButton (But use Label)
        BackBtn = new JLabel();
        BackBtn.setText("BACK");
        BackBtn.setBounds(606, 640, 150, 50);
        BackBtn.setOpaque(true);
        BackBtn.setBackground(new Color(166, 166, 166));
        BackBtn.setForeground(black);
        BackBtn.setHorizontalAlignment(SwingConstants.CENTER);
        BackBtn.setVerticalAlignment(SwingConstants.CENTER);
        BackBtnFont = usingFontsBold(BackBtnFont, 25f, "font/Shantell_Sans/ShantellSans-Regular.ttf");
        BackBtn.setFont(BackBtnFont);
        add(BackBtn);

        // Event-Hander
        addMouseMotionListener(new Mouse());
        addMouseMotionListener(new OpacityMouseAction());

        ActionListener updateTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgBox1.moveLeft(1400, -550, 2);
                bgBox2.moveLeft(1600, -800, 1);
                bgBox3.moveLeft(1550, -1000, 3);
                bgBox4.moveLeft(1750, -1000, 1);
                bgBox5.moveLeft(2000, -1000, 2);
                bgBox6.moveLeft(1400, -300, 2);
                repaint();
            }
        };
        new Timer(10, updateTask).start();
    }
    // Main PaintComponents Method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); // for smoothly 
        g2d.setFont(BackBtnFont);
        paintBackgroundBox(g2d);
        
        // This is tutorial text
        AffineTransform originalTransform = g2d.getTransform(); 
        AffineTransform tfText = new AffineTransform();
        tfText.rotate(Math.toRadians(-20)); 
        g2d.setTransform(tfText);
        TextFont = usingFontsBold(TextFont, 35f, "font/Shantell_Sans/ShantellSans-Regular.ttf");
        g2d.setFont(TextFont);
        g2d.setColor(new Color(166, 166, 166));
        g2d.drawString("Map 0's the Tutorial", -30, 220); 
        g2d.setTransform(originalTransform);
        
    }

    public void paintBackgroundBox(Graphics g){
        g.setColor(new Color(255,175,126,255)); // orange
        g.fillRect(bgBox4.object.x, bgBox4.object.y, bgBox4.object.width, bgBox4.object.height);
        
        g.setColor(new Color(255, 104, 104,255).brighter()); // red
        g.fillRect(bgBox1.object.x, bgBox1.object.y, bgBox1.object.width, bgBox1.object.height);
        
        g.setColor(new Color(177,255,143,255)); // green
        g.fillRect(bgBox2.object.x, bgBox2.object.y, bgBox2.object.width, bgBox2.object.height);
        
        g.setColor(new Color(222,158,228,255)); // purple
        g.fillRect(bgBox5.object.x, bgBox5.object.y, bgBox5.object.width, bgBox5.object.height);
        
        g.setColor(new Color(190,245,255,255)); // blue
        g.fillRect(bgBox3.object.x, bgBox3.object.y, bgBox3.object.width, bgBox3.object.height);

        g.setColor(new Color(50,60,50,255)); // black
        g.fillRect(bgBox6.object.x, bgBox6.object.y, bgBox6.object.width, bgBox6.object.height);

    }
    
    // Using Fonts Method Section
    public Font usingFonts(Font fontName,  float sizeFont, String fontFilePath_ttf){
        try {
            fontName = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath_ttf)).deriveFont(sizeFont);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath_ttf)));
            return fontName;
        } catch (IOException|FontFormatException e){
            // Handle Exception
            System.out.println("Font Problems Please Check");
            return null;
        }
    }
    public Font usingFontsBold(Font fontName,  float sizeFont, String fontFilePath_ttf){
        try {
            fontName = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath_ttf)).deriveFont(Font.BOLD,sizeFont);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath_ttf)));
            return fontName;
        } catch (IOException|FontFormatException e){
            // Handle Exception
            System.out.println("Font Problems Please Check");
            return null;
        }
    }

    // Inner Class for Event Handler Section
    private class Mouse extends MouseAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
        // Check mouse's position
        // System.out.println("mouseX:" + e.getX() + ", mouseY:" + e.getY());
        }
    }

    private class OpacityMouseAction extends MouseMotionAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            // Hold over on Map
            for (int i = 0; i < map.length; i++) {
                if (map[i].getBounds().contains(e.getPoint())) {
                    map[i].setBackground(mColor[i].darker());
                } else {
                    map[i].setBackground(mColor[i]);
                }
            }

            // Hold over on Back Label
            if (BackBtn.getBounds().contains(e.getPoint())){
                BackBtn.setBackground(new Color(166,166,166).darker());
            }else{
                BackBtn.setBackground(new Color(166,166,166));
            }
        }
    }
}