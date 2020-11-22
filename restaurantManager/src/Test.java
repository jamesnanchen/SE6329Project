public class Test {
    public static void main(String[] args){
        RestaurantSystemController rsc = new RestaurantSystemController();
        boolean logIn = rsc.login(2,"password02");
        if(logIn){
            System.out.println("you are logged in");
        }
        else{
            System.out.println("logged in failed");
        }
    }
}
