import java.awt.Color;


import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Particle extends JPanel   {
	
	float x;
	float y;
	Ellipse2D.Double particleImage;
	int diameter=60;
	int radius=diameter/2;
	int directionX=1;
	int directionY=1;
	int direction=1;
	float speed;
	Random rnd=new Random();
	int xCord,yCord;
	String particleName;
	int maxSize;
	int infections=0;
	Color particleColor;
	double distance=0;
	int infected=0;
	double angleX=Math.toRadians(rnd.nextInt(360));
	double angleY=Math.toRadians(rnd.nextInt(360));


	double bounce=0;
	double bounceBack=0;
	double theta=0;
	int setDirection=0;
	double velX=0;
	double velY=0;
	double infectionPeriod=0;
	double mass;
	double collisions=0;
	




	
	public Particle(double x,double y) {
		
	
		this.x=(float) x;
		this.y=(float) y;
		
		//speed=rnd.nextInt(5)+1;
		particleColor=Color.blue;
		
	
		
	}
	
	
	
	
	public void draw(Graphics2D g) {
		g.setColor(particleColor);
		particleImage=new Ellipse2D.Double(x, y, diameter, diameter);
		//g.fill(particleImage);
		//g.drawString(particleName, (int)this.x+radius, (int)this.y+radius);
		g.draw(particleImage);
	}
	
	
	
	public void moveV1() {
		if ((x+diameter)>=1180) {
			directionX=directionX*-1;
			x-=10;
		}
		if ((y+diameter)>=830) {
			directionY=directionY*-1;
			y-=10;
		}
		if (x<=0) {
			directionX=directionX*-1;
			x+=10;
		}
		if (y<=0) {
			directionY=directionY*-1;
			y+=10;
		}
		
		angleX=Math.cos(angleX);
		angleY=Math.sin(angleY);
		
		
		
		x+=directionX*speed;
		//y+=directionY*speed;
		
		
		
	}
	
	public void beforeCollision(Particle particle) {
		particle.speed=0;
		this.speed=10;
		this.directionX=-1;
		particle.directionX=-1;
	}
	
	public void colliding(Particle particle) {
		 distance=Math.sqrt(Math.pow((this.x+radius)-(particle.x+radius), 2)+(Math.pow((this.y+radius)-(particle.y+radius), 2)));
		 double u1;
		 double u2;


	        bounce=diameter-distance;
			if (distance<=diameter) {
				if (bounce>0) {
					 u1=this.speed;
					 u2=particle.speed;
					this.speed=(float) ((((this.mass-particle.mass)/(this.mass+particle.mass))*u1) + (((2*particle.mass)/(this.mass+particle.mass)*u2)));
					particle.speed=(float) (((2*this.mass)*u1)/(this.mass+particle.mass) + ((particle.mass-this.mass)*u2)/(this.mass+particle.mass));
					System.out.println("Particle1 speed = " + this.speed);
					System.out.println("Particle2 speed = " + particle.speed);
					if(particle.directionX!=this.directionX) {
						this.directionX*=-1;
						particle.directionX*=-1;

					}
                    collisions+=1;
                    System.out.println("Collisions=" + collisions);
					
					

					
				}
				
			}

		
		
	}
	
	public void moveV2() {
		if ((x+diameter)>=1180) {
			directionX=directionX*-1;
			x-=1;
		}
		if ((y+diameter)>=830) {
			directionY=directionY*-1;
			y-=1;
		}
		if (x<=0) {
			directionX=directionX*-1;
			x+=1;
		}
		if ( y<=0) {
			directionY=directionY*-1;
			y+=1;
		}
		
		
		x+=directionX*speed;
		y+=directionY*speed;
		bounceBack=0;
		
		
	}
	
	public void checkCollideV3(Particle particle) {
		double xCentre=this.x+radius;
		double yCentre=this.y+radius;
        //distance=Math.sqrt(Math.pow(this.xCentre-(particle.x+radius), 2)+(Math.pow(this.yCentre-(particle.y+radius), 2)));
       // theta=Math.atan((this.yCentre-(particle.y+radius))/(this.xCentre-(particle.xCentre+radius)));
       
        if(distance<=diameter) {
    		if (distance <diameter) {
    			bounceBack=(distance/2);
    			/*
    			this.x+=(bounceBack*Math.cos(theta)*-1*directionX);
    		    this.y+=(bounceBack*Math.sin(theta)*-1*directionY);
    		    particle.x+=(bounceBack*Math.cos(theta)*-1*directionX);
    		    particle.y+=(bounceBack*Math.sin(theta)*-1*directionY);
    		    */
    		    System.out.println("bounceback="+bounceBack);
    		    System.out.println("theta="+theta);
    		    particle.bounceBack=bounceBack;
 
    		}
			
    	//  after collision direction is changed in opposite direction  X and Y respectively
    		this.directionX*=-1;     
    		this.directionY*=-1;
    				
    		particle.directionX*=-1;
    		particle.directionY*=-1;
    				
    	         //No action when two infected particles collide
    	    if (this.infected==1 && particle.infected==1) {
    					
    		} 
    	    //If colliding particle is infected then change the other particle to infected 
    	    //and vice versa
    	    else if (this.infected==1 ) {
    	    	particle.infected=1;
    	    	particle.particleColor=Color.red;

    		}
    		else if (particle.infected==1) {
    			this.infected=1;
    			this.particleColor=Color.red;
    		}					
		}  
		
	}   
	
	
	
	
	public void checkCollideV1(Particle particle) {
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
			//collisions+=1;
			
			//particleImage.
	
		}

    }
	
	
	
	public void checkCollideByDistanceV1(Particle particle) {
		
		
        distance=Math.sqrt(Math.pow((this.x+radius)-(particle.x+radius), 2)+(Math.pow((this.y+radius)-(particle.y+radius), 2)));

        bounce=diameter-distance;
		if (distance<=diameter) {
			if (bounce>0){
			this.directionX=-1*this.directionX;
			if (directionX<0) x-=((bounce/2)+12);
			if (directionX>0) x+=((bounce/2)+12);
			
			this.directionY=-1*this.directionY;
			//if (directionY>0) y+=((bounce/2)+12);
			//if (directionY<0) y-=((bounce/2)+12);
			
			
			
			particle.directionX=-1*this.directionX;
			particle.directionY=-1*this.directionY;

				
			}
			
             if (this.infected==1 && particle.infected==1) {
				
			}
             
			else if (this.infected==1 && particle.infected!=2 && particle.infected!=3) {
				particle.infected=1;
				particle.particleColor=Color.red;
				//infections+=1;

			}
			else if (particle.infected==1 && this.infected!=2 && this.infected!=3) {
				this.infected=1;
				this.particleColor=Color.red;
				
				//infections+=1;
			}	
		
		
		}
		

    }
	
	public void checkInfected() {
		
		if(this.infected==1) {
			    
				infected=rnd.nextInt(4);
				if(infected==0) infected=1;
				if(infected==2) this.particleColor=Color.GREEN;
				if(infected==3) this.particleColor=Color.BLACK;
			
			
			
		}

		


	}
	
	
	
	
	
	public void checkCollideByDistanceV2(Particle particle) {
		double xCentre=this.x+radius;
		double yCentre=this.y+radius;
       // distance=Math.sqrt(Math.pow(this.xCentre-particle.xCentre, 2)+(Math.pow(this.yCentre-particle.yCentre, 2)));
		if (distance<=80 ) {
			this.directionX=-1*this.directionX;
			if (directionX>0) x+=25;
			if (directionX<0) x-=25;
			this.directionY=-1*this.directionY;
			if (directionY>0) y+=25;
			if (directionY<0) y-=25;
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


		
	

			
		

	


		
	
	
	


