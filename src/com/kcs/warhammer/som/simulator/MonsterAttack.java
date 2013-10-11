package com.kcs.warhammer.som.simulator;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class MonsterAttack {

	private static Random generator = new Random(new Date().getTime());
	private static Scanner scanner = new Scanner(System.in);
	private static ArrayList<Monster> monsters = new ArrayList<Monster>();
	private static int ITERATIONS = 10000;
	private static int WALLTOUGHNESS = 8;
	private static int wallWounds = 20;
	private static int totalWoundsInflicted = 0;
	
	private static String readLine(){
		System.out.print(":");
		String input = "";
	    input = scanner.nextLine();
	    return input;
	}
	
	private static boolean checkInput(String[] stats){
		int t1, t2;
		try{
			t1 = Integer.valueOf(stats[0]);
			t2 = Integer.valueOf(stats[1]);
		}
		catch (Exception e){
			System.out.println("Input not understood.  Please try again.");
			return false;
		}
		if(t1 > 10){
			System.out.println("Monster strength cannot exceed 10.  Please try again.");
			return false;
		}
		return true;
	}
	
	private static void getMonsters(){
		String[] stats = {" ", " "};
		System.out.println("Eneter a new monster by giving its strength and number of attacks.");
		System.out.println("For example, to enter a monster with Strength 5 and 4 attacks ");
		System.out.println("enter '5 4' If there are no more, enter 'x'");
		
		stats = readLine().split(" ");
		
		while(!stats[0].equals("x")){

			if(checkInput(stats)){
				monsters.add(new Monster(Integer.valueOf(stats[0]), Integer.valueOf(stats[1])));
			}
			stats = readLine().split(" ");
		}
	}
	
	private static void monstersAttack(){
		for(Monster monster : monsters){
			for(int attack = 0; attack < monster.getAttacks(); attack++){
				int toWoundRoll = generator.nextInt(6) + 1;
				int STdiff = WALLTOUGHNESS - monster.getStrength();
				
				if((STdiff > 1 && toWoundRoll == 6) ^
				   (STdiff == 1 && toWoundRoll >= 5) ^
				   (STdiff == 0 && toWoundRoll >= 4) ^
				   (STdiff == -1 && toWoundRoll >= 3) ^
				   (STdiff < -1 && toWoundRoll >= 2)){
					if(!wardSave()){
						wallWounds --;
						totalWoundsInflicted ++;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int attackerVictory = 0;
		
		System.out.println("Welcome to the monster simulator.");
		
		getMonsters();
		
		for(int iteration = 0; iteration < ITERATIONS; iteration++){
			wallWounds = 20;
			int turn = 1;

			while(wallWounds > 0 && turn < 7){
				monstersAttack();
				if(wallWounds < 1){
					attackerVictory ++;
				}
				turn ++;
			}
			if(wallWounds > 0){
				System.out.println("Attackers fail!  Wounds remaining:" + wallWounds);	
			}
			else{
				System.out.println("The wall has been breached in " + turn + " turns!");
			}
		}

		System.out.println("Attackers win " + attackerVictory/100 + "% of the time in " + ITERATIONS + " iterations.");
		System.out.println("Average wounds per game: " + totalWoundsInflicted/ITERATIONS);
	}
	
	private static boolean wardSave(){
		int wardSave = generator.nextInt(6) + 1;
		if(wardSave > 3 ){
			return true;
		}
		else return false;
	}
}
