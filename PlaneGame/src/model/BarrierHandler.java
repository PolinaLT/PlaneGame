package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class BarrierHandler {
	private List<Barrier> planeBarrierList = new ArrayList<>();
	private List<Barrier> bonusBarrierList = new ArrayList<>();
	private List<Barrier> lightningBarrierList = new ArrayList<>();
	private List<Barrier> workList = new ArrayList<>();
	private int numberOfBarriers;
	private Barrier barrier;
	private GameStatus gameStatus = GameStatus.PLAY;
	private int winStatus = 0;
	private int type;
	private List<Barrier> typeList = new ArrayList<>();
	
	public BarrierHandler() {
		
	}

	
	public List<Barrier> createBonusList() {
		for (numberOfBarriers = 0; numberOfBarriers < 100; numberOfBarriers++) {		
			barrier = new BonusBarrier();			
			bonusBarrierList.add(barrier);
		}			
		return bonusBarrierList;		
	}
	
	public List<Barrier> createPlaneList() {
		for (numberOfBarriers = 0; numberOfBarriers < 100; numberOfBarriers++) {
			barrier = new PlaneBarrier();
			planeBarrierList.add(barrier);
		}
		return planeBarrierList;
	}
	
	public List<Barrier> createLightningList() {
		for (numberOfBarriers = 0; numberOfBarriers < 300; numberOfBarriers++) {
			barrier = new LightningBarrier();
			lightningBarrierList.add(barrier);
		}
		return lightningBarrierList;
	}
	
	public void changeLocation() {
		changeList(bonusBarrierList);
		bonusBarrierList.clear();
		bonusBarrierList.addAll(typeList);
		changeList(planeBarrierList);
		planeBarrierList.clear();
		planeBarrierList.addAll(typeList);
		changeList(lightningBarrierList);
		lightningBarrierList.clear();
		lightningBarrierList.addAll(typeList);
	}
	
	public void changeList(List<Barrier> barrierList) {
		for (numberOfBarriers = 0; numberOfBarriers < barrierList.size(); numberOfBarriers++) {
			barrier = barrierList.get(numberOfBarriers);
			barrier.setXLocation();
			workList.add(barrier);
		}
		typeList.clear();
		typeList.addAll(workList);
		workList.clear();
	}

	/*public GameStatus checkStatus(int xPlane, int yPlane) {
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
	}*/


}
