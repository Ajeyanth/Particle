import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;;

public class GraphicsExV2  {

	static ParticlePanel panel ;
	static JLabel label,recovered;
	static JLabel time;
	static JTextField numberParticlesField,speedField,infectionField;
	static Timer timer;
	static Timer timer2;
	static int infectionRate=0;
	static int timeElapsed;
	static int timeElapsedms;
	static double checkInfected=0;

	
	public static void main(String[] args) {
	
			particleSimulation();
		
			//ScratchPad pad=new ScratchPad();
			//pad.test();
	}
	
	public static void particleSimulation() {
		JFrame window=new JFrame();
		window.setSize(1000,1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		JButton button=new JButton("Start");
		button.setSize(50, 30);
		JButton resetButton=new JButton("Reset");
		button.setSize(50, 30);
		JButton pause=new JButton("Pause");
		button.setSize(50, 30);
		label=new JLabel("Infections=");
		recovered= new JLabel("Recovered=");

		time=new JLabel("Time=");
		numberParticlesField=new JTextField();
		numberParticlesField.setPreferredSize(new Dimension(70, 30));
		speedField=new JTextField();
		speedField.setPreferredSize(new Dimension(70, 30));
		infectionField=new JTextField();
		infectionField.setPreferredSize(new Dimension(70, 30));
		
		JPanel navPanel=new JPanel();
		navPanel.setPreferredSize(new Dimension(100, 850));
		navPanel.setBackground(Color.GRAY);
		

		
		
		panel = new ParticlePanel();
		panel.setPreferredSize(new Dimension(1200, 850));
		panel.setBackground(Color.white);
		
		JPanel outerPanel=new JPanel();
		outerPanel.setSize(800,800);
		outerPanel.setBackground(Color.BLUE);

		timer();
        animate();
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				   timer.start();
					System.out.println("I am clicked");
					
				}
			

		});
		
         pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				   timer.stop();
					
					
				}
			

		});
         
         /*pause.addKeyListener(new KeyAdapter() {
		 
        	 public void keyPressed(KeyEvent e) {
        	        int key = e.getKeyCode();
        	        if (key == KeyEvent.VK_P) {
        	            timer.stop();
        	            System.out.println("Pause");
        	        }
			
             }
 			

 		});*/
		
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					timer.stop();
					int numberOfParticles=Integer.parseInt(numberParticlesField.getText());
					int speed=Integer.parseInt(speedField.getText());
					 infectionRate=Integer.parseInt(infectionField.getText());
					panel.setNumberofParticles(numberOfParticles);
					panel.resetParticles(speed,infectionRate);
					System.out.println("Reset clicked");
					timeElapsed=0;
				}

		});
		
		
		navPanel.add(button);
		navPanel.add(resetButton);
		navPanel.add(pause);

		navPanel.add(numberParticlesField);
		navPanel.add(speedField);
		navPanel.add(infectionField);
		navPanel.add(label);
		navPanel.add(time);
		navPanel.add(recovered);


		outerPanel.add(navPanel);
		
		//Component particlePanel;
		outerPanel.add(panel);
		window.add(outerPanel);
		window.pack();
	}

	
	public static void animate() {
		
		timer=new Timer(20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("timerLoop");
				
				for (int i=0;i<panel.particles.length;i++) {
					panel.particles[i].moveV2();
					
				}
				panel.repaint();
				for (int i=0;i<panel.particles.length;i++) {
					//panel.particles[i].displayParticle();
					for(int j=i+1;j<panel.particles.length;j++) {
					
					panel.particles[i].checkCollideByDistanceV1(panel.particles[j]);
						
					}
				}
				//System.out.println("Infections=" +panel.getTotalCollisions());
				label.setText(String.valueOf("Infections="+panel.getTotalCollisions()));
				recovered.setText(String.valueOf("Recovered="+panel.getTotalRecovered()));

				

				if (panel.getTotalCollisions()==(panel.particles.length)) {
					timer.stop();
				}
				
				//if ((panel.getTotalCollisions())==(panel.particles.length/2)){
				//	System.out.println("")
				//}
				timeElapsed+=1;
				if (timeElapsed % 5==0) {
					timeElapsedms+=1;
				}
				if (timeElapsedms==10) {
					timeElapsedms=0;
				}
				time.setText(String.valueOf("Time="+(timeElapsed/50)+":"+"0"+timeElapsedms));
				
				checkInfected+=1;

               
				if(checkInfected==50) {
					for (int i=0;i<panel.particles.length;i++) {
                    
				    if(panel.particles[i].infected==1) {		
					panel.particles[i].infectionPeriod+=1;
					System.out.println(panel.particles[i].infectionPeriod);
					if(panel.particles[i].infectionPeriod==5) {
						panel.particles[i].checkInfected();
						System.out.println("Check infected");

						panel.particles[i].infectionPeriod=0;
					}

					}
				    
					checkInfected=0;
				}
				}
				
				
			}
			
		});
	}
	
	public static void timer() {
		timer2=new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
			
		});
	}
	
	
		

}