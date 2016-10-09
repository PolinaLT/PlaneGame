package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.xml.xpath.XPath;


public class GameObjectHandler {
	private List<Barrier> planeBarrierList = new ArrayList<>();
	private List<Barrier> bonusBarrierList = new ArrayList<>();
	private List<Barrier> lightningBarrierList = new ArrayList<>();
	private List<Barrier> workList = new ArrayList<>();
	private List<Whizbang> whizbangList = new ArrayList<>();
	private int numberOfBarriers;
	private Barrier barrier;
	private GameStatus gameStatus = GameStatus.PLAY;
	private int winStatus = 0;
	private List<Barrier> typeList = new ArrayList<>();
	private int xPlane;
	private int yPlane;
	private int level;
	private int xForBarrier;
	private int yForBarrier;
	private int length;
	private final int startLength = 1000;
	private final int changeLength = 500;
	private static int bonusStatus = 0;
	private final int startBonus = 3;
	private final int startBarrier = 7;
	
	public GameObjectHandler(int level) {
		this.level = level;
		length = startLength + changeLength * (level - 1);
	}
	
	public List<Barrier> createBonusList() {
		for (numberOfBarriers = 0; numberOfBarriers < startBonus * level * 2; numberOfBarriers++) {		
			barrier = new BonusBarrier(length);			
			bonusBarrierList.add(barrier);
		}			
		return bonusBarrierList;		
	}
	
	public List<Barrier> createPlaneList() {
		for (numberOfBarriers = 0; numberOfBarriers < startBarrier * level; numberOfBarriers++) {
			barrier = new PlaneBarrier(length);
			planeBarrierList.add(barrier);
		}
		return planeBarrierList;
	}
	
	public List<Barrier> createLightningList() {
		for (numberOfBarriers = 0; numberOfBarriers < startBarrier * level; numberOfBarriers++) {
			barrier = new LightningBarrier(length);
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

	public void addWhizbang(Whizbang whizbang) {
		whizbangList.add(whizbang);
	}
	
	public List<Whizbang> getWhizbangList() {
		return whizbangList;
	}
	
	/////////////////////////
	// Сравнение для выстрелов
	///////////////////////////
	
	public GameStatus checkStatus(int xPlane, int yPlane) {
		this.xPlane = xPlane;
		this.yPlane = yPlane;
		
		bonusStatus();
		barrierListStatus(lightningBarrierList);
		barrierListStatus(planeBarrierList);
		
		if (winStatus < 0) {
			gameStatus = GameStatus.WIN;
		}
		
		return gameStatus;
	}
	
	public static int bonusReport() {
		return bonusStatus;
	}
	
	private void bonusStatus() {
		for (int i = 0; i < bonusBarrierList.size(); i++) {
			barrier = bonusBarrierList.get(i);
			xForBarrier = barrier.getXLocation();
			yForBarrier = barrier.getYLocation();
			
			if((yForBarrier - yPlane > -20) & (yForBarrier - yPlane < 15) & 
					(xForBarrier - xPlane == -19)) {
				bonusStatus ++;
				System.out.println(bonusStatus);
			}
			
			if (xForBarrier < 0) {
				winStatus --;
			}
			else {
				winStatus ++;
			}			
		}
	}
	
	private void barrierListStatus(List<Barrier> barrierList) {
		
		for (int i = 0; i < barrierList.size(); i++) {
			barrier = barrierList.get(i);
			xForBarrier = barrier.getXLocation();
			yForBarrier = barrier.getYLocation();
			
			if((yForBarrier - yPlane > -20) & (yForBarrier - yPlane < 15) & 
					(xForBarrier - xPlane > -15) & (xForBarrier - xPlane < 15)) {
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
	}
}
