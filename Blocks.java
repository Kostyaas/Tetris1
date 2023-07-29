import utils.Constants;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Blocks  implements Serializable {

    private int BlocksX, BlocksY;
    int SizeBlockx;
    int SizeBlocky;
    Color color;
    public Blocks(int x, int y, Color color, int SizeBlockx, int SizeBlocky) {
        this.BlocksX = x;
        this.BlocksY = y;
        this.color = color;
        this.SizeBlockx =  SizeBlockx;
        this.SizeBlocky = SizeBlocky;
    }
    void paint(Graphics g) {
        g.setColor(color);
        g.fillRoundRect(BlocksX, BlocksY, SizeBlockx,SizeBlocky,4,4);
    }
}



class Figuer extends JPanel {
    Color color2;
    Random random = new Random();
    int[][] arr = new int[13][20];
    Color[][] arrColor = new Color[13][20];
     Blocks block;

    int SizeBlockx = Constants.Block_size;
    int SizeBlocky = Constants.Block_size;
    protected int FiguerX = Constants.Block_size;
    protected int FiguerY =  0;
    protected Color color = Color.BLACK;
    boolean Over = false;
    boolean Start = false;
    int[][] XY = new int[4][4];
    int[][] XY2 = new int[4][4];
    private int oldx, oldy;

    protected Figuer(Color color) {
        this.color = color;
    }



    enum RotateType { // для задания типа поворота - влево или вправо
        LEFT,
        RIGHT
    }

    // метод поворачивающий матрицу
    public static int[][] rotateMatrix(int[][] matrix, RotateType type) {
        var rows = matrix[0].length;
        var columns = matrix.length;
        var result = new int[rows][columns];
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < columns; j++) {
                result[i][j] = (type == RotateType.LEFT) ?
                        matrix[j][rows - 1 - i] : matrix[columns - 1 - j][i];
            }
        }
        return result;
    }

    public void addL() {
        int num = random.nextInt(0, 11);
        FiguerX = num * SizeBlockx;
        XY = new int[][]{
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        FiguerY = 0;

    }

    public void addW() {
        int num = random.nextInt(0, 11);
        FiguerX = num * SizeBlockx;
        XY = new int[][]{
                {0, 0,0, 0},
                {0, 0, 1, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };
        FiguerY = 0;
    }

    public void add1() {
        int num = random.nextInt(0, 10);
        FiguerX = num * SizeBlockx;
        XY = new int[][]{
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}
        };
        FiguerY = 0;
    }

    public void addF() {
        int num = random.nextInt(0, 11);
        XY = new int[][]{
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        FiguerX = num * SizeBlockx;
        FiguerY = 0;
    }
    public void addS() {
        int num = random.nextInt(0, 11);
        FiguerX = num * SizeBlockx;
        XY = new int[][]{
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0}
        };
        FiguerY = 0;
    }
    public void addS2() {
        int num = random.nextInt(0, 11);
        FiguerX =  num * SizeBlockx;
        XY = new int[][]{
                {0, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}
        };
        FiguerY = 0;
    }
    public void addSq() {
        int num = random.nextInt(0, 11);
        FiguerX = num * SizeBlockx;
        XY = new int[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        FiguerY = 0;
    }

    public void RandomFig(int a, Color color) {
        if (a == 0)
            addW();
        if (a == 1)
            addS();
        if (a == 2)
            addL();
        if(a == 3)
            addSq();
        if(a == 4)
            addS2();
        if(a == 5)
            addF();
        if(a == 6)
            add1();
        this.color = color;
    }

    public void  ShowRightpanel(int a, Color colorS)
    {
        if (a == 0)
            XY2 = new int[][]{
                    {0, 0,0, 0},
                    {0, 0, 1, 0},
                    {0, 1, 1, 1},
                    {0, 0, 0, 0}
            };
        if (a == 1)
            XY2 = new int[][]{
                    {0, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 0, 0}
            };
        if (a == 2)
            XY2 = new int[][]{
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0}
            };
        if(a == 3)
            XY2 = new int[][]{
                    {0, 0, 0, 0},
                    {0, 1, 1, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0}
            };
        if(a == 4)
            XY2 = new int[][]{
                    {0, 0, 1, 0},
                    {0, 1, 1, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
            };
        if(a == 5)
            XY2 = new int[][]{
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0}
            };
        if(a == 6)
        {
            XY2 = new int[][]{
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0}
            };
        }
        color2 = colorS;
    }

    public void rotate(RotateType type, int st) {
        if (RotateTrue(XY, FiguerX, FiguerY, arr, type, SizeBlockx, SizeBlocky) && st == 1)
            XY = rotateMatrix(XY, type);
    }

    public boolean GameOver()
    {
        for(int i = 0; i < 13; i ++) {
            if (arr[i][1] == 1) {
                Over = true;
                return true;
            }
        }
        Over = false;
        return false;
    }
    static public boolean RotateTrue(int[][] XY, int x, int y, int[][] arr, RotateType type, int SizeBlockx, int SizeBlocky) {
        XY = rotateMatrix(XY, type);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (XY[i][j] == 1 && arr[x / SizeBlockx + i][y / SizeBlocky + j] == 1)
                    return false;
            }
        }
        return true;
    }

    public void move(int a1) {
        if (DownClear() && a1 == 1) {
            FiguerY += SizeBlocky;
        }
    }

    public void SideMoveR(int st) {
        if (!HaveFigRight(arr, FiguerX, FiguerY, XY, SizeBlockx, SizeBlocky) && st == 1)
            FiguerX = FiguerX + SizeBlockx;
    }

    public void SideMoveL(int st) {

        if (!HaveFigLeft(arr, FiguerX, FiguerY, XY, SizeBlockx,  SizeBlocky) && st == 1)
            FiguerX = FiguerX - SizeBlockx;
    }


    static public boolean HaveFigLeft(int[][] arr, int x, int y, int[][] XY, int SizeBlockx, int SizeBlocky) {
        int temp = 0;
        int minx = 0, miny = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (XY[i][j] == 1  && arr[x / SizeBlockx - 1 + i][y / SizeBlocky + j] == 1) {
                    return true;
                }
        return false;
    }

    static public boolean HaveFigRight(int[][] arr, int x, int y, int[][] XY, int SizeBlockx, int SizeBlocky) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (XY[i][j] == 1 && arr[x / SizeBlockx + i + 1][y / SizeBlocky + j] == 1)
                    return true;

        return false;
    }

    public void ScreenExtension(int x1, int y1) {
        oldx = FiguerX / SizeBlockx;
        oldy = FiguerY / SizeBlocky;

        SizeBlockx = x1 / Constants.WIGHT;
        SizeBlocky = y1 / Constants.HEIGHT;
        FiguerX = oldx * SizeBlockx;
        FiguerY = oldy * SizeBlocky;

    }
    static public boolean HaveFigDown(int[][] arr, int x, int y, int[][] XY, int SizeBlockx, int SizeBlocky) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (XY[i][j] == 1 && arr[x / SizeBlockx + i][y / SizeBlocky + j + 1] == 1)
                    return true;


        return false;
    }

    public boolean DownClear() {
        if (HaveFigDown(arr, FiguerX, FiguerY, XY, SizeBlockx, SizeBlocky)) {
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    if (XY[i][j] == 1) {
                        arr[FiguerX / SizeBlockx + i][FiguerY / SizeBlocky + j] = 1;
                        arrColor[FiguerX / SizeBlockx + i][FiguerY / SizeBlocky + j] = color;
                    }
            return false;
        }
        return true;
    }


    public boolean FillLines(int y1) {
        for (int i = 0; i < 13; i++) {
            if (arr[i][y1] == 0)
                return false;
        }
        return true;
    }

    public int StripLines(){
        Music music = new Music();
        int sum = 0;
        for (int j = 18; j > 0; j--) {
            if (FillLines(j)) {
                sum++;
                DropLine(j);
                music.PlayMusic("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\music\\striplines.wav");
            }
        }
        return sum;
    }

    private void DropLine(int y){
        for(int i = 0; i < 13; i++)
            arr[i][y] = 0;
        for(int my = y; my > 1; my--)
            for(int mx = 0; mx < 13; mx++) {
                arr[mx][my] = arr[mx][my - 1];
                arrColor[mx][my] = arrColor[mx][my - 1];
            }
    }
    public void Clear()
    {
        for (int i = 0; i < 13; i++)
            for (int j = 0; j < 19; j++)
                arr[i][j] = 0;
        int num = random.nextInt(3, 9);
        FiguerX = num * SizeBlockx;
        FiguerY = 0;
    }
    public void Start()
    {
        Start =  true;
    }
    @Override
    public void paint(Graphics g) {
        if(Start) {
            if (!Over) {
                DroveArr(g);
                DroveFigure(g);
                /////
                g.setColor(Color.RED); g.drawLine(0, SizeBlocky, 13 * SizeBlockx, SizeBlocky);
            }
            else {
                Image img = new ImageIcon("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\page\\game-over.jpg").getImage();
                g.drawImage(img, 0, 0, 20 * SizeBlockx, 20 * SizeBlocky, null);
            }

        }
        else {
            Image img2 = new ImageIcon("C:\\Users\\kosty\\IdeaProjects\\Tetris1.2\\page\\TetrisMenu.png").getImage();
            g.drawImage(img2, 0, 0, 20 * SizeBlockx, 20 * SizeBlocky, null);
        }
    }

    private void DroveFigure(Graphics g)
    {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                if (XY[i][j] == 1) {
                    block = new Blocks(FiguerX + i * SizeBlockx, FiguerY + j * SizeBlocky, color, SizeBlockx, SizeBlocky);
                    block.paint(g);
                }
                if (XY2[i][j] == 1) {
                    block = new Blocks(SizeBlockx * 14 + i * SizeBlockx, j * SizeBlocky, color2, SizeBlockx, SizeBlocky);
                    block.paint(g);
                }
            }
    }
    private void DroveArr(Graphics g)
    {
        for (int i = 0; i < 13; i++) {
            arr[i][19] = 1;
            for (int j = 0; j < 20; j++) {
                if (arr[i][j] == 1 && j != 19) {
                    block = new Blocks(i * SizeBlockx, j * SizeBlocky, arrColor[i][j], SizeBlockx, SizeBlocky);
                    block.paint(g);
                }
                else if (arr[i][j] == 0 && j != 19) {
                    block = new Blocks(i * SizeBlockx, j * SizeBlocky, Color.orange, SizeBlockx, SizeBlocky);
                    block.paint(g);
                }
                else if (j == 19) {
                    block = new Blocks(i * SizeBlockx, j * SizeBlocky, Color.YELLOW, SizeBlockx, SizeBlocky);
                    block.paint(g);
                }
            }
        }
    }
}

