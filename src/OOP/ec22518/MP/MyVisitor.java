package OOP.ec22518.MP;
import OOP.ec22518.MP.contributions.Visitor;
import OOP.ec22518.MP.contributions.Item;

import javax.swing.*;

public class MyVisitor implements Visitor{


    public Inventory i;

    public String dialogue ="";


    public MyVisitor(Inventory i)
    {
        this.i=i;
    }


        public final int max_items = 4;
        public void tell(String s)
        {
            this.dialogue=s;
        }

        public char getChoice(String s, char [] choices)
        {
            String input="";

                input = JOptionPane.showInputDialog(null, s);

        while(input==null)
        {
            input=JOptionPane.showInputDialog(null, "You cannot press cancel");
        }


            StringBuilder str = new StringBuilder();

            for(char c : choices)
            {
                str.append(c + " ");
            }



            while(checkInput(input, choices))
            {
                return this.getChoice("Please input a valid option: " + str.toString(), choices);
            }

            return input.charAt(0);
        }

        public boolean checkInput(String input, char [] choices)
        {
            if(input.length()!=1)
            {
                return true;
            }

            for(char c: choices)
            {
                if(input.charAt(0)==c)
                {
                    return false;
                }
            }
            return true;
        }

        public boolean giveItem(Item X)
        {
//            if(i.inventory.size()==max_items)
//            {
//                this.tell("You have reached the maximum number of items");
//                return false;
//            }

            char choice = this.getChoice("You are being given "+ X.name + ". Do you accept?", new char[] {'y', 'n'});


            if(choice=='y')
            {
                if(i.inventory.containsKey(X))
                {
                    i.updateItem(X);
                    return true;
                }

                i.addItem(X);
                return true;
            }

        return false;

        }

        public void takeHealth(int n)
        {
            i.updateHealth(i.inventory.get(i.health)-n);
        }

        public void giveHealth(int n)
        {
            i.updateHealth(i.inventory.get(i.health)+ n);
        }


        public boolean hasIdenticalItem(Item x) {
            for (Item i: i.inventory.keySet())
                if (x == i)
                    return true;
            return false;
        }

        public boolean hasEqualItem(Item x) {
            for (Item i: i.inventory.keySet())
                if (x.equals(i))
                    return true;
            return false;
        }

        public void giveGold(int n)
        {
            i.addGold(n);
        }

        public int takeGold(int n)
        {
            i.removeGold(n);

            return n;
        }
    }

