package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.xml.xpath.XPath;


public class BarrierHandler {
	private List<Barrier> planeBarrierList = new ArrayList<>();
	private List<Barrier> bonusBarrierList = new ArrayList<>();
	private List<Barrier> lightningBarrierList = new ArrayList<>();
	private List<Barrier> workList = new ArrayList<>();
	private int numberOfBarriers;
	private Barrier barrier;
	private GameStatus gameStatus = GameStatus.PLAY;
	private int winStatus = 0;
	private List<Barrier> typeList = new ArrayList<>();
	private int xPlane;
	private int yPlane;
	
	public BarrierHandler() {
		
	}

	
	public List<Barrier> createBonusList() {
		for (numberOfBarriers = 0; numberOfBarriers < 10; numberOfBarriers++) {		
			barrier = new BonusBarrier(6000);			
			bonusBarrierList.add(barrier);
		}			
		return bonusBarrierList;		
	}
	
	public List<Barrier> createPlaneList() {
		for (numberOfBarriers = 0; numberOfBarriers < 10; numberOfBarriers++) {
			barrier = new PlaneBarrier(6000);
			planeBarrierList.add(barrier);
		}
		return planeBarrierList;
	}
	
	public List<Barrier> createLightningList() {
		for (numberOfBarriers = 0; numberOfBarriers < 30; numberOfBarriers++) {
			barrier = new LightningBarrier(6000);
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

	public GameStatus checkStatus(int xPlane, int yPlane) {
		this.xPlane = xPlane;
		this.yPlane = yPlane;
		//System.out.println(xPlane + " " + yPlane);
		
		barrierListStatus(bonusBarrierList);
		barrierListStatus(lightningBarrierList);
		barrierListStatus(planeBarrierList);
		
		if (winStatus < 0) {
			gameStatus = GameStatus.WIN;
		}
		//System.out.println(gameStatus);
		return gameStatus;
	}
	
	private void barrierListStatus(List<Barrier> barrierList) {
		/*for (int i = 0; i < barrierList.size(); i++) {
			barrier = barrierList.get(i);
			int xForBarrier = barrier.getXLocation();
			int yForBarrier = barrier.getYLocation();
			
			for (int a = xPlane; a < xPlane + 15; a++) {
				for (int b = yPlane; b < yPlane + 15; b++) {
					for (int c = xForBarrier; c < xForBarrier + 20; c++) {
						for (int d = yForBarrier; d < yForBarrier + 20; d++) {
							if (a == c & b == d) {
								gameStatus = GameStatus.LOSS;
								System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
								System.out.println(a);
								System.out.println(b);
								System.out.println(c);
								System.out.println(d);
							}
							
							if (d < 0) {
								winStatus --;
							}
							else {
								winStatus ++;
							}
						}
					}
				}
			}
			
			
		}*/
		
		
		for (int i = 0; i < barrierList.size(); i++) {
			barrier = barrierList.get(i);
			int xForBarrier = barrier.getXLocation();
			int yForBarrier = barrier.getYLocation();
			
			if((yForBarrier - yPlane > -20) & (yForBarrier - yPlane < 15) & 
					(xForBarrier - xPlane > -15) & (xForBarrier - xPlane < 15)) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!");
				gameStatus = GameStatus.LOSS;
			}
			
			else {
				if (xForBarrier < 0) {
					winStatus --;
				}
				else {
					winStatus ++;
				}
			}
			
		}
		
		
		
		/*for (int i = 0; i < barrierList.size(); i++) {
			barrier = barrierList.get(i);
			int xForBarrier = barrier.getXLocation();
			
			for (int yForPlane = yPlane; yForPlane < (yPlane + 49); yForPlane++) {
				
				for (int yForBarrier = barrier.getYLocation(); yForBarrier < (barrier.getYLocation() + 20); yForBarrier++) {
					
					if (xForBarrier == xPlane && yForBarrier == yForPlane) {
						gameStatus = GameStatus.LOSS;
						break;
					}
					
					if (xForBarrier < 0) {
						winStatus--;
					}
					else {
						winStatus++;
					}
					
				}
				
			}
		
		}*/		
	}


}
