
package Model;

import Controller.MainMenuController;

public class Main {
    
    public static void main(String[] args){
        ConnectionManager.connect();
        MainMenuController.getInstance().makeVisible(true);
    }      
}