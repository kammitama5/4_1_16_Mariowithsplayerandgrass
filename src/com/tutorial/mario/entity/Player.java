package com.tutorial.mario.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;
import com.tutorial.mario.tile.Tile;

public class Player extends Entity{

	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.player.getBufferedImage(), x, y, width, height, null);
		
	}

	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		if(x<=0) x = 0;
		if(y<=0) y = 0;
		if (x+width>=1080) x = 1080-width;
		if (y+height>=771) y = 771-height;
		for(Tile t:handler.tile){
			if(!t.solid) break;
			if(t.getId()==Id.wall){
				if(getBoundsTop().intersects(t.getBounds())){
					setVelY(0);
				
				}
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelY(0);
					y = t.getY()-t.height;
					if(falling) falling = false;
				}
				if(getBoundsLeft().intersects(t.getBounds())){
					setVelX(0);
					x = t.getX() + t.width;
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelX(0);
					x = t.getX()-t.height;
				}
			}
		}
		if (jumping){
			gravity -=0.1;
			setVelY((int)-gravity);
			if(gravity<=0.0){
				jumping = false;
				falling = true;
			}
		}
		if(falling){
			gravity+=0.1;
			setVelY((int)gravity);
		}
	}

}
