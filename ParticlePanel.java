import java.awt.Color;


import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Line2D;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.swing.*;

public class ParticlePanel extends JPanel{
	Random rnd=new Random();
	double x,y;
	Graphics2D g2;
	int numberOfParticles=100;
	int infectedPosition;
	int infected=0;

	
	Particle particle1,particle2;
	
	Particle[] particles=new Particle[numberOfParticles];
	
	public ParticlePanel() {
		createParticles(5);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//Line2D.Double line=new Line2D.Double(300, 50, 300, 600);
		//g2.draw(line);
		drawParticles(g2);
		
	}
	
	
public void createParticles(int maxSpeed) {
		
		for (int i=0;i<particles.length;i++) {
			x=rnd.nextInt(1200);
			y=rnd.nextInt(850);
			particles[i]=new Particle(x, y);
			int speed=rnd.nextInt(maxSpeed)+1;;
			particles[i].speed=speed;
		}
	}
	
	public void drawParticles(Graphics2D g) {
		for (int i=0;i<particles.length;i++) {
			
			particles[i].draw(g);
			
			
			
			
		}
		
		
	}
	
	public int getTotalCollisions() {
		int totalCollisions=0;
		for (int i=0;i<particles.length;i++) {
			if (particles[i].infected==1) {
				totalCollisions+=1;
			}
			
		}
		return totalCollisions;
		
	}
	
	public int getTotalRecovered() {
		int totalRecovered=0;
		for (int i=0;i<particles.length;i++) {
			if (particles[i].infected==2) {
				totalRecovered+=1;
			}
			
		}
		return totalRecovered;
		
	}
	
	public int getTotalDeaths() {
		int totalDeaths=0;
		for (int i=0;i<particles.length;i++) {
			if (particles[i].infected==3) {
				totalDeaths+=1;
			}
			
		}
		return totalDeaths;
		
	}
	
	public void resetParticles(int maxSpeed,int infectionRate) {
		particles=new Particle[numberOfParticles];
		createParticles(maxSpeed);
		infect(infectionRate);
		repaint();
	}
	
	public void infect(int infectionRate) {
		for (int i=0;i<infectionRate;i++) {
			int infectedPosition;
			infectedPosition=rnd.nextInt(numberOfParticles);
			particles[infectedPosition].particleColor=Color.red;
			particles[infectedPosition].infected=1;
			System.out.println(infectedPosition);
			

            
	
			
		}
		
		
	}
	
	public void setNumberofParticles(int number) {
		numberOfParticles=number;
	}
	
	public void checkInfected() {
		for(int i=0;i<particles.length;i++) {
			if(particles[infectedPosition].infected!=1) {
				System.out.println(particles[i] +" Not infected");
			}
		}
	}
	
	
	
	
	
	
	
	  
	
}


