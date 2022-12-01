package cs.edu.nju;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*account类，分别包含5个属性，卡号、姓名、密码、余额、取现额度*/
class account{
    private String cardID;
    private String userName;
    private String code;
    private double money;
    private double thresh;

    account(){

    }

    account(String cardId, String userName, String code, double money, double thresh){
        this.cardID = cardId;
        this.userName = userName;
        this.code = code;
        this.money = money;
        this.thresh = thresh;

    }
    public void setCode(String code){
        this.code = code;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setMoney(double money){
        this.money = money;
    }

    public void setThresh(double thresh){
        this.thresh = thresh;
    }



    public String getCardID() {
        return cardID;
    }

    public String getUserName(){
        return userName;
    }

    public String getCode(){
        return code;
    }

    public double getMoney(){
        return money;
    }

    public double getThresh(){
        return thresh;
    }

    public ArrayList getUserInf(){
        ArrayList userInf = new ArrayList(Arrays.asList(this.cardID,this.userName,this.money, this.thresh));
        System.out.println("您的账户信息如下");
        System.out.println("卡号："+this.cardID);
        System.out.println("姓名："+this.userName);
        System.out.println("余额："+this.money);
        System.out.println(("取现额度："+this.thresh));
        return userInf;
    }

}

public class ATM {

    private ArrayList<account> userInf = new ArrayList<account>();

    private String administerID = "a11123";
    private String administerCode = "a11123";

     ATM(){
        account chen = new account("622583000001", "陈", "123456", 100000000, 999999999);
        account gao = new account("622583000002", "高", "000000", 3123, 45645);
        account liu = new account("622583000003", "刘", "111111", 2321, 12345);
        account liang = new account("622583000004", "梁", "222222", 8978, 32135);
        this.userInf.add(chen);
        this.userInf.add(gao);
        this.userInf.add(liu);
        this.userInf.add(liang);

    }

    public void setAdministerID(String ID){
         this.administerID = ID;
    }
    public void setAdministerCode(String code){
         this.administerCode = code;
    }

    public void administerFunc(){
         System.out.println("==============欢迎来到管理员界面==============");
        System.out.println("0.查看当前所有用户");
        System.out.println("1.修改管理员账号ID");
        System.out.println("2.修改管理员账号密码");
        System.out.println("3.退出管理员系统");
        System.out.print("请输入命令0、1、2、3选择对用的操作：");

        Scanner sr = new Scanner(System.in);
        while (true) {
            int input = sr.nextInt();
            switch (input) {
                case 0:
                    for(int i=0 ; i<this.userInf.size() ; ++i){
                        System.out.println(this.userInf.get(i).getCardID());
                        System.out.println(this.userInf.get(i).getUserName());
                        System.out.println(this.userInf.get(i).getMoney());
                        System.out.println(this.userInf.get(i).getThresh());
                        System.out.println("=============================");
                    }
                    System.out.println("输入任意键回到管理员界面");
                    String input1 = sr.next();
                    this.administerFunc();
                    return;

                case 1:
                    System.out.print("请输入新的管理员ID：");
                    this.setAdministerID(sr.next());
                    System.out.println("新的管理员ID设置成功");
                    System.out.println("输入任意键回到管理员界面");
                    String input2 = sr.next();
                    this.administerFunc();
                    return;

                case 2:
                    System.out.print("请输入新的管理员密码：");
                    this.setAdministerCode(sr.next());
                    System.out.print("新密码设置成功！");
                    System.out.println("输入任意键回到管理员界面");
                    String input3 = sr.next();
                    this.administerFunc();
                    return;

            }
            if(input == 3){
                return;
            }
            else if(input >3){
                System.out.println("请输入有效的数字");
            }
        }

    }


    public void administerLogin(){
         Scanner sr = new Scanner(System.in);
         System.out.println("===========欢迎来到管理员界面，请输入您的管理员账号和密码===========");
         while(true){
             System.out.print("请输入用户名：");
             String ID= sr.next();

             System.out.print("请输入密码：");
             String code = sr.next();

             if(ID.equals(this.administerID) && code.equals(this.administerCode)){
                 System.out.println("管理员登陆成功");
                 this.administerFunc();
                 return;
             }
             else{
                 System.out.println("用户名或密码输入错误");
                 while(true){
                     System.out.print("是否要回到主界面，是/yes，否/no:");
                     String input = sr.next();
                     if(input.equals("yes")){
                         this.begin();
                         return ;
                     }
                     else if(input.equals("no")){
                         break;
                     }
                     else{
                         System.out.println("请输入yes/no:");
                     }

                 }

             }
         }



    }

    public void register(){
        Scanner sr = new Scanner(System.in);
        account user = new account();
        System.out.println("=========欢迎来到注册页面==========");


        System.out.println("请输入您的姓名：");
        user.setUserName(sr.next());

        System.out.println("请输入密码：");
        String input = sr.next();

        while(true){
            System.out.println("请再次输入密码");
            String input1 = sr.next();
            if(!input.equals(input1)){
                System.out.println("两次密码不一致，请输入与上面一致的密码");
            }
            else{
                user.setCode(input1);
                break;
            }
        }

        //自动生成12位卡号，前6位相同
        Random r = new Random();
        String cardID = "622583";
        while(true){

            int j=0;
            for(int i=0 ; i<6 ; ++i){
                cardID+=(char)(r.nextInt(10)+48);
            }

            for(j=0 ; j<this.userInf.size() ; ++j){
                if(cardID.equals(userInf.get(j).getCardID())){
                    cardID = "622583";
                    break;
                }
            }
            if(j == this.userInf.size()){
                break;
            }
        }
        user.setCardID(cardID);

        System.out.println("您的卡号是"+cardID);
        System.out.println("请按1继续操作");
        while(true){
            if(sr.nextInt() == 1){
                break;
            }
        }

        user.setMoney(0);
        user.setThresh(10000);
        this.userInf.add(user);

        System.out.println("注册成功！将跳转回首页面");
        this.begin();

    }

    public void begin() {
        System.out.println("==================欢迎进入小陈银行==================");
        System.out.println("0.管理员界面");
        System.out.println("1.登录账户");
        System.out.println("2.注册开户");
        System.out.println("3.退出系统");
        System.out.print("请输入命令1、2、3选择对用的操作：");
        Scanner sr = new Scanner(System.in);
        int input = sr.nextInt();
        switch (input) {
            case 0:
                this.administerLogin();
                break;
            case 1:
                this.logIn(this.userInf);
                break;
            case 2:
                this.register();
                break;
            }
        if(input == 3){
            return;
        }
        else if(input >3){
            System.out.println("请输入有效的数字");
            this.begin();

        }
    }


    public void logIn(ArrayList<account> userInf){
        Scanner sr = new Scanner(System.in);

        int i=0;
        while(true){
            System.out.println("请输入用户卡号/用户名：");
            String inputCardID = sr.next();
            System.out.println("请输入密码：");
            String inputCode = sr.next();

            for(i=0 ; i<userInf.size(); ++i){
                if(userInf.get(i).getCardID().equals(inputCardID) && userInf.get(i).getCode().equals(inputCode)){
                    System.out.println("登录成功");
                    this.userMain(inputCardID);
                    break;

                }

                else if(userInf.get(i).getUserName().equals(inputCardID) && userInf.get(i).getCode().equals(inputCode)){
                    System.out.println("登录成功");
                    this.userMain(userInf.get(i).getCardID());
                    break;

                }
            }

            if(i == userInf.size()){
                System.out.println("用户名或密码错误，登陆失败，输入任意键返回登录界面");
                String input = sr.next();
                this.begin();
                return;

            }
            else{
                return;
            }

        }

    }

    /*用户登录成功后*/
    public void saveMoney(account user){
        Scanner sr = new Scanner(System.in);
        System.out.println("请输入要存入的金额（人民币/RMB：元）：");

        user.setMoney(user.getMoney() + sr.nextDouble());
        System.out.println("存入成功，当前用户余额为："+ user.getMoney());
    }

    public void withdrawMoney(account user){
        Scanner sr = new Scanner(System.in);
        System.out.println("请输入要取出的金额（人民币/RMB：元）：");
        Double input = sr.nextDouble();
        if(user.getMoney() < input){
            System.out.println("取出失败，您的账户上没有这么多钱");
        }
        else{
            user.setMoney(user.getMoney() - input);
            System.out.println("取出成功，当前用户余额为："+ user.getMoney());
        }

    }

    public void transferMoney(account user){
        Scanner sr = new Scanner(System.in);
        account userTransfer = new account();
        while(true){
            System.out.print("请输入要转给用户的卡号：");
            String cardID = sr.next();
            int i=0;
            for(i=0 ; i<this.userInf.size() ; ++i){
                if(this.userInf.get(i).getCardID().equals(cardID)){
                    userTransfer = this.userInf.get(i);
                    break;
                }
            }
            if(i == this.userInf.size()){
                System.out.println("不存在的卡号，无法转账");
            }

            else{
                System.out.print("请输入要转账的金额：");
                double transferMoney = sr.nextDouble();

                if(transferMoney <0 ){
                    System.out.println("转账时便，不能转出负数的金额");
                }

                if(transferMoney > user.getMoney()){
                    System.out.println("转账失败，卡内余额不足");
                }
                else{
                    System.out.println(("确认转账？，确认请输入yes，否则输入no"));
                    String input = sr.next();
                    if(input.equals("yes")){
                        userTransfer.setMoney(userTransfer.getMoney() + transferMoney);
                        System.out.println("转账成功到用户："+userTransfer.getUserName());
                        break;
                    }
                    else if(input.equals("no")){

                    }
                    else{
                        System.out.println("请输入yes或者no");
                    }
                }
            }
            System.out.println("输入任意键重新转账，输入exit退出转账系统回到用户界面：");
            if(sr.next().equals("exit")){
                break;
            }

        }

    }

    public void changeCode(account user){
        Scanner sr = new Scanner(System.in);
        System.out.println("请输入新的密码：");
        user.setCode(sr.nextLine());
        System.out.println("更改密码成功，请重新登录");
        this.begin();

    }

    public void cancellation(account user){
        Scanner sr = new Scanner(System.in);
        System.out.println("您当前余额为"+user.getMoney()+"元，确认要注销账户吗，确认请输入yes，输入其他内容将退出注销");
        String input = sr.next();
        if(input.equals("yes")){
            for(int i=0 ; i<this.userInf.size() ; ++i){
                if(this.userInf.get(i).getCardID().equals(user.getCardID())){
                    this.userInf.remove(i);
                    System.out.println("用户注销成功");
                    break;
                }
            }
        }
        else{
            System.out.println("取消注销");
        }

        System.out.println("结束注销，返回主界面");
        this.begin();

    }



    public void userMain(String cardID) {
        System.out.println("==================欢迎进入小陈银行用户界面==================");
        System.out.println("0.查询");
        System.out.println("1.存款");
        System.out.println("2.取款");
        System.out.println("3.转账");
        System.out.println("4.修改密码");
        System.out.println("5.退出");
        System.out.println("6.注销当前账户");
        System.out.print("请输入命令0、1、2、3、4、5、6选择对用的操作：");
        Scanner sr = new Scanner(System.in);

        account user = this.userInf.get(0);
        for (int i = 0; i < this.userInf.size(); ++i) {
            if (this.userInf.get(i).getCardID().equals(cardID)) {
                user = this.userInf.get(i);  //把后面对象的地址给user
            }
        }

        int input = sr.nextInt();
        switch (input) {
            case 0:
                System.out.println("当前账户信息：");
                user.getUserInf();
                break;

            case 1:
                this.saveMoney(user);
                break;

            case 2:
                this.withdrawMoney(user);
                break;

            case 3:
                this.transferMoney(user);
                break;

            case 4:
                this.changeCode(user);
                return;

            case 5:
                this.begin();
                return;

            case 6:
                this.cancellation(user);
                return;

            default:
                break;
        }

        System.out.println("对用户操作完毕，输入任意键返回用户界面");
        sr.next();
        this.userMain(cardID);


    }


    public static void main(String[] args) {
         ATM atm = new ATM();
         atm.begin();

    }
}

