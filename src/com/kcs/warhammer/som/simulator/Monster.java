package com.kcs.warhammer.som.simulator;

public class Monster {
	
	private int attacks, strength;

	public Monster(int attacks, int strength){
		this.attacks = attacks;
		this.strength = strength;
	}

	public int getAttacks() {
		return attacks;
	}

	public void setAttacks(int attacks) {
		this.attacks = attacks;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

}
