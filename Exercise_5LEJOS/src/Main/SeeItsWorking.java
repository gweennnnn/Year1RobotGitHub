package Main;

import established.GridTraveller;
import grid.GridBoard;
import grid.GridPuz;
import grid.GridSuccessorFunction;
import grid.GridBoard.Direction;

import lejos.geom.Point;
import java.util.List;

import lejos.nxt.Button;
import Interfaces.PuzzleInterface;
import Interfaces.SuccessorFunction;
import Main.Search.SearchType;


public class SeeItsWorking {
	
	private static SearchType searchChoice = SearchType.AStar;
	
	public static void main(String[] args) {
		
		solveGridPuzzle();
	}
	
	public static void solveGridPuzzle(){
		GridPuz startstate = new GridPuz(GridBoard.start);
		
		GridPuz endstate = new GridPuz(GridBoard.endGoal);
		
		SuccessorFunction<Direction, GridPuz> succfunc = new GridSuccessorFunction();
		
		Search<Direction> USearch = new Search<Direction>
        (startstate, endstate, searchChoice); 
		
		List<Direction> solutionList = USearch.search(succfunc, searchChoice); 

				int count = 0; 
				for(int i = 0; i < solutionList.size(); i++) 
				{ 
				System.out.print(solutionList.get(i).toString() + ", "); 
				count++; 
				} 
				System.out.println(); 
				System.out.println("Total Moves: " + count); 	
				
				Button.waitForAnyPress();
				
				GridTraveller traveller = new GridTraveller(solutionList);
				
				traveller.runActions();
	}
}