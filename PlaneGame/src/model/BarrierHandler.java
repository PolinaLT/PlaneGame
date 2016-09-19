package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BarrierHandler {
	private List<Barrier> barrierList = new ArrayList<>();
	private List<Barrier> workList = new ArrayList<>();
	private int numberOfBarriers;
	Barrier barrier;
	GameStatus gameStatus = GameStatus.PLAY;
	int winStatus = 0;
	
	public BarrierHandler() {
		
	}

	
	public List<Barrier> createBarrierList() {
		barrierList = new ArrayList<>();
		for (numberOfBarriers = 0; numberOfBarriers < 500; numberOfBarriers++) {
			barrier = new Barrier();
			barrierList.add(barrier);
			System.out.println(barrier.getType() + " " + barrier.getXLocation() + " " + barrier.getYLocation());
		}		
		
		return barrierList;		
	}
	
	public List<Barrier> changeLocation() {
		/*Iterator<Barrier> iterator = barrierList.iterator();
		while (iterator.hasNext()) {
			barrier = iterator.next();
			barrier.setXLocation(5);
			
		}*/
		for (numberOfBarriers = 0; numberOfBarriers < 500; numberOfBarriers++) {
			barrier = barrierList.get(numberOfBarriers);
			barrier.setXLocation(5);
			workList.add(barrier);
			System.out.println(barrier.getType() + " " + barrier.getXLocation() + " " + barrier.getYLocation());
		}
		barrierList.clear();
		barrierList.addAll(workList);
		workList.clear();
		
		return barrierList;		
	}

	public GameStatus checkStatus(int xPlane, int yPlane) {
		for (int i = 0; i < barrierList.size(); i++) {
			barrier = barrierList.get(i);
			for (int y = yPlane; y < yPlane + 15; y++) {
				if (barrier.getXLocation() == xPlane && barrier.getYLocation() == y) {
					gameStatus = GameStatus.LOSS;
					break;
				}
				if (barrier.getXLocation() < 0) {
					winStatus--;
				}
				else {
					winStatus++;
				}
			}
		}
		if (winStatus < 0) {
			gameStatus = GameStatus.WIN;
		}
		return gameStatus;
	}


}
