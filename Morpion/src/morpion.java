import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Player {
    
    public static Case[][] Simulation(Case[][] grille, int x, int y, int val)
    {
        Case[][] grilleSimu = new Case[3][3];
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                grilleSimu[i][j] = new Case(i,j,0);
            }
        }
        for (int k = 0; k < 3; k++)
        {
            for (int l = 0 ; l< 3; l++) 
            {
                grilleSimu[k][l].setVal(grille[k][l].getVal());
            }
        }
        grilleSimu[x][y].setVal(val);
        return grilleSimu;
    }
    
    public static int min_max(Case[][] grilleAct, int profondeur, int joueur)
    {
    
        if ( (partieFini(grilleAct) !=0) || profondeur == 0)
            return partieFini(grilleAct);
        else {
            int score = 0;
            for (int i = 1; i <= profondeur; i++)
            {
                int X = caseNumI(grilleAct, i).getX();
                int Y = caseNumI(grilleAct, i).getY();
                score = score + min_max(Simulation(grilleAct, X, Y, joueur), profondeur -1, joueur*(-1));
            }
            return score;
        }
    }
    
    public static Case caseNumI(Case[][] grilleAct, int i)
    {
        int compteur = 1;
        for (int k = 0; k < 3; k++)
        {
            for (int l = 0 ; l< 3; l++) 
            {
                if (grilleAct[k][l].getVal() == 0) 
                {
                    if (compteur == i)
                        return grilleAct[k][l];
                    else compteur ++;
                }
            }
        }
        System.err.println("ERROR");
        /*for (int k = 0; k < 3; k++)
        {
            for (int l = 0 ; l< 3; l++) 
            { 
                System.err.println(grilleAct[k][l].getVal());
            }
        } */  
        
        return null;
    }
    
    public static int partieFini( Case[][] grille)
    {
        if( (grille[0][0].getVal() != 0) && (grille[0][0].getVal() == grille[1][1].getVal()) && (grille[1][1].getVal() == grille[2][2].getVal()) )
        {
                return 0;
        }
        if( (grille[1][1].getVal() != 0) && (grille[0][2].getVal() == grille[1][1].getVal()) && (grille[1][1].getVal() == grille[2][0].getVal()) )
        {
                return 0;
        }

        for(int i = 0; i < 3; i++)
        {
            if( (grille[i][0].getVal() != 0) && ( grille[i][0].getVal() == grille[i][1].getVal() ) && ( grille[i][1].getVal() == grille[i][2].getVal() ) )
            {
                    return 0;
            }
        }
        for(int j = 0; j < 3; j++)
        {
            if( (grille[0][j].getVal() != 0 )  && ( grille[0][j].getVal() == grille[1][j].getVal() ) && ( grille[1][j].getVal() == grille[2][j].getVal() ) )
            {
                    return 0;
            }
        }
        return 10;
    }

    public static void main(String args[]) 
    {
        Scanner in = new Scanner(System.in);
        Case[][] grille = new Case[3][3];
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                grille[i][j] = new Case(i,j,0);
            }
        }
        // game loop
        int tourJeu = 1;
        int joueur;
        while (true) 
        {
            int opponentRow = in.nextInt();
            int opponentCol = in.nextInt();
            if (opponentRow != -1){
                grille[opponentRow][opponentCol].setVal(-1);
                tourJeu++;
            }
            if(tourJeu ==1)
            {
                joueur = 1;
            }
            else
            {
                joueur = -1;
            }
            int validActionCount = in.nextInt();
            for (int i = 0; i < validActionCount; i++) 
            {
                int row = in.nextInt();
                int col = in.nextInt();
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            
            //System.err.println(tourJeu);
            if (tourJeu ==1) 
            {
                System.out.println("1 1");
                grille[1][1].setVal(1);
                joueur = joueur*(-1);
            }
            
            if (tourJeu == 2) 
            {
                if(grille[1][1].getVal() == -1)
                {
                    System.out.println("0 0");
                    grille[0][0].setVal(1);
                    joueur = joueur*(-1);
                }
                else
                {
                    System.out.println("1 1");
                    grille[1][1].setVal(1);
                    joueur = joueur*(-1);
                }
                    
            }
            if(tourJeu >= 3)
            {
                int min = 100000;
                int caseAJoue = 1;
                int score;
                for(int g=1;g<(11-tourJeu);g++)
                {
                    score = 0;
                    //System.err.println(g);
                    
                    int X = caseNumI(grille, g).getX();
                    int Y = caseNumI(grille, g).getY();
                    score = score + min_max(Simulation(grille, X, Y, joueur), (9-tourJeu), joueur*(-1));
                    System.err.println(score);
                    //System.err.println(min_max(Simulation(grille, X, Y, joueur), (6-tourJeu), joueur*(-1)));
                    
                    if (score < min)
                    {
                        min = score;
                        caseAJoue = g;
                    }
                }
                int x = caseNumI(grille, caseAJoue).getX();
                int y = caseNumI(grille, caseAJoue).getY();
                
                for (int k = 0; k < 3; k++)
                {
                    for (int l = 0 ; l< 3; l++) 
                    { 
                        //System.err.println(grille[k][l].getVal());
                    }
                }
                
                System.out.println(x + " "+ y);
                grille[x][y].setVal(1);
            }
            tourJeu++;
        }
    }
}

class Case 
{
    private int x;
    private int y;
    private int val;
    
    public Case (int x, int y, int val)
    {
         this.x = x;
         this.y = y;
         this.val = val;
     }
     public int getVal()
     {
         return val;
     }
     
     public int getX()
     {
         return x;
     }
     
     public int getY()
     {
         return y;
     }
     
     public void setVal(int valeur)
     {
         this.val = valeur;
     }
}