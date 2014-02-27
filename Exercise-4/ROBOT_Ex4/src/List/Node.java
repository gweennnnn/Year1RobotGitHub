package List;

import java.util.List;

import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;


public class Node<_action, _state> {
	private Node<_action, _state> predecessor;
	private _action move;
	private _state state;
	
	public Node(_action move, _state state) {
		this(move, state, null);
	}
	
	public Node(_action move, _state state, Node<_action, _state> predecessor) {
		super();
		this.predecessor = predecessor;
		this.move = move;
		this.state = state;
	}
	

	public void getSolutionList(List<_action> solutionList)
	{
		Node<_action, _state> currNode = this;
		
		while(currNode.getMove() != null)
		{
			solutionList.add(0, currNode.getMove());
			currNode = currNode.getPredecessor();
		}
//		if()
//		predecessor.getSolutionList(solutionList, startAction);
//		solutionList.add(getMove());

	}


	public Node<_action, _state> getPredecessor() {
		return predecessor;
	}

	public _action getMove() {
		return move;
	}

	public _state getState() {
		return state;
	}
}
