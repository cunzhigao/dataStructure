public class Change_State {
    /*
    设立了6种状态
    0： 为初试状态
    1： 为输入为+-变成的状态
    2： 输入数字0-9变成的状态
    3： 输入dot变成的状态
    4： 输入0-9变成的状态
    5： 输入e变成的状态
    6： 输入+/-变为的状态

     */

    public void outputFlag(int[] flag) { //输出状态机的转化

        for (int x : flag) {
            System.out.println(x);
        }
    }

    public int SearchIsLarge(int[] flag) {   //判断e前面的数字是否大于10
        int t = 0;
        for (int i = 0; flag[i] != 3; i++) {
            if (flag[i] == 2) {
                t++;
            }
        }
        return t;

    }

    public int SearchState2(int[] flag) {    //判断当前的状态是否为2
        int t = 0;
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == 2) {
                t++;
            }
        }
        return t;

    }


    public boolean anlyse(String s) {
        //转化为字符数组
        char[] cs = s.toCharArray();
        //此为标志位
        int flag = 0;
        //状态记录表
        int[] State = new int[cs.length + 1];

        while (flag < cs.length) {

            State[0] = 0;

            switch (cs[flag]) {

                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    if (State[flag] == 4 || State[flag] == 6) {
                        State[flag + 1] = 5;
                    }
                    else if (State[flag] == 5) {
                        State[flag + 1] = 5;
                    }
                    else if (State[flag] == 3) {
                        State[flag + 1] = 3;
                    }
                    else if (State[flag] == 2) {
                        State[flag + 1] = 2;
                    }
                    else if (State[flag] == 0 || State[flag] == 1) {
                        State[flag + 1] = 2;
                    }
                    else {
                        outputFlag(State);
                        return false;
                    }
                    break;


                case '0':
                    if (State[flag] == 5 || State[flag] == 3) {
                        State[flag + 1] = State[flag];
                    }
                    else if (State[flag] == 0) {
                        State[flag + 1] = 2;
                    }
                    else if (State[flag] == 2) {
                        if (cs[flag - 1] == '0' && SearchState2(State) == 1) {    //检查是否为两个0，并且第一个0出现在除了+-的开头
                            outputFlag(State);
                            return false;
                        } else State[flag + 1] = 2;
                    }
                    else if (State[flag] == 1) {
                        State[flag + 1] = 2;
                    }
                    else {
                        outputFlag(State);
                        return false;
                    }

                    break;


                case '+':
                case '-':
                    if (State[flag] == 0) {
                        State[flag + 1] = 1;
                    }
                    else if (State[flag] == 4) {
                        State[flag + 1] = 6;
                    }
                    else {
                        outputFlag(State);
                        return false;
                    }
                    break;


                case '.':
                    if (State[flag] == 2) State[flag + 1] = 3;
                    else {
                        outputFlag(State);
                        return false;
                    }
                    break;


                case 'e':
                    if (State[flag] == 3) {
                        if (SearchIsLarge(State) < 2) {//e前面的数字只有1位
                            State[flag + 1] = 4;
                        } else {
                            outputFlag(State);
                            return false;
                        }
                    }
                    else if (State[flag] == 2 && flag == 1) {
                        State[flag + 1] = 4;
                    }
                    else {
                        outputFlag(State);
                        return false;
                    }
                    break;


                default:
                    outputFlag(State);
                    return false;

            }
            flag++;

        }
        outputFlag(State);
        return true;

    }

    public static void main(String[] args){
        Change_State cs = new Change_State();
        System.out.println(cs.anlyse("132.658"));;
    }


}