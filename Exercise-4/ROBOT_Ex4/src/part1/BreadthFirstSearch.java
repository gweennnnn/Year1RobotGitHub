package part1;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Current;

import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.util.ActionStatePair;
import rp13.search.util.EqualityGoalTest;



public class BreadthFirstSearch {
	
	//WHERE WE START SEARCHING
	EightPuzzle startState;
	
	//WHERE WE WANT TO END UP
	EightPuzzle endState;
	
	//ALL POSSIBLE MOVES
	EightPuzzleSuccessorFunction sf;
	
	//LIST OF FRONTIER STATES
	List<Node> frontier;
	
	//LIST OF ELEMENTS THAT HAVE ALREADY BEEN EXPLORED
	List<EightPuzzle> explored;
	
	
	

	public BreadthFirstSearch(EightPuzzle startState, EightPuzzle endState) {
		super();
		this.startState = startState;
		this.endState = endState;
	}
	
	//CHECKS IF THE CURRENT STATE IS THE GOAL WE'RE SEARCHING FOR
	
	public boolean isGoalState(EightPuzzle currentState) {
		 
		return currentState.equals(endState);
	}
	
	//CHECKS IF A PARTICULAR STATE IS IN A LIST (F.E. FRONTIERS LIST)
	
	public boolean isInList(EightPuzzle currentState, List a){
		
		for (int i = 0; i < a.size(); i++){
			if (a.get(i) == currentState) return true;
		}
		
		return false;
	}
	
	public Node pop(){
		return frontier.remove(0);
		
	}
	
	public List<PuzzleMove> search(ActionStatePair<PuzzleMove, EightPuzzle> start, EightPuzzle goal){
		
		List<PuzzleMove> failed = new ArrayList();
				
		List<ActionStatePair<PuzzleMove, EightPuzzle>> successors = new ArrayList();
		
		Node<PuzzleMove, EightPuzzle> currentNode = new Node(start);
		
		EightPuzzle currentState = currentNode.getData().getState();
		
		EqualityGoalTest<EightPuzzle> equals = new EqualityGoalTest(goal);
		
		

		
		frontier.add(currentNode);
		
			while(!frontier.isEmpty()){
				
			
				if(equals.isGoal(currentState)){
					return currentNode.solutionList();
				}
				//FILLING IN THE FRONTIERS LIST WITH SUCCESSORS
				
				sf.getSuccessors(currentNode.getData().getState(), successors); //<----------gets all the successors
				
				for(ActionStatePair<PuzzleMove, EightPuzzle> state : successors){ //<--------------saves all the successors as nodes
					
					if(!isInList(currentState, explored)){
						
						Node<PuzzleMove, EightPuzzle> node = new Node(state, currentNode);
						
						frontier.add(node); //<------------------adds it to the frontiers list
					}

					
				}
				
				explored.add(currentState);
				currentNode = pop();			
				currentState = currentNode.getData().getState();
			}
			
			return failed;
			
		}
		


	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}
