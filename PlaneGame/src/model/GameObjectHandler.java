package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.xml.xpath.XPath;


public class GameObjectHandler {
	private List<GameObject> stoneBarrierList = new ArrayList<>();
	private List<GameObject> bonusBarrierList = new ArrayList<>();
	private List<GameObject> lightningBarrierList = new ArrayList<>();
	private List<GameObject> workList = new ArrayList<>();
	private List<GameObject> whizbangList = new ArrayList<>();
	private int numberOfBarriers;
	private GameObject barrier;
	private GameObject whizbang;
	private GameStatus gameStatus = GameStatus.PLAY;
	private List<GameObject> typeList = new ArrayList<>();
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
	
	public List<GameObject> createBonusList() {
		for (numberOfBarriers = 0; numberOfBarriers < startBonus * level * 2; numberOfBarriers++) {		
			barrier = new BonusBarrier(length);			
			bonusBarrierList.add(barrier);
		}			
		return bonusBarrierList;		
	}
	
	public List<GameObject> createStoneList() {
		for (numberOfBarriers = 0; numberOfBarriers < startBarrier * level; numberOfBarriers++) {
			barrier = new StoneBarrier(length);
			stoneBarrierList.add(barrier);
		}
		return stoneBarrierList;
	}
	
	public List<GameObject> createLightningList() {
		for (numberOfBarriers = 0; numberOfBarriers < startBarrier * level; numberOfBarriers++) {
			barrier = new LightningBarrier(length);
			lightningBarrierList.add(barrier);
		}
		return lightningBarrierList;
	}
	
	public void changeLocation() {
		whizbangStatus();
		
		checkList(bonusBarrierList);
		checkList(lightningBarrierList);
		checkList(stoneBarrierList);
		
		changeList(bonusBarrierList);
		bonusBarrierList.clear();
		bonusBarrierList.addAll(typeList);
		
		changeList(stoneBarrierList);
		stoneBarrierList.clear();
		stoneBarrierList.addAll(typeList);
		
		changeList(lightningBarrierList);
		lightningBarrierList.clear();
		lightningBarrierList.addAll(typeList);
		
		changeList(whizbangList);
		whizbangList.clear();
		whizbangList.addAll(typeList);
	}
	
	private void checkList(List<GameObject> list) {
		Iterator<GameObject> iterator = list.iterator();
		
		while(iterator.hasNext()) {
			if (iterator.next().getXLocation() <= 0) {
				iterator.remove();
			}
		}
	}
	
	private void changeList(List<GameObject> barrierList) {
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
	
	public List<GameObject> getWhizbangList() {
		return whizbangList;
	}
	
	public GameStatus checkStatus(int xPlane, int yPlane) {
		this.xPlane = xPlane;
		this.yPlane = yPlane;
		
		bonusStatus();
		//whizbangStatus();
		barrierListStatus(lightningBarrierList);
		barrierListStatus(stoneBarrierList);
		
		if (bonusBarrierList.isEmpty() & stoneBarrierList.isEmpty() & lightningBarrierList.isEmpty()) {
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
					
		}
	}
	
	private void whizbangStatus() {
		if (!whizbangList.isEmpty()) {
			for (int i = 0; i < whizbangList.size(); i++) {
				whizbang = whizbangList.get(i);
				for (int j = 0; j < stoneBarrierList.size(); j++) {
					barrier = stoneBarrierList.get(j);
					if ((barrier.getXLocation() == whizbang.getXLocation()) & 
							(barrier.getYLocation() - whizbang.getYLocation() > -1) &
							(barrier.getYLocation() - whizbang.getYLocation() < 21)) {
						barrier.setNullPosition();
						whizbang.setNullPosition();
					}
				}	
			}
		}
	}
	
	private void barrierListStatus(List<GameObject> barrierList) {
		
		for (int i = 0; i < barrierList.size(); i++) {
			barrier = barrierList.get(i);
			xForBarrier = barrier.getXLocation();
			yForBarrier = barrier.getYLocation();
			
			if((yForBarrier - yPlane > -20) & (yForBarrier - yPlane < 15) & 
					(xForBarrier - xPlane > -15) & (xForBarrier - xPlane < 15)) {
				gameStatus = GameStatus.LOSS;
			}
						
		}			
	}
}
