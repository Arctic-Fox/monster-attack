package com.kcs.warhammer.som.simulator;

import java.util.Date;
import java.util.Random;

public class MonstarAttack {

	private static Random generator = new Random(new Date().getTime());
	
	public static void main(String[] args) {
		int ITERATIONS = 10000;
		int monsterStrength = Integer.valueOf(args[0]);
		int numberOfMonsters = Integer.valueOf(args[1]);
		
		int turn = 1;
		int wallWounds = 20;
		int attackerVictory = 0;
		
		
		for(int t = 0; t < ITERATIONS; t++){
				
		while(wallWounds > 0 && turn < 7){
			for(int i = 0; i < numberOfMonsters; i++){
				for(int j = 0; j < 3; j++){
					int attack = generator.nextInt(6) + 1;
					if(monsterStrength < 7 && attack == 6){
						wallWounds = resolveHit(wallWounds);
					}
					if(monsterStrength == 7 && attack > 4){
						wallWounds = resolveHit(wallWounds);
					}
					if(monsterStrength == 8 && attack > 3){
						wallWounds = resolveHit(wallWounds);
					}
					if(monsterStrength == 9 && attack > 2){
						wallWounds = resolveHit(wallWounds);
					}
					if(monsterStrength == 10 && attack > 1){
						wallWounds = resolveHit(wallWounds);
					}
				}//j loop
			}//i loop
			turn ++;
		}//while
		
			if(wallWounds > 0){
				System.out.println("Attackers fail!  Wounds remaining:" + wallWounds);
			
			}
			else{
				System.out.println("The walls have been breached in " + turn + " turns!");
				attackerVictory ++;
			}
			turn = 0;
			wallWounds = 20;
		}
		
		System.out.println("Attackers win " + attackerVictory/100 + "% of the time.");
	}
	
	private static int resolveHit(int wounds){
		int wardSave = generator.nextInt(6) + 1;
		if(wardSave > 3 ){
			return wounds;
		}
		else return wounds - 1;
	}

}
