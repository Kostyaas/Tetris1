import utils.Constants;
import utils.DificultLevel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class Main extends JFrame{
    JFrame frame = new JFrame();
    int st = 1;
    int difficalt = DificultLevel.EASY.getLevel();
    int difficaltR = DificultLevel.EASY.getLevel();
    int  scoreR = 0;
    transient Music music = new Music();
    int score = 0;

    boolean AlwaysTrue = true;
    int i = 0;
    Color color = Color.BLACK;
    Random random = new Random();
    int num;

    /////////////////
    Figuer figuer = new Figuer(color);
    Canvas canvas = new Canvas(figuer);
    JButton btn = new JButton("Перезапустить");
    JButton btn2 = new JButton("Выход");
    JButton btn3 = new JButton("Старт");
    JButton btnStop = new JButton("Стоп");

    JTextField text = new JTextField("Введите снизу сложность");
    JPanel  panel = new JPanel();
    private boolean Over = false;
    boolean Start = false;
    //////////////////////////////////////
    int FrameX = Constants.WORK_SPACE_WIGHT;
    int FrameY = Constants.WORK_SPACE_HEIGHT;
    //////////////////////////////////////


    public static void main(String[] args) {

        new Main().go();

    }
    public class Restart implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Over = false;
            music.PlayMusic("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\music\\buttonklick.wav");
            figuer.Clear();
            score = 0;
            color = RandomColor();
            figuer.RandomFig(num, color);
            color = RandomColor();
            num = random.nextInt(7);
            figuer.ShowRightpanel(num, color);
        }
    }
    public class stop implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            music.PlayMusic("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\music\\buttonklick.wav");
            if(i == 0)
            {
                btnStop.setText("Продолжить");
                st = 0;
                i = 1;
            }
            else {
                btnStop.setText("Стоп");
                st = 1;
                i = 0;
            }
        }
    }

    public class Start implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            figuer.Start();
            Start = true;
        }
    }

    public class Exit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            music.PlayMusic("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\music\\exit.wav");
            System.exit(0);

        }
    }
    class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent a) {
            if (!Over) {
                if (a.getKeyCode() ==  KeyEvent.VK_A)   figuer.SideMoveL(st);
                if (a.getKeyCode() ==  KeyEvent.VK_D)   figuer.SideMoveR(st);
                if (a.getKeyCode() ==  KeyEvent.VK_S)   figuer.move(st);
                if(a.getKeyCode() == KeyEvent.VK_E)     figuer.rotate(Figuer.RotateType.LEFT,  st);
                if(a.getKeyCode() == KeyEvent.VK_Q)     figuer.rotate(Figuer.RotateType.RIGHT, st);
            }
            canvas.repaint();
        }
    }
    class ADDcomponent extends ComponentAdapter
    {
        @Override
        public void componentResized(ComponentEvent evt) {
            Component c = (Component) evt.getSource();
            figuer.ScreenExtension(c.getWidth(), c.getHeight());
        }
    }

    Main() //окно
    {
        GridLayout layout = new GridLayout(6, 1, 0, 0);
        num = random.nextInt(6);
        frame.setTitle("Tetris");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(800, 400, FrameX , FrameY );
        panel.setLayout(layout);
        text.setEnabled(false);
        JButton button = new JButton("Сложность");

        final JPopupMenu popup = new JPopupMenu();
        popup.add(new JMenuItem(new AbstractAction("EASY") {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.PlayMusic("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\music\\buttonklick.wav");
                difficalt = DificultLevel.EASY.getLevel();
                frame.setTitle("Tetris - " +DificultLevel.StringLevel(difficalt));
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("MEDIUM") {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.PlayMusic("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\music\\buttonklick.wav");
                difficalt = DificultLevel.MEDIUM.getLevel();
                frame.setTitle("Tetris - " +DificultLevel.StringLevel(difficalt));
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("HARD!!!") {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.PlayMusic("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\music\\buttonklick.wav");
                difficalt = DificultLevel.HARD.getLevel();
                frame.setTitle("Tetris - " +DificultLevel.StringLevel(difficalt));
            }
        }));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                music.PlayMusic("C:\\Users\\kosty\\Idegit initaProjects\\Tetris1.2\\music\\buttonklick.wav");
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        btn3.addKeyListener(new KeyListener());
        btn2.addKeyListener(new KeyListener());
        btn.addKeyListener(new KeyListener());
        btnStop.addKeyListener(new KeyListener());
        panel.add(btn3);
        panel.add(btn);
        panel.add(button);
        panel.add(btn2);
        panel.add(btnStop);
        btn.addActionListener(new Restart());
        btn.addActionListener(new Restart());
        btn2.addActionListener(new Exit());
        btnStop.addActionListener(new stop());
        music.PlayMusic("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\musictetris.mp3");
        frame.add(BorderLayout.WEST, panel);
        frame.add(BorderLayout.CENTER, canvas);
        canvas.addComponentListener(new ADDcomponent());
        frame.setMinimumSize(new Dimension(300, 400));
        frame.setVisible(true);
    }

    void go()//правила игры
    {
        ActionListener start = new Start();
        int temp = 1;
        num = random.nextInt(7);
        color = RandomColor();
        figuer.RandomFig(num, color);
        num = random.nextInt(7);
        color = RandomColor();
        figuer.ShowRightpanel(num, color);
        while (AlwaysTrue) {
            btn3.addActionListener(start);
            if (!Over && Start) {
                Over = figuer.GameOver();
                score += figuer.StripLines();

                try {
                    Thread.sleep(difficalt);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                DownClear();
                temp = 1;
            }
            if(temp == 1 && Over)
            {
                    music.PlayMusic("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\music\\gameover.wav");
                    MAthScore();
                    temp = 0;
                    btn.addActionListener(new Restart());
            }
        }
    }
    private void DownClear()
    {
        float r,g,b;
        if (figuer.DownClear()) {
            figuer.move(st);
            canvas.repaint();
        }
        else {
            music.PlayMusic("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\music\\udarkybika.wav");
            figuer.RandomFig(num, color);
            num = random.nextInt(7);
            color = RandomColor();
            figuer.ShowRightpanel(num, color);
            canvas.repaint();
        }
    }

    private void MAthScore()
    {
        if(scoreR <= score) {
            {
                scoreR = score;
                difficaltR = difficalt;
            }
        }
        JOptionPane.showMessageDialog(null,
                "Ваш результат:Сложность: " + DificultLevel.StringLevel(difficalt) + " счет: " + score + '\n' + "Рекорд: Сложность: " + DificultLevel.StringLevel(difficaltR) + " счет: " + scoreR,
                "Результаты",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private Color RandomColor()
    {
        float r,g,b;
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();
        color = new Color(r,g,b);
        while (color == Color.orange)
        {
            r = random.nextFloat();
            g = random.nextFloat();
            b = random.nextFloat();
            color = new Color(r,g,b);
        }
        return color;
    }
}
class Canvas extends JPanel { //картинка

    Figuer figure;

    Canvas(Figuer figure1)
    {
        figure = figure1;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        figure.paint(g);
    }
}


