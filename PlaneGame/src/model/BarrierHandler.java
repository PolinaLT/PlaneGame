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
			//System.out.println(barrier.getType() + " " + barrier.getXLocation() + " " + barrier.getYLocation());
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
			barrier.setXLocation(1);
			workList.add(barrier);
			//System.out.println(barrier.getType() + " " + barrier.getXLocation() + " " + barrier.getYLocation());
		}
		barrierList.clear();
		barrierList.addAll(workList);
		workList.clear();
		
		return barrierList;		
	}

	public GameStatus checkStatus(int xPlane, int yPlane) {
		System.out.println(xPlane + " " + yPlane);
		for (int i = 0; i < barrierList.size(); i++) {
			barrier = barrierList.get(i);
			for (int yForPlane = yPlane; yForPlane < yPlane + 15; yForPlane++) {
				for (int yForBarrier = barrier.getYLocation(); yForBarrier < (barrier.getYLocation() + 20); yForBarrier++) {
					if (barrier.getXLocation() == (xPlane + 15) && yForBarrier == yForPlane) {
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
		}
		if (winStatus < 0) {
			gameStatus = GameStatus.WIN;
		}
		System.out.println(gameStatus);
		return gameStatus;
	}


}
