import java.util.Scanner;
import java.io.*;
class BattleShips
{
	public int fileLines=0;
	public String file;
	public String strLine;
	
	public void readFile()  //Read file method to read input from file and print coordinates.
	{
		try
		{
			
			System.out.println("Enter the file name");
			Scanner in=new Scanner(System.in);
			file=in.nextLine();
			FileInputStream fstream = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			

			
			
			while ((strLine = br.readLine()) != null)
			{
				System.out.println(strLine);   //Printing the coordinates.
				fileLines++;
			}
			br.close();
			
		}
		catch(Exception e)
		{
		}
	}
	public void oceanShip()   //oceanShip() function for printing ocean with ships.
	{
		char[][] occenShips=new char[11][11];
		char empty='-';
		int k=0;
		for(int i=1;i<=10;i++)
		{
			for(int j=1;j<=10;j++)
			{
				occenShips[i][j]=empty;
			}
		}
		String[] temp;
		String delimiter = " ";
		int flag=0;
		try
		{
			FileInputStream fstream = new FileInputStream(file);  
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			while ((strLine = br.readLine()) != null)  //Reading file to end.
			{
				//System.out.println (strLine);
				temp = strLine.split(delimiter);    //Spliting coordinates.
				int a=Integer.parseInt(temp[0]);     //Storing in a integer variables by converting to int.
				int b=Integer.parseInt(temp[1]);
				occenShips[a][b]='S';                 //Place S in the ships places.
			}
		}
		catch(Exception e)
		{
		}
		System.out.println("The ocean With ships");
		for(int i=0;i<=10;i++)
		{
			for(int j=0;j<=10;j++)
			{
				if(i==0 && j!=0)
				{
					System.out.print(j+"   ");                 //Printing numbers of columns.
				}
				else if(j==0)
				{
					System.out.print(k+"   ");                 //Printing numbers of rows.
					k++;
				}
				else
				{
					System.out.print(occenShips[i][j]+"   ");  //Printing ocean with ships.
				}
			}
			System.out.println();
		}
			
	}
			
	
	public char findShip(int x,int y)  //Method to find the ship in ocean.
	{
		String[] temp;
		String delimiter = " ";
		int flag=0;
		try
		{
			FileInputStream fstream = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			while ((strLine = br.readLine()) != null)  //Reading file to the end.
			{
				//System.out.println (strLine);
				temp = strLine.split(delimiter);   //Spliting coordinates.
				int a=Integer.parseInt(temp[0]);     //Storing in a integer variables by converting to int.
				int b=Integer.parseInt(temp[1]);
		
				if(x==a && y==b)                   //If any ship found make flag=1;
				{
					flag=1;
					break;
				}
			}
			br.close();     //closing the file.
		}
		catch(Exception e)
		{
			
		}
		if(flag==1)
			return 'H';    //If flag=1 then H return means HITT.
		else
			return 'M';     //else M return means MISS.
		
	}
	
	
	public void player()    //The player plays game here.
	{
		char[][] ships=new char[11][11];
		char empty='-';
		int k=0;
		for(int i=1;i<=10;i++)
		{
			for(int j=1;j<=10;j++)
			{
				ships[i][j]=empty;
			}
		}
		oceanShip();  //oceanShip() method for printing ocean with ships.
		System.out.println("The ocean before shooting");
		for(int i=0;i<=10;i++)
		{
			for(int j=0;j<=10;j++)
			{
				if(i==0 && j!=0)
				{
					System.out.print(j+"   ");  //Printing numbers of columns.
				}
				else if(j==0)
				{
					System.out.print(k+"   ");   //Printing numbers of rows.
					k++;
				}
				else
				{
					System.out.print(ships[i][j]+"   ");  //Printing ocean.
				}
			}
			System.out.println();
		}
		Scanner in=new Scanner(System.in);
		int x,y,count=0,total=0,misses=40;
		while(fileLines>count)
		{
			 k=0;
			total++;
			System .out.println("Get Ready to Shoot out");
			System.out.println("Enter x and y coordinates :"); //Input coordinates for shooting.
			x=in.nextInt();
			y=in.nextInt();
			if(ships[x][y]=='H' || ships[x][y]=='M')   //Message for already shooted coordinates.
			{
				System.out.println("You already shooted that coordinates");
				total--;
			}
			else
			{
				char c=findShip(x,y);  
							//Calling a method to findShips in the occean it returns H or M which specifies HIT or MISS.
				if(c=='H')
				{
					count++;  //Counting number of HITTS.
				}
				else
				{
					misses--;  //Counting misses ( deceasing chances from 40).
				}
				if(misses==0)  //If remaining misses are 0 then break the running game.
				{
					break;
				}
				ships[x][y]=c;
				for(int i=0;i<=10;i++)
				{
					for(int j=0;j<=10;j++)
					{
						if(i==0 && j!=0)
						{
							System.out.print(j+"   ");           //Printing numbers of columns..
						}
						else if(j==0)
						{
							System.out.print(k+"   ");           //Printing numbers of rows.
							k++;
						}
						else
						{
							System.out.print(ships[i][j]+"   ");  //Printing ocean.
						}
					}
					System.out.println();
				}
			}
			System.out.println("The remaining misses chances = "+misses);
			
		}
		if(misses==0)  //Message for if maximum misses cross the player.
		{
			System.out.println("Sorry! You cross the maximum misses:");
			System.out.println("Game Over");
		}
		else  //Message if player won the game.
		{
			System.out.println("Welldone you won the Game");
			System.out.println("All ships are sunked in "+total+" Shoots");
			System.out.println("Game Over");
		}
		
	}
	
}
class FindShip
{
	public static void main (String[] args)
	{
		BattleShips z=new BattleShips(); //Creating object for Battleship class.
		
		z.readFile();  //calling readfile() function in BattleShip class
		
		z.player();   //Game start's with player() function.
	}
}