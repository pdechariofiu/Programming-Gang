import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class HauntedTower {
    int userFloor;
    int userLocation;
    String userName = "No name";
    String exitOption;
    String itemChoice;
    String userChoice;
    ArrayList<String> backpackItemsArray = new ArrayList<>(); //Stores items as user selects them
    String backpackItemsString; //Output of backpackItemsArray;
    String userItemChoice;
    Object[] yesNoChoices = {"Yes", "No, Thanks"};
    
    String iconPath = "/Users/azulbarros/NetBeansProjects/hauntedTower/icons/";
    ImageIcon welcomeIcon = new ImageIcon(iconPath + "welcome.jpeg");
    ImageIcon actionIcon = new ImageIcon(iconPath + "action!.png");
    ImageIcon questionIcon = new ImageIcon(iconPath + "questionMark.jpg");
    ImageIcon frontDoorIcon = new ImageIcon(iconPath + "frontDoor.png");
    ImageIcon livingRoomIcon = new ImageIcon(iconPath + "livingRoom.jpeg");
    ImageIcon diningRoomIcon = new ImageIcon(iconPath + "diningRoom.jpg");
    ImageIcon bathroomIcon = new ImageIcon(iconPath + "bathroom.jpeg");
    ImageIcon kitchenIcon = new ImageIcon(iconPath + "kitchen.jpeg");
    ImageIcon pantryIcon = new ImageIcon(iconPath + "pantry.jpeg");
    ImageIcon firstBedIcon = new ImageIcon(iconPath + "firstBed.jpeg");
    ImageIcon lastBedIcon = new ImageIcon(iconPath + "lastBed.jpeg");
    ImageIcon secondBathIcon = new ImageIcon(iconPath + "secondBath.jpeg");
    ImageIcon masterBedIcon = new ImageIcon(iconPath + "masterBed.jpeg");
    ImageIcon masterBathIcon = new ImageIcon(iconPath + "masterBath.jpeg");
    ImageIcon closetIcon = new ImageIcon(iconPath + "masterCloset.jpeg");
    ImageIcon gameOverIcon = new ImageIcon(iconPath + "gameOver.jpg");
        
    public String getUserChoice () {
       return userChoice;
    }
    public String getUserName () {
       return userName;
    }
    
    public void addToBackpack(String item){
        if (!backpackItemsArray.contains(item)){
            backpackItemsArray.add(item);
        }
    }

    //Prints backpackItemsArray items as a string formatted based on number of items
    public void printBackpackItems(ArrayList anArray){
        int sizeOfList = anArray.size();

        backpackItemsString = anArray.get(0).toString();

        //Formats ArrayList as a String based on (respectively), 2 items, 1 item, or more than 2 items
        switch (sizeOfList){
            case 2:
                backpackItemsString = backpackItemsString + " and " + anArray.get(sizeOfList - 1).toString();
                break;

            default:
                if (sizeOfList > 2){
                    for (int i = 1; i < sizeOfList - 1; ++i){
                        if (!backpackItemsString.contains(anArray.get(i).toString())){
                            backpackItemsString = backpackItemsString + ", " + anArray.get(i).toString();            
                        }
                    }
                    backpackItemsString = backpackItemsString + ", and " + anArray.get(sizeOfList - 1).toString();
                break;
                }
        }
    }

    public void exitTower () { //End game if user successfully exits tower
        JOptionPane.showMessageDialog(null, "Congrats! you made it! \n Here"
                + " are your items: \n");
        printBackpackItems(backpackItemsArray);
    }
    
    public void dieInTower () { //End game if user is killed by item
        JOptionPane.showMessageDialog(null, "You died in the tower! "
                + "\nHere are your items: \n");
        printBackpackItems(backpackItemsArray);
        System.exit(0);
    }
    
    public void setUserName () { // Asks for users name 
       userName = JOptionPane.showInputDialog(null, "What is your name?", null, 
               JOptionPane.PLAIN_MESSAGE, questionIcon, null, "").toString();
    }
   
    public void showWelcomeMessage () { // Welcomes user
       JOptionPane.showMessageDialog(null, "Welcome " + userName +
               ", and goodluck in the Tower of Terror! \nHope you make it out "
               + "alive... \nHAHAhahAHAhaAH\n", "Welcome.",
               JOptionPane.PLAIN_MESSAGE, welcomeIcon);
   }
    public void startTowerOptions () { // Start of the tower //I dont get this PED
       Object[] startChoices = {"Elevator", "Item"};
       userChoice = (String)JOptionPane.showInputDialog(
            null, "     Where would you like to go?", "An important question..."
            , JOptionPane.PLAIN_MESSAGE, frontDoorIcon, startChoices,
            "Elevator");
       if(userChoice.equals("Item")) {
           backpack[10] = "Front door item";
           elevator(); ////// this happens without explaining so it looks like they chose elevator but they didn't
       } else{
           elevator();
       }
   }
    public void elevator () {
        Object[] elevatorChoices = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        userFloor = (int) JOptionPane.showInputDialog(null, "What floor?",
            "Pick.", JOptionPane.PLAIN_MESSAGE, questionIcon, 
            elevatorChoices, "1");
        if (userFloor == userLocation) {
            System.out.print("Pick another floor.");
            elevator();
        }
        userLocation = userFloor;
        moreOptions();
    }
    
    public void boilerRoomOptions() {
        JOptionPane.showMessageDialog(null,"You're in the Boiler Room."
            + "\nThere's nothing in here.", 
                "Surprise!", JOptionPane.PLAIN_MESSAGE);
        elevator();
    }
    
    public void storageRoomOptions() {
        String [] itemOptions = {"Chest", "Ignore Item"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're in the Storage Room."
                + "\nA chest sits in the room."
                + "\nWould you like to open it?", "Storage Room",  
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "You found the key to the attic!",
                    "Surprise!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Attic Key");
                break;
            default:
                break;
        }
        elevator();
    }
    
    public void livingRoomOptions () {
        String [] itemOptions = {"Chest", "Ignore Item"};
        int choice = JOptionPane.showOptionDialog(null, "You're on the 2nd Floor. "
                + "\nThere's a chest in here. "
                + "\nWould you like to see what's inside the chest?", "2nd Floor", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                itemOptions, itemOptions[0]);

        switch (choice){
            case 0:
                JOptionPane.showMessageDialog(null, "A ghost escapes out of the "
                        + "chest and scares you to death.", "BOO!",JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Ghost");
                //FIXME need a gameOver() if they die in the tower
                break;
            default:
                break;
        }
        elevator();   /////// idk what other options to add, also needs text
        }

    public void bathroomOptions() {
        String [] itemOptions = {"Mirror", "Shower", "Ignore Items"};
        int choice = JOptionPane.showOptionDialog(null, "You're on the 3rd Floor. "
                + "\nAnd what is that dark red stain on the wall? "
                + "\nWould you like to take a peek in the Mirror? Step in the Shower? Move along?", 
                "3rd Floor", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "There's a bloody face staring "
                        + "back at you...It's you."
                        + "\nYou blink for a second and the blood is gone.", 
                        "Surprise!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Memory of a Bloody Face");
                break;
            case 1: 
                JOptionPane.showMessageDialog(null, "The room has suddenly steamed"
                        + " up and you feel fingers touching the back of your neck.",
                        "Surprise!", JOptionPane.INFORMATION_MESSAGE);
                addToBackpack("Creepy Fingers");
                break;
            default:
                break;
        }
        elevator();
    }
    
    public void diningRoomOptions() {
        String [] itemOptions = {"Candelabra", "Ignore Item"};
   
        int choice = JOptionPane.showOptionDialog(null, "You're on the 4th Floor. "
                + "\nWhat a beautiful Candelabra on the table! "
                + "\nShall we sit down and have a closer look?", "3rd Floor", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice){
            case 0:
                JOptionPane.showMessageDialog(null, "You take a seat to get a look "
                        + "when the Candelabra lights up. "
                        + "\nThe shadow of death appears behind you and covers you "
                        + "in darkness.", "BOO!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Candelabra");
                break;
            default:
                break;
        }
        elevator();
    }
    
    public void kitchenOptions() {
         String [] itemOptions = {"Cabinets", "Refrigerator", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're on the 5th Floor. "
                + "\nHungry? "
                + "\nWhy don't we check the Cabinets or the Refrigerator?", 
                "5th Floor", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice){
            case 0:
                JOptionPane.showMessageDialog(null, "You open the cabinet. "
                        + "\nThe dishes start flying all over the room and one hits you in the head.", 
                        "Surprise!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Flying Dishes");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Looks like someone went to "
                        + "that famous soul food restaurant. "
                        + "\nYou open the leftovers and find your soul in the food.", 
                        "BOO!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Soul Food");
                break;
            default:
                break;  
        }
        elevator();
    }
    
    public void pantryOptions() {
        String [] itemOptions = {"Dusty Recipe Box", "Broom", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're on the 6th Floor. "
                + "\n*SNEEZE* "
                + "\nThat Recipe Box is covered in dust. "
                + "\nShould we take a look inside or sweep this place up with the Broom?", 
                "6th Floor", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "You open up the Dusty Recipe Box. "
                        + "\nA recipe for devil's food cake appears. "
                        + "\nLooks like " + userName + " is one of the ingredients.", 
                        "Surprise!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Recipe for Devil's Food Cake");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You reach for the Broom."
                        + "\nIt flies up into the air and heads straight at you.", 
                        "Surprise!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Flying Broom");
                break;
            default:
                break;
        }
        elevator();   /////// idk what other options to add, also needs text
    }
    
    public void masterBedOptions() {
        String [] itemOptions = {"Jewelry Box", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're on the 7th Floor. "
                + "\nA beautiful Jewelry Box sits on an armoire. "
                + "\nWould you like to take a peek inside?", 
                "7th Floor", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "A gleaming stone sits inside "
                        + "of the Jewelry Box. You reach for it. "
                        + "\nThat was a mistake " + userName + "."
                        + "\nThat's the cursed Hope Diamond. But there's no hope left "
                        + "for you.", "Surprise!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Cursed Hope");
                break;
            default:
                break;
        }
        elevator();
    }
    
    public void firstBedOptions() {
        String [] itemOptions = {"Windows", "Rocking Chair", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're on the 9th Floor."
                + "\nAnd what beautiful Windows! Take a look outside " + userName + "!"
                + "\nIf not, grab a seat in that wooden Rocking Chair in the corner.", 
                "9th Floor", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "It's awfully late. "
                        + "\nWhy is that child swinging outside? "
                        + "\nWhere'd he go? How'd he get behind " + userName + "?", 
                        "Surprise!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Evil Child");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You take a seat on the Rocking "
                        + "Chair when it suddenly starts rocking on its own.", 
                        "Surprise!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Self-Rocking Chair");
                break;
            default:
                break;
        }
        elevator();
    }
    
    public void secondBedOptions() {
        String [] itemOptions = {"Doll House", "Dresser", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're on the 10th Floor."
                + "\nWhat a cute Doll House! And a grand Dresser? "
                + "\nWould you like to play with the dolls or try on a new outfit?", 
                "10th Floor", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "You crouch down to the Doll "
                        + "House when the dolls start dancing on their own. "
                        + "\nThey're dancing towards you.", 
                        "Surprise!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Possessed Doll House");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You open a drawer in the Dresser "
                        + "when - BOO! - a ghost flies out. "
                        + "\nYou're looking awfully pale.", 
                        "Surprise!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Ghost");
                break;
            default:
                break;
        }
        elevator();
    }
    
    public void atticOptions() {
        if (backpackItemsArray.contains("Attic Key")){
            String [] itemOptions = {"Chest", "Ignore Item"};
        
            int choice = JOptionPane.showOptionDialog(null, "You're in the Attic."
                    + "\nAnd you're holding the Attic Key!"
                    + "\nThere's a Chest in the room. Would you like to open it?", 
                    "Attic", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                    null, itemOptions, itemOptions[0]);
        
            switch (choice) {
                case 0:
                    JOptionPane.showMessageDialog(null, "You open the chest and find the key to the 1st Floor!",
                            "Surprise!", JOptionPane.PLAIN_MESSAGE);
                    addToBackpack("1st Floor Key");
                    break;
                default:
                    break;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Looks like you don't have the key to enter the Attic",
                    "Attic", JOptionPane.PLAIN_MESSAGE);
        }
        elevator();
    }
    
    public void moreOptions() {
        //while (userChoice.equals("Elevator")) {
            switch (userFloor) {
                case(1):
                    Object [] basementChoice = {"Storage Room", "Boiler Room"};
                    userChoice =  (String) JOptionPane.showInputDialog(null,
                    "Which room?", "??", JOptionPane.PLAIN_MESSAGE,
                    questionIcon, basementChoice, "Storage Room");
                    if (userChoice.equals("Storage Room")) {
                        storageRoomOptions();
                    } else {
                        boilerRoomOptions();
                    }
                    break;
                case(2): 
                    if (backpackItemsArray.contains("Front Door Key")) {
                        exitOption = (String) JOptionPane.showInputDialog(null,
                    "Would you like to exit?", "??", JOptionPane.PLAIN_MESSAGE,
                    questionIcon, yesNoChoices, "No");
                    } else {
                        elevator(); 
                    }
                    if (exitOption.equals("Yes")) {
                        exitTower();
                    } else {
                        elevator();
                    }
                    break;
                case(3):
                    livingRoomOptions();
                    break;
                case(4):
                    bathroomOptions();
                    break;
                case(5):
                    diningRoomOptions();
                    break;
                case(6):
                    kitchenOptions();
                    break;
                case(7):
                    pantryOptions();
                    break;
                case(8):
                    masterBedOptions();
                    break;
                case(9):
                    firstBedOptions();
                    break;
                case(10):
                    secondBedOptions();
                    break;
                case(11):
                    atticOptions();
                    break;
            }
        }
    }
//}