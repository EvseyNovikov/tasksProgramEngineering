package model;

import java.util.Random;

public class ActionChain {
    Handler chain;
    Player player;

    public static int SUCCESS = 1;
    public static int LOSS = 3;

    Random generate;
    final int NUMHANDLER = 3;
    final int NUMMAX = 7;


    public ActionChain(Player player) {
        this.player = player;
        generate = new Random();
        buildChain();
    }

    private void buildChain() {
        chain = new NegativeHandler(new PositiveHandler(null, player), player);
    }

    public boolean process() {
        int type = generate.nextInt(NUMHANDLER);//розыгрыш
        return process(type);
    }


    public boolean process(Integer a) {
        return chain.process(1 + a % NUMHANDLER);//обрезка по числу имеющихся обработчиков
    }
}