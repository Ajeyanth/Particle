import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class ParticleV2 {
	
	double x;
	double y;
	double xCentre;
	double yCentre;
	Ellipse2D.Double particleImage;
	int diameter=8;
	int radius=diameter/2;
	int directionX=1;
	int directionY=1;
	int speed;
	Random rnd=new Random();
	int xCord,yCord;
	String particleName;
	int maxSize;
	int infections=0;
	Color particleColor;
	double distance=0;
	int infected=0;
	
	



	
	public ParticleV2(double x,double y) {
		this.x=x;
		this.y=y;
		//this.xCentre=xCentre;
		//this.yCentre=yCentre;
		//speed=rnd.nextInt(5)+1;
		particleColor=Color.blue;
	}
	
	
	public void draw(Graphics2D g) {
		g.setColor(particleColor);
		particleImage=new Ellipse2D.Double(x, y, diameter, diameter);
		g.fill(particleImage);
		g.draw(particleImage);
	}
	
	public void move() {
		if (x>=1199) directionX=directionX*-1;
		if (y>=849)directionY=directionY*-1;
		if (x<=0) directionX=directionX*-1;
		if (y<=0) directionY=directionY*-1;
		
		
		
		x+=directionX*speed;
		y+=directionY*speed;
		
	}
	
	
	
	public void checkCollide(Particle particle) {
		if(this.x==particle.x && this.y==particle.y) {
			this.directionX=-1*this.directionX;
			this.directionY=-1*this.directionY;
			particle.directionX=-1*this.directionX;
			particle.directionY=-1*this.directionY;
			this.particleColor=Color.red;
			particle.particleColor=Color.red;
			//collisions+=1;
	
		}

    }
	
	public void checkCollideV2(Particle particle) {
		if(this.particleImage.intersects(particle.x, particle.y, radius, radius)) {
			this.directionX=-1*this.directionX;
			if (directionX>0) x+=15;
			if (directionX<0) x-=15;
			this.directionY=-1*this.directionY;
			if (directionY>0) y+=15;
			if (directionY<0) y-=15;
			
			particle.directionX=-1*this.directionX;
			particle.directionY=-1*this.directionY;
			this.particleColor=Color.red;
			particle.particleColor=Color.red;
			//collisions+=1;
			
			//particleImage.
	
		}

    }
	
	
	
	public void checkCollideByDistance(Particle particle) {
		xCentre=this.x+radius;
		yCentre=this.y+radius;
        //distance=Math.sqrt(Math.pow(this.xCentre-particle.xCentre, 2)+(Math.pow(this.yCentre-particle.yCentre, 2)));
		if (distance<=10) {
			this.directionX=-1*this.directionX;
			if (directionX>0) x+=15;
			if (directionX<0) x-=15;
			this.directionY=-1*this.directionY;
			if (directionY>0) y+=15;
			if (directionY<0) y-=15;
			particle.directionX=-1*this.directionX;
			particle.directionY=-1*this.directionY;
			
			/*if ((this.particleColor.equals(Color.red) |  (particle.particleColor.equals(Color.red) )))  {
				if (!(this.particleColor.equals(Color.red) && particle.particleColor.equals(Color.red))) {
					this.particleColor=Color.red;
					particle.particleColor=Color.red;
					collisions+=1;*/
             if (this.infected==1 && particle.infected==1) {
				
			}
             
			else if (this.infected==1 ) {
				particle.infected=1;
				particle.particleColor=Color.red;
				//infections+=1;

			}
			else if (particle.infected==1) {
				this.infected=1;
				this.particleColor=Color.red;
				//infections+=1;
			}	
		
		
		}
		

	}
			
		
}
	


		
