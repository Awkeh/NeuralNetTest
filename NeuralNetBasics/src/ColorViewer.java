import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorViewer extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JButton       btn;
	private JLabel        label;
	private JColorChooser picker;
	private JPanel        pane;
	
	public ColorViewer()
	{
		super("Hello machine learning!");
		
		NeuralNet.train(100000);
		
		init();
		org();
		listen();
		setVisible(true);
	}
	
	private void init()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		btn    = new JButton("Print RGB array (Training purposes)");
		label  = new JLabel("Hello Machine Learning!");
		label.setFont(new Font("Arial", Font.BOLD, 24));
		picker = new JColorChooser();
		pane   = new JPanel();
		pane.setLayout(new GridBagLayout());
	}
	
	private void org()
	{
		picker.setPreviewPanel(new JPanel());
		pane.add(label);
		add(picker, BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
	}
	
	private void listen()
	{
		// This event is obsolete
		btn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Color color = picker.getColor();
				System.out.printf("{ %d.0f, %d.0f, %d.0f },\n", color.getRed(), color.getGreen(), color.getBlue());
			}
			
		});
		
		picker.getSelectionModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				Color color = picker.getColor();
				pane.setBackground(color);
				
				float r = (float) color.getRed();
				float g = (float) color.getGreen();
				float b = (float) color.getBlue();
				
				r /= 255.0f;
				g /= 255.0f;
				b /= 255.0f;
				
				boolean light = NeuralNet.isLight(r, g, b);
				
				if(light)
					label.setForeground(Color.BLACK);
				else
					label.setForeground(Color.WHITE);
				
			}
		
		});
	}
	
}
