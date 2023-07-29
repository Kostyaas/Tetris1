package utils;

public enum DificultLevel {

    EASY(400),
    MEDIUM(250),
    HARD(150);

    DificultLevel(int level){
        this.level=level;
    }

    private int level;

    public int getLevel(){
        return  level;
    }
    public static String  StringLevel(int level)
    {
        if(level == EASY.getLevel())
            return "EASY";
        if(level == MEDIUM.getLevel())
            return "MEDIUM";
        else
            return "HARD";
    }



}
