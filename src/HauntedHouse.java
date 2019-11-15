//********************************************************************************
// PANTHERID:  6229219
// CLASS: COP 2210 – Fall 2019
// ASSIGNMENT # 3
// DATE: October 22, 2019
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else.

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class HauntedHouse {
    String username; //Stores username from user input

    ArrayList<String> backpackItemsArray = new ArrayList<>(); //Stores items as user selects them
    String backpackItemsString; //Output of backpackItemsArray

    final ImageIcon house = new ImageIcon("hauntedhouse.png"); //Used in welcomemessage

    //Images used to show layout of the house and location as user moves throughout rooms
    final ImageIcon layout = new ImageIcon("layout.png");
    final ImageIcon bathroom = new ImageIcon("bathroom.png");
    final ImageIcon blueBedroom = new ImageIcon("bluebedroom.png");
    final ImageIcon diningRoom = new ImageIcon("diningroom.png");
    final ImageIcon downstairs = new ImageIcon("downstairs.png");
    final ImageIcon frontDoor = new ImageIcon("frontdoor.png");
    final ImageIcon kitchen = new ImageIcon("kitchen.png");
    final ImageIcon livingRoom = new ImageIcon("livingroom.png");
    final ImageIcon masterBathroom = new ImageIcon("masterbathroom.png");
    final ImageIcon masterBedroom = new ImageIcon("masterbedroom.png");
    final ImageIcon pantry = new ImageIcon("pantry.png");
    final ImageIcon redBedroom = new ImageIcon("redbedroom.png");
    final ImageIcon upstairs = new ImageIcon("upstairs.png");
    final ImageIcon upstairsBathroom = new ImageIcon("upstairsbathroom.png");

    final ImageIcon purgatory = new ImageIcon("purgatory.jpg"); //Gratuitous image for gameOver()
    final ImageIcon skull = new ImageIcon("skull.png"); //Gartuitous image for gameOver()

    String lastRoom; //Print last room user was in before gameOver() or escape()
    
    //Adds items selected by user to ArrayList
    public void addToBackpack(String item){
        if (!backpackItemsArray.contains(item)){
            backpackItemsArray.add(item);
        }
    }

    //Prints backpackItemsArray items as a string formatted based on number of items
    public void printBackpack(ArrayList anArray){
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

    //Welcome messages, gets username, and sends user to the Front Door
    public void enterHouse(){    
        JOptionPane.showMessageDialog(null, "My-Oh-My. "
            + "\nWhat a beautiful house! "
            + "\nLooks a little spooky. =0 "
            + "\nShould we go inside?", "Happy Halloween", 
            JOptionPane.PLAIN_MESSAGE, house);

        JOptionPane.showMessageDialog(null, "MWUAHAHAHA! \nWelcome to the Haunted House."
                + "\nYou'll be moving from room to room looking for an escape!"
                + "\nChoosing the wrong item may result in death."
                + "\nChoosing the right one will get you out of here alive."
                + "\nLet's head to the Front Door and look at the map."            
                + "\nGood luck!", "Haunted House", JOptionPane.PLAIN_MESSAGE);
        
        username = JOptionPane.showInputDialog("What is your name by the way?");
        
        frontDoor();
    }

    //Shows layout and gives user movement options
    public void frontDoor(){
        String [] roomOptions = {"Stairs", "Living Room", "Dining Room"};
       
        int choice = JOptionPane.showOptionDialog(null, "You're at the Front Door. "
                + username + ", do you want to enter the Stairs, Living Room, or Dining Room?",
                "Front Door", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, layout, roomOptions, roomOptions[0]);

        switch (choice){
            case 0:
                downstairs();
                break;
            case 1:
                livingRoom();
                break;
            case 2:
                diningRoom();
                break;
            default:
                frontDoor();
                break;
        }
    }

    //Shows location, allows user to explore items,gives movement options
    public void livingRoom(){
        String [] itemOptions = {"Chest", "Ignore Item"};

        int choice = JOptionPane.showOptionDialog(null, "You're in the Living Room. "
                + "\nA chest sits ominously below a painting of your great-great Uncle Harry. "
                + "\nWould you like to see what's inside the chest?", "Living Room", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                itemOptions, itemOptions[0]);

        switch (choice){
            case 0:
                JOptionPane.showMessageDialog(null, "It looks like Harry never left. "
                        + "\nHis ghost escapes out of the chest and scares you to "
                        + "the death.", "BOO!",JOptionPane.PLAIN_MESSAGE);
                lastRoom = "Living Room";
                addToBackpack("Chest");
                gameOver();
                break;
            default:
                break;
        }
        
        String [] roomOptions = {"Front Door", "Bathroom"};

        choice = JOptionPane.showOptionDialog(null, "Would you like to return to "
                + "the Front Door or enter the Bathroom?", "Living Room", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, livingRoom, 
                roomOptions, roomOptions[0]);

        switch (choice){
            case 0:
                frontDoor();
                break;
            case 1:
                downstairsBathroom();
                break;
            default:
                livingRoom();
                break;
        }
    }

    //Shows location, allows user to explore items,gives movement options
    public void downstairsBathroom(){
        String [] itemOptions = {"Mirror", "Shower", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're in the Bathroom. "
                + "\nAnd what is that dark red stain on the wall? "
                + "\nWould you like to take a peek in the Mirror? Step in the Shower? Move along?", 
                "Bathroom", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "There's a bloody face staring "
                        + "back at you...It's you."
                        + "\nYou blink for a second and the blood isn't there.", 
                        "BOO!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Mirror");
                break;
            case 1: 
                JOptionPane.showMessageDialog(null, "The room has suddenly steamed"
                        + " up and you feel fingers touching the back of your neck."
                        + "\nThey pulled you upstairs.", 
                        "BOO!", JOptionPane.INFORMATION_MESSAGE);
                addToBackpack("Shower");
                upstairs();
                break;
            default:
                break;
        }
        
        JOptionPane.showMessageDialog(null, username + ", return to the Living Room.", 
                "Bathroom", JOptionPane.PLAIN_MESSAGE, bathroom);
        
        livingRoom();
    }

    //Shows location, allows user to explore items,gives movement options    
    public void diningRoom(){
        String [] itemOptions = {"Candelabra", "Ignore Item"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're in the Dining Room. "
                + "\nWhat a beautiful Candelabra on the table! "
                + "\nShall we sit down and have a closer look?", "Dining Room", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, null, itemOptions, itemOptions[0]);
        
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
        
        String [] roomOptions = {"Front Door", "Kitchen"};
        
        choice = JOptionPane.showOptionDialog(null, "Would you like to return to "
                + "the Front Door or enter the Kitchen?", "Dining Room", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                diningRoom, roomOptions, roomOptions[0]);
        
        switch (choice){
            case 0:
                frontDoor();
                break;
            default:
                kitchen();
                break;
        }
    }

    //Shows location, allows user to explore items,gives movement options    
    public void kitchen(){
        String [] itemOptions = {"Cabinets", "Refrigerator", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're in the Kitchen. "
                + "\nFancy a snack? "
                + "\nWhy don't we check the Cabinets or the Refrigerator?", 
                "Kitchen", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice){
            case 0:
                JOptionPane.showMessageDialog(null, "You open the cabinet. "
                        + "\nThe dishes start flying all over the room and one hits you in the head.", 
                        "BOO!", JOptionPane.PLAIN_MESSAGE);
                lastRoom = "Kitchen";
                addToBackpack("Cabinets");
                gameOver();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Looks like someone went to "
                        + "that famous soul food restaurant. "
                        + "\nYou open the leftovers and find your soul in the food.", 
                        "BOO!", JOptionPane.PLAIN_MESSAGE);
                lastRoom = "Kitchen";
                addToBackpack("Refrigerator");
                gameOver();
                break;
            default:
                break;  
        }
        
        String [] roomOptions = {"Pantry", "Dining Room"};
        
        choice = JOptionPane.showOptionDialog(null, username + ", would you like to enter the "
                + "Pantry or the Dining Room?", "Kitchen", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, kitchen, roomOptions, roomOptions[0]);
        
        switch (choice) {
            case 0:
                pantry();
                break;
            default:
                diningRoom();
                break;
        }
    }
    
    //Shows location, allows user to explore items,gives movement options
    public void pantry(){
        String [] itemOptions = {"Dusty Recipe Box", "Broom", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're in the Pantry. "
                + "\n*SNEEZE* "
                + "\nThat Recipe Box is covered in dust. "
                + "\nShould we take a look inside or sweep this place up with the Broom?", "Pantry", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "You open up the Dusty Recipe Box. "
                        + "\nA recipe for devil's food cake appears. "
                        + "\nLooks like " + username + " is one of the ingredients.", "BOO!", 
                        JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Dusty Recipe Box");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You reach for the Broom."
                        + "\nIt flies up into the air and heads straight at you.", 
                        "BOO!", JOptionPane.PLAIN_MESSAGE);
                lastRoom = "Pantry";
                addToBackpack("Broom");
                gameOver();
                break;
            default:
                break;
        }
        
        JOptionPane.showMessageDialog(null, username + ", let's return to the Kitchen.", 
                "Pantry", JOptionPane.PLAIN_MESSAGE, pantry);
        
        kitchen();
    }
 
    //Shows location, allows user to explore items,gives movement options
    public void blueBedroom(){
        String [] itemOptions = {"Windows", "Rocking Chair", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're in the Blue Bedroom. "
                + "\nAnd what beautiful Windows! Take a look outside " + username + "!"
                + "\nIf not, grab a seat in that wooden Rocking Chair in the corner.", "Blue Bedroom", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "It's awfully late. "
                        + "\nWhy is that child swinging outside? "
                        + "\nWhere'd he go? How'd he get behind " + username + "?", 
                        "BOO!", JOptionPane.PLAIN_MESSAGE);
                lastRoom = "Blue Bedroom";
                addToBackpack("Windows");
                gameOver();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You take a seat on the Rocking "
                        + "Chair when it suddenly starts rocking on its own."
                        + "\nIt rocks you all the way to the Master Bedroom", 
                        "BOO!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Rocking Chair");
                masterBedroom();
                break;
            default:
                break;
        }
        
        String [] roomOptions = {"Bathroom", "Stairs"};
        
        choice = JOptionPane.showOptionDialog(null, username + " do you want to enter the "
                + "Bathroom or head to the Stairs?", "Blue Bedroom", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                blueBedroom, roomOptions, roomOptions[0]);
        
        switch (choice) {
            case 0:
                upstairsBathroom();
                break;
            default:
                upstairs();
                break;
        }
    }
    
    //Shows location, allows user to explore items,gives movement options    
    public void redBedroom(){
        String [] itemOptions = {"Doll House", "Dresser", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're in the Red Bedroom. "
                + "\nWhat a cute Doll House! And a grand Dresser? "
                + "\nWould you like to play with the dolls or try on a new outfit?", 
                "Red Bedroom", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "You crouch down to the Doll "
                        + "House when the dolls start dancing on their own. "
                        + "\nThey're dancing towards you.", "BOO!", JOptionPane.PLAIN_MESSAGE);
                lastRoom = "Red Bedroom";
                addToBackpack("Doll House");
                gameOver();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You open a drawer in the Dresser "
                        + "when - BOO! - a ghost flies out. "
                        + "\nYou're looking awfully pale.", "BOO!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Dresser");
                break;
            default:
                break;
        }
        
        String [] roomOptions = {"Bathroom", "Stairs"};
        
        choice = JOptionPane.showOptionDialog(null, username + ", enter the Bathroom or return "
                + "to the Stairs?", "Red Bedroom", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, redBedroom, roomOptions, roomOptions[0]);
        
        switch (choice) {
            case 0:
                upstairsBathroom();
                break;
            default:
                upstairs();
                break;
        }
    }
    
    //Shows location, allows user to explore items,gives movement options    
    public void upstairsBathroom(){
        String [] itemOptions = {"Mirror", "Shower", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're in the Bathroom. "
                + "\nAnd what is that dark red stain on the wall? "
                + "\nWould you like to take a peek in the Mirror? Step in the Shower? Move along?", 
                "Bathroom", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "There's a bloody face staring "
                        + "back at you...It's you.", "BOO!", JOptionPane.PLAIN_MESSAGE);
                lastRoom = "Bathroom";
                addToBackpack("Mirror");
                gameOver();
                break;
            case 1: 
                JOptionPane.showMessageDialog(null, "The room has suddenly steamed"
                        + " up and you feel fingers touching the back of your neck."
                        + "\nThey pull you into the Pantry.", 
                        "BOO!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Shower");
                pantry();
                break;
            default:
                break;
        }
        
        String [] roomOptions = {"Red Bedroom", "Blue Bedroom"};
        
        choice = JOptionPane.showOptionDialog(null, username + ", do you want to enter the "
                + "Red Bedroom or the Blue Bedroom?", "Bathroom", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, upstairsBathroom, roomOptions, roomOptions[0]);
        
        switch (choice){
            case 0:
                redBedroom();
                break;
            default:
                blueBedroom();
                break;
        }
    }

    //Shows location and gives movement options    
    public void upstairs(){
        String [] roomOptions = {"Master Bedroom", "Red Bedroom", "Blue Bedroom", "Downstairs"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're Upstairs. \n"
                + username + ", do you want to enter the Master Bedroom, the Red Bedroom, the Blue "
                + "Bedroom, or go Downstairs?", "Upstairs", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, upstairs, roomOptions, roomOptions[0]);
        
        switch (choice) {
            case 0:
                masterBedroom();
                break;
            case 1:
                redBedroom();
                break;
            case 2:
                blueBedroom();
                break;
            default:
                downstairs();
                break;
        }
    }
    
    //Shows location and gives movement options
    public void downstairs(){
        String [] roomOptions = {"Front Door", "Upstairs"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're Downstairs. \n"
                + username + ", do you want to return to the Front Door or go Upstairs?", 
                "Downstairs", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                downstairs, roomOptions, roomOptions[0]);
        
        switch (choice) {
            case 0:
                frontDoor();
                break;
            default:
                upstairs();
                break;
        }
    }
    
    //Shows location, allows user to explore items,gives movement options    
    public void masterBedroom(){
        String [] itemOptions = {"Jewelry Box", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're in the Master Bedroom. "
                + "\nA beautiful Jewelry Box sits on the armoire. "
                + "\nWould you like to take a peek inside?", "Master Bedroom", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "A gleaming stone sits inside "
                        + "of the Jewelry Box. You reach for it. "
                        + "\nThat was a mistake " + username + "."
                        + "\nThat's the cursed Hope Diamond. But there's no hope left "
                        + "for you.", "BOO!", JOptionPane.PLAIN_MESSAGE);
                addToBackpack("Jewelry Box");
                break;
            default:
                break;
        }
        
        String [] roomOptions = {"Master Bathroom", "Stairs"};
        
        choice = JOptionPane.showOptionDialog(null, username + ", do you want to enter the Master "
                + "Bathroom or return to the Stairs?", "Master Bedroom", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                masterBedroom, roomOptions, roomOptions[0]);
        
        switch (choice) {
            case 0:
                masterBathroom();
                break;
            default:
                upstairs();
                break;
        }   
    }
    
    //Shows location, allows user to explore items,gives movement options
    public void masterBathroom(){
        String [] itemOptions = {"Intricate Oil Lamp", "Shower", "Ignore Items"};
        
        int choice = JOptionPane.showOptionDialog(null, "You're in the Master Bathroom. "
                + "\nAnd it's so dark in here! "
                + "\nDo you want to light the Intricate Oil Lamp by the tub? Or take a relaxing Shower?", 
                "Master Bathroom", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, null, itemOptions, itemOptions[0]);
        
        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "You touch the intricate design "
                        + "on the Intricate Oil Lamp when a genie pops out. "
                        + "\nHe grants you three wishes. "
                        + "\nYou're now rich and beautiful."
                        + "\nFor your last wish, you wish yourself out of the house."
                        + "\n*POOF*", "BOO!", JOptionPane.PLAIN_MESSAGE);
                lastRoom = "Master Bathroom";
                addToBackpack("Intricate Oil Lamp");
                escape();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You reach for the shower door "
                        + "and hear singing. "
                        + "\nWhere'd you go?", "BOO!", JOptionPane.PLAIN_MESSAGE);
                lastRoom = "Master Bathroom";
                addToBackpack("Shower");
                gameOver();
                break;
            default:
                break;                
        }
        
        JOptionPane.showMessageDialog(null, username + ", let's return to the Master Bedroom.", 
                "Master Bathroom", JOptionPane.PLAIN_MESSAGE, masterBathroom);
        
        masterBedroom();
    }

    //Calls printBackpack() with backpackItemsArray as argument
    //Tells user they died in the house and tells them items selected with JOptionPane
    //Exits program
    public void gameOver(){
        printBackpack(backpackItemsArray);
        
        JOptionPane.showMessageDialog(null, username + ", you died in the " + lastRoom + 
                ". You brought the " + backpackItemsString + " with you to purgatory.", 
                "Game Over", JOptionPane.PLAIN_MESSAGE, purgatory);
        
        JOptionPane.showMessageDialog(null, "", 
                "Game Over", JOptionPane.PLAIN_MESSAGE, skull);
        
        System.exit(0);
    }
    
    //Calls printBackpack() with backpackItemsArray as argument
    //Tells user they got out of the house and tells them items selected with JOptionPane
    //Exits program
    public void escape(){
        printBackpack(backpackItemsArray);
        
        JOptionPane.showMessageDialog(null, username + "! You've made it out of the Haunted House Alive!" + 
                "\nYou escaped through the " + lastRoom + ". " +
                "\nYou left with the " + backpackItemsString + "." +
                "\nFeel free to visit again whenever you'd like " + username + ". :)", 
                "You Won!", JOptionPane.PLAIN_MESSAGE, house);
    
        System.exit(0);
    } 
 
    //Creates a new HauntedHouse object and begins at enterHouse()
    public static void main(String[] args) {
        HauntedHouse letsPlay = new HauntedHouse();
               
        letsPlay.enterHouse();
    } 
}